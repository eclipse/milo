/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.SessionActivityListener;
import org.eclipse.milo.opcua.sdk.client.api.UaSession;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFieldList;
import org.eclipse.milo.opcua.stack.core.types.structured.EventNotificationList;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.NotificationMessage;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusChangeNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.jooq.lambda.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class OpcUaSubscriptionManager implements UaSubscriptionManager {

    public static final UInteger DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH = uint(65535);

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<UInteger, OpcUaSubscription> subscriptions = Maps.newConcurrentMap();

    private final List<SubscriptionListener> subscriptionListeners = Lists.newCopyOnWriteArrayList();

    private final ConcurrentMap<NodeId, AtomicLong> pendingCountMap = Maps.newConcurrentMap();

    private final List<SubscriptionAcknowledgement> acknowledgements = newArrayList();

    private final ExecutionQueue deliveryQueue;
    private final ExecutionQueue processingQueue;

    private final OpcUaClient client;

    public OpcUaSubscriptionManager(OpcUaClient client) {
        this.client = client;

        deliveryQueue = new ExecutionQueue(client.getConfig().getExecutor());
        processingQueue = new ExecutionQueue(client.getConfig().getExecutor());

        client.addSessionActivityListener(new SessionActivityListener() {
            @Override
            public void onSessionInactive(UaSession session) {
                // This allows a session that gets re-activated to immediately start
                // publishing again instead of waiting for outstanding PublishRequests
                // from before the re-activation to expire/timeout.
                pendingCountMap.replace(session.getSessionId(), new AtomicLong(0));
            }
        });
    }

    @Override
    public CompletableFuture<UaSubscription> createSubscription(double requestedPublishingInterval) {
        // Keep-alive every ~10-12s or every publishing interval if longer.
        UInteger maxKeepAliveCount = uint(Math.max(1, (int) Math.ceil(10000.0 / requestedPublishingInterval)));

        // Lifetime must be 3x (or greater) the keep-alive count.
        UInteger maxLifetimeCount = uint(maxKeepAliveCount.intValue() * 6);

        return createSubscription(
            requestedPublishingInterval,
            maxLifetimeCount,
            maxKeepAliveCount,
            DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH,
            true, ubyte(0)
        );
    }

    @Override
    public CompletableFuture<UaSubscription> createSubscription(
        double requestedPublishingInterval,
        UInteger requestedLifetimeCount,
        UInteger requestedMaxKeepAliveCount,
        UInteger maxNotificationsPerPublish,
        boolean publishingEnabled,
        UByte priority) {

        CompletableFuture<CreateSubscriptionResponse> future = client.createSubscription(
            requestedPublishingInterval,
            requestedLifetimeCount,
            requestedMaxKeepAliveCount,
            maxNotificationsPerPublish,
            publishingEnabled, priority
        );

        return future.thenApply(response -> {
            OpcUaSubscription subscription = new OpcUaSubscription(
                client,
                response.getSubscriptionId(),
                response.getRevisedPublishingInterval(),
                response.getRevisedLifetimeCount(),
                response.getRevisedMaxKeepAliveCount(),
                maxNotificationsPerPublish,
                publishingEnabled, priority);

            subscriptions.put(subscription.getSubscriptionId(), subscription);

            maybeSendPublishRequests();

            return subscription;
        });
    }

    @Override
    public CompletableFuture<UaSubscription> modifySubscription(
        UInteger subscriptionId,
        double requestedPublishingInterval) {

        OpcUaSubscription subscription = subscriptions.get(subscriptionId);

        if (subscription == null) {
            CompletableFuture<UaSubscription> f = new CompletableFuture<>();
            f.completeExceptionally(new UaException(StatusCodes.Bad_SubscriptionIdInvalid));
            return f;
        }

        // Keep-alive every ~10-12s or every publishing interval if longer.
        UInteger requestedMaxKeepAliveCount = uint(Math.max(1, (int) Math.ceil(10000.0 / requestedPublishingInterval)));

        // Lifetime must be 3x (or greater) the keep-alive count.
        UInteger requestedLifetimeCount = uint(requestedMaxKeepAliveCount.intValue() * 6);

        CompletableFuture<UaSubscription> future = modifySubscription(
            subscriptionId,
            requestedPublishingInterval,
            requestedLifetimeCount,
            requestedMaxKeepAliveCount,
            subscription.getMaxNotificationsPerPublish(),
            subscription.getPriority()
        );

        future.thenRun(this::maybeSendPublishRequests);

        return future;
    }

    @Override
    public CompletableFuture<UaSubscription> modifySubscription(
        UInteger subscriptionId,
        double requestedPublishingInterval,
        UInteger requestedLifetimeCount,
        UInteger requestedMaxKeepAliveCount,
        UInteger maxNotificationsPerPublish,
        UByte priority) {

        OpcUaSubscription subscription = subscriptions.get(subscriptionId);

        if (subscription == null) {
            CompletableFuture<UaSubscription> f = new CompletableFuture<>();
            f.completeExceptionally(new UaException(StatusCodes.Bad_SubscriptionIdInvalid));
            return f;
        }

        CompletableFuture<ModifySubscriptionResponse> future = client.modifySubscription(
            subscriptionId,
            requestedPublishingInterval,
            requestedLifetimeCount,
            requestedMaxKeepAliveCount,
            maxNotificationsPerPublish,
            priority
        );

        return future.thenApply(response -> {
            subscription.setRevisedPublishingInterval(response.getRevisedPublishingInterval());
            subscription.setRevisedLifetimeCount(response.getRevisedLifetimeCount());
            subscription.setRevisedMaxKeepAliveCount(response.getRevisedMaxKeepAliveCount());
            subscription.setMaxNotificationsPerPublish(maxNotificationsPerPublish);
            subscription.setPriority(priority);

            maybeSendPublishRequests();

            return subscription;
        });
    }

    @Override
    public CompletableFuture<UaSubscription> deleteSubscription(UInteger subscriptionId) {
        List<UInteger> subscriptionIds = newArrayList(subscriptionId);

        return client.deleteSubscriptions(subscriptionIds).thenApply(r -> {
            OpcUaSubscription subscription = subscriptions.remove(subscriptionId);

            maybeSendPublishRequests();

            return subscription;
        });
    }

    public void transferFailed(UInteger subscriptionId, StatusCode statusCode) {
        OpcUaSubscription subscription = subscriptions.remove(subscriptionId);

        if (subscription != null) {
            subscriptionListeners.forEach(l -> l.onSubscriptionTransferFailed(subscription, statusCode));
        }
    }

    @Override
    public ImmutableList<UaSubscription> getSubscriptions() {
        return ImmutableList.copyOf(subscriptions.values());
    }

    @Override
    public void addSubscriptionListener(SubscriptionListener listener) {
        subscriptionListeners.add(listener);
    }

    @Override
    public void removeSubscriptionListener(SubscriptionListener listener) {
        subscriptionListeners.remove(listener);
    }

    private long getMaxPendingPublishes() {
        long maxPendingPublishRequests = client.getConfig().getMaxPendingPublishRequests().longValue();

        return Math.min(subscriptions.size() * 2, maxPendingPublishRequests);
    }

    private UInteger getTimeoutHint() {
        double minKeepAlive = subscriptions.values().stream()
            .map(s -> s.getRevisedPublishingInterval() * s.getRevisedMaxKeepAliveCount().doubleValue())
            .min(Comparator.<Double>naturalOrder())
            .orElse(client.getConfig().getRequestTimeout().doubleValue());

        long timeoutHint = (long) (getMaxPendingPublishes() * minKeepAlive * 1.25);

        return uint(timeoutHint);
    }

    private void maybeSendPublishRequests() {
        long maxPendingPublishes = getMaxPendingPublishes();

        if (maxPendingPublishes == 0) return;

        client.getSession().thenAccept(session -> {
            AtomicLong pendingCount = pendingCountMap.computeIfAbsent(
                session.getSessionId(), id -> new AtomicLong(0L));

            for (long i = pendingCount.get(); i < maxPendingPublishes; i++) {
                if (pendingCount.incrementAndGet() <= maxPendingPublishes) {
                    sendPublishRequest(session, pendingCount);
                } else {
                    pendingCount.getAndUpdate(p -> (p > 0) ? p - 1 : 0);
                }
            }

            if (pendingCountMap.size() > 1) {
                // Prune any old sessions...
                pendingCountMap.entrySet().removeIf(e ->
                    !e.getKey().equals(session.getSessionId()));
            }
        });
    }

    private void sendPublishRequest(UaSession session, AtomicLong pendingCount) {
        SubscriptionAcknowledgement[] subscriptionAcknowledgements;

        synchronized (acknowledgements) {
            subscriptionAcknowledgements = acknowledgements.toArray(
                new SubscriptionAcknowledgement[acknowledgements.size()]);

            acknowledgements.clear();
        }

        final UInteger requestHandle = client.nextRequestHandle();

        RequestHeader requestHeader = new RequestHeader(
            session.getAuthenticationToken(),
            DateTime.now(),
            requestHandle,
            uint(0),
            null,
            getTimeoutHint(),
            null
        );

        PublishRequest request = new PublishRequest(
            requestHeader,
            subscriptionAcknowledgements
        );

        if (logger.isDebugEnabled()) {
            String[] ackStrings = Arrays.stream(subscriptionAcknowledgements)
                .map(ack -> String.format("id=%s/seq=%s",
                    ack.getSubscriptionId(), ack.getSequenceNumber()))
                .toArray(String[]::new);

            logger.debug(
                "Sending PublishRequest, requestHandle={}, acknowledgements={}",
                requestHandle, Arrays.toString(ackStrings));
        }

        client.<PublishResponse>sendRequest(request).whenCompleteAsync((response, ex) -> {

            pendingCount.getAndUpdate(p -> (p > 0) ? p - 1 : 0);

            if (response != null) {
                logger.debug("Received PublishResponse, sequenceNumber={}",
                    response.getNotificationMessage().getSequenceNumber());

                processingQueue.submit(() -> onPublishComplete(response));

                maybeSendPublishRequests();
            } else {
                StatusCode statusCode = UaException.extract(ex)
                    .map(UaException::getStatusCode)
                    .orElse(StatusCode.BAD);

                logger.debug("Publish service failure: {}", statusCode, ex);

                if (statusCode.getValue() != StatusCodes.Bad_TooManyPublishRequests) {
                    maybeSendPublishRequests();
                }

                synchronized (this.acknowledgements) {
                    Collections.addAll(this.acknowledgements, subscriptionAcknowledgements);
                }

                UaException uax = UaException.extract(ex).orElse(new UaException(ex));
                subscriptionListeners.forEach(l -> l.onPublishFailure(uax));
            }
        }, client.getConfig().getExecutor());
    }

    private void onPublishComplete(PublishResponse response) {
        logger.debug("onPublishComplete() response for subscriptionId={}", response.getSubscriptionId());

        UInteger subscriptionId = response.getSubscriptionId();
        OpcUaSubscription subscription = subscriptions.get(subscriptionId);

        if (subscription == null) return;

        NotificationMessage notificationMessage = response.getNotificationMessage();

        long sequenceNumber = notificationMessage.getSequenceNumber().longValue();
        long expectedSequenceNumber = subscription.getLastSequenceNumber() + 1;

        if (sequenceNumber > expectedSequenceNumber) {
            logger.warn("[id={}] expected sequence={}, received sequence={}. Calling Republish service...",
                subscriptionId, expectedSequenceNumber, sequenceNumber);

            processingQueue.pause();
            processingQueue.submitToHead(() -> onPublishComplete(response));

            republish(subscriptionId, expectedSequenceNumber, sequenceNumber).whenComplete((dataLost, ex) -> {
                if (ex != null) {
                    logger.debug("Republish failed: {}", ex.getMessage(), ex);

                    subscriptionListeners.forEach(l -> l.onNotificationDataLost(subscription));
                } else {
                    // Republish succeeded, possibly with some data loss, resume processing.
                    if (dataLost) {
                        subscriptionListeners.forEach(l -> l.onNotificationDataLost(subscription));
                    }
                }

                subscription.setLastSequenceNumber(sequenceNumber - 1);
                processingQueue.resume();
            });

            return;
        }

        subscription.setLastSequenceNumber(sequenceNumber);

        UInteger[] availableSequenceNumbers = response.getAvailableSequenceNumbers();

        if (availableSequenceNumbers != null && availableSequenceNumbers.length > 0) {
            synchronized (acknowledgements) {
                for (UInteger available : availableSequenceNumbers) {
                    acknowledgements.add(new SubscriptionAcknowledgement(subscriptionId, available));
                }
            }

            if (logger.isDebugEnabled()) {
                String[] seqStrings = Arrays.stream(availableSequenceNumbers)
                    .map(sequence -> String.format("id=%s/seq=%s", subscriptionId, sequence))
                    .toArray(String[]::new);

                logger.debug(
                    "[id={}] PublishResponse sequence={}, available sequences={}",
                    subscriptionId, sequenceNumber, Arrays.toString(seqStrings));
            }
        }

        onNotificationMessage(subscriptionId, notificationMessage);
    }

    private CompletableFuture<Boolean> republish(UInteger subscriptionId, long fromSequence, long toSequence) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        republish(subscriptionId, fromSequence, toSequence, false, future);

        return future;
    }

    private void republish(UInteger subscriptionId,
                           long fromSequence,
                           long toSequence,
                           boolean dataLost,
                           CompletableFuture<Boolean> future) {

        if (fromSequence == toSequence) {
            future.complete(dataLost);
        } else {
            client.republish(subscriptionId, uint(fromSequence)).whenComplete((response, ex) -> {
                if (response != null) {
                    try {
                        onRepublishComplete(subscriptionId, response, uint(fromSequence));

                        republish(subscriptionId, fromSequence + 1, toSequence, dataLost, future);
                    } catch (UaException e) {
                        republish(subscriptionId, fromSequence + 1, toSequence, true, future);
                    }
                } else {
                    StatusCode statusCode = UaException.extract(ex)
                        .map(UaException::getStatusCode)
                        .orElse(StatusCode.BAD);

                    if (statusCode.getValue() == StatusCodes.Bad_MessageNotAvailable) {
                        republish(subscriptionId, fromSequence + 1, toSequence, true, future);
                    } else {
                        future.completeExceptionally(ex);
                    }
                }
            });
        }
    }

    private void onRepublishComplete(UInteger subscriptionId,
                                     RepublishResponse response,
                                     UInteger expectedSequenceNumber) throws UaException {

        NotificationMessage notificationMessage = response.getNotificationMessage();
        UInteger sequenceNumber = notificationMessage.getSequenceNumber();

        if (!sequenceNumber.equals(expectedSequenceNumber)) {
            throw new UaException(StatusCodes.Bad_SequenceNumberInvalid,
                "expected sequence=" + expectedSequenceNumber + ", received sequence=" + sequenceNumber);
        }

        onNotificationMessage(subscriptionId, notificationMessage);
    }

    private void onNotificationMessage(UInteger subscriptionId, NotificationMessage notificationMessage) {
        DateTime publishTime = notificationMessage.getPublishTime();

        logger.debug("onNotificationMessage(), subscriptionId={}, sequenceNumber={}, publishTime={}",
            subscriptionId, notificationMessage.getSequenceNumber(), publishTime);


        OpcUaSubscription subscription = subscriptions.get(subscriptionId);
        if (subscription != null) {
            deliverNotificationMessage(subscription, notificationMessage);
        }
    }

    private void deliverNotificationMessage(OpcUaSubscription subscription, NotificationMessage notificationMessage) {
        subscription.getNotificationSemaphore().acquire().thenAccept(permit -> deliveryQueue.submit(() -> {
            try {
                Map<UInteger, OpcUaMonitoredItem> items = subscription.getItemsByClientHandle();
                List<ExtensionObject> notificationData = l(notificationMessage.getNotificationData());

                for (ExtensionObject xo : notificationData) {
                    Object o = xo.getObject();

                    if (o instanceof DataChangeNotification) {
                        DataChangeNotification dcn = (DataChangeNotification) o;
                        List<MonitoredItemNotification> monitoredItems = l(dcn.getMonitoredItems());
                        int notificationCount = monitoredItems.size();

                        logger.debug("Received {} MonitoredItemNotifications", notificationCount);

                        for (MonitoredItemNotification min : monitoredItems) {
                            logger.trace("MonitoredItemNotification: clientHandle={}, value={}",
                                min.getClientHandle(), min.getValue());

                            OpcUaMonitoredItem item = items.get(min.getClientHandle());
                            if (item != null) item.onValueArrived(min.getValue());
                            else logger.warn("no item for clientHandle=" + min.getClientHandle());
                        }

                        if (notificationCount == 0) {
                            subscriptionListeners.forEach(
                                listener -> listener.onKeepAlive(subscription, notificationMessage.getPublishTime())
                            );

                            subscription.getNotificationListeners().forEach(
                                listener -> listener.onKeepAliveNotification(
                                    subscription, notificationMessage.getPublishTime())
                            );
                        } else {
                            if (!subscription.getNotificationListeners().isEmpty()) {
                                ImmutableList.Builder<Tuple2<UaMonitoredItem, DataValue>> builder =
                                    ImmutableList.builder();

                                for (MonitoredItemNotification n : monitoredItems) {
                                    UaMonitoredItem item = subscription
                                        .getItemsByClientHandle().get(n.getClientHandle());

                                    if (item != null) {
                                        builder.add(new Tuple2<>(item, n.getValue()));
                                    }
                                }

                                ImmutableList<Tuple2<UaMonitoredItem, DataValue>> itemValues = builder.build();

                                subscription.getNotificationListeners().forEach(
                                    listener -> listener.onDataChangeNotification(
                                        subscription,
                                        itemValues,
                                        notificationMessage.getPublishTime()
                                    )
                                );
                            }
                        }
                    } else if (o instanceof EventNotificationList) {
                        EventNotificationList enl = (EventNotificationList) o;
                        List<EventFieldList> events = l(enl.getEvents());

                        for (EventFieldList efl : events) {
                            logger.trace("EventFieldList: clientHandle={}, values={}",
                                efl.getClientHandle(), Arrays.toString(efl.getEventFields()));

                            OpcUaMonitoredItem item = items.get(efl.getClientHandle());
                            if (item != null) item.onEventArrived(efl.getEventFields());
                        }

                        if (!subscription.getNotificationListeners().isEmpty()) {
                            ImmutableList.Builder<Tuple2<UaMonitoredItem, Variant[]>> builder = ImmutableList.builder();

                            for (EventFieldList efl : events) {
                                UaMonitoredItem item = subscription.getItemsByClientHandle().get(efl.getClientHandle());
                                if (item != null) {
                                    builder.add(new Tuple2<>(item, efl.getEventFields()));
                                }
                            }

                            ImmutableList<Tuple2<UaMonitoredItem, Variant[]>> itemEvents = builder.build();

                            subscription.getNotificationListeners().forEach(
                                listener -> listener.onEventNotification(
                                    subscription,
                                    itemEvents,
                                    notificationMessage.getPublishTime()
                                )
                            );
                        }
                    } else if (o instanceof StatusChangeNotification) {
                        StatusChangeNotification scn = (StatusChangeNotification) o;

                        logger.debug("StatusChangeNotification: {}", scn.getStatus());

                        subscriptionListeners.forEach(
                            listener -> listener.onStatusChanged(subscription, scn.getStatus())
                        );

                        subscription.getNotificationListeners().forEach(
                            listener -> listener.onStatusChangedNotification(subscription, scn.getStatus())
                        );

                        if (scn.getStatus().getValue() == StatusCodes.Bad_Timeout) {
                            subscriptions.remove(subscription.getSubscriptionId());
                            maybeSendPublishRequests();
                        }
                    }
                }
            } finally {
                permit.release();
            }
        }));
    }

    public void startPublishing() {
        maybeSendPublishRequests();
    }

    public void clearSubscriptions() {
        subscriptions.clear();
    }

}

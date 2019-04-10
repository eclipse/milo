/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.Function;

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
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class OpcUaSubscriptionManager implements UaSubscriptionManager {

    public static final UInteger DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH = uint(65535);

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<UInteger, OpcUaSubscription> subscriptions = Maps.newConcurrentMap();

    private final List<SubscriptionListener> subscriptionListeners = Lists.newCopyOnWriteArrayList();

    private final ConcurrentMap<NodeId, AtomicLong> pendingCountMap = Maps.newConcurrentMap();

    private final LinkedList<SubscriptionAcknowledgement> acknowledgements = newLinkedList();

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

            @Override
            public void onSessionActive(UaSession session) {
                maybeSendPublishRequests();
            }
        });
    }

    @Override
    public CompletableFuture<UaSubscription> createSubscription(double requestedPublishingInterval) {
        return createSubscription(
            requestedPublishingInterval,
            this::getLifetimeCount,
            this::getMaxKeepAliveCount,
            DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH,
            true,
            UByte.MIN
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

        return createSubscription(
            requestedPublishingInterval,
            (p, c) -> requestedLifetimeCount,
            p -> requestedMaxKeepAliveCount,
            maxNotificationsPerPublish,
            publishingEnabled,
            priority
        );
    }

    @Override
    public CompletableFuture<UaSubscription> createSubscription(
        double requestedPublishingInterval,
        BiFunction<Double, UInteger, UInteger> getLifetimeCount,
        Function<Double, UInteger> getMaxKeepAliveCount,
        UInteger maxNotificationsPerPublish,
        boolean publishingEnabled,
        UByte priority) {

        UInteger requestedMaxKeepAliveCount = getMaxKeepAliveCount.apply(requestedPublishingInterval);
        UInteger requestedLifetimeCount = getLifetimeCount.apply(
            requestedPublishingInterval, requestedMaxKeepAliveCount);

        CompletableFuture<CreateSubscriptionResponse> future = client.createSubscription(
            requestedPublishingInterval,
            requestedLifetimeCount,
            requestedMaxKeepAliveCount,
            maxNotificationsPerPublish,
            publishingEnabled, priority
        );

        return future.thenCompose(response -> {
            OpcUaSubscription subscription = new OpcUaSubscription(
                client,
                response.getSubscriptionId(),
                response.getRevisedPublishingInterval(),
                response.getRevisedLifetimeCount(),
                response.getRevisedMaxKeepAliveCount(),
                maxNotificationsPerPublish,
                publishingEnabled,
                priority
            );

            subscription.setRequestedPublishingInterval(requestedPublishingInterval);
            subscription.setRequestedLifetimeCount(requestedLifetimeCount);
            subscription.setRequestedMaxKeepAliveCount(requestedMaxKeepAliveCount);

            double revisedPublishingInterval = response.getRevisedPublishingInterval();
            UInteger newMaxKeepAliveCount = getMaxKeepAliveCount.apply(revisedPublishingInterval);

            if (requestedPublishingInterval != revisedPublishingInterval &&
                !requestedMaxKeepAliveCount.equals(newMaxKeepAliveCount)) {

                UInteger newLifetimeCount = getLifetimeCount.apply(revisedPublishingInterval, newMaxKeepAliveCount);

                CompletableFuture<ModifySubscriptionResponse> modifyFuture = client.modifySubscription(
                    response.getSubscriptionId(),
                    revisedPublishingInterval,
                    newLifetimeCount,
                    newMaxKeepAliveCount,
                    maxNotificationsPerPublish,
                    priority
                );

                return modifyFuture.thenApply(modifyResponse -> {
                    subscription.setRequestedLifetimeCount(newLifetimeCount);
                    subscription.setRequestedMaxKeepAliveCount(newMaxKeepAliveCount);

                    subscription.setRevisedPublishingInterval(modifyResponse.getRevisedPublishingInterval());
                    subscription.setRevisedLifetimeCount(modifyResponse.getRevisedLifetimeCount());
                    subscription.setRevisedMaxKeepAliveCount(modifyResponse.getRevisedMaxKeepAliveCount());

                    subscriptions.put(subscription.getSubscriptionId(), subscription);

                    maybeSendPublishRequests();

                    return subscription;
                });
            } else {
                subscriptions.put(subscription.getSubscriptionId(), subscription);

                maybeSendPublishRequests();

                return completedFuture(subscription);
            }
        });
    }

    private UInteger getMaxKeepAliveCount(double publishingInterval) {
        int count = (int) Math.ceil(10000.0 / Math.max(1, publishingInterval));
        return uint(Math.max(1, count));
    }

    private UInteger getLifetimeCount(double publishingInterval, UInteger maxKeepAliveCount) {
        // Lifetime must be 3x (or greater) the keep-alive count.
        try {
            long value = maxKeepAliveCount.toBigInteger()
                .multiply(BigInteger.valueOf(6)).longValueExact();

            return uint(Math.min(value, UInteger.MAX_VALUE));
        } catch (ArithmeticException e) {
            return UInteger.MAX;
        }
    }

    @Override
    public CompletableFuture<UaSubscription> modifySubscription(
        UInteger subscriptionId,
        double requestedPublishingInterval) {

        OpcUaSubscription subscription = subscriptions.get(subscriptionId);

        if (subscription == null) {
            return failedUaFuture(StatusCodes.Bad_SubscriptionIdInvalid);
        }

        return modifySubscription(
            subscriptionId,
            requestedPublishingInterval,
            this::getLifetimeCount,
            this::getMaxKeepAliveCount,
            subscription.getMaxNotificationsPerPublish(),
            subscription.getPriority()
        );
    }

    @Override
    public CompletableFuture<UaSubscription> modifySubscription(
        UInteger subscriptionId,
        double requestedPublishingInterval,
        UInteger requestedLifetimeCount,
        UInteger requestedMaxKeepAliveCount,
        UInteger maxNotificationsPerPublish,
        UByte priority) {

        return modifySubscription(
            subscriptionId,
            requestedPublishingInterval,
            (p, c) -> requestedLifetimeCount,
            p -> requestedMaxKeepAliveCount,
            maxNotificationsPerPublish,
            priority
        );
    }

    @Override
    public CompletableFuture<UaSubscription> modifySubscription(
        UInteger subscriptionId,
        double requestedPublishingInterval,
        BiFunction<Double, UInteger, UInteger> getLifetimeCount,
        Function<Double, UInteger> getMaxKeepAliveCount,
        UInteger maxNotificationsPerPublish,
        UByte priority) {

        OpcUaSubscription subscription = subscriptions.get(subscriptionId);

        if (subscription == null) {
            return failedUaFuture(StatusCodes.Bad_SubscriptionIdInvalid);
        }

        UInteger requestedMaxKeepAliveCount = getMaxKeepAliveCount.apply(requestedPublishingInterval);
        UInteger requestedLifetimeCount = getLifetimeCount.apply(
            requestedPublishingInterval, requestedMaxKeepAliveCount);

        CompletableFuture<ModifySubscriptionResponse> future = client.modifySubscription(
            subscriptionId,
            requestedPublishingInterval,
            requestedLifetimeCount,
            requestedMaxKeepAliveCount,
            maxNotificationsPerPublish,
            priority
        );

        return future.thenCompose(response -> {
            subscription.setRequestedPublishingInterval(requestedPublishingInterval);
            subscription.setRequestedLifetimeCount(requestedLifetimeCount);
            subscription.setRequestedMaxKeepAliveCount(requestedMaxKeepAliveCount);

            subscription.setRevisedPublishingInterval(response.getRevisedPublishingInterval());
            subscription.setRevisedLifetimeCount(response.getRevisedLifetimeCount());
            subscription.setRevisedMaxKeepAliveCount(response.getRevisedMaxKeepAliveCount());
            subscription.setMaxNotificationsPerPublish(maxNotificationsPerPublish);
            subscription.setPriority(priority);

            double revisedPublishingInterval = response.getRevisedPublishingInterval();
            UInteger newMaxKeepAliveCount = getMaxKeepAliveCount.apply(revisedPublishingInterval);

            if (requestedPublishingInterval != revisedPublishingInterval &&
                !requestedMaxKeepAliveCount.equals(newMaxKeepAliveCount)) {

                UInteger newLifetimeCount = getLifetimeCount.apply(revisedPublishingInterval, newMaxKeepAliveCount);

                CompletableFuture<ModifySubscriptionResponse> modifyFuture = client.modifySubscription(
                    subscriptionId,
                    revisedPublishingInterval,
                    newLifetimeCount,
                    newMaxKeepAliveCount,
                    maxNotificationsPerPublish,
                    priority
                );

                return modifyFuture.thenApply(modifyResponse -> {
                    subscription.setRequestedLifetimeCount(newLifetimeCount);
                    subscription.setRequestedMaxKeepAliveCount(newMaxKeepAliveCount);

                    subscription.setRevisedPublishingInterval(modifyResponse.getRevisedPublishingInterval());
                    subscription.setRevisedLifetimeCount(modifyResponse.getRevisedLifetimeCount());
                    subscription.setRevisedMaxKeepAliveCount(modifyResponse.getRevisedMaxKeepAliveCount());

                    maybeSendPublishRequests();

                    return subscription;
                });
            } else {
                maybeSendPublishRequests();

                return completedFuture(subscription);
            }
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

        return subscriptions.isEmpty() ?
            0 : Math.min(subscriptions.size() + 1, maxPendingPublishRequests);
    }

    private UInteger getTimeoutHint() {
        double maxKeepAlive = subscriptions.values().stream()
            .map(s -> s.getRevisedPublishingInterval() * s.getRevisedMaxKeepAliveCount().doubleValue())
            .max(Comparator.naturalOrder())
            .orElse(client.getConfig().getRequestTimeout().doubleValue());

        long maxPendingPublishes = getMaxPendingPublishes();

        double timeoutHint = maxPendingPublishes * maxKeepAlive * 1.5;

        if (Double.isInfinite(timeoutHint) || timeoutHint > UInteger.MAX_VALUE) {
            timeoutHint = 0d;
        }

        logger.debug(
            "getTimeoutHint() maxKeepAlive={} maxPendingPublishes={} timeoutHint={}",
            maxKeepAlive, maxPendingPublishes, timeoutHint);

        return uint((long) timeoutHint);
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

        int maxArrayLength = client.getConfig().getEncodingLimits().getMaxArrayLength();

        synchronized (acknowledgements) {
            List<SubscriptionAcknowledgement> ackSubList = acknowledgements
                .subList(0, Math.min(acknowledgements.size(), maxArrayLength));

            subscriptionAcknowledgements = ackSubList.toArray(new SubscriptionAcknowledgement[0]);

            ackSubList.clear();
        }


        RequestHeader requestHeader = client.getStackClient().newRequestHeader(
            session.getAuthenticationToken(),
            getTimeoutHint()
        );

        UInteger requestHandle = requestHeader.getRequestHandle();

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

        client.<PublishResponse>sendRequest(request).whenComplete((response, ex) -> {
            if (response != null) {
                logger.debug("Received PublishResponse, sequenceNumber={}",
                    response.getNotificationMessage().getSequenceNumber());

                processingQueue.submit(() -> onPublishComplete(response, pendingCount));
            } else {
                StatusCode statusCode = UaException.extract(ex)
                    .map(UaException::getStatusCode)
                    .orElse(StatusCode.BAD);

                logger.debug("Publish service failure (requestHandle={}): {}", requestHandle, statusCode, ex);

                pendingCount.getAndUpdate(p -> (p > 0) ? p - 1 : 0);

                if (statusCode.getValue() != StatusCodes.Bad_NoSubscription &&
                    statusCode.getValue() != StatusCodes.Bad_TooManyPublishRequests) {

                    maybeSendPublishRequests();
                }

                synchronized (this.acknowledgements) {
                    Collections.addAll(this.acknowledgements, subscriptionAcknowledgements);
                }

                UaException uax = UaException.extract(ex).orElse(new UaException(ex));
                subscriptionListeners.forEach(l -> l.onPublishFailure(uax));
            }
        });
    }

    private void onPublishComplete(PublishResponse response, AtomicLong pendingCount) {
        logger.debug("onPublishComplete() response for subscriptionId={}", response.getSubscriptionId());

        UInteger subscriptionId = response.getSubscriptionId();
        OpcUaSubscription subscription = subscriptions.get(subscriptionId);

        if (subscription == null) {
            pendingCount.getAndUpdate(p -> (p > 0) ? p - 1 : 0);
            maybeSendPublishRequests();
            return;
        }

        NotificationMessage notificationMessage = response.getNotificationMessage();

        long sequenceNumber = notificationMessage.getSequenceNumber().longValue();
        long expectedSequenceNumber = subscription.getLastSequenceNumber() + 1;

        if (sequenceNumber > expectedSequenceNumber) {
            logger.warn("[id={}] expected sequence={}, received sequence={}. Calling Republish service...",
                subscriptionId, expectedSequenceNumber, sequenceNumber);

            processingQueue.pause();
            processingQueue.submitToHead(() -> onPublishComplete(response, pendingCount));

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

        if (notificationMessage.getNotificationData() != null &&
            notificationMessage.getNotificationData().length > 0) {

            // Set last sequence number only if this isn't a keep-alive
            subscription.setLastSequenceNumber(sequenceNumber);
        }

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

        DateTime publishTime = notificationMessage.getPublishTime();

        logger.debug("onPublishComplete(), subscriptionId={}, sequenceNumber={}, publishTime={}",
            subscriptionId, notificationMessage.getSequenceNumber(), publishTime);

        deliverNotificationMessage(subscription, notificationMessage).thenRunAsync(
            () -> {
                pendingCount.getAndUpdate(p -> (p > 0) ? p - 1 : 0);

                maybeSendPublishRequests();
            },
            client.getConfig().getExecutor()
        );
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

        DateTime publishTime = notificationMessage.getPublishTime();

        logger.debug("onRepublishComplete(), subscriptionId={}, sequenceNumber={}, publishTime={}",
            subscriptionId, notificationMessage.getSequenceNumber(), publishTime);

        OpcUaSubscription subscription = subscriptions.get(subscriptionId);

        if (subscription != null) {
            deliverNotificationMessage(subscription, notificationMessage);
        }
    }

    private CompletableFuture<Unit> deliverNotificationMessage(
        OpcUaSubscription subscription, NotificationMessage notificationMessage) {

        CompletableFuture<Unit> delivered = new CompletableFuture<>();

        subscription.getNotificationSemaphore().acquire().thenAccept(permit -> deliveryQueue.submit(() -> {
            try {
                Map<UInteger, OpcUaMonitoredItem> items = subscription.getItemsByClientHandle();
                List<ExtensionObject> notificationData = l(notificationMessage.getNotificationData());

                if (notificationData.isEmpty()) {
                    subscriptionListeners.forEach(
                        listener -> listener.onKeepAlive(subscription, notificationMessage.getPublishTime())
                    );

                    subscription.getNotificationListeners().forEach(
                        listener -> listener.onKeepAliveNotification(
                            subscription, notificationMessage.getPublishTime())
                    );
                }

                for (ExtensionObject xo : notificationData) {
                    Object o = xo.decode(client.getSerializationContext());

                    if (o instanceof DataChangeNotification) {
                        DataChangeNotification dcn = (DataChangeNotification) o;
                        List<MonitoredItemNotification> monitoredItemNotifications = l(dcn.getMonitoredItems());
                        int notificationCount = monitoredItemNotifications.size();

                        logger.debug("Received {} MonitoredItemNotifications", notificationCount);

                        for (MonitoredItemNotification min : monitoredItemNotifications) {
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
                                List<UaMonitoredItem> monitoredItems = new ArrayList<>();
                                List<DataValue> dataValues = new ArrayList<>();

                                for (MonitoredItemNotification n : monitoredItemNotifications) {
                                    UaMonitoredItem item = subscription
                                        .getItemsByClientHandle().get(n.getClientHandle());

                                    if (item != null) {
                                        monitoredItems.add(item);
                                        dataValues.add(n.getValue());
                                    }
                                }

                                subscription.getNotificationListeners().forEach(
                                    listener -> listener.onDataChangeNotification(
                                        subscription,
                                        monitoredItems,
                                        dataValues,
                                        notificationMessage.getPublishTime()
                                    )
                                );
                            }
                        }
                    } else if (o instanceof EventNotificationList) {
                        EventNotificationList enl = (EventNotificationList) o;
                        List<EventFieldList> eventFieldLists = l(enl.getEvents());

                        for (EventFieldList efl : eventFieldLists) {
                            logger.trace("EventFieldList: clientHandle={}, values={}",
                                efl.getClientHandle(), Arrays.toString(efl.getEventFields()));

                            OpcUaMonitoredItem item = items.get(efl.getClientHandle());
                            if (item != null) item.onEventArrived(efl.getEventFields());
                        }

                        if (!subscription.getNotificationListeners().isEmpty()) {
                            List<UaMonitoredItem> monitoredItems = new ArrayList<>();
                            List<Variant[]> eventFields = new ArrayList<>();

                            for (EventFieldList efl : eventFieldLists) {
                                UaMonitoredItem item = subscription
                                    .getItemsByClientHandle().get(efl.getClientHandle());

                                if (item != null) {
                                    monitoredItems.add(item);
                                    eventFields.add(efl.getEventFields());
                                }
                            }

                            subscription.getNotificationListeners().forEach(
                                listener -> listener.onEventNotification(
                                    subscription,
                                    monitoredItems,
                                    eventFields,
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
                        }
                    }
                }
            } finally {
                permit.release();

                delivered.complete(Unit.VALUE);
            }
        }));

        return delivered;
    }

    public void startPublishing() {
        maybeSendPublishRequests();
    }

    public void clearSubscriptions() {
        subscriptions.clear();
    }

    public void pauseDelivery() {
        deliveryQueue.pause();
    }

    public void resumeDelivery() {
        deliveryQueue.resume();
    }

}

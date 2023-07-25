/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.NotificationMessage;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.eclipse.milo.opcua.stack.core.util.TaskQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class PublishingManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ConcurrentMap<NodeId, AtomicLong> pendingCountMap = new ConcurrentHashMap<>();

    private final Map<UInteger, SubscriptionDetails> subscriptionDetails = new ConcurrentHashMap<>();

    private final TaskQueue deliveryQueue;
    private final TaskQueue processingQueue;

    private final OpcUaClient client;

    public PublishingManager(OpcUaClient client) {
        this.client = client;

        deliveryQueue = new TaskQueue(client.getTransport().getConfig().getExecutor());
        processingQueue = new TaskQueue(client.getTransport().getConfig().getExecutor());
    }

    void addSubscription(
        OpcUaSubscription subscription,
        Consumer<NotificationMessage> notificationMessageConsumer
    ) {

        subscription.getSubscriptionId().ifPresent(id -> {
            subscriptionDetails.put(id, new SubscriptionDetails(subscription));

            maybeSendPublishRequests();
        });
    }

    void removeSubscription(OpcUaSubscription subscription) {}

    private void maybeSendPublishRequests() {
        long maxPendingPublishes = getMaxPendingPublishes();

        if (maxPendingPublishes > 0) {
            client.getSessionAsync().thenAccept(session -> {
                AtomicLong pendingCount = pendingCountMap.computeIfAbsent(
                    session.getSessionId(),
                    id -> new AtomicLong(0L)
                );

                for (long i = pendingCount.get(); i < maxPendingPublishes; i++) {
                    if (pendingCount.incrementAndGet() <= maxPendingPublishes) {
                        sendPublishRequest(session, pendingCount);
                    } else {
                        pendingCount.getAndUpdate(p -> (p > 0) ? p - 1 : 0);
                    }
                }

                if (pendingCountMap.size() > 1) {
                    // Prune any old sessions...
                    pendingCountMap.entrySet().removeIf(
                        e ->
                            !e.getKey().equals(session.getSessionId())
                    );
                }
            });
        }
    }

    private void sendPublishRequest(OpcUaSession session, AtomicLong pendingCount) {
        var subscriptionAcknowledgements = new ArrayList<SubscriptionAcknowledgement>();

        subscriptionDetails.values().forEach(subscription -> {
            synchronized (subscription.availableAcknowledgements) {
                subscription.availableAcknowledgements.forEach(
                    sequenceNumber ->
                        subscription.getSubscription().getSubscriptionId().ifPresent(
                            subscriptionId ->
                                subscriptionAcknowledgements.add(
                                    new SubscriptionAcknowledgement(subscriptionId, sequenceNumber)
                                )
                        )
                );
                subscription.availableAcknowledgements.clear();
            }
        });

        RequestHeader requestHeader = client.newRequestHeader(
            session.getAuthenticationToken(),
            getTimeoutHint()
        );

        UInteger requestHandle = requestHeader.getRequestHandle();

        var request = new PublishRequest(
            requestHeader,
            subscriptionAcknowledgements.toArray(new SubscriptionAcknowledgement[0])
        );

        if (logger.isDebugEnabled()) {
            String[] ackStrings = subscriptionAcknowledgements.stream()
                .map(ack -> String.format("id=%s/seq=%s",
                    ack.getSubscriptionId(), ack.getSequenceNumber()))
                .toArray(String[]::new);

            logger.debug(
                "Sending PublishRequest, requestHandle={}, acknowledgements={}",
                requestHandle, Arrays.toString(ackStrings)
            );
        }

        client.getTransport().sendRequestMessage(request).whenComplete((response, ex) -> {
            if (response instanceof PublishResponse) {
                PublishResponse publishResponse = (PublishResponse) response;
                logger.debug(
                    "Received PublishResponse, sequenceNumber={}",
                    publishResponse.getNotificationMessage().getSequenceNumber()
                );

                // TODO processingQueue.submit(() -> onPublishComplete(publishResponse, pendingCount));
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

                UaException uax = UaException.extract(ex).orElse(new UaException(ex));
                // TODO subscriptionListeners.forEach(l -> l.onPublishFailure(uax));
            }
        });
    }

    private long getMaxPendingPublishes() {
        long maxPendingPublishRequests =
            client.getConfig().getMaxPendingPublishRequests().longValue();

        return subscriptionDetails.isEmpty() ?
            0 :
            Math.min(subscriptionDetails.size() + 1, maxPendingPublishRequests);
    }

    private UInteger getTimeoutHint() {
        double maxKeepAlive = client.getConfig().getRequestTimeout().doubleValue();

        List<SubscriptionDetails> subscriptions = List.copyOf(subscriptionDetails.values());

        for (SubscriptionDetails details : subscriptions) {
            Optional<Double> revisedPublishingInterval =
                details.getSubscription().getRevisedPublishingInterval();
            Optional<UInteger> revisedMaxKeepAliveCount =
                details.getSubscription().getRevisedMaxKeepAliveCount();

            if (revisedPublishingInterval.isPresent() && revisedMaxKeepAliveCount.isPresent()) {
                double keepAlive = revisedPublishingInterval.get() *
                    revisedMaxKeepAliveCount.get().doubleValue();

                if (keepAlive >= maxKeepAlive) {
                    maxKeepAlive = keepAlive;
                }
            }
        }

        long maxPendingPublishes = getMaxPendingPublishes();
        double timeoutHint = maxKeepAlive * maxPendingPublishes * 1.5;

        if (Double.isInfinite(timeoutHint) || timeoutHint > UInteger.MAX_VALUE) {
            maxKeepAlive = 0d;
        }

        logger.debug(
            "getTimeoutHint() maxKeepAlive={} maxPendingPublishes={} timeoutHint={}",
            maxKeepAlive, maxPendingPublishes, timeoutHint);

        return uint((long) timeoutHint);
    }

    private static class SubscriptionDetails {

        private final List<UInteger> availableAcknowledgements =
            Collections.synchronizedList(new ArrayList<>());

        private volatile long lastSequenceNumber = 0L;

        private final OpcUaSubscription subscription;

        private SubscriptionDetails(OpcUaSubscription subscription) {
            this.subscription = subscription;
        }

        public OpcUaSubscription getSubscription() {
            return subscription;
        }

    }

}

/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.subscriptions;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.PeekingIterator;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.Ints;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfigLimits;
import org.eclipse.milo.opcua.sdk.server.diagnostics.SubscriptionDiagnostics;
import org.eclipse.milo.opcua.sdk.server.items.BaseMonitoredItem;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultBinaryEncoding;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFieldList;
import org.eclipse.milo.opcua.stack.core.types.structured.EventNotificationList;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemNotification;
import org.eclipse.milo.opcua.stack.core.types.structured.NotificationMessage;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusChangeNotification;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.sdk.server.subscriptions.SubscriptionManager.KEY_ACK_RESULTS;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class Subscription {

    /**
     * Maximum number of NotificationMessages to store for republishing.
     */
    private static final int MAX_AVAILABLE_MESSAGES = 1024;

    /**
     * Maximum number of notifications that can be returned in a single PublishResponse.
     */
    private static final int MAX_NOTIFICATIONS_PER_PUBLISH = 65535;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private volatile Iterator<BaseMonitoredItem<?>> lastIterator = Collections.emptyIterator();

    private final AtomicLong itemIds = new AtomicLong(1L);
    private final Map<UInteger, BaseMonitoredItem<?>> itemsById = Maps.newConcurrentMap();

    private final AtomicReference<State> state = new AtomicReference<>(State.Normal);
    private final AtomicReference<StateListener> stateListener = new AtomicReference<>();

    private final AtomicLong sequenceNumber = new AtomicLong(1L);

    private final ConcurrentSkipListMap<UInteger, NotificationMessage> availableMessages =
        new ConcurrentSkipListMap<>(UInteger::compareTo);

    private final PublishHandler publishHandler = new PublishHandler();
    private final TimerHandler timerHandler = new TimerHandler();

    private volatile ScheduledFuture<?> publishingTimer;

    private volatile boolean messageSent = false;
    private volatile boolean moreNotifications = false;
    private volatile long keepAliveCounter;
    private volatile long lifetimeCounter;

    private volatile double publishingInterval;
    private volatile long lifetimeCount;
    private volatile long maxKeepAliveCount;
    private volatile int maxNotificationsPerPublish;
    private volatile boolean publishingEnabled;
    private volatile int priority;

    private volatile SubscriptionManager subscriptionManager;

    private final SubscriptionDiagnostics subscriptionDiagnostics;

    private final SerializationContext serializationContext;
    private final UInteger subscriptionId;

    public Subscription(
        SubscriptionManager subscriptionManager,
        UInteger subscriptionId,
        double publishingInterval,
        long maxKeepAliveCount,
        long lifetimeCount,
        long maxNotificationsPerPublish,
        boolean publishingEnabled,
        int priority
    ) {

        this.subscriptionManager = subscriptionManager;
        this.subscriptionId = subscriptionId;

        subscriptionDiagnostics = new SubscriptionDiagnostics(this);

        serializationContext = subscriptionManager.getServer().getSerializationContext();

        setPublishingInterval(publishingInterval);
        setMaxKeepAliveCount(maxKeepAliveCount);
        setLifetimeCount(lifetimeCount);
        setMaxNotificationsPerPublish(maxNotificationsPerPublish);

        this.publishingEnabled = publishingEnabled;
        this.priority = priority;

        resetKeepAliveCounter();
        resetLifetimeCounter();

        logger.debug("[id={}] subscription created, interval={}, keep-alive={}, lifetime={}",
            subscriptionId, publishingInterval, maxKeepAliveCount, lifetimeCount);
    }

    public synchronized void modifySubscription(ModifySubscriptionRequest request) {
        setPublishingInterval(request.getRequestedPublishingInterval());
        setMaxKeepAliveCount(request.getRequestedMaxKeepAliveCount().longValue());
        setLifetimeCount(request.getRequestedLifetimeCount().longValue());
        setMaxNotificationsPerPublish(request.getMaxNotificationsPerPublish().longValue());

        this.priority = request.getPriority().intValue();

        resetLifetimeCounter();

        subscriptionDiagnostics.getModifyCount().increment();

        logger.debug("[id={}] subscription modified, interval={}, keep-alive={}, lifetime={}",
            subscriptionId, publishingInterval, maxKeepAliveCount, lifetimeCount);
    }

    public synchronized List<BaseMonitoredItem<?>> deleteSubscription() {
        setState(State.Closed);

        ScheduledFuture<?> sf = publishingTimer;
        if (sf != null) sf.cancel(false);
        publishingTimer = null;

        logger.debug("[id={}] subscription deleted.", subscriptionId);

        return Lists.newArrayList(itemsById.values());
    }

    public synchronized void setPublishingMode(SetPublishingModeRequest request) {
        boolean previouslyEnabled = publishingEnabled;
        publishingEnabled = request.getPublishingEnabled();

        resetLifetimeCounter();

        if (previouslyEnabled != publishingEnabled) {
            if (publishingEnabled) {
                subscriptionDiagnostics.getEnableCount().increment();
            } else {
                subscriptionDiagnostics.getDisableCount().increment();
            }
        }

        logger.debug("[id={}] {}.", subscriptionId, publishingEnabled ? "publishing enabled." : "publishing disabled.");
    }

    public synchronized void addMonitoredItems(List<BaseMonitoredItem<?>> createdItems) {
        for (BaseMonitoredItem<?> item : createdItems) {
            itemsById.put(item.getId(), item);
        }

        resetLifetimeCounter();

        logger.debug("[id={}] created {} MonitoredItems.", subscriptionId, createdItems.size());
    }

    public synchronized void removeMonitoredItems(List<BaseMonitoredItem<?>> deletedItems) {
        for (BaseMonitoredItem<?> item : deletedItems) {
            itemsById.remove(item.getId());
        }

        resetLifetimeCounter();

        logger.debug("[id={}] deleted {} MonitoredItems.", subscriptionId, deletedItems.size());
    }

    public synchronized Map<UInteger, BaseMonitoredItem<?>> getMonitoredItems() {
        return itemsById;
    }

    /**
     * Given the requested publishing interval, set it to something reasonable.
     *
     * @param requestedPublishingInterval the requested publishing interval.
     */
    private void setPublishingInterval(double requestedPublishingInterval) {
        OpcUaServerConfigLimits limits = subscriptionManager.getServer()
            .getConfig()
            .getLimits();

        double minPublishingInterval = limits.getMinPublishingInterval();
        double maxPublishingInterval = limits.getMaxPublishingInterval();

        if (requestedPublishingInterval < minPublishingInterval ||
            Double.isNaN(requestedPublishingInterval) ||
            Double.isInfinite(requestedPublishingInterval)) {

            requestedPublishingInterval = limits.getDefaultPublishingInterval();
        }

        if (requestedPublishingInterval > maxPublishingInterval) {
            requestedPublishingInterval = maxPublishingInterval;
        }

        this.publishingInterval = requestedPublishingInterval;
    }

    private void setMaxKeepAliveCount(long maxKeepAliveCount) {
        OpcUaServerConfigLimits limits = subscriptionManager.getServer()
            .getConfig()
            .getLimits();

        if (maxKeepAliveCount == 0) maxKeepAliveCount = 3;

        double keepAliveInterval = maxKeepAliveCount * publishingInterval;

        // keep alive interval cannot be longer than the max subscription lifetime.
        double maxSubscriptionLifetime = limits.getMaxSubscriptionLifetime();

        if (keepAliveInterval > maxSubscriptionLifetime) {
            maxKeepAliveCount = (long) (maxSubscriptionLifetime / publishingInterval);

            if (maxKeepAliveCount < UInteger.MAX_VALUE) {
                if (maxSubscriptionLifetime % publishingInterval != 0) {
                    maxKeepAliveCount++;
                }
            }

            keepAliveInterval = maxKeepAliveCount * publishingInterval;
        }

        // the time between publishes cannot exceed the max publishing interval.
        double maxPublishingInterval = limits
            .getMaxPublishingInterval();

        if (keepAliveInterval > maxPublishingInterval) {
            maxKeepAliveCount = (long) (maxPublishingInterval / publishingInterval);

            if (maxKeepAliveCount < UInteger.MAX_VALUE) {
                if (maxPublishingInterval % publishingInterval != 0) {
                    maxKeepAliveCount++;
                }
            }
        }

        this.maxKeepAliveCount = maxKeepAliveCount;
    }

    private void setLifetimeCount(long lifetimeCount) {
        OpcUaServerConfigLimits limits = subscriptionManager.getServer()
            .getConfig()
            .getLimits();

        double lifetimeInterval = lifetimeCount * publishingInterval;

        // lifetime cannot be longer than the max subscription lifetime.
        double maxSubscriptionLifetime = limits.getMaxSubscriptionLifetime();

        if (lifetimeInterval > maxSubscriptionLifetime) {
            lifetimeCount = (long) (maxSubscriptionLifetime / publishingInterval);

            if (lifetimeCount < UInteger.MAX_VALUE) {
                if (maxSubscriptionLifetime % publishingInterval != 0) {
                    lifetimeCount++;
                }
            }
        }

        // the lifetime must be greater than the keepalive.
        if (maxKeepAliveCount < UInteger.MAX_VALUE / 3) {
            if (maxKeepAliveCount * 3 > lifetimeCount) {
                lifetimeCount = maxKeepAliveCount * 3;
            }

            lifetimeInterval = lifetimeCount * publishingInterval;
        } else {
            lifetimeCount = UInteger.MAX_VALUE;
            lifetimeInterval = Double.MAX_VALUE;
        }

        // apply the minimum.
        double minSubscriptionLifetime = limits.getMinSubscriptionLifetime();

        if (minSubscriptionLifetime > publishingInterval && minSubscriptionLifetime > lifetimeInterval) {
            lifetimeCount = (long) (minSubscriptionLifetime / publishingInterval);

            if (lifetimeCount < UInteger.MAX_VALUE) {
                if (minSubscriptionLifetime % publishingInterval != 0) {
                    lifetimeCount++;
                }
            }
        }

        this.lifetimeCount = lifetimeCount;
    }

    private void setMaxNotificationsPerPublish(long maxNotificationsPerPublish) {
        if (maxNotificationsPerPublish <= 0 || maxNotificationsPerPublish > MAX_NOTIFICATIONS_PER_PUBLISH) {
            maxNotificationsPerPublish = MAX_NOTIFICATIONS_PER_PUBLISH;
        }
        this.maxNotificationsPerPublish = Ints.saturatedCast(maxNotificationsPerPublish);
    }

    private synchronized PublishQueue publishQueue() {
        return subscriptionManager.getPublishQueue();
    }

    private long currentSequenceNumber() {
        return sequenceNumber.get();
    }

    private long nextSequenceNumber() {
        return sequenceNumber.getAndIncrement();
    }

    void resetLifetimeCounter() {
        lifetimeCounter = lifetimeCount;

        logger.debug("[id={}] lifetime counter reset to {}", subscriptionId, lifetimeCounter);
    }

    private void resetKeepAliveCounter() {
        keepAliveCounter = maxKeepAliveCount;

        logger.debug("[id={}] keep-alive counter reset to {}", subscriptionId, maxKeepAliveCount);
    }

    private void returnKeepAlive(ServiceRequest service) {
        ResponseHeader header = service.createResponseHeader();

        UInteger sequenceNumber = uint(currentSequenceNumber());

        NotificationMessage notificationMessage = new NotificationMessage(
            sequenceNumber,
            DateTime.now(),
            new ExtensionObject[0]
        );

        UInteger[] available = getAvailableSequenceNumbers();

        StatusCode[] acknowledgeResults = service.attr(KEY_ACK_RESULTS).get();

        PublishResponse response = new PublishResponse(
            header,
            subscriptionId,
            available,
            moreNotifications,
            notificationMessage,
            acknowledgeResults,
            new DiagnosticInfo[0]
        );

        service.setResponse(response);

        logger.debug("[id={}] returned keep-alive NotificationMessage sequenceNumber={}.",
            subscriptionId, sequenceNumber);
    }

    void returnStatusChangeNotification(ServiceRequest service, StatusCode status) {
        StatusChangeNotification statusChange = new StatusChangeNotification(status, null);

        UInteger sequenceNumber = uint(nextSequenceNumber());

        NotificationMessage notificationMessage = new NotificationMessage(
            sequenceNumber,
            DateTime.now(),
            new ExtensionObject[]{ExtensionObject.encode(serializationContext, statusChange)}
        );

        ResponseHeader header = service.createResponseHeader();

        PublishResponse response = new PublishResponse(
            header,
            subscriptionId,
            new UInteger[0],
            false,
            notificationMessage,
            service.attr(KEY_ACK_RESULTS).get(),
            new DiagnosticInfo[0]
        );

        service.setResponse(response);

        logger.debug(
            "[id={}] returned StatusChangeNotification ({}) sequenceNumber={}.",
            subscriptionId,
            status,
            sequenceNumber
        );
    }

    private void returnNotifications(ServiceRequest service) {
        LinkedHashSet<BaseMonitoredItem<?>> items = new LinkedHashSet<>();

        lastIterator.forEachRemaining(items::add);

        itemsById.values().stream()
            .filter(item -> item.hasNotifications() || item.isTriggered())
            .forEach(items::add);

        PeekingIterator<BaseMonitoredItem<?>> iterator = Iterators.peekingIterator(items.iterator());

        gatherAndSend(iterator, service);

        lastIterator = iterator.hasNext() ? iterator : Collections.emptyIterator();
    }

    /**
     * Gather {@link MonitoredItemNotification}s and send them using {@code service}.
     *
     * @param iterator a {@link PeekingIterator} over the current {@link BaseMonitoredItem}s.
     * @param service  a {@link ServiceRequest}.
     */
    private void gatherAndSend(
        PeekingIterator<BaseMonitoredItem<?>> iterator,
        ServiceRequest service) {

        List<UaStructure> notifications = Lists.newArrayList();

        while (notifications.size() < maxNotificationsPerPublish && iterator.hasNext()) {
            BaseMonitoredItem<?> item = iterator.peek();

            boolean gatheredAllForItem = gather(item, notifications, maxNotificationsPerPublish);

            if (gatheredAllForItem) {
                iterator.next();
            } else {
                // Not being able to gather all notifications for an item implies that
                // notifications.size() is no longer < maxNotificationsPerPublish, but
                // force a break from the loop just in case...
                break;
            }
        }

        moreNotifications = iterator.hasNext();

        sendNotifications(service, notifications);

        if (moreNotifications) {
            ServiceRequest nextService = publishQueue().poll();

            if (nextService != null) {
                gatherAndSend(iterator, nextService);
            } else {
                publishQueue().addSubscription(this);
            }
        }
    }

    private boolean gather(BaseMonitoredItem<?> item, List<UaStructure> notifications, int maxNotifications) {
        int max = maxNotifications - notifications.size();

        return item.getNotifications(notifications, max);
    }

    private void sendNotifications(ServiceRequest service, List<UaStructure> notifications) {
        List<MonitoredItemNotification> dataNotifications = Lists.newArrayList();
        List<EventFieldList> eventNotifications = Lists.newArrayList();

        notifications.forEach(notification -> {
            if (notification instanceof MonitoredItemNotification) {
                dataNotifications.add((MonitoredItemNotification) notification);
            } else if (notification instanceof EventFieldList) {
                eventNotifications.add((EventFieldList) notification);
            }
        });

        List<ExtensionObject> notificationData = Lists.newArrayList();

        if (dataNotifications.size() > 0) {
            DataChangeNotification dataChange = new DataChangeNotification(
                dataNotifications.toArray(new MonitoredItemNotification[0]),
                new DiagnosticInfo[0]
            );

            notificationData.add(ExtensionObject.encode(
                serializationContext,
                dataChange,
                dataChange.getBinaryEncodingId(),
                OpcUaDefaultBinaryEncoding.getInstance()
            ));

            subscriptionDiagnostics.getDataChangeNotificationsCount().add(dataNotifications.size());
        }

        if (eventNotifications.size() > 0) {
            EventNotificationList eventChange = new EventNotificationList(
                eventNotifications.toArray(new EventFieldList[0])
            );

            notificationData.add(ExtensionObject.encode(
                serializationContext,
                eventChange,
                eventChange.getBinaryEncodingId(),
                OpcUaDefaultBinaryEncoding.getInstance()
            ));

            subscriptionDiagnostics.getEventNotificationsCount().add(eventNotifications.size());
        }

        subscriptionDiagnostics.getNotificationsCount().add(notificationData.size());

        UInteger sequenceNumber = uint(nextSequenceNumber());

        NotificationMessage notificationMessage = new NotificationMessage(
            sequenceNumber,
            DateTime.now(),
            notificationData.toArray(new ExtensionObject[0])
        );

        availableMessages.put(notificationMessage.getSequenceNumber(), notificationMessage);

        while (availableMessages.size() > MAX_AVAILABLE_MESSAGES) {
            Map.Entry<UInteger, NotificationMessage> entry = availableMessages.pollFirstEntry();
            if (entry != null) {
                subscriptionDiagnostics.getDiscardedMessageCount().increment();
                logger.debug("Discarded cached NotificationMessage with sequenceNumber={}", entry.getKey());
            }
        }

        UInteger[] available = getAvailableSequenceNumbers();
        StatusCode[] acknowledgeResults = service.attr(KEY_ACK_RESULTS).get();

        ResponseHeader header = service.createResponseHeader();

        PublishResponse response = new PublishResponse(
            header,
            subscriptionId,
            available,
            moreNotifications,
            notificationMessage,
            acknowledgeResults,
            new DiagnosticInfo[0]
        );

        service.setResponse(response);

        logger.debug(
            "[id={}] returning {} DataChangeNotification(s) and " +
                "{} EventNotificationList(s) sequenceNumber={} moreNotifications={}.",
            subscriptionId, dataNotifications.size(),
            eventNotifications.size(), sequenceNumber, moreNotifications);
    }

    private boolean notificationsAvailable() {
        return itemsById.values().stream()
            .anyMatch(item -> item.hasNotifications() || item.isTriggered());
    }

    private void setState(State state) {
        State previousState = this.state.getAndSet(state);

        logger.debug("[id={}] {} -> {}", subscriptionId, previousState, state);

        StateListener listener = stateListener.get();

        if (listener != null) {
            listener.onStateChange(this, previousState, state);
        }

        if (state == State.Late) {
            subscriptionDiagnostics.getLatePublishRequestCount().increment();
        }
    }

    public UInteger getId() {
        return subscriptionId;
    }

    public double getPublishingInterval() {
        return publishingInterval;
    }

    public long getMaxKeepAliveCount() {
        return maxKeepAliveCount;
    }

    public long getLifetimeCount() {
        return lifetimeCount;
    }

    public int getMaxNotificationsPerPublish() {
        return maxNotificationsPerPublish;
    }

    public boolean isPublishingEnabled() {
        return publishingEnabled;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * Get the current value of the keep alive counter.
     *
     * @return the current value of the keep alive counter.
     */
    public long getKeepAliveCounter() {
        return keepAliveCounter;
    }

    /**
     * Get the current value of the lifetime counter.
     *
     * @return the current value of the lifetime counter.
     */
    public long getLifetimeCounter() {
        return lifetimeCounter;
    }

    public synchronized UInteger getMonitoredItemCount() {
        return uint(itemsById.size());
    }

    public synchronized UInteger getDisabledMonitoredItemCount() {
        return uint(
            itemsById.values().stream()
                .filter(m -> m.getMonitoringMode() == MonitoringMode.Disabled)
                .count()
        );
    }

    /**
     * Get the sequence number of the next notification message.
     *
     * @return the sequence number of the next notification message.
     */
    public UInteger getNextSequenceNumber() {
        return uint(sequenceNumber.get());
    }

    public synchronized UInteger[] getAvailableSequenceNumbers() {
        Set<UInteger> uIntegers = availableMessages.keySet();
        UInteger[] available = uIntegers.toArray(new UInteger[0]);
        Arrays.sort(available);
        return available;
    }

    public synchronized UInteger getUnacknowledgeMessageCount() {
        return uint(availableMessages.size());
    }

    public synchronized SubscriptionManager getSubscriptionManager() {
        return subscriptionManager;
    }

    public synchronized void setSubscriptionManager(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    public Session getSession() {
        return subscriptionManager.getSession();
    }

    public long nextItemId() {
        return itemIds.getAndIncrement();
    }

    public void setStateListener(StateListener listener) {
        stateListener.set(listener);
    }

    /**
     * Handle an incoming {@link PublishRequest}.
     *
     * @param service The service request that contains the {@link PublishRequest}.
     */
    synchronized void onPublish(ServiceRequest service) {
        subscriptionDiagnostics.getPublishRequestCount().increment();

        State state = this.state.get();

        if (logger.isTraceEnabled()) {
            logger.trace("[id={}] onPublish(), state={}, keep-alive={}, lifetime={}",
                subscriptionId, state, keepAliveCounter, lifetimeCounter);
        }

        if (state == State.Normal) {
            publishHandler.whenNormal(service);
        } else if (state == State.KeepAlive) {
            publishHandler.whenKeepAlive(service);
        } else if (state == State.Late) {
            publishHandler.whenLate(service);
        } else if (state == State.Closed) {
            publishHandler.whenClosed(service);
        } else {
            throw new RuntimeException("Unhandled subscription state: " + state);
        }
    }

    /**
     * The publishing timer has elapsed.
     */
    synchronized void onPublishingTimer() {
        State state = this.state.get();

        if (logger.isTraceEnabled()) {
            logger.trace(
                "[id={}] onPublishingTimer(), state={}, keep-alive={}, lifetime={}",
                subscriptionId, state, keepAliveCounter, lifetimeCounter);
        }

        // lifetimeCounter is always accessed while synchronized on 'this'.
        lifetimeCounter = lifetimeCounter - 1;

        long startNanos = System.nanoTime();

        if (state == State.Normal) {
            timerHandler.whenNormal();
        } else if (state == State.KeepAlive) {
            timerHandler.whenKeepAlive();
        } else if (state == State.Late) {
            timerHandler.whenLate();
        } else if (state == State.Closed) {
            logger.debug("[id={}] onPublish(), state={}", subscriptionId, state); // No-op.
        } else {
            throw new RuntimeException("unhandled subscription state: " + state);
        }

        long elapsedNanos = System.nanoTime() - startNanos;

        long intervalNanos = TimeUnit.NANOSECONDS.convert(
            DoubleMath.roundToLong(publishingInterval, RoundingMode.UP),
            TimeUnit.MILLISECONDS
        );

        long adjustedIntervalNanos = Math.max(0, intervalNanos - elapsedNanos);

        startPublishingTimer(adjustedIntervalNanos);
    }

    synchronized void startPublishingTimer() {
        long intervalNanos = TimeUnit.NANOSECONDS.convert(
            DoubleMath.roundToLong(publishingInterval, RoundingMode.UP),
            TimeUnit.MILLISECONDS
        );

        startPublishingTimer(intervalNanos);
    }

    private synchronized void startPublishingTimer(long delayNanos) {
        if (state.get() == State.Closed) return;

        if (lifetimeCounter < 1) {
            logger.debug("[id={}] lifetime expired.", subscriptionId);

            setState(State.Closed);
        } else {
            publishingTimer = subscriptionManager.getServer().getScheduledExecutorService().schedule(
                this::onPublishingTimer,
                delayNanos,
                TimeUnit.NANOSECONDS
            );
        }
    }

    public synchronized StatusCode acknowledge(UInteger sequenceNumber) {
        if (availableMessages.remove(sequenceNumber) != null) {
            logger.debug("[id={}] sequence number acknowledged: {}", subscriptionId, sequenceNumber);

            return StatusCode.GOOD;
        } else {
            logger.debug("[id={}] sequence number unknown: {}", subscriptionId, sequenceNumber);

            return new StatusCode(StatusCodes.Bad_SequenceNumberUnknown);
        }
    }

    public synchronized NotificationMessage republish(UInteger sequenceNumber) {
        resetLifetimeCounter();

        subscriptionDiagnostics.getRepublishRequestCount().increment();
        subscriptionDiagnostics.getRepublishMessageRequestCount().increment();

        NotificationMessage notificationMessage = availableMessages.get(sequenceNumber);

        if (notificationMessage != null) {
            subscriptionDiagnostics.getRepublishMessageCount().increment();
        }

        return notificationMessage;
    }

    public SubscriptionDiagnostics getSubscriptionDiagnostics() {
        return subscriptionDiagnostics;
    }

    private class PublishHandler {
        private void whenNormal(ServiceRequest service) {
            boolean publishingEnabled = Subscription.this.publishingEnabled;

            if (!publishingEnabled || (publishingEnabled && !moreNotifications)) {
                /* Subscription State Table Row 4 */
                publishQueue().addRequest(service);
            } else if (publishingEnabled && moreNotifications) {
                /* Subscription State Table Row 5 */
                resetLifetimeCounter();
                resetKeepAliveCounter();
                messageSent = true;
                returnNotifications(service);
            } else {
                throw new IllegalStateException("unhandled subscription state");
            }
        }

        private void whenLate(ServiceRequest service) {
            boolean publishingEnabled = Subscription.this.publishingEnabled;
            boolean notificationsAvailable = notificationsAvailable();

            if (publishingEnabled && (notificationsAvailable || moreNotifications)) {
                /* Subscription State Table Row 10 */
                setState(State.Normal);
                resetLifetimeCounter();
                resetKeepAliveCounter();
                messageSent = true;
                returnNotifications(service);
            } else if (!publishingEnabled ||
                (publishingEnabled && !notificationsAvailable && !moreNotifications)) {
                /* Subscription State Table Row 11 */
                setState(State.KeepAlive);
                resetLifetimeCounter();
                resetKeepAliveCounter();
                messageSent = true;
                returnKeepAlive(service);
            } else {
                throw new IllegalStateException("unhandled subscription state");
            }
        }

        private void whenKeepAlive(ServiceRequest service) {
            /* Subscription State Table Row 13 */
            publishQueue().addRequest(service);
        }

        private void whenClosing(ServiceRequest service) {
            returnStatusChangeNotification(service, new StatusCode(StatusCodes.Bad_Timeout));

            setState(State.Closed);
        }

        private void whenClosed(ServiceRequest service) {
            publishQueue().addRequest(service);
        }
    }

    private class TimerHandler {
        private void whenNormal() {
            boolean publishRequestQueued = publishQueue().isNotEmpty();
            boolean publishingEnabled = Subscription.this.publishingEnabled;
            boolean notificationsAvailable = notificationsAvailable();

            if (publishRequestQueued && publishingEnabled && notificationsAvailable) {
                /* Subscription State Table Row 6 */
                ServiceRequest service = publishQueue().poll();

                if (service != null) {
                    resetLifetimeCounter();
                    resetKeepAliveCounter();
                    messageSent = true;
                    returnNotifications(service);
                } else {
                    whenNormal();
                }
            } else if (publishRequestQueued && !messageSent &&
                (!publishingEnabled || (publishingEnabled && !notificationsAvailable))) {
                /* Subscription State Table Row 7 */
                ServiceRequest service = publishQueue().poll();

                if (service != null) {
                    resetLifetimeCounter();
                    resetKeepAliveCounter();
                    messageSent = true;
                    returnKeepAlive(service);
                } else {
                    whenNormal();
                }
            } else if (!publishRequestQueued && (!messageSent || (publishingEnabled && notificationsAvailable))) {
                /* Subscription State Table Row 8 */
                setState(State.Late);

                publishQueue().addSubscription(Subscription.this);
            } else if (messageSent && (!publishingEnabled || (publishingEnabled && !notificationsAvailable))) {
                /* Subscription State Table Row 9 */
                setState(State.KeepAlive);
                resetKeepAliveCounter();
            } else {
                throw new IllegalStateException("unhandled subscription state");
            }
        }

        private void whenLate() {
            /* Subscription State Table Row 12 */

            // Ensure this subscription is in the PublishQueue wait list.
            // Publishing timer will be started after this method returns.
            publishQueue().addSubscription(Subscription.this);

            subscriptionDiagnostics.getLatePublishRequestCount().increment();
        }

        private void whenKeepAlive() {
            boolean publishingEnabled = Subscription.this.publishingEnabled;
            boolean notificationsAvailable = notificationsAvailable();
            boolean publishRequestQueued = publishQueue().isNotEmpty();

            if (publishingEnabled && notificationsAvailable && publishRequestQueued) {
                /* Subscription State Table Row 14 */
                ServiceRequest service = publishQueue().poll();

                if (service != null) {
                    setState(State.Normal);
                    resetLifetimeCounter();
                    resetKeepAliveCounter();
                    messageSent = true;
                    returnNotifications(service);
                } else {
                    whenKeepAlive();
                }
            } else if (publishRequestQueued && keepAliveCounter == 1 &&
                (!publishingEnabled || (publishingEnabled && !notificationsAvailable))) {
                /* Subscription State Table Row 15 */

                ServiceRequest service = publishQueue().poll();

                if (service != null) {
                    resetLifetimeCounter();
                    resetKeepAliveCounter();
                    returnKeepAlive(service);
                } else {
                    whenKeepAlive();
                }
            } else if (keepAliveCounter > 1 &&
                (!publishingEnabled || (publishingEnabled && !notificationsAvailable))) {
                /* Subscription State Table Row 16 */

                keepAliveCounter--;
            } else if (!publishRequestQueued &&
                (keepAliveCounter == 1 ||
                    (keepAliveCounter > 1 && publishingEnabled && notificationsAvailable))) {
                /* Subscription State Table Row 17 */

                setState(State.Late);

                publishQueue().addSubscription(Subscription.this);
            }
        }
    }

    public enum State {
        Closed,
        Normal,
        KeepAlive,
        Late
    }

    public interface StateListener {
        void onStateChange(Subscription subscription, State previousState, State currentState);
    }

}

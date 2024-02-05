/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyResult;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.supplyAsyncCompose;

public class OpcUaSubscription {

    private static final double DEFAULT_PUBLISHING_INTERVAL = 1000.0;
    private static final UInteger DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH = uint(65535);
    private static final UByte DEFAULT_PRIORITY = ubyte(0);

    private SyncState syncState = SyncState.INITIAL;

    private ServerState serverState;

    private final AtomicReference<Modifications> modifications = new AtomicReference<>(null);

    /**
     * MonitoredItems added to this Subscription, by ClientHandle.
     */
    private final Map<UInteger, OpcUaMonitoredItem> monitoredItems = new ConcurrentHashMap<>();

    /**
     * MonitoredItems that have been removed from the Subscription and are pending deletion on the
     * Server.
     */
    private List<OpcUaMonitoredItem> itemsToDelete = new ArrayList<>();

    private final ClientHandleSequence clientHandleSequence =
        new ClientHandleSequence(monitoredItems::containsKey);

    private Double publishingInterval = DEFAULT_PUBLISHING_INTERVAL;
    private UInteger maxKeepAliveCount = calculateMaxKeepAliveCount(publishingInterval);
    private UInteger lifetimeCount = calculateLifetimeCount(maxKeepAliveCount);
    private UInteger maxNotificationsPerPublish = DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH;
    private UByte priority = DEFAULT_PRIORITY;

    private boolean lifetimeAndKeepAliveCalculated = true;

    private final OpcUaClient client;

    public OpcUaSubscription(OpcUaClient client) {
        this.client = client;
    }

    public OpcUaSubscription(OpcUaClient client, double publishingInterval) {
        this.client = client;
        this.publishingInterval = publishingInterval;
    }

    /**
     * Get the client this Subscription belongs to.
     *
     * @return the {@link OpcUaClient} this Subscription belongs to.
     */
    public OpcUaClient getClient() {
        return client;
    }

    //region Subscription Management

    public void create() throws UaException {
        if (syncState == SyncState.INITIAL) {
            if (maxKeepAliveCount == null) {
                maxKeepAliveCount = calculateMaxKeepAliveCount(publishingInterval);
            }
            if (lifetimeCount == null) {
                lifetimeCount = calculateLifetimeCount(maxKeepAliveCount);
            }

            CreateSubscriptionResponse response = client.createSubscription(
                publishingInterval,
                lifetimeCount,
                maxKeepAliveCount,
                maxNotificationsPerPublish,
                true,
                priority
            );

            syncState = SyncState.SYNCHRONIZED;

            serverState = new ServerState(
                response.getSubscriptionId(),
                response.getRevisedPublishingInterval(),
                response.getRevisedLifetimeCount(),
                response.getRevisedMaxKeepAliveCount(),
                maxNotificationsPerPublish,
                priority,
                true
            );

            client.getPublishingManager().addSubscription(
                this,
                notificationMessage -> {
                    // TODO
                }
            );
        } else {
            throw new UaException(StatusCodes.Bad_InvalidState);
        }
    }

    public CompletionStage<Unit> createAsync() {
        return supplyAsyncCompose(() -> {
            try {
                create();
                return CompletableFuture.completedFuture(Unit.VALUE);
            } catch (UaException e) {
                return CompletableFuture.failedFuture(e);
            }
        }, client.getTransport().getConfig().getExecutor());
    }

    public void modify() throws UaException {
        if (syncState == SyncState.INITIAL) {
            throw new UaException(StatusCodes.Bad_InvalidState);
        } else if (syncState == SyncState.UNSYNCHRONIZED) {
            Modifications diff = modifications.getAndSet(null);
            assert diff != null;

            ModifySubscriptionResponse response = client.modifySubscription(
                serverState.getSubscriptionId(),
                diff.publishingInterval().orElse(serverState.getPublishingInterval()),
                diff.lifetimeCount().orElse(serverState.getLifetimeCount()),
                diff.maxKeepAliveCount().orElse(serverState.getMaxKeepAliveCount()),
                diff.maxNotificationsPerPublish().orElse(maxNotificationsPerPublish),
                diff.priority().orElse(priority)
            );

            serverState = new ServerState(
                serverState.getSubscriptionId(),
                response.getRevisedPublishingInterval(),
                response.getRevisedLifetimeCount(),
                response.getRevisedMaxKeepAliveCount(),
                maxNotificationsPerPublish,
                priority,
                serverState.isPublishingEnabled()
            );

            syncState = SyncState.SYNCHRONIZED;

            if (modifications.get() == null) {
                syncState = SyncState.SYNCHRONIZED;
            }
        }
    }

    public CompletionStage<Unit> modifyAsync() {
        return supplyAsyncCompose(() -> {
            try {
                modify();
                return CompletableFuture.completedFuture(Unit.VALUE);
            } catch (UaException e) {
                return CompletableFuture.failedFuture(e);
            }
        }, client.getTransport().getConfig().getExecutor());
    }

    public void delete() throws UaException {
        if (syncState != SyncState.INITIAL) {
            DeleteSubscriptionsResponse response =
                client.deleteSubscriptions(List.of(serverState.getSubscriptionId()));

            StatusCode[] results = requireNonNull(response.getResults());

            if (results[0].isGood()) {
                serverState = null;
                syncState = SyncState.INITIAL;

                client.getPublishingManager().removeSubscription(this);
            } else {
                throw new UaException(results[0]);
            }
        }
    }

    public CompletionStage<Unit> deleteAsync() {
        return supplyAsyncCompose(() -> {
            try {
                delete();
                return CompletableFuture.completedFuture(Unit.VALUE);
            } catch (UaException e) {
                return CompletableFuture.failedFuture(e);
            }
        }, client.getTransport().getConfig().getExecutor());
    }

    //endregion

    //region MonitoredMode Management

    public void setMonitoringMode(
        MonitoringMode monitoringMode,
        List<OpcUaMonitoredItem> monitoredItems
    ) throws UaException {

        List<UInteger> monitoredItemIds = monitoredItems.stream()
            .map(item -> item.getMonitoredItemId().orElseThrow())
            .collect(Collectors.toList());

        SetMonitoringModeResponse response =
            client.setMonitoringMode(serverState.getSubscriptionId(), monitoringMode, monitoredItemIds);

        StatusCode[] results = requireNonNull(response.getResults());

        for (int i = 0; i < results.length; i++) {
            StatusCode result = results[i];

            monitoredItems.get(i).applySetMonitoringModeResult(result);
        }
    }

    //endregion

    //region Monitored Item Management

    public void addMonitoredItem(OpcUaMonitoredItem item) {
        if (!monitoredItems.containsValue(item)) {
            UInteger clientHandle = clientHandleSequence.nextClientHandle();
            item.setClientHandle(clientHandle);
            item.setSubscription(this);

            monitoredItems.put(clientHandle, item);
        }
    }

    public void addMonitoredItems(List<OpcUaMonitoredItem> items) {
        items.forEach(this::addMonitoredItem);
    }

    public void removeMonitoredItem(OpcUaMonitoredItem item) {
        OpcUaMonitoredItem removedItem =
            item.getClientHandle().map(monitoredItems::remove).orElse(null);

        if (removedItem != null) {
            removedItem.setSubscription(null);
            removedItem.setClientHandle(null);
            itemsToDelete.add(removedItem);
            syncState = SyncState.UNSYNCHRONIZED;
        }
    }

    public void removeMonitoredItems(List<OpcUaMonitoredItem> items) {
        items.forEach(this::removeMonitoredItem);
    }

    /**
     * Synchronize the Subscription's MonitoredItems with the Server.
     * <p>
     * This is a compound operation that deletes any MonitoredItems that have been removed from
     * the Subscription, modifies any existing MonitoredItems that have been changed, and creates
     * any new MonitoredItems that have been added but not yet created on the Server.
     *
     * @return the total number of MonitoredItems that were deleted, modified, or created.
     * @throws UaException if any of the service calls fail.
     */
    public int synchronizeMonitoredItems() throws UaException {
        List<OpcUaMonitoredItem> deletedItems = deleteMonitoredItems();
        List<OpcUaMonitoredItem> modifiedItems = modifyMonitoredItems();
        List<OpcUaMonitoredItem> createdItems = createMonitoredItems();

        return deletedItems.size() + modifiedItems.size() + createdItems.size();
    }

    /**
     * Create any MonitoredItems that have been added to the Subscription but not yet created on
     * the Server.
     *
     * @return a List of the MonitoredItems that were created.
     * @throws UaException if the create service call fails.
     */
    List<OpcUaMonitoredItem> createMonitoredItems() throws UaException {
        List<OpcUaMonitoredItem> itemsToCreate = monitoredItems.values()
            .stream()
            .filter(item -> item.getSyncState() == OpcUaMonitoredItem.SyncState.INITIAL)
            .collect(Collectors.toList());

        if (!itemsToCreate.isEmpty()) {
            CreateMonitoredItemsResponse response = client.createMonitoredItems(
                serverState.getSubscriptionId(),
                TimestampsToReturn.Both,
                itemsToCreate.stream()
                    .map(OpcUaMonitoredItem::newCreateRequest)
                    .collect(Collectors.toList())
            );

            MonitoredItemCreateResult[] results = requireNonNull(response.getResults());

            for (int i = 0; i < results.length; i++) {
                MonitoredItemCreateResult result = results[i];

                itemsToCreate.get(i).applyCreateResult(result);
            }
        }

        return itemsToCreate;
    }

    /**
     * Modify any MonitoredItems that have been changed.
     *
     * @return a List of the MonitoredItems that were modified.
     * @throws UaException if the modify service call fails.
     */
    List<OpcUaMonitoredItem> modifyMonitoredItems() throws UaException {
        List<OpcUaMonitoredItem> itemsToModify = monitoredItems.values()
            .stream()
            .filter(item -> item.getSyncState() == OpcUaMonitoredItem.SyncState.UNSYNCHRONIZED)
            .collect(Collectors.toList());

        if (!itemsToModify.isEmpty()) {
            ModifyMonitoredItemsResponse response = client.modifyMonitoredItems(
                serverState.getSubscriptionId(),
                TimestampsToReturn.Both,
                itemsToModify.stream()
                    .map(OpcUaMonitoredItem::newModifyRequest)
                    .collect(Collectors.toList())
            );

            MonitoredItemModifyResult[] results = requireNonNull(response.getResults());

            for (int i = 0; i < results.length; i++) {
                MonitoredItemModifyResult result = results[i];

                itemsToModify.get(i).applyModifyResult(result);
            }
        }

        return itemsToModify;
    }

    /**
     * Delete any MonitoredItems that have been removed from the Subscription.
     *
     * @return a List of the MonitoredItems that were deleted.
     * @throws UaException if the delete service call fails.
     */
    List<OpcUaMonitoredItem> deleteMonitoredItems() throws UaException {
        List<OpcUaMonitoredItem> itemsToDelete = this.itemsToDelete;
        this.itemsToDelete = new ArrayList<>();

        if (!itemsToDelete.isEmpty()) {
            List<UInteger> itemIds = itemsToDelete.stream()
                .map(item -> item.getMonitoredItemId().orElseThrow())
                .collect(Collectors.toList());

            DeleteMonitoredItemsResponse response =
                client.deleteMonitoredItems(serverState.getSubscriptionId(), itemIds);

            StatusCode[] results = requireNonNull(response.getResults());

            for (int i = 0; i < results.length; i++) {
                StatusCode result = results[i];

                itemsToDelete.get(i).applyDeleteResult(result);
            }
        }

        return itemsToDelete;
    }

    //endregion

    public void setPublishingMode(boolean enabled) throws UaException {
        if (syncState == SyncState.INITIAL) {
            throw new UaException(StatusCodes.Bad_InvalidState);
        } else {
            SetPublishingModeResponse response = client.setPublishingMode(
                enabled,
                List.of(serverState.getSubscriptionId())
            );

            StatusCode result = requireNonNull(response.getResults())[0];

            if (result.isGood()) {
                serverState = new ServerState(
                    serverState.getSubscriptionId(),
                    serverState.getPublishingInterval(),
                    serverState.getLifetimeCount(),
                    serverState.getMaxKeepAliveCount(),
                    maxNotificationsPerPublish,
                    priority,
                    enabled
                );
            } else {
                throw new UaException(result);
            }
        }
    }

    public CompletionStage<Unit> setPublishingModeAsync(boolean enabled) {
        return supplyAsyncCompose(() -> {
            try {
                setPublishingMode(enabled);
                return CompletableFuture.completedFuture(Unit.VALUE);
            } catch (UaException e) {
                return CompletableFuture.failedFuture(e);
            }
        }, client.getTransport().getConfig().getExecutor());
    }

    public SyncState getSyncState() {
        return syncState;
    }

    public Optional<ServerState> getServerState() {
        return Optional.ofNullable(serverState);
    }

    public Double getPublishingInterval() {
        return publishingInterval;
    }

    public UInteger getLifetimeCount() {
        return lifetimeCount;
    }

    public UInteger getMaxKeepAliveCount() {
        return maxKeepAliveCount;
    }

    /**
     * Get the Subscription's Priority setting.
     * <p>
     * The Server does not revise this setting, so the value reflects the most recently requested
     * by a create or modify operation.
     *
     * @return the Subscription's Priority setting.
     */
    public UByte getPriority() {
        return priority;
    }

    /**
     * Get the Subscription's MaxNotificationsPerPublish setting.
     * <p>
     * The Server does not revise this setting, so the value reflects the most recently requested
     * by a create or modify operation.
     *
     * @return the Subscription's MaxNotificationsPerPublish setting.
     */
    public UInteger getMaxNotificationsPerPublish() {
        return maxNotificationsPerPublish;
    }

    public Optional<Boolean> isPublishingEnabled() {
        return getServerState().map(ServerState::isPublishingEnabled);
    }

    /**
     * Get the SubscriptionId assigned to this Subscription by the Server.
     * <p>
     * The SubscriptionId is available only after the Subscription has been created.
     *
     * @return the SubscriptionId assigned to this Subscription by the Server.
     */
    public Optional<UInteger> getSubscriptionId() {
        return getServerState().map(ServerState::getSubscriptionId);
    }

    /**
     * Get the revised PublishingInterval from the most recent create or modify operation.
     * <p>
     * The revised PublishingInterval is available only after the Subscription has been created
     * or modified.
     *
     * @return the revised PublishingInterval from the most recent create or modify operation.
     */
    public Optional<Double> getRevisedPublishingInterval() {
        return getServerState().map(ServerState::getPublishingInterval);
    }

    /**
     * Get the revised LifetimeCount from the most recent create or modify operation.
     * <p>
     * The revised LifetimeCount is available only after the Subscription has been created or
     * modified.
     *
     * @return the revised LifetimeCount from the most recent create or modify operation.
     */
    public Optional<UInteger> getRevisedLifetimeCount() {
        return getServerState().map(ServerState::getLifetimeCount);
    }

    /**
     * Get the revised MaxKeepAliveCount from the most recent create or modify operation.
     * <p>
     * The revised MaxKeepAliveCount is available only after the Subscription has been created
     * or modified.
     *
     * @return the revised MaxKeepAliveCount from the most recent create or modify operation.
     */
    public Optional<UInteger> getRevisedMaxKeepAliveCount() {
        return getServerState().map(ServerState::getMaxKeepAliveCount);
    }

    /**
     * Set a new PublishingInterval for this Subscription.
     * <p>
     * If the Subscription has not yet been created, this will be the PublishingInterval used
     * during the create service call.
     * <p>
     * If the Subscription has already been created, this will be the PublishingInterval used
     * during the next modify service call. {@link #getPublishingInterval()} will not
     * reflect this change until the modify service call has completed.
     *
     * @param publishingInterval the new PublishingInterval.
     * @see #create()
     * @see #createAsync()
     * @see #modify()
     * @see #modifyAsync()
     */
    public void setPublishingInterval(Double publishingInterval) {
        this.publishingInterval = publishingInterval;

        Modifications diff = modifications.updateAndGet(
            d ->
                Objects.requireNonNullElseGet(d, Modifications::new)
        );

        diff.publishingInterval = publishingInterval;

        syncState = SyncState.UNSYNCHRONIZED;

        if (lifetimeAndKeepAliveCalculated) {
            UInteger maxKeepAliveCount = calculateMaxKeepAliveCount(publishingInterval);
            UInteger lifetimeCount = calculateLifetimeCount(maxKeepAliveCount);

            setMaxKeepAliveCount(maxKeepAliveCount);
            setLifetimeCount(lifetimeCount);
        }
    }

    /**
     * Set a new LifetimeCount for this Subscription.
     * <p>
     * If the Subscription has not yet been created, this will be the LifetimeCount used during
     * the create service call.
     * <p>
     * If the Subscription has already been created, this will be the LifetimeCount used during
     * the next modify service call. {@link #getLifetimeCount()}} will not reflect this
     * change until the modify service call has completed.
     *
     * @param lifetimeCount the new LifetimeCount.
     * @see #create()
     * @see #createAsync()
     * @see #modify()
     * @see #modifyAsync()
     */
    public void setLifetimeCount(UInteger lifetimeCount) {
        this.lifetimeCount = lifetimeCount;

        Modifications diff = modifications.updateAndGet(
            d ->
                Objects.requireNonNullElseGet(d, Modifications::new)
        );

        diff.lifetimeCount = lifetimeCount;

        syncState = SyncState.UNSYNCHRONIZED;
    }

    /**
     * Set a new MaxKeepAliveCount for this Subscription.
     * <p>
     * If the Subscription has not yet been created, this will be the MaxKeepAliveCount used during
     * the create service call.
     * <p>
     * If the Subscription has already been created, this will be the MaxKeepAliveCount used during
     * the next modify service call. {@link #getMaxKeepAliveCount()}} will not reflect
     * this change until the modify service call has completed.
     *
     * @param maxKeepAliveCount the new MaxKeepAliveCount.
     * @see #create()
     * @see #createAsync()
     * @see #modify()
     * @see #modifyAsync()
     */
    public void setMaxKeepAliveCount(UInteger maxKeepAliveCount) {
        this.maxKeepAliveCount = maxKeepAliveCount;

        Modifications diff = modifications.updateAndGet(
            d ->
                Objects.requireNonNullElseGet(d, Modifications::new)
        );

        diff.maxKeepAliveCount = maxKeepAliveCount;

        syncState = SyncState.UNSYNCHRONIZED;
    }

    /**
     * Set a new Priority for this Subscription.
     * <p>
     * If the Subscription has not yet been created, this will be the Priority used during the
     * create service call.
     * <p>
     * If the Subscription has already been created, this will be the Priority used during the
     * next modify service call. {@link #getPriority()} will not reflect this change until the
     * modify service call has completed.
     *
     * @param priority the new Priority.
     * @see #create()
     * @see #createAsync()
     * @see #modify()
     * @see #modifyAsync()
     */
    public void setPriority(UByte priority) {
        this.priority = priority;

        Modifications diff = modifications.updateAndGet(
            d ->
                Objects.requireNonNullElseGet(d, Modifications::new)
        );

        diff.priority = priority;

        syncState = SyncState.UNSYNCHRONIZED;
    }

    /**
     * Set a new MaxNotificationsPerPublish for this Subscription.
     * <p>
     * If the Subscription has not yet been created, this will be the MaxNotificationsPerPublish
     * used during the create service call.
     * <p>
     * If the Subscription has already been created, this will be the MaxNotificationsPerPublish
     * used during the next modify service call. {@link #getMaxNotificationsPerPublish()} will
     * not reflect this change until the modify service call has completed.
     *
     * @param maxNotificationsPerPublish the new MaxNotificationsPerPublish.
     * @see #create()
     * @see #createAsync()
     * @see #modify()
     * @see #modifyAsync()
     */
    public void setMaxNotificationsPerPublish(UInteger maxNotificationsPerPublish) {
        this.maxNotificationsPerPublish = maxNotificationsPerPublish;

        Modifications diff = modifications.updateAndGet(
            d ->
                Objects.requireNonNullElseGet(d, Modifications::new)
        );

        diff.maxNotificationsPerPublish = maxNotificationsPerPublish;

        syncState = SyncState.UNSYNCHRONIZED;
    }

    /**
     * Set whether the LifetimeCount and MaxKeepAliveCount should be calculated automatically any
     * time the PublishingInterval is set.
     *
     * @param lifetimeAndKeepAliveCalculated {@code true} if the LifetimeCount and
     *     MaxKeepAliveCount should be calculated automatically.
     * @see #isLifetimeAndKeepAliveCalculated()
     */
    public void setLifetimeAndKeepAliveCalculated(boolean lifetimeAndKeepAliveCalculated) {
        this.lifetimeAndKeepAliveCalculated = lifetimeAndKeepAliveCalculated;
    }

    /**
     * @return {@code true} if the LifetimeCount and MaxKeepAliveCount are calculated
     *     automatically any time the Publishing Interval is set.
     * @see #setLifetimeAndKeepAliveCalculated(boolean)
     */
    public boolean isLifetimeAndKeepAliveCalculated() {
        return lifetimeAndKeepAliveCalculated;
    }

    private static UInteger calculateMaxKeepAliveCount(double publishingInterval) {
        // Send a keep-alive every 10 seconds if the publishing interval is faster than
        // 10 seconds, or every publishing interval otherwise.
        int count = (int) Math.ceil(10000.0 / Math.max(1, publishingInterval));

        return uint(Math.max(1, count));
    }

    private static UInteger calculateLifetimeCount(UInteger maxKeepAliveCount) {
        // Lifetime must be 3x (or greater) the keep-alive count.
        BigInteger lifetimeCount = maxKeepAliveCount.toBigInteger()
            .multiply(BigInteger.valueOf(5))
            .min(BigInteger.valueOf(UInteger.MAX_VALUE));

        return uint(lifetimeCount.longValue());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OpcUaSubscription.class.getSimpleName() + "[", "]")
            .add("subscriptionId=" + getSubscriptionId().orElse(null))
            .add("state=" + syncState)
            .toString();
    }

    private static class Modifications {

        private @Nullable Double publishingInterval;
        private @Nullable UInteger lifetimeCount;
        private @Nullable UInteger maxKeepAliveCount;
        private @Nullable UInteger maxNotificationsPerPublish;
        private @Nullable UByte priority;

        private Optional<Double> publishingInterval() {
            return Optional.ofNullable(publishingInterval);
        }

        private Optional<UInteger> lifetimeCount() {
            return Optional.ofNullable(lifetimeCount);
        }

        private Optional<UInteger> maxKeepAliveCount() {
            return Optional.ofNullable(maxKeepAliveCount);
        }

        private Optional<UInteger> maxNotificationsPerPublish() {
            return Optional.ofNullable(maxNotificationsPerPublish);
        }

        private Optional<UByte> priority() {
            return Optional.ofNullable(priority);
        }

    }

    /**
     * The state of the Subscription as it exists on the server (based on the most recent,
     * successful, create or modify operations).
     */
    public static class ServerState {

        private final UInteger subscriptionId;
        private final Double publishingInterval;
        private final UInteger lifetimeCount;
        private final UInteger maxKeepAliveCount;
        private final UInteger maxNotificationsPerPublish;
        private final UByte priority;
        private final boolean publishingEnabled;

        public ServerState(
            UInteger subscriptionId,
            Double publishingInterval,
            UInteger lifetimeCount,
            UInteger maxKeepAliveCount,
            UInteger maxNotificationsPerPublish,
            UByte priority,
            boolean publishingEnabled
        ) {

            this.subscriptionId = subscriptionId;
            this.publishingInterval = publishingInterval;
            this.lifetimeCount = lifetimeCount;
            this.maxKeepAliveCount = maxKeepAliveCount;
            this.maxNotificationsPerPublish = maxNotificationsPerPublish;
            this.priority = priority;
            this.publishingEnabled = publishingEnabled;
        }

        public UInteger getSubscriptionId() {
            return subscriptionId;
        }

        public Double getPublishingInterval() {
            return publishingInterval;
        }

        public UInteger getLifetimeCount() {
            return lifetimeCount;
        }

        public UInteger getMaxKeepAliveCount() {
            return maxKeepAliveCount;
        }

        public UInteger getMaxNotificationsPerPublish() {
            return maxNotificationsPerPublish;
        }

        public UByte getPriority() {
            return priority;
        }

        public boolean isPublishingEnabled() {
            return publishingEnabled;
        }

    }

    public enum SyncState {

        /**
         * The Subscription has been instantiated but does not exist on the server.
         */
        INITIAL,

        /**
         * The Subscription has been created on the server and has no outstanding modifications to
         * synchronize.
         */
        SYNCHRONIZED,

        /**
         * The Subscription has been created on the server but has outstanding modifications to
         * synchronize.
         */
        UNSYNCHRONIZED

    }

}

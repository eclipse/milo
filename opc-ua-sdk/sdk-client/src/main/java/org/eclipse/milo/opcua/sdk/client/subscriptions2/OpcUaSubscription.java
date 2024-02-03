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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyResult;
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

    private State state = State.INITIAL;

    private final AtomicReference<ModificationDiff> pendingModification = new AtomicReference<>(null);

    private final ReentrantLock lock = new ReentrantLock();


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

    private Double requestedPublishingInterval = DEFAULT_PUBLISHING_INTERVAL;
    private UInteger requestedMaxKeepAliveCount = calculateMaxKeepAliveCount(requestedPublishingInterval);
    private UInteger requestedLifetimeCount = calculateLifetimeCount(requestedMaxKeepAliveCount);
    private UInteger maxNotificationsPerPublish = DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH;
    private UByte priority = DEFAULT_PRIORITY;

    private UInteger subscriptionId;
    private Double revisedPublishingInterval;
    private UInteger revisedLifetimeCount;
    private UInteger revisedMaxKeepAliveCount;

    private boolean lifetimeAndKeepAliveCalculated = true;

    private final OpcUaClient client;

    public OpcUaSubscription(OpcUaClient client) {
        this.client = client;
    }

    public OpcUaSubscription(OpcUaClient client, double requestedPublishingInterval) {
        this.client = client;
        this.requestedPublishingInterval = requestedPublishingInterval;
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
        lock.lock();
        try {
            if (state == State.INITIAL) {
                if (requestedMaxKeepAliveCount == null) {
                    requestedMaxKeepAliveCount = calculateMaxKeepAliveCount(requestedPublishingInterval);
                }
                if (requestedLifetimeCount == null) {
                    requestedLifetimeCount = calculateLifetimeCount(requestedMaxKeepAliveCount);
                }

                CreateSubscriptionResponse response = client.createSubscription(
                    requestedPublishingInterval,
                    requestedLifetimeCount,
                    requestedMaxKeepAliveCount,
                    maxNotificationsPerPublish,
                    true,
                    priority
                );

                state = State.SYNCHRONIZED;

                subscriptionId = response.getSubscriptionId();
                revisedPublishingInterval = response.getRevisedPublishingInterval();
                revisedLifetimeCount = response.getRevisedLifetimeCount();
                revisedMaxKeepAliveCount = response.getRevisedMaxKeepAliveCount();

                client.getPublishingManager().addSubscription(
                    this,
                    notificationMessage -> {
                        // TODO
                    }
                );
            } else {
                throw new UaException(StatusCodes.Bad_InvalidState);
            }
        } finally {
            lock.unlock();
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
        try {
            modifyAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<Unit> modifyAsync() {
        try {
            lock.lock();

            if (state == State.INITIAL) {
                return CompletableFuture.failedFuture(new UaException(StatusCodes.Bad_InvalidState));
            } else if (state == State.SYNCHRONIZED) {
                return CompletableFuture.completedFuture(Unit.VALUE);
            } else {
                ModificationDiff diff = pendingModification.getAndSet(null);
                assert diff != null;

                CompletableFuture<ModifySubscriptionResponse> future = client.modifySubscriptionAsync(
                    subscriptionId,
                    diff.requestedPublishingInterval().orElse(revisedPublishingInterval),
                    diff.requestedLifetimeCount().orElse(revisedLifetimeCount),
                    diff.requestedMaxKeepAliveCount().orElse(revisedMaxKeepAliveCount),
                    diff.maxNotificationsPerPublish().orElse(maxNotificationsPerPublish),
                    diff.priority().orElse(priority)
                );

                return future.thenCompose(response -> {
                    try {
                        lock.lock();

                        requestedPublishingInterval = diff.requestedPublishingInterval().orElse(requestedPublishingInterval);
                        requestedLifetimeCount = diff.requestedLifetimeCount().orElse(requestedLifetimeCount);
                        requestedMaxKeepAliveCount = diff.requestedMaxKeepAliveCount().orElse(requestedMaxKeepAliveCount);
                        maxNotificationsPerPublish = diff.maxNotificationsPerPublish().orElse(maxNotificationsPerPublish);
                        priority = diff.priority().orElse(priority);

                        revisedPublishingInterval = response.getRevisedPublishingInterval();
                        revisedLifetimeCount = response.getRevisedLifetimeCount();
                        revisedMaxKeepAliveCount = response.getRevisedMaxKeepAliveCount();

                        if (pendingModification.get() == null) {
                            state = State.SYNCHRONIZED;
                        }
                    } finally {
                        lock.unlock();
                    }

                    return CompletableFuture.completedFuture(Unit.VALUE);
                });
            }
        } finally {
            lock.unlock();
        }
    }

    public void delete() throws UaException {
        try {
            deleteAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<Unit> deleteAsync() {
        try {
            lock.lock();

            if (state != State.INITIAL) {
                CompletableFuture<DeleteSubscriptionsResponse> future =
                    client.deleteSubscriptionsAsync(List.of(subscriptionId));

                return future.thenCompose(response -> {
                    try {
                        lock.lock();

                        subscriptionId = null;
                        revisedPublishingInterval = null;
                        revisedLifetimeCount = null;
                        revisedMaxKeepAliveCount = null;

                        state = State.INITIAL;
                    } finally {
                        lock.unlock();
                    }

                    client.getPublishingManager().removeSubscription(this);

                    return CompletableFuture.completedFuture(Unit.VALUE);
                });
            } else {
                return CompletableFuture.completedFuture(Unit.VALUE);
            }
        } finally {
            lock.unlock();
        }
    }

    //endregion

    //region Monitored Item Management

    public void addMonitoredItem(OpcUaMonitoredItem item) {
        UInteger clientHandle = clientHandleSequence.nextClientHandle();
        item.setClientHandle(clientHandle);
        item.setSubscription(this);
        monitoredItems.put(clientHandle, item);
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
            state = State.UNSYNCHRONIZED;
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
            .filter(item -> item.getState() == OpcUaMonitoredItem.State.INITIAL)
            .collect(Collectors.toList());

        if (!itemsToCreate.isEmpty()) {
            CreateMonitoredItemsResponse response = client.createMonitoredItems(
                subscriptionId,
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
            .filter(item -> item.getState() == OpcUaMonitoredItem.State.UNSYNCHRONIZED)
            .collect(Collectors.toList());

        if (!itemsToModify.isEmpty()) {
            ModifyMonitoredItemsResponse response = client.modifyMonitoredItems(
                subscriptionId,
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
                client.deleteMonitoredItems(subscriptionId, itemIds);

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
        try {
            setPublishingModeAsync(enabled).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<Unit> setPublishingModeAsync(boolean enabled) {
        if (state == State.INITIAL) {
            return CompletableFuture.failedFuture(new UaException(StatusCodes.Bad_InvalidState));
        } else {
            CompletableFuture<SetPublishingModeResponse> future = client.setPublishingModeAsync(
                enabled,
                List.of(subscriptionId)
            );

            return future.thenCompose(response -> CompletableFuture.completedFuture(Unit.VALUE));
        }
    }

    public State getState() {
        return state;
    }

    public Double getRequestedPublishingInterval() {
        return requestedPublishingInterval;
    }

    public UInteger getRequestedLifetimeCount() {
        return requestedLifetimeCount;
    }

    public UInteger getRequestedMaxKeepAliveCount() {
        return requestedMaxKeepAliveCount;
    }

    /**
     * Get the SubscriptionId assigned to this Subscription by the Server.
     * <p>
     * The SubscriptionId is available only after the Subscription has been created.
     *
     * @return the SubscriptionId assigned to this Subscription by the Server.
     */
    public Optional<UInteger> getSubscriptionId() {
        return Optional.ofNullable(subscriptionId);
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
        return Optional.ofNullable(revisedPublishingInterval);
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
        return Optional.ofNullable(revisedLifetimeCount);
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
        return Optional.ofNullable(revisedMaxKeepAliveCount);
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

    /**
     * Set a new PublishingInterval for this Subscription.
     * <p>
     * If the Subscription has not yet been created, this will be the PublishingInterval used
     * during the create service call.
     * <p>
     * If the Subscription has already been created, this will be the PublishingInterval used
     * during the next modify service call. {@link #getRequestedPublishingInterval()} will not
     * reflect this change until the modify service call has completed.
     *
     * @param publishingInterval the new PublishingInterval.
     * @see #create()
     * @see #createAsync()
     * @see #modify()
     * @see #modifyAsync()
     */
    public void setPublishingInterval(Double publishingInterval) {
        try {
            lock.lock();

            if (state == State.INITIAL) {
                this.requestedPublishingInterval = publishingInterval;
            } else {
                ModificationDiff diff = pendingModification.updateAndGet(d -> Objects.requireNonNullElseGet(d, ModificationDiff::new));

                diff.requestedPublishingInterval = publishingInterval;

                state = State.UNSYNCHRONIZED;
            }

            if (lifetimeAndKeepAliveCalculated) {
                UInteger maxKeepAliveCount = calculateMaxKeepAliveCount(publishingInterval);
                UInteger lifetimeCount = calculateLifetimeCount(maxKeepAliveCount);

                setMaxKeepAliveCount(maxKeepAliveCount);
                setLifetimeCount(lifetimeCount);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Set a new LifetimeCount for this Subscription.
     * <p>
     * If the Subscription has not yet been created, this will be the LifetimeCount used during
     * the create service call.
     * <p>
     * If the Subscription has already been created, this will be the LifetimeCount used during
     * the next modify service call. {@link #getRequestedLifetimeCount()}} will not reflect this
     * change until the modify service call has completed.
     *
     * @param lifetimeCount the new LifetimeCount.
     * @see #create()
     * @see #createAsync()
     * @see #modify()
     * @see #modifyAsync()
     */
    public void setLifetimeCount(UInteger lifetimeCount) {
        try {
            lock.lock();

            if (state == State.INITIAL) {
                this.requestedLifetimeCount = lifetimeCount;
            } else {
                ModificationDiff diff = pendingModification.updateAndGet(d -> Objects.requireNonNullElseGet(d, ModificationDiff::new));
                diff.requestedLifetimeCount = lifetimeCount;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Set a new MaxKeepAliveCount for this Subscription.
     * <p>
     * If the Subscription has not yet been created, this will be the MaxKeepAliveCount used during
     * the create service call.
     * <p>
     * If the Subscription has already been created, this will be the MaxKeepAliveCount used during
     * the next modify service call. {@link #getRequestedMaxKeepAliveCount()}} will not reflect
     * this change until the modify service call has completed.
     *
     * @param maxKeepAliveCount the new MaxKeepAliveCount.
     * @see #create()
     * @see #createAsync()
     * @see #modify()
     * @see #modifyAsync()
     */
    public void setMaxKeepAliveCount(UInteger maxKeepAliveCount) {
        try {
            lock.lock();

            if (state == State.INITIAL) {
                this.requestedMaxKeepAliveCount = maxKeepAliveCount;
            } else {
                ModificationDiff diff = pendingModification.updateAndGet(d -> Objects.requireNonNullElseGet(d, ModificationDiff::new));
                diff.requestedMaxKeepAliveCount = maxKeepAliveCount;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
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
        try {
            lock.lock();

            if (state == State.INITIAL) {
                this.priority = priority;
            } else {
                ModificationDiff diff = pendingModification.updateAndGet(d -> Objects.requireNonNullElseGet(d, ModificationDiff::new));
                diff.priority = priority;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
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
        try {
            lock.lock();

            if (state == State.INITIAL) {
                this.maxNotificationsPerPublish = maxNotificationsPerPublish;
            } else {
                ModificationDiff diff = pendingModification.updateAndGet(d -> Objects.requireNonNullElseGet(d, ModificationDiff::new));
                diff.maxNotificationsPerPublish = maxNotificationsPerPublish;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
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
        try {
            lock.lock();
            this.lifetimeAndKeepAliveCalculated = lifetimeAndKeepAliveCalculated;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @return {@code true} if the LifetimeCount and MaxKeepAliveCount are calculated
     *     automatically any time the Publishing Interval is set.
     * @see #setLifetimeAndKeepAliveCalculated(boolean)
     */
    public boolean isLifetimeAndKeepAliveCalculated() {
        try {
            lock.lock();

            return lifetimeAndKeepAliveCalculated;
        } finally {
            lock.unlock();
        }
    }

    UInteger nextClientHandle() {
        return clientHandleSequence.nextClientHandle();
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
            .add("subscriptionId=" + subscriptionId)
            .add("state=" + state)
            .toString();
    }

    private static class ModificationDiff {
        private @Nullable Double requestedPublishingInterval;
        private @Nullable UInteger requestedLifetimeCount;
        private @Nullable UInteger requestedMaxKeepAliveCount;
        private @Nullable UInteger maxNotificationsPerPublish;
        private @Nullable UByte priority;

        private Optional<Double> requestedPublishingInterval() {
            return Optional.ofNullable(requestedPublishingInterval);
        }

        private Optional<UInteger> requestedLifetimeCount() {
            return Optional.ofNullable(requestedLifetimeCount);
        }

        private Optional<UInteger> requestedMaxKeepAliveCount() {
            return Optional.ofNullable(requestedMaxKeepAliveCount);
        }

        private Optional<UInteger> maxNotificationsPerPublish() {
            return Optional.ofNullable(maxNotificationsPerPublish);
        }

        private Optional<UByte> priority() {
            return Optional.ofNullable(priority);
        }
    }

    public enum State {

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

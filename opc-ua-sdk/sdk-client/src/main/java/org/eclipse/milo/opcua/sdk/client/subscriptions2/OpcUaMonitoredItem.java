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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.CreateMonitoredItemBatch;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.DeleteMonitoredItemBatch;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.ModifyMonitoredItemBatch;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.SetMonitoringModeBatch;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaMonitoredItem {


    private State state = State.INITIAL;
    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicReference<ModificationDiff> diffRef = new AtomicReference<>(null);


    // MonitoredItem parameters that a user might modify via SetMonitoringMode:
    private MonitoringMode monitoringMode = MonitoringMode.Reporting;

    // MonitoredItem parameters that a user might modify via ModifyMonitoredItem:
    private @Nullable UInteger clientHandle;
    private Double requestedSamplingInterval = 1000.0;
    private @Nullable ExtensionObject filter;
    private UInteger requestedQueueSize = uint(1);
    private boolean discardOldest = true;

    private @Nullable UInteger monitoredItemId;
    private @Nullable Double revisedSamplingInterval;
    private @Nullable UInteger revisedQueueSize;
    private @Nullable ExtensionObject filterResult;

    private final OpcUaSubscription subscription;
    private final ReadValueId readValueId;

    public OpcUaMonitoredItem(OpcUaSubscription subscription, ReadValueId readValueId) {
        this.subscription = subscription;
        this.readValueId = readValueId;
    }

    public StatusCode create() throws UaException {
        try {
            return createAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<StatusCode> createAsync() {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                if (clientHandle == null) {
                    clientHandle = subscription.nextClientHandle();
                }

                var request = new MonitoredItemCreateRequest(
                    readValueId,
                    monitoringMode,
                    new MonitoringParameters(
                        clientHandle,
                        requestedSamplingInterval,
                        filter,
                        requestedQueueSize,
                        discardOldest
                    )
                );

                CompletableFuture<CreateMonitoredItemsResponse> future =
                    subscription.getClient().createMonitoredItemsAsync(
                        subscription.getSubscriptionId().orElseThrow(),
                        TimestampsToReturn.Both,
                        List.of(request)
                    );

                return future.thenCompose(response -> {
                    lock.lock();
                    try {
                        MonitoredItemCreateResult result = requireNonNull(response.getResults())[0];

                        StatusCode statusCode = result.getStatusCode();

                        if (statusCode.isGood()) {
                            this.monitoredItemId = result.getMonitoredItemId();
                            this.filterResult = result.getFilterResult();
                            this.revisedQueueSize = result.getRevisedQueueSize();
                            this.revisedSamplingInterval = result.getRevisedSamplingInterval();

                            state = State.SYNCHRONIZED;
                        } else {
                            // TODO
                            state = State.INITIAL;
                        }

                        return CompletableFuture.completedFuture(statusCode);
                    } finally {
                        lock.unlock();
                    }
                });
            } else {
                return CompletableFuture.completedFuture(StatusCode.GOOD);
            }
        } finally {
            lock.unlock();
        }
    }


    public void create(CreateMonitoredItemBatch batch) {}

    public void modify() throws UaException {}

    public void modify(ModifyMonitoredItemBatch batch) {}

    public void delete() throws UaException {}

    public void delete(DeleteMonitoredItemBatch batch) {}

    public void setMonitoringMode(MonitoringMode monitoringMode) throws UaException {
        // TODO
    }

    public void setMonitoringMode(MonitoringMode monitoringMode, SetMonitoringModeBatch batch) {
        // TODO
    }

    public void setClientHandle(UInteger clientHandle) {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                this.clientHandle = clientHandle;
            } else {
                ModificationDiff diff = diffRef.updateAndGet(
                    d ->
                        Objects.requireNonNullElseGet(d, ModificationDiff::new)
                );

                diff.clientHandle = clientHandle;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
    }

    public void setSamplingInterval(double samplingInterval) {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                this.requestedSamplingInterval = samplingInterval;
            } else {
                ModificationDiff diff = diffRef.updateAndGet(
                    d ->
                        Objects.requireNonNullElseGet(d, ModificationDiff::new)
                );

                diff.requestedSamplingInterval = samplingInterval;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
    }

    public void setRequestedQueueSize(UInteger requestedQueueSize) {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                this.requestedQueueSize = requestedQueueSize;
            } else {
                ModificationDiff diff = diffRef.updateAndGet(
                    d ->
                        Objects.requireNonNullElseGet(d, ModificationDiff::new)
                );

                diff.requestedQueueSize = requestedQueueSize;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
    }

    public void setDiscardOldest(boolean discardOldest) {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                this.discardOldest = discardOldest;
            } else {
                ModificationDiff diff = diffRef.updateAndGet(
                    d ->
                        Objects.requireNonNullElseGet(d, ModificationDiff::new)
                );

                diff.discardOldest = discardOldest;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
    }

    public Optional<UInteger> getClientHandle() {
        return Optional.ofNullable(clientHandle);
    }

    public Optional<UInteger> getMonitoredItemId() {
        return Optional.ofNullable(monitoredItemId);
    }

    public Optional<UInteger> getRevisedQueueSize() {
        return Optional.ofNullable(revisedQueueSize);
    }

    public Optional<Double> getRevisedSamplingInterval() {
        return Optional.ofNullable(revisedSamplingInterval);
    }

    public Optional<ExtensionObject> getFilterResult() {
        return Optional.ofNullable(filterResult);
    }

    public void addDataValueListener(DataValueListener listener) {}

    public void removeDataValueListener(DataValueListener listener) {}

    public void addEventValueListener(EventValueListener listener) {}

    public void removeEventValueListener(EventValueListener listener) {}

    /**
     * A callback that receives notification of new values for a {@link ManagedDataItem}.
     */
    public interface DataValueListener {

        /**
         * A new {@link DataValue} for {@code item} has arrived.
         * <p>
         * Take care not to block unnecessarily in this callback because subscription notifications
         * are processed synchronously as a backpressure mechanism. Blocking inside this callback
         * will prevent subsequent notifications from being processed and new PublishRequests from
         * being sent.
         *
         * @param item the {@link OpcUaMonitoredItem} for which a new value has arrived.
         * @param value the new {@link DataValue}.
         */
        void onDataValueReceived(OpcUaMonitoredItem item, DataValue value);

    }

    /**
     * A callback that receives notification of new events for a {@link OpcUaMonitoredItem}.
     */
    public interface EventValueListener {

        /**
         * A new event for {@code item} has arrived.
         * <p>
         * Take care not to block unnecessarily in this callback because subscription notifications
         * are processed synchronously as a backpressure mechanism. Blocking inside this callback
         * will prevent subsequent notifications from being processed and new PublishRequests from
         * being sent.
         *
         * @param item the {@link OpcUaMonitoredItem} for which a new event has arrived.
         * @param eventValues the new event field values.
         */
        void onEventValueReceived(OpcUaMonitoredItem item, Variant[] eventValues);

    }

    private static class ModificationDiff {

        private @Nullable UInteger clientHandle;
        private @Nullable Double requestedSamplingInterval;
        private @Nullable ExtensionObject filter;
        private @Nullable UInteger requestedQueueSize;
        private @Nullable Boolean discardOldest;

    }

    enum State {
        INITIAL,

        SYNCHRONIZED,

        UNSYNCHRONIZED
    }

    public static OpcUaMonitoredItem newDataItem(OpcUaSubscription subscription, NodeId nodeId) {
        return null; // TODO
    }

    public static OpcUaMonitoredItem newDataItem(OpcUaSubscription subscription, NodeId nodeId, double samplingInterval) {
        return null; // TODO
    }

    public static OpcUaMonitoredItem newDataItem(OpcUaSubscription subscription, ReadValueId readValueId) {
        return null; // TODO
    }

    public static OpcUaMonitoredItem newDataItem(OpcUaSubscription subscription, ReadValueId readValueId, double samplingInterval) {
        return null; // TODO
    }

    public static OpcUaMonitoredItem newEventItem(OpcUaSubscription subscription, NodeId nodeId) {
        return null; // TODO
    }

    public static OpcUaMonitoredItem newEventItem(OpcUaSubscription subscription, NodeId nodeId, EventFilter eventFilter) {
        return null; // TODO
    }

}

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
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.CreateMonitoredItemBatch;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.DeleteMonitoredItemBatch;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.ModifyMonitoredItemBatch;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.SetMonitoringModeBatch;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.supplyAsyncCompose;

public class OpcUaMonitoredItem {


    private State state = State.INITIAL;
    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicReference<ModificationDiff> diffRef = new AtomicReference<>(null);

    private @Nullable DataValueListener dataValueListener;
    private @Nullable EventValueListener eventValueListener;

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

    public void create() throws UaException {
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

                CreateMonitoredItemsResponse response =
                    subscription.getClient().createMonitoredItems(
                        subscription.getSubscriptionId().orElseThrow(),
                        TimestampsToReturn.Both,
                        List.of(request)
                    );

                MonitoredItemCreateResult result = requireNonNull(response.getResults())[0];

                StatusCode statusCode = result.getStatusCode();

                if (statusCode.isGood()) {
                    state = State.SYNCHRONIZED;

                    this.monitoredItemId = result.getMonitoredItemId();
                    this.filterResult = result.getFilterResult();
                    this.revisedQueueSize = result.getRevisedQueueSize();
                    this.revisedSamplingInterval = result.getRevisedSamplingInterval();
                } else {
                    state = State.INITIAL;

                    throw new UaException(statusCode);
                }
            } else {
                throw new UaException(StatusCodes.Bad_InvalidState);
            }
        } finally {
            lock.unlock();
        }
    }

    public CompletableFuture<Unit> createAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                create();
                return Unit.VALUE;
            } catch (UaException e) {
                throw new RuntimeException(e);
            }
        }, subscription.getClient().getTransport().getConfig().getExecutor());
    }

    public void create(CreateMonitoredItemBatch batch) {
        // TODO
    }

    public void modify() throws UaException {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                throw new UaException(StatusCodes.Bad_InvalidState);
            } else {
                ModificationDiff diff = diffRef.getAndSet(null);

                if (diff != null) {
                    if (diff.clientHandle != null) {
                        clientHandle = diff.clientHandle;
                    }
                    if (diff.requestedSamplingInterval != null) {
                        requestedSamplingInterval = diff.requestedSamplingInterval;
                    }
                    if (diff.filter != null) {
                        filter = diff.filter;
                    }
                    if (diff.requestedQueueSize != null) {
                        requestedQueueSize = diff.requestedQueueSize;
                    }
                    if (diff.discardOldest != null) {
                        discardOldest = diff.discardOldest;
                    }
                }

                var request = new MonitoredItemModifyRequest(
                    monitoredItemId,
                    new MonitoringParameters(
                        clientHandle,
                        requestedSamplingInterval,
                        filter,
                        requestedQueueSize,
                        discardOldest
                    )
                );

                ModifyMonitoredItemsResponse response = subscription.getClient().modifyMonitoredItems(
                    subscription.getSubscriptionId().orElseThrow(),
                    TimestampsToReturn.Both,
                    List.of(request)
                );

                MonitoredItemModifyResult result = requireNonNull(response.getResults())[0];

                StatusCode statusCode = result.getStatusCode();

                if (statusCode.isGood()) {
                    state = State.SYNCHRONIZED;

                    this.filterResult = result.getFilterResult();
                    this.revisedQueueSize = result.getRevisedQueueSize();
                    this.revisedSamplingInterval = result.getRevisedSamplingInterval();
                } else {
                    if (statusCode.getValue() == StatusCodes.Bad_MonitoredItemIdInvalid) {
                        state = State.INITIAL;
                    } else {
                        state = State.UNSYNCHRONIZED;
                    }

                    throw new UaException(statusCode);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public CompletableFuture<Unit> modifyAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                modify();
                return Unit.VALUE;
            } catch (UaException e) {
                throw new RuntimeException(e);
            }
        }, subscription.getClient().getTransport().getConfig().getExecutor());
    }

    public void modify(ModifyMonitoredItemBatch batch) {
        // TODO
    }

    public void delete() throws UaException {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                throw new UaException(StatusCodes.Bad_InvalidState);
            } else {
                if (monitoredItemId != null) {
                    DeleteMonitoredItemsResponse response =
                        subscription.getClient().deleteMonitoredItems(
                            subscription.getSubscriptionId().orElseThrow(),
                            List.of(monitoredItemId)
                        );

                    StatusCode result = requireNonNull(response.getResults())[0];

                    if (result.isGood()) {
                        state = State.INITIAL;
                    } else {
                        if (result.getValue() == StatusCodes.Bad_MonitoredItemIdInvalid) {
                            state = State.INITIAL;
                        }

                        throw new UaException(result);
                    }
                } else {
                    throw new UaException(StatusCodes.Bad_InvalidState);
                }
            }
        } finally {
            lock.unlock();
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
        }, subscription.getClient().getTransport().getConfig().getExecutor());
    }

    public void delete(DeleteMonitoredItemBatch batch) {
        // TODO
    }

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

    public void setFilter(ExtensionObject filter) {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                this.filter = filter;
            } else {
                ModificationDiff diff = diffRef.updateAndGet(
                    d ->
                        Objects.requireNonNullElseGet(d, ModificationDiff::new)
                );

                diff.filter = filter;

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

    public Double getRequestedSamplingInterval() {
        return requestedSamplingInterval;
    }

    public UInteger getRequestedQueueSize() {
        return requestedQueueSize;
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

    public void setDataValueListener(DataValueListener listener) {
        lock.lock();
        try {
            this.dataValueListener = listener;
        } finally {
            lock.unlock();
        }
    }

    public void setEventValueListener(EventValueListener listener) {
        lock.lock();
        try {
            this.eventValueListener = listener;
        } finally {
            lock.unlock();
        }
    }

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
        var readValueId = new ReadValueId(
            nodeId,
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        return newDataItem(subscription, readValueId);
    }

    public static OpcUaMonitoredItem newDataItem(OpcUaSubscription subscription, NodeId nodeId, double samplingInterval) {
        var readValueId = new ReadValueId(
            nodeId,
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        return newDataItem(subscription, readValueId, samplingInterval);
    }

    public static OpcUaMonitoredItem newDataItem(OpcUaSubscription subscription, ReadValueId readValueId) {
        return new OpcUaMonitoredItem(subscription, readValueId);
    }

    public static OpcUaMonitoredItem newDataItem(OpcUaSubscription subscription, ReadValueId readValueId, double samplingInterval) {
        var item = newDataItem(subscription, readValueId);
        item.setSamplingInterval(samplingInterval);

        return item;
    }

    public static OpcUaMonitoredItem newEventItem(OpcUaSubscription subscription, NodeId nodeId) {
        var readValueId = new ReadValueId(
            nodeId,
            AttributeId.EventNotifier.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        return new OpcUaMonitoredItem(subscription, readValueId);
    }

    public static OpcUaMonitoredItem newEventItem(
        OpcUaSubscription subscription,
        NodeId nodeId,
        EventFilter eventFilter
    ) {

        var readValueId = new ReadValueId(
            nodeId,
            AttributeId.EventNotifier.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        EncodingContext ctx = subscription.getClient().getStaticEncodingContext();
        ExtensionObject filter = ExtensionObject.encode(ctx, eventFilter);

        var item = new OpcUaMonitoredItem(subscription, readValueId);
        item.setSamplingInterval(0.0);
        item.setFilter(filter);

        return item;
    }

}

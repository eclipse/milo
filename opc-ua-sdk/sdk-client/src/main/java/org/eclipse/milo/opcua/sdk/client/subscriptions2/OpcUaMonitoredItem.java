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

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.stack.core.AttributeId;
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
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.jetbrains.annotations.Nullable;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaMonitoredItem {


    private State state = State.INITIAL;
    private final ReentrantLock lock = new ReentrantLock();
    private ModificationDiff pendingModification;

    private @Nullable DataValueListener dataValueListener;
    private @Nullable EventValueListener eventValueListener;

    // MonitoredItem parameters that a user might modify via SetMonitoringMode:
    private MonitoringMode monitoringMode = MonitoringMode.Reporting;

    // MonitoredItem parameters that a user might modify via ModifyMonitoredItem:
    private @Nullable UInteger clientHandle;
    private Double requestedSamplingInterval = 1000.0;
    private @Nullable MonitoringFilter filter;
    private UInteger requestedQueueSize = uint(1);
    private boolean discardOldest = true;

    // MonitoredItem parameters that are returned from the server:
    private @Nullable UInteger monitoredItemId;
    private @Nullable Double revisedSamplingInterval;
    private @Nullable UInteger revisedQueueSize;
    private @Nullable ExtensionObject filterResult;

    private @Nullable OpcUaSubscription subscription = null;

    private @Nullable StatusCode lastOperationResult;

    private final ReadValueId readValueId;

    public OpcUaMonitoredItem(ReadValueId readValueId) {
        this.readValueId = readValueId;
    }

//    public void create() throws UaException {
//        lock.lock();
//        try {
//            if (state == State.INITIAL) {
//                if (clientHandle == null) {
//                    clientHandle = subscription.nextClientHandle();
//                }
//
//                var request = new MonitoredItemCreateRequest(
//                    readValueId,
//                    monitoringMode,
//                    new MonitoringParameters(
//                        clientHandle,
//                        requestedSamplingInterval,
//                        filter,
//                        requestedQueueSize,
//                        discardOldest
//                    )
//                );
//
//                CreateMonitoredItemsResponse response =
//                    subscription.getClient().createMonitoredItems(
//                        subscription.getSubscriptionId().orElseThrow(),
//                        TimestampsToReturn.Both,
//                        List.of(request)
//                    );
//
//                MonitoredItemCreateResult result = requireNonNull(response.getResults())[0];
//
//                StatusCode statusCode = result.getStatusCode();
//
//                if (statusCode.isGood()) {
//                    state = State.SYNCHRONIZED;
//
//                    this.monitoredItemId = result.getMonitoredItemId();
//                    this.filterResult = result.getFilterResult();
//                    this.revisedQueueSize = result.getRevisedQueueSize();
//                    this.revisedSamplingInterval = result.getRevisedSamplingInterval();
//                } else {
//                    state = State.INITIAL;
//
//                    throw new UaException(statusCode);
//                }
//            } else {
//                throw new UaException(StatusCodes.Bad_InvalidState);
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public CompletionStage<Unit> createAsync() {
//        return supplyAsyncCompose(() -> {
//            try {
//                create();
//                return CompletableFuture.completedFuture(Unit.VALUE);
//            } catch (UaException e) {
//                return CompletableFuture.failedFuture(e);
//            }
//        }, subscription.getClient().getTransport().getConfig().getExecutor());
//    }
//
//    public void modify() throws UaException {
//        lock.lock();
//        try {
//            if (state == State.INITIAL) {
//                throw new UaException(StatusCodes.Bad_InvalidState);
//            } else {
//                ModificationDiff diff = pendingModification.get();
//
//                if (diff != null) {
//                    if (diff.isMonitoringModeModified()) {
//                        modifyMonitoringMode(diff);
//                    }
//
//                    if (diff.isMonitoringParameterModified()) {
//                        modifyMonitoringParameters(diff);
//                    }
//
//                    pendingModification.set(null);
//                }
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    private void modifyMonitoringMode(ModificationDiff diff) throws UaException {
//        UInteger subscriptionId = subscription.getSubscriptionId().orElse(null);
//        if (subscriptionId == null || monitoredItemId == null) {
//            throw new UaException(StatusCodes.Bad_InvalidState);
//        }
//
//        SetMonitoringModeResponse response = subscription.getClient().setMonitoringMode(
//            subscription.getSubscriptionId().orElseThrow(),
//            diff.monitoringMode,
//            List.of(monitoredItemId)
//        );
//
//        StatusCode result = requireNonNull(response.getResults())[0];
//
//        if (result.isGood()) {
//            state = State.SYNCHRONIZED;
//
//            this.monitoringMode = diff.monitoringMode;
//
//            diff.clearMonitoringMode();
//        } else {
//            if (result.getValue() == StatusCodes.Bad_MonitoredItemIdInvalid) {
//                state = State.INITIAL;
//            } else {
//                state = State.UNSYNCHRONIZED;
//            }
//
//            throw new UaException(result);
//        }
//    }
//
//    private void modifyMonitoringParameters(ModificationDiff diff) throws UaException {
//        UInteger newClientHandle = diff.clientHandle != null ?
//            diff.clientHandle : clientHandle;
//        Double newRequestedSamplingInterval = diff.requestedSamplingInterval != null ?
//            diff.requestedSamplingInterval : requestedSamplingInterval;
//        MonitoringFilter newFilter = diff.filter != null ?
//            diff.filter : filter;
//        UInteger newRequestedQueueSize = diff.requestedQueueSize != null ?
//            diff.requestedQueueSize : requestedQueueSize;
//        Boolean newDiscardOldest = diff.discardOldest != null ?
//            diff.discardOldest : discardOldest;
//
//        var request = new MonitoredItemModifyRequest(
//            monitoredItemId,
//            new MonitoringParameters(
//                newClientHandle,
//                newRequestedSamplingInterval,
//                newFilter,
//                newRequestedQueueSize,
//                newDiscardOldest
//            )
//        );
//
//        ModifyMonitoredItemsResponse response = subscription.getClient().modifyMonitoredItems(
//            subscription.getSubscriptionId().orElseThrow(),
//            TimestampsToReturn.Both,
//            List.of(request)
//        );
//
//        MonitoredItemModifyResult result = requireNonNull(response.getResults())[0];
//
//        StatusCode statusCode = result.getStatusCode();
//
//        if (statusCode.isGood()) {
//            state = State.SYNCHRONIZED;
//
//            this.clientHandle = newClientHandle;
//            this.requestedSamplingInterval = newRequestedSamplingInterval;
//            this.filter = newFilter;
//            this.requestedQueueSize = newRequestedQueueSize;
//            this.discardOldest = newDiscardOldest;
//
//            this.filterResult = result.getFilterResult();
//            this.revisedQueueSize = result.getRevisedQueueSize();
//            this.revisedSamplingInterval = result.getRevisedSamplingInterval();
//
//            diff.clearMonitoringParameters();
//        } else {
//            if (statusCode.getValue() == StatusCodes.Bad_MonitoredItemIdInvalid) {
//                state = State.INITIAL;
//            } else {
//                state = State.UNSYNCHRONIZED;
//            }
//
//            throw new UaException(statusCode);
//        }
//    }
//
//    public CompletionStage<Unit> modifyAsync() {
//        return supplyAsyncCompose(() -> {
//            try {
//                modify();
//                return CompletableFuture.completedFuture(Unit.VALUE);
//            } catch (UaException e) {
//                return CompletableFuture.failedFuture(e);
//            }
//        }, subscription.getClient().getTransport().getConfig().getExecutor());
//    }
//
//    public void delete() throws UaException {
//        lock.lock();
//        try {
//            if (state == State.INITIAL) {
//                throw new UaException(StatusCodes.Bad_InvalidState);
//            } else {
//                UInteger subscriptionId = subscription.getSubscriptionId().orElse(null);
//
//                if (subscriptionId != null && monitoredItemId != null) {
//                    DeleteMonitoredItemsResponse response =
//                        subscription.getClient().deleteMonitoredItems(subscriptionId, List.of(monitoredItemId));
//
//                    StatusCode result = requireNonNull(response.getResults())[0];
//
//                    if (result.isGood()) {
//                        state = State.INITIAL;
//                    } else {
//                        if (result.getValue() == StatusCodes.Bad_MonitoredItemIdInvalid) {
//                            state = State.INITIAL;
//                        }
//
//                        throw new UaException(result);
//                    }
//                } else {
//                    throw new UaException(StatusCodes.Bad_InvalidState);
//                }
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public CompletionStage<Unit> deleteAsync() {
//        return supplyAsyncCompose(() -> {
//            try {
//                delete();
//                return CompletableFuture.completedFuture(Unit.VALUE);
//            } catch (UaException e) {
//                return CompletableFuture.failedFuture(e);
//            }
//        }, subscription.getClient().getTransport().getConfig().getExecutor());
//    }

    public void setMonitoringMode(MonitoringMode monitoringMode) throws UaException {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                this.monitoringMode = monitoringMode;
            } else {
                if (pendingModification == null) {
                    pendingModification = new ModificationDiff();
                }

                pendingModification.monitoringMode = monitoringMode;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
    }

    public void setClientHandle(UInteger clientHandle) {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                this.clientHandle = clientHandle;
            } else {
                if (pendingModification == null) {
                    pendingModification = new ModificationDiff();
                }

                pendingModification.clientHandle = clientHandle;

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
                if (pendingModification == null) {
                    pendingModification = new ModificationDiff();
                }

                pendingModification.requestedSamplingInterval = samplingInterval;

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
                if (pendingModification == null) {
                    pendingModification = new ModificationDiff();
                }

                pendingModification.requestedQueueSize = requestedQueueSize;

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
                if (pendingModification == null) {
                    pendingModification = new ModificationDiff();
                }

                pendingModification.discardOldest = discardOldest;

                state = State.UNSYNCHRONIZED;
            }
        } finally {
            lock.unlock();
        }
    }

    public void setFilter(@Nullable MonitoringFilter filter) {
        lock.lock();
        try {
            if (state == State.INITIAL) {
                this.filter = filter;
            } else {
                if (pendingModification == null) {
                    pendingModification = new ModificationDiff();
                }

                pendingModification.filter = filter;

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

    public MonitoringMode getMonitoringMode() {
        return monitoringMode;
    }

    public Optional<StatusCode> getLastOperationResult() {
        return Optional.ofNullable(lastOperationResult);
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

    void setSubscription(OpcUaSubscription subscription) {
        lock.lock();
        try {
            this.subscription = subscription;
        } finally {
            lock.unlock();
        }
    }

    State getState() {
        return state;
    }

    void reset() {
        // TODO reset to initial state
        monitoredItemId = null;
        state = State.INITIAL;
    }

    MonitoredItemCreateRequest newCreateRequest() {
        if (subscription == null) {
            throw new IllegalStateException("subscription is null");
        }

        ExtensionObject filterXo = null;
        if (filter != null) {
            EncodingContext ctx = subscription.getClient().getStaticEncodingContext();
            filterXo = ExtensionObject.encode(ctx, filter);
        }

        return new MonitoredItemCreateRequest(
            readValueId,
            monitoringMode,
            new MonitoringParameters(
                clientHandle,
                requestedSamplingInterval,
                filterXo,
                requestedQueueSize,
                discardOldest
            )
        );
    }

    MonitoredItemModifyRequest newModifyRequest() {
        if (subscription == null) {
            throw new IllegalStateException("no subscription");
        }
        if (monitoredItemId == null) {
            throw new IllegalStateException("no monitoredItemId");
        }
        if (pendingModification == null) {
            throw new IllegalStateException("no pending modification");
        }

        UInteger newClientHandle = pendingModification.clientHandle().orElse(clientHandle);
        Double newRequestedSamplingInterval = pendingModification.requestedSamplingInterval().orElse(requestedSamplingInterval);
        MonitoringFilter newFilter = pendingModification.filter().orElse(filter);
        UInteger newRequestedQueueSize = pendingModification.requestedQueueSize().orElse(requestedQueueSize);
        Boolean newDiscardOldest = pendingModification.discardOldest().orElse(discardOldest);

        ExtensionObject newFilterXo = null;
        if (newFilter != null) {
            EncodingContext ctx = subscription.getClient().getStaticEncodingContext();
            newFilterXo = ExtensionObject.encode(ctx, newFilter);
        }

        return new MonitoredItemModifyRequest(
            monitoredItemId,
            new MonitoringParameters(
                newClientHandle,
                newRequestedSamplingInterval,
                newFilterXo,
                newRequestedQueueSize,
                newDiscardOldest
            )
        );
    }

    void applyCreateResult(MonitoredItemCreateResult result) {
        StatusCode statusCode = result.getStatusCode();

        if (statusCode.isGood()) {
            monitoredItemId = result.getMonitoredItemId();
            filterResult = result.getFilterResult();
            revisedQueueSize = result.getRevisedQueueSize();
            revisedSamplingInterval = result.getRevisedSamplingInterval();

            state = State.SYNCHRONIZED;
        } else {
            state = State.INITIAL;
        }

        this.lastOperationResult = statusCode;
    }

    void applyModifyResult(MonitoredItemModifyResult result) {
        StatusCode statusCode = result.getStatusCode();

        if (statusCode.isGood()) {
            clientHandle = pendingModification.clientHandle().orElse(clientHandle);
            requestedSamplingInterval = pendingModification.requestedSamplingInterval().orElse(requestedSamplingInterval);
            filter = pendingModification.filter().orElse(filter);
            requestedQueueSize = pendingModification.requestedQueueSize().orElse(requestedQueueSize);
            discardOldest = pendingModification.discardOldest().orElse(discardOldest);
            pendingModification = null;

            filterResult = result.getFilterResult();
            revisedQueueSize = result.getRevisedQueueSize();
            revisedSamplingInterval = result.getRevisedSamplingInterval();

            state = State.SYNCHRONIZED;
        } else {
            state = State.UNSYNCHRONIZED;
        }

        lastOperationResult = statusCode;
    }

    void applyDeleteResult(StatusCode statusCode) {
        // TODO
        clientHandle = null;
        monitoredItemId = null;
        state = State.INITIAL;
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
        private @Nullable MonitoringFilter filter;
        private @Nullable UInteger requestedQueueSize;
        private @Nullable Boolean discardOldest;
        private @Nullable MonitoringMode monitoringMode;


        Optional<UInteger> clientHandle() {
            return Optional.ofNullable(clientHandle);
        }

        Optional<Double> requestedSamplingInterval() {
            return Optional.ofNullable(requestedSamplingInterval);
        }

        Optional<MonitoringFilter> filter() {
            return Optional.ofNullable(filter);
        }

        Optional<UInteger> requestedQueueSize() {
            return Optional.ofNullable(requestedQueueSize);
        }

        Optional<Boolean> discardOldest() {
            return Optional.ofNullable(discardOldest);
        }

        Optional<MonitoringMode> monitoringMode() {
            return Optional.ofNullable(monitoringMode);
        }

        void clearMonitoringMode() {
            monitoringMode = null;
        }

        void clearMonitoringParameters() {
            clientHandle = null;
            requestedSamplingInterval = null;
            filter = null;
            requestedQueueSize = null;
            discardOldest = null;
        }

        boolean isMonitoringModeModified() {
            return monitoringMode != null;
        }

        boolean isMonitoringParameterModified() {
            return clientHandle != null ||
                requestedSamplingInterval != null ||
                filter != null ||
                requestedQueueSize != null ||
                discardOldest != null;
        }

    }

    enum State {
        INITIAL,

        SYNCHRONIZED,

        UNSYNCHRONIZED
    }

    public static OpcUaMonitoredItem newDataItem(NodeId nodeId) {
        var readValueId = new ReadValueId(
            nodeId,
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        return new OpcUaMonitoredItem(readValueId);
    }

    public static OpcUaMonitoredItem newDataItem(NodeId nodeId, double samplingInterval) {
        var readValueId = new ReadValueId(
            nodeId,
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        var item = new OpcUaMonitoredItem(readValueId);
        item.setSamplingInterval(samplingInterval);

        return item;
    }


    public static OpcUaMonitoredItem newEventItem(NodeId nodeId) {
        return newEventItem(nodeId, null);
    }

    public static OpcUaMonitoredItem newEventItem(NodeId nodeId, @Nullable EventFilter eventFilter) {
        var readValueId = new ReadValueId(
            nodeId,
            AttributeId.EventNotifier.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        var item = new OpcUaMonitoredItem(readValueId);
        item.setSamplingInterval(0.0);
        item.setFilter(eventFilter);

        return item;
    }

}

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

import java.util.EnumSet;
import java.util.Optional;

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
    private ModificationDiff pendingModification;

    private EnumSet<ModifiedParameter> modifications = EnumSet.noneOf(ModifiedParameter.class);

    private @Nullable DataValueListener dataValueListener;
    private @Nullable EventValueListener eventValueListener;

    // MonitoredItem parameters that a user might modify via SetMonitoringMode:
    private MonitoringMode monitoringMode = MonitoringMode.Reporting;

    // MonitoredItem parameters that a user might modify via ModifyMonitoredItem:
    private Double samplingInterval = 1000.0;
    private @Nullable MonitoringFilter filter;
    private UInteger queueSize = uint(1);
    private boolean discardOldest = true;

    // MonitoredItem parameters that are returned from the server:
    private @Nullable UInteger monitoredItemId;
    private @Nullable Double revisedSamplingInterval;
    private @Nullable UInteger revisedQueueSize;
    private @Nullable ExtensionObject filterResult;

    private @Nullable StatusCode lastOperationResult;
    private @Nullable StatusCode createResult;
    private @Nullable StatusCode modifyResult;
    private @Nullable StatusCode deleteResult;

    private @Nullable UInteger clientHandle;
    private @Nullable OpcUaSubscription subscription;

    private final ReadValueId readValueId;

    public OpcUaMonitoredItem(ReadValueId readValueId) {
        this.readValueId = readValueId;
    }

    public void setSamplingInterval(double samplingInterval) {
        if (state == State.INITIAL) {
            this.samplingInterval = samplingInterval;
        } else {
            if (pendingModification == null) {
                pendingModification = new ModificationDiff();
            }

            pendingModification.requestedSamplingInterval = samplingInterval;

            modifications.add(ModifiedParameter.SAMPLING_INTERVAL);

            state = State.UNSYNCHRONIZED;
        }
    }

    public void setQueueSize(UInteger queueSize) {
        if (state == State.INITIAL) {
            this.queueSize = queueSize;
        } else {
            if (pendingModification == null) {
                pendingModification = new ModificationDiff();
            }

            pendingModification.requestedQueueSize = queueSize;

            modifications.add(ModifiedParameter.QUEUE_SIZE);

            state = State.UNSYNCHRONIZED;
        }
    }

    public void setDiscardOldest(boolean discardOldest) {
        if (state == State.INITIAL) {
            this.discardOldest = discardOldest;
        } else {
            if (pendingModification == null) {
                pendingModification = new ModificationDiff();
            }

            pendingModification.discardOldest = discardOldest;

            modifications.add(ModifiedParameter.DISCARD_OLDEST);

            state = State.UNSYNCHRONIZED;
        }
    }

    public void setFilter(@Nullable MonitoringFilter filter) {
        if (state == State.INITIAL) {
            this.filter = filter;
        } else {
            if (pendingModification == null) {
                pendingModification = new ModificationDiff();
            }

            pendingModification.filter = filter;

            modifications.add(ModifiedParameter.FILTER);

            state = State.UNSYNCHRONIZED;
        }
    }

    public void setMonitoringMode(MonitoringMode monitoringMode) throws UaException {
        if (state == State.INITIAL) {
            this.monitoringMode = monitoringMode;
        } else {
            if (pendingModification == null) {
                pendingModification = new ModificationDiff();
            }

            pendingModification.monitoringMode = monitoringMode;

            modifications.add(ModifiedParameter.MONITORING_MODE);

            state = State.UNSYNCHRONIZED;
        }
    }

    /**
     * Get the Server-assigned id for this MonitoredItem.
     * <p>
     * Present only if the MonitoredItem has been created.
     *
     * @return the Server-assigned id for this MonitoredItem.
     */
    public Optional<UInteger> getMonitoredItemId() {
        return Optional.ofNullable(monitoredItemId);
    }

    /**
     * Get the most recent SamplingInterval this MonitoredItem was configured with.
     * <p>
     * It may differ from the actual SamplingInterval that the server is using, see
     * {@link #getRevisedSamplingInterval()}.
     *
     * @return the most recent SamplingInterval this MonitoredItem was configured with.
     */
    public Double getSamplingInterval() {
        return samplingInterval;
    }

    /**
     * Get the most recent QueueSize this MonitoredItem was configured with.
     * <p>
     * It may differ from the actual QueueSize that the server is using, see
     * {@link #getRevisedQueueSize()}.
     *
     * @return the most recent QueueSize this MonitoredItem was configured with.
     */
    public UInteger getQueueSize() {
        return queueSize;
    }

    /**
     * Get the revised SamplingInterval that the server is using for this MonitoredItem.
     * <p>
     * Present only if the MonitoredItem has been created or modified.
     *
     * @return the revised SamplingInterval that the server is using for this MonitoredItem.
     */
    public Optional<Double> getRevisedSamplingInterval() {
        return Optional.ofNullable(revisedSamplingInterval);
    }

    /**
     * Get the revised QueueSize that the server is using for this MonitoredItem.
     * <p>
     * Present only if the MonitoredItem has been created or modified.
     *
     * @return the revised QueueSize that the server is using for this MonitoredItem.
     */
    public Optional<UInteger> getRevisedQueueSize() {
        return Optional.ofNullable(revisedQueueSize);
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

    public void setDataValueListener(@Nullable DataValueListener listener) {
        this.dataValueListener = listener;
    }

    public void setEventValueListener(@Nullable EventValueListener listener) {
        this.eventValueListener = listener;
    }

    void setSubscription(@Nullable OpcUaSubscription subscription) {
        this.subscription = subscription;
    }

    State getState() {
        return state;
    }

    EnumSet<ModifiedParameter> getModifications() {
        return modifications;
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
                samplingInterval,
                filterXo,
                queueSize,
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

        Double newRequestedSamplingInterval = pendingModification.requestedSamplingInterval().orElse(samplingInterval);
        MonitoringFilter newFilter = pendingModification.filter().orElse(filter);
        UInteger newRequestedQueueSize = pendingModification.requestedQueueSize().orElse(queueSize);
        Boolean newDiscardOldest = pendingModification.discardOldest().orElse(discardOldest);

        ExtensionObject newFilterXo = null;
        if (newFilter != null) {
            EncodingContext ctx = subscription.getClient().getStaticEncodingContext();
            newFilterXo = ExtensionObject.encode(ctx, newFilter);
        }

        return new MonitoredItemModifyRequest(
            monitoredItemId,
            new MonitoringParameters(
                clientHandle,
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
            samplingInterval = pendingModification.requestedSamplingInterval().orElse(samplingInterval);
            filter = pendingModification.filter().orElse(filter);
            queueSize = pendingModification.requestedQueueSize().orElse(queueSize);
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
        lastOperationResult = statusCode;

        state = State.INITIAL;
    }

    /**
     * Get the Client-assigned id for this MonitoredItem.
     * <p>
     * Present only if the MonitoredItem has been added to a Subscription, see
     * {@link OpcUaSubscription#addMonitoredItem(OpcUaMonitoredItem)}.
     *
     * @return the Client-assigned id for this MonitoredItem.
     */
    Optional<UInteger> getClientHandle() {
        return Optional.ofNullable(clientHandle);
    }

    /**
     * Set the Client-assigned id for this MonitoredItem.
     *
     * @param clientHandle the Client-assigned id for this MonitoredItem.
     */
    void setClientHandle(@Nullable UInteger clientHandle) {
        this.clientHandle = clientHandle;
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

        private @Nullable Double requestedSamplingInterval;
        private @Nullable MonitoringFilter filter;
        private @Nullable UInteger requestedQueueSize;
        private @Nullable Boolean discardOldest;
        private @Nullable MonitoringMode monitoringMode;


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
            requestedSamplingInterval = null;
            filter = null;
            requestedQueueSize = null;
            discardOldest = null;
        }

        boolean isMonitoringModeModified() {
            return monitoringMode != null;
        }

        boolean isMonitoringParameterModified() {
            return requestedSamplingInterval != null ||
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

    enum ModifiedParameter {
        SAMPLING_INTERVAL,
        QUEUE_SIZE,
        DISCARD_OLDEST,
        FILTER,
        MONITORING_MODE
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

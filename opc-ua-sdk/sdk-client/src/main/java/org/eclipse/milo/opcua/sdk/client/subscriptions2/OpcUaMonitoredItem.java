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

import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.stack.core.AttributeId;
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

    private SyncState syncState = SyncState.INITIAL;
    private Modifications modifications;

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

    private @Nullable StatusCode createResult;
    private @Nullable StatusCode modifyResult;
    private @Nullable StatusCode deleteResult;
    private @Nullable StatusCode setMonitoringModeResult;

    private @Nullable UInteger clientHandle;
    private @Nullable OpcUaSubscription subscription;

    private final ReadValueId readValueId;

    public OpcUaMonitoredItem(ReadValueId readValueId) {
        this.readValueId = readValueId;
    }

    public OpcUaMonitoredItem(ReadValueId readValueId, MonitoringMode monitoringMode) {
        this.readValueId = readValueId;
        this.monitoringMode = monitoringMode;
    }

    /**
     * Set the SamplingInterval parameter for this MonitoredItem.
     * <p>
     * This change must be synchronized to the server before it takes effect.
     *
     * @param samplingInterval the new SamplingInterval parameter value.
     * @see OpcUaSubscription#synchronizeMonitoredItems()
     */
    public void setSamplingInterval(double samplingInterval) {
        this.samplingInterval = samplingInterval;

        if (modifications == null) {
            modifications = new Modifications();
        }

        modifications.samplingInterval = samplingInterval;

        syncState = SyncState.UNSYNCHRONIZED;
    }

    /**
     * Modify the QueueSize parameter for this MonitoredItem.
     * <p>
     * This change must be synchronized to the server before it takes effect.
     *
     * @param queueSize the new QueueSize parameter value.
     * @see OpcUaSubscription#synchronizeMonitoredItems()
     */
    public void setQueueSize(UInteger queueSize) {
        this.queueSize = queueSize;

        if (modifications == null) {
            modifications = new Modifications();
        }

        modifications.queueSize = queueSize;

        syncState = SyncState.UNSYNCHRONIZED;
    }

    /**
     * Modify the DiscardOldest parameter for this MonitoredItem.
     * <p>
     * This change must be synchronized to the server before it takes effect.
     *
     * @param discardOldest the new DiscardOldest parameter value.
     */
    public void setDiscardOldest(boolean discardOldest) {
        this.discardOldest = discardOldest;

        if (modifications == null) {
            modifications = new Modifications();
        }

        modifications.discardOldest = discardOldest;

        syncState = SyncState.UNSYNCHRONIZED;
    }

    /**
     * Modify the Filter parameter for this MonitoredItem.
     * <p>
     * This change must be synchronized to the server before it takes effect.
     *
     * @param filter the new Filter parameter value.
     * @see OpcUaSubscription#synchronizeMonitoredItems()
     */
    public void setFilter(@Nullable MonitoringFilter filter) {
        this.filter = filter;

        if (modifications == null) {
            modifications = new Modifications();
        }

        modifications.filter = filter;

        syncState = SyncState.UNSYNCHRONIZED;
    }

    /**
     * Get the Client-assigned id for this MonitoredItem.
     * <p>
     * Present only if the MonitoredItem has been added to a Subscription, see
     * {@link OpcUaSubscription#addMonitoredItem(OpcUaMonitoredItem)}.
     *
     * @return the Client-assigned id for this MonitoredItem.
     */
    public Optional<UInteger> getClientHandle() {
        return Optional.ofNullable(clientHandle);
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
     * Get the most recent DiscardOldest this MonitoredItem was configured with.
     *
     * @return the most recent DiscardOldest this MonitoredItem was configured with.
     */
    public boolean getDiscardOldest() {
        return discardOldest;
    }

    /**
     * Get the most recent Filter this MonitoredItem was configured with.
     *
     * @return the most recent Filter this MonitoredItem was configured with.
     */
    public @Nullable MonitoringFilter getFilter() {
        return filter;
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

    /**
     * Get the filter result structure for the most recently configured monitoring filter.
     *
     * @return the filter result structure for the most recently configured monitoring filter.
     */
    public Optional<ExtensionObject> getFilterResult() {
        return Optional.ofNullable(filterResult);
    }

    /**
     * Get the current MonitoringMode for this MonitoredItem.
     *
     * @return the current MonitoringMode for this MonitoredItem.
     */
    public MonitoringMode getMonitoringMode() {
        return monitoringMode;
    }

    /**
     * Get the result of the most recent CreateMonitoredItems service call, if one has been made.
     *
     * @return the result of the most recent CreateMonitoredItems service call, if one has been
     *     made.
     */
    public Optional<StatusCode> getCreateResult() {
        return Optional.ofNullable(createResult);
    }

    /**
     * Get the result of the most recent ModifyMonitoredItems service call, if one has been made.
     *
     * @return the result of the most recent ModifyMonitoredItems service call, if one has been
     *     made.
     */
    public Optional<StatusCode> getModifyResult() {
        return Optional.ofNullable(modifyResult);
    }

    /**
     * Get the result of the most recent DeleteMonitoredItems service call, if one has been made.
     *
     * @return the result of the most recent DeleteMonitoredItems service call, if one has been
     *     made.
     */
    public Optional<StatusCode> getDeleteResult() {
        return Optional.ofNullable(deleteResult);
    }

    /**
     * Get the result of the most recent SetMonitoringMode service call, if one has been made.
     *
     * @return the result of the most recent SetMonitoringMode service call, if one has been made.
     */
    public Optional<StatusCode> getSetMonitoringModeResult() {
        return Optional.ofNullable(setMonitoringModeResult);
    }

    /**
     * Set the {@link DataValueListener} for this MonitoredItem.
     *
     * @param listener the {@link DataValueListener} for this MonitoredItem.
     */
    public void setDataValueListener(@Nullable DataValueListener listener) {
        this.dataValueListener = listener;
    }

    /**
     * Set the {@link EventValueListener} for this MonitoredItem.
     *
     * @param listener the {@link EventValueListener} for this MonitoredItem.
     */
    public void setEventValueListener(@Nullable EventValueListener listener) {
        this.eventValueListener = listener;
    }

    /**
     * Set the Subscription that this MonitoredItem belongs to.
     *
     * @param subscription the Subscription that this MonitoredItem belongs to.
     */
    void setSubscription(@Nullable OpcUaSubscription subscription) {
        this.subscription = subscription;
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
     * Set the current MonitoringMode for this MonitoredItem.
     *
     * @param monitoringMode the current MonitoringMode for this MonitoredItem.
     */
    void setMonitoringMode(MonitoringMode monitoringMode) {
        this.monitoringMode = monitoringMode;
    }

    SyncState getSyncState() {
        return syncState;
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
        if (modifications == null) {
            throw new IllegalStateException("no pending modification");
        }

        Double newRequestedSamplingInterval = modifications.samplingInterval().orElse(samplingInterval);
        MonitoringFilter newFilter = modifications.filter().orElse(filter);
        UInteger newRequestedQueueSize = modifications.queueSize().orElse(queueSize);
        Boolean newDiscardOldest = modifications.discardOldest().orElse(discardOldest);

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

            syncState = SyncState.SYNCHRONIZED;
        } else {
            syncState = SyncState.INITIAL;
        }

        this.createResult = statusCode;
    }

    void applyModifyResult(MonitoredItemModifyResult result) {
        StatusCode statusCode = result.getStatusCode();

        if (statusCode.isGood()) {
            samplingInterval = modifications.samplingInterval().orElse(samplingInterval);
            filter = modifications.filter().orElse(filter);
            queueSize = modifications.queueSize().orElse(queueSize);
            discardOldest = modifications.discardOldest().orElse(discardOldest);
            modifications = null;

            filterResult = result.getFilterResult();
            revisedQueueSize = result.getRevisedQueueSize();
            revisedSamplingInterval = result.getRevisedSamplingInterval();

            syncState = SyncState.SYNCHRONIZED;
        } else {
            syncState = SyncState.UNSYNCHRONIZED;
        }

        this.modifyResult = statusCode;
    }

    void applyDeleteResult(StatusCode statusCode) {
        // TODO
        clientHandle = null;
        monitoredItemId = null;
        deleteResult = statusCode;

        syncState = SyncState.INITIAL;
    }

    void applySetMonitoringModeResult(StatusCode statusCode) {
        // TODO
        this.setMonitoringModeResult = statusCode;
    }

    void notifyDataValueReceived(DataValue value) {
        if (dataValueListener != null) {
            dataValueListener.onDataReceived(this, value);
        }
    }

    void notifyEventValuesReceived(Variant[] eventValues) {
        if (eventValueListener != null) {
            eventValueListener.onEventReceived(this, eventValues);
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
        void onDataReceived(OpcUaMonitoredItem item, DataValue value);

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
        void onEventReceived(OpcUaMonitoredItem item, Variant[] eventValues);

    }

    static class Modifications {

        private @Nullable Double samplingInterval;
        private @Nullable MonitoringFilter filter;
        private @Nullable UInteger queueSize;
        private @Nullable Boolean discardOldest;


        Optional<Double> samplingInterval() {
            return Optional.ofNullable(samplingInterval);
        }

        Optional<MonitoringFilter> filter() {
            return Optional.ofNullable(filter);
        }

        Optional<UInteger> queueSize() {
            return Optional.ofNullable(queueSize);
        }

        Optional<Boolean> discardOldest() {
            return Optional.ofNullable(discardOldest);
        }

    }

    /**
     * The state of the MonitoredItem as it exists on the server, after the recent successful
     * operation.
     */
    public static class ServerState {

        private final UInteger monitoredItemId;
        private final MonitoringMode monitoringMode;
        private final double samplingInterval;
        private final @Nullable MonitoringFilter filter;
        private final UInteger queueSize;
        private final boolean discardOldest;

        private ServerState(
            UInteger monitoredItemId,
            MonitoringMode monitoringMode,
            double samplingInterval,
            @Nullable MonitoringFilter filter,
            UInteger queueSize,
            boolean discardOldest
        ) {

            this.monitoredItemId = monitoredItemId;
            this.monitoringMode = monitoringMode;
            this.samplingInterval = samplingInterval;
            this.filter = filter;
            this.queueSize = queueSize;
            this.discardOldest = discardOldest;
        }

        public UInteger getMonitoredItemId() {
            return monitoredItemId;
        }

        public MonitoringMode getMonitoringMode() {
            return monitoringMode;
        }

        public double getSamplingInterval() {
            return samplingInterval;
        }

        public @Nullable MonitoringFilter getFilter() {
            return filter;
        }

        public UInteger getQueueSize() {
            return queueSize;
        }

        public boolean getDiscardOldest() {
            return discardOldest;
        }

    }

    enum SyncState {
        INITIAL,

        SYNCHRONIZED,

        UNSYNCHRONIZED
    }

    /**
     * Create a new MonitoredItem for the Value attribute of the Node identified by
     * {@code nodeId}.
     * <p>
     * This item will not exist on the server until it has been added to an
     * {@link OpcUaSubscription} and the subscription has been synchronized.
     *
     * @param nodeId the {@link NodeId} of the Node to monitor.
     * @return a new MonitoredItem for the Value attribute of the Node identified by
     *     {@code nodeId}.
     * @see OpcUaSubscription#addMonitoredItem(OpcUaMonitoredItem)
     * @see OpcUaSubscription#synchronizeMonitoredItems()
     */
    public static OpcUaMonitoredItem newDataItem(NodeId nodeId) {
        var readValueId = new ReadValueId(
            nodeId,
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        return new OpcUaMonitoredItem(readValueId);
    }

    /**
     * Create a new MonitoredItem for the Value attribute of the Node identified by
     * {@code nodeId}, with the specified {@code samplingInterval}.
     * <p>
     * This item will not exist on the server until it has been added to an
     * {@link OpcUaSubscription} and the subscription has been synchronized.
     *
     * @param nodeId the {@link NodeId} of the Node to monitor.
     * @param samplingInterval the sampling interval for the MonitoredItem.
     * @return a new MonitoredItem for the Value attribute of the Node identified by
     *     {@code nodeId}, with the specified {@code samplingInterval}.
     * @see OpcUaSubscription#addMonitoredItem(OpcUaMonitoredItem)
     * @see OpcUaSubscription#synchronizeMonitoredItems()
     */
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

    /**
     * Create a new MonitoredItem for the EventNotifier attribute of the Node identified by
     * {@code nodeId}.
     * <p>
     * This item will not exist on the server until it has been added to an
     * {@link OpcUaSubscription} and the subscription has been synchronized.
     *
     * @param nodeId the {@link NodeId} of the Node to monitor.
     * @return a new MonitoredItem for the EventNotifier attribute of the Node identified by
     *     {@code nodeId}.
     * @see OpcUaSubscription#addMonitoredItem(OpcUaMonitoredItem)
     * @see OpcUaSubscription#synchronizeMonitoredItems()
     */
    public static OpcUaMonitoredItem newEventItem(NodeId nodeId) {
        return newEventItem(nodeId, null);
    }

    /**
     * Create a new MonitoredItem for the EventNotifier attribute of the Node identified by
     * {@code nodeId}, with the specified {@code eventFilter}.
     * <p>
     * This item will not exist on the server until it has been added to an
     * {@link OpcUaSubscription} and the subscription has been synchronized.
     *
     * @param nodeId the {@link NodeId} of the Node to monitor.
     * @param eventFilter the {@link EventFilter} to use for the MonitoredItem.
     * @return a new MonitoredItem for the EventNotifier attribute of the Node identified by
     *     {@code nodeId}, with the specified {@code eventFilter}.
     * @see OpcUaSubscription#addMonitoredItem(OpcUaMonitoredItem)
     * @see OpcUaSubscription#synchronizeMonitoredItems()
     */
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

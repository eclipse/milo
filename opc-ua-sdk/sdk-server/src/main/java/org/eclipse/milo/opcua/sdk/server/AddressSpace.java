/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.items.DataItem;
import org.eclipse.milo.opcua.sdk.server.items.EventItem;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredItem;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResult;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.jetbrains.annotations.Nullable;

import static java.util.Collections.nCopies;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public interface AddressSpace {

    //region Attribute Services

    /**
     * Read one or more values from nodes belonging to this {@link AddressSpace}.
     * <p>
     * Complete the operation with {@link ReadContext#success}.
     *
     * @param context the {@link ReadContext}.
     * @param maxAge requested max age.
     * @param timestamps requested timestamp values.
     * @param readValueIds the values to read.
     * @return the {@link DataValue}s read.
     */
    List<DataValue> read(ReadContext context, Double maxAge, TimestampsToReturn timestamps, List<ReadValueId> readValueIds);

    /**
     * Write one or more values to nodes belonging to this {@link AddressSpace}.
     * <p>
     * Complete the operation with {@link WriteContext#success}.
     *
     * @param context the {@link WriteContext}.
     * @param writeValues the values to write.
     */
    List<StatusCode> write(WriteContext context, List<WriteValue> writeValues);

    /**
     * Read history values from nodes belonging to this {@link AddressSpace}.
     * <p>
     * Complete the operation with {@link HistoryReadContext#success}.
     *
     * @param context the {@link HistoryReadContext}.
     * @param timestamps requested timestamp values.
     * @param readValueIds the values to read.
     */
    default List<HistoryReadResult> historyRead(
        HistoryReadContext context,
        HistoryReadDetails readDetails,
        TimestampsToReturn timestamps,
        List<HistoryReadValueId> readValueIds
    ) {

        HistoryReadResult result = new HistoryReadResult(
            new StatusCode(StatusCodes.Bad_HistoryOperationUnsupported),
            null,
            null
        );

        return Collections.nCopies(readValueIds.size(), result);
    }

    /**
     * Update history values in nodes belonging to this {@link AddressSpace}.
     * <p>
     * Complete the operation with {@link HistoryUpdateContext#success}.
     *
     * @param context the {@link HistoryUpdateContext}.
     * @param updateDetails the values to read.
     */
    default List<HistoryUpdateResult> historyUpdate(
        HistoryUpdateContext context,
        List<HistoryUpdateDetails> updateDetails
    ) {

        HistoryUpdateResult result = new HistoryUpdateResult(
            new StatusCode(StatusCodes.Bad_HistoryOperationUnsupported),
            null,
            null
        );

        return Collections.nCopies(updateDetails.size(), result);
    }

    //endregion

    //region Method Services

    /**
     * Invoke one or more methods belonging to this {@link AddressSpace}.
     *
     * @param context the {@link CallContext}.
     * @param requests The {@link CallMethodRequest}s for the methods to invoke.
     */
    default void call(CallContext context, List<CallMethodRequest> requests) {
        CallMethodResult result = new CallMethodResult(
            new StatusCode(StatusCodes.Bad_NotImplemented),
            new StatusCode[0],
            new DiagnosticInfo[0],
            new Variant[0]
        );

        context.success(nCopies(requests.size(), result));
    }

    //endregion

    //region MonitoredItem Services

    /**
     * A {@link DataItem} is being created for a Node managed by this {@link AddressSpace}.
     * <p>
     * This is a chance to revise the requested queue size and/or sampling interval.
     * <p>
     * The sampling interval has already been revised to fit within the configured server limits and to be at least the
     * value of the Minimum Sampling Interval attribute for the Node if it was present.
     *
     * @param itemToMonitor the item that will be monitored.
     * @param requestedQueueSize the requested queue size.
     * @param requestedSamplingInterval the requested sampling interval.
     * @param revisionCallback the callback to invoke to revise the sampling interval and queue size.
     */
    default void onCreateDataItem(
        @SuppressWarnings("unused") ReadValueId itemToMonitor,
        Double requestedSamplingInterval,
        UInteger requestedQueueSize,
        BiConsumer<Double, UInteger> revisionCallback) {

        revisionCallback.accept(requestedSamplingInterval, requestedQueueSize);
    }

    /**
     * A {@link DataItem} is being modified for a Node managed by this {@link AddressSpace}.
     * <p>
     * This is a chance to revise the requested queue size and/or sampling interval.
     * <p>
     * The sampling interval has already been revised to fit within the configured server limits and to be at least the
     * value of the Minimum Sampling Interval attribute for the Node if it was present.
     *
     * @param itemToModify the item that will be modified.
     * @param requestedQueueSize the requested queue size.
     * @param requestedSamplingInterval the requested sampling interval.
     * @param revisionCallback the callback to invoke to revise the sampling interval and queue size.
     */
    default void onModifyDataItem(
        @SuppressWarnings("unused") ReadValueId itemToModify,
        Double requestedSamplingInterval,
        UInteger requestedQueueSize,
        BiConsumer<Double, UInteger> revisionCallback) {

        revisionCallback.accept(requestedSamplingInterval, requestedQueueSize);
    }

    /**
     * An {@link EventItem} is being created for a Node managed by this {@link AddressSpace}.
     * <p>
     * This is a chance to revise the requested queue size.
     *
     * @param itemToMonitor the item that will be monitored.
     * @param requestedQueueSize the requested queue size.
     * @param revisionCallback the callback to invoke to revise the queue size.
     */
    default void onCreateEventItem(
        @SuppressWarnings("unused") ReadValueId itemToMonitor,
        UInteger requestedQueueSize,
        Consumer<UInteger> revisionCallback) {

        revisionCallback.accept(requestedQueueSize);
    }

    /**
     * An {@link EventItem} is being modified for a Node managed by this {@link AddressSpace}.
     * <p>
     * This is a chance to revise the requested queue size.
     *
     * @param itemToModify the item that will be modified.
     * @param requestedQueueSize the requested queue size.
     * @param revisionCallback the callback to invoke to revise the queue size.
     */
    default void onModifyEventItem(
        @SuppressWarnings("unused") ReadValueId itemToModify,
        UInteger requestedQueueSize,
        Consumer<UInteger> revisionCallback) {

        revisionCallback.accept(requestedQueueSize);
    }

    /**
     * {@link DataItem}s have been created for nodes belonging to this {@link AddressSpace}.
     * <p>
     * If sampling is enabled for this item, it is expected that a best-effort will be made to update the item's value
     * at the sampling rate.
     *
     * @param dataItems the {@link DataItem}s that were created.
     */
    void onDataItemsCreated(List<DataItem> dataItems);

    /**
     * {@link DataItem}s have been modified for nodes belonging to this {@link AddressSpace}.
     * <p>
     * Check to see if the sampling rate has changed or if sampling has been enabled or disabled.
     *
     * @param dataItems the {@link DataItem}s that were modified.
     */
    void onDataItemsModified(List<DataItem> dataItems);

    /**
     * {@link DataItem}s have been deleted for nodes belonging to this {@link AddressSpace}.
     * <p>
     * Updates to this item should cease and any references to it should be removed.
     *
     * @param dataItems the {@link DataItem}s that were deleted.
     */
    void onDataItemsDeleted(List<DataItem> dataItems);

    /**
     * {@link EventItem}s have been created for nodes belonging to this {@link AddressSpace}.
     *
     * @param eventItems the {@link EventItem}s that were created.
     */
    default void onEventItemsCreated(List<EventItem> eventItems) {
    }

    /**
     * {@link EventItem}s have been modified for nodes belonging to this {@link AddressSpace}.
     *
     * @param eventItems the {@link EventItem}s that were modified.
     */
    default void onEventItemsModified(List<EventItem> eventItems) {
    }

    /**
     * {@link EventItem}s have been deleted for nodes belonging to this {@link AddressSpace}.
     *
     * @param eventItems the {@link EventItem}s that were deleted.
     */
    default void onEventItemsDeleted(List<EventItem> eventItems) {
    }

    /**
     * {@link MonitoredItem}s have had their {@link MonitoringMode} modified by a client.
     * <p>
     * Check if sampling is still enabled and react accordingly.
     *
     * @param monitoredItems The {@link MonitoredItem}s whose {@link MonitoringMode} was modified.
     */
    void onMonitoringModeChanged(List<MonitoredItem> monitoredItems);

    //endregion

    //region NodeManagement Services

    default void addNodes(AddNodesContext context, List<AddNodesItem> nodesToAdd) {
        AddNodesResult result = new AddNodesResult(
            new StatusCode(StatusCodes.Bad_NotSupported),
            NodeId.NULL_VALUE
        );

        context.success(Collections.nCopies(nodesToAdd.size(), result));
    }

    default void deleteNodes(DeleteNodesContext context, List<DeleteNodesItem> nodesToDelete) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.success(Collections.nCopies(nodesToDelete.size(), statusCode));
    }

    default void addReferences(AddReferencesContext context, List<AddReferencesItem> referencesToAdd) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.success(Collections.nCopies(referencesToAdd.size(), statusCode));
    }

    default void deleteReferences(DeleteReferencesContext context, List<DeleteReferencesItem> referencesToDelete) {
        StatusCode statusCode = new StatusCode(StatusCodes.Bad_NotSupported);

        context.success(Collections.nCopies(referencesToDelete.size(), statusCode));
    }

    //endregion

    //region View Services

    /**
     * Get all References for which {@code nodeId} is the source.
     * <p>
     * If a Node instance for {@code nodeId} does not exist then {@link BrowseContext#failure(StatusCode)} should be
     * invoked with {@link StatusCodes#Bad_NodeIdUnknown}.
     *
     * @param context the {@link BrowseContext}.
     * @param view the {@link ViewDescription}.
     * @param nodeId the {@link NodeId} to browse.
     */
    List<Reference> browse(BrowseContext context, ViewDescription view, NodeId nodeId) throws UaException;

    /**
     * References for which {@code nodeId} is the source are being collected from all AddressSpace instances.
     * Return any References where {@code nodeId} is the source this AddressSpace may have to contribute.
     * <p>
     * The Node identified by {@code nodeId} may be managed by another AddressSpace.
     *
     * @param context the {@link BrowseContext}.
     * @param view the {@link ViewDescription}.
     * @param nodeId the {@link NodeId} to get references fo.
     */
    List<Reference> getReferences(BrowseContext context, ViewDescription view, NodeId nodeId);

    /**
     * Register one or more {@link NodeId}s.
     *
     * @param context the {@link RegisterNodesContext}.
     * @param nodeIds the {@link NodeId}s to register.
     */
    default void registerNodes(RegisterNodesContext context, List<NodeId> nodeIds) {
        context.success(nodeIds);
    }

    /**
     * Unregister one or more previously registered {@link NodeId}s.
     *
     * @param context the {@link UnregisterNodesContext}.
     * @param nodeIds the {@link NodeId}s to unregister.
     */
    default void unregisterNodes(UnregisterNodesContext context, List<NodeId> nodeIds) {
        context.success(Collections.nCopies(nodeIds.size(), Unit.VALUE));
    }

    /**
     * Get the number of views, if any, managed by this {@link AddressSpace} implementation.
     *
     * @return the number of views, if any, managed by this {@link AddressSpace} implementation.
     */
    default UInteger getViewCount() {
        return uint(0);
    }

    //endregion

    final class ReadContext extends ServiceOperationContext<ReadValueId> {

        public ReadContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public ReadContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<ReadValueId> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class WriteContext extends ServiceOperationContext<WriteValue> {

        public WriteContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public WriteContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<WriteValue> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class HistoryReadContext extends ServiceOperationContext<HistoryReadValueId> {

        public HistoryReadContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public HistoryReadContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<HistoryReadValueId> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class HistoryUpdateContext extends ServiceOperationContext<HistoryUpdateDetails> {

        public HistoryUpdateContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public HistoryUpdateContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<HistoryUpdateDetails> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class CallContext extends AsyncServiceOperationContext<CallMethodRequest, List<CallMethodResult>> {

        public CallContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public CallContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<CallMethodRequest> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class AddNodesContext extends AsyncServiceOperationContext<AddNodesItem, List<AddNodesResult>> {

        public AddNodesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public AddNodesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<AddNodesItem> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class DeleteNodesContext extends AsyncServiceOperationContext<DeleteNodesItem, List<StatusCode>> {

        public DeleteNodesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public DeleteNodesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<DeleteNodesItem> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class AddReferencesContext extends AsyncServiceOperationContext<AddReferencesItem, List<StatusCode>> {

        public AddReferencesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public AddReferencesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<AddReferencesItem> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class DeleteReferencesContext extends AsyncServiceOperationContext<DeleteReferencesItem, List<StatusCode>> {

        public DeleteReferencesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public DeleteReferencesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<DeleteReferencesItem> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class BrowseContext extends AsyncServiceOperationContext<NodeId, List<Reference>> {

        public BrowseContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public BrowseContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<NodeId> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class RegisterNodesContext extends AsyncServiceOperationContext<NodeId, List<NodeId>> {

        public RegisterNodesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public RegisterNodesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<NodeId> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

    final class UnregisterNodesContext extends AsyncServiceOperationContext<NodeId, List<Unit>> {

        public UnregisterNodesContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public UnregisterNodesContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<NodeId> diagnosticsContext,
            @Nullable String auditEntryId,
            UInteger timeoutHint,
            ExtensionObject additionalHeader
        ) {

            super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);
        }

    }

}

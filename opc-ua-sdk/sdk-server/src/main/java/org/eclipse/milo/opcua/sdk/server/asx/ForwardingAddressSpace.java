/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.asx;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.server.items.DataItem;
import org.eclipse.milo.opcua.sdk.server.items.EventItem;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredItem;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

public abstract class ForwardingAddressSpace implements AddressSpace {

    protected abstract AddressSpace delegate();

    @Override
    public void read(
        ReadContext context,
        Double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> readValueIds
    ) {

        delegate().read(context, maxAge, timestamps, readValueIds);
    }

    @Override
    public void write(WriteContext context, List<WriteValue> writeValues) {
        delegate().write(context, writeValues);
    }

    @Override
    public void historyRead(
        HistoryReadContext context,
        HistoryReadDetails readDetails,
        TimestampsToReturn timestamps,
        List<HistoryReadValueId> readValueIds
    ) {

        delegate().historyRead(context, readDetails, timestamps, readValueIds);
    }

    @Override
    public void historyUpdate(HistoryUpdateContext context, List<HistoryUpdateDetails> updateDetails) {
        delegate().historyUpdate(context, updateDetails);
    }

    @Override
    public void call(CallContext context, List<CallMethodRequest> requests) {
        delegate().call(context, requests);
    }

    @Override
    public void onCreateDataItem(
        ReadValueId itemToMonitor,
        Double requestedSamplingInterval,
        UInteger requestedQueueSize,
        BiConsumer<Double, UInteger> revisionCallback
    ) {

        delegate().onCreateDataItem(itemToMonitor, requestedSamplingInterval, requestedQueueSize, revisionCallback);
    }

    @Override
    public void onModifyDataItem(
        ReadValueId itemToModify,
        Double requestedSamplingInterval,
        UInteger requestedQueueSize,
        BiConsumer<Double, UInteger> revisionCallback
    ) {

        delegate().onModifyDataItem(itemToModify, requestedSamplingInterval, requestedQueueSize, revisionCallback);
    }

    @Override
    public void onCreateEventItem(
        ReadValueId itemToMonitor,
        UInteger requestedQueueSize,
        Consumer<UInteger> revisionCallback
    ) {

        delegate().onCreateEventItem(itemToMonitor, requestedQueueSize, revisionCallback);
    }

    @Override
    public void onModifyEventItem(
        ReadValueId itemToModify,
        UInteger requestedQueueSize,
        Consumer<UInteger> revisionCallback
    ) {

        delegate().onModifyEventItem(itemToModify, requestedQueueSize, revisionCallback);
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        delegate().onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        delegate().onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        delegate().onDataItemsDeleted(dataItems);
    }

    @Override
    public void onEventItemsCreated(List<EventItem> eventItems) {
        delegate().onEventItemsCreated(eventItems);
    }

    @Override
    public void onEventItemsModified(List<EventItem> eventItems) {
        delegate().onEventItemsModified(eventItems);
    }

    @Override
    public void onEventItemsDeleted(List<EventItem> eventItems) {
        delegate().onEventItemsDeleted(eventItems);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        delegate().onMonitoringModeChanged(monitoredItems);
    }

    @Override
    public void addNodes(AddNodesContext context, List<AddNodesItem> nodesToAdd) {
        delegate().addNodes(context, nodesToAdd);
    }

    @Override
    public void deleteNodes(DeleteNodesContext context, List<DeleteNodesItem> nodesToDelete) {
        delegate().deleteNodes(context, nodesToDelete);
    }

    @Override
    public void addReferences(AddReferencesContext context, List<AddReferencesItem> referencesToAdd) {
        delegate().addReferences(context, referencesToAdd);
    }

    @Override
    public void deleteReferences(DeleteReferencesContext context, List<DeleteReferencesItem> referencesToDelete) {
        delegate().deleteReferences(context, referencesToDelete);
    }

    @Override
    public void browse(BrowseContext context, NodeId nodeId) {
        delegate().browse(context, nodeId);
    }

    @Override
    public void browse(BrowseContext context, ViewDescription view, NodeId nodeId) {
        delegate().browse(context, view, nodeId);
    }

    @Override
    public void getReferences(BrowseContext context, ViewDescription view, NodeId nodeId) {
        delegate().getReferences(context, view, nodeId);
    }

    @Override
    public void registerNodes(RegisterNodesContext context, List<NodeId> nodeIds) {
        delegate().registerNodes(context, nodeIds);
    }

    @Override
    public void unregisterNodes(UnregisterNodesContext context, List<NodeId> nodeIds) {
        delegate().unregisterNodes(context, nodeIds);
    }

    @Override
    public UInteger getViewCount() {
        return delegate().getViewCount();
    }

}

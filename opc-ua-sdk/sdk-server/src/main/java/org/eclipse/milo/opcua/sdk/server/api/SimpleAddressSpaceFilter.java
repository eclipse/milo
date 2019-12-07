/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.function.Predicate;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

/**
 * A simple {@link AddressSpaceFilter} that delegates each of the filter operations to a simple filter on {@link NodeId}
 * using the most reasonable NodeId from that operation's request as the filter criteria.
 */
public abstract class SimpleAddressSpaceFilter implements AddressSpaceFilter {

    /**
     * Create a new {@link SimpleAddressSpaceFilter} that uses a {@link Predicate} on {@link NodeId}.
     *
     * @param nodeIdFilter a {@link Predicate} that tests a {@link NodeId}.
     * @return a new {@link SimpleAddressSpaceFilter} that uses a {@link Predicate} on {@link NodeId}.
     */
    public static SimpleAddressSpaceFilter create(Predicate<NodeId> nodeIdFilter) {
        return create(nodeIdFilter, item -> nodeIdFilter.test(item.getReadValueId().getNodeId()));
    }

    /**
     * Create a new {@link SimpleAddressSpaceFilter} that uses a separate {@link Predicate} for {@link NodeId}s and
     * {@link MonitoredItem}s.
     *
     * @param nodeIdFilter        a {@link Predicate} that tests a {@link NodeId}.
     * @param monitoredItemFilter a {@link Predicate} that tests a {@link MonitoredItem}.
     * @return a new {@link SimpleAddressSpaceFilter} that uses a {@link Predicate} on {@link NodeId}.
     */
    public static SimpleAddressSpaceFilter create(
        Predicate<NodeId> nodeIdFilter,
        Predicate<MonitoredItem> monitoredItemFilter
    ) {

        return new SimpleAddressSpaceFilter() {
            @Override
            protected boolean filter(NodeId nodeId) {
                return nodeIdFilter.test(nodeId);
            }

            @Override
            protected boolean filter(MonitoredItem monitoredItem) {
                return monitoredItemFilter.test(monitoredItem);
            }
        };
    }

    /**
     * Return {@code true} if the operation {@code nodeId} belongs to should be handled this filter's
     * {@link AddressSpace}.
     * <p>
     * This is not an indication that a Node for {@code nodeId} exists, rather, it's an indication that this
     * AddressSpace would be responsible for the Node *if it did* exist.
     *
     * @param nodeId a {@link NodeId}.
     * @return {@code true} if the operation {@code nodeId} belongs to should be handled this filter's
     * {@link AddressSpace}.
     */
    protected abstract boolean filter(NodeId nodeId);

    /**
     * Return {@code true} if the operation {@code monitoredItem} belongs to should be handled by this filter's
     * {@link AddressSpace}.
     *
     * @param monitoredItem the {@link MonitoredItem} being operated on.
     * @return {@code true} if the operation {@code monitoredItem} belongs to should be handled by this filter's
     * {@link AddressSpace}.
     */
    protected abstract boolean filter(MonitoredItem monitoredItem);


    //region ViewServices

    @Override
    public boolean filterBrowse(OpcUaServer server, NodeId nodeId) {
        return filter(nodeId);
    }

    @Override
    public boolean filterRegisterNode(OpcUaServer server, NodeId nodeId) {
        return filter(nodeId);
    }

    @Override
    public boolean filterUnregisterNode(OpcUaServer server, NodeId nodeId) {
        return filter(nodeId);
    }

    //endregion

    //region AttributeServices

    @Override
    public boolean filterRead(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterWrite(OpcUaServer server, WriteValue writeValue) {
        return filter(writeValue.getNodeId());
    }

    @Override
    public boolean filterHistoryRead(OpcUaServer server, HistoryReadValueId historyReadValueId) {
        return filter(historyReadValueId.getNodeId());
    }

    @Override
    public boolean filterHistoryUpdate(OpcUaServer server, HistoryUpdateDetails historyUpdateDetails) {
        return filter(historyUpdateDetails.getNodeId());
    }

    //endregion

    //region MethodServices

    @Override
    public boolean filterCall(OpcUaServer server, CallMethodRequest callMethodRequest) {
        return filter(callMethodRequest.getObjectId());
    }

    //endregion

    //region MonitoredItemServices

    @Override
    public boolean filterOnBeforeDataItemCreated(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnBeforeDataItemModified(OpcUaServer server, DataItem dataItem) {
        return filter(dataItem);
    }

    @Override
    public boolean filterOnAfterDataItemsCreated(OpcUaServer server, DataItem dataItem) {
        return filter(dataItem);
    }

    @Override
    public boolean filterOnAfterDataItemsModified(OpcUaServer server, DataItem dataItem) {
        return filter(dataItem);
    }

    @Override
    public boolean filterOnAfterDataItemsDeleted(OpcUaServer server, DataItem dataItem) {
        return filter(dataItem);
    }

    @Override
    public boolean filterOnBeforeEventItemCreated(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnBeforeEventItemModified(OpcUaServer server, EventItem eventItem) {
        return filter(eventItem);
    }

    @Override
    public boolean filterOnAfterEventItemsCreated(OpcUaServer server, EventItem eventItem) {
        return filter(eventItem);
    }

    @Override
    public boolean filterOnAfterEventItemsModified(OpcUaServer server, EventItem eventItem) {
        return filter(eventItem);
    }

    @Override
    public boolean filterOnAfterEventItemsDeleted(OpcUaServer server, EventItem eventItem) {
        return filter(eventItem);
    }

    @Override
    public boolean filterOnMonitoringModeChanged(OpcUaServer server, MonitoredItem monitoredItem) {
        return filter(monitoredItem);
    }

    //endregion

    //region NodeManagementServices

    @Override
    public boolean filterAddNodes(OpcUaServer server, AddNodesItem addNodesItem) {
        NamespaceTable namespaceTable = server.getNamespaceTable();

        ExpandedNodeId requestedNewNodeId =
            addNodesItem.getRequestedNewNodeId();

        if (requestedNewNodeId.isNotNull()) {
            return requestedNewNodeId
                .local(namespaceTable)
                .map(this::filter)
                .orElse(false);
        } else {
            return addNodesItem.getParentNodeId()
                .local(namespaceTable)
                .map(this::filter)
                .orElse(false);
        }
    }

    @Override
    public boolean filterDeleteNodes(OpcUaServer server, DeleteNodesItem deleteNodesItem) {
        return filter(deleteNodesItem.getNodeId());
    }

    @Override
    public boolean filterAddReferences(OpcUaServer server, AddReferencesItem addReferencesItem) {
        return filter(addReferencesItem.getSourceNodeId());
    }

    @Override
    public boolean filterDeleteReferences(OpcUaServer server, DeleteReferencesItem deleteReferencesItem) {
        return filter(deleteReferencesItem.getSourceNodeId());
    }

    //endregion

}

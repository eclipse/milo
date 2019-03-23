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
     * @param filter a {@link Predicate} that tests a {@link NodeId}.
     * @return a new {@link SimpleAddressSpaceFilter} that uses a {@link Predicate} on {@link NodeId}.
     */
    public static SimpleAddressSpaceFilter create(Predicate<NodeId> filter) {
        return new SimpleAddressSpaceFilter() {
            @Override
            protected boolean filter(NodeId nodeId) {
                return filter.test(nodeId);
            }
        };
    }

    /**
     * Return {@code true} if the operation {@code nodeId} belongs to should be handled this {@link AddressSpace}.
     * <p>
     * This is not an indication that a Node for {@code nodeId} exists, rather, it's an indication that this
     * AddressSpace would be responsible for the Node *if it did* exist.
     *
     * @param nodeId a {@link NodeId}.
     * @return {@code true} if the operation {@code nodeId} belongs to should be handled this {@link AddressSpace}.
     */
    protected abstract boolean filter(NodeId nodeId);


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
    public boolean filterOnCreateDataItem(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnModifyDataItem(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnCreateEventItem(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnModifyEventItem(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnDataItemsCreated(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnDataItemsModified(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnDataItemsDeleted(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnEventItemsCreated(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnEventItemsModified(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnEventItemsDeleted(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
    }

    @Override
    public boolean filterOnMonitoringModeChanged(OpcUaServer server, ReadValueId readValueId) {
        return filter(readValueId.getNodeId());
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

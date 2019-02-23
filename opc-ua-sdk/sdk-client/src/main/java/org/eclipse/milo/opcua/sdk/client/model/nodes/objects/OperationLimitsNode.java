/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.OperationLimitsType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class OperationLimitsNode extends FolderNode implements OperationLimitsType {
    public OperationLimitsNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerReadNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_READ);
    }

    public CompletableFuture<UInteger> getMaxNodesPerRead() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_READ);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerRead(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_READ, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerHistoryReadDataNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA);
    }

    public CompletableFuture<UInteger> getMaxNodesPerHistoryReadData() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerHistoryReadData(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerHistoryReadEventsNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS);
    }

    public CompletableFuture<UInteger> getMaxNodesPerHistoryReadEvents() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerHistoryReadEvents(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerWriteNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_WRITE);
    }

    public CompletableFuture<UInteger> getMaxNodesPerWrite() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_WRITE);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerWrite(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_WRITE, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerHistoryUpdateDataNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);
    }

    public CompletableFuture<UInteger> getMaxNodesPerHistoryUpdateData() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerHistoryUpdateData(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerHistoryUpdateEventsNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS);
    }

    public CompletableFuture<UInteger> getMaxNodesPerHistoryUpdateEvents() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerHistoryUpdateEvents(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerMethodCallNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_METHOD_CALL);
    }

    public CompletableFuture<UInteger> getMaxNodesPerMethodCall() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerMethodCall(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerBrowseNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_BROWSE);
    }

    public CompletableFuture<UInteger> getMaxNodesPerBrowse() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_BROWSE);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerBrowse(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_BROWSE, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerRegisterNodesNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES);
    }

    public CompletableFuture<UInteger> getMaxNodesPerRegisterNodes() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerRegisterNodes(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerTranslateBrowsePathsToNodeIdsNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS);
    }

    public CompletableFuture<UInteger> getMaxNodesPerTranslateBrowsePathsToNodeIds() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS, value);
    }

    public CompletableFuture<PropertyNode> getMaxNodesPerNodeManagementNode() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT);
    }

    public CompletableFuture<UInteger> getMaxNodesPerNodeManagement() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT);
    }

    public CompletableFuture<StatusCode> setMaxNodesPerNodeManagement(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT, value);
    }

    public CompletableFuture<PropertyNode> getMaxMonitoredItemsPerCallNode() {
        return getPropertyNode(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL);
    }

    public CompletableFuture<UInteger> getMaxMonitoredItemsPerCall() {
        return getProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL);
    }

    public CompletableFuture<StatusCode> setMaxMonitoredItemsPerCall(UInteger value) {
        return setProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL, value);
    }
}

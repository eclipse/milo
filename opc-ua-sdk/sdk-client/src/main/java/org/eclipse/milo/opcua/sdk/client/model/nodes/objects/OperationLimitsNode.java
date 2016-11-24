/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.OperationLimitsType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public class OperationLimitsNode extends BaseObjectNode implements OperationLimitsType {

    public OperationLimitsNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerRead() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_READ.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerRead() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_READ);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerRead(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_READ, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerHistoryReadData() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerHistoryReadData() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerHistoryReadData(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerHistoryReadEvents() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerHistoryReadEvents() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerHistoryReadEvents(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerWrite() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_WRITE.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerWrite() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_WRITE);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerWrite(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_WRITE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerHistoryUpdateData() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerHistoryUpdateData() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerHistoryUpdateData(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerHistoryUpdateEvents() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerHistoryUpdateEvents() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerHistoryUpdateEvents(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerMethodCall() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_METHOD_CALL.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerMethodCall() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerMethodCall(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerBrowse() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_BROWSE.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerBrowse() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_BROWSE);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerBrowse(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_BROWSE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerRegisterNodes() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerRegisterNodes() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerRegisterNodes(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerTranslateBrowsePathsToNodeIds() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerTranslateBrowsePathsToNodeIds() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxNodesPerNodeManagement() {
        return getPropertyNode(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxNodesPerNodeManagement() {
        return getProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNodesPerNodeManagement(UInteger value) {
        return setProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxMonitoredItemsPerCall() {
        return getPropertyNode(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxMonitoredItemsPerCall() {
        return getProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxMonitoredItemsPerCall(UInteger value) {
        return setProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL, value);
    }


}
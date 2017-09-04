/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.OperationLimitsType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class OperationLimitsNode extends FolderNode implements OperationLimitsType {
    public OperationLimitsNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                               LocalizedText displayName, LocalizedText description, UInteger writeMask,
                               UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public OperationLimitsNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                               LocalizedText displayName, LocalizedText description, UInteger writeMask,
                               UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getMaxNodesPerReadNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_READ);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerRead() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_READ);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerRead(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_READ, value);
    }

    public PropertyNode getMaxNodesPerHistoryReadDataNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerHistoryReadData() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerHistoryReadData(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA, value);
    }

    public PropertyNode getMaxNodesPerHistoryReadEventsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerHistoryReadEvents() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerHistoryReadEvents(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS, value);
    }

    public PropertyNode getMaxNodesPerWriteNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_WRITE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerWrite() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_WRITE);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerWrite(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_WRITE, value);
    }

    public PropertyNode getMaxNodesPerHistoryUpdateDataNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerHistoryUpdateData() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerHistoryUpdateData(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA, value);
    }

    public PropertyNode getMaxNodesPerHistoryUpdateEventsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerHistoryUpdateEvents() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerHistoryUpdateEvents(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS, value);
    }

    public PropertyNode getMaxNodesPerMethodCallNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_METHOD_CALL);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerMethodCall() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerMethodCall(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL, value);
    }

    public PropertyNode getMaxNodesPerBrowseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_BROWSE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerBrowse() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_BROWSE);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerBrowse(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_BROWSE, value);
    }

    public PropertyNode getMaxNodesPerRegisterNodesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerRegisterNodes() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerRegisterNodes(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES, value);
    }

    public PropertyNode getMaxNodesPerTranslateBrowsePathsToNodeIdsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerTranslateBrowsePathsToNodeIds() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS, value);
    }

    public PropertyNode getMaxNodesPerNodeManagementNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxNodesPerNodeManagement() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT);
        return propertyValue.orElse(null);
    }

    public void setMaxNodesPerNodeManagement(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT, value);
    }

    public PropertyNode getMaxMonitoredItemsPerCallNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxMonitoredItemsPerCall() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL);
        return propertyValue.orElse(null);
    }

    public void setMaxMonitoredItemsPerCall(UInteger value) {
        setProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL, value);
    }
}

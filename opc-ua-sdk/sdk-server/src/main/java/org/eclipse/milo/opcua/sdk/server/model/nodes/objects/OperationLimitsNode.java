/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.OperationLimitsType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class OperationLimitsNode extends FolderNode implements OperationLimitsType {
    public OperationLimitsNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                               LocalizedText displayName, LocalizedText description, UInteger writeMask,
                               UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public OperationLimitsNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                               LocalizedText displayName, LocalizedText description, UInteger writeMask,
                               UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getMaxMonitoredItemsPerCallNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxMonitoredItemsPerCall() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxMonitoredItemsPerCall(UInteger value) {
        setProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL, value);
    }

    @Override
    public PropertyNode getMaxNodesPerTranslateBrowsePathsToNodeIdsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerTranslateBrowsePathsToNodeIds() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS, value);
    }

    @Override
    public PropertyNode getMaxNodesPerNodeManagementNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerNodeManagement() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerNodeManagement(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT, value);
    }

    @Override
    public PropertyNode getMaxNodesPerHistoryUpdateEventsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerHistoryUpdateEvents() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerHistoryUpdateEvents(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS, value);
    }

    @Override
    public PropertyNode getMaxNodesPerWriteNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_WRITE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerWrite() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_WRITE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerWrite(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_WRITE, value);
    }

    @Override
    public PropertyNode getMaxNodesPerReadNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_READ);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerRead() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_READ);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerRead(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_READ, value);
    }

    @Override
    public PropertyNode getMaxNodesPerHistoryReadEventsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerHistoryReadEvents() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerHistoryReadEvents(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS, value);
    }

    @Override
    public PropertyNode getMaxNodesPerHistoryUpdateDataNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerHistoryUpdateData() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerHistoryUpdateData(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA, value);
    }

    @Override
    public PropertyNode getMaxNodesPerMethodCallNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_METHOD_CALL);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerMethodCall() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerMethodCall(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL, value);
    }

    @Override
    public PropertyNode getMaxNodesPerHistoryReadDataNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerHistoryReadData() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerHistoryReadData(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA, value);
    }

    @Override
    public PropertyNode getMaxNodesPerRegisterNodesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerRegisterNodes() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerRegisterNodes(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES, value);
    }

    @Override
    public PropertyNode getMaxNodesPerBrowseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_BROWSE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxNodesPerBrowse() {
        Optional<UInteger> propertyValue = getProperty(OperationLimitsType.MAX_NODES_PER_BROWSE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxNodesPerBrowse(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_BROWSE, value);
    }
}

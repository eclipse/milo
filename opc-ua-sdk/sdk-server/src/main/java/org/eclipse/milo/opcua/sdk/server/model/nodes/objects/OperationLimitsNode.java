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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.OperationLimitsType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:OperationLimitsType")
public class OperationLimitsNode extends BaseObjectNode implements OperationLimitsType {

    public OperationLimitsNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public UInteger getMaxNodesPerRead() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_READ);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerReadNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_READ.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerRead(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_READ, value);
    }

    @Override
    public UInteger getMaxNodesPerHistoryReadData() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerHistoryReadDataNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerHistoryReadData(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_DATA, value);
    }

    @Override
    public UInteger getMaxNodesPerHistoryReadEvents() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerHistoryReadEventsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerHistoryReadEvents(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_READ_EVENTS, value);
    }

    @Override
    public UInteger getMaxNodesPerWrite() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_WRITE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerWriteNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_WRITE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerWrite(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_WRITE, value);
    }

    @Override
    public UInteger getMaxNodesPerHistoryUpdateData() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerHistoryUpdateDataNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerHistoryUpdateData(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA, value);
    }

    @Override
    public UInteger getMaxNodesPerHistoryUpdateEvents() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerHistoryUpdateEventsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerHistoryUpdateEvents(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_EVENTS, value);
    }

    @Override
    public UInteger getMaxNodesPerMethodCall() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerMethodCallNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_METHOD_CALL.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerMethodCall(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_METHOD_CALL, value);
    }

    @Override
    public UInteger getMaxNodesPerBrowse() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_BROWSE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerBrowseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_BROWSE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerBrowse(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_BROWSE, value);
    }

    @Override
    public UInteger getMaxNodesPerRegisterNodes() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerRegisterNodesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerRegisterNodes(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_REGISTER_NODES, value);
    }

    @Override
    public UInteger getMaxNodesPerTranslateBrowsePathsToNodeIds() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerTranslateBrowsePathsToNodeIdsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS, value);
    }

    @Override
    public UInteger getMaxNodesPerNodeManagement() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxNodesPerNodeManagementNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxNodesPerNodeManagement(UInteger value) {
        setProperty(OperationLimitsType.MAX_NODES_PER_NODE_MANAGEMENT, value);
    }

    @Override
    public UInteger getMaxMonitoredItemsPerCall() {
        Optional<UInteger> property = getProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxMonitoredItemsPerCallNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxMonitoredItemsPerCall(UInteger value) {
        setProperty(OperationLimitsType.MAX_MONITORED_ITEMS_PER_CALL, value);
    }

}

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
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.HistoryServerCapabilitiesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class HistoryServerCapabilitiesNode extends BaseObjectNode implements HistoryServerCapabilitiesType {
    public HistoryServerCapabilitiesNode(ServerNodeMap nodeMap, NodeId nodeId,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public HistoryServerCapabilitiesNode(ServerNodeMap nodeMap, NodeId nodeId,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getAccessHistoryDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getAccessHistoryDataCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setAccessHistoryDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY, value);
    }

    public PropertyNode getAccessHistoryEventsCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getAccessHistoryEventsCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setAccessHistoryEventsCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY, value);
    }

    public PropertyNode getMaxReturnDataValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxReturnDataValues() {
        Optional<UInteger> propertyValue = getProperty(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES);
        return propertyValue.orElse(null);
    }

    public void setMaxReturnDataValues(UInteger value) {
        setProperty(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES, value);
    }

    public PropertyNode getMaxReturnEventValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxReturnEventValues() {
        Optional<UInteger> propertyValue = getProperty(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES);
        return propertyValue.orElse(null);
    }

    public void setMaxReturnEventValues(UInteger value) {
        setProperty(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES, value);
    }

    public PropertyNode getInsertDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getInsertDataCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setInsertDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY, value);
    }

    public PropertyNode getReplaceDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getReplaceDataCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setReplaceDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY, value);
    }

    public PropertyNode getUpdateDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getUpdateDataCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setUpdateDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY, value);
    }

    public PropertyNode getDeleteRawCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getDeleteRawCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setDeleteRawCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY, value);
    }

    public PropertyNode getDeleteAtTimeCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getDeleteAtTimeCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setDeleteAtTimeCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY, value);
    }

    public PropertyNode getInsertEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getInsertEventCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setInsertEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY, value);
    }

    public PropertyNode getReplaceEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getReplaceEventCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setReplaceEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY, value);
    }

    public PropertyNode getUpdateEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getUpdateEventCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setUpdateEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY, value);
    }

    public PropertyNode getDeleteEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getDeleteEventCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setDeleteEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY, value);
    }

    public PropertyNode getInsertAnnotationCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getInsertAnnotationCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY);
        return propertyValue.orElse(null);
    }

    public void setInsertAnnotationCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY, value);
    }

    public FolderNode getAggregateFunctionsNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions");
        return component.map(node -> (FolderNode) node).orElse(null);
    }
}

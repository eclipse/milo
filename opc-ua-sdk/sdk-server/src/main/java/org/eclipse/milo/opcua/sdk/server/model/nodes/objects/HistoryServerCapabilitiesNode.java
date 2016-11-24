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
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.HistoryServerCapabilitiesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:HistoryServerCapabilitiesType")
public class HistoryServerCapabilitiesNode extends BaseObjectNode implements HistoryServerCapabilitiesType {

    public HistoryServerCapabilitiesNode(
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
    public Boolean getAccessHistoryDataCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getAccessHistoryDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setAccessHistoryDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY, value);
    }

    @Override
    public Boolean getAccessHistoryEventsCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getAccessHistoryEventsCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setAccessHistoryEventsCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY, value);
    }

    @Override
    public UInteger getMaxReturnDataValues() {
        Optional<UInteger> property = getProperty(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxReturnDataValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxReturnDataValues(UInteger value) {
        setProperty(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES, value);
    }

    @Override
    public UInteger getMaxReturnEventValues() {
        Optional<UInteger> property = getProperty(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxReturnEventValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxReturnEventValues(UInteger value) {
        setProperty(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES, value);
    }

    @Override
    public Boolean getInsertDataCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInsertDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInsertDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY, value);
    }

    @Override
    public Boolean getReplaceDataCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getReplaceDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setReplaceDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY, value);
    }

    @Override
    public Boolean getUpdateDataCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getUpdateDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setUpdateDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY, value);
    }

    @Override
    public Boolean getDeleteRawCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getDeleteRawCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setDeleteRawCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY, value);
    }

    @Override
    public Boolean getDeleteAtTimeCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getDeleteAtTimeCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setDeleteAtTimeCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY, value);
    }

    @Override
    public Boolean getInsertEventCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInsertEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInsertEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY, value);
    }

    @Override
    public Boolean getReplaceEventCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getReplaceEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setReplaceEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY, value);
    }

    @Override
    public Boolean getUpdateEventCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getUpdateEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setUpdateEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY, value);
    }

    @Override
    public Boolean getDeleteEventCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getDeleteEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setDeleteEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY, value);
    }

    @Override
    public Boolean getInsertAnnotationCapability() {
        Optional<Boolean> property = getProperty(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInsertAnnotationCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInsertAnnotationCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY, value);
    }

    @Override
    public FolderNode getAggregateFunctionsNode() {
        Optional<ObjectNode> component = getObjectComponent("AggregateFunctions");

        return component.map(node -> (FolderNode) node).orElse(null);
    }

}

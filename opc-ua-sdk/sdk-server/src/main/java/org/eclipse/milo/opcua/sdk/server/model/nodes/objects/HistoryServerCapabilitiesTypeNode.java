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

import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.HistoryServerCapabilitiesType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class HistoryServerCapabilitiesTypeNode extends BaseObjectTypeNode implements HistoryServerCapabilitiesType {
    public HistoryServerCapabilitiesTypeNode(UaNodeContext context, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public HistoryServerCapabilitiesTypeNode(UaNodeContext context, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyTypeNode getAccessHistoryDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getAccessHistoryDataCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setAccessHistoryDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getAccessHistoryEventsCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getAccessHistoryEventsCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setAccessHistoryEventsCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getMaxReturnDataValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxReturnDataValues() {
        Optional<UInteger> propertyValue = getProperty(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxReturnDataValues(UInteger value) {
        setProperty(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES, value);
    }

    @Override
    public PropertyTypeNode getMaxReturnEventValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxReturnEventValues() {
        Optional<UInteger> propertyValue = getProperty(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxReturnEventValues(UInteger value) {
        setProperty(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES, value);
    }

    @Override
    public PropertyTypeNode getInsertDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getInsertDataCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInsertDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getReplaceDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getReplaceDataCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setReplaceDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getUpdateDataCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getUpdateDataCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setUpdateDataCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getDeleteRawCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getDeleteRawCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setDeleteRawCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getDeleteAtTimeCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getDeleteAtTimeCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setDeleteAtTimeCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getInsertEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getInsertEventCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInsertEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getReplaceEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getReplaceEventCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setReplaceEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getUpdateEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getUpdateEventCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setUpdateEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getDeleteEventCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getDeleteEventCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setDeleteEventCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY, value);
    }

    @Override
    public PropertyTypeNode getInsertAnnotationCapabilityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getInsertAnnotationCapability() {
        Optional<Boolean> propertyValue = getProperty(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInsertAnnotationCapability(Boolean value) {
        setProperty(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY, value);
    }

    @Override
    public FolderTypeNode getAggregateFunctionsNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions");
        return (FolderTypeNode) component.orElse(null);
    }
}

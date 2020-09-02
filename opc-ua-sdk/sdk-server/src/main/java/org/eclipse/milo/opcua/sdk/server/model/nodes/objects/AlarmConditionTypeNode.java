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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AlarmConditionType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AlarmConditionTypeNode extends AcknowledgeableConditionTypeNode implements AlarmConditionType {
    public AlarmConditionTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                  LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                  UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AlarmConditionTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                  LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                  UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyTypeNode getInputNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.INPUT_NODE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public NodeId getInputNode() {
        Optional<NodeId> propertyValue = getProperty(AlarmConditionType.INPUT_NODE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInputNode(NodeId value) {
        setProperty(AlarmConditionType.INPUT_NODE, value);
    }

    @Override
    public PropertyTypeNode getSuppressedOrShelvedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.SUPPRESSED_OR_SHELVED);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getSuppressedOrShelved() {
        Optional<Boolean> propertyValue = getProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED);
        return propertyValue.orElse(null);
    }

    @Override
    public void setSuppressedOrShelved(Boolean value) {
        setProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED, value);
    }

    @Override
    public PropertyTypeNode getMaxTimeShelvedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.MAX_TIME_SHELVED);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Double getMaxTimeShelved() {
        Optional<Double> propertyValue = getProperty(AlarmConditionType.MAX_TIME_SHELVED);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxTimeShelved(Double value) {
        setProperty(AlarmConditionType.MAX_TIME_SHELVED, value);
    }

    @Override
    public TwoStateVariableTypeNode getEnabledStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EnabledState");
        return (TwoStateVariableTypeNode) component.orElse(null);
    }

    @Override
    public LocalizedText getEnabledState() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EnabledState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setEnabledState(LocalizedText value) {
        getVariableComponent("http://opcfoundation.org/UA/", "EnabledState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableTypeNode getActiveStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ActiveState");
        return (TwoStateVariableTypeNode) component.orElse(null);
    }

    @Override
    public LocalizedText getActiveState() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ActiveState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setActiveState(LocalizedText value) {
        getVariableComponent("http://opcfoundation.org/UA/", "ActiveState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableTypeNode getSuppressedStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SuppressedState");
        return (TwoStateVariableTypeNode) component.orElse(null);
    }

    @Override
    public LocalizedText getSuppressedState() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SuppressedState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSuppressedState(LocalizedText value) {
        getVariableComponent("http://opcfoundation.org/UA/", "SuppressedState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ShelvedStateMachineTypeNode getShelvingStateNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ShelvingState");
        return (ShelvedStateMachineTypeNode) component.orElse(null);
    }
}

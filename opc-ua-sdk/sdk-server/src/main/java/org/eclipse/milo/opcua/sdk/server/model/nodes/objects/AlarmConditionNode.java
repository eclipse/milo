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

import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AlarmConditionType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AlarmConditionNode extends AcknowledgeableConditionNode implements AlarmConditionType {
    public AlarmConditionNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                              LocalizedText displayName, LocalizedText description, UInteger writeMask,
                              UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AlarmConditionNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                              LocalizedText displayName, LocalizedText description, UInteger writeMask,
                              UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getInputNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.INPUT_NODE);
        return (PropertyNode) propertyNode.orElse(null);
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
    public PropertyNode getSuppressedOrShelvedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.SUPPRESSED_OR_SHELVED);
        return (PropertyNode) propertyNode.orElse(null);
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
    public PropertyNode getMaxTimeShelvedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.MAX_TIME_SHELVED);
        return (PropertyNode) propertyNode.orElse(null);
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
    public TwoStateVariableNode getActiveStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ActiveState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getActiveState() {
        Optional<VariableNode> component = getVariableComponent("ActiveState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setActiveState(LocalizedText value) {
        getVariableComponent("ActiveState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableNode getSuppressedStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SuppressedState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getSuppressedState() {
        Optional<VariableNode> component = getVariableComponent("SuppressedState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSuppressedState(LocalizedText value) {
        getVariableComponent("SuppressedState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableNode getEnabledStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EnabledState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getEnabledState() {
        Optional<VariableNode> component = getVariableComponent("EnabledState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setEnabledState(LocalizedText value) {
        getVariableComponent("EnabledState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ShelvedStateMachineNode getShelvingStateNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ShelvingState");
        return (ShelvedStateMachineNode) component.orElse(null);
    }
}

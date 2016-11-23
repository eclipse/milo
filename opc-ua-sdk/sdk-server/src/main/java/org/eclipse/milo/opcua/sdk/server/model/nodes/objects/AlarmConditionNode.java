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

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AlarmConditionType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AlarmConditionType")
public class AlarmConditionNode extends AcknowledgeableConditionNode implements AlarmConditionType {

    public AlarmConditionNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NodeId getInputNode() {
        Optional<NodeId> property = getProperty(AlarmConditionType.INPUT_NODE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInputNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.INPUT_NODE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInputNode(NodeId value) {
        setProperty(AlarmConditionType.INPUT_NODE, value);
    }

    @Override
    public Boolean getSuppressedOrShelved() {
        Optional<Boolean> property = getProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSuppressedOrShelvedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.SUPPRESSED_OR_SHELVED.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSuppressedOrShelved(Boolean value) {
        setProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED, value);
    }

    @Override
    public Double getMaxTimeShelved() {
        Optional<Double> property = getProperty(AlarmConditionType.MAX_TIME_SHELVED);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxTimeShelvedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AlarmConditionType.MAX_TIME_SHELVED.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxTimeShelved(Double value) {
        setProperty(AlarmConditionType.MAX_TIME_SHELVED, value);
    }

    @Override
    public ShelvedStateMachineNode getShelvingStateNode() {
        Optional<ObjectNode> component = getObjectComponent("ShelvingState");

        return component.map(node -> (ShelvedStateMachineNode) node).orElse(null);
    }

    @Override
    public LocalizedText getEnabledState() {
        Optional<VariableNode> component = getVariableComponent("EnabledState");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public TwoStateVariableNode getEnabledStateNode() {
        Optional<VariableNode> component = getVariableComponent("EnabledState");

        return component.map(node -> (TwoStateVariableNode) node).orElse(null);
    }

    @Override
    public void setEnabledState(LocalizedText value) {
        getVariableComponent("EnabledState")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public LocalizedText getActiveState() {
        Optional<VariableNode> component = getVariableComponent("ActiveState");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public TwoStateVariableNode getActiveStateNode() {
        Optional<VariableNode> component = getVariableComponent("ActiveState");

        return component.map(node -> (TwoStateVariableNode) node).orElse(null);
    }

    @Override
    public void setActiveState(LocalizedText value) {
        getVariableComponent("ActiveState")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public LocalizedText getSuppressedState() {
        Optional<VariableNode> component = getVariableComponent("SuppressedState");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public TwoStateVariableNode getSuppressedStateNode() {
        Optional<VariableNode> component = getVariableComponent("SuppressedState");

        return component.map(node -> (TwoStateVariableNode) node).orElse(null);
    }

    @Override
    public void setSuppressedState(LocalizedText value) {
        getVariableComponent("SuppressedState")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}

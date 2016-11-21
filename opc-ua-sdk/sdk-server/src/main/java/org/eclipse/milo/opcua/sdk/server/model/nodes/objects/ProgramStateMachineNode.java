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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.FiniteStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.FiniteTransitionVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ProgramDiagnosticNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ProgramStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:ProgramStateMachineType")
public class ProgramStateMachineNode extends FiniteStateMachineNode implements ProgramStateMachineType {

    public ProgramStateMachineNode(
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
    public Boolean getCreatable() {
        Optional<Boolean> property = getProperty(ProgramStateMachineType.CREATABLE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getCreatableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.CREATABLE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setCreatable(Boolean value) {
        setProperty(ProgramStateMachineType.CREATABLE, value);
    }

    @Override
    public Boolean getDeletable() {
        Optional<Boolean> property = getProperty(ProgramStateMachineType.DELETABLE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getDeletableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.DELETABLE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setDeletable(Boolean value) {
        setProperty(ProgramStateMachineType.DELETABLE, value);
    }

    @Override
    public Boolean getAutoDelete() {
        Optional<Boolean> property = getProperty(ProgramStateMachineType.AUTO_DELETE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getAutoDeleteNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.AUTO_DELETE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setAutoDelete(Boolean value) {
        setProperty(ProgramStateMachineType.AUTO_DELETE, value);
    }

    @Override
    public Integer getRecycleCount() {
        Optional<Integer> property = getProperty(ProgramStateMachineType.RECYCLE_COUNT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getRecycleCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.RECYCLE_COUNT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setRecycleCount(Integer value) {
        setProperty(ProgramStateMachineType.RECYCLE_COUNT, value);
    }

    @Override
    public UInteger getInstanceCount() {
        Optional<UInteger> property = getProperty(ProgramStateMachineType.INSTANCE_COUNT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInstanceCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.INSTANCE_COUNT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInstanceCount(UInteger value) {
        setProperty(ProgramStateMachineType.INSTANCE_COUNT, value);
    }

    @Override
    public UInteger getMaxInstanceCount() {
        Optional<UInteger> property = getProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxInstanceCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.MAX_INSTANCE_COUNT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxInstanceCount(UInteger value) {
        setProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT, value);
    }

    @Override
    public UInteger getMaxRecycleCount() {
        Optional<UInteger> property = getProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxRecycleCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.MAX_RECYCLE_COUNT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxRecycleCount(UInteger value) {
        setProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT, value);
    }

    @Override
    public BaseObjectNode getFinalResultDataNode() {
        Optional<ObjectNode> component = getObjectComponent("FinalResultData");

        return component.map(node -> (BaseObjectNode) node).orElse(null);
    }

    @Override
    public StateNode getReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("Ready");

        return component.map(node -> (StateNode) node).orElse(null);
    }

    @Override
    public StateNode getRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("Running");

        return component.map(node -> (StateNode) node).orElse(null);
    }

    @Override
    public StateNode getSuspendedNode() {
        Optional<ObjectNode> component = getObjectComponent("Suspended");

        return component.map(node -> (StateNode) node).orElse(null);
    }

    @Override
    public StateNode getHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("Halted");

        return component.map(node -> (StateNode) node).orElse(null);
    }

    @Override
    public TransitionNode getHaltedToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("HaltedToReady");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getReadyToRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("ReadyToRunning");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getRunningToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("RunningToHalted");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getRunningToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("RunningToReady");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getRunningToSuspendedNode() {
        Optional<ObjectNode> component = getObjectComponent("RunningToSuspended");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getSuspendedToRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("SuspendedToRunning");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getSuspendedToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("SuspendedToHalted");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getSuspendedToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("SuspendedToReady");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getReadyToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("ReadyToHalted");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public LocalizedText getCurrentState() {
        Optional<VariableNode> component = getVariableComponent("CurrentState");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public FiniteStateVariableNode getCurrentStateNode() {
        Optional<VariableNode> component = getVariableComponent("CurrentState");

        return component.map(node -> (FiniteStateVariableNode) node).orElse(null);
    }

    @Override
    public void setCurrentState(LocalizedText value) {
        getVariableComponent("CurrentState")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public LocalizedText getLastTransition() {
        Optional<VariableNode> component = getVariableComponent("LastTransition");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public FiniteTransitionVariableNode getLastTransitionNode() {
        Optional<VariableNode> component = getVariableComponent("LastTransition");

        return component.map(node -> (FiniteTransitionVariableNode) node).orElse(null);
    }

    @Override
    public void setLastTransition(LocalizedText value) {
        getVariableComponent("LastTransition")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ProgramDiagnosticDataType getProgramDiagnostics() {
        Optional<VariableNode> component = getVariableComponent("ProgramDiagnostics");

        return component.map(node -> (ProgramDiagnosticDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public ProgramDiagnosticNode getProgramDiagnosticsNode() {
        Optional<VariableNode> component = getVariableComponent("ProgramDiagnostics");

        return component.map(node -> (ProgramDiagnosticNode) node).orElse(null);
    }

    @Override
    public void setProgramDiagnostics(ProgramDiagnosticDataType value) {
        getVariableComponent("ProgramDiagnostics")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}

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

public class ProgramStateMachineNode extends FiniteStateMachineNode implements ProgramStateMachineType {
    public ProgramStateMachineNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ProgramStateMachineNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getCreatableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.CREATABLE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getCreatable() {
        Optional<Boolean> propertyValue = getProperty(ProgramStateMachineType.CREATABLE);
        return propertyValue.orElse(null);
    }

    public void setCreatable(Boolean value) {
        setProperty(ProgramStateMachineType.CREATABLE, value);
    }

    public PropertyNode getDeletableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.DELETABLE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getDeletable() {
        Optional<Boolean> propertyValue = getProperty(ProgramStateMachineType.DELETABLE);
        return propertyValue.orElse(null);
    }

    public void setDeletable(Boolean value) {
        setProperty(ProgramStateMachineType.DELETABLE, value);
    }

    public PropertyNode getAutoDeleteNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.AUTO_DELETE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getAutoDelete() {
        Optional<Boolean> propertyValue = getProperty(ProgramStateMachineType.AUTO_DELETE);
        return propertyValue.orElse(null);
    }

    public void setAutoDelete(Boolean value) {
        setProperty(ProgramStateMachineType.AUTO_DELETE, value);
    }

    public PropertyNode getRecycleCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.RECYCLE_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Integer getRecycleCount() {
        Optional<Integer> propertyValue = getProperty(ProgramStateMachineType.RECYCLE_COUNT);
        return propertyValue.orElse(null);
    }

    public void setRecycleCount(Integer value) {
        setProperty(ProgramStateMachineType.RECYCLE_COUNT, value);
    }

    public PropertyNode getInstanceCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.INSTANCE_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getInstanceCount() {
        Optional<UInteger> propertyValue = getProperty(ProgramStateMachineType.INSTANCE_COUNT);
        return propertyValue.orElse(null);
    }

    public void setInstanceCount(UInteger value) {
        setProperty(ProgramStateMachineType.INSTANCE_COUNT, value);
    }

    public PropertyNode getMaxInstanceCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.MAX_INSTANCE_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxInstanceCount() {
        Optional<UInteger> propertyValue = getProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT);
        return propertyValue.orElse(null);
    }

    public void setMaxInstanceCount(UInteger value) {
        setProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT, value);
    }

    public PropertyNode getMaxRecycleCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.MAX_RECYCLE_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getMaxRecycleCount() {
        Optional<UInteger> propertyValue = getProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT);
        return propertyValue.orElse(null);
    }

    public void setMaxRecycleCount(UInteger value) {
        setProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT, value);
    }

    public FiniteStateVariableNode getCurrentStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentState");
        return component.map(node -> (FiniteStateVariableNode) node).orElse(null);
    }

    public LocalizedText getCurrentState() {
        Optional<VariableNode> component = getVariableComponent("CurrentState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setCurrentState(LocalizedText value) {
        getVariableComponent("CurrentState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public FiniteTransitionVariableNode getLastTransitionNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LastTransition");
        return component.map(node -> (FiniteTransitionVariableNode) node).orElse(null);
    }

    public LocalizedText getLastTransition() {
        Optional<VariableNode> component = getVariableComponent("LastTransition");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setLastTransition(LocalizedText value) {
        getVariableComponent("LastTransition").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public ProgramDiagnosticNode getProgramDiagnosticsNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ProgramDiagnostics");
        return component.map(node -> (ProgramDiagnosticNode) node).orElse(null);
    }

    public ProgramDiagnosticDataType getProgramDiagnostics() {
        Optional<VariableNode> component = getVariableComponent("ProgramDiagnostics");
        return component.map(node -> (ProgramDiagnosticDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setProgramDiagnostics(ProgramDiagnosticDataType value) {
        getVariableComponent("ProgramDiagnostics").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseObjectNode getFinalResultDataNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "FinalResultData");
        return component.map(node -> (BaseObjectNode) node).orElse(null);
    }

    public StateNode getReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Ready");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public StateNode getRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Running");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public StateNode getSuspendedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Suspended");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public StateNode getHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Halted");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public TransitionNode getHaltedToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HaltedToReady");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getReadyToRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ReadyToRunning");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getRunningToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "RunningToHalted");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getRunningToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "RunningToReady");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getRunningToSuspendedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "RunningToSuspended");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getSuspendedToRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToRunning");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getSuspendedToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToHalted");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getSuspendedToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToReady");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getReadyToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ReadyToHalted");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }
}

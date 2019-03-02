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

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.FiniteStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.FiniteTransitionVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ProgramDiagnosticNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ProgramStateMachineType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;

public class ProgramStateMachineNode extends FiniteStateMachineNode implements ProgramStateMachineType {
    public ProgramStateMachineNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ProgramStateMachineNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getAutoDeleteNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.AUTO_DELETE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getAutoDelete() {
        Optional<Boolean> propertyValue = getProperty(ProgramStateMachineType.AUTO_DELETE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setAutoDelete(Boolean value) {
        setProperty(ProgramStateMachineType.AUTO_DELETE, value);
    }

    @Override
    public PropertyNode getMaxRecycleCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.MAX_RECYCLE_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxRecycleCount() {
        Optional<UInteger> propertyValue = getProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxRecycleCount(UInteger value) {
        setProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT, value);
    }

    @Override
    public PropertyNode getInstanceCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.INSTANCE_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getInstanceCount() {
        Optional<UInteger> propertyValue = getProperty(ProgramStateMachineType.INSTANCE_COUNT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInstanceCount(UInteger value) {
        setProperty(ProgramStateMachineType.INSTANCE_COUNT, value);
    }

    @Override
    public PropertyNode getDeletableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.DELETABLE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getDeletable() {
        Optional<Boolean> propertyValue = getProperty(ProgramStateMachineType.DELETABLE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setDeletable(Boolean value) {
        setProperty(ProgramStateMachineType.DELETABLE, value);
    }

    @Override
    public PropertyNode getRecycleCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.RECYCLE_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Integer getRecycleCount() {
        Optional<Integer> propertyValue = getProperty(ProgramStateMachineType.RECYCLE_COUNT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setRecycleCount(Integer value) {
        setProperty(ProgramStateMachineType.RECYCLE_COUNT, value);
    }

    @Override
    public PropertyNode getMaxInstanceCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.MAX_INSTANCE_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxInstanceCount() {
        Optional<UInteger> propertyValue = getProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxInstanceCount(UInteger value) {
        setProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT, value);
    }

    @Override
    public PropertyNode getCreatableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramStateMachineType.CREATABLE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getCreatable() {
        Optional<Boolean> propertyValue = getProperty(ProgramStateMachineType.CREATABLE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setCreatable(Boolean value) {
        setProperty(ProgramStateMachineType.CREATABLE, value);
    }

    @Override
    public StateNode getRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Running");
        return (StateNode) component.orElse(null);
    }

    @Override
    public StateNode getSuspendedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Suspended");
        return (StateNode) component.orElse(null);
    }

    @Override
    public BaseObjectNode getFinalResultDataNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "FinalResultData");
        return (BaseObjectNode) component.orElse(null);
    }

    @Override
    public TransitionNode getHaltedToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HaltedToReady");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public StateNode getHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Halted");
        return (StateNode) component.orElse(null);
    }

    @Override
    public StateNode getReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Ready");
        return (StateNode) component.orElse(null);
    }

    @Override
    public TransitionNode getRunningToSuspendedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "RunningToSuspended");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getReadyToRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ReadyToRunning");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getRunningToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "RunningToHalted");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getRunningToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "RunningToReady");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public UaMethodNode getResetMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Reset", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public TransitionNode getSuspendedToRunningNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToRunning");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getSuspendedToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToHalted");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public FiniteStateVariableNode getCurrentStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentState");
        return (FiniteStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getCurrentState() {
        Optional<VariableNode> component = getVariableComponent("CurrentState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setCurrentState(LocalizedText value) {
        getVariableComponent("CurrentState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TransitionNode getSuspendedToReadyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToReady");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public UaMethodNode getStartMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Start", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getResumeMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Resume", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public TransitionNode getReadyToHaltedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ReadyToHalted");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public ProgramDiagnosticNode getProgramDiagnosticsNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ProgramDiagnostics");
        return (ProgramDiagnosticNode) component.orElse(null);
    }

    @Override
    public ProgramDiagnosticDataType getProgramDiagnostics() {
        Optional<VariableNode> component = getVariableComponent("ProgramDiagnostics");
        return component.map(node -> (ProgramDiagnosticDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setProgramDiagnostics(ProgramDiagnosticDataType value) {
        getVariableComponent("ProgramDiagnostics").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public FiniteTransitionVariableNode getLastTransitionNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LastTransition");
        return (FiniteTransitionVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getLastTransition() {
        Optional<VariableNode> component = getVariableComponent("LastTransition");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setLastTransition(LocalizedText value) {
        getVariableComponent("LastTransition").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UaMethodNode getSuspendMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Suspend", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getHaltMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Halt", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}

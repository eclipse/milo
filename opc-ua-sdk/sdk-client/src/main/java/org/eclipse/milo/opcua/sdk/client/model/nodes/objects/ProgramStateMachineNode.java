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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteTransitionVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ProgramDiagnosticNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgramStateMachineType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;

public class ProgramStateMachineNode extends FiniteStateMachineNode implements ProgramStateMachineType {
    public ProgramStateMachineNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getCreatableNode() {
        return getPropertyNode(ProgramStateMachineType.CREATABLE);
    }

    public CompletableFuture<Boolean> getCreatable() {
        return getProperty(ProgramStateMachineType.CREATABLE);
    }

    public CompletableFuture<StatusCode> setCreatable(Boolean value) {
        return setProperty(ProgramStateMachineType.CREATABLE, value);
    }

    public CompletableFuture<PropertyNode> getDeletableNode() {
        return getPropertyNode(ProgramStateMachineType.DELETABLE);
    }

    public CompletableFuture<Boolean> getDeletable() {
        return getProperty(ProgramStateMachineType.DELETABLE);
    }

    public CompletableFuture<StatusCode> setDeletable(Boolean value) {
        return setProperty(ProgramStateMachineType.DELETABLE, value);
    }

    public CompletableFuture<PropertyNode> getAutoDeleteNode() {
        return getPropertyNode(ProgramStateMachineType.AUTO_DELETE);
    }

    public CompletableFuture<Boolean> getAutoDelete() {
        return getProperty(ProgramStateMachineType.AUTO_DELETE);
    }

    public CompletableFuture<StatusCode> setAutoDelete(Boolean value) {
        return setProperty(ProgramStateMachineType.AUTO_DELETE, value);
    }

    public CompletableFuture<PropertyNode> getRecycleCountNode() {
        return getPropertyNode(ProgramStateMachineType.RECYCLE_COUNT);
    }

    public CompletableFuture<Integer> getRecycleCount() {
        return getProperty(ProgramStateMachineType.RECYCLE_COUNT);
    }

    public CompletableFuture<StatusCode> setRecycleCount(Integer value) {
        return setProperty(ProgramStateMachineType.RECYCLE_COUNT, value);
    }

    public CompletableFuture<PropertyNode> getInstanceCountNode() {
        return getPropertyNode(ProgramStateMachineType.INSTANCE_COUNT);
    }

    public CompletableFuture<UInteger> getInstanceCount() {
        return getProperty(ProgramStateMachineType.INSTANCE_COUNT);
    }

    public CompletableFuture<StatusCode> setInstanceCount(UInteger value) {
        return setProperty(ProgramStateMachineType.INSTANCE_COUNT, value);
    }

    public CompletableFuture<PropertyNode> getMaxInstanceCountNode() {
        return getPropertyNode(ProgramStateMachineType.MAX_INSTANCE_COUNT);
    }

    public CompletableFuture<UInteger> getMaxInstanceCount() {
        return getProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT);
    }

    public CompletableFuture<StatusCode> setMaxInstanceCount(UInteger value) {
        return setProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT, value);
    }

    public CompletableFuture<PropertyNode> getMaxRecycleCountNode() {
        return getPropertyNode(ProgramStateMachineType.MAX_RECYCLE_COUNT);
    }

    public CompletableFuture<UInteger> getMaxRecycleCount() {
        return getProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT);
    }

    public CompletableFuture<StatusCode> setMaxRecycleCount(UInteger value) {
        return setProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT, value);
    }

    public CompletableFuture<FiniteStateVariableNode> getCurrentStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentState").thenApply(FiniteStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getCurrentState() {
        return getCurrentStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setCurrentState(LocalizedText value) {
        return getCurrentStateNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<FiniteTransitionVariableNode> getLastTransitionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LastTransition").thenApply(FiniteTransitionVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getLastTransition() {
        return getLastTransitionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setLastTransition(LocalizedText value) {
        return getLastTransitionNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<ProgramDiagnosticNode> getProgramDiagnosticsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ProgramDiagnostics").thenApply(ProgramDiagnosticNode.class::cast);
    }

    public CompletableFuture<ProgramDiagnosticDataType> getProgramDiagnostics() {
        return getProgramDiagnosticsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ProgramDiagnosticDataType.class));
    }

    public CompletableFuture<StatusCode> setProgramDiagnostics(ProgramDiagnosticDataType value) {
        return getProgramDiagnosticsNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseObjectNode> getFinalResultDataNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "FinalResultData").thenApply(BaseObjectNode.class::cast);
    }

    public CompletableFuture<StateNode> getReadyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Ready").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getRunningNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Running").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getSuspendedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Suspended").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getHaltedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Halted").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getHaltedToReadyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "HaltedToReady").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getReadyToRunningNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ReadyToRunning").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getRunningToHaltedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "RunningToHalted").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getRunningToReadyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "RunningToReady").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getRunningToSuspendedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "RunningToSuspended").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getSuspendedToRunningNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToRunning").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getSuspendedToHaltedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToHalted").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getSuspendedToReadyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToReady").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getReadyToHaltedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ReadyToHalted").thenApply(TransitionNode.class::cast);
    }
}

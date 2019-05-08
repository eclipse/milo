/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteTransitionVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ProgramDiagnosticTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgramStateMachineType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;

public class ProgramStateMachineTypeNode extends FiniteStateMachineTypeNode implements ProgramStateMachineType {
    public ProgramStateMachineTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getCreatableNode() {
        return getPropertyNode(ProgramStateMachineType.CREATABLE);
    }

    public CompletableFuture<Boolean> getCreatable() {
        return getProperty(ProgramStateMachineType.CREATABLE);
    }

    public CompletableFuture<StatusCode> setCreatable(Boolean value) {
        return setProperty(ProgramStateMachineType.CREATABLE, value);
    }

    public CompletableFuture<PropertyTypeNode> getDeletableNode() {
        return getPropertyNode(ProgramStateMachineType.DELETABLE);
    }

    public CompletableFuture<Boolean> getDeletable() {
        return getProperty(ProgramStateMachineType.DELETABLE);
    }

    public CompletableFuture<StatusCode> setDeletable(Boolean value) {
        return setProperty(ProgramStateMachineType.DELETABLE, value);
    }

    public CompletableFuture<PropertyTypeNode> getAutoDeleteNode() {
        return getPropertyNode(ProgramStateMachineType.AUTO_DELETE);
    }

    public CompletableFuture<Boolean> getAutoDelete() {
        return getProperty(ProgramStateMachineType.AUTO_DELETE);
    }

    public CompletableFuture<StatusCode> setAutoDelete(Boolean value) {
        return setProperty(ProgramStateMachineType.AUTO_DELETE, value);
    }

    public CompletableFuture<PropertyTypeNode> getRecycleCountNode() {
        return getPropertyNode(ProgramStateMachineType.RECYCLE_COUNT);
    }

    public CompletableFuture<Integer> getRecycleCount() {
        return getProperty(ProgramStateMachineType.RECYCLE_COUNT);
    }

    public CompletableFuture<StatusCode> setRecycleCount(Integer value) {
        return setProperty(ProgramStateMachineType.RECYCLE_COUNT, value);
    }

    public CompletableFuture<PropertyTypeNode> getInstanceCountNode() {
        return getPropertyNode(ProgramStateMachineType.INSTANCE_COUNT);
    }

    public CompletableFuture<UInteger> getInstanceCount() {
        return getProperty(ProgramStateMachineType.INSTANCE_COUNT);
    }

    public CompletableFuture<StatusCode> setInstanceCount(UInteger value) {
        return setProperty(ProgramStateMachineType.INSTANCE_COUNT, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxInstanceCountNode() {
        return getPropertyNode(ProgramStateMachineType.MAX_INSTANCE_COUNT);
    }

    public CompletableFuture<UInteger> getMaxInstanceCount() {
        return getProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT);
    }

    public CompletableFuture<StatusCode> setMaxInstanceCount(UInteger value) {
        return setProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxRecycleCountNode() {
        return getPropertyNode(ProgramStateMachineType.MAX_RECYCLE_COUNT);
    }

    public CompletableFuture<UInteger> getMaxRecycleCount() {
        return getProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT);
    }

    public CompletableFuture<StatusCode> setMaxRecycleCount(UInteger value) {
        return setProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT, value);
    }

    @Override
    public CompletableFuture<FiniteStateVariableTypeNode> getCurrentStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentState").thenApply(FiniteStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getCurrentState() {
        return getCurrentStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentState(LocalizedText value) {
        return getCurrentStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<FiniteTransitionVariableTypeNode> getLastTransitionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LastTransition").thenApply(FiniteTransitionVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getLastTransition() {
        return getLastTransitionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setLastTransition(LocalizedText value) {
        return getLastTransitionNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ProgramDiagnosticTypeNode> getProgramDiagnosticsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ProgramDiagnostics").thenApply(ProgramDiagnosticTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ProgramDiagnosticDataType> getProgramDiagnostics() {
        return getProgramDiagnosticsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ProgramDiagnosticDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setProgramDiagnostics(ProgramDiagnosticDataType value) {
        return getProgramDiagnosticsNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseObjectTypeNode> getFinalResultDataNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "FinalResultData").thenApply(BaseObjectTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getReadyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Ready").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getRunningNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Running").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getSuspendedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Suspended").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getHaltedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Halted").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getHaltedToReadyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "HaltedToReady").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getReadyToRunningNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ReadyToRunning").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getRunningToHaltedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "RunningToHalted").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getRunningToReadyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "RunningToReady").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getRunningToSuspendedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "RunningToSuspended").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getSuspendedToRunningNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToRunning").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getSuspendedToHaltedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToHalted").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getSuspendedToReadyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "SuspendedToReady").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getReadyToHaltedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ReadyToHalted").thenApply(TransitionTypeNode.class::cast);
    }
}

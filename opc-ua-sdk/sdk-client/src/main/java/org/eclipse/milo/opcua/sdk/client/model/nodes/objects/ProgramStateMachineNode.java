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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;


public class ProgramStateMachineNode extends FiniteStateMachineNode implements ProgramStateMachineType {

    public ProgramStateMachineNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> creatable() {
        return getPropertyNode(ProgramStateMachineType.CREATABLE.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getCreatable() {
        return getProperty(ProgramStateMachineType.CREATABLE);
    }

    @Override
    public CompletableFuture<StatusCode> setCreatable(Boolean value) {
        return setProperty(ProgramStateMachineType.CREATABLE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> deletable() {
        return getPropertyNode(ProgramStateMachineType.DELETABLE.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getDeletable() {
        return getProperty(ProgramStateMachineType.DELETABLE);
    }

    @Override
    public CompletableFuture<StatusCode> setDeletable(Boolean value) {
        return setProperty(ProgramStateMachineType.DELETABLE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> autoDelete() {
        return getPropertyNode(ProgramStateMachineType.AUTO_DELETE.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getAutoDelete() {
        return getProperty(ProgramStateMachineType.AUTO_DELETE);
    }

    @Override
    public CompletableFuture<StatusCode> setAutoDelete(Boolean value) {
        return setProperty(ProgramStateMachineType.AUTO_DELETE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> recycleCount() {
        return getPropertyNode(ProgramStateMachineType.RECYCLE_COUNT.getBrowseName());
    }

    @Override
    public CompletableFuture<Integer> getRecycleCount() {
        return getProperty(ProgramStateMachineType.RECYCLE_COUNT);
    }

    @Override
    public CompletableFuture<StatusCode> setRecycleCount(Integer value) {
        return setProperty(ProgramStateMachineType.RECYCLE_COUNT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> instanceCount() {
        return getPropertyNode(ProgramStateMachineType.INSTANCE_COUNT.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getInstanceCount() {
        return getProperty(ProgramStateMachineType.INSTANCE_COUNT);
    }

    @Override
    public CompletableFuture<StatusCode> setInstanceCount(UInteger value) {
        return setProperty(ProgramStateMachineType.INSTANCE_COUNT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxInstanceCount() {
        return getPropertyNode(ProgramStateMachineType.MAX_INSTANCE_COUNT.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxInstanceCount() {
        return getProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxInstanceCount(UInteger value) {
        return setProperty(ProgramStateMachineType.MAX_INSTANCE_COUNT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxRecycleCount() {
        return getPropertyNode(ProgramStateMachineType.MAX_RECYCLE_COUNT.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxRecycleCount() {
        return getProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxRecycleCount(UInteger value) {
        return setProperty(ProgramStateMachineType.MAX_RECYCLE_COUNT, value);
    }


    @Override
    public CompletableFuture<FiniteStateVariableNode> currentState() {
        return getVariableComponent(QualifiedName.parse("0:CurrentState"))
            .thenApply(FiniteStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getCurrentState() {
        return currentState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentState(LocalizedText value) {
        return currentState()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<FiniteTransitionVariableNode> lastTransition() {
        return getVariableComponent(QualifiedName.parse("0:LastTransition"))
            .thenApply(FiniteTransitionVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getLastTransition() {
        return lastTransition()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setLastTransition(LocalizedText value) {
        return lastTransition()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ProgramDiagnosticNode> programDiagnostics() {
        return getVariableComponent(QualifiedName.parse("0:ProgramDiagnostics"))
            .thenApply(ProgramDiagnosticNode.class::cast);
    }

    public CompletableFuture<ProgramDiagnosticDataType> getProgramDiagnostics() {
        return programDiagnostics()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ProgramDiagnosticDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setProgramDiagnostics(ProgramDiagnosticDataType value) {
        return programDiagnostics()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseObjectNode> finalResultData() {
        return getObjectComponent(QualifiedName.parse("0:FinalResultData"))
            .thenApply(BaseObjectNode.class::cast);
    }

    @Override
    public CompletableFuture<StateNode> ready() {
        return getObjectComponent(QualifiedName.parse("0:Ready"))
            .thenApply(StateNode.class::cast);
    }

    @Override
    public CompletableFuture<StateNode> running() {
        return getObjectComponent(QualifiedName.parse("0:Running"))
            .thenApply(StateNode.class::cast);
    }

    @Override
    public CompletableFuture<StateNode> suspended() {
        return getObjectComponent(QualifiedName.parse("0:Suspended"))
            .thenApply(StateNode.class::cast);
    }

    @Override
    public CompletableFuture<StateNode> halted() {
        return getObjectComponent(QualifiedName.parse("0:Halted"))
            .thenApply(StateNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> haltedToReady() {
        return getObjectComponent(QualifiedName.parse("0:HaltedToReady"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> readyToRunning() {
        return getObjectComponent(QualifiedName.parse("0:ReadyToRunning"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> runningToHalted() {
        return getObjectComponent(QualifiedName.parse("0:RunningToHalted"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> runningToReady() {
        return getObjectComponent(QualifiedName.parse("0:RunningToReady"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> runningToSuspended() {
        return getObjectComponent(QualifiedName.parse("0:RunningToSuspended"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> suspendedToRunning() {
        return getObjectComponent(QualifiedName.parse("0:SuspendedToRunning"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> suspendedToHalted() {
        return getObjectComponent(QualifiedName.parse("0:SuspendedToHalted"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> suspendedToReady() {
        return getObjectComponent(QualifiedName.parse("0:SuspendedToReady"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> readyToHalted() {
        return getObjectComponent(QualifiedName.parse("0:ReadyToHalted"))
            .thenApply(TransitionNode.class::cast);
    }

}
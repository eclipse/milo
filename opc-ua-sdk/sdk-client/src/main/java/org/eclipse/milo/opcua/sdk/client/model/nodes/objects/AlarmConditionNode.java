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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AlarmConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class AlarmConditionNode extends AcknowledgeableConditionNode implements AlarmConditionType {

    public AlarmConditionNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> inputNode() {
        return getPropertyNode(AlarmConditionType.INPUT_NODE.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getInputNode() {
        return getProperty(AlarmConditionType.INPUT_NODE);
    }

    @Override
    public CompletableFuture<StatusCode> setInputNode(NodeId value) {
        return setProperty(AlarmConditionType.INPUT_NODE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> suppressedOrShelved() {
        return getPropertyNode(AlarmConditionType.SUPPRESSED_OR_SHELVED.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getSuppressedOrShelved() {
        return getProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED);
    }

    @Override
    public CompletableFuture<StatusCode> setSuppressedOrShelved(Boolean value) {
        return setProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxTimeShelved() {
        return getPropertyNode(AlarmConditionType.MAX_TIME_SHELVED.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getMaxTimeShelved() {
        return getProperty(AlarmConditionType.MAX_TIME_SHELVED);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxTimeShelved(Double value) {
        return setProperty(AlarmConditionType.MAX_TIME_SHELVED, value);
    }


    @Override
    public CompletableFuture<TwoStateVariableNode> enabledState() {
        return getVariableComponent(QualifiedName.parse("0:EnabledState"))
            .thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getEnabledState() {
        return enabledState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEnabledState(LocalizedText value) {
        return enabledState()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableNode> activeState() {
        return getVariableComponent(QualifiedName.parse("0:ActiveState"))
            .thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getActiveState() {
        return activeState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setActiveState(LocalizedText value) {
        return activeState()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableNode> suppressedState() {
        return getVariableComponent(QualifiedName.parse("0:SuppressedState"))
            .thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getSuppressedState() {
        return suppressedState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSuppressedState(LocalizedText value) {
        return suppressedState()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ShelvedStateMachineNode> shelvingState() {
        return getObjectComponent(QualifiedName.parse("0:ShelvingState"))
            .thenApply(ShelvedStateMachineNode.class::cast);
    }

}
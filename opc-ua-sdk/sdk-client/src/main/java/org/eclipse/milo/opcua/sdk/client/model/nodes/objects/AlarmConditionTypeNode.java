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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AlarmConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AlarmConditionTypeNode extends AcknowledgeableConditionTypeNode implements AlarmConditionType {
    public AlarmConditionTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getInputNodeNode() {
        return getPropertyNode(AlarmConditionType.INPUT_NODE);
    }

    public CompletableFuture<NodeId> getInputNode() {
        return getProperty(AlarmConditionType.INPUT_NODE);
    }

    public CompletableFuture<StatusCode> setInputNode(NodeId value) {
        return setProperty(AlarmConditionType.INPUT_NODE, value);
    }

    public CompletableFuture<PropertyTypeNode> getSuppressedOrShelvedNode() {
        return getPropertyNode(AlarmConditionType.SUPPRESSED_OR_SHELVED);
    }

    public CompletableFuture<Boolean> getSuppressedOrShelved() {
        return getProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED);
    }

    public CompletableFuture<StatusCode> setSuppressedOrShelved(Boolean value) {
        return setProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxTimeShelvedNode() {
        return getPropertyNode(AlarmConditionType.MAX_TIME_SHELVED);
    }

    public CompletableFuture<Double> getMaxTimeShelved() {
        return getProperty(AlarmConditionType.MAX_TIME_SHELVED);
    }

    public CompletableFuture<StatusCode> setMaxTimeShelved(Double value) {
        return setProperty(AlarmConditionType.MAX_TIME_SHELVED, value);
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getEnabledStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EnabledState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getEnabledState() {
        return getEnabledStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEnabledState(LocalizedText value) {
        return getEnabledStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getActiveStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ActiveState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getActiveState() {
        return getActiveStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setActiveState(LocalizedText value) {
        return getActiveStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getSuppressedStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SuppressedState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getSuppressedState() {
        return getSuppressedStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSuppressedState(LocalizedText value) {
        return getSuppressedStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ShelvedStateMachineTypeNode> getShelvingStateNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ShelvingState").thenApply(ShelvedStateMachineTypeNode.class::cast);
    }
}

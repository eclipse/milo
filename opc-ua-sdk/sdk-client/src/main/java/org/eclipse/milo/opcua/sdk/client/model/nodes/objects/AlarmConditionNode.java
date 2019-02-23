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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AlarmConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AlarmConditionNode extends AcknowledgeableConditionNode implements AlarmConditionType {
    public AlarmConditionNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getInputNodeNode() {
        return getPropertyNode(AlarmConditionType.INPUT_NODE);
    }

    public CompletableFuture<NodeId> getInputNode() {
        return getProperty(AlarmConditionType.INPUT_NODE);
    }

    public CompletableFuture<StatusCode> setInputNode(NodeId value) {
        return setProperty(AlarmConditionType.INPUT_NODE, value);
    }

    public CompletableFuture<PropertyNode> getSuppressedOrShelvedNode() {
        return getPropertyNode(AlarmConditionType.SUPPRESSED_OR_SHELVED);
    }

    public CompletableFuture<Boolean> getSuppressedOrShelved() {
        return getProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED);
    }

    public CompletableFuture<StatusCode> setSuppressedOrShelved(Boolean value) {
        return setProperty(AlarmConditionType.SUPPRESSED_OR_SHELVED, value);
    }

    public CompletableFuture<PropertyNode> getMaxTimeShelvedNode() {
        return getPropertyNode(AlarmConditionType.MAX_TIME_SHELVED);
    }

    public CompletableFuture<Double> getMaxTimeShelved() {
        return getProperty(AlarmConditionType.MAX_TIME_SHELVED);
    }

    public CompletableFuture<StatusCode> setMaxTimeShelved(Double value) {
        return setProperty(AlarmConditionType.MAX_TIME_SHELVED, value);
    }

    public CompletableFuture<TwoStateVariableNode> getEnabledStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EnabledState").thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getEnabledState() {
        return getEnabledStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setEnabledState(LocalizedText value) {
        return getEnabledStateNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<TwoStateVariableNode> getActiveStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ActiveState").thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getActiveState() {
        return getActiveStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setActiveState(LocalizedText value) {
        return getActiveStateNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<TwoStateVariableNode> getSuppressedStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SuppressedState").thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getSuppressedState() {
        return getSuppressedStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setSuppressedState(LocalizedText value) {
        return getSuppressedStateNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<ShelvedStateMachineNode> getShelvingStateNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ShelvingState").thenApply(ShelvedStateMachineNode.class::cast);
    }
}

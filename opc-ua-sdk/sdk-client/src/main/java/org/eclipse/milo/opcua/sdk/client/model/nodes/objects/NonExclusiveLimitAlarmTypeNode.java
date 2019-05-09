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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonExclusiveLimitAlarmType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class NonExclusiveLimitAlarmTypeNode extends LimitAlarmTypeNode implements NonExclusiveLimitAlarmType {
    public NonExclusiveLimitAlarmTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
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
    public CompletableFuture<TwoStateVariableTypeNode> getHighHighStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "HighHighState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getHighHighState() {
        return getHighHighStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setHighHighState(LocalizedText value) {
        return getHighHighStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getHighStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "HighState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getHighState() {
        return getHighStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setHighState(LocalizedText value) {
        return getHighStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getLowStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LowState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getLowState() {
        return getLowStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setLowState(LocalizedText value) {
        return getLowStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getLowLowStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LowLowState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getLowLowState() {
        return getLowLowStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setLowLowState(LocalizedText value) {
        return getLowLowStateNode().thenCompose(node -> node.setValue(value));
    }
}

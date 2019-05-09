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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AcknowledgeableConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AcknowledgeableConditionTypeNode extends ConditionTypeNode implements AcknowledgeableConditionType {
    public AcknowledgeableConditionTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
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
    public CompletableFuture<TwoStateVariableTypeNode> getAckedStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "AckedState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getAckedState() {
        return getAckedStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setAckedState(LocalizedText value) {
        return getAckedStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getConfirmedStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ConfirmedState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getConfirmedState() {
        return getConfirmedStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setConfirmedState(LocalizedText value) {
        return getConfirmedStateNode().thenCompose(node -> node.setValue(value));
    }
}

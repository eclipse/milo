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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.StateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TransitionVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.StateMachineType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class StateMachineTypeNode extends BaseObjectTypeNode implements StateMachineType {
    public StateMachineTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<? extends StateVariableTypeNode> getCurrentStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentState").thenApply(StateVariableTypeNode.class::cast);
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
    public CompletableFuture<? extends TransitionVariableTypeNode> getLastTransitionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LastTransition").thenApply(TransitionVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getLastTransition() {
        return getLastTransitionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setLastTransition(LocalizedText value) {
        return getLastTransitionNode().thenCompose(node -> node.setValue(value));
    }
}

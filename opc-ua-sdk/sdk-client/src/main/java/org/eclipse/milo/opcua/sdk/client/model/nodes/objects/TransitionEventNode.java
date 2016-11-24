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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.StateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TransitionVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.TransitionEventType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class TransitionEventNode extends BaseEventNode implements TransitionEventType {

    public TransitionEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<TransitionVariableNode> transition() {
        return getVariableComponent(QualifiedName.parse("0:Transition"))
            .thenApply(TransitionVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getTransition() {
        return transition()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTransition(LocalizedText value) {
        return transition()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<StateVariableNode> fromState() {
        return getVariableComponent(QualifiedName.parse("0:FromState"))
            .thenApply(StateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getFromState() {
        return fromState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setFromState(LocalizedText value) {
        return fromState()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<StateVariableNode> toState() {
        return getVariableComponent(QualifiedName.parse("0:ToState"))
            .thenApply(StateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getToState() {
        return toState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setToState(LocalizedText value) {
        return toState()
            .thenCompose(node -> node.setValue(value));
    }


}
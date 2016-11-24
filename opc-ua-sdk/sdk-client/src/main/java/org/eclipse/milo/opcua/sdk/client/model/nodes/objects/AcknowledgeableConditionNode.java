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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AcknowledgeableConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class AcknowledgeableConditionNode extends ConditionNode implements AcknowledgeableConditionType {

    public AcknowledgeableConditionNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
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
    public CompletableFuture<TwoStateVariableNode> ackedState() {
        return getVariableComponent(QualifiedName.parse("0:AckedState"))
            .thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getAckedState() {
        return ackedState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setAckedState(LocalizedText value) {
        return ackedState()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableNode> confirmedState() {
        return getVariableComponent(QualifiedName.parse("0:ConfirmedState"))
            .thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getConfirmedState() {
        return confirmedState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setConfirmedState(LocalizedText value) {
        return confirmedState()
            .thenCompose(node -> node.setValue(value));
    }


}
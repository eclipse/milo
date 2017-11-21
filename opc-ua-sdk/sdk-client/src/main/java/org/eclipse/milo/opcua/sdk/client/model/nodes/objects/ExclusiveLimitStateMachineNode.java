/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveLimitStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public class ExclusiveLimitStateMachineNode extends FiniteStateMachineNode implements ExclusiveLimitStateMachineType {
    public ExclusiveLimitStateMachineNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<StateNode> getHighHighNode() {
        return getObjectComponent(QualifiedName.parse("0:HighHigh")).thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getHighNode() {
        return getObjectComponent(QualifiedName.parse("0:High")).thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getLowNode() {
        return getObjectComponent(QualifiedName.parse("0:Low")).thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getLowLowNode() {
        return getObjectComponent(QualifiedName.parse("0:LowLow")).thenApply(StateNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getLowLowToLowNode() {
        return getObjectComponent(QualifiedName.parse("0:LowLowToLow")).thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getLowToLowLowNode() {
        return getObjectComponent(QualifiedName.parse("0:LowToLowLow")).thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getHighHighToHighNode() {
        return getObjectComponent(QualifiedName.parse("0:HighHighToHigh")).thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getHighToHighHighNode() {
        return getObjectComponent(QualifiedName.parse("0:HighToHighHigh")).thenApply(TransitionNode.class::cast);
    }
}

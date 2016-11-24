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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveLimitStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;


public class ExclusiveLimitStateMachineNode extends FiniteStateMachineNode implements ExclusiveLimitStateMachineType {

    public ExclusiveLimitStateMachineNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<StateNode> highHigh() {
        return getObjectComponent(QualifiedName.parse("0:HighHigh"))
            .thenApply(StateNode.class::cast);
    }

    @Override
    public CompletableFuture<StateNode> high() {
        return getObjectComponent(QualifiedName.parse("0:High"))
            .thenApply(StateNode.class::cast);
    }

    @Override
    public CompletableFuture<StateNode> low() {
        return getObjectComponent(QualifiedName.parse("0:Low"))
            .thenApply(StateNode.class::cast);
    }

    @Override
    public CompletableFuture<StateNode> lowLow() {
        return getObjectComponent(QualifiedName.parse("0:LowLow"))
            .thenApply(StateNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> lowLowToLow() {
        return getObjectComponent(QualifiedName.parse("0:LowLowToLow"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> lowToLowLow() {
        return getObjectComponent(QualifiedName.parse("0:LowToLowLow"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> highHighToHigh() {
        return getObjectComponent(QualifiedName.parse("0:HighHighToHigh"))
            .thenApply(TransitionNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionNode> highToHighHigh() {
        return getObjectComponent(QualifiedName.parse("0:HighToHighHigh"))
            .thenApply(TransitionNode.class::cast);
    }

}
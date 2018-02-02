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

public class ExclusiveLimitStateMachineNode extends FiniteStateMachineNode implements ExclusiveLimitStateMachineType {
    public ExclusiveLimitStateMachineNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<StateNode> getHighHighNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "HighHigh").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getHighNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "High").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getLowNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Low").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getLowLowNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "LowLow").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getLowLowToLowNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "LowLowToLow").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getLowToLowLowNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "LowToLowLow").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getHighHighToHighNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "HighHighToHigh").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getHighToHighHighNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "HighToHighHigh").thenApply(TransitionNode.class::cast);
    }
}

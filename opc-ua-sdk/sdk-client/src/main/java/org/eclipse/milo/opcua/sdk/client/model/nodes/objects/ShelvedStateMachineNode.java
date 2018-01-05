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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ShelvedStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class ShelvedStateMachineNode extends FiniteStateMachineNode implements ShelvedStateMachineType {
    public ShelvedStateMachineNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getUnshelveTimeNode() {
        return getPropertyNode(ShelvedStateMachineType.UNSHELVE_TIME);
    }

    public CompletableFuture<Double> getUnshelveTime() {
        return getProperty(ShelvedStateMachineType.UNSHELVE_TIME);
    }

    public CompletableFuture<StatusCode> setUnshelveTime(Double value) {
        return setProperty(ShelvedStateMachineType.UNSHELVE_TIME, value);
    }

    public CompletableFuture<StateNode> getUnshelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Unshelved").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getTimedShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "TimedShelved").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<StateNode> getOneShotShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelved").thenApply(StateNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getUnshelvedToTimedShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "UnshelvedToTimedShelved").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getUnshelvedToOneShotShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "UnshelvedToOneShotShelved").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getTimedShelvedToUnshelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "TimedShelvedToUnshelved").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getTimedShelvedToOneShotShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "TimedShelvedToOneShotShelved").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getOneShotShelvedToUnshelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelvedToUnshelved").thenApply(TransitionNode.class::cast);
    }

    public CompletableFuture<TransitionNode> getOneShotShelvedToTimedShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelvedToTimedShelved").thenApply(TransitionNode.class::cast);
    }
}

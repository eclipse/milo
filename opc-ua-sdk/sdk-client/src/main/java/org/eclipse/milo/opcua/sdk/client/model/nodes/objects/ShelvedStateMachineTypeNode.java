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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ShelvedStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class ShelvedStateMachineTypeNode extends FiniteStateMachineTypeNode implements ShelvedStateMachineType {
    public ShelvedStateMachineTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getUnshelveTimeNode() {
        return getPropertyNode(ShelvedStateMachineType.UNSHELVE_TIME);
    }

    public CompletableFuture<Double> getUnshelveTime() {
        return getProperty(ShelvedStateMachineType.UNSHELVE_TIME);
    }

    public CompletableFuture<StatusCode> setUnshelveTime(Double value) {
        return setProperty(ShelvedStateMachineType.UNSHELVE_TIME, value);
    }

    @Override
    public CompletableFuture<StateTypeNode> getUnshelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Unshelved").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getTimedShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "TimedShelved").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getOneShotShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelved").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getUnshelvedToTimedShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "UnshelvedToTimedShelved").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getUnshelvedToOneShotShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "UnshelvedToOneShotShelved").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getTimedShelvedToUnshelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "TimedShelvedToUnshelved").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getTimedShelvedToOneShotShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "TimedShelvedToOneShotShelved").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getOneShotShelvedToUnshelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelvedToUnshelved").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getOneShotShelvedToTimedShelvedNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelvedToTimedShelved").thenApply(TransitionTypeNode.class::cast);
    }
}

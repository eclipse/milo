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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveLimitStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ExclusiveLimitStateMachineTypeNode extends FiniteStateMachineTypeNode implements ExclusiveLimitStateMachineType {
    public ExclusiveLimitStateMachineTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<StateTypeNode> getHighHighNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "HighHigh").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getHighNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "High").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getLowNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Low").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<StateTypeNode> getLowLowNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "LowLow").thenApply(StateTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getLowLowToLowNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "LowLowToLow").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getLowToLowLowNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "LowToLowLow").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getHighHighToHighNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "HighHighToHigh").thenApply(TransitionTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<TransitionTypeNode> getHighToHighHighNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "HighToHighHigh").thenApply(TransitionTypeNode.class::cast);
    }
}

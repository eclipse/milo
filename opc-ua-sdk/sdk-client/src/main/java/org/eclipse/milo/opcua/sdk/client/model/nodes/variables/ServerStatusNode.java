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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;

public class ServerStatusNode extends BaseDataVariableNode implements ServerStatusType {
    public ServerStatusNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<BaseDataVariableNode> getStartTimeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "StartTime").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getStartTime() {
        return getStartTimeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    public CompletableFuture<StatusCode> setStartTime(DateTime value) {
        return getStartTimeNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCurrentTimeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentTime").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getCurrentTime() {
        return getCurrentTimeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    public CompletableFuture<StatusCode> setCurrentTime(DateTime value) {
        return getCurrentTimeNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "State").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServerState> getState() {
        return getStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServerState.class));
    }

    public CompletableFuture<StatusCode> setState(ServerState value) {
        return getStateNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BuildInfoNode> getBuildInfoNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BuildInfo").thenApply(BuildInfoNode.class::cast);
    }

    public CompletableFuture<BuildInfo> getBuildInfo() {
        return getBuildInfoNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, BuildInfo.class));
    }

    public CompletableFuture<StatusCode> setBuildInfo(BuildInfo value) {
        return getBuildInfoNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSecondsTillShutdownNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SecondsTillShutdown").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSecondsTillShutdown() {
        return getSecondsTillShutdownNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setSecondsTillShutdown(UInteger value) {
        return getSecondsTillShutdownNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getShutdownReasonNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ShutdownReason").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getShutdownReason() {
        return getShutdownReasonNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setShutdownReason(LocalizedText value) {
        return getShutdownReasonNode().thenCompose(node -> node.setValue(value));
    }
}

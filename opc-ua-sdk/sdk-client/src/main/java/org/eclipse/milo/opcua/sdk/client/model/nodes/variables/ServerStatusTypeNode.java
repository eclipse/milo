/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

public class ServerStatusTypeNode extends BaseDataVariableTypeNode implements ServerStatusType {
    public ServerStatusTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getStartTimeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "StartTime").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<DateTime> getStartTime() {
        return getStartTimeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setStartTime(DateTime value) {
        return getStartTimeNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCurrentTimeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentTime").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<DateTime> getCurrentTime() {
        return getCurrentTimeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentTime(DateTime value) {
        return getCurrentTimeNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "State").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServerState> getState() {
        return getStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServerState.class));
    }

    @Override
    public CompletableFuture<StatusCode> setState(ServerState value) {
        return getStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BuildInfoTypeNode> getBuildInfoNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BuildInfo").thenApply(BuildInfoTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<BuildInfo> getBuildInfo() {
        return getBuildInfoNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, BuildInfo.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBuildInfo(BuildInfo value) {
        return getBuildInfoNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSecondsTillShutdownNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SecondsTillShutdown").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getSecondsTillShutdown() {
        return getSecondsTillShutdownNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecondsTillShutdown(UInteger value) {
        return getSecondsTillShutdownNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getShutdownReasonNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ShutdownReason").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getShutdownReason() {
        return getShutdownReasonNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setShutdownReason(LocalizedText value) {
        return getShutdownReasonNode().thenCompose(node -> node.setValue(value));
    }
}

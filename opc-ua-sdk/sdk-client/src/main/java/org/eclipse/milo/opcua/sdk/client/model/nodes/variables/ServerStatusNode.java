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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;


public class ServerStatusNode extends BaseDataVariableNode implements ServerStatusType {

    public ServerStatusNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<BaseDataVariableNode> startTime() {
        return getComponent(QualifiedName.parse("0:StartTime"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getStartTime() {
        return startTime()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setStartTime(DateTime value) {
        return startTime()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> currentTime() {
        return getComponent(QualifiedName.parse("0:CurrentTime"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getCurrentTime() {
        return currentTime()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentTime(DateTime value) {
        return currentTime()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> state() {
        return getComponent(QualifiedName.parse("0:State"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServerState> getState() {
        return state()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServerState.class));
    }

    @Override
    public CompletableFuture<StatusCode> setState(ServerState value) {
        return state()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BuildInfoNode> buildInfo() {
        return getComponent(QualifiedName.parse("0:BuildInfo"))
            .thenApply(BuildInfoNode.class::cast);
    }

    public CompletableFuture<BuildInfo> getBuildInfo() {
        return buildInfo()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, BuildInfo.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBuildInfo(BuildInfo value) {
        return buildInfo()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> secondsTillShutdown() {
        return getComponent(QualifiedName.parse("0:SecondsTillShutdown"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSecondsTillShutdown() {
        return secondsTillShutdown()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecondsTillShutdown(UInteger value) {
        return secondsTillShutdown()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> shutdownReason() {
        return getComponent(QualifiedName.parse("0:ShutdownReason"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getShutdownReason() {
        return shutdownReason()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setShutdownReason(LocalizedText value) {
        return shutdownReason()
            .thenCompose(node -> node.setValue(value));
    }

}
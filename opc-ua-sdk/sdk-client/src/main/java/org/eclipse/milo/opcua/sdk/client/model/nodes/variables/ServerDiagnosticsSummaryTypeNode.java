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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ServerDiagnosticsSummaryTypeNode extends BaseDataVariableTypeNode implements ServerDiagnosticsSummaryType {
    public ServerDiagnosticsSummaryTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getServerViewCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ServerViewCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getServerViewCount() {
        return getServerViewCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setServerViewCount(UInteger value) {
        return getServerViewCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCurrentSessionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentSessionCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getCurrentSessionCount() {
        return getCurrentSessionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentSessionCount(UInteger value) {
        return getCurrentSessionCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCumulatedSessionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CumulatedSessionCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getCumulatedSessionCount() {
        return getCumulatedSessionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCumulatedSessionCount(UInteger value) {
        return getCumulatedSessionCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSecurityRejectedSessionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SecurityRejectedSessionCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getSecurityRejectedSessionCount() {
        return getSecurityRejectedSessionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecurityRejectedSessionCount(UInteger value) {
        return getSecurityRejectedSessionCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getRejectedSessionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RejectedSessionCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getRejectedSessionCount() {
        return getRejectedSessionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRejectedSessionCount(UInteger value) {
        return getRejectedSessionCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSessionTimeoutCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionTimeoutCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getSessionTimeoutCount() {
        return getSessionTimeoutCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionTimeoutCount(UInteger value) {
        return getSessionTimeoutCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSessionAbortCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionAbortCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getSessionAbortCount() {
        return getSessionAbortCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionAbortCount(UInteger value) {
        return getSessionAbortCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getPublishingIntervalCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "PublishingIntervalCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getPublishingIntervalCount() {
        return getPublishingIntervalCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setPublishingIntervalCount(UInteger value) {
        return getPublishingIntervalCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCurrentSubscriptionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentSubscriptionCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getCurrentSubscriptionCount() {
        return getCurrentSubscriptionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentSubscriptionCount(UInteger value) {
        return getCurrentSubscriptionCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCumulatedSubscriptionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CumulatedSubscriptionCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getCumulatedSubscriptionCount() {
        return getCumulatedSubscriptionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCumulatedSubscriptionCount(UInteger value) {
        return getCumulatedSubscriptionCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSecurityRejectedRequestsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SecurityRejectedRequestsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getSecurityRejectedRequestsCount() {
        return getSecurityRejectedRequestsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecurityRejectedRequestsCount(UInteger value) {
        return getSecurityRejectedRequestsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getRejectedRequestsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RejectedRequestsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getRejectedRequestsCount() {
        return getRejectedRequestsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRejectedRequestsCount(UInteger value) {
        return getRejectedRequestsCountNode().thenCompose(node -> node.setValue(value));
    }
}

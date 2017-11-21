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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ServerDiagnosticsSummaryNode extends BaseDataVariableNode implements ServerDiagnosticsSummaryType {
    public ServerDiagnosticsSummaryNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<BaseDataVariableNode> getServerViewCountNode() {
        return getVariableComponent(QualifiedName.parse("0:ServerViewCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getServerViewCount() {
        return getServerViewCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setServerViewCount(UInteger value) {
        return getServerViewCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCurrentSessionCountNode() {
        return getVariableComponent(QualifiedName.parse("0:CurrentSessionCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentSessionCount() {
        return getCurrentSessionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCurrentSessionCount(UInteger value) {
        return getCurrentSessionCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCumulatedSessionCountNode() {
        return getVariableComponent(QualifiedName.parse("0:CumulatedSessionCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCumulatedSessionCount() {
        return getCumulatedSessionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCumulatedSessionCount(UInteger value) {
        return getCumulatedSessionCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSecurityRejectedSessionCountNode() {
        return getVariableComponent(QualifiedName.parse("0:SecurityRejectedSessionCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSecurityRejectedSessionCount() {
        return getSecurityRejectedSessionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setSecurityRejectedSessionCount(UInteger value) {
        return getSecurityRejectedSessionCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getRejectedSessionCountNode() {
        return getVariableComponent(QualifiedName.parse("0:RejectedSessionCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRejectedSessionCount() {
        return getRejectedSessionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setRejectedSessionCount(UInteger value) {
        return getRejectedSessionCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSessionTimeoutCountNode() {
        return getVariableComponent(QualifiedName.parse("0:SessionTimeoutCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSessionTimeoutCount() {
        return getSessionTimeoutCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setSessionTimeoutCount(UInteger value) {
        return getSessionTimeoutCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSessionAbortCountNode() {
        return getVariableComponent(QualifiedName.parse("0:SessionAbortCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSessionAbortCount() {
        return getSessionAbortCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setSessionAbortCount(UInteger value) {
        return getSessionAbortCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getPublishingIntervalCountNode() {
        return getVariableComponent(QualifiedName.parse("0:PublishingIntervalCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getPublishingIntervalCount() {
        return getPublishingIntervalCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setPublishingIntervalCount(UInteger value) {
        return getPublishingIntervalCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCurrentSubscriptionCountNode() {
        return getVariableComponent(QualifiedName.parse("0:CurrentSubscriptionCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentSubscriptionCount() {
        return getCurrentSubscriptionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCurrentSubscriptionCount(UInteger value) {
        return getCurrentSubscriptionCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCumulatedSubscriptionCountNode() {
        return getVariableComponent(QualifiedName.parse("0:CumulatedSubscriptionCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCumulatedSubscriptionCount() {
        return getCumulatedSubscriptionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCumulatedSubscriptionCount(UInteger value) {
        return getCumulatedSubscriptionCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSecurityRejectedRequestsCountNode() {
        return getVariableComponent(QualifiedName.parse("0:SecurityRejectedRequestsCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSecurityRejectedRequestsCount() {
        return getSecurityRejectedRequestsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setSecurityRejectedRequestsCount(UInteger value) {
        return getSecurityRejectedRequestsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getRejectedRequestsCountNode() {
        return getVariableComponent(QualifiedName.parse("0:RejectedRequestsCount")).thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRejectedRequestsCount() {
        return getRejectedRequestsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setRejectedRequestsCount(UInteger value) {
        return getRejectedRequestsCountNode().thenCompose(node -> node.setValue(value));
    }
}

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


    @Override
    public CompletableFuture<BaseDataVariableNode> serverViewCount() {
        return getComponent(QualifiedName.parse("0:ServerViewCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getServerViewCount() {
        return serverViewCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setServerViewCount(UInteger value) {
        return serverViewCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> currentSessionCount() {
        return getComponent(QualifiedName.parse("0:CurrentSessionCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentSessionCount() {
        return currentSessionCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentSessionCount(UInteger value) {
        return currentSessionCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> cumulatedSessionCount() {
        return getComponent(QualifiedName.parse("0:CumulatedSessionCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCumulatedSessionCount() {
        return cumulatedSessionCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCumulatedSessionCount(UInteger value) {
        return cumulatedSessionCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> securityRejectedSessionCount() {
        return getComponent(QualifiedName.parse("0:SecurityRejectedSessionCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSecurityRejectedSessionCount() {
        return securityRejectedSessionCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecurityRejectedSessionCount(UInteger value) {
        return securityRejectedSessionCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> rejectedSessionCount() {
        return getComponent(QualifiedName.parse("0:RejectedSessionCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRejectedSessionCount() {
        return rejectedSessionCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRejectedSessionCount(UInteger value) {
        return rejectedSessionCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> sessionTimeoutCount() {
        return getComponent(QualifiedName.parse("0:SessionTimeoutCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSessionTimeoutCount() {
        return sessionTimeoutCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionTimeoutCount(UInteger value) {
        return sessionTimeoutCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> sessionAbortCount() {
        return getComponent(QualifiedName.parse("0:SessionAbortCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSessionAbortCount() {
        return sessionAbortCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionAbortCount(UInteger value) {
        return sessionAbortCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> publishingIntervalCount() {
        return getComponent(QualifiedName.parse("0:PublishingIntervalCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getPublishingIntervalCount() {
        return publishingIntervalCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setPublishingIntervalCount(UInteger value) {
        return publishingIntervalCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> currentSubscriptionCount() {
        return getComponent(QualifiedName.parse("0:CurrentSubscriptionCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentSubscriptionCount() {
        return currentSubscriptionCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentSubscriptionCount(UInteger value) {
        return currentSubscriptionCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> cumulatedSubscriptionCount() {
        return getComponent(QualifiedName.parse("0:CumulatedSubscriptionCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCumulatedSubscriptionCount() {
        return cumulatedSubscriptionCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCumulatedSubscriptionCount(UInteger value) {
        return cumulatedSubscriptionCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> securityRejectedRequestsCount() {
        return getComponent(QualifiedName.parse("0:SecurityRejectedRequestsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSecurityRejectedRequestsCount() {
        return securityRejectedRequestsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecurityRejectedRequestsCount(UInteger value) {
        return securityRejectedRequestsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> rejectedRequestsCount() {
        return getComponent(QualifiedName.parse("0:RejectedRequestsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRejectedRequestsCount() {
        return rejectedRequestsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRejectedRequestsCount(UInteger value) {
        return rejectedRequestsCount()
            .thenCompose(node -> node.setValue(value));
    }

}
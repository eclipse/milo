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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SamplingIntervalDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerDiagnosticsSummaryNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;


public class ServerDiagnosticsNode extends BaseObjectNode implements ServerDiagnosticsType {

    public ServerDiagnosticsNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> enabledFlag() {
        return getPropertyNode(ServerDiagnosticsType.ENABLED_FLAG.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getEnabledFlag() {
        return getProperty(ServerDiagnosticsType.ENABLED_FLAG);
    }

    @Override
    public CompletableFuture<StatusCode> setEnabledFlag(Boolean value) {
        return setProperty(ServerDiagnosticsType.ENABLED_FLAG, value);
    }


    @Override
    public CompletableFuture<ServerDiagnosticsSummaryNode> serverDiagnosticsSummary() {
        return getVariableComponent(QualifiedName.parse("0:ServerDiagnosticsSummary"))
            .thenApply(ServerDiagnosticsSummaryNode.class::cast);
    }

    public CompletableFuture<ServerDiagnosticsSummaryDataType> getServerDiagnosticsSummary() {
        return serverDiagnosticsSummary()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServerDiagnosticsSummaryDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType value) {
        return serverDiagnosticsSummary()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<SamplingIntervalDiagnosticsArrayNode> samplingIntervalDiagnosticsArray() {
        return getVariableComponent(QualifiedName.parse("0:SamplingIntervalDiagnosticsArray"))
            .thenApply(SamplingIntervalDiagnosticsArrayNode.class::cast);
    }

    public CompletableFuture<SamplingIntervalDiagnosticsDataType[]> getSamplingIntervalDiagnosticsArray() {
        return samplingIntervalDiagnosticsArray()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, SamplingIntervalDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setSamplingIntervalDiagnosticsArray(SamplingIntervalDiagnosticsDataType[] value) {
        return samplingIntervalDiagnosticsArray()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<SubscriptionDiagnosticsArrayNode> subscriptionDiagnosticsArray() {
        return getVariableComponent(QualifiedName.parse("0:SubscriptionDiagnosticsArray"))
            .thenApply(SubscriptionDiagnosticsArrayNode.class::cast);
    }

    public CompletableFuture<SubscriptionDiagnosticsDataType[]> getSubscriptionDiagnosticsArray() {
        return subscriptionDiagnosticsArray()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, SubscriptionDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value) {
        return subscriptionDiagnosticsArray()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<SessionsDiagnosticsSummaryNode> sessionsDiagnosticsSummary() {
        return getObjectComponent(QualifiedName.parse("0:SessionsDiagnosticsSummary"))
            .thenApply(SessionsDiagnosticsSummaryNode.class::cast);
    }

}
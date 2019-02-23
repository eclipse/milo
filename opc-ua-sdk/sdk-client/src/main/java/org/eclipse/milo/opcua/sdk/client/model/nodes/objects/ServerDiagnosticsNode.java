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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SamplingIntervalDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerDiagnosticsSummaryNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public class ServerDiagnosticsNode extends BaseObjectNode implements ServerDiagnosticsType {
    public ServerDiagnosticsNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getEnabledFlagNode() {
        return getPropertyNode(ServerDiagnosticsType.ENABLED_FLAG);
    }

    public CompletableFuture<Boolean> getEnabledFlag() {
        return getProperty(ServerDiagnosticsType.ENABLED_FLAG);
    }

    public CompletableFuture<StatusCode> setEnabledFlag(Boolean value) {
        return setProperty(ServerDiagnosticsType.ENABLED_FLAG, value);
    }

    public CompletableFuture<ServerDiagnosticsSummaryNode> getServerDiagnosticsSummaryNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ServerDiagnosticsSummary").thenApply(ServerDiagnosticsSummaryNode.class::cast);
    }

    public CompletableFuture<ServerDiagnosticsSummaryDataType> getServerDiagnosticsSummary() {
        return getServerDiagnosticsSummaryNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServerDiagnosticsSummaryDataType.class));
    }

    public CompletableFuture<StatusCode> setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType value) {
        return getServerDiagnosticsSummaryNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<SamplingIntervalDiagnosticsArrayNode> getSamplingIntervalDiagnosticsArrayNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SamplingIntervalDiagnosticsArray").thenApply(SamplingIntervalDiagnosticsArrayNode.class::cast);
    }

    public CompletableFuture<SamplingIntervalDiagnosticsDataType[]> getSamplingIntervalDiagnosticsArray() {
        return getSamplingIntervalDiagnosticsArrayNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SamplingIntervalDiagnosticsDataType[].class));
    }

    public CompletableFuture<StatusCode> setSamplingIntervalDiagnosticsArray(SamplingIntervalDiagnosticsDataType[] value) {
        return getSamplingIntervalDiagnosticsArrayNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<SubscriptionDiagnosticsArrayNode> getSubscriptionDiagnosticsArrayNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SubscriptionDiagnosticsArray").thenApply(SubscriptionDiagnosticsArrayNode.class::cast);
    }

    public CompletableFuture<SubscriptionDiagnosticsDataType[]> getSubscriptionDiagnosticsArray() {
        return getSubscriptionDiagnosticsArrayNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SubscriptionDiagnosticsDataType[].class));
    }

    public CompletableFuture<StatusCode> setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value) {
        return getSubscriptionDiagnosticsArrayNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<SessionsDiagnosticsSummaryNode> getSessionsDiagnosticsSummaryNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "SessionsDiagnosticsSummary").thenApply(SessionsDiagnosticsSummaryNode.class::cast);
    }
}

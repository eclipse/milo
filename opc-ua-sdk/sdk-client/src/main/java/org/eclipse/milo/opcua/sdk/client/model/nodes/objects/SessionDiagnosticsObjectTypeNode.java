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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionDiagnosticsObjectType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public class SessionDiagnosticsObjectTypeNode extends BaseObjectTypeNode implements SessionDiagnosticsObjectType {
    public SessionDiagnosticsObjectTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<SessionDiagnosticsVariableTypeNode> getSessionDiagnosticsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionDiagnostics").thenApply(SessionDiagnosticsVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<SessionDiagnosticsDataType> getSessionDiagnostics() {
        return getSessionDiagnosticsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SessionDiagnosticsDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionDiagnostics(SessionDiagnosticsDataType value) {
        return getSessionDiagnosticsNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<SessionSecurityDiagnosticsTypeNode> getSessionSecurityDiagnosticsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionSecurityDiagnostics").thenApply(SessionSecurityDiagnosticsTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<SessionSecurityDiagnosticsDataType> getSessionSecurityDiagnostics() {
        return getSessionSecurityDiagnosticsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SessionSecurityDiagnosticsDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionSecurityDiagnostics(
        SessionSecurityDiagnosticsDataType value) {
        return getSessionSecurityDiagnosticsNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<SubscriptionDiagnosticsArrayTypeNode> getSubscriptionDiagnosticsArrayNode(
    ) {
        return getVariableComponent("http://opcfoundation.org/UA/", "SubscriptionDiagnosticsArray").thenApply(SubscriptionDiagnosticsArrayTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<SubscriptionDiagnosticsDataType[]> getSubscriptionDiagnosticsArray() {
        return getSubscriptionDiagnosticsArrayNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SubscriptionDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setSubscriptionDiagnosticsArray(
        SubscriptionDiagnosticsDataType[] value) {
        return getSubscriptionDiagnosticsArrayNode().thenCompose(node -> node.setValue(value));
    }
}

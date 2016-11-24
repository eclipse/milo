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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionDiagnosticsObjectType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;


public class SessionDiagnosticsObjectNode extends BaseObjectNode implements SessionDiagnosticsObjectType {

    public SessionDiagnosticsObjectNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<SessionDiagnosticsVariableNode> sessionDiagnostics() {
        return getVariableComponent(QualifiedName.parse("0:SessionDiagnostics"))
            .thenApply(SessionDiagnosticsVariableNode.class::cast);
    }

    public CompletableFuture<SessionDiagnosticsDataType> getSessionDiagnostics() {
        return sessionDiagnostics()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, SessionDiagnosticsDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionDiagnostics(SessionDiagnosticsDataType value) {
        return sessionDiagnostics()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<SessionSecurityDiagnosticsNode> sessionSecurityDiagnostics() {
        return getVariableComponent(QualifiedName.parse("0:SessionSecurityDiagnostics"))
            .thenApply(SessionSecurityDiagnosticsNode.class::cast);
    }

    public CompletableFuture<SessionSecurityDiagnosticsDataType> getSessionSecurityDiagnostics() {
        return sessionSecurityDiagnostics()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, SessionSecurityDiagnosticsDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value) {
        return sessionSecurityDiagnostics()
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


}
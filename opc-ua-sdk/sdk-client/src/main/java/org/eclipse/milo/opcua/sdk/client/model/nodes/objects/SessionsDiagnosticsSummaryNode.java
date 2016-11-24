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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionsDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;


public class SessionsDiagnosticsSummaryNode extends BaseObjectNode implements SessionsDiagnosticsSummaryType {

    public SessionsDiagnosticsSummaryNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<SessionDiagnosticsArrayNode> sessionDiagnosticsArray() {
        return getVariableComponent(QualifiedName.parse("0:SessionDiagnosticsArray"))
            .thenApply(SessionDiagnosticsArrayNode.class::cast);
    }

    public CompletableFuture<SessionDiagnosticsDataType[]> getSessionDiagnosticsArray() {
        return sessionDiagnosticsArray()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, SessionDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionDiagnosticsArray(SessionDiagnosticsDataType[] value) {
        return sessionDiagnosticsArray()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<SessionSecurityDiagnosticsArrayNode> sessionSecurityDiagnosticsArray() {
        return getVariableComponent(QualifiedName.parse("0:SessionSecurityDiagnosticsArray"))
            .thenApply(SessionSecurityDiagnosticsArrayNode.class::cast);
    }

    public CompletableFuture<SessionSecurityDiagnosticsDataType[]> getSessionSecurityDiagnosticsArray() {
        return sessionSecurityDiagnosticsArray()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, SessionSecurityDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionSecurityDiagnosticsArray(SessionSecurityDiagnosticsDataType[] value) {
        return sessionSecurityDiagnosticsArray()
            .thenCompose(node -> node.setValue(value));
    }


}
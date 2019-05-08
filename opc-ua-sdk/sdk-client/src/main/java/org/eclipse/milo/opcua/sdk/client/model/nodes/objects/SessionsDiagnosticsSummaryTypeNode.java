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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionsDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public class SessionsDiagnosticsSummaryTypeNode extends BaseObjectTypeNode implements SessionsDiagnosticsSummaryType {
    public SessionsDiagnosticsSummaryTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<SessionDiagnosticsArrayTypeNode> getSessionDiagnosticsArrayNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionDiagnosticsArray").thenApply(SessionDiagnosticsArrayTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<SessionDiagnosticsDataType[]> getSessionDiagnosticsArray() {
        return getSessionDiagnosticsArrayNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SessionDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionDiagnosticsArray(
        SessionDiagnosticsDataType[] value) {
        return getSessionDiagnosticsArrayNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<SessionSecurityDiagnosticsArrayTypeNode> getSessionSecurityDiagnosticsArrayNode(
    ) {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionSecurityDiagnosticsArray").thenApply(SessionSecurityDiagnosticsArrayTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<SessionSecurityDiagnosticsDataType[]> getSessionSecurityDiagnosticsArray(
    ) {
        return getSessionSecurityDiagnosticsArrayNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SessionSecurityDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionSecurityDiagnosticsArray(
        SessionSecurityDiagnosticsDataType[] value) {
        return getSessionSecurityDiagnosticsArrayNode().thenCompose(node -> node.setValue(value));
    }
}

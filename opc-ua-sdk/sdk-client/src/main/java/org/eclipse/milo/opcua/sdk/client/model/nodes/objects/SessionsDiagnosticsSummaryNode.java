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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionsDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public class SessionsDiagnosticsSummaryNode extends BaseObjectNode implements SessionsDiagnosticsSummaryType {
    public SessionsDiagnosticsSummaryNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<SessionDiagnosticsArrayNode> getSessionDiagnosticsArrayNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionDiagnosticsArray").thenApply(SessionDiagnosticsArrayNode.class::cast);
    }

    public CompletableFuture<SessionDiagnosticsDataType[]> getSessionDiagnosticsArray() {
        return getSessionDiagnosticsArrayNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SessionDiagnosticsDataType[].class));
    }

    public CompletableFuture<StatusCode> setSessionDiagnosticsArray(SessionDiagnosticsDataType[] value) {
        return getSessionDiagnosticsArrayNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<SessionSecurityDiagnosticsArrayNode> getSessionSecurityDiagnosticsArrayNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionSecurityDiagnosticsArray").thenApply(SessionSecurityDiagnosticsArrayNode.class::cast);
    }

    public CompletableFuture<SessionSecurityDiagnosticsDataType[]> getSessionSecurityDiagnosticsArray() {
        return getSessionSecurityDiagnosticsArrayNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SessionSecurityDiagnosticsDataType[].class));
    }

    public CompletableFuture<StatusCode> setSessionSecurityDiagnosticsArray(SessionSecurityDiagnosticsDataType[] value) {
        return getSessionSecurityDiagnosticsArrayNode().thenCompose(node -> node.setValue(value));
    }
}

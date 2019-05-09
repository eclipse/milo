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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public class SessionSecurityDiagnosticsArrayTypeNode extends BaseDataVariableTypeNode implements SessionSecurityDiagnosticsArrayType {
    public SessionSecurityDiagnosticsArrayTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
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
}

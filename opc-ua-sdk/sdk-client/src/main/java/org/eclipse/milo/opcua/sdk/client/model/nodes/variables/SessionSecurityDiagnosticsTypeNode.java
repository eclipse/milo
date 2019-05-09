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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

public class SessionSecurityDiagnosticsTypeNode extends BaseDataVariableTypeNode implements SessionSecurityDiagnosticsType {
    public SessionSecurityDiagnosticsTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSessionIdNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionId").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<NodeId> getSessionId() {
        return getSessionIdNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, NodeId.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionId(NodeId value) {
        return getSessionIdNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getClientUserIdOfSessionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientUserIdOfSession").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getClientUserIdOfSession() {
        return getClientUserIdOfSessionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientUserIdOfSession(String value) {
        return getClientUserIdOfSessionNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getClientUserIdHistoryNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientUserIdHistory").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String[]> getClientUserIdHistory() {
        return getClientUserIdHistoryNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientUserIdHistory(String[] value) {
        return getClientUserIdHistoryNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getAuthenticationMechanismNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "AuthenticationMechanism").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getAuthenticationMechanism() {
        return getAuthenticationMechanismNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setAuthenticationMechanism(String value) {
        return getAuthenticationMechanismNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getEncodingNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "Encoding").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getEncoding() {
        return getEncodingNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEncoding(String value) {
        return getEncodingNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getTransportProtocolNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TransportProtocol").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getTransportProtocol() {
        return getTransportProtocolNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTransportProtocol(String value) {
        return getTransportProtocolNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSecurityModeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SecurityMode").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<MessageSecurityMode> getSecurityMode() {
        return getSecurityModeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, MessageSecurityMode.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecurityMode(MessageSecurityMode value) {
        return getSecurityModeNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSecurityPolicyUriNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SecurityPolicyUri").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getSecurityPolicyUri() {
        return getSecurityPolicyUriNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecurityPolicyUri(String value) {
        return getSecurityPolicyUriNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getClientCertificateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientCertificate").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ByteString> getClientCertificate() {
        return getClientCertificateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ByteString.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientCertificate(ByteString value) {
        return getClientCertificateNode().thenCompose(node -> node.setValue(value));
    }
}

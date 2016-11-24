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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;


public class SessionSecurityDiagnosticsNode extends BaseDataVariableNode implements SessionSecurityDiagnosticsType {

    public SessionSecurityDiagnosticsNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<BaseDataVariableNode> sessionId() {
        return getComponent(QualifiedName.parse("0:SessionId"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<NodeId> getSessionId() {
        return sessionId()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, NodeId.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionId(NodeId value) {
        return sessionId()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> clientUserIdOfSession() {
        return getComponent(QualifiedName.parse("0:ClientUserIdOfSession"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getClientUserIdOfSession() {
        return clientUserIdOfSession()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientUserIdOfSession(String value) {
        return clientUserIdOfSession()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> clientUserIdHistory() {
        return getComponent(QualifiedName.parse("0:ClientUserIdHistory"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String[]> getClientUserIdHistory() {
        return clientUserIdHistory()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientUserIdHistory(String[] value) {
        return clientUserIdHistory()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> authenticationMechanism() {
        return getComponent(QualifiedName.parse("0:AuthenticationMechanism"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getAuthenticationMechanism() {
        return authenticationMechanism()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setAuthenticationMechanism(String value) {
        return authenticationMechanism()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> encoding() {
        return getComponent(QualifiedName.parse("0:Encoding"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getEncoding() {
        return encoding()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEncoding(String value) {
        return encoding()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> transportProtocol() {
        return getComponent(QualifiedName.parse("0:TransportProtocol"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getTransportProtocol() {
        return transportProtocol()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTransportProtocol(String value) {
        return transportProtocol()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> securityMode() {
        return getComponent(QualifiedName.parse("0:SecurityMode"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<MessageSecurityMode> getSecurityMode() {
        return securityMode()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, MessageSecurityMode.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecurityMode(MessageSecurityMode value) {
        return securityMode()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> securityPolicyUri() {
        return getComponent(QualifiedName.parse("0:SecurityPolicyUri"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getSecurityPolicyUri() {
        return securityPolicyUri()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSecurityPolicyUri(String value) {
        return securityPolicyUri()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> clientCertificate() {
        return getComponent(QualifiedName.parse("0:ClientCertificate"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ByteString> getClientCertificate() {
        return clientCertificate()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ByteString.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientCertificate(ByteString value) {
        return clientCertificate()
            .thenCompose(node -> node.setValue(value));
    }

}
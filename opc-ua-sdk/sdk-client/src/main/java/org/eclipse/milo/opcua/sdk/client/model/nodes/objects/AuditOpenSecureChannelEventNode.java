/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditOpenSecureChannelEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

public class AuditOpenSecureChannelEventNode extends AuditChannelEventNode implements AuditOpenSecureChannelEventType {
    public AuditOpenSecureChannelEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getClientCertificateNode() {
        return getPropertyNode(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE);
    }

    public CompletableFuture<ByteString> getClientCertificate() {
        return getProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE);
    }

    public CompletableFuture<StatusCode> setClientCertificate(ByteString value) {
        return setProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE, value);
    }

    public CompletableFuture<PropertyNode> getClientCertificateThumbprintNode() {
        return getPropertyNode(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT);
    }

    public CompletableFuture<String> getClientCertificateThumbprint() {
        return getProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT);
    }

    public CompletableFuture<StatusCode> setClientCertificateThumbprint(String value) {
        return setProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT, value);
    }

    public CompletableFuture<PropertyNode> getRequestTypeNode() {
        return getPropertyNode(AuditOpenSecureChannelEventType.REQUEST_TYPE);
    }

    public CompletableFuture<SecurityTokenRequestType> getRequestType() {
        return getProperty(AuditOpenSecureChannelEventType.REQUEST_TYPE);
    }

    public CompletableFuture<StatusCode> setRequestType(SecurityTokenRequestType value) {
        return setProperty(AuditOpenSecureChannelEventType.REQUEST_TYPE, value);
    }

    public CompletableFuture<PropertyNode> getSecurityPolicyUriNode() {
        return getPropertyNode(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI);
    }

    public CompletableFuture<String> getSecurityPolicyUri() {
        return getProperty(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI);
    }

    public CompletableFuture<StatusCode> setSecurityPolicyUri(String value) {
        return setProperty(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI, value);
    }

    public CompletableFuture<PropertyNode> getSecurityModeNode() {
        return getPropertyNode(AuditOpenSecureChannelEventType.SECURITY_MODE);
    }

    public CompletableFuture<MessageSecurityMode> getSecurityMode() {
        return getProperty(AuditOpenSecureChannelEventType.SECURITY_MODE);
    }

    public CompletableFuture<StatusCode> setSecurityMode(MessageSecurityMode value) {
        return setProperty(AuditOpenSecureChannelEventType.SECURITY_MODE, value);
    }

    public CompletableFuture<PropertyNode> getRequestedLifetimeNode() {
        return getPropertyNode(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME);
    }

    public CompletableFuture<Double> getRequestedLifetime() {
        return getProperty(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME);
    }

    public CompletableFuture<StatusCode> setRequestedLifetime(Double value) {
        return setProperty(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME, value);
    }
}

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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCreateSessionEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditCreateSessionEventNode extends AuditSessionEventNode implements AuditCreateSessionEventType {
    public AuditCreateSessionEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getSecureChannelIdNode() {
        return getPropertyNode(AuditCreateSessionEventType.SECURE_CHANNEL_ID);
    }

    public CompletableFuture<String> getSecureChannelId() {
        return getProperty(AuditCreateSessionEventType.SECURE_CHANNEL_ID);
    }

    public CompletableFuture<StatusCode> setSecureChannelId(String value) {
        return setProperty(AuditCreateSessionEventType.SECURE_CHANNEL_ID, value);
    }

    public CompletableFuture<PropertyNode> getClientCertificateNode() {
        return getPropertyNode(AuditCreateSessionEventType.CLIENT_CERTIFICATE);
    }

    public CompletableFuture<ByteString> getClientCertificate() {
        return getProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE);
    }

    public CompletableFuture<StatusCode> setClientCertificate(ByteString value) {
        return setProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE, value);
    }

    public CompletableFuture<PropertyNode> getClientCertificateThumbprintNode() {
        return getPropertyNode(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT);
    }

    public CompletableFuture<String> getClientCertificateThumbprint() {
        return getProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT);
    }

    public CompletableFuture<StatusCode> setClientCertificateThumbprint(String value) {
        return setProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT, value);
    }

    public CompletableFuture<PropertyNode> getRevisedSessionTimeoutNode() {
        return getPropertyNode(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT);
    }

    public CompletableFuture<Double> getRevisedSessionTimeout() {
        return getProperty(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT);
    }

    public CompletableFuture<StatusCode> setRevisedSessionTimeout(Double value) {
        return setProperty(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT, value);
    }
}

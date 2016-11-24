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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditActivateSessionEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;


public class AuditActivateSessionEventNode extends AuditSessionEventNode implements AuditActivateSessionEventType {

    public AuditActivateSessionEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> clientSoftwareCertificates() {
        return getPropertyNode(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES.getBrowseName());
    }

    @Override
    public CompletableFuture<SignedSoftwareCertificate[]> getClientSoftwareCertificates() {
        return getProperty(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES);
    }

    @Override
    public CompletableFuture<StatusCode> setClientSoftwareCertificates(SignedSoftwareCertificate[] value) {
        return setProperty(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES, value);
    }

    @Override
    public CompletableFuture<PropertyNode> userIdentityToken() {
        return getPropertyNode(AuditActivateSessionEventType.USER_IDENTITY_TOKEN.getBrowseName());
    }

    @Override
    public CompletableFuture<UserIdentityToken> getUserIdentityToken() {
        return getProperty(AuditActivateSessionEventType.USER_IDENTITY_TOKEN);
    }

    @Override
    public CompletableFuture<StatusCode> setUserIdentityToken(UserIdentityToken value) {
        return setProperty(AuditActivateSessionEventType.USER_IDENTITY_TOKEN, value);
    }

    @Override
    public CompletableFuture<PropertyNode> secureChannelId() {
        return getPropertyNode(AuditActivateSessionEventType.SECURE_CHANNEL_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getSecureChannelId() {
        return getProperty(AuditActivateSessionEventType.SECURE_CHANNEL_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setSecureChannelId(String value) {
        return setProperty(AuditActivateSessionEventType.SECURE_CHANNEL_ID, value);
    }


}
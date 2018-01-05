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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;

public interface AuditActivateSessionEventType extends AuditSessionEventType {
    QualifiedProperty<SignedSoftwareCertificate[]> CLIENT_SOFTWARE_CERTIFICATES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientSoftwareCertificates",
        NodeId.parse("ns=0;i=344"),
        ValueRanks.OneDimension,
        SignedSoftwareCertificate[].class
    );

    QualifiedProperty<UserIdentityToken> USER_IDENTITY_TOKEN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserIdentityToken",
        NodeId.parse("ns=0;i=316"),
        ValueRanks.Scalar,
        UserIdentityToken.class
    );

    QualifiedProperty<String> SECURE_CHANNEL_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecureChannelId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    CompletableFuture<? extends PropertyType> getClientSoftwareCertificatesNode();

    CompletableFuture<SignedSoftwareCertificate[]> getClientSoftwareCertificates();

    CompletableFuture<StatusCode> setClientSoftwareCertificates(SignedSoftwareCertificate[] value);

    CompletableFuture<? extends PropertyType> getUserIdentityTokenNode();

    CompletableFuture<UserIdentityToken> getUserIdentityToken();

    CompletableFuture<StatusCode> setUserIdentityToken(UserIdentityToken value);

    CompletableFuture<? extends PropertyType> getSecureChannelIdNode();

    CompletableFuture<String> getSecureChannelId();

    CompletableFuture<StatusCode> setSecureChannelId(String value);
}

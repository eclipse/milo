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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;


public interface AuditActivateSessionEventType extends AuditSessionEventType {

    Property<SignedSoftwareCertificate[]> CLIENT_SOFTWARE_CERTIFICATES = new BasicProperty<>(
        QualifiedName.parse("0:ClientSoftwareCertificates"),
        NodeId.parse("ns=0;i=344"),
        1,
        SignedSoftwareCertificate[].class
    );

    Property<UserIdentityToken> USER_IDENTITY_TOKEN = new BasicProperty<>(
        QualifiedName.parse("0:UserIdentityToken"),
        NodeId.parse("ns=0;i=316"),
        -1,
        UserIdentityToken.class
    );

    Property<String> SECURE_CHANNEL_ID = new BasicProperty<>(
        QualifiedName.parse("0:SecureChannelId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );


    CompletableFuture<? extends PropertyType> clientSoftwareCertificates();

    CompletableFuture<SignedSoftwareCertificate[]> getClientSoftwareCertificates();

    CompletableFuture<StatusCode> setClientSoftwareCertificates(SignedSoftwareCertificate[] value);

    CompletableFuture<? extends PropertyType> userIdentityToken();

    CompletableFuture<UserIdentityToken> getUserIdentityToken();

    CompletableFuture<StatusCode> setUserIdentityToken(UserIdentityToken value);

    CompletableFuture<? extends PropertyType> secureChannelId();

    CompletableFuture<String> getSecureChannelId();

    CompletableFuture<StatusCode> setSecureChannelId(String value);


}
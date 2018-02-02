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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface AuditCreateSessionEventType extends AuditSessionEventType {
    QualifiedProperty<String> SECURE_CHANNEL_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecureChannelId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<ByteString> CLIENT_CERTIFICATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientCertificate",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    QualifiedProperty<String> CLIENT_CERTIFICATE_THUMBPRINT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientCertificateThumbprint",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Double> REVISED_SESSION_TIMEOUT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RevisedSessionTimeout",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    CompletableFuture<? extends PropertyType> getSecureChannelIdNode();

    CompletableFuture<String> getSecureChannelId();

    CompletableFuture<StatusCode> setSecureChannelId(String value);

    CompletableFuture<? extends PropertyType> getClientCertificateNode();

    CompletableFuture<ByteString> getClientCertificate();

    CompletableFuture<StatusCode> setClientCertificate(ByteString value);

    CompletableFuture<? extends PropertyType> getClientCertificateThumbprintNode();

    CompletableFuture<String> getClientCertificateThumbprint();

    CompletableFuture<StatusCode> setClientCertificateThumbprint(String value);

    CompletableFuture<? extends PropertyType> getRevisedSessionTimeoutNode();

    CompletableFuture<Double> getRevisedSessionTimeout();

    CompletableFuture<StatusCode> setRevisedSessionTimeout(Double value);
}

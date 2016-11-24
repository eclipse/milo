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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public interface AuditCreateSessionEventType extends AuditSessionEventType {

    Property<String> SECURE_CHANNEL_ID = new BasicProperty<>(
        QualifiedName.parse("0:SecureChannelId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<ByteString> CLIENT_CERTIFICATE = new BasicProperty<>(
        QualifiedName.parse("0:ClientCertificate"),
        NodeId.parse("ns=0;i=15"),
        -1,
        ByteString.class
    );

    Property<String> CLIENT_CERTIFICATE_THUMBPRINT = new BasicProperty<>(
        QualifiedName.parse("0:ClientCertificateThumbprint"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<Double> REVISED_SESSION_TIMEOUT = new BasicProperty<>(
        QualifiedName.parse("0:RevisedSessionTimeout"),
        NodeId.parse("ns=0;i=290"),
        -1,
        Double.class
    );


    CompletableFuture<? extends PropertyType> secureChannelId();

    CompletableFuture<String> getSecureChannelId();

    CompletableFuture<StatusCode> setSecureChannelId(String value);

    CompletableFuture<? extends PropertyType> clientCertificate();

    CompletableFuture<ByteString> getClientCertificate();

    CompletableFuture<StatusCode> setClientCertificate(ByteString value);

    CompletableFuture<? extends PropertyType> clientCertificateThumbprint();

    CompletableFuture<String> getClientCertificateThumbprint();

    CompletableFuture<StatusCode> setClientCertificateThumbprint(String value);

    CompletableFuture<? extends PropertyType> revisedSessionTimeout();

    CompletableFuture<Double> getRevisedSessionTimeout();

    CompletableFuture<StatusCode> setRevisedSessionTimeout(Double value);


}
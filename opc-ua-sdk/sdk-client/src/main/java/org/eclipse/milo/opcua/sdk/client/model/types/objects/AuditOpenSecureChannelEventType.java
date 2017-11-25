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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

public interface AuditOpenSecureChannelEventType extends AuditChannelEventType {
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

    QualifiedProperty<SecurityTokenRequestType> REQUEST_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RequestType",
        NodeId.parse("ns=0;i=315"),
        ValueRanks.Scalar,
        SecurityTokenRequestType.class
    );

    QualifiedProperty<String> SECURITY_POLICY_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecurityPolicyUri",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<MessageSecurityMode> SECURITY_MODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecurityMode",
        NodeId.parse("ns=0;i=302"),
        ValueRanks.Scalar,
        MessageSecurityMode.class
    );

    QualifiedProperty<Double> REQUESTED_LIFETIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RequestedLifetime",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    CompletableFuture<? extends PropertyType> getClientCertificateNode();

    CompletableFuture<ByteString> getClientCertificate();

    CompletableFuture<StatusCode> setClientCertificate(ByteString value);

    CompletableFuture<? extends PropertyType> getClientCertificateThumbprintNode();

    CompletableFuture<String> getClientCertificateThumbprint();

    CompletableFuture<StatusCode> setClientCertificateThumbprint(String value);

    CompletableFuture<? extends PropertyType> getRequestTypeNode();

    CompletableFuture<SecurityTokenRequestType> getRequestType();

    CompletableFuture<StatusCode> setRequestType(SecurityTokenRequestType value);

    CompletableFuture<? extends PropertyType> getSecurityPolicyUriNode();

    CompletableFuture<String> getSecurityPolicyUri();

    CompletableFuture<StatusCode> setSecurityPolicyUri(String value);

    CompletableFuture<? extends PropertyType> getSecurityModeNode();

    CompletableFuture<MessageSecurityMode> getSecurityMode();

    CompletableFuture<StatusCode> setSecurityMode(MessageSecurityMode value);

    CompletableFuture<? extends PropertyType> getRequestedLifetimeNode();

    CompletableFuture<Double> getRequestedLifetime();

    CompletableFuture<StatusCode> setRequestedLifetime(Double value);
}

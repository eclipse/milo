/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
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

    PropertyType getClientCertificateNode();

    ByteString getClientCertificate();

    void setClientCertificate(ByteString value);

    PropertyType getClientCertificateThumbprintNode();

    String getClientCertificateThumbprint();

    void setClientCertificateThumbprint(String value);

    PropertyType getRequestTypeNode();

    SecurityTokenRequestType getRequestType();

    void setRequestType(SecurityTokenRequestType value);

    PropertyType getSecurityPolicyUriNode();

    String getSecurityPolicyUri();

    void setSecurityPolicyUri(String value);

    PropertyType getSecurityModeNode();

    MessageSecurityMode getSecurityMode();

    void setSecurityMode(MessageSecurityMode value);

    PropertyType getRequestedLifetimeNode();

    Double getRequestedLifetime();

    void setRequestedLifetime(Double value);
}

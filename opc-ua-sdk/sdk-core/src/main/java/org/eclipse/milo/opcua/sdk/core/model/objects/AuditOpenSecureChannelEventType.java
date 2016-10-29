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

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

public interface AuditOpenSecureChannelEventType extends AuditChannelEventType {

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

    Property<SecurityTokenRequestType> REQUEST_TYPE = new BasicProperty<>(
        QualifiedName.parse("0:RequestType"),
        NodeId.parse("ns=0;i=315"),
        -1,
        SecurityTokenRequestType.class
    );

    Property<String> SECURITY_POLICY_URI = new BasicProperty<>(
        QualifiedName.parse("0:SecurityPolicyUri"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<MessageSecurityMode> SECURITY_MODE = new BasicProperty<>(
        QualifiedName.parse("0:SecurityMode"),
        NodeId.parse("ns=0;i=302"),
        -1,
        MessageSecurityMode.class
    );

    Property<Double> REQUESTED_LIFETIME = new BasicProperty<>(
        QualifiedName.parse("0:RequestedLifetime"),
        NodeId.parse("ns=0;i=290"),
        -1,
        Double.class
    );

    ByteString getClientCertificate();

    PropertyType getClientCertificateNode();

    void setClientCertificate(ByteString value);

    String getClientCertificateThumbprint();

    PropertyType getClientCertificateThumbprintNode();

    void setClientCertificateThumbprint(String value);

    SecurityTokenRequestType getRequestType();

    PropertyType getRequestTypeNode();

    void setRequestType(SecurityTokenRequestType value);

    String getSecurityPolicyUri();

    PropertyType getSecurityPolicyUriNode();

    void setSecurityPolicyUri(String value);

    MessageSecurityMode getSecurityMode();

    PropertyType getSecurityModeNode();

    void setSecurityMode(MessageSecurityMode value);

    Double getRequestedLifetime();

    PropertyType getRequestedLifetimeNode();

    void setRequestedLifetime(Double value);
}

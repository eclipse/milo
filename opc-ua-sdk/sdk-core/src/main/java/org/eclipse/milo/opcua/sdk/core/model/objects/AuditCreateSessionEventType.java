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

    String getSecureChannelId();

    PropertyType getSecureChannelIdNode();

    void setSecureChannelId(String value);

    ByteString getClientCertificate();

    PropertyType getClientCertificateNode();

    void setClientCertificate(ByteString value);

    String getClientCertificateThumbprint();

    PropertyType getClientCertificateThumbprintNode();

    void setClientCertificateThumbprint(String value);

    Double getRevisedSessionTimeout();

    PropertyType getRevisedSessionTimeoutNode();

    void setRevisedSessionTimeout(Double value);
}

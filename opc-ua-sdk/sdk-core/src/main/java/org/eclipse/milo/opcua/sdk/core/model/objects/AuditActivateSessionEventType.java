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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
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

    SignedSoftwareCertificate[] getClientSoftwareCertificates();

    PropertyType getClientSoftwareCertificatesNode();

    void setClientSoftwareCertificates(SignedSoftwareCertificate[] value);

    UserIdentityToken getUserIdentityToken();

    PropertyType getUserIdentityTokenNode();

    void setUserIdentityToken(UserIdentityToken value);

    String getSecureChannelId();

    PropertyType getSecureChannelIdNode();

    void setSecureChannelId(String value);
}

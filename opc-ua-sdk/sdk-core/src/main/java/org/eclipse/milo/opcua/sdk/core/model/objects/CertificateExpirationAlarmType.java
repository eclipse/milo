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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public interface CertificateExpirationAlarmType extends SystemOffNormalAlarmType {

    Property<DateTime> EXPIRATION_DATE = new BasicProperty<>(
        QualifiedName.parse("0:ExpirationDate"),
        NodeId.parse("ns=0;i=13"),
        -1,
        DateTime.class
    );

    Property<NodeId> CERTIFICATE_TYPE = new BasicProperty<>(
        QualifiedName.parse("0:CertificateType"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<ByteString> CERTIFICATE = new BasicProperty<>(
        QualifiedName.parse("0:Certificate"),
        NodeId.parse("ns=0;i=15"),
        -1,
        ByteString.class
    );

    DateTime getExpirationDate();

    PropertyType getExpirationDateNode();

    void setExpirationDate(DateTime value);

    NodeId getCertificateType();

    PropertyType getCertificateTypeNode();

    void setCertificateType(NodeId value);

    ByteString getCertificate();

    PropertyType getCertificateNode();

    void setCertificate(ByteString value);
}

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

public interface AuditCertificateDataMismatchEventType extends AuditCertificateEventType {

    Property<String> INVALID_HOSTNAME = new BasicProperty<>(
        QualifiedName.parse("0:InvalidHostname"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<String> INVALID_URI = new BasicProperty<>(
        QualifiedName.parse("0:InvalidUri"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    String getInvalidHostname();

    PropertyType getInvalidHostnameNode();

    void setInvalidHostname(String value);

    String getInvalidUri();

    PropertyType getInvalidUriNode();

    void setInvalidUri(String value);
}

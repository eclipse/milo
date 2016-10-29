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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public interface AuditEventType extends BaseEventType {

    Property<DateTime> ACTION_TIME_STAMP = new BasicProperty<>(
        QualifiedName.parse("0:ActionTimeStamp"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<Boolean> STATUS = new BasicProperty<>(
        QualifiedName.parse("0:Status"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<String> SERVER_ID = new BasicProperty<>(
        QualifiedName.parse("0:ServerId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<String> CLIENT_AUDIT_ENTRY_ID = new BasicProperty<>(
        QualifiedName.parse("0:ClientAuditEntryId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<String> CLIENT_USER_ID = new BasicProperty<>(
        QualifiedName.parse("0:ClientUserId"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    DateTime getActionTimeStamp();

    PropertyType getActionTimeStampNode();

    void setActionTimeStamp(DateTime value);

    Boolean getStatus();

    PropertyType getStatusNode();

    void setStatus(Boolean value);

    String getServerId();

    PropertyType getServerIdNode();

    void setServerId(String value);

    String getClientAuditEntryId();

    PropertyType getClientAuditEntryIdNode();

    void setClientAuditEntryId(String value);

    String getClientUserId();

    PropertyType getClientUserIdNode();

    void setClientUserId(String value);
}

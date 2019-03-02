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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditEventType extends BaseEventType {
    QualifiedProperty<String> CLIENT_USER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientUserId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> SERVER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> CLIENT_AUDIT_ENTRY_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientAuditEntryId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Boolean> STATUS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Status",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<DateTime> ACTION_TIME_STAMP = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ActionTimeStamp",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    PropertyType getClientUserIdNode();

    String getClientUserId();

    void setClientUserId(String value);

    PropertyType getServerIdNode();

    String getServerId();

    void setServerId(String value);

    PropertyType getClientAuditEntryIdNode();

    String getClientAuditEntryId();

    void setClientAuditEntryId(String value);

    PropertyType getStatusNode();

    Boolean getStatus();

    void setStatus(Boolean value);

    PropertyType getActionTimeStampNode();

    DateTime getActionTimeStamp();

    void setActionTimeStamp(DateTime value);
}

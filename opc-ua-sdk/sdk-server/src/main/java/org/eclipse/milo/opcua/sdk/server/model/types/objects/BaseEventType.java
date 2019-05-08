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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;

public interface BaseEventType extends BaseObjectType {
    QualifiedProperty<ByteString> EVENT_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventId",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    QualifiedProperty<NodeId> EVENT_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventType",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<NodeId> SOURCE_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceNode",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<String> SOURCE_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceName",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<DateTime> TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Time",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> RECEIVE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReceiveTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<TimeZoneDataType> LOCAL_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LocalTime",
        NodeId.parse("ns=0;i=8912"),
        ValueRanks.Scalar,
        TimeZoneDataType.class
    );

    QualifiedProperty<LocalizedText> MESSAGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Message",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<UShort> SEVERITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Severity",
        NodeId.parse("ns=0;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    PropertyType getEventIdNode();

    ByteString getEventId();

    void setEventId(ByteString value);

    PropertyType getEventTypeNode();

    NodeId getEventType();

    void setEventType(NodeId value);

    PropertyType getSourceNodeNode();

    NodeId getSourceNode();

    void setSourceNode(NodeId value);

    PropertyType getSourceNameNode();

    String getSourceName();

    void setSourceName(String value);

    PropertyType getTimeNode();

    DateTime getTime();

    void setTime(DateTime value);

    PropertyType getReceiveTimeNode();

    DateTime getReceiveTime();

    void setReceiveTime(DateTime value);

    PropertyType getLocalTimeNode();

    TimeZoneDataType getLocalTime();

    void setLocalTime(TimeZoneDataType value);

    PropertyType getMessageNode();

    LocalizedText getMessage();

    void setMessage(LocalizedText value);

    PropertyType getSeverityNode();

    UShort getSeverity();

    void setSeverity(UShort value);
}

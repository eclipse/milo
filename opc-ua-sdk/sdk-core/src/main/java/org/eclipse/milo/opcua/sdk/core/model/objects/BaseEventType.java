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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;

public interface BaseEventType extends BaseObjectType {

    Property<ByteString> EVENT_ID = new BasicProperty<>(
        QualifiedName.parse("0:EventId"),
        NodeId.parse("ns=0;i=15"),
        -1,
        ByteString.class
    );

    Property<NodeId> EVENT_TYPE = new BasicProperty<>(
        QualifiedName.parse("0:EventType"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<NodeId> SOURCE_NODE = new BasicProperty<>(
        QualifiedName.parse("0:SourceNode"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<String> SOURCE_NAME = new BasicProperty<>(
        QualifiedName.parse("0:SourceName"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<DateTime> TIME = new BasicProperty<>(
        QualifiedName.parse("0:Time"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<DateTime> RECEIVE_TIME = new BasicProperty<>(
        QualifiedName.parse("0:ReceiveTime"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<TimeZoneDataType> LOCAL_TIME = new BasicProperty<>(
        QualifiedName.parse("0:LocalTime"),
        NodeId.parse("ns=0;i=8912"),
        -1,
        TimeZoneDataType.class
    );

    Property<LocalizedText> MESSAGE = new BasicProperty<>(
        QualifiedName.parse("0:Message"),
        NodeId.parse("ns=0;i=21"),
        -1,
        LocalizedText.class
    );

    Property<UShort> SEVERITY = new BasicProperty<>(
        QualifiedName.parse("0:Severity"),
        NodeId.parse("ns=0;i=5"),
        -1,
        UShort.class
    );

    ByteString getEventId();

    PropertyType getEventIdNode();

    void setEventId(ByteString value);

    NodeId getEventType();

    PropertyType getEventTypeNode();

    void setEventType(NodeId value);

    NodeId getSourceNode();

    PropertyType getSourceNodeNode();

    void setSourceNode(NodeId value);

    String getSourceName();

    PropertyType getSourceNameNode();

    void setSourceName(String value);

    DateTime getTime();

    PropertyType getTimeNode();

    void setTime(DateTime value);

    DateTime getReceiveTime();

    PropertyType getReceiveTimeNode();

    void setReceiveTime(DateTime value);

    TimeZoneDataType getLocalTime();

    PropertyType getLocalTimeNode();

    void setLocalTime(TimeZoneDataType value);

    LocalizedText getMessage();

    PropertyType getMessageNode();

    void setMessage(LocalizedText value);

    UShort getSeverity();

    PropertyType getSeverityNode();

    void setSeverity(UShort value);
}

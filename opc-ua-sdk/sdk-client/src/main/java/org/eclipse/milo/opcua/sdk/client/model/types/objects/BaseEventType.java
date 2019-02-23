/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
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

    CompletableFuture<? extends PropertyType> getEventIdNode();

    CompletableFuture<ByteString> getEventId();

    CompletableFuture<StatusCode> setEventId(ByteString value);

    CompletableFuture<? extends PropertyType> getEventTypeNode();

    CompletableFuture<NodeId> getEventType();

    CompletableFuture<StatusCode> setEventType(NodeId value);

    CompletableFuture<? extends PropertyType> getSourceNodeNode();

    CompletableFuture<NodeId> getSourceNode();

    CompletableFuture<StatusCode> setSourceNode(NodeId value);

    CompletableFuture<? extends PropertyType> getSourceNameNode();

    CompletableFuture<String> getSourceName();

    CompletableFuture<StatusCode> setSourceName(String value);

    CompletableFuture<? extends PropertyType> getTimeNode();

    CompletableFuture<DateTime> getTime();

    CompletableFuture<StatusCode> setTime(DateTime value);

    CompletableFuture<? extends PropertyType> getReceiveTimeNode();

    CompletableFuture<DateTime> getReceiveTime();

    CompletableFuture<StatusCode> setReceiveTime(DateTime value);

    CompletableFuture<? extends PropertyType> getLocalTimeNode();

    CompletableFuture<TimeZoneDataType> getLocalTime();

    CompletableFuture<StatusCode> setLocalTime(TimeZoneDataType value);

    CompletableFuture<? extends PropertyType> getMessageNode();

    CompletableFuture<LocalizedText> getMessage();

    CompletableFuture<StatusCode> setMessage(LocalizedText value);

    CompletableFuture<? extends PropertyType> getSeverityNode();

    CompletableFuture<UShort> getSeverity();

    CompletableFuture<StatusCode> setSeverity(UShort value);
}

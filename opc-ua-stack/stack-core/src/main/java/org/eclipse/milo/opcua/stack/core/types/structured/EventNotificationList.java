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

package org.eclipse.milo.opcua.stack.core.types.structured;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("EventNotificationList")
public class EventNotificationList extends NotificationData {

    public static final NodeId TypeId = Identifiers.EventNotificationList;
    public static final NodeId BinaryEncodingId = Identifiers.EventNotificationList_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventNotificationList_Encoding_DefaultXml;

    protected final EventFieldList[] _events;

    public EventNotificationList() {
        super();
        this._events = null;
    }

    public EventNotificationList(EventFieldList[] _events) {
        super();
        this._events = _events;
    }

    @Nullable
    public EventFieldList[] getEvents() { return _events; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Events", _events)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EventNotificationList> {
        @Override
        public EventNotificationList decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            EventFieldList[] _events =
                reader.readArray(
                    () -> (EventFieldList) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EventFieldList", reader),
                    EventFieldList.class
                );

            return new EventNotificationList(_events);
        }

        @Override
        public void encode(SerializationContext context, EventNotificationList encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                encodable._events,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EventFieldList", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EventNotificationList> {
        @Override
        public EventNotificationList decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            EventFieldList[] _events =
                reader.readArray(
                    "Events",
                    f -> (EventFieldList) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EventFieldList", reader),
                    EventFieldList.class
                );

            return new EventNotificationList(_events);
        }

        @Override
        public void encode(SerializationContext context, EventNotificationList encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                "Events",
                encodable._events,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EventFieldList", e, writer)
            );
        }
    }

}

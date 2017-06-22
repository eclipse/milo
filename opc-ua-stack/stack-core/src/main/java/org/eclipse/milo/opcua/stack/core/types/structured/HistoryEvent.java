/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("HistoryEvent")
public class HistoryEvent implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryEvent;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryEvent_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryEvent_Encoding_DefaultXml;

    protected final HistoryEventFieldList[] _events;

    public HistoryEvent() {
        this._events = null;
    }

    public HistoryEvent(HistoryEventFieldList[] _events) {
        this._events = _events;
    }

    @Nullable
    public HistoryEventFieldList[] getEvents() { return _events; }

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

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryEvent> {
        @Override
        public HistoryEvent decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            HistoryEventFieldList[] _events =
                reader.readArray(
                    () -> (HistoryEventFieldList) context.decode(
                        HistoryEventFieldList.BinaryEncodingId, reader),
                    HistoryEventFieldList.class
                );

            return new HistoryEvent(_events);
        }

        @Override
        public void encode(SerializationContext context, HistoryEvent value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                value._events,
                e -> context.encode(HistoryEventFieldList.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryEvent> {
        @Override
        public HistoryEvent decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            HistoryEventFieldList[] _events =
                reader.readArray(
                    "Events",
                    f -> (HistoryEventFieldList) context.decode(
                        HistoryEventFieldList.XmlEncodingId, reader),
                    HistoryEventFieldList.class
                );

            return new HistoryEvent(_events);
        }

        @Override
        public void encode(SerializationContext context, HistoryEvent encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                "Events",
                encodable._events,
                (f, e) -> context.encode(HistoryEventFieldList.XmlEncodingId, e, writer)
            );
        }
    }

}

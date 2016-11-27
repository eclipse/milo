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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("EventFieldList")
public class EventFieldList implements UaStructure {

    public static final NodeId TypeId = Identifiers.EventFieldList;
    public static final NodeId BinaryEncodingId = Identifiers.EventFieldList_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventFieldList_Encoding_DefaultXml;

    protected final UInteger _clientHandle;
    protected final Variant[] _eventFields;

    public EventFieldList() {
        this._clientHandle = null;
        this._eventFields = null;
    }

    public EventFieldList(UInteger _clientHandle, Variant[] _eventFields) {
        this._clientHandle = _clientHandle;
        this._eventFields = _eventFields;
    }

    public UInteger getClientHandle() { return _clientHandle; }

    @Nullable
    public Variant[] getEventFields() { return _eventFields; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ClientHandle", _clientHandle)
            .add("EventFields", _eventFields)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EventFieldList> {
        @Override
        public EventFieldList decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _clientHandle = reader.readUInt32();
            Variant[] _eventFields = reader.readArray(reader::readVariant, Variant.class);

            return new EventFieldList(_clientHandle, _eventFields);
        }

        @Override
        public void encode(SerializationContext context, EventFieldList encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._clientHandle);
            writer.writeArray(encodable._eventFields, writer::writeVariant);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EventFieldList> {
        @Override
        public EventFieldList decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _clientHandle = reader.readUInt32("ClientHandle");
            Variant[] _eventFields = reader.readArray("EventFields", reader::readVariant, Variant.class);

            return new EventFieldList(_clientHandle, _eventFields);
        }

        @Override
        public void encode(SerializationContext context, EventFieldList encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("ClientHandle", encodable._clientHandle);
            writer.writeArray("EventFields", encodable._eventFields, writer::writeVariant);
        }
    }

}

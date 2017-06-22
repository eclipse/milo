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

@UaDataType("TimeZoneDataType")
public class TimeZoneDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.TimeZoneDataType;
    public static final NodeId BinaryEncodingId = Identifiers.TimeZoneDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TimeZoneDataType_Encoding_DefaultXml;

    protected final Short _offset;
    protected final Boolean _daylightSavingInOffset;

    public TimeZoneDataType() {
        this._offset = null;
        this._daylightSavingInOffset = null;
    }

    public TimeZoneDataType(Short _offset, Boolean _daylightSavingInOffset) {
        this._offset = _offset;
        this._daylightSavingInOffset = _daylightSavingInOffset;
    }

    public Short getOffset() { return _offset; }

    public Boolean getDaylightSavingInOffset() { return _daylightSavingInOffset; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Offset", _offset)
            .add("DaylightSavingInOffset", _daylightSavingInOffset)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<TimeZoneDataType> {
        @Override
        public TimeZoneDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Short _offset = reader.readInt16();
            Boolean _daylightSavingInOffset = reader.readBoolean();

            return new TimeZoneDataType(_offset, _daylightSavingInOffset);
        }

        @Override
        public void encode(SerializationContext context, TimeZoneDataType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeInt16(value._offset);
            writer.writeBoolean(value._daylightSavingInOffset);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<TimeZoneDataType> {
        @Override
        public TimeZoneDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Short _offset = reader.readInt16("Offset");
            Boolean _daylightSavingInOffset = reader.readBoolean("DaylightSavingInOffset");

            return new TimeZoneDataType(_offset, _daylightSavingInOffset);
        }

        @Override
        public void encode(SerializationContext context, TimeZoneDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeInt16("Offset", encodable._offset);
            writer.writeBoolean("DaylightSavingInOffset", encodable._daylightSavingInOffset);
        }
    }

}

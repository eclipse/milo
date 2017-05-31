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

@UaDataType("XVType")
public class XVType implements UaStructure {

    public static final NodeId TypeId = Identifiers.XVType;
    public static final NodeId BinaryEncodingId = Identifiers.XVType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.XVType_Encoding_DefaultXml;

    protected final Double _x;
    protected final Float _value;

    public XVType() {
        this._x = null;
        this._value = null;
    }

    public XVType(Double _x, Float _value) {
        this._x = _x;
        this._value = _value;
    }

    public Double getX() { return _x; }

    public Float getValue() { return _value; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("X", _x)
            .add("Value", _value)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<XVType> {
        @Override
        public XVType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Double _x = reader.readDouble();
            Float _value = reader.readFloat();

            return new XVType(_x, _value);
        }

        @Override
        public void encode(SerializationContext context, XVType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDouble(encodable._x);
            writer.writeFloat(encodable._value);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<XVType> {
        @Override
        public XVType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Double _x = reader.readDouble("X");
            Float _value = reader.readFloat("Value");

            return new XVType(_x, _value);
        }

        @Override
        public void encode(SerializationContext context, XVType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDouble("X", encodable._x);
            writer.writeFloat("Value", encodable._value);
        }
    }

}

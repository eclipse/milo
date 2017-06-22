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

@UaDataType("LiteralOperand")
public class LiteralOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.LiteralOperand;
    public static final NodeId BinaryEncodingId = Identifiers.LiteralOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.LiteralOperand_Encoding_DefaultXml;

    protected final Variant _value;

    public LiteralOperand() {
        super();
        this._value = null;
    }

    public LiteralOperand(Variant _value) {
        super();
        this._value = _value;
    }

    public Variant getValue() { return _value; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Value", _value)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<LiteralOperand> {
        @Override
        public LiteralOperand decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Variant _value = reader.readVariant();

            return new LiteralOperand(_value);
        }

        @Override
        public void encode(SerializationContext context, LiteralOperand value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeVariant(value._value);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<LiteralOperand> {
        @Override
        public LiteralOperand decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Variant _value = reader.readVariant("Value");

            return new LiteralOperand(_value);
        }

        @Override
        public void encode(SerializationContext context, LiteralOperand encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeVariant("Value", encodable._value);
        }
    }

}

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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("OptionSet")
public class OptionSet implements UaStructure {

    public static final NodeId TypeId = Identifiers.OptionSet;
    public static final NodeId BinaryEncodingId = Identifiers.OptionSet_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.OptionSet_Encoding_DefaultXml;

    protected final ByteString _value;
    protected final ByteString _validBits;

    public OptionSet() {
        this._value = null;
        this._validBits = null;
    }

    public OptionSet(ByteString _value, ByteString _validBits) {
        this._value = _value;
        this._validBits = _validBits;
    }

    public ByteString getValue() { return _value; }

    public ByteString getValidBits() { return _validBits; }

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
            .add("ValidBits", _validBits)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<OptionSet> {
        @Override
        public OptionSet decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ByteString _value = reader.readByteString();
            ByteString _validBits = reader.readByteString();

            return new OptionSet(_value, _validBits);
        }

        @Override
        public void encode(SerializationContext context, OptionSet encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeByteString(encodable._value);
            writer.writeByteString(encodable._validBits);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<OptionSet> {
        @Override
        public OptionSet decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ByteString _value = reader.readByteString("Value");
            ByteString _validBits = reader.readByteString("ValidBits");

            return new OptionSet(_value, _validBits);
        }

        @Override
        public void encode(SerializationContext context, OptionSet encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeByteString("Value", encodable._value);
            writer.writeByteString("ValidBits", encodable._validBits);
        }
    }

}

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

@UaDataType("SignatureData")
public class SignatureData implements UaStructure {

    public static final NodeId TypeId = Identifiers.SignatureData;
    public static final NodeId BinaryEncodingId = Identifiers.SignatureData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SignatureData_Encoding_DefaultXml;

    protected final String _algorithm;
    protected final ByteString _signature;

    public SignatureData() {
        this._algorithm = null;
        this._signature = null;
    }

    public SignatureData(String _algorithm, ByteString _signature) {
        this._algorithm = _algorithm;
        this._signature = _signature;
    }

    public String getAlgorithm() { return _algorithm; }

    public ByteString getSignature() { return _signature; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Algorithm", _algorithm)
            .add("Signature", _signature)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SignatureData> {
        @Override
        public SignatureData decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _algorithm = reader.readString();
            ByteString _signature = reader.readByteString();

            return new SignatureData(_algorithm, _signature);
        }

        @Override
        public void encode(SerializationContext context, SignatureData encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._algorithm);
            writer.writeByteString(encodable._signature);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SignatureData> {
        @Override
        public SignatureData decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _algorithm = reader.readString("Algorithm");
            ByteString _signature = reader.readByteString("Signature");

            return new SignatureData(_algorithm, _signature);
        }

        @Override
        public void encode(SerializationContext context, SignatureData encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("Algorithm", encodable._algorithm);
            writer.writeByteString("Signature", encodable._signature);
        }
    }

}

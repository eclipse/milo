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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class SignatureData implements UaStructure {

    public static final NodeId TypeId = Identifiers.SignatureData;
    public static final NodeId BinaryEncodingId = Identifiers.SignatureData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SignatureData_Encoding_DefaultXml;

    protected final String algorithm;
    protected final ByteString signature;

    public SignatureData() {
        this.algorithm = null;
        this.signature = null;
    }

    public SignatureData(String algorithm, ByteString signature) {
        this.algorithm = algorithm;
        this.signature = signature;
    }

    public String getAlgorithm() { return algorithm; }

    public ByteString getSignature() { return signature; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Algorithm", algorithm)
            .add("Signature", signature)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SignatureData> {

        @Override
        public Class<SignatureData> getType() {
            return SignatureData.class;
        }

        @Override
        public SignatureData decode(UaDecoder decoder) throws UaSerializationException {
            String algorithm = decoder.readString("Algorithm");
            ByteString signature = decoder.readByteString("Signature");

            return new SignatureData(algorithm, signature);
        }

        @Override
        public void encode(SignatureData value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("Algorithm", value.algorithm);
            encoder.writeByteString("Signature", value.signature);
        }
    }

}

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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("SignedSoftwareCertificate")
public class SignedSoftwareCertificate implements UaStructure {

    public static final NodeId TypeId = Identifiers.SignedSoftwareCertificate;
    public static final NodeId BinaryEncodingId = Identifiers.SignedSoftwareCertificate_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SignedSoftwareCertificate_Encoding_DefaultXml;

    protected final ByteString _certificateData;
    protected final ByteString _signature;

    public SignedSoftwareCertificate() {
        this._certificateData = null;
        this._signature = null;
    }

    public SignedSoftwareCertificate(ByteString _certificateData, ByteString _signature) {
        this._certificateData = _certificateData;
        this._signature = _signature;
    }

    public ByteString getCertificateData() { return _certificateData; }

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
            .add("CertificateData", _certificateData)
            .add("Signature", _signature)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SignedSoftwareCertificate> {
        @Override
        public SignedSoftwareCertificate decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ByteString _certificateData = reader.readByteString();
            ByteString _signature = reader.readByteString();

            return new SignedSoftwareCertificate(_certificateData, _signature);
        }

        @Override
        public void encode(SerializationContext context, SignedSoftwareCertificate value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeByteString(value._certificateData);
            writer.writeByteString(value._signature);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SignedSoftwareCertificate> {
        @Override
        public SignedSoftwareCertificate decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ByteString _certificateData = reader.readByteString("CertificateData");
            ByteString _signature = reader.readByteString("Signature");

            return new SignedSoftwareCertificate(_certificateData, _signature);
        }

        @Override
        public void encode(SerializationContext context, SignedSoftwareCertificate encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeByteString("CertificateData", encodable._certificateData);
            writer.writeByteString("Signature", encodable._signature);
        }
    }

}

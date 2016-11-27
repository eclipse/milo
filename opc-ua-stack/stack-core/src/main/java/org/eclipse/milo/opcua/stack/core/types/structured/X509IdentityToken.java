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

@UaDataType("X509IdentityToken")
public class X509IdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.X509IdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.X509IdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.X509IdentityToken_Encoding_DefaultXml;

    protected final ByteString _certificateData;

    public X509IdentityToken() {
        super(null);
        this._certificateData = null;
    }

    public X509IdentityToken(String _policyId, ByteString _certificateData) {
        super(_policyId);
        this._certificateData = _certificateData;
    }

    public ByteString getCertificateData() { return _certificateData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", _policyId)
            .add("CertificateData", _certificateData)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<X509IdentityToken> {
        @Override
        public X509IdentityToken decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString();
            ByteString _certificateData = reader.readByteString();

            return new X509IdentityToken(_policyId, _certificateData);
        }

        @Override
        public void encode(SerializationContext context, X509IdentityToken encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._policyId);
            writer.writeByteString(encodable._certificateData);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<X509IdentityToken> {
        @Override
        public X509IdentityToken decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString("PolicyId");
            ByteString _certificateData = reader.readByteString("CertificateData");

            return new X509IdentityToken(_policyId, _certificateData);
        }

        @Override
        public void encode(SerializationContext context, X509IdentityToken encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("PolicyId", encodable._policyId);
            writer.writeByteString("CertificateData", encodable._certificateData);
        }
    }

}

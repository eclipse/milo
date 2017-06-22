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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("IssuedIdentityToken")
public class IssuedIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.IssuedIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.IssuedIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.IssuedIdentityToken_Encoding_DefaultXml;

    protected final ByteString _tokenData;
    protected final String _encryptionAlgorithm;

    public IssuedIdentityToken() {
        super(null);
        this._tokenData = null;
        this._encryptionAlgorithm = null;
    }

    public IssuedIdentityToken(String _policyId, ByteString _tokenData, String _encryptionAlgorithm) {
        super(_policyId);
        this._tokenData = _tokenData;
        this._encryptionAlgorithm = _encryptionAlgorithm;
    }

    public ByteString getTokenData() { return _tokenData; }

    public String getEncryptionAlgorithm() { return _encryptionAlgorithm; }

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
            .add("TokenData", _tokenData)
            .add("EncryptionAlgorithm", _encryptionAlgorithm)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<IssuedIdentityToken> {
        @Override
        public IssuedIdentityToken decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString();
            ByteString _tokenData = reader.readByteString();
            String _encryptionAlgorithm = reader.readString();

            return new IssuedIdentityToken(_policyId, _tokenData, _encryptionAlgorithm);
        }

        @Override
        public void encode(SerializationContext context, IssuedIdentityToken value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(value._policyId);
            writer.writeByteString(value._tokenData);
            writer.writeString(value._encryptionAlgorithm);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<IssuedIdentityToken> {
        @Override
        public IssuedIdentityToken decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString("PolicyId");
            ByteString _tokenData = reader.readByteString("TokenData");
            String _encryptionAlgorithm = reader.readString("EncryptionAlgorithm");

            return new IssuedIdentityToken(_policyId, _tokenData, _encryptionAlgorithm);
        }

        @Override
        public void encode(SerializationContext context, IssuedIdentityToken encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("PolicyId", encodable._policyId);
            writer.writeByteString("TokenData", encodable._tokenData);
            writer.writeString("EncryptionAlgorithm", encodable._encryptionAlgorithm);
        }
    }

}

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
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class IssuedIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.IssuedIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.IssuedIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.IssuedIdentityToken_Encoding_DefaultXml;

    protected final ByteString tokenData;
    protected final String encryptionAlgorithm;

    public IssuedIdentityToken() {
        super(null);
        this.tokenData = null;
        this.encryptionAlgorithm = null;
    }

    public IssuedIdentityToken(String policyId, ByteString tokenData, String encryptionAlgorithm) {
        super(policyId);
        this.tokenData = tokenData;
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public ByteString getTokenData() { return tokenData; }

    public String getEncryptionAlgorithm() { return encryptionAlgorithm; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", policyId)
            .add("TokenData", tokenData)
            .add("EncryptionAlgorithm", encryptionAlgorithm)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<IssuedIdentityToken> {

        @Override
        public Class<IssuedIdentityToken> getType() {
            return IssuedIdentityToken.class;
        }

        @Override
        public IssuedIdentityToken decode(UaDecoder decoder) throws UaSerializationException {
            String policyId = decoder.readString("PolicyId");
            ByteString tokenData = decoder.readByteString("TokenData");
            String encryptionAlgorithm = decoder.readString("EncryptionAlgorithm");

            return new IssuedIdentityToken(policyId, tokenData, encryptionAlgorithm);
        }

        @Override
        public void encode(IssuedIdentityToken value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("PolicyId", value.policyId);
            encoder.writeByteString("TokenData", value.tokenData);
            encoder.writeString("EncryptionAlgorithm", value.encryptionAlgorithm);
        }
    }

}

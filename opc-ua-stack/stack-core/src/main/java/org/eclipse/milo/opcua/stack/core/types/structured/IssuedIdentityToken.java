/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class IssuedIdentityToken extends UserIdentityToken implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=938");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=939");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=940");

    private final ByteString tokenData;

    private final String encryptionAlgorithm;

    public IssuedIdentityToken(String policyId, ByteString tokenData, String encryptionAlgorithm) {
        super(policyId);
        this.tokenData = tokenData;
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public ByteString getTokenData() {
        return tokenData;
    }

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public static final class Codec extends GenericDataTypeCodec<IssuedIdentityToken> {
        @Override
        public Class<IssuedIdentityToken> getType() {
            return IssuedIdentityToken.class;
        }

        @Override
        public IssuedIdentityToken decode(SerializationContext context, UaDecoder decoder) {
            String policyId = decoder.readString("PolicyId");
            ByteString tokenData = decoder.readByteString("TokenData");
            String encryptionAlgorithm = decoder.readString("EncryptionAlgorithm");
            return new IssuedIdentityToken(policyId, tokenData, encryptionAlgorithm);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, IssuedIdentityToken value) {
            encoder.writeString("PolicyId", value.getPolicyId());
            encoder.writeByteString("TokenData", value.getTokenData());
            encoder.writeString("EncryptionAlgorithm", value.getEncryptionAlgorithm());
        }
    }
}

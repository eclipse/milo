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
public class X509IdentityToken extends UserIdentityToken implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=325");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=326");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=327");

    private final ByteString certificateData;

    public X509IdentityToken(String policyId, ByteString certificateData) {
        super(policyId);
        this.certificateData = certificateData;
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

    public ByteString getCertificateData() {
        return certificateData;
    }

    public static final class Codec extends GenericDataTypeCodec<X509IdentityToken> {
        @Override
        public Class<X509IdentityToken> getType() {
            return X509IdentityToken.class;
        }

        @Override
        public X509IdentityToken decode(SerializationContext context, UaDecoder decoder) {
            String policyId = decoder.readString("PolicyId");
            ByteString certificateData = decoder.readByteString("CertificateData");
            return new X509IdentityToken(policyId, certificateData);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, X509IdentityToken value) {
            encoder.writeString("PolicyId", value.getPolicyId());
            encoder.writeByteString("CertificateData", value.getCertificateData());
        }
    }
}

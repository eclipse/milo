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
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SignedSoftwareCertificate extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=344");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=345");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=346");

    private final ByteString certificateData;

    private final ByteString signature;

    public SignedSoftwareCertificate(ByteString certificateData, ByteString signature) {
        this.certificateData = certificateData;
        this.signature = signature;
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

    public ByteString getSignature() {
        return signature;
    }

    public static final class Codec extends GenericDataTypeCodec<SignedSoftwareCertificate> {
        @Override
        public Class<SignedSoftwareCertificate> getType() {
            return SignedSoftwareCertificate.class;
        }

        @Override
        public SignedSoftwareCertificate decode(SerializationContext context, UaDecoder decoder) {
            ByteString certificateData = decoder.readByteString("CertificateData");
            ByteString signature = decoder.readByteString("Signature");
            return new SignedSoftwareCertificate(certificateData, signature);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SignedSoftwareCertificate value) {
            encoder.writeByteString("CertificateData", value.getCertificateData());
            encoder.writeByteString("Signature", value.getSignature());
        }
    }
}

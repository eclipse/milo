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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class TrustListDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12554");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12676");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12680");

    private final UInteger specifiedLists;

    private final ByteString[] trustedCertificates;

    private final ByteString[] trustedCrls;

    private final ByteString[] issuerCertificates;

    private final ByteString[] issuerCrls;

    public TrustListDataType(UInteger specifiedLists, ByteString[] trustedCertificates,
                             ByteString[] trustedCrls, ByteString[] issuerCertificates, ByteString[] issuerCrls) {
        this.specifiedLists = specifiedLists;
        this.trustedCertificates = trustedCertificates;
        this.trustedCrls = trustedCrls;
        this.issuerCertificates = issuerCertificates;
        this.issuerCrls = issuerCrls;
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

    public UInteger getSpecifiedLists() {
        return specifiedLists;
    }

    public ByteString[] getTrustedCertificates() {
        return trustedCertificates;
    }

    public ByteString[] getTrustedCrls() {
        return trustedCrls;
    }

    public ByteString[] getIssuerCertificates() {
        return issuerCertificates;
    }

    public ByteString[] getIssuerCrls() {
        return issuerCrls;
    }

    public static final class Codec extends GenericDataTypeCodec<TrustListDataType> {
        @Override
        public Class<TrustListDataType> getType() {
            return TrustListDataType.class;
        }

        @Override
        public TrustListDataType decode(SerializationContext context, UaDecoder decoder) {
            UInteger specifiedLists = decoder.readUInt32("SpecifiedLists");
            ByteString[] trustedCertificates = decoder.readByteStringArray("TrustedCertificates");
            ByteString[] trustedCrls = decoder.readByteStringArray("TrustedCrls");
            ByteString[] issuerCertificates = decoder.readByteStringArray("IssuerCertificates");
            ByteString[] issuerCrls = decoder.readByteStringArray("IssuerCrls");
            return new TrustListDataType(specifiedLists, trustedCertificates, trustedCrls, issuerCertificates, issuerCrls);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, TrustListDataType value) {
            encoder.writeUInt32("SpecifiedLists", value.getSpecifiedLists());
            encoder.writeByteStringArray("TrustedCertificates", value.getTrustedCertificates());
            encoder.writeByteStringArray("TrustedCrls", value.getTrustedCrls());
            encoder.writeByteStringArray("IssuerCertificates", value.getIssuerCertificates());
            encoder.writeByteStringArray("IssuerCrls", value.getIssuerCrls());
        }
    }
}

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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class TrustListDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.TrustListDataType;
    public static final NodeId BinaryEncodingId = Identifiers.TrustListDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TrustListDataType_Encoding_DefaultXml;

    protected final UInteger specifiedLists;
    protected final ByteString[] trustedCertificates;
    protected final ByteString[] trustedCrls;
    protected final ByteString[] issuerCertificates;
    protected final ByteString[] issuerCrls;

    public TrustListDataType() {
        this.specifiedLists = null;
        this.trustedCertificates = null;
        this.trustedCrls = null;
        this.issuerCertificates = null;
        this.issuerCrls = null;
    }

    public TrustListDataType(UInteger specifiedLists, ByteString[] trustedCertificates, ByteString[] trustedCrls, ByteString[] issuerCertificates, ByteString[] issuerCrls) {
        this.specifiedLists = specifiedLists;
        this.trustedCertificates = trustedCertificates;
        this.trustedCrls = trustedCrls;
        this.issuerCertificates = issuerCertificates;
        this.issuerCrls = issuerCrls;
    }

    public UInteger getSpecifiedLists() { return specifiedLists; }

    @Nullable
    public ByteString[] getTrustedCertificates() { return trustedCertificates; }

    @Nullable
    public ByteString[] getTrustedCrls() { return trustedCrls; }

    @Nullable
    public ByteString[] getIssuerCertificates() { return issuerCertificates; }

    @Nullable
    public ByteString[] getIssuerCrls() { return issuerCrls; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SpecifiedLists", specifiedLists)
            .add("TrustedCertificates", trustedCertificates)
            .add("TrustedCrls", trustedCrls)
            .add("IssuerCertificates", issuerCertificates)
            .add("IssuerCrls", issuerCrls)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<TrustListDataType> {

        @Override
        public Class<TrustListDataType> getType() {
            return TrustListDataType.class;
        }

        @Override
        public TrustListDataType decode(UaDecoder decoder) throws UaSerializationException {
            UInteger specifiedLists = decoder.readUInt32("SpecifiedLists");
            ByteString[] trustedCertificates = decoder.readArray("TrustedCertificates", decoder::readByteString, ByteString.class);
            ByteString[] trustedCrls = decoder.readArray("TrustedCrls", decoder::readByteString, ByteString.class);
            ByteString[] issuerCertificates = decoder.readArray("IssuerCertificates", decoder::readByteString, ByteString.class);
            ByteString[] issuerCrls = decoder.readArray("IssuerCrls", decoder::readByteString, ByteString.class);

            return new TrustListDataType(specifiedLists, trustedCertificates, trustedCrls, issuerCertificates, issuerCrls);
        }

        @Override
        public void encode(TrustListDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SpecifiedLists", value.specifiedLists);
            encoder.writeArray("TrustedCertificates", value.trustedCertificates, encoder::writeByteString);
            encoder.writeArray("TrustedCrls", value.trustedCrls, encoder::writeByteString);
            encoder.writeArray("IssuerCertificates", value.issuerCertificates, encoder::writeByteString);
            encoder.writeArray("IssuerCrls", value.issuerCrls, encoder::writeByteString);
        }
    }

}

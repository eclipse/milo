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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("TrustListDataType")
public class TrustListDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.TrustListDataType;
    public static final NodeId BinaryEncodingId = Identifiers.TrustListDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TrustListDataType_Encoding_DefaultXml;

    protected final UInteger _specifiedLists;
    protected final ByteString[] _trustedCertificates;
    protected final ByteString[] _trustedCrls;
    protected final ByteString[] _issuerCertificates;
    protected final ByteString[] _issuerCrls;

    public TrustListDataType() {
        this._specifiedLists = null;
        this._trustedCertificates = null;
        this._trustedCrls = null;
        this._issuerCertificates = null;
        this._issuerCrls = null;
    }

    public TrustListDataType(UInteger _specifiedLists, ByteString[] _trustedCertificates, ByteString[] _trustedCrls, ByteString[] _issuerCertificates, ByteString[] _issuerCrls) {
        this._specifiedLists = _specifiedLists;
        this._trustedCertificates = _trustedCertificates;
        this._trustedCrls = _trustedCrls;
        this._issuerCertificates = _issuerCertificates;
        this._issuerCrls = _issuerCrls;
    }

    public UInteger getSpecifiedLists() { return _specifiedLists; }

    @Nullable
    public ByteString[] getTrustedCertificates() { return _trustedCertificates; }

    @Nullable
    public ByteString[] getTrustedCrls() { return _trustedCrls; }

    @Nullable
    public ByteString[] getIssuerCertificates() { return _issuerCertificates; }

    @Nullable
    public ByteString[] getIssuerCrls() { return _issuerCrls; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SpecifiedLists", _specifiedLists)
            .add("TrustedCertificates", _trustedCertificates)
            .add("TrustedCrls", _trustedCrls)
            .add("IssuerCertificates", _issuerCertificates)
            .add("IssuerCrls", _issuerCrls)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<TrustListDataType> {
        @Override
        public TrustListDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _specifiedLists = reader.readUInt32();
            ByteString[] _trustedCertificates = reader.readArray(reader::readByteString, ByteString.class);
            ByteString[] _trustedCrls = reader.readArray(reader::readByteString, ByteString.class);
            ByteString[] _issuerCertificates = reader.readArray(reader::readByteString, ByteString.class);
            ByteString[] _issuerCrls = reader.readArray(reader::readByteString, ByteString.class);

            return new TrustListDataType(_specifiedLists, _trustedCertificates, _trustedCrls, _issuerCertificates, _issuerCrls);
        }

        @Override
        public void encode(SerializationContext context, TrustListDataType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(value._specifiedLists);
            writer.writeArray(value._trustedCertificates, writer::writeByteString);
            writer.writeArray(value._trustedCrls, writer::writeByteString);
            writer.writeArray(value._issuerCertificates, writer::writeByteString);
            writer.writeArray(value._issuerCrls, writer::writeByteString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<TrustListDataType> {
        @Override
        public TrustListDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _specifiedLists = reader.readUInt32("SpecifiedLists");
            ByteString[] _trustedCertificates = reader.readArray("TrustedCertificates", reader::readByteString, ByteString.class);
            ByteString[] _trustedCrls = reader.readArray("TrustedCrls", reader::readByteString, ByteString.class);
            ByteString[] _issuerCertificates = reader.readArray("IssuerCertificates", reader::readByteString, ByteString.class);
            ByteString[] _issuerCrls = reader.readArray("IssuerCrls", reader::readByteString, ByteString.class);

            return new TrustListDataType(_specifiedLists, _trustedCertificates, _trustedCrls, _issuerCertificates, _issuerCrls);
        }

        @Override
        public void encode(SerializationContext context, TrustListDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("SpecifiedLists", encodable._specifiedLists);
            writer.writeArray("TrustedCertificates", encodable._trustedCertificates, writer::writeByteString);
            writer.writeArray("TrustedCrls", encodable._trustedCrls, writer::writeByteString);
            writer.writeArray("IssuerCertificates", encodable._issuerCertificates, writer::writeByteString);
            writer.writeArray("IssuerCrls", encodable._issuerCrls, writer::writeByteString);
        }
    }

}

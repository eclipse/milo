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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

    public ByteString[] getTrustedCertificates() { return _trustedCertificates; }

    public ByteString[] getTrustedCrls() { return _trustedCrls; }

    public ByteString[] getIssuerCertificates() { return _issuerCertificates; }

    public ByteString[] getIssuerCrls() { return _issuerCrls; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(TrustListDataType trustListDataType, UaEncoder encoder) {
        encoder.encodeUInt32("SpecifiedLists", trustListDataType._specifiedLists);
        encoder.encodeArray("TrustedCertificates", trustListDataType._trustedCertificates, encoder::encodeByteString);
        encoder.encodeArray("TrustedCrls", trustListDataType._trustedCrls, encoder::encodeByteString);
        encoder.encodeArray("IssuerCertificates", trustListDataType._issuerCertificates, encoder::encodeByteString);
        encoder.encodeArray("IssuerCrls", trustListDataType._issuerCrls, encoder::encodeByteString);
    }

    public static TrustListDataType decode(UaDecoder decoder) {
        UInteger _specifiedLists = decoder.decodeUInt32("SpecifiedLists");
        ByteString[] _trustedCertificates = decoder.decodeArray("TrustedCertificates", decoder::decodeByteString, ByteString.class);
        ByteString[] _trustedCrls = decoder.decodeArray("TrustedCrls", decoder::decodeByteString, ByteString.class);
        ByteString[] _issuerCertificates = decoder.decodeArray("IssuerCertificates", decoder::decodeByteString, ByteString.class);
        ByteString[] _issuerCrls = decoder.decodeArray("IssuerCrls", decoder::decodeByteString, ByteString.class);

        return new TrustListDataType(_specifiedLists, _trustedCertificates, _trustedCrls, _issuerCertificates, _issuerCrls);
    }

    static {
        DelegateRegistry.registerEncoder(TrustListDataType::encode, TrustListDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(TrustListDataType::decode, TrustListDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}

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

@UaDataType("SignedSoftwareCertificate")
public class SignedSoftwareCertificate implements UaStructure {

    public static final NodeId TypeId = Identifiers.SignedSoftwareCertificate;
    public static final NodeId BinaryEncodingId = Identifiers.SignedSoftwareCertificate_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SignedSoftwareCertificate_Encoding_DefaultXml;

    protected final ByteString _certificateData;
    protected final ByteString _signature;

    public SignedSoftwareCertificate() {
        this._certificateData = null;
        this._signature = null;
    }

    public SignedSoftwareCertificate(ByteString _certificateData, ByteString _signature) {
        this._certificateData = _certificateData;
        this._signature = _signature;
    }

    public ByteString getCertificateData() { return _certificateData; }

    public ByteString getSignature() { return _signature; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(SignedSoftwareCertificate signedSoftwareCertificate, UaEncoder encoder) {
        encoder.encodeByteString("CertificateData", signedSoftwareCertificate._certificateData);
        encoder.encodeByteString("Signature", signedSoftwareCertificate._signature);
    }

    public static SignedSoftwareCertificate decode(UaDecoder decoder) {
        ByteString _certificateData = decoder.decodeByteString("CertificateData");
        ByteString _signature = decoder.decodeByteString("Signature");

        return new SignedSoftwareCertificate(_certificateData, _signature);
    }

    static {
        DelegateRegistry.registerEncoder(SignedSoftwareCertificate::encode, SignedSoftwareCertificate.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SignedSoftwareCertificate::decode, SignedSoftwareCertificate.class, BinaryEncodingId, XmlEncodingId);
    }

}

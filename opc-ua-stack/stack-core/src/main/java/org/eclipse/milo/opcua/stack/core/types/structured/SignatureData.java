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

@UaDataType("SignatureData")
public class SignatureData implements UaStructure {

    public static final NodeId TypeId = Identifiers.SignatureData;
    public static final NodeId BinaryEncodingId = Identifiers.SignatureData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SignatureData_Encoding_DefaultXml;

    protected final String _algorithm;
    protected final ByteString _signature;

    public SignatureData() {
        this._algorithm = null;
        this._signature = null;
    }

    public SignatureData(String _algorithm, ByteString _signature) {
        this._algorithm = _algorithm;
        this._signature = _signature;
    }

    public String getAlgorithm() { return _algorithm; }

    public ByteString getSignature() { return _signature; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(SignatureData signatureData, UaEncoder encoder) {
        encoder.encodeString("Algorithm", signatureData._algorithm);
        encoder.encodeByteString("Signature", signatureData._signature);
    }

    public static SignatureData decode(UaDecoder decoder) {
        String _algorithm = decoder.decodeString("Algorithm");
        ByteString _signature = decoder.decodeByteString("Signature");

        return new SignatureData(_algorithm, _signature);
    }

    static {
        DelegateRegistry.registerEncoder(SignatureData::encode, SignatureData.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SignatureData::decode, SignatureData.class, BinaryEncodingId, XmlEncodingId);
    }

}

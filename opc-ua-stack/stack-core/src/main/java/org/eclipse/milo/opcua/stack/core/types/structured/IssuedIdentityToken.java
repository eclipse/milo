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
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("IssuedIdentityToken")
public class IssuedIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.IssuedIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.IssuedIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.IssuedIdentityToken_Encoding_DefaultXml;

    protected final ByteString _tokenData;
    protected final String _encryptionAlgorithm;

    public IssuedIdentityToken() {
        super(null);
        this._tokenData = null;
        this._encryptionAlgorithm = null;
    }

    public IssuedIdentityToken(String _policyId, ByteString _tokenData, String _encryptionAlgorithm) {
        super(_policyId);
        this._tokenData = _tokenData;
        this._encryptionAlgorithm = _encryptionAlgorithm;
    }

    public ByteString getTokenData() { return _tokenData; }

    public String getEncryptionAlgorithm() { return _encryptionAlgorithm; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(IssuedIdentityToken issuedIdentityToken, UaEncoder encoder) {
        encoder.encodeString("PolicyId", issuedIdentityToken._policyId);
        encoder.encodeByteString("TokenData", issuedIdentityToken._tokenData);
        encoder.encodeString("EncryptionAlgorithm", issuedIdentityToken._encryptionAlgorithm);
    }

    public static IssuedIdentityToken decode(UaDecoder decoder) {
        String _policyId = decoder.decodeString("PolicyId");
        ByteString _tokenData = decoder.decodeByteString("TokenData");
        String _encryptionAlgorithm = decoder.decodeString("EncryptionAlgorithm");

        return new IssuedIdentityToken(_policyId, _tokenData, _encryptionAlgorithm);
    }

    static {
        DelegateRegistry.registerEncoder(IssuedIdentityToken::encode, IssuedIdentityToken.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(IssuedIdentityToken::decode, IssuedIdentityToken.class, BinaryEncodingId, XmlEncodingId);
    }

}

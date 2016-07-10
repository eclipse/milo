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

@UaDataType("UserNameIdentityToken")
public class UserNameIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.UserNameIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.UserNameIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UserNameIdentityToken_Encoding_DefaultXml;

    protected final String _userName;
    protected final ByteString _password;
    protected final String _encryptionAlgorithm;

    public UserNameIdentityToken() {
        super(null);
        this._userName = null;
        this._password = null;
        this._encryptionAlgorithm = null;
    }

    public UserNameIdentityToken(String _policyId, String _userName, ByteString _password, String _encryptionAlgorithm) {
        super(_policyId);
        this._userName = _userName;
        this._password = _password;
        this._encryptionAlgorithm = _encryptionAlgorithm;
    }

    public String getUserName() { return _userName; }

    public ByteString getPassword() { return _password; }

    public String getEncryptionAlgorithm() { return _encryptionAlgorithm; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(UserNameIdentityToken userNameIdentityToken, UaEncoder encoder) {
        encoder.encodeString("PolicyId", userNameIdentityToken._policyId);
        encoder.encodeString("UserName", userNameIdentityToken._userName);
        encoder.encodeByteString("Password", userNameIdentityToken._password);
        encoder.encodeString("EncryptionAlgorithm", userNameIdentityToken._encryptionAlgorithm);
    }

    public static UserNameIdentityToken decode(UaDecoder decoder) {
        String _policyId = decoder.decodeString("PolicyId");
        String _userName = decoder.decodeString("UserName");
        ByteString _password = decoder.decodeByteString("Password");
        String _encryptionAlgorithm = decoder.decodeString("EncryptionAlgorithm");

        return new UserNameIdentityToken(_policyId, _userName, _password, _encryptionAlgorithm);
    }

    static {
        DelegateRegistry.registerEncoder(UserNameIdentityToken::encode, UserNameIdentityToken.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(UserNameIdentityToken::decode, UserNameIdentityToken.class, BinaryEncodingId, XmlEncodingId);
    }

}

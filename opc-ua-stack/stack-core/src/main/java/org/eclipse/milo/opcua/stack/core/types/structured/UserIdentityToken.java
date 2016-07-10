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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("UserIdentityToken")
public class UserIdentityToken implements UaStructure {

    public static final NodeId TypeId = Identifiers.UserIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.UserIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UserIdentityToken_Encoding_DefaultXml;

    protected final String _policyId;

    public UserIdentityToken() {
        this._policyId = null;
    }

    public UserIdentityToken(String _policyId) {
        this._policyId = _policyId;
    }

    public String getPolicyId() { return _policyId; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(UserIdentityToken userIdentityToken, UaEncoder encoder) {
        encoder.encodeString("PolicyId", userIdentityToken._policyId);
    }

    public static UserIdentityToken decode(UaDecoder decoder) {
        String _policyId = decoder.decodeString("PolicyId");

        return new UserIdentityToken(_policyId);
    }

    static {
        DelegateRegistry.registerEncoder(UserIdentityToken::encode, UserIdentityToken.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(UserIdentityToken::decode, UserIdentityToken.class, BinaryEncodingId, XmlEncodingId);
    }

}

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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("AnonymousIdentityToken")
public class AnonymousIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.AnonymousIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.AnonymousIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AnonymousIdentityToken_Encoding_DefaultXml;


    public AnonymousIdentityToken() {
        super(null);
    }

    public AnonymousIdentityToken(String _policyId) {
        super(_policyId);
    }


    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(AnonymousIdentityToken anonymousIdentityToken, UaEncoder encoder) {
        encoder.encodeString("PolicyId", anonymousIdentityToken._policyId);
    }

    public static AnonymousIdentityToken decode(UaDecoder decoder) {
        String _policyId = decoder.decodeString("PolicyId");

        return new AnonymousIdentityToken(_policyId);
    }

    static {
        DelegateRegistry.registerEncoder(AnonymousIdentityToken::encode, AnonymousIdentityToken.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(AnonymousIdentityToken::decode, AnonymousIdentityToken.class, BinaryEncodingId, XmlEncodingId);
    }

}

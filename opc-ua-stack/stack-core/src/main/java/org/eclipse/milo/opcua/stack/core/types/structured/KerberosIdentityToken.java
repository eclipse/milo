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

@UaDataType("KerberosIdentityToken")
public class KerberosIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.KerberosIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.KerberosIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.KerberosIdentityToken_Encoding_DefaultXml;

    protected final ByteString _ticketData;

    public KerberosIdentityToken() {
        super(null);
        this._ticketData = null;
    }

    public KerberosIdentityToken(String _policyId, ByteString _ticketData) {
        super(_policyId);
        this._ticketData = _ticketData;
    }

    public ByteString getTicketData() { return _ticketData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(KerberosIdentityToken kerberosIdentityToken, UaEncoder encoder) {
        encoder.encodeString("PolicyId", kerberosIdentityToken._policyId);
        encoder.encodeByteString("TicketData", kerberosIdentityToken._ticketData);
    }

    public static KerberosIdentityToken decode(UaDecoder decoder) {
        String _policyId = decoder.decodeString("PolicyId");
        ByteString _ticketData = decoder.decodeByteString("TicketData");

        return new KerberosIdentityToken(_policyId, _ticketData);
    }

    static {
        DelegateRegistry.registerEncoder(KerberosIdentityToken::encode, KerberosIdentityToken.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(KerberosIdentityToken::decode, KerberosIdentityToken.class, BinaryEncodingId, XmlEncodingId);
    }

}

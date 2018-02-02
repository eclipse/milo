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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class KerberosIdentityToken extends UserIdentityToken {

    public static final NodeId TypeId = Identifiers.KerberosIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.KerberosIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.KerberosIdentityToken_Encoding_DefaultXml;

    protected final ByteString ticketData;

    public KerberosIdentityToken() {
        super(null);
        this.ticketData = null;
    }

    public KerberosIdentityToken(String policyId, ByteString ticketData) {
        super(policyId);
        this.ticketData = ticketData;
    }

    public ByteString getTicketData() { return ticketData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", policyId)
            .add("TicketData", ticketData)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<KerberosIdentityToken> {

        @Override
        public Class<KerberosIdentityToken> getType() {
            return KerberosIdentityToken.class;
        }

        @Override
        public KerberosIdentityToken decode(UaDecoder decoder) throws UaSerializationException {
            String policyId = decoder.readString("PolicyId");
            ByteString ticketData = decoder.readByteString("TicketData");

            return new KerberosIdentityToken(policyId, ticketData);
        }

        @Override
        public void encode(KerberosIdentityToken value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("PolicyId", value.policyId);
            encoder.writeByteString("TicketData", value.ticketData);
        }
    }

}

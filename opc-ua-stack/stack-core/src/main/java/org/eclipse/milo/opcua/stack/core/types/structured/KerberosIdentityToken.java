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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", _policyId)
            .add("TicketData", _ticketData)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<KerberosIdentityToken> {
        @Override
        public KerberosIdentityToken decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString();
            ByteString _ticketData = reader.readByteString();

            return new KerberosIdentityToken(_policyId, _ticketData);
        }

        @Override
        public void encode(SerializationContext context, KerberosIdentityToken encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._policyId);
            writer.writeByteString(encodable._ticketData);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<KerberosIdentityToken> {
        @Override
        public KerberosIdentityToken decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString("PolicyId");
            ByteString _ticketData = reader.readByteString("TicketData");

            return new KerberosIdentityToken(_policyId, _ticketData);
        }

        @Override
        public void encode(SerializationContext context, KerberosIdentityToken encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("PolicyId", encodable._policyId);
            writer.writeByteString("TicketData", encodable._ticketData);
        }
    }

}

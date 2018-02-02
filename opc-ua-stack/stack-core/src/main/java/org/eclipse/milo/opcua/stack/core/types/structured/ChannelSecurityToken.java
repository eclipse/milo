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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ChannelSecurityToken implements UaStructure {

    public static final NodeId TypeId = Identifiers.ChannelSecurityToken;
    public static final NodeId BinaryEncodingId = Identifiers.ChannelSecurityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ChannelSecurityToken_Encoding_DefaultXml;

    protected final UInteger channelId;
    protected final UInteger tokenId;
    protected final DateTime createdAt;
    protected final UInteger revisedLifetime;

    public ChannelSecurityToken() {
        this.channelId = null;
        this.tokenId = null;
        this.createdAt = null;
        this.revisedLifetime = null;
    }

    public ChannelSecurityToken(UInteger channelId, UInteger tokenId, DateTime createdAt, UInteger revisedLifetime) {
        this.channelId = channelId;
        this.tokenId = tokenId;
        this.createdAt = createdAt;
        this.revisedLifetime = revisedLifetime;
    }

    public UInteger getChannelId() { return channelId; }

    public UInteger getTokenId() { return tokenId; }

    public DateTime getCreatedAt() { return createdAt; }

    public UInteger getRevisedLifetime() { return revisedLifetime; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ChannelId", channelId)
            .add("TokenId", tokenId)
            .add("CreatedAt", createdAt)
            .add("RevisedLifetime", revisedLifetime)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ChannelSecurityToken> {

        @Override
        public Class<ChannelSecurityToken> getType() {
            return ChannelSecurityToken.class;
        }

        @Override
        public ChannelSecurityToken decode(UaDecoder decoder) throws UaSerializationException {
            UInteger channelId = decoder.readUInt32("ChannelId");
            UInteger tokenId = decoder.readUInt32("TokenId");
            DateTime createdAt = decoder.readDateTime("CreatedAt");
            UInteger revisedLifetime = decoder.readUInt32("RevisedLifetime");

            return new ChannelSecurityToken(channelId, tokenId, createdAt, revisedLifetime);
        }

        @Override
        public void encode(ChannelSecurityToken value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("ChannelId", value.channelId);
            encoder.writeUInt32("TokenId", value.tokenId);
            encoder.writeDateTime("CreatedAt", value.createdAt);
            encoder.writeUInt32("RevisedLifetime", value.revisedLifetime);
        }
    }

}

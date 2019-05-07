/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ChannelSecurityToken extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=441");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=443");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=442");

    private final UInteger channelId;

    private final UInteger tokenId;

    private final DateTime createdAt;

    private final UInteger revisedLifetime;

    public ChannelSecurityToken(UInteger channelId, UInteger tokenId, DateTime createdAt,
                                UInteger revisedLifetime) {
        this.channelId = channelId;
        this.tokenId = tokenId;
        this.createdAt = createdAt;
        this.revisedLifetime = revisedLifetime;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public UInteger getChannelId() {
        return channelId;
    }

    public UInteger getTokenId() {
        return tokenId;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public UInteger getRevisedLifetime() {
        return revisedLifetime;
    }

    public static final class Codec extends GenericDataTypeCodec<ChannelSecurityToken> {
        @Override
        public Class<ChannelSecurityToken> getType() {
            return ChannelSecurityToken.class;
        }

        @Override
        public ChannelSecurityToken decode(SerializationContext context, UaDecoder decoder) {
            UInteger channelId = decoder.readUInt32("ChannelId");
            UInteger tokenId = decoder.readUInt32("TokenId");
            DateTime createdAt = decoder.readDateTime("CreatedAt");
            UInteger revisedLifetime = decoder.readUInt32("RevisedLifetime");
            return new ChannelSecurityToken(channelId, tokenId, createdAt, revisedLifetime);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ChannelSecurityToken value) {
            encoder.writeUInt32("ChannelId", value.getChannelId());
            encoder.writeUInt32("TokenId", value.getTokenId());
            encoder.writeDateTime("CreatedAt", value.getCreatedAt());
            encoder.writeUInt32("RevisedLifetime", value.getRevisedLifetime());
        }
    }
}

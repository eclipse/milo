/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.5.2/#5.5.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.5.2/#5.5.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ChannelSecurityToken extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=441");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=443");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=442");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15131");

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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 443),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ChannelId", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("TokenId", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("CreatedAt", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("RevisedLifetime", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ChannelSecurityToken> {
        @Override
        public Class<ChannelSecurityToken> getType() {
            return ChannelSecurityToken.class;
        }

        @Override
        public ChannelSecurityToken decodeType(SerializationContext context, UaDecoder decoder) {
            UInteger channelId = decoder.readUInt32("ChannelId");
            UInteger tokenId = decoder.readUInt32("TokenId");
            DateTime createdAt = decoder.readDateTime("CreatedAt");
            UInteger revisedLifetime = decoder.readUInt32("RevisedLifetime");
            return new ChannelSecurityToken(channelId, tokenId, createdAt, revisedLifetime);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ChannelSecurityToken value) {
            encoder.writeUInt32("ChannelId", value.getChannelId());
            encoder.writeUInt32("TokenId", value.getTokenId());
            encoder.writeDateTime("CreatedAt", value.getCreatedAt());
            encoder.writeUInt32("RevisedLifetime", value.getRevisedLifetime());
        }
    }
}

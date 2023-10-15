/*
 * Copyright (c) 2023 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.7">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.7</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RedundantServerDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=853");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=855");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=854");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15362");

    private final @Nullable String serverId;

    private final UByte serviceLevel;

    private final ServerState serverState;

    public RedundantServerDataType(@Nullable String serverId, UByte serviceLevel,
                                   ServerState serverState) {
        this.serverId = serverId;
        this.serviceLevel = serviceLevel;
        this.serverState = serverState;
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

    public @Nullable String getServerId() {
        return serverId;
    }

    public UByte getServiceLevel() {
        return serviceLevel;
    }

    public ServerState getServerState() {
        return serverState;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 855),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ServerId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServiceLevel", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerState", LocalizedText.NULL_VALUE, new NodeId(0, 852), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RedundantServerDataType> {
        @Override
        public Class<RedundantServerDataType> getType() {
            return RedundantServerDataType.class;
        }

        @Override
        public RedundantServerDataType decodeType(EncodingContext context, UaDecoder decoder) {
            String serverId = decoder.decodeString("ServerId");
            UByte serviceLevel = decoder.decodeByte("ServiceLevel");
            ServerState serverState = ServerState.from(decoder.decodeEnum("ServerState"));
            return new RedundantServerDataType(serverId, serviceLevel, serverState);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               RedundantServerDataType value) {
            encoder.encodeString("ServerId", value.getServerId());
            encoder.encodeByte("ServiceLevel", value.getServiceLevel());
            encoder.encodeEnum("ServerState", value.getServerState());
        }
    }
}

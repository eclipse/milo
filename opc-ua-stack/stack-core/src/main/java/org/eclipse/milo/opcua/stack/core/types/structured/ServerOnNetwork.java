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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.3/#5.4.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.3/#5.4.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ServerOnNetwork extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=12189");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=12207");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=12195");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15095");

    private final UInteger recordId;

    private final String serverName;

    private final String discoveryUrl;

    private final String[] serverCapabilities;

    public ServerOnNetwork(UInteger recordId, String serverName, String discoveryUrl,
                           String[] serverCapabilities) {
        this.recordId = recordId;
        this.serverName = serverName;
        this.discoveryUrl = discoveryUrl;
        this.serverCapabilities = serverCapabilities;
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

    public UInteger getRecordId() {
        return recordId;
    }

    public String getServerName() {
        return serverName;
    }

    public String getDiscoveryUrl() {
        return discoveryUrl;
    }

    public String[] getServerCapabilities() {
        return serverCapabilities;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 12207),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RecordId", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiscoveryUrl", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerCapabilities", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ServerOnNetwork> {
        @Override
        public Class<ServerOnNetwork> getType() {
            return ServerOnNetwork.class;
        }

        @Override
        public ServerOnNetwork decodeType(SerializationContext context, UaDecoder decoder) {
            UInteger recordId = decoder.decodeUInt32("RecordId");
            String serverName = decoder.decodeString("ServerName");
            String discoveryUrl = decoder.decodeString("DiscoveryUrl");
            String[] serverCapabilities = decoder.decodeStringArray("ServerCapabilities");
            return new ServerOnNetwork(recordId, serverName, discoveryUrl, serverCapabilities);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, ServerOnNetwork value) {
            encoder.encodeUInt32("RecordId", value.getRecordId());
            encoder.encodeString("ServerName", value.getServerName());
            encoder.encodeString("DiscoveryUrl", value.getDiscoveryUrl());
            encoder.encodeStringArray("ServerCapabilities", value.getServerCapabilities());
        }
    }
}

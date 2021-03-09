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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ServerOnNetwork extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12189");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12195");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12207");

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
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
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

    public static final class Codec extends GenericDataTypeCodec<ServerOnNetwork> {
        @Override
        public Class<ServerOnNetwork> getType() {
            return ServerOnNetwork.class;
        }

        @Override
        public ServerOnNetwork decode(SerializationContext context, UaDecoder decoder) {
            UInteger recordId = decoder.readUInt32("RecordId");
            String serverName = decoder.readString("ServerName");
            String discoveryUrl = decoder.readString("DiscoveryUrl");
            String[] serverCapabilities = decoder.readStringArray("ServerCapabilities");
            return new ServerOnNetwork(recordId, serverName, discoveryUrl, serverCapabilities);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ServerOnNetwork value) {
            encoder.writeUInt32("RecordId", value.getRecordId());
            encoder.writeString("ServerName", value.getServerName());
            encoder.writeString("DiscoveryUrl", value.getDiscoveryUrl());
            encoder.writeStringArray("ServerCapabilities", value.getServerCapabilities());
        }
    }
}

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
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
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
public class FindServersOnNetworkResponse extends Structure implements UaResponseMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=12191");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=12209");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=12197");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15097");

    private final ResponseHeader responseHeader;

    private final DateTime lastCounterResetTime;

    private final ServerOnNetwork[] servers;

    public FindServersOnNetworkResponse(ResponseHeader responseHeader, DateTime lastCounterResetTime,
                                        ServerOnNetwork[] servers) {
        this.responseHeader = responseHeader;
        this.lastCounterResetTime = lastCounterResetTime;
        this.servers = servers;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public DateTime getLastCounterResetTime() {
        return lastCounterResetTime;
    }

    public ServerOnNetwork[] getServers() {
        return servers;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 12209),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("LastCounterResetTime", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("Servers", LocalizedText.NULL_VALUE, new NodeId(0, 12189), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<FindServersOnNetworkResponse> {
        @Override
        public Class<FindServersOnNetworkResponse> getType() {
            return FindServersOnNetworkResponse.class;
        }

        @Override
        public FindServersOnNetworkResponse decodeType(SerializationContext context,
                                                       UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            DateTime lastCounterResetTime = decoder.readDateTime("LastCounterResetTime");
            ServerOnNetwork[] servers = (ServerOnNetwork[]) decoder.readStructArray("Servers", ServerOnNetwork.TYPE_ID);
            return new FindServersOnNetworkResponse(responseHeader, lastCounterResetTime, servers);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               FindServersOnNetworkResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeDateTime("LastCounterResetTime", value.getLastCounterResetTime());
            encoder.writeStructArray("Servers", value.getServers(), ServerOnNetwork.TYPE_ID);
        }
    }
}

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
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
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
public class FindServersOnNetworkRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=12190");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=12208");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=12196");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15096");

    private final RequestHeader requestHeader;

    private final UInteger startingRecordId;

    private final UInteger maxRecordsToReturn;

    private final String[] serverCapabilityFilter;

    public FindServersOnNetworkRequest(RequestHeader requestHeader, UInteger startingRecordId,
                                       UInteger maxRecordsToReturn, String[] serverCapabilityFilter) {
        this.requestHeader = requestHeader;
        this.startingRecordId = startingRecordId;
        this.maxRecordsToReturn = maxRecordsToReturn;
        this.serverCapabilityFilter = serverCapabilityFilter;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public UInteger getStartingRecordId() {
        return startingRecordId;
    }

    public UInteger getMaxRecordsToReturn() {
        return maxRecordsToReturn;
    }

    public String[] getServerCapabilityFilter() {
        return serverCapabilityFilter;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 12208),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("StartingRecordId", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("MaxRecordsToReturn", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerCapabilityFilter", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<FindServersOnNetworkRequest> {
        @Override
        public Class<FindServersOnNetworkRequest> getType() {
            return FindServersOnNetworkRequest.class;
        }

        @Override
        public FindServersOnNetworkRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger startingRecordId = decoder.readUInt32("StartingRecordId");
            UInteger maxRecordsToReturn = decoder.readUInt32("MaxRecordsToReturn");
            String[] serverCapabilityFilter = decoder.readStringArray("ServerCapabilityFilter");
            return new FindServersOnNetworkRequest(requestHeader, startingRecordId, maxRecordsToReturn, serverCapabilityFilter);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               FindServersOnNetworkRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("StartingRecordId", value.getStartingRecordId());
            encoder.writeUInt32("MaxRecordsToReturn", value.getMaxRecordsToReturn());
            encoder.writeStringArray("ServerCapabilityFilter", value.getServerCapabilityFilter());
        }
    }
}

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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.6/#5.8.6.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.6/#5.8.6.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class UnregisterNodesRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=564");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=566");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=565");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15197");

    private final RequestHeader requestHeader;

    private final NodeId[] nodesToUnregister;

    public UnregisterNodesRequest(RequestHeader requestHeader, NodeId[] nodesToUnregister) {
        this.requestHeader = requestHeader;
        this.nodesToUnregister = nodesToUnregister;
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

    public NodeId[] getNodesToUnregister() {
        return nodesToUnregister;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 566),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodesToUnregister", LocalizedText.NULL_VALUE, new NodeId(0, 17), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<UnregisterNodesRequest> {
        @Override
        public Class<UnregisterNodesRequest> getType() {
            return UnregisterNodesRequest.class;
        }

        @Override
        public UnregisterNodesRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            NodeId[] nodesToUnregister = decoder.decodeNodeIdArray("NodesToUnregister");
            return new UnregisterNodesRequest(requestHeader, nodesToUnregister);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               UnregisterNodesRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeNodeIdArray("NodesToUnregister", value.getNodesToUnregister());
        }
    }
}

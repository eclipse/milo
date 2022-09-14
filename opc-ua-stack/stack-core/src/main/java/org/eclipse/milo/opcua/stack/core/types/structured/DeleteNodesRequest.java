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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.7.4/#5.7.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.7.4/#5.7.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DeleteNodesRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=498");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=500");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=499");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15173");

    private final RequestHeader requestHeader;

    private final DeleteNodesItem[] nodesToDelete;

    public DeleteNodesRequest(RequestHeader requestHeader, DeleteNodesItem[] nodesToDelete) {
        this.requestHeader = requestHeader;
        this.nodesToDelete = nodesToDelete;
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

    public DeleteNodesItem[] getNodesToDelete() {
        return nodesToDelete;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 500),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodesToDelete", LocalizedText.NULL_VALUE, new NodeId(0, 382), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteNodesRequest> {
        @Override
        public Class<DeleteNodesRequest> getType() {
            return DeleteNodesRequest.class;
        }

        @Override
        public DeleteNodesRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            DeleteNodesItem[] nodesToDelete = (DeleteNodesItem[]) decoder.decodeStructArray("NodesToDelete", DeleteNodesItem.TYPE_ID);
            return new DeleteNodesRequest(requestHeader, nodesToDelete);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               DeleteNodesRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeStructArray("NodesToDelete", value.getNodesToDelete(), DeleteNodesItem.TYPE_ID);
        }
    }
}

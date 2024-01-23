/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.StringJoiner;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.5/#5.8.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.5/#5.8.5.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
public class RegisterNodesRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=558");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=560");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=559");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15195");

    private final RequestHeader requestHeader;

    private final NodeId @Nullable [] nodesToRegister;

    public RegisterNodesRequest(RequestHeader requestHeader, NodeId @Nullable [] nodesToRegister) {
        this.requestHeader = requestHeader;
        this.nodesToRegister = nodesToRegister;
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

    public NodeId @Nullable [] getNodesToRegister() {
        return nodesToRegister;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", RegisterNodesRequest.class.getSimpleName() + "[", "]");
        joiner.add("requestHeader=" + getRequestHeader());
        joiner.add("nodesToRegister=" + java.util.Arrays.toString(getNodesToRegister()));
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 560),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodesToRegister", LocalizedText.NULL_VALUE, new NodeId(0, 17), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RegisterNodesRequest> {
        @Override
        public Class<RegisterNodesRequest> getType() {
            return RegisterNodesRequest.class;
        }

        @Override
        public RegisterNodesRequest decodeType(EncodingContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            NodeId[] nodesToRegister = decoder.decodeNodeIdArray("NodesToRegister");
            return new RegisterNodesRequest(requestHeader, nodesToRegister);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, RegisterNodesRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeNodeIdArray("NodesToRegister", value.getNodesToRegister());
        }
    }
}

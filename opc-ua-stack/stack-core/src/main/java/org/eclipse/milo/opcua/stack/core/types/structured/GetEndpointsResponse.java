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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.4/#5.4.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.4/#5.4.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class GetEndpointsResponse extends Structure implements UaResponseMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=429");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=431");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=430");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15101");

    private final ResponseHeader responseHeader;

    private final EndpointDescription[] endpoints;

    public GetEndpointsResponse(ResponseHeader responseHeader, EndpointDescription[] endpoints) {
        this.responseHeader = responseHeader;
        this.endpoints = endpoints;
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

    public EndpointDescription[] getEndpoints() {
        return endpoints;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 431),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("Endpoints", LocalizedText.NULL_VALUE, new NodeId(0, 312), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<GetEndpointsResponse> {
        @Override
        public Class<GetEndpointsResponse> getType() {
            return GetEndpointsResponse.class;
        }

        @Override
        public GetEndpointsResponse decodeType(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.decodeStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            EndpointDescription[] endpoints = (EndpointDescription[]) decoder.decodeStructArray("Endpoints", EndpointDescription.TYPE_ID);
            return new GetEndpointsResponse(responseHeader, endpoints);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               GetEndpointsResponse value) {
            encoder.encodeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.encodeStructArray("Endpoints", value.getEndpoints(), EndpointDescription.TYPE_ID);
        }
    }
}

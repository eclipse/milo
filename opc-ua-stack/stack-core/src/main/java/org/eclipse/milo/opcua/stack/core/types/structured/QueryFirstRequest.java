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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
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
public class QueryFirstRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=613");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=615");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=614");

    private final RequestHeader requestHeader;

    private final ViewDescription view;

    private final NodeTypeDescription[] nodeTypes;

    private final ContentFilter filter;

    private final UInteger maxDataSetsToReturn;

    private final UInteger maxReferencesToReturn;

    public QueryFirstRequest(RequestHeader requestHeader, ViewDescription view,
                             NodeTypeDescription[] nodeTypes, ContentFilter filter, UInteger maxDataSetsToReturn,
                             UInteger maxReferencesToReturn) {
        this.requestHeader = requestHeader;
        this.view = view;
        this.nodeTypes = nodeTypes;
        this.filter = filter;
        this.maxDataSetsToReturn = maxDataSetsToReturn;
        this.maxReferencesToReturn = maxReferencesToReturn;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public ViewDescription getView() {
        return view;
    }

    public NodeTypeDescription[] getNodeTypes() {
        return nodeTypes;
    }

    public ContentFilter getFilter() {
        return filter;
    }

    public UInteger getMaxDataSetsToReturn() {
        return maxDataSetsToReturn;
    }

    public UInteger getMaxReferencesToReturn() {
        return maxReferencesToReturn;
    }

    public static final class Codec extends GenericDataTypeCodec<QueryFirstRequest> {
        @Override
        public Class<QueryFirstRequest> getType() {
            return QueryFirstRequest.class;
        }

        @Override
        public QueryFirstRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            ViewDescription view = (ViewDescription) decoder.readStruct("View", ViewDescription.TYPE_ID);
            NodeTypeDescription[] nodeTypes = (NodeTypeDescription[]) decoder.readStructArray("NodeTypes", NodeTypeDescription.TYPE_ID);
            ContentFilter filter = (ContentFilter) decoder.readStruct("Filter", ContentFilter.TYPE_ID);
            UInteger maxDataSetsToReturn = decoder.readUInt32("MaxDataSetsToReturn");
            UInteger maxReferencesToReturn = decoder.readUInt32("MaxReferencesToReturn");
            return new QueryFirstRequest(requestHeader, view, nodeTypes, filter, maxDataSetsToReturn, maxReferencesToReturn);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, QueryFirstRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStruct("View", value.getView(), ViewDescription.TYPE_ID);
            encoder.writeStructArray("NodeTypes", value.getNodeTypes(), NodeTypeDescription.TYPE_ID);
            encoder.writeStruct("Filter", value.getFilter(), ContentFilter.TYPE_ID);
            encoder.writeUInt32("MaxDataSetsToReturn", value.getMaxDataSetsToReturn());
            encoder.writeUInt32("MaxReferencesToReturn", value.getMaxReferencesToReturn());
        }
    }
}

/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class QueryFirstRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.QueryFirstRequest;
    public static final NodeId BinaryEncodingId = Identifiers.QueryFirstRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryFirstRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final ViewDescription view;
    protected final NodeTypeDescription[] nodeTypes;
    protected final ContentFilter filter;
    protected final UInteger maxDataSetsToReturn;
    protected final UInteger maxReferencesToReturn;

    public QueryFirstRequest() {
        this.requestHeader = null;
        this.view = null;
        this.nodeTypes = null;
        this.filter = null;
        this.maxDataSetsToReturn = null;
        this.maxReferencesToReturn = null;
    }

    public QueryFirstRequest(RequestHeader requestHeader, ViewDescription view, NodeTypeDescription[] nodeTypes, ContentFilter filter, UInteger maxDataSetsToReturn, UInteger maxReferencesToReturn) {
        this.requestHeader = requestHeader;
        this.view = view;
        this.nodeTypes = nodeTypes;
        this.filter = filter;
        this.maxDataSetsToReturn = maxDataSetsToReturn;
        this.maxReferencesToReturn = maxReferencesToReturn;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public ViewDescription getView() { return view; }

    @Nullable
    public NodeTypeDescription[] getNodeTypes() { return nodeTypes; }

    public ContentFilter getFilter() { return filter; }

    public UInteger getMaxDataSetsToReturn() { return maxDataSetsToReturn; }

    public UInteger getMaxReferencesToReturn() { return maxReferencesToReturn; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", requestHeader)
            .add("View", view)
            .add("NodeTypes", nodeTypes)
            .add("Filter", filter)
            .add("MaxDataSetsToReturn", maxDataSetsToReturn)
            .add("MaxReferencesToReturn", maxReferencesToReturn)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<QueryFirstRequest> {

        @Override
        public Class<QueryFirstRequest> getType() {
            return QueryFirstRequest.class;
        }

        @Override
        public QueryFirstRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            ViewDescription view = (ViewDescription) decoder.readBuiltinStruct("View", ViewDescription.class);
            NodeTypeDescription[] nodeTypes =
                decoder.readBuiltinStructArray(
                    "NodeTypes",
                    NodeTypeDescription.class
                );
            ContentFilter filter = (ContentFilter) decoder.readBuiltinStruct("Filter", ContentFilter.class);
            UInteger maxDataSetsToReturn = decoder.readUInt32("MaxDataSetsToReturn");
            UInteger maxReferencesToReturn = decoder.readUInt32("MaxReferencesToReturn");

            return new QueryFirstRequest(requestHeader, view, nodeTypes, filter, maxDataSetsToReturn, maxReferencesToReturn);
        }

        @Override
        public void encode(QueryFirstRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStruct("View", value.view, ViewDescription.class);
            encoder.writeBuiltinStructArray(
                "NodeTypes",
                value.nodeTypes,
                NodeTypeDescription.class
            );
            encoder.writeBuiltinStruct("Filter", value.filter, ContentFilter.class);
            encoder.writeUInt32("MaxDataSetsToReturn", value.maxDataSetsToReturn);
            encoder.writeUInt32("MaxReferencesToReturn", value.maxReferencesToReturn);
        }
    }

}

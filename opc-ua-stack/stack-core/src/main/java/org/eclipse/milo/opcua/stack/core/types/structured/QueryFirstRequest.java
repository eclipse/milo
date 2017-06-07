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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("QueryFirstRequest")
public class QueryFirstRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.QueryFirstRequest;
    public static final NodeId BinaryEncodingId = Identifiers.QueryFirstRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryFirstRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final ViewDescription _view;
    protected final NodeTypeDescription[] _nodeTypes;
    protected final ContentFilter _filter;
    protected final UInteger _maxDataSetsToReturn;
    protected final UInteger _maxReferencesToReturn;

    public QueryFirstRequest() {
        this._requestHeader = null;
        this._view = null;
        this._nodeTypes = null;
        this._filter = null;
        this._maxDataSetsToReturn = null;
        this._maxReferencesToReturn = null;
    }

    public QueryFirstRequest(RequestHeader _requestHeader, ViewDescription _view, NodeTypeDescription[] _nodeTypes, ContentFilter _filter, UInteger _maxDataSetsToReturn, UInteger _maxReferencesToReturn) {
        this._requestHeader = _requestHeader;
        this._view = _view;
        this._nodeTypes = _nodeTypes;
        this._filter = _filter;
        this._maxDataSetsToReturn = _maxDataSetsToReturn;
        this._maxReferencesToReturn = _maxReferencesToReturn;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public ViewDescription getView() { return _view; }

    @Nullable
    public NodeTypeDescription[] getNodeTypes() { return _nodeTypes; }

    public ContentFilter getFilter() { return _filter; }

    public UInteger getMaxDataSetsToReturn() { return _maxDataSetsToReturn; }

    public UInteger getMaxReferencesToReturn() { return _maxReferencesToReturn; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", _requestHeader)
            .add("View", _view)
            .add("NodeTypes", _nodeTypes)
            .add("Filter", _filter)
            .add("MaxDataSetsToReturn", _maxDataSetsToReturn)
            .add("MaxReferencesToReturn", _maxReferencesToReturn)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<QueryFirstRequest> {
        @Override
        public QueryFirstRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            ViewDescription _view = (ViewDescription) context.decode(ViewDescription.BinaryEncodingId, reader);
            NodeTypeDescription[] _nodeTypes =
                reader.readArray(
                    () -> (NodeTypeDescription) context.decode(
                        NodeTypeDescription.BinaryEncodingId, reader),
                    NodeTypeDescription.class
                );
            ContentFilter _filter = (ContentFilter) context.decode(ContentFilter.BinaryEncodingId, reader);
            UInteger _maxDataSetsToReturn = reader.readUInt32();
            UInteger _maxReferencesToReturn = reader.readUInt32();

            return new QueryFirstRequest(_requestHeader, _view, _nodeTypes, _filter, _maxDataSetsToReturn, _maxReferencesToReturn);
        }

        @Override
        public void encode(SerializationContext context, QueryFirstRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            context.encode(ViewDescription.BinaryEncodingId, value._view, writer);
            writer.writeArray(
                value._nodeTypes,
                e -> context.encode(NodeTypeDescription.BinaryEncodingId, e, writer)
            );
            context.encode(ContentFilter.BinaryEncodingId, value._filter, writer);
            writer.writeUInt32(value._maxDataSetsToReturn);
            writer.writeUInt32(value._maxReferencesToReturn);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<QueryFirstRequest> {
        @Override
        public QueryFirstRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            ViewDescription _view = (ViewDescription) context.decode(ViewDescription.XmlEncodingId, reader);
            NodeTypeDescription[] _nodeTypes =
                reader.readArray(
                    "NodeTypes",
                    f -> (NodeTypeDescription) context.decode(
                        NodeTypeDescription.XmlEncodingId, reader),
                    NodeTypeDescription.class
                );
            ContentFilter _filter = (ContentFilter) context.decode(ContentFilter.XmlEncodingId, reader);
            UInteger _maxDataSetsToReturn = reader.readUInt32("MaxDataSetsToReturn");
            UInteger _maxReferencesToReturn = reader.readUInt32("MaxReferencesToReturn");

            return new QueryFirstRequest(_requestHeader, _view, _nodeTypes, _filter, _maxDataSetsToReturn, _maxReferencesToReturn);
        }

        @Override
        public void encode(SerializationContext context, QueryFirstRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            context.encode(ViewDescription.XmlEncodingId, encodable._view, writer);
            writer.writeArray(
                "NodeTypes",
                encodable._nodeTypes,
                (f, e) -> context.encode(NodeTypeDescription.XmlEncodingId, e, writer)
            );
            context.encode(ContentFilter.XmlEncodingId, encodable._filter, writer);
            writer.writeUInt32("MaxDataSetsToReturn", encodable._maxDataSetsToReturn);
            writer.writeUInt32("MaxReferencesToReturn", encodable._maxReferencesToReturn);
        }
    }

}

/*
 * Copyright (c) 2016 Kevin Herron
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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
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


    public static void encode(QueryFirstRequest queryFirstRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", queryFirstRequest._requestHeader != null ? queryFirstRequest._requestHeader : new RequestHeader());
        encoder.encodeSerializable("View", queryFirstRequest._view != null ? queryFirstRequest._view : new ViewDescription());
        encoder.encodeArray("NodeTypes", queryFirstRequest._nodeTypes, encoder::encodeSerializable);
        encoder.encodeSerializable("Filter", queryFirstRequest._filter != null ? queryFirstRequest._filter : new ContentFilter());
        encoder.encodeUInt32("MaxDataSetsToReturn", queryFirstRequest._maxDataSetsToReturn);
        encoder.encodeUInt32("MaxReferencesToReturn", queryFirstRequest._maxReferencesToReturn);
    }

    public static QueryFirstRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        ViewDescription _view = decoder.decodeSerializable("View", ViewDescription.class);
        NodeTypeDescription[] _nodeTypes = decoder.decodeArray("NodeTypes", decoder::decodeSerializable, NodeTypeDescription.class);
        ContentFilter _filter = decoder.decodeSerializable("Filter", ContentFilter.class);
        UInteger _maxDataSetsToReturn = decoder.decodeUInt32("MaxDataSetsToReturn");
        UInteger _maxReferencesToReturn = decoder.decodeUInt32("MaxReferencesToReturn");

        return new QueryFirstRequest(_requestHeader, _view, _nodeTypes, _filter, _maxDataSetsToReturn, _maxReferencesToReturn);
    }

    static {
        DelegateRegistry.registerEncoder(QueryFirstRequest::encode, QueryFirstRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(QueryFirstRequest::decode, QueryFirstRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}

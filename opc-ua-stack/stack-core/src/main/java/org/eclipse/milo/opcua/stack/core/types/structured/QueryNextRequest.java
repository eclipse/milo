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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("QueryNextRequest")
public class QueryNextRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.QueryNextRequest;
    public static final NodeId BinaryEncodingId = Identifiers.QueryNextRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryNextRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Boolean _releaseContinuationPoint;
    protected final ByteString _continuationPoint;

    public QueryNextRequest() {
        this._requestHeader = null;
        this._releaseContinuationPoint = null;
        this._continuationPoint = null;
    }

    public QueryNextRequest(RequestHeader _requestHeader, Boolean _releaseContinuationPoint, ByteString _continuationPoint) {
        this._requestHeader = _requestHeader;
        this._releaseContinuationPoint = _releaseContinuationPoint;
        this._continuationPoint = _continuationPoint;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Boolean getReleaseContinuationPoint() { return _releaseContinuationPoint; }

    public ByteString getContinuationPoint() { return _continuationPoint; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(QueryNextRequest queryNextRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", queryNextRequest._requestHeader != null ? queryNextRequest._requestHeader : new RequestHeader());
        encoder.encodeBoolean("ReleaseContinuationPoint", queryNextRequest._releaseContinuationPoint);
        encoder.encodeByteString("ContinuationPoint", queryNextRequest._continuationPoint);
    }

    public static QueryNextRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        Boolean _releaseContinuationPoint = decoder.decodeBoolean("ReleaseContinuationPoint");
        ByteString _continuationPoint = decoder.decodeByteString("ContinuationPoint");

        return new QueryNextRequest(_requestHeader, _releaseContinuationPoint, _continuationPoint);
    }

    static {
        DelegateRegistry.registerEncoder(QueryNextRequest::encode, QueryNextRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(QueryNextRequest::decode, QueryNextRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}

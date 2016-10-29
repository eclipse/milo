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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("QueryNextResponse")
public class QueryNextResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.QueryNextResponse;
    public static final NodeId BinaryEncodingId = Identifiers.QueryNextResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryNextResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final QueryDataSet[] _queryDataSets;
    protected final ByteString _revisedContinuationPoint;

    public QueryNextResponse() {
        this._responseHeader = null;
        this._queryDataSets = null;
        this._revisedContinuationPoint = null;
    }

    public QueryNextResponse(ResponseHeader _responseHeader, QueryDataSet[] _queryDataSets, ByteString _revisedContinuationPoint) {
        this._responseHeader = _responseHeader;
        this._queryDataSets = _queryDataSets;
        this._revisedContinuationPoint = _revisedContinuationPoint;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public QueryDataSet[] getQueryDataSets() { return _queryDataSets; }

    public ByteString getRevisedContinuationPoint() { return _revisedContinuationPoint; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(QueryNextResponse queryNextResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", queryNextResponse._responseHeader != null ? queryNextResponse._responseHeader : new ResponseHeader());
        encoder.encodeArray("QueryDataSets", queryNextResponse._queryDataSets, encoder::encodeSerializable);
        encoder.encodeByteString("RevisedContinuationPoint", queryNextResponse._revisedContinuationPoint);
    }

    public static QueryNextResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        QueryDataSet[] _queryDataSets = decoder.decodeArray("QueryDataSets", decoder::decodeSerializable, QueryDataSet.class);
        ByteString _revisedContinuationPoint = decoder.decodeByteString("RevisedContinuationPoint");

        return new QueryNextResponse(_responseHeader, _queryDataSets, _revisedContinuationPoint);
    }

    static {
        DelegateRegistry.registerEncoder(QueryNextResponse::encode, QueryNextResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(QueryNextResponse::decode, QueryNextResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}

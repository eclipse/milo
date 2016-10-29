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
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("QueryFirstResponse")
public class QueryFirstResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.QueryFirstResponse;
    public static final NodeId BinaryEncodingId = Identifiers.QueryFirstResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryFirstResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final QueryDataSet[] _queryDataSets;
    protected final ByteString _continuationPoint;
    protected final ParsingResult[] _parsingResults;
    protected final DiagnosticInfo[] _diagnosticInfos;
    protected final ContentFilterResult _filterResult;

    public QueryFirstResponse() {
        this._responseHeader = null;
        this._queryDataSets = null;
        this._continuationPoint = null;
        this._parsingResults = null;
        this._diagnosticInfos = null;
        this._filterResult = null;
    }

    public QueryFirstResponse(ResponseHeader _responseHeader, QueryDataSet[] _queryDataSets, ByteString _continuationPoint, ParsingResult[] _parsingResults, DiagnosticInfo[] _diagnosticInfos, ContentFilterResult _filterResult) {
        this._responseHeader = _responseHeader;
        this._queryDataSets = _queryDataSets;
        this._continuationPoint = _continuationPoint;
        this._parsingResults = _parsingResults;
        this._diagnosticInfos = _diagnosticInfos;
        this._filterResult = _filterResult;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public QueryDataSet[] getQueryDataSets() { return _queryDataSets; }

    public ByteString getContinuationPoint() { return _continuationPoint; }

    public ParsingResult[] getParsingResults() { return _parsingResults; }

    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    public ContentFilterResult getFilterResult() { return _filterResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(QueryFirstResponse queryFirstResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", queryFirstResponse._responseHeader != null ? queryFirstResponse._responseHeader : new ResponseHeader());
        encoder.encodeArray("QueryDataSets", queryFirstResponse._queryDataSets, encoder::encodeSerializable);
        encoder.encodeByteString("ContinuationPoint", queryFirstResponse._continuationPoint);
        encoder.encodeArray("ParsingResults", queryFirstResponse._parsingResults, encoder::encodeSerializable);
        encoder.encodeArray("DiagnosticInfos", queryFirstResponse._diagnosticInfos, encoder::encodeDiagnosticInfo);
        encoder.encodeSerializable("FilterResult", queryFirstResponse._filterResult != null ? queryFirstResponse._filterResult : new ContentFilterResult());
    }

    public static QueryFirstResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        QueryDataSet[] _queryDataSets = decoder.decodeArray("QueryDataSets", decoder::decodeSerializable, QueryDataSet.class);
        ByteString _continuationPoint = decoder.decodeByteString("ContinuationPoint");
        ParsingResult[] _parsingResults = decoder.decodeArray("ParsingResults", decoder::decodeSerializable, ParsingResult.class);
        DiagnosticInfo[] _diagnosticInfos = decoder.decodeArray("DiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);
        ContentFilterResult _filterResult = decoder.decodeSerializable("FilterResult", ContentFilterResult.class);

        return new QueryFirstResponse(_responseHeader, _queryDataSets, _continuationPoint, _parsingResults, _diagnosticInfos, _filterResult);
    }

    static {
        DelegateRegistry.registerEncoder(QueryFirstResponse::encode, QueryFirstResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(QueryFirstResponse::decode, QueryFirstResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}

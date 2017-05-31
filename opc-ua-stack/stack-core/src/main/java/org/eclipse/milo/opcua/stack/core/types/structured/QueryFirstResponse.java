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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
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

    @Nullable
    public QueryDataSet[] getQueryDataSets() { return _queryDataSets; }

    public ByteString getContinuationPoint() { return _continuationPoint; }

    @Nullable
    public ParsingResult[] getParsingResults() { return _parsingResults; }

    @Nullable
    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    public ContentFilterResult getFilterResult() { return _filterResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .add("QueryDataSets", _queryDataSets)
            .add("ContinuationPoint", _continuationPoint)
            .add("ParsingResults", _parsingResults)
            .add("DiagnosticInfos", _diagnosticInfos)
            .add("FilterResult", _filterResult)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<QueryFirstResponse> {
        @Override
        public QueryFirstResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            QueryDataSet[] _queryDataSets =
                reader.readArray(
                    () -> (QueryDataSet) context.decode(
                        QueryDataSet.BinaryEncodingId, reader),
                    QueryDataSet.class
                );
            ByteString _continuationPoint = reader.readByteString();
            ParsingResult[] _parsingResults =
                reader.readArray(
                    () -> (ParsingResult) context.decode(
                        ParsingResult.BinaryEncodingId, reader),
                    ParsingResult.class
                );
            DiagnosticInfo[] _diagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);
            ContentFilterResult _filterResult = (ContentFilterResult) context.decode(ContentFilterResult.BinaryEncodingId, reader);

            return new QueryFirstResponse(_responseHeader, _queryDataSets, _continuationPoint, _parsingResults, _diagnosticInfos, _filterResult);
        }

        @Override
        public void encode(SerializationContext context, QueryFirstResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, encodable._responseHeader, writer);
            writer.writeArray(
                encodable._queryDataSets,
                e -> context.encode(QueryDataSet.BinaryEncodingId, e, writer)
            );
            writer.writeByteString(encodable._continuationPoint);
            writer.writeArray(
                encodable._parsingResults,
                e -> context.encode(ParsingResult.BinaryEncodingId, e, writer)
            );
            writer.writeArray(encodable._diagnosticInfos, writer::writeDiagnosticInfo);
            context.encode(ContentFilterResult.BinaryEncodingId, encodable._filterResult, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<QueryFirstResponse> {
        @Override
        public QueryFirstResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            QueryDataSet[] _queryDataSets =
                reader.readArray(
                    "QueryDataSets",
                    f -> (QueryDataSet) context.decode(
                        QueryDataSet.XmlEncodingId, reader),
                    QueryDataSet.class
                );
            ByteString _continuationPoint = reader.readByteString("ContinuationPoint");
            ParsingResult[] _parsingResults =
                reader.readArray(
                    "ParsingResults",
                    f -> (ParsingResult) context.decode(
                        ParsingResult.XmlEncodingId, reader),
                    ParsingResult.class
                );
            DiagnosticInfo[] _diagnosticInfos = reader.readArray("DiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);
            ContentFilterResult _filterResult = (ContentFilterResult) context.decode(ContentFilterResult.XmlEncodingId, reader);

            return new QueryFirstResponse(_responseHeader, _queryDataSets, _continuationPoint, _parsingResults, _diagnosticInfos, _filterResult);
        }

        @Override
        public void encode(SerializationContext context, QueryFirstResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            writer.writeArray(
                "QueryDataSets",
                encodable._queryDataSets,
                (f, e) -> context.encode(QueryDataSet.XmlEncodingId, e, writer)
            );
            writer.writeByteString("ContinuationPoint", encodable._continuationPoint);
            writer.writeArray(
                "ParsingResults",
                encodable._parsingResults,
                (f, e) -> context.encode(ParsingResult.XmlEncodingId, e, writer)
            );
            writer.writeArray("DiagnosticInfos", encodable._diagnosticInfos, writer::writeDiagnosticInfo);
            context.encode(ContentFilterResult.XmlEncodingId, encodable._filterResult, writer);
        }
    }

}

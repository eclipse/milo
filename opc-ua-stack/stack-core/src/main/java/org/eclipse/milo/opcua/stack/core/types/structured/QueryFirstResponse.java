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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class QueryFirstResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.QueryFirstResponse;
    public static final NodeId BinaryEncodingId = Identifiers.QueryFirstResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryFirstResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final QueryDataSet[] queryDataSets;
    protected final ByteString continuationPoint;
    protected final ParsingResult[] parsingResults;
    protected final DiagnosticInfo[] diagnosticInfos;
    protected final ContentFilterResult filterResult;

    public QueryFirstResponse() {
        this.responseHeader = null;
        this.queryDataSets = null;
        this.continuationPoint = null;
        this.parsingResults = null;
        this.diagnosticInfos = null;
        this.filterResult = null;
    }

    public QueryFirstResponse(ResponseHeader responseHeader, QueryDataSet[] queryDataSets, ByteString continuationPoint, ParsingResult[] parsingResults, DiagnosticInfo[] diagnosticInfos, ContentFilterResult filterResult) {
        this.responseHeader = responseHeader;
        this.queryDataSets = queryDataSets;
        this.continuationPoint = continuationPoint;
        this.parsingResults = parsingResults;
        this.diagnosticInfos = diagnosticInfos;
        this.filterResult = filterResult;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Nullable
    public QueryDataSet[] getQueryDataSets() { return queryDataSets; }

    public ByteString getContinuationPoint() { return continuationPoint; }

    @Nullable
    public ParsingResult[] getParsingResults() { return parsingResults; }

    @Nullable
    public DiagnosticInfo[] getDiagnosticInfos() { return diagnosticInfos; }

    public ContentFilterResult getFilterResult() { return filterResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .add("QueryDataSets", queryDataSets)
            .add("ContinuationPoint", continuationPoint)
            .add("ParsingResults", parsingResults)
            .add("DiagnosticInfos", diagnosticInfos)
            .add("FilterResult", filterResult)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<QueryFirstResponse> {

        @Override
        public Class<QueryFirstResponse> getType() {
            return QueryFirstResponse.class;
        }

        @Override
        public QueryFirstResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            QueryDataSet[] queryDataSets =
                decoder.readBuiltinStructArray(
                    "QueryDataSets",
                    QueryDataSet.class
                );
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            ParsingResult[] parsingResults =
                decoder.readBuiltinStructArray(
                    "ParsingResults",
                    ParsingResult.class
                );
            DiagnosticInfo[] diagnosticInfos = decoder.readArray("DiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);
            ContentFilterResult filterResult = (ContentFilterResult) decoder.readBuiltinStruct("FilterResult", ContentFilterResult.class);

            return new QueryFirstResponse(responseHeader, queryDataSets, continuationPoint, parsingResults, diagnosticInfos, filterResult);
        }

        @Override
        public void encode(QueryFirstResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeBuiltinStructArray(
                "QueryDataSets",
                value.queryDataSets,
                QueryDataSet.class
            );
            encoder.writeByteString("ContinuationPoint", value.continuationPoint);
            encoder.writeBuiltinStructArray(
                "ParsingResults",
                value.parsingResults,
                ParsingResult.class
            );
            encoder.writeArray("DiagnosticInfos", value.diagnosticInfos, encoder::writeDiagnosticInfo);
            encoder.writeBuiltinStruct("FilterResult", value.filterResult, ContentFilterResult.class);
        }
    }

}

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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class QueryFirstResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=616");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=618");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=617");

    private final ResponseHeader responseHeader;

    private final QueryDataSet[] queryDataSets;

    private final ByteString continuationPoint;

    private final ParsingResult[] parsingResults;

    private final DiagnosticInfo[] diagnosticInfos;

    private final ContentFilterResult filterResult;

    public QueryFirstResponse(ResponseHeader responseHeader, QueryDataSet[] queryDataSets,
                              ByteString continuationPoint, ParsingResult[] parsingResults,
                              DiagnosticInfo[] diagnosticInfos, ContentFilterResult filterResult) {
        this.responseHeader = responseHeader;
        this.queryDataSets = queryDataSets;
        this.continuationPoint = continuationPoint;
        this.parsingResults = parsingResults;
        this.diagnosticInfos = diagnosticInfos;
        this.filterResult = filterResult;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public QueryDataSet[] getQueryDataSets() {
        return queryDataSets;
    }

    public ByteString getContinuationPoint() {
        return continuationPoint;
    }

    public ParsingResult[] getParsingResults() {
        return parsingResults;
    }

    public DiagnosticInfo[] getDiagnosticInfos() {
        return diagnosticInfos;
    }

    public ContentFilterResult getFilterResult() {
        return filterResult;
    }

    public static final class Codec extends GenericDataTypeCodec<QueryFirstResponse> {
        @Override
        public Class<QueryFirstResponse> getType() {
            return QueryFirstResponse.class;
        }

        @Override
        public QueryFirstResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            QueryDataSet[] queryDataSets = (QueryDataSet[]) decoder.readStructArray("QueryDataSets", QueryDataSet.TYPE_ID);
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            ParsingResult[] parsingResults = (ParsingResult[]) decoder.readStructArray("ParsingResults", ParsingResult.TYPE_ID);
            DiagnosticInfo[] diagnosticInfos = decoder.readDiagnosticInfoArray("DiagnosticInfos");
            ContentFilterResult filterResult = (ContentFilterResult) decoder.readStruct("FilterResult", ContentFilterResult.TYPE_ID);
            return new QueryFirstResponse(responseHeader, queryDataSets, continuationPoint, parsingResults, diagnosticInfos, filterResult);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, QueryFirstResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeStructArray("QueryDataSets", value.getQueryDataSets(), QueryDataSet.TYPE_ID);
            encoder.writeByteString("ContinuationPoint", value.getContinuationPoint());
            encoder.writeStructArray("ParsingResults", value.getParsingResults(), ParsingResult.TYPE_ID);
            encoder.writeDiagnosticInfoArray("DiagnosticInfos", value.getDiagnosticInfos());
            encoder.writeStruct("FilterResult", value.getFilterResult(), ContentFilterResult.TYPE_ID);
        }
    }
}

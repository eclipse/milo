/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.9.3/#5.9.3.1">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.9.3/#5.9.3.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class QueryFirstResponse extends Structure implements UaResponseMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=616");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=618");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=617");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15252");

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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 618),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("QueryDataSets", LocalizedText.NULL_VALUE, new NodeId(0, 577), 1, null, UInteger.valueOf(0), false),
                new StructureField("ContinuationPoint", LocalizedText.NULL_VALUE, new NodeId(0, 521), -1, null, UInteger.valueOf(0), false),
                new StructureField("ParsingResults", LocalizedText.NULL_VALUE, new NodeId(0, 610), 1, null, UInteger.valueOf(0), false),
                new StructureField("DiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false),
                new StructureField("FilterResult", LocalizedText.NULL_VALUE, new NodeId(0, 607), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<QueryFirstResponse> {
        @Override
        public Class<QueryFirstResponse> getType() {
            return QueryFirstResponse.class;
        }

        @Override
        public QueryFirstResponse decodeType(EncodingContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.decodeStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            QueryDataSet[] queryDataSets = (QueryDataSet[]) decoder.decodeStructArray("QueryDataSets", QueryDataSet.TYPE_ID);
            ByteString continuationPoint = decoder.decodeByteString("ContinuationPoint");
            ParsingResult[] parsingResults = (ParsingResult[]) decoder.decodeStructArray("ParsingResults", ParsingResult.TYPE_ID);
            DiagnosticInfo[] diagnosticInfos = decoder.decodeDiagnosticInfoArray("DiagnosticInfos");
            ContentFilterResult filterResult = (ContentFilterResult) decoder.decodeStruct("FilterResult", ContentFilterResult.TYPE_ID);
            return new QueryFirstResponse(responseHeader, queryDataSets, continuationPoint, parsingResults, diagnosticInfos, filterResult);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               QueryFirstResponse value) {
            encoder.encodeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.encodeStructArray("QueryDataSets", value.getQueryDataSets(), QueryDataSet.TYPE_ID);
            encoder.encodeByteString("ContinuationPoint", value.getContinuationPoint());
            encoder.encodeStructArray("ParsingResults", value.getParsingResults(), ParsingResult.TYPE_ID);
            encoder.encodeDiagnosticInfoArray("DiagnosticInfos", value.getDiagnosticInfos());
            encoder.encodeStruct("FilterResult", value.getFilterResult(), ContentFilterResult.TYPE_ID);
        }
    }
}

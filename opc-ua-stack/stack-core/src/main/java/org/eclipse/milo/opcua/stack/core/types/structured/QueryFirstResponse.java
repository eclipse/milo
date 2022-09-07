package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
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
public class QueryFirstResponse extends Structure implements UaResponseMessage {
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

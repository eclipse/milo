package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.22.3">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.22.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class EventFilterResult extends MonitoringFilterResult implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=734");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=736");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=735");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15314");

    private final StatusCode[] selectClauseResults;

    private final DiagnosticInfo[] selectClauseDiagnosticInfos;

    private final ContentFilterResult whereClauseResult;

    public EventFilterResult(StatusCode[] selectClauseResults,
                             DiagnosticInfo[] selectClauseDiagnosticInfos, ContentFilterResult whereClauseResult) {
        this.selectClauseResults = selectClauseResults;
        this.selectClauseDiagnosticInfos = selectClauseDiagnosticInfos;
        this.whereClauseResult = whereClauseResult;
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

    public StatusCode[] getSelectClauseResults() {
        return selectClauseResults;
    }

    public DiagnosticInfo[] getSelectClauseDiagnosticInfos() {
        return selectClauseDiagnosticInfos;
    }

    public ContentFilterResult getWhereClauseResult() {
        return whereClauseResult;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 736),
            new NodeId(0, 731),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("SelectClauseResults", LocalizedText.NULL_VALUE, new NodeId(0, 19), 1, null, UInteger.valueOf(0), false),
                new StructureField("SelectClauseDiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false),
                new StructureField("WhereClauseResult", LocalizedText.NULL_VALUE, new NodeId(0, 607), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EventFilterResult> {
        @Override
        public Class<EventFilterResult> getType() {
            return EventFilterResult.class;
        }

        @Override
        public EventFilterResult decodeType(SerializationContext context, UaDecoder decoder) {
            StatusCode[] selectClauseResults = decoder.readStatusCodeArray("SelectClauseResults");
            DiagnosticInfo[] selectClauseDiagnosticInfos = decoder.readDiagnosticInfoArray("SelectClauseDiagnosticInfos");
            ContentFilterResult whereClauseResult = (ContentFilterResult) decoder.readStruct("WhereClauseResult", ContentFilterResult.TYPE_ID);
            return new EventFilterResult(selectClauseResults, selectClauseDiagnosticInfos, whereClauseResult);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               EventFilterResult value) {
            encoder.writeStatusCodeArray("SelectClauseResults", value.getSelectClauseResults());
            encoder.writeDiagnosticInfoArray("SelectClauseDiagnosticInfos", value.getSelectClauseDiagnosticInfos());
            encoder.writeStruct("WhereClauseResult", value.getWhereClauseResult(), ContentFilterResult.TYPE_ID);
        }
    }
}

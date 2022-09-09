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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.9.3/#5.9.3.1">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.9.3/#5.9.3.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ParsingResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=610");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=612");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=611");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15236");

    private final StatusCode statusCode;

    private final StatusCode[] dataStatusCodes;

    private final DiagnosticInfo[] dataDiagnosticInfos;

    public ParsingResult(StatusCode statusCode, StatusCode[] dataStatusCodes,
                         DiagnosticInfo[] dataDiagnosticInfos) {
        this.statusCode = statusCode;
        this.dataStatusCodes = dataStatusCodes;
        this.dataDiagnosticInfos = dataDiagnosticInfos;
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

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public StatusCode[] getDataStatusCodes() {
        return dataStatusCodes;
    }

    public DiagnosticInfo[] getDataDiagnosticInfos() {
        return dataDiagnosticInfos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 612),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataStatusCodes", LocalizedText.NULL_VALUE, new NodeId(0, 19), 1, null, UInteger.valueOf(0), false),
                new StructureField("DataDiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ParsingResult> {
        @Override
        public Class<ParsingResult> getType() {
            return ParsingResult.class;
        }

        @Override
        public ParsingResult decodeType(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            StatusCode[] dataStatusCodes = decoder.readStatusCodeArray("DataStatusCodes");
            DiagnosticInfo[] dataDiagnosticInfos = decoder.readDiagnosticInfoArray("DataDiagnosticInfos");
            return new ParsingResult(statusCode, dataStatusCodes, dataDiagnosticInfos);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, ParsingResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeStatusCodeArray("DataStatusCodes", value.getDataStatusCodes());
            encoder.writeDiagnosticInfoArray("DataDiagnosticInfos", value.getDataDiagnosticInfos());
        }
    }
}

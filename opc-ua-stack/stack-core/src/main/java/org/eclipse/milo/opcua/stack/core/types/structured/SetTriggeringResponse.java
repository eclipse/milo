package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.5/#5.12.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.5/#5.12.5.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class SetTriggeringResponse extends Structure implements UaResponseMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=776");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=778");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=777");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15333");

    private final ResponseHeader responseHeader;

    private final StatusCode[] addResults;

    private final DiagnosticInfo[] addDiagnosticInfos;

    private final StatusCode[] removeResults;

    private final DiagnosticInfo[] removeDiagnosticInfos;

    public SetTriggeringResponse(ResponseHeader responseHeader, StatusCode[] addResults,
                                 DiagnosticInfo[] addDiagnosticInfos, StatusCode[] removeResults,
                                 DiagnosticInfo[] removeDiagnosticInfos) {
        this.responseHeader = responseHeader;
        this.addResults = addResults;
        this.addDiagnosticInfos = addDiagnosticInfos;
        this.removeResults = removeResults;
        this.removeDiagnosticInfos = removeDiagnosticInfos;
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

    public StatusCode[] getAddResults() {
        return addResults;
    }

    public DiagnosticInfo[] getAddDiagnosticInfos() {
        return addDiagnosticInfos;
    }

    public StatusCode[] getRemoveResults() {
        return removeResults;
    }

    public DiagnosticInfo[] getRemoveDiagnosticInfos() {
        return removeDiagnosticInfos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 778),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("AddResults", LocalizedText.NULL_VALUE, new NodeId(0, 19), 1, null, UInteger.valueOf(0), false),
                new StructureField("AddDiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false),
                new StructureField("RemoveResults", LocalizedText.NULL_VALUE, new NodeId(0, 19), 1, null, UInteger.valueOf(0), false),
                new StructureField("RemoveDiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SetTriggeringResponse> {
        @Override
        public Class<SetTriggeringResponse> getType() {
            return SetTriggeringResponse.class;
        }

        @Override
        public SetTriggeringResponse decodeType(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            StatusCode[] addResults = decoder.readStatusCodeArray("AddResults");
            DiagnosticInfo[] addDiagnosticInfos = decoder.readDiagnosticInfoArray("AddDiagnosticInfos");
            StatusCode[] removeResults = decoder.readStatusCodeArray("RemoveResults");
            DiagnosticInfo[] removeDiagnosticInfos = decoder.readDiagnosticInfoArray("RemoveDiagnosticInfos");
            return new SetTriggeringResponse(responseHeader, addResults, addDiagnosticInfos, removeResults, removeDiagnosticInfos);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SetTriggeringResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeStatusCodeArray("AddResults", value.getAddResults());
            encoder.writeDiagnosticInfoArray("AddDiagnosticInfos", value.getAddDiagnosticInfos());
            encoder.writeStatusCodeArray("RemoveResults", value.getRemoveResults());
            encoder.writeDiagnosticInfoArray("RemoveDiagnosticInfos", value.getRemoveDiagnosticInfos());
        }
    }
}

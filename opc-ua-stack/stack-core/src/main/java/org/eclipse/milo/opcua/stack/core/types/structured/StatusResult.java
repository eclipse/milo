package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.14">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.14</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class StatusResult extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=299");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=301");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=300");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15371");

    private final StatusCode statusCode;

    private final DiagnosticInfo diagnosticInfo;

    public StatusResult(StatusCode statusCode, DiagnosticInfo diagnosticInfo) {
        this.statusCode = statusCode;
        this.diagnosticInfo = diagnosticInfo;
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

    public DiagnosticInfo getDiagnosticInfo() {
        return diagnosticInfo;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 301),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiagnosticInfo", LocalizedText.NULL_VALUE, new NodeId(0, 25), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StatusResult> {
        @Override
        public Class<StatusResult> getType() {
            return StatusResult.class;
        }

        @Override
        public StatusResult decodeType(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            DiagnosticInfo diagnosticInfo = decoder.readDiagnosticInfo("DiagnosticInfo");
            return new StatusResult(statusCode, diagnosticInfo);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, StatusResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeDiagnosticInfo("DiagnosticInfo", value.getDiagnosticInfo());
        }
    }
}

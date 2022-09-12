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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.17">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.17</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class SemanticChangeStructureDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=897");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=899");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=898");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15374");

    private final NodeId affected;

    private final NodeId affectedType;

    public SemanticChangeStructureDataType(NodeId affected, NodeId affectedType) {
        this.affected = affected;
        this.affectedType = affectedType;
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

    public NodeId getAffected() {
        return affected;
    }

    public NodeId getAffectedType() {
        return affectedType;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 899),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Affected", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("AffectedType", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SemanticChangeStructureDataType> {
        @Override
        public Class<SemanticChangeStructureDataType> getType() {
            return SemanticChangeStructureDataType.class;
        }

        @Override
        public SemanticChangeStructureDataType decodeType(SerializationContext context,
                                                          UaDecoder decoder) {
            NodeId affected = decoder.readNodeId("Affected");
            NodeId affectedType = decoder.readNodeId("AffectedType");
            return new SemanticChangeStructureDataType(affected, affectedType);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SemanticChangeStructureDataType value) {
            encoder.writeNodeId("Affected", value.getAffected());
            encoder.writeNodeId("AffectedType", value.getAffectedType());
        }
    }
}

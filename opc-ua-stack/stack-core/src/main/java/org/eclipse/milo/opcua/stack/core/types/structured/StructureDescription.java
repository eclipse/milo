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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.33">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.33</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class StructureDescription extends DataTypeDescription implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15487");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=126");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15589");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15058");

    private final StructureDefinition structureDefinition;

    public StructureDescription(NodeId dataTypeId, QualifiedName name,
                                StructureDefinition structureDefinition) {
        super(dataTypeId, name);
        this.structureDefinition = structureDefinition;
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

    public StructureDefinition getStructureDefinition() {
        return structureDefinition;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 126),
            new NodeId(0, 14525),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DataTypeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 20), -1, null, UInteger.valueOf(0), false),
                new StructureField("StructureDefinition", LocalizedText.NULL_VALUE, new NodeId(0, 99), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StructureDescription> {
        @Override
        public Class<StructureDescription> getType() {
            return StructureDescription.class;
        }

        @Override
        public StructureDescription decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId dataTypeId = decoder.readNodeId("DataTypeId");
            QualifiedName name = decoder.readQualifiedName("Name");
            StructureDefinition structureDefinition = (StructureDefinition) decoder.readStruct("StructureDefinition", StructureDefinition.TYPE_ID);
            return new StructureDescription(dataTypeId, name, structureDefinition);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               StructureDescription value) {
            encoder.writeNodeId("DataTypeId", value.getDataTypeId());
            encoder.writeQualifiedName("Name", value.getName());
            encoder.writeStruct("StructureDefinition", value.getStructureDefinition(), StructureDefinition.TYPE_ID);
        }
    }
}

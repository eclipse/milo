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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.16">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.16</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ModelChangeStructureDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=877");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=879");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=878");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15373");

    private final NodeId affected;

    private final NodeId affectedType;

    private final UByte verb;

    public ModelChangeStructureDataType(NodeId affected, NodeId affectedType, UByte verb) {
        this.affected = affected;
        this.affectedType = affectedType;
        this.verb = verb;
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

    public UByte getVerb() {
        return verb;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 879),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Affected", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("AffectedType", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("Verb", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ModelChangeStructureDataType> {
        @Override
        public Class<ModelChangeStructureDataType> getType() {
            return ModelChangeStructureDataType.class;
        }

        @Override
        public ModelChangeStructureDataType decodeType(SerializationContext context,
                                                       UaDecoder decoder) {
            NodeId affected = decoder.readNodeId("Affected");
            NodeId affectedType = decoder.readNodeId("AffectedType");
            UByte verb = decoder.readByte("Verb");
            return new ModelChangeStructureDataType(affected, affectedType, verb);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ModelChangeStructureDataType value) {
            encoder.writeNodeId("Affected", value.getAffected());
            encoder.writeNodeId("AffectedType", value.getAffectedType());
            encoder.writeByte("Verb", value.getVerb());
        }
    }
}

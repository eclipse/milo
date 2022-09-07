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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12/#12.2.12.4">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12/#12.2.12.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class EnumDefinition extends DataTypeDefinition implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=100");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=123");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=14799");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15067");

    private final EnumField[] fields;

    public EnumDefinition(EnumField[] fields) {
        this.fields = fields;
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

    public EnumField[] getFields() {
        return fields;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 123),
            new NodeId(0, 97),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Fields", LocalizedText.NULL_VALUE, new NodeId(0, 102), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EnumDefinition> {
        @Override
        public Class<EnumDefinition> getType() {
            return EnumDefinition.class;
        }

        @Override
        public EnumDefinition decode(SerializationContext context, UaDecoder decoder) {
            EnumField[] fields = (EnumField[]) decoder.readStructArray("Fields", EnumField.TYPE_ID);
            return new EnumDefinition(fields);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EnumDefinition value) {
            encoder.writeStructArray("Fields", value.getFields(), EnumField.TYPE_ID);
        }
    }
}

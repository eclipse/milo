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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DecimalDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=17861");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=17863");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=17862");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15045");

    private final Short scale;

    private final ByteString value;

    public DecimalDataType(Short scale, ByteString value) {
        this.scale = scale;
        this.value = value;
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

    public Short getScale() {
        return scale;
    }

    public ByteString getValue() {
        return value;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 17863),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Scale", LocalizedText.NULL_VALUE, new NodeId(0, 4), -1, null, UInteger.valueOf(0), false),
                new StructureField("Value", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DecimalDataType> {
        @Override
        public Class<DecimalDataType> getType() {
            return DecimalDataType.class;
        }

        @Override
        public DecimalDataType decode(SerializationContext context, UaDecoder decoder) {
            Short scale = decoder.readInt16("Scale");
            ByteString value = decoder.readByteString("Value");
            return new DecimalDataType(scale, value);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DecimalDataType value) {
            encoder.writeInt16("Scale", value.getScale());
            encoder.writeByteString("Value", value.getValue());
        }
    }
}

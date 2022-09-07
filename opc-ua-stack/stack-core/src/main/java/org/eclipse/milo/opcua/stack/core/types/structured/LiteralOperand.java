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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.4/#7.7.4.3">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.4/#7.7.4.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class LiteralOperand extends FilterOperand implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=595");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=597");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=596");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15208");

    private final Variant value;

    public LiteralOperand(Variant value) {
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

    public Variant getValue() {
        return value;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 597),
            new NodeId(0, 589),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Value", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<LiteralOperand> {
        @Override
        public Class<LiteralOperand> getType() {
            return LiteralOperand.class;
        }

        @Override
        public LiteralOperand decode(SerializationContext context, UaDecoder decoder) {
            Variant value = decoder.readVariant("Value");
            return new LiteralOperand(value);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, LiteralOperand value) {
            encoder.writeVariant("Value", value.getValue());
        }
    }
}

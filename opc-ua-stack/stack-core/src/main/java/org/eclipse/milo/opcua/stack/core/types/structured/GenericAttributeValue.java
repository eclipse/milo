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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.24.10">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.24.10</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class GenericAttributeValue extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=17606");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=17610");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=17608");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15163");

    private final UInteger attributeId;

    private final Variant value;

    public GenericAttributeValue(UInteger attributeId, Variant value) {
        this.attributeId = attributeId;
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

    public UInteger getAttributeId() {
        return attributeId;
    }

    public Variant getValue() {
        return value;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 17610),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("AttributeId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("Value", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<GenericAttributeValue> {
        @Override
        public Class<GenericAttributeValue> getType() {
            return GenericAttributeValue.class;
        }

        @Override
        public GenericAttributeValue decodeType(SerializationContext context, UaDecoder decoder) {
            UInteger attributeId = decoder.readUInt32("AttributeId");
            Variant value = decoder.readVariant("Value");
            return new GenericAttributeValue(attributeId, value);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               GenericAttributeValue value) {
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeVariant("Value", value.getValue());
        }
    }
}

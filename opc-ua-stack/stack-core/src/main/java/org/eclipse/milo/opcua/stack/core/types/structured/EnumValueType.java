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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12/#12.2.12.6">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12/#12.2.12.6</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class EnumValueType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=7594");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=8251");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=7616");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15082");

    private final Long value;

    private final LocalizedText displayName;

    private final LocalizedText description;

    public EnumValueType(Long value, LocalizedText displayName, LocalizedText description) {
        this.value = value;
        this.displayName = displayName;
        this.description = description;
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

    public Long getValue() {
        return value;
    }

    public LocalizedText getDisplayName() {
        return displayName;
    }

    public LocalizedText getDescription() {
        return description;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 8251),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Value", LocalizedText.NULL_VALUE, new NodeId(0, 8), -1, null, UInteger.valueOf(0), false),
                new StructureField("DisplayName", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false),
                new StructureField("Description", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EnumValueType> {
        @Override
        public Class<EnumValueType> getType() {
            return EnumValueType.class;
        }

        @Override
        public EnumValueType decodeType(SerializationContext context, UaDecoder decoder) {
            Long value = decoder.readInt64("Value");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            return new EnumValueType(value, displayName, description);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, EnumValueType value) {
            encoder.writeInt64("Value", value.getValue());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
        }
    }
}

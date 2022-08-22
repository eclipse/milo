package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.jetbrains.annotations.Nullable;

public enum ModelChangeStructureVerbMask implements UaEnumeration {
    NodeAdded(1),

    NodeDeleted(2),

    ReferenceAdded(4),

    ReferenceDeleted(8),

    DataTypeChanged(16);

    private final int value;

    ModelChangeStructureVerbMask(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=11941");
    }

    public static @Nullable ModelChangeStructureVerbMask from(int value) {
        switch (value) {
            case 1:
                return NodeAdded;
            case 2:
                return NodeDeleted;
            case 4:
                return ReferenceAdded;
            case 8:
                return ReferenceDeleted;
            case 16:
                return DataTypeChanged;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "NodeAdded"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "NodeDeleted"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "ReferenceAdded"),
            new EnumField(8L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "ReferenceDeleted"),
            new EnumField(16L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "DataTypeChanged")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<ModelChangeStructureVerbMask> {
        @Override
        public Class<ModelChangeStructureVerbMask> getType() {
            return ModelChangeStructureVerbMask.class;
        }

        @Override
        public ModelChangeStructureVerbMask decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.ModelChangeStructureVerbMask.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ModelChangeStructureVerbMask value) {
            encoder.writeEnum(null, value);
        }
    }
}

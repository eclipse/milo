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

public enum DeadbandType implements UaEnumeration {
    None(0),

    Absolute(1),

    Percent(2);

    private final int value;

    DeadbandType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=718");
    }

    public static @Nullable DeadbandType from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return Absolute;
            case 2:
                return Percent;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "None"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Absolute"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Percent")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<DeadbandType> {
        @Override
        public Class<DeadbandType> getType() {
            return DeadbandType.class;
        }

        @Override
        public DeadbandType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.DeadbandType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DeadbandType value) {
            encoder.writeEnum(null, value);
        }
    }
}

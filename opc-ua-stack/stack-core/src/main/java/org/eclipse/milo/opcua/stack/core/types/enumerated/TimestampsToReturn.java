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

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.40">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.40</a>
 */
public enum TimestampsToReturn implements UaEnumeration {
    Source(0),

    Server(1),

    Both(2),

    Neither(3),

    Invalid(4);

    private final int value;

    TimestampsToReturn(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=625");
    }

    public static @Nullable TimestampsToReturn from(int value) {
        switch (value) {
            case 0:
                return Source;
            case 1:
                return Server;
            case 2:
                return Both;
            case 3:
                return Neither;
            case 4:
                return Invalid;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Source"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Server"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Both"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Neither"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Invalid")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<TimestampsToReturn> {
        @Override
        public Class<TimestampsToReturn> getType() {
            return TimestampsToReturn.class;
        }

        @Override
        public TimestampsToReturn decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, TimestampsToReturn value) {
            encoder.writeEnum(null, value);
        }
    }
}

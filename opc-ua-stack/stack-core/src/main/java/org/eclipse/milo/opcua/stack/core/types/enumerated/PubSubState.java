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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.1</a>
 */
public enum PubSubState implements UaEnumeration {
    Disabled(0),

    Paused(1),

    Operational(2),

    Error(3),

    PreOperational(4);

    private final int value;

    PubSubState(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=14647");
    }

    public static @Nullable PubSubState from(int value) {
        switch (value) {
            case 0:
                return Disabled;
            case 1:
                return Paused;
            case 2:
                return Operational;
            case 3:
                return Error;
            case 4:
                return PreOperational;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Disabled"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Paused"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Operational"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Error"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "PreOperational")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubState> {
        @Override
        public Class<PubSubState> getType() {
            return PubSubState.class;
        }

        @Override
        public PubSubState decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.PubSubState.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, PubSubState value) {
            encoder.writeEnum(null, value);
        }
    }
}

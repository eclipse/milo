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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.7">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.7</a>
 */
public enum TsnTalkerStatus implements UaEnumeration {
    /**
     * No Talker detected.
     */
    None(0),

    /**
     * Talker ready (configured).
     */
    Ready(1),

    /**
     * Talker failed.
     */
    Failed(2);

    private final int value;

    TsnTalkerStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=24222");
    }

    public static @Nullable TsnTalkerStatus from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return Ready;
            case 2:
                return Failed;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, new LocalizedText("", "No Talker detected."), "None"),
            new EnumField(1L, LocalizedText.NULL_VALUE, new LocalizedText("", "Talker ready (configured)."), "Ready"),
            new EnumField(2L, LocalizedText.NULL_VALUE, new LocalizedText("", "Talker failed."), "Failed")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<TsnTalkerStatus> {
        @Override
        public Class<TsnTalkerStatus> getType() {
            return TsnTalkerStatus.class;
        }

        @Override
        public TsnTalkerStatus decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, TsnTalkerStatus.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, TsnTalkerStatus value) {
            encoder.writeEnum(null, value);
        }
    }
}

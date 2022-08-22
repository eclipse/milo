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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.5">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.5</a>
 */
public enum RedundancySupport implements UaEnumeration {
    None(0),

    Cold(1),

    Warm(2),

    Hot(3),

    Transparent(4),

    HotAndMirrored(5);

    private final int value;

    RedundancySupport(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=851");
    }

    public static @Nullable RedundancySupport from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return Cold;
            case 2:
                return Warm;
            case 3:
                return Hot;
            case 4:
                return Transparent;
            case 5:
                return HotAndMirrored;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "None"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Cold"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Warm"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Hot"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Transparent"),
            new EnumField(5L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "HotAndMirrored")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<RedundancySupport> {
        @Override
        public Class<RedundancySupport> getType() {
            return RedundancySupport.class;
        }

        @Override
        public RedundancySupport decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.RedundancySupport.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, RedundancySupport value) {
            encoder.writeEnum(null, value);
        }
    }
}

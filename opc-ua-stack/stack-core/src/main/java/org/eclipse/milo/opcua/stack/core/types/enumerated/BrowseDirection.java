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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.5">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.5</a>
 */
public enum BrowseDirection implements UaEnumeration {
    Forward(0),

    Inverse(1),

    Both(2),

    Invalid(3);

    private final int value;

    BrowseDirection(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=510");
    }

    public static @Nullable BrowseDirection from(int value) {
        switch (value) {
            case 0:
                return Forward;
            case 1:
                return Inverse;
            case 2:
                return Both;
            case 3:
                return Invalid;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Forward"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Inverse"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Both"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Invalid")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseDirection> {
        @Override
        public Class<BrowseDirection> getType() {
            return BrowseDirection.class;
        }

        @Override
        public BrowseDirection decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseDirection value) {
            encoder.writeEnum(null, value);
        }
    }
}

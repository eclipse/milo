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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.1">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.1</a>
 */
public enum Duplex implements UaEnumeration {
    /**
     * Full duplex.
     */
    Full(0),

    /**
     * Half duplex.
     */
    Half(1),

    /**
     * Link is currently disconnected or initializing.
     */
    Unknown(2);

    private final int value;

    Duplex(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TypeInfo.TYPE_ID;
    }

    public static @Nullable Duplex from(int value) {
        switch (value) {
            case 0:
                return Full;
            case 1:
                return Half;
            case 2:
                return Unknown;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, new LocalizedText("", "Full duplex."), "Full"),
            new EnumField(1L, LocalizedText.NULL_VALUE, new LocalizedText("", "Half duplex."), "Half"),
            new EnumField(2L, LocalizedText.NULL_VALUE, new LocalizedText("", "Link is currently disconnected or initializing."), "Unknown")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<Duplex> {
        @Override
        public Class<Duplex> getType() {
            return Duplex.class;
        }

        @Override
        public Duplex decodeType(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, Duplex.class);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, Duplex value) {
            encoder.writeEnum(null, value);
        }
    }

    public static final class TypeInfo {
        public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=24210");
    }
}

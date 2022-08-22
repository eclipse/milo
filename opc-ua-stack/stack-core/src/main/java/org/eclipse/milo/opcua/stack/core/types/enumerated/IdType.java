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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.5/#12.2.5.1">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.5/#12.2.5.1</a>
 */
public enum IdType implements UaEnumeration {
    Numeric(0),

    String(1),

    Guid(2),

    Opaque(3);

    private final int value;

    IdType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=256");
    }

    public static @Nullable IdType from(int value) {
        switch (value) {
            case 0:
                return Numeric;
            case 1:
                return String;
            case 2:
                return Guid;
            case 3:
                return Opaque;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Numeric"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "String"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Guid"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Opaque")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<IdType> {
        @Override
        public Class<IdType> getType() {
            return IdType.class;
        }

        @Override
        public IdType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.IdType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, IdType value) {
            encoder.writeEnum(null, value);
        }
    }
}

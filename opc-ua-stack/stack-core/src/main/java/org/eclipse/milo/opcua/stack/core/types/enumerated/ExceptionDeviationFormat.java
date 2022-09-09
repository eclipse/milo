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
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/5.2.2">https://reference.opcfoundation.org/v104/Core/docs/Part11/5.2.2</a>
 */
public enum ExceptionDeviationFormat implements UaEnumeration {
    AbsoluteValue(0),

    PercentOfValue(1),

    PercentOfRange(2),

    PercentOfEuRange(3),

    Unknown(4);

    private final int value;

    ExceptionDeviationFormat(int value) {
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

    public static @Nullable ExceptionDeviationFormat from(int value) {
        switch (value) {
            case 0:
                return AbsoluteValue;
            case 1:
                return PercentOfValue;
            case 2:
                return PercentOfRange;
            case 3:
                return PercentOfEuRange;
            case 4:
                return Unknown;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "AbsoluteValue"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "PercentOfValue"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "PercentOfRange"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "PercentOfEURange"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Unknown")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<ExceptionDeviationFormat> {
        @Override
        public Class<ExceptionDeviationFormat> getType() {
            return ExceptionDeviationFormat.class;
        }

        @Override
        public ExceptionDeviationFormat decodeType(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, ExceptionDeviationFormat.class);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ExceptionDeviationFormat value) {
            encoder.writeEnum(null, value);
        }
    }

    public static final class TypeInfo {
        public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=890");
    }
}

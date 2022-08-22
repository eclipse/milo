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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.7">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.6.7</a>
 */
public enum AxisScaleEnumeration implements UaEnumeration {
    Linear(0),

    Log(1),

    Ln(2);

    private final int value;

    AxisScaleEnumeration(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=12077");
    }

    public static @Nullable AxisScaleEnumeration from(int value) {
        switch (value) {
            case 0:
                return Linear;
            case 1:
                return Log;
            case 2:
                return Ln;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Linear"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Log"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Ln")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<AxisScaleEnumeration> {
        @Override
        public Class<AxisScaleEnumeration> getType() {
            return AxisScaleEnumeration.class;
        }

        @Override
        public AxisScaleEnumeration decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           AxisScaleEnumeration value) {
            encoder.writeEnum(null, value);
        }
    }
}

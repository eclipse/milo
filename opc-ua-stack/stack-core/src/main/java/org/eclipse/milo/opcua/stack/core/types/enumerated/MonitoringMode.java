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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.23">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.23</a>
 */
public enum MonitoringMode implements UaEnumeration {
    Disabled(0),

    Sampling(1),

    Reporting(2);

    private final int value;

    MonitoringMode(int value) {
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

    public static @Nullable MonitoringMode from(int value) {
        switch (value) {
            case 0:
                return Disabled;
            case 1:
                return Sampling;
            case 2:
                return Reporting;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Disabled"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Sampling"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Reporting")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoringMode> {
        @Override
        public Class<MonitoringMode> getType() {
            return MonitoringMode.class;
        }

        @Override
        public MonitoringMode decodeType(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, MonitoringMode.class);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, MonitoringMode value) {
            encoder.writeEnum(null, value);
        }
    }

    public static final class TypeInfo {
        public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=716");
    }
}

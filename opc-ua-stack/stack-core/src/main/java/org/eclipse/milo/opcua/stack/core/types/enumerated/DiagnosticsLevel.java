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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.4</a>
 */
public enum DiagnosticsLevel implements UaEnumeration {
    Basic(0),

    Advanced(1),

    Info(2),

    Log(3),

    Debug(4);

    private final int value;

    DiagnosticsLevel(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=19723");
    }

    public static @Nullable DiagnosticsLevel from(int value) {
        switch (value) {
            case 0:
                return Basic;
            case 1:
                return Advanced;
            case 2:
                return Info;
            case 3:
                return Log;
            case 4:
                return Debug;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Basic"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Advanced"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Info"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Log"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Debug")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<DiagnosticsLevel> {
        @Override
        public Class<DiagnosticsLevel> getType() {
            return DiagnosticsLevel.class;
        }

        @Override
        public DiagnosticsLevel decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, DiagnosticsLevel.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DiagnosticsLevel value) {
            encoder.writeEnum(null, value);
        }
    }
}

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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.6">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.6</a>
 */
public enum PubSubDiagnosticsCounterClassification implements UaEnumeration {
    Information(0),

    Error(1);

    private final int value;

    PubSubDiagnosticsCounterClassification(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=19730");
    }

    public static @Nullable PubSubDiagnosticsCounterClassification from(int value) {
        switch (value) {
            case 0:
                return Information;
            case 1:
                return Error;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Information"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Error")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubDiagnosticsCounterClassification> {
        @Override
        public Class<PubSubDiagnosticsCounterClassification> getType() {
            return PubSubDiagnosticsCounterClassification.class;
        }

        @Override
        public PubSubDiagnosticsCounterClassification decode(SerializationContext context,
                                                             UaDecoder decoder) {
            return decoder.readEnum(null, PubSubDiagnosticsCounterClassification.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PubSubDiagnosticsCounterClassification value) {
            encoder.writeEnum(null, value);
        }
    }
}
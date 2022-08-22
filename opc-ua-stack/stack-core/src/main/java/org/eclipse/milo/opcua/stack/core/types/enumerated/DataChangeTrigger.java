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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.10">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.10</a>
 */
public enum DataChangeTrigger implements UaEnumeration {
    Status(0),

    StatusValue(1),

    StatusValueTimestamp(2);

    private final int value;

    DataChangeTrigger(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=717");
    }

    public static @Nullable DataChangeTrigger from(int value) {
        switch (value) {
            case 0:
                return Status;
            case 1:
                return StatusValue;
            case 2:
                return StatusValueTimestamp;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Status"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "StatusValue"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "StatusValueTimestamp")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<DataChangeTrigger> {
        @Override
        public Class<DataChangeTrigger> getType() {
            return DataChangeTrigger.class;
        }

        @Override
        public DataChangeTrigger decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DataChangeTrigger value) {
            encoder.writeEnum(null, value);
        }
    }
}

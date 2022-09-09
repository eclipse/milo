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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.10">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.10</a>
 */
public enum MessageSecurityMode implements UaEnumeration {
    Invalid(0),

    None(1),

    Sign(2),

    SignAndEncrypt(3);

    private final int value;

    MessageSecurityMode(int value) {
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

    public static @Nullable MessageSecurityMode from(int value) {
        switch (value) {
            case 0:
                return Invalid;
            case 1:
                return None;
            case 2:
                return Sign;
            case 3:
                return SignAndEncrypt;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Invalid"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "None"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Sign"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "SignAndEncrypt")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<MessageSecurityMode> {
        @Override
        public Class<MessageSecurityMode> getType() {
            return MessageSecurityMode.class;
        }

        @Override
        public MessageSecurityMode decodeType(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, MessageSecurityMode.class);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               MessageSecurityMode value) {
            encoder.writeEnum(null, value);
        }
    }

    public static final class TypeInfo {
        public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=302");
    }
}

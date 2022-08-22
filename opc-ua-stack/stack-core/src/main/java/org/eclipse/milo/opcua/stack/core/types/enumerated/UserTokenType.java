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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.43">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.43</a>
 */
public enum UserTokenType implements UaEnumeration {
    Anonymous(0),

    UserName(1),

    Certificate(2),

    IssuedToken(3);

    private final int value;

    UserTokenType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=303");
    }

    public static @Nullable UserTokenType from(int value) {
        switch (value) {
            case 0:
                return Anonymous;
            case 1:
                return UserName;
            case 2:
                return Certificate;
            case 3:
                return IssuedToken;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Anonymous"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "UserName"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Certificate"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "IssuedToken")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<UserTokenType> {
        @Override
        public Class<UserTokenType> getType() {
            return UserTokenType.class;
        }

        @Override
        public UserTokenType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, UserTokenType value) {
            encoder.writeEnum(null, value);
        }
    }
}

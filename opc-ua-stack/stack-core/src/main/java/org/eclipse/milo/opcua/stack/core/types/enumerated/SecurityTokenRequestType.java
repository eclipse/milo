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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.12">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.12</a>
 */
public enum SecurityTokenRequestType implements UaEnumeration {
    Issue(0),

    Renew(1);

    private final int value;

    SecurityTokenRequestType(int value) {
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

    public static @Nullable SecurityTokenRequestType from(int value) {
        switch (value) {
            case 0:
                return Issue;
            case 1:
                return Renew;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Issue"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Renew")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<SecurityTokenRequestType> {
        @Override
        public Class<SecurityTokenRequestType> getType() {
            return SecurityTokenRequestType.class;
        }

        @Override
        public SecurityTokenRequestType decodeType(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, SecurityTokenRequestType.class);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SecurityTokenRequestType value) {
            encoder.writeEnum(null, value);
        }
    }

    public static final class TypeInfo {
        public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=315");
    }
}

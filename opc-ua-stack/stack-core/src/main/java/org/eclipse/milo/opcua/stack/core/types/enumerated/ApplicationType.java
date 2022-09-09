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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.4">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.4</a>
 */
public enum ApplicationType implements UaEnumeration {
    Server(0),

    Client(1),

    ClientAndServer(2),

    DiscoveryServer(3);

    private final int value;

    ApplicationType(int value) {
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

    public static @Nullable ApplicationType from(int value) {
        switch (value) {
            case 0:
                return Server;
            case 1:
                return Client;
            case 2:
                return ClientAndServer;
            case 3:
                return DiscoveryServer;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Server"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Client"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "ClientAndServer"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "DiscoveryServer")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<ApplicationType> {
        @Override
        public Class<ApplicationType> getType() {
            return ApplicationType.class;
        }

        @Override
        public ApplicationType decodeType(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, ApplicationType.class);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, ApplicationType value) {
            encoder.writeEnum(null, value);
        }
    }

    public static final class TypeInfo {
        public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=307");
    }
}

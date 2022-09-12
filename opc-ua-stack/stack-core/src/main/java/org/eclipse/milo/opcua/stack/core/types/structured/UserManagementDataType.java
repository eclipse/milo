package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part18/5.2.4">https://reference.opcfoundation.org/v105/Core/docs/Part18/5.2.4</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class UserManagementDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=24281");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=24292");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=24296");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=24300");

    private final String userName;

    private final UserConfigurationMask userConfiguration;

    private final String description;

    public UserManagementDataType(String userName, UserConfigurationMask userConfiguration,
                                  String description) {
        this.userName = userName;
        this.userConfiguration = userConfiguration;
        this.description = description;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public String getUserName() {
        return userName;
    }

    public UserConfigurationMask getUserConfiguration() {
        return userConfiguration;
    }

    public String getDescription() {
        return description;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 24292),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("UserName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("UserConfiguration", LocalizedText.NULL_VALUE, new NodeId(0, 24279), -1, null, UInteger.valueOf(0), false),
                new StructureField("Description", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<UserManagementDataType> {
        @Override
        public Class<UserManagementDataType> getType() {
            return UserManagementDataType.class;
        }

        @Override
        public UserManagementDataType decodeType(SerializationContext context, UaDecoder decoder) {
            String userName = decoder.readString("UserName");
            UserConfigurationMask userConfiguration = new UserConfigurationMask(decoder.readUInt32("UserConfiguration"));
            String description = decoder.readString("Description");
            return new UserManagementDataType(userName, userConfiguration, description);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               UserManagementDataType value) {
            encoder.writeString("UserName", value.getUserName());
            encoder.writeUInt32("UserConfiguration", value.getUserConfiguration().getValue());
            encoder.writeString("Description", value.getDescription());
        }
    }
}

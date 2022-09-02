package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.12/#6.2.12.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.12/#6.2.12.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class SecurityGroupDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23601");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23853");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23921");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23989");

    private final String name;

    private final String[] securityGroupFolder;

    private final Double keyLifetime;

    private final String securityPolicyUri;

    private final UInteger maxFutureKeyCount;

    private final UInteger maxPastKeyCount;

    private final String securityGroupId;

    private final RolePermissionType[] rolePermissions;

    private final KeyValuePair[] groupProperties;

    public SecurityGroupDataType(String name, String[] securityGroupFolder, Double keyLifetime,
                                 String securityPolicyUri, UInteger maxFutureKeyCount, UInteger maxPastKeyCount,
                                 String securityGroupId, RolePermissionType[] rolePermissions,
                                 KeyValuePair[] groupProperties) {
        this.name = name;
        this.securityGroupFolder = securityGroupFolder;
        this.keyLifetime = keyLifetime;
        this.securityPolicyUri = securityPolicyUri;
        this.maxFutureKeyCount = maxFutureKeyCount;
        this.maxPastKeyCount = maxPastKeyCount;
        this.securityGroupId = securityGroupId;
        this.rolePermissions = rolePermissions;
        this.groupProperties = groupProperties;
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

    public String getName() {
        return name;
    }

    public String[] getSecurityGroupFolder() {
        return securityGroupFolder;
    }

    public Double getKeyLifetime() {
        return keyLifetime;
    }

    public String getSecurityPolicyUri() {
        return securityPolicyUri;
    }

    public UInteger getMaxFutureKeyCount() {
        return maxFutureKeyCount;
    }

    public UInteger getMaxPastKeyCount() {
        return maxPastKeyCount;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public RolePermissionType[] getRolePermissions() {
        return rolePermissions;
    }

    public KeyValuePair[] getGroupProperties() {
        return groupProperties;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23853),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityGroupFolder", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("KeyLifetime", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityPolicyUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("MaxFutureKeyCount", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("MaxPastKeyCount", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityGroupId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("RolePermissions", LocalizedText.NULL_VALUE, new NodeId(0, 96), 1, null, UInteger.valueOf(0), false),
                new StructureField("GroupProperties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SecurityGroupDataType> {
        @Override
        public Class<SecurityGroupDataType> getType() {
            return SecurityGroupDataType.class;
        }

        @Override
        public SecurityGroupDataType decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            String[] securityGroupFolder = decoder.readStringArray("SecurityGroupFolder");
            Double keyLifetime = decoder.readDouble("KeyLifetime");
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");
            UInteger maxFutureKeyCount = decoder.readUInt32("MaxFutureKeyCount");
            UInteger maxPastKeyCount = decoder.readUInt32("MaxPastKeyCount");
            String securityGroupId = decoder.readString("SecurityGroupId");
            RolePermissionType[] rolePermissions = (RolePermissionType[]) decoder.readStructArray("RolePermissions", RolePermissionType.TYPE_ID);
            KeyValuePair[] groupProperties = (KeyValuePair[]) decoder.readStructArray("GroupProperties", KeyValuePair.TYPE_ID);
            return new SecurityGroupDataType(name, securityGroupFolder, keyLifetime, securityPolicyUri, maxFutureKeyCount, maxPastKeyCount, securityGroupId, rolePermissions, groupProperties);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SecurityGroupDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeStringArray("SecurityGroupFolder", value.getSecurityGroupFolder());
            encoder.writeDouble("KeyLifetime", value.getKeyLifetime());
            encoder.writeString("SecurityPolicyUri", value.getSecurityPolicyUri());
            encoder.writeUInt32("MaxFutureKeyCount", value.getMaxFutureKeyCount());
            encoder.writeUInt32("MaxPastKeyCount", value.getMaxPastKeyCount());
            encoder.writeString("SecurityGroupId", value.getSecurityGroupId());
            encoder.writeStructArray("RolePermissions", value.getRolePermissions(), RolePermissionType.TYPE_ID);
            encoder.writeStructArray("GroupProperties", value.getGroupProperties(), KeyValuePair.TYPE_ID);
        }
    }
}

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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.3.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.3.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class SubscribedDataSetMirrorDataType extends SubscribedDataSetDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15635");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15713");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16012");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16311");

    private final String parentNodeName;

    private final RolePermissionType[] rolePermissions;

    public SubscribedDataSetMirrorDataType(String parentNodeName,
                                           RolePermissionType[] rolePermissions) {
        this.parentNodeName = parentNodeName;
        this.rolePermissions = rolePermissions;
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

    public String getParentNodeName() {
        return parentNodeName;
    }

    public RolePermissionType[] getRolePermissions() {
        return rolePermissions;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15713),
            new NodeId(0, 15630),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ParentNodeName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("RolePermissions", LocalizedText.NULL_VALUE, new NodeId(0, 96), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SubscribedDataSetMirrorDataType> {
        @Override
        public Class<SubscribedDataSetMirrorDataType> getType() {
            return SubscribedDataSetMirrorDataType.class;
        }

        @Override
        public SubscribedDataSetMirrorDataType decodeType(SerializationContext context,
                                                          UaDecoder decoder) {
            String parentNodeName = decoder.readString("ParentNodeName");
            RolePermissionType[] rolePermissions = (RolePermissionType[]) decoder.readStructArray("RolePermissions", RolePermissionType.TYPE_ID);
            return new SubscribedDataSetMirrorDataType(parentNodeName, rolePermissions);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SubscribedDataSetMirrorDataType value) {
            encoder.writeString("ParentNodeName", value.getParentNodeName());
            encoder.writeStructArray("RolePermissions", value.getRolePermissions(), RolePermissionType.TYPE_ID);
        }
    }
}

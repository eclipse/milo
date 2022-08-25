package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.PriorityMappingEntryType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class PriorityMappingTableTypeNode extends BaseObjectTypeNode implements PriorityMappingTableType {
    public PriorityMappingTableTypeNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                        RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                        UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    public PriorityMappingTableTypeNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                        RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions);
    }

    @Override
    public PropertyTypeNode getPriorityMapppingEntriesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(PriorityMappingTableType.PRIORITY_MAPPPING_ENTRIES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public PriorityMappingEntryType[] getPriorityMapppingEntries() {
        return getProperty(PriorityMappingTableType.PRIORITY_MAPPPING_ENTRIES).orElse(null);
    }

    @Override
    public void setPriorityMapppingEntries(PriorityMappingEntryType[] value) {
        setProperty(PriorityMappingTableType.PRIORITY_MAPPPING_ENTRIES, value);
    }

    @Override
    public MethodNode getAddPriorityMappingEntryMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddPriorityMappingEntry", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getDeletePriorityMappingEntryMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "DeletePriorityMappingEntry", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}

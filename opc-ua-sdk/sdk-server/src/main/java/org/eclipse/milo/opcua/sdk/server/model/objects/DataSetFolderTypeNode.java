package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class DataSetFolderTypeNode extends FolderTypeNode implements DataSetFolderType {
    public DataSetFolderTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                 RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                 UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    public DataSetFolderTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                 RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions);
    }

    @Override
    public MethodNode getAddPublishedDataItemsMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddPublishedDataItems", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getAddPublishedEventsMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddPublishedEvents", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getAddPublishedDataItemsTemplateMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddPublishedDataItemsTemplate", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getAddPublishedEventsTemplateMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddPublishedEventsTemplate", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getRemovePublishedDataSetMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "RemovePublishedDataSet", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getAddDataSetFolderMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddDataSetFolder", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getRemoveDataSetFolderMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "RemoveDataSetFolder", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}

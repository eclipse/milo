package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class TrustListTypeNode extends FileTypeNode implements TrustListType {
    public TrustListTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask, RolePermissionType[] rolePermissions,
                             RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                             UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    public TrustListTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask, RolePermissionType[] rolePermissions,
                             RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions);
    }

    @Override
    public PropertyTypeNode getLastUpdateTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TrustListType.LAST_UPDATE_TIME);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getLastUpdateTime() {
        return getProperty(TrustListType.LAST_UPDATE_TIME).orElse(null);
    }

    @Override
    public void setLastUpdateTime(DateTime value) {
        setProperty(TrustListType.LAST_UPDATE_TIME, value);
    }

    @Override
    public PropertyTypeNode getUpdateFrequencyNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TrustListType.UPDATE_FREQUENCY);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Double getUpdateFrequency() {
        return getProperty(TrustListType.UPDATE_FREQUENCY).orElse(null);
    }

    @Override
    public void setUpdateFrequency(Double value) {
        setProperty(TrustListType.UPDATE_FREQUENCY, value);
    }

    @Override
    public MethodNode getOpenWithMasksMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "OpenWithMasks", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getCloseAndUpdateMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "CloseAndUpdate", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getAddCertificateMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddCertificate", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public MethodNode getRemoveCertificateMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "RemoveCertificate", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}

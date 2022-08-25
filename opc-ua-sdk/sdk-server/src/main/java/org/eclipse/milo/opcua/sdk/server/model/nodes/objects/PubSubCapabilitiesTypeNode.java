/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.PubSubCapabilitiesType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class PubSubCapabilitiesTypeNode extends BaseObjectTypeNode implements PubSubCapabilitiesType {
    public PubSubCapabilitiesTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                      RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                      UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    public PubSubCapabilitiesTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                      RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions);
    }

    @Override
    public PropertyTypeNode getMaxPubSubConnectionsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(PubSubCapabilitiesType.MAX_PUB_SUB_CONNECTIONS);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxPubSubConnections() {
        return getProperty(PubSubCapabilitiesType.MAX_PUB_SUB_CONNECTIONS).orElse(null);
    }

    @Override
    public void setMaxPubSubConnections(UInteger value) {
        setProperty(PubSubCapabilitiesType.MAX_PUB_SUB_CONNECTIONS, value);
    }

    @Override
    public PropertyTypeNode getMaxWriterGroupsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(PubSubCapabilitiesType.MAX_WRITER_GROUPS);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxWriterGroups() {
        return getProperty(PubSubCapabilitiesType.MAX_WRITER_GROUPS).orElse(null);
    }

    @Override
    public void setMaxWriterGroups(UInteger value) {
        setProperty(PubSubCapabilitiesType.MAX_WRITER_GROUPS, value);
    }

    @Override
    public PropertyTypeNode getMaxReaderGroupsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(PubSubCapabilitiesType.MAX_READER_GROUPS);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxReaderGroups() {
        return getProperty(PubSubCapabilitiesType.MAX_READER_GROUPS).orElse(null);
    }

    @Override
    public void setMaxReaderGroups(UInteger value) {
        setProperty(PubSubCapabilitiesType.MAX_READER_GROUPS, value);
    }

    @Override
    public PropertyTypeNode getMaxDataSetWritersNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(PubSubCapabilitiesType.MAX_DATA_SET_WRITERS);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxDataSetWriters() {
        return getProperty(PubSubCapabilitiesType.MAX_DATA_SET_WRITERS).orElse(null);
    }

    @Override
    public void setMaxDataSetWriters(UInteger value) {
        setProperty(PubSubCapabilitiesType.MAX_DATA_SET_WRITERS, value);
    }

    @Override
    public PropertyTypeNode getMaxDataSetReadersNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(PubSubCapabilitiesType.MAX_DATA_SET_READERS);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxDataSetReaders() {
        return getProperty(PubSubCapabilitiesType.MAX_DATA_SET_READERS).orElse(null);
    }

    @Override
    public void setMaxDataSetReaders(UInteger value) {
        setProperty(PubSubCapabilitiesType.MAX_DATA_SET_READERS, value);
    }

    @Override
    public PropertyTypeNode getMaxFieldsPerDataSetNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(PubSubCapabilitiesType.MAX_FIELDS_PER_DATA_SET);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getMaxFieldsPerDataSet() {
        return getProperty(PubSubCapabilitiesType.MAX_FIELDS_PER_DATA_SET).orElse(null);
    }

    @Override
    public void setMaxFieldsPerDataSet(UInteger value) {
        setProperty(PubSubCapabilitiesType.MAX_FIELDS_PER_DATA_SET, value);
    }
}
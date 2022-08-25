/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.MultiStateDictionaryEntryDiscreteBaseType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessLevelExType;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class MultiStateDictionaryEntryDiscreteBaseTypeNode extends MultiStateValueDiscreteTypeNode implements MultiStateDictionaryEntryDiscreteBaseType {
    public MultiStateDictionaryEntryDiscreteBaseTypeNode(UaNodeContext context, NodeId nodeId,
                                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                         UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                                         RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                                         DataValue value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions,
                                                         UByte accessLevel, UByte userAccessLevel, Double minimumSamplingInterval, boolean historizing,
                                                         AccessLevelExType accessLevelEx) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing, accessLevelEx);
    }

    public MultiStateDictionaryEntryDiscreteBaseTypeNode(UaNodeContext context, NodeId nodeId,
                                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                         UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                                         RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                                         DataValue value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, value, dataType, valueRank, arrayDimensions);
    }

    @Override
    public PropertyTypeNode getEnumDictionaryEntriesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(MultiStateDictionaryEntryDiscreteBaseType.ENUM_DICTIONARY_ENTRIES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Object getEnumDictionaryEntries() {
        return getProperty(MultiStateDictionaryEntryDiscreteBaseType.ENUM_DICTIONARY_ENTRIES).orElse(null);
    }

    @Override
    public void setEnumDictionaryEntries(Object value) {
        setProperty(MultiStateDictionaryEntryDiscreteBaseType.ENUM_DICTIONARY_ENTRIES, value);
    }

    @Override
    public PropertyTypeNode getValueAsDictionaryEntriesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(MultiStateDictionaryEntryDiscreteBaseType.VALUE_AS_DICTIONARY_ENTRIES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public NodeId[] getValueAsDictionaryEntries() {
        return getProperty(MultiStateDictionaryEntryDiscreteBaseType.VALUE_AS_DICTIONARY_ENTRIES).orElse(null);
    }

    @Override
    public void setValueAsDictionaryEntries(NodeId[] value) {
        setProperty(MultiStateDictionaryEntryDiscreteBaseType.VALUE_AS_DICTIONARY_ENTRIES, value);
    }
}
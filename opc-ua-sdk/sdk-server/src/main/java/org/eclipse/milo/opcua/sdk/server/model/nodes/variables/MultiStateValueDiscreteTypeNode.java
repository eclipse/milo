/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.MultiStateValueDiscreteType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;

public class MultiStateValueDiscreteTypeNode extends DiscreteItemTypeNode implements MultiStateValueDiscreteType {
    public MultiStateValueDiscreteTypeNode(UaNodeContext context, NodeId nodeId,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public MultiStateValueDiscreteTypeNode(UaNodeContext context, NodeId nodeId,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                           Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                           double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public PropertyTypeNode getEnumValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(MultiStateValueDiscreteType.ENUM_VALUES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public EnumValueType[] getEnumValues() {
        Optional<EnumValueType[]> propertyValue = getProperty(MultiStateValueDiscreteType.ENUM_VALUES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEnumValues(EnumValueType[] value) {
        setProperty(MultiStateValueDiscreteType.ENUM_VALUES, value);
    }

    @Override
    public PropertyTypeNode getValueAsTextNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(MultiStateValueDiscreteType.VALUE_AS_TEXT);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public LocalizedText getValueAsText() {
        Optional<LocalizedText> propertyValue = getProperty(MultiStateValueDiscreteType.VALUE_AS_TEXT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setValueAsText(LocalizedText value) {
        setProperty(MultiStateValueDiscreteType.VALUE_AS_TEXT, value);
    }
}

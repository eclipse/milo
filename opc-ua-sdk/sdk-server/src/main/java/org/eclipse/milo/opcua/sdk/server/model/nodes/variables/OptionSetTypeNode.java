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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.OptionSetType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class OptionSetTypeNode extends BaseDataVariableTypeNode implements OptionSetType {
    public OptionSetTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public OptionSetTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                             UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                             double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public PropertyTypeNode getOptionSetValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OptionSetType.OPTION_SET_VALUES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public LocalizedText[] getOptionSetValues() {
        Optional<LocalizedText[]> propertyValue = getProperty(OptionSetType.OPTION_SET_VALUES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setOptionSetValues(LocalizedText[] value) {
        setProperty(OptionSetType.OPTION_SET_VALUES, value);
    }

    @Override
    public PropertyTypeNode getBitMaskNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(OptionSetType.BIT_MASK);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean[] getBitMask() {
        Optional<Boolean[]> propertyValue = getProperty(OptionSetType.BIT_MASK);
        return propertyValue.orElse(null);
    }

    @Override
    public void setBitMask(Boolean[] value) {
        setProperty(OptionSetType.BIT_MASK, value);
    }
}

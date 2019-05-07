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

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.DataItemType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class DataItemTypeNode extends BaseDataVariableTypeNode implements DataItemType {
    public DataItemTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                            LocalizedText displayName, LocalizedText description, UInteger writeMask,
                            UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public DataItemTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                            LocalizedText displayName, LocalizedText description, UInteger writeMask,
                            UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                            UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                            double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public PropertyTypeNode getDefinitionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DataItemType.DEFINITION);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public String getDefinition() {
        Optional<String> propertyValue = getProperty(DataItemType.DEFINITION);
        return propertyValue.orElse(null);
    }

    @Override
    public void setDefinition(String value) {
        setProperty(DataItemType.DEFINITION, value);
    }

    @Override
    public PropertyTypeNode getValuePrecisionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DataItemType.VALUE_PRECISION);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Double getValuePrecision() {
        Optional<Double> propertyValue = getProperty(DataItemType.VALUE_PRECISION);
        return propertyValue.orElse(null);
    }

    @Override
    public void setValuePrecision(Double value) {
        setProperty(DataItemType.VALUE_PRECISION, value);
    }
}

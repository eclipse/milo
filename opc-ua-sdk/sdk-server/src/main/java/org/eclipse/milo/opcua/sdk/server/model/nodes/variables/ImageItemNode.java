/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ImageItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:ImageItemType")
public class ImageItemNode extends ArrayItemNode implements ImageItemType {

    public ImageItemNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeManager, nodeId, variableTypeNode);
    }

    public ImageItemNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        DataValue value,
        NodeId dataType,
        Integer valueRank,
        UInteger[] arrayDimensions,
        UByte accessLevel,
        UByte userAccessLevel,
        Double minimumSamplingInterval,
        boolean historizing) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask,
            value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }


    @Override
    public AxisInformation getXAxisDefinition() {
        Optional<AxisInformation> property = getProperty(ImageItemType.X_AXIS_DEFINITION);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getXAxisDefinitionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ImageItemType.X_AXIS_DEFINITION.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setXAxisDefinition(AxisInformation value) {
        setProperty(ImageItemType.X_AXIS_DEFINITION, value);
    }

    @Override
    public AxisInformation getYAxisDefinition() {
        Optional<AxisInformation> property = getProperty(ImageItemType.Y_AXIS_DEFINITION);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getYAxisDefinitionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ImageItemType.Y_AXIS_DEFINITION.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setYAxisDefinition(AxisInformation value) {
        setProperty(ImageItemType.Y_AXIS_DEFINITION, value);
    }

}

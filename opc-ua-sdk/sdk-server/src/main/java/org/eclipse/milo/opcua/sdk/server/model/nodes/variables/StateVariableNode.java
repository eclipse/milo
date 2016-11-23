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

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.StateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:StateVariableType")
public class StateVariableNode extends BaseDataVariableNode implements StateVariableType {

    public StateVariableNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeMap, nodeId, variableTypeNode);
    }

    public StateVariableNode(
        ServerNodeMap nodeMap,
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

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask,
            value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }


    @Override
    public Object getId() {
        Optional<Object> property = getProperty(StateVariableType.ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(StateVariableType.ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setId(Object value) {
        setProperty(StateVariableType.ID, value);
    }

    @Override
    public QualifiedName getName() {
        Optional<QualifiedName> property = getProperty(StateVariableType.NAME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(StateVariableType.NAME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setName(QualifiedName value) {
        setProperty(StateVariableType.NAME, value);
    }

    @Override
    public UInteger getNumber() {
        Optional<UInteger> property = getProperty(StateVariableType.NUMBER);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNumberNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(StateVariableType.NUMBER.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setNumber(UInteger value) {
        setProperty(StateVariableType.NUMBER, value);
    }

    @Override
    public LocalizedText getEffectiveDisplayName() {
        Optional<LocalizedText> property = getProperty(StateVariableType.EFFECTIVE_DISPLAY_NAME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEffectiveDisplayNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(StateVariableType.EFFECTIVE_DISPLAY_NAME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEffectiveDisplayName(LocalizedText value) {
        setProperty(StateVariableType.EFFECTIVE_DISPLAY_NAME, value);
    }

}

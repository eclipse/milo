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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:TwoStateVariableType")
public class TwoStateVariableNode extends StateVariableNode implements TwoStateVariableType {

    public TwoStateVariableNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeMap, nodeId, variableTypeNode);
    }

    public TwoStateVariableNode(
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
    public Boolean getId() {
        Optional<Boolean> property = getProperty(TwoStateVariableType.ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TwoStateVariableType.ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setId(Boolean value) {
        setProperty(TwoStateVariableType.ID, value);
    }

    @Override
    public DateTime getTransitionTime() {
        Optional<DateTime> property = getProperty(TwoStateVariableType.TRANSITION_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getTransitionTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TwoStateVariableType.TRANSITION_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setTransitionTime(DateTime value) {
        setProperty(TwoStateVariableType.TRANSITION_TIME, value);
    }

    @Override
    public DateTime getEffectiveTransitionTime() {
        Optional<DateTime> property = getProperty(TwoStateVariableType.EFFECTIVE_TRANSITION_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEffectiveTransitionTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TwoStateVariableType.EFFECTIVE_TRANSITION_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEffectiveTransitionTime(DateTime value) {
        setProperty(TwoStateVariableType.EFFECTIVE_TRANSITION_TIME, value);
    }

    @Override
    public LocalizedText getTrueState() {
        Optional<LocalizedText> property = getProperty(TwoStateVariableType.TRUE_STATE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getTrueStateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TwoStateVariableType.TRUE_STATE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setTrueState(LocalizedText value) {
        setProperty(TwoStateVariableType.TRUE_STATE, value);
    }

    @Override
    public LocalizedText getFalseState() {
        Optional<LocalizedText> property = getProperty(TwoStateVariableType.FALSE_STATE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getFalseStateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TwoStateVariableType.FALSE_STATE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setFalseState(LocalizedText value) {
        setProperty(TwoStateVariableType.FALSE_STATE, value);
    }

}

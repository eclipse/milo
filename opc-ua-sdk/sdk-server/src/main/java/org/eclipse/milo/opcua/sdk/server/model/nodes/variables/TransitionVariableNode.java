/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TransitionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class TransitionVariableNode extends BaseDataVariableNode implements TransitionVariableType {
    public TransitionVariableNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                  LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                  UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public TransitionVariableNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                  LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                  UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                                  UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                  double minimumSamplingInterval, boolean historizing) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    public PropertyNode getIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Object getId() {
        Optional<Object> propertyValue = getProperty(TransitionVariableType.ID);
        return propertyValue.orElse(null);
    }

    public void setId(Object value) {
        setProperty(TransitionVariableType.ID, value);
    }

    public PropertyNode getNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.NAME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public QualifiedName getName() {
        Optional<QualifiedName> propertyValue = getProperty(TransitionVariableType.NAME);
        return propertyValue.orElse(null);
    }

    public void setName(QualifiedName value) {
        setProperty(TransitionVariableType.NAME, value);
    }

    public PropertyNode getNumberNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.NUMBER);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getNumber() {
        Optional<UInteger> propertyValue = getProperty(TransitionVariableType.NUMBER);
        return propertyValue.orElse(null);
    }

    public void setNumber(UInteger value) {
        setProperty(TransitionVariableType.NUMBER, value);
    }

    public PropertyNode getTransitionTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.TRANSITION_TIME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public DateTime getTransitionTime() {
        Optional<DateTime> propertyValue = getProperty(TransitionVariableType.TRANSITION_TIME);
        return propertyValue.orElse(null);
    }

    public void setTransitionTime(DateTime value) {
        setProperty(TransitionVariableType.TRANSITION_TIME, value);
    }

    public PropertyNode getEffectiveTransitionTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.EFFECTIVE_TRANSITION_TIME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public DateTime getEffectiveTransitionTime() {
        Optional<DateTime> propertyValue = getProperty(TransitionVariableType.EFFECTIVE_TRANSITION_TIME);
        return propertyValue.orElse(null);
    }

    public void setEffectiveTransitionTime(DateTime value) {
        setProperty(TransitionVariableType.EFFECTIVE_TRANSITION_TIME, value);
    }
}

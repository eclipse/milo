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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TransitionVariableType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class TransitionVariableTypeNode extends BaseDataVariableTypeNode implements TransitionVariableType {
    public TransitionVariableTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public TransitionVariableTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                                      UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                      double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public PropertyTypeNode getIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.ID);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Object getId() {
        Optional<Object> propertyValue = getProperty(TransitionVariableType.ID);
        return propertyValue.orElse(null);
    }

    @Override
    public void setId(Object value) {
        setProperty(TransitionVariableType.ID, value);
    }

    @Override
    public PropertyTypeNode getNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.NAME);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public QualifiedName getName() {
        Optional<QualifiedName> propertyValue = getProperty(TransitionVariableType.NAME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setName(QualifiedName value) {
        setProperty(TransitionVariableType.NAME, value);
    }

    @Override
    public PropertyTypeNode getNumberNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.NUMBER);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public UInteger getNumber() {
        Optional<UInteger> propertyValue = getProperty(TransitionVariableType.NUMBER);
        return propertyValue.orElse(null);
    }

    @Override
    public void setNumber(UInteger value) {
        setProperty(TransitionVariableType.NUMBER, value);
    }

    @Override
    public PropertyTypeNode getTransitionTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.TRANSITION_TIME);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getTransitionTime() {
        Optional<DateTime> propertyValue = getProperty(TransitionVariableType.TRANSITION_TIME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setTransitionTime(DateTime value) {
        setProperty(TransitionVariableType.TRANSITION_TIME, value);
    }

    @Override
    public PropertyTypeNode getEffectiveTransitionTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(TransitionVariableType.EFFECTIVE_TRANSITION_TIME);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getEffectiveTransitionTime() {
        Optional<DateTime> propertyValue = getProperty(TransitionVariableType.EFFECTIVE_TRANSITION_TIME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEffectiveTransitionTime(DateTime value) {
        setProperty(TransitionVariableType.EFFECTIVE_TRANSITION_TIME, value);
    }
}

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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.LimitAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class LimitAlarmNode extends AlarmConditionNode implements LimitAlarmType {
    public LimitAlarmNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                          LocalizedText displayName, LocalizedText description, UInteger writeMask,
                          UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public LimitAlarmNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                          LocalizedText displayName, LocalizedText description, UInteger writeMask,
                          UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getHighHighLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.HIGH_HIGH_LIMIT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Double getHighHighLimit() {
        Optional<Double> propertyValue = getProperty(LimitAlarmType.HIGH_HIGH_LIMIT);
        return propertyValue.orElse(null);
    }

    public void setHighHighLimit(Double value) {
        setProperty(LimitAlarmType.HIGH_HIGH_LIMIT, value);
    }

    public PropertyNode getHighLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.HIGH_LIMIT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Double getHighLimit() {
        Optional<Double> propertyValue = getProperty(LimitAlarmType.HIGH_LIMIT);
        return propertyValue.orElse(null);
    }

    public void setHighLimit(Double value) {
        setProperty(LimitAlarmType.HIGH_LIMIT, value);
    }

    public PropertyNode getLowLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.LOW_LIMIT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Double getLowLimit() {
        Optional<Double> propertyValue = getProperty(LimitAlarmType.LOW_LIMIT);
        return propertyValue.orElse(null);
    }

    public void setLowLimit(Double value) {
        setProperty(LimitAlarmType.LOW_LIMIT, value);
    }

    public PropertyNode getLowLowLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.LOW_LOW_LIMIT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Double getLowLowLimit() {
        Optional<Double> propertyValue = getProperty(LimitAlarmType.LOW_LOW_LIMIT);
        return propertyValue.orElse(null);
    }

    public void setLowLowLimit(Double value) {
        setProperty(LimitAlarmType.LOW_LOW_LIMIT, value);
    }
}

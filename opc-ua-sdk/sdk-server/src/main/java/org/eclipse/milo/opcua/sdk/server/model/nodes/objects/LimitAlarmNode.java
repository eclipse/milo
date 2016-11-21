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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.LimitAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:LimitAlarmType")
public class LimitAlarmNode extends AlarmConditionNode implements LimitAlarmType {

    public LimitAlarmNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Double getHighHighLimit() {
        Optional<Double> property = getProperty(LimitAlarmType.HIGH_HIGH_LIMIT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getHighHighLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.HIGH_HIGH_LIMIT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setHighHighLimit(Double value) {
        setProperty(LimitAlarmType.HIGH_HIGH_LIMIT, value);
    }

    @Override
    public Double getHighLimit() {
        Optional<Double> property = getProperty(LimitAlarmType.HIGH_LIMIT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getHighLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.HIGH_LIMIT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setHighLimit(Double value) {
        setProperty(LimitAlarmType.HIGH_LIMIT, value);
    }

    @Override
    public Double getLowLimit() {
        Optional<Double> property = getProperty(LimitAlarmType.LOW_LIMIT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLowLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.LOW_LIMIT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLowLimit(Double value) {
        setProperty(LimitAlarmType.LOW_LIMIT, value);
    }

    @Override
    public Double getLowLowLimit() {
        Optional<Double> property = getProperty(LimitAlarmType.LOW_LOW_LIMIT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLowLowLimitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(LimitAlarmType.LOW_LOW_LIMIT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLowLowLimit(Double value) {
        setProperty(LimitAlarmType.LOW_LOW_LIMIT, value);
    }

}

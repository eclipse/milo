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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.AnalogItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:AnalogItemType")
public class AnalogItemNode extends DataItemNode implements AnalogItemType {

    public AnalogItemNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeManager, nodeId, variableTypeNode);
    }

    public AnalogItemNode(
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
    public Range getInstrumentRange() {
        Optional<Range> property = getProperty(AnalogItemType.INSTRUMENT_RANGE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInstrumentRangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AnalogItemType.INSTRUMENT_RANGE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInstrumentRange(Range value) {
        setProperty(AnalogItemType.INSTRUMENT_RANGE, value);
    }

    @Override
    public Range getEURange() {
        Optional<Range> property = getProperty(AnalogItemType.E_U_RANGE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEURangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AnalogItemType.E_U_RANGE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEURange(Range value) {
        setProperty(AnalogItemType.E_U_RANGE, value);
    }

    @Override
    public EUInformation getEngineeringUnits() {
        Optional<EUInformation> property = getProperty(AnalogItemType.ENGINEERING_UNITS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEngineeringUnitsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AnalogItemType.ENGINEERING_UNITS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEngineeringUnits(EUInformation value) {
        setProperty(AnalogItemType.ENGINEERING_UNITS, value);
    }

}

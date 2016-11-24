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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ArrayItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:ArrayItemType")
public class ArrayItemNode extends DataItemNode implements ArrayItemType {

    public ArrayItemNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeMap, nodeId, variableTypeNode);
    }

    public ArrayItemNode(
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
    public Range getInstrumentRange() {
        Optional<Range> property = getProperty(ArrayItemType.INSTRUMENT_RANGE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInstrumentRangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.INSTRUMENT_RANGE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInstrumentRange(Range value) {
        setProperty(ArrayItemType.INSTRUMENT_RANGE, value);
    }

    @Override
    public Range getEURange() {
        Optional<Range> property = getProperty(ArrayItemType.E_U_RANGE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEURangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.E_U_RANGE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEURange(Range value) {
        setProperty(ArrayItemType.E_U_RANGE, value);
    }

    @Override
    public EUInformation getEngineeringUnits() {
        Optional<EUInformation> property = getProperty(ArrayItemType.ENGINEERING_UNITS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEngineeringUnitsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.ENGINEERING_UNITS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEngineeringUnits(EUInformation value) {
        setProperty(ArrayItemType.ENGINEERING_UNITS, value);
    }

    @Override
    public LocalizedText getTitle() {
        Optional<LocalizedText> property = getProperty(ArrayItemType.TITLE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getTitleNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.TITLE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setTitle(LocalizedText value) {
        setProperty(ArrayItemType.TITLE, value);
    }

    @Override
    public AxisScaleEnumeration getAxisScaleType() {
        Optional<AxisScaleEnumeration> property = getProperty(ArrayItemType.AXIS_SCALE_TYPE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getAxisScaleTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.AXIS_SCALE_TYPE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setAxisScaleType(AxisScaleEnumeration value) {
        setProperty(ArrayItemType.AXIS_SCALE_TYPE, value);
    }

}

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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ArrayItemType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

public class ArrayItemTypeNode extends DataItemTypeNode implements ArrayItemType {
    public ArrayItemTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ArrayItemTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                             LocalizedText displayName, LocalizedText description, UInteger writeMask,
                             UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                             UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                             double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public PropertyTypeNode getInstrumentRangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.INSTRUMENT_RANGE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Range getInstrumentRange() {
        Optional<Range> propertyValue = getProperty(ArrayItemType.INSTRUMENT_RANGE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInstrumentRange(Range value) {
        setProperty(ArrayItemType.INSTRUMENT_RANGE, value);
    }

    @Override
    public PropertyTypeNode getEURangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.E_U_RANGE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Range getEURange() {
        Optional<Range> propertyValue = getProperty(ArrayItemType.E_U_RANGE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEURange(Range value) {
        setProperty(ArrayItemType.E_U_RANGE, value);
    }

    @Override
    public PropertyTypeNode getEngineeringUnitsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.ENGINEERING_UNITS);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public EUInformation getEngineeringUnits() {
        Optional<EUInformation> propertyValue = getProperty(ArrayItemType.ENGINEERING_UNITS);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEngineeringUnits(EUInformation value) {
        setProperty(ArrayItemType.ENGINEERING_UNITS, value);
    }

    @Override
    public PropertyTypeNode getTitleNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.TITLE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public LocalizedText getTitle() {
        Optional<LocalizedText> propertyValue = getProperty(ArrayItemType.TITLE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setTitle(LocalizedText value) {
        setProperty(ArrayItemType.TITLE, value);
    }

    @Override
    public PropertyTypeNode getAxisScaleTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ArrayItemType.AXIS_SCALE_TYPE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public AxisScaleEnumeration getAxisScaleType() {
        Optional<AxisScaleEnumeration> propertyValue = getProperty(ArrayItemType.AXIS_SCALE_TYPE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setAxisScaleType(AxisScaleEnumeration value) {
        setProperty(ArrayItemType.AXIS_SCALE_TYPE, value);
    }
}

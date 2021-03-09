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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.AnalogItemType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

public class AnalogItemTypeNode extends DataItemTypeNode implements AnalogItemType {
    public AnalogItemTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                              LocalizedText displayName, LocalizedText description, UInteger writeMask,
                              UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AnalogItemTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                              LocalizedText displayName, LocalizedText description, UInteger writeMask,
                              UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                              UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                              double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public PropertyTypeNode getInstrumentRangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AnalogItemType.INSTRUMENT_RANGE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Range getInstrumentRange() {
        Optional<Range> propertyValue = getProperty(AnalogItemType.INSTRUMENT_RANGE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInstrumentRange(Range value) {
        setProperty(AnalogItemType.INSTRUMENT_RANGE, value);
    }

    @Override
    public PropertyTypeNode getEURangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AnalogItemType.E_U_RANGE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Range getEURange() {
        Optional<Range> propertyValue = getProperty(AnalogItemType.E_U_RANGE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEURange(Range value) {
        setProperty(AnalogItemType.E_U_RANGE, value);
    }

    @Override
    public PropertyTypeNode getEngineeringUnitsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AnalogItemType.ENGINEERING_UNITS);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public EUInformation getEngineeringUnits() {
        Optional<EUInformation> propertyValue = getProperty(AnalogItemType.ENGINEERING_UNITS);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEngineeringUnits(EUInformation value) {
        setProperty(AnalogItemType.ENGINEERING_UNITS, value);
    }
}

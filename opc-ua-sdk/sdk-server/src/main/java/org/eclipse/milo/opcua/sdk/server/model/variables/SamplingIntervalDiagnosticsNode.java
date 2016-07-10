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

package org.eclipse.milo.opcua.sdk.server.model.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.model.variables.SamplingIntervalDiagnosticsType;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;

@UaVariableNode(typeName = "0:SamplingIntervalDiagnosticsType")
public class SamplingIntervalDiagnosticsNode extends BaseDataVariableNode implements SamplingIntervalDiagnosticsType {

    public SamplingIntervalDiagnosticsNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeManager, nodeId, variableTypeNode);
    }

    public SamplingIntervalDiagnosticsNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        Optional<LocalizedText> description,
        Optional<UInteger> writeMask,
        Optional<UInteger> userWriteMask,
        DataValue value,
        NodeId dataType,
        Integer valueRank,
        Optional<UInteger[]> arrayDimensions,
        UByte accessLevel,
        UByte userAccessLevel,
        Optional<Double> minimumSamplingInterval,
        boolean historizing) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask,
            value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public DataValue getValue() {
        SamplingIntervalDiagnosticsDataType value = new SamplingIntervalDiagnosticsDataType(
            getSamplingInterval(),
            getSampledMonitoredItemsCount(),
            getMaxSampledMonitoredItemsCount(),
            getDisabledMonitoredItemsSamplingCount()
        );

        return new DataValue(new Variant(value));
    }

    @Override
    public Double getSamplingInterval() {
        Optional<VariableNode> component = getVariableComponent("SamplingInterval");

        return component.map(node -> (Double) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSamplingIntervalNode() {
        Optional<VariableNode> component = getVariableComponent("SamplingInterval");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSamplingInterval(Double value) {
        getVariableComponent("SamplingInterval")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getSampledMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("SampledMonitoredItemsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSampledMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("SampledMonitoredItemsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSampledMonitoredItemsCount(UInteger value) {
        getVariableComponent("SampledMonitoredItemsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getMaxSampledMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("MaxSampledMonitoredItemsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getMaxSampledMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("MaxSampledMonitoredItemsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setMaxSampledMonitoredItemsCount(UInteger value) {
        getVariableComponent("MaxSampledMonitoredItemsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getDisabledMonitoredItemsSamplingCount() {
        Optional<VariableNode> component = getVariableComponent("DisabledMonitoredItemsSamplingCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDisabledMonitoredItemsSamplingCountNode() {
        Optional<VariableNode> component = getVariableComponent("DisabledMonitoredItemsSamplingCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDisabledMonitoredItemsSamplingCount(UInteger value) {
        getVariableComponent("DisabledMonitoredItemsSamplingCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}

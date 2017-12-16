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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SamplingIntervalDiagnosticsType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class SamplingIntervalDiagnosticsNode extends BaseDataVariableNode implements SamplingIntervalDiagnosticsType {
    public SamplingIntervalDiagnosticsNode(ServerNodeMap nodeMap, NodeId nodeId,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public SamplingIntervalDiagnosticsNode(ServerNodeMap nodeMap, NodeId nodeId,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                           Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                           double minimumSamplingInterval, boolean historizing) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    public BaseDataVariableNode getSamplingIntervalNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SamplingInterval");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public Double getSamplingInterval() {
        Optional<VariableNode> component = getVariableComponent("SamplingInterval");
        return component.map(node -> (Double) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSamplingInterval(Double value) {
        getVariableComponent("SamplingInterval").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getSampledMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SampledMonitoredItemsCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getSampledMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("SampledMonitoredItemsCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSampledMonitoredItemsCount(UInteger value) {
        getVariableComponent("SampledMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getMaxSampledMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "MaxSampledMonitoredItemsCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getMaxSampledMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("MaxSampledMonitoredItemsCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setMaxSampledMonitoredItemsCount(UInteger value) {
        getVariableComponent("MaxSampledMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getDisabledMonitoredItemsSamplingCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DisabledMonitoredItemsSamplingCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getDisabledMonitoredItemsSamplingCount() {
        Optional<VariableNode> component = getVariableComponent("DisabledMonitoredItemsSamplingCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setDisabledMonitoredItemsSamplingCount(UInteger value) {
        getVariableComponent("DisabledMonitoredItemsSamplingCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}

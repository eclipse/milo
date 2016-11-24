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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SamplingIntervalDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public class SamplingIntervalDiagnosticsNode extends BaseDataVariableNode implements SamplingIntervalDiagnosticsType {

    public SamplingIntervalDiagnosticsNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<BaseDataVariableNode> samplingInterval() {
        return getComponent(QualifiedName.parse("0:SamplingInterval"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<Double> getSamplingInterval() {
        return samplingInterval()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, Double.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSamplingInterval(Double value) {
        return samplingInterval()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> sampledMonitoredItemsCount() {
        return getComponent(QualifiedName.parse("0:SampledMonitoredItemsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSampledMonitoredItemsCount() {
        return sampledMonitoredItemsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSampledMonitoredItemsCount(UInteger value) {
        return sampledMonitoredItemsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> maxSampledMonitoredItemsCount() {
        return getComponent(QualifiedName.parse("0:MaxSampledMonitoredItemsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxSampledMonitoredItemsCount() {
        return maxSampledMonitoredItemsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setMaxSampledMonitoredItemsCount(UInteger value) {
        return maxSampledMonitoredItemsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> disabledMonitoredItemsSamplingCount() {
        return getComponent(QualifiedName.parse("0:DisabledMonitoredItemsSamplingCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDisabledMonitoredItemsSamplingCount() {
        return disabledMonitoredItemsSamplingCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDisabledMonitoredItemsSamplingCount(UInteger value) {
        return disabledMonitoredItemsSamplingCount()
            .thenCompose(node -> node.setValue(value));
    }

}
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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SamplingIntervalDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;

public class SamplingIntervalDiagnosticsArrayNode extends BaseDataVariableNode implements SamplingIntervalDiagnosticsArrayType {
    public SamplingIntervalDiagnosticsArrayNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<SamplingIntervalDiagnosticsNode> getSamplingIntervalDiagnosticsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SamplingIntervalDiagnostics").thenApply(SamplingIntervalDiagnosticsNode.class::cast);
    }

    public CompletableFuture<SamplingIntervalDiagnosticsDataType> getSamplingIntervalDiagnostics() {
        return getSamplingIntervalDiagnosticsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SamplingIntervalDiagnosticsDataType.class));
    }

    public CompletableFuture<StatusCode> setSamplingIntervalDiagnostics(SamplingIntervalDiagnosticsDataType value) {
        return getSamplingIntervalDiagnosticsNode().thenCompose(node -> node.setValue(value));
    }
}

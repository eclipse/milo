/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public class SubscriptionDiagnosticsArrayNode extends BaseDataVariableNode implements SubscriptionDiagnosticsArrayType {
    public SubscriptionDiagnosticsArrayNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<SubscriptionDiagnosticsNode> getSubscriptionDiagnosticsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SubscriptionDiagnostics").thenApply(SubscriptionDiagnosticsNode.class::cast);
    }

    public CompletableFuture<SubscriptionDiagnosticsDataType> getSubscriptionDiagnostics() {
        return getSubscriptionDiagnosticsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SubscriptionDiagnosticsDataType.class));
    }

    public CompletableFuture<StatusCode> setSubscriptionDiagnostics(SubscriptionDiagnosticsDataType value) {
        return getSubscriptionDiagnosticsNode().thenCompose(node -> node.setValue(value));
    }
}

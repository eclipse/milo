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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class FiniteTransitionVariableNode extends TransitionVariableNode implements FiniteTransitionVariableType {
    public FiniteTransitionVariableNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getIdNode() {
        return getPropertyNode(FiniteTransitionVariableType.ID);
    }

    public CompletableFuture<NodeId> getId() {
        return getProperty(FiniteTransitionVariableType.ID);
    }

    public CompletableFuture<StatusCode> setId(NodeId value) {
        return setProperty(FiniteTransitionVariableType.ID, value);
    }
}

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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ConditionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class ConditionVariableNode extends BaseDataVariableNode implements ConditionVariableType {
    public ConditionVariableNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getSourceTimestampNode() {
        return getPropertyNode(ConditionVariableType.SOURCE_TIMESTAMP);
    }

    public CompletableFuture<DateTime> getSourceTimestamp() {
        return getProperty(ConditionVariableType.SOURCE_TIMESTAMP);
    }

    public CompletableFuture<StatusCode> setSourceTimestamp(DateTime value) {
        return setProperty(ConditionVariableType.SOURCE_TIMESTAMP, value);
    }
}

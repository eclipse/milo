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

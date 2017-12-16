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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveDeviationAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class ExclusiveDeviationAlarmNode extends ExclusiveLimitAlarmNode implements ExclusiveDeviationAlarmType {
    public ExclusiveDeviationAlarmNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getSetpointNodeNode() {
        return getPropertyNode(ExclusiveDeviationAlarmType.SETPOINT_NODE);
    }

    public CompletableFuture<NodeId> getSetpointNode() {
        return getProperty(ExclusiveDeviationAlarmType.SETPOINT_NODE);
    }

    public CompletableFuture<StatusCode> setSetpointNode(NodeId value) {
        return setProperty(ExclusiveDeviationAlarmType.SETPOINT_NODE, value);
    }
}

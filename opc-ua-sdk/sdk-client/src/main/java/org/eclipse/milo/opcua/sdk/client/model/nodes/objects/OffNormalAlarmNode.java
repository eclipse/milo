/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.OffNormalAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class OffNormalAlarmNode extends DiscreteAlarmNode implements OffNormalAlarmType {
    public OffNormalAlarmNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getNormalStateNode() {
        return getPropertyNode(OffNormalAlarmType.NORMAL_STATE);
    }

    public CompletableFuture<NodeId> getNormalState() {
        return getProperty(OffNormalAlarmType.NORMAL_STATE);
    }

    public CompletableFuture<StatusCode> setNormalState(NodeId value) {
        return setProperty(OffNormalAlarmType.NORMAL_STATE, value);
    }
}

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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SystemStatusChangeEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

public class SystemStatusChangeEventNode extends SystemEventNode implements SystemStatusChangeEventType {
    public SystemStatusChangeEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getSystemStateNode() {
        return getPropertyNode(SystemStatusChangeEventType.SYSTEM_STATE);
    }

    public CompletableFuture<ServerState> getSystemState() {
        return getProperty(SystemStatusChangeEventType.SYSTEM_STATE);
    }

    public CompletableFuture<StatusCode> setSystemState(ServerState value) {
        return setProperty(SystemStatusChangeEventType.SYSTEM_STATE, value);
    }
}

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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditUpdateMethodEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditUpdateMethodEventNode extends AuditEventNode implements AuditUpdateMethodEventType {
    public AuditUpdateMethodEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getMethodIdNode() {
        return getPropertyNode(AuditUpdateMethodEventType.METHOD_ID);
    }

    public CompletableFuture<NodeId> getMethodId() {
        return getProperty(AuditUpdateMethodEventType.METHOD_ID);
    }

    public CompletableFuture<StatusCode> setMethodId(NodeId value) {
        return setProperty(AuditUpdateMethodEventType.METHOD_ID, value);
    }

    public CompletableFuture<PropertyNode> getInputArgumentsNode() {
        return getPropertyNode(AuditUpdateMethodEventType.INPUT_ARGUMENTS);
    }

    public CompletableFuture<Object[]> getInputArguments() {
        return getProperty(AuditUpdateMethodEventType.INPUT_ARGUMENTS);
    }

    public CompletableFuture<StatusCode> setInputArguments(Object[] value) {
        return setProperty(AuditUpdateMethodEventType.INPUT_ARGUMENTS, value);
    }
}

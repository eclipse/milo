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

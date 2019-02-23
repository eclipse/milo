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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditUpdateStateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditUpdateStateEventNode extends AuditUpdateMethodEventNode implements AuditUpdateStateEventType {
    public AuditUpdateStateEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getOldStateIdNode() {
        return getPropertyNode(AuditUpdateStateEventType.OLD_STATE_ID);
    }

    public CompletableFuture<?> getOldStateId() {
        return getProperty(AuditUpdateStateEventType.OLD_STATE_ID);
    }

    public CompletableFuture<StatusCode> setOldStateId(Object value) {
        return setProperty(AuditUpdateStateEventType.OLD_STATE_ID, value);
    }

    public CompletableFuture<PropertyNode> getNewStateIdNode() {
        return getPropertyNode(AuditUpdateStateEventType.NEW_STATE_ID);
    }

    public CompletableFuture<?> getNewStateId() {
        return getProperty(AuditUpdateStateEventType.NEW_STATE_ID);
    }

    public CompletableFuture<StatusCode> setNewStateId(Object value) {
        return setProperty(AuditUpdateStateEventType.NEW_STATE_ID, value);
    }
}

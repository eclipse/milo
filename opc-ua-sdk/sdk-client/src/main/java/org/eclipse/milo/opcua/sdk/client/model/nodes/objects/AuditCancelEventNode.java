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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCancelEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditCancelEventNode extends AuditSessionEventNode implements AuditCancelEventType {
    public AuditCancelEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getRequestHandleNode() {
        return getPropertyNode(AuditCancelEventType.REQUEST_HANDLE);
    }

    public CompletableFuture<UInteger> getRequestHandle() {
        return getProperty(AuditCancelEventType.REQUEST_HANDLE);
    }

    public CompletableFuture<StatusCode> setRequestHandle(UInteger value) {
        return setProperty(AuditCancelEventType.REQUEST_HANDLE, value);
    }
}

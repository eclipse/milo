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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditChannelEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditChannelEventNode extends AuditSecurityEventNode implements AuditChannelEventType {
    public AuditChannelEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getSecureChannelIdNode() {
        return getPropertyNode(AuditChannelEventType.SECURE_CHANNEL_ID);
    }

    public CompletableFuture<String> getSecureChannelId() {
        return getProperty(AuditChannelEventType.SECURE_CHANNEL_ID);
    }

    public CompletableFuture<StatusCode> setSecureChannelId(String value) {
        return setProperty(AuditChannelEventType.SECURE_CHANNEL_ID, value);
    }
}

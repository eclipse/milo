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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditEventNode extends BaseEventNode implements AuditEventType {
    public AuditEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getActionTimeStampNode() {
        return getPropertyNode(AuditEventType.ACTION_TIME_STAMP);
    }

    public CompletableFuture<DateTime> getActionTimeStamp() {
        return getProperty(AuditEventType.ACTION_TIME_STAMP);
    }

    public CompletableFuture<StatusCode> setActionTimeStamp(DateTime value) {
        return setProperty(AuditEventType.ACTION_TIME_STAMP, value);
    }

    public CompletableFuture<PropertyNode> getStatusNode() {
        return getPropertyNode(AuditEventType.STATUS);
    }

    public CompletableFuture<Boolean> getStatus() {
        return getProperty(AuditEventType.STATUS);
    }

    public CompletableFuture<StatusCode> setStatus(Boolean value) {
        return setProperty(AuditEventType.STATUS, value);
    }

    public CompletableFuture<PropertyNode> getServerIdNode() {
        return getPropertyNode(AuditEventType.SERVER_ID);
    }

    public CompletableFuture<String> getServerId() {
        return getProperty(AuditEventType.SERVER_ID);
    }

    public CompletableFuture<StatusCode> setServerId(String value) {
        return setProperty(AuditEventType.SERVER_ID, value);
    }

    public CompletableFuture<PropertyNode> getClientAuditEntryIdNode() {
        return getPropertyNode(AuditEventType.CLIENT_AUDIT_ENTRY_ID);
    }

    public CompletableFuture<String> getClientAuditEntryId() {
        return getProperty(AuditEventType.CLIENT_AUDIT_ENTRY_ID);
    }

    public CompletableFuture<StatusCode> setClientAuditEntryId(String value) {
        return setProperty(AuditEventType.CLIENT_AUDIT_ENTRY_ID, value);
    }

    public CompletableFuture<PropertyNode> getClientUserIdNode() {
        return getPropertyNode(AuditEventType.CLIENT_USER_ID);
    }

    public CompletableFuture<String> getClientUserId() {
        return getProperty(AuditEventType.CLIENT_USER_ID);
    }

    public CompletableFuture<StatusCode> setClientUserId(String value) {
        return setProperty(AuditEventType.CLIENT_USER_ID, value);
    }
}

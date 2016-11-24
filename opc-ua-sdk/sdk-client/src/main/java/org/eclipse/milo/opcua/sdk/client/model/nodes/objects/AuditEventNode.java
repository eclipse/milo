/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class AuditEventNode extends BaseEventNode implements AuditEventType {

    public AuditEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> actionTimeStamp() {
        return getPropertyNode(AuditEventType.ACTION_TIME_STAMP.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getActionTimeStamp() {
        return getProperty(AuditEventType.ACTION_TIME_STAMP);
    }

    @Override
    public CompletableFuture<StatusCode> setActionTimeStamp(DateTime value) {
        return setProperty(AuditEventType.ACTION_TIME_STAMP, value);
    }

    @Override
    public CompletableFuture<PropertyNode> status() {
        return getPropertyNode(AuditEventType.STATUS.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getStatus() {
        return getProperty(AuditEventType.STATUS);
    }

    @Override
    public CompletableFuture<StatusCode> setStatus(Boolean value) {
        return setProperty(AuditEventType.STATUS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> serverId() {
        return getPropertyNode(AuditEventType.SERVER_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getServerId() {
        return getProperty(AuditEventType.SERVER_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setServerId(String value) {
        return setProperty(AuditEventType.SERVER_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> clientAuditEntryId() {
        return getPropertyNode(AuditEventType.CLIENT_AUDIT_ENTRY_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getClientAuditEntryId() {
        return getProperty(AuditEventType.CLIENT_AUDIT_ENTRY_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setClientAuditEntryId(String value) {
        return setProperty(AuditEventType.CLIENT_AUDIT_ENTRY_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> clientUserId() {
        return getPropertyNode(AuditEventType.CLIENT_USER_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getClientUserId() {
        return getProperty(AuditEventType.CLIENT_USER_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setClientUserId(String value) {
        return setProperty(AuditEventType.CLIENT_USER_ID, value);
    }


}
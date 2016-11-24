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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryValueUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;


public class AuditHistoryValueUpdateEventNode extends AuditHistoryUpdateEventNode implements AuditHistoryValueUpdateEventType {

    public AuditHistoryValueUpdateEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> updatedNode() {
        return getPropertyNode(AuditHistoryValueUpdateEventType.UPDATED_NODE.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getUpdatedNode() {
        return getProperty(AuditHistoryValueUpdateEventType.UPDATED_NODE);
    }

    @Override
    public CompletableFuture<StatusCode> setUpdatedNode(NodeId value) {
        return setProperty(AuditHistoryValueUpdateEventType.UPDATED_NODE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> performInsertReplace() {
        return getPropertyNode(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE.getBrowseName());
    }

    @Override
    public CompletableFuture<PerformUpdateType> getPerformInsertReplace() {
        return getProperty(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE);
    }

    @Override
    public CompletableFuture<StatusCode> setPerformInsertReplace(PerformUpdateType value) {
        return setProperty(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> newValues() {
        return getPropertyNode(AuditHistoryValueUpdateEventType.NEW_VALUES.getBrowseName());
    }

    @Override
    public CompletableFuture<DataValue[]> getNewValues() {
        return getProperty(AuditHistoryValueUpdateEventType.NEW_VALUES);
    }

    @Override
    public CompletableFuture<StatusCode> setNewValues(DataValue[] value) {
        return setProperty(AuditHistoryValueUpdateEventType.NEW_VALUES, value);
    }

    @Override
    public CompletableFuture<PropertyNode> oldValues() {
        return getPropertyNode(AuditHistoryValueUpdateEventType.OLD_VALUES.getBrowseName());
    }

    @Override
    public CompletableFuture<DataValue[]> getOldValues() {
        return getProperty(AuditHistoryValueUpdateEventType.OLD_VALUES);
    }

    @Override
    public CompletableFuture<StatusCode> setOldValues(DataValue[] value) {
        return setProperty(AuditHistoryValueUpdateEventType.OLD_VALUES, value);
    }


}
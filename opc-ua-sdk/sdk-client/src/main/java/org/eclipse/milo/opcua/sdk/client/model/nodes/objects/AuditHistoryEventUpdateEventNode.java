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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryEventUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;


public class AuditHistoryEventUpdateEventNode extends AuditHistoryUpdateEventNode implements AuditHistoryEventUpdateEventType {

    public AuditHistoryEventUpdateEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> updatedNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.UPDATED_NODE.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getUpdatedNode() {
        return getProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE);
    }

    @Override
    public CompletableFuture<StatusCode> setUpdatedNode(NodeId value) {
        return setProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> performInsertReplace() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE.getBrowseName());
    }

    @Override
    public CompletableFuture<PerformUpdateType> getPerformInsertReplace() {
        return getProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE);
    }

    @Override
    public CompletableFuture<StatusCode> setPerformInsertReplace(PerformUpdateType value) {
        return setProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> filter() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.FILTER.getBrowseName());
    }

    @Override
    public CompletableFuture<EventFilter> getFilter() {
        return getProperty(AuditHistoryEventUpdateEventType.FILTER);
    }

    @Override
    public CompletableFuture<StatusCode> setFilter(EventFilter value) {
        return setProperty(AuditHistoryEventUpdateEventType.FILTER, value);
    }

    @Override
    public CompletableFuture<PropertyNode> newValues() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.NEW_VALUES.getBrowseName());
    }

    @Override
    public CompletableFuture<HistoryEventFieldList[]> getNewValues() {
        return getProperty(AuditHistoryEventUpdateEventType.NEW_VALUES);
    }

    @Override
    public CompletableFuture<StatusCode> setNewValues(HistoryEventFieldList[] value) {
        return setProperty(AuditHistoryEventUpdateEventType.NEW_VALUES, value);
    }

    @Override
    public CompletableFuture<PropertyNode> oldValues() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.OLD_VALUES.getBrowseName());
    }

    @Override
    public CompletableFuture<HistoryEventFieldList[]> getOldValues() {
        return getProperty(AuditHistoryEventUpdateEventType.OLD_VALUES);
    }

    @Override
    public CompletableFuture<StatusCode> setOldValues(HistoryEventFieldList[] value) {
        return setProperty(AuditHistoryEventUpdateEventType.OLD_VALUES, value);
    }


}
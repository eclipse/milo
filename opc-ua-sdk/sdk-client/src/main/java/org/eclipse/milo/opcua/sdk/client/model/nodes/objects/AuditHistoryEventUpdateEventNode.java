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

    public CompletableFuture<PropertyNode> getUpdatedNodeNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.UPDATED_NODE);
    }

    public CompletableFuture<NodeId> getUpdatedNode() {
        return getProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE);
    }

    public CompletableFuture<StatusCode> setUpdatedNode(NodeId value) {
        return setProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE, value);
    }

    public CompletableFuture<PropertyNode> getPerformInsertReplaceNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE);
    }

    public CompletableFuture<PerformUpdateType> getPerformInsertReplace() {
        return getProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE);
    }

    public CompletableFuture<StatusCode> setPerformInsertReplace(PerformUpdateType value) {
        return setProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    public CompletableFuture<PropertyNode> getFilterNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.FILTER);
    }

    public CompletableFuture<EventFilter> getFilter() {
        return getProperty(AuditHistoryEventUpdateEventType.FILTER);
    }

    public CompletableFuture<StatusCode> setFilter(EventFilter value) {
        return setProperty(AuditHistoryEventUpdateEventType.FILTER, value);
    }

    public CompletableFuture<PropertyNode> getNewValuesNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.NEW_VALUES);
    }

    public CompletableFuture<HistoryEventFieldList[]> getNewValues() {
        return getProperty(AuditHistoryEventUpdateEventType.NEW_VALUES);
    }

    public CompletableFuture<StatusCode> setNewValues(HistoryEventFieldList[] value) {
        return setProperty(AuditHistoryEventUpdateEventType.NEW_VALUES, value);
    }

    public CompletableFuture<PropertyNode> getOldValuesNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.OLD_VALUES);
    }

    public CompletableFuture<HistoryEventFieldList[]> getOldValues() {
        return getProperty(AuditHistoryEventUpdateEventType.OLD_VALUES);
    }

    public CompletableFuture<StatusCode> setOldValues(HistoryEventFieldList[] value) {
        return setProperty(AuditHistoryEventUpdateEventType.OLD_VALUES, value);
    }
}

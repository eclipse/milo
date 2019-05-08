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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryEventUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

public class AuditHistoryEventUpdateEventTypeNode extends AuditHistoryUpdateEventTypeNode implements AuditHistoryEventUpdateEventType {
    public AuditHistoryEventUpdateEventTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getUpdatedNodeNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.UPDATED_NODE);
    }

    public CompletableFuture<NodeId> getUpdatedNode() {
        return getProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE);
    }

    public CompletableFuture<StatusCode> setUpdatedNode(NodeId value) {
        return setProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE, value);
    }

    public CompletableFuture<PropertyTypeNode> getPerformInsertReplaceNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE);
    }

    public CompletableFuture<PerformUpdateType> getPerformInsertReplace() {
        return getProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE);
    }

    public CompletableFuture<StatusCode> setPerformInsertReplace(PerformUpdateType value) {
        return setProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    public CompletableFuture<PropertyTypeNode> getFilterNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.FILTER);
    }

    public CompletableFuture<EventFilter> getFilter() {
        return getProperty(AuditHistoryEventUpdateEventType.FILTER);
    }

    public CompletableFuture<StatusCode> setFilter(EventFilter value) {
        return setProperty(AuditHistoryEventUpdateEventType.FILTER, value);
    }

    public CompletableFuture<PropertyTypeNode> getNewValuesNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.NEW_VALUES);
    }

    public CompletableFuture<HistoryEventFieldList[]> getNewValues() {
        return getProperty(AuditHistoryEventUpdateEventType.NEW_VALUES);
    }

    public CompletableFuture<StatusCode> setNewValues(HistoryEventFieldList[] value) {
        return setProperty(AuditHistoryEventUpdateEventType.NEW_VALUES, value);
    }

    public CompletableFuture<PropertyTypeNode> getOldValuesNode() {
        return getPropertyNode(AuditHistoryEventUpdateEventType.OLD_VALUES);
    }

    public CompletableFuture<HistoryEventFieldList[]> getOldValues() {
        return getProperty(AuditHistoryEventUpdateEventType.OLD_VALUES);
    }

    public CompletableFuture<StatusCode> setOldValues(HistoryEventFieldList[] value) {
        return setProperty(AuditHistoryEventUpdateEventType.OLD_VALUES, value);
    }
}

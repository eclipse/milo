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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryValueUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

public class AuditHistoryValueUpdateEventNode extends AuditHistoryUpdateEventNode implements AuditHistoryValueUpdateEventType {
    public AuditHistoryValueUpdateEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getUpdatedNodeNode() {
        return getPropertyNode(AuditHistoryValueUpdateEventType.UPDATED_NODE);
    }

    public CompletableFuture<NodeId> getUpdatedNode() {
        return getProperty(AuditHistoryValueUpdateEventType.UPDATED_NODE);
    }

    public CompletableFuture<StatusCode> setUpdatedNode(NodeId value) {
        return setProperty(AuditHistoryValueUpdateEventType.UPDATED_NODE, value);
    }

    public CompletableFuture<PropertyNode> getPerformInsertReplaceNode() {
        return getPropertyNode(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE);
    }

    public CompletableFuture<PerformUpdateType> getPerformInsertReplace() {
        return getProperty(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE);
    }

    public CompletableFuture<StatusCode> setPerformInsertReplace(PerformUpdateType value) {
        return setProperty(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    public CompletableFuture<PropertyNode> getNewValuesNode() {
        return getPropertyNode(AuditHistoryValueUpdateEventType.NEW_VALUES);
    }

    public CompletableFuture<DataValue[]> getNewValues() {
        return getProperty(AuditHistoryValueUpdateEventType.NEW_VALUES);
    }

    public CompletableFuture<StatusCode> setNewValues(DataValue[] value) {
        return setProperty(AuditHistoryValueUpdateEventType.NEW_VALUES, value);
    }

    public CompletableFuture<PropertyNode> getOldValuesNode() {
        return getPropertyNode(AuditHistoryValueUpdateEventType.OLD_VALUES);
    }

    public CompletableFuture<DataValue[]> getOldValues() {
        return getProperty(AuditHistoryValueUpdateEventType.OLD_VALUES);
    }

    public CompletableFuture<StatusCode> setOldValues(DataValue[] value) {
        return setProperty(AuditHistoryValueUpdateEventType.OLD_VALUES, value);
    }
}

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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryEventDeleteEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

public class AuditHistoryEventDeleteEventNode extends AuditHistoryDeleteEventNode implements AuditHistoryEventDeleteEventType {
    public AuditHistoryEventDeleteEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getEventIdsNode() {
        return getPropertyNode(AuditHistoryEventDeleteEventType.EVENT_IDS);
    }

    public CompletableFuture<ByteString[]> getEventIds() {
        return getProperty(AuditHistoryEventDeleteEventType.EVENT_IDS);
    }

    public CompletableFuture<StatusCode> setEventIds(ByteString[] value) {
        return setProperty(AuditHistoryEventDeleteEventType.EVENT_IDS, value);
    }

    public CompletableFuture<PropertyNode> getOldValuesNode() {
        return getPropertyNode(AuditHistoryEventDeleteEventType.OLD_VALUES);
    }

    public CompletableFuture<HistoryEventFieldList> getOldValues() {
        return getProperty(AuditHistoryEventDeleteEventType.OLD_VALUES);
    }

    public CompletableFuture<StatusCode> setOldValues(HistoryEventFieldList value) {
        return setProperty(AuditHistoryEventDeleteEventType.OLD_VALUES, value);
    }
}

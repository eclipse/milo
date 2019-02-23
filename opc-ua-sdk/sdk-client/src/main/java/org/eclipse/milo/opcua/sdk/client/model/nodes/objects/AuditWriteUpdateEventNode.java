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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditWriteUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditWriteUpdateEventNode extends AuditUpdateEventNode implements AuditWriteUpdateEventType {
    public AuditWriteUpdateEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getAttributeIdNode() {
        return getPropertyNode(AuditWriteUpdateEventType.ATTRIBUTE_ID);
    }

    public CompletableFuture<UInteger> getAttributeId() {
        return getProperty(AuditWriteUpdateEventType.ATTRIBUTE_ID);
    }

    public CompletableFuture<StatusCode> setAttributeId(UInteger value) {
        return setProperty(AuditWriteUpdateEventType.ATTRIBUTE_ID, value);
    }

    public CompletableFuture<PropertyNode> getIndexRangeNode() {
        return getPropertyNode(AuditWriteUpdateEventType.INDEX_RANGE);
    }

    public CompletableFuture<String> getIndexRange() {
        return getProperty(AuditWriteUpdateEventType.INDEX_RANGE);
    }

    public CompletableFuture<StatusCode> setIndexRange(String value) {
        return setProperty(AuditWriteUpdateEventType.INDEX_RANGE, value);
    }

    public CompletableFuture<PropertyNode> getOldValueNode() {
        return getPropertyNode(AuditWriteUpdateEventType.OLD_VALUE);
    }

    public CompletableFuture<?> getOldValue() {
        return getProperty(AuditWriteUpdateEventType.OLD_VALUE);
    }

    public CompletableFuture<StatusCode> setOldValue(Object value) {
        return setProperty(AuditWriteUpdateEventType.OLD_VALUE, value);
    }

    public CompletableFuture<PropertyNode> getNewValueNode() {
        return getPropertyNode(AuditWriteUpdateEventType.NEW_VALUE);
    }

    public CompletableFuture<?> getNewValue() {
        return getProperty(AuditWriteUpdateEventType.NEW_VALUE);
    }

    public CompletableFuture<StatusCode> setNewValue(Object value) {
        return setProperty(AuditWriteUpdateEventType.NEW_VALUE, value);
    }
}

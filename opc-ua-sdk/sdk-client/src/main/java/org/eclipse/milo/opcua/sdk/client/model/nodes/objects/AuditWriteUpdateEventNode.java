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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditWriteUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public class AuditWriteUpdateEventNode extends AuditUpdateEventNode implements AuditWriteUpdateEventType {

    public AuditWriteUpdateEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> attributeId() {
        return getPropertyNode(AuditWriteUpdateEventType.ATTRIBUTE_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getAttributeId() {
        return getProperty(AuditWriteUpdateEventType.ATTRIBUTE_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setAttributeId(UInteger value) {
        return setProperty(AuditWriteUpdateEventType.ATTRIBUTE_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> indexRange() {
        return getPropertyNode(AuditWriteUpdateEventType.INDEX_RANGE.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getIndexRange() {
        return getProperty(AuditWriteUpdateEventType.INDEX_RANGE);
    }

    @Override
    public CompletableFuture<StatusCode> setIndexRange(String value) {
        return setProperty(AuditWriteUpdateEventType.INDEX_RANGE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> oldValue() {
        return getPropertyNode(AuditWriteUpdateEventType.OLD_VALUE.getBrowseName());
    }

    @Override
    public CompletableFuture<? extends Object> getOldValue() {
        return getProperty(AuditWriteUpdateEventType.OLD_VALUE);
    }

    @Override
    public CompletableFuture<StatusCode> setOldValue(Object value) {
        return setProperty(AuditWriteUpdateEventType.OLD_VALUE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> newValue() {
        return getPropertyNode(AuditWriteUpdateEventType.NEW_VALUE.getBrowseName());
    }

    @Override
    public CompletableFuture<? extends Object> getNewValue() {
        return getProperty(AuditWriteUpdateEventType.NEW_VALUE);
    }

    @Override
    public CompletableFuture<StatusCode> setNewValue(Object value) {
        return setProperty(AuditWriteUpdateEventType.NEW_VALUE, value);
    }


}
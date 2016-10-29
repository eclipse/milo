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

package org.eclipse.milo.opcua.sdk.client.nodes.attached;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AttachedVariableNode extends AttachedNode implements UaVariableNode {

    public AttachedVariableNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<DataValue> readValue() {
        return readAttribute(AttributeId.Value);
    }

    @Override
    public CompletableFuture<DataValue> readDataType() {
        return readAttribute(AttributeId.DataType);
    }

    @Override
    public CompletableFuture<DataValue> readValueRank() {
        return readAttribute(AttributeId.ValueRank);
    }

    @Override
    public CompletableFuture<DataValue> readArrayDimensions() {
        return readAttribute(AttributeId.ArrayDimensions);
    }

    @Override
    public CompletableFuture<DataValue> readAccessLevel() {
        return readAttribute(AttributeId.AccessLevel);
    }

    @Override
    public CompletableFuture<DataValue> readUserAccessLevel() {
        return readAttribute(AttributeId.UserAccessLevel);
    }

    @Override
    public CompletableFuture<DataValue> readMinimumSamplingInterval() {
        return readAttribute(AttributeId.MinimumSamplingInterval);
    }

    @Override
    public CompletableFuture<DataValue> readHistorizing() {
        return readAttribute(AttributeId.Historizing);
    }

    @Override
    public CompletableFuture<StatusCode> writeValue(DataValue value) {
        return writeAttribute(AttributeId.Value, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeDataType(DataValue value) {
        return writeAttribute(AttributeId.DataType, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeValueRank(DataValue value) {
        return writeAttribute(AttributeId.ValueRank, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeArrayDimensions(DataValue value) {
        return writeAttribute(AttributeId.ArrayDimensions, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeAccessLevel(DataValue value) {
        return writeAttribute(AttributeId.AccessLevel, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeUserAccessLevel(DataValue value) {
        return writeAttribute(AttributeId.UserAccessLevel, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeMinimumSamplingInterval(DataValue value) {
        return writeAttribute(AttributeId.MinimumSamplingInterval, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeHistorizing(DataValue value) {
        return writeAttribute(AttributeId.Historizing, value);
    }

}

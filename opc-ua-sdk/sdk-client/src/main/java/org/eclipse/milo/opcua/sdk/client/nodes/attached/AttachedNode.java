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

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.NodeCache;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

import static com.google.common.collect.Lists.newArrayList;

public class AttachedNode implements UaNode {

    protected final NodeCache nodeCache;

    protected final OpcUaClient client;
    protected final NodeId nodeId;

    public AttachedNode(OpcUaClient client, NodeId nodeId) {
        this.client = client;
        this.nodeId = nodeId;

        nodeCache = client.getNodeCache();
    }

    @Override
    public CompletableFuture<DataValue> readNodeId() {
        return readAttribute(AttributeId.NodeId);
    }

    @Override
    public CompletableFuture<DataValue> readNodeClass() {
        return readAttribute(AttributeId.NodeClass);
    }

    @Override
    public CompletableFuture<DataValue> readBrowseName() {
        return readAttribute(AttributeId.BrowseName);
    }

    @Override
    public CompletableFuture<DataValue> readDisplayName() {
        return readAttribute(AttributeId.DisplayName);
    }

    @Override
    public CompletableFuture<DataValue> readDescription() {
        return readAttribute(AttributeId.Description);
    }

    @Override
    public CompletableFuture<DataValue> readWriteMask() {
        return readAttribute(AttributeId.WriteMask);
    }

    @Override
    public CompletableFuture<DataValue> readUserWriteMask() {
        return readAttribute(AttributeId.UserWriteMask);
    }

    protected CompletableFuture<DataValue> readAttribute(AttributeId attributeId) {
        Optional<DataValue> opt =
            AttributeId.BASE_NODE_ATTRIBUTES.contains(attributeId) ?
                nodeCache.getAttribute(nodeId, attributeId) : Optional.empty();

        return opt.map(CompletableFuture::completedFuture).orElseGet(() -> {
            ReadValueId readValueId = new ReadValueId(
                nodeId, attributeId.uid(), null, QualifiedName.NULL_VALUE);

            CompletableFuture<ReadResponse> future =
                client.read(0.0, TimestampsToReturn.Neither, newArrayList(readValueId));

            return future.thenApply(response -> {
                DataValue value = response.getResults()[0];

                if (attributeId != AttributeId.Value) {
                    nodeCache.putAttribute(nodeId, attributeId, value);
                }

                return value;
            });
        });
    }

    @Override
    public CompletableFuture<StatusCode> writeNodeId(DataValue value) {
        return writeAttribute(AttributeId.NodeId, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeNodeClass(DataValue value) {
        return writeAttribute(AttributeId.NodeClass, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeBrowseName(DataValue value) {
        return writeAttribute(AttributeId.BrowseName, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeDisplayName(DataValue value) {
        return writeAttribute(AttributeId.DisplayName, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeDescription(DataValue value) {
        return writeAttribute(AttributeId.Description, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeWriteMask(DataValue value) {
        return writeAttribute(AttributeId.WriteMask, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeUserWriteMask(DataValue value) {
        return writeAttribute(AttributeId.UserWriteMask, value);
    }

    protected CompletableFuture<StatusCode> writeAttribute(AttributeId attributeId, DataValue value) {
        WriteValue writeValue = new WriteValue(
            nodeId, attributeId.uid(), null, value);

        return client.write(newArrayList(writeValue)).thenApply(response -> {
            StatusCode statusCode = response.getResults()[0];

            if (statusCode.isGood()) {
                nodeCache.invalidate(nodeId, attributeId);
            }

            return statusCode;
        });
    }

}

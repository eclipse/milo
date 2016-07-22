/*
 * Copyright (c) 2016 Red Hat
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */
package org.eclipse.milo.examples.helloworld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;

import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode.GOOD;

public class HelloNamespace implements Namespace {

    // Nodes state

    private final Map<String, Object> nodesValues = Maps.newConcurrentMap();

    // Read and write handlers

    @Override
    public void read(
        ReadContext context,
        Double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> readValueIds) {

        context.complete(
            readValueIds.stream()
                .map(id -> new DataValue(new Variant(nodesValues.get(nodeIdToString(id.getNodeId())))))
                .collect(toList())
        );
    }

    @Override
    public void write(WriteContext context, List<WriteValue> writeValues) {
        context.complete(
            writeValues.stream()
                .map(writeValue -> {
                    nodesValues.put(
                        nodeIdToString(writeValue.getNodeId()),
                        writeValue.getValue().getValue().getValue());
                    return GOOD;
                })
                .collect(toList())
        );
    }

    // Operations not needed for Hello World example

    @Override
    public UShort getNamespaceIndex() {
        return null;
    }

    @Override
    public String getNamespaceUri() {
        return null;
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {

    }

    @Override
    public CompletableFuture<List<Reference>> getReferences(NodeId nodeId) {
        return null;
    }

    // Helpers

    private String nodeIdToString(NodeId id) {
        return id.getNamespaceIndex().toString() + id.getIdentifier();
    }

}

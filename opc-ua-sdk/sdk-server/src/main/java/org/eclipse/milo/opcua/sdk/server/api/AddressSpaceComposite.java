/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.services.DefaultAttributeServiceSet;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public abstract class AddressSpaceComposite implements AddressSpace {

    private final List<AddressSpace> addressSpaces = new CopyOnWriteArrayList<>();

    private final OpcUaServer server;

    public AddressSpaceComposite(OpcUaServer server) {
        this.server = server;
    }

    private AddressSpace getAddressSpace(NodeId nodeId) {
        Optional<AddressSpace> addressSpace = addressSpaces.stream()
            .filter(asx -> asx.filter(nodeId))
            .findFirst();

        return addressSpace.orElse(new EmptyAddressSpace(server));
    }

    @Override
    public void browse(BrowseContext context, ViewDescription view, NodeId nodeId) {
        try {
            AddressSpace addressSpace = addressSpaces.stream()
                .filter(asx -> asx.filter(nodeId))
                .findFirst()
                .orElseThrow(() -> new UaException(StatusCodes.Bad_NodeIdUnknown));

            addressSpace.browse(context, view, nodeId);
        } catch (UaException e) {
            context.failure(e);
        }
    }

    @Override
    public void getReferences(BrowseContext context, ViewDescription view, NodeId nodeId) {
        List<CompletableFuture<List<Reference>>> futures = new ArrayList<>();

        for (AddressSpace asx : addressSpaces) {
            BrowseContext browseContext = new BrowseContext(
                server,
                context.getSession().orElse(null)
            );

            asx.getReferences(browseContext, view, nodeId);

            futures.add(browseContext.getFuture());
        }

        CompletableFuture<List<Reference>> references = FutureUtils.sequence(futures).thenApply(
            refs ->
                refs.stream()
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(toList())
        );

        references
            .exceptionally(ex -> emptyList())
            .thenAccept(context::success);
    }

    @Override
    public void read(
        ReadContext context,
        Double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> readValueIds
    ) {

        CompletableFuture<List<DataValue>> valuesFuture = DefaultAttributeServiceSet.addressSpaceRead(
            server,
            context.getSession().orElse(null),
            maxAge,
            timestamps,
            readValueIds
        );

        valuesFuture.thenAccept(context::complete);
    }

    @Override
    public void write(WriteContext context, List<WriteValue> writeValues) {
        CompletableFuture<List<StatusCode>> resultsFuture = DefaultAttributeServiceSet.addressSpaceWrite(
            server,
            context.getSession().orElse(null),
            writeValues
        );

        resultsFuture.thenAccept(context::complete);
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

    private static class EmptyAddressSpace extends ManagedAddressSpace {

        public EmptyAddressSpace(OpcUaServer server) {
            super(server);
        }

        @Override
        public boolean filter(NodeId nodeId) {
            return true;
        }

        @Override
        public void onDataItemsCreated(List<DataItem> dataItems) {}

        @Override
        public void onDataItemsModified(List<DataItem> dataItems) {}

        @Override
        public void onDataItemsDeleted(List<DataItem> dataItems) {}

        @Override
        public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {}

    }
}

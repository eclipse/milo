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
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.services.MonitoredItemServices;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResult;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.sdk.server.util.GroupMapCollate.groupMapCollate;

/**
 * An {@link AddressSpace} that is composed of one or more registered sub-AddressSpaces.
 * <p>
 * Service call operations are executed by the first sub-AddressSpace that matches on the NodeId in the operation.
 */
public abstract class AddressSpaceComposite extends AbstractLifecycle implements AddressSpace {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CopyOnWriteArrayList<AddressSpace> addressSpaces = new CopyOnWriteArrayList<>();

    private final OpcUaServer server;

    public AddressSpaceComposite(OpcUaServer server) {
        this.server = server;
    }

    @Override
    protected void onStartup() {}

    @Override
    protected void onShutdown() {}

    @Override
    public boolean filter(NodeId nodeId) {
        return addressSpaces.stream()
            .anyMatch(asx -> asx.filter(nodeId));
    }

    /**
     * Register an {@link AddressSpace} with this composite.
     * <p>
     * The AddressSpace is inserted at the end of the list.
     *
     * @param addressSpace the {@link AddressSpace} to register.
     */
    public synchronized void register(AddressSpace addressSpace) {
        if (!addressSpaces.contains(addressSpace)) {
            addressSpaces.add(addressSpace);

            logger.debug("registered {}", addressSpace);
        } else {
            logger.warn("AddressSpace already registered: {}", addressSpace);
        }
    }

    /**
     * Register an {@link AddressSpace} with this composite.
     * <p>
     * The AddressSpace is inserted at the front of the list.
     *
     * @param addressSpace the {@link AddressSpace} to register.
     */
    public synchronized void registerFirst(AddressSpace addressSpace) {
        if (!addressSpaces.contains(addressSpace)) {
            addressSpaces.add(0, addressSpace);

            logger.debug("registered {} at index 0", addressSpace);
        } else {
            logger.warn("AddressSpace already registered: {}", addressSpace);
        }
    }

    /**
     * Unregister an {@link AddressSpace} with this composite.
     *
     * @param addressSpace the {@link AddressSpace} to unregister.
     */
    public synchronized void unregister(AddressSpace addressSpace) {
        if (addressSpaces.contains(addressSpace)) {
            addressSpaces.remove(addressSpace);

            logger.debug("unregistered {}", addressSpace);
        } else {
            logger.warn("AddressSpace not registered: {}", addressSpace);
        }
    }

    /**
     * Get the {@link OpcUaServer} instance.
     *
     * @return the {@link OpcUaServer} instance.
     */
    protected OpcUaServer getServer() {
        return server;
    }

    /**
     * Get a copy of the current {@link AddressSpace} list.
     *
     * @return a copy of the current {@link AddressSpace} list.
     */
    protected List<AddressSpace> getAddressSpaces() {
        return new ArrayList<>(addressSpaces);
    }

    private AddressSpace getAddressSpace(NodeId nodeId) {
        Optional<AddressSpace> addressSpace = addressSpaces.stream()
            .filter(asx -> asx.filter(nodeId))
            .findFirst();

        return addressSpace.orElse(new EmptyAddressSpace(server));
    }

    private AddressSpace getAddressSpace(ExpandedNodeId nodeId) {
        return nodeId.local(getServer().getNamespaceTable())
            .map(this::getAddressSpace)
            .orElse(new EmptyAddressSpace(server));
    }

    private <T> Map<AddressSpace, List<T>> groupedBy(List<T> items, Function<T, NodeId> getNodeId) {
        return items.stream().collect(groupingBy(item -> {
            NodeId nodeId = getNodeId.apply(item);

            return getAddressSpace(nodeId);
        }));
    }

    //region ViewServices

    @Override
    public void browse(BrowseContext context, ViewDescription view, NodeId nodeId) {
        List<AddressSpace> addressSpaces = getAddressSpaces();

        AddressSpace firstMatch;
        try {
            firstMatch = addressSpaces.stream()
                .filter(asx -> asx.filter(nodeId))
                .findFirst()
                .orElseThrow(() -> new UaException(StatusCodes.Bad_NodeIdUnknown));

            addressSpaces.remove(firstMatch);
        } catch (UaException e) {
            context.failure(e);
            return;
        }

        List<CompletableFuture<List<Reference>>> futures = new ArrayList<>();

        BrowseContext browseContext = new BrowseContext(
            getServer(),
            context.getSession().orElse(null)
        );

        firstMatch.browse(browseContext, view, nodeId);

        futures.add(browseContext.getFuture());

        for (AddressSpace asx : addressSpaces) {
            browseContext = new BrowseContext(
                getServer(),
                context.getSession().orElse(null)
            );

            asx.getReferences(browseContext, view, nodeId);

            futures.add(browseContext.getFuture());
        }

        CompletableFuture<List<Reference>> future = FutureUtils.sequence(futures).thenApply(
            refs ->
                refs.stream()
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(toList())
        );

        future.thenAccept(context::success);
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

    //endregion

    //region AttributeServices

    @Override
    public void read(
        ReadContext context,
        Double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> readValueIds
    ) {

        CompletableFuture<List<DataValue>> values = groupMapCollate(
            readValueIds,
            readValueId -> getAddressSpace(readValueId.getNodeId()),
            (AddressSpace asx) -> group -> {
                ReadContext ctx = new ReadContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.read(ctx, maxAge, timestamps, group);

                return ctx.getFuture();
            }
        );

        values.thenAccept(context::success);
    }

    @Override
    public void write(
        WriteContext context,
        List<WriteValue> writeValues
    ) {

        CompletableFuture<List<StatusCode>> results = groupMapCollate(
            writeValues,
            writeValue -> getAddressSpace(writeValue.getNodeId()),
            (AddressSpace asx) -> group -> {

                WriteContext ctx = new WriteContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.write(ctx, group);

                return ctx.getFuture();
            }
        );

        results.thenAccept(context::success);
    }

    //endregion

    //region AttributeHistoryServices

    @Override
    public void historyRead(
        HistoryReadContext context,
        HistoryReadDetails details,
        TimestampsToReturn timestamps,
        List<HistoryReadValueId> readValueIds
    ) {

        CompletableFuture<List<HistoryReadResult>> results = groupMapCollate(
            readValueIds,
            readValueId -> getAddressSpace(readValueId.getNodeId()),
            (AddressSpace asx) -> group -> {

                HistoryReadContext ctx = new HistoryReadContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.historyRead(
                    ctx,
                    details,
                    timestamps,
                    group
                );

                return ctx.getFuture();
            }
        );

        results.thenAccept(context::success);
    }

    @Override
    public void historyUpdate(
        HistoryUpdateContext context,
        List<HistoryUpdateDetails> updateDetailsList
    ) {

        CompletableFuture<List<HistoryUpdateResult>> results = groupMapCollate(
            updateDetailsList,
            updateDetails -> getAddressSpace(updateDetails.getNodeId()),
            (AddressSpace asx) -> group -> {

                HistoryUpdateContext ctx = new HistoryUpdateContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.historyUpdate(ctx, group);

                return ctx.getFuture();
            }
        );

        results.thenAccept(context::success);
    }

    //endregion

    //region MethodServices

    @Override
    public void call(
        CallContext context,
        List<CallMethodRequest> requests
    ) {

        CompletableFuture<List<CallMethodResult>> results = groupMapCollate(
            requests,
            request -> getAddressSpace(request.getObjectId()),
            (AddressSpace asx) -> group -> {

                CallContext ctx = new CallContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.call(ctx, group);

                return ctx.getFuture();
            }
        );

        results.thenAccept(context::success);
    }

    //endregion

    //region MonitoredItemServices

    @Override
    public void onCreateDataItem(
        ReadValueId itemToMonitor,
        Double requestedSamplingInterval,
        UInteger requestedQueueSize,
        BiConsumer<Double, UInteger> revisionCallback
    ) {

        getAddressSpace(itemToMonitor.getNodeId()).onCreateDataItem(
            itemToMonitor,
            requestedSamplingInterval,
            requestedQueueSize,
            revisionCallback
        );
    }

    @Override
    public void onModifyDataItem(
        ReadValueId itemToModify,
        Double requestedSamplingInterval,
        UInteger requestedQueueSize,
        BiConsumer<Double, UInteger> revisionCallback
    ) {

        getAddressSpace(itemToModify.getNodeId()).onModifyDataItem(
            itemToModify,
            requestedSamplingInterval,
            requestedQueueSize,
            revisionCallback
        );
    }

    @Override
    public void onCreateEventItem(
        ReadValueId itemToMonitor,
        UInteger requestedQueueSize,
        Consumer<UInteger> revisionCallback
    ) {

        getAddressSpace(itemToMonitor.getNodeId()).onCreateEventItem(
            itemToMonitor,
            requestedQueueSize,
            revisionCallback
        );
    }

    @Override
    public void onModifyEventItem(
        ReadValueId itemToModify,
        UInteger requestedQueueSize,
        Consumer<UInteger> revisionCallback
    ) {

        getAddressSpace(itemToModify.getNodeId()).onModifyEventItem(
            itemToModify,
            requestedQueueSize,
            revisionCallback
        );
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        Map<AddressSpace, List<DataItem>> byAddressSpace = groupedBy(
            dataItems,
            dataItem -> dataItem.getReadValueId().getNodeId()
        );

        byAddressSpace.forEach(MonitoredItemServices::onDataItemsCreated);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        Map<AddressSpace, List<DataItem>> byAddressSpace = groupedBy(
            dataItems,
            dataItem -> dataItem.getReadValueId().getNodeId()
        );

        byAddressSpace.forEach(MonitoredItemServices::onDataItemsModified);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        Map<AddressSpace, List<DataItem>> byAddressSpace = groupedBy(
            dataItems,
            dataItem -> dataItem.getReadValueId().getNodeId()
        );

        byAddressSpace.forEach(MonitoredItemServices::onDataItemsDeleted);
    }

    @Override
    public void onEventItemsCreated(List<EventItem> eventItems) {
        Map<AddressSpace, List<EventItem>> byAddressSpace = groupedBy(
            eventItems,
            eventItem -> eventItem.getReadValueId().getNodeId()
        );

        byAddressSpace.forEach(MonitoredItemServices::onEventItemsCreated);
    }

    @Override
    public void onEventItemsModified(List<EventItem> eventItems) {
        Map<AddressSpace, List<EventItem>> byAddressSpace = groupedBy(
            eventItems,
            eventItem -> eventItem.getReadValueId().getNodeId()
        );

        byAddressSpace.forEach(MonitoredItemServices::onEventItemsModified);
    }

    @Override
    public void onEventItemsDeleted(List<EventItem> eventItems) {
        Map<AddressSpace, List<EventItem>> byAddressSpace = groupedBy(
            eventItems,
            eventItem -> eventItem.getReadValueId().getNodeId()
        );

        byAddressSpace.forEach(MonitoredItemServices::onEventItemsDeleted);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        Map<AddressSpace, List<MonitoredItem>> byAddressSpace = groupedBy(
            monitoredItems,
            monitoredItem -> monitoredItem.getReadValueId().getNodeId()
        );

        byAddressSpace.forEach(MonitoredItemServices::onMonitoringModeChanged);
    }

    //endregion

    //region NodeManagementServices

    @Override
    public void addNodes(AddNodesContext context, List<AddNodesItem> nodesToAdd) {
        CompletableFuture<List<AddNodesResult>> results = groupMapCollate(
            nodesToAdd,
            addNodesItem -> {
                ExpandedNodeId requestedNewNodeId =
                    addNodesItem.getRequestedNewNodeId();

                if (requestedNewNodeId.isNotNull()) {
                    return getAddressSpace(requestedNewNodeId);
                } else {
                    return getAddressSpace(addNodesItem.getParentNodeId());
                }
            },
            (AddressSpace asx) -> group -> {

                AddNodesContext ctx = new AddNodesContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.addNodes(ctx, group);

                return ctx.getFuture();
            }
        );

        results.thenAccept(context::success);
    }

    @Override
    public void deleteNodes(DeleteNodesContext context, List<DeleteNodesItem> nodesToDelete) {
        CompletableFuture<List<StatusCode>> results = groupMapCollate(
            nodesToDelete,
            deleteNodesItem -> getAddressSpace(deleteNodesItem.getNodeId()),
            (AddressSpace asx) -> group -> {

                DeleteNodesContext ctx = new DeleteNodesContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.deleteNodes(ctx, group);

                return ctx.getFuture();
            }
        );

        results.thenAccept(context::success);
    }

    @Override
    public void addReferences(AddReferencesContext context, List<AddReferencesItem> referencesToAdd) {
        CompletableFuture<List<StatusCode>> results = groupMapCollate(
            referencesToAdd,
            addReferencesItem -> getAddressSpace(addReferencesItem.getSourceNodeId()),
            (AddressSpace asx) -> group -> {

                AddReferencesContext ctx = new AddReferencesContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.addReferences(ctx, group);

                return ctx.getFuture();
            }
        );

        results.thenAccept(context::success);
    }

    @Override
    public void deleteReferences(DeleteReferencesContext context, List<DeleteReferencesItem> referencesToDelete) {
        CompletableFuture<List<StatusCode>> results = groupMapCollate(
            referencesToDelete,
            deleteReferencesItem -> getAddressSpace(deleteReferencesItem.getSourceNodeId()),
            (AddressSpace asx) -> group -> {

                DeleteReferencesContext ctx = new DeleteReferencesContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.deleteReferences(ctx, group);

                return ctx.getFuture();
            }
        );

        results.thenAccept(context::success);
    }

    //endregion

    private static class EmptyAddressSpace extends ManagedAddressSpace {

        EmptyAddressSpace(OpcUaServer server) {
            super(server);
        }

        // EmptyAddressSpace is used ephemerally and should never be started/registered

        @Override
        protected void onStartup() {
            throw new IllegalStateException("EmptyAddressSpace onStartup()");
        }

        @Override
        protected void onShutdown() {
            throw new IllegalStateException("EmptyAddressSpace onShutdown()");
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

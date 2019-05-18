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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.services.MonitoredItemServices;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
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
import org.eclipse.milo.opcua.stack.core.util.Unit;
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

    private final CompositeAddressSpaceFilter filter = new CompositeAddressSpaceFilter(addressSpaces);

    private final OpcUaServer server;

    public AddressSpaceComposite(OpcUaServer server) {
        this.server = server;
    }

    @Override
    protected void onStartup() {}

    @Override
    protected void onShutdown() {}

    @Override
    public AddressSpaceFilter getFilter() {
        return filter;
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

    private AddressSpace getAddressSpace(Predicate<AddressSpace> filter) {
        return addressSpaces.stream()
            .filter(filter)
            .findFirst()
            .orElse(new EmptyAddressSpace(server));
    }

    //region ViewServices

    @Override
    public void browse(BrowseContext context, ViewDescription view, NodeId nodeId) {
        List<AddressSpace> addressSpaces = getAddressSpaces();

        AddressSpace firstMatch;
        try {
            firstMatch = addressSpaces.stream()
                .filter(asx -> asx.getFilter().filterBrowse(server, nodeId))
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

        // If the first AddressSpace match completed exceptionally the whole
        // browse is a failure, regardless of whether other AddressSpaces have
        // references pointing to a NodeId that doesn't actually exist.
        future.whenComplete((references, ex) -> {
            if (references != null) {
                context.success(references);
            } else {
                context.failure(
                    UaException.extract(ex)
                        .orElse(new UaException(ex))
                );
            }
        });
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
    public void registerNodes(RegisterNodesContext context, List<NodeId> nodeIds) {
        CompletableFuture<List<NodeId>> registeredNodeIds = groupMapCollate(
            nodeIds,
            nodeId -> getAddressSpace(
                asx ->
                    asx.getFilter().filterRegisterNode(server, nodeId)
            ),
            (AddressSpace asx) -> group -> {
                RegisterNodesContext ctx = new RegisterNodesContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.registerNodes(ctx, group);

                return ctx.getFuture();
            }
        );

        registeredNodeIds.thenAccept(context::success);
    }

    @Override
    public void unregisterNodes(UnregisterNodesContext context, List<NodeId> nodeIds) {
        CompletableFuture<List<Unit>> units = groupMapCollate(
            nodeIds,
            nodeId -> getAddressSpace(
                asx ->
                    asx.getFilter().filterUnregisterNode(server, nodeId)
            ),
            (AddressSpace asx) -> group -> {
                UnregisterNodesContext ctx = new UnregisterNodesContext(
                    server,
                    context.getSession().orElse(null),
                    context.getDiagnosticsContext()
                );

                asx.unregisterNodes(ctx, group);

                return ctx.getFuture();
            }
        );

        units.thenAccept(context::success);
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
            readValueId -> getAddressSpace(
                asx ->
                    asx.getFilter().filterRead(server, readValueId)
            ),
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
            writeValue -> getAddressSpace(
                asx ->
                    asx.getFilter().filterWrite(server, writeValue)
            ),
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
            readValueId -> getAddressSpace(
                asx ->
                    asx.getFilter().filterHistoryRead(server, readValueId)
            ),
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
            updateDetails -> getAddressSpace(
                asx ->
                    asx.getFilter().filterHistoryUpdate(server, updateDetails)
            ),
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
            request -> getAddressSpace(
                asx ->
                    asx.getFilter().filterCall(server, request)
            ),
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

        AddressSpace addressSpace = getAddressSpace(
            asx ->
                asx.getFilter().filterOnCreateDataItem(server, itemToMonitor)
        );

        addressSpace.onCreateDataItem(
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

        AddressSpace addressSpace = getAddressSpace(
            asx ->
                asx.getFilter().filterOnModifyDataItem(server, itemToModify)
        );

        addressSpace.onModifyDataItem(
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

        AddressSpace addressSpace = getAddressSpace(
            asx ->
                asx.getFilter().filterOnCreateEventItem(server, itemToMonitor)
        );

        addressSpace.onCreateEventItem(
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

        AddressSpace addressSpace = getAddressSpace(
            asx ->
                asx.getFilter().filterOnModifyEventItem(server, itemToModify)
        );

        addressSpace.onModifyEventItem(
            itemToModify,
            requestedQueueSize,
            revisionCallback
        );
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        Map<AddressSpace, List<DataItem>> byAddressSpace = dataItems.stream().collect(groupingBy(item ->
            getAddressSpace(
                asx ->
                    asx.getFilter().filterOnDataItemsCreated(server, item.getReadValueId())
            )
        ));

        byAddressSpace.forEach(MonitoredItemServices::onDataItemsCreated);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        Map<AddressSpace, List<DataItem>> byAddressSpace = dataItems.stream().collect(groupingBy(item ->
            getAddressSpace(
                asx ->
                    asx.getFilter().filterOnDataItemsModified(server, item.getReadValueId())
            )
        ));

        byAddressSpace.forEach(MonitoredItemServices::onDataItemsModified);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        Map<AddressSpace, List<DataItem>> byAddressSpace = dataItems.stream().collect(groupingBy(item ->
            getAddressSpace(
                asx ->
                    asx.getFilter().filterOnDataItemsDeleted(server, item.getReadValueId())
            )
        ));

        byAddressSpace.forEach(MonitoredItemServices::onDataItemsDeleted);
    }

    @Override
    public void onEventItemsCreated(List<EventItem> eventItems) {
        Map<AddressSpace, List<EventItem>> byAddressSpace = eventItems.stream().collect(groupingBy(item ->
            getAddressSpace(
                asx ->
                    asx.getFilter().filterOnEventItemsCreated(server, item.getReadValueId())
            )
        ));

        byAddressSpace.forEach(MonitoredItemServices::onEventItemsCreated);
    }

    @Override
    public void onEventItemsModified(List<EventItem> eventItems) {
        Map<AddressSpace, List<EventItem>> byAddressSpace = eventItems.stream().collect(groupingBy(item ->
            getAddressSpace(
                asx ->
                    asx.getFilter().filterOnEventItemsModified(server, item.getReadValueId())
            )
        ));

        byAddressSpace.forEach(MonitoredItemServices::onEventItemsModified);
    }

    @Override
    public void onEventItemsDeleted(List<EventItem> eventItems) {
        Map<AddressSpace, List<EventItem>> byAddressSpace = eventItems.stream().collect(groupingBy(item ->
            getAddressSpace(
                asx ->
                    asx.getFilter().filterOnEventItemsDeleted(server, item.getReadValueId())
            )
        ));

        byAddressSpace.forEach(MonitoredItemServices::onEventItemsDeleted);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        Map<AddressSpace, List<MonitoredItem>> byAddressSpace = monitoredItems.stream().collect(groupingBy(item ->
            getAddressSpace(
                asx ->
                    asx.getFilter().filterOnMonitoringModeChanged(server, item.getReadValueId())
            )
        ));

        byAddressSpace.forEach(MonitoredItemServices::onMonitoringModeChanged);
    }

    //endregion

    //region NodeManagementServices

    @Override
    public void addNodes(AddNodesContext context, List<AddNodesItem> nodesToAdd) {
        CompletableFuture<List<AddNodesResult>> results = groupMapCollate(
            nodesToAdd,
            addNodesItem -> getAddressSpace(
                asx ->
                    asx.getFilter().filterAddNodes(server, addNodesItem)
            ),
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
            deleteNodesItem -> getAddressSpace(
                asx ->
                    asx.getFilter().filterDeleteNodes(server, deleteNodesItem)
            ),
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
            addReferencesItem -> getAddressSpace(
                asx ->
                    asx.getFilter().filterAddReferences(server, addReferencesItem)
            ),
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
            deleteReferencesItem -> getAddressSpace(
                asx ->
                    asx.getFilter().filterDeleteReferences(server, deleteReferencesItem)
            ),
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

    private static class CompositeAddressSpaceFilter implements AddressSpaceFilter {

        private final List<AddressSpace> addressSpaces;

        CompositeAddressSpaceFilter(List<AddressSpace> addressSpaces) {
            this.addressSpaces = addressSpaces;
        }

        @Override
        public boolean filterBrowse(OpcUaServer server, NodeId nodeId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterBrowse(server, nodeId));
        }

        @Override
        public boolean filterRegisterNode(OpcUaServer server, NodeId nodeId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterRegisterNode(server, nodeId));
        }

        @Override
        public boolean filterUnregisterNode(OpcUaServer server, NodeId nodeId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterUnregisterNode(server, nodeId));
        }

        @Override
        public boolean filterRead(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterRead(server, readValueId));
        }

        @Override
        public boolean filterWrite(OpcUaServer server, WriteValue writeValue) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterWrite(server, writeValue));
        }

        @Override
        public boolean filterHistoryRead(OpcUaServer server, HistoryReadValueId historyReadValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterHistoryRead(server, historyReadValueId));
        }

        @Override
        public boolean filterHistoryUpdate(OpcUaServer server, HistoryUpdateDetails historyUpdateDetails) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterHistoryUpdate(server, historyUpdateDetails));
        }

        @Override
        public boolean filterCall(OpcUaServer server, CallMethodRequest callMethodRequest) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterCall(server, callMethodRequest));
        }

        @Override
        public boolean filterOnCreateDataItem(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnCreateDataItem(server, readValueId));
        }

        @Override
        public boolean filterOnModifyDataItem(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnModifyDataItem(server, readValueId));
        }

        @Override
        public boolean filterOnCreateEventItem(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnCreateEventItem(server, readValueId));
        }

        @Override
        public boolean filterOnModifyEventItem(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnModifyEventItem(server, readValueId));
        }

        @Override
        public boolean filterOnDataItemsCreated(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnDataItemsCreated(server, readValueId));
        }

        @Override
        public boolean filterOnDataItemsModified(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnDataItemsModified(server, readValueId));
        }

        @Override
        public boolean filterOnDataItemsDeleted(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnDataItemsDeleted(server, readValueId));
        }

        @Override
        public boolean filterOnEventItemsCreated(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnEventItemsCreated(server, readValueId));
        }

        @Override
        public boolean filterOnEventItemsModified(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnEventItemsModified(server, readValueId));
        }

        @Override
        public boolean filterOnEventItemsDeleted(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnEventItemsDeleted(server, readValueId));
        }

        @Override
        public boolean filterOnMonitoringModeChanged(OpcUaServer server, ReadValueId readValueId) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterOnMonitoringModeChanged(server, readValueId));
        }

        @Override
        public boolean filterAddNodes(OpcUaServer server, AddNodesItem addNodesItem) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterAddNodes(server, addNodesItem));
        }

        @Override
        public boolean filterDeleteNodes(OpcUaServer server, DeleteNodesItem deleteNodesItem) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterDeleteNodes(server, deleteNodesItem));
        }

        @Override
        public boolean filterAddReferences(OpcUaServer server, AddReferencesItem addReferencesItem) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterAddReferences(server, addReferencesItem));
        }

        @Override
        public boolean filterDeleteReferences(OpcUaServer server, DeleteReferencesItem deleteReferencesItem) {
            return addressSpaces.stream()
                .anyMatch(asx -> asx.getFilter().filterDeleteReferences(server, deleteReferencesItem));
        }

    }

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
        public AddressSpaceFilter getFilter() {
            return new SimpleAddressSpaceFilter() {
                @Override
                protected boolean filter(NodeId nodeId) {
                    return true;
                }
            };
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

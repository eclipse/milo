/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem.DataValueListener;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static java.util.Collections.singletonList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;


public class ManagedSubscription {

    public static final double DEFAULT_PUBLISHING_INTERVAL = 1000.0;
    public static final double DEFAULT_SAMPLING_INTERVAL = 1000.0;
    public static final UInteger DEFAULT_QUEUE_SIZE = uint(2);

    private final CopyOnWriteArrayList<ChangeListener> changeListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<StatusListener> statusListeners = new CopyOnWriteArrayList<>();

    /**
     * Data items keyed by the client handle of the underlying {@link UaMonitoredItem}.
     */
    private final Map<UInteger, ManagedDataItem> dataItems = new ConcurrentHashMap<>();

    /**
     * Event items keyed by the client handle of the underlying {@link UaMonitoredItem}.
     */
    private final Map<UInteger, ManagedEventItem> eventItems = new ConcurrentHashMap<>();

    private MonitoringMode defaultMonitoringMode = MonitoringMode.Reporting;
    private double defaultSamplingInterval = DEFAULT_SAMPLING_INTERVAL;
    private UInteger defaultQueueSize = DEFAULT_QUEUE_SIZE;
    private TimestampsToReturn defaultTimestamps = TimestampsToReturn.Both;
    private ExtensionObject defaultDataFilter = null;
    private boolean defaultDiscardOldest = true;

    private final OpcUaClient client;
    private final OpcUaSubscription subscription;

    public ManagedSubscription(OpcUaClient client, OpcUaSubscription subscription) {
        this.client = client;
        this.subscription = subscription;

        subscription.addNotificationListener(new ManagedSubscriptionNotificationListener());
    }

    /**
     * Get the {@link OpcUaClient} that created this {@link ManagedSubscription}.
     *
     * @return the {@link OpcUaClient} that created this {@link ManagedSubscription}.
     */
    public OpcUaClient getClient() {
        return client;
    }

    /**
     * Get the underlying {@link OpcUaSubscription}.
     *
     * @return the underlying {@link OpcUaSubscription}.
     */
    public OpcUaSubscription getSubscription() {
        return subscription;
    }

    /**
     * Get a copied List of the current {@link ManagedDataItem}s.
     *
     * @return a copied List of the current {@link ManagedDataItem}s.
     */
    public List<ManagedDataItem> getDataItems() {
        return new ArrayList<>(dataItems.values());
    }

    /**
     * Get a copied List of the current {@link ManagedEventItem}s.
     *
     * @return a copied List of the current {@link ManagedEventItem}s.
     */
    public List<ManagedEventItem> getEventItems() {
        return new ArrayList<>(eventItems.values());
    }

    //region create/delete ManagedDataItem

    /**
     * Create a {@link ManagedDataItem} monitoring the Value attribute of the Node identified by {@code nodeId}.
     * <p>
     * The operation will fail of the Node is not a Variable or VariableType Node. To create an item that monitors a
     * different attribute use {@link #createDataItem(double, ReadValueId)}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param nodeId the {@link NodeId} identifying a Variable or VariableType Node.
     * @return a {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public ManagedDataItem createDataItem(NodeId nodeId) throws UaException {
        return createDataItems(singletonList(nodeId)).get(0);
    }

    /**
     * Create a {@link ManagedDataItem} monitoring the Value attribute of the Node identified by {@code nodeId}.
     * <p>
     * The operation will fail of the Node is not a Variable or VariableType Node. To create an item that monitors a
     * different attribute use {@link #createDataItem(double, ReadValueId)}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param nodeId   the {@link NodeId} identifying a Variable or VariableType Node.
     * @param consumer a {@link Consumer} that will receive each item as it is created.
     * @return a {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public ManagedDataItem createDataItem(NodeId nodeId, Consumer<ManagedDataItem> consumer) throws UaException {
        return createDataItems(singletonList(nodeId), consumer).get(0);
    }

    /**
     * Create {@link ManagedDataItem}s monitoring the Value attribute of the Nodes identified by {@code nodeIds}.
     * <p>
     * This operation will fail of the Node is not a Variable or VariableType Node. To create items that monitor
     * different attributes use {@link #createDataItems(double, List)}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param nodeIds the {@link NodeId}s identifying a Variable or VariableType Nodes.
     * @return a List of {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public List<ManagedDataItem> createDataItems(List<NodeId> nodeIds) throws UaException {
        return createDataItems(nodeIds, item -> {});
    }

    /**
     * Create {@link ManagedDataItem}s monitoring the Value attribute of the Nodes identified by {@code nodeIds}.
     * <p>
     * This operation will fail of the Node is not a Variable or VariableType Node. To create items that monitor
     * different attributes use {@link #createDataItems(double, List)}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param nodeIds  the {@link NodeId}s identifying a Variable or VariableType Nodes.
     * @param consumer a {@link Consumer} that will receive each item as it is created.
     * @return a List of {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public List<ManagedDataItem> createDataItems(
        List<NodeId> nodeIds,
        Consumer<ManagedDataItem> consumer
    ) throws UaException {

        List<ReadValueId> readValueIds = nodeIds.stream()
            .map(nodeId ->
                new ReadValueId(
                    nodeId,
                    AttributeId.Value.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        return createDataItems(getDefaultSamplingInterval(), readValueIds, consumer);
    }

    /**
     * Create a {@link ManagedDataItem} monitoring the Node and Attribute identified by {@code readValueId}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param samplingInterval the sampling interval to request.
     * @param readValueId      the {@link ReadValueId} identifying the Node and Attribute.
     * @return a {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public ManagedDataItem createDataItem(double samplingInterval, ReadValueId readValueId) throws UaException {
        return createDataItem(samplingInterval, readValueId, item -> {});
    }

    /**
     * Create a {@link ManagedDataItem} monitoring the Node and Attribute identified by {@code readValueId}.
     * <p>
     * {@code consumer} will receive each item as it is created to provide an opportunity to add a
     * {@link DataValueListener} before the first value change has arrived.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param samplingInterval the sampling interval to request.
     * @param readValueId      the {@link ReadValueId} identifying the Node and Attribute.
     * @param consumer         a {@link Consumer} that will receive each item as it is created.
     * @return a {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public ManagedDataItem createDataItem(
        double samplingInterval,
        ReadValueId readValueId,
        Consumer<ManagedDataItem> consumer
    ) throws UaException {

        return createDataItems(samplingInterval, singletonList(readValueId), consumer).get(0);
    }

    /**
     * Create {@link ManagedDataItem}s monitoring the Nodes and Attributes identified by {@code readValueIds}.
     * <p>
     * The operation results should be checked before each item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param samplingInterval the sampling interval to request.
     * @param readValueIds     the {@link ReadValueId}s identifying the Nodes and Attributes.
     * @return a List of {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public List<ManagedDataItem> createDataItems(
        double samplingInterval,
        List<ReadValueId> readValueIds
    ) throws UaException {

        return createDataItems(samplingInterval, readValueIds, item -> {});
    }

    /**
     * Create {@link ManagedDataItem}s monitoring the Nodes and Attributes identified by {@code readValueIds}.
     * <p>
     * {@code consumer} will receive each item as it is created to provide an opportunity to add a
     * {@link DataValueListener} before the first value change has arrived.
     * <p>
     * The operation results should be checked before each item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param samplingInterval the sampling interval to request.
     * @param readValueIds     the {@link ReadValueId}s identifying the Nodes and Attributes.
     * @param consumer         a {@link Consumer} that will receive each item as it is created.
     * @return a List of {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public List<ManagedDataItem> createDataItems(
        double samplingInterval,
        List<ReadValueId> readValueIds,
        Consumer<ManagedDataItem> consumer
    ) throws UaException {

        try {
            CompletableFuture<List<ManagedDataItem>> future = createDataItemsAsync(
                samplingInterval,
                readValueIds,
                consumer
            );

            return future.get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Create {@link ManagedDataItem}s monitoring the Nodes and Attributes identified by {@code readValueIds}.
     * <p>
     * The operation results should be checked before each item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     * <p>
     * This call completes asynchronously.
     *
     * @param samplingInterval the sampling interval to request.
     * @param readValueIds     the {@link ReadValueId}s identifying the Nodes and Attributes.
     * @return a {@link CompletableFuture} that completes successfully with a List of {@link ManagedDataItem} or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<ManagedDataItem>> createDataItemsAsync(
        double samplingInterval,
        List<ReadValueId> readValueIds
    ) {

        return createDataItemsAsync(samplingInterval, readValueIds, item -> {});
    }

    /**
     * Create {@link ManagedDataItem}s monitoring the Nodes and Attributes identified by {@code readValueIds}.
     * <p>
     * {@code consumer} will receive each item as it is created to provide an opportunity to add a
     * {@link DataValueListener} before the first value change has arrived.
     * <p>
     * The operation results should be checked before each item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     * <p>
     * This call completes asynchronously.
     *
     * @param samplingInterval the sampling interval to request.
     * @param readValueIds     the {@link ReadValueId}s identifying the Nodes and Attributes.
     * @param consumer         a {@link Consumer} that will receive each item as it is created.
     * @return a {@link CompletableFuture} that completes successfully with a List of {@link ManagedDataItem} or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<ManagedDataItem>> createDataItemsAsync(
        double samplingInterval,
        List<ReadValueId> readValueIds,
        Consumer<ManagedDataItem> consumer
    ) {

        final ExtensionObject filter = getDefaultDataFilter();
        final UInteger queueSize = getDefaultQueueSize();
        final boolean discardOldest = getDefaultDiscardOldest();

        List<MonitoredItemCreateRequest> createRequests = readValueIds.stream()
            .map(readValueId -> {
                MonitoringParameters parameters = new MonitoringParameters(
                    subscription.nextClientHandle(),
                    samplingInterval,
                    filter,
                    queueSize,
                    discardOldest
                );

                return new MonitoredItemCreateRequest(readValueId, getDefaultMonitoringMode(), parameters);
            })
            .collect(Collectors.toList());

        CompletableFuture<List<UaMonitoredItem>> monitoredItems = subscription.createMonitoredItems(
            getDefaultTimestamps(),
            createRequests,
            (item, id) -> {
                ManagedDataItem dataItem = createAndTrackDataItem(item);
                consumer.accept(dataItem);
            }
        );

        return monitoredItems.thenApply(
            items ->
                items.stream()
                    .map(item -> dataItems.get(item.getClientHandle()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
        );
    }

    /**
     * Delete a {@link ManagedDataItem}.
     * <p>
     * The result of the delete operation can be obtained via {@link ManagedDataItem#getStatusCode()}.
     *
     * @param dataItem the {@link ManagedDataItem} to delete.
     * @throws UaException if a service-level error occurs.
     */
    public void deleteDataItem(ManagedDataItem dataItem) throws UaException {
        deleteDataItems(singletonList(dataItem));
    }

    /**
     * Delete one or more {@link ManagedDataItem}s.
     * <p>
     * The result of the delete operations can be obtained via {@link ManagedDataItem#getStatusCode()}.
     *
     * @param dataItems the {@link ManagedDataItem}s to delete.
     * @throws UaException if a service-level error occurs.
     */
    public void deleteDataItems(List<ManagedDataItem> dataItems) throws UaException {
        try {
            deleteDataItemsAsync(dataItems).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Delete a {@link ManagedDataItem}.
     * <p>
     * The result of the delete operation can be obtained via {@link ManagedDataItem#getStatusCode()}.
     * <p>
     * This call completes asynchronously.
     *
     * @param dataItem the {@link ManagedDataItem} to delete.
     * @return a {@link CompletableFuture} that completes successfully if the service completed successfully or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Unit> deleteDataItemAsync(ManagedDataItem dataItem) {
        return deleteDataItemsAsync(singletonList(dataItem));
    }

    /**
     * Delete one or more {@link ManagedDataItem}s.
     * <p>
     * The result of the delete operations can be obtained via {@link ManagedDataItem#getStatusCode()}.
     * <p>
     * This call completes asynchronously.
     *
     * @param dataItemsToDelete the {@link ManagedDataItem}s to delete.
     * @return a {@link CompletableFuture} that completes successfully if the service completed successfully or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Unit> deleteDataItemsAsync(List<ManagedDataItem> dataItemsToDelete) {
        List<UaMonitoredItem> monitoredItems = dataItemsToDelete.stream()
            .map(ManagedItem::getMonitoredItem)
            .collect(Collectors.toList());

        return subscription.deleteMonitoredItems(monitoredItems).thenCompose(statusCodes -> {
            for (UaMonitoredItem monitoredItem : monitoredItems) {
                this.dataItems.remove(monitoredItem.getClientHandle());
            }

            return completedFuture(Unit.VALUE);
        });
    }

    private ManagedDataItem createAndTrackDataItem(UaMonitoredItem item) {
        ManagedDataItem dataItem = new ManagedDataItem(client, this, (OpcUaMonitoredItem) item);
        dataItems.put(item.getClientHandle(), dataItem);
        return dataItem;
    }

    //endregion

    //region create/delete ManagedEventItem

    /**
     * Create a {@link ManagedEventItem}.
     * <p>
     * This operation will fail of the Node identified by {@code nodeId} is not an Object Node.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedEventItem#getStatusCode()}.
     *
     * @param nodeId      the {@link NodeId} identifying an Object Node.
     * @param eventFilter the {@link EventFilter} to use.
     * @return a {@link ManagedEventItem}.
     * @throws UaException if a service-level error occurs.
     * @see EventFilterBuilder
     */
    public ManagedEventItem createEventItem(NodeId nodeId, EventFilter eventFilter) throws UaException {
        return createEventItem(nodeId, eventFilter, item -> {});
    }

    /**
     * Create a {@link ManagedEventItem}.
     * <p>
     * This operation will fail of the Node identified by {@code nodeId} is not an Object Node.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedEventItem#getStatusCode()}.
     *
     * @param nodeId      the {@link NodeId} identifying an Object Node.
     * @param eventFilter the {@link EventFilter} to use.
     * @param consumer    a {@link Consumer} that will receive each item as it is created.
     * @return a {@link ManagedEventItem}.
     * @throws UaException if a service-level error occurs.
     * @see EventFilterBuilder
     */
    public ManagedEventItem createEventItem(
        NodeId nodeId,
        EventFilter eventFilter,
        Consumer<ManagedEventItem> consumer
    ) throws UaException {

        return createEventItems(singletonList(nodeId), singletonList(eventFilter), consumer).get(0);
    }

    /**
     * Create one or more {@link ManagedEventItem}s.
     * <p>
     * This operation will fail of the Node identified by {@code nodeId} is not an Object Node.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedEventItem#getStatusCode()}.
     *
     * @param nodeIds      the {@link NodeId}s identifying Object Nodes.
     * @param eventFilters the corresponding {@link EventFilter} to create each item with.
     * @return a List of {@link ManagedEventItem}s.
     * @throws UaException if a service-level error occurs.
     */
    public List<ManagedEventItem> createEventItems(
        List<NodeId> nodeIds,
        List<EventFilter> eventFilters
    ) throws UaException {

        return createEventItems(nodeIds, eventFilters, item -> {});
    }

    /**
     * Create one or more {@link ManagedEventItem}s.
     * <p>
     * This operation will fail of the Node identified by {@code nodeId} is not an Object Node.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedEventItem#getStatusCode()}.
     *
     * @param nodeIds      the {@link NodeId}s identifying Object Nodes.
     * @param eventFilters the corresponding {@link EventFilter} to create each item with.
     * @param consumer     a {@link Consumer} that will receive each item as it is created.
     * @return a List of {@link ManagedEventItem}s.
     * @throws UaException if a service-level error occurs.
     */
    public List<ManagedEventItem> createEventItems(
        List<NodeId> nodeIds,
        List<EventFilter> eventFilters,
        Consumer<ManagedEventItem> consumer
    ) throws UaException {

        try {
            CompletableFuture<List<ManagedEventItem>> future = createEventItemsAsync(nodeIds, eventFilters, consumer);

            return future.get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Create one or more {@link ManagedEventItem}s.
     * <p>
     * This operation will fail of the Node identified by {@code nodeId} is not an Object Node.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedEventItem#getStatusCode()}.
     * <p>
     * This operation completes asynchronously.
     *
     * @param nodeIds      the {@link NodeId}s identifying Object Nodes.
     * @param eventFilters the corresponding {@link EventFilter} to create each item with.
     * @return a {@link CompletableFuture} that completes successfully with the List of {@link ManagedEventItem}s or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<ManagedEventItem>> createEventItemsAsync(
        List<NodeId> nodeIds,
        List<EventFilter> eventFilters
    ) {

        return createEventItemsAsync(nodeIds, eventFilters, item -> {});
    }

    /**
     * Create one or more {@link ManagedEventItem}s.
     * <p>
     * This operation will fail of the Node identified by {@code nodeId} is not an Object Node.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedEventItem#getStatusCode()}.
     * <p>
     * This operation completes asynchronously.
     *
     * @param nodeIds      the {@link NodeId}s identifying Object Nodes.
     * @param eventFilters the corresponding {@link EventFilter} to create each item with.
     * @param consumer     a {@link Consumer} that will receive each item as it is created.
     * @return a {@link CompletableFuture} that completes successfully with the List of {@link ManagedEventItem}s or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<ManagedEventItem>> createEventItemsAsync(
        List<NodeId> nodeIds,
        List<EventFilter> eventFilters,
        Consumer<ManagedEventItem> consumer
    ) {

        final UInteger queueSize = getDefaultQueueSize();
        final boolean discardOldest = getDefaultDiscardOldest();

        List<MonitoredItemCreateRequest> createRequests = new ArrayList<>();

        for (int i = 0; i < nodeIds.size(); i++) {
            ReadValueId readValueId = new ReadValueId(
                nodeIds.get(i),
                AttributeId.EventNotifier.uid(),
                null,
                QualifiedName.NULL_VALUE
            );

            MonitoringParameters parameters = new MonitoringParameters(
                subscription.nextClientHandle(),
                0.0,
                ExtensionObject.encode(client.getSerializationContext(), eventFilters.get(i)),
                queueSize,
                discardOldest
            );

            createRequests.add(new MonitoredItemCreateRequest(readValueId, getDefaultMonitoringMode(), parameters));
        }

        CompletableFuture<List<UaMonitoredItem>> monitoredItems = subscription.createMonitoredItems(
            getDefaultTimestamps(),
            createRequests,
            (item, id) -> {
                ManagedEventItem eventItem = createAndTrackEventItem(item);
                consumer.accept(eventItem);
            }
        );

        return monitoredItems.thenApply(
            items ->
                items.stream()
                    .map(item -> eventItems.get(item.getClientHandle()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
        );
    }

    /**
     * Delete a {@link ManagedEventItem}.
     * <p>
     * The result of the delete operation can be obtained via {@link ManagedEventItem#getStatusCode()}.
     *
     * @param eventItem the {@link ManagedEventItem} to delete.
     * @throws UaException if a service-level error occurs.
     */
    public void deleteEventItem(ManagedEventItem eventItem) throws UaException {
        deleteEventItems(singletonList(eventItem));
    }

    /**
     * Delete one or more {@link ManagedEventItem}s.
     * <p>
     * The result of the delete operations can be obtained via {@link ManagedEventItem#getStatusCode()}.
     *
     * @param eventItems the {@link ManagedEventItem}s to delete.
     * @throws UaException if a service-level error occurs.
     */
    public void deleteEventItems(List<ManagedEventItem> eventItems) throws UaException {
        try {
            deleteEventItemsAsync(eventItems).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Delete a {@link ManagedEventItem}.
     * <p>
     * The result of the delete operation can be obtained via {@link ManagedEventItem#getStatusCode()}.
     * <p>
     * This call completes asynchronously.
     *
     * @param eventItem the {@link ManagedEventItem} to delete.
     * @return a {@link CompletableFuture} that completes successfully if the service completed successfully or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Unit> deleteEventItemAsync(ManagedEventItem eventItem) {
        return deleteEventItemsAsync(singletonList(eventItem));
    }

    /**
     * Delete one or more {@link ManagedEventItem}s.
     * <p>
     * The result of the delete operations can be obtained via {@link ManagedEventItem#getStatusCode()}.
     * <p>
     * This call completes asynchronously.
     *
     * @param eventItemsToDelete the {@link ManagedEventItem}s to delete.
     * @return a {@link CompletableFuture} that completes successfully if the service completed successfully or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Unit> deleteEventItemsAsync(List<ManagedEventItem> eventItemsToDelete) {
        List<UaMonitoredItem> monitoredItems = eventItemsToDelete.stream()
            .map(ManagedItem::getMonitoredItem)
            .collect(Collectors.toList());

        return subscription.deleteMonitoredItems(monitoredItems).thenCompose(statusCodes -> {
            for (UaMonitoredItem monitoredItem : monitoredItems) {
                this.eventItems.remove(monitoredItem.getClientHandle());
            }

            return completedFuture(Unit.VALUE);
        });
    }

    private ManagedEventItem createAndTrackEventItem(UaMonitoredItem item) {
        ManagedEventItem eventItem = new ManagedEventItem(client, this, (OpcUaMonitoredItem) item);
        eventItems.put(item.getClientHandle(), eventItem);
        return eventItem;
    }

    //endregion

    /**
     * Get this Subscription's current publishing interval.
     *
     * @return this Subscription's current publishing interval.
     */
    public double getPublishingInterval() {
        return subscription.getRevisedPublishingInterval();
    }

    /**
     * Request a new publishing interval for this Subscription.
     *
     * @param publishingInterval the requested publishing interval.
     * @return the new publishing interval, possibly revised by the server.
     * @throws UaException if a service-level error occurs.
     */
    public double setPublishingInterval(double publishingInterval) throws UaException {
        try {
            CompletableFuture<Double> future =
                setPublishingIntervalAsync(publishingInterval);

            return future.get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Request a new publishing interval for this Subscription.
     * <p>
     * This call completes asynchronously.
     *
     * @param publishingInterval the requested publishing interval.
     * @return a {@link CompletableFuture} that completes successfully with the new publishing interval or completes
     * exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Double> setPublishingIntervalAsync(double publishingInterval) {
        CompletableFuture<UaSubscription> future = client.getSubscriptionManager()
            .modifySubscription(subscription.getSubscriptionId(), publishingInterval);

        return future.thenApply(UaSubscription::getRevisedPublishingInterval);
    }

    /**
     * Get this Subscription's current publishing mode.
     *
     * @return {@code true} if publishing is enabled, {@code false} if publishing is disabled.
     */
    public boolean isPublishingEnabled() {
        return subscription.isPublishingEnabled();
    }

    /**
     * Set this Subscription's publishing mode.
     *
     * @param enabled {@code true} to enable publishing, {@code false} to disable publishing.
     * @throws UaException if a service-level error occurs.
     */
    public void setPublishingEnabled(boolean enabled) throws UaException {
        try {
            setPublishingEnabledAsync(enabled).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Set this Subscription's publishing mode.
     *
     * @param enabled {@code true} to enable publishing, {@code false} to disable publishing.
     * @return a {@link CompletableFuture} that completes successfully if the publishing mode was set or completes
     * exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Unit> setPublishingEnabledAsync(boolean enabled) {
        return subscription.setPublishingMode(enabled).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(Unit.VALUE);
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    //region default MonitoredItemCreateRequest values

    /**
     * Get the default {@link MonitoringMode} new items will be created in.
     *
     * @return the default {@link MonitoringMode} new items will be created in.
     */
    public synchronized MonitoringMode getDefaultMonitoringMode() {
        return defaultMonitoringMode;
    }

    /**
     * Set the new default {@link MonitoringMode} new items will be created in.
     *
     * @param defaultMonitoringMode the new default {@link MonitoringMode} new items will be created in.
     */
    public synchronized void setDefaultMonitoringMode(MonitoringMode defaultMonitoringMode) {
        this.defaultMonitoringMode = defaultMonitoringMode;
    }

    /**
     * Get the sampling interval used in calls where it is not specified explicitly.
     *
     * @return the sampling interval used in calls where it is not specified explicitly.
     */
    public synchronized double getDefaultSamplingInterval() {
        return defaultSamplingInterval;
    }

    /**
     * Set the sampling interval used in calls where it is not specified explicitly.
     *
     * @param defaultSamplingInterval the sampling interval used in calls where it is not specified explicitly.
     */
    public synchronized void setDefaultSamplingInterval(double defaultSamplingInterval) {
        this.defaultSamplingInterval = defaultSamplingInterval;
    }

    /**
     * Get the queue size used when creating {@link ManagedDataItem}s.
     *
     * @return the queue size used when creating {@link ManagedDataItem}s.
     */
    public synchronized UInteger getDefaultQueueSize() {
        return defaultQueueSize;
    }

    /**
     * Set the queue size used when creating {@link ManagedDataItem}s.
     *
     * @param defaultQueueSize the queue size used when creating {@link ManagedDataItem}s.
     */
    public synchronized void setDefaultQueueSize(UInteger defaultQueueSize) {
        this.defaultQueueSize = defaultQueueSize;
    }

    /**
     * Get the encoded {@link DataChangeFilter} used when creating {@link ManagedDataItem}s.
     *
     * @return the encoded {@link DataChangeFilter} used when creating {@link ManagedDataItem}s.
     */
    @Nullable
    public synchronized ExtensionObject getDefaultDataFilter() {
        return defaultDataFilter;
    }

    /**
     * Set the {@link DataChangeFilter} used when creating {@link ManagedDataItem}s.
     * <p>
     * The default value is {@code null}, which is interpreted by servers as a filter triggered by changes in Status
     * or Value with no deadband applied.
     *
     * @param defaultDataFilter the {@link DataChangeFilter} to use when creating {@link ManagedDataItem}s.
     */
    public synchronized void setDefaultDataFilter(@Nullable DataChangeFilter defaultDataFilter) {
        this.defaultDataFilter = defaultDataFilter != null ?
            ExtensionObject.encode(client.getSerializationContext(), defaultDataFilter) : null;
    }

    /**
     * Get the default {@link TimestampsToReturn} used when creating {@link ManagedDataItem}s.
     *
     * @return the default {@link TimestampsToReturn} used when creating {@link ManagedDataItem}s.
     */
    public synchronized TimestampsToReturn getDefaultTimestamps() {
        return defaultTimestamps;
    }

    /**
     * Set the default {@link TimestampsToReturn} used when creating {@link ManagedDataItem}s.
     *
     * @param defaultTimestamps the default {@link TimestampsToReturn} used when creating {@link ManagedDataItem}s.
     */
    public synchronized void setDefaultTimestamps(TimestampsToReturn defaultTimestamps) {
        this.defaultTimestamps = defaultTimestamps;
    }

    /**
     * Get the default discard policy used when creating {@link ManagedDataItem}s and {@link ManagedEventItem}s.
     *
     * @return the default discard policy used when creating {@link ManagedDataItem}s and {@link ManagedEventItem}s.
     */
    public synchronized boolean getDefaultDiscardOldest() {
        return defaultDiscardOldest;
    }

    /**
     * Set the default discard policy used when creating {@link ManagedDataItem}s and {@link ManagedEventItem}s.
     *
     * @param defaultDiscardOldest the default discard policy used when creating {@link ManagedDataItem}s and
     *                             {@link ManagedEventItem}s.
     */
    public synchronized void setDefaultDiscardOldest(boolean defaultDiscardOldest) {
        this.defaultDiscardOldest = defaultDiscardOldest;
    }

    //endregion

    //region listener bookkeeping

    /**
     * Add a {@link ChangeListener} to this {@link ManagedSubscription}.
     *
     * @param changeListener the {@link ChangeListener} to add.
     */
    public void addChangeListener(ChangeListener changeListener) {
        changeListeners.add(changeListener);
    }

    /**
     * Remove a {@link ChangeListener} from this {@link ManagedSubscription}.
     *
     * @param changeListener the {@link ChangeListener} to remove.
     * @return {@code true} if the listener was removed.
     */
    public boolean removeChangeListener(ChangeListener changeListener) {
        return changeListeners.remove(changeListener);
    }

    /**
     * Add a {@link StatusListener} to this {@link ManagedSubscription}.
     *
     * @param statusListener the {@link StatusListener} to add.
     */
    public void addStatusListener(StatusListener statusListener) {
        statusListeners.add(statusListener);
    }

    /**
     * Remove a {@link StatusListener} from this {@link ManagedSubscription}.
     *
     * @param statusListener the {@link StatusListener} to remove.
     * @return {@code true} if the listener was removed.
     */
    public boolean removeStatusListener(StatusListener statusListener) {
        return statusListeners.remove(statusListener);
    }

    /**
     * Add a data {@link ChangeListener} to this {@link ManagedSubscription}.
     * <p>
     * {@code biConsumer} will be invoked when new values for {@link ManagedDataItem}s have arrived via
     * {@link ChangeListener#onDataReceived(List, List)}.
     * <p>
     * The listener is transformed into the returned {@link ChangeListener} that can later be removed.
     *
     * @param biConsumer a {@link BiConsumer} that will be invoked when new values for {@link ManagedDataItem}s have
     *                   arrived.
     * @return a {@link ChangeListener} that can later be removed.
     */
    public ChangeListener addDataChangeListener(BiConsumer<List<ManagedDataItem>, List<DataValue>> biConsumer) {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void onDataReceived(List<ManagedDataItem> dataItems, List<DataValue> dataValues) {
                biConsumer.accept(dataItems, dataValues);
            }
        };

        addChangeListener(changeListener);

        return changeListener;
    }

    /**
     * Add an event {@link ChangeListener} to this {@link ManagedSubscription}.
     * <p>
     * {@code biConsumer} will be invoked when new events for {@link ManagedEventItem}s have arrived via
     * {@link ChangeListener#onEventReceived(List, List)}.
     * <p>
     * The listener is transformed into the returned {@link ChangeListener} that can later be removed.
     *
     * @param biConsumer a {@link BiConsumer} that will be invoked when new events for {@link ManagedEventItem}s have
     *                   arrived.
     * @return a {@link ChangeListener} that can later be removed.
     */
    public ChangeListener addEventChangeListener(BiConsumer<List<ManagedEventItem>, List<Variant[]>> biConsumer) {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void onEventReceived(List<ManagedEventItem> eventItems, List<Variant[]> eventFields) {
                biConsumer.accept(eventItems, eventFields);
            }
        };

        addChangeListener(changeListener);

        return changeListener;
    }

    //endregion

    /**
     * Delete this {@link ManagedSubscription} and its underlying {@link OpcUaSubscription}.
     *
     * @throws UaException if a service-level error occurs.
     */
    public void delete() throws UaException {
        try {
            deleteAsync().get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Delete this {@link ManagedSubscription} and its underlying {@link OpcUaSubscription}.
     * <p>
     * This call completes asynchronously.
     *
     * @return a {@link CompletableFuture} that completes successfully if the service completed successfully or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Unit> deleteAsync() {
        return client.getSubscriptionManager()
            .deleteSubscription(subscription.getSubscriptionId())
            .thenApply(s -> Unit.VALUE);
    }

    /**
     * Create a {@link ManagedSubscription} with the default publishing interval.
     *
     * @param client the {@link OpcUaClient} instance to create the subscription with.
     * @return a {@link ManagedSubscription}.
     * @throws UaException if a service-level error occurs preventing the subscription from being created.
     * @see ManagedSubscription#DEFAULT_PUBLISHING_INTERVAL
     */
    public static ManagedSubscription create(OpcUaClient client) throws UaException {
        return create(client, DEFAULT_PUBLISHING_INTERVAL);
    }

    /**
     * Create a {@link ManagedSubscription} with the publishing interval specified by {@code publishingInterval}.
     *
     * @param client             the {@link OpcUaClient} instance to create the subscription with.
     * @param publishingInterval the publishing interval to request when creating the subscription.
     * @return a {@link ManagedSubscription}.
     * @throws UaException if a service-level error occurs preventing the subscription from being created.
     */
    public static ManagedSubscription create(OpcUaClient client, double publishingInterval) throws UaException {
        try {
            return createAsync(client, publishingInterval).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Create a {@link ManagedSubscription} with the publishing interval specified by {@code publishingInterval}.
     * <p>
     * This call completes asynchronously.
     *
     * @param client             the {@link OpcUaClient} instance to create the subscription with.
     * @param publishingInterval the publishing interval to request when creating the subscription.
     * @return a {@link CompletableFuture} that completes successfully with the new subscription or completes
     * exceptionally if a service-level error occurs preventing the subscription from being created.
     */
    public static CompletableFuture<ManagedSubscription> createAsync(OpcUaClient client, double publishingInterval) {
        CompletableFuture<UaSubscription> future =
            client.getSubscriptionManager().createSubscription(publishingInterval);

        return future.thenApply(subscription -> new ManagedSubscription(client, (OpcUaSubscription) subscription));
    }

    /**
     * A callback that receives data and event change notifications for {@link ManagedDataItem}s and
     * {@link ManagedEventItem}s belonging to a {@link ManagedSubscription}.
     */
    public interface ChangeListener {

        /**
         * New values for {@code dataItems} have arrived.
         * <p>
         * Take care not to block unnecessarily in this callback because subscription notifications are processed
         * synchronously as a backpressure mechanism. Blocking inside this callback will prevent subsequent
         * notifications from being processed and new PublishRequests from being sent.
         *
         * @param dataItems  the list of {@link ManagedDataItem}s for which new values have arrived.
         * @param dataValues the corresponding {@link DataValue} for each {@link ManagedDataItem} in {@code dataItems}.
         */
        default void onDataReceived(List<ManagedDataItem> dataItems, List<DataValue> dataValues) {}

        /**
         * New events for {@code eventItems} have arrived.
         * <p>
         * Take care not to block unnecessarily in this callback because subscription notifications are processed
         * synchronously as a backpressure mechanism. Blocking inside this callback will prevent subsequent
         * notifications from being processed and new PublishRequests from being sent.
         *
         * @param eventItems  the list of {@link ManagedEventItem} for which new events have arrived.
         * @param eventFields the corresponding event field values for each {@link ManagedEventItem} in
         *                    {@code eventItems}.
         */
        default void onEventReceived(List<ManagedEventItem> eventItems, List<Variant[]> eventFields) {}

        /**
         * The keep alive interval for the subscription has expired without any value changes and an empty
         * "keep alive" DataChangeNotification has arrived. No action is required.
         */
        default void onKeepAliveReceived() {}

    }

    /**
     * A callback that receives status-related notifications for a {@link ManagedSubscription}.
     */
    public interface StatusListener {

        /**
         * Notification data for this subscription was lost because attempts to call the Republish service failed.
         * <p>
         * Some action is required to ensure all the data items belonging to this subscription have a current value.
         * The default implementation asynchronously invokes the the ResendData method on the server with this
         * subscription's id as the argument.
         *
         * @param subscription the {@link ManagedSubscription} for which notification data was lost.
         */
        default void onNotificationDataLost(ManagedSubscription subscription) {
            subscription.getClient().call(
                new CallMethodRequest(
                    Identifiers.Server,
                    Identifiers.Server_ResendData,
                    new Variant[]{new Variant(subscription.getSubscription().getSubscriptionId())}
                )
            );
        }

        /**
         * A status change notification for {@code subscription} was received.
         * <p>
         * Generally this happens in 2 scenarios:
         * <ol>
         *     <li>
         *     the subscription has timed out on the server and no longer exists. In this case the {@code statusCode}
         *     should be {@link StatusCodes#Bad_Timeout}. If data and event change notifications for items belonging to
         *     this subscription are still desired then the subscription should be re-created.
         *     </li>
         *     <li>
         *     the subscription has been transferred to another session. In this case the {@code statusCode} should be
         *     {@link StatusCodes#Good_SubscriptionTransferred} and no further action is necessary.
         *     </li>
         * </ol>
         *
         * @param subscription the {@link ManagedSubscription} for which a status change notification was received.
         * @param statusCode   the {@link StatusCode} from the status change notification.
         */
        default void onSubscriptionStatusChanged(ManagedSubscription subscription, StatusCode statusCode) {}

        /**
         * A subscription transfer has failed.
         * <p>
         * Upon reconnecting after a connection interruption an {@link OpcUaClient} will attempt to resume its previous
         * Session. If this fails a new Session is created and the Subscriptions from the previous Session are
         * transferred to the new one.
         * <p>
         * This callback is notified when the transfer to the new session has failed and indicates that the
         * subscription and all of its {@link ManagedDataItem}s and {@link ManagedEventItem}s will need to be created
         * again.
         *
         * @param subscription the {@link ManagedSubscription} for which transfer has failed.
         * @param statusCode   the {@link StatusCode} for the transfer failure.
         */
        default void onSubscriptionTransferFailed(ManagedSubscription subscription, StatusCode statusCode) {}

    }

    /**
     * A {@link UaSubscription.NotificationListener} used internally by {@link ManagedSubscription} to notify its
     * {@link ChangeListener}s and {@link StatusListener}s.
     */
    private class ManagedSubscriptionNotificationListener implements
        UaSubscriptionManager.SubscriptionListener, UaSubscription.NotificationListener {

        private final ExecutionQueue executionQueue = new ExecutionQueue(client.getConfig().getExecutor());

        //region UaSubscription.NotificationListener

        @Override
        public void onDataChangeNotification(
            UaSubscription subscription,
            List<UaMonitoredItem> monitoredItems,
            List<DataValue> dataValues,
            DateTime publishTime
        ) {

            List<ManagedDataItem> itemsToNotify = new ArrayList<>(monitoredItems.size());
            List<DataValue> valuesToNotify = new ArrayList<>(monitoredItems.size());

            for (int i = 0; i < monitoredItems.size(); i++) {
                UInteger key = monitoredItems.get(i).getClientHandle();
                ManagedDataItem dataItem = dataItems.get(key);
                if (dataItem != null) {
                    itemsToNotify.add(dataItem);
                    valuesToNotify.add(dataValues.get(i));
                }
            }

            changeListeners.forEach(
                changeListener ->
                    changeListener.onDataReceived(itemsToNotify, valuesToNotify)
            );
        }

        @Override
        public void onEventNotification(
            UaSubscription subscription,
            List<UaMonitoredItem> monitoredItems,
            List<Variant[]> eventFields,
            DateTime publishTime
        ) {

            List<ManagedEventItem> itemsToNotify = new ArrayList<>(monitoredItems.size());
            List<Variant[]> fieldsToNotify = new ArrayList<>(monitoredItems.size());

            for (int i = 0; i < monitoredItems.size(); i++) {
                UInteger key = monitoredItems.get(i).getClientHandle();
                ManagedEventItem eventItem = eventItems.get(key);
                if (eventItem != null) {
                    itemsToNotify.add(eventItem);
                    fieldsToNotify.add(eventFields.get(i));
                }
            }

            changeListeners.forEach(
                changeListener ->
                    changeListener.onEventReceived(itemsToNotify, fieldsToNotify)
            );
        }

        @Override
        public void onKeepAliveNotification(UaSubscription subscription, DateTime publishTime) {
            changeListeners.forEach(ChangeListener::onKeepAliveReceived);
        }

        @Override
        public void onStatusChangedNotification(UaSubscription subscription, StatusCode status) {
            executionQueue.submit(() ->
                statusListeners.forEach(
                    statusListener ->
                        statusListener.onSubscriptionStatusChanged(ManagedSubscription.this, status)
                )
            );
        }

        //endregion

        //region UaSubscriptionManager.SubscriptionListener

        // These callbacks need to be filtered to see if they are for the
        // UaSubscription backing this ManagedSubscription instance before
        // doing notification.

        @Override
        public void onNotificationDataLost(UaSubscription subscription) {
            if (ManagedSubscription.this.subscription.getSubscriptionId().equals(subscription.getSubscriptionId())) {
                executionQueue.submit(() ->
                    statusListeners.forEach(
                        statusListener ->
                            statusListener.onNotificationDataLost(ManagedSubscription.this)
                    )
                );
            }
        }

        @Override
        public void onSubscriptionTransferFailed(UaSubscription subscription, StatusCode statusCode) {
            if (ManagedSubscription.this.subscription.getSubscriptionId().equals(subscription.getSubscriptionId())) {
                executionQueue.submit(() ->
                    statusListeners.forEach(
                        statusListener ->
                            statusListener.onSubscriptionTransferFailed(ManagedSubscription.this, statusCode)
                    )
                );
            }
        }

        //endregion

    }

}

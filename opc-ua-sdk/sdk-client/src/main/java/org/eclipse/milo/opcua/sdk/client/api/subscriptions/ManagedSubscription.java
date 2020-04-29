/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.subscriptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscription;
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

    private final Map<UaMonitoredItem, ManagedDataItem> dataItems = new ConcurrentHashMap<>();
    private final Map<UaMonitoredItem, ManagedEventItem> eventItems = new ConcurrentHashMap<>();

    private double defaultSamplingInterval = DEFAULT_SAMPLING_INTERVAL;
    private UInteger defaultQueueSize = DEFAULT_QUEUE_SIZE;
    private TimestampsToReturn defaultTimestamps = TimestampsToReturn.Both;
    private ExtensionObject defaultDataFilter = null;

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
     * different attribute use {@link #createDataItem(ReadValueId, double)}.
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
     * Create {@link ManagedDataItem}s monitoring the Value attribute of the Nodes identified by {@code nodeIds}.
     * <p>
     * This operation will fail of the Node is not a Variable or VariableType Node. To create items that monitor
     * different attributes use {@link #createDataItems(List, double)}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param nodeIds the {@link NodeId}s identifying a Variable or VariableType Nodes.
     * @return a List of {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public List<ManagedDataItem> createDataItems(List<NodeId> nodeIds) throws UaException {
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

        return createDataItems(readValueIds, getDefaultSamplingInterval());
    }

    /**
     * Create a {@link ManagedDataItem} monitoring the Node and attribute identified by {@code readValueId}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param readValueId      the {@link ReadValueId} identifying the Node and attribute to monitor.
     * @param samplingInterval the rate to request this item is sampled at.
     * @return a {@link ManagedDataItem}.
     * @throws UaException if a service-level error occurs.
     */
    public ManagedDataItem createDataItem(ReadValueId readValueId, double samplingInterval) throws UaException {
        return createDataItems(singletonList(readValueId), samplingInterval).get(0);
    }

    /**
     * Create {@link ManagedDataItem}s for the Nodes and attributes identified by {@code readValueIds}.
     * <p>
     * The operation result of each item should be checked before the item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param readValueIds     the {@link ReadValueId}s identifying the Nodes and attributes to monitor.
     * @param samplingInterval the rate to request these items are sampled at.
     * @return a List of {@link ManagedDataItem}s.
     * @throws UaException if a service-level error occurs.
     */
    public List<ManagedDataItem> createDataItems(
        List<ReadValueId> readValueIds,
        double samplingInterval
    ) throws UaException {

        try {
            CompletableFuture<List<ManagedDataItem>> future = createDataItemsAsync(
                readValueIds,
                samplingInterval
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
     * Create a {@link ManagedDataItem} monitoring the Node and attribute identified by {@code readValueId}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     * <p>
     * This call completes asynchronously.
     *
     * @param readValueIds     the {@link ReadValueId}s identifying the items to create.
     * @param samplingInterval the rate to request these items are sampled at.
     * @return a {@link CompletableFuture} that completes successfully if the service call succeeds or completes
     * exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<ManagedDataItem>> createDataItemsAsync(
        List<ReadValueId> readValueIds,
        double samplingInterval
    ) {

        final ExtensionObject filter = getDefaultDataFilter();
        final UInteger queueSize = getDefaultQueueSize();

        List<MonitoredItemCreateRequest> createRequests = readValueIds.stream()
            .map(readValueId -> {
                MonitoringParameters parameters = new MonitoringParameters(
                    subscription.nextClientHandle(),
                    samplingInterval,
                    filter,
                    queueSize,
                    true
                );

                return new MonitoredItemCreateRequest(readValueId, MonitoringMode.Reporting, parameters);
            })
            .collect(Collectors.toList());

        CompletableFuture<List<UaMonitoredItem>> monitoredItems = subscription.createMonitoredItems(
            getDefaultTimestamps(),
            createRequests
        );

        return monitoredItems.thenApply(
            items ->
                items.stream()
                    .map(this::createAndTrackDataItem)
                    .collect(Collectors.toList())
        );
    }

    public StatusCode deleteDataItem(ManagedDataItem dataItem) throws UaException {
        try {
            CompletableFuture<StatusCode> future = deleteDataItemAsync(dataItem);

            return future.get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<StatusCode> deleteDataItemAsync(ManagedDataItem dataItem) {
        CompletableFuture<List<StatusCode>> future =
            subscription.deleteMonitoredItems(singletonList(dataItem.getMonitoredItem()));

        return future.thenApply(results -> results.get(0));
    }

    private ManagedDataItem createAndTrackDataItem(UaMonitoredItem item) {
        ManagedDataItem dataItem = new ManagedDataItem(client, this, (OpcUaMonitoredItem) item);
        dataItems.put((OpcUaMonitoredItem) item, dataItem);
        return dataItem;
    }

    //endregion

    //region create/delete ManagedEventItem

    public ManagedEventItem createEventItem(NodeId nodeId, EventFilter eventFilter) throws UaException {
        return createEventItems(singletonList(nodeId), eventFilter).get(0);
    }

    public List<ManagedEventItem> createEventItems(List<NodeId> nodeId, EventFilter eventFilter) throws UaException {
        try {
            CompletableFuture<List<ManagedEventItem>> future = createEventItemsAsync(nodeId, eventFilter);

            return future.get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<List<ManagedEventItem>> createEventItemsAsync(
        List<NodeId> nodeIds,
        EventFilter eventFilter
    ) {

        List<MonitoredItemCreateRequest> createRequests = nodeIds.stream()
            .map(nodeId -> {
                ReadValueId readValueId = new ReadValueId(
                    nodeId,
                    AttributeId.EventNotifier.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                );

                MonitoringParameters parameters = new MonitoringParameters(
                    subscription.nextClientHandle(),
                    0.0,
                    ExtensionObject.encode(client.getSerializationContext(), eventFilter),
                    DEFAULT_QUEUE_SIZE,
                    true
                );

                return new MonitoredItemCreateRequest(readValueId, MonitoringMode.Reporting, parameters);
            })
            .collect(Collectors.toList());

        CompletableFuture<List<UaMonitoredItem>> monitoredItems = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            createRequests
        );

        return monitoredItems.thenApply(
            items ->
                items.stream()
                    .map(this::createAndTrackEventItem)
                    .collect(Collectors.toList())
        );
    }

    public StatusCode deleteEventItem(ManagedEventItem eventItem) throws UaException {
        try {
            CompletableFuture<StatusCode> future = deleteEventItemAsync(eventItem);

            return future.get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<StatusCode> deleteEventItemAsync(ManagedEventItem eventItem) {
        CompletableFuture<List<StatusCode>> future =
            subscription.deleteMonitoredItems(singletonList(eventItem.getMonitoredItem()));

        return future.thenApply(results -> results.get(0));
    }

    private ManagedEventItem createAndTrackEventItem(UaMonitoredItem item) {
        ManagedEventItem eventItem = new ManagedEventItem(client, (OpcUaMonitoredItem) item);
        eventItems.put((OpcUaMonitoredItem) item, eventItem);
        return eventItem;
    }

    //endregion

    //region default monitoring parameters

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
     * Get this Subscription's current publishing interval.
     *
     * @return this Subscription's current publishing interval.
     */
    public double getPublishingInterval() {
        return subscription.getRevisedPublishingInterval();
    }

    /**
     * Set this Subscription's publishing mode.
     *
     * @param enabled {@code true} to enable publishing, {@code false} to disable publishing.
     * @throws UaException if a service-level error occurs.
     */
    public void setPublishingMode(boolean enabled) throws UaException {
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

    /**
     * Get this Subscription's current publishing mode.
     *
     * @return {@code true} if publishing is enabled, {@code false} if publishing is disabled.
     */
    public boolean isPublishingEnabled() {
        return subscription.isPublishingEnabled();
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
     * Get the sampling interval used in calls where it is not specified explicitly.
     *
     * @return the sampling interval used in calls where it is not specified explicitly.
     */
    public synchronized double getDefaultSamplingInterval() {
        return defaultSamplingInterval;
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
     * Get the queue size used when creating {@link ManagedDataItem}s.
     *
     * @return the queue size used when creating {@link ManagedDataItem}s.
     */
    public synchronized UInteger getDefaultQueueSize() {
        return defaultQueueSize;
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
     * Get the encoded {@link DataChangeFilter} used when creating {@link ManagedDataItem}s.
     *
     * @return the encoded {@link DataChangeFilter} used when creating {@link ManagedDataItem}s.
     */
    @Nullable
    public synchronized ExtensionObject getDefaultDataFilter() {
        return defaultDataFilter;
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
     * Get the default {@link TimestampsToReturn} used when creating {@link ManagedDataItem}s.
     *
     * @return the default {@link TimestampsToReturn} used when creating {@link ManagedDataItem}s.
     */
    public synchronized TimestampsToReturn getDefaultTimestamps() {
        return defaultTimestamps;
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
     */
    public void removeChangeListener(ChangeListener changeListener) {
        changeListeners.remove(changeListener);
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
     */
    public void removeStatusListener(StatusListener statusListener) {
        statusListeners.remove(statusListener);
    }

    /**
     * TODO
     *
     * @param bc
     * @return
     */
    public ChangeListener addDataChangeListener(BiConsumer<List<ManagedDataItem>, List<DataValue>> bc) {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void onDataReceived(List<ManagedDataItem> dataItems, List<DataValue> dataValues) {
                bc.accept(dataItems, dataValues);
            }
        };

        addChangeListener(changeListener);

        return changeListener;
    }

    /**
     * TODO
     *
     * @param bc
     * @return
     */
    public ChangeListener addEventChangeListener(BiConsumer<List<ManagedEventItem>, List<Variant[]>> bc) {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void onEventReceived(List<ManagedEventItem> eventItems, List<Variant[]> eventFields) {
                bc.accept(eventItems, eventFields);
            }
        };

        addChangeListener(changeListener);

        return changeListener;
    }

    //endregion

    /**
     * Delete this {@link ManagedSubscription} and its underlying {@link OpcUaSubscription}.
     *
     * @return the operation-level {@link StatusCode}.
     * @throws UaException if a service-level error occurs.
     */
    public StatusCode delete() throws UaException {
        try {
            return deleteAsync().get();
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
     * @return a {@link CompletableFuture} that completes successfully with the operation result or completes
     * exceptionally if a service-level error occurs.
     */
    public CompletableFuture<StatusCode> deleteAsync() {
        return client.getSubscriptionManager()
            .deleteSubscription(subscription.getSubscriptionId())
            .thenApply(s -> StatusCode.GOOD);
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
         * TODO
         *
         * @param subscription
         * @param statusCode
         */
        default void onSubscriptionStatusChanged(ManagedSubscription subscription, StatusCode statusCode) {}

        /**
         * TODO
         *
         * @param subscription
         * @param statusCode
         */
        default void onSubscriptionTransferFailed(ManagedSubscription subscription, StatusCode statusCode) {}

    }

    /**
     * A {@link UaSubscription.NotificationListener} used internally by {@link ManagedSubscription} to notify its
     * {@link ChangeListener}s and {@link StatusListener}s.
     */
    private class ManagedSubscriptionNotificationListener implements UaSubscription.NotificationListener {

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
                UaMonitoredItem key = monitoredItems.get(i);
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
                UaMonitoredItem key = monitoredItems.get(i);
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

    }

}

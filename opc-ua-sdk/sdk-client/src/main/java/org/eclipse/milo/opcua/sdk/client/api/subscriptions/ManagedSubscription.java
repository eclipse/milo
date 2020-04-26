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
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscription;
import org.eclipse.milo.opcua.stack.core.AttributeId;
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

    private final CopyOnWriteArrayList<DataListener> dataListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<EventListener> eventListeners = new CopyOnWriteArrayList<>();

    private final Map<UaMonitoredItem, ManagedDataItem> dataItems = new ConcurrentHashMap<>();
    private final Map<UaMonitoredItem, ManagedEventItem> eventItems = new ConcurrentHashMap<>();

    // TODO default filter?
    // TODO default queue size?
    // TODO default sampling interval?
    // TODO default timestamps?

    private final OpcUaClient client;
    private final OpcUaSubscription subscription;

    public ManagedSubscription(OpcUaClient client, OpcUaSubscription subscription) {
        this.client = client;
        this.subscription = subscription;

        subscription.addNotificationListener(new ManagedSubscriptionNotificationListener());
    }

    //region create/delete ManagedDataItem

    public ManagedDataItem createDataItem(NodeId nodeId) throws UaException {
        ReadValueId readValueId = new ReadValueId(
            nodeId,
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        return createDataItem(readValueId, DEFAULT_SAMPLING_INTERVAL);
    }

    public ManagedDataItem createDataItem(ReadValueId readValueId, double samplingInterval) throws UaException {
        return createDataItems(singletonList(readValueId), samplingInterval).get(0);
    }

    /**
     * Create {@link ManagedDataItem}s for each of the {@link ReadValueId}s in {@code readValueIds}.
     * <p>
     * The operation result should be checked before this item is used further.
     * See {@link ManagedDataItem#getStatusCode()}.
     *
     * @param readValueIds     the {@link ReadValueId}s identifying the items to create.
     * @param samplingInterval the rate to request these items are sampled at.
     * @return a List of {@link ManagedDataItem}s.s
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
     * Create {@link ManagedDataItem}s for each of the {@link ReadValueId}s in {@code readValueIds}.
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

        List<MonitoredItemCreateRequest> createRequests = readValueIds.stream()
            .map(readValueId -> {
                MonitoringParameters parameters = new MonitoringParameters(
                    subscription.nextClientHandle(),
                    samplingInterval,
                    null,
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
        ManagedDataItem dataItem = new ManagedDataItem(client, (OpcUaMonitoredItem) item);
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
        return null;
    }

    private ManagedEventItem createAndTrackEventItem(UaMonitoredItem item) {
        ManagedEventItem eventItem = new ManagedEventItem(client, (OpcUaMonitoredItem) item);
        eventItems.put((OpcUaMonitoredItem) item, eventItem);
        return eventItem;
    }

    //endregion

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
     * Get the underlying {@link OpcUaSubscription}.
     *
     * @return the underlying {@link OpcUaSubscription}.
     */
    public OpcUaSubscription getSubscription() {
        return subscription;
    }

    /**
     * Add a {@link DataListener} to this {@link ManagedSubscription}.
     *
     * @param dataListener the {@link DataListener} to add.
     */
    public void addDataListener(DataListener dataListener) {
        dataListeners.add(dataListener);
    }

    /**
     * Remove a {@link DataListener} from this {@link ManagedSubscription}.
     *
     * @param dataListener the {@link DataListener} to remove.
     */
    public void removeDataListener(DataListener dataListener) {
        dataListeners.remove(dataListener);
    }

    /**
     * Add an {@link EventListener} to this {@link ManagedSubscription}.
     *
     * @param eventListener the {@link EventListener} to add.
     */
    public void addEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    /**
     * Remove an {@link EventListener} from this {@link ManagedSubscription}.
     *
     * @param eventListener the {@link EventListener} to remove.
     */
    public void removeEventListener(EventListener eventListener) {
        eventListeners.remove(eventListener);
    }

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
     * A callback that receives notification of new values for {@link ManagedDataItem}s belonging to a
     * {@link ManagedSubscription}.
     */
    public interface DataListener {

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
        void onDataReceived(List<ManagedDataItem> dataItems, List<DataValue> dataValues);

    }

    /**
     * A callback that receives notification of new events for {@link ManagedEventItem}s belonging to a
     * {@link ManagedSubscription}.
     */
    public interface EventListener {

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
        void onEventReceived(List<ManagedEventItem> eventItems, List<Variant[]> eventFields);

    }

    /**
     * A {@link UaSubscription.NotificationListener} used internally by {@link ManagedSubscription} to notify its
     * {@link DataListener}s and {@link EventListener}s.
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

            dataListeners.forEach(
                dataListener ->
                    dataListener.onDataReceived(itemsToNotify, valuesToNotify)
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

            eventListeners.forEach(
                eventListener ->
                    eventListener.onEventReceived(itemsToNotify, fieldsToNotify)
            );
        }

    }

}

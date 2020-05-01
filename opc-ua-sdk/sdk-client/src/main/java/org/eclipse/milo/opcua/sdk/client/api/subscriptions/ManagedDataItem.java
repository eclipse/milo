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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static java.util.Collections.singletonList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class ManagedDataItem {

    private final CopyOnWriteArrayList<DataValueListener> dataValueListeners = new CopyOnWriteArrayList<>();

    private UaMonitoredItem.ValueConsumer valueConsumer = null;

    private final OpcUaClient client;
    private final ManagedSubscription subscription;
    private final OpcUaMonitoredItem item;

    ManagedDataItem(OpcUaClient client, ManagedSubscription subscription, OpcUaMonitoredItem item) {
        this.client = client;
        this.subscription = subscription;
        this.item = item;
    }

    /**
     * Get the {@link OpcUaClient} that created this {@link ManagedDataItem}.
     *
     * @return the {@link OpcUaClient} that created this {@link ManagedDataItem}.
     */
    public OpcUaClient getClient() {
        return client;
    }

    /**
     * Get the underlying {@link OpcUaMonitoredItem}.
     *
     * @return the underlying {@link OpcUaMonitoredItem}.
     */
    public OpcUaMonitoredItem getMonitoredItem() {
        return item;
    }

    /**
     * Get the {@link NodeId} component of the {@link ReadValueId} for this item.
     *
     * @return the {@link NodeId} component of the {@link ReadValueId} for this item.
     */
    public NodeId getNodeId() {
        return getReadValueId().getNodeId();
    }

    /**
     * Get the {@link ReadValueId} for this item.
     *
     * @return the {@link ReadValueId} for this item.
     */
    public ReadValueId getReadValueId() {
        return item.getReadValueId();
    }

    /**
     * Get the {@link StatusCode} of the last operation this item was part of.
     *
     * @return the {@link StatusCode} of the last operation this item was part of.
     */
    public StatusCode getStatusCode() {
        return item.getStatusCode();
    }

    //region MonitoringMode operations

    /**
     * Get this item's current {@link MonitoringMode}.
     *
     * @return this item's current {@link MonitoringMode}.
     * @see UaMonitoredItem#getMonitoringMode()
     */
    public MonitoringMode getMonitoringMode() {
        return item.getMonitoringMode();
    }

    public void setMonitoringMode(MonitoringMode monitoringMode) throws UaException {
        try {
            setMonitoringModeAsync(monitoringMode).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<Unit> setMonitoringModeAsync(MonitoringMode monitoringMode) {
        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().setMonitoringMode(
            monitoringMode,
            singletonList(item)
        );

        return future.thenApply(statusCodes -> statusCodes.get(0)).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(Unit.VALUE);
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    //endregion

    //region SamplingInterval operations

    /**
     * Get this item's current sampling interval, i.e. its revised sampling interval.
     *
     * @return this item's current sampling interval, i.e. its revised sampling interval.
     * @see UaMonitoredItem#getRevisedSamplingInterval()
     */
    public double getSamplingInterval() {
        return item.getRevisedSamplingInterval();
    }

    /**
     * Request a new sampling interval for this item.
     *
     * @param samplingInterval the new sampling interval to request.
     * @return the new sampling interval, possibly revised by the server.
     * @throws UaException if a service-level error occurs.
     */
    public double setSamplingInterval(double samplingInterval) throws UaException {
        try {
            return setSamplingIntervalAsync(samplingInterval).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Request a new sampling interval for this item.
     * <p>
     * This call completes asynchronously.
     *
     * @param samplingInterval the new sampling interval to request.
     * @return a {@link CompletableFuture} that completes successfully with the new sampling interval, possibly revised
     * by the server, or completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Double> setSamplingIntervalAsync(double samplingInterval) {
        MonitoringParameters parameters = new MonitoringParameters(
            item.getClientHandle(),
            samplingInterval,
            item.getMonitoringFilter(),
            // use revised so as not to modify the queue
            // size if it was revised from the original
            item.getRevisedQueueSize(),
            item.getDiscardOldest()
        );

        MonitoredItemModifyRequest modifyRequest =
            new MonitoredItemModifyRequest(item.getMonitoredItemId(), parameters);

        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().modifyMonitoredItems(
            subscription.getDefaultTimestamps(),
            singletonList(modifyRequest)
        );

        return future.thenApply(statusCodes -> statusCodes.get(0)).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(item.getRevisedSamplingInterval());
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    //endregion

    //region QueueSize operations

    /**
     * Get this item's current queue size, i.e. its revised queue size.
     *
     * @return this item's current queue size, i.e. its revised queue size.
     * @see UaMonitoredItem#getRevisedQueueSize()
     */
    public UInteger getQueueSize() {
        return item.getRevisedQueueSize();
    }

    /**
     * Request a new queue size for this item.
     *
     * @param queueSize the new queue size to request.
     * @return the new queue size, possibly revised by the server.
     * @throws UaException if a service-level error occurs.
     */
    public UInteger setQueueSize(UInteger queueSize) throws UaException {
        try {
            return setQueueSizeAsync(queueSize).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Request a new queue size for this item.
     * <p>
     * This call completes asynchronously.
     *
     * @param queueSize the new queue size to request.
     * @return a {@link CompletableFuture} that completes successfully with the new queue size, possibly revised by the
     * server, or completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<UInteger> setQueueSizeAsync(UInteger queueSize) {
        MonitoringParameters parameters = new MonitoringParameters(
            item.getClientHandle(),
            item.getRevisedSamplingInterval(),
            item.getMonitoringFilter(),
            queueSize,
            item.getDiscardOldest()
        );

        MonitoredItemModifyRequest modifyRequest =
            new MonitoredItemModifyRequest(item.getMonitoredItemId(), parameters);

        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().modifyMonitoredItems(
            subscription.getDefaultTimestamps(),
            singletonList(modifyRequest)
        );

        return future.thenApply(statusCodes -> statusCodes.get(0)).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(item.getRevisedQueueSize());
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    //endregion

    //region TimestampToReturn operations

    /**
     * Get this item's current {@link TimestampsToReturn} parameter.
     *
     * @return this item's current {@link TimestampsToReturn} parameter.
     */
    public TimestampsToReturn getTimestampsToReturn() {
        return item.getTimestamps();
    }

    /**
     * Set a new {@link TimestampsToReturn} parameter on this item.
     *
     * @param timestamps a new {@link TimestampsToReturn} parameter.
     * @throws UaException if a service-level error occurs.
     */
    public void setTimestampsToReturn(TimestampsToReturn timestamps) throws UaException {
        try {
            setTimestampsToReturnAsync(timestamps).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Set a new {@link TimestampsToReturn} parameter on this item.
     * <p>
     * This call completes asynchronously.
     *
     * @param timestamps a new {@link TimestampsToReturn} parameter.
     * @return a {@link CompletableFuture} that completes successfully if the item was modified and completes
     * exceptionally if a service-level error occurs.
     */
    public CompletableFuture<Unit> setTimestampsToReturnAsync(TimestampsToReturn timestamps) {
        MonitoringParameters parameters = new MonitoringParameters(
            item.getClientHandle(),
            item.getRevisedSamplingInterval(),
            item.getMonitoringFilter(),
            item.getRevisedQueueSize(),
            item.getDiscardOldest()
        );

        MonitoredItemModifyRequest modifyRequest =
            new MonitoredItemModifyRequest(item.getMonitoredItemId(), parameters);

        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().modifyMonitoredItems(
            timestamps,
            singletonList(modifyRequest)
        );

        return future.thenApply(statusCodes -> statusCodes.get(0)).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(Unit.VALUE);
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    //endregion

    //region DiscardOldest operations

    public boolean getDiscardOldest() {
        return item.getDiscardOldest();
    }

    public void setDiscardOldest(boolean discardOldest) throws UaException {
        try {
            setDiscardOldestAsync(discardOldest).get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<Unit> setDiscardOldestAsync(boolean discardOldest) {
        MonitoringParameters parameters = new MonitoringParameters(
            item.getClientHandle(),
            item.getRevisedSamplingInterval(),
            item.getMonitoringFilter(),
            item.getRevisedQueueSize(),
            discardOldest
        );

        MonitoredItemModifyRequest modifyRequest =
            new MonitoredItemModifyRequest(item.getMonitoredItemId(), parameters);

        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().modifyMonitoredItems(
            item.getTimestamps(),
            singletonList(modifyRequest)
        );

        return future.thenApply(statusCodes -> statusCodes.get(0)).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(Unit.VALUE);
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    //endregion

    //region DataValueListener bookkeeping

    /**
     * Add a {@link DataValue} {@link Consumer} to this {@link ManagedDataItem}.
     * <p>
     * {@code consumer} will be invoked any time a new {@link DataValue} arrives for this item.
     * <p>
     * The Consumer is transformed into the returned {@link DataValueListener} that can later be removed.
     *
     * @param consumer a {@link DataValue} {@link Consumer}.
     * @return a {@link DataValueListener} that can later be removed.
     */
    public synchronized DataValueListener addDataValueListener(Consumer<DataValue> consumer) {
        DataValueListener dataValueListener = (item, value) -> consumer.accept(value);

        addDataValueListener(dataValueListener);

        return dataValueListener;
    }

    /**
     * Add a {@link DataValueListener} to this {@link ManagedDataItem}.
     * <p>
     * {@code listener} will be invoked any time a new {@link DataValue} arrives for this item.
     *
     * @param dataValueListener the {@link DataValueListener} to add.
     */
    public synchronized void addDataValueListener(DataValueListener dataValueListener) {
        dataValueListeners.add(dataValueListener);

        if (valueConsumer == null) {
            valueConsumer = new ManagedValueConsumer();
            item.setValueConsumer(valueConsumer);
        }
    }

    /**
     * Remove a {@link DataValueListener} from this {@link ManagedDataItem}.
     *
     * @param dataValueListener the {@link DataValueListener} to remove.
     * @return {@code true} if the listener was removed.
     */
    public synchronized boolean removeDataValueListener(DataValueListener dataValueListener) {
        boolean removed = dataValueListeners.remove(dataValueListener);

        if (dataValueListeners.isEmpty()) {
            item.setValueConsumer((UaMonitoredItem.ValueConsumer) null);
            valueConsumer = null;
        }

        return removed;
    }

    //endregion

    /**
     * Delete this {@link ManagedDataItem}.
     *
     * @return the {@link StatusCode} from the delete operation.
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
     * Delete this {@link ManagedDataItem}.
     * <p>
     * This call completes asynchronously.
     *
     * @return a {@link CompletableFuture} that completes successfully with the operation result or completes
     * exceptionally if a service-level error occurs.
     */
    public CompletableFuture<StatusCode> deleteAsync() {
        return subscription.getSubscription()
            .deleteMonitoredItems(singletonList(item))
            .thenApply(statusCodes -> statusCodes.get(0));
    }

    /**
     * A callback that receives notification of new values for a {@link ManagedDataItem}.
     */
    public interface DataValueListener {

        /**
         * A new {@link DataValue} for {@code item} has arrived.
         * <p>
         * Take care not to block unnecessarily in this callback because subscription notifications are processed
         * synchronously as a backpressure mechanism. Blocking inside this callback will prevent subsequent
         * notifications from being processed and new PublishRequests from being sent.
         *
         * @param item  the {@link ManagedDataItem} for which a new value has arrived.
         * @param value the new {@link DataValue}.
         */
        void onDataValueReceived(ManagedDataItem item, DataValue value);

    }

    private class ManagedValueConsumer implements UaMonitoredItem.ValueConsumer {
        @Override
        public void onValueArrived(SerializationContext context, UaMonitoredItem item, DataValue value) {
            dataValueListeners.forEach(
                dataValueListener ->
                    dataValueListener.onDataValueReceived(ManagedDataItem.this, value)
            );
        }
    }

}

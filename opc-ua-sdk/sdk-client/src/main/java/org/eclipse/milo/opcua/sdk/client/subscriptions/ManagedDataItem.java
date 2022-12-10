/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.BatchModifyMonitoredItems.ModifyMonitoredItemResult;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static java.util.Collections.singletonList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;


public class ManagedDataItem extends ManagedItem {

    private final CopyOnWriteArrayList<DataValueListener> dataValueListeners = new CopyOnWriteArrayList<>();

    private UaMonitoredItem.ValueConsumer valueConsumer = null;

    ManagedDataItem(OpcUaClient client, ManagedSubscription subscription, OpcUaMonitoredItem item) {
        super(client, subscription, item);
    }

    @Override
    public CompletableFuture<Unit> deleteAsync() {
        return subscription.deleteDataItemAsync(this);
    }

    //region SamplingInterval operations

    /**
     * Get this item's current sampling interval, i.e. its revised sampling interval.
     *
     * @return this item's current sampling interval, i.e. its revised sampling interval.
     * @see UaMonitoredItem#getRevisedSamplingInterval()
     */
    public double getSamplingInterval() {
        return monitoredItem.getRevisedSamplingInterval();
    }

    /**
     * Request a new sampling interval for this item.
     *
     * @param samplingInterval the new sampling interval to request.
     * @return the new sampling interval, possibly revised by the server.
     * @throws UaException if an operation- or service-level error occurs.
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
     * by the server, or completes exceptionally if an operation- or service-level error occurs.
     */
    public CompletableFuture<Double> setSamplingIntervalAsync(double samplingInterval) {
        MonitoringParameters parameters = new MonitoringParameters(
            monitoredItem.getClientHandle(),
            samplingInterval,
            monitoredItem.getMonitoringFilter(),
            // use revised so as not to modify the queue
            // size if it was revised from the original
            monitoredItem.getRevisedQueueSize(),
            monitoredItem.getDiscardOldest()
        );

        MonitoredItemModifyRequest modifyRequest =
            new MonitoredItemModifyRequest(monitoredItem.getMonitoredItemId(), parameters);

        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().modifyMonitoredItems(
            subscription.getDefaultTimestamps(),
            singletonList(modifyRequest)
        );

        return future.thenApply(statusCodes -> statusCodes.get(0)).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(monitoredItem.getRevisedSamplingInterval());
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    /**
     * Request a new sampling interval for this item as part of a batch operation.
     *
     * @param samplingInterval the new sampling interval to request.
     * @param batch            the {@link BatchModifyMonitoredItems} operation.
     * @return a {@link CompletableFuture} that completes successfully with the new sampling interval, possibly revised
     * by the server, or completes exceptionally if an operation- or service-level error occurred.
     */
    public CompletableFuture<Double> setSamplingIntervalAsync(
        double samplingInterval,
        BatchModifyMonitoredItems batch
    ) {

        CompletableFuture<ModifyMonitoredItemResult> future = batch.add(
            getMonitoredItem(),
            b -> b.setSamplingInterval(samplingInterval)
        );

        return future.thenCompose(result -> {
            if (result.isServiceResultGood()) {
                Optional<CompletableFuture<Double>> opt = result.operationResult().map(s -> {
                    if (s.isGood()) {
                        return completedFuture(getSamplingInterval());
                    } else {
                        return failedUaFuture(s);
                    }
                });

                // if the service result is good the operation result must be present.
                return opt.orElse(failedUaFuture(new StatusCode(StatusCodes.Bad_InternalError)));
            } else {
                return failedUaFuture(result.serviceResult());
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
     * <p>
     * Listeners should be added in the callback that happens during item creation in the
     * {@link ManagedSubscription#createDataItem(double, ReadValueId, Consumer)} and
     * {@link ManagedSubscription#createDataItemsAsync(double, List, Consumer)} to avoid a possible
     * race condition between the initial value arriving and the listener being registered.
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
     * <p>
     * Listeners should be added in the callback that happens during item creation in the
     * {@link ManagedSubscription#createDataItem(double, ReadValueId, Consumer)} and
     * {@link ManagedSubscription#createDataItemsAsync(double, List, Consumer)} to avoid a possible
     * race condition between the initial value arriving and the listener being registered.
     *
     * @param dataValueListener the {@link DataValueListener} to add.
     */
    public synchronized void addDataValueListener(DataValueListener dataValueListener) {
        dataValueListeners.add(dataValueListener);

        if (valueConsumer == null) {
            valueConsumer = new ManagedValueConsumer();
            monitoredItem.setValueConsumer(valueConsumer);
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
            monitoredItem.setValueConsumer((UaMonitoredItem.ValueConsumer) null);
            valueConsumer = null;
        }

        return removed;
    }

    //endregion

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
        public void onValueArrived(UaMonitoredItem item, DataValue value) {
            dataValueListeners.forEach(
                dataValueListener ->
                    dataValueListener.onDataValueReceived(ManagedDataItem.this, value)
            );
        }
    }

}

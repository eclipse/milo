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

import java.util.Collections;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

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

    public NodeId getNodeId() {
        return getReadValueId().getNodeId();
    }

    public ReadValueId getReadValueId() {
        return item.getReadValueId();
    }

    public StatusCode getStatusCode() {
        return item.getStatusCode();
    }

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
            Collections.singletonList(item)
        );

        return future.thenApply(statusCodes -> statusCodes.get(0)).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(Unit.VALUE);
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    public double getSamplingInterval() {
        return item.getRevisedSamplingInterval();
    }

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

    public CompletableFuture<Double> setSamplingIntervalAsync(double samplingInterval) {
        MonitoringParameters parameters = new MonitoringParameters(
            item.getClientHandle(),
            samplingInterval,
            item.getMonitoringFilter(),
            // use revised so as not to modify the queue
            // size if it was revised from the original
            item.getRevisedQueueSize(),
            true // TODO
        );

        MonitoredItemModifyRequest modifyRequest =
            new MonitoredItemModifyRequest(item.getMonitoredItemId(), parameters);

        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().modifyMonitoredItems(
            subscription.getDefaultTimestamps(),
            Collections.singletonList(modifyRequest)
        );

        return future.thenApply(statusCodes -> statusCodes.get(0)).thenCompose(statusCode -> {
            if (statusCode.isGood()) {
                return completedFuture(item.getRevisedSamplingInterval());
            } else {
                return failedUaFuture(statusCode);
            }
        });
    }

    public synchronized DataValueListener addDataValueListener(Consumer<DataValue> consumer) {
        DataValueListener dataValueListener = (item, value) -> consumer.accept(value);

        addDataValueListener(dataValueListener);

        return dataValueListener;
    }

    public synchronized void addDataValueListener(DataValueListener dataValueListener) {
        dataValueListeners.add(dataValueListener);

        if (valueConsumer == null) {
            valueConsumer = new ManagedValueConsumer();
            item.setValueConsumer(valueConsumer);
        }
    }

    public synchronized void removeDataValueListener(DataValueListener dataValueListener) {
        dataValueListeners.remove(dataValueListener);

        if (dataValueListeners.isEmpty()) {
            item.setValueConsumer((UaMonitoredItem.ValueConsumer) null);
            valueConsumer = null;
        }
    }

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

    public CompletableFuture<StatusCode> deleteAsync() {
        return subscription.getSubscription()
            .deleteMonitoredItems(Collections.singletonList(item))
            .thenApply(statusCodes -> statusCodes.get(0));
    }

    public interface DataValueListener {

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

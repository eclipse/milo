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
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
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

public abstract class ManagedItem {

    protected final OpcUaClient client;
    protected final ManagedSubscription subscription;
    protected final OpcUaMonitoredItem monitoredItem;

    ManagedItem(OpcUaClient client, ManagedSubscription subscription, OpcUaMonitoredItem monitoredItem) {
        this.client = client;
        this.subscription = subscription;
        this.monitoredItem = monitoredItem;
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
     * Get the {@link ManagedSubscription} this item belongs to.
     *
     * @return the {@link ManagedSubscription} this item belongs to.
     */
    public ManagedSubscription getSubscription() {
        return subscription;
    }

    /**
     * Get the underlying {@link OpcUaMonitoredItem}.
     *
     * @return the underlying {@link OpcUaMonitoredItem}.
     */
    public OpcUaMonitoredItem getMonitoredItem() {
        return monitoredItem;
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
        return monitoredItem.getReadValueId();
    }

    /**
     * Get the {@link StatusCode} of the last operation this item was part of.
     *
     * @return the {@link StatusCode} of the last operation this item was part of.
     */
    public StatusCode getStatusCode() {
        return monitoredItem.getStatusCode();
    }

    //region MonitoringMode operations

    /**
     * Get this item's current {@link MonitoringMode}.
     *
     * @return this item's current {@link MonitoringMode}.
     * @see UaMonitoredItem#getMonitoringMode()
     */
    public MonitoringMode getMonitoringMode() {
        return monitoredItem.getMonitoringMode();
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
            singletonList(monitoredItem)
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

    //region QueueSize operations

    /**
     * Get this item's current queue size, i.e. its revised queue size.
     *
     * @return this item's current queue size, i.e. its revised queue size.
     * @see UaMonitoredItem#getRevisedQueueSize()
     */
    public UInteger getQueueSize() {
        return monitoredItem.getRevisedQueueSize();
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
            monitoredItem.getClientHandle(),
            monitoredItem.getRevisedSamplingInterval(),
            monitoredItem.getMonitoringFilter(),
            queueSize,
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
                return completedFuture(monitoredItem.getRevisedQueueSize());
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
        return monitoredItem.getTimestamps();
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
            monitoredItem.getClientHandle(),
            monitoredItem.getRevisedSamplingInterval(),
            monitoredItem.getMonitoringFilter(),
            monitoredItem.getRevisedQueueSize(),
            monitoredItem.getDiscardOldest()
        );

        MonitoredItemModifyRequest modifyRequest =
            new MonitoredItemModifyRequest(monitoredItem.getMonitoredItemId(), parameters);

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
        return monitoredItem.getDiscardOldest();
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
            monitoredItem.getClientHandle(),
            monitoredItem.getRevisedSamplingInterval(),
            monitoredItem.getMonitoringFilter(),
            monitoredItem.getRevisedQueueSize(),
            discardOldest
        );

        MonitoredItemModifyRequest modifyRequest =
            new MonitoredItemModifyRequest(monitoredItem.getMonitoredItemId(), parameters);

        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().modifyMonitoredItems(
            monitoredItem.getTimestamps(),
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

}

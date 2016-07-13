/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client.api.subscriptions;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;

public interface UaSubscription {

    /**
     * @return the server-assigned id for this {@link UaSubscription}.
     */
    UInteger getSubscriptionId();

    /**
     * @return the actual/revised publishing interval of this {@link UaSubscription}.
     */
    double getRevisedPublishingInterval();

    /**
     * @return the actual/revised lifetime count of this {@link UaSubscription}.
     */
    UInteger getRevisedLifetimeCount();

    /**
     * @return the actual/revised max keep-alive count of this {@link UaSubscription}.
     */
    UInteger getRevisedMaxKeepAliveCount();

    /**
     * @return the maximum number of notifications that will be returned in any publish response.
     */
    UInteger getMaxNotificationsPerPublish();

    /**
     * @return {@code true} if publishing is enabled.
     */
    boolean isPublishingEnabled();

    /**
     * @return the relative priority assigned to this {@link UaSubscription}.
     */
    UByte getPriority();

    /**
     * @return an {@link ImmutableList} of this {@link UaSubscription}'s {@link UaMonitoredItem}s.
     */
    ImmutableList<UaMonitoredItem> getMonitoredItems();

    /**
     * Create one or more {@link UaMonitoredItem}s.
     * <p>
     * Callers must check the quality of each of the returned {@link UaMonitoredItem}s; it is not to be assumed that
     * all items were created successfully. Any item with a bad quality will not be updated nor will it be part of the
     * subscription's bookkeeping.
     * <p>
     * <b>WARNING:</b> items must be created in {@link MonitoringMode#Sampling} and then later set to
     * {@link MonitoringMode#Reporting} <i>after</i> consumers have been set in order to avoid a race condition where
     * the initial values arrive before the consumers are set. Alternatively, you can use
     * {@link #createMonitoredItems(TimestampsToReturn, List, BiConsumer)} to avoid this race condition.
     *
     * @param timestampsToReturn the {@link TimestampsToReturn}.
     * @param itemsToCreate      a list of {@link MonitoredItemCreateRequest}s.
     * @return a list of {@link UaMonitoredItem}s.
     */
    CompletableFuture<List<UaMonitoredItem>> createMonitoredItems(TimestampsToReturn timestampsToReturn,
                                                                  List<MonitoredItemCreateRequest> itemsToCreate);

    /**
     * Create one or more {@link UaMonitoredItem}s.
     * <p>
     * Callers must check the quality of each of the returned {@link UaMonitoredItem}s; it is not to be assumed that
     * all items were created successfully. Any item with a bad quality will not be updated nor will it be part of the
     * subscription's bookkeeping.
     * <p>
     * {@code itemCreationCallback} will be invoked for each successfully created {@link UaMonitoredItem}. Callers
     * should use this opportunity to register any value or event consumers on the item, as this is the only time in
     * which it is guaranteed no values or events will be delivered to the item yet.
     *
     * @param timestampsToReturn   the {@link TimestampsToReturn}.
     * @param itemsToCreate        a list of {@link MonitoredItemCreateRequest}s.
     * @param itemCreationCallback callback to be invoked for each successfully created {@link UaMonitoredItem}.
     * @return a list of {@link UaMonitoredItem}s.
     */
    CompletableFuture<List<UaMonitoredItem>> createMonitoredItems(TimestampsToReturn timestampsToReturn,
                                                                  List<MonitoredItemCreateRequest> itemsToCreate,
                                                                  BiConsumer<UaMonitoredItem, Integer> itemCreationCallback);

    /**
     * Modify one or more {@link UaMonitoredItem}s.
     *
     * @param timestampsToReturn the {@link TimestampsToReturn} to set for each item.
     * @param itemsToModify      a list of {@link MonitoredItemModifyRequest}s.
     * @return a {@link CompletableFuture} containing a list of {@link StatusCode}s, the size and order matching that
     * of {@code itemsToModify}.
     */
    CompletableFuture<List<StatusCode>> modifyMonitoredItems(TimestampsToReturn timestampsToReturn,
                                                             List<MonitoredItemModifyRequest> itemsToModify);

    /**
     * Delete on or more {@link UaMonitoredItem}s.
     *
     * @param itemsToDelete the items to delete.
     * @return a {@link CompletableFuture} containing a list of {@link StatusCode}s, the size and order matching that
     * of {@code itemsToDelete}.
     */
    CompletableFuture<List<StatusCode>> deleteMonitoredItems(List<UaMonitoredItem> itemsToDelete);

    /**
     * Set the {@link MonitoringMode} for one or more {@link UaMonitoredItem}s.
     *
     * @param monitoringMode the {@link MonitoringMode} to set.
     * @param items          the {@link UaMonitoredItem}s to set the mode on.
     * @return a {@link CompletableFuture} containing a list of {@link StatusCode}s, the size and order matching that
     * of {@code items}.
     */
    CompletableFuture<List<StatusCode>> setMonitoringMode(MonitoringMode monitoringMode, List<UaMonitoredItem> items);

    /**
     * Set the publishing mode for this subscription.
     *
     * @param publishingEnabled {@code true} if publishing should be enabled.
     * @return a {@link CompletableFuture} containing a {@link StatusCode} representing the result of this operation.
     */
    CompletableFuture<StatusCode> setPublishingMode(boolean publishingEnabled);

}

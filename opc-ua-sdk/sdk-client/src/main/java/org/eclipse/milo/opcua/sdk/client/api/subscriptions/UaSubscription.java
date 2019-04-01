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
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
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
     * @return the last requested publishing interval of this {@link UaSubscription}.
     */
    double getRequestedPublishingInterval();

    /**
     * @return the actual/revised publishing interval of this {@link UaSubscription}.
     */
    double getRevisedPublishingInterval();

    /**
     * @return the last requested lifetime count of this {@link UaSubscription}.
     */
    UInteger getRequestedLifetimeCount();

    /**
     * @return the actual/revised lifetime count of this {@link UaSubscription}.
     */
    UInteger getRevisedLifetimeCount();

    /**
     * @return the last requested max keep-alive count of this {@link UaSubscription}.
     */
    UInteger getRequestedMaxKeepAliveCount();

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
    default CompletableFuture<List<UaMonitoredItem>> createMonitoredItems(
        TimestampsToReturn timestampsToReturn,
        List<MonitoredItemCreateRequest> itemsToCreate,
        BiConsumer<UaMonitoredItem, Integer> itemCreationCallback) {

        return createMonitoredItems(
            timestampsToReturn,
            itemsToCreate,
            (serializationContext, item, index) ->
                itemCreationCallback.accept(item, index)
        );
    }

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
    CompletableFuture<List<UaMonitoredItem>> createMonitoredItems(
        TimestampsToReturn timestampsToReturn,
        List<MonitoredItemCreateRequest> itemsToCreate,
        ItemCreationCallback itemCreationCallback);

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

    /**
     * Add a {@link NotificationListener}.
     *
     * @param listener the {@link NotificationListener} to add.
     */
    void addNotificationListener(NotificationListener listener);

    /**
     * Remove a {@link NotificationListener}.
     *
     * @param listener the {@link NotificationListener} to remove.
     */
    void removeNotificationListener(NotificationListener listener);

    interface ItemCreationCallback {

        void onItemCreated(SerializationContext serializationContext, UaMonitoredItem item, int index);

    }


    interface NotificationListener {

        /**
         * A notification containing data value changes for this {@link UaSubscription} has arrived.
         * <p>
         * This callback is invoked after all individual item callbacks and is offered as an alternative to defining
         * callbacks on a per-item basis. Its use is not required.
         *
         * @param subscription   the {@link UaSubscription} that received the notification.
         * @param monitoredItems the {@link UaMonitoredItem}s that received a data value change.
         * @param dataValues     the corresponding {@link DataValue} change for each item in {@code monitoredItems}.
         * @param publishTime    the time on the server at which this notification was published.
         */
        default void onDataChangeNotification(UaSubscription subscription,
                                              List<UaMonitoredItem> monitoredItems,
                                              List<DataValue> dataValues,
                                              DateTime publishTime) {}

        /**
         * A notification containing events for this {@link UaSubscription} has arrived.
         * <p>
         * This callback is invoked after all individual item callbacks and is offered as an alternative to defining
         * callbacks on a per-item basis. Its use is not required.
         *
         * @param subscription   the {@link UaSubscription} that received the notification.
         * @param monitoredItems the {@link UaMonitoredItem}s that received an event notification.
         * @param eventFields    the corresponding event field values for each item in {@code monitoredItems}.
         * @param publishTime    the time on the server at which this notification was published.
         */
        default void onEventNotification(UaSubscription subscription,
                                         List<UaMonitoredItem> monitoredItems,
                                         List<Variant[]> eventFields,
                                         DateTime publishTime) {}

        /**
         * A keep-alive message was received.
         *
         * @param subscription the {@link UaSubscription} that received the keep-alive.
         * @param publishTime  the time the server published the keep-alive.
         */
        default void onKeepAliveNotification(UaSubscription subscription, DateTime publishTime) {}

        /**
         * A status change notification was received.
         *
         * @param subscription the {@link UaSubscription} that received the status change.
         * @param status       the new subscription status.
         */
        default void onStatusChangedNotification(UaSubscription subscription, StatusCode status) {}

    }

}

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
import java.util.function.BiFunction;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.sdk.client.api.UaClient;
import org.eclipse.milo.opcua.sdk.client.api.UaSession;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem.EventConsumer;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem.ValueConsumer;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ItemTransferredCallback;
import org.eclipse.milo.opcua.sdk.client.subscriptions.TransferMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface UaSubscriptionManager {

    /**
     * Create a {@link UaSubscription} using default parameters.
     * <p>
     * The requested max keep-alive count and lifetime count will be derived from the requested publishing interval.
     *
     * @param requestedPublishingInterval the requested publishing interval of the subscription.
     * @return a {@link CompletableFuture} containing the {@link UaSubscription}.
     */
    CompletableFuture<UaSubscription> createSubscription(double requestedPublishingInterval);

    /**
     * Create a {@link UaSubscription}.
     *
     * @param requestedPublishingInterval the requested publishing interval.
     * @param requestedLifetimeCount      the requested lifetime count.
     * @param requestedMaxKeepAliveCount  the requested max keep-alive count.
     * @param maxNotificationsPerPublish  the maximum number of notifications allowed in a publish response.
     * @param publishingEnabled           {@code true} if publishing is enabled for the subscription.
     * @param priority                    the relative priority to assign to the subscription.
     * @return a {@link CompletableFuture} containing the {@link UaSubscription}.
     */
    CompletableFuture<UaSubscription> createSubscription(double requestedPublishingInterval,
                                                         UInteger requestedLifetimeCount,
                                                         UInteger requestedMaxKeepAliveCount,
                                                         UInteger maxNotificationsPerPublish,
                                                         boolean publishingEnabled,
                                                         UByte priority);

    /**
     * Create a {@link UaSubscription}.
     * <p>
     * This overload uses provided functions to calculate keep-alive and lifetime counts. They may be called more than
     * once, with revised values, in order to modify the subscription if the server revises the requested publishing
     * interval.
     *
     * @param requestedPublishingInterval the requested publishing interval.
     * @param getLifetimeCount            function returning lifetime count given publishing interval and
     *                                    keep-alive count.
     * @param getMaxKeepAliveCount        function returning max keep-alive count given publishing interval.
     * @param maxNotificationsPerPublish  the maximum number of notifications allowed in a publish response.
     * @param publishingEnabled           {@code true} if publishing is enabled for the subscription.
     * @param priority                    the relative priority to assign to the subscription.
     * @return a {@link CompletableFuture} containing the {@link UaSubscription}.
     */
    CompletableFuture<UaSubscription> createSubscription(double requestedPublishingInterval,
                                                         BiFunction<Double, UInteger, UInteger> getLifetimeCount,
                                                         Function<Double, UInteger> getMaxKeepAliveCount,
                                                         UInteger maxNotificationsPerPublish,
                                                         boolean publishingEnabled,
                                                         UByte priority);

    /**
     * Request a new publishing interval for a {@link UaSubscription}.
     * <p>
     * The requested max keep-alive count and lifetime count will be derived from the requested publishing interval.
     *
     * @param subscriptionId              the server-assigned id of the {@link UaSubscription} to modify.
     * @param requestedPublishingInterval the requested publishing interval.
     * @return a {@link CompletableFuture} containing the {@link UaSubscription}.
     */
    CompletableFuture<UaSubscription> modifySubscription(UInteger subscriptionId,
                                                         double requestedPublishingInterval);

    /**
     * Modify a {@link UaSubscription}.
     *
     * @param subscriptionId              the server-assigned id of the {@link UaSubscription} to modify.
     * @param requestedPublishingInterval the requested publishing interval.
     * @param requestedLifetimeCount      the requested lifetime count.
     * @param requestedMaxKeepAliveCount  the requested max keep-alive count.
     * @param maxNotificationsPerPublish  the maximum number of notifications allowed in a publish response.
     * @param priority                    the relative priority to assign to the subscription.
     * @return a {@link CompletableFuture} containing the {@link UaSubscription}.
     */
    CompletableFuture<UaSubscription> modifySubscription(UInteger subscriptionId,
                                                         double requestedPublishingInterval,
                                                         UInteger requestedLifetimeCount,
                                                         UInteger requestedMaxKeepAliveCount,
                                                         UInteger maxNotificationsPerPublish,
                                                         UByte priority);

    /**
     * Modify a {@link UaSubscription}.
     * <p>
     * This overload uses provided functions to calculate keep-alive and lifetime counts. They may be called more than
     * once, with revised values, in order to modify the subscription if the server revises the requested publishing
     * interval.
     *
     * @param subscriptionId              the server-assigned id of the {@link UaSubscription} to modify.
     * @param requestedPublishingInterval the requested publishing interval.
     * @param getLifetimeCount            function returning lifetime count given publishing interval and
     *                                    keep-alive count.
     * @param getMaxKeepAliveCount        function returning max keep-alive count given publishing interval.
     * @param maxNotificationsPerPublish  the maximum number of notifications allowed in a publish response.
     * @param priority                    the relative priority to assign to the subscription.
     * @return a {@link CompletableFuture} containing the {@link UaSubscription}.
     */
    CompletableFuture<UaSubscription> modifySubscription(UInteger subscriptionId,
                                                         double requestedPublishingInterval,
                                                         BiFunction<Double, UInteger, UInteger> getLifetimeCount,
                                                         Function<Double, UInteger> getMaxKeepAliveCount,
                                                         UInteger maxNotificationsPerPublish,
                                                         UByte priority);

    /**
     * Complete the transfer of {@code subscription} and its monitored items to this {@link UaSubscriptionManager}.
     * <p>
     * The subscription shall already have been transferred to this client's session via the Transfer Subscription
     * service.
     * <p>
     * The {@code callback} provides a chance to configure a new a {@link ValueConsumer} or {@link EventConsumer} to
     * each item belonging to the transferred subscription.
     *
     * @param subscription the {@link UaSubscription} to transfer to this {@link UaSubscriptionManager}.
     * @param callback     the {@link ItemTransferredCallback} that will be invoked for each item belonging to the
     *                     transferred subscription.
     * @return a {@link CompletableFuture} containing the new {@link UaSubscription}.
     */
    CompletableFuture<UaSubscription> transferSubscription(
        UaSubscription subscription,
        ItemTransferredCallback callback
    );

    /**
     * Complete the transfer of a subscription and its monitored items to this {@link UaSubscriptionManager}.
     * <p>
     * The subscription shall already have been transferred to this client's session via the Transfer Subscription
     * service.
     * <p>
     * If the original session resided on a remote machine then information about the subscription required by this call
     * must be obtained either by interrogating the Server diagnostics, if enabled, or by some other out-of-band means.
     *
     * @param subscriptionId              the server assigned id of the {@link UaSubscription} to transfer.
     * @param nextSequenceNumber          the sequence number to start processing publishing requests from.
     * @param requestedPublishingInterval the publishing interval requested when the subscription was created.
     * @param publishingInterval          the actual publishing interval of the subscription.
     * @param requestedLifetimeCount      the lifetime count requested when the subscription was created.
     * @param lifetimeCount               the actual lifetime count of the subscription.
     * @param requestedMaxKeepAliveCount  the max keep alive requested when the subscription was created.
     * @param maxKeepAliveCount           the actual max keep alive of the subscription.
     * @param maxNotificationsPerPublish  the maximum number of notifications allowed in a publish response.
     * @param publishingEnabled           {@code true} if publishing is enabled for the subscription.
     * @param priority                    the relative priority to assign to the subscription.
     * @param itemsToTransfer             a collection of {@link TransferMonitoredItemsRequest} representing the
     *                                    monitored items on the subscription to be reconstituted in the subscription.
     * @return a {@link CompletableFuture} containing the {@link UaSubscription}
     * @see UaClient#transferSubscriptions(List, boolean)
     */
    CompletableFuture<UaSubscription> transferSubscription(
        UInteger subscriptionId,
        UInteger nextSequenceNumber,
        double requestedPublishingInterval,
        double publishingInterval,
        UInteger requestedLifetimeCount,
        UInteger lifetimeCount,
        UInteger requestedMaxKeepAliveCount,
        UInteger maxKeepAliveCount,
        UInteger maxNotificationsPerPublish,
        boolean publishingEnabled,
        UByte priority,
        List<TransferMonitoredItemsRequest> itemsToTransfer
    );

    /**
     * Delete a {@link UaSubscription}.
     *
     * @param subscriptionId the server-assigned id of the {@link UaSubscription} to delete.
     * @return a {@link CompletableFuture} containing the deleted {@link UaSubscription}.
     */
    CompletableFuture<UaSubscription> deleteSubscription(UInteger subscriptionId);

    /**
     * @return an {@link ImmutableList} of {@link UaSubscription}s managed by this {@link UaSubscriptionManager}.
     */
    ImmutableList<UaSubscription> getSubscriptions();

    /**
     * Add a {@link SubscriptionListener}.
     *
     * @param listener the {@link SubscriptionListener} to add.
     */
    void addSubscriptionListener(SubscriptionListener listener);

    /**
     * Remove a {@link SubscriptionListener}.
     *
     * @param listener the {@link SubscriptionListener} to remove.
     */
    void removeSubscriptionListener(SubscriptionListener listener);

    interface SubscriptionListener {

        /**
         * A keep-alive message was received.
         *
         * @param subscription the {@link UaSubscription} that received the keep-alive.
         * @param publishTime  the time the server published the keep-alive.
         */
        default void onKeepAlive(UaSubscription subscription, DateTime publishTime) {}

        /**
         * A status change notification was received.
         *
         * @param subscription the {@link UaSubscription} that received the status change.
         * @param status       the new subscription status.
         */
        default void onStatusChanged(UaSubscription subscription, StatusCode status) {}

        /**
         * A publish failure has occurred.
         * <p>
         * No additional action is required.
         *
         * @param exception the cause of the failure.
         */
        default void onPublishFailure(UaException exception) {}

        /**
         * Attempts to recover missed notification data have failed.
         * <p>
         * When a notification is missed a series of Republish requests are initiated to recover the missing data. If
         * republishing fails, or any of the notifications are no longer available, this callback will be invoked.
         *
         * @param subscription the subscription that missed notification data.
         */
        default void onNotificationDataLost(UaSubscription subscription) {}

        /**
         * A new {@link UaSession} was established, and upon attempting to transfer an existing subscription to this
         * new session, a failure occurred.
         * <p>
         * This subscription will be removed from {@link UaSubscriptionManager}'s bookkeeping. It must be re-created.
         *
         * @param subscription the {@link UaSubscription} that could not be transferred.
         * @param statusCode   the {@link StatusCode} for the transfer failure.
         */
        default void onSubscriptionTransferFailed(UaSubscription subscription, StatusCode statusCode) {}

    }

}

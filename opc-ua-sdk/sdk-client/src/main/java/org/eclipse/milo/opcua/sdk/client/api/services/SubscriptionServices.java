/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;

public interface SubscriptionServices {

    /**
     * This service is used to create a subscription. Subscriptions monitor a set of monitored items for notifications
     * and return them to the client in response to Publish requests.
     *
     * @param requestedPublishingInterval this interval defines the cyclic rate that the subscription is being requested
     *                                    to return notifications to the client. This interval is expressed in
     *                                    milliseconds.
     * @param requestedLifetimeCount      the requested lifetime count. The lifetime count shall be a minimum of three
     *                                    times the keep keep-alive count.
     * @param requestedMaxKeepAliveCount  the requested maximum keep-alive count. When the publishing timer has expired
     *                                    this number of times without requiring any notification to be sent, the
     *                                    subscription sends a keep-alive message to the client.
     * @param maxNotificationsPerPublish  the maximum number of notifications that the client wishes to receive in a
     *                                    single publish response. A value of zero indicates that there is no limit.
     * @param publishingEnabled           if {@code true}, publishing is enabled for this subscription.
     * @param priority                    indicates the relative priority of the subscription.
     * @return a {@link CompletableFuture} containing the {@link CreateSubscriptionResponse}.
     */
    CompletableFuture<CreateSubscriptionResponse> createSubscription(double requestedPublishingInterval,
                                                                     UInteger requestedLifetimeCount,
                                                                     UInteger requestedMaxKeepAliveCount,
                                                                     UInteger maxNotificationsPerPublish,
                                                                     boolean publishingEnabled,
                                                                     UByte priority);

    /**
     * This service is used to modify a subscription.
     *
     * @param subscriptionId              the server-assigned identifier for the subscription.
     * @param requestedPublishingInterval this interval defines the cyclic rate that the subscription is being requested
     *                                    to return notifications to the client. This interval is expressed in
     *                                    milliseconds.
     * @param requestedLifetimeCount      the requested lifetime count. The lifetime count shall be a minimum of three
     *                                    times the keep keep-alive count.
     * @param requestedMaxKeepAliveCount  the requested maximum keep-alive count. When the publishing timer has expired
     *                                    this number of times without requiring any notification to be sent, the
     *                                    subscription sends a keep-alive message to the client.
     * @param maxNotificationsPerPublish  the maximum number of notifications that the client wishes to receive in a
     *                                    single publish response. A value of zero indicates that there is no limit.
     * @param priority                    indicates the relative priority of the subscription.
     * @return a {@link CompletableFuture} containing the {@link ModifySubscriptionResponse}.
     */
    CompletableFuture<ModifySubscriptionResponse> modifySubscription(UInteger subscriptionId,
                                                                     double requestedPublishingInterval,
                                                                     UInteger requestedLifetimeCount,
                                                                     UInteger requestedMaxKeepAliveCount,
                                                                     UInteger maxNotificationsPerPublish,
                                                                     UByte priority);

    /**
     * This service is invoked to delete one or more subscriptions that belong to the client's session.
     *
     * @param subscriptionIds the server-assigned identifiers for the subscriptions.
     * @return a {@link CompletableFuture} containing the {@link DeleteSubscriptionsResponse}.
     */
    CompletableFuture<DeleteSubscriptionsResponse> deleteSubscriptions(List<UInteger> subscriptionIds);

    /**
     * This service is used to transfer a subscription and its monitored items from one session to another.
     *
     * @param subscriptionIds   the server-assigned identifiers for the subscriptions to transfer.
     * @param sendInitialValues if {@code true}, the first Publish response after the TransferSubscriptions service
     *                          call shall contain the current values of all monitored items in the
     *                          subscription where the {@link MonitoringMode} is {@link MonitoringMode#Reporting}.
     * @return a {@link CompletableFuture} containing the {@link TransferSubscriptionsResponse}.
     */
    CompletableFuture<TransferSubscriptionsResponse> transferSubscriptions(List<UInteger> subscriptionIds,
                                                                           boolean sendInitialValues);

    /**
     * This service is used to enable sending of notifications on one or more subscriptions.
     *
     * @param publishingEnabled {@code true} if publishing of notification messages is to be enabled.
     * @param subscriptionIds   a list of server-assigned subscription identifiers to enable or disable publishing on.
     * @return a {@link CompletableFuture} containing the {@link SetPublishingModeResponse}.
     */
    CompletableFuture<SetPublishingModeResponse> setPublishingMode(boolean publishingEnabled,
                                                                   List<UInteger> subscriptionIds);

    /**
     * This service is used for two purposes. First, it is used to acknowledge the receipt of notification messages for
     * one or more subscriptions. Second, it is used to request the server to return a notification message or a
     * keep-alive message.
     *
     * @param subscriptionAcknowledgements the list of acknowledgements for one or more subscriptions. This list may
     *                                     contain multiple acknowledgements for the same subscription (multiple entries
     *                                     with the same subscriptionId).
     * @return a {@link CompletableFuture} containing the {@link PublishResponse}.
     */
    CompletableFuture<PublishResponse> publish(List<SubscriptionAcknowledgement> subscriptionAcknowledgements);

    /**
     * This service requests the subscription to republish a notification message from its retransmission queue. If the
     * server does not have the requested message in its retransmission queue, it returns an error response.
     *
     * @param subscriptionId           the server-assigned identifier for the subscription to be republished.
     * @param retransmitSequenceNumber the sequence number of a specific notification message to be republished.
     * @return a {@link CompletableFuture} containing the {@link RepublishResponse}.
     */
    CompletableFuture<RepublishResponse> republish(UInteger subscriptionId, UInteger retransmitSequenceNumber);

}

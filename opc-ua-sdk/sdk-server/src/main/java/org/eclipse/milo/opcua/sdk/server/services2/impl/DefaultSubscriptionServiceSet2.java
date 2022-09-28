/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services2.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredDataItem;
import org.eclipse.milo.opcua.sdk.server.services2.SubscriptionServiceSet2;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferResult;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

import static org.eclipse.milo.opcua.sdk.server.services2.AbstractServiceSet.createResponseHeader;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class DefaultSubscriptionServiceSet2 implements SubscriptionServiceSet2 {

    private final OpcUaServer server;

    public DefaultSubscriptionServiceSet2(OpcUaServer server) {
        this.server = server;
    }

    @Override
    public CompletableFuture<CreateSubscriptionResponse> onCreateSubscription(
        ServiceRequestContext context,
        CreateSubscriptionRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<CreateSubscriptionResponse> future =
            session.getSubscriptionManager().createSubscription(request);

        session.getSessionDiagnostics().getCreateSubscriptionCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<ModifySubscriptionResponse> onModifySubscription(
        ServiceRequestContext context,
        ModifySubscriptionRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<ModifySubscriptionResponse> future =
            session.getSubscriptionManager().modifySubscription(request);

        session.getSessionDiagnostics().getModifySubscriptionCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<DeleteSubscriptionsResponse> onDeleteSubscriptions(
        ServiceRequestContext context,
        DeleteSubscriptionsRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<DeleteSubscriptionsResponse> future =
            session.getSubscriptionManager().deleteSubscriptions(request);

        session.getSessionDiagnostics().getDeleteSubscriptionsCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<TransferSubscriptionsResponse> onTransferSubscriptions(ServiceRequestContext context, TransferSubscriptionsRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<TransferSubscriptionsResponse> future = transferSubscriptions(request, session);

        session.getSessionDiagnostics().getTransferSubscriptionsCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<SetPublishingModeResponse> onSetPublishingMode(ServiceRequestContext context, SetPublishingModeRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<SetPublishingModeResponse> future =
            session.getSubscriptionManager().setPublishingMode(request);

        session.getSessionDiagnostics().getSetPublishingModeCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<PublishResponse> onPublish(ServiceRequestContext context, PublishRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<PublishResponse> future =
            session.getSubscriptionManager().publish(context, request);

        session.getSessionDiagnostics().getPublishCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<RepublishResponse> onRepublish(ServiceRequestContext context, RepublishRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<RepublishResponse> future =
            session.getSubscriptionManager().republish(request);

        session.getSessionDiagnostics().getRepublishCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    private CompletableFuture<TransferSubscriptionsResponse> transferSubscriptions(
        TransferSubscriptionsRequest request,
        Session session
    ) {

        List<UInteger> subscriptionIds = List.of(request.getSubscriptionIds());

        if (subscriptionIds.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        var results = new ArrayList<TransferResult>();

        for (UInteger subscriptionId : subscriptionIds) {
            Subscription subscription = server.getSubscriptions().get(subscriptionId);

            if (subscription == null) {
                results.add(new TransferResult(
                    new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid),
                    new UInteger[0]
                ));
            } else {
                Session otherSession = subscription.getSession();

                if (!sessionsHaveSameUser(session, otherSession)) {
                    results.add(new TransferResult(
                        new StatusCode(StatusCodes.Bad_UserAccessDenied),
                        new UInteger[0]
                    ));
                } else {
                    UInteger[] availableSequenceNumbers;

                    synchronized (subscription) {
                        otherSession.getSubscriptionManager().sendStatusChangeNotification(
                            subscription,
                            new StatusCode(StatusCodes.Good_SubscriptionTransferred)
                        );
                        otherSession.getSubscriptionManager().removeSubscription(subscriptionId);

                        subscription.setSubscriptionManager(session.getSubscriptionManager());
                        session.getSubscriptionManager().addSubscription(subscription);

                        subscription.getMonitoredItems().values().forEach(item -> item.setSession(session));

                        availableSequenceNumbers = subscription.getAvailableSequenceNumbers();

                        if (request.getSendInitialValues()) {
                            subscription.getMonitoredItems().values().stream()
                                .filter(item -> item instanceof MonitoredDataItem)
                                .map(item -> (MonitoredDataItem) item)
                                .forEach(MonitoredDataItem::maybeSendLastValue);
                        }
                    }

                    subscription.getSubscriptionDiagnostics().getTransferRequestCount().increment();

                    ApplicationDescription toClient = session.getClientDescription();
                    ApplicationDescription fromClient = otherSession.getClientDescription();

                    if (Objects.equals(toClient, fromClient)) {
                        subscription.getSubscriptionDiagnostics().getTransferredToSameClientCount().increment();
                    } else {
                        subscription.getSubscriptionDiagnostics().getTransferredToAltClientCount().increment();
                    }

                    results.add(new TransferResult(StatusCode.GOOD, availableSequenceNumbers));
                }
            }
        }

        var response = new TransferSubscriptionsResponse(
            createResponseHeader(request),
            results.toArray(TransferResult[]::new),
            new DiagnosticInfo[0]
        );

        return CompletableFuture.completedFuture(response);
    }

    private static boolean sessionsHaveSameUser(Session s1, Session s2) {
        Object identity1 = s1.getIdentityObject();
        Object identity2 = s2.getIdentityObject();

        return Objects.equals(identity1, identity2);
    }

}

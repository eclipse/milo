/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.services;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredDataItem;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.sdk.server.subscriptions.SubscriptionManager;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.application.services.SubscriptionServiceSet;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
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

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class SubscriptionServices implements SubscriptionServiceSet {

    private final SubscriptionManager subscriptionManager;

    public SubscriptionServices(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    @Override
    public void onCreateSubscription(ServiceRequest<CreateSubscriptionRequest, CreateSubscriptionResponse> service) {
        subscriptionManager.createSubscription(service);
    }

    @Override
    public void onModifySubscription(ServiceRequest<ModifySubscriptionRequest, ModifySubscriptionResponse> service) {
        subscriptionManager.modifySubscription(service);
    }

    @Override
    public void onDeleteSubscriptions(ServiceRequest<DeleteSubscriptionsRequest, DeleteSubscriptionsResponse> service) {
        subscriptionManager.deleteSubscription(service);
    }

    @Override
    public void onSetPublishingMode(ServiceRequest<SetPublishingModeRequest, SetPublishingModeResponse> service) {
        subscriptionManager.setPublishingMode(service);
    }

    @Override
    public void onPublish(ServiceRequest<PublishRequest, PublishResponse> service) {
        subscriptionManager.publish(service);
    }

    @Override
    public void onRepublish(ServiceRequest<RepublishRequest, RepublishResponse> service) {
        subscriptionManager.republish(service);
    }

    @Override
    public void onTransferSubscriptions(
        ServiceRequest<TransferSubscriptionsRequest, TransferSubscriptionsResponse> service) {

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        TransferSubscriptionsRequest request = service.getRequest();
        List<UInteger> subscriptionIds = l(request.getSubscriptionIds());

        if (subscriptionIds.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        List<TransferResult> results = Lists.newArrayList();

        for (UInteger subscriptionId : subscriptionIds) {
            Subscription subscription = server.getSubscriptions().get(subscriptionId);

            if (subscription == null) {
                results.add(new TransferResult(new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid), new UInteger[0]));
            } else {
                Session otherSession = subscription.getSession();

                if (!sessionsHaveSameUser(session, otherSession)) {
                    results.add(new TransferResult(new StatusCode(StatusCodes.Bad_UserAccessDenied), new UInteger[0]));
                } else {
                    UInteger[] availableSequenceNumbers;

                    synchronized (subscription) {
                        otherSession.getSubscriptionManager().sendStatusChangeNotification(subscription);
                        otherSession.getSubscriptionManager().removeSubscription(subscriptionId);

                        subscription.setSubscriptionManager(session.getSubscriptionManager());
                        subscriptionManager.addSubscription(subscription);

                        availableSequenceNumbers = subscription.getAvailableSequenceNumbers();

                        if (request.getSendInitialValues()) {
                            subscription.getMonitoredItems().values().stream()
                                .filter(item -> item instanceof MonitoredDataItem)
                                .map(item -> (MonitoredDataItem) item)
                                .forEach(MonitoredDataItem::clearLastValue);
                        }
                    }

                    results.add(new TransferResult(StatusCode.GOOD, availableSequenceNumbers));
                }
            }
        }

        service.setResponse(new TransferSubscriptionsResponse(
            service.createResponseHeader(),
            a(results, TransferResult.class),
            new DiagnosticInfo[0]
        ));
    }

    private boolean sessionsHaveSameUser(Session s1, Session s2) {
        Object identity1 = s1.getIdentityObject();
        Object identity2 = s2.getIdentityObject();

        return Objects.equals(identity1, identity2);
    }

}

/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferResult;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.server.services.SubscriptionServiceSet;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class DefaultSubscriptionServiceSet implements SubscriptionServiceSet {

    private final SubscriptionManager subscriptionManager;

    public DefaultSubscriptionServiceSet(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    @Override
    public void onCreateSubscription(ServiceRequest service) {
        subscriptionManager.createSubscription(service);
    }

    @Override
    public void onModifySubscription(ServiceRequest service) throws UaException {
        subscriptionManager.modifySubscription(service);
    }

    @Override
    public void onDeleteSubscriptions(ServiceRequest service) throws UaException {
        subscriptionManager.deleteSubscription(service);
    }

    @Override
    public void onSetPublishingMode(ServiceRequest service) {
        subscriptionManager.setPublishingMode(service);
    }

    @Override
    public void onPublish(ServiceRequest service) {
        subscriptionManager.publish(service);
    }

    @Override
    public void onRepublish(ServiceRequest service) {
        subscriptionManager.republish(service);
    }

    @Override
    public void onTransferSubscriptions(ServiceRequest service) throws UaException {
        TransferSubscriptionsRequest request = (TransferSubscriptionsRequest) service.getRequest();

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        List<UInteger> subscriptionIds = l(request.getSubscriptionIds());

        if (subscriptionIds.isEmpty()) {
            throw new UaException(StatusCodes.Bad_NothingToDo);
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

        TransferSubscriptionsResponse response = new TransferSubscriptionsResponse(
            service.createResponseHeader(),
            a(results, TransferResult.class),
            new DiagnosticInfo[0]
        );

        service.setResponse(response);
    }

    private boolean sessionsHaveSameUser(Session s1, Session s2) {
        Object identity1 = s1.getIdentityObject();
        Object identity2 = s2.getIdentityObject();

        return Objects.equals(identity1, identity2);
    }

}

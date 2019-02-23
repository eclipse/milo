/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.methods;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.annotations.UaInputArgument;
import org.eclipse.milo.opcua.sdk.server.annotations.UaMethod;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredDataItem;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler.InvocationContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ResendData {

    private final OpcUaServer server;

    public ResendData(OpcUaServer server) {
        this.server = server;
    }

    @UaMethod
    public void invoke(
        InvocationContext context,

        @UaInputArgument(name = "subscriptionId")
            UInteger subscriptionId) throws UaException {

        Session session = context.getSession().orElse(null);

        if (session != null) {
            Subscription subscription = session.getSubscriptionManager().getSubscription(subscriptionId);

            if (subscription == null) {
                context.setFailure(new UaException(StatusCodes.Bad_SubscriptionIdInvalid));
            } else {
                subscription.getMonitoredItems().values().stream()
                    .filter(item -> item instanceof MonitoredDataItem)
                    .map(item -> (MonitoredDataItem) item)
                    .forEach(MonitoredDataItem::clearLastValue);
            }
        } else {
            context.setFailure(new UaException(StatusCodes.Bad_UserAccessDenied));
        }
    }

}

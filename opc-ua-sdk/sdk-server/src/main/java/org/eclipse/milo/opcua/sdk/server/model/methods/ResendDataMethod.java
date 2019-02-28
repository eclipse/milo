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

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredDataItem;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public class ResendDataMethod extends AbstractMethodInvocationHandler {

    public static final Argument SUBSCRIPTION_ID = new Argument(
        "SubscriptionId",
        Identifiers.UInt32,
        ValueRanks.Scalar,
        null,
        LocalizedText.NULL_VALUE
    );

    public ResendDataMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{SUBSCRIPTION_ID};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[0];
    }

    @Override
    protected Variant[] invoke(InvocationContext invocationContext, Variant[] inputValues) throws UaException {
        UInteger subscriptionId = (UInteger) inputValues[0].getValue();

        Session session = invocationContext.getSession().orElse(null);

        if (session != null) {
            Subscription subscription = session.getSubscriptionManager().getSubscription(subscriptionId);

            if (subscription == null) {
                throw new UaException(StatusCodes.Bad_SubscriptionIdInvalid);
            } else {
                subscription.getMonitoredItems().values().stream()
                    .filter(item -> item instanceof MonitoredDataItem)
                    .map(item -> (MonitoredDataItem) item)
                    .forEach(MonitoredDataItem::clearLastValue);
            }
        } else {
            throw new UaException(StatusCodes.Bad_UserAccessDenied);
        }

        return new Variant[0];
    }
}

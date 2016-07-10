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

package org.eclipse.milo.opcua.sdk.server.model.methods;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.annotations.UaInputArgument;
import org.eclipse.milo.opcua.sdk.server.annotations.UaMethod;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler.InvocationContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
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

        Subscription subscription = server.getSubscriptions().get(subscriptionId);

        if (subscription == null) {
            context.setFailure(new UaException(new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid)));
        } else {
            // TODO
            // subscription.resendData();
        }
    }

}

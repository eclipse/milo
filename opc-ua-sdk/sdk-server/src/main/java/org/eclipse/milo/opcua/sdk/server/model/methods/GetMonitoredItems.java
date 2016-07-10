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

import java.util.List;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.annotations.UaInputArgument;
import org.eclipse.milo.opcua.sdk.server.annotations.UaMethod;
import org.eclipse.milo.opcua.sdk.server.annotations.UaOutputArgument;
import org.eclipse.milo.opcua.sdk.server.items.BaseMonitoredItem;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler.InvocationContext;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler.Out;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class GetMonitoredItems {

    private final OpcUaServer server;

    public GetMonitoredItems(OpcUaServer server) {
        this.server = server;
    }

    @UaMethod
    public void invoke(
        InvocationContext context,

        @UaInputArgument(name = "subscriptionId")
            UInteger subscriptionId,

        @UaOutputArgument(name = "serverHandles")
            Out<UInteger[]> serverHandles,

        @UaOutputArgument(name = "clientHandles")
            Out<UInteger[]> clientHandles) throws UaException {

        Subscription subscription = server.getSubscriptions().get(subscriptionId);

        if (subscription == null) {
            context.setFailure(new UaException(new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid)));
        } else {
            List<UInteger> serverHandleList = Lists.newArrayList();
            List<UInteger> clientHandleList = Lists.newArrayList();

            for (BaseMonitoredItem<?> item : subscription.getMonitoredItems().values()) {
                serverHandleList.add(item.getId());
                clientHandleList.add(uint(item.getClientHandle()));
            }

            serverHandles.set(serverHandleList.toArray(new UInteger[serverHandleList.size()]));
            clientHandles.set(clientHandleList.toArray(new UInteger[clientHandleList.size()]));
        }
    }

}

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

import java.util.List;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.items.BaseMonitoredItem;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class GetMonitoredItemsMethod extends AbstractMethodInvocationHandler {

    public static final Argument SUBSCRIPTION_ID = new Argument(
        "SubscriptionId",
        Identifiers.UInt32,
        ValueRanks.Scalar,
        null,
        LocalizedText.NULL_VALUE
    );

    public static final Argument SERVER_HANDLES = new Argument(
        "ServerHandles",
        Identifiers.UInt32,
        ValueRanks.OneDimension,
        new UInteger[]{uint(0)},
        LocalizedText.NULL_VALUE
    );

    public static final Argument CLIENT_HANDLES = new Argument(
        "ClientHandles",
        Identifiers.UInt32,
        ValueRanks.OneDimension,
        new UInteger[]{uint(0)},
        LocalizedText.NULL_VALUE
    );

    private final OpcUaServer server;

    public GetMonitoredItemsMethod(UaMethodNode node) {
        super(node);

        server = node.getNodeContext().getServer();
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{SUBSCRIPTION_ID};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{SERVER_HANDLES, CLIENT_HANDLES};
    }

    @Override
    protected Variant[] invoke(InvocationContext invocationContext, Variant[] inputValues) throws UaException {
        UInteger subscriptionId = (UInteger) inputValues[0].getValue();

        Subscription subscription = server.getSubscriptions().get(subscriptionId);

        if (subscription != null) {
            List<UInteger> serverHandleList = Lists.newArrayList();
            List<UInteger> clientHandleList = Lists.newArrayList();

            for (BaseMonitoredItem<?> item : subscription.getMonitoredItems().values()) {
                serverHandleList.add(item.getId());
                clientHandleList.add(uint(item.getClientHandle()));
            }

            return new Variant[]{
                new Variant(serverHandleList.toArray(new UInteger[0])),
                new Variant(clientHandleList.toArray(new UInteger[0]))
            };
        } else {
            throw new UaException(new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid));
        }
    }

}

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
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.methods.Out;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class GetMonitoredItemsMethod extends AbstractMethodInvocationHandler {
    public static final Argument SUBSCRIPTION_ID = new Argument(
        "SubscriptionId",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument SERVER_HANDLES = new Argument(
        "ServerHandles",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.OneDimension,
        new UInteger[]{Unsigned.uint(0)},
        new LocalizedText("", "")
    );

    public static final Argument CLIENT_HANDLES = new Argument(
        "ClientHandles",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.OneDimension,
        new UInteger[]{Unsigned.uint(0)},
        new LocalizedText("", "")
    );

    public GetMonitoredItemsMethod(UaMethodNode node) {
        super(node);
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
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        UInteger subscriptionId = (UInteger) inputValues[0].getValue();
        Out<UInteger[]> serverHandles = new Out<UInteger[]>();
        Out<UInteger[]> clientHandles = new Out<UInteger[]>();
        invoke(context, subscriptionId, serverHandles, clientHandles);
        return new Variant[]{new Variant(serverHandles.get()), new Variant(clientHandles.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   UInteger subscriptionId, Out<UInteger[]> serverHandles, Out<UInteger[]> clientHandles) throws
        UaException;
}

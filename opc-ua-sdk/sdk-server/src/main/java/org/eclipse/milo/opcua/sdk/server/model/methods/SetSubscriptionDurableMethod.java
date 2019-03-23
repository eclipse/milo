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
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class SetSubscriptionDurableMethod extends AbstractMethodInvocationHandler {
    public static final Argument SUBSCRIPTION_ID = new Argument(
        "SubscriptionId",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument LIFETIME_IN_HOURS = new Argument(
        "LifetimeInHours",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument REVISED_LIFETIME_IN_HOURS = new Argument(
        "RevisedLifetimeInHours",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public SetSubscriptionDurableMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{SUBSCRIPTION_ID, LIFETIME_IN_HOURS};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{REVISED_LIFETIME_IN_HOURS};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        UInteger subscriptionId = (UInteger) inputValues[0].getValue();
        UInteger lifetimeInHours = (UInteger) inputValues[1].getValue();
        Out<UInteger> revisedLifetimeInHours = new Out<UInteger>();
        invoke(context, subscriptionId, lifetimeInHours, revisedLifetimeInHours);
        return new Variant[]{new Variant(revisedLifetimeInHours.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   UInteger subscriptionId, UInteger lifetimeInHours, Out<UInteger> revisedLifetimeInHours)
        throws UaException;
}

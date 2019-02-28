/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.methods;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.nodes.methods.AbstractMethodInvocationHandler.InvocationContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class GetMonitoredItemsNode extends UaMethodNode {

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

    private volatile InvocationDelegate invocationDelegate = new InvocationDelegate() {};

    public GetMonitoredItemsNode(
        UaNodeContext context,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        Boolean executable,
        Boolean userExecutable
    ) {

        super(
            context,
            nodeId,
            browseName,
            displayName,
            description,
            writeMask,
            userWriteMask,
            executable,
            userExecutable
        );

        super.setInvocationHandler(new InvocationHandler(this));
    }

    public void setInvocationDelegate(@Nonnull InvocationDelegate invocationDelegate) {
        Preconditions.checkNotNull(invocationDelegate, "invocation delegate must be non-null");

        this.invocationDelegate = invocationDelegate;
    }

    interface InvocationDelegate {

        default void invoke(
            InvocationContext context,
            UInteger subscriptionId,
            AtomicReference<UInteger[]> serverHandles,
            AtomicReference<UInteger[]> clientHandles
        ) throws UaException {

            throw new UaException(StatusCodes.Bad_NotImplemented);
        }

    }

    private class InvocationHandler extends AbstractMethodInvocationHandler {

        public InvocationHandler(GetMonitoredItemsNode node) {
            super(node);
        }

        @Override
        protected Argument[] getInputArguments() {
            return new Argument[]{
                SUBSCRIPTION_ID
            };
        }

        @Override
        protected Argument[] getOutputArguments() {
            return new Argument[]{
                SERVER_HANDLES,
                CLIENT_HANDLES
            };
        }

        @Override
        protected Variant[] invoke(InvocationContext invocationContext, Variant[] inputArguments) throws UaException {
            UInteger subscriptionId = (UInteger) inputArguments[0].getValue();
            AtomicReference<UInteger[]> serverHandles = new AtomicReference<>();
            AtomicReference<UInteger[]> clientHandles = new AtomicReference<>();

            invocationDelegate.invoke(invocationContext, subscriptionId, serverHandles, clientHandles);

            return new Variant[]{
                new Variant(serverHandles.get()),
                new Variant(clientHandles.get())
            };
        }

    }

}

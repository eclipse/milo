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
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class RequestServerStateChangeMethod extends AbstractMethodInvocationHandler {
    public static final Argument STATE = new Argument(
        "State",
        NodeId.parse("ns=0;i=852"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument ESTIMATED_RETURN_TIME = new Argument(
        "EstimatedReturnTime",
        NodeId.parse("ns=0;i=13"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument SECONDS_TILL_SHUTDOWN = new Argument(
        "SecondsTillShutdown",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument REASON = new Argument(
        "Reason",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument RESTART = new Argument(
        "Restart",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public RequestServerStateChangeMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{STATE, ESTIMATED_RETURN_TIME, SECONDS_TILL_SHUTDOWN, REASON, RESTART};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        ServerState state = (ServerState) inputValues[0].getValue();
        DateTime estimatedReturnTime = (DateTime) inputValues[1].getValue();
        UInteger secondsTillShutdown = (UInteger) inputValues[2].getValue();
        LocalizedText reason = (LocalizedText) inputValues[3].getValue();
        Boolean restart = (Boolean) inputValues[4].getValue();
        invoke(context, state, estimatedReturnTime, secondsTillShutdown, reason, restart);
        return new Variant[]{};
    }

    protected abstract void invoke(InvocationContext context,
                                   ServerState state, DateTime estimatedReturnTime, UInteger secondsTillShutdown,
                                   LocalizedText reason, Boolean restart) throws UaException;
}

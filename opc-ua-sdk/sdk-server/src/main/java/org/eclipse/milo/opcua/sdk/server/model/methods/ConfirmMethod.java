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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class ConfirmMethod extends AbstractMethodInvocationHandler {
    public static final Argument EVENT_ID = new Argument(
        "EventId",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "The identifier for the event to comment.")
    );

    public static final Argument COMMENT = new Argument(
        "Comment",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "The comment to add to the condition.")
    );

    public ConfirmMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{EVENT_ID, COMMENT};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        ByteString eventId = (ByteString) inputValues[0].getValue();
        LocalizedText comment = (LocalizedText) inputValues[1].getValue();
        invoke(context, eventId, comment);
        return new Variant[]{};
    }

    protected abstract void invoke(InvocationContext context,
                                   ByteString eventId, LocalizedText comment) throws UaException;
}

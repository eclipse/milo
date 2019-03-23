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
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class CreateDirectoryMethod extends AbstractMethodInvocationHandler {
    public static final Argument DIRECTORY_NAME = new Argument(
        "DirectoryName",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument DIRECTORY_NODE_ID = new Argument(
        "DirectoryNodeId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public CreateDirectoryMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{DIRECTORY_NAME};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{DIRECTORY_NODE_ID};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        String directoryName = (String) inputValues[0].getValue();
        Out<NodeId> directoryNodeId = new Out<NodeId>();
        invoke(context, directoryName, directoryNodeId);
        return new Variant[]{new Variant(directoryNodeId.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   String directoryName, Out<NodeId> directoryNodeId) throws UaException;
}

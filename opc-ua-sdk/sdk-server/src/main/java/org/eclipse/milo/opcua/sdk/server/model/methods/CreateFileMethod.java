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

public abstract class CreateFileMethod extends AbstractMethodInvocationHandler {
    public static final Argument FILE_NAME = new Argument(
        "FileName",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument REQUEST_FILE_OPEN = new Argument(
        "RequestFileOpen",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument FILE_NODE_ID = new Argument(
        "FileNodeId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument FILE_HANDLE = new Argument(
        "FileHandle",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public CreateFileMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{FILE_NAME, REQUEST_FILE_OPEN};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{FILE_NODE_ID, FILE_HANDLE};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        String fileName = (String) inputValues[0].getValue();
        Boolean requestFileOpen = (Boolean) inputValues[1].getValue();
        Out<NodeId> fileNodeId = new Out<NodeId>();
        Out<UInteger> fileHandle = new Out<UInteger>();
        invoke(context, fileName, requestFileOpen, fileNodeId, fileHandle);
        return new Variant[]{new Variant(fileNodeId.get()), new Variant(fileHandle.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   String fileName, Boolean requestFileOpen, Out<NodeId> fileNodeId, Out<UInteger> fileHandle)
        throws UaException;
}

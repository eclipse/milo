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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class OpenMethod extends AbstractMethodInvocationHandler {
    public static final Argument MODE = new Argument(
        "Mode",
        NodeId.parse("ns=0;i=3"),
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

    public OpenMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{MODE};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{FILE_HANDLE};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        UByte mode = (UByte) inputValues[0].getValue();
        Out<UInteger> fileHandle = new Out<UInteger>();
        invoke(context, mode, fileHandle);
        return new Variant[]{new Variant(fileHandle.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   UByte mode, Out<UInteger> fileHandle) throws UaException;
}

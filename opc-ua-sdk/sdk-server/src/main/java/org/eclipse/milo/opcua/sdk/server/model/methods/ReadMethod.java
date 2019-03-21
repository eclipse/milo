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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class ReadMethod extends AbstractMethodInvocationHandler {
    public static final Argument FILE_HANDLE = new Argument(
        "FileHandle",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument LENGTH = new Argument(
        "Length",
        NodeId.parse("ns=0;i=6"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument DATA = new Argument(
        "Data",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public ReadMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{FILE_HANDLE, LENGTH};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{DATA};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        UInteger fileHandle = (UInteger) inputValues[0].getValue();
        Integer length = (Integer) inputValues[1].getValue();
        Out<ByteString> data = new Out<ByteString>();
        invoke(context, fileHandle, length, data);
        return new Variant[]{new Variant(data.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   UInteger fileHandle, Integer length, Out<ByteString> data) throws UaException;
}

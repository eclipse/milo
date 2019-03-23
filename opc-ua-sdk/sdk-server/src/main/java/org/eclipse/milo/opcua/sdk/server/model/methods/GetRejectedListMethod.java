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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class GetRejectedListMethod extends AbstractMethodInvocationHandler {
    public static final Argument CERTIFICATES = new Argument(
        "Certificates",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.OneDimension,
        new UInteger[]{Unsigned.uint(0)},
        new LocalizedText("", "")
    );

    public GetRejectedListMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{CERTIFICATES};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        Out<ByteString[]> certificates = new Out<ByteString[]>();
        invoke(context, certificates);
        return new Variant[]{new Variant(certificates.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   Out<ByteString[]> certificates) throws UaException;
}

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
import org.eclipse.milo.opcua.sdk.server.api.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class RemoveCertificateMethod extends AbstractMethodInvocationHandler {
    public static final Argument THUMBPRINT = new Argument(
        "Thumbprint",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument IS_TRUSTED_CERTIFICATE = new Argument(
        "IsTrustedCertificate",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public RemoveCertificateMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{THUMBPRINT, IS_TRUSTED_CERTIFICATE};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        String thumbprint = (String) inputValues[0].getValue();
        Boolean isTrustedCertificate = (Boolean) inputValues[1].getValue();
        invoke(context, thumbprint, isTrustedCertificate);
        return new Variant[]{};
    }

    protected abstract void invoke(InvocationContext context,
                                   String thumbprint, Boolean isTrustedCertificate) throws UaException;
}

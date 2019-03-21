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
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class CreateSigningRequestMethod extends AbstractMethodInvocationHandler {
    public static final Argument CERTIFICATE_GROUP_ID = new Argument(
        "CertificateGroupId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument CERTIFICATE_TYPE_ID = new Argument(
        "CertificateTypeId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument SUBJECT_NAME = new Argument(
        "SubjectName",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument REGENERATE_PRIVATE_KEY = new Argument(
        "RegeneratePrivateKey",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument NONCE = new Argument(
        "Nonce",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument CERTIFICATE_REQUEST = new Argument(
        "CertificateRequest",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public CreateSigningRequestMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{CERTIFICATE_GROUP_ID, CERTIFICATE_TYPE_ID, SUBJECT_NAME, REGENERATE_PRIVATE_KEY, NONCE};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{CERTIFICATE_REQUEST};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        NodeId certificateGroupId = (NodeId) inputValues[0].getValue();
        NodeId certificateTypeId = (NodeId) inputValues[1].getValue();
        String subjectName = (String) inputValues[2].getValue();
        Boolean regeneratePrivateKey = (Boolean) inputValues[3].getValue();
        ByteString nonce = (ByteString) inputValues[4].getValue();
        Out<ByteString> certificateRequest = new Out<ByteString>();
        invoke(context, certificateGroupId, certificateTypeId, subjectName, regeneratePrivateKey, nonce, certificateRequest);
        return new Variant[]{new Variant(certificateRequest.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   NodeId certificateGroupId, NodeId certificateTypeId, String subjectName,
                                   Boolean regeneratePrivateKey, ByteString nonce, Out<ByteString> certificateRequest) throws
        UaException;
}

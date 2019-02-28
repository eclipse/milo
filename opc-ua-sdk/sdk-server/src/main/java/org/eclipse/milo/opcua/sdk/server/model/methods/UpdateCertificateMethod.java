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

import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

public abstract class UpdateCertificateMethod extends AbstractMethodInvocationHandler {
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

    public static final Argument CERTIFICATE = new Argument(
        "Certificate",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument ISSUER_CERTIFICATES = new Argument(
        "IssuerCertificates",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.OneDimension,
        new UInteger[]{Unsigned.uint(0)},
        new LocalizedText("", "")
    );

    public static final Argument PRIVATE_KEY_FORMAT = new Argument(
        "PrivateKeyFormat",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument PRIVATE_KEY = new Argument(
        "PrivateKey",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public static final Argument APPLY_CHANGES_REQUIRED = new Argument(
        "ApplyChangesRequired",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        null,
        new LocalizedText("", "")
    );

    public UpdateCertificateMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{CERTIFICATE_GROUP_ID, CERTIFICATE_TYPE_ID, CERTIFICATE, ISSUER_CERTIFICATES, PRIVATE_KEY_FORMAT, PRIVATE_KEY};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{APPLY_CHANGES_REQUIRED};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        NodeId certificateGroupId = (NodeId) inputValues[0].getValue();
        NodeId certificateTypeId = (NodeId) inputValues[1].getValue();
        ByteString certificate = (ByteString) inputValues[2].getValue();
        ByteString[] issuerCertificates = (ByteString[]) inputValues[3].getValue();
        String privateKeyFormat = (String) inputValues[4].getValue();
        ByteString privateKey = (ByteString) inputValues[5].getValue();
        AtomicReference<Boolean> applyChangesRequired = new AtomicReference<Boolean>();
        invoke(context, certificateGroupId, certificateTypeId, certificate, issuerCertificates, privateKeyFormat, privateKey, applyChangesRequired);
        return new Variant[]{new Variant(applyChangesRequired.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   NodeId certificateGroupId, NodeId certificateTypeId, ByteString certificate,
                                   ByteString[] issuerCertificates, String privateKeyFormat, ByteString privateKey,
                                   AtomicReference<Boolean> applyChangesRequired) throws UaException;
}

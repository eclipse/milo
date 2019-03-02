/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditActivateSessionEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;

public class AuditActivateSessionEventNode extends AuditSessionEventNode implements AuditActivateSessionEventType {
    public AuditActivateSessionEventNode(UaNodeContext context, NodeId nodeId,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditActivateSessionEventNode(UaNodeContext context, NodeId nodeId,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getSecureChannelIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.SECURE_CHANNEL_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getSecureChannelId() {
        Optional<String> propertyValue = getProperty(AuditActivateSessionEventType.SECURE_CHANNEL_ID);
        return propertyValue.orElse(null);
    }

    @Override
    public void setSecureChannelId(String value) {
        setProperty(AuditActivateSessionEventType.SECURE_CHANNEL_ID, value);
    }

    @Override
    public PropertyNode getClientSoftwareCertificatesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public SignedSoftwareCertificate[] getClientSoftwareCertificates() {
        Optional<SignedSoftwareCertificate[]> propertyValue = getProperty(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setClientSoftwareCertificates(SignedSoftwareCertificate[] value) {
        setProperty(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES, value);
    }

    @Override
    public PropertyNode getUserIdentityTokenNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.USER_IDENTITY_TOKEN);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UserIdentityToken getUserIdentityToken() {
        Optional<UserIdentityToken> propertyValue = getProperty(AuditActivateSessionEventType.USER_IDENTITY_TOKEN);
        return propertyValue.orElse(null);
    }

    @Override
    public void setUserIdentityToken(UserIdentityToken value) {
        setProperty(AuditActivateSessionEventType.USER_IDENTITY_TOKEN, value);
    }
}

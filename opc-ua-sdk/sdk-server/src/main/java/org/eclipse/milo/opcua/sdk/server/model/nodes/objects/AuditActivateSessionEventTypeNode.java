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

import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditActivateSessionEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;

public class AuditActivateSessionEventTypeNode extends AuditSessionEventTypeNode implements AuditActivateSessionEventType {
    public AuditActivateSessionEventTypeNode(UaNodeContext context, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditActivateSessionEventTypeNode(UaNodeContext context, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyTypeNode getClientSoftwareCertificatesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES);
        return (PropertyTypeNode) propertyNode.orElse(null);
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
    public PropertyTypeNode getUserIdentityTokenNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.USER_IDENTITY_TOKEN);
        return (PropertyTypeNode) propertyNode.orElse(null);
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

    @Override
    public PropertyTypeNode getSecureChannelIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.SECURE_CHANNEL_ID);
        return (PropertyTypeNode) propertyNode.orElse(null);
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
}

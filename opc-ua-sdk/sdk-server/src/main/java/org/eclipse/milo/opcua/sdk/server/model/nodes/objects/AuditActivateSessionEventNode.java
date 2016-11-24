/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditActivateSessionEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditActivateSessionEventType")
public class AuditActivateSessionEventNode extends AuditSessionEventNode implements AuditActivateSessionEventType {

    public AuditActivateSessionEventNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public SignedSoftwareCertificate[] getClientSoftwareCertificates() {
        Optional<SignedSoftwareCertificate[]> property = getProperty(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getClientSoftwareCertificatesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setClientSoftwareCertificates(SignedSoftwareCertificate[] value) {
        setProperty(AuditActivateSessionEventType.CLIENT_SOFTWARE_CERTIFICATES, value);
    }

    @Override
    public UserIdentityToken getUserIdentityToken() {
        Optional<UserIdentityToken> property = getProperty(AuditActivateSessionEventType.USER_IDENTITY_TOKEN);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getUserIdentityTokenNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.USER_IDENTITY_TOKEN.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setUserIdentityToken(UserIdentityToken value) {
        setProperty(AuditActivateSessionEventType.USER_IDENTITY_TOKEN, value);
    }

    @Override
    public String getSecureChannelId() {
        Optional<String> property = getProperty(AuditActivateSessionEventType.SECURE_CHANNEL_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSecureChannelIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditActivateSessionEventType.SECURE_CHANNEL_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSecureChannelId(String value) {
        setProperty(AuditActivateSessionEventType.SECURE_CHANNEL_ID, value);
    }

}

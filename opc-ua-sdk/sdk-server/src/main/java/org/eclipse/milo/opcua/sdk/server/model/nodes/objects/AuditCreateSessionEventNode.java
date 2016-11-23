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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditCreateSessionEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditCreateSessionEventType")
public class AuditCreateSessionEventNode extends AuditSessionEventNode implements AuditCreateSessionEventType {

    public AuditCreateSessionEventNode(
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
    public String getSecureChannelId() {
        Optional<String> property = getProperty(AuditCreateSessionEventType.SECURE_CHANNEL_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSecureChannelIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.SECURE_CHANNEL_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSecureChannelId(String value) {
        setProperty(AuditCreateSessionEventType.SECURE_CHANNEL_ID, value);
    }

    @Override
    public ByteString getClientCertificate() {
        Optional<ByteString> property = getProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getClientCertificateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.CLIENT_CERTIFICATE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setClientCertificate(ByteString value) {
        setProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE, value);
    }

    @Override
    public String getClientCertificateThumbprint() {
        Optional<String> property = getProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getClientCertificateThumbprintNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setClientCertificateThumbprint(String value) {
        setProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT, value);
    }

    @Override
    public Double getRevisedSessionTimeout() {
        Optional<Double> property = getProperty(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getRevisedSessionTimeoutNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setRevisedSessionTimeout(Double value) {
        setProperty(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT, value);
    }

}

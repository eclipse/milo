/*
 * Copyright (c) 2017 Kevin Herron
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

public class AuditCreateSessionEventNode extends AuditSessionEventNode implements AuditCreateSessionEventType {
    public AuditCreateSessionEventNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                       UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditCreateSessionEventNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                       UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getSecureChannelIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.SECURE_CHANNEL_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public String getSecureChannelId() {
        Optional<String> propertyValue = getProperty(AuditCreateSessionEventType.SECURE_CHANNEL_ID);
        return propertyValue.orElse(null);
    }

    public void setSecureChannelId(String value) {
        setProperty(AuditCreateSessionEventType.SECURE_CHANNEL_ID, value);
    }

    public PropertyNode getClientCertificateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.CLIENT_CERTIFICATE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public ByteString getClientCertificate() {
        Optional<ByteString> propertyValue = getProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE);
        return propertyValue.orElse(null);
    }

    public void setClientCertificate(ByteString value) {
        setProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE, value);
    }

    public PropertyNode getClientCertificateThumbprintNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public String getClientCertificateThumbprint() {
        Optional<String> propertyValue = getProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT);
        return propertyValue.orElse(null);
    }

    public void setClientCertificateThumbprint(String value) {
        setProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT, value);
    }

    public PropertyNode getRevisedSessionTimeoutNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Double getRevisedSessionTimeout() {
        Optional<Double> propertyValue = getProperty(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT);
        return propertyValue.orElse(null);
    }

    public void setRevisedSessionTimeout(Double value) {
        setProperty(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT, value);
    }
}

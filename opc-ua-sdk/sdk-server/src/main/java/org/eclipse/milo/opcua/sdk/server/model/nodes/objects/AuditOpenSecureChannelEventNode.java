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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditOpenSecureChannelEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

public class AuditOpenSecureChannelEventNode extends AuditChannelEventNode implements AuditOpenSecureChannelEventType {
    public AuditOpenSecureChannelEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditOpenSecureChannelEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getClientCertificateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public ByteString getClientCertificate() {
        Optional<ByteString> propertyValue = getProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE);
        return propertyValue.orElse(null);
    }

    public void setClientCertificate(ByteString value) {
        setProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE, value);
    }

    public PropertyNode getClientCertificateThumbprintNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public String getClientCertificateThumbprint() {
        Optional<String> propertyValue = getProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT);
        return propertyValue.orElse(null);
    }

    public void setClientCertificateThumbprint(String value) {
        setProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT, value);
    }

    public PropertyNode getRequestTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.REQUEST_TYPE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public SecurityTokenRequestType getRequestType() {
        Optional<SecurityTokenRequestType> propertyValue = getProperty(AuditOpenSecureChannelEventType.REQUEST_TYPE);
        return propertyValue.orElse(null);
    }

    public void setRequestType(SecurityTokenRequestType value) {
        setProperty(AuditOpenSecureChannelEventType.REQUEST_TYPE, value);
    }

    public PropertyNode getSecurityPolicyUriNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public String getSecurityPolicyUri() {
        Optional<String> propertyValue = getProperty(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI);
        return propertyValue.orElse(null);
    }

    public void setSecurityPolicyUri(String value) {
        setProperty(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI, value);
    }

    public PropertyNode getSecurityModeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.SECURITY_MODE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public MessageSecurityMode getSecurityMode() {
        Optional<MessageSecurityMode> propertyValue = getProperty(AuditOpenSecureChannelEventType.SECURITY_MODE);
        return propertyValue.orElse(null);
    }

    public void setSecurityMode(MessageSecurityMode value) {
        setProperty(AuditOpenSecureChannelEventType.SECURITY_MODE, value);
    }

    public PropertyNode getRequestedLifetimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Double getRequestedLifetime() {
        Optional<Double> propertyValue = getProperty(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME);
        return propertyValue.orElse(null);
    }

    public void setRequestedLifetime(Double value) {
        setProperty(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME, value);
    }
}

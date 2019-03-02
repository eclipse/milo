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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditOpenSecureChannelEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

public class AuditOpenSecureChannelEventNode extends AuditChannelEventNode implements AuditOpenSecureChannelEventType {
    public AuditOpenSecureChannelEventNode(UaNodeContext context, NodeId nodeId,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditOpenSecureChannelEventNode(UaNodeContext context, NodeId nodeId,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getRequestTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.REQUEST_TYPE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public SecurityTokenRequestType getRequestType() {
        Optional<SecurityTokenRequestType> propertyValue = getProperty(AuditOpenSecureChannelEventType.REQUEST_TYPE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setRequestType(SecurityTokenRequestType value) {
        setProperty(AuditOpenSecureChannelEventType.REQUEST_TYPE, value);
    }

    @Override
    public PropertyNode getSecurityPolicyUriNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getSecurityPolicyUri() {
        Optional<String> propertyValue = getProperty(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI);
        return propertyValue.orElse(null);
    }

    @Override
    public void setSecurityPolicyUri(String value) {
        setProperty(AuditOpenSecureChannelEventType.SECURITY_POLICY_URI, value);
    }

    @Override
    public PropertyNode getClientCertificateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public ByteString getClientCertificate() {
        Optional<ByteString> propertyValue = getProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setClientCertificate(ByteString value) {
        setProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE, value);
    }

    @Override
    public PropertyNode getClientCertificateThumbprintNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getClientCertificateThumbprint() {
        Optional<String> propertyValue = getProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setClientCertificateThumbprint(String value) {
        setProperty(AuditOpenSecureChannelEventType.CLIENT_CERTIFICATE_THUMBPRINT, value);
    }

    @Override
    public PropertyNode getSecurityModeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.SECURITY_MODE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public MessageSecurityMode getSecurityMode() {
        Optional<MessageSecurityMode> propertyValue = getProperty(AuditOpenSecureChannelEventType.SECURITY_MODE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setSecurityMode(MessageSecurityMode value) {
        setProperty(AuditOpenSecureChannelEventType.SECURITY_MODE, value);
    }

    @Override
    public PropertyNode getRequestedLifetimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Double getRequestedLifetime() {
        Optional<Double> propertyValue = getProperty(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setRequestedLifetime(Double value) {
        setProperty(AuditOpenSecureChannelEventType.REQUESTED_LIFETIME, value);
    }
}

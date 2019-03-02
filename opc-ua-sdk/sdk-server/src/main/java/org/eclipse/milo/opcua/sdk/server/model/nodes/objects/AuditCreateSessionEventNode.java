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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditCreateSessionEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditCreateSessionEventNode extends AuditSessionEventNode implements AuditCreateSessionEventType {
    public AuditCreateSessionEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                       UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditCreateSessionEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                       UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getClientCertificateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.CLIENT_CERTIFICATE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public ByteString getClientCertificate() {
        Optional<ByteString> propertyValue = getProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setClientCertificate(ByteString value) {
        setProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE, value);
    }

    @Override
    public PropertyNode getRevisedSessionTimeoutNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Double getRevisedSessionTimeout() {
        Optional<Double> propertyValue = getProperty(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setRevisedSessionTimeout(Double value) {
        setProperty(AuditCreateSessionEventType.REVISED_SESSION_TIMEOUT, value);
    }

    @Override
    public PropertyNode getSecureChannelIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.SECURE_CHANNEL_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getSecureChannelId() {
        Optional<String> propertyValue = getProperty(AuditCreateSessionEventType.SECURE_CHANNEL_ID);
        return propertyValue.orElse(null);
    }

    @Override
    public void setSecureChannelId(String value) {
        setProperty(AuditCreateSessionEventType.SECURE_CHANNEL_ID, value);
    }

    @Override
    public PropertyNode getClientCertificateThumbprintNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getClientCertificateThumbprint() {
        Optional<String> propertyValue = getProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setClientCertificateThumbprint(String value) {
        setProperty(AuditCreateSessionEventType.CLIENT_CERTIFICATE_THUMBPRINT, value);
    }
}

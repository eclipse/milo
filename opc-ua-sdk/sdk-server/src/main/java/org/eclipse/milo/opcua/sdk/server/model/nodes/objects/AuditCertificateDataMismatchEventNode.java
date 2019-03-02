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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditCertificateDataMismatchEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditCertificateDataMismatchEventNode extends AuditCertificateEventNode implements AuditCertificateDataMismatchEventType {
    public AuditCertificateDataMismatchEventNode(UaNodeContext context, NodeId nodeId,
                                                 QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                 UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditCertificateDataMismatchEventNode(UaNodeContext context, NodeId nodeId,
                                                 QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                 UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getInvalidUriNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCertificateDataMismatchEventType.INVALID_URI);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getInvalidUri() {
        Optional<String> propertyValue = getProperty(AuditCertificateDataMismatchEventType.INVALID_URI);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInvalidUri(String value) {
        setProperty(AuditCertificateDataMismatchEventType.INVALID_URI, value);
    }

    @Override
    public PropertyNode getInvalidHostnameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditCertificateDataMismatchEventType.INVALID_HOSTNAME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getInvalidHostname() {
        Optional<String> propertyValue = getProperty(AuditCertificateDataMismatchEventType.INVALID_HOSTNAME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setInvalidHostname(String value) {
        setProperty(AuditCertificateDataMismatchEventType.INVALID_HOSTNAME, value);
    }
}

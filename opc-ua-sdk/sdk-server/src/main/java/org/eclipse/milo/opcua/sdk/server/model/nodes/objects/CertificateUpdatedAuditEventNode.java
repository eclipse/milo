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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.CertificateUpdatedAuditEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CertificateUpdatedAuditEventNode extends AuditUpdateMethodEventNode implements CertificateUpdatedAuditEventType {
    public CertificateUpdatedAuditEventNode(UaNodeContext context, NodeId nodeId,
                                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                            UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public CertificateUpdatedAuditEventNode(UaNodeContext context, NodeId nodeId,
                                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                            UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getCertificateGroupNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public NodeId getCertificateGroup() {
        Optional<NodeId> propertyValue = getProperty(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP);
        return propertyValue.orElse(null);
    }

    @Override
    public void setCertificateGroup(NodeId value) {
        setProperty(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP, value);
    }

    @Override
    public PropertyNode getCertificateTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public NodeId getCertificateType() {
        Optional<NodeId> propertyValue = getProperty(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setCertificateType(NodeId value) {
        setProperty(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE, value);
    }
}

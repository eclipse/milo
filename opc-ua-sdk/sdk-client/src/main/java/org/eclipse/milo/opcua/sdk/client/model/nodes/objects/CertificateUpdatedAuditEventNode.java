/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateUpdatedAuditEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class CertificateUpdatedAuditEventNode extends AuditUpdateMethodEventNode implements CertificateUpdatedAuditEventType {
    public CertificateUpdatedAuditEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getCertificateGroupNode() {
        return getPropertyNode(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP);
    }

    public CompletableFuture<NodeId> getCertificateGroup() {
        return getProperty(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP);
    }

    public CompletableFuture<StatusCode> setCertificateGroup(NodeId value) {
        return setProperty(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP, value);
    }

    public CompletableFuture<PropertyNode> getCertificateTypeNode() {
        return getPropertyNode(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE);
    }

    public CompletableFuture<NodeId> getCertificateType() {
        return getProperty(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE);
    }

    public CompletableFuture<StatusCode> setCertificateType(NodeId value) {
        return setProperty(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE, value);
    }
}

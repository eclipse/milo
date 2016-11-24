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

    @Override
    public CompletableFuture<PropertyNode> certificateGroup() {
        return getPropertyNode(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getCertificateGroup() {
        return getProperty(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP);
    }

    @Override
    public CompletableFuture<StatusCode> setCertificateGroup(NodeId value) {
        return setProperty(CertificateUpdatedAuditEventType.CERTIFICATE_GROUP, value);
    }

    @Override
    public CompletableFuture<PropertyNode> certificateType() {
        return getPropertyNode(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getCertificateType() {
        return getProperty(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE);
    }

    @Override
    public CompletableFuture<StatusCode> setCertificateType(NodeId value) {
        return setProperty(CertificateUpdatedAuditEventType.CERTIFICATE_TYPE, value);
    }


}
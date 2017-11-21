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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateGroupType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class CertificateGroupNode extends BaseObjectNode implements CertificateGroupType {
    public CertificateGroupNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getCertificateTypesNode() {
        return getPropertyNode(CertificateGroupType.CERTIFICATE_TYPES);
    }

    public CompletableFuture<NodeId[]> getCertificateTypes() {
        return getProperty(CertificateGroupType.CERTIFICATE_TYPES);
    }

    public CompletableFuture<StatusCode> setCertificateTypes(NodeId[] value) {
        return setProperty(CertificateGroupType.CERTIFICATE_TYPES, value);
    }

    public CompletableFuture<TrustListNode> getTrustListNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "TrustList").thenApply(TrustListNode.class::cast);
    }
}

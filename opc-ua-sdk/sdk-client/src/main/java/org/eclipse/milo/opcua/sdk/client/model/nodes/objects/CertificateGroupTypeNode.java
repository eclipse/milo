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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateGroupType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class CertificateGroupTypeNode extends BaseObjectTypeNode implements CertificateGroupType {
    public CertificateGroupTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getCertificateTypesNode() {
        return getPropertyNode(CertificateGroupType.CERTIFICATE_TYPES);
    }

    public CompletableFuture<NodeId[]> getCertificateTypes() {
        return getProperty(CertificateGroupType.CERTIFICATE_TYPES);
    }

    public CompletableFuture<StatusCode> setCertificateTypes(NodeId[] value) {
        return setProperty(CertificateGroupType.CERTIFICATE_TYPES, value);
    }

    @Override
    public CompletableFuture<TrustListTypeNode> getTrustListNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "TrustList").thenApply(TrustListTypeNode.class::cast);
    }
}

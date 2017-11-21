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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateGroupFolderType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class CertificateGroupFolderNode extends FolderNode implements CertificateGroupFolderType {
    public CertificateGroupFolderNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<CertificateGroupNode> getDefaultApplicationGroupNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "DefaultApplicationGroup").thenApply(CertificateGroupNode.class::cast);
    }

    public CompletableFuture<CertificateGroupNode> getDefaultHttpsGroupNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "DefaultHttpsGroup").thenApply(CertificateGroupNode.class::cast);
    }

    public CompletableFuture<CertificateGroupNode> getDefaultUserTokenGroupNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "DefaultUserTokenGroup").thenApply(CertificateGroupNode.class::cast);
    }
}

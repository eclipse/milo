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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateGroupFolderType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;


public class CertificateGroupFolderNode extends FolderNode implements CertificateGroupFolderType {

    public CertificateGroupFolderNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<CertificateGroupNode> defaultApplicationGroup() {
        return getObjectComponent(QualifiedName.parse("0:DefaultApplicationGroup"))
            .thenApply(CertificateGroupNode.class::cast);
    }

    @Override
    public CompletableFuture<CertificateGroupNode> defaultHttpsGroup() {
        return getObjectComponent(QualifiedName.parse("0:DefaultHttpsGroup"))
            .thenApply(CertificateGroupNode.class::cast);
    }

    @Override
    public CompletableFuture<CertificateGroupNode> defaultUserTokenGroup() {
        return getObjectComponent(QualifiedName.parse("0:DefaultUserTokenGroup"))
            .thenApply(CertificateGroupNode.class::cast);
    }

}
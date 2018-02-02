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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespacesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class NamespacesNode extends BaseObjectNode implements NamespacesType {
    public NamespacesNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<AddressSpaceFileNode> getAddressSpaceFileNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "AddressSpaceFile").thenApply(AddressSpaceFileNode.class::cast);
    }
}

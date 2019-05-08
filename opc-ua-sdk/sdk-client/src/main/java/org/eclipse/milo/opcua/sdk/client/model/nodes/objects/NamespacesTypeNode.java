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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespacesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class NamespacesTypeNode extends BaseObjectTypeNode implements NamespacesType {
    public NamespacesTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<AddressSpaceFileTypeNode> getAddressSpaceFileNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "AddressSpaceFile").thenApply(AddressSpaceFileTypeNode.class::cast);
    }
}

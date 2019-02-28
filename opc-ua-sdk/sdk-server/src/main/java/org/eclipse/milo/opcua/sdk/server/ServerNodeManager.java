/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.DelegatingNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * A {@link NodeManager} that delegates to the NodeManager belonging to the same namespace as the NodeId or source
 * NodeId of the reference being acted on.
 * <p>
 * Doesn't actually store any Nodes or References itself; sub-managers are expected to do the actual storage.
 */
public class ServerNodeManager extends DelegatingNodeManager<UaNode> {

    private final OpcUaServer server;

    public ServerNodeManager(OpcUaServer server) {
        this.server = server;
    }

    @Override
    protected NodeManager<UaNode> getNodeManager(NodeId nodeId) {
        return server.getNamespaceManager()
            .getNamespace(nodeId.getNamespaceIndex())
            .getNodeManager();
    }

    @Override
    public void addReference(Reference reference) {
        super.addReference(reference);

        reference.invert()
            .ifPresent(super::addVirtualReference);
    }

    @Override
    public void removeReference(Reference reference) {
        super.removeReference(reference);

        reference.invert()
            .ifPresent(super::removeVirtualReference);
    }

}

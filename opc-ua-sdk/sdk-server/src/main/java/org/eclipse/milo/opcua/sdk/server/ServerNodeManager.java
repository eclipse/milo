/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server;

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

    private OpcUaServer server;

    public ServerNodeManager(OpcUaServer server) {
        this.server = server;
    }

    @Override
    protected NodeManager<UaNode> getNodeManager(NodeId nodeId) {
        return server.getNamespaceManager()
            .getNamespace(nodeId.getNamespaceIndex())
            .getNodeManager();
    }

}

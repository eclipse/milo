/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.NamespaceManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface UaNodeContext {

    /**
     * @return the {@link OpcUaServer} instance.
     */
    OpcUaServer getServer();


    NodeManager<UaNode> getNodeManager();

    /**
     * @return the {@link NamespaceManager} from the server.
     */
    default NamespaceManager getNamespaceManager() {
        return getServer().getNamespaceManager();
    }

    /**
     * Get the {@link NodeManager} managing the {@link UaNode} identified by {@code nodeId}, if it exists.
     *
     * @param nodeId the {@link NodeId} of a {@link UaNode}.
     * @return the {@link NodeManager} managing the {@link UaNode} identified by {@code nodeId}, if it exists.
     */
    default Optional<NodeManager<UaNode>> getNodeManager(NodeId nodeId) {
        return getServer().getAddressSpaceManager().getAddressSpace(nodeId).getNodeManager();
    }

}

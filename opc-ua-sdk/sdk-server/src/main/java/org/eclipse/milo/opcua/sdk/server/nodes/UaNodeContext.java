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

import org.eclipse.milo.opcua.sdk.server.NamespaceManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;

public interface UaNodeContext {

    /**
     * @return the {@link OpcUaServer} instance.
     */
    OpcUaServer getServer();

    /**
     * @return the {@link UaNodeManager} managing this node.
     */
    default NodeManager<UaNode> getNodeManager() {
        return getServer().getNodeManager();
    }

    /**
     * @return the {@link NamespaceManager} from the server.
     */
    default NamespaceManager getNamespaceManager() {
        return getServer().getNamespaceManager();
    }

}

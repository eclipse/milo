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

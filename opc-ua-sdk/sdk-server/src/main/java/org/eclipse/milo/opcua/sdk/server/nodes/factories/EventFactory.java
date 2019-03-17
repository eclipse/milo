/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class EventFactory extends AbstractLifecycle {

    private final OpcUaServer server;
    private final NodeManager<UaNode> nodeManager;
    private final NodeFactory nodeFactory;

    public EventFactory(OpcUaServer server) {
        this(
            server,
            server.getObjectTypeManager(),
            server.getVariableTypeManager()
        );
    }

    public EventFactory(
        OpcUaServer server,
        ObjectTypeManager objectTypeManager,
        VariableTypeManager variableTypeManager
    ) {

        this.server = server;

        nodeManager = new UaNodeManager();

        nodeFactory = new NodeFactory(
            new EventNodeContext(server, nodeManager),
            objectTypeManager,
            variableTypeManager
        );
    }

    @Override
    protected void onStartup() {
        server.getAddressSpaceManager().register(nodeManager);
    }

    @Override
    protected void onShutdown() {
        server.getAddressSpaceManager().unregister(nodeManager);
    }

    public BaseEventNode createEvent(NodeId nodeId, NodeId typeDefinitionId) throws UaException {
        return (BaseEventNode) nodeFactory.createNode(
            nodeId,
            typeDefinitionId,
            true
        );
    }

    private static class EventNodeContext implements UaNodeContext {

        private final OpcUaServer server;
        private final NodeManager<UaNode> nodeManager;

        EventNodeContext(OpcUaServer server, NodeManager<UaNode> nodeManager) {
            this.server = server;
            this.nodeManager = nodeManager;
        }

        @Override
        public OpcUaServer getServer() {
            return server;
        }

        @Override
        public NodeManager<UaNode> getNodeManager() {
            return nodeManager;
        }

    }

}

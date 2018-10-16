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

package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import java.util.Optional;
import java.util.Set;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class EventFactory {

    private final OpcUaServer server;

    private final ObjectTypeManager objectTypeManager;
    private final VariableTypeManager variableTypeManager;

    public EventFactory(
        UaNodeContext context,
        ObjectTypeManager objectTypeManager,
        VariableTypeManager variableTypeManager) {

        this.objectTypeManager = objectTypeManager;
        this.variableTypeManager = variableTypeManager;

        server = context.getServer();
    }

    public BaseEventNode createEvent(
        NodeId nodeId,
        NodeId typeDefinitionId) throws UaException {

        NodeFactory nodeFactory = new NodeFactory(
            new EventNodeContext(server),
            objectTypeManager,
            variableTypeManager
        );

        return (BaseEventNode) nodeFactory.createNode(nodeId, typeDefinitionId, true);
    }

    private static class EventNodeContext implements UaNodeContext {

        private final EventNodeManager nodeManager;

        private final OpcUaServer server;

        EventNodeContext(OpcUaServer server) {
            this.server = server;

            nodeManager = new EventNodeManager(server.getNodeManager());
        }

        @Override
        public OpcUaServer getServer() {
            return server;
        }

        @Override
        public UaNodeManager getNodeManager() {
            return nodeManager;
        }

    }

    private static class EventNodeManager extends UaNodeManager {

        private final UaNodeManager delegate;

        EventNodeManager(UaNodeManager delegate) {
            this.delegate = delegate;
        }

        @Override
        public boolean containsNode(NodeId nodeId) {
            return super.containsNode(nodeId) || delegate.containsNode(nodeId);
        }

        @Override
        public Optional<UaNode> getNode(NodeId nodeId) {
            Optional<UaNode> node = super.getNode(nodeId);

            if (node.isPresent()) {
                return node;
            } else {
                return delegate.getNode(nodeId);
            }
        }

        @Override
        public Set<Reference> getReferences(NodeId nodeId) {
            if (super.containsNode(nodeId)) {
                return super.getReferences(nodeId);
            } else {
                return delegate.getReferences(nodeId);
            }
        }

    }

}

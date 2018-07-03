package org.eclipse.milo.opcua.sdk.server.nodes;

import java.util.Optional;
import java.util.Set;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public class EventFactory {

    private final OpcUaServer server;
    private final ObjectTypeManager objectTypeManager;
    private final VariableTypeManager variableTypeManager;

    public EventFactory(
        OpcUaServer server,
        ObjectTypeManager objectTypeManager,
        VariableTypeManager variableTypeManager) {

        this.server = server;
        this.objectTypeManager = objectTypeManager;
        this.variableTypeManager = variableTypeManager;
    }

    public BaseEventNode createEvent(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId) {

        NodeFactory nodeFactory = new NodeFactory(
            new EventNodeContext(server),
            objectTypeManager,
            variableTypeManager
        );

        return (BaseEventNode) nodeFactory.createObject(
            nodeId, browseName, displayName, typeDefinitionId);
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

package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.jooq.lambda.tuple.Tuple3;

public class NodeFactory {

    private final UaNodeContext context;
    private final ObjectTypeManager objectTypeManager;
    private final VariableTypeManager variableTypeManager;

    public NodeFactory(
        UaNodeContext context,
        ObjectTypeManager objectTypeManager,
        VariableTypeManager variableTypeManager) {

        this.context = context;
        this.objectTypeManager = objectTypeManager;
        this.variableTypeManager = variableTypeManager;
    }

//    public UaObjectNode createObject(
//        NodeId typeId,
//        NodeId nodeId,
//        boolean includeOptionalNodes) throws UaException {
//
//        UaNode node = createNode(nodeId, typeId, includeOptionalNodes);
//
//        if (node instanceof UaObjectNode) {
//            return (UaObjectNode) node;
//        } else {
//            return null; // TODO
//        }
//    }
//
//    public UaVariableNode createVariable(
//        NodeId typeId,
//        NodeId nodeId,
//        boolean includeOptionalNodes) throws UaException {
//
//        UaNode node = createNode(nodeId, typeId, includeOptionalNodes);
//
//        if (node instanceof UaVariableNode) {
//            return (UaVariableNode) node;
//        } else {
//            return null; // TODO
//        }
//    }

    public UaNode createNode(
        NodeId rootNodeId,
        NodeId typeDefinitionId,
        boolean includeOptionalNodes) throws UaException {

        UaNodeManager nodeManager = context.getNodeManager();

        if (!nodeManager.containsNode(typeDefinitionId)) {
            throw new UaException(
                StatusCodes.Bad_NodeIdUnknown,
                "unknown type definition: " + typeDefinitionId);
        }

        InstanceDeclarationHierarchy instanceDeclarationHierarchy =
            InstanceDeclarationHierarchy.create(nodeManager, typeDefinitionId, includeOptionalNodes);

        NodeTable nodeTable =
            instanceDeclarationHierarchy.getNodeTable();

        ReferenceTable referenceTable =
            instanceDeclarationHierarchy.getReferenceTable();

        Map<BrowsePath, UaNode> nodes = new HashMap<>();

        for (Map.Entry<BrowsePath, NodeId> entry : nodeTable.nodes.entrySet()) {
            BrowsePath browsePath = entry.getKey();
            NodeId nodeId = entry.getValue();

            UaNode node = nodeManager.get(nodeId);

            if (browsePath.parent == null) {
                // Root Node of hierarchy will be the ObjectType or VariableType to be instantiated

                if (node instanceof ObjectTypeNode) {
                    UaNode instance = instanceFromTypeDefinition(rootNodeId, (ObjectTypeNode) node);

                    nodes.put(browsePath, instance);
                } else if (node instanceof VariableTypeNode) {
                    UaNode instance = instanceFromTypeDefinition(rootNodeId, (VariableTypeNode) node);

                    nodes.put(browsePath, instance);
                } else {
                    throw new UaException(StatusCodes.Bad_InternalError);
                }
            } else {
                // Non-root Nodes are all instance declarations
                // TODO nodeId + ...
                NodeId instanceNodeId = new NodeId(
                    rootNodeId.getNamespaceIndex(),
                    UUID.randomUUID()
                );

                if (node instanceof MethodNode) {
                    // TODO Make a copy and set the same MethodInvocationHandler the declaration has?

                } else if (node instanceof ObjectNode) {
                    ObjectNode declaration = (ObjectNode) node;

                    ExpandedNodeId targetNodeId = getTypeDefinition(referenceTable, browsePath);

                    UaNode typeDefinitionNode = nodeManager.get(targetNodeId);

                    if (typeDefinitionNode instanceof ObjectTypeNode) {
                        UaObjectNode instance = instanceFromTypeDefinition(
                            instanceNodeId, (ObjectTypeNode) typeDefinitionNode);

                        instance.setBrowseName(declaration.getBrowseName());
                        instance.setDisplayName(declaration.getDisplayName());
                        instance.setDescription(declaration.getDescription());
                        instance.setWriteMask(declaration.getWriteMask());
                        instance.setUserWriteMask(declaration.getUserWriteMask());
                        instance.setEventNotifier(declaration.getEventNotifier());

                        nodes.put(browsePath, instance);
                    } else {
                        throw new UaException(
                            StatusCodes.Bad_InternalError,
                            "expected type definition for " + targetNodeId);
                    }
                } else if (node instanceof VariableNode) {
                    VariableNode declaration = (VariableNode) node;

                    ExpandedNodeId targetNodeId = getTypeDefinition(referenceTable, browsePath);

                    UaNode typeDefinitionNode = nodeManager.get(targetNodeId);

                    if (typeDefinitionNode instanceof VariableTypeNode) {
                        UaVariableNode instance = instanceFromTypeDefinition(
                            instanceNodeId, (VariableTypeNode) typeDefinitionNode);

                        instance.setBrowseName(declaration.getBrowseName());
                        instance.setDisplayName(declaration.getDisplayName());
                        instance.setDescription(declaration.getDescription());
                        instance.setWriteMask(declaration.getWriteMask());
                        instance.setUserWriteMask(declaration.getUserWriteMask());
                        instance.setValue(declaration.getValue());
                        instance.setDataType(declaration.getDataType());
                        instance.setValueRank(declaration.getValueRank());
                        instance.setArrayDimensions(declaration.getArrayDimensions());

                        nodes.put(browsePath, instance);
                    } else {
                        throw new UaException(
                            StatusCodes.Bad_InternalError,
                            "expected type definition for " + targetNodeId);
                    }
                } else {
                    throw new UaException(
                        StatusCodes.Bad_InternalError,
                        "not an instance declaration: " + node);
                }
            }
        }

        nodes.forEach((browsePath, node) -> {
            List<Tuple3<BrowsePath, NodeId, ReferenceTable.Target>> references =
                referenceTable.getReferences(browsePath);

            references.forEach(t -> {
                NodeId referenceTypeId = t.v2;
                ReferenceTable.Target target = t.v3;

                if (!Identifiers.HasModellingRule.equals(referenceTypeId)) {
                    if (target.targetNodeId != null) {
                        NodeClass targetNodeClass = nodeManager
                            .getNode(target.targetNodeId)
                            .map(UaNode::getNodeClass)
                            .orElse(NodeClass.Unspecified);

                        node.addReference(new Reference(
                            node.getNodeId(),
                            referenceTypeId,
                            target.targetNodeId,
                            targetNodeClass,
                            true
                        ));
                    } else {
                        BrowsePath targetPath = target.targetPath;

                        UaNode targetNode = nodes.get(targetPath);

                        if (targetNode != null) {
                            node.addReference(new Reference(
                                node.getNodeId(),
                                referenceTypeId,
                                targetNode.getNodeId().expanded(),
                                targetNode.getNodeClass(),
                                true
                            ));
                        }
                    }
                }
            });

            nodeManager.addNode(node);
        });

        return nodeManager.get(rootNodeId);
    }

    private static ExpandedNodeId getTypeDefinition(ReferenceTable referenceTable, BrowsePath browsePath) {
        return referenceTable
            .getReferences(browsePath)
            .stream()
            .filter(t -> t.v2.equals(Identifiers.HasTypeDefinition))
            .map(t -> t.v3.targetNodeId)
            .findFirst()
            .orElse(ExpandedNodeId.NULL_VALUE);
    }

    private UaObjectNode instanceFromTypeDefinition(
        NodeId nodeId,
        ObjectTypeNode typeDefinitionNode) throws UaException {

        NodeId typeDefinitionId = typeDefinitionNode.getNodeId();

        ObjectTypeManager.ObjectNodeConstructor ctor = objectTypeManager
            .getNodeFactory(typeDefinitionId)
            .orElseThrow(() -> new UaException(
                StatusCodes.Bad_TypeDefinitionInvalid,
                "no NodeFactory for type definition: " + typeDefinitionId));

        return ctor.apply(
            context,
            nodeId,
            typeDefinitionNode.getBrowseName(),
            typeDefinitionNode.getDisplayName(),
            typeDefinitionNode.getDescription(),
            typeDefinitionNode.getWriteMask(),
            typeDefinitionNode.getUserWriteMask()
        );
    }

    private UaVariableNode instanceFromTypeDefinition(
        NodeId nodeId,
        VariableTypeNode typeDefinitionNode) throws UaException {

        NodeId typeDefinitionId = typeDefinitionNode.getNodeId();

        VariableTypeManager.VariableNodeConstructor ctor = variableTypeManager
            .getNodeFactory(typeDefinitionId)
            .orElseThrow(() -> new UaException(
                StatusCodes.Bad_TypeDefinitionInvalid,
                "no NodeFactory for type definition: " + typeDefinitionId));

        return ctor.apply(
            context,
            nodeId,
            typeDefinitionNode.getBrowseName(),
            typeDefinitionNode.getDisplayName(),
            typeDefinitionNode.getDescription(),
            typeDefinitionNode.getWriteMask(),
            typeDefinitionNode.getUserWriteMask()
        );
    }

}

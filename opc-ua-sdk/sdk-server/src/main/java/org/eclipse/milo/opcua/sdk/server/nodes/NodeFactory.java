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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.util.StreamUtil;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

@Deprecated
public class NodeFactory {

    private final OpcUaServer server;
    private final NodeManager<UaNode> nodeManager;

    private final ObjectTypeManager objectTypeManager;
    private final VariableTypeManager variableTypeManager;

    public NodeFactory(
        UaNodeContext context,
        ObjectTypeManager objectTypeManager,
        VariableTypeManager variableTypeManager) {

        this.objectTypeManager = objectTypeManager;
        this.variableTypeManager = variableTypeManager;

        server = context.getServer();
        nodeManager = context.getNodeManager();
    }

    public UaObjectNode createObject(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId) {

        UaObjectNode objectNode = (UaObjectNode) createNode(nodeId, typeDefinitionId);

        objectNode.setBrowseName(browseName);
        objectNode.setDisplayName(displayName);

        return objectNode;
    }

    public UaVariableNode createVariable(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId) {

        UaVariableNode variableNode = (UaVariableNode) createNode(nodeId, typeDefinitionId);

        variableNode.setBrowseName(browseName);
        variableNode.setDisplayName(displayName);

        return variableNode;
    }

    private UaNode createNode(NodeId nodeId,
                              NodeId typeDefinitionId) throws UaRuntimeException {

        UaNode typeDefinitionNode = nodeManager.getNode(typeDefinitionId)
            .orElseThrow(() ->
                new UaRuntimeException(
                    StatusCodes.Bad_NodeIdUnknown,
                    "unknown type definition: " + typeDefinitionId));

        UaNode node;

        if (typeDefinitionNode instanceof VariableTypeNode) {
            node = instanceFromTypeDefinition(nodeId, (UaVariableTypeNode) typeDefinitionNode);
            nodeManager.addNode(node);
        } else if (typeDefinitionNode instanceof ObjectTypeNode) {
            node = instanceFromTypeDefinition(nodeId, (UaObjectTypeNode) typeDefinitionNode);
            nodeManager.addNode(node);
        } else {
            throw new UaRuntimeException(StatusCodes.Bad_UnexpectedError, "typeDefinitionNode: " + typeDefinitionNode);
        }

        List<UaVariableNode> propertyDeclarations = typeDefinitionNode.getReferences().stream()
            .filter(Reference.HAS_PROPERTY_PREDICATE)
            .distinct()
            .map(r -> nodeManager.getNode(r.getTargetNodeId()))
            .flatMap(StreamUtil::opt2stream)
            .map(UaVariableNode.class::cast)
            .filter(vn ->
                vn.getReferences().stream().anyMatch(r ->
                    Identifiers.HasModellingRule.equals(r.getReferenceTypeId()) &&
                        Identifiers.ModellingRule_Mandatory.expanded().equals(r.getTargetNodeId())))
            .collect(Collectors.toList());

        for (UaVariableNode declaration : propertyDeclarations) {
            UaVariableTypeNode typeDefinition = (UaVariableTypeNode) declaration.getTypeDefinitionNode();

            NodeId instanceId = createNodeId(nodeId, declaration.getBrowseName().getName());

            UaVariableNode instance = (UaVariableNode) createNode(instanceId, typeDefinition.getNodeId());
            instance.setBrowseName(declaration.getBrowseName());
            instance.setDisplayName(declaration.getDisplayName());
            instance.setDescription(declaration.getDescription());
            instance.setWriteMask(declaration.getWriteMask());
            instance.setUserWriteMask(declaration.getUserWriteMask());
            instance.setValue(declaration.getValue());
            instance.setDataType(declaration.getDataType());
            instance.setValueRank(declaration.getValueRank());
            instance.setArrayDimensions(declaration.getArrayDimensions());

            node.addProperty(instance);
            nodeManager.addNode(instance);
        }

        List<UaVariableNode> variableComponents = typeDefinitionNode.getReferences().stream()
            .filter(Reference.HAS_COMPONENT_PREDICATE)
            .map(r -> nodeManager.getNode(r.getTargetNodeId()))
            .flatMap(StreamUtil::opt2stream)
            .filter(n -> n instanceof UaVariableNode)
            .map(UaVariableNode.class::cast)
            .collect(Collectors.toList());

        for (UaVariableNode declaration : variableComponents) {
            boolean placeholder = declaration.getReferences().stream()
                .anyMatch(r -> r.isForward() &&
                    r.getReferenceTypeId().equals(Identifiers.HasModellingRule) &&
                    (Identifiers.ModellingRule_OptionalPlaceholder.expanded().equals(r.getTargetNodeId()) ||
                        Identifiers.ModellingRule_MandatoryPlaceholder.expanded().equals(r.getTargetNodeId()) ||
                        Identifiers.ModellingRule_ExposesItsArray.expanded().equals(r.getTargetNodeId()))
                );

            if (placeholder) continue;

            UaVariableTypeNode typeDefinition = (UaVariableTypeNode) declaration.getTypeDefinitionNode();

            NodeId instanceId = createNodeId(nodeId, declaration.getBrowseName().getName());

            UaVariableNode instance = (UaVariableNode) createNode(instanceId, typeDefinition.getNodeId());
            instance.setBrowseName(declaration.getBrowseName());
            instance.setDisplayName(declaration.getDisplayName());
            instance.setDescription(declaration.getDescription());
            instance.setWriteMask(declaration.getWriteMask());
            instance.setUserWriteMask(declaration.getUserWriteMask());
            instance.setValue(declaration.getValue());
            instance.setDataType(declaration.getDataType());
            instance.setValueRank(declaration.getValueRank());
            instance.setArrayDimensions(declaration.getArrayDimensions());

            addComponent(node, instance);
            nodeManager.addNode(instance);
        }

        if (node instanceof ObjectNode) {
            List<UaObjectNode> objectComponents = typeDefinitionNode.getReferences().stream()
                .filter(Reference.HAS_COMPONENT_PREDICATE)
                .map(r -> nodeManager.getNode(r.getTargetNodeId()))
                .flatMap(StreamUtil::opt2stream)
                .filter(n -> n instanceof UaObjectNode)
                .map(UaObjectNode.class::cast)
                .collect(Collectors.toList());

            for (UaObjectNode declaration : objectComponents) {
                boolean placeholder = declaration.getReferences().stream()
                    .anyMatch(r -> r.isForward() &&
                        r.getReferenceTypeId().equals(Identifiers.HasModellingRule) &&
                        (Identifiers.ModellingRule_OptionalPlaceholder.expanded().equals(r.getTargetNodeId()) ||
                            Identifiers.ModellingRule_MandatoryPlaceholder.expanded().equals(r.getTargetNodeId()) ||
                            Identifiers.ModellingRule_ExposesItsArray.expanded().equals(r.getTargetNodeId()))
                    );

                if (placeholder) continue;

                UaObjectTypeNode typeDefinition = (UaObjectTypeNode) declaration.getTypeDefinitionNode();

                NodeId instanceId = createNodeId(nodeId, declaration.getBrowseName().getName());

                UaObjectNode instance = (UaObjectNode) createNode(instanceId, typeDefinition.getNodeId());
                instance.setBrowseName(declaration.getBrowseName());
                instance.setDisplayName(declaration.getDisplayName());
                instance.setDescription(declaration.getDescription());
                instance.setWriteMask(declaration.getWriteMask());
                instance.setUserWriteMask(declaration.getUserWriteMask());
                instance.setEventNotifier(declaration.getEventNotifier());

                addComponent(node, instance);
                nodeManager.addNode(instance);
            }
        }

        return node;
    }

    private UaObjectNode instanceFromTypeDefinition(NodeId nodeId, ObjectTypeNode typeDefinitionNode) {
        NodeId typeDefinitionId = typeDefinitionNode.getNodeId();

        ObjectTypeManager.ObjectNodeConstructor ctor = objectTypeManager
            .getNodeFactory(typeDefinitionId)
            .orElseThrow(() -> new UaRuntimeException(
                StatusCodes.Bad_TypeDefinitionInvalid,
                "no NodeFactory for type definition: " + typeDefinitionId)
            );

        UaObjectNode objectNode = ctor.apply(
            server,
            nodeId,
            typeDefinitionNode.getBrowseName(),
            typeDefinitionNode.getDisplayName(),
            typeDefinitionNode.getDescription(),
            typeDefinitionNode.getWriteMask(),
            typeDefinitionNode.getUserWriteMask()
        );

        objectNode.addReference(new Reference(
            objectNode.getNodeId(),
            Identifiers.HasTypeDefinition,
            typeDefinitionId.expanded(),
            true
        ));

        return objectNode;
    }

    private UaVariableNode instanceFromTypeDefinition(NodeId nodeId, VariableTypeNode typeDefinitionNode) {
        NodeId typeDefinitionId = typeDefinitionNode.getNodeId();

        VariableTypeManager.VariableNodeConstructor ctor = variableTypeManager
            .getNodeFactory(typeDefinitionId)
            .orElseThrow(() -> new UaRuntimeException(
                StatusCodes.Bad_TypeDefinitionInvalid,
                "no NodeFactory for type definition: " + typeDefinitionId)
            );

        UaVariableNode variableNode = ctor.apply(
            server,
            nodeId,
            typeDefinitionNode.getBrowseName(),
            typeDefinitionNode.getDisplayName(),
            typeDefinitionNode.getDescription(),
            typeDefinitionNode.getWriteMask(),
            typeDefinitionNode.getUserWriteMask()
        );

        variableNode.setValue(typeDefinitionNode.getValue());
        variableNode.setDataType(typeDefinitionNode.getDataType());
        variableNode.setValueRank(typeDefinitionNode.getValueRank());
        variableNode.setArrayDimensions(typeDefinitionNode.getArrayDimensions());

        variableNode.addReference(new Reference(
            variableNode.getNodeId(),
            Identifiers.HasTypeDefinition,
            typeDefinitionId.expanded(),
            true
        ));

        return variableNode;
    }

    private void addComponent(UaNode sourceNode, UaNode targetNode) {
        sourceNode.addReference(new Reference(
            sourceNode.getNodeId(),
            Identifiers.HasComponent,
            targetNode.getNodeId().expanded(),
            true
        ));

        targetNode.addReference(new Reference(
            targetNode.getNodeId(),
            Identifiers.HasComponent,
            sourceNode.getNodeId().expanded(),
            false
        ));
    }

    private NodeId createNodeId(NodeId nodeId, String toAppend) {
        return new NodeId(nodeId.getNamespaceIndex(), nodeId.getIdentifier() + "." + toAppend);
    }

}

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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.util.StreamUtil;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class NodeFactory {

    private final ServerNodeMap nodeMap;
    private final ObjectTypeManager objectTypeManager;
    private final VariableTypeManager variableTypeManager;

    public NodeFactory(
        ServerNodeMap nodeMap,
        ObjectTypeManager objectTypeManager,
        VariableTypeManager variableTypeManager) {

        this.nodeMap = nodeMap;
        this.objectTypeManager = objectTypeManager;
        this.variableTypeManager = variableTypeManager;
    }

    public UaObjectNode createObject(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId) {

        UaObjectNode objectNode = createObject(nodeId, typeDefinitionId);

        objectNode.setBrowseName(browseName);
        objectNode.setDisplayName(displayName);

        return objectNode;
    }

    public <T extends ObjectNode> T createObject(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId,
        Class<T> clazz) {

        T objectNode = createNode(nodeId, typeDefinitionId, clazz);

        objectNode.setBrowseName(browseName);
        objectNode.setDisplayName(displayName);

        return objectNode;
    }

    public UaVariableNode createVariable(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId) {

        UaVariableNode variableNode = createVariable(nodeId, typeDefinitionId);

        variableNode.setBrowseName(browseName);
        variableNode.setDisplayName(displayName);

        return variableNode;
    }

    public <T extends VariableNode> T createVariable(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId,
        Class<T> clazz) {

        T variableNode = createNode(nodeId, typeDefinitionId, clazz);

        variableNode.setBrowseName(browseName);
        variableNode.setDisplayName(displayName);

        return variableNode;
    }

    private UaObjectNode createObject(NodeId nodeId, NodeId typeDefinitionId) throws UaRuntimeException {
        return createNode(nodeId, typeDefinitionId, UaObjectNode.class);
    }

    private UaVariableNode createVariable(NodeId nodeId, NodeId typeDefinitionId) throws UaRuntimeException {
        return createNode(nodeId, typeDefinitionId, UaVariableNode.class);
    }

    private <T extends Node> T createNode(NodeId nodeId,
                                          NodeId typeDefinitionId,
                                          Class<T> clazz) throws UaRuntimeException {

        UaNode typeDefinitionNode = (UaNode) nodeMap.getNode(typeDefinitionId)
            .orElseThrow(() ->
                new UaRuntimeException(
                    StatusCodes.Bad_NodeIdUnknown,
                    "unknown type definition: " + typeDefinitionId));

        UaNode node;

        if (typeDefinitionNode instanceof VariableTypeNode) {
            node = instanceFromTypeDefinition(nodeId, (UaVariableTypeNode) typeDefinitionNode);
            nodeMap.addNode(node);
        } else if (typeDefinitionNode instanceof ObjectTypeNode) {
            node = instanceFromTypeDefinition(nodeId, (UaObjectTypeNode) typeDefinitionNode);
            nodeMap.addNode(node);
        } else {
            throw new UaRuntimeException(StatusCodes.Bad_UnexpectedError, "typeDefinitionNode: " + typeDefinitionNode);
        }

        List<UaVariableNode> propertyDeclarations = typeDefinitionNode.getReferences().stream()
            .filter(Reference.HAS_PROPERTY_PREDICATE)
            .distinct()
            .map(r -> nodeMap.getNode(r.getTargetNodeId()))
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

            UaVariableNode instance = createVariable(instanceId, typeDefinition.getNodeId());
            instance.setBrowseName(declaration.getBrowseName());
            instance.setDisplayName(declaration.getDisplayName());
            instance.setDescription(declaration.getDescription());
            instance.setWriteMask(declaration.getWriteMask());
            instance.setUserWriteMask(declaration.getUserWriteMask());
            instance.setValue(declaration.getValue());
            instance.setDataType(declaration.getDataType());
            instance.setValueRank(declaration.getValueRank());
            instance.setArrayDimensions(declaration.getArrayDimensions());
            instance.setAccessLevel(declaration.getAccessLevel());
            instance.setUserAccessLevel(declaration.getUserAccessLevel());

            node.addProperty(instance);
            nodeMap.addNode(instance);
        }

        List<UaVariableNode> variableComponents = typeDefinitionNode.getReferences().stream()
            .filter(Reference.HAS_COMPONENT_PREDICATE)
            .filter(reference -> reference.getTargetNodeClass() == NodeClass.Variable)
            .map(r -> nodeMap.getNode(r.getTargetNodeId()))
            .flatMap(StreamUtil::opt2stream)
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

            UaVariableNode instance = createVariable(instanceId, typeDefinition.getNodeId());
            instance.setBrowseName(declaration.getBrowseName());
            instance.setDisplayName(declaration.getDisplayName());
            instance.setDescription(declaration.getDescription());
            instance.setWriteMask(declaration.getWriteMask());
            instance.setUserWriteMask(declaration.getUserWriteMask());
            instance.setValue(declaration.getValue());
            instance.setDataType(declaration.getDataType());
            instance.setValueRank(declaration.getValueRank());
            instance.setArrayDimensions(declaration.getArrayDimensions());
            instance.setAccessLevel(declaration.getAccessLevel());
            instance.setUserAccessLevel(declaration.getUserAccessLevel());

            addComponent(node, instance);
            nodeMap.addNode(instance);
        }

        if (node instanceof ObjectNode) {
            List<UaObjectNode> objectComponents = typeDefinitionNode.getReferences().stream()
                .filter(Reference.HAS_COMPONENT_PREDICATE)
                .filter(reference -> reference.getTargetNodeClass() == NodeClass.Object)
                .map(r -> nodeMap.getNode(r.getTargetNodeId()))
                .flatMap(StreamUtil::opt2stream)
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

                UaObjectNode instance = createObject(instanceId, typeDefinition.getNodeId());
                instance.setBrowseName(declaration.getBrowseName());
                instance.setDisplayName(declaration.getDisplayName());
                instance.setDescription(declaration.getDescription());
                instance.setWriteMask(declaration.getWriteMask());
                instance.setUserWriteMask(declaration.getUserWriteMask());
                instance.setEventNotifier(declaration.getEventNotifier());

                addComponent(node, instance);
                nodeMap.addNode(instance);
            }
        }

        return clazz.cast(node);
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
            nodeMap,
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
            NodeClass.ObjectType,
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
            nodeMap,
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
            NodeClass.VariableType,
            true
        ));

        return variableNode;
    }

    private void addComponent(UaNode sourceNode, UaNode targetNode) {
        sourceNode.addReference(new Reference(
            sourceNode.getNodeId(),
            Identifiers.HasComponent,
            targetNode.getNodeId().expanded(),
            targetNode.getNodeClass(),
            true
        ));

        targetNode.addReference(new Reference(
            targetNode.getNodeId(),
            Identifiers.HasComponent,
            sourceNode.getNodeId().expanded(),
            sourceNode.getNodeClass(),
            false
        ));
    }

    private NodeId createNodeId(NodeId nodeId, String toAppend) {
        return new NodeId(nodeId.getNamespaceIndex(), nodeId.getIdentifier() + "." + toAppend);
    }

}

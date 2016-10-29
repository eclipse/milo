/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.util.StreamUtil;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.reflections.Reflections;

public class VariableNodeFactory {

    private static final Reflections NODE_REFLECTIONS =
        new Reflections("org.eclipse.milo.opcua.sdk.server.model");

    private final UaNodeManager nodeManager;

    public VariableNodeFactory(UaNodeManager nodeManager) {
        this.nodeManager = nodeManager;
    }

    public UaVariableNode create(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId) {

        UaVariableNode variableNode = create(nodeId, typeDefinitionId);

        variableNode.setBrowseName(browseName);
        variableNode.setDisplayName(displayName);

        return variableNode;
    }

    public <T extends VariableNode> T create(
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        NodeId typeDefinitionId,
        Class<T> clazz) {

        T variableNode = create(nodeId, typeDefinitionId, clazz);

        variableNode.setBrowseName(browseName);
        variableNode.setDisplayName(displayName);

        return variableNode;
    }

    public UaVariableNode create(NodeId nodeId, NodeId typeDefinitionId) throws UaRuntimeException {
        return create(nodeId, typeDefinitionId, UaVariableNode.class);
    }

    public <T extends VariableNode> T create(NodeId nodeId,
                                             NodeId typeDefinitionId,
                                             Class<T> clazz) throws UaRuntimeException {

        UaVariableTypeNode typeDefinitionNode = (UaVariableTypeNode) nodeManager.getNode(typeDefinitionId)
            .orElseThrow(() ->
                new UaRuntimeException(
                    StatusCodes.Bad_NodeIdUnknown,
                    "unknown type definition: " + typeDefinitionId));

        UaVariableNode node = instanceFromTypeDefinition(nodeId, typeDefinitionNode);
        nodeManager.addNode(node);

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

            UaVariableNode instance = create(instanceId, typeDefinition.getNodeId());
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

        List<UaVariableNode> variableDeclarations = typeDefinitionNode.getReferences().stream()
            .filter(Reference.HAS_COMPONENT_PREDICATE)
            .map(r -> nodeManager.getNode(r.getTargetNodeId()))
            .flatMap(StreamUtil::opt2stream)
            .map(UaVariableNode.class::cast)
            .collect(Collectors.toList());

        for (UaVariableNode declaration : variableDeclarations) {
            UaVariableTypeNode typeDefinition = (UaVariableTypeNode) declaration.getTypeDefinitionNode();

            NodeId instanceId = createNodeId(nodeId, declaration.getBrowseName().getName());

            UaVariableNode instance = create(instanceId, typeDefinition.getNodeId());
            instance.setBrowseName(declaration.getBrowseName());
            instance.setDisplayName(declaration.getDisplayName());
            instance.setDescription(declaration.getDescription());
            instance.setWriteMask(declaration.getWriteMask());
            instance.setUserWriteMask(declaration.getUserWriteMask());
            instance.setValue(declaration.getValue());
            instance.setDataType(declaration.getDataType());
            instance.setValueRank(declaration.getValueRank());
            instance.setArrayDimensions(declaration.getArrayDimensions());

            node.addComponent(instance);
            nodeManager.addNode(instance);
        }

        return clazz.cast(node);
    }

    private UaVariableNode instanceFromTypeDefinition(NodeId nodeId, UaVariableTypeNode typeDefinitionNode) {
        QualifiedName browseName = typeDefinitionNode.getBrowseName();

        Set<Class<?>> classes = NODE_REFLECTIONS.getTypesAnnotatedWith(
            variableAnnotation(browseName.toParseableString()));

        Class<?> clazz = classes.stream().findFirst().orElseThrow(() ->
            new UaRuntimeException(
                StatusCodes.Bad_TypeDefinitionInvalid,
                "unknown variable type: " + browseName));

        try {
            Class[] uaVariableNodeCtorParams = {
                UaNodeManager.class,
                NodeId.class,
                VariableTypeNode.class
            };

            Constructor<?> ctor = clazz.getDeclaredConstructor(uaVariableNodeCtorParams);

            Object[] initArgs = {
                nodeManager,
                nodeId,
                typeDefinitionNode
            };

            UaVariableNode variableNode = (UaVariableNode) ctor.newInstance(initArgs);

            variableNode.addReference(new Reference(
                nodeId,
                Identifiers.HasTypeDefinition,
                new ExpandedNodeId(typeDefinitionNode.getNodeId()),
                NodeClass.VariableType,
                true
            ));

            return variableNode;
        } catch (Exception e) {
            throw new UaRuntimeException(e);
        }
    }

    private NodeId createNodeId(NodeId nodeId, String toAppend) {
        return new NodeId(nodeId.getNamespaceIndex(), nodeId.getIdentifier() + "." + toAppend);
    }

    private org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode variableAnnotation(final String typeName) {
        return new org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode() {
            @Override
            public String typeName() {
                return typeName;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode.class;
            }
        };
    }

}

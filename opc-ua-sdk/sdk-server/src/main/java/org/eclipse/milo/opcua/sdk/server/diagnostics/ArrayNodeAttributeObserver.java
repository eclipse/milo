/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeObserver;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

/**
 * An {@link AttributeObserver} that watches the value of a Node with an array value and adds/removes/updates
 * read-only child nodes for each element as necessary.
 */
class ArrayNodeAttributeObserver implements AttributeObserver {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LinkedList<UaVariableNode> elementNodes = new LinkedList<>();

    private final UaVariableNode arrayNode;
    private final Supplier<Object> valueGetter;
    private final NodeId elementNodeTypeDefinitionId;

    public ArrayNodeAttributeObserver(UaVariableNode arrayNode, NodeId elementNodeTypeDefinitionId) {
        this(arrayNode, () -> arrayNode.getValue().getValue().getValue(), elementNodeTypeDefinitionId);
    }

    public ArrayNodeAttributeObserver(
        UaVariableNode arrayNode,
        Supplier<Object> valueGetter,
        NodeId elementNodeTypeDefinitionId
    ) {

        this.arrayNode = arrayNode;
        this.valueGetter = valueGetter;
        this.elementNodeTypeDefinitionId = elementNodeTypeDefinitionId;
    }

    @Override
    public void attributeChanged(UaNode node, AttributeId attributeId, Object value) {
        if (attributeId == AttributeId.Value) {
            Object valueObject = ((DataValue) value).getValue().getValue();

            if (valueObject == null || !valueObject.getClass().isArray()) {
                return;
            }

            int length = Array.getLength(valueObject);

            if (elementNodes.size() < length) {
                for (int i = elementNodes.size(); i < length; i++) {
                    final int index = i;

                    String id = buildBrowseNamePath(node) + "[" + i + "]";
                    NodeId elementNodeId = arrayNode.getNodeId().withId(id);

                    NodeFactory nodeFactory = new NodeFactory(node.getNodeContext());

                    try {
                        UaVariableNode elementNode = (UaVariableNode) nodeFactory.createNode(
                            elementNodeId,
                            elementNodeTypeDefinitionId,
                            false
                        );

                        elementNode.setBrowseName(new QualifiedName(
                            arrayNode.getBrowseName().getNamespaceIndex(),
                            arrayNode.getBrowseName().getName() + "[" + i + "]"
                        ));
                        elementNode.setDisplayName(new LocalizedText(
                            arrayNode.getDisplayName().getLocale(),
                            arrayNode.getDisplayName().getText() + "[" + i + "]"
                        ));
                        elementNode.setArrayDimensions(null);
                        elementNode.setValueRank(ValueRanks.Scalar);
                        elementNode.setDataType(arrayNode.getDataType());
                        elementNode.setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)));
                        elementNode.setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)));

                        Object array = valueGetter.get();
                        Object elementValue = Array.get(array, index);
                        elementNode.setValue(new DataValue(new Variant(elementValue)));

                        elementNode.setAttributeDelegate(new AttributeDelegate() {
                            @Override
                            public DataValue getValue(AttributeContext context, VariableNode node) {
                                Object array = valueGetter.get();
                                Object elementValue = Array.get(array, index);

                                DataValue value = new DataValue(new Variant(elementValue));
                                
                                node.setValue(value);

                                return value;
                            }
                        });

                        elementNodes.add(elementNode);

                        arrayNode.addComponent(elementNode);
                        arrayNode.getNodeManager().addNode(elementNode);

                        NodeId elementDataType = elementNode.getDataType();

                        OpcUaServer server = elementNode.getNodeContext().getServer();

                        if (subtypeOf(server, elementDataType, Identifiers.Structure)) {
                            elementNode.addAttributeObserver(
                                new ComplexVariableNodeAttributeObserver(elementNode)
                            );
                        }
                    } catch (UaException e) {
                        logger.error("Error creating element Node for {}", arrayNode.getNodeId(), e);
                    }
                }
            } else if (elementNodes.size() > length) {
                while (elementNodes.size() > length) {
                    UaVariableNode elementNode = elementNodes.removeLast();

                    arrayNode.removeComponent(elementNode);
                    arrayNode.getNodeManager().removeNode(elementNode);
                }
            }

            for (int i = 0; i < length; i++) {
                Object o = Array.get(valueObject, i);

                elementNodes.get(i).setValue(new DataValue(new Variant(o)));
            }
        }
    }

    private static String buildBrowseNamePath(UaNode node) {
        return buildBrowseNamePath(node, new ArrayList<>());
    }

    private static String buildBrowseNamePath(UaNode node, List<String> browseNames) {
        if (node == null || node.getNodeId().equals(Identifiers.ObjectsFolder)) {
            Collections.reverse(browseNames);

            return String.join(".", browseNames);
        }

        browseNames.add(node.getBrowseName().toParseableString());

        Optional<Reference> referenceToParent = node.getReferences().stream()
            .filter(r -> r.isInverse() && r.subtypeOf(Identifiers.HierarchicalReferences))
            .findFirst();

        Optional<UaNode> parentNode = referenceToParent
            .flatMap(r ->
                node.getNodeContext()
                    .getServer()
                    .getAddressSpaceManager()
                    .getManagedNode(r.getTargetNodeId())
            );

        return buildBrowseNamePath(parentNode.orElse(null), browseNames);
    }

    /**
     * @return {@code true} if {@code dataTypeId} is a subtype of {@code potentialSuperTypeId}.
     */
    private static boolean subtypeOf(OpcUaServer server, NodeId dataTypeId, NodeId potentialSuperTypeId) {
        UaNode dataTypeNode = server.getAddressSpaceManager()
            .getManagedNode(dataTypeId)
            .orElse(null);

        if (dataTypeNode != null) {
            NodeId superTypeId = getSuperTypeId(server, dataTypeId);

            if (superTypeId != null) {
                return superTypeId.equals(potentialSuperTypeId) ||
                    subtypeOf(server, superTypeId, potentialSuperTypeId);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Nullable
    private static NodeId getSuperTypeId(OpcUaServer server, NodeId dataTypeId) {
        UaNode dataTypeNode = server.getAddressSpaceManager()
            .getManagedNode(dataTypeId)
            .orElse(null);

        if (dataTypeNode != null) {
            return dataTypeNode.getReferences()
                .stream()
                .filter(Reference.SUBTYPE_OF)
                .flatMap(r -> opt2stream(r.getTargetNodeId().local(server.getNamespaceTable())))
                .findFirst()
                .orElse(null);
        } else {
            return null;
        }
    }

}

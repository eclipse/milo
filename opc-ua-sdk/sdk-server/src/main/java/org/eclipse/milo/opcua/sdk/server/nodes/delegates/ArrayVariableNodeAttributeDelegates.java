/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public class ArrayVariableNodeAttributeDelegates {

    public static void install(UaVariableNode variableNode) {
        install(
            variableNode,
            (n) -> n.getValue().getValue().getValue(),
            (n, v) -> n.setValue(new DataValue(new Variant(v)))
        );
    }

    public static void install(
        UaVariableNode arrayNode,
        Function<VariableNode, Object> getter,
        BiConsumer<VariableNode, Object> setter
    ) {

        // 1. set array value on variableNode sets value at index on child nodes
        // 2. set value on child node updates array value in variableNode
        // 3. get array value on variableNode gets current array value
        // 4. get value on child node gets value at index from current array value

        LinkedList<UaVariableNode> elementNodes = new LinkedList<>();

        arrayNode.setAttributeDelegate(new AttributeDelegate() {
            @Override
            public DataValue getValue(AttributeContext context, VariableNode node) {
                return new DataValue(new Variant(getter.apply(node)));
            }

            @Override
            public void setValue(AttributeContext context, VariableNode node, DataValue value) {
                setter.accept(node, value);
            }
        });

        arrayNode.addAttributeObserver((node, attributeId, value) -> {
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

                        UaVariableNode.UaVariableNodeBuilder builder =
                            UaVariableNode.builder(arrayNode.getNodeContext());

//                        UaVariableNode elementNode = builder
//                            .setNodeId(arrayNode.getNodeId().withId(id))
//                            .setBrowseName(new QualifiedName(arrayNode.getBrowseName().getNamespaceIndex(), id))
//                            .setDisplayName(new LocalizedText(
//                                arrayNode.getDisplayName().getLocale(),
//                                arrayNode.getDisplayName().getText() + "[" + i + "]"
//                            ))
//                            .setDataType(Identifiers.Int32)
//                            .build();


                        UaVariableNode elementNode = new UaVariableNode(
                            arrayNode.getNodeContext(),
                            arrayNode.getNodeId().withId(id),
                            new QualifiedName(arrayNode.getBrowseName().getNamespaceIndex(), id),
                            new LocalizedText(
                                arrayNode.getDisplayName().getLocale(),
                                arrayNode.getDisplayName().getText() + "[" + i + "]"
                            ),
                            LocalizedText.NULL_VALUE,
                            arrayNode.getWriteMask(),
                            arrayNode.getUserWriteMask()
                        ) {

                            {
                                setArrayDimensions(null);
                                setValueRank(ValueRanks.Scalar);
                                setDataType(arrayNode.getDataType());

                                Object array = getter.apply(arrayNode);
                                Object elementValue = Array.get(array, index);
                                setValue(new DataValue(new Variant(elementValue)));
                            }

                            @Override
                            public synchronized DataValue getValue() {
                                Object array = getter.apply(arrayNode);
                                Object elementValue = Array.get(array, index);

                                return new DataValue(new Variant(elementValue));
                            }

                            @Override
                            public synchronized void setValue(DataValue value) {
                                Object array = getter.apply(arrayNode);
                                Object elementValue = value.getValue().getValue();

                                Array.set(array, index, elementValue);

                                setter.accept(arrayNode, array);
                            }
                        };

                        elementNodes.add(elementNode);

                        arrayNode.addComponent(elementNode);
                        arrayNode.getNodeManager().addNode(elementNode);
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
        });
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

}

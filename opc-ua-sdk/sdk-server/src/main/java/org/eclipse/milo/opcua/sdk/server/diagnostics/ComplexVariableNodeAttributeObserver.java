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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeObserver;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;

public class ComplexVariableNodeAttributeObserver implements AttributeObserver {

    private final AtomicBoolean initialized = new AtomicBoolean(false);

    private final SerializationContext serializationContext;

    private final UaVariableNode complexNode;
    private final Supplier<Object> valueGetter;

    public ComplexVariableNodeAttributeObserver(UaVariableNode complexNode) {
        this(complexNode, () -> {
            Object value = complexNode.getValue().getValue().getValue();

            if (value instanceof ExtensionObject) {
                SerializationContext serializationContext = complexNode.getNodeContext()
                    .getServer()
                    .getSerializationContext();

                return ((ExtensionObject) value).decode(serializationContext);
            } else {
                return value;
            }
        });
    }

    public ComplexVariableNodeAttributeObserver(UaVariableNode complexNode, Supplier<Object> valueGetter) {
        this.complexNode = complexNode;
        this.valueGetter = valueGetter;

        serializationContext = complexNode.getNodeContext().getServer().getSerializationContext();
    }

    @Override
    public void attributeChanged(UaNode node, AttributeId attributeId, Object value) {
        if (attributeId == AttributeId.Value) {
            Object valueObject = ((DataValue) value).getValue().getValue();

            if (!(valueObject instanceof ExtensionObject)) {
                return;
            }

            valueObject = ((ExtensionObject) valueObject).decode(serializationContext);

            if (initialized.compareAndSet(false, true)) {
                Map<String, Method> getters = new HashMap<>();

                for (Method m : valueObject.getClass().getMethods()) {
                    String methodName = m.getName();
                    if (methodName.startsWith("get")) {
                        getters.put(methodName.substring(3), m);
                    }
                }

                Set<String> componentNames = complexNode.getComponentNodes().stream()
                    .map(n -> n.getBrowseName().getName())
                    .collect(Collectors.toSet());

                Set<String> fieldNameIntersection = Sets.intersection(getters.keySet(), componentNames);

                complexNode.getComponentNodes().forEach(n -> {
                    final String name = n.getBrowseName().getName();

                    if (fieldNameIntersection.contains(name)) {
                        UaNode memberNode = (UaNode) n;

                        memberNode.setAttributeDelegate(new AttributeDelegate() {
                            @Override
                            public DataValue getValue(AttributeContext context, VariableNode node) {
                                final Object complexValue = valueGetter.get();
                                try {
                                    Method m = getters.get(name);
                                    Object o = m.invoke(complexValue);
                                    node.setValue(new DataValue(new Variant(o)));
                                    return node.getValue();
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    LoggerFactory.getLogger(ComplexVariableNodeAttributeObserver.class)
                                        .error("error getting value for field '{}' of {}", name, complexValue, e);
                                    return new DataValue(new StatusCode(StatusCodes.Bad_InternalError));
                                }
                            }
                        });

                        if (memberNode instanceof UaVariableNode) {
                            NodeId memberDataType = ((UaVariableNode) memberNode).getDataType();

                            OpcUaServer server = complexNode.getNodeContext().getServer();

                            if (subtypeOf(server, memberDataType, Identifiers.Structure)) {
                                memberNode.addAttributeObserver(
                                    new ComplexVariableNodeAttributeObserver((UaVariableNode) memberNode)
                                );
                            }
                        }
                    }
                });
            }
        }
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

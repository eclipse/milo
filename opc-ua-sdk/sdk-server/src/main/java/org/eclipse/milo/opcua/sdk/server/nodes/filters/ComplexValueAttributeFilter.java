/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.filters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;

public class ComplexValueAttributeFilter implements AttributeFilter {

    private final AtomicBoolean initialized = new AtomicBoolean(false);

    @Override
    public Object getAttribute(AttributeFilterContext ctx, AttributeId attributeId) {
        if (attributeId == AttributeId.Value) {
            DataValue value = (DataValue) ctx.getAttribute(attributeId);

            if (ctx.getNode() instanceof UaVariableNode &&
                initialized.compareAndSet(false, true)) {

                initializeStructMembers(
                    (UaVariableNode) ctx.getNode(),
                    value.getValue().getValue()
                );
            }

            return value;
        } else {
            return ctx.getAttribute(attributeId);
        }
    }

    @Override
    public void setAttribute(AttributeFilterContext ctx, AttributeId attributeId, Object value) {
        if (attributeId == AttributeId.Value) {
            if (ctx.getNode() instanceof UaVariableNode &&
                initialized.compareAndSet(false, true)) {

                initializeStructMembers(
                    (UaVariableNode) ctx.getNode(),
                    ((DataValue) value).getValue().getValue()
                );
            }

            ctx.setAttribute(attributeId, value);
        } else {
            ctx.setAttribute(attributeId, value);
        }
    }

    private void initializeStructMembers(UaVariableNode complexNode, Object structValue) {
        Map<String, Method> getters = new HashMap<>();

        for (Method m : structValue.getClass().getMethods()) {
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

                memberNode.getFilterChain().addLast(
                    AttributeFilters.getValue(ctx -> {
                        final Object complexValue = complexNode.getValue().getValue().getValue();

                        try {
                            Method m = getters.get(name);
                            Object o = m.invoke(complexValue);
                            return new DataValue(new Variant(o));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            LoggerFactory.getLogger(ComplexValueAttributeFilter.class)
                                .error("error getting value for field '{}' of {}", name, complexValue, e);
                            return new DataValue(new StatusCode(StatusCodes.Bad_InternalError));
                        }
                    })
                );

                if (memberNode instanceof UaVariableNode) {
                    NodeId memberDataType = ((UaVariableNode) memberNode).getDataType();

                    OpcUaServer server = complexNode.getNodeContext().getServer();

                    if (subtypeOf(server, memberDataType, Identifiers.Structure)) {
                        // Add this in front of of the value filter added above
                        memberNode.getFilterChain().addFirst(new ComplexValueAttributeFilter());
                    }
                }
            }
        });
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

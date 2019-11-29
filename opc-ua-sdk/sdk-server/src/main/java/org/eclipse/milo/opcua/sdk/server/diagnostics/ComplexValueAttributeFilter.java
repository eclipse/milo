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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilter;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext.SetAttributeContext;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;

class ComplexValueAttributeFilter implements AttributeFilter {

    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final Map<String, Method> getters = new HashMap<>();
    private final List<UaVariableNode> members = new ArrayList<>();

    @Override
    public void setAttribute(SetAttributeContext ctx, AttributeId attributeId, Object value) {
        if (attributeId == AttributeId.Value) {
            if (ctx.getNode() instanceof UaVariableNode) {
                Object o = ((DataValue) value).getValue().getValue();

                if (o instanceof ExtensionObject) {
                    o = decodeValue(ctx, (ExtensionObject) o);
                }

                synchronizeStructMembers((UaVariableNode) ctx.getNode(), o);
            }

            ctx.setAttribute(attributeId, value);
        } else {
            ctx.setAttribute(attributeId, value);
        }
    }

    private synchronized void synchronizeStructMembers(UaVariableNode complexNode, Object structValue) {
        if (initialized.compareAndSet(false, true)) {
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

            getters.keySet().removeIf(k -> !fieldNameIntersection.contains(k));

            // TODO calling distinct() is a work-around for whatever bug
            //  is causing duplicate HasComponent references on some nodes.
            complexNode.getComponentNodes().stream().distinct().forEach(node -> {
                if (node instanceof UaVariableNode) {
                    UaVariableNode memberNode = (UaVariableNode) node;
                    String memberName = node.getBrowseName().getName();

                    if (getters.containsKey(memberName)) {
                        members.add(memberNode);

                        NodeId memberDataType = memberNode.getDataType();

                        OpcUaServer server = complexNode.getNodeContext().getServer();

                        if (memberNode.getValueRank() == ValueRanks.OneDimension) {
                            memberNode.getFilterChain().addLast(new ArrayValueAttributeFilter());
                        } else if (subtypeOf(server, memberDataType, Identifiers.Structure)) {
                            memberNode.getFilterChain().addLast(new ComplexValueAttributeFilter());
                        }
                    }
                }
            });
        }

        members.forEach(memberNode -> {
            String memberName = memberNode.getBrowseName().getName();

            try {
                Method m = getters.get(memberName);
                Object o = m.invoke(structValue);

                if (o instanceof UaStructure) {
                    ExtensionObject xo = encodeValue(
                        complexNode.getNodeContext(),
                        (UaStructure) o
                    );

                    memberNode.setValue(new DataValue(new Variant(xo)));
                } else {
                    memberNode.setValue(new DataValue(new Variant(o)));
                }
            } catch (Throwable e) {
                LoggerFactory.getLogger(ComplexValueAttributeFilter.class)
                    .error("error getting value for field '{}' of {}", memberName, structValue, e);

                memberNode.setValue(new DataValue(new StatusCode(StatusCodes.Bad_InternalError)));
            }
        });
    }

    private static Object decodeValue(AttributeFilterContext ctx, ExtensionObject xo) {
        return xo.decode(ctx.getNode().getNodeContext().getServer().getSerializationContext());
    }

    private static ExtensionObject encodeValue(UaNodeContext ctx, UaStructure value) {
        return ExtensionObject.encode(ctx.getServer().getSerializationContext(), value);
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

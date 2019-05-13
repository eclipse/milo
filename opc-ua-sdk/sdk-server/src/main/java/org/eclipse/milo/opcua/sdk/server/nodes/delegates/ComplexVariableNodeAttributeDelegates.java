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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Structure;
import org.slf4j.LoggerFactory;

public class ComplexVariableNodeAttributeDelegates {

    private ComplexVariableNodeAttributeDelegates() {}

    /**
     * Install an AttributeDelegate on {@code variableNode} and any of its component Nodes that are part of modelling
     * the structure type represented by {@code valueClass}.
     * <p>
     * Each of the delegates will invoke {@code valueSupplier} every time the structure value is needed. The top-level
     * delegate will use the entire structure value. The component delegates will use the value returned by the
     * appropriate getter on the structure value.
     *
     * @param variableNode  a {@link UaVariableNode} with a structured datatype that also models that datatype in its
     *                      component nodes.
     * @param valueClass    the {@link Class} of the structured datatype.
     * @param valueSupplier a {@link Supplier} that gets the current structure value when invoked.
     * @param <T>           the structure type.
     */
    public static <T extends Structure> void install(
        UaVariableNode variableNode,
        Class<T> valueClass,
        Supplier<T> valueSupplier
    ) {

        variableNode.setAttributeDelegate(new AttributeDelegate() {
            @Override
            public DataValue getValue(AttributeContext context, VariableNode node) {
                T value = valueSupplier.get();

                ExtensionObject encodedValue = ExtensionObject.encode(
                    variableNode.getNodeContext().getServer().getSerializationContext(),
                    value
                );

                node.setValue(new DataValue(new Variant(encodedValue)));

                return node.getValue();
            }
        });

        Map<String, Method> getters = new HashMap<>();

        for (Method m : valueClass.getMethods()) {
            String methodName = m.getName();
            if (methodName.startsWith("get")) {
                getters.put(methodName.substring(3), m);
            }
        }

        Set<String> componentNames = variableNode.getComponentNodes().stream()
            .map(n -> n.getBrowseName().getName())
            .collect(Collectors.toSet());

        Set<String> fieldNameIntersection = Sets.intersection(getters.keySet(), componentNames);

        variableNode.getComponentNodes().forEach(n -> {
            final String name = n.getBrowseName().getName();

            if (fieldNameIntersection.contains(name)) {
                ((UaNode) n).setAttributeDelegate(new AttributeDelegate() {
                    @Override
                    public DataValue getValue(AttributeContext context, VariableNode node) {
                        final T value = valueSupplier.get();
                        try {
                            Method m = getters.get(name);
                            Object o = m.invoke(value);
                            node.setValue(new DataValue(new Variant(o)));
                            return node.getValue();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            LoggerFactory.getLogger(ComplexVariableNodeAttributeDelegates.class)
                                .error("error getting value for field '{}' of {}", name, value, e);
                            return new DataValue(new StatusCode(StatusCodes.Bad_InternalError));
                        }
                    }
                });
            }
        });
    }

}

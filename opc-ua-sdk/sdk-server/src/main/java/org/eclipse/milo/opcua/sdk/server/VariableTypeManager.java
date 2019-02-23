/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class VariableTypeManager {

    private final ConcurrentMap<NodeId, VariableTypeDefinition> typeDefinitions = Maps.newConcurrentMap();

    public void registerVariableType(
        NodeId typeDefinition,
        Class<? extends UaVariableNode> nodeClass,
        VariableNodeConstructor variableNodeConstructor) {

        typeDefinitions.put(typeDefinition, new VariableTypeDefinition(nodeClass, variableNodeConstructor));
    }

    public Optional<VariableNodeConstructor> getNodeFactory(NodeId typeDefinition) {
        VariableTypeDefinition def = typeDefinitions.get(typeDefinition);

        return Optional.ofNullable(def).map(d -> d.nodeFactory);
    }

    private static class VariableTypeDefinition {
        final Class<? extends UaVariableNode> nodeClass;
        final VariableNodeConstructor nodeFactory;

        private VariableTypeDefinition(
            Class<? extends UaVariableNode> nodeClass,
            VariableNodeConstructor nodeFactory) {

            this.nodeClass = nodeClass;
            this.nodeFactory = nodeFactory;
        }
    }

    @FunctionalInterface
    public interface VariableNodeConstructor {
        UaVariableNode apply(
            UaNodeContext context,
            NodeId nodeId,
            QualifiedName browseName,
            LocalizedText displayName,
            LocalizedText description,
            UInteger writeMask,
            UInteger userWriteMask);
    }

}

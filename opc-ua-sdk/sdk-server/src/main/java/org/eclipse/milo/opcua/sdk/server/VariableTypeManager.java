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

package org.eclipse.milo.opcua.sdk.server;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
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
            ServerNodeMap nodeMap,
            NodeId nodeId,
            QualifiedName browseName,
            LocalizedText displayName,
            LocalizedText description,
            UInteger writeMask,
            UInteger userWriteMask);
    }

}
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

package org.eclipse.milo.opcua.sdk.client;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class TypeRegistry {

    private final ConcurrentMap<NodeId, TypeDefinition> typeDefinitions = Maps.newConcurrentMap();

    public void registerType(
        NodeId typeDefinition,
        Class<? extends Node> typeClass,
        Class<? extends UaNode> nodeClass,
        NodeFactory nodeFactory) {

        typeDefinitions.put(typeDefinition, new TypeDefinition(typeClass, nodeClass, nodeFactory));
    }

    public Optional<NodeFactory> getNodeFactory(NodeId typeDefinition) {
        TypeDefinition def = typeDefinitions.get(typeDefinition);

        return Optional.ofNullable(def).map(d -> d.nodeFactory);
    }

    private static class TypeDefinition {
        final Class<? extends Node> typeClass;
        final Class<? extends UaNode> nodeClass;
        final NodeFactory nodeFactory;

        private TypeDefinition(
            Class<? extends Node> typeClass,
            Class<? extends UaNode> nodeClass,
            NodeFactory nodeFactory) {

            this.typeClass = typeClass;
            this.nodeClass = nodeClass;
            this.nodeFactory = nodeFactory;
        }
    }

    public interface NodeFactory {
        UaNode apply(OpcUaClient client, NodeId nodeId);
    }

}

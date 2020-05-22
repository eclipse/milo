/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaObjectNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class ObjectTypeManager {

    private final ConcurrentMap<NodeId, ObjectTypeDefinition> typeDefinitions = Maps.newConcurrentMap();

    public void registerObjectType(
        NodeId typeDefinition,
        Class<? extends UaObjectNode> nodeClass,
        ObjectNodeConstructor objectNodeConstructor
    ) {

        typeDefinitions.put(typeDefinition, new ObjectTypeDefinition(nodeClass, objectNodeConstructor));
    }

    public Optional<ObjectNodeConstructor> getNodeFactory(NodeId typeDefinition) {
        ObjectTypeDefinition def = typeDefinitions.get(typeDefinition);

        return Optional.ofNullable(def).map(d -> d.nodeFactory);
    }

    private static class ObjectTypeDefinition {
        final Class<? extends UaNode> nodeClass;
        final ObjectNodeConstructor nodeFactory;

        private ObjectTypeDefinition(
            Class<? extends UaNode> nodeClass,
            ObjectNodeConstructor nodeFactory) {

            this.nodeClass = nodeClass;
            this.nodeFactory = nodeFactory;
        }
    }

    @FunctionalInterface
    public interface ObjectNodeConstructor {

        UaObjectNode apply(
            OpcUaClient client,
            NodeId nodeId,
            NodeClass nodeClass,
            QualifiedName browseName,
            LocalizedText displayName,
            LocalizedText description,
            UInteger writeMask,
            UInteger userWriteMask,
            UByte eventNotifier
        );

    }

}

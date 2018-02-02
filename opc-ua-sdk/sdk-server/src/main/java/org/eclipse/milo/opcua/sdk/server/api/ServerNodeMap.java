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

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface ServerNodeMap extends ConcurrentMap<NodeId, ServerNode> {

    /**
     * Add a {@link UaNode} to this {@link ServerNodeMap}.
     * <p>
     * This method is shorthand for:
     * <pre>
     *     {@code nodeMap.put(node.getNodeId(), node);}
     * </pre>
     *
     * @param node the {@link UaNode} to add.
     */
    default void addNode(ServerNode node) {
        put(node.getNodeId(), node);
    }

    /**
     * Add a {@link Reference} to the {@link UaNode} indicated by {@link Reference#getSourceNodeId()}.
     *
     * @param reference the {@link Reference} to add.
     * @return {@code true} if the {@link UaNode} exists and the reference was added.
     */
    default boolean addReference(Reference reference) {
        return getNode(reference.getSourceNodeId()).map(node -> {
            node.addReference(reference);

            return true;
        }).orElse(false);
    }

    /**
     * Check if a {@link UaNode} exists in this {@link ServerNodeMap}.
     *
     * @param node the {@link UaNode} in question.
     * @return {@code true} if this {@link ServerNodeMap} contains the {@link ServerNode}.
     */
    default boolean containsNode(ServerNode node) {
        return containsNodeId(node.getNodeId());
    }

    /**
     * Check if a {@link ServerNode} identified by {@link NodeId} exists in this {@link ServerNodeMap}.
     *
     * @param nodeId the {@link NodeId} of the {@link UaNode} in question.
     * @return {@code true} if this {@link ServerNodeMap} contains the {@link ServerNode} identified by {@code nodeId}.
     */
    default boolean containsNodeId(NodeId nodeId) {
        return containsKey(nodeId);
    }

    /**
     * Get the {@link ServerNode} identified by the provided {@link NodeId}, if it exists.
     *
     * @param nodeId the {@link NodeId} of the {@link UaNode}.
     * @return an {@link Optional} containing the {@link UaNode}, if present.
     */
    default Optional<ServerNode> getNode(NodeId nodeId) {
        return Optional.ofNullable(get(nodeId));
    }

    /**
     * Get the {@link ServerNode} identified by the provided {@link ExpandedNodeId}, if it exists.
     *
     * @param nodeId the {@link ExpandedNodeId} of the {@link UaNode}.
     * @return an {@link Optional} containing the {@link UaNode}, if present.
     */
    default Optional<ServerNode> getNode(ExpandedNodeId nodeId) {
        return nodeId.local().flatMap(this::getNode);
    }

    /**
     * Remove the {@link ServerNode} identified by the provided {@link NodeId}, if it exists.
     *
     * @param nodeId the {@link NodeId} of the {@link UaNode}.
     * @return an {@link Optional} containing the {@link UaNode}, if removed.
     */
    default Optional<ServerNode> removeNode(NodeId nodeId) {
        return Optional.ofNullable(remove(nodeId));
    }

    /**
     * Get the server's {@link NamespaceTable}.
     * <p>
     * // TODO this has no business here, but ServerNodeMap is currently the only thing passed into UaNode.
     *
     * @return the server's {@link NamespaceTable}.
     */
    NamespaceTable getNamespaceTable();

}

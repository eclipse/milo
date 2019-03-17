/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface NodeManager<T extends Node> {

    /**
     * Return {@code true} if this {@link NodeManager} contains the Node identified by {@code nodeId}.
     *
     * @param nodeId the {@link NodeId}.
     * @return {@code true} if this {@link NodeManager} contains the Node identified by {@code nodeId}.
     */
    boolean containsNode(NodeId nodeId);

    /**
     * Add {@code node} to this {@link NodeManager}, replacing a previous Node identified by the same {@link NodeId}
     * if it exists.
     *
     * @param node the {@link Node} to add.
     * @return the previous Node identified by the {@link NodeId} of {@code node}, if it exists.
     */
    Optional<T> addNode(T node);

    /**
     * Get the Node identified by {@code nodeId} from this {@link NodeManager}, if it exists.
     *
     * @param nodeId the {@link NodeId} identifying the Node to get.
     * @return the Node identified by {@code nodeId} from this {@link NodeManager}, if it exists.
     */
    Optional<T> getNode(NodeId nodeId);

    /**
     * Remove the Node identified by {@code nodeId} from this {@link NodeManager}, if it exists.
     *
     * @param nodeId the {@link NodeId} identifying the Node to remove.
     * @return the Node removed from this {@link NodeManager}, if it exists.
     */
    Optional<T> removeNode(NodeId nodeId);

    /**
     * Add a {@link Reference} to this {@link NodeManager}.
     * <p>
     * An inverse Reference of {@code reference} will also be added.
     *
     * @param reference the {@link Reference} to add.
     */
    void addReference(Reference reference);

    /**
     * Remove a {@link Reference} from this {@link NodeManager}.
     * <p>
     * The inverse Reference of {@code reference} will also be removed.
     *
     * @param reference the {@link Reference} to remove.
     */
    void removeReference(Reference reference);

    /**
     * Get all {@link Reference}s that have {@code nodeId} as their source {@link NodeId}.
     *
     * @param nodeId the source {@link NodeId}.
     * @return all {@link Reference}s that have {@code nodeId} as their source {@link NodeId}.
     */
    List<Reference> getReferences(NodeId nodeId);

    /**
     * Get all {@link Reference}s that have {@code nodeId} as their source {@link NodeId}, filtered by {@code filter}.
     *
     * @param nodeId the source {@link NodeId}.
     * @param filter a {@link Predicate} to filter {@link Reference}s.
     * @return all {@link Reference}s that have {@code nodeId} as their source {@link NodeId}, filtered by
     * {@code filter}.
     */
    List<Reference> getReferences(NodeId nodeId, Predicate<Reference> filter);

    default boolean containsNode(Node node) {
        return containsNode(node.getNodeId());
    }

    default boolean containsNode(ExpandedNodeId nodeId) {
        return nodeId.local().map(this::containsNode).orElse(false);
    }

    @Nullable
    default T get(NodeId nodeId) {
        return getNode(nodeId).orElse(null);
    }

    @Nullable
    default T get(ExpandedNodeId nodeId) {
        return getNode(nodeId).orElse(null);
    }

    default Optional<T> getNode(ExpandedNodeId nodeId) {
        return nodeId.local().flatMap(this::getNode);
    }

    default Optional<T> removeNode(T node) {
        return removeNode(node.getNodeId());
    }

    default Optional<T> removeNode(ExpandedNodeId nodeId) {
        return nodeId.local().flatMap(this::removeNode);
    }

}

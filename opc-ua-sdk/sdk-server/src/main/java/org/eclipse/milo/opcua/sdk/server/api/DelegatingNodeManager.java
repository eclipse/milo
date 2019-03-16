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

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * A {@link NodeManager} that delegates calls to a NodeManager determined by the subclass.
 *
 * @param <T> the type of {@link Node} being managed.
 */
public abstract class DelegatingNodeManager<T extends Node> implements NodeManager<T> {

    /**
     * Get the delegate NodeManager that should handle the call for {@code nodeId}.
     *
     * @param nodeId the {@link NodeId}.
     * @return the NodeManager to delegate to.
     */
    protected abstract NodeManager<T> getNodeManager(NodeId nodeId);

    @Override
    public boolean containsNode(NodeId nodeId) {
        return getNodeManager(nodeId).containsNode(nodeId);
    }

    @Override
    public Optional<T> addNode(T node) {
        return getNodeManager(node.getNodeId()).addNode(node);
    }

    @Override
    public Optional<T> getNode(NodeId nodeId) {
        return getNodeManager(nodeId).getNode(nodeId);
    }

    @Override
    public Optional<T> getNode(ExpandedNodeId nodeId) {
        return nodeId.local().flatMap(id -> getNodeManager(id).getNode(id));
    }

    @Override
    public Optional<T> removeNode(NodeId nodeId) {
        return getNodeManager(nodeId).removeNode(nodeId);
    }

    @Override
    public Optional<T> removeNode(ExpandedNodeId nodeId) {
        return nodeId.local().flatMap(id -> getNodeManager(id).removeNode(id));
    }

    @Override
    public void addReference(Reference reference) {
        getNodeManager(reference.getSourceNodeId()).addReference(reference);
    }

    @Override
    public void removeReference(Reference reference) {
        getNodeManager(reference.getSourceNodeId()).removeReference(reference);
    }

    @Override
    public List<Reference> getReferences(NodeId nodeId) {
        return getNodeManager(nodeId).getReferences(nodeId);
    }

    @Override
    public List<Reference> getReferences(NodeId nodeId, Predicate<Reference> filter) {
        return getNodeManager(nodeId).getReferences(nodeId, filter);
    }

}

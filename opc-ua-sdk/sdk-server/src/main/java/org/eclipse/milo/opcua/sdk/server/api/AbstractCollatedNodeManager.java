/*
 * Copyright (c) 2019 Kevin Herron
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
import java.util.Set;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public abstract class AbstractCollatedNodeManager<K, T extends Node> implements NodeManager<T> {

    public abstract K getKey(NodeId nodeId);

    public abstract NodeManager<T> getNodeManager(K key);

    @Override
    public void addNode(T node) {
        getNodeManager(node).addNode(node);
    }

    @Override
    public boolean containsNode(NodeId nodeId) {
        return getNodeManager(nodeId).containsNode(nodeId);
    }

    @Override
    public Optional<T> getNode(NodeId nodeId) {
        return getNodeManager(nodeId).getNode(nodeId);
    }

    @Override
    public Optional<T> removeNode(NodeId nodeId) {
        return getNodeManager(nodeId).removeNode(nodeId);
    }

    @Override
    public void addReference(Reference reference) {
        getNodeManager(reference.getSourceNodeId()).addReference(reference);

        invert(reference).ifPresent(inverted ->
            getNodeManager(inverted.getSourceNodeId())
                .addReference(inverted)
        );
    }

    @Override
    public void removeReference(Reference reference) {
        getNodeManager(reference.getSourceNodeId()).removeReference(reference);

        invert(reference).ifPresent(inverted ->
            getNodeManager(inverted.getSourceNodeId())
                .removeReference(inverted)
        );
    }

    @Override
    public Set<Reference> getReferences(NodeId nodeId) {
        return getNodeManager(nodeId).getReferences(nodeId);
    }

    private NodeManager<T> getNodeManager(Node node) {
        return getNodeManager(node.getNodeId());
    }

    private NodeManager<T> getNodeManager(NodeId nodeId) {
        return getNodeManager(getKey(nodeId));
    }

    private Optional<Reference> invert(Reference originalReference) {
        return originalReference.getTargetNodeId().local().map(sourceNodeId -> {
            // New target NodeClass is the original source Node's NodeClass
            NodeClass targetNodeClass = getNode(originalReference.getSourceNodeId())
                .map(Node::getNodeClass)
                .orElse(NodeClass.Unspecified);

            return new Reference(
                sourceNodeId,
                originalReference.getReferenceTypeId(),
                originalReference.getSourceNodeId().expanded(),
                targetNodeClass,
                !originalReference.isForward()
            );
        });
    }

}

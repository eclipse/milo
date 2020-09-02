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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.MapMaker;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.nodes.Node;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class AbstractNodeManager<T extends Node> implements NodeManager<T> {

    private final ConcurrentMap<NodeId, T> nodeMap;
    private final ConcurrentMap<NodeId, LinkedHashMultiset<Reference>> referenceMap;

    public AbstractNodeManager() {
        nodeMap = makeNodeMap(new MapMaker());

        referenceMap = new ConcurrentHashMap<>();
    }

    /**
     * Optionally customize the backing {@link ConcurrentMap} with the provided {@link MapMaker}.
     *
     * @param mapMaker the {@link MapMaker} that make the backing map with.
     * @return a {@link ConcurrentMap}.
     */
    protected ConcurrentMap<NodeId, T> makeNodeMap(MapMaker mapMaker) {
        return mapMaker.makeMap();
    }

    /**
     * Get the backing {@link ConcurrentMap} holding this {@link NodeManager}'s Nodes.
     *
     * @return the backing {@link ConcurrentMap} holding this {@link NodeManager}'s Nodes.
     */
    protected ConcurrentMap<NodeId, T> getNodeMap() {
        return nodeMap;
    }

    /**
     * Get the backing {@link ConcurrentMap} holding this {@link NodeManager}'s References.
     *
     * @return the backing {@link ConcurrentMap} holding this {@link NodeManager}'s References.
     */
    public ConcurrentMap<NodeId, LinkedHashMultiset<Reference>> getReferenceMap() {
        return referenceMap;
    }

    /**
     * Get a copied List of the Nodes being managed.
     *
     * @return a copied List of the Nodes being managed.
     */
    public List<T> getNodes() {
        return new ArrayList<>(nodeMap.values());
    }

    /**
     * Get a copied List of the {@link NodeId}s being managed.
     *
     * @return a copied List of the {@link NodeId}s being managed.
     */
    public List<NodeId> getNodeIds() {
        return new ArrayList<>(nodeMap.keySet());
    }

    @Override
    public boolean containsNode(NodeId nodeId) {
        return nodeMap.containsKey(nodeId);
    }

    @Override
    public boolean containsNode(ExpandedNodeId nodeId, NamespaceTable namespaceTable) {
        return nodeId.local(namespaceTable)
            .map(this::containsNode)
            .orElse(false);
    }

    @Override
    public Optional<T> addNode(T node) {
        return Optional.ofNullable(nodeMap.put(node.getNodeId(), node));
    }

    @Override
    public Optional<T> getNode(NodeId nodeId) {
        return Optional.ofNullable(nodeMap.get(nodeId));
    }

    @Override
    public Optional<T> getNode(ExpandedNodeId nodeId, NamespaceTable namespaceTable) {
        return nodeId.local(namespaceTable).flatMap(this::getNode);
    }

    @Override
    public Optional<T> removeNode(NodeId nodeId) {
        return Optional.ofNullable(nodeMap.remove(nodeId));
    }

    @Override
    public Optional<T> removeNode(ExpandedNodeId nodeId, NamespaceTable namespaceTable) {
        return nodeId.local(namespaceTable).flatMap(this::removeNode);
    }

    @Override
    public synchronized void addReference(Reference reference) {
        LinkedHashMultiset<Reference> references = referenceMap.computeIfAbsent(
            reference.getSourceNodeId(),
            nodeId -> LinkedHashMultiset.create()
        );

        references.add(reference);
    }

    @Override
    public synchronized void addReferences(Reference reference, NamespaceTable namespaceTable) {
        addReference(reference);

        reference.invert(namespaceTable).ifPresent(this::addReference);
    }

    @Override
    public synchronized void removeReference(Reference reference) {
        LinkedHashMultiset<Reference> references = referenceMap.get(
            reference.getSourceNodeId()
        );

        if (references != null) {
            references.remove(reference);

            if (references.isEmpty()) {
                referenceMap.remove(reference.getSourceNodeId());
            }
        }
    }

    @Override
    public synchronized void removeReferences(Reference reference, NamespaceTable namespaceTable) {
        removeReference(reference);

        reference.invert(namespaceTable).ifPresent(this::removeReference);
    }

    @Override
    public synchronized List<Reference> getReferences(NodeId nodeId) {
        LinkedHashMultiset<Reference> references = referenceMap.get(nodeId);

        if (references != null) {
            return new ArrayList<>(references);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Reference> getReferences(NodeId nodeId, Predicate<Reference> filter) {
        return getReferences(nodeId)
            .stream()
            .filter(filter)
            .collect(Collectors.toList());
    }

}

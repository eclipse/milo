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

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.MapMaker;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class AbstractNodeManager<T extends Node> implements NodeManager<T> {

    private final ConcurrentMap<NodeId, T> nodeMap;
    private final ConcurrentMap<NodeId, ConcurrentHashMultiset<Reference>> referenceMap;

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
    public ConcurrentMap<NodeId, ConcurrentHashMultiset<Reference>> getReferenceMap() {
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
    public void addReference(Reference reference) {
        ConcurrentHashMultiset<Reference> references = referenceMap.computeIfAbsent(
            reference.getSourceNodeId(),
            nodeId -> ConcurrentHashMultiset.create()
        );

        references.add(reference);
    }

    @Override
    public void addReferences(Reference reference, NamespaceTable namespaceTable) {
        addReference(reference);

        reference.invert(namespaceTable).ifPresent(this::addReference);
    }

    @Override
    public void removeReference(Reference reference) {
        ConcurrentHashMultiset<Reference> references = referenceMap.computeIfAbsent(
            reference.getSourceNodeId(),
            nodeId -> ConcurrentHashMultiset.create()
        );

        references.remove(reference);

        if (references.isEmpty()) {
            references = referenceMap.remove(reference.getSourceNodeId());

            if (references != null && !references.isEmpty()) {
                // Oops, it gained a node between isEmpty() and remove(), merge
                // it back in...
                referenceMap.merge(
                    reference.getSourceNodeId(),
                    references,
                    (references1, references2) -> {
                        ConcurrentHashMultiset<Reference> merged = ConcurrentHashMultiset.create();
                        merged.addAll(references1);
                        merged.addAll(references2);
                        return merged;
                    }
                );
            }
        }
    }

    @Override
    public void removeReferences(Reference reference, NamespaceTable namespaceTable) {
        removeReference(reference);

        reference.invert(namespaceTable).ifPresent(this::removeReference);
    }

    @Override
    public List<Reference> getReferences(NodeId nodeId) {
        ConcurrentHashMultiset<Reference> references = referenceMap.get(nodeId);

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

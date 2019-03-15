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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Multimaps;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class AbstractNodeManager<T extends Node> implements NodeManager<T> {

    private final ConcurrentMap<NodeId, T> nodeMap;
    private final ListMultimap<NodeId, Reference> referencesBySource;
    private final ListMultimap<NodeId, Reference> referencesByTarget;

    public AbstractNodeManager() {
        nodeMap = makeNodeMap(new MapMaker());
        referencesBySource = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());
        referencesByTarget = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());
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

    @Override
    public boolean containsNode(NodeId nodeId) {
        return nodeMap.containsKey(nodeId);
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
    public Optional<T> getNode(ExpandedNodeId nodeId) {
        return nodeId.local().flatMap(this::getNode);
    }

    @Override
    public Optional<T> removeNode(NodeId nodeId) {
        return Optional.ofNullable(nodeMap.remove(nodeId));
    }

    @Override
    public Optional<T> removeNode(ExpandedNodeId nodeId) {
        return nodeId.local().flatMap(this::removeNode);
    }

    @Override
    public void addReference(Reference reference) {
        referencesBySource.put(reference.getSourceNodeId(), reference);

        reference.getTargetNodeId().local().ifPresent(id -> referencesByTarget.put(id, reference));

        reference.invert().ifPresent(inverted -> {
            referencesBySource.put(inverted.getSourceNodeId(), inverted);

            inverted.getTargetNodeId().local().ifPresent(id -> referencesByTarget.put(id, inverted));
        });
    }

    @Override
    public void removeReference(Reference reference) {
        referencesBySource.remove(reference.getSourceNodeId(), reference);

        reference.getTargetNodeId().local().ifPresent(id -> referencesByTarget.remove(id, reference));
    }

    @Override
    public List<Reference> getReferences(NodeId sourceNodeId) {
        return new ArrayList<>(referencesBySource.get(sourceNodeId));
    }

    @Override
    public List<Reference> getReferences(NodeId sourceNodeId, Predicate<Reference> filter) {
        return referencesBySource.get(sourceNodeId)
            .stream()
            .filter(filter)
            .collect(Collectors.toList());
    }

    @Override
    public List<Reference> getReferencesTo(NodeId targetNodeId) {
        return new ArrayList<>(referencesByTarget.get(targetNodeId));
    }

}

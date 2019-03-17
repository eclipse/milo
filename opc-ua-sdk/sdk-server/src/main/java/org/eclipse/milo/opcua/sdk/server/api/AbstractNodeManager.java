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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class AbstractNodeManager<T extends Node> implements NodeManager<T> {

    private final ConcurrentMap<NodeId, T> nodeMap;
    private final ListMultimap<NodeId, Reference> referenceMultimap;

    public AbstractNodeManager() {
        nodeMap = makeNodeMap(new MapMaker());
        referenceMultimap = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());
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

    protected ConcurrentMap<NodeId, T> getNodeMap() {
        return nodeMap;
    }

    protected ListMultimap<NodeId, Reference> getReferenceMultimap() {
        return referenceMultimap;
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
    public Optional<T> removeNode(NodeId nodeId) {
        return Optional.ofNullable(nodeMap.remove(nodeId));
    }

    @Override
    public void addReference(Reference reference) {
        referenceMultimap.put(reference.getSourceNodeId(), reference);

        reference.invert().ifPresent(
            inverted ->
                referenceMultimap.put(inverted.getSourceNodeId(), inverted)
        );
    }

    @Override
    public void removeReference(Reference reference) {
        referenceMultimap.remove(reference.getSourceNodeId(), reference);

        reference.invert().ifPresent(
            inverted ->
                referenceMultimap.remove(inverted.getSourceNodeId(), inverted)
        );
    }

    @Override
    public List<Reference> getReferences(NodeId nodeId) {
        synchronized (referenceMultimap) {
            return new ArrayList<>(referenceMultimap.get(nodeId));
        }
    }

    @Override
    public List<Reference> getReferences(NodeId nodeId, Predicate<Reference> filter) {
        synchronized (referenceMultimap) {
            return referenceMultimap.get(nodeId)
                .stream()
                .filter(filter)
                .collect(Collectors.toList());
        }
    }

}

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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public abstract class AbstractNodeManager<T extends Node> implements NodeManager<T> {

    private final ConcurrentMap<NodeId, T> nodeMap;
    private final ListMultimap<NodeId, Reference> concreteReferences;
    private final ListMultimap<NodeId, Reference> virtualReferences;

    public AbstractNodeManager() {
        nodeMap = makeNodeMap(new MapMaker());
        concreteReferences = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());
        virtualReferences = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());
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
    public void addNode(T node) {
        nodeMap.put(node.getNodeId(), node);
    }

    @Override
    public boolean containsNode(NodeId nodeId) {
        return nodeMap.containsKey(nodeId);
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
        concreteReferences.put(reference.getSourceNodeId(), reference);
    }

    @Override
    public void addVirtualReference(Reference reference) {
        virtualReferences.put(reference.getSourceNodeId(), reference);
    }

    @Override
    public void removeReference(Reference reference) {
        concreteReferences.remove(reference.getSourceNodeId(), reference);
    }

    @Override
    public void removeVirtualReference(Reference reference) {
        virtualReferences.remove(reference.getSourceNodeId(), reference);
    }

    @Override
    public List<Reference> getReferences(NodeId nodeId) {
        List<Reference> concreteList = concreteReferences.get(nodeId);
        LinkedHashSet<Reference> concreteSet = new LinkedHashSet<>(concreteList);
        LinkedHashSet<Reference> virtualSet = new LinkedHashSet<>(virtualReferences.get(nodeId));

        // All virtual refs that do not also have a concrete ref
        Set<Reference> uniqueVirtualRefs = Sets.difference(virtualSet, concreteSet);

        List<Reference> references = Lists.newArrayList();
        references.addAll(concreteList);
        references.addAll(uniqueVirtualRefs);
        return references;
    }

}

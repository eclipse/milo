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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MapMaker;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;

public abstract class AbstractNodeManager<T extends Node> implements NodeManager<T> {

    private final ReadWriteLock refLock = new ReentrantReadWriteLock();
    private final ListMultimap<NodeId, Reference> refsBySource = ArrayListMultimap.create();
    private final ListMultimap<ExpandedNodeId, Reference> refsByTarget = ArrayListMultimap.create();

    private final ConcurrentMap<NodeId, T> nodeMap;

    public AbstractNodeManager() {
        MapMaker mapMaker = new MapMaker();

        nodeMap = makeNodeMap(mapMaker);
    }

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
        NodeId sourceNodeId = reference.getSourceNodeId();
        ExpandedNodeId targetNodeId = reference.getTargetNodeId();

        refLock.writeLock().lock();
        try {
            refsBySource.put(sourceNodeId, reference);
            refsByTarget.put(targetNodeId, reference);
        } finally {
            refLock.writeLock().unlock();
        }
    }

    @Override
    public void removeReference(Reference reference) {
        NodeId sourceNodeId = reference.getSourceNodeId();
        ExpandedNodeId targetNodeId = reference.getTargetNodeId();

        refLock.writeLock().lock();
        try {
            refsBySource.remove(sourceNodeId, reference);
            refsByTarget.remove(targetNodeId, reference);
        } finally {
            refLock.writeLock().unlock();
        }
    }

    @Override
    public Set<Reference> getReferences(NodeId nodeId) {
        LinkedHashSet<Reference> references = new LinkedHashSet<>();

        refLock.readLock().lock();
        try {
            List<Reference> concrete = refsBySource.get(nodeId);

            // From any reference in which nodeId is target, a virtual reference can be
            // derived where nodeId is source by "inverting" the reference, if possible.
            List<Reference> derived =
                refsByTarget.get(nodeId.expanded())
                    .stream()
                    .flatMap(r -> opt2stream(invert(r)))
                    .collect(Collectors.toList());

            references.addAll(concrete);
            references.addAll(derived);
        } finally {
            refLock.readLock().unlock();
        }

        return references;
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

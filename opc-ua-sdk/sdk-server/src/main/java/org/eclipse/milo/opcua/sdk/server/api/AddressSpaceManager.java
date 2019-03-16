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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class AddressSpaceManager extends AddressSpaceComposite {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<NodeManager<UaNode>> nodeManagers = new CopyOnWriteArrayList<>();

    public AddressSpaceManager(OpcUaServer server) {
        super(server);
    }

    public synchronized void register(NodeManager<UaNode> nodeManager) {
        if (!nodeManagers.contains(nodeManager)) {
            nodeManagers.add(nodeManager);
        } else {
            logger.warn("NodeManager already registered: {}", nodeManager);
        }
    }

    public synchronized void unregister(NodeManager<UaNode> nodeManager) {
        if (nodeManagers.contains(nodeManager)) {
            nodeManagers.remove(nodeManager);
        } else {
            logger.warn("NodeManager not registered: {}", nodeManager);
        }
    }

    public CompletableFuture<List<Reference>> browseAll(AccessContext context, NodeId nodeId) {
        ViewDescription view = new ViewDescription(
            NodeId.NULL_VALUE,
            DateTime.NULL_VALUE,
            uint(0)
        );

        return browseAll(context, view, nodeId);
    }

    public CompletableFuture<List<Reference>> browseAll(AccessContext context, ViewDescription view, NodeId nodeId) {
        List<AddressSpace> addressSpaces = getAddressSpaces();

        try {
            addressSpaces.stream()
                .filter(asx -> asx.filter(nodeId))
                .findFirst()
                .orElseThrow(() -> new UaException(StatusCodes.Bad_NodeIdUnknown));
        } catch (UaException e) {
            return failedUaFuture(e.getStatusCode());
        }

        List<CompletableFuture<List<Reference>>> futures = new ArrayList<>();

        for (AddressSpace asx : addressSpaces) {
            BrowseContext browseContext = new BrowseContext(
                getServer(),
                context.getSession().orElse(null)
            );

            if (asx.filter(nodeId)) {
                asx.browse(browseContext, view, nodeId);
            } else {
                asx.getReferences(browseContext, view, nodeId);
            }

            futures.add(browseContext.getFuture());
        }

        return FutureUtils.sequence(futures).thenApply(
            refs ->
                refs.stream()
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(toList())
        );
    }

    public Optional<UaNode> getManagedNode(NodeId nodeId) {
        return nodeManagers.stream()
            .filter(n -> n.containsNode(nodeId))
            .findFirst()
            .flatMap(n -> n.getNode(nodeId));
    }

    public Optional<UaNode> getManagedNode(ExpandedNodeId nodeId) {
        return nodeId.local().flatMap(this::getManagedNode);
    }

    /**
     * Get all References from {@code sourceNodeId}, collected from the {@link NodeManager}, if present, of all
     * registered {@link AddressSpace}s.
     *
     * @param sourceNodeId TODO
     * @return TODO
     */
    public List<Reference> getManagedReferences(NodeId sourceNodeId) {
        return nodeManagers.stream()
            .map(n -> n.getReferences(sourceNodeId))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public List<Reference> getManagedReferences(NodeId sourceNodeId, Predicate<Reference> filter) {
        return nodeManagers.stream()
            .map(n -> n.getReferences(sourceNodeId, filter))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

}

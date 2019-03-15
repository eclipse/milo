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

import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class AddressSpaceManager {

    private final List<AddressSpace> addressSpaces = new CopyOnWriteArrayList<>();
    private final List<NodeManager<UaNode>> nodeManagers = new CopyOnWriteArrayList<>();

    private final OpcUaServer server;

    public AddressSpaceManager(OpcUaServer server) {
        this.server = server;
    }

    public void register(AddressSpace addressSpace) {
        addressSpaces.add(addressSpace);

        addressSpace.getNodeManager().ifPresent(this::register);
    }

    public void unregister(AddressSpace addressSpace) {
        addressSpaces.remove(addressSpace);

        addressSpace.getNodeManager().ifPresent(this::unregister);
    }

    public void register(NodeManager<UaNode> nodeManager) {
        nodeManagers.add(nodeManager);
    }

    public void unregister(NodeManager<UaNode> nodeManager) {
        nodeManagers.remove(nodeManager);
    }

    public List<AddressSpace> getAddressSpaces() {
        return new ArrayList<>(addressSpaces);
    }

    public AddressSpace getAddressSpace(NodeId nodeId) {
        return addressSpaces.stream()
            .filter(asx -> asx.filter(nodeId))
            .findFirst()
            .orElse(null); // TODO "empty" AddressSpace?
    }

    public AddressSpace getAddressSpace(ExpandedNodeId nodeId) {
        return nodeId.local()
            .map(this::getAddressSpace)
            .orElse(null);
    }

    public Optional<NodeManager<UaNode>> getNodeManager(NodeId nodeId) {
        AddressSpace addressSpace = getAddressSpace(nodeId);

        if (addressSpace != null) {
            return addressSpace.getNodeManager();
        } else {
            return Optional.empty();
        }
    }

    public Optional<NodeManager<UaNode>> getNodeManager(ExpandedNodeId nodeId) {
        AddressSpace addressSpace = getAddressSpace(nodeId);

        if (addressSpace != null) {
            return addressSpace.getNodeManager();
        } else {
            return Optional.empty();
        }
    }

    public Optional<UaNode> getManagedNode(NodeId nodeId) {
//        return getNodeManager(nodeId)
//            .flatMap(n -> n.getNode(nodeId));
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
//        return addressSpaces.stream()
//            .map(asx ->
//                asx.getNodeManager()
//                    .map(n -> n.getReferences(sourceNodeId))
//                    .orElse(Collections.emptyList())
//            )
//            .flatMap(Collection::stream)
//            .collect(Collectors.toList());

        return nodeManagers.stream()
            .map(n -> n.getReferences(sourceNodeId))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public List<Reference> getManagedReferences(NodeId sourceNodeId, Predicate<Reference> filter) {
//        return addressSpaces.stream()
//            .map(asx ->
//                asx.getNodeManager()
//                    .map(n -> n.getReferences(sourceNodeId, filter))
//                    .orElse(Collections.emptyList())
//            )
//            .flatMap(Collection::stream)
//            .collect(Collectors.toList());

        return nodeManagers.stream()
            .map(n -> n.getReferences(sourceNodeId, filter))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public CompletableFuture<List<Reference>> browse(AccessContext context, NodeId nodeId) {
        ViewDescription view = new ViewDescription(
            NodeId.NULL_VALUE,
            DateTime.NULL_VALUE,
            uint(0)
        );

        return browse(context, view, nodeId);
    }

    public CompletableFuture<List<Reference>> browse(AccessContext context, ViewDescription view, NodeId nodeId) {
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
            ViewServices.BrowseContext browseContext = new ViewServices.BrowseContext(
                server,
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

}

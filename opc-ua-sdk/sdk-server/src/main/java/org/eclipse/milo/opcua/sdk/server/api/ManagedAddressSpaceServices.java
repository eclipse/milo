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
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.methods.MethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.services.MethodServices;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AsyncAttributeFilter;
import org.eclipse.milo.opcua.sdk.server.util.GroupMapCollate;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.CompletableFuture.completedFuture;

public abstract class ManagedAddressSpaceServices extends AbstractLifecycle implements AddressSpaceServices {

    private static final int KEY_ASYNC = 0;
    private static final int KEY_BLOCKING = 1;
    private static final int KEY_NOT_FOUND = 2;

    /**
     * Execution strategy for an attribute read or write operation.
     */
    enum ExecutionStrategy {
        ASYNC,
        BLOCKING
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UaNodeManager nodeManager = new UaNodeManager();

    private final UaNodeContext nodeContext;
    private final NodeFactory nodeFactory;

    private final OpcUaServer server;

    public ManagedAddressSpaceServices(OpcUaServer server) {
        this.server = server;

        nodeContext = new UaNodeContext() {
            @Override
            public OpcUaServer getServer() {
                return server;
            }

            @Override
            public NodeManager<UaNode> getNodeManager() {
                return nodeManager;
            }
        };

        nodeFactory = new NodeFactory(nodeContext);
    }

    protected OpcUaServer getServer() {
        return server;
    }

    protected UaNodeContext getNodeContext() {
        return nodeContext;
    }

    protected NodeFactory getNodeFactory() {
        return nodeFactory;
    }

    protected UaNodeManager getNodeManager() {
        return nodeManager;
    }

    @Override
    protected void onStartup() {
        registerNodeManager(nodeManager);
    }

    @Override
    protected void onShutdown() {
        unregisterNodeManager(nodeManager);
    }

    /**
     * Register this {@link ManagedAddressSpaceServices}'s {@link UaNodeManager} with its managing entity.
     * <p>
     * The default implementation registers it with the Server's {@link AddressSpaceManager}.
     *
     * @param nodeManager the {@link UaNodeManager} to register.
     */
    protected void registerNodeManager(UaNodeManager nodeManager) {
        server.getAddressSpaceManager().register(nodeManager);
    }

    /**
     * Unregister this {@link ManagedAddressSpaceServices}'s {@link UaNodeManager} with its managing entity.
     * <p>
     * The default implementation unregisters it with the Server's {@link AddressSpaceManager}.
     *
     * @param nodeManager the {@link UaNodeManager} to unregister.
     */
    protected void unregisterNodeManager(UaNodeManager nodeManager) {
        server.getAddressSpaceManager().unregister(nodeManager);
    }

    @Override
    public void browse(BrowseContext context, ViewDescription viewDescription, NodeId nodeId) {
        if (nodeManager.containsNode(nodeId)) {
            List<Reference> references = nodeManager.getReferences(nodeId);

            logger.debug("Browsed {} references for {}", references.size(), nodeId);

            context.success(references);
        } else {
            context.failure(StatusCodes.Bad_NodeIdUnknown);
        }
    }

    @Override
    public void getReferences(BrowseContext context, ViewDescription viewDescription, NodeId nodeId) {
        List<Reference> references = nodeManager.getReferences(nodeId);

        logger.debug("Got {} references for {}", references.size(), nodeId);

        context.success(references);
    }

    @Override
    public void registerNodes(RegisterNodesContext context, List<NodeId> nodeIds) {
        context.success(nodeIds);
    }

    @Override
    public void unregisterNodes(UnregisterNodesContext context, List<NodeId> nodeIds) {
        context.success(Collections.nCopies(nodeIds.size(), Unit.VALUE));
    }

    @Override
    public void read(
        ReadContext context,
        Double maxAge,
        TimestampsToReturn timestamps,
        List<ReadValueId> readValueIds
    ) {

        CompletableFuture<List<DataValue>> future = GroupMapCollate.groupMapCollate(
            readValueIds,
            readValueId -> {
                UaNode node = nodeManager.get(readValueId.getNodeId());

                if (node != null) {
                    ExecutionStrategy readStrategy =
                        getReadStrategy(node, readValueId.getAttributeId());

                    if (readStrategy == ExecutionStrategy.ASYNC) {
                        return KEY_ASYNC;
                    } else {
                        return KEY_BLOCKING;
                    }
                } else {
                    return KEY_NOT_FOUND;
                }
            },
            (Integer key) -> readValueIdsForKey -> {
                switch (key) {
                    case KEY_ASYNC: {
                        return readAsync(readValueIdsForKey);
                    }
                    case KEY_BLOCKING: {
                        return readBlocking(readValueIdsForKey);
                    }
                    default:
                    case KEY_NOT_FOUND: {
                        List<DataValue> values = Collections.nCopies(
                            readValueIdsForKey.size(),
                            new DataValue(StatusCodes.Bad_NodeIdUnknown)
                        );
                        return completedFuture(values);
                    }
                }
            }
        );

        future.thenAccept(context::success);
    }

    private CompletableFuture<List<DataValue>> readAsync(List<ReadValueId> idsForKey) {
        // TODO BatchContext? Here or in AsyncAttributeReader?

        List<CompletableFuture<DataValue>> futures = new ArrayList<>();

        for (ReadValueId id : idsForKey) {
            UaNode node = nodeManager.get(id.getNodeId());

            if (node != null) {
                // TODO should use AsyncAttributeReader or readAttributeAsync
                futures.add(
                    node.getAttributeAsync(
                        new AttributeContext(null, null),
                        AttributeId.from(id.getAttributeId()).get()
                    )
                );
            } else {
                futures.add(completedFuture(new DataValue(StatusCodes.Bad_NodeIdUnknown)));
            }
        }

        return FutureUtils.sequence(futures);
    }

    private CompletableFuture<List<DataValue>> readBlocking(List<ReadValueId> idsForKey) {
        List<CompletableFuture<DataValue>> futures = new ArrayList<>();

        for (ReadValueId id : idsForKey) {
            UaNode node = nodeManager.get(id.getNodeId());

            if (node != null) {
                // TODO should use BlockingAttributeReader or readAttributeBlocking
                AttributeContext attributeContext = new AttributeContext(null, null); // TODO

                DataValue value = node.readAttribute(attributeContext, id.getAttributeId());

                futures.add(completedFuture(value));
            } else {
                futures.add(completedFuture(new DataValue(StatusCodes.Bad_NodeIdUnknown)));
            }
        }

        return FutureUtils.sequence(futures);
    }

    /**
     * Get the {@link ExecutionStrategy} for reading an attribute on a {@link UaNode}.
     *
     * @param node        the {@link UaNode} being read.
     * @param attributeId the attribute id being read.
     * @return the {@link ExecutionStrategy} to use.
     */
    protected ExecutionStrategy getReadStrategy(UaNode node, UInteger attributeId) {
        Optional<ExecutionStrategy> strategy = AttributeId.from(attributeId).map(id -> {
            boolean async = node.getFilterChain().getFilters()
                .stream()
                .anyMatch(f -> f.filterGet(id) && f instanceof AsyncAttributeFilter);

            return async ? ExecutionStrategy.ASYNC : ExecutionStrategy.BLOCKING;
        });

        return strategy.orElse(ExecutionStrategy.BLOCKING);
    }

    protected ExecutionStrategy getWriteStrategy(UaNode node, UInteger attributeId) {
        Optional<ExecutionStrategy> strategy = AttributeId.from(attributeId).map(id -> {
            boolean async = node.getFilterChain().getFilters()
                .stream()
                .anyMatch(f -> f.filterSet(id) && f instanceof AsyncAttributeFilter);

            return async ? ExecutionStrategy.ASYNC : ExecutionStrategy.BLOCKING;
        });

        return strategy.orElse(ExecutionStrategy.BLOCKING);
    }

    @Override
    public void write(
        WriteContext context,
        List<WriteValue> writeValues
    ) {

        List<StatusCode> results = Lists.newArrayListWithCapacity(writeValues.size());

        for (WriteValue writeValue : writeValues) {
            UaServerNode node = nodeManager.get(writeValue.getNodeId());

            if (node != null) {
                try {
                    node.writeAttribute(
                        new AttributeContext(context),
                        writeValue.getAttributeId(),
                        writeValue.getValue(),
                        writeValue.getIndexRange()
                    );

                    results.add(StatusCode.GOOD);

                    logger.debug(
                        "Wrote value {} to {} attribute of {}",
                        writeValue.getValue().getValue(),
                        AttributeId.from(writeValue.getAttributeId())
                            .map(Object::toString).orElse("unknown"),
                        node.getNodeId()
                    );
                } catch (UaException e) {
                    logger.error("Unable to write value={}", writeValue.getValue(), e);
                    results.add(e.getStatusCode());
                }
            } else {
                results.add(new StatusCode(StatusCodes.Bad_NodeIdUnknown));
            }
        }

        context.success(results);
    }

    /**
     * Invoke one or more methods belonging to this {@link MethodServices}.
     *
     * @param context  the {@link CallContext}.
     * @param requests The {@link CallMethodRequest}s for the methods to invoke.
     */
    @Override
    public void call(CallContext context, List<CallMethodRequest> requests) {
        List<CallMethodResult> results = Lists.newArrayListWithCapacity(requests.size());

        for (CallMethodRequest request : requests) {
            MethodInvocationHandler handler = getInvocationHandler(
                request.getObjectId(),
                request.getMethodId()
            ).orElse(MethodInvocationHandler.NODE_ID_UNKNOWN);

            try {
                results.add(handler.invoke(context, request));
            } catch (Throwable t) {
                LoggerFactory.getLogger(getClass())
                    .error("Uncaught Throwable invoking method handler for methodId={}.", request.getMethodId(), t);

                results.add(
                    new CallMethodResult(
                        new StatusCode(StatusCodes.Bad_InternalError),
                        new StatusCode[0], new DiagnosticInfo[0], new Variant[0])
                );
            }
        }

        context.success(results);
    }

    /**
     * Get the {@link MethodInvocationHandler} for the method identified by {@code methodId}, if it exists.
     *
     * @param objectId the {@link NodeId} identifying the object the method will be invoked on.
     * @param methodId the {@link NodeId} identifying the method.
     * @return the {@link MethodInvocationHandler} for {@code methodId}, if it exists.
     */
    protected Optional<MethodInvocationHandler> getInvocationHandler(NodeId objectId, NodeId methodId) {
        return nodeManager.getNode(objectId).flatMap(node -> {
            UaMethodNode methodNode = null;

            if (node instanceof UaObjectNode) {
                UaObjectNode objectNode = (UaObjectNode) node;

                methodNode = objectNode.findMethodNode(methodId);
            } else if (node instanceof UaObjectTypeNode) {
                UaObjectTypeNode objectTypeNode = (UaObjectTypeNode) node;

                methodNode = objectTypeNode.findMethodNode(methodId);
            }

            if (methodNode != null) {
                return Optional.of(methodNode.getInvocationHandler());
            } else {
                return Optional.empty();
            }
        });
    }

}

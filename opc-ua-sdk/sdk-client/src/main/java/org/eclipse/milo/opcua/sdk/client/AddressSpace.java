/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.sdk.client.ObjectTypeManager.ObjectNodeConstructor;
import org.eclipse.milo.opcua.sdk.client.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaViewNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.BuiltinReferenceType;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedFuture;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class AddressSpace {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private NodeCache nodeCache = new NodeCache();

    private BrowseOptions browseOptions = new BrowseOptions();

    private final OpcUaClient client;

    public AddressSpace(OpcUaClient client) {
        this.client = client;
    }

    /**
     * Get a {@link UaNode} instance for the Node identified by {@code nodeId}.
     *
     * @param nodeId the {@link NodeId} identifying the Node to get.
     * @return a {@link UaNode} instance for the Node identified by {@code nodeId}.
     * @throws UaException if an error occurs while creating the Node.
     */
    public UaNode getNode(NodeId nodeId) throws UaException {
        try {
            return getNodeAsync(nodeId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Get a {@link UaNode} instance for the Node identified by {@code nodeId}.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId the {@link NodeId} identifying the Node to get.
     * @return a CompletableFuture that completes successfully with the UaNode instance or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<? extends UaNode> getNodeAsync(NodeId nodeId) {
        UaNode cachedNode = nodeCache.getIfPresent(nodeId);

        if (cachedNode != null) {
            return completedFuture(cachedNode);
        } else {
            return createNode(nodeId).whenComplete((node, ex) -> {
                if (node != null) {
                    nodeCache.put(nodeId, node);
                }
            });
        }
    }

    /**
     * Get a {@link UaObjectNode} instance for the ObjectNode identified by {@code nodeId}.
     * <p>
     * The type definition will be read when the instance is created. If this type definition is
     * registered with the {@link ObjectTypeManager} a {@link UaObjectNode} of the appropriate
     * subclass will be returned.
     *
     * @param nodeId the {@link NodeId} identifying the ObjectNode to get.
     * @return a {@link UaObjectNode} instance for the ObjectNode identified by {@code nodeId}.
     * @throws UaException if an error occurs while creating the ObjectNode.
     */
    public UaObjectNode getObjectNode(NodeId nodeId) throws UaException {
        try {
            return getObjectNodeAsync(nodeId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Get a {@link UaObjectNode} instance for the ObjectNode identified by {@code nodeId},
     * assuming the type definition identified by {@code typeDefinitionId}.
     * <p>
     * If this type definition is registered with the {@link ObjectTypeManager} a
     * {@link UaObjectNode} of the appropriate subclass will be returned.
     *
     * @param nodeId           the {@link NodeId} identifying the ObjectNode to get.
     * @param typeDefinitionId the {@link NodeId} identifying the type definition.
     * @return a {@link UaObjectNode} instance for the ObjectNode identified by {@code nodeId}.
     * @throws UaException if an error occurs while creating the ObjectNode.
     */
    public UaObjectNode getObjectNode(NodeId nodeId, NodeId typeDefinitionId) throws UaException {
        try {
            return getObjectNodeAsync(nodeId, typeDefinitionId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Get a {@link UaObjectNode} instance for the ObjectNode identified by {@code nodeId}.
     * <p>
     * The type definition will be read when the instance is created. If this type definition is
     * registered with the {@link ObjectTypeManager} a {@link UaObjectNode} of the appropriate
     * subclass will be returned.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId the {@link NodeId} identifying the ObjectNode to get.
     * @return a CompletableFuture that completes successfully with a {@link UaObjectNode} instance
     * for the ObjectNode identified by {@code nodeId} or completes exceptionally if an error
     * occurs creating the ObjectNode.
     */
    public CompletableFuture<UaObjectNode> getObjectNodeAsync(NodeId nodeId) {
        UaNode cachedNode = nodeCache.getIfPresent(nodeId);

        if (cachedNode instanceof UaObjectNode) {
            return completedFuture((UaObjectNode) cachedNode);
        } else {
            CompletableFuture<NodeId> typeDefinitionFuture = readTypeDefinition(nodeId);

            return typeDefinitionFuture.thenCompose(typeDefinitionId -> getObjectNodeAsync(nodeId, typeDefinitionId));
        }
    }

    /**
     * Get a {@link UaObjectNode} instance for the ObjectNode identified by {@code nodeId},
     * assuming the type definition identified by {@code typeDefinitionId}.
     * <p>
     * If this type definition is registered with the {@link ObjectTypeManager} a
     * {@link UaObjectNode} of the appropriate subclass will be returned.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId           the {@link NodeId} identifying the ObjectNode to get.
     * @param typeDefinitionId the {@link NodeId} identifying the type definition.
     * @return a CompletableFuture that completes successfully with a {@link UaObjectNode} instance
     * for the ObjectNode identified by {@code nodeId} or completes exceptionally if an error
     * occurs creating the ObjectNode.
     */
    public CompletableFuture<UaObjectNode> getObjectNodeAsync(NodeId nodeId, NodeId typeDefinitionId) {
        UaNode cachedNode = nodeCache.getIfPresent(nodeId);

        if (cachedNode instanceof UaObjectNode) {
            return completedFuture((UaObjectNode) cachedNode);
        } else {
            CompletableFuture<ReadResponse> future = readAttributes(nodeId, AttributeId.OBJECT_ATTRIBUTES);

            return future.thenCompose(response -> {
                List<DataValue> attributeValues = l(response.getResults());

                try {
                    UaObjectNode node = newObjectNode(nodeId, typeDefinitionId, attributeValues);

                    nodeCache.put(node.getNodeId(), node);

                    return completedFuture(node);
                } catch (UaException e) {
                    return failedFuture(e);
                }
            });
        }
    }

    /**
     * Get a {@link UaVariableNode} instance for the VariableNode identified by {@code nodeId}.
     * <p>
     * The type definition will be read when the instance is created. If this type definition is
     * registered with the {@link VariableTypeManager} a {@link UaVariableNode} of the appropriate
     * subclass will be returned.
     *
     * @param nodeId the {@link NodeId} identifying the VariableNode to get.
     * @return a {@link UaVariableNode} instance for the VariableNode identified by {@code nodeId}.
     * @throws UaException if an error occurs while creating the VariableNode.
     */
    public UaVariableNode getVariableNode(NodeId nodeId) throws UaException {
        try {
            return getVariableNodeAsync(nodeId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Get a {@link UaVariableNode} instance for the VariableNode identified by {@code nodeId},
     * assuming the type definition identified by {@code typeDefinitionId}.
     * <p>
     * If this type definition is registered with the {@link VariableTypeManager} a
     * {@link UaVariableNode} of the appropriate subclass will be returned.
     *
     * @param nodeId           the {@link NodeId} identifying the VariableNode to get.
     * @param typeDefinitionId the {@link NodeId} identifying the type definition.
     * @return a {@link UaVariableNode} instance for the VariableNode identified by {@code nodeId}.
     * @throws UaException if an error occurs while creating the VariableNode.
     */
    public UaVariableNode getVariableNode(NodeId nodeId, NodeId typeDefinitionId) throws UaException {
        try {
            return getVariableNodeAsync(nodeId, typeDefinitionId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Get a {@link UaVariableNode} instance for the VariableNode identified by {@code nodeId}.
     * <p>
     * The type definition will be read when the instance is created. If this type definition is
     * registered with the {@link VariableTypeManager} a {@link UaVariableNode} of the appropriate
     * subclass will be returned.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId the {@link NodeId} identifying the VariableNode to get.
     * @return a CompletableFuture that completes successfully with a {@link UaVariableNode}
     * instance for the VariableNode identified by {@code nodeId} or completes exceptionally if an
     * error occurs while creating the VariableNode.
     */
    public CompletableFuture<UaVariableNode> getVariableNodeAsync(NodeId nodeId) {
        UaNode cachedNode = nodeCache.getIfPresent(nodeId);

        if (cachedNode instanceof UaVariableNode) {
            return completedFuture((UaVariableNode) cachedNode);
        } else {
            CompletableFuture<NodeId> typeDefinitionFuture = readTypeDefinition(nodeId);

            return typeDefinitionFuture.thenCompose(typeDefinitionId -> getVariableNodeAsync(nodeId, typeDefinitionId));
        }
    }

    /**
     * Get a {@link UaVariableNode} instance for the VariableNode identified by {@code nodeId},
     * assuming the type definition identified by {@code typeDefinitionId}.
     * <p>
     * If this type definition is registered with the {@link VariableTypeManager} a
     * {@link UaVariableNode} of the appropriate subclass will be returned.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId           the {@link NodeId} identifying the VariableNode to get.
     * @param typeDefinitionId the {@link NodeId} identifying the type definition.
     * @return a CompletableFuture that completes successfully with a {@link UaVariableNode}
     * instance for the VariableNode identified by {@code nodeId} or completes exceptionally if an
     * error occurs while creating the VariableNode.
     */
    public CompletableFuture<UaVariableNode> getVariableNodeAsync(NodeId nodeId, NodeId typeDefinitionId) {
        UaNode cachedNode = nodeCache.getIfPresent(nodeId);

        if (cachedNode instanceof UaVariableNode) {
            return completedFuture((UaVariableNode) cachedNode);
        } else {
            CompletableFuture<ReadResponse> future = readAttributes(nodeId, AttributeId.VARIABLE_ATTRIBUTES);

            return future.thenCompose(response -> {
                List<DataValue> attributeValues = l(response.getResults());

                try {
                    UaVariableNode node = newVariableNode(nodeId, typeDefinitionId, attributeValues);

                    nodeCache.put(node.getNodeId(), node);

                    return completedFuture(node);
                } catch (UaException e) {
                    return failedFuture(e);
                }
            });
        }
    }

    /**
     * Call the Browse service to get a {@link UaNode}'s references using the currently configured
     * {@link BrowseOptions}.
     *
     * @param node the {@link UaNode} to browse.
     * @return a List of {@link ReferenceDescription}s.
     * @throws UaException if a service-level error occurs.
     * @see #getBrowseOptions()
     * @see #modifyBrowseOptions(Consumer)
     * @see #setBrowseOptions(BrowseOptions)
     */
    public List<ReferenceDescription> browse(UaNode node) throws UaException {
        return browse(node, getBrowseOptions());
    }

    /**
     * Call the Browse service to get a {@link UaNode}'s references.
     *
     * @param node          the {@link UaNode} to browse.
     * @param browseOptions the {@link BrowseOptions} to browse with.
     * @return a List of {@link ReferenceDescription}s.
     * @throws UaException if a service-level error occurs.
     */
    public List<ReferenceDescription> browse(UaNode node, BrowseOptions browseOptions) throws UaException {
        try {
            return browseAsync(node, browseOptions).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Call the Browse service to get a Node's references using the currently configured
     * {@link BrowseOptions}.
     *
     * @param nodeId the {@link NodeId} of the Node to browse.
     * @return a List of {@link ReferenceDescription}s.
     * @throws UaException if a service-level error occurs.
     * @see #getBrowseOptions()
     * @see #modifyBrowseOptions(Consumer)
     * @see #setBrowseOptions(BrowseOptions)
     */
    public List<ReferenceDescription> browse(NodeId nodeId) throws UaException {
        return browse(nodeId, getBrowseOptions());
    }

    /**
     * Call the Browse service to get a Node's references.
     *
     * @param nodeId        the {@link NodeId} of the Node to browse.
     * @param browseOptions the {@link BrowseOptions} to browse with.
     * @return a List of {@link ReferenceDescription}s.
     * @throws UaException if a service-level error occurs.
     */
    public List<ReferenceDescription> browse(NodeId nodeId, BrowseOptions browseOptions) throws UaException {
        try {
            return browseAsync(nodeId, browseOptions).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Call the Browse service to get a {@link UaNode}'s references using the currently configured
     * {@link BrowseOptions}.
     * <p>
     * This call completes asynchronously.
     *
     * @param node the {@link UaNode} to browse.
     * @return a CompletableFuture that completes successfully with the List of references or
     * completes exceptionally if a service-level error occurs.
     * @see #getBrowseOptions()
     * @see #modifyBrowseOptions(Consumer)
     * @see #setBrowseOptions(BrowseOptions)
     */
    public CompletableFuture<List<ReferenceDescription>> browseAsync(UaNode node) {
        return browseAsync(node.getNodeId(), getBrowseOptions());
    }

    /**
     * Call the Browse service to get a {@link UaNode}'s references.
     * <p>
     * This call completes asynchronously.
     *
     * @param node          the {@link UaNode} to browse.
     * @param browseOptions the {@link BrowseOptions} to browse with.
     * @return a CompletableFuture that completes successfully with the List of references or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<ReferenceDescription>> browseAsync(UaNode node, BrowseOptions browseOptions) {
        return browseAsync(node.getNodeId(), browseOptions);
    }

    /**
     * Call the Browse service to get a Node's references  using the currently configured
     * {@link BrowseOptions}.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId the {@link NodeId} of the Node to browse.
     * @return a CompletableFuture that completes successfully with the List of references or
     * completes exceptionally if a service-level error occurs.
     * @see #getBrowseOptions()
     * @see #modifyBrowseOptions(Consumer)
     * @see #setBrowseOptions(BrowseOptions)
     */
    public CompletableFuture<List<ReferenceDescription>> browseAsync(NodeId nodeId) {
        return browseAsync(nodeId, getBrowseOptions());
    }

    /**
     * Call the Browse service to get a Node's references.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId        the {@link NodeId} of the Node to browse.
     * @param browseOptions the {@link BrowseOptions} to browse with.
     * @return a CompletableFuture that completes successfully with the List of references or
     * completes exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<ReferenceDescription>> browseAsync(NodeId nodeId, BrowseOptions browseOptions) {
        BrowseDescription browseDescription = new BrowseDescription(
            nodeId,
            browseOptions.getBrowseDirection(),
            browseOptions.getReferenceTypeId(),
            browseOptions.isIncludeSubtypes(),
            browseOptions.getNodeClassMask(),
            uint(BrowseResultMask.All.getValue())
        );

        return BrowseHelper.browse(client, browseDescription);
    }

    /**
     * Browse from {@code node} using the currently configured {@link BrowseOptions}.
     *
     * @param node the {@link UaNode} to start the browse from.
     * @return a List of {@link UaNode}s referenced by {@code node} given the currently configured
     * {@link BrowseOptions}.
     * @throws UaException if an error occurs while browsing or creating Nodes.
     * @see #browseNodes(UaNode, BrowseOptions)
     * @see #getBrowseOptions()
     * @see #modifyBrowseOptions(Consumer)
     * @see #setBrowseOptions(BrowseOptions)
     */
    public List<? extends UaNode> browseNodes(UaNode node) throws UaException {
        return browseNodes(node, getBrowseOptions());
    }

    /**
     * Browse from {@code node} using {@code browseOptions}.
     *
     * @param node          the {@link UaNode} to start the browse from.
     * @param browseOptions the {@link BrowseOptions} to use.
     * @return a List of {@link UaNode}s referenced by {@code node} given {@code browseOptions}.
     * @throws UaException if an error occurs while browsing or creating Nodes.
     */
    public List<? extends UaNode> browseNodes(UaNode node, BrowseOptions browseOptions) throws UaException {
        try {
            return browseNodesAsync(node, browseOptions).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Browse from {@code nodeId} using the currently configured {@link BrowseOptions}.
     *
     * @param nodeId the {@link NodeId} to start the browse from.
     * @return a List of {@link UaNode}s referenced by {@code nodeId} given the currently configured
     * {@link BrowseOptions}.
     * @throws UaException if an error occurs while browsing or creating Nodes.
     * @see #browseNodes(UaNode, BrowseOptions)
     * @see #getBrowseOptions()
     * @see #modifyBrowseOptions(Consumer)
     * @see #setBrowseOptions(BrowseOptions)
     */
    public List<? extends UaNode> browseNodes(NodeId nodeId) throws UaException {
        return browseNodes(nodeId, getBrowseOptions());
    }

    /**
     * Browse from {@code nodeId} using {@code browseOptions}.
     *
     * @param nodeId        the {@link NodeId} to start the browse from.
     * @param browseOptions the {@link BrowseOptions} to use.
     * @return a List of {@link UaNode}s referenced by {@code nodeId} given {@code browseOptions}.
     * @throws UaException if an error occurs while browsing or creating Nodes.
     */
    public List<? extends UaNode> browseNodes(NodeId nodeId, BrowseOptions browseOptions) throws UaException {
        try {
            return browseNodesAsync(nodeId, browseOptions).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    /**
     * Browse from {@code node} using the currently configured {@link BrowseOptions}.
     * <p>
     * This call completes asynchronously.
     *
     * @param node the {@link UaNode} to start the browse from.
     * @return a CompletableFuture that completes successfully with a List of {@link UaNode}s
     * referenced by {@code node} given the currently configured {@link BrowseOptions} or completes
     * exceptionally if a service-level error occurs.
     * @see #browseNodesAsync(UaNode, BrowseOptions)
     * @see #getBrowseOptions()
     * @see #modifyBrowseOptions(Consumer)
     * @see #setBrowseOptions(BrowseOptions)
     */
    public CompletableFuture<List<? extends UaNode>> browseNodesAsync(UaNode node) {
        return browseNodesAsync(node.getNodeId());
    }

    /**
     * Browse from {@code node} using {@code browseOptions}.
     * <p>
     * This call completes asynchronously.
     *
     * @param node          the {@link UaNode} to start the browse from.
     * @param browseOptions the {@link BrowseOptions} to use.
     * @return a CompletableFuture that completes successfully with a List of {@link UaNode}s
     * referenced by {@code node} given the currently configured {@link BrowseOptions} or completes
     * exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<? extends UaNode>> browseNodesAsync(UaNode node, BrowseOptions browseOptions) {
        return browseNodesAsync(node.getNodeId(), browseOptions);
    }

    /**
     * Browse from {@code nodeId} using the currently configured {@link BrowseOptions}.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId the {@link NodeId} to start the browse from.
     * @return a CompletableFuture that completes successfully with a List of {@link UaNode}s
     * referenced by {@code node} given the currently configured {@link BrowseOptions} or completes
     * exceptionally if a service-level error occurs.
     * @see #browseNodesAsync(UaNode, BrowseOptions)
     * @see #getBrowseOptions()
     * @see #modifyBrowseOptions(Consumer)
     * @see #setBrowseOptions(BrowseOptions)
     */
    public CompletableFuture<List<? extends UaNode>> browseNodesAsync(NodeId nodeId) {
        return browseNodesAsync(nodeId, getBrowseOptions());
    }

    /**
     * Browse from {@code nodeId} using {@code browseOptions}.
     * <p>
     * This call completes asynchronously.
     *
     * @param nodeId        the {@link NodeId} to start the browse from.
     * @param browseOptions the {@link BrowseOptions} to use.
     * @return a CompletableFuture that completes successfully with a List of {@link UaNode}s
     * referenced by {@code node} given the currently configured {@link BrowseOptions} or completes
     * exceptionally if a service-level error occurs.
     */
    public CompletableFuture<List<? extends UaNode>> browseNodesAsync(NodeId nodeId, BrowseOptions browseOptions) {
        BrowseDescription browseDescription = new BrowseDescription(
            nodeId,
            browseOptions.getBrowseDirection(),
            browseOptions.getReferenceTypeId(),
            browseOptions.isIncludeSubtypes(),
            browseOptions.getNodeClassMask(),
            uint(BrowseResultMask.All.getValue())
        );

        CompletableFuture<List<ReferenceDescription>> browse = BrowseHelper.browse(client, browseDescription);

        return browse.thenCompose(references -> {
            List<CompletableFuture<? extends UaNode>> cfs = references.stream()
                .map(reference -> {
                    NodeClass nodeClass = reference.getNodeClass();
                    ExpandedNodeId xNodeId = reference.getNodeId();
                    ExpandedNodeId xTypeDefinitionId = reference.getTypeDefinition();

                    switch (nodeClass) {
                        case Object:
                        case Variable: {
                            CompletableFuture<CompletableFuture<? extends UaNode>> ff =
                                toNodeIdAsync(xNodeId).thenCombine(
                                    toNodeIdAsync(xTypeDefinitionId),
                                    (targetNodeId, typeDefinitionId) -> {
                                        if (nodeClass == NodeClass.Object) {
                                            return getObjectNodeAsync(targetNodeId, typeDefinitionId);
                                        } else {
                                            return getVariableNodeAsync(targetNodeId, typeDefinitionId);
                                        }
                                    }
                                );

                            return unwrap(ff).exceptionally(ex -> {
                                logger.warn("Failed to create Node from Reference to {}", reference.getNodeId(), ex);
                                return null;
                            });
                        }
                        default: {
                            // TODO specialized getNode for other NodeClasses?
                            return toNodeIdAsync(xNodeId).thenCompose(this::getNodeAsync).exceptionally(ex -> {
                                logger.warn("Failed to create Node from Reference to {}", reference.getNodeId(), ex);
                                return null;
                            });
                        }
                    }
                })
                .collect(Collectors.toList());

            return sequence(cfs);
        });
    }

    private static CompletableFuture<List<? extends UaNode>> sequence(
        List<CompletableFuture<? extends UaNode>> cfs
    ) {

        if (cfs.isEmpty()) {
            return completedFuture(Collections.emptyList());
        }

        @SuppressWarnings("rawtypes")
        CompletableFuture[] fa = cfs.toArray(new CompletableFuture[0]);

        return CompletableFuture.allOf(fa).thenApply(v -> {
            List<UaNode> results = new ArrayList<>(cfs.size());

            for (CompletableFuture<? extends UaNode> cf : cfs) {
                UaNode node = cf.join();
                if (node != null) {
                    results.add(node);
                }
            }

            return results;
        });
    }

    private static CompletableFuture<? extends UaNode> unwrap(
        CompletableFuture<CompletableFuture<? extends UaNode>> future
    ) {

        return future.thenCompose(node -> node);
    }

    /**
     * Convert {@code xni} to a {@link NodeId} in the server, reading the namespace table from the
     * server if necessary.
     * <p>
     * Returns {@link NodeId#NULL_VALUE} if the conversion could not be completed for any reason.
     *
     * @param xni the {@link ExpandedNodeId} to convert to a {@link NodeId}.
     * @return a {@link NodeId} local to the server, or {@link NodeId#NULL_VALUE} if conversion
     * could not be completed for any reason.
     */
    public NodeId toNodeId(ExpandedNodeId xni) {
        try {
            return toNodeIdAsync(xni).get();
        } catch (ExecutionException | InterruptedException e) {
            return NodeId.NULL_VALUE;
        }
    }

    /**
     * Convert {@code xni} to a {@link NodeId} in the server, reading the namespace table from the
     * server if necessary.
     * <p>
     * Returns {@link NodeId#NULL_VALUE} if the conversion could not be completed for any reason.
     *
     * @param xni the {@link ExpandedNodeId} to convert to a {@link NodeId}.
     * @return a {@link NodeId} local to the server, or {@link NodeId#NULL_VALUE} if conversion
     * could not be completed for any reason.
     */
    public CompletableFuture<NodeId> toNodeIdAsync(ExpandedNodeId xni) {
        // TODO should this fail with Bad_NodeIdUnknown instead of returning NodeId.NULL_VALUE?
        if (xni.isLocal()) {
            Optional<NodeId> local = xni.toNodeId(client.getNamespaceTable());

            return local.map(CompletableFuture::completedFuture).orElse(
                client.readNamespaceTableAsync()
                    .thenCompose(
                        namespaceTable ->
                            completedFuture(xni.toNodeId(namespaceTable).orElse(NodeId.NULL_VALUE))
                    )
                    .exceptionally(e -> NodeId.NULL_VALUE)
            );
        } else {
            return completedFuture(NodeId.NULL_VALUE);
        }
    }

    /**
     * Get the default {@link BrowseOptions} used during browse calls that don't have an explicit
     * {@link BrowseOptions} parameter.
     *
     * @return the default {@link BrowseOptions}.
     */
    public synchronized BrowseOptions getBrowseOptions() {
        return browseOptions;
    }

    /**
     * Modify the default {@link BrowseOptions} used during browse calls that don't have an
     * explicit {@link BrowseOptions} parameter.
     *
     * @param builderConsumer a {@link Consumer} that receives a {@link BrowseOptions.Builder}.
     */
    public synchronized void modifyBrowseOptions(Consumer<BrowseOptions.Builder> builderConsumer) {
        BrowseOptions.Builder builder = new BrowseOptions.Builder(browseOptions);

        builderConsumer.accept(builder);

        setBrowseOptions(builder.build());
    }

    /**
     * Set a new default {@link BrowseOptions} used during browse calls that don't have an explicit
     * {@link BrowseOptions} parameter.
     *
     * @param browseOptions the new default {@link BrowseOptions}.
     */
    public synchronized void setBrowseOptions(BrowseOptions browseOptions) {
        this.browseOptions = browseOptions;
    }

    /**
     * Get the current {@link NodeCache}.
     *
     * @return the current {@link NodeCache}.
     */
    public synchronized NodeCache getNodeCache() {
        return nodeCache;
    }

    /**
     * Set a new {@link NodeCache}.
     *
     * @param nodeCache a new {@link NodeCache}.
     */
    public synchronized void setNodeCache(NodeCache nodeCache) {
        this.nodeCache = nodeCache;
    }

    private CompletableFuture<NodeId> readTypeDefinition(NodeId nodeId) {
        CompletableFuture<BrowseResult> browseFuture = client.browse(new BrowseDescription(
            nodeId,
            BrowseDirection.Forward,
            Identifiers.HasTypeDefinition,
            false,
            uint(NodeClass.ObjectType.getValue() | NodeClass.VariableType.getValue()),
            uint(BrowseResultMask.All.getValue())
        ));

        return browseFuture.thenCompose(result -> {
            if (result.getStatusCode().isGood()) {
                Optional<ExpandedNodeId> typeDefinitionId = l(result.getReferences())
                    .stream()
                    .filter(r -> Objects.equals(Identifiers.HasTypeDefinition, r.getReferenceTypeId()))
                    .map(ReferenceDescription::getNodeId)
                    .findFirst();

                return typeDefinitionId.map(this::toNodeIdAsync)
                    .orElse(completedFuture(NodeId.NULL_VALUE));
            } else {
                return completedFuture(NodeId.NULL_VALUE);
            }
        });
    }

    /**
     * Create a {@link UaNode} instance without prior knowledge of the {@link NodeClass} or type
     * definition, if applicable.
     *
     * @param nodeId the {@link NodeId} of the Node to create.
     * @return a {@link UaNode} instance for the Node identified by {@code nodeId}.
     */
    private CompletableFuture<? extends UaNode> createNode(NodeId nodeId) {
        CompletableFuture<ReadResponse> future = readAttributes(nodeId, AttributeId.BASE_ATTRIBUTES);

        return future.thenCompose(response -> {
            List<DataValue> results = l(response.getResults());

            return createNodeFromBaseAttributes(nodeId, results);
        });
    }

    private CompletableFuture<? extends UaNode> createNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        StatusCode nodeIdStatusCode = baseAttributeValues.get(0).getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            return failedUaFuture(nodeIdStatusCode);
        }

        Integer nodeClassValue = (Integer) baseAttributeValues.get(1).getValue().getValue();
        if (nodeClassValue == null) {
            return failedUaFuture(StatusCodes.Bad_NodeClassInvalid);
        }
        NodeClass nodeClass = NodeClass.from(nodeClassValue);
        if (nodeClass == null) {
            return failedUaFuture(StatusCodes.Bad_NodeClassInvalid);
        }

        switch (nodeClass) {
            case DataType:
                return createDataTypeNodeFromBaseAttributes(nodeId, baseAttributeValues);
            case Method:
                return createMethodNodeFromBaseAttributes(nodeId, baseAttributeValues);
            case Object:
                return createObjectNodeFromBaseAttributes(nodeId, baseAttributeValues);
            case ObjectType:
                return createObjectTypeNodeFromBaseAttributes(nodeId, baseAttributeValues);
            case ReferenceType:
                return createReferenceTypeNodeFromBaseAttributes(nodeId, baseAttributeValues);
            case Variable:
                return createVariableNodeFromBaseAttributes(nodeId, baseAttributeValues);
            case VariableType:
                return createVariableTypeNodeFromBaseAttributes(nodeId, baseAttributeValues);
            case View:
                return createViewNodeFromBaseAttributes(nodeId, baseAttributeValues);
            default:
                throw new IllegalArgumentException("NodeClass: " + nodeClass);
        }
    }

    private CompletableFuture<UaDataTypeNode> createDataTypeNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.DATA_TYPE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        CompletableFuture<ReadResponse> attributesFuture = readAttributes(nodeId, remainingAttributes);

        return attributesFuture.thenCompose(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            try {
                UaDataTypeNode node = newDataTypeNode(nodeId, attributeValues);

                nodeCache.put(node.getNodeId(), node);

                return completedFuture(node);
            } catch (UaException e) {
                return failedFuture(e);
            }
        });
    }

    private CompletableFuture<UaMethodNode> createMethodNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.METHOD_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        CompletableFuture<ReadResponse> attributesFuture = readAttributes(nodeId, remainingAttributes);

        return attributesFuture.thenCompose(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            try {
                UaMethodNode node = newMethodNode(nodeId, attributeValues);

                nodeCache.put(node.getNodeId(), node);

                return completedFuture(node);
            } catch (UaException e) {
                return failedFuture(e);
            }
        });
    }

    private CompletableFuture<UaObjectNode> createObjectNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.OBJECT_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        CompletableFuture<ReadResponse> attributesFuture = readAttributes(nodeId, remainingAttributes);
        CompletableFuture<NodeId> typeDefinitionFuture = readTypeDefinition(nodeId);

        return CompletableFuture.allOf(attributesFuture, typeDefinitionFuture)
            .thenCompose(ignored -> {
                ReadResponse response = attributesFuture.join();
                NodeId typeDefinitionId = typeDefinitionFuture.join();

                List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
                Collections.addAll(attributeValues, response.getResults());

                try {
                    UaObjectNode node = newObjectNode(nodeId, typeDefinitionId, attributeValues);

                    nodeCache.put(node.getNodeId(), node);

                    return completedFuture(node);
                } catch (UaException e) {
                    return failedFuture(e);
                }
            });
    }

    private CompletableFuture<UaObjectTypeNode> createObjectTypeNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.OBJECT_TYPE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        CompletableFuture<ReadResponse> attributesFuture = readAttributes(nodeId, remainingAttributes);

        return attributesFuture.thenCompose(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            try {
                UaObjectTypeNode node = newObjectTypeNode(nodeId, attributeValues);

                nodeCache.put(node.getNodeId(), node);

                return completedFuture(node);
            } catch (UaException e) {
                return failedFuture(e);
            }
        });
    }

    private CompletableFuture<UaReferenceTypeNode> createReferenceTypeNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.REFERENCE_TYPE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        CompletableFuture<ReadResponse> attributesFuture = readAttributes(nodeId, remainingAttributes);

        return attributesFuture.thenCompose(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            try {
                UaReferenceTypeNode node = newReferenceTypeNode(nodeId, attributeValues);

                nodeCache.put(node.getNodeId(), node);

                return completedFuture(node);
            } catch (UaException e) {
                return failedFuture(e);
            }
        });
    }

    private CompletableFuture<UaVariableNode> createVariableNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.VARIABLE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        CompletableFuture<ReadResponse> attributesFuture = readAttributes(nodeId, remainingAttributes);
        CompletableFuture<NodeId> typeDefinitionFuture = readTypeDefinition(nodeId);

        return CompletableFuture.allOf(attributesFuture, typeDefinitionFuture)
            .thenCompose(ignored -> {
                ReadResponse response = attributesFuture.join();
                NodeId typeDefinitionId = typeDefinitionFuture.join();

                List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
                Collections.addAll(attributeValues, response.getResults());

                try {
                    UaVariableNode node = newVariableNode(nodeId, typeDefinitionId, attributeValues);

                    nodeCache.put(node.getNodeId(), node);

                    return completedFuture(node);
                } catch (UaException e) {
                    return failedFuture(e);
                }
            });
    }

    private CompletableFuture<UaVariableTypeNode> createVariableTypeNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.VARIABLE_TYPE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        CompletableFuture<ReadResponse> attributesFuture = readAttributes(nodeId, remainingAttributes);

        return attributesFuture.thenCompose(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            try {
                UaVariableTypeNode node = newVariableTypeNode(nodeId, attributeValues);

                nodeCache.put(node.getNodeId(), node);

                return completedFuture(node);
            } catch (UaException e) {
                return failedFuture(e);
            }
        });
    }

    private CompletableFuture<UaViewNode> createViewNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.VIEW_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        CompletableFuture<ReadResponse> attributesFuture = readAttributes(nodeId, remainingAttributes);

        return attributesFuture.thenCompose(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            try {
                UaViewNode node = newViewNode(nodeId, attributeValues);

                nodeCache.put(node.getNodeId(), node);

                return completedFuture(node);
            } catch (UaException e) {
                return failedFuture(e);
            }
        });
    }

    private CompletableFuture<ReadResponse> readAttributes(NodeId nodeId, Set<AttributeId> attributeIds) {
        List<ReadValueId> readValueIds = attributeIds.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        return client.read(0.0, TimestampsToReturn.Neither, readValueIds);
    }

    private UaDataTypeNode newDataTypeNode(NodeId nodeId, List<DataValue> attributeValues) throws UaException {
        DataValue nodeIdDataValue = attributeValues.get(0);
        StatusCode nodeIdStatusCode = nodeIdDataValue.getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            throw new UaException(nodeIdStatusCode);
        }

        try {
            NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

            Preconditions.checkArgument(
                nodeClass == NodeClass.DataType,
                "expected NodeClass.DataType, got NodeClass." + nodeClass
            );

            QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
            LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
            LocalizedText description = getAttributeOrNull(attributeValues.get(4), LocalizedText.class);
            UInteger writeMask = getAttributeOrNull(attributeValues.get(5), UInteger.class);
            UInteger userWriteMask = getAttributeOrNull(attributeValues.get(6), UInteger.class);

            Boolean isAbstract = (Boolean) attributeValues.get(7).getValue().getValue();

            return new UaDataTypeNode(
                client,
                nodeId,
                nodeClass,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                isAbstract
            );
        } catch (Throwable t) {
            throw UaException.extract(t)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, t));
        }
    }

    private UaMethodNode newMethodNode(NodeId nodeId, List<DataValue> attributeValues) throws UaException {
        DataValue nodeIdDataValue = attributeValues.get(0);
        StatusCode nodeIdStatusCode = nodeIdDataValue.getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            throw new UaException(nodeIdStatusCode);
        }

        try {
            NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

            Preconditions.checkArgument(
                nodeClass == NodeClass.Method,
                "expected NodeClass.Method, got NodeClass." + nodeClass
            );

            QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
            LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
            LocalizedText description = getAttributeOrNull(attributeValues.get(4), LocalizedText.class);
            UInteger writeMask = getAttributeOrNull(attributeValues.get(5), UInteger.class);
            UInteger userWriteMask = getAttributeOrNull(attributeValues.get(6), UInteger.class);

            Boolean executable = (Boolean) attributeValues.get(7).getValue().getValue();
            Boolean userExecutable = (Boolean) attributeValues.get(8).getValue().getValue();

            return new UaMethodNode(
                client,
                nodeId,
                nodeClass,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                executable,
                userExecutable
            );
        } catch (Throwable t) {
            throw UaException.extract(t)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, t));
        }
    }

    private UaObjectNode newObjectNode(
        NodeId nodeId,
        NodeId typeDefinitionId,
        List<DataValue> attributeValues
    ) throws UaException {

        DataValue nodeIdDataValue = attributeValues.get(0);
        StatusCode nodeIdStatusCode = nodeIdDataValue.getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            throw new UaException(nodeIdStatusCode);
        }

        try {
            NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

            Preconditions.checkArgument(
                nodeClass == NodeClass.Object,
                "expected NodeClass.Object, got NodeClass." + nodeClass
            );

            QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
            LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
            LocalizedText description = getAttributeOrNull(attributeValues.get(4), LocalizedText.class);
            UInteger writeMask = getAttributeOrNull(attributeValues.get(5), UInteger.class);
            UInteger userWriteMask = getAttributeOrNull(attributeValues.get(6), UInteger.class);

            UByte eventNotifier = (UByte) attributeValues.get(7).getValue().getValue();

            ObjectNodeConstructor constructor = client.getObjectTypeManager()
                .getNodeConstructor(typeDefinitionId)
                .orElse(UaObjectNode::new);

            return constructor.apply(
                client,
                nodeId,
                nodeClass,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                eventNotifier
            );
        } catch (Throwable t) {
            throw UaException.extract(t)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, t));
        }
    }

    private UaObjectTypeNode newObjectTypeNode(NodeId nodeId, List<DataValue> attributeValues) throws UaException {
        DataValue nodeIdDataValue = attributeValues.get(0);
        StatusCode nodeIdStatusCode = nodeIdDataValue.getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            throw new UaException(nodeIdStatusCode);
        }

        try {
            NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

            Preconditions.checkArgument(
                nodeClass == NodeClass.ObjectType,
                "expected NodeClass.ObjectType, got NodeClass." + nodeClass
            );

            QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
            LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
            LocalizedText description = getAttributeOrNull(attributeValues.get(4), LocalizedText.class);
            UInteger writeMask = getAttributeOrNull(attributeValues.get(5), UInteger.class);
            UInteger userWriteMask = getAttributeOrNull(attributeValues.get(6), UInteger.class);

            Boolean isAbstract = (Boolean) attributeValues.get(7).getValue().getValue();

            return new UaObjectTypeNode(
                client,
                nodeId,
                nodeClass,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                isAbstract
            );
        } catch (Throwable t) {
            throw UaException.extract(t)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, t));
        }
    }

    private UaReferenceTypeNode newReferenceTypeNode(
        NodeId nodeId,
        List<DataValue> attributeValues
    ) throws UaException {

        DataValue nodeIdDataValue = attributeValues.get(0);
        StatusCode nodeIdStatusCode = nodeIdDataValue.getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            throw new UaException(nodeIdStatusCode);
        }

        try {
            NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

            Preconditions.checkArgument(
                nodeClass == NodeClass.ReferenceType,
                "expected NodeClass.ReferenceType, got NodeClass." + nodeClass
            );

            QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
            LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
            LocalizedText description = getAttributeOrNull(attributeValues.get(4), LocalizedText.class);
            UInteger writeMask = getAttributeOrNull(attributeValues.get(5), UInteger.class);
            UInteger userWriteMask = getAttributeOrNull(attributeValues.get(6), UInteger.class);

            Boolean isAbstract = (Boolean) attributeValues.get(7).getValue().getValue();
            Boolean symmetric = (Boolean) attributeValues.get(8).getValue().getValue();
            LocalizedText inverseName = getAttributeOrNull(attributeValues.get(9), LocalizedText.class);

            return new UaReferenceTypeNode(
                client,
                nodeId,
                nodeClass,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                isAbstract,
                symmetric,
                inverseName
            );
        } catch (Throwable t) {
            throw UaException.extract(t)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, t));
        }
    }

    private UaVariableNode newVariableNode(
        NodeId nodeId,
        NodeId typeDefinitionId,
        List<DataValue> attributeValues
    ) throws UaException {

        DataValue nodeIdDataValue = attributeValues.get(0);
        StatusCode nodeIdStatusCode = nodeIdDataValue.getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            throw new UaException(nodeIdStatusCode);
        }

        try {
            NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

            Preconditions.checkArgument(
                nodeClass == NodeClass.Variable,
                "expected NodeClass.Variable, got NodeClass." + nodeClass
            );

            QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
            LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
            LocalizedText description = getAttributeOrNull(attributeValues.get(4), LocalizedText.class);
            UInteger writeMask = getAttributeOrNull(attributeValues.get(5), UInteger.class);
            UInteger userWriteMask = getAttributeOrNull(attributeValues.get(6), UInteger.class);

            DataValue value = attributeValues.get(7);
            NodeId dataType = (NodeId) attributeValues.get(8).getValue().getValue();
            Integer valueRank = (Integer) attributeValues.get(9).getValue().getValue();
            UInteger[] arrayDimensions = getAttributeOrNull(attributeValues.get(10), UInteger[].class);
            UByte accessLevel = (UByte) attributeValues.get(11).getValue().getValue();
            UByte userAccessLevel = (UByte) attributeValues.get(12).getValue().getValue();
            Double minimumSamplingInterval = getAttributeOrNull(attributeValues.get(13), Double.class);
            Boolean historizing = (Boolean) attributeValues.get(14).getValue().getValue();

            VariableTypeManager.VariableNodeConstructor constructor = client.getVariableTypeManager()
                .getNodeConstructor(typeDefinitionId)
                .orElse(UaVariableNode::new);

            return constructor.apply(
                client,
                nodeId,
                nodeClass,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                value,
                dataType,
                valueRank,
                arrayDimensions,
                accessLevel,
                userAccessLevel,
                minimumSamplingInterval,
                historizing
            );
        } catch (Throwable t) {
            throw UaException.extract(t)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, t));
        }
    }

    private UaVariableTypeNode newVariableTypeNode(NodeId nodeId, List<DataValue> attributeValues) throws UaException {
        DataValue nodeIdDataValue = attributeValues.get(0);
        StatusCode nodeIdStatusCode = nodeIdDataValue.getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            throw new UaException(nodeIdStatusCode);
        }

        try {
            NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

            Preconditions.checkArgument(
                nodeClass == NodeClass.VariableType,
                "expected NodeClass.VariableType, got NodeClass." + nodeClass
            );

            QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
            LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
            LocalizedText description = getAttributeOrNull(attributeValues.get(4), LocalizedText.class);
            UInteger writeMask = getAttributeOrNull(attributeValues.get(5), UInteger.class);
            UInteger userWriteMask = getAttributeOrNull(attributeValues.get(6), UInteger.class);

            DataValue value = attributeValues.get(7);
            NodeId dataType = (NodeId) attributeValues.get(8).getValue().getValue();
            Integer valueRank = (Integer) attributeValues.get(9).getValue().getValue();
            UInteger[] arrayDimensions = getAttributeOrNull(attributeValues.get(10), UInteger[].class);
            Boolean isAbstract = (Boolean) attributeValues.get(11).getValue().getValue();

            return new UaVariableTypeNode(
                client,
                nodeId,
                nodeClass,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                value,
                dataType,
                valueRank,
                arrayDimensions,
                isAbstract
            );
        } catch (Throwable t) {
            throw UaException.extract(t)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, t));
        }
    }

    private UaViewNode newViewNode(NodeId nodeId, List<DataValue> attributeValues) throws UaException {
        DataValue nodeIdDataValue = attributeValues.get(0);
        StatusCode nodeIdStatusCode = nodeIdDataValue.getStatusCode();
        if (nodeIdStatusCode != null && nodeIdStatusCode.isBad()) {
            throw new UaException(nodeIdStatusCode);
        }

        try {
            NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

            Preconditions.checkArgument(
                nodeClass == NodeClass.View,
                "expected NodeClass.View, got NodeClass." + nodeClass
            );

            QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
            LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
            LocalizedText description = getAttributeOrNull(attributeValues.get(4), LocalizedText.class);
            UInteger writeMask = getAttributeOrNull(attributeValues.get(5), UInteger.class);
            UInteger userWriteMask = getAttributeOrNull(attributeValues.get(6), UInteger.class);

            Boolean containsNoLoops = (Boolean) attributeValues.get(7).getValue().getValue();
            UByte eventNotifier = (UByte) attributeValues.get(8).getValue().getValue();

            return new UaViewNode(
                client,
                nodeId,
                nodeClass,
                browseName,
                displayName,
                description,
                writeMask,
                userWriteMask,
                containsNoLoops,
                eventNotifier
            );
        } catch (Throwable t) {
            throw UaException.extract(t)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, t));
        }
    }

    @Nullable
    private static <T> T getAttributeOrNull(DataValue value, Class<T> attributeClazz) {
        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null && statusCode.isBad()) {
            return null;
        } else {
            Object attributeValue = value.getValue().getValue();

            try {
                return attributeClazz.cast(attributeValue);
            } catch (ClassCastException e) {
                return null;
            }
        }
    }

    public static class BrowseOptions {

        private final BrowseDirection browseDirection;
        private final NodeId referenceTypeId;
        private final boolean includeSubtypes;
        private final UInteger nodeClassMask;

        public BrowseOptions() {
            this(BrowseDirection.Forward, Identifiers.HierarchicalReferences, true, uint(0xFF));
        }

        public BrowseOptions(
            BrowseDirection browseDirection,
            NodeId referenceTypeId,
            boolean includeSubtypes,
            UInteger nodeClassMask
        ) {

            this.browseDirection = browseDirection;
            this.referenceTypeId = referenceTypeId;
            this.includeSubtypes = includeSubtypes;
            this.nodeClassMask = nodeClassMask;
        }

        public BrowseDirection getBrowseDirection() {
            return browseDirection;
        }

        public NodeId getReferenceTypeId() {
            return referenceTypeId;
        }

        public boolean isIncludeSubtypes() {
            return includeSubtypes;
        }

        public UInteger getNodeClassMask() {
            return nodeClassMask;
        }

        public BrowseOptions copy(Consumer<Builder> builderConsumer) {
            Builder builder = new Builder(this);

            builderConsumer.accept(builder);

            return builder.build();
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private BrowseDirection browseDirection = BrowseDirection.Forward;
            private NodeId referenceTypeId = Identifiers.HierarchicalReferences;
            private boolean includeSubtypes = true;
            private UInteger nodeClassMask = uint(0xFF);

            private Builder() {}

            private Builder(BrowseOptions browseOptions) {
                this.browseDirection = browseOptions.getBrowseDirection();
                this.referenceTypeId = browseOptions.getReferenceTypeId();
                this.includeSubtypes = browseOptions.isIncludeSubtypes();
                this.nodeClassMask = browseOptions.getNodeClassMask();
            }

            public Builder setBrowseDirection(BrowseDirection browseDirection) {
                this.browseDirection = browseDirection;
                return this;
            }

            public Builder setReferenceType(BuiltinReferenceType referenceType) {
                return setReferenceType(referenceType.getNodeId());
            }

            public Builder setReferenceType(NodeId referenceTypeId) {
                this.referenceTypeId = referenceTypeId;
                return this;
            }

            public Builder setIncludeSubtypes(boolean includeSubtypes) {
                this.includeSubtypes = includeSubtypes;
                return this;
            }

            public Builder setNodeClassMask(UInteger nodeClassMask) {
                this.nodeClassMask = nodeClassMask;
                return this;
            }

            public Builder setNodeClassMask(Set<NodeClass> nodeClasses) {
                int mask = 0;
                for (NodeClass nodeClass : nodeClasses) {
                    mask |= nodeClass.getValue();
                }
                return setNodeClassMask(uint(mask));
            }

            public BrowseOptions build() {
                return new BrowseOptions(browseDirection, referenceTypeId, includeSubtypes, nodeClassMask);
            }

        }

    }

}

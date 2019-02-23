/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ViewNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public interface AddressSpace {

    /**
     * Get a {@link UaNode} instance for the provided {@link NodeId}.
     * <p>
     * If the node is an ObjectNode or VariableNode and has a type registered, the instance will be a concrete
     * implementation representing the registered type.
     *
     * @param nodeId the {@link NodeId} of the instance.
     * @return a {@link UaNode} instance for the provided {@link NodeId}.
     */
    CompletableFuture<UaNode> getNodeInstance(NodeId nodeId);

    /**
     * Get an {@link ObjectNode} instance for the provided {@link NodeId}.
     * <p>
     * If the node is an ObjectNode with a registered type, the instance will be a concrete implementation
     * representing the registered type.
     *
     * @param nodeId the {@link NodeId} of the instance.
     * @return an {@link ObjectNode} instance for the provided {@link NodeId}.
     */
    CompletableFuture<ObjectNode> getObjectNode(NodeId nodeId);

    /**
     * Get an {@link ObjectNode} instance for the provided {@link NodeId}, asserting the concrete implementation will
     * be assignable from the provided Class.
     *
     * @param nodeId    the {@link NodeId} of the instance.
     * @param nodeClazz the expected Class of the instance.
     * @return an {@link ObjectNode} instance for the provided {@link NodeId} having the provided concrete
     * implementation.
     */
    <T extends ObjectNode> CompletableFuture<T> getObjectNode(NodeId nodeId, Class<T> nodeClazz);

    /**
     * Get a {@link VariableNode} instance for the provided {@link NodeId}.
     * <p>
     * If the node is a VariableNode with a registered type, the instance will be a concrete implementation
     * representing the registered type.
     *
     * @param nodeId the {@link NodeId} of the instance.
     * @return a {@link VariableNode} instance for the provided {@link NodeId}.
     */
    CompletableFuture<VariableNode> getVariableNode(NodeId nodeId);

    /**
     * Get a {@link VariableNode} instance for the provided {@link NodeId}, asserting the concrete implementation will
     * be assignable from the provided Class.
     *
     * @param nodeId    the {@link NodeId} of the instance.
     * @param nodeClazz the expected Class of the instance.
     * @return a {@link VariableNode} instance for the provided {@link NodeId} having the provided concrete
     * implementation.
     */
    <T extends VariableNode> CompletableFuture<T> getVariableNode(NodeId nodeId, Class<T> nodeClazz);

    /**
     * Create a {@link Node} instance for the given NodeId.
     * <p>
     * The {@link NodeClass} will be read and the appropriate node instance created.
     * <p>
     * The type registry is not used if the node is a VariableNode or ObjectNode.
     *
     * @param nodeId the {@link NodeId} of the instance.
     * @return a {@link Node} instance for the provided {@link NodeId}.
     */
    CompletableFuture<? extends Node> createNode(NodeId nodeId);

    DataTypeNode createDataTypeNode(NodeId nodeId);

    MethodNode createMethodNode(NodeId nodeId);

    ObjectNode createObjectNode(NodeId nodeId);

    ObjectTypeNode createObjectTypeNode(NodeId nodeId);

    ReferenceTypeNode createReferenceTypeNode(NodeId nodeId);

    VariableNode createVariableNode(NodeId nodeId);

    VariableTypeNode createVariableTypeNode(NodeId nodeId);

    ViewNode createViewNode(NodeId nodeId);

    /**
     * Browse forward for hierarchical references to Method, Object, and Variable nodes.
     *
     * @param nodeId the {@link NodeId} to browse from.
     * @return a list of {@link Node}s found by following forward hierarchical references.
     */
    CompletableFuture<List<Node>> browse(NodeId nodeId);

    /**
     * Browse forward for hierarchical references to Method, Object, and Variable nodes.
     *
     * @param node the {@link Node} to browse from.
     * @return a list of {@link Node}s found by following forward hierarchical references.
     */
    default CompletableFuture<List<Node>> browseNode(Node node) {
        return node.getNodeId().thenCompose(this::browse);
    }

}

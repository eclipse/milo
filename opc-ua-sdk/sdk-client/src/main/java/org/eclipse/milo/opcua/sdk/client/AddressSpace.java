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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface AddressSpace {

    UaNode getNode(NodeId nodeId);
    CompletableFuture<UaNode> getNodeAsync(NodeId nodeId);

    UaObjectNode getObjectNode(NodeId nodeId);
    UaObjectNode getObjectNode(NodeId nodeId, NodeId typeDefinitionId);
    CompletableFuture<UaObjectNode> getObjectNodeAsync(NodeId nodeId);
    CompletableFuture<UaObjectNode> getObjectNodeAsync(NodeId nodeId, NodeId typeDefinitionId);

    UaVariableNode getVariableNode(NodeId nodeId);
    UaVariableNode getVariableNode(NodeId nodeId, NodeId typeDefinitionId);
    CompletableFuture<UaVariableNode> getVariableNodeAsync(NodeId nodeId);
    CompletableFuture<UaVariableNode> getVariableNodeAsync(NodeId nodeId, NodeId typeDefinitionId);

    List<UaNode> browseNode(UaNode node);
    List<UaNode> browseNode(NodeId nodeId);

    CompletableFuture<NodeId> localizeAsync(ExpandedNodeId nodeId);

    void setBrowseNodeClassMask(UInteger mask);
    void setBrowseReferenceType(NodeId referenceTypeId);
    void setBrowseSubtypes(boolean browseSubtypes);

}

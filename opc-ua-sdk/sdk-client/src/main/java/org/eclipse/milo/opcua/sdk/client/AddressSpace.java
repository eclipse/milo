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

public class AddressSpace {

    private final OpcUaClient client;

    public AddressSpace(OpcUaClient client) {
        this.client = client;
    }

    public UaNode getNode(NodeId nodeId) {
        return null;
    }
    public CompletableFuture<UaNode> getNodeAsync(NodeId nodeId) {
        return null;
    }

    public UaObjectNode getObjectNode(NodeId nodeId) {
        return null;
    }

    public UaObjectNode getObjectNode(NodeId nodeId, NodeId typeDefinitionId) {
        return null;
    }

    public CompletableFuture<UaObjectNode> getObjectNodeAsync(NodeId nodeId) {
        return null;
    }

    public CompletableFuture<UaObjectNode> getObjectNodeAsync(NodeId nodeId, NodeId typeDefinitionId) {
        return null;
    }

    public UaVariableNode getVariableNode(NodeId nodeId) {
        return null;
    }

    public UaVariableNode getVariableNode(NodeId nodeId, NodeId typeDefinitionId) {
        return null;
    }

    public CompletableFuture<UaVariableNode> getVariableNodeAsync(NodeId nodeId) {
        return null;
    }

    public CompletableFuture<UaVariableNode> getVariableNodeAsync(NodeId nodeId, NodeId typeDefinitionId) {
        return null;
    }

    public List<UaNode> browseNode(UaNode node) {
        return null;
    }

    public List<UaNode> browseNode(NodeId nodeId) {
        return null;
    }

    public CompletableFuture<NodeId> localizeAsync(ExpandedNodeId nodeId) {
        return null;
    }

    public void setBrowseNodeClassMask(UInteger mask) {

    }

    public void setBrowseReferenceType(NodeId referenceTypeId) {

    }

    public void setBrowseSubtypes(boolean browseSubtypes) {

    }

}

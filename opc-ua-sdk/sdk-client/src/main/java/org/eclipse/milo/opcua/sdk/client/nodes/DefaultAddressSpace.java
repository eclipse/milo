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

package org.eclipse.milo.opcua.sdk.client.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.AddressSpace;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaMethodNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaObjectNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaVariableNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaViewNode;
import org.eclipse.milo.opcua.sdk.client.nodes.attached.AttachedDataTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.attached.AttachedMethodNode;
import org.eclipse.milo.opcua.sdk.client.nodes.attached.AttachedObjectNode;
import org.eclipse.milo.opcua.sdk.client.nodes.attached.AttachedObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.attached.AttachedReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.attached.AttachedVariableNode;
import org.eclipse.milo.opcua.sdk.client.nodes.attached.AttachedVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.attached.AttachedViewNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

import static com.google.common.collect.Lists.newArrayList;

public class DefaultAddressSpace implements AddressSpace {

    private final OpcUaClient client;

    public DefaultAddressSpace(OpcUaClient client) {
        this.client = client;
    }

    @Override
    public CompletableFuture<UaNode> getNode(NodeId nodeId) {
        ReadValueId readValueId = new ReadValueId(
            nodeId, AttributeId.NodeClass.uid(), null, QualifiedName.NULL_VALUE);

        CompletableFuture<ReadResponse> future =
            client.read(0.0, TimestampsToReturn.Neither, newArrayList(readValueId));

        return future.thenCompose(response -> {
            DataValue value = response.getResults()[0];
            NodeClass nodeClass = (NodeClass) value.getValue().getValue();

            if (nodeClass != null) {
                client.getNodeCache().putAttribute(nodeId, AttributeId.NodeClass, value);

                return CompletableFuture.completedFuture(createNode(nodeId, nodeClass));
            } else {
                return failedFuture(new UaException(value.getStatusCode(), "NodeClass was null"));
            }
        });
    }

    @Override
    public UaDataTypeNode getDataTypeNode(NodeId nodeId) {
        return new AttachedDataTypeNode(client, nodeId);
    }

    @Override
    public UaMethodNode getMethodNode(NodeId nodeId) {
        return new AttachedMethodNode(client, nodeId);
    }

    @Override
    public UaObjectNode getObjectNode(NodeId nodeId) {
        return new AttachedObjectNode(client, nodeId);
    }

    @Override
    public UaObjectTypeNode getObjectTypeNode(NodeId nodeId) {
        return new AttachedObjectTypeNode(client, nodeId);
    }

    @Override
    public UaReferenceTypeNode getReferenceTypeNode(NodeId nodeId) {
        return new AttachedReferenceTypeNode(client, nodeId);
    }

    @Override
    public UaVariableNode getVariableNode(NodeId nodeId) {
        return new AttachedVariableNode(client, nodeId);
    }

    @Override
    public UaVariableTypeNode getVariableTypeNode(NodeId nodeId) {
        return new AttachedVariableTypeNode(client, nodeId);
    }

    @Override
    public UaViewNode getViewNode(NodeId nodeId) {
        return new AttachedViewNode(client, nodeId);
    }

    private UaNode createNode(NodeId nodeId, NodeClass nodeClass) {
        switch (nodeClass) {
            case DataType:
                return new AttachedDataTypeNode(client, nodeId);
            case Method:
                return new AttachedMethodNode(client, nodeId);
            case Object:
                return new AttachedObjectNode(client, nodeId);
            case ObjectType:
                return new AttachedObjectTypeNode(client, nodeId);
            case ReferenceType:
                return new AttachedReferenceTypeNode(client, nodeId);
            case Variable:
                return new AttachedVariableNode(client, nodeId);
            case VariableType:
                return new AttachedVariableTypeNode(client, nodeId);
            case View:
                return new AttachedViewNode(client, nodeId);
            default:
                throw new IllegalStateException("unhandled NodeClass: " + nodeClass);
        }
    }

    private static <T> CompletableFuture<T> failedFuture(UaException exception) {
        CompletableFuture<T> f = new CompletableFuture<>();
        f.completeExceptionally(exception);
        return f;
    }

}

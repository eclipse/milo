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

package org.eclipse.milo.opcua.sdk.client;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.api.AddressSpace;
import org.eclipse.milo.opcua.sdk.client.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ViewNode;
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
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
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

import static com.google.common.collect.Lists.newArrayList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class DefaultAddressSpace implements AddressSpace {

    private final OpcUaClient client;

    public DefaultAddressSpace(OpcUaClient client) {
        this.client = client;
    }

    @Override
    public CompletableFuture<UaNode> createNode(NodeId nodeId) {
        ReadValueId readValueId = new ReadValueId(
            nodeId, AttributeId.NodeClass.uid(), null, QualifiedName.NULL_VALUE);

        CompletableFuture<ReadResponse> future =
            client.read(0.0, TimestampsToReturn.Neither, newArrayList(readValueId));

        return future.thenCompose(response -> {
            DataValue value = l(response.getResults()).get(0);
            NodeClass nodeClass = NodeClass.from((Integer) value.getValue().getValue());

            if (nodeClass != null) {
                client.getNodeCache().putAttribute(nodeId, AttributeId.NodeClass, value);

                return completedFuture(createNode(nodeId, nodeClass));
            } else {
                return failedFuture(new UaException(value.getStatusCode(), "NodeClass was null"));
            }
        });
    }

    @Override
    public CompletableFuture<UaNode> getNodeInstance(NodeId nodeId) {
        return createNode(nodeId).thenCompose(node -> {
            if (node instanceof UaVariableNode) {
                return getVariableNodeInstance(nodeId, (UaVariableNode) node);
            } else if (node instanceof UaObjectNode) {
                return getObjectNodeInstance(nodeId, (UaObjectNode) node);
            } else {
                return completedFuture(node);
            }
        });
    }

    private CompletableFuture<UaNode> getObjectNodeInstance(NodeId nodeId, UaObjectNode node) {
        return node.getTypeDefinition()
            .thenCompose(Node::getNodeId)
            .thenCompose(
                typeDefinition ->
                    client.getTypeRegistry().getNodeFactory(typeDefinition)
                        .map(f -> completedFuture(f.apply(client, nodeId)))
                        .orElse(completedFuture(node))
            );
    }

    private CompletableFuture<UaNode> getVariableNodeInstance(NodeId nodeId, UaVariableNode node) {
        return node.getTypeDefinition()
            .thenCompose(Node::getNodeId)
            .thenCompose(
                typeDefinition ->
                    client.getTypeRegistry().getNodeFactory(typeDefinition)
                        .map(f -> completedFuture(f.apply(client, nodeId)))
                        .orElse(completedFuture(node))
            );
    }

    @Override
    public CompletableFuture<ObjectNode> getObjectNode(NodeId nodeId) {
        return getNodeInstance(nodeId).thenCompose(node -> {
            if (ObjectNode.class.isAssignableFrom(node.getClass())) {
                return completedFuture(ObjectNode.class.cast(node));
            } else {
                return failedFuture(
                    new UaException(
                        StatusCodes.Bad_InvalidArgument,
                        String.format("ObjectNode not assignable from %s", node.getClass()))
                );
            }
        });
    }

    @Override
    public <T extends ObjectNode> CompletableFuture<T> getObjectNode(NodeId nodeId, Class<T> nodeClazz) {
        return getNodeInstance(nodeId).thenCompose(node -> {
            if (nodeClazz.isAssignableFrom(node.getClass())) {
                return completedFuture(nodeClazz.cast(node));
            } else {
                return failedFuture(
                    new UaException(
                        StatusCodes.Bad_InvalidArgument,
                        String.format("%s not assignable from %s", nodeClazz, node.getClass()))
                );
            }
        });
    }

    @Override
    public CompletableFuture<VariableNode> getVariableNode(NodeId nodeId) {
        return getNodeInstance(nodeId).thenCompose(node -> {
            if (VariableNode.class.isAssignableFrom(node.getClass())) {
                return completedFuture(VariableNode.class.cast(node));
            } else {
                return failedFuture(
                    new UaException(
                        StatusCodes.Bad_InvalidArgument,
                        String.format("VariableNode not assignable from %s", node.getClass()))
                );
            }
        });
    }

    @Override
    public <T extends VariableNode> CompletableFuture<T> getVariableNode(NodeId nodeId, Class<T> nodeClazz) {
        return getNodeInstance(nodeId).thenCompose(node -> {
            if (nodeClazz.isAssignableFrom(node.getClass())) {
                return completedFuture(nodeClazz.cast(node));
            } else {
                return failedFuture(
                    new UaException(
                        StatusCodes.Bad_InvalidArgument,
                        String.format("%s not assignable from %s", nodeClazz, node.getClass()))
                );
            }
        });
    }

    @Override
    public DataTypeNode createDataTypeNode(NodeId nodeId) {
        return new UaDataTypeNode(client, nodeId);
    }

    @Override
    public MethodNode createMethodNode(NodeId nodeId) {
        return new UaMethodNode(client, nodeId);
    }

    @Override
    public ObjectNode createObjectNode(NodeId nodeId) {
        return new UaObjectNode(client, nodeId);
    }

    @Override
    public ObjectTypeNode createObjectTypeNode(NodeId nodeId) {
        return new UaObjectTypeNode(client, nodeId);
    }

    @Override
    public ReferenceTypeNode createReferenceTypeNode(NodeId nodeId) {
        return new UaReferenceTypeNode(client, nodeId);
    }

    @Override
    public UaVariableNode createVariableNode(NodeId nodeId) {
        return new UaVariableNode(client, nodeId);
    }

    @Override
    public VariableTypeNode createVariableTypeNode(NodeId nodeId) {
        return new UaVariableTypeNode(client, nodeId);
    }

    @Override
    public ViewNode createViewNode(NodeId nodeId) {
        return new UaViewNode(client, nodeId);
    }

    @Override
    public CompletableFuture<List<Node>> browse(NodeId nodeId) {
        UInteger nodeClassMask = uint(
            NodeClass.Method.getValue() |
                NodeClass.Object.getValue() |
                NodeClass.Variable.getValue());

        UInteger resultMask = uint(BrowseResultMask.All.getValue());

        CompletableFuture<BrowseResult> future = client.browse(new BrowseDescription(
            nodeId,
            BrowseDirection.Forward,
            Identifiers.HierarchicalReferences,
            true,
            nodeClassMask,
            resultMask
        ));

        return future.thenApply(
            result -> {
                List<ReferenceDescription> references = l(result.getReferences());

                return references.stream()
                    .flatMap(r -> opt2stream(
                        r.getNodeId().local().map(
                            id -> createNode(id, r.getNodeClass()))))
                    .collect(toList());
            }
        );
    }

    private UaNode createNode(NodeId nodeId, NodeClass nodeClass) {
        switch (nodeClass) {
            case DataType:
                return new UaDataTypeNode(client, nodeId);
            case Method:
                return new UaMethodNode(client, nodeId);
            case Object:
                return new UaObjectNode(client, nodeId);
            case ObjectType:
                return new UaObjectTypeNode(client, nodeId);
            case ReferenceType:
                return new UaReferenceTypeNode(client, nodeId);
            case Variable:
                return new UaVariableNode(client, nodeId);
            case VariableType:
                return new UaVariableTypeNode(client, nodeId);
            case View:
                return new UaViewNode(client, nodeId);
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

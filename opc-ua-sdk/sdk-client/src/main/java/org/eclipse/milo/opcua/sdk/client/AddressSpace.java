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

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
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
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
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
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class AddressSpace {

    private volatile Duration expireAfter = Duration.ofMinutes(2);
    private volatile long maximumSize = 1024;
    private final Cache<NodeId, UaNode> cache = buildCache();

    private final OpcUaClient client;

    public AddressSpace(OpcUaClient client) {
        this.client = client;
    }

    public UaNode getNode(NodeId nodeId) throws UaException {
        try {
            return getNodeAsync(nodeId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends UaNode> getNodeAsync(NodeId nodeId) {
        UaNode cachedNode = cache.getIfPresent(nodeId);

        if (cachedNode != null) {
            return completedFuture(cachedNode);
        } else {
            return createNode(nodeId).whenComplete((node, ex) -> {
                if (node != null) {
                    cache.put(nodeId, node);
                }
            });
        }
    }

    public UaObjectNode getObjectNode(NodeId nodeId) throws UaException {
        try {
            return getObjectNodeAsync(nodeId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public UaObjectNode getObjectNode(NodeId nodeId, NodeId typeDefinitionId) throws UaException {
        try {
            return getObjectNodeAsync(nodeId, typeDefinitionId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<UaObjectNode> getObjectNodeAsync(NodeId nodeId) {
        UaNode cachedNode = cache.getIfPresent(nodeId);

        if (cachedNode instanceof UaObjectNode) {
            return completedFuture((UaObjectNode) cachedNode);
        } else {
            CompletableFuture<NodeId> typeDefinitionFuture = readTypeDefinition(nodeId);

            return typeDefinitionFuture.thenCompose(typeDefinitionId -> getObjectNodeAsync(nodeId, typeDefinitionId));
        }
    }

    public CompletableFuture<UaObjectNode> getObjectNodeAsync(NodeId nodeId, NodeId typeDefinitionId) {
        UaNode cachedNode = cache.getIfPresent(nodeId);

        if (cachedNode instanceof UaObjectNode) {
            return completedFuture((UaObjectNode) cachedNode);
        } else {
            List<ReadValueId> readValueIds = AttributeId.OBJECT_ATTRIBUTES.stream()
                .map(id ->
                    new ReadValueId(
                        nodeId,
                        id.uid(),
                        null,
                        QualifiedName.NULL_VALUE
                    )
                )
                .collect(Collectors.toList());

            CompletableFuture<ReadResponse> future = client.read(
                0.0,
                TimestampsToReturn.Neither,
                readValueIds
            );

            return future.thenApply(response -> {
                List<DataValue> attributeValues = l(response.getResults());

                UaObjectNode node = newObjectNode(nodeId, typeDefinitionId, attributeValues);

                cache.put(node.getNodeId(), node);

                return node;
            });
        }
    }

    public UaVariableNode getVariableNode(NodeId nodeId) throws UaException {
        try {
            return getVariableNodeAsync(nodeId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public UaVariableNode getVariableNode(NodeId nodeId, NodeId typeDefinitionId) throws UaException {
        try {
            return getVariableNodeAsync(nodeId, typeDefinitionId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<UaVariableNode> getVariableNodeAsync(NodeId nodeId) {
        UaNode cachedNode = cache.getIfPresent(nodeId);

        if (cachedNode instanceof UaVariableNode) {
            return completedFuture((UaVariableNode) cachedNode);
        } else {
            CompletableFuture<NodeId> typeDefinitionFuture = readTypeDefinition(nodeId);

            return typeDefinitionFuture.thenCompose(typeDefinitionId -> getVariableNodeAsync(nodeId, typeDefinitionId));
        }
    }

    public CompletableFuture<UaVariableNode> getVariableNodeAsync(NodeId nodeId, NodeId typeDefinitionId) {
        UaNode cachedNode = cache.getIfPresent(nodeId);

        if (cachedNode instanceof UaVariableNode) {
            return completedFuture((UaVariableNode) cachedNode);
        } else {
            List<ReadValueId> readValueIds = AttributeId.VARIABLE_ATTRIBUTES.stream()
                .map(id ->
                    new ReadValueId(
                        nodeId,
                        id.uid(),
                        null,
                        QualifiedName.NULL_VALUE
                    )
                )
                .collect(Collectors.toList());

            CompletableFuture<ReadResponse> future = client.read(
                0.0,
                TimestampsToReturn.Neither,
                readValueIds
            );

            return future.thenApply(response -> {
                List<DataValue> attributeValues = l(response.getResults());

                UaVariableNode node = newVariableNode(nodeId, typeDefinitionId, attributeValues);

                cache.put(node.getNodeId(), node);

                return node;
            });
        }
    }

    public List<UaNode> browseNode(UaNode node) throws UaException {
        return null;
    }

    public List<UaNode> browseNode(NodeId nodeId) throws UaException {
        return null;
    }

    public CompletableFuture<List<UaNode>> browseNodeAsync(UaNode node) {
        return null;
    }

    public CompletableFuture<List<UaNode>> browseNodeAsync(NodeId nodeId) {
        return null;
    }

    public NodeId localize(ExpandedNodeId nodeId) {
        return null;
    }

    public CompletableFuture<NodeId> localizeAsync(ExpandedNodeId nodeId) {
        return null;
    }

    public void setBrowseNodeClassMask(UInteger mask) {

    }

    public void setBrowseNodeClassMask(Set<NodeClass> nodeClasses) {
        int mask = 0;
        for (NodeClass nodeClass : nodeClasses) {
            mask |= nodeClass.getValue();
        }
        setBrowseNodeClassMask(uint(mask));
    }

    public void setBrowseReferenceType(NodeId referenceTypeId) {

    }

    public void setBrowseSubtypes(boolean browseSubtypes) {

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

        return browseFuture.thenApply(result -> {
            if (result.getStatusCode().isGood()) {
                Optional<ExpandedNodeId> typeDefinitionId = Arrays.stream(result.getReferences())
                    .filter(r -> Objects.equals(Identifiers.HasTypeDefinition, r.getReferenceTypeId()))
                    .map(ReferenceDescription::getNodeId)
                    .findFirst();

                // TODO better xni -> local function that looks in the current
                //  namespace table and reads from server as a fallback.
                return typeDefinitionId
                    .flatMap(xni -> xni.local(client.getNamespaceTable()))
                    .orElse(NodeId.NULL_VALUE);
            } else {
                return NodeId.NULL_VALUE;
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
        List<ReadValueId> readValueIds = AttributeId.BASE_ATTRIBUTES.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> future = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        return future.thenCompose(response -> {
            List<DataValue> results = l(response.getResults());

            Integer nodeClassValue = (Integer) results.get(1).getValue().getValue();
            if (nodeClassValue == null) {
                return FutureUtils.failedUaFuture(StatusCodes.Bad_NodeClassInvalid);
            }
            NodeClass nodeClass = NodeClass.from(nodeClassValue);
            if (nodeClass == null) {
                return FutureUtils.failedUaFuture(StatusCodes.Bad_NodeClassInvalid);
            }

            switch (nodeClass) {
                case DataType:
                    return createDataTypeNodeFromBaseAttributes(nodeId, results);
                case Method:
                    return createMethodNodeFromBaseAttributes(nodeId, results);
                case Object:
                    return createObjectNodeFromBaseAttributes(nodeId, results);
                case ObjectType:
                    return createObjectTypeNodeFromBaseAttributes(nodeId, results);
                case ReferenceType:
                    return createReferenceTypeNodeFromBaseAttributes(nodeId, results);
                case Variable:
                    return createVariableNodeFromBaseAttributes(nodeId, results);
                case VariableType:
                    return createVariableTypeNodeFromBaseAttributes(nodeId, results);
                case View:
                    return createViewNodeFromBaseAttributes(nodeId, results);
                default:
                    throw new IllegalArgumentException("NodeClass: " + nodeClass);
            }
        });
    }

    private CompletableFuture<UaNode> createDataTypeNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.DATA_TYPE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        List<ReadValueId> readValueIds = remainingAttributes.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> attributesFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        return attributesFuture.thenApply(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            UaDataTypeNode node = newDataTypeNode(nodeId, attributeValues);

            cache.put(node.getNodeId(), node);

            return node;
        });
    }

    private CompletableFuture<UaNode> createMethodNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.METHOD_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        List<ReadValueId> readValueIds = remainingAttributes.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> attributesFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        return attributesFuture.thenApply(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            UaMethodNode node = newMethodNode(nodeId, attributeValues);

            cache.put(node.getNodeId(), node);

            return node;
        });
    }

    private CompletableFuture<UaNode> createObjectNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.OBJECT_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        List<ReadValueId> readValueIds = remainingAttributes.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> attributesFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        CompletableFuture<NodeId> typeDefinitionFuture = readTypeDefinition(nodeId);

        return attributesFuture.thenCombine(typeDefinitionFuture, (response, typeDefinitionId) -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            UaObjectNode node = newObjectNode(nodeId, typeDefinitionId, attributeValues);

            cache.put(node.getNodeId(), node);

            return node;
        });
    }

    private CompletableFuture<UaNode> createObjectTypeNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.OBJECT_TYPE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        List<ReadValueId> readValueIds = remainingAttributes.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> attributesFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        return attributesFuture.thenApply(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            UaObjectTypeNode node = newObjectTypeNode(nodeId, attributeValues);

            cache.put(node.getNodeId(), node);

            return node;
        });
    }

    private CompletableFuture<UaNode> createReferenceTypeNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.REFERENCE_TYPE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        List<ReadValueId> readValueIds = remainingAttributes.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> attributesFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        return attributesFuture.thenApply(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            UaReferenceTypeNode node = newReferenceTypeNode(nodeId, attributeValues);

            cache.put(node.getNodeId(), node);

            return node;
        });
    }

    private CompletableFuture<UaNode> createVariableNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.VARIABLE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        List<ReadValueId> readValueIds = remainingAttributes.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> attributesFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        CompletableFuture<NodeId> typeDefinitionFuture = readTypeDefinition(nodeId);

        return attributesFuture.thenCombine(typeDefinitionFuture, (response, typeDefinitionId) -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            UaVariableNode node = newVariableNode(nodeId, typeDefinitionId, attributeValues);

            cache.put(node.getNodeId(), node);

            return node;
        });
    }

    private CompletableFuture<UaNode> createVariableTypeNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.VARIABLE_TYPE_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        List<ReadValueId> readValueIds = remainingAttributes.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> attributesFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        return attributesFuture.thenApply(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            UaVariableTypeNode node = newVariableTypeNode(nodeId, attributeValues);

            cache.put(node.getNodeId(), node);

            return node;
        });
    }

    private CompletableFuture<UaNode> createViewNodeFromBaseAttributes(
        NodeId nodeId,
        List<DataValue> baseAttributeValues
    ) {

        Set<AttributeId> remainingAttributes = Sets.difference(
            AttributeId.VIEW_ATTRIBUTES,
            AttributeId.BASE_ATTRIBUTES
        );

        List<ReadValueId> readValueIds = remainingAttributes.stream()
            .map(id ->
                new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<ReadResponse> attributesFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            readValueIds
        );

        return attributesFuture.thenApply(response -> {
            List<DataValue> attributeValues = new ArrayList<>(baseAttributeValues);
            Collections.addAll(attributeValues, response.getResults());

            UaViewNode node = newViewNode(nodeId, attributeValues);

            cache.put(node.getNodeId(), node);

            return node;
        });
    }

    private UaDataTypeNode newDataTypeNode(NodeId nodeId, List<DataValue> attributeValues) {
        NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

        Preconditions.checkArgument(
            nodeClass == NodeClass.DataType,
            "expected NodeClass.DataType, got NodeClass." + nodeClass
        );

        QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
        LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
        LocalizedText description = (LocalizedText) attributeValues.get(4).getValue().getValue();
        UInteger writeMask = (UInteger) attributeValues.get(5).getValue().getValue();
        UInteger userWriteMask = (UInteger) attributeValues.get(6).getValue().getValue();

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
    }

    private UaMethodNode newMethodNode(NodeId nodeId, List<DataValue> attributeValues) {
        NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

        Preconditions.checkArgument(
            nodeClass == NodeClass.Method,
            "expected NodeClass.Method, got NodeClass." + nodeClass
        );

        QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
        LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
        LocalizedText description = (LocalizedText) attributeValues.get(4).getValue().getValue();
        UInteger writeMask = (UInteger) attributeValues.get(5).getValue().getValue();
        UInteger userWriteMask = (UInteger) attributeValues.get(6).getValue().getValue();

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
    }

    private UaObjectNode newObjectNode(NodeId nodeId, NodeId typeDefinitionId, List<DataValue> attributeValues) {
        NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

        Preconditions.checkArgument(
            nodeClass == NodeClass.Object,
            "expected NodeClass.Object, got NodeClass." + nodeClass
        );

        QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
        LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
        LocalizedText description = (LocalizedText) attributeValues.get(4).getValue().getValue();
        UInteger writeMask = (UInteger) attributeValues.get(5).getValue().getValue();
        UInteger userWriteMask = (UInteger) attributeValues.get(6).getValue().getValue();

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
    }

    private UaObjectTypeNode newObjectTypeNode(NodeId nodeId, List<DataValue> attributeValues) {
        NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

        Preconditions.checkArgument(
            nodeClass == NodeClass.ObjectType,
            "expected NodeClass.ObjectType, got NodeClass." + nodeClass
        );

        QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
        LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
        LocalizedText description = (LocalizedText) attributeValues.get(4).getValue().getValue();
        UInteger writeMask = (UInteger) attributeValues.get(5).getValue().getValue();
        UInteger userWriteMask = (UInteger) attributeValues.get(6).getValue().getValue();

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
    }

    private UaReferenceTypeNode newReferenceTypeNode(NodeId nodeId, List<DataValue> attributeValues) {
        NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

        Preconditions.checkArgument(
            nodeClass == NodeClass.ReferenceType,
            "expected NodeClass.ReferenceType, got NodeClass." + nodeClass
        );

        QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
        LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
        LocalizedText description = (LocalizedText) attributeValues.get(4).getValue().getValue();
        UInteger writeMask = (UInteger) attributeValues.get(5).getValue().getValue();
        UInteger userWriteMask = (UInteger) attributeValues.get(6).getValue().getValue();

        Boolean isAbstract = (Boolean) attributeValues.get(7).getValue().getValue();
        Boolean symmetric = (Boolean) attributeValues.get(8).getValue().getValue();
        LocalizedText inverseName = (LocalizedText) attributeValues.get(9).getValue().getValue();

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
    }

    private UaVariableNode newVariableNode(NodeId nodeId, NodeId typeDefinitionId, List<DataValue> attributeValues) {
        NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

        Preconditions.checkArgument(
            nodeClass == NodeClass.Variable,
            "expected NodeClass.Variable, got NodeClass." + nodeClass
        );

        QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
        LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
        LocalizedText description = (LocalizedText) attributeValues.get(4).getValue().getValue();
        UInteger writeMask = (UInteger) attributeValues.get(5).getValue().getValue();
        UInteger userWriteMask = (UInteger) attributeValues.get(6).getValue().getValue();

        DataValue value = attributeValues.get(7);
        NodeId dataType = (NodeId) attributeValues.get(8).getValue().getValue();
        Integer valueRank = (Integer) attributeValues.get(9).getValue().getValue();
        UInteger[] arrayDimensions = (UInteger[]) attributeValues.get(10).getValue().getValue();
        UByte accessLevel = (UByte) attributeValues.get(11).getValue().getValue();
        UByte userAccessLevel = (UByte) attributeValues.get(12).getValue().getValue();
        Double minimumSamplingInterval = (Double) attributeValues.get(13).getValue().getValue();
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
    }

    private UaVariableTypeNode newVariableTypeNode(NodeId nodeId, List<DataValue> attributeValues) {
        NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

        Preconditions.checkArgument(
            nodeClass == NodeClass.VariableType,
            "expected NodeClass.VariableType, got NodeClass." + nodeClass
        );

        QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
        LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
        LocalizedText description = (LocalizedText) attributeValues.get(4).getValue().getValue();
        UInteger writeMask = (UInteger) attributeValues.get(5).getValue().getValue();
        UInteger userWriteMask = (UInteger) attributeValues.get(6).getValue().getValue();

        DataValue value = attributeValues.get(7);
        NodeId dataType = (NodeId) attributeValues.get(8).getValue().getValue();
        Integer valueRank = (Integer) attributeValues.get(9).getValue().getValue();
        UInteger[] arrayDimensions = (UInteger[]) attributeValues.get(10).getValue().getValue();
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
    }

    private UaViewNode newViewNode(NodeId nodeId, List<DataValue> attributeValues) {
        NodeClass nodeClass = NodeClass.from((Integer) attributeValues.get(1).getValue().getValue());

        Preconditions.checkArgument(
            nodeClass == NodeClass.View,
            "expected NodeClass.View, got NodeClass." + nodeClass
        );

        QualifiedName browseName = (QualifiedName) attributeValues.get(2).getValue().getValue();
        LocalizedText displayName = (LocalizedText) attributeValues.get(3).getValue().getValue();
        LocalizedText description = (LocalizedText) attributeValues.get(4).getValue().getValue();
        UInteger writeMask = (UInteger) attributeValues.get(5).getValue().getValue();
        UInteger userWriteMask = (UInteger) attributeValues.get(6).getValue().getValue();

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
    }

    private Cache<NodeId, UaNode> buildCache() {
        return CacheBuilder.newBuilder()
            .expireAfterWrite(expireAfter)
            .maximumSize(maximumSize)
            .recordStats()
            .build();
    }

}

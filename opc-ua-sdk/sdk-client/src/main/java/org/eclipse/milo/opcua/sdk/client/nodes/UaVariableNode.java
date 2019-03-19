/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.nodes;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class UaVariableNode extends UaNode implements VariableNode {

    public UaVariableNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<? extends VariableNode> getVariableComponent(String namespaceUri, String name) {
        UShort namespaceIndex = client.getNamespaceTable().getIndex(namespaceUri);

        if (namespaceIndex != null) {
            return getVariableComponent(new QualifiedName(namespaceIndex, name));
        } else {
            return failedUaFuture(StatusCodes.Bad_NotFound);
        }
    }

    public CompletableFuture<? extends VariableNode> getVariableComponent(QualifiedName browseName) {
        UInteger nodeClassMask = uint(NodeClass.Variable.getValue());
        UInteger resultMask = uint(BrowseResultMask.All.getValue());

        CompletableFuture<BrowseResult> future = client.browse(
            new BrowseDescription(
                nodeId,
                BrowseDirection.Forward,
                Identifiers.HasComponent,
                false,
                nodeClassMask,
                resultMask
            )
        );

        return future.thenCompose(result -> {
            List<ReferenceDescription> references = l(result.getReferences());

            Optional<CompletableFuture<VariableNode>> node = references.stream()
                .filter(r -> browseName.equals(r.getBrowseName()))
                .flatMap(r -> {
                    Optional<CompletableFuture<VariableNode>> opt = r.getNodeId()
                        .local(client.getNamespaceTable())
                        .map(id -> client.getAddressSpace().getVariableNode(id));

                    return opt2stream(opt);
                })
                .findFirst();

            return node.orElse(failedUaFuture(StatusCodes.Bad_NotFound));
        });
    }

    public CompletableFuture<? extends VariableTypeNode> getTypeDefinition() {
        UInteger nodeClassMask = uint(NodeClass.VariableType.getValue());
        UInteger resultMask = uint(BrowseResultMask.All.getValue());

        CompletableFuture<BrowseResult> future = client.browse(
            new BrowseDescription(
                nodeId,
                BrowseDirection.Forward,
                Identifiers.HasTypeDefinition,
                false,
                nodeClassMask,
                resultMask
            )
        );

        return future.thenCompose(result -> {
            List<ReferenceDescription> references = l(result.getReferences());

            Optional<VariableTypeNode> node = references.stream()
                .flatMap(r -> {
                    Optional<VariableTypeNode> opt = r.getNodeId()
                        .local(client.getNamespaceTable())
                        .map(id -> client.getAddressSpace().createVariableTypeNode(id));

                    return opt2stream(opt);
                })
                .findFirst();

            return node.map(CompletableFuture::completedFuture)
                .orElse(FutureUtils.failedUaFuture(StatusCodes.Bad_NotFound));
        });
    }

    @Override
    public CompletableFuture<Object> getValue() {
        return getAttributeOrFail(readValue());
    }

    @Override
    public CompletableFuture<NodeId> getDataType() {
        return getAttributeOrFail(readDataType());
    }

    @Override
    public CompletableFuture<Integer> getValueRank() {
        return getAttributeOrFail(readValueRank());
    }

    @Override
    public CompletableFuture<UInteger[]> getArrayDimensions() {
        return getAttributeOrFail(readArrayDimensions());
    }

    @Override
    public CompletableFuture<UByte> getAccessLevel() {
        return getAttributeOrFail(readAccessLevel());
    }

    @Override
    public CompletableFuture<UByte> getUserAccessLevel() {
        return getAttributeOrFail(readUserAccessLevel());
    }

    @Override
    public CompletableFuture<Double> getMinimumSamplingInterval() {
        return getAttributeOrFail(readMinimumSamplingInterval());
    }

    @Override
    public CompletableFuture<Boolean> getHistorizing() {
        return getAttributeOrFail(readHistorizing());
    }

    @Override
    public CompletableFuture<StatusCode> setValue(Object value) {
        return writeValue(valueOnly(new Variant(value)));
    }

    @Override
    public CompletableFuture<StatusCode> setDataType(NodeId dataType) {
        return writeDataType(valueOnly(new Variant(dataType)));
    }

    @Override
    public CompletableFuture<StatusCode> setValueRank(int valueRank) {
        return writeValueRank(valueOnly(new Variant(valueRank)));
    }

    @Override
    public CompletableFuture<StatusCode> setArrayDimensions(UInteger[] arrayDimensions) {
        return writeArrayDimensions(valueOnly(new Variant(arrayDimensions)));
    }

    @Override
    public CompletableFuture<StatusCode> setAccessLevel(UByte accessLevel) {
        return writeAccessLevel(valueOnly(new Variant(accessLevel)));
    }

    @Override
    public CompletableFuture<StatusCode> setUserAccessLevel(UByte userAccessLevel) {
        return writeUserAccessLevel(valueOnly(new Variant(userAccessLevel)));
    }

    @Override
    public CompletableFuture<StatusCode> setMinimumSamplingInterval(double minimumSamplingInterval) {
        return writeMinimumSamplingInterval(valueOnly(new Variant(minimumSamplingInterval)));
    }

    @Override
    public CompletableFuture<StatusCode> setHistorizing(boolean historizing) {
        return writeHistorizing(valueOnly(new Variant(historizing)));
    }

    @Override
    public CompletableFuture<DataValue> readValue() {
        return readAttribute(AttributeId.Value);
    }

    @Override
    public CompletableFuture<DataValue> readDataType() {
        return readAttribute(AttributeId.DataType);
    }

    @Override
    public CompletableFuture<DataValue> readValueRank() {
        return readAttribute(AttributeId.ValueRank);
    }

    @Override
    public CompletableFuture<DataValue> readArrayDimensions() {
        return readAttribute(AttributeId.ArrayDimensions);
    }

    @Override
    public CompletableFuture<DataValue> readAccessLevel() {
        return readAttribute(AttributeId.AccessLevel);
    }

    @Override
    public CompletableFuture<DataValue> readUserAccessLevel() {
        return readAttribute(AttributeId.UserAccessLevel);
    }

    @Override
    public CompletableFuture<DataValue> readMinimumSamplingInterval() {
        return readAttribute(AttributeId.MinimumSamplingInterval);
    }

    @Override
    public CompletableFuture<DataValue> readHistorizing() {
        return readAttribute(AttributeId.Historizing);
    }

    @Override
    public CompletableFuture<StatusCode> writeValue(DataValue value) {
        return writeAttribute(AttributeId.Value, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeDataType(DataValue value) {
        return writeAttribute(AttributeId.DataType, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeValueRank(DataValue value) {
        return writeAttribute(AttributeId.ValueRank, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeArrayDimensions(DataValue value) {
        return writeAttribute(AttributeId.ArrayDimensions, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeAccessLevel(DataValue value) {
        return writeAttribute(AttributeId.AccessLevel, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeUserAccessLevel(DataValue value) {
        return writeAttribute(AttributeId.UserAccessLevel, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeMinimumSamplingInterval(DataValue value) {
        return writeAttribute(AttributeId.MinimumSamplingInterval, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeHistorizing(DataValue value) {
        return writeAttribute(AttributeId.Historizing, value);
    }

}


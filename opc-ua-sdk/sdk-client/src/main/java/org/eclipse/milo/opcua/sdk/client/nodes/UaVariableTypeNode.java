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

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;

public class UaVariableTypeNode extends UaNode implements VariableTypeNode {

    public UaVariableTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
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
    public CompletableFuture<Boolean> getIsAbstract() {
        return getAttributeOrFail(readIsAbstract());
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
    public CompletableFuture<StatusCode> setIsAbstract(boolean isAbstract) {
        return writeIsAbstract(valueOnly(new Variant(isAbstract)));
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
    public CompletableFuture<DataValue> readIsAbstract() {
        return readAttribute(AttributeId.IsAbstract);
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
    public CompletableFuture<StatusCode> writeIsAbstract(DataValue value) {
        return writeAttribute(AttributeId.IsAbstract, value);
    }

}

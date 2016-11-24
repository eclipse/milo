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

package org.eclipse.milo.opcua.sdk.client.api.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface VariableTypeNode extends Node {

    /**
     * Get the Value attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readValue()}.
     *
     * @return the Value attribute value.
     */
    CompletableFuture<Object> getValue();

    /**
     * Get the DataType attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readDataType()}.
     *
     * @return the DataType attribute value.
     */
    CompletableFuture<NodeId> getDataType();

    /**
     * Get the ValueRank attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readValueRank()}.
     *
     * @return the ValueRank attribute value.
     */
    CompletableFuture<Integer> getValueRank();

    /**
     * Get the ArrayDimensions attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readArrayDimensions()}.
     *
     * @return the ArrayDimensions attribute value.
     */
    CompletableFuture<UInteger[]> getArrayDimensions();

    /**
     * Get the IsAbstract attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readIsAbstract()}.
     *
     * @return the IsAbstract attribute value.
     */
    CompletableFuture<Boolean> getIsAbstract();

    /**
     * Set the Value attribute.
     *
     * @param value the {@link Object} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setValue(Object value);

    /**
     * Set the DataType attribute.
     *
     * @param dataType the {@link NodeId} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setDataType(NodeId dataType);

    /**
     * Set the ValueRank attribute.
     *
     * @param valueRank the {@link Integer} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setValueRank(int valueRank);

    /**
     * Set the ArrayDimensions attribute.
     *
     * @param arrayDimensions the {@link UInteger}[] to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setArrayDimensions(UInteger[] arrayDimensions);

    /**
     * Write a {@link Boolean} to the IsAbstract attribute.
     *
     * @param isAbstract the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setIsAbstract(boolean isAbstract);

    /**
     * Read the Value attribute {@link DataValue}.
     *
     * @return the Value attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readValue();

    /**
     * Read the DataType attribute {@link DataValue}.
     *
     * @return the DataType attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readDataType();

    /**
     * Read the ValueRank attribute {@link DataValue}.
     *
     * @return the ValueRank attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readValueRank();

    /**
     * Read the ArrayDimensions attribute {@link DataValue}.
     *
     * @return the ArrayDimensions attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readArrayDimensions();

    /**
     * Read the IsAbstract attribute {@link DataValue}.
     *
     * @return the IsAbstract attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readIsAbstract();

    /**
     * Write a {@link DataValue} to the Value attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeValue(DataValue value);

    /**
     * Write a {@link DataValue} to the DataType attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeDataType(DataValue value);

    /**
     * Write a {@link DataValue} to the ValueRank attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeValueRank(DataValue value);

    /**
     * Write a {@link DataValue} to the ArrayDimensions attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeArrayDimensions(DataValue value);

    /**
     * Write a {@link DataValue} to the IsAbstract attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeIsAbstract(DataValue value);

}

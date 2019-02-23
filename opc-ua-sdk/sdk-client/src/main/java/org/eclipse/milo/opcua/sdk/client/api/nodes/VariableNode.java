/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface VariableNode extends Node {

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
     * @return the ValueRank attribute value, if present.
     */
    CompletableFuture<Integer> getValueRank();

    /**
     * Get the ArrayDimensions attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readArrayDimensions()}.
     *
     * @return the ArrayDimensions attribute value, if present.
     */
    CompletableFuture<UInteger[]> getArrayDimensions();

    /**
     * Get the AccessLevel attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readAccessLevel()}.
     *
     * @return the AccessLevel attribute value.
     */
    CompletableFuture<UByte> getAccessLevel();

    /**
     * Get the UserAccessLevel attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readUserAccessLevel()}.
     *
     * @return the UserAccessLevel attribute value.
     */
    CompletableFuture<UByte> getUserAccessLevel();

    /**
     * Get the MinimumSamplingInterval attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readMinimumSamplingInterval()}.
     *
     * @return the MinimumSamplingInterval attribute value, if present.
     */
    CompletableFuture<Double> getMinimumSamplingInterval();

    /**
     * Get the Historizing attribute value.
     * <p>
     * If quality and timestamps are required, see {@link #readHistorizing()}.
     *
     * @return the Historizing attribute value.
     */
    CompletableFuture<Boolean> getHistorizing();

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
     * Set the AccessLevel attribute.
     *
     * @param accessLevel the {@link UByte} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setAccessLevel(UByte accessLevel);

    /**
     * Set the UserAccessLevel attribute.
     *
     * @param userAccessLevel the {@link UByte} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setUserAccessLevel(UByte userAccessLevel);

    /**
     * Set the MinimumSamplingInterval attribute.
     *
     * @param minimumSamplingInterval the {@link Double} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setMinimumSamplingInterval(double minimumSamplingInterval);

    /**
     * Set the Historizing attribute.
     *
     * @param historizing the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setHistorizing(boolean historizing);

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
     * Read the AccessLevel attribute {@link DataValue}.
     *
     * @return the AccessLevel attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readAccessLevel();

    /**
     * Read the UserAccessLevel attribute {@link DataValue}.
     *
     * @return the UserAccessLevel attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readUserAccessLevel();

    /**
     * Read the MinimumSamplingInterval attribute {@link DataValue}.
     *
     * @return the MinimumSamplingInterval attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readMinimumSamplingInterval();

    /**
     * Read the Historizing attribute {@link DataValue}.
     *
     * @return the Historizing attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readHistorizing();

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
     * Write a {@link DataValue} to the AccessLevel attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeAccessLevel(DataValue value);

    /**
     * Write a {@link DataValue} to the UserAccessLevel attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeUserAccessLevel(DataValue value);

    /**
     * Write a {@link DataValue} to the MinimumSamplingInterval attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeMinimumSamplingInterval(DataValue value);

    /**
     * Write a {@link DataValue} to the Historizing attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeHistorizing(DataValue value);

}

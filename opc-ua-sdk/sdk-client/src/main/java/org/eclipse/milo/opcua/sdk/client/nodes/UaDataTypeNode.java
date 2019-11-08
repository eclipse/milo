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
import org.eclipse.milo.opcua.sdk.client.api.nodes.DataTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.DataTypeNodeProperties;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;

import static org.eclipse.milo.opcua.sdk.core.nodes.DataTypeNodeProperties.NodeVersion;
import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;

public class UaDataTypeNode extends UaNode implements DataTypeNode {

    public UaDataTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<Boolean> getIsAbstract() {
        return getAttributeOrFail(readIsAbstract());
    }

    @Override
    public CompletableFuture<StatusCode> setIsAbstract(boolean isAbstract) {
        return writeIsAbstract(valueOnly(new Variant(isAbstract)));
    }

    @Override
    public CompletableFuture<DataValue> readIsAbstract() {
        return readAttribute(AttributeId.IsAbstract);
    }

    @Override
    public CompletableFuture<StatusCode> writeIsAbstract(DataValue value) {
        return writeAttribute(AttributeId.IsAbstract, value);
    }

    /**
     * Get the value of the {@link DataTypeNodeProperties#NodeVersion} Property, if it exists.
     *
     * @return the value of the NodeVersion Property, if it exists.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<String> getNodeVersion() {
        return getProperty(DataTypeNodeProperties.NodeVersion);
    }

    /**
     * Get the value of the {@link DataTypeNodeProperties#EnumStrings} Property, if it exists.
     *
     * @return the value of the EnumStrings Property, if it exists.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<LocalizedText[]> getEnumStrings() {
        return getProperty(DataTypeNodeProperties.EnumStrings);
    }

    /**
     * Get the value of the {@link DataTypeNodeProperties#EnumValues} Property, if it exists.
     *
     * @return the value of the EnumValues Property, if it exists.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<EnumValueType[]> getEnumValues() {
        return getProperty(DataTypeNodeProperties.EnumValues);
    }

    /**
     * Get the value of the {@link DataTypeNodeProperties#OptionSetValues} Property, if it exists.
     *
     * @return the value of the OptionSetValues Property, if it exists.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<LocalizedText[]> getOptionSetValues() {
        return getProperty(DataTypeNodeProperties.OptionSetValues);
    }

    /**
     * Set the value of the {@link DataTypeNodeProperties#NodeVersion} Property, if it exists.
     *
     * @param nodeVersion the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<StatusCode> setNodeVersion(String nodeVersion) {
        return setProperty(NodeVersion, nodeVersion);
    }

    /**
     * Set the value of the EnumStrings Property, if it exists.
     *
     * @param enumStrings the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<StatusCode> setEnumStrings(LocalizedText[] enumStrings) {
        return setProperty(DataTypeNodeProperties.EnumStrings, enumStrings);
    }

    /**
     * Set the value of the {@link DataTypeNodeProperties#EnumValues} Property, if it exists.
     *
     * @param enumValues the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<StatusCode> setEnumValues(EnumValueType[] enumValues) {
        return setProperty(DataTypeNodeProperties.EnumValues, enumValues);
    }

    /**
     * Set the value of the {@link DataTypeNodeProperties#OptionSetValues} Property, if it exists.
     *
     * @param optionSetValues the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<StatusCode> setOptionSetValues(LocalizedText[] optionSetValues) {
        return setProperty(DataTypeNodeProperties.OptionSetValues, optionSetValues);
    }

}

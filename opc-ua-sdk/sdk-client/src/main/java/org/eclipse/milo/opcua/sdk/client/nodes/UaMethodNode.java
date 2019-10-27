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
import org.eclipse.milo.opcua.sdk.client.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNodeProperties;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;

public class UaMethodNode extends UaNode implements MethodNode {

    public UaMethodNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<Boolean> getExecutable() {
        return getAttributeOrFail(readExecutable());
    }

    @Override
    public CompletableFuture<Boolean> getUserExecutable() {
        return getAttributeOrFail(readUserExecutable());
    }

    @Override
    public CompletableFuture<StatusCode> setExecutable(boolean executable) {
        return writeExecutable(valueOnly(new Variant(executable)));
    }

    @Override
    public CompletableFuture<StatusCode> setUserExecutable(boolean userExecutable) {
        return writeUserExecutable(valueOnly(new Variant(userExecutable)));
    }

    @Override
    public CompletableFuture<DataValue> readExecutable() {
        return readAttribute(AttributeId.Executable);
    }

    @Override
    public CompletableFuture<DataValue> readUserExecutable() {
        return readAttribute(AttributeId.UserExecutable);
    }

    @Override
    public CompletableFuture<StatusCode> writeExecutable(DataValue value) {
        return writeAttribute(AttributeId.Executable, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeUserExecutable(DataValue value) {
        return writeAttribute(AttributeId.UserExecutable, value);
    }

    /**
     * Get the value of the NodeVersion Property, if it exists.
     * <p>
     * The NodeVersion Property is used to indicate the version of a Node.
     * <p>
     * The NodeVersion Property is updated each time a Reference is added or deleted to the Node the Property belongs
     * to. Attribute value changes do not cause the NodeVersion to change. Clients may read the NodeVersion Property or
     * subscribe to it to determine when the structure of a Node has changed.
     * <p>
     * This Property is optional. If not present, the future will be completed exceptionally with a {@link UaException}
     * indicating {@link StatusCodes#Bad_NotFound}.
     *
     * @return the value of the NodeVersion Property, if it exists.
     */
    public CompletableFuture<String> getNodeVersion() {
        return getProperty(MethodNodeProperties.NodeVersion);
    }

    /**
     * Get the value of the InputArguments Property, if it exists.
     * <p>
     * The InputArguments Property is used to specify the arguments that shall be used by a client when calling the
     * Method.
     * <p>
     * This Property is optional. If not present, the future will be completed exceptionally with a {@link UaException}
     * indicating {@link StatusCodes#Bad_NotFound}.
     *
     * @return the value of the InputArguments Property, if it exists.
     */
    public CompletableFuture<Argument[]> getInputArguments() {
        return getProperty(MethodNodeProperties.InputArguments);
    }

    /**
     * Get the value of the OutputArguments Property, if it exists.
     * <p>
     * The OutputArguments Property specifies the result returned from the Method call.
     * <p>
     * This Property is optional. If not present, the future will be completed exceptionally with a {@link UaException}
     * indicating {@link StatusCodes#Bad_NotFound}.
     *
     * @return the value of the OutputArguments Property, if it exists.
     */
    public CompletableFuture<Argument[]> getOutputArguments() {
        return getProperty(MethodNodeProperties.OutputArguments);
    }

    /**
     * Set the value of the NodeVersion Property, if it exists.
     *
     * @param nodeVersion the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see MethodNodeProperties#NodeVersion
     */
    public CompletableFuture<StatusCode> setNodeVersion(String nodeVersion) {
        return setProperty(MethodNodeProperties.NodeVersion, nodeVersion);
    }

    /**
     * Set the value of the InputArguments Property, if it exists.
     *
     * @param inputArguments the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see MethodNodeProperties#InputArguments
     */
    public CompletableFuture<StatusCode> setInputArguments(Argument[] inputArguments) {
        return setProperty(MethodNodeProperties.InputArguments, inputArguments);
    }

    /**
     * Set the value of the OutputArguments Property, if it exists.
     *
     * @param outputArguments the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see MethodNodeProperties#OutputArguments
     */
    public CompletableFuture<StatusCode> setOutputArguments(Argument[] outputArguments) {
        return setProperty(MethodNodeProperties.OutputArguments, outputArguments);
    }

}

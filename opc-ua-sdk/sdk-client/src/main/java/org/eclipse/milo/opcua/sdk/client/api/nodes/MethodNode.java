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

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;

public interface MethodNode extends Node {

    /**
     * The NodeVersion Property is used to indicate the version of a Node.
     * <p>
     * The NodeVersion Property is updated each time a Reference is added or deleted to the Node the Property belongs
     * to. Attribute value changes do not cause the NodeVersion to change. Clients may read the NodeVersion Property or
     * subscribe to it to determine when the structure of a Node has changed.
     *
     * @see MethodNode#getNodeVersion()
     */
    QualifiedProperty<String> NodeVersion = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "NodeVersion",
        Identifiers.String,
        ValueRanks.Scalar,
        String.class
    );

    /**
     * The InputArguments Property is used to specify the arguments that shall be used by a client when calling the
     * Method.
     *
     * @see MethodNode#getInputArguments()
     */
    QualifiedProperty<Argument[]> InputArguments = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "InputArguments",
        Identifiers.Argument,
        ValueRanks.OneDimension,
        Argument[].class
    );

    /**
     * The OutputArguments Property specifies the result returned from the Method call.
     *
     * @see MethodNode#getOutputArguments()
     */
    QualifiedProperty<Argument[]> OutputArguments = new QualifiedProperty<>(
        Namespaces.OPC_UA,
        "OutputArguments",
        Identifiers.Argument,
        ValueRanks.OneDimension,
        Argument[].class
    );

    /**
     * Get the Executable attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readExecutable()}.
     *
     * @return the Executable attribute.
     */
    CompletableFuture<Boolean> getExecutable();

    /**
     * Get the UserExecutable attribute.
     * <p>
     * If quality and timestamps are required, see {@link #readUserExecutable()}.
     *
     * @return the UserExecutable attribute.
     */
    CompletableFuture<Boolean> getUserExecutable();

    /**
     * Set the Executable attribute.
     *
     * @param executable the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setExecutable(boolean executable);

    /**
     * Set the UserExecutable attribute.
     *
     * @param userExecutable the {@link Boolean} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> setUserExecutable(boolean userExecutable);

    /**
     * Read the UserExecutable attribute {@link DataValue}.
     *
     * @return the UserExecutable attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readExecutable();

    /**
     * Read the UserExecutable attribute {@link DataValue}.
     *
     * @return the UserExecutable attribute {@link DataValue}.
     */
    CompletableFuture<DataValue> readUserExecutable();

    /**
     * Write a {@link DataValue} to the Executable attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeExecutable(DataValue value);

    /**
     * Write a {@link DataValue} to the UserExecutable attribute.
     *
     * @param value the {@link DataValue} to write.
     * @return the {@link StatusCode} of the write operation.
     */
    CompletableFuture<StatusCode> writeUserExecutable(DataValue value);

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
    CompletableFuture<String> getNodeVersion();

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
    CompletableFuture<Argument[]> getInputArguments();

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
    CompletableFuture<Argument[]> getOutputArguments();

}

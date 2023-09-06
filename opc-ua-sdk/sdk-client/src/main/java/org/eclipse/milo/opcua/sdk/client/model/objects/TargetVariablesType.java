/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.FieldTargetDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.9/#9.1.9.2.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.9/#9.1.9.2.1</a>
 */
public interface TargetVariablesType extends SubscribedDataSetType {
    QualifiedProperty<FieldTargetDataType[]> TARGET_VARIABLES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TargetVariables",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14744"),
        1,
        FieldTargetDataType[].class
    );

    /**
     * Get the local value of the TargetVariables Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the TargetVariables Node.
     * @throws UaException if an error occurs creating or getting the TargetVariables Node.
     */
    FieldTargetDataType[] getTargetVariables() throws UaException;

    /**
     * Set the local value of the TargetVariables Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the TargetVariables Node.
     * @throws UaException if an error occurs creating or getting the TargetVariables Node.
     */
    void setTargetVariables(FieldTargetDataType[] value) throws UaException;

    /**
     * Read the value of the TargetVariables Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link FieldTargetDataType[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    FieldTargetDataType[] readTargetVariables() throws UaException;

    /**
     * Write a new value for the TargetVariables Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link FieldTargetDataType[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeTargetVariables(FieldTargetDataType[] value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readTargetVariables}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends FieldTargetDataType[]> readTargetVariablesAsync();

    /**
     * An asynchronous implementation of {@link #writeTargetVariables}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeTargetVariablesAsync(FieldTargetDataType[] value);

    /**
     * Get the TargetVariables {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the TargetVariables {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getTargetVariablesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTargetVariablesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getTargetVariablesNodeAsync();
}
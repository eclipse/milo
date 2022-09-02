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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.12">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.12</a>
 */
public interface PubSubCapabilitiesType extends BaseObjectType {
    QualifiedProperty<UInteger> MAX_PUB_SUB_CONNECTIONS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxPubSubConnections",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_WRITER_GROUPS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxWriterGroups",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_READER_GROUPS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxReaderGroups",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_DATA_SET_WRITERS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxDataSetWriters",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_DATA_SET_READERS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxDataSetReaders",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_FIELDS_PER_DATA_SET = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxFieldsPerDataSet",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        -1,
        UInteger.class
    );

    /**
     * Get the local value of the MaxPubSubConnections Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxPubSubConnections Node.
     * @throws UaException if an error occurs creating or getting the MaxPubSubConnections Node.
     */
    UInteger getMaxPubSubConnections() throws UaException;

    /**
     * Set the local value of the MaxPubSubConnections Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the MaxPubSubConnections Node.
     * @throws UaException if an error occurs creating or getting the MaxPubSubConnections Node.
     */
    void setMaxPubSubConnections(UInteger value) throws UaException;

    /**
     * Read the value of the MaxPubSubConnections Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxPubSubConnections() throws UaException;

    /**
     * Write a new value for the MaxPubSubConnections Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxPubSubConnections(UInteger value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxPubSubConnections}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxPubSubConnectionsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxPubSubConnections}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxPubSubConnectionsAsync(UInteger value);

    /**
     * Get the MaxPubSubConnections {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxPubSubConnections {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxPubSubConnectionsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxPubSubConnectionsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxPubSubConnectionsNodeAsync();

    /**
     * Get the local value of the MaxWriterGroups Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxWriterGroups Node.
     * @throws UaException if an error occurs creating or getting the MaxWriterGroups Node.
     */
    UInteger getMaxWriterGroups() throws UaException;

    /**
     * Set the local value of the MaxWriterGroups Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the MaxWriterGroups Node.
     * @throws UaException if an error occurs creating or getting the MaxWriterGroups Node.
     */
    void setMaxWriterGroups(UInteger value) throws UaException;

    /**
     * Read the value of the MaxWriterGroups Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxWriterGroups() throws UaException;

    /**
     * Write a new value for the MaxWriterGroups Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxWriterGroups(UInteger value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxWriterGroups}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxWriterGroupsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxWriterGroups}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxWriterGroupsAsync(UInteger value);

    /**
     * Get the MaxWriterGroups {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxWriterGroups {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxWriterGroupsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxWriterGroupsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxWriterGroupsNodeAsync();

    /**
     * Get the local value of the MaxReaderGroups Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxReaderGroups Node.
     * @throws UaException if an error occurs creating or getting the MaxReaderGroups Node.
     */
    UInteger getMaxReaderGroups() throws UaException;

    /**
     * Set the local value of the MaxReaderGroups Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the MaxReaderGroups Node.
     * @throws UaException if an error occurs creating or getting the MaxReaderGroups Node.
     */
    void setMaxReaderGroups(UInteger value) throws UaException;

    /**
     * Read the value of the MaxReaderGroups Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxReaderGroups() throws UaException;

    /**
     * Write a new value for the MaxReaderGroups Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxReaderGroups(UInteger value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxReaderGroups}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxReaderGroupsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxReaderGroups}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxReaderGroupsAsync(UInteger value);

    /**
     * Get the MaxReaderGroups {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxReaderGroups {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxReaderGroupsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxReaderGroupsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxReaderGroupsNodeAsync();

    /**
     * Get the local value of the MaxDataSetWriters Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxDataSetWriters Node.
     * @throws UaException if an error occurs creating or getting the MaxDataSetWriters Node.
     */
    UInteger getMaxDataSetWriters() throws UaException;

    /**
     * Set the local value of the MaxDataSetWriters Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the MaxDataSetWriters Node.
     * @throws UaException if an error occurs creating or getting the MaxDataSetWriters Node.
     */
    void setMaxDataSetWriters(UInteger value) throws UaException;

    /**
     * Read the value of the MaxDataSetWriters Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxDataSetWriters() throws UaException;

    /**
     * Write a new value for the MaxDataSetWriters Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxDataSetWriters(UInteger value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxDataSetWriters}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxDataSetWritersAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxDataSetWriters}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxDataSetWritersAsync(UInteger value);

    /**
     * Get the MaxDataSetWriters {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxDataSetWriters {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxDataSetWritersNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxDataSetWritersNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxDataSetWritersNodeAsync();

    /**
     * Get the local value of the MaxDataSetReaders Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxDataSetReaders Node.
     * @throws UaException if an error occurs creating or getting the MaxDataSetReaders Node.
     */
    UInteger getMaxDataSetReaders() throws UaException;

    /**
     * Set the local value of the MaxDataSetReaders Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the MaxDataSetReaders Node.
     * @throws UaException if an error occurs creating or getting the MaxDataSetReaders Node.
     */
    void setMaxDataSetReaders(UInteger value) throws UaException;

    /**
     * Read the value of the MaxDataSetReaders Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxDataSetReaders() throws UaException;

    /**
     * Write a new value for the MaxDataSetReaders Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxDataSetReaders(UInteger value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxDataSetReaders}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxDataSetReadersAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxDataSetReaders}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxDataSetReadersAsync(UInteger value);

    /**
     * Get the MaxDataSetReaders {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxDataSetReaders {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxDataSetReadersNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxDataSetReadersNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxDataSetReadersNodeAsync();

    /**
     * Get the local value of the MaxFieldsPerDataSet Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxFieldsPerDataSet Node.
     * @throws UaException if an error occurs creating or getting the MaxFieldsPerDataSet Node.
     */
    UInteger getMaxFieldsPerDataSet() throws UaException;

    /**
     * Set the local value of the MaxFieldsPerDataSet Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the MaxFieldsPerDataSet Node.
     * @throws UaException if an error occurs creating or getting the MaxFieldsPerDataSet Node.
     */
    void setMaxFieldsPerDataSet(UInteger value) throws UaException;

    /**
     * Read the value of the MaxFieldsPerDataSet Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxFieldsPerDataSet() throws UaException;

    /**
     * Write a new value for the MaxFieldsPerDataSet Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxFieldsPerDataSet(UInteger value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxFieldsPerDataSet}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxFieldsPerDataSetAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxFieldsPerDataSet}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxFieldsPerDataSetAsync(UInteger value);

    /**
     * Get the MaxFieldsPerDataSet {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxFieldsPerDataSet {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxFieldsPerDataSetNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxFieldsPerDataSetNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxFieldsPerDataSetNodeAsync();
}

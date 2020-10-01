package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface OperationLimitsType extends FolderType {
    QualifiedProperty<UInteger> MAX_NODES_PER_READ = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerRead",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_READ_DATA = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryReadData",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_READ_EVENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryReadEvents",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_WRITE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerWrite",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_UPDATE_DATA = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryUpdateData",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_UPDATE_EVENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryUpdateEvents",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_METHOD_CALL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerMethodCall",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_BROWSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerBrowse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_REGISTER_NODES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerRegisterNodes",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerTranslateBrowsePathsToNodeIds",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_NODE_MANAGEMENT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerNodeManagement",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_MONITORED_ITEMS_PER_CALL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxMonitoredItemsPerCall",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    /**
     * Get the local value of the MaxNodesPerRead Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerRead Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerRead Node.
     */
    UInteger getMaxNodesPerRead() throws UaException;

    /**
     * Set the local value of the MaxNodesPerRead Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerRead the local value to set for the MaxNodesPerRead Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerRead Node.
     */
    void setMaxNodesPerRead(UInteger maxNodesPerRead) throws UaException;

    /**
     * Read the value of the MaxNodesPerRead Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerRead() throws UaException;

    /**
     * Write a new value for the MaxNodesPerRead Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerRead the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerRead(UInteger maxNodesPerRead) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerRead()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerReadAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerRead(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerReadAsync(UInteger maxNodesPerRead);

    /**
     * Get the MaxNodesPerRead {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerRead {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerReadNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerReadNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerReadNodeAsync();

    /**
     * Get the local value of the MaxNodesPerHistoryReadData Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerHistoryReadData Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerHistoryReadData Node.
     */
    UInteger getMaxNodesPerHistoryReadData() throws UaException;

    /**
     * Set the local value of the MaxNodesPerHistoryReadData Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerHistoryReadData the local value to set for the MaxNodesPerHistoryReadData Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerHistoryReadData Node.
     */
    void setMaxNodesPerHistoryReadData(UInteger maxNodesPerHistoryReadData) throws UaException;

    /**
     * Read the value of the MaxNodesPerHistoryReadData Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerHistoryReadData() throws UaException;

    /**
     * Write a new value for the MaxNodesPerHistoryReadData Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerHistoryReadData the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerHistoryReadData(UInteger maxNodesPerHistoryReadData) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerHistoryReadData()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerHistoryReadDataAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerHistoryReadData(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerHistoryReadDataAsync(
        UInteger maxNodesPerHistoryReadData);

    /**
     * Get the MaxNodesPerHistoryReadData {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerHistoryReadData {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerHistoryReadDataNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerHistoryReadDataNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerHistoryReadDataNodeAsync();

    /**
     * Get the local value of the MaxNodesPerHistoryReadEvents Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerHistoryReadEvents Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerHistoryReadEvents Node.
     */
    UInteger getMaxNodesPerHistoryReadEvents() throws UaException;

    /**
     * Set the local value of the MaxNodesPerHistoryReadEvents Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerHistoryReadEvents the local value to set for the MaxNodesPerHistoryReadEvents Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerHistoryReadEvents Node.
     */
    void setMaxNodesPerHistoryReadEvents(UInteger maxNodesPerHistoryReadEvents) throws UaException;

    /**
     * Read the value of the MaxNodesPerHistoryReadEvents Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerHistoryReadEvents() throws UaException;

    /**
     * Write a new value for the MaxNodesPerHistoryReadEvents Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerHistoryReadEvents the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerHistoryReadEvents(UInteger maxNodesPerHistoryReadEvents) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerHistoryReadEvents()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerHistoryReadEventsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerHistoryReadEvents(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerHistoryReadEventsAsync(
        UInteger maxNodesPerHistoryReadEvents);

    /**
     * Get the MaxNodesPerHistoryReadEvents {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerHistoryReadEvents {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerHistoryReadEventsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerHistoryReadEventsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerHistoryReadEventsNodeAsync();

    /**
     * Get the local value of the MaxNodesPerWrite Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerWrite Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerWrite Node.
     */
    UInteger getMaxNodesPerWrite() throws UaException;

    /**
     * Set the local value of the MaxNodesPerWrite Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerWrite the local value to set for the MaxNodesPerWrite Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerWrite Node.
     */
    void setMaxNodesPerWrite(UInteger maxNodesPerWrite) throws UaException;

    /**
     * Read the value of the MaxNodesPerWrite Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerWrite() throws UaException;

    /**
     * Write a new value for the MaxNodesPerWrite Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerWrite the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerWrite(UInteger maxNodesPerWrite) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerWrite()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerWriteAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerWrite(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerWriteAsync(UInteger maxNodesPerWrite);

    /**
     * Get the MaxNodesPerWrite {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerWrite {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerWriteNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerWriteNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerWriteNodeAsync();

    /**
     * Get the local value of the MaxNodesPerHistoryUpdateData Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerHistoryUpdateData Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerHistoryUpdateData Node.
     */
    UInteger getMaxNodesPerHistoryUpdateData() throws UaException;

    /**
     * Set the local value of the MaxNodesPerHistoryUpdateData Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerHistoryUpdateData the local value to set for the MaxNodesPerHistoryUpdateData Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerHistoryUpdateData Node.
     */
    void setMaxNodesPerHistoryUpdateData(UInteger maxNodesPerHistoryUpdateData) throws UaException;

    /**
     * Read the value of the MaxNodesPerHistoryUpdateData Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerHistoryUpdateData() throws UaException;

    /**
     * Write a new value for the MaxNodesPerHistoryUpdateData Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerHistoryUpdateData the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerHistoryUpdateData(UInteger maxNodesPerHistoryUpdateData) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerHistoryUpdateData()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerHistoryUpdateDataAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerHistoryUpdateData(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerHistoryUpdateDataAsync(
        UInteger maxNodesPerHistoryUpdateData);

    /**
     * Get the MaxNodesPerHistoryUpdateData {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerHistoryUpdateData {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerHistoryUpdateDataNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerHistoryUpdateDataNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerHistoryUpdateDataNodeAsync();

    /**
     * Get the local value of the MaxNodesPerHistoryUpdateEvents Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerHistoryUpdateEvents Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerHistoryUpdateEvents Node.
     */
    UInteger getMaxNodesPerHistoryUpdateEvents() throws UaException;

    /**
     * Set the local value of the MaxNodesPerHistoryUpdateEvents Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerHistoryUpdateEvents the local value to set for the MaxNodesPerHistoryUpdateEvents Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerHistoryUpdateEvents Node.
     */
    void setMaxNodesPerHistoryUpdateEvents(UInteger maxNodesPerHistoryUpdateEvents) throws
        UaException;

    /**
     * Read the value of the MaxNodesPerHistoryUpdateEvents Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerHistoryUpdateEvents() throws UaException;

    /**
     * Write a new value for the MaxNodesPerHistoryUpdateEvents Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerHistoryUpdateEvents the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerHistoryUpdateEvents(UInteger maxNodesPerHistoryUpdateEvents) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerHistoryUpdateEvents()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerHistoryUpdateEventsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerHistoryUpdateEvents(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerHistoryUpdateEventsAsync(
        UInteger maxNodesPerHistoryUpdateEvents);

    /**
     * Get the MaxNodesPerHistoryUpdateEvents {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerHistoryUpdateEvents {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerHistoryUpdateEventsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerHistoryUpdateEventsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerHistoryUpdateEventsNodeAsync();

    /**
     * Get the local value of the MaxNodesPerMethodCall Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerMethodCall Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerMethodCall Node.
     */
    UInteger getMaxNodesPerMethodCall() throws UaException;

    /**
     * Set the local value of the MaxNodesPerMethodCall Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerMethodCall the local value to set for the MaxNodesPerMethodCall Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerMethodCall Node.
     */
    void setMaxNodesPerMethodCall(UInteger maxNodesPerMethodCall) throws UaException;

    /**
     * Read the value of the MaxNodesPerMethodCall Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerMethodCall() throws UaException;

    /**
     * Write a new value for the MaxNodesPerMethodCall Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerMethodCall the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerMethodCall(UInteger maxNodesPerMethodCall) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerMethodCall()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerMethodCallAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerMethodCall(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerMethodCallAsync(UInteger maxNodesPerMethodCall);

    /**
     * Get the MaxNodesPerMethodCall {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerMethodCall {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerMethodCallNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerMethodCallNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerMethodCallNodeAsync();

    /**
     * Get the local value of the MaxNodesPerBrowse Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerBrowse Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerBrowse Node.
     */
    UInteger getMaxNodesPerBrowse() throws UaException;

    /**
     * Set the local value of the MaxNodesPerBrowse Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerBrowse the local value to set for the MaxNodesPerBrowse Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerBrowse Node.
     */
    void setMaxNodesPerBrowse(UInteger maxNodesPerBrowse) throws UaException;

    /**
     * Read the value of the MaxNodesPerBrowse Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerBrowse() throws UaException;

    /**
     * Write a new value for the MaxNodesPerBrowse Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerBrowse the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerBrowse(UInteger maxNodesPerBrowse) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerBrowse()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerBrowseAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerBrowse(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerBrowseAsync(UInteger maxNodesPerBrowse);

    /**
     * Get the MaxNodesPerBrowse {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerBrowse {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerBrowseNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerBrowseNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerBrowseNodeAsync();

    /**
     * Get the local value of the MaxNodesPerRegisterNodes Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerRegisterNodes Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerRegisterNodes Node.
     */
    UInteger getMaxNodesPerRegisterNodes() throws UaException;

    /**
     * Set the local value of the MaxNodesPerRegisterNodes Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerRegisterNodes the local value to set for the MaxNodesPerRegisterNodes Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerRegisterNodes Node.
     */
    void setMaxNodesPerRegisterNodes(UInteger maxNodesPerRegisterNodes) throws UaException;

    /**
     * Read the value of the MaxNodesPerRegisterNodes Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerRegisterNodes() throws UaException;

    /**
     * Write a new value for the MaxNodesPerRegisterNodes Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerRegisterNodes the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerRegisterNodes(UInteger maxNodesPerRegisterNodes) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerRegisterNodes()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerRegisterNodesAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerRegisterNodes(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerRegisterNodesAsync(
        UInteger maxNodesPerRegisterNodes);

    /**
     * Get the MaxNodesPerRegisterNodes {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerRegisterNodes {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerRegisterNodesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerRegisterNodesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerRegisterNodesNodeAsync();

    /**
     * Get the local value of the MaxNodesPerTranslateBrowsePathsToNodeIds Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerTranslateBrowsePathsToNodeIds Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerTranslateBrowsePathsToNodeIds Node.
     */
    UInteger getMaxNodesPerTranslateBrowsePathsToNodeIds() throws UaException;

    /**
     * Set the local value of the MaxNodesPerTranslateBrowsePathsToNodeIds Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerTranslateBrowsePathsToNodeIds the local value to set for the MaxNodesPerTranslateBrowsePathsToNodeIds Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerTranslateBrowsePathsToNodeIds Node.
     */
    void setMaxNodesPerTranslateBrowsePathsToNodeIds(
        UInteger maxNodesPerTranslateBrowsePathsToNodeIds) throws UaException;

    /**
     * Read the value of the MaxNodesPerTranslateBrowsePathsToNodeIds Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerTranslateBrowsePathsToNodeIds() throws UaException;

    /**
     * Write a new value for the MaxNodesPerTranslateBrowsePathsToNodeIds Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerTranslateBrowsePathsToNodeIds the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerTranslateBrowsePathsToNodeIds(
        UInteger maxNodesPerTranslateBrowsePathsToNodeIds) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerTranslateBrowsePathsToNodeIds()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerTranslateBrowsePathsToNodeIdsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerTranslateBrowsePathsToNodeIdsAsync(
        UInteger maxNodesPerTranslateBrowsePathsToNodeIds);

    /**
     * Get the MaxNodesPerTranslateBrowsePathsToNodeIds {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerTranslateBrowsePathsToNodeIds {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerTranslateBrowsePathsToNodeIdsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerTranslateBrowsePathsToNodeIdsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerTranslateBrowsePathsToNodeIdsNodeAsync();

    /**
     * Get the local value of the MaxNodesPerNodeManagement Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxNodesPerNodeManagement Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerNodeManagement Node.
     */
    UInteger getMaxNodesPerNodeManagement() throws UaException;

    /**
     * Set the local value of the MaxNodesPerNodeManagement Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxNodesPerNodeManagement the local value to set for the MaxNodesPerNodeManagement Node.
     * @throws UaException if an error occurs creating or getting the MaxNodesPerNodeManagement Node.
     */
    void setMaxNodesPerNodeManagement(UInteger maxNodesPerNodeManagement) throws UaException;

    /**
     * Read the value of the MaxNodesPerNodeManagement Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxNodesPerNodeManagement() throws UaException;

    /**
     * Write a new value for the MaxNodesPerNodeManagement Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxNodesPerNodeManagement the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxNodesPerNodeManagement(UInteger maxNodesPerNodeManagement) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxNodesPerNodeManagement()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxNodesPerNodeManagementAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxNodesPerNodeManagement(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxNodesPerNodeManagementAsync(
        UInteger maxNodesPerNodeManagement);

    /**
     * Get the MaxNodesPerNodeManagement {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxNodesPerNodeManagement {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxNodesPerNodeManagementNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxNodesPerNodeManagementNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxNodesPerNodeManagementNodeAsync();

    /**
     * Get the local value of the MaxMonitoredItemsPerCall Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxMonitoredItemsPerCall Node.
     * @throws UaException if an error occurs creating or getting the MaxMonitoredItemsPerCall Node.
     */
    UInteger getMaxMonitoredItemsPerCall() throws UaException;

    /**
     * Set the local value of the MaxMonitoredItemsPerCall Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxMonitoredItemsPerCall the local value to set for the MaxMonitoredItemsPerCall Node.
     * @throws UaException if an error occurs creating or getting the MaxMonitoredItemsPerCall Node.
     */
    void setMaxMonitoredItemsPerCall(UInteger maxMonitoredItemsPerCall) throws UaException;

    /**
     * Read the value of the MaxMonitoredItemsPerCall Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxMonitoredItemsPerCall() throws UaException;

    /**
     * Write a new value for the MaxMonitoredItemsPerCall Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxMonitoredItemsPerCall the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxMonitoredItemsPerCall(UInteger maxMonitoredItemsPerCall) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxMonitoredItemsPerCall()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxMonitoredItemsPerCallAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxMonitoredItemsPerCall(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxMonitoredItemsPerCallAsync(
        UInteger maxMonitoredItemsPerCall);

    /**
     * Get the MaxMonitoredItemsPerCall {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxMonitoredItemsPerCall {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxMonitoredItemsPerCallNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxMonitoredItemsPerCallNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxMonitoredItemsPerCallNodeAsync();
}

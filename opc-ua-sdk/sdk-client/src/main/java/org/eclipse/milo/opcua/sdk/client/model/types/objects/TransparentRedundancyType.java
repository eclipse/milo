package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.RedundantServerDataType;

public interface TransparentRedundancyType extends ServerRedundancyType {
    QualifiedProperty<String> CURRENT_SERVER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CurrentServerId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<RedundantServerDataType[]> REDUNDANT_SERVER_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RedundantServerArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=853"),
        ValueRanks.OneDimension,
        RedundantServerDataType[].class
    );

    /**
     * Get the local value of the CurrentServerId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CurrentServerId Node.
     * @throws UaException if an error occurs creating or getting the CurrentServerId Node.
     */
    String getCurrentServerId() throws UaException;

    /**
     * Set the local value of the CurrentServerId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param currentServerId the local value to set for the CurrentServerId Node.
     * @throws UaException if an error occurs creating or getting the CurrentServerId Node.
     */
    void setCurrentServerId(String currentServerId) throws UaException;

    /**
     * Read the value of the CurrentServerId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readCurrentServerId() throws UaException;

    /**
     * Write a new value for the CurrentServerId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param currentServerId the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCurrentServerId(String currentServerId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCurrentServerId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readCurrentServerIdAsync();

    /**
     * An asynchronous implementation of {@link #writeCurrentServerId(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCurrentServerIdAsync(String currentServerId);

    /**
     * Get the CurrentServerId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CurrentServerId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getCurrentServerIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCurrentServerIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getCurrentServerIdNodeAsync();

    /**
     * Get the local value of the RedundantServerArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the RedundantServerArray Node.
     * @throws UaException if an error occurs creating or getting the RedundantServerArray Node.
     */
    RedundantServerDataType[] getRedundantServerArray() throws UaException;

    /**
     * Set the local value of the RedundantServerArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param redundantServerArray the local value to set for the RedundantServerArray Node.
     * @throws UaException if an error occurs creating or getting the RedundantServerArray Node.
     */
    void setRedundantServerArray(RedundantServerDataType[] redundantServerArray) throws UaException;

    /**
     * Read the value of the RedundantServerArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link RedundantServerDataType[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    RedundantServerDataType[] readRedundantServerArray() throws UaException;

    /**
     * Write a new value for the RedundantServerArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param redundantServerArray the {@link RedundantServerDataType[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeRedundantServerArray(RedundantServerDataType[] redundantServerArray) throws UaException;

    /**
     * An asynchronous implementation of {@link #readRedundantServerArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends RedundantServerDataType[]> readRedundantServerArrayAsync();

    /**
     * An asynchronous implementation of {@link #writeRedundantServerArray(RedundantServerDataType[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeRedundantServerArrayAsync(
        RedundantServerDataType[] redundantServerArray);

    /**
     * Get the RedundantServerArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RedundantServerArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getRedundantServerArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRedundantServerArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getRedundantServerArrayNodeAsync();
}

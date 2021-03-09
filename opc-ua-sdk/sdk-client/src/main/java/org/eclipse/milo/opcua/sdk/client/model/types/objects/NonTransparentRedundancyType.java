package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface NonTransparentRedundancyType extends ServerRedundancyType {
    QualifiedProperty<String[]> SERVER_URI_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerUriArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    /**
     * Get the local value of the ServerUriArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ServerUriArray Node.
     * @throws UaException if an error occurs creating or getting the ServerUriArray Node.
     */
    String[] getServerUriArray() throws UaException;

    /**
     * Set the local value of the ServerUriArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param serverUriArray the local value to set for the ServerUriArray Node.
     * @throws UaException if an error occurs creating or getting the ServerUriArray Node.
     */
    void setServerUriArray(String[] serverUriArray) throws UaException;

    /**
     * Read the value of the ServerUriArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String[] readServerUriArray() throws UaException;

    /**
     * Write a new value for the ServerUriArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param serverUriArray the {@link String[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeServerUriArray(String[] serverUriArray) throws UaException;

    /**
     * An asynchronous implementation of {@link #readServerUriArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String[]> readServerUriArrayAsync();

    /**
     * An asynchronous implementation of {@link #writeServerUriArray(String[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeServerUriArrayAsync(String[] serverUriArray);

    /**
     * Get the ServerUriArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ServerUriArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getServerUriArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getServerUriArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getServerUriArrayNodeAsync();
}

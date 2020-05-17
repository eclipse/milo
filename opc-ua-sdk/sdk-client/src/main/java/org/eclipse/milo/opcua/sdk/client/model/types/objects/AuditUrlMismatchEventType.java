package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditUrlMismatchEventType extends AuditCreateSessionEventType {
    QualifiedProperty<String> ENDPOINT_URL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EndpointUrl",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    /**
     * Get the local value of the EndpointUrl Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the EndpointUrl Node.
     * @throws UaException if an error occurs creating or getting the EndpointUrl Node.
     */
    String getEndpointUrl() throws UaException;

    /**
     * Set the local value of the EndpointUrl Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param endpointUrl the local value to set for the EndpointUrl Node.
     * @throws UaException if an error occurs creating or getting the EndpointUrl Node.
     */
    void setEndpointUrl(String endpointUrl) throws UaException;

    /**
     * Read the value of the EndpointUrl Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readEndpointUrl() throws UaException;

    /**
     * Write a new value for the EndpointUrl Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param endpointUrl the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeEndpointUrl(String endpointUrl) throws UaException;

    /**
     * An asynchronous implementation of {@link #readEndpointUrl()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readEndpointUrlAsync();

    /**
     * An asynchronous implementation of {@link #writeEndpointUrl(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeEndpointUrlAsync(String endpointUrl);

    /**
     * Get the EndpointUrl {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the EndpointUrl {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getEndpointUrlNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getEndpointUrlNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getEndpointUrlNodeAsync();
}

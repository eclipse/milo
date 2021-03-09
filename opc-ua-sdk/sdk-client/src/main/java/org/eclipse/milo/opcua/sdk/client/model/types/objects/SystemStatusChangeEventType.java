package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

public interface SystemStatusChangeEventType extends SystemEventType {
    QualifiedProperty<ServerState> SYSTEM_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SystemState",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=852"),
        ValueRanks.Scalar,
        ServerState.class
    );

    /**
     * Get the local value of the SystemState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SystemState Node.
     * @throws UaException if an error occurs creating or getting the SystemState Node.
     */
    ServerState getSystemState() throws UaException;

    /**
     * Set the local value of the SystemState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param systemState the local value to set for the SystemState Node.
     * @throws UaException if an error occurs creating or getting the SystemState Node.
     */
    void setSystemState(ServerState systemState) throws UaException;

    /**
     * Read the value of the SystemState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServerState} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServerState readSystemState() throws UaException;

    /**
     * Write a new value for the SystemState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param systemState the {@link ServerState} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSystemState(ServerState systemState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSystemState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServerState> readSystemStateAsync();

    /**
     * An asynchronous implementation of {@link #writeSystemState(ServerState)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSystemStateAsync(ServerState systemState);

    /**
     * Get the SystemState {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SystemState {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSystemStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSystemStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSystemStateNodeAsync();
}

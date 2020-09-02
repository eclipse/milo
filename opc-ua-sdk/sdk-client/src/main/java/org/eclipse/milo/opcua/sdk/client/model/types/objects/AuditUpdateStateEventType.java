package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditUpdateStateEventType extends AuditUpdateMethodEventType {
    QualifiedProperty<Object> OLD_STATE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldStateId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<Object> NEW_STATE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewStateId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    /**
     * Get the local value of the OldStateId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the OldStateId Node.
     * @throws UaException if an error occurs creating or getting the OldStateId Node.
     */
    Object getOldStateId() throws UaException;

    /**
     * Set the local value of the OldStateId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param oldStateId the local value to set for the OldStateId Node.
     * @throws UaException if an error occurs creating or getting the OldStateId Node.
     */
    void setOldStateId(Object oldStateId) throws UaException;

    /**
     * Read the value of the OldStateId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Object} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Object readOldStateId() throws UaException;

    /**
     * Write a new value for the OldStateId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param oldStateId the {@link Object} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeOldStateId(Object oldStateId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readOldStateId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<?> readOldStateIdAsync();

    /**
     * An asynchronous implementation of {@link #writeOldStateId(Object)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeOldStateIdAsync(Object oldStateId);

    /**
     * Get the OldStateId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the OldStateId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getOldStateIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getOldStateIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getOldStateIdNodeAsync();

    /**
     * Get the local value of the NewStateId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NewStateId Node.
     * @throws UaException if an error occurs creating or getting the NewStateId Node.
     */
    Object getNewStateId() throws UaException;

    /**
     * Set the local value of the NewStateId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param newStateId the local value to set for the NewStateId Node.
     * @throws UaException if an error occurs creating or getting the NewStateId Node.
     */
    void setNewStateId(Object newStateId) throws UaException;

    /**
     * Read the value of the NewStateId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Object} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Object readNewStateId() throws UaException;

    /**
     * Write a new value for the NewStateId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param newStateId the {@link Object} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNewStateId(Object newStateId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNewStateId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<?> readNewStateIdAsync();

    /**
     * An asynchronous implementation of {@link #writeNewStateId(Object)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeNewStateIdAsync(Object newStateId);

    /**
     * Get the NewStateId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NewStateId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNewStateIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNewStateIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNewStateIdNodeAsync();
}

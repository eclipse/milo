package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;

public interface AuditDeleteReferencesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<DeleteReferencesItem[]> REFERENCES_TO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReferencesToDelete",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=385"),
        ValueRanks.OneDimension,
        DeleteReferencesItem[].class
    );

    /**
     * Get the local value of the ReferencesToDelete Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ReferencesToDelete Node.
     * @throws UaException if an error occurs creating or getting the ReferencesToDelete Node.
     */
    DeleteReferencesItem[] getReferencesToDelete() throws UaException;

    /**
     * Set the local value of the ReferencesToDelete Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param referencesToDelete the local value to set for the ReferencesToDelete Node.
     * @throws UaException if an error occurs creating or getting the ReferencesToDelete Node.
     */
    void setReferencesToDelete(DeleteReferencesItem[] referencesToDelete) throws UaException;

    /**
     * Read the value of the ReferencesToDelete Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DeleteReferencesItem[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DeleteReferencesItem[] readReferencesToDelete() throws UaException;

    /**
     * Write a new value for the ReferencesToDelete Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param referencesToDelete the {@link DeleteReferencesItem[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeReferencesToDelete(DeleteReferencesItem[] referencesToDelete) throws UaException;

    /**
     * An asynchronous implementation of {@link #readReferencesToDelete()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DeleteReferencesItem[]> readReferencesToDeleteAsync();

    /**
     * An asynchronous implementation of {@link #writeReferencesToDelete(DeleteReferencesItem[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeReferencesToDeleteAsync(
        DeleteReferencesItem[] referencesToDelete);

    /**
     * Get the ReferencesToDelete {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReferencesToDelete {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getReferencesToDeleteNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReferencesToDeleteNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getReferencesToDeleteNodeAsync();
}

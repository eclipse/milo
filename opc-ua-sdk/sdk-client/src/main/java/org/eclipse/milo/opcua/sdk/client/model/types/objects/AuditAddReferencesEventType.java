package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;

public interface AuditAddReferencesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<AddReferencesItem[]> REFERENCES_TO_ADD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReferencesToAdd",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=379"),
        ValueRanks.OneDimension,
        AddReferencesItem[].class
    );

    /**
     * Get the local value of the ReferencesToAdd Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ReferencesToAdd Node.
     * @throws UaException if an error occurs creating or getting the ReferencesToAdd Node.
     */
    AddReferencesItem[] getReferencesToAdd() throws UaException;

    /**
     * Set the local value of the ReferencesToAdd Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param referencesToAdd the local value to set for the ReferencesToAdd Node.
     * @throws UaException if an error occurs creating or getting the ReferencesToAdd Node.
     */
    void setReferencesToAdd(AddReferencesItem[] referencesToAdd) throws UaException;

    /**
     * Read the value of the ReferencesToAdd Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link AddReferencesItem[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    AddReferencesItem[] readReferencesToAdd() throws UaException;

    /**
     * Write a new value for the ReferencesToAdd Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param referencesToAdd the {@link AddReferencesItem[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeReferencesToAdd(AddReferencesItem[] referencesToAdd) throws UaException;

    /**
     * An asynchronous implementation of {@link #readReferencesToAdd()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends AddReferencesItem[]> readReferencesToAddAsync();

    /**
     * An asynchronous implementation of {@link #writeReferencesToAdd(AddReferencesItem[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeReferencesToAddAsync(AddReferencesItem[] referencesToAdd);

    /**
     * Get the ReferencesToAdd {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReferencesToAdd {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getReferencesToAddNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReferencesToAddNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getReferencesToAddNodeAsync();
}

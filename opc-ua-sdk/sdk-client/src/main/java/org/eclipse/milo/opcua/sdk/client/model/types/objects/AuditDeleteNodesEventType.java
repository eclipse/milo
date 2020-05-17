package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditDeleteNodesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<DeleteNodesItem[]> NODES_TO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NodesToDelete",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=382"),
        ValueRanks.OneDimension,
        DeleteNodesItem[].class
    );

    /**
     * Get the local value of the NodesToDelete Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NodesToDelete Node.
     * @throws UaException if an error occurs creating or getting the NodesToDelete Node.
     */
    DeleteNodesItem[] getNodesToDelete() throws UaException;

    /**
     * Set the local value of the NodesToDelete Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param nodesToDelete the local value to set for the NodesToDelete Node.
     * @throws UaException if an error occurs creating or getting the NodesToDelete Node.
     */
    void setNodesToDelete(DeleteNodesItem[] nodesToDelete) throws UaException;

    /**
     * Read the value of the NodesToDelete Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DeleteNodesItem[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DeleteNodesItem[] readNodesToDelete() throws UaException;

    /**
     * Write a new value for the NodesToDelete Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param nodesToDelete the {@link DeleteNodesItem[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNodesToDelete(DeleteNodesItem[] nodesToDelete) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNodesToDelete()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DeleteNodesItem[]> readNodesToDeleteAsync();

    /**
     * An asynchronous implementation of {@link #writeNodesToDelete(DeleteNodesItem[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeNodesToDeleteAsync(DeleteNodesItem[] nodesToDelete);

    /**
     * Get the NodesToDelete {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NodesToDelete {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNodesToDeleteNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNodesToDeleteNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNodesToDeleteNodeAsync();
}

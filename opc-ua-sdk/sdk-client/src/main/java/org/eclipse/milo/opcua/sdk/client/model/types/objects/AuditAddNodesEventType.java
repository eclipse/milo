package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditAddNodesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<AddNodesItem[]> NODES_TO_ADD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NodesToAdd",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=376"),
        ValueRanks.OneDimension,
        AddNodesItem[].class
    );

    /**
     * Get the local value of the NodesToAdd Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NodesToAdd Node.
     * @throws UaException if an error occurs creating or getting the NodesToAdd Node.
     */
    AddNodesItem[] getNodesToAdd() throws UaException;

    /**
     * Set the local value of the NodesToAdd Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param nodesToAdd the local value to set for the NodesToAdd Node.
     * @throws UaException if an error occurs creating or getting the NodesToAdd Node.
     */
    void setNodesToAdd(AddNodesItem[] nodesToAdd) throws UaException;

    /**
     * Read the value of the NodesToAdd Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link AddNodesItem[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    AddNodesItem[] readNodesToAdd() throws UaException;

    /**
     * Write a new value for the NodesToAdd Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param nodesToAdd the {@link AddNodesItem[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNodesToAdd(AddNodesItem[] nodesToAdd) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNodesToAdd()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends AddNodesItem[]> readNodesToAddAsync();

    /**
     * An asynchronous implementation of {@link #writeNodesToAdd(AddNodesItem[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeNodesToAddAsync(AddNodesItem[] nodesToAdd);

    /**
     * Get the NodesToAdd {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NodesToAdd {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNodesToAddNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNodesToAddNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNodesToAddNodeAsync();
}

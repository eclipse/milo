package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditHistoryDeleteEventType extends AuditHistoryUpdateEventType {
    QualifiedProperty<NodeId> UPDATED_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdatedNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    /**
     * Get the local value of the UpdatedNode Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UpdatedNode Node.
     * @throws UaException if an error occurs creating or getting the UpdatedNode Node.
     */
    NodeId getUpdatedNode() throws UaException;

    /**
     * Set the local value of the UpdatedNode Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param updatedNode the local value to set for the UpdatedNode Node.
     * @throws UaException if an error occurs creating or getting the UpdatedNode Node.
     */
    void setUpdatedNode(NodeId updatedNode) throws UaException;

    /**
     * Read the value of the UpdatedNode Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readUpdatedNode() throws UaException;

    /**
     * Write a new value for the UpdatedNode Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param updatedNode the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUpdatedNode(NodeId updatedNode) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUpdatedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readUpdatedNodeAsync();

    /**
     * An asynchronous implementation of {@link #writeUpdatedNode(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeUpdatedNodeAsync(NodeId updatedNode);

    /**
     * Get the UpdatedNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UpdatedNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getUpdatedNodeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUpdatedNodeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getUpdatedNodeNodeAsync();
}

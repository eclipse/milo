package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditConditionRespondEventType extends AuditConditionEventType {
    QualifiedProperty<Integer> SELECTED_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SelectedResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    /**
     * Get the local value of the SelectedResponse Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SelectedResponse Node.
     * @throws UaException if an error occurs creating or getting the SelectedResponse Node.
     */
    Integer getSelectedResponse() throws UaException;

    /**
     * Set the local value of the SelectedResponse Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param selectedResponse the local value to set for the SelectedResponse Node.
     * @throws UaException if an error occurs creating or getting the SelectedResponse Node.
     */
    void setSelectedResponse(Integer selectedResponse) throws UaException;

    /**
     * Read the value of the SelectedResponse Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Integer} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Integer readSelectedResponse() throws UaException;

    /**
     * Write a new value for the SelectedResponse Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param selectedResponse the {@link Integer} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSelectedResponse(Integer selectedResponse) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSelectedResponse()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Integer> readSelectedResponseAsync();

    /**
     * An asynchronous implementation of {@link #writeSelectedResponse(Integer)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeSelectedResponseAsync(Integer selectedResponse);

    /**
     * Get the SelectedResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SelectedResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSelectedResponseNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSelectedResponseNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSelectedResponseNodeAsync();
}

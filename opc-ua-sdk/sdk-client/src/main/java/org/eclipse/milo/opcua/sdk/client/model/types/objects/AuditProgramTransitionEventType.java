package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface AuditProgramTransitionEventType extends AuditUpdateStateEventType {
    QualifiedProperty<UInteger> TRANSITION_NUMBER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TransitionNumber",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    /**
     * Get the local value of the TransitionNumber Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the TransitionNumber Node.
     * @throws UaException if an error occurs creating or getting the TransitionNumber Node.
     */
    UInteger getTransitionNumber() throws UaException;

    /**
     * Set the local value of the TransitionNumber Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param transitionNumber the local value to set for the TransitionNumber Node.
     * @throws UaException if an error occurs creating or getting the TransitionNumber Node.
     */
    void setTransitionNumber(UInteger transitionNumber) throws UaException;

    /**
     * Read the value of the TransitionNumber Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readTransitionNumber() throws UaException;

    /**
     * Write a new value for the TransitionNumber Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param transitionNumber the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeTransitionNumber(UInteger transitionNumber) throws UaException;

    /**
     * An asynchronous implementation of {@link #readTransitionNumber()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readTransitionNumberAsync();

    /**
     * An asynchronous implementation of {@link #writeTransitionNumber(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeTransitionNumberAsync(UInteger transitionNumber);

    /**
     * Get the TransitionNumber {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the TransitionNumber {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getTransitionNumberNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTransitionNumberNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getTransitionNumberNodeAsync();
}

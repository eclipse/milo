package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface StateType extends BaseObjectType {
    QualifiedProperty<UInteger> STATE_NUMBER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StateNumber",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    /**
     * Get the local value of the StateNumber Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the StateNumber Node.
     * @throws UaException if an error occurs creating or getting the StateNumber Node.
     */
    UInteger getStateNumber() throws UaException;

    /**
     * Set the local value of the StateNumber Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param stateNumber the local value to set for the StateNumber Node.
     * @throws UaException if an error occurs creating or getting the StateNumber Node.
     */
    void setStateNumber(UInteger stateNumber) throws UaException;

    /**
     * Read the value of the StateNumber Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readStateNumber() throws UaException;

    /**
     * Write a new value for the StateNumber Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param stateNumber the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeStateNumber(UInteger stateNumber) throws UaException;

    /**
     * An asynchronous implementation of {@link #readStateNumber()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readStateNumberAsync();

    /**
     * An asynchronous implementation of {@link #writeStateNumber(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeStateNumberAsync(UInteger stateNumber);

    /**
     * Get the StateNumber {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the StateNumber {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getStateNumberNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getStateNumberNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getStateNumberNodeAsync();
}

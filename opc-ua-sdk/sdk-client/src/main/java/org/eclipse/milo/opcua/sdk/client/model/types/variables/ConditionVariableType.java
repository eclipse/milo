package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface ConditionVariableType extends BaseDataVariableType {
    QualifiedProperty<DateTime> SOURCE_TIMESTAMP = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceTimestamp",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    /**
     * Get the local value of the SourceTimestamp Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SourceTimestamp Node.
     * @throws UaException if an error occurs creating or getting the SourceTimestamp Node.
     */
    DateTime getSourceTimestamp() throws UaException;

    /**
     * Set the local value of the SourceTimestamp Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sourceTimestamp the local value to set for the SourceTimestamp Node.
     * @throws UaException if an error occurs creating or getting the SourceTimestamp Node.
     */
    void setSourceTimestamp(DateTime sourceTimestamp) throws UaException;

    /**
     * Read the value of the SourceTimestamp Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readSourceTimestamp() throws UaException;

    /**
     * Write a new value for the SourceTimestamp Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sourceTimestamp the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSourceTimestamp(DateTime sourceTimestamp) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSourceTimestamp()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readSourceTimestampAsync();

    /**
     * An asynchronous implementation of {@link #writeSourceTimestamp(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSourceTimestampAsync(DateTime sourceTimestamp);

    /**
     * Get the SourceTimestamp {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SourceTimestamp {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSourceTimestampNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSourceTimestampNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSourceTimestampNodeAsync();
}

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface TrustListType extends FileType {
    QualifiedProperty<DateTime> LAST_UPDATE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastUpdateTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    /**
     * Get the local value of the LastUpdateTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastUpdateTime Node.
     * @throws UaException if an error occurs creating or getting the LastUpdateTime Node.
     */
    DateTime getLastUpdateTime() throws UaException;

    /**
     * Set the local value of the LastUpdateTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastUpdateTime the local value to set for the LastUpdateTime Node.
     * @throws UaException if an error occurs creating or getting the LastUpdateTime Node.
     */
    void setLastUpdateTime(DateTime lastUpdateTime) throws UaException;

    /**
     * Read the value of the LastUpdateTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readLastUpdateTime() throws UaException;

    /**
     * Write a new value for the LastUpdateTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastUpdateTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastUpdateTime(DateTime lastUpdateTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastUpdateTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readLastUpdateTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeLastUpdateTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeLastUpdateTimeAsync(DateTime lastUpdateTime);

    /**
     * Get the LastUpdateTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastUpdateTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastUpdateTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastUpdateTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastUpdateTimeNodeAsync();
}

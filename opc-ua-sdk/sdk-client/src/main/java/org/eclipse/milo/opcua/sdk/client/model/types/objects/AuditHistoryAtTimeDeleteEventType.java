package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface AuditHistoryAtTimeDeleteEventType extends AuditHistoryDeleteEventType {
    QualifiedProperty<DateTime[]> REQ_TIMES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReqTimes",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.OneDimension,
        DateTime[].class
    );

    QualifiedProperty<DataValue[]> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    /**
     * Get the local value of the ReqTimes Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ReqTimes Node.
     * @throws UaException if an error occurs creating or getting the ReqTimes Node.
     */
    DateTime[] getReqTimes() throws UaException;

    /**
     * Set the local value of the ReqTimes Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param reqTimes the local value to set for the ReqTimes Node.
     * @throws UaException if an error occurs creating or getting the ReqTimes Node.
     */
    void setReqTimes(DateTime[] reqTimes) throws UaException;

    /**
     * Read the value of the ReqTimes Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime[] readReqTimes() throws UaException;

    /**
     * Write a new value for the ReqTimes Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param reqTimes the {@link DateTime[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeReqTimes(DateTime[] reqTimes) throws UaException;

    /**
     * An asynchronous implementation of {@link #readReqTimes()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime[]> readReqTimesAsync();

    /**
     * An asynchronous implementation of {@link #writeReqTimes(DateTime[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeReqTimesAsync(DateTime[] reqTimes);

    /**
     * Get the ReqTimes {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReqTimes {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getReqTimesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReqTimesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getReqTimesNodeAsync();

    /**
     * Get the local value of the OldValues Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the OldValues Node.
     * @throws UaException if an error occurs creating or getting the OldValues Node.
     */
    DataValue[] getOldValues() throws UaException;

    /**
     * Set the local value of the OldValues Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param oldValues the local value to set for the OldValues Node.
     * @throws UaException if an error occurs creating or getting the OldValues Node.
     */
    void setOldValues(DataValue[] oldValues) throws UaException;

    /**
     * Read the value of the OldValues Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DataValue[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DataValue[] readOldValues() throws UaException;

    /**
     * Write a new value for the OldValues Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param oldValues the {@link DataValue[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeOldValues(DataValue[] oldValues) throws UaException;

    /**
     * An asynchronous implementation of {@link #readOldValues()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DataValue[]> readOldValuesAsync();

    /**
     * An asynchronous implementation of {@link #writeOldValues(DataValue[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeOldValuesAsync(DataValue[] oldValues);

    /**
     * Get the OldValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the OldValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getOldValuesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getOldValuesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getOldValuesNodeAsync();
}

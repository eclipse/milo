package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditHistoryRawModifyDeleteEventType extends AuditHistoryDeleteEventType {
    QualifiedProperty<Boolean> IS_DELETE_MODIFIED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IsDeleteModified",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<DateTime> START_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StartTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> END_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EndTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DataValue[]> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    /**
     * Get the local value of the IsDeleteModified Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the IsDeleteModified Node.
     * @throws UaException if an error occurs creating or getting the IsDeleteModified Node.
     */
    Boolean getIsDeleteModified() throws UaException;

    /**
     * Set the local value of the IsDeleteModified Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param isDeleteModified the local value to set for the IsDeleteModified Node.
     * @throws UaException if an error occurs creating or getting the IsDeleteModified Node.
     */
    void setIsDeleteModified(Boolean isDeleteModified) throws UaException;

    /**
     * Read the value of the IsDeleteModified Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readIsDeleteModified() throws UaException;

    /**
     * Write a new value for the IsDeleteModified Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param isDeleteModified the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeIsDeleteModified(Boolean isDeleteModified) throws UaException;

    /**
     * An asynchronous implementation of {@link #readIsDeleteModified()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readIsDeleteModifiedAsync();

    /**
     * An asynchronous implementation of {@link #writeIsDeleteModified(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeIsDeleteModifiedAsync(Boolean isDeleteModified);

    /**
     * Get the IsDeleteModified {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the IsDeleteModified {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getIsDeleteModifiedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getIsDeleteModifiedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getIsDeleteModifiedNodeAsync();

    /**
     * Get the local value of the StartTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the StartTime Node.
     * @throws UaException if an error occurs creating or getting the StartTime Node.
     */
    DateTime getStartTime() throws UaException;

    /**
     * Set the local value of the StartTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param startTime the local value to set for the StartTime Node.
     * @throws UaException if an error occurs creating or getting the StartTime Node.
     */
    void setStartTime(DateTime startTime) throws UaException;

    /**
     * Read the value of the StartTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readStartTime() throws UaException;

    /**
     * Write a new value for the StartTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param startTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeStartTime(DateTime startTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readStartTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readStartTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeStartTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeStartTimeAsync(DateTime startTime);

    /**
     * Get the StartTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the StartTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getStartTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getStartTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getStartTimeNodeAsync();

    /**
     * Get the local value of the EndTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the EndTime Node.
     * @throws UaException if an error occurs creating or getting the EndTime Node.
     */
    DateTime getEndTime() throws UaException;

    /**
     * Set the local value of the EndTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param endTime the local value to set for the EndTime Node.
     * @throws UaException if an error occurs creating or getting the EndTime Node.
     */
    void setEndTime(DateTime endTime) throws UaException;

    /**
     * Read the value of the EndTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readEndTime() throws UaException;

    /**
     * Write a new value for the EndTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param endTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeEndTime(DateTime endTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readEndTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readEndTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeEndTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeEndTimeAsync(DateTime endTime);

    /**
     * Get the EndTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the EndTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getEndTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getEndTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getEndTimeNodeAsync();

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
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeOldValuesAsync(DataValue[] oldValues);

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

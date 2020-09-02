package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditHistoryEventDeleteEventType extends AuditHistoryDeleteEventType {
    QualifiedProperty<ByteString[]> EVENT_IDS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventIds",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15"),
        ValueRanks.OneDimension,
        ByteString[].class
    );

    QualifiedProperty<HistoryEventFieldList> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=920"),
        ValueRanks.Scalar,
        HistoryEventFieldList.class
    );

    /**
     * Get the local value of the EventIds Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the EventIds Node.
     * @throws UaException if an error occurs creating or getting the EventIds Node.
     */
    ByteString[] getEventIds() throws UaException;

    /**
     * Set the local value of the EventIds Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param eventIds the local value to set for the EventIds Node.
     * @throws UaException if an error occurs creating or getting the EventIds Node.
     */
    void setEventIds(ByteString[] eventIds) throws UaException;

    /**
     * Read the value of the EventIds Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ByteString[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ByteString[] readEventIds() throws UaException;

    /**
     * Write a new value for the EventIds Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param eventIds the {@link ByteString[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeEventIds(ByteString[] eventIds) throws UaException;

    /**
     * An asynchronous implementation of {@link #readEventIds()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ByteString[]> readEventIdsAsync();

    /**
     * An asynchronous implementation of {@link #writeEventIds(ByteString[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeEventIdsAsync(ByteString[] eventIds);

    /**
     * Get the EventIds {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the EventIds {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getEventIdsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getEventIdsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getEventIdsNodeAsync();

    /**
     * Get the local value of the OldValues Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the OldValues Node.
     * @throws UaException if an error occurs creating or getting the OldValues Node.
     */
    HistoryEventFieldList getOldValues() throws UaException;

    /**
     * Set the local value of the OldValues Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param oldValues the local value to set for the OldValues Node.
     * @throws UaException if an error occurs creating or getting the OldValues Node.
     */
    void setOldValues(HistoryEventFieldList oldValues) throws UaException;

    /**
     * Read the value of the OldValues Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link HistoryEventFieldList} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    HistoryEventFieldList readOldValues() throws UaException;

    /**
     * Write a new value for the OldValues Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param oldValues the {@link HistoryEventFieldList} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeOldValues(HistoryEventFieldList oldValues) throws UaException;

    /**
     * An asynchronous implementation of {@link #readOldValues()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends HistoryEventFieldList> readOldValuesAsync();

    /**
     * An asynchronous implementation of {@link #writeOldValues(HistoryEventFieldList)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeOldValuesAsync(HistoryEventFieldList oldValues);

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

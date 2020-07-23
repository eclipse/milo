package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditWriteUpdateEventType extends AuditUpdateEventType {
    QualifiedProperty<UInteger> ATTRIBUTE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AttributeId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<String> INDEX_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IndexRange",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=291"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Object> OLD_VALUE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValue",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<Object> NEW_VALUE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewValue",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    /**
     * Get the local value of the AttributeId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the AttributeId Node.
     * @throws UaException if an error occurs creating or getting the AttributeId Node.
     */
    UInteger getAttributeId() throws UaException;

    /**
     * Set the local value of the AttributeId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param attributeId the local value to set for the AttributeId Node.
     * @throws UaException if an error occurs creating or getting the AttributeId Node.
     */
    void setAttributeId(UInteger attributeId) throws UaException;

    /**
     * Read the value of the AttributeId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readAttributeId() throws UaException;

    /**
     * Write a new value for the AttributeId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param attributeId the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeAttributeId(UInteger attributeId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readAttributeId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readAttributeIdAsync();

    /**
     * An asynchronous implementation of {@link #writeAttributeId(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeAttributeIdAsync(UInteger attributeId);

    /**
     * Get the AttributeId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AttributeId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getAttributeIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAttributeIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getAttributeIdNodeAsync();

    /**
     * Get the local value of the IndexRange Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the IndexRange Node.
     * @throws UaException if an error occurs creating or getting the IndexRange Node.
     */
    String getIndexRange() throws UaException;

    /**
     * Set the local value of the IndexRange Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param indexRange the local value to set for the IndexRange Node.
     * @throws UaException if an error occurs creating or getting the IndexRange Node.
     */
    void setIndexRange(String indexRange) throws UaException;

    /**
     * Read the value of the IndexRange Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readIndexRange() throws UaException;

    /**
     * Write a new value for the IndexRange Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param indexRange the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeIndexRange(String indexRange) throws UaException;

    /**
     * An asynchronous implementation of {@link #readIndexRange()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readIndexRangeAsync();

    /**
     * An asynchronous implementation of {@link #writeIndexRange(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeIndexRangeAsync(String indexRange);

    /**
     * Get the IndexRange {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the IndexRange {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getIndexRangeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getIndexRangeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getIndexRangeNodeAsync();

    /**
     * Get the local value of the OldValue Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the OldValue Node.
     * @throws UaException if an error occurs creating or getting the OldValue Node.
     */
    Object getOldValue() throws UaException;

    /**
     * Set the local value of the OldValue Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param oldValue the local value to set for the OldValue Node.
     * @throws UaException if an error occurs creating or getting the OldValue Node.
     */
    void setOldValue(Object oldValue) throws UaException;

    /**
     * Read the value of the OldValue Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Object} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Object readOldValue() throws UaException;

    /**
     * Write a new value for the OldValue Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param oldValue the {@link Object} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeOldValue(Object oldValue) throws UaException;

    /**
     * An asynchronous implementation of {@link #readOldValue()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<?> readOldValueAsync();

    /**
     * An asynchronous implementation of {@link #writeOldValue(Object)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeOldValueAsync(Object oldValue);

    /**
     * Get the OldValue {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the OldValue {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getOldValueNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getOldValueNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getOldValueNodeAsync();

    /**
     * Get the local value of the NewValue Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NewValue Node.
     * @throws UaException if an error occurs creating or getting the NewValue Node.
     */
    Object getNewValue() throws UaException;

    /**
     * Set the local value of the NewValue Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param newValue the local value to set for the NewValue Node.
     * @throws UaException if an error occurs creating or getting the NewValue Node.
     */
    void setNewValue(Object newValue) throws UaException;

    /**
     * Read the value of the NewValue Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Object} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Object readNewValue() throws UaException;

    /**
     * Write a new value for the NewValue Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param newValue the {@link Object} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNewValue(Object newValue) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNewValue()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<?> readNewValueAsync();

    /**
     * An asynchronous implementation of {@link #writeNewValue(Object)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeNewValueAsync(Object newValue);

    /**
     * Get the NewValue {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NewValue {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNewValueNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNewValueNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNewValueNodeAsync();
}

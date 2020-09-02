package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface FileType extends BaseObjectType {
    QualifiedProperty<ULong> SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Size",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=9"),
        ValueRanks.Scalar,
        ULong.class
    );

    QualifiedProperty<Boolean> WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Writable",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> USER_WRITABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserWritable",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UShort> OPEN_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OpenCount",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<String> MIME_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MimeType",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    /**
     * Get the local value of the Size Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Size Node.
     * @throws UaException if an error occurs creating or getting the Size Node.
     */
    ULong getSize() throws UaException;

    /**
     * Set the local value of the Size Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param size the local value to set for the Size Node.
     * @throws UaException if an error occurs creating or getting the Size Node.
     */
    void setSize(ULong size) throws UaException;

    /**
     * Read the value of the Size Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ULong} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ULong readSize() throws UaException;

    /**
     * Write a new value for the Size Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param size the {@link ULong} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSize(ULong size) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSize()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ULong> readSizeAsync();

    /**
     * An asynchronous implementation of {@link #writeSize(ULong)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeSizeAsync(ULong size);

    /**
     * Get the Size {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Size {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSizeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSizeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSizeNodeAsync();

    /**
     * Get the local value of the Writable Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Writable Node.
     * @throws UaException if an error occurs creating or getting the Writable Node.
     */
    Boolean getWritable() throws UaException;

    /**
     * Set the local value of the Writable Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param writable the local value to set for the Writable Node.
     * @throws UaException if an error occurs creating or getting the Writable Node.
     */
    void setWritable(Boolean writable) throws UaException;

    /**
     * Read the value of the Writable Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readWritable() throws UaException;

    /**
     * Write a new value for the Writable Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param writable the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeWritable(Boolean writable) throws UaException;

    /**
     * An asynchronous implementation of {@link #readWritable()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readWritableAsync();

    /**
     * An asynchronous implementation of {@link #writeWritable(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeWritableAsync(Boolean writable);

    /**
     * Get the Writable {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Writable {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getWritableNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getWritableNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getWritableNodeAsync();

    /**
     * Get the local value of the UserWritable Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UserWritable Node.
     * @throws UaException if an error occurs creating or getting the UserWritable Node.
     */
    Boolean getUserWritable() throws UaException;

    /**
     * Set the local value of the UserWritable Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param userWritable the local value to set for the UserWritable Node.
     * @throws UaException if an error occurs creating or getting the UserWritable Node.
     */
    void setUserWritable(Boolean userWritable) throws UaException;

    /**
     * Read the value of the UserWritable Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readUserWritable() throws UaException;

    /**
     * Write a new value for the UserWritable Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param userWritable the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUserWritable(Boolean userWritable) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUserWritable()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readUserWritableAsync();

    /**
     * An asynchronous implementation of {@link #writeUserWritable(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeUserWritableAsync(Boolean userWritable);

    /**
     * Get the UserWritable {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UserWritable {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getUserWritableNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUserWritableNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getUserWritableNodeAsync();

    /**
     * Get the local value of the OpenCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the OpenCount Node.
     * @throws UaException if an error occurs creating or getting the OpenCount Node.
     */
    UShort getOpenCount() throws UaException;

    /**
     * Set the local value of the OpenCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param openCount the local value to set for the OpenCount Node.
     * @throws UaException if an error occurs creating or getting the OpenCount Node.
     */
    void setOpenCount(UShort openCount) throws UaException;

    /**
     * Read the value of the OpenCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UShort} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UShort readOpenCount() throws UaException;

    /**
     * Write a new value for the OpenCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param openCount the {@link UShort} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeOpenCount(UShort openCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readOpenCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UShort> readOpenCountAsync();

    /**
     * An asynchronous implementation of {@link #writeOpenCount(UShort)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeOpenCountAsync(UShort openCount);

    /**
     * Get the OpenCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the OpenCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getOpenCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getOpenCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getOpenCountNodeAsync();

    /**
     * Get the local value of the MimeType Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MimeType Node.
     * @throws UaException if an error occurs creating or getting the MimeType Node.
     */
    String getMimeType() throws UaException;

    /**
     * Set the local value of the MimeType Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param mimeType the local value to set for the MimeType Node.
     * @throws UaException if an error occurs creating or getting the MimeType Node.
     */
    void setMimeType(String mimeType) throws UaException;

    /**
     * Read the value of the MimeType Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readMimeType() throws UaException;

    /**
     * Write a new value for the MimeType Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param mimeType the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMimeType(String mimeType) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMimeType()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readMimeTypeAsync();

    /**
     * An asynchronous implementation of {@link #writeMimeType(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeMimeTypeAsync(String mimeType);

    /**
     * Get the MimeType {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MimeType {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMimeTypeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMimeTypeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMimeTypeNodeAsync();
}

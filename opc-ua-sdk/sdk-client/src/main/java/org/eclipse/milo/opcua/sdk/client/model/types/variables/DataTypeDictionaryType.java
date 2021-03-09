package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface DataTypeDictionaryType extends BaseDataVariableType {
    QualifiedProperty<String> DATA_TYPE_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DataTypeVersion",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> NAMESPACE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    /**
     * Get the local value of the DataTypeVersion Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DataTypeVersion Node.
     * @throws UaException if an error occurs creating or getting the DataTypeVersion Node.
     */
    String getDataTypeVersion() throws UaException;

    /**
     * Set the local value of the DataTypeVersion Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param dataTypeVersion the local value to set for the DataTypeVersion Node.
     * @throws UaException if an error occurs creating or getting the DataTypeVersion Node.
     */
    void setDataTypeVersion(String dataTypeVersion) throws UaException;

    /**
     * Read the value of the DataTypeVersion Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readDataTypeVersion() throws UaException;

    /**
     * Write a new value for the DataTypeVersion Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param dataTypeVersion the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDataTypeVersion(String dataTypeVersion) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDataTypeVersion()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readDataTypeVersionAsync();

    /**
     * An asynchronous implementation of {@link #writeDataTypeVersion(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDataTypeVersionAsync(String dataTypeVersion);

    /**
     * Get the DataTypeVersion {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DataTypeVersion {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getDataTypeVersionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDataTypeVersionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getDataTypeVersionNodeAsync();

    /**
     * Get the local value of the NamespaceUri Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NamespaceUri Node.
     * @throws UaException if an error occurs creating or getting the NamespaceUri Node.
     */
    String getNamespaceUri() throws UaException;

    /**
     * Set the local value of the NamespaceUri Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param namespaceUri the local value to set for the NamespaceUri Node.
     * @throws UaException if an error occurs creating or getting the NamespaceUri Node.
     */
    void setNamespaceUri(String namespaceUri) throws UaException;

    /**
     * Read the value of the NamespaceUri Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readNamespaceUri() throws UaException;

    /**
     * Write a new value for the NamespaceUri Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param namespaceUri the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNamespaceUri(String namespaceUri) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNamespaceUri()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readNamespaceUriAsync();

    /**
     * An asynchronous implementation of {@link #writeNamespaceUri(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeNamespaceUriAsync(String namespaceUri);

    /**
     * Get the NamespaceUri {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NamespaceUri {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNamespaceUriNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNamespaceUriNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNamespaceUriNodeAsync();
}

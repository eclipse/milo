package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface DataTypeDescriptionType extends BaseDataVariableType {
    QualifiedProperty<String> DATA_TYPE_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DataTypeVersion",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<ByteString> DICTIONARY_FRAGMENT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DictionaryFragment",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15"),
        ValueRanks.Scalar,
        ByteString.class
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
     * Get the local value of the DictionaryFragment Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DictionaryFragment Node.
     * @throws UaException if an error occurs creating or getting the DictionaryFragment Node.
     */
    ByteString getDictionaryFragment() throws UaException;

    /**
     * Set the local value of the DictionaryFragment Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param dictionaryFragment the local value to set for the DictionaryFragment Node.
     * @throws UaException if an error occurs creating or getting the DictionaryFragment Node.
     */
    void setDictionaryFragment(ByteString dictionaryFragment) throws UaException;

    /**
     * Read the value of the DictionaryFragment Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ByteString} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ByteString readDictionaryFragment() throws UaException;

    /**
     * Write a new value for the DictionaryFragment Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param dictionaryFragment the {@link ByteString} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDictionaryFragment(ByteString dictionaryFragment) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDictionaryFragment()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ByteString> readDictionaryFragmentAsync();

    /**
     * An asynchronous implementation of {@link #writeDictionaryFragment(ByteString)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDictionaryFragmentAsync(ByteString dictionaryFragment);

    /**
     * Get the DictionaryFragment {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DictionaryFragment {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getDictionaryFragmentNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDictionaryFragmentNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getDictionaryFragmentNodeAsync();
}

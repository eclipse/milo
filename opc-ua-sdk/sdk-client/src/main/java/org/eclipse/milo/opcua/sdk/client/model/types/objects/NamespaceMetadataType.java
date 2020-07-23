package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface NamespaceMetadataType extends BaseObjectType {
    QualifiedProperty<String> NAMESPACE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> NAMESPACE_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceVersion",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<DateTime> NAMESPACE_PUBLICATION_DATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespacePublicationDate",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=13"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<Boolean> IS_NAMESPACE_SUBSET = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IsNamespaceSubset",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<IdType[]> STATIC_NODE_ID_TYPES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StaticNodeIdTypes",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=256"),
        ValueRanks.OneDimension,
        IdType[].class
    );

    QualifiedProperty<String[]> STATIC_NUMERIC_NODE_ID_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StaticNumericNodeIdRange",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=291"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String> STATIC_STRING_NODE_ID_PATTERN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StaticStringNodeIdPattern",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

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
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeNamespaceUriAsync(String namespaceUri);

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

    /**
     * Get the local value of the NamespaceVersion Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NamespaceVersion Node.
     * @throws UaException if an error occurs creating or getting the NamespaceVersion Node.
     */
    String getNamespaceVersion() throws UaException;

    /**
     * Set the local value of the NamespaceVersion Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param namespaceVersion the local value to set for the NamespaceVersion Node.
     * @throws UaException if an error occurs creating or getting the NamespaceVersion Node.
     */
    void setNamespaceVersion(String namespaceVersion) throws UaException;

    /**
     * Read the value of the NamespaceVersion Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readNamespaceVersion() throws UaException;

    /**
     * Write a new value for the NamespaceVersion Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param namespaceVersion the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNamespaceVersion(String namespaceVersion) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNamespaceVersion()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readNamespaceVersionAsync();

    /**
     * An asynchronous implementation of {@link #writeNamespaceVersion(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeNamespaceVersionAsync(String namespaceVersion);

    /**
     * Get the NamespaceVersion {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NamespaceVersion {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNamespaceVersionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNamespaceVersionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNamespaceVersionNodeAsync();

    /**
     * Get the local value of the NamespacePublicationDate Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NamespacePublicationDate Node.
     * @throws UaException if an error occurs creating or getting the NamespacePublicationDate Node.
     */
    DateTime getNamespacePublicationDate() throws UaException;

    /**
     * Set the local value of the NamespacePublicationDate Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param namespacePublicationDate the local value to set for the NamespacePublicationDate Node.
     * @throws UaException if an error occurs creating or getting the NamespacePublicationDate Node.
     */
    void setNamespacePublicationDate(DateTime namespacePublicationDate) throws UaException;

    /**
     * Read the value of the NamespacePublicationDate Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readNamespacePublicationDate() throws UaException;

    /**
     * Write a new value for the NamespacePublicationDate Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param namespacePublicationDate the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNamespacePublicationDate(DateTime namespacePublicationDate) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNamespacePublicationDate()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readNamespacePublicationDateAsync();

    /**
     * An asynchronous implementation of {@link #writeNamespacePublicationDate(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeNamespacePublicationDateAsync(DateTime namespacePublicationDate);

    /**
     * Get the NamespacePublicationDate {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NamespacePublicationDate {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNamespacePublicationDateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNamespacePublicationDateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNamespacePublicationDateNodeAsync();

    /**
     * Get the local value of the IsNamespaceSubset Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the IsNamespaceSubset Node.
     * @throws UaException if an error occurs creating or getting the IsNamespaceSubset Node.
     */
    Boolean getIsNamespaceSubset() throws UaException;

    /**
     * Set the local value of the IsNamespaceSubset Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param isNamespaceSubset the local value to set for the IsNamespaceSubset Node.
     * @throws UaException if an error occurs creating or getting the IsNamespaceSubset Node.
     */
    void setIsNamespaceSubset(Boolean isNamespaceSubset) throws UaException;

    /**
     * Read the value of the IsNamespaceSubset Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readIsNamespaceSubset() throws UaException;

    /**
     * Write a new value for the IsNamespaceSubset Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param isNamespaceSubset the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeIsNamespaceSubset(Boolean isNamespaceSubset) throws UaException;

    /**
     * An asynchronous implementation of {@link #readIsNamespaceSubset()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readIsNamespaceSubsetAsync();

    /**
     * An asynchronous implementation of {@link #writeIsNamespaceSubset(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeIsNamespaceSubsetAsync(Boolean isNamespaceSubset);

    /**
     * Get the IsNamespaceSubset {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the IsNamespaceSubset {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getIsNamespaceSubsetNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getIsNamespaceSubsetNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getIsNamespaceSubsetNodeAsync();

    /**
     * Get the local value of the StaticNodeIdTypes Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the StaticNodeIdTypes Node.
     * @throws UaException if an error occurs creating or getting the StaticNodeIdTypes Node.
     */
    IdType[] getStaticNodeIdTypes() throws UaException;

    /**
     * Set the local value of the StaticNodeIdTypes Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param staticNodeIdTypes the local value to set for the StaticNodeIdTypes Node.
     * @throws UaException if an error occurs creating or getting the StaticNodeIdTypes Node.
     */
    void setStaticNodeIdTypes(IdType[] staticNodeIdTypes) throws UaException;

    /**
     * Read the value of the StaticNodeIdTypes Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link IdType[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    IdType[] readStaticNodeIdTypes() throws UaException;

    /**
     * Write a new value for the StaticNodeIdTypes Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param staticNodeIdTypes the {@link IdType[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeStaticNodeIdTypes(IdType[] staticNodeIdTypes) throws UaException;

    /**
     * An asynchronous implementation of {@link #readStaticNodeIdTypes()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends IdType[]> readStaticNodeIdTypesAsync();

    /**
     * An asynchronous implementation of {@link #writeStaticNodeIdTypes(IdType[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeStaticNodeIdTypesAsync(IdType[] staticNodeIdTypes);

    /**
     * Get the StaticNodeIdTypes {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the StaticNodeIdTypes {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getStaticNodeIdTypesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getStaticNodeIdTypesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getStaticNodeIdTypesNodeAsync();

    /**
     * Get the local value of the StaticNumericNodeIdRange Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the StaticNumericNodeIdRange Node.
     * @throws UaException if an error occurs creating or getting the StaticNumericNodeIdRange Node.
     */
    String[] getStaticNumericNodeIdRange() throws UaException;

    /**
     * Set the local value of the StaticNumericNodeIdRange Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param staticNumericNodeIdRange the local value to set for the StaticNumericNodeIdRange Node.
     * @throws UaException if an error occurs creating or getting the StaticNumericNodeIdRange Node.
     */
    void setStaticNumericNodeIdRange(String[] staticNumericNodeIdRange) throws UaException;

    /**
     * Read the value of the StaticNumericNodeIdRange Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String[] readStaticNumericNodeIdRange() throws UaException;

    /**
     * Write a new value for the StaticNumericNodeIdRange Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param staticNumericNodeIdRange the {@link String[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeStaticNumericNodeIdRange(String[] staticNumericNodeIdRange) throws UaException;

    /**
     * An asynchronous implementation of {@link #readStaticNumericNodeIdRange()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String[]> readStaticNumericNodeIdRangeAsync();

    /**
     * An asynchronous implementation of {@link #writeStaticNumericNodeIdRange(String[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeStaticNumericNodeIdRangeAsync(String[] staticNumericNodeIdRange);

    /**
     * Get the StaticNumericNodeIdRange {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the StaticNumericNodeIdRange {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getStaticNumericNodeIdRangeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getStaticNumericNodeIdRangeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getStaticNumericNodeIdRangeNodeAsync();

    /**
     * Get the local value of the StaticStringNodeIdPattern Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the StaticStringNodeIdPattern Node.
     * @throws UaException if an error occurs creating or getting the StaticStringNodeIdPattern Node.
     */
    String getStaticStringNodeIdPattern() throws UaException;

    /**
     * Set the local value of the StaticStringNodeIdPattern Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param staticStringNodeIdPattern the local value to set for the StaticStringNodeIdPattern Node.
     * @throws UaException if an error occurs creating or getting the StaticStringNodeIdPattern Node.
     */
    void setStaticStringNodeIdPattern(String staticStringNodeIdPattern) throws UaException;

    /**
     * Read the value of the StaticStringNodeIdPattern Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readStaticStringNodeIdPattern() throws UaException;

    /**
     * Write a new value for the StaticStringNodeIdPattern Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param staticStringNodeIdPattern the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeStaticStringNodeIdPattern(String staticStringNodeIdPattern) throws UaException;

    /**
     * An asynchronous implementation of {@link #readStaticStringNodeIdPattern()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readStaticStringNodeIdPatternAsync();

    /**
     * An asynchronous implementation of {@link #writeStaticStringNodeIdPattern(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeStaticStringNodeIdPatternAsync(String staticStringNodeIdPattern);

    /**
     * Get the StaticStringNodeIdPattern {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the StaticStringNodeIdPattern {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getStaticStringNodeIdPatternNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getStaticStringNodeIdPatternNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getStaticStringNodeIdPatternNodeAsync();

    /**
     * Get the NamespaceFile {@link AddressSpaceFileType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NamespaceFile {@link AddressSpaceFileType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    AddressSpaceFileType getNamespaceFileNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNamespaceFileNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * AddressSpaceFileType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends AddressSpaceFileType> getNamespaceFileNodeAsync();
}

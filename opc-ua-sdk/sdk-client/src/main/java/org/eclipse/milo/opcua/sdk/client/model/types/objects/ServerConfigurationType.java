package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface ServerConfigurationType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_CAPABILITIES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerCapabilities",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> SUPPORTED_PRIVATE_KEY_FORMATS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SupportedPrivateKeyFormats",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<UInteger> MAX_TRUST_LIST_SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxTrustListSize",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<Boolean> MULTICAST_DNS_ENABLED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MulticastDnsEnabled",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    /**
     * Get the local value of the ServerCapabilities Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ServerCapabilities Node.
     * @throws UaException if an error occurs creating or getting the ServerCapabilities Node.
     */
    String[] getServerCapabilities() throws UaException;

    /**
     * Set the local value of the ServerCapabilities Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param serverCapabilities the local value to set for the ServerCapabilities Node.
     * @throws UaException if an error occurs creating or getting the ServerCapabilities Node.
     */
    void setServerCapabilities(String[] serverCapabilities) throws UaException;

    /**
     * Read the value of the ServerCapabilities Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String[] readServerCapabilities() throws UaException;

    /**
     * Write a new value for the ServerCapabilities Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param serverCapabilities the {@link String[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeServerCapabilities(String[] serverCapabilities) throws UaException;

    /**
     * An asynchronous implementation of {@link #readServerCapabilities()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String[]> readServerCapabilitiesAsync();

    /**
     * An asynchronous implementation of {@link #writeServerCapabilities(String[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeServerCapabilitiesAsync(String[] serverCapabilities);

    /**
     * Get the ServerCapabilities {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ServerCapabilities {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getServerCapabilitiesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getServerCapabilitiesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getServerCapabilitiesNodeAsync();

    /**
     * Get the local value of the SupportedPrivateKeyFormats Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SupportedPrivateKeyFormats Node.
     * @throws UaException if an error occurs creating or getting the SupportedPrivateKeyFormats Node.
     */
    String[] getSupportedPrivateKeyFormats() throws UaException;

    /**
     * Set the local value of the SupportedPrivateKeyFormats Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param supportedPrivateKeyFormats the local value to set for the SupportedPrivateKeyFormats Node.
     * @throws UaException if an error occurs creating or getting the SupportedPrivateKeyFormats Node.
     */
    void setSupportedPrivateKeyFormats(String[] supportedPrivateKeyFormats) throws UaException;

    /**
     * Read the value of the SupportedPrivateKeyFormats Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String[] readSupportedPrivateKeyFormats() throws UaException;

    /**
     * Write a new value for the SupportedPrivateKeyFormats Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param supportedPrivateKeyFormats the {@link String[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSupportedPrivateKeyFormats(String[] supportedPrivateKeyFormats) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSupportedPrivateKeyFormats()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String[]> readSupportedPrivateKeyFormatsAsync();

    /**
     * An asynchronous implementation of {@link #writeSupportedPrivateKeyFormats(String[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeSupportedPrivateKeyFormatsAsync(String[] supportedPrivateKeyFormats);

    /**
     * Get the SupportedPrivateKeyFormats {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SupportedPrivateKeyFormats {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSupportedPrivateKeyFormatsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSupportedPrivateKeyFormatsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSupportedPrivateKeyFormatsNodeAsync();

    /**
     * Get the local value of the MaxTrustListSize Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxTrustListSize Node.
     * @throws UaException if an error occurs creating or getting the MaxTrustListSize Node.
     */
    UInteger getMaxTrustListSize() throws UaException;

    /**
     * Set the local value of the MaxTrustListSize Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxTrustListSize the local value to set for the MaxTrustListSize Node.
     * @throws UaException if an error occurs creating or getting the MaxTrustListSize Node.
     */
    void setMaxTrustListSize(UInteger maxTrustListSize) throws UaException;

    /**
     * Read the value of the MaxTrustListSize Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxTrustListSize() throws UaException;

    /**
     * Write a new value for the MaxTrustListSize Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxTrustListSize the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxTrustListSize(UInteger maxTrustListSize) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxTrustListSize()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxTrustListSizeAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxTrustListSize(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeMaxTrustListSizeAsync(UInteger maxTrustListSize);

    /**
     * Get the MaxTrustListSize {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxTrustListSize {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxTrustListSizeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxTrustListSizeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxTrustListSizeNodeAsync();

    /**
     * Get the local value of the MulticastDnsEnabled Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MulticastDnsEnabled Node.
     * @throws UaException if an error occurs creating or getting the MulticastDnsEnabled Node.
     */
    Boolean getMulticastDnsEnabled() throws UaException;

    /**
     * Set the local value of the MulticastDnsEnabled Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param multicastDnsEnabled the local value to set for the MulticastDnsEnabled Node.
     * @throws UaException if an error occurs creating or getting the MulticastDnsEnabled Node.
     */
    void setMulticastDnsEnabled(Boolean multicastDnsEnabled) throws UaException;

    /**
     * Read the value of the MulticastDnsEnabled Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readMulticastDnsEnabled() throws UaException;

    /**
     * Write a new value for the MulticastDnsEnabled Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param multicastDnsEnabled the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMulticastDnsEnabled(Boolean multicastDnsEnabled) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMulticastDnsEnabled()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readMulticastDnsEnabledAsync();

    /**
     * An asynchronous implementation of {@link #writeMulticastDnsEnabled(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeMulticastDnsEnabledAsync(Boolean multicastDnsEnabled);

    /**
     * Get the MulticastDnsEnabled {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MulticastDnsEnabled {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMulticastDnsEnabledNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMulticastDnsEnabledNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMulticastDnsEnabledNodeAsync();

    /**
     * Get the CertificateGroups {@link CertificateGroupFolderType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CertificateGroups {@link CertificateGroupFolderType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    CertificateGroupFolderType getCertificateGroupsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCertificateGroupsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * CertificateGroupFolderType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends CertificateGroupFolderType> getCertificateGroupsNodeAsync();
}

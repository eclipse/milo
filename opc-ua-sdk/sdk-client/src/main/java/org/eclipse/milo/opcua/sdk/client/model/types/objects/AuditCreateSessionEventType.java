package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditCreateSessionEventType extends AuditSessionEventType {
    QualifiedProperty<String> SECURE_CHANNEL_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecureChannelId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<ByteString> CLIENT_CERTIFICATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientCertificate",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    QualifiedProperty<String> CLIENT_CERTIFICATE_THUMBPRINT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientCertificateThumbprint",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Double> REVISED_SESSION_TIMEOUT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RevisedSessionTimeout",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    /**
     * Get the local value of the SecureChannelId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SecureChannelId Node.
     * @throws UaException if an error occurs creating or getting the SecureChannelId Node.
     */
    String getSecureChannelId() throws UaException;

    /**
     * Set the local value of the SecureChannelId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param secureChannelId the local value to set for the SecureChannelId Node.
     * @throws UaException if an error occurs creating or getting the SecureChannelId Node.
     */
    void setSecureChannelId(String secureChannelId) throws UaException;

    /**
     * Read the value of the SecureChannelId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readSecureChannelId() throws UaException;

    /**
     * Write a new value for the SecureChannelId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param secureChannelId the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSecureChannelId(String secureChannelId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSecureChannelId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readSecureChannelIdAsync();

    /**
     * An asynchronous implementation of {@link #writeSecureChannelId(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeSecureChannelIdAsync(String secureChannelId);

    /**
     * Get the SecureChannelId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SecureChannelId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSecureChannelIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSecureChannelIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSecureChannelIdNodeAsync();

    /**
     * Get the local value of the ClientCertificate Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ClientCertificate Node.
     * @throws UaException if an error occurs creating or getting the ClientCertificate Node.
     */
    ByteString getClientCertificate() throws UaException;

    /**
     * Set the local value of the ClientCertificate Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param clientCertificate the local value to set for the ClientCertificate Node.
     * @throws UaException if an error occurs creating or getting the ClientCertificate Node.
     */
    void setClientCertificate(ByteString clientCertificate) throws UaException;

    /**
     * Read the value of the ClientCertificate Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ByteString} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ByteString readClientCertificate() throws UaException;

    /**
     * Write a new value for the ClientCertificate Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param clientCertificate the {@link ByteString} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeClientCertificate(ByteString clientCertificate) throws UaException;

    /**
     * An asynchronous implementation of {@link #readClientCertificate()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ByteString> readClientCertificateAsync();

    /**
     * An asynchronous implementation of {@link #writeClientCertificate(ByteString)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeClientCertificateAsync(ByteString clientCertificate);

    /**
     * Get the ClientCertificate {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ClientCertificate {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getClientCertificateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getClientCertificateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getClientCertificateNodeAsync();

    /**
     * Get the local value of the ClientCertificateThumbprint Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ClientCertificateThumbprint Node.
     * @throws UaException if an error occurs creating or getting the ClientCertificateThumbprint Node.
     */
    String getClientCertificateThumbprint() throws UaException;

    /**
     * Set the local value of the ClientCertificateThumbprint Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param clientCertificateThumbprint the local value to set for the ClientCertificateThumbprint Node.
     * @throws UaException if an error occurs creating or getting the ClientCertificateThumbprint Node.
     */
    void setClientCertificateThumbprint(String clientCertificateThumbprint) throws UaException;

    /**
     * Read the value of the ClientCertificateThumbprint Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readClientCertificateThumbprint() throws UaException;

    /**
     * Write a new value for the ClientCertificateThumbprint Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param clientCertificateThumbprint the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeClientCertificateThumbprint(String clientCertificateThumbprint) throws UaException;

    /**
     * An asynchronous implementation of {@link #readClientCertificateThumbprint()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readClientCertificateThumbprintAsync();

    /**
     * An asynchronous implementation of {@link #writeClientCertificateThumbprint(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeClientCertificateThumbprintAsync(String clientCertificateThumbprint);

    /**
     * Get the ClientCertificateThumbprint {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ClientCertificateThumbprint {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getClientCertificateThumbprintNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getClientCertificateThumbprintNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getClientCertificateThumbprintNodeAsync();

    /**
     * Get the local value of the RevisedSessionTimeout Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the RevisedSessionTimeout Node.
     * @throws UaException if an error occurs creating or getting the RevisedSessionTimeout Node.
     */
    Double getRevisedSessionTimeout() throws UaException;

    /**
     * Set the local value of the RevisedSessionTimeout Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param revisedSessionTimeout the local value to set for the RevisedSessionTimeout Node.
     * @throws UaException if an error occurs creating or getting the RevisedSessionTimeout Node.
     */
    void setRevisedSessionTimeout(Double revisedSessionTimeout) throws UaException;

    /**
     * Read the value of the RevisedSessionTimeout Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readRevisedSessionTimeout() throws UaException;

    /**
     * Write a new value for the RevisedSessionTimeout Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param revisedSessionTimeout the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeRevisedSessionTimeout(Double revisedSessionTimeout) throws UaException;

    /**
     * An asynchronous implementation of {@link #readRevisedSessionTimeout()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readRevisedSessionTimeoutAsync();

    /**
     * An asynchronous implementation of {@link #writeRevisedSessionTimeout(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeRevisedSessionTimeoutAsync(Double revisedSessionTimeout);

    /**
     * Get the RevisedSessionTimeout {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RevisedSessionTimeout {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getRevisedSessionTimeoutNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRevisedSessionTimeoutNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getRevisedSessionTimeoutNodeAsync();
}

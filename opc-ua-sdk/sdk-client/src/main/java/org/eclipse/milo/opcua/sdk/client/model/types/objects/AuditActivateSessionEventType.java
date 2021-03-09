package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;

public interface AuditActivateSessionEventType extends AuditSessionEventType {
    QualifiedProperty<SignedSoftwareCertificate[]> CLIENT_SOFTWARE_CERTIFICATES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientSoftwareCertificates",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=344"),
        ValueRanks.OneDimension,
        SignedSoftwareCertificate[].class
    );

    QualifiedProperty<UserIdentityToken> USER_IDENTITY_TOKEN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserIdentityToken",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=316"),
        ValueRanks.Scalar,
        UserIdentityToken.class
    );

    QualifiedProperty<String> SECURE_CHANNEL_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecureChannelId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    /**
     * Get the local value of the ClientSoftwareCertificates Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ClientSoftwareCertificates Node.
     * @throws UaException if an error occurs creating or getting the ClientSoftwareCertificates Node.
     */
    SignedSoftwareCertificate[] getClientSoftwareCertificates() throws UaException;

    /**
     * Set the local value of the ClientSoftwareCertificates Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param clientSoftwareCertificates the local value to set for the ClientSoftwareCertificates Node.
     * @throws UaException if an error occurs creating or getting the ClientSoftwareCertificates Node.
     */
    void setClientSoftwareCertificates(SignedSoftwareCertificate[] clientSoftwareCertificates) throws
        UaException;

    /**
     * Read the value of the ClientSoftwareCertificates Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SignedSoftwareCertificate[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SignedSoftwareCertificate[] readClientSoftwareCertificates() throws UaException;

    /**
     * Write a new value for the ClientSoftwareCertificates Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param clientSoftwareCertificates the {@link SignedSoftwareCertificate[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeClientSoftwareCertificates(SignedSoftwareCertificate[] clientSoftwareCertificates)
        throws UaException;

    /**
     * An asynchronous implementation of {@link #readClientSoftwareCertificates()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SignedSoftwareCertificate[]> readClientSoftwareCertificatesAsync();

    /**
     * An asynchronous implementation of {@link #writeClientSoftwareCertificates(SignedSoftwareCertificate[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeClientSoftwareCertificatesAsync(
        SignedSoftwareCertificate[] clientSoftwareCertificates);

    /**
     * Get the ClientSoftwareCertificates {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ClientSoftwareCertificates {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getClientSoftwareCertificatesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getClientSoftwareCertificatesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getClientSoftwareCertificatesNodeAsync();

    /**
     * Get the local value of the UserIdentityToken Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UserIdentityToken Node.
     * @throws UaException if an error occurs creating or getting the UserIdentityToken Node.
     */
    UserIdentityToken getUserIdentityToken() throws UaException;

    /**
     * Set the local value of the UserIdentityToken Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param userIdentityToken the local value to set for the UserIdentityToken Node.
     * @throws UaException if an error occurs creating or getting the UserIdentityToken Node.
     */
    void setUserIdentityToken(UserIdentityToken userIdentityToken) throws UaException;

    /**
     * Read the value of the UserIdentityToken Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UserIdentityToken} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UserIdentityToken readUserIdentityToken() throws UaException;

    /**
     * Write a new value for the UserIdentityToken Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param userIdentityToken the {@link UserIdentityToken} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUserIdentityToken(UserIdentityToken userIdentityToken) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUserIdentityToken()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UserIdentityToken> readUserIdentityTokenAsync();

    /**
     * An asynchronous implementation of {@link #writeUserIdentityToken(UserIdentityToken)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeUserIdentityTokenAsync(UserIdentityToken userIdentityToken);

    /**
     * Get the UserIdentityToken {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UserIdentityToken {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getUserIdentityTokenNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUserIdentityTokenNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getUserIdentityTokenNodeAsync();

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
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSecureChannelIdAsync(String secureChannelId);

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
}

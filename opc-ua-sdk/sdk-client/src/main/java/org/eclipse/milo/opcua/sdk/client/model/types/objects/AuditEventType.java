package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface AuditEventType extends BaseEventType {
    QualifiedProperty<DateTime> ACTION_TIME_STAMP = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ActionTimeStamp",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<Boolean> STATUS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Status",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<String> SERVER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> CLIENT_AUDIT_ENTRY_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientAuditEntryId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> CLIENT_USER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientUserId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    /**
     * Get the local value of the ActionTimeStamp Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ActionTimeStamp Node.
     * @throws UaException if an error occurs creating or getting the ActionTimeStamp Node.
     */
    DateTime getActionTimeStamp() throws UaException;

    /**
     * Set the local value of the ActionTimeStamp Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param actionTimeStamp the local value to set for the ActionTimeStamp Node.
     * @throws UaException if an error occurs creating or getting the ActionTimeStamp Node.
     */
    void setActionTimeStamp(DateTime actionTimeStamp) throws UaException;

    /**
     * Read the value of the ActionTimeStamp Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readActionTimeStamp() throws UaException;

    /**
     * Write a new value for the ActionTimeStamp Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param actionTimeStamp the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeActionTimeStamp(DateTime actionTimeStamp) throws UaException;

    /**
     * An asynchronous implementation of {@link #readActionTimeStamp()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readActionTimeStampAsync();

    /**
     * An asynchronous implementation of {@link #writeActionTimeStamp(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeActionTimeStampAsync(DateTime actionTimeStamp);

    /**
     * Get the ActionTimeStamp {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ActionTimeStamp {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getActionTimeStampNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getActionTimeStampNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getActionTimeStampNodeAsync();

    /**
     * Get the local value of the Status Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Status Node.
     * @throws UaException if an error occurs creating or getting the Status Node.
     */
    Boolean getStatus() throws UaException;

    /**
     * Set the local value of the Status Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param status the local value to set for the Status Node.
     * @throws UaException if an error occurs creating or getting the Status Node.
     */
    void setStatus(Boolean status) throws UaException;

    /**
     * Read the value of the Status Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readStatus() throws UaException;

    /**
     * Write a new value for the Status Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param status the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeStatus(Boolean status) throws UaException;

    /**
     * An asynchronous implementation of {@link #readStatus()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readStatusAsync();

    /**
     * An asynchronous implementation of {@link #writeStatus(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeStatusAsync(Boolean status);

    /**
     * Get the Status {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Status {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getStatusNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getStatusNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getStatusNodeAsync();

    /**
     * Get the local value of the ServerId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ServerId Node.
     * @throws UaException if an error occurs creating or getting the ServerId Node.
     */
    String getServerId() throws UaException;

    /**
     * Set the local value of the ServerId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param serverId the local value to set for the ServerId Node.
     * @throws UaException if an error occurs creating or getting the ServerId Node.
     */
    void setServerId(String serverId) throws UaException;

    /**
     * Read the value of the ServerId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readServerId() throws UaException;

    /**
     * Write a new value for the ServerId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param serverId the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeServerId(String serverId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readServerId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readServerIdAsync();

    /**
     * An asynchronous implementation of {@link #writeServerId(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeServerIdAsync(String serverId);

    /**
     * Get the ServerId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ServerId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getServerIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getServerIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getServerIdNodeAsync();

    /**
     * Get the local value of the ClientAuditEntryId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ClientAuditEntryId Node.
     * @throws UaException if an error occurs creating or getting the ClientAuditEntryId Node.
     */
    String getClientAuditEntryId() throws UaException;

    /**
     * Set the local value of the ClientAuditEntryId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param clientAuditEntryId the local value to set for the ClientAuditEntryId Node.
     * @throws UaException if an error occurs creating or getting the ClientAuditEntryId Node.
     */
    void setClientAuditEntryId(String clientAuditEntryId) throws UaException;

    /**
     * Read the value of the ClientAuditEntryId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readClientAuditEntryId() throws UaException;

    /**
     * Write a new value for the ClientAuditEntryId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param clientAuditEntryId the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeClientAuditEntryId(String clientAuditEntryId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readClientAuditEntryId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readClientAuditEntryIdAsync();

    /**
     * An asynchronous implementation of {@link #writeClientAuditEntryId(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeClientAuditEntryIdAsync(String clientAuditEntryId);

    /**
     * Get the ClientAuditEntryId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ClientAuditEntryId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getClientAuditEntryIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getClientAuditEntryIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getClientAuditEntryIdNodeAsync();

    /**
     * Get the local value of the ClientUserId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ClientUserId Node.
     * @throws UaException if an error occurs creating or getting the ClientUserId Node.
     */
    String getClientUserId() throws UaException;

    /**
     * Set the local value of the ClientUserId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param clientUserId the local value to set for the ClientUserId Node.
     * @throws UaException if an error occurs creating or getting the ClientUserId Node.
     */
    void setClientUserId(String clientUserId) throws UaException;

    /**
     * Read the value of the ClientUserId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readClientUserId() throws UaException;

    /**
     * Write a new value for the ClientUserId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param clientUserId the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeClientUserId(String clientUserId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readClientUserId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readClientUserIdAsync();

    /**
     * An asynchronous implementation of {@link #writeClientUserId(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeClientUserIdAsync(String clientUserId);

    /**
     * Get the ClientUserId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ClientUserId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getClientUserIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getClientUserIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getClientUserIdNodeAsync();
}

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface AuditSessionEventType extends AuditSecurityEventType {
    QualifiedProperty<NodeId> SESSION_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SessionId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    /**
     * Get the local value of the SessionId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SessionId Node.
     * @throws UaException if an error occurs creating or getting the SessionId Node.
     */
    NodeId getSessionId() throws UaException;

    /**
     * Set the local value of the SessionId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sessionId the local value to set for the SessionId Node.
     * @throws UaException if an error occurs creating or getting the SessionId Node.
     */
    void setSessionId(NodeId sessionId) throws UaException;

    /**
     * Read the value of the SessionId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readSessionId() throws UaException;

    /**
     * Write a new value for the SessionId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sessionId the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSessionId(NodeId sessionId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSessionId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readSessionIdAsync();

    /**
     * An asynchronous implementation of {@link #writeSessionId(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSessionIdAsync(NodeId sessionId);

    /**
     * Get the SessionId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SessionId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSessionIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSessionIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSessionIdNodeAsync();
}

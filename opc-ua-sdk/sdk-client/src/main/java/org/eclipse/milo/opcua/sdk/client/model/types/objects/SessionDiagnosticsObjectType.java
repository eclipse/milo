package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public interface SessionDiagnosticsObjectType extends BaseObjectType {
    /**
     * Get the local value of the SessionDiagnostics Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SessionDiagnostics Node.
     * @throws UaException if an error occurs creating or getting the SessionDiagnostics Node.
     */
    SessionDiagnosticsDataType getSessionDiagnostics() throws UaException;

    /**
     * Set the local value of the SessionDiagnostics Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sessionDiagnostics the local value to set for the SessionDiagnostics Node.
     * @throws UaException if an error occurs creating or getting the SessionDiagnostics Node.
     */
    void setSessionDiagnostics(SessionDiagnosticsDataType sessionDiagnostics) throws UaException;

    /**
     * Read the value of the SessionDiagnostics Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SessionDiagnosticsDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SessionDiagnosticsDataType readSessionDiagnostics() throws UaException;

    /**
     * Write a new value for the SessionDiagnostics Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sessionDiagnostics the {@link SessionDiagnosticsDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSessionDiagnostics(SessionDiagnosticsDataType sessionDiagnostics) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSessionDiagnostics()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SessionDiagnosticsDataType> readSessionDiagnosticsAsync();

    /**
     * An asynchronous implementation of {@link #writeSessionDiagnostics(SessionDiagnosticsDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSessionDiagnosticsAsync(
        SessionDiagnosticsDataType sessionDiagnostics);

    /**
     * Get the SessionDiagnostics {@link SessionDiagnosticsVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SessionDiagnostics {@link SessionDiagnosticsVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    SessionDiagnosticsVariableType getSessionDiagnosticsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSessionDiagnosticsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends SessionDiagnosticsVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends SessionDiagnosticsVariableType> getSessionDiagnosticsNodeAsync();

    /**
     * Get the local value of the SessionSecurityDiagnostics Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SessionSecurityDiagnostics Node.
     * @throws UaException if an error occurs creating or getting the SessionSecurityDiagnostics Node.
     */
    SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics() throws UaException;

    /**
     * Set the local value of the SessionSecurityDiagnostics Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sessionSecurityDiagnostics the local value to set for the SessionSecurityDiagnostics Node.
     * @throws UaException if an error occurs creating or getting the SessionSecurityDiagnostics Node.
     */
    void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics)
        throws UaException;

    /**
     * Read the value of the SessionSecurityDiagnostics Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SessionSecurityDiagnosticsDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SessionSecurityDiagnosticsDataType readSessionSecurityDiagnostics() throws UaException;

    /**
     * Write a new value for the SessionSecurityDiagnostics Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sessionSecurityDiagnostics the {@link SessionSecurityDiagnosticsDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSessionSecurityDiagnostics(
        SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSessionSecurityDiagnostics()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SessionSecurityDiagnosticsDataType> readSessionSecurityDiagnosticsAsync(
    );

    /**
     * An asynchronous implementation of {@link #writeSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSessionSecurityDiagnosticsAsync(
        SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics);

    /**
     * Get the SessionSecurityDiagnostics {@link SessionSecurityDiagnosticsType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SessionSecurityDiagnostics {@link SessionSecurityDiagnosticsType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    SessionSecurityDiagnosticsType getSessionSecurityDiagnosticsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSessionSecurityDiagnosticsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends SessionSecurityDiagnosticsType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends SessionSecurityDiagnosticsType> getSessionSecurityDiagnosticsNodeAsync(
    );

    /**
     * Get the local value of the SubscriptionDiagnosticsArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SubscriptionDiagnosticsArray Node.
     * @throws UaException if an error occurs creating or getting the SubscriptionDiagnosticsArray Node.
     */
    SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray() throws UaException;

    /**
     * Set the local value of the SubscriptionDiagnosticsArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param subscriptionDiagnosticsArray the local value to set for the SubscriptionDiagnosticsArray Node.
     * @throws UaException if an error occurs creating or getting the SubscriptionDiagnosticsArray Node.
     */
    void setSubscriptionDiagnosticsArray(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray) throws UaException;

    /**
     * Read the value of the SubscriptionDiagnosticsArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SubscriptionDiagnosticsDataType[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SubscriptionDiagnosticsDataType[] readSubscriptionDiagnosticsArray() throws UaException;

    /**
     * Write a new value for the SubscriptionDiagnosticsArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param subscriptionDiagnosticsArray the {@link SubscriptionDiagnosticsDataType[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSubscriptionDiagnosticsArray(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSubscriptionDiagnosticsArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SubscriptionDiagnosticsDataType[]> readSubscriptionDiagnosticsArrayAsync(
    );

    /**
     * An asynchronous implementation of {@link #writeSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSubscriptionDiagnosticsArrayAsync(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray);

    /**
     * Get the SubscriptionDiagnosticsArray {@link SubscriptionDiagnosticsArrayType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SubscriptionDiagnosticsArray {@link SubscriptionDiagnosticsArrayType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    SubscriptionDiagnosticsArrayType getSubscriptionDiagnosticsArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSubscriptionDiagnosticsArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends SubscriptionDiagnosticsArrayType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends SubscriptionDiagnosticsArrayType> getSubscriptionDiagnosticsArrayNodeAsync(
    );
}

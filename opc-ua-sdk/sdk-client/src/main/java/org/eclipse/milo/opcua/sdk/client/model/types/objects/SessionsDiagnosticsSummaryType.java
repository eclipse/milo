package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsArrayType;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public interface SessionsDiagnosticsSummaryType extends BaseObjectType {
    /**
     * Get the local value of the SessionDiagnosticsArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SessionDiagnosticsArray Node.
     * @throws UaException if an error occurs creating or getting the SessionDiagnosticsArray Node.
     */
    SessionDiagnosticsDataType[] getSessionDiagnosticsArray() throws UaException;

    /**
     * Set the local value of the SessionDiagnosticsArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sessionDiagnosticsArray the local value to set for the SessionDiagnosticsArray Node.
     * @throws UaException if an error occurs creating or getting the SessionDiagnosticsArray Node.
     */
    void setSessionDiagnosticsArray(SessionDiagnosticsDataType[] sessionDiagnosticsArray) throws
        UaException;

    /**
     * Read the value of the SessionDiagnosticsArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SessionDiagnosticsDataType[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SessionDiagnosticsDataType[] readSessionDiagnosticsArray() throws UaException;

    /**
     * Write a new value for the SessionDiagnosticsArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sessionDiagnosticsArray the {@link SessionDiagnosticsDataType[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSessionDiagnosticsArray(SessionDiagnosticsDataType[] sessionDiagnosticsArray) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readSessionDiagnosticsArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SessionDiagnosticsDataType[]> readSessionDiagnosticsArrayAsync();

    /**
     * An asynchronous implementation of {@link #writeSessionDiagnosticsArray(SessionDiagnosticsDataType[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSessionDiagnosticsArrayAsync(
        SessionDiagnosticsDataType[] sessionDiagnosticsArray);

    /**
     * Get the SessionDiagnosticsArray {@link SessionDiagnosticsArrayType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SessionDiagnosticsArray {@link SessionDiagnosticsArrayType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    SessionDiagnosticsArrayType getSessionDiagnosticsArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSessionDiagnosticsArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends SessionDiagnosticsArrayType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends SessionDiagnosticsArrayType> getSessionDiagnosticsArrayNodeAsync();

    /**
     * Get the local value of the SessionSecurityDiagnosticsArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SessionSecurityDiagnosticsArray Node.
     * @throws UaException if an error occurs creating or getting the SessionSecurityDiagnosticsArray Node.
     */
    SessionSecurityDiagnosticsDataType[] getSessionSecurityDiagnosticsArray() throws UaException;

    /**
     * Set the local value of the SessionSecurityDiagnosticsArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sessionSecurityDiagnosticsArray the local value to set for the SessionSecurityDiagnosticsArray Node.
     * @throws UaException if an error occurs creating or getting the SessionSecurityDiagnosticsArray Node.
     */
    void setSessionSecurityDiagnosticsArray(
        SessionSecurityDiagnosticsDataType[] sessionSecurityDiagnosticsArray) throws UaException;

    /**
     * Read the value of the SessionSecurityDiagnosticsArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SessionSecurityDiagnosticsDataType[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SessionSecurityDiagnosticsDataType[] readSessionSecurityDiagnosticsArray() throws UaException;

    /**
     * Write a new value for the SessionSecurityDiagnosticsArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sessionSecurityDiagnosticsArray the {@link SessionSecurityDiagnosticsDataType[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSessionSecurityDiagnosticsArray(
        SessionSecurityDiagnosticsDataType[] sessionSecurityDiagnosticsArray) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSessionSecurityDiagnosticsArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SessionSecurityDiagnosticsDataType[]> readSessionSecurityDiagnosticsArrayAsync(
    );

    /**
     * An asynchronous implementation of {@link #writeSessionSecurityDiagnosticsArray(SessionSecurityDiagnosticsDataType[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSessionSecurityDiagnosticsArrayAsync(
        SessionSecurityDiagnosticsDataType[] sessionSecurityDiagnosticsArray);

    /**
     * Get the SessionSecurityDiagnosticsArray {@link SessionSecurityDiagnosticsArrayType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SessionSecurityDiagnosticsArray {@link SessionSecurityDiagnosticsArrayType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    SessionSecurityDiagnosticsArrayType getSessionSecurityDiagnosticsArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSessionSecurityDiagnosticsArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends SessionSecurityDiagnosticsArrayType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends SessionSecurityDiagnosticsArrayType> getSessionSecurityDiagnosticsArrayNodeAsync(
    );
}

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public interface SessionSecurityDiagnosticsArrayType extends BaseDataVariableType {
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
}

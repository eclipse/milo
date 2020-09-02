package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;

public interface SamplingIntervalDiagnosticsArrayType extends BaseDataVariableType {
    /**
     * Get the local value of the SamplingIntervalDiagnostics Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SamplingIntervalDiagnostics Node.
     * @throws UaException if an error occurs creating or getting the SamplingIntervalDiagnostics Node.
     */
    SamplingIntervalDiagnosticsDataType getSamplingIntervalDiagnostics() throws UaException;

    /**
     * Set the local value of the SamplingIntervalDiagnostics Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param samplingIntervalDiagnostics the local value to set for the SamplingIntervalDiagnostics Node.
     * @throws UaException if an error occurs creating or getting the SamplingIntervalDiagnostics Node.
     */
    void setSamplingIntervalDiagnostics(
        SamplingIntervalDiagnosticsDataType samplingIntervalDiagnostics) throws UaException;

    /**
     * Read the value of the SamplingIntervalDiagnostics Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SamplingIntervalDiagnosticsDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SamplingIntervalDiagnosticsDataType readSamplingIntervalDiagnostics() throws UaException;

    /**
     * Write a new value for the SamplingIntervalDiagnostics Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param samplingIntervalDiagnostics the {@link SamplingIntervalDiagnosticsDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSamplingIntervalDiagnostics(
        SamplingIntervalDiagnosticsDataType samplingIntervalDiagnostics) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSamplingIntervalDiagnostics()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SamplingIntervalDiagnosticsDataType> readSamplingIntervalDiagnosticsAsync(
    );

    /**
     * An asynchronous implementation of {@link #writeSamplingIntervalDiagnostics(SamplingIntervalDiagnosticsDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSamplingIntervalDiagnosticsAsync(
        SamplingIntervalDiagnosticsDataType samplingIntervalDiagnostics);

    /**
     * Get the SamplingIntervalDiagnostics {@link SamplingIntervalDiagnosticsType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SamplingIntervalDiagnostics {@link SamplingIntervalDiagnosticsType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    SamplingIntervalDiagnosticsType getSamplingIntervalDiagnosticsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSamplingIntervalDiagnosticsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends SamplingIntervalDiagnosticsType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends SamplingIntervalDiagnosticsType> getSamplingIntervalDiagnosticsNodeAsync(
    );
}

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SamplingIntervalDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public interface ServerDiagnosticsType extends BaseObjectType {
    QualifiedProperty<Boolean> ENABLED_FLAG = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EnabledFlag",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    /**
     * Get the local value of the EnabledFlag Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the EnabledFlag Node.
     * @throws UaException if an error occurs creating or getting the EnabledFlag Node.
     */
    Boolean getEnabledFlag() throws UaException;

    /**
     * Set the local value of the EnabledFlag Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param enabledFlag the local value to set for the EnabledFlag Node.
     * @throws UaException if an error occurs creating or getting the EnabledFlag Node.
     */
    void setEnabledFlag(Boolean enabledFlag) throws UaException;

    /**
     * Read the value of the EnabledFlag Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readEnabledFlag() throws UaException;

    /**
     * Write a new value for the EnabledFlag Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param enabledFlag the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeEnabledFlag(Boolean enabledFlag) throws UaException;

    /**
     * An asynchronous implementation of {@link #readEnabledFlag()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readEnabledFlagAsync();

    /**
     * An asynchronous implementation of {@link #writeEnabledFlag(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeEnabledFlagAsync(Boolean enabledFlag);

    /**
     * Get the EnabledFlag {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the EnabledFlag {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getEnabledFlagNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getEnabledFlagNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getEnabledFlagNodeAsync();

    /**
     * Get the local value of the ServerDiagnosticsSummary Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ServerDiagnosticsSummary Node.
     * @throws UaException if an error occurs creating or getting the ServerDiagnosticsSummary Node.
     */
    ServerDiagnosticsSummaryDataType getServerDiagnosticsSummary() throws UaException;

    /**
     * Set the local value of the ServerDiagnosticsSummary Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param serverDiagnosticsSummary the local value to set for the ServerDiagnosticsSummary Node.
     * @throws UaException if an error occurs creating or getting the ServerDiagnosticsSummary Node.
     */
    void setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType serverDiagnosticsSummary) throws
        UaException;

    /**
     * Read the value of the ServerDiagnosticsSummary Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServerDiagnosticsSummaryDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServerDiagnosticsSummaryDataType readServerDiagnosticsSummary() throws UaException;

    /**
     * Write a new value for the ServerDiagnosticsSummary Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param serverDiagnosticsSummary the {@link ServerDiagnosticsSummaryDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType serverDiagnosticsSummary)
        throws UaException;

    /**
     * An asynchronous implementation of {@link #readServerDiagnosticsSummary()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServerDiagnosticsSummaryDataType> readServerDiagnosticsSummaryAsync();

    /**
     * An asynchronous implementation of {@link #writeServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeServerDiagnosticsSummaryAsync(
        ServerDiagnosticsSummaryDataType serverDiagnosticsSummary);

    /**
     * Get the ServerDiagnosticsSummary {@link ServerDiagnosticsSummaryType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ServerDiagnosticsSummary {@link ServerDiagnosticsSummaryType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    ServerDiagnosticsSummaryType getServerDiagnosticsSummaryNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getServerDiagnosticsSummaryNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends ServerDiagnosticsSummaryType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends ServerDiagnosticsSummaryType> getServerDiagnosticsSummaryNodeAsync();

    /**
     * Get the local value of the SamplingIntervalDiagnosticsArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SamplingIntervalDiagnosticsArray Node.
     * @throws UaException if an error occurs creating or getting the SamplingIntervalDiagnosticsArray Node.
     */
    SamplingIntervalDiagnosticsDataType[] getSamplingIntervalDiagnosticsArray() throws UaException;

    /**
     * Set the local value of the SamplingIntervalDiagnosticsArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param samplingIntervalDiagnosticsArray the local value to set for the SamplingIntervalDiagnosticsArray Node.
     * @throws UaException if an error occurs creating or getting the SamplingIntervalDiagnosticsArray Node.
     */
    void setSamplingIntervalDiagnosticsArray(
        SamplingIntervalDiagnosticsDataType[] samplingIntervalDiagnosticsArray) throws UaException;

    /**
     * Read the value of the SamplingIntervalDiagnosticsArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SamplingIntervalDiagnosticsDataType[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SamplingIntervalDiagnosticsDataType[] readSamplingIntervalDiagnosticsArray() throws UaException;

    /**
     * Write a new value for the SamplingIntervalDiagnosticsArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param samplingIntervalDiagnosticsArray the {@link SamplingIntervalDiagnosticsDataType[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSamplingIntervalDiagnosticsArray(
        SamplingIntervalDiagnosticsDataType[] samplingIntervalDiagnosticsArray) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSamplingIntervalDiagnosticsArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SamplingIntervalDiagnosticsDataType[]> readSamplingIntervalDiagnosticsArrayAsync(
    );

    /**
     * An asynchronous implementation of {@link #writeSamplingIntervalDiagnosticsArray(SamplingIntervalDiagnosticsDataType[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSamplingIntervalDiagnosticsArrayAsync(
        SamplingIntervalDiagnosticsDataType[] samplingIntervalDiagnosticsArray);

    /**
     * Get the SamplingIntervalDiagnosticsArray {@link SamplingIntervalDiagnosticsArrayType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SamplingIntervalDiagnosticsArray {@link SamplingIntervalDiagnosticsArrayType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    SamplingIntervalDiagnosticsArrayType getSamplingIntervalDiagnosticsArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSamplingIntervalDiagnosticsArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends SamplingIntervalDiagnosticsArrayType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends SamplingIntervalDiagnosticsArrayType> getSamplingIntervalDiagnosticsArrayNodeAsync(
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

    /**
     * Get the SessionsDiagnosticsSummary {@link SessionsDiagnosticsSummaryType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SessionsDiagnosticsSummary {@link SessionsDiagnosticsSummaryType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    SessionsDiagnosticsSummaryType getSessionsDiagnosticsSummaryNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSessionsDiagnosticsSummaryNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * SessionsDiagnosticsSummaryType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends SessionsDiagnosticsSummaryType> getSessionsDiagnosticsSummaryNodeAsync(
    );
}

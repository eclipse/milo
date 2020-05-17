package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AcknowledgeableConditionType extends ConditionType {
    /**
     * Get the local value of the EnabledState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the EnabledState Node.
     * @throws UaException if an error occurs creating or getting the EnabledState Node.
     */
    LocalizedText getEnabledState() throws UaException;

    /**
     * Set the local value of the EnabledState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param enabledState the local value to set for the EnabledState Node.
     * @throws UaException if an error occurs creating or getting the EnabledState Node.
     */
    void setEnabledState(LocalizedText enabledState) throws UaException;

    /**
     * Read the value of the EnabledState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readEnabledState() throws UaException;

    /**
     * Write a new value for the EnabledState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param enabledState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeEnabledState(LocalizedText enabledState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readEnabledState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readEnabledStateAsync();

    /**
     * An asynchronous implementation of {@link #writeEnabledState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeEnabledStateAsync(LocalizedText enabledState);

    /**
     * Get the EnabledState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the EnabledState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getEnabledStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getEnabledStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getEnabledStateNodeAsync();

    /**
     * Get the local value of the AckedState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the AckedState Node.
     * @throws UaException if an error occurs creating or getting the AckedState Node.
     */
    LocalizedText getAckedState() throws UaException;

    /**
     * Set the local value of the AckedState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param ackedState the local value to set for the AckedState Node.
     * @throws UaException if an error occurs creating or getting the AckedState Node.
     */
    void setAckedState(LocalizedText ackedState) throws UaException;

    /**
     * Read the value of the AckedState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readAckedState() throws UaException;

    /**
     * Write a new value for the AckedState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param ackedState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeAckedState(LocalizedText ackedState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readAckedState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readAckedStateAsync();

    /**
     * An asynchronous implementation of {@link #writeAckedState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeAckedStateAsync(LocalizedText ackedState);

    /**
     * Get the AckedState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AckedState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getAckedStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAckedStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getAckedStateNodeAsync();

    /**
     * Get the local value of the ConfirmedState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ConfirmedState Node.
     * @throws UaException if an error occurs creating or getting the ConfirmedState Node.
     */
    LocalizedText getConfirmedState() throws UaException;

    /**
     * Set the local value of the ConfirmedState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param confirmedState the local value to set for the ConfirmedState Node.
     * @throws UaException if an error occurs creating or getting the ConfirmedState Node.
     */
    void setConfirmedState(LocalizedText confirmedState) throws UaException;

    /**
     * Read the value of the ConfirmedState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readConfirmedState() throws UaException;

    /**
     * Write a new value for the ConfirmedState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param confirmedState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeConfirmedState(LocalizedText confirmedState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readConfirmedState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readConfirmedStateAsync();

    /**
     * An asynchronous implementation of {@link #writeConfirmedState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeConfirmedStateAsync(LocalizedText confirmedState);

    /**
     * Get the ConfirmedState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ConfirmedState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getConfirmedStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getConfirmedStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getConfirmedStateNodeAsync();
}

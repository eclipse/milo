package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface NonExclusiveLimitAlarmType extends LimitAlarmType {
    /**
     * Get the local value of the ActiveState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ActiveState Node.
     * @throws UaException if an error occurs creating or getting the ActiveState Node.
     */
    LocalizedText getActiveState() throws UaException;

    /**
     * Set the local value of the ActiveState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param activeState the local value to set for the ActiveState Node.
     * @throws UaException if an error occurs creating or getting the ActiveState Node.
     */
    void setActiveState(LocalizedText activeState) throws UaException;

    /**
     * Read the value of the ActiveState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readActiveState() throws UaException;

    /**
     * Write a new value for the ActiveState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param activeState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeActiveState(LocalizedText activeState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readActiveState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readActiveStateAsync();

    /**
     * An asynchronous implementation of {@link #writeActiveState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeActiveStateAsync(LocalizedText activeState);

    /**
     * Get the ActiveState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ActiveState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getActiveStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getActiveStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getActiveStateNodeAsync();

    /**
     * Get the local value of the HighHighState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the HighHighState Node.
     * @throws UaException if an error occurs creating or getting the HighHighState Node.
     */
    LocalizedText getHighHighState() throws UaException;

    /**
     * Set the local value of the HighHighState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param highHighState the local value to set for the HighHighState Node.
     * @throws UaException if an error occurs creating or getting the HighHighState Node.
     */
    void setHighHighState(LocalizedText highHighState) throws UaException;

    /**
     * Read the value of the HighHighState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readHighHighState() throws UaException;

    /**
     * Write a new value for the HighHighState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param highHighState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeHighHighState(LocalizedText highHighState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readHighHighState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readHighHighStateAsync();

    /**
     * An asynchronous implementation of {@link #writeHighHighState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeHighHighStateAsync(LocalizedText highHighState);

    /**
     * Get the HighHighState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the HighHighState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getHighHighStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getHighHighStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getHighHighStateNodeAsync();

    /**
     * Get the local value of the HighState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the HighState Node.
     * @throws UaException if an error occurs creating or getting the HighState Node.
     */
    LocalizedText getHighState() throws UaException;

    /**
     * Set the local value of the HighState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param highState the local value to set for the HighState Node.
     * @throws UaException if an error occurs creating or getting the HighState Node.
     */
    void setHighState(LocalizedText highState) throws UaException;

    /**
     * Read the value of the HighState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readHighState() throws UaException;

    /**
     * Write a new value for the HighState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param highState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeHighState(LocalizedText highState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readHighState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readHighStateAsync();

    /**
     * An asynchronous implementation of {@link #writeHighState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeHighStateAsync(LocalizedText highState);

    /**
     * Get the HighState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the HighState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getHighStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getHighStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getHighStateNodeAsync();

    /**
     * Get the local value of the LowState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LowState Node.
     * @throws UaException if an error occurs creating or getting the LowState Node.
     */
    LocalizedText getLowState() throws UaException;

    /**
     * Set the local value of the LowState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lowState the local value to set for the LowState Node.
     * @throws UaException if an error occurs creating or getting the LowState Node.
     */
    void setLowState(LocalizedText lowState) throws UaException;

    /**
     * Read the value of the LowState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readLowState() throws UaException;

    /**
     * Write a new value for the LowState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lowState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLowState(LocalizedText lowState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLowState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readLowStateAsync();

    /**
     * An asynchronous implementation of {@link #writeLowState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeLowStateAsync(LocalizedText lowState);

    /**
     * Get the LowState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LowState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getLowStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLowStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getLowStateNodeAsync();

    /**
     * Get the local value of the LowLowState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LowLowState Node.
     * @throws UaException if an error occurs creating or getting the LowLowState Node.
     */
    LocalizedText getLowLowState() throws UaException;

    /**
     * Set the local value of the LowLowState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lowLowState the local value to set for the LowLowState Node.
     * @throws UaException if an error occurs creating or getting the LowLowState Node.
     */
    void setLowLowState(LocalizedText lowLowState) throws UaException;

    /**
     * Read the value of the LowLowState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readLowLowState() throws UaException;

    /**
     * Write a new value for the LowLowState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lowLowState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLowLowState(LocalizedText lowLowState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLowLowState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readLowLowStateAsync();

    /**
     * An asynchronous implementation of {@link #writeLowLowState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeLowLowStateAsync(LocalizedText lowLowState);

    /**
     * Get the LowLowState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LowLowState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getLowLowStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLowLowStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getLowLowStateNodeAsync();
}

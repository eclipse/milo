package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface FiniteStateMachineType extends StateMachineType {
    /**
     * Get the local value of the CurrentState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CurrentState Node.
     * @throws UaException if an error occurs creating or getting the CurrentState Node.
     */
    LocalizedText getCurrentState() throws UaException;

    /**
     * Set the local value of the CurrentState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param currentState the local value to set for the CurrentState Node.
     * @throws UaException if an error occurs creating or getting the CurrentState Node.
     */
    void setCurrentState(LocalizedText currentState) throws UaException;

    /**
     * Read the value of the CurrentState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readCurrentState() throws UaException;

    /**
     * Write a new value for the CurrentState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param currentState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCurrentState(LocalizedText currentState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCurrentState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readCurrentStateAsync();

    /**
     * An asynchronous implementation of {@link #writeCurrentState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCurrentStateAsync(LocalizedText currentState);

    /**
     * Get the CurrentState {@link FiniteStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CurrentState {@link FiniteStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    FiniteStateVariableType getCurrentStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCurrentStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends FiniteStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends FiniteStateVariableType> getCurrentStateNodeAsync();

    /**
     * Get the local value of the LastTransition Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastTransition Node.
     * @throws UaException if an error occurs creating or getting the LastTransition Node.
     */
    LocalizedText getLastTransition() throws UaException;

    /**
     * Set the local value of the LastTransition Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastTransition the local value to set for the LastTransition Node.
     * @throws UaException if an error occurs creating or getting the LastTransition Node.
     */
    void setLastTransition(LocalizedText lastTransition) throws UaException;

    /**
     * Read the value of the LastTransition Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readLastTransition() throws UaException;

    /**
     * Write a new value for the LastTransition Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastTransition the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastTransition(LocalizedText lastTransition) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastTransition()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readLastTransitionAsync();

    /**
     * An asynchronous implementation of {@link #writeLastTransition(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLastTransitionAsync(LocalizedText lastTransition);

    /**
     * Get the LastTransition {@link FiniteTransitionVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastTransition {@link FiniteTransitionVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    FiniteTransitionVariableType getLastTransitionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastTransitionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends FiniteTransitionVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends FiniteTransitionVariableType> getLastTransitionNodeAsync();
}

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface ProgramTransitionAuditEventType extends AuditUpdateStateEventType {
    /**
     * Get the local value of the Transition Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Transition Node.
     * @throws UaException if an error occurs creating or getting the Transition Node.
     */
    LocalizedText getTransition() throws UaException;

    /**
     * Set the local value of the Transition Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param transition the local value to set for the Transition Node.
     * @throws UaException if an error occurs creating or getting the Transition Node.
     */
    void setTransition(LocalizedText transition) throws UaException;

    /**
     * Read the value of the Transition Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readTransition() throws UaException;

    /**
     * Write a new value for the Transition Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param transition the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeTransition(LocalizedText transition) throws UaException;

    /**
     * An asynchronous implementation of {@link #readTransition()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readTransitionAsync();

    /**
     * An asynchronous implementation of {@link #writeTransition(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeTransitionAsync(LocalizedText transition);

    /**
     * Get the Transition {@link FiniteTransitionVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Transition {@link FiniteTransitionVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    FiniteTransitionVariableType getTransitionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTransitionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends FiniteTransitionVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends FiniteTransitionVariableType> getTransitionNodeAsync();
}

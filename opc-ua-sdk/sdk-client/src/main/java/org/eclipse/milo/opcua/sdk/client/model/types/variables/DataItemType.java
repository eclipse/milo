package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface DataItemType extends BaseDataVariableType {
    QualifiedProperty<String> DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Definition",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Double> VALUE_PRECISION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ValuePrecision",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    /**
     * Get the local value of the Definition Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Definition Node.
     * @throws UaException if an error occurs creating or getting the Definition Node.
     */
    String getDefinition() throws UaException;

    /**
     * Set the local value of the Definition Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param definition the local value to set for the Definition Node.
     * @throws UaException if an error occurs creating or getting the Definition Node.
     */
    void setDefinition(String definition) throws UaException;

    /**
     * Read the value of the Definition Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readDefinition() throws UaException;

    /**
     * Write a new value for the Definition Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param definition the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDefinition(String definition) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDefinition()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readDefinitionAsync();

    /**
     * An asynchronous implementation of {@link #writeDefinition(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDefinitionAsync(String definition);

    /**
     * Get the Definition {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Definition {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getDefinitionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDefinitionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getDefinitionNodeAsync();

    /**
     * Get the local value of the ValuePrecision Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ValuePrecision Node.
     * @throws UaException if an error occurs creating or getting the ValuePrecision Node.
     */
    Double getValuePrecision() throws UaException;

    /**
     * Set the local value of the ValuePrecision Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param valuePrecision the local value to set for the ValuePrecision Node.
     * @throws UaException if an error occurs creating or getting the ValuePrecision Node.
     */
    void setValuePrecision(Double valuePrecision) throws UaException;

    /**
     * Read the value of the ValuePrecision Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readValuePrecision() throws UaException;

    /**
     * Write a new value for the ValuePrecision Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param valuePrecision the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeValuePrecision(Double valuePrecision) throws UaException;

    /**
     * An asynchronous implementation of {@link #readValuePrecision()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readValuePrecisionAsync();

    /**
     * An asynchronous implementation of {@link #writeValuePrecision(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeValuePrecisionAsync(Double valuePrecision);

    /**
     * Get the ValuePrecision {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ValuePrecision {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getValuePrecisionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getValuePrecisionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getValuePrecisionNodeAsync();
}

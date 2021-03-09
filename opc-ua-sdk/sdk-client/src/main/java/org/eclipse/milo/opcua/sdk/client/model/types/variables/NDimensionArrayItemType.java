package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public interface NDimensionArrayItemType extends ArrayItemType {
    QualifiedProperty<AxisInformation[]> AXIS_DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AxisDefinition",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12079"),
        ValueRanks.OneDimension,
        AxisInformation[].class
    );

    /**
     * Get the local value of the AxisDefinition Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the AxisDefinition Node.
     * @throws UaException if an error occurs creating or getting the AxisDefinition Node.
     */
    AxisInformation[] getAxisDefinition() throws UaException;

    /**
     * Set the local value of the AxisDefinition Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param axisDefinition the local value to set for the AxisDefinition Node.
     * @throws UaException if an error occurs creating or getting the AxisDefinition Node.
     */
    void setAxisDefinition(AxisInformation[] axisDefinition) throws UaException;

    /**
     * Read the value of the AxisDefinition Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link AxisInformation[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    AxisInformation[] readAxisDefinition() throws UaException;

    /**
     * Write a new value for the AxisDefinition Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param axisDefinition the {@link AxisInformation[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeAxisDefinition(AxisInformation[] axisDefinition) throws UaException;

    /**
     * An asynchronous implementation of {@link #readAxisDefinition()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends AxisInformation[]> readAxisDefinitionAsync();

    /**
     * An asynchronous implementation of {@link #writeAxisDefinition(AxisInformation[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeAxisDefinitionAsync(AxisInformation[] axisDefinition);

    /**
     * Get the AxisDefinition {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AxisDefinition {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getAxisDefinitionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAxisDefinitionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getAxisDefinitionNodeAsync();
}

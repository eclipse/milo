package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AlarmConditionType extends AcknowledgeableConditionType {
    QualifiedProperty<NodeId> INPUT_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InputNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<Boolean> SUPPRESSED_OR_SHELVED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SuppressedOrShelved",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Double> MAX_TIME_SHELVED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxTimeShelved",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    /**
     * Get the local value of the InputNode Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the InputNode Node.
     * @throws UaException if an error occurs creating or getting the InputNode Node.
     */
    NodeId getInputNode() throws UaException;

    /**
     * Set the local value of the InputNode Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param inputNode the local value to set for the InputNode Node.
     * @throws UaException if an error occurs creating or getting the InputNode Node.
     */
    void setInputNode(NodeId inputNode) throws UaException;

    /**
     * Read the value of the InputNode Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readInputNode() throws UaException;

    /**
     * Write a new value for the InputNode Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param inputNode the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeInputNode(NodeId inputNode) throws UaException;

    /**
     * An asynchronous implementation of {@link #readInputNode()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readInputNodeAsync();

    /**
     * An asynchronous implementation of {@link #writeInputNode(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeInputNodeAsync(NodeId inputNode);

    /**
     * Get the InputNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the InputNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getInputNodeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getInputNodeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getInputNodeNodeAsync();

    /**
     * Get the local value of the SuppressedOrShelved Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SuppressedOrShelved Node.
     * @throws UaException if an error occurs creating or getting the SuppressedOrShelved Node.
     */
    Boolean getSuppressedOrShelved() throws UaException;

    /**
     * Set the local value of the SuppressedOrShelved Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param suppressedOrShelved the local value to set for the SuppressedOrShelved Node.
     * @throws UaException if an error occurs creating or getting the SuppressedOrShelved Node.
     */
    void setSuppressedOrShelved(Boolean suppressedOrShelved) throws UaException;

    /**
     * Read the value of the SuppressedOrShelved Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readSuppressedOrShelved() throws UaException;

    /**
     * Write a new value for the SuppressedOrShelved Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param suppressedOrShelved the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSuppressedOrShelved(Boolean suppressedOrShelved) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSuppressedOrShelved()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readSuppressedOrShelvedAsync();

    /**
     * An asynchronous implementation of {@link #writeSuppressedOrShelved(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeSuppressedOrShelvedAsync(Boolean suppressedOrShelved);

    /**
     * Get the SuppressedOrShelved {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SuppressedOrShelved {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSuppressedOrShelvedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSuppressedOrShelvedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSuppressedOrShelvedNodeAsync();

    /**
     * Get the local value of the MaxTimeShelved Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxTimeShelved Node.
     * @throws UaException if an error occurs creating or getting the MaxTimeShelved Node.
     */
    Double getMaxTimeShelved() throws UaException;

    /**
     * Set the local value of the MaxTimeShelved Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxTimeShelved the local value to set for the MaxTimeShelved Node.
     * @throws UaException if an error occurs creating or getting the MaxTimeShelved Node.
     */
    void setMaxTimeShelved(Double maxTimeShelved) throws UaException;

    /**
     * Read the value of the MaxTimeShelved Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readMaxTimeShelved() throws UaException;

    /**
     * Write a new value for the MaxTimeShelved Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxTimeShelved the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxTimeShelved(Double maxTimeShelved) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxTimeShelved()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readMaxTimeShelvedAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxTimeShelved(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeMaxTimeShelvedAsync(Double maxTimeShelved);

    /**
     * Get the MaxTimeShelved {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxTimeShelved {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxTimeShelvedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxTimeShelvedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxTimeShelvedNodeAsync();

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
     * Get the local value of the SuppressedState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SuppressedState Node.
     * @throws UaException if an error occurs creating or getting the SuppressedState Node.
     */
    LocalizedText getSuppressedState() throws UaException;

    /**
     * Set the local value of the SuppressedState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param suppressedState the local value to set for the SuppressedState Node.
     * @throws UaException if an error occurs creating or getting the SuppressedState Node.
     */
    void setSuppressedState(LocalizedText suppressedState) throws UaException;

    /**
     * Read the value of the SuppressedState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readSuppressedState() throws UaException;

    /**
     * Write a new value for the SuppressedState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param suppressedState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSuppressedState(LocalizedText suppressedState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSuppressedState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readSuppressedStateAsync();

    /**
     * An asynchronous implementation of {@link #writeSuppressedState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeSuppressedStateAsync(LocalizedText suppressedState);

    /**
     * Get the SuppressedState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SuppressedState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getSuppressedStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSuppressedStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getSuppressedStateNodeAsync();

    /**
     * Get the ShelvingState {@link ShelvedStateMachineType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ShelvingState {@link ShelvedStateMachineType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    ShelvedStateMachineType getShelvingStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getShelvingStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ShelvedStateMachineType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends ShelvedStateMachineType> getShelvingStateNodeAsync();
}

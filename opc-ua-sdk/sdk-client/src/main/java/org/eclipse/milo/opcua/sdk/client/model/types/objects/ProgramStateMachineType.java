package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;

public interface ProgramStateMachineType extends FiniteStateMachineType {
    QualifiedProperty<Boolean> CREATABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Creatable",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> DELETABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Deletable",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> AUTO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AutoDelete",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Integer> RECYCLE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RecycleCount",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    QualifiedProperty<UInteger> INSTANCE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InstanceCount",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_INSTANCE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxInstanceCount",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_RECYCLE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxRecycleCount",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    /**
     * Get the local value of the Creatable Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Creatable Node.
     * @throws UaException if an error occurs creating or getting the Creatable Node.
     */
    Boolean getCreatable() throws UaException;

    /**
     * Set the local value of the Creatable Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param creatable the local value to set for the Creatable Node.
     * @throws UaException if an error occurs creating or getting the Creatable Node.
     */
    void setCreatable(Boolean creatable) throws UaException;

    /**
     * Read the value of the Creatable Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readCreatable() throws UaException;

    /**
     * Write a new value for the Creatable Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param creatable the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCreatable(Boolean creatable) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCreatable()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readCreatableAsync();

    /**
     * An asynchronous implementation of {@link #writeCreatable(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCreatableAsync(Boolean creatable);

    /**
     * Get the Creatable {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Creatable {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getCreatableNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCreatableNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getCreatableNodeAsync();

    /**
     * Get the local value of the Deletable Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Deletable Node.
     * @throws UaException if an error occurs creating or getting the Deletable Node.
     */
    Boolean getDeletable() throws UaException;

    /**
     * Set the local value of the Deletable Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param deletable the local value to set for the Deletable Node.
     * @throws UaException if an error occurs creating or getting the Deletable Node.
     */
    void setDeletable(Boolean deletable) throws UaException;

    /**
     * Read the value of the Deletable Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readDeletable() throws UaException;

    /**
     * Write a new value for the Deletable Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param deletable the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDeletable(Boolean deletable) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDeletable()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readDeletableAsync();

    /**
     * An asynchronous implementation of {@link #writeDeletable(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDeletableAsync(Boolean deletable);

    /**
     * Get the Deletable {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Deletable {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getDeletableNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDeletableNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getDeletableNodeAsync();

    /**
     * Get the local value of the AutoDelete Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the AutoDelete Node.
     * @throws UaException if an error occurs creating or getting the AutoDelete Node.
     */
    Boolean getAutoDelete() throws UaException;

    /**
     * Set the local value of the AutoDelete Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param autoDelete the local value to set for the AutoDelete Node.
     * @throws UaException if an error occurs creating or getting the AutoDelete Node.
     */
    void setAutoDelete(Boolean autoDelete) throws UaException;

    /**
     * Read the value of the AutoDelete Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readAutoDelete() throws UaException;

    /**
     * Write a new value for the AutoDelete Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param autoDelete the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeAutoDelete(Boolean autoDelete) throws UaException;

    /**
     * An asynchronous implementation of {@link #readAutoDelete()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readAutoDeleteAsync();

    /**
     * An asynchronous implementation of {@link #writeAutoDelete(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeAutoDeleteAsync(Boolean autoDelete);

    /**
     * Get the AutoDelete {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AutoDelete {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getAutoDeleteNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAutoDeleteNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getAutoDeleteNodeAsync();

    /**
     * Get the local value of the RecycleCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the RecycleCount Node.
     * @throws UaException if an error occurs creating or getting the RecycleCount Node.
     */
    Integer getRecycleCount() throws UaException;

    /**
     * Set the local value of the RecycleCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param recycleCount the local value to set for the RecycleCount Node.
     * @throws UaException if an error occurs creating or getting the RecycleCount Node.
     */
    void setRecycleCount(Integer recycleCount) throws UaException;

    /**
     * Read the value of the RecycleCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Integer} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Integer readRecycleCount() throws UaException;

    /**
     * Write a new value for the RecycleCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param recycleCount the {@link Integer} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeRecycleCount(Integer recycleCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readRecycleCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Integer> readRecycleCountAsync();

    /**
     * An asynchronous implementation of {@link #writeRecycleCount(Integer)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeRecycleCountAsync(Integer recycleCount);

    /**
     * Get the RecycleCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RecycleCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getRecycleCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRecycleCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getRecycleCountNodeAsync();

    /**
     * Get the local value of the InstanceCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the InstanceCount Node.
     * @throws UaException if an error occurs creating or getting the InstanceCount Node.
     */
    UInteger getInstanceCount() throws UaException;

    /**
     * Set the local value of the InstanceCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param instanceCount the local value to set for the InstanceCount Node.
     * @throws UaException if an error occurs creating or getting the InstanceCount Node.
     */
    void setInstanceCount(UInteger instanceCount) throws UaException;

    /**
     * Read the value of the InstanceCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readInstanceCount() throws UaException;

    /**
     * Write a new value for the InstanceCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param instanceCount the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeInstanceCount(UInteger instanceCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readInstanceCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readInstanceCountAsync();

    /**
     * An asynchronous implementation of {@link #writeInstanceCount(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeInstanceCountAsync(UInteger instanceCount);

    /**
     * Get the InstanceCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the InstanceCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getInstanceCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getInstanceCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getInstanceCountNodeAsync();

    /**
     * Get the local value of the MaxInstanceCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxInstanceCount Node.
     * @throws UaException if an error occurs creating or getting the MaxInstanceCount Node.
     */
    UInteger getMaxInstanceCount() throws UaException;

    /**
     * Set the local value of the MaxInstanceCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxInstanceCount the local value to set for the MaxInstanceCount Node.
     * @throws UaException if an error occurs creating or getting the MaxInstanceCount Node.
     */
    void setMaxInstanceCount(UInteger maxInstanceCount) throws UaException;

    /**
     * Read the value of the MaxInstanceCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxInstanceCount() throws UaException;

    /**
     * Write a new value for the MaxInstanceCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxInstanceCount the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxInstanceCount(UInteger maxInstanceCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxInstanceCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxInstanceCountAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxInstanceCount(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxInstanceCountAsync(UInteger maxInstanceCount);

    /**
     * Get the MaxInstanceCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxInstanceCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxInstanceCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxInstanceCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxInstanceCountNodeAsync();

    /**
     * Get the local value of the MaxRecycleCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxRecycleCount Node.
     * @throws UaException if an error occurs creating or getting the MaxRecycleCount Node.
     */
    UInteger getMaxRecycleCount() throws UaException;

    /**
     * Set the local value of the MaxRecycleCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxRecycleCount the local value to set for the MaxRecycleCount Node.
     * @throws UaException if an error occurs creating or getting the MaxRecycleCount Node.
     */
    void setMaxRecycleCount(UInteger maxRecycleCount) throws UaException;

    /**
     * Read the value of the MaxRecycleCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxRecycleCount() throws UaException;

    /**
     * Write a new value for the MaxRecycleCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxRecycleCount the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxRecycleCount(UInteger maxRecycleCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxRecycleCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxRecycleCountAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxRecycleCount(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxRecycleCountAsync(UInteger maxRecycleCount);

    /**
     * Get the MaxRecycleCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxRecycleCount {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxRecycleCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxRecycleCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxRecycleCountNodeAsync();

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

    /**
     * Get the local value of the ProgramDiagnostics Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ProgramDiagnostics Node.
     * @throws UaException if an error occurs creating or getting the ProgramDiagnostics Node.
     */
    ProgramDiagnosticDataType getProgramDiagnostics() throws UaException;

    /**
     * Set the local value of the ProgramDiagnostics Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param programDiagnostics the local value to set for the ProgramDiagnostics Node.
     * @throws UaException if an error occurs creating or getting the ProgramDiagnostics Node.
     */
    void setProgramDiagnostics(ProgramDiagnosticDataType programDiagnostics) throws UaException;

    /**
     * Read the value of the ProgramDiagnostics Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ProgramDiagnosticDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ProgramDiagnosticDataType readProgramDiagnostics() throws UaException;

    /**
     * Write a new value for the ProgramDiagnostics Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param programDiagnostics the {@link ProgramDiagnosticDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeProgramDiagnostics(ProgramDiagnosticDataType programDiagnostics) throws UaException;

    /**
     * An asynchronous implementation of {@link #readProgramDiagnostics()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ProgramDiagnosticDataType> readProgramDiagnosticsAsync();

    /**
     * An asynchronous implementation of {@link #writeProgramDiagnostics(ProgramDiagnosticDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeProgramDiagnosticsAsync(
        ProgramDiagnosticDataType programDiagnostics);

    /**
     * Get the ProgramDiagnostics {@link ProgramDiagnosticType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ProgramDiagnostics {@link ProgramDiagnosticType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    ProgramDiagnosticType getProgramDiagnosticsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getProgramDiagnosticsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends ProgramDiagnosticType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends ProgramDiagnosticType> getProgramDiagnosticsNodeAsync();

    /**
     * Get the FinalResultData {@link BaseObjectType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the FinalResultData {@link BaseObjectType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseObjectType getFinalResultDataNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getFinalResultDataNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * BaseObjectType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseObjectType> getFinalResultDataNodeAsync();

    /**
     * Get the Ready {@link StateType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Ready {@link StateType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    StateType getReadyNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReadyNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * StateType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends StateType> getReadyNodeAsync();

    /**
     * Get the Running {@link StateType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Running {@link StateType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    StateType getRunningNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRunningNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * StateType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends StateType> getRunningNodeAsync();

    /**
     * Get the Suspended {@link StateType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Suspended {@link StateType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    StateType getSuspendedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSuspendedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * StateType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends StateType> getSuspendedNodeAsync();

    /**
     * Get the Halted {@link StateType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Halted {@link StateType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    StateType getHaltedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getHaltedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * StateType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends StateType> getHaltedNodeAsync();

    /**
     * Get the HaltedToReady {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the HaltedToReady {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getHaltedToReadyNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getHaltedToReadyNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getHaltedToReadyNodeAsync();

    /**
     * Get the ReadyToRunning {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReadyToRunning {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getReadyToRunningNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReadyToRunningNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getReadyToRunningNodeAsync();

    /**
     * Get the RunningToHalted {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RunningToHalted {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getRunningToHaltedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRunningToHaltedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getRunningToHaltedNodeAsync();

    /**
     * Get the RunningToReady {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RunningToReady {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getRunningToReadyNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRunningToReadyNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getRunningToReadyNodeAsync();

    /**
     * Get the RunningToSuspended {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RunningToSuspended {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getRunningToSuspendedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRunningToSuspendedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getRunningToSuspendedNodeAsync();

    /**
     * Get the SuspendedToRunning {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SuspendedToRunning {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getSuspendedToRunningNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSuspendedToRunningNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getSuspendedToRunningNodeAsync();

    /**
     * Get the SuspendedToHalted {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SuspendedToHalted {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getSuspendedToHaltedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSuspendedToHaltedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getSuspendedToHaltedNodeAsync();

    /**
     * Get the SuspendedToReady {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SuspendedToReady {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getSuspendedToReadyNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSuspendedToReadyNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getSuspendedToReadyNodeAsync();

    /**
     * Get the ReadyToHalted {@link TransitionType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReadyToHalted {@link TransitionType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TransitionType getReadyToHaltedNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReadyToHaltedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TransitionType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TransitionType> getReadyToHaltedNodeAsync();
}

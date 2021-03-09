package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

public interface AuditHistoryValueUpdateEventType extends AuditHistoryUpdateEventType {
    QualifiedProperty<NodeId> UPDATED_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdatedNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<PerformUpdateType> PERFORM_INSERT_REPLACE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PerformInsertReplace",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11293"),
        ValueRanks.Scalar,
        PerformUpdateType.class
    );

    QualifiedProperty<DataValue[]> NEW_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    QualifiedProperty<DataValue[]> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    /**
     * Get the local value of the UpdatedNode Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UpdatedNode Node.
     * @throws UaException if an error occurs creating or getting the UpdatedNode Node.
     */
    NodeId getUpdatedNode() throws UaException;

    /**
     * Set the local value of the UpdatedNode Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param updatedNode the local value to set for the UpdatedNode Node.
     * @throws UaException if an error occurs creating or getting the UpdatedNode Node.
     */
    void setUpdatedNode(NodeId updatedNode) throws UaException;

    /**
     * Read the value of the UpdatedNode Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readUpdatedNode() throws UaException;

    /**
     * Write a new value for the UpdatedNode Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param updatedNode the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUpdatedNode(NodeId updatedNode) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUpdatedNode()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readUpdatedNodeAsync();

    /**
     * An asynchronous implementation of {@link #writeUpdatedNode(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeUpdatedNodeAsync(NodeId updatedNode);

    /**
     * Get the UpdatedNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UpdatedNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getUpdatedNodeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUpdatedNodeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getUpdatedNodeNodeAsync();

    /**
     * Get the local value of the PerformInsertReplace Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the PerformInsertReplace Node.
     * @throws UaException if an error occurs creating or getting the PerformInsertReplace Node.
     */
    PerformUpdateType getPerformInsertReplace() throws UaException;

    /**
     * Set the local value of the PerformInsertReplace Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param performInsertReplace the local value to set for the PerformInsertReplace Node.
     * @throws UaException if an error occurs creating or getting the PerformInsertReplace Node.
     */
    void setPerformInsertReplace(PerformUpdateType performInsertReplace) throws UaException;

    /**
     * Read the value of the PerformInsertReplace Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link PerformUpdateType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    PerformUpdateType readPerformInsertReplace() throws UaException;

    /**
     * Write a new value for the PerformInsertReplace Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param performInsertReplace the {@link PerformUpdateType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writePerformInsertReplace(PerformUpdateType performInsertReplace) throws UaException;

    /**
     * An asynchronous implementation of {@link #readPerformInsertReplace()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends PerformUpdateType> readPerformInsertReplaceAsync();

    /**
     * An asynchronous implementation of {@link #writePerformInsertReplace(PerformUpdateType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writePerformInsertReplaceAsync(
        PerformUpdateType performInsertReplace);

    /**
     * Get the PerformInsertReplace {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the PerformInsertReplace {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getPerformInsertReplaceNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getPerformInsertReplaceNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getPerformInsertReplaceNodeAsync();

    /**
     * Get the local value of the NewValues Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NewValues Node.
     * @throws UaException if an error occurs creating or getting the NewValues Node.
     */
    DataValue[] getNewValues() throws UaException;

    /**
     * Set the local value of the NewValues Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param newValues the local value to set for the NewValues Node.
     * @throws UaException if an error occurs creating or getting the NewValues Node.
     */
    void setNewValues(DataValue[] newValues) throws UaException;

    /**
     * Read the value of the NewValues Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DataValue[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DataValue[] readNewValues() throws UaException;

    /**
     * Write a new value for the NewValues Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param newValues the {@link DataValue[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNewValues(DataValue[] newValues) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNewValues()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DataValue[]> readNewValuesAsync();

    /**
     * An asynchronous implementation of {@link #writeNewValues(DataValue[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeNewValuesAsync(DataValue[] newValues);

    /**
     * Get the NewValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NewValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNewValuesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNewValuesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNewValuesNodeAsync();

    /**
     * Get the local value of the OldValues Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the OldValues Node.
     * @throws UaException if an error occurs creating or getting the OldValues Node.
     */
    DataValue[] getOldValues() throws UaException;

    /**
     * Set the local value of the OldValues Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param oldValues the local value to set for the OldValues Node.
     * @throws UaException if an error occurs creating or getting the OldValues Node.
     */
    void setOldValues(DataValue[] oldValues) throws UaException;

    /**
     * Read the value of the OldValues Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DataValue[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DataValue[] readOldValues() throws UaException;

    /**
     * Write a new value for the OldValues Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param oldValues the {@link DataValue[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeOldValues(DataValue[] oldValues) throws UaException;

    /**
     * An asynchronous implementation of {@link #readOldValues()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DataValue[]> readOldValuesAsync();

    /**
     * An asynchronous implementation of {@link #writeOldValues(DataValue[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeOldValuesAsync(DataValue[] oldValues);

    /**
     * Get the OldValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the OldValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getOldValuesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getOldValuesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getOldValuesNodeAsync();
}

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface HistoryServerCapabilitiesType extends BaseObjectType {
    QualifiedProperty<Boolean> ACCESS_HISTORY_DATA_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AccessHistoryDataCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> ACCESS_HISTORY_EVENTS_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AccessHistoryEventsCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UInteger> MAX_RETURN_DATA_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxReturnDataValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_RETURN_EVENT_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxReturnEventValues",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<Boolean> INSERT_DATA_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InsertDataCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> REPLACE_DATA_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReplaceDataCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> UPDATE_DATA_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdateDataCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> DELETE_RAW_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DeleteRawCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> DELETE_AT_TIME_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DeleteAtTimeCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> INSERT_EVENT_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InsertEventCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> REPLACE_EVENT_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReplaceEventCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> UPDATE_EVENT_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdateEventCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> DELETE_EVENT_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DeleteEventCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> INSERT_ANNOTATION_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InsertAnnotationCapability",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    /**
     * Get the local value of the AccessHistoryDataCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the AccessHistoryDataCapability Node.
     * @throws UaException if an error occurs creating or getting the AccessHistoryDataCapability Node.
     */
    Boolean getAccessHistoryDataCapability() throws UaException;

    /**
     * Set the local value of the AccessHistoryDataCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param accessHistoryDataCapability the local value to set for the AccessHistoryDataCapability Node.
     * @throws UaException if an error occurs creating or getting the AccessHistoryDataCapability Node.
     */
    void setAccessHistoryDataCapability(Boolean accessHistoryDataCapability) throws UaException;

    /**
     * Read the value of the AccessHistoryDataCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readAccessHistoryDataCapability() throws UaException;

    /**
     * Write a new value for the AccessHistoryDataCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param accessHistoryDataCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeAccessHistoryDataCapability(Boolean accessHistoryDataCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readAccessHistoryDataCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readAccessHistoryDataCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeAccessHistoryDataCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeAccessHistoryDataCapabilityAsync(
        Boolean accessHistoryDataCapability);

    /**
     * Get the AccessHistoryDataCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AccessHistoryDataCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getAccessHistoryDataCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAccessHistoryDataCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getAccessHistoryDataCapabilityNodeAsync();

    /**
     * Get the local value of the AccessHistoryEventsCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the AccessHistoryEventsCapability Node.
     * @throws UaException if an error occurs creating or getting the AccessHistoryEventsCapability Node.
     */
    Boolean getAccessHistoryEventsCapability() throws UaException;

    /**
     * Set the local value of the AccessHistoryEventsCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param accessHistoryEventsCapability the local value to set for the AccessHistoryEventsCapability Node.
     * @throws UaException if an error occurs creating or getting the AccessHistoryEventsCapability Node.
     */
    void setAccessHistoryEventsCapability(Boolean accessHistoryEventsCapability) throws UaException;

    /**
     * Read the value of the AccessHistoryEventsCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readAccessHistoryEventsCapability() throws UaException;

    /**
     * Write a new value for the AccessHistoryEventsCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param accessHistoryEventsCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeAccessHistoryEventsCapability(Boolean accessHistoryEventsCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readAccessHistoryEventsCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readAccessHistoryEventsCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeAccessHistoryEventsCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeAccessHistoryEventsCapabilityAsync(
        Boolean accessHistoryEventsCapability);

    /**
     * Get the AccessHistoryEventsCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AccessHistoryEventsCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getAccessHistoryEventsCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAccessHistoryEventsCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getAccessHistoryEventsCapabilityNodeAsync();

    /**
     * Get the local value of the MaxReturnDataValues Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxReturnDataValues Node.
     * @throws UaException if an error occurs creating or getting the MaxReturnDataValues Node.
     */
    UInteger getMaxReturnDataValues() throws UaException;

    /**
     * Set the local value of the MaxReturnDataValues Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxReturnDataValues the local value to set for the MaxReturnDataValues Node.
     * @throws UaException if an error occurs creating or getting the MaxReturnDataValues Node.
     */
    void setMaxReturnDataValues(UInteger maxReturnDataValues) throws UaException;

    /**
     * Read the value of the MaxReturnDataValues Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxReturnDataValues() throws UaException;

    /**
     * Write a new value for the MaxReturnDataValues Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxReturnDataValues the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxReturnDataValues(UInteger maxReturnDataValues) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxReturnDataValues()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxReturnDataValuesAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxReturnDataValues(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxReturnDataValuesAsync(UInteger maxReturnDataValues);

    /**
     * Get the MaxReturnDataValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxReturnDataValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxReturnDataValuesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxReturnDataValuesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxReturnDataValuesNodeAsync();

    /**
     * Get the local value of the MaxReturnEventValues Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxReturnEventValues Node.
     * @throws UaException if an error occurs creating or getting the MaxReturnEventValues Node.
     */
    UInteger getMaxReturnEventValues() throws UaException;

    /**
     * Set the local value of the MaxReturnEventValues Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxReturnEventValues the local value to set for the MaxReturnEventValues Node.
     * @throws UaException if an error occurs creating or getting the MaxReturnEventValues Node.
     */
    void setMaxReturnEventValues(UInteger maxReturnEventValues) throws UaException;

    /**
     * Read the value of the MaxReturnEventValues Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxReturnEventValues() throws UaException;

    /**
     * Write a new value for the MaxReturnEventValues Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxReturnEventValues the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxReturnEventValues(UInteger maxReturnEventValues) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxReturnEventValues()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxReturnEventValuesAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxReturnEventValues(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxReturnEventValuesAsync(UInteger maxReturnEventValues);

    /**
     * Get the MaxReturnEventValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxReturnEventValues {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxReturnEventValuesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxReturnEventValuesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxReturnEventValuesNodeAsync();

    /**
     * Get the local value of the InsertDataCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the InsertDataCapability Node.
     * @throws UaException if an error occurs creating or getting the InsertDataCapability Node.
     */
    Boolean getInsertDataCapability() throws UaException;

    /**
     * Set the local value of the InsertDataCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param insertDataCapability the local value to set for the InsertDataCapability Node.
     * @throws UaException if an error occurs creating or getting the InsertDataCapability Node.
     */
    void setInsertDataCapability(Boolean insertDataCapability) throws UaException;

    /**
     * Read the value of the InsertDataCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readInsertDataCapability() throws UaException;

    /**
     * Write a new value for the InsertDataCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param insertDataCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeInsertDataCapability(Boolean insertDataCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readInsertDataCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readInsertDataCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeInsertDataCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeInsertDataCapabilityAsync(Boolean insertDataCapability);

    /**
     * Get the InsertDataCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the InsertDataCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getInsertDataCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getInsertDataCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getInsertDataCapabilityNodeAsync();

    /**
     * Get the local value of the ReplaceDataCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ReplaceDataCapability Node.
     * @throws UaException if an error occurs creating or getting the ReplaceDataCapability Node.
     */
    Boolean getReplaceDataCapability() throws UaException;

    /**
     * Set the local value of the ReplaceDataCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param replaceDataCapability the local value to set for the ReplaceDataCapability Node.
     * @throws UaException if an error occurs creating or getting the ReplaceDataCapability Node.
     */
    void setReplaceDataCapability(Boolean replaceDataCapability) throws UaException;

    /**
     * Read the value of the ReplaceDataCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readReplaceDataCapability() throws UaException;

    /**
     * Write a new value for the ReplaceDataCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param replaceDataCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeReplaceDataCapability(Boolean replaceDataCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readReplaceDataCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readReplaceDataCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeReplaceDataCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeReplaceDataCapabilityAsync(Boolean replaceDataCapability);

    /**
     * Get the ReplaceDataCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReplaceDataCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getReplaceDataCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReplaceDataCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getReplaceDataCapabilityNodeAsync();

    /**
     * Get the local value of the UpdateDataCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UpdateDataCapability Node.
     * @throws UaException if an error occurs creating or getting the UpdateDataCapability Node.
     */
    Boolean getUpdateDataCapability() throws UaException;

    /**
     * Set the local value of the UpdateDataCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param updateDataCapability the local value to set for the UpdateDataCapability Node.
     * @throws UaException if an error occurs creating or getting the UpdateDataCapability Node.
     */
    void setUpdateDataCapability(Boolean updateDataCapability) throws UaException;

    /**
     * Read the value of the UpdateDataCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readUpdateDataCapability() throws UaException;

    /**
     * Write a new value for the UpdateDataCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param updateDataCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUpdateDataCapability(Boolean updateDataCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUpdateDataCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readUpdateDataCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeUpdateDataCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeUpdateDataCapabilityAsync(Boolean updateDataCapability);

    /**
     * Get the UpdateDataCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UpdateDataCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getUpdateDataCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUpdateDataCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getUpdateDataCapabilityNodeAsync();

    /**
     * Get the local value of the DeleteRawCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DeleteRawCapability Node.
     * @throws UaException if an error occurs creating or getting the DeleteRawCapability Node.
     */
    Boolean getDeleteRawCapability() throws UaException;

    /**
     * Set the local value of the DeleteRawCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param deleteRawCapability the local value to set for the DeleteRawCapability Node.
     * @throws UaException if an error occurs creating or getting the DeleteRawCapability Node.
     */
    void setDeleteRawCapability(Boolean deleteRawCapability) throws UaException;

    /**
     * Read the value of the DeleteRawCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readDeleteRawCapability() throws UaException;

    /**
     * Write a new value for the DeleteRawCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param deleteRawCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDeleteRawCapability(Boolean deleteRawCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDeleteRawCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readDeleteRawCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeDeleteRawCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDeleteRawCapabilityAsync(Boolean deleteRawCapability);

    /**
     * Get the DeleteRawCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DeleteRawCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getDeleteRawCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDeleteRawCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getDeleteRawCapabilityNodeAsync();

    /**
     * Get the local value of the DeleteAtTimeCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DeleteAtTimeCapability Node.
     * @throws UaException if an error occurs creating or getting the DeleteAtTimeCapability Node.
     */
    Boolean getDeleteAtTimeCapability() throws UaException;

    /**
     * Set the local value of the DeleteAtTimeCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param deleteAtTimeCapability the local value to set for the DeleteAtTimeCapability Node.
     * @throws UaException if an error occurs creating or getting the DeleteAtTimeCapability Node.
     */
    void setDeleteAtTimeCapability(Boolean deleteAtTimeCapability) throws UaException;

    /**
     * Read the value of the DeleteAtTimeCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readDeleteAtTimeCapability() throws UaException;

    /**
     * Write a new value for the DeleteAtTimeCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param deleteAtTimeCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDeleteAtTimeCapability(Boolean deleteAtTimeCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDeleteAtTimeCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readDeleteAtTimeCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeDeleteAtTimeCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDeleteAtTimeCapabilityAsync(Boolean deleteAtTimeCapability);

    /**
     * Get the DeleteAtTimeCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DeleteAtTimeCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getDeleteAtTimeCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDeleteAtTimeCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getDeleteAtTimeCapabilityNodeAsync();

    /**
     * Get the local value of the InsertEventCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the InsertEventCapability Node.
     * @throws UaException if an error occurs creating or getting the InsertEventCapability Node.
     */
    Boolean getInsertEventCapability() throws UaException;

    /**
     * Set the local value of the InsertEventCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param insertEventCapability the local value to set for the InsertEventCapability Node.
     * @throws UaException if an error occurs creating or getting the InsertEventCapability Node.
     */
    void setInsertEventCapability(Boolean insertEventCapability) throws UaException;

    /**
     * Read the value of the InsertEventCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readInsertEventCapability() throws UaException;

    /**
     * Write a new value for the InsertEventCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param insertEventCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeInsertEventCapability(Boolean insertEventCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readInsertEventCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readInsertEventCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeInsertEventCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeInsertEventCapabilityAsync(Boolean insertEventCapability);

    /**
     * Get the InsertEventCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the InsertEventCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getInsertEventCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getInsertEventCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getInsertEventCapabilityNodeAsync();

    /**
     * Get the local value of the ReplaceEventCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ReplaceEventCapability Node.
     * @throws UaException if an error occurs creating or getting the ReplaceEventCapability Node.
     */
    Boolean getReplaceEventCapability() throws UaException;

    /**
     * Set the local value of the ReplaceEventCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param replaceEventCapability the local value to set for the ReplaceEventCapability Node.
     * @throws UaException if an error occurs creating or getting the ReplaceEventCapability Node.
     */
    void setReplaceEventCapability(Boolean replaceEventCapability) throws UaException;

    /**
     * Read the value of the ReplaceEventCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readReplaceEventCapability() throws UaException;

    /**
     * Write a new value for the ReplaceEventCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param replaceEventCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeReplaceEventCapability(Boolean replaceEventCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readReplaceEventCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readReplaceEventCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeReplaceEventCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeReplaceEventCapabilityAsync(Boolean replaceEventCapability);

    /**
     * Get the ReplaceEventCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReplaceEventCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getReplaceEventCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReplaceEventCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getReplaceEventCapabilityNodeAsync();

    /**
     * Get the local value of the UpdateEventCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UpdateEventCapability Node.
     * @throws UaException if an error occurs creating or getting the UpdateEventCapability Node.
     */
    Boolean getUpdateEventCapability() throws UaException;

    /**
     * Set the local value of the UpdateEventCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param updateEventCapability the local value to set for the UpdateEventCapability Node.
     * @throws UaException if an error occurs creating or getting the UpdateEventCapability Node.
     */
    void setUpdateEventCapability(Boolean updateEventCapability) throws UaException;

    /**
     * Read the value of the UpdateEventCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readUpdateEventCapability() throws UaException;

    /**
     * Write a new value for the UpdateEventCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param updateEventCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUpdateEventCapability(Boolean updateEventCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUpdateEventCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readUpdateEventCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeUpdateEventCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeUpdateEventCapabilityAsync(Boolean updateEventCapability);

    /**
     * Get the UpdateEventCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UpdateEventCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getUpdateEventCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUpdateEventCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getUpdateEventCapabilityNodeAsync();

    /**
     * Get the local value of the DeleteEventCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DeleteEventCapability Node.
     * @throws UaException if an error occurs creating or getting the DeleteEventCapability Node.
     */
    Boolean getDeleteEventCapability() throws UaException;

    /**
     * Set the local value of the DeleteEventCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param deleteEventCapability the local value to set for the DeleteEventCapability Node.
     * @throws UaException if an error occurs creating or getting the DeleteEventCapability Node.
     */
    void setDeleteEventCapability(Boolean deleteEventCapability) throws UaException;

    /**
     * Read the value of the DeleteEventCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readDeleteEventCapability() throws UaException;

    /**
     * Write a new value for the DeleteEventCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param deleteEventCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDeleteEventCapability(Boolean deleteEventCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDeleteEventCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readDeleteEventCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeDeleteEventCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDeleteEventCapabilityAsync(Boolean deleteEventCapability);

    /**
     * Get the DeleteEventCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DeleteEventCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getDeleteEventCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDeleteEventCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getDeleteEventCapabilityNodeAsync();

    /**
     * Get the local value of the InsertAnnotationCapability Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the InsertAnnotationCapability Node.
     * @throws UaException if an error occurs creating or getting the InsertAnnotationCapability Node.
     */
    Boolean getInsertAnnotationCapability() throws UaException;

    /**
     * Set the local value of the InsertAnnotationCapability Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param insertAnnotationCapability the local value to set for the InsertAnnotationCapability Node.
     * @throws UaException if an error occurs creating or getting the InsertAnnotationCapability Node.
     */
    void setInsertAnnotationCapability(Boolean insertAnnotationCapability) throws UaException;

    /**
     * Read the value of the InsertAnnotationCapability Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readInsertAnnotationCapability() throws UaException;

    /**
     * Write a new value for the InsertAnnotationCapability Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param insertAnnotationCapability the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeInsertAnnotationCapability(Boolean insertAnnotationCapability) throws UaException;

    /**
     * An asynchronous implementation of {@link #readInsertAnnotationCapability()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readInsertAnnotationCapabilityAsync();

    /**
     * An asynchronous implementation of {@link #writeInsertAnnotationCapability(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeInsertAnnotationCapabilityAsync(
        Boolean insertAnnotationCapability);

    /**
     * Get the InsertAnnotationCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the InsertAnnotationCapability {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getInsertAnnotationCapabilityNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getInsertAnnotationCapabilityNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getInsertAnnotationCapabilityNodeAsync();

    /**
     * Get the AggregateFunctions {@link FolderType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AggregateFunctions {@link FolderType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    FolderType getAggregateFunctionsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAggregateFunctionsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * FolderType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends FolderType> getAggregateFunctionsNodeAsync();
}

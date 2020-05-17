package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface LimitAlarmType extends AlarmConditionType {
    QualifiedProperty<Double> HIGH_HIGH_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "HighHighLimit",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> HIGH_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "HighLimit",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> LOW_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LowLimit",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> LOW_LOW_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LowLowLimit",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    /**
     * Get the local value of the HighHighLimit Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the HighHighLimit Node.
     * @throws UaException if an error occurs creating or getting the HighHighLimit Node.
     */
    Double getHighHighLimit() throws UaException;

    /**
     * Set the local value of the HighHighLimit Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param highHighLimit the local value to set for the HighHighLimit Node.
     * @throws UaException if an error occurs creating or getting the HighHighLimit Node.
     */
    void setHighHighLimit(Double highHighLimit) throws UaException;

    /**
     * Read the value of the HighHighLimit Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readHighHighLimit() throws UaException;

    /**
     * Write a new value for the HighHighLimit Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param highHighLimit the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeHighHighLimit(Double highHighLimit) throws UaException;

    /**
     * An asynchronous implementation of {@link #readHighHighLimit()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readHighHighLimitAsync();

    /**
     * An asynchronous implementation of {@link #writeHighHighLimit(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeHighHighLimitAsync(Double highHighLimit);

    /**
     * Get the HighHighLimit {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the HighHighLimit {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getHighHighLimitNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getHighHighLimitNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getHighHighLimitNodeAsync();

    /**
     * Get the local value of the HighLimit Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the HighLimit Node.
     * @throws UaException if an error occurs creating or getting the HighLimit Node.
     */
    Double getHighLimit() throws UaException;

    /**
     * Set the local value of the HighLimit Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param highLimit the local value to set for the HighLimit Node.
     * @throws UaException if an error occurs creating or getting the HighLimit Node.
     */
    void setHighLimit(Double highLimit) throws UaException;

    /**
     * Read the value of the HighLimit Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readHighLimit() throws UaException;

    /**
     * Write a new value for the HighLimit Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param highLimit the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeHighLimit(Double highLimit) throws UaException;

    /**
     * An asynchronous implementation of {@link #readHighLimit()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readHighLimitAsync();

    /**
     * An asynchronous implementation of {@link #writeHighLimit(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeHighLimitAsync(Double highLimit);

    /**
     * Get the HighLimit {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the HighLimit {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getHighLimitNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getHighLimitNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getHighLimitNodeAsync();

    /**
     * Get the local value of the LowLimit Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LowLimit Node.
     * @throws UaException if an error occurs creating or getting the LowLimit Node.
     */
    Double getLowLimit() throws UaException;

    /**
     * Set the local value of the LowLimit Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lowLimit the local value to set for the LowLimit Node.
     * @throws UaException if an error occurs creating or getting the LowLimit Node.
     */
    void setLowLimit(Double lowLimit) throws UaException;

    /**
     * Read the value of the LowLimit Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readLowLimit() throws UaException;

    /**
     * Write a new value for the LowLimit Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lowLimit the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLowLimit(Double lowLimit) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLowLimit()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readLowLimitAsync();

    /**
     * An asynchronous implementation of {@link #writeLowLimit(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeLowLimitAsync(Double lowLimit);

    /**
     * Get the LowLimit {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LowLimit {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLowLimitNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLowLimitNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLowLimitNodeAsync();

    /**
     * Get the local value of the LowLowLimit Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LowLowLimit Node.
     * @throws UaException if an error occurs creating or getting the LowLowLimit Node.
     */
    Double getLowLowLimit() throws UaException;

    /**
     * Set the local value of the LowLowLimit Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lowLowLimit the local value to set for the LowLowLimit Node.
     * @throws UaException if an error occurs creating or getting the LowLowLimit Node.
     */
    void setLowLowLimit(Double lowLowLimit) throws UaException;

    /**
     * Read the value of the LowLowLimit Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readLowLowLimit() throws UaException;

    /**
     * Write a new value for the LowLowLimit Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lowLowLimit the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLowLowLimit(Double lowLowLimit) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLowLowLimit()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readLowLowLimitAsync();

    /**
     * An asynchronous implementation of {@link #writeLowLowLimit(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeLowLowLimitAsync(Double lowLowLimit);

    /**
     * Get the LowLowLimit {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LowLowLimit {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLowLowLimitNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLowLowLimitNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLowLowLimitNodeAsync();
}

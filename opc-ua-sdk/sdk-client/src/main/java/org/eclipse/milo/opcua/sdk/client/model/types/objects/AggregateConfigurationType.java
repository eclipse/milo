package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public interface AggregateConfigurationType extends BaseObjectType {
    QualifiedProperty<Boolean> TREAT_UNCERTAIN_AS_BAD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TreatUncertainAsBad",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UByte> PERCENT_DATA_BAD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PercentDataBad",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    QualifiedProperty<UByte> PERCENT_DATA_GOOD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PercentDataGood",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    QualifiedProperty<Boolean> USE_SLOPED_EXTRAPOLATION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UseSlopedExtrapolation",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    /**
     * Get the local value of the TreatUncertainAsBad Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the TreatUncertainAsBad Node.
     * @throws UaException if an error occurs creating or getting the TreatUncertainAsBad Node.
     */
    Boolean getTreatUncertainAsBad() throws UaException;

    /**
     * Set the local value of the TreatUncertainAsBad Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param treatUncertainAsBad the local value to set for the TreatUncertainAsBad Node.
     * @throws UaException if an error occurs creating or getting the TreatUncertainAsBad Node.
     */
    void setTreatUncertainAsBad(Boolean treatUncertainAsBad) throws UaException;

    /**
     * Read the value of the TreatUncertainAsBad Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readTreatUncertainAsBad() throws UaException;

    /**
     * Write a new value for the TreatUncertainAsBad Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param treatUncertainAsBad the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeTreatUncertainAsBad(Boolean treatUncertainAsBad) throws UaException;

    /**
     * An asynchronous implementation of {@link #readTreatUncertainAsBad()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readTreatUncertainAsBadAsync();

    /**
     * An asynchronous implementation of {@link #writeTreatUncertainAsBad(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeTreatUncertainAsBadAsync(Boolean treatUncertainAsBad);

    /**
     * Get the TreatUncertainAsBad {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the TreatUncertainAsBad {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getTreatUncertainAsBadNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTreatUncertainAsBadNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getTreatUncertainAsBadNodeAsync();

    /**
     * Get the local value of the PercentDataBad Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the PercentDataBad Node.
     * @throws UaException if an error occurs creating or getting the PercentDataBad Node.
     */
    UByte getPercentDataBad() throws UaException;

    /**
     * Set the local value of the PercentDataBad Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param percentDataBad the local value to set for the PercentDataBad Node.
     * @throws UaException if an error occurs creating or getting the PercentDataBad Node.
     */
    void setPercentDataBad(UByte percentDataBad) throws UaException;

    /**
     * Read the value of the PercentDataBad Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UByte} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UByte readPercentDataBad() throws UaException;

    /**
     * Write a new value for the PercentDataBad Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param percentDataBad the {@link UByte} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writePercentDataBad(UByte percentDataBad) throws UaException;

    /**
     * An asynchronous implementation of {@link #readPercentDataBad()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UByte> readPercentDataBadAsync();

    /**
     * An asynchronous implementation of {@link #writePercentDataBad(UByte)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writePercentDataBadAsync(UByte percentDataBad);

    /**
     * Get the PercentDataBad {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the PercentDataBad {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getPercentDataBadNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getPercentDataBadNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getPercentDataBadNodeAsync();

    /**
     * Get the local value of the PercentDataGood Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the PercentDataGood Node.
     * @throws UaException if an error occurs creating or getting the PercentDataGood Node.
     */
    UByte getPercentDataGood() throws UaException;

    /**
     * Set the local value of the PercentDataGood Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param percentDataGood the local value to set for the PercentDataGood Node.
     * @throws UaException if an error occurs creating or getting the PercentDataGood Node.
     */
    void setPercentDataGood(UByte percentDataGood) throws UaException;

    /**
     * Read the value of the PercentDataGood Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UByte} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UByte readPercentDataGood() throws UaException;

    /**
     * Write a new value for the PercentDataGood Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param percentDataGood the {@link UByte} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writePercentDataGood(UByte percentDataGood) throws UaException;

    /**
     * An asynchronous implementation of {@link #readPercentDataGood()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UByte> readPercentDataGoodAsync();

    /**
     * An asynchronous implementation of {@link #writePercentDataGood(UByte)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writePercentDataGoodAsync(UByte percentDataGood);

    /**
     * Get the PercentDataGood {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the PercentDataGood {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getPercentDataGoodNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getPercentDataGoodNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getPercentDataGoodNodeAsync();

    /**
     * Get the local value of the UseSlopedExtrapolation Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UseSlopedExtrapolation Node.
     * @throws UaException if an error occurs creating or getting the UseSlopedExtrapolation Node.
     */
    Boolean getUseSlopedExtrapolation() throws UaException;

    /**
     * Set the local value of the UseSlopedExtrapolation Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param useSlopedExtrapolation the local value to set for the UseSlopedExtrapolation Node.
     * @throws UaException if an error occurs creating or getting the UseSlopedExtrapolation Node.
     */
    void setUseSlopedExtrapolation(Boolean useSlopedExtrapolation) throws UaException;

    /**
     * Read the value of the UseSlopedExtrapolation Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Boolean} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Boolean readUseSlopedExtrapolation() throws UaException;

    /**
     * Write a new value for the UseSlopedExtrapolation Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param useSlopedExtrapolation the {@link Boolean} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUseSlopedExtrapolation(Boolean useSlopedExtrapolation) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUseSlopedExtrapolation()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Boolean> readUseSlopedExtrapolationAsync();

    /**
     * An asynchronous implementation of {@link #writeUseSlopedExtrapolation(Boolean)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeUseSlopedExtrapolationAsync(Boolean useSlopedExtrapolation);

    /**
     * Get the UseSlopedExtrapolation {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UseSlopedExtrapolation {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getUseSlopedExtrapolationNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUseSlopedExtrapolationNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getUseSlopedExtrapolationNodeAsync();
}

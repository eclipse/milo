package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public interface ServerCapabilitiesType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_PROFILE_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerProfileArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> LOCALE_ID_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LocaleIdArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=295"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<Double> MIN_SUPPORTED_SAMPLE_RATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MinSupportedSampleRate",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<UShort> MAX_BROWSE_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxBrowseContinuationPoints",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<UShort> MAX_QUERY_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxQueryContinuationPoints",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<UShort> MAX_HISTORY_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxHistoryContinuationPoints",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<SignedSoftwareCertificate[]> SOFTWARE_CERTIFICATES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SoftwareCertificates",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=344"),
        ValueRanks.OneDimension,
        SignedSoftwareCertificate[].class
    );

    QualifiedProperty<UInteger> MAX_ARRAY_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxArrayLength",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_STRING_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxStringLength",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_BYTE_STRING_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxByteStringLength",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    /**
     * Get the local value of the ServerProfileArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ServerProfileArray Node.
     * @throws UaException if an error occurs creating or getting the ServerProfileArray Node.
     */
    String[] getServerProfileArray() throws UaException;

    /**
     * Set the local value of the ServerProfileArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param serverProfileArray the local value to set for the ServerProfileArray Node.
     * @throws UaException if an error occurs creating or getting the ServerProfileArray Node.
     */
    void setServerProfileArray(String[] serverProfileArray) throws UaException;

    /**
     * Read the value of the ServerProfileArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String[] readServerProfileArray() throws UaException;

    /**
     * Write a new value for the ServerProfileArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param serverProfileArray the {@link String[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeServerProfileArray(String[] serverProfileArray) throws UaException;

    /**
     * An asynchronous implementation of {@link #readServerProfileArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String[]> readServerProfileArrayAsync();

    /**
     * An asynchronous implementation of {@link #writeServerProfileArray(String[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeServerProfileArrayAsync(String[] serverProfileArray);

    /**
     * Get the ServerProfileArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ServerProfileArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getServerProfileArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getServerProfileArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getServerProfileArrayNodeAsync();

    /**
     * Get the local value of the LocaleIdArray Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LocaleIdArray Node.
     * @throws UaException if an error occurs creating or getting the LocaleIdArray Node.
     */
    String[] getLocaleIdArray() throws UaException;

    /**
     * Set the local value of the LocaleIdArray Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param localeIdArray the local value to set for the LocaleIdArray Node.
     * @throws UaException if an error occurs creating or getting the LocaleIdArray Node.
     */
    void setLocaleIdArray(String[] localeIdArray) throws UaException;

    /**
     * Read the value of the LocaleIdArray Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String[] readLocaleIdArray() throws UaException;

    /**
     * Write a new value for the LocaleIdArray Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param localeIdArray the {@link String[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLocaleIdArray(String[] localeIdArray) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLocaleIdArray()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String[]> readLocaleIdArrayAsync();

    /**
     * An asynchronous implementation of {@link #writeLocaleIdArray(String[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLocaleIdArrayAsync(String[] localeIdArray);

    /**
     * Get the LocaleIdArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LocaleIdArray {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLocaleIdArrayNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLocaleIdArrayNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLocaleIdArrayNodeAsync();

    /**
     * Get the local value of the MinSupportedSampleRate Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MinSupportedSampleRate Node.
     * @throws UaException if an error occurs creating or getting the MinSupportedSampleRate Node.
     */
    Double getMinSupportedSampleRate() throws UaException;

    /**
     * Set the local value of the MinSupportedSampleRate Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param minSupportedSampleRate the local value to set for the MinSupportedSampleRate Node.
     * @throws UaException if an error occurs creating or getting the MinSupportedSampleRate Node.
     */
    void setMinSupportedSampleRate(Double minSupportedSampleRate) throws UaException;

    /**
     * Read the value of the MinSupportedSampleRate Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readMinSupportedSampleRate() throws UaException;

    /**
     * Write a new value for the MinSupportedSampleRate Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param minSupportedSampleRate the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMinSupportedSampleRate(Double minSupportedSampleRate) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMinSupportedSampleRate()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readMinSupportedSampleRateAsync();

    /**
     * An asynchronous implementation of {@link #writeMinSupportedSampleRate(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMinSupportedSampleRateAsync(Double minSupportedSampleRate);

    /**
     * Get the MinSupportedSampleRate {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MinSupportedSampleRate {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMinSupportedSampleRateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMinSupportedSampleRateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMinSupportedSampleRateNodeAsync();

    /**
     * Get the local value of the MaxBrowseContinuationPoints Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxBrowseContinuationPoints Node.
     * @throws UaException if an error occurs creating or getting the MaxBrowseContinuationPoints Node.
     */
    UShort getMaxBrowseContinuationPoints() throws UaException;

    /**
     * Set the local value of the MaxBrowseContinuationPoints Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxBrowseContinuationPoints the local value to set for the MaxBrowseContinuationPoints Node.
     * @throws UaException if an error occurs creating or getting the MaxBrowseContinuationPoints Node.
     */
    void setMaxBrowseContinuationPoints(UShort maxBrowseContinuationPoints) throws UaException;

    /**
     * Read the value of the MaxBrowseContinuationPoints Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UShort} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UShort readMaxBrowseContinuationPoints() throws UaException;

    /**
     * Write a new value for the MaxBrowseContinuationPoints Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxBrowseContinuationPoints the {@link UShort} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxBrowseContinuationPoints(UShort maxBrowseContinuationPoints) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxBrowseContinuationPoints()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UShort> readMaxBrowseContinuationPointsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxBrowseContinuationPoints(UShort)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxBrowseContinuationPointsAsync(
        UShort maxBrowseContinuationPoints);

    /**
     * Get the MaxBrowseContinuationPoints {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxBrowseContinuationPoints {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxBrowseContinuationPointsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxBrowseContinuationPointsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxBrowseContinuationPointsNodeAsync();

    /**
     * Get the local value of the MaxQueryContinuationPoints Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxQueryContinuationPoints Node.
     * @throws UaException if an error occurs creating or getting the MaxQueryContinuationPoints Node.
     */
    UShort getMaxQueryContinuationPoints() throws UaException;

    /**
     * Set the local value of the MaxQueryContinuationPoints Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxQueryContinuationPoints the local value to set for the MaxQueryContinuationPoints Node.
     * @throws UaException if an error occurs creating or getting the MaxQueryContinuationPoints Node.
     */
    void setMaxQueryContinuationPoints(UShort maxQueryContinuationPoints) throws UaException;

    /**
     * Read the value of the MaxQueryContinuationPoints Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UShort} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UShort readMaxQueryContinuationPoints() throws UaException;

    /**
     * Write a new value for the MaxQueryContinuationPoints Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxQueryContinuationPoints the {@link UShort} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxQueryContinuationPoints(UShort maxQueryContinuationPoints) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxQueryContinuationPoints()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UShort> readMaxQueryContinuationPointsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxQueryContinuationPoints(UShort)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxQueryContinuationPointsAsync(
        UShort maxQueryContinuationPoints);

    /**
     * Get the MaxQueryContinuationPoints {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxQueryContinuationPoints {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxQueryContinuationPointsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxQueryContinuationPointsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxQueryContinuationPointsNodeAsync();

    /**
     * Get the local value of the MaxHistoryContinuationPoints Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxHistoryContinuationPoints Node.
     * @throws UaException if an error occurs creating or getting the MaxHistoryContinuationPoints Node.
     */
    UShort getMaxHistoryContinuationPoints() throws UaException;

    /**
     * Set the local value of the MaxHistoryContinuationPoints Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxHistoryContinuationPoints the local value to set for the MaxHistoryContinuationPoints Node.
     * @throws UaException if an error occurs creating or getting the MaxHistoryContinuationPoints Node.
     */
    void setMaxHistoryContinuationPoints(UShort maxHistoryContinuationPoints) throws UaException;

    /**
     * Read the value of the MaxHistoryContinuationPoints Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UShort} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UShort readMaxHistoryContinuationPoints() throws UaException;

    /**
     * Write a new value for the MaxHistoryContinuationPoints Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxHistoryContinuationPoints the {@link UShort} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxHistoryContinuationPoints(UShort maxHistoryContinuationPoints) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxHistoryContinuationPoints()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UShort> readMaxHistoryContinuationPointsAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxHistoryContinuationPoints(UShort)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxHistoryContinuationPointsAsync(
        UShort maxHistoryContinuationPoints);

    /**
     * Get the MaxHistoryContinuationPoints {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxHistoryContinuationPoints {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxHistoryContinuationPointsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxHistoryContinuationPointsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxHistoryContinuationPointsNodeAsync();

    /**
     * Get the local value of the SoftwareCertificates Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SoftwareCertificates Node.
     * @throws UaException if an error occurs creating or getting the SoftwareCertificates Node.
     */
    SignedSoftwareCertificate[] getSoftwareCertificates() throws UaException;

    /**
     * Set the local value of the SoftwareCertificates Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param softwareCertificates the local value to set for the SoftwareCertificates Node.
     * @throws UaException if an error occurs creating or getting the SoftwareCertificates Node.
     */
    void setSoftwareCertificates(SignedSoftwareCertificate[] softwareCertificates) throws UaException;

    /**
     * Read the value of the SoftwareCertificates Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link SignedSoftwareCertificate[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    SignedSoftwareCertificate[] readSoftwareCertificates() throws UaException;

    /**
     * Write a new value for the SoftwareCertificates Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param softwareCertificates the {@link SignedSoftwareCertificate[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSoftwareCertificates(SignedSoftwareCertificate[] softwareCertificates) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readSoftwareCertificates()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends SignedSoftwareCertificate[]> readSoftwareCertificatesAsync();

    /**
     * An asynchronous implementation of {@link #writeSoftwareCertificates(SignedSoftwareCertificate[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSoftwareCertificatesAsync(
        SignedSoftwareCertificate[] softwareCertificates);

    /**
     * Get the SoftwareCertificates {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SoftwareCertificates {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSoftwareCertificatesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSoftwareCertificatesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSoftwareCertificatesNodeAsync();

    /**
     * Get the local value of the MaxArrayLength Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxArrayLength Node.
     * @throws UaException if an error occurs creating or getting the MaxArrayLength Node.
     */
    UInteger getMaxArrayLength() throws UaException;

    /**
     * Set the local value of the MaxArrayLength Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxArrayLength the local value to set for the MaxArrayLength Node.
     * @throws UaException if an error occurs creating or getting the MaxArrayLength Node.
     */
    void setMaxArrayLength(UInteger maxArrayLength) throws UaException;

    /**
     * Read the value of the MaxArrayLength Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxArrayLength() throws UaException;

    /**
     * Write a new value for the MaxArrayLength Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxArrayLength the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxArrayLength(UInteger maxArrayLength) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxArrayLength()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxArrayLengthAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxArrayLength(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxArrayLengthAsync(UInteger maxArrayLength);

    /**
     * Get the MaxArrayLength {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxArrayLength {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxArrayLengthNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxArrayLengthNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxArrayLengthNodeAsync();

    /**
     * Get the local value of the MaxStringLength Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxStringLength Node.
     * @throws UaException if an error occurs creating or getting the MaxStringLength Node.
     */
    UInteger getMaxStringLength() throws UaException;

    /**
     * Set the local value of the MaxStringLength Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxStringLength the local value to set for the MaxStringLength Node.
     * @throws UaException if an error occurs creating or getting the MaxStringLength Node.
     */
    void setMaxStringLength(UInteger maxStringLength) throws UaException;

    /**
     * Read the value of the MaxStringLength Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxStringLength() throws UaException;

    /**
     * Write a new value for the MaxStringLength Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxStringLength the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxStringLength(UInteger maxStringLength) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxStringLength()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxStringLengthAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxStringLength(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxStringLengthAsync(UInteger maxStringLength);

    /**
     * Get the MaxStringLength {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxStringLength {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxStringLengthNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxStringLengthNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxStringLengthNodeAsync();

    /**
     * Get the local value of the MaxByteStringLength Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxByteStringLength Node.
     * @throws UaException if an error occurs creating or getting the MaxByteStringLength Node.
     */
    UInteger getMaxByteStringLength() throws UaException;

    /**
     * Set the local value of the MaxByteStringLength Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxByteStringLength the local value to set for the MaxByteStringLength Node.
     * @throws UaException if an error occurs creating or getting the MaxByteStringLength Node.
     */
    void setMaxByteStringLength(UInteger maxByteStringLength) throws UaException;

    /**
     * Read the value of the MaxByteStringLength Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxByteStringLength() throws UaException;

    /**
     * Write a new value for the MaxByteStringLength Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxByteStringLength the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxByteStringLength(UInteger maxByteStringLength) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxByteStringLength()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxByteStringLengthAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxByteStringLength(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxByteStringLengthAsync(UInteger maxByteStringLength);

    /**
     * Get the MaxByteStringLength {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxByteStringLength {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMaxByteStringLengthNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxByteStringLengthNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMaxByteStringLengthNodeAsync();

    /**
     * Get the OperationLimits {@link OperationLimitsType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the OperationLimits {@link OperationLimitsType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    OperationLimitsType getOperationLimitsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getOperationLimitsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * OperationLimitsType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends OperationLimitsType> getOperationLimitsNodeAsync();

    /**
     * Get the ModellingRules {@link FolderType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ModellingRules {@link FolderType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    FolderType getModellingRulesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getModellingRulesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * FolderType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends FolderType> getModellingRulesNodeAsync();

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

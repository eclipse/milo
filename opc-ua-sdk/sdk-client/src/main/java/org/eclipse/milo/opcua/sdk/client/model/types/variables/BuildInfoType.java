package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface BuildInfoType extends BaseDataVariableType {
    /**
     * Get the local value of the ProductUri Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ProductUri Node.
     * @throws UaException if an error occurs creating or getting the ProductUri Node.
     */
    String getProductUri() throws UaException;

    /**
     * Set the local value of the ProductUri Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param productUri the local value to set for the ProductUri Node.
     * @throws UaException if an error occurs creating or getting the ProductUri Node.
     */
    void setProductUri(String productUri) throws UaException;

    /**
     * Read the value of the ProductUri Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readProductUri() throws UaException;

    /**
     * Write a new value for the ProductUri Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param productUri the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeProductUri(String productUri) throws UaException;

    /**
     * An asynchronous implementation of {@link #readProductUri()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readProductUriAsync();

    /**
     * An asynchronous implementation of {@link #writeProductUri(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeProductUriAsync(String productUri);

    /**
     * Get the ProductUri {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ProductUri {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getProductUriNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getProductUriNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getProductUriNodeAsync();

    /**
     * Get the local value of the ManufacturerName Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ManufacturerName Node.
     * @throws UaException if an error occurs creating or getting the ManufacturerName Node.
     */
    String getManufacturerName() throws UaException;

    /**
     * Set the local value of the ManufacturerName Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param manufacturerName the local value to set for the ManufacturerName Node.
     * @throws UaException if an error occurs creating or getting the ManufacturerName Node.
     */
    void setManufacturerName(String manufacturerName) throws UaException;

    /**
     * Read the value of the ManufacturerName Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readManufacturerName() throws UaException;

    /**
     * Write a new value for the ManufacturerName Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param manufacturerName the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeManufacturerName(String manufacturerName) throws UaException;

    /**
     * An asynchronous implementation of {@link #readManufacturerName()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readManufacturerNameAsync();

    /**
     * An asynchronous implementation of {@link #writeManufacturerName(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeManufacturerNameAsync(String manufacturerName);

    /**
     * Get the ManufacturerName {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ManufacturerName {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getManufacturerNameNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getManufacturerNameNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getManufacturerNameNodeAsync();

    /**
     * Get the local value of the ProductName Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ProductName Node.
     * @throws UaException if an error occurs creating or getting the ProductName Node.
     */
    String getProductName() throws UaException;

    /**
     * Set the local value of the ProductName Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param productName the local value to set for the ProductName Node.
     * @throws UaException if an error occurs creating or getting the ProductName Node.
     */
    void setProductName(String productName) throws UaException;

    /**
     * Read the value of the ProductName Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readProductName() throws UaException;

    /**
     * Write a new value for the ProductName Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param productName the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeProductName(String productName) throws UaException;

    /**
     * An asynchronous implementation of {@link #readProductName()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readProductNameAsync();

    /**
     * An asynchronous implementation of {@link #writeProductName(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeProductNameAsync(String productName);

    /**
     * Get the ProductName {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ProductName {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getProductNameNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getProductNameNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getProductNameNodeAsync();

    /**
     * Get the local value of the SoftwareVersion Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SoftwareVersion Node.
     * @throws UaException if an error occurs creating or getting the SoftwareVersion Node.
     */
    String getSoftwareVersion() throws UaException;

    /**
     * Set the local value of the SoftwareVersion Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param softwareVersion the local value to set for the SoftwareVersion Node.
     * @throws UaException if an error occurs creating or getting the SoftwareVersion Node.
     */
    void setSoftwareVersion(String softwareVersion) throws UaException;

    /**
     * Read the value of the SoftwareVersion Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readSoftwareVersion() throws UaException;

    /**
     * Write a new value for the SoftwareVersion Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param softwareVersion the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSoftwareVersion(String softwareVersion) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSoftwareVersion()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readSoftwareVersionAsync();

    /**
     * An asynchronous implementation of {@link #writeSoftwareVersion(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSoftwareVersionAsync(String softwareVersion);

    /**
     * Get the SoftwareVersion {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SoftwareVersion {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getSoftwareVersionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSoftwareVersionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getSoftwareVersionNodeAsync();

    /**
     * Get the local value of the BuildNumber Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the BuildNumber Node.
     * @throws UaException if an error occurs creating or getting the BuildNumber Node.
     */
    String getBuildNumber() throws UaException;

    /**
     * Set the local value of the BuildNumber Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param buildNumber the local value to set for the BuildNumber Node.
     * @throws UaException if an error occurs creating or getting the BuildNumber Node.
     */
    void setBuildNumber(String buildNumber) throws UaException;

    /**
     * Read the value of the BuildNumber Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readBuildNumber() throws UaException;

    /**
     * Write a new value for the BuildNumber Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param buildNumber the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeBuildNumber(String buildNumber) throws UaException;

    /**
     * An asynchronous implementation of {@link #readBuildNumber()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readBuildNumberAsync();

    /**
     * An asynchronous implementation of {@link #writeBuildNumber(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeBuildNumberAsync(String buildNumber);

    /**
     * Get the BuildNumber {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the BuildNumber {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getBuildNumberNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getBuildNumberNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getBuildNumberNodeAsync();

    /**
     * Get the local value of the BuildDate Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the BuildDate Node.
     * @throws UaException if an error occurs creating or getting the BuildDate Node.
     */
    DateTime getBuildDate() throws UaException;

    /**
     * Set the local value of the BuildDate Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param buildDate the local value to set for the BuildDate Node.
     * @throws UaException if an error occurs creating or getting the BuildDate Node.
     */
    void setBuildDate(DateTime buildDate) throws UaException;

    /**
     * Read the value of the BuildDate Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readBuildDate() throws UaException;

    /**
     * Write a new value for the BuildDate Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param buildDate the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeBuildDate(DateTime buildDate) throws UaException;

    /**
     * An asynchronous implementation of {@link #readBuildDate()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readBuildDateAsync();

    /**
     * An asynchronous implementation of {@link #writeBuildDate(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeBuildDateAsync(DateTime buildDate);

    /**
     * Get the BuildDate {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the BuildDate {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getBuildDateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getBuildDateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getBuildDateNodeAsync();
}

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;

public interface SessionDiagnosticsVariableType extends BaseDataVariableType {
    /**
     * Get the local value of the SessionId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SessionId Node.
     * @throws UaException if an error occurs creating or getting the SessionId Node.
     */
    NodeId getSessionId() throws UaException;

    /**
     * Set the local value of the SessionId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sessionId the local value to set for the SessionId Node.
     * @throws UaException if an error occurs creating or getting the SessionId Node.
     */
    void setSessionId(NodeId sessionId) throws UaException;

    /**
     * Read the value of the SessionId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readSessionId() throws UaException;

    /**
     * Write a new value for the SessionId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sessionId the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSessionId(NodeId sessionId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSessionId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readSessionIdAsync();

    /**
     * An asynchronous implementation of {@link #writeSessionId(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSessionIdAsync(NodeId sessionId);

    /**
     * Get the SessionId {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SessionId {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getSessionIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSessionIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getSessionIdNodeAsync();

    /**
     * Get the local value of the SessionName Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SessionName Node.
     * @throws UaException if an error occurs creating or getting the SessionName Node.
     */
    String getSessionName() throws UaException;

    /**
     * Set the local value of the SessionName Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param sessionName the local value to set for the SessionName Node.
     * @throws UaException if an error occurs creating or getting the SessionName Node.
     */
    void setSessionName(String sessionName) throws UaException;

    /**
     * Read the value of the SessionName Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readSessionName() throws UaException;

    /**
     * Write a new value for the SessionName Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param sessionName the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSessionName(String sessionName) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSessionName()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readSessionNameAsync();

    /**
     * An asynchronous implementation of {@link #writeSessionName(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSessionNameAsync(String sessionName);

    /**
     * Get the SessionName {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SessionName {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getSessionNameNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSessionNameNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getSessionNameNodeAsync();

    /**
     * Get the local value of the ClientDescription Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ClientDescription Node.
     * @throws UaException if an error occurs creating or getting the ClientDescription Node.
     */
    ApplicationDescription getClientDescription() throws UaException;

    /**
     * Set the local value of the ClientDescription Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param clientDescription the local value to set for the ClientDescription Node.
     * @throws UaException if an error occurs creating or getting the ClientDescription Node.
     */
    void setClientDescription(ApplicationDescription clientDescription) throws UaException;

    /**
     * Read the value of the ClientDescription Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ApplicationDescription} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ApplicationDescription readClientDescription() throws UaException;

    /**
     * Write a new value for the ClientDescription Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param clientDescription the {@link ApplicationDescription} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeClientDescription(ApplicationDescription clientDescription) throws UaException;

    /**
     * An asynchronous implementation of {@link #readClientDescription()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ApplicationDescription> readClientDescriptionAsync();

    /**
     * An asynchronous implementation of {@link #writeClientDescription(ApplicationDescription)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeClientDescriptionAsync(
        ApplicationDescription clientDescription);

    /**
     * Get the ClientDescription {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ClientDescription {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getClientDescriptionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getClientDescriptionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getClientDescriptionNodeAsync();

    /**
     * Get the local value of the ServerUri Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ServerUri Node.
     * @throws UaException if an error occurs creating or getting the ServerUri Node.
     */
    String getServerUri() throws UaException;

    /**
     * Set the local value of the ServerUri Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param serverUri the local value to set for the ServerUri Node.
     * @throws UaException if an error occurs creating or getting the ServerUri Node.
     */
    void setServerUri(String serverUri) throws UaException;

    /**
     * Read the value of the ServerUri Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readServerUri() throws UaException;

    /**
     * Write a new value for the ServerUri Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param serverUri the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeServerUri(String serverUri) throws UaException;

    /**
     * An asynchronous implementation of {@link #readServerUri()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readServerUriAsync();

    /**
     * An asynchronous implementation of {@link #writeServerUri(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeServerUriAsync(String serverUri);

    /**
     * Get the ServerUri {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ServerUri {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getServerUriNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getServerUriNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getServerUriNodeAsync();

    /**
     * Get the local value of the EndpointUrl Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the EndpointUrl Node.
     * @throws UaException if an error occurs creating or getting the EndpointUrl Node.
     */
    String getEndpointUrl() throws UaException;

    /**
     * Set the local value of the EndpointUrl Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param endpointUrl the local value to set for the EndpointUrl Node.
     * @throws UaException if an error occurs creating or getting the EndpointUrl Node.
     */
    void setEndpointUrl(String endpointUrl) throws UaException;

    /**
     * Read the value of the EndpointUrl Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readEndpointUrl() throws UaException;

    /**
     * Write a new value for the EndpointUrl Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param endpointUrl the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeEndpointUrl(String endpointUrl) throws UaException;

    /**
     * An asynchronous implementation of {@link #readEndpointUrl()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readEndpointUrlAsync();

    /**
     * An asynchronous implementation of {@link #writeEndpointUrl(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeEndpointUrlAsync(String endpointUrl);

    /**
     * Get the EndpointUrl {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the EndpointUrl {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getEndpointUrlNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getEndpointUrlNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getEndpointUrlNodeAsync();

    /**
     * Get the local value of the LocaleIds Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LocaleIds Node.
     * @throws UaException if an error occurs creating or getting the LocaleIds Node.
     */
    String[] getLocaleIds() throws UaException;

    /**
     * Set the local value of the LocaleIds Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param localeIds the local value to set for the LocaleIds Node.
     * @throws UaException if an error occurs creating or getting the LocaleIds Node.
     */
    void setLocaleIds(String[] localeIds) throws UaException;

    /**
     * Read the value of the LocaleIds Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String[] readLocaleIds() throws UaException;

    /**
     * Write a new value for the LocaleIds Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param localeIds the {@link String[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLocaleIds(String[] localeIds) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLocaleIds()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String[]> readLocaleIdsAsync();

    /**
     * An asynchronous implementation of {@link #writeLocaleIds(String[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLocaleIdsAsync(String[] localeIds);

    /**
     * Get the LocaleIds {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LocaleIds {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getLocaleIdsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLocaleIdsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getLocaleIdsNodeAsync();

    /**
     * Get the local value of the ActualSessionTimeout Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ActualSessionTimeout Node.
     * @throws UaException if an error occurs creating or getting the ActualSessionTimeout Node.
     */
    Double getActualSessionTimeout() throws UaException;

    /**
     * Set the local value of the ActualSessionTimeout Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param actualSessionTimeout the local value to set for the ActualSessionTimeout Node.
     * @throws UaException if an error occurs creating or getting the ActualSessionTimeout Node.
     */
    void setActualSessionTimeout(Double actualSessionTimeout) throws UaException;

    /**
     * Read the value of the ActualSessionTimeout Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Double} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Double readActualSessionTimeout() throws UaException;

    /**
     * Write a new value for the ActualSessionTimeout Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param actualSessionTimeout the {@link Double} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeActualSessionTimeout(Double actualSessionTimeout) throws UaException;

    /**
     * An asynchronous implementation of {@link #readActualSessionTimeout()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Double> readActualSessionTimeoutAsync();

    /**
     * An asynchronous implementation of {@link #writeActualSessionTimeout(Double)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeActualSessionTimeoutAsync(Double actualSessionTimeout);

    /**
     * Get the ActualSessionTimeout {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ActualSessionTimeout {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getActualSessionTimeoutNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getActualSessionTimeoutNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getActualSessionTimeoutNodeAsync();

    /**
     * Get the local value of the MaxResponseMessageSize Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MaxResponseMessageSize Node.
     * @throws UaException if an error occurs creating or getting the MaxResponseMessageSize Node.
     */
    UInteger getMaxResponseMessageSize() throws UaException;

    /**
     * Set the local value of the MaxResponseMessageSize Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param maxResponseMessageSize the local value to set for the MaxResponseMessageSize Node.
     * @throws UaException if an error occurs creating or getting the MaxResponseMessageSize Node.
     */
    void setMaxResponseMessageSize(UInteger maxResponseMessageSize) throws UaException;

    /**
     * Read the value of the MaxResponseMessageSize Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readMaxResponseMessageSize() throws UaException;

    /**
     * Write a new value for the MaxResponseMessageSize Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param maxResponseMessageSize the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMaxResponseMessageSize(UInteger maxResponseMessageSize) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMaxResponseMessageSize()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readMaxResponseMessageSizeAsync();

    /**
     * An asynchronous implementation of {@link #writeMaxResponseMessageSize(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeMaxResponseMessageSizeAsync(UInteger maxResponseMessageSize);

    /**
     * Get the MaxResponseMessageSize {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MaxResponseMessageSize {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getMaxResponseMessageSizeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMaxResponseMessageSizeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getMaxResponseMessageSizeNodeAsync();

    /**
     * Get the local value of the ClientConnectionTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ClientConnectionTime Node.
     * @throws UaException if an error occurs creating or getting the ClientConnectionTime Node.
     */
    DateTime getClientConnectionTime() throws UaException;

    /**
     * Set the local value of the ClientConnectionTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param clientConnectionTime the local value to set for the ClientConnectionTime Node.
     * @throws UaException if an error occurs creating or getting the ClientConnectionTime Node.
     */
    void setClientConnectionTime(DateTime clientConnectionTime) throws UaException;

    /**
     * Read the value of the ClientConnectionTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readClientConnectionTime() throws UaException;

    /**
     * Write a new value for the ClientConnectionTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param clientConnectionTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeClientConnectionTime(DateTime clientConnectionTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readClientConnectionTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readClientConnectionTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeClientConnectionTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeClientConnectionTimeAsync(DateTime clientConnectionTime);

    /**
     * Get the ClientConnectionTime {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ClientConnectionTime {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getClientConnectionTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getClientConnectionTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getClientConnectionTimeNodeAsync();

    /**
     * Get the local value of the ClientLastContactTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ClientLastContactTime Node.
     * @throws UaException if an error occurs creating or getting the ClientLastContactTime Node.
     */
    DateTime getClientLastContactTime() throws UaException;

    /**
     * Set the local value of the ClientLastContactTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param clientLastContactTime the local value to set for the ClientLastContactTime Node.
     * @throws UaException if an error occurs creating or getting the ClientLastContactTime Node.
     */
    void setClientLastContactTime(DateTime clientLastContactTime) throws UaException;

    /**
     * Read the value of the ClientLastContactTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readClientLastContactTime() throws UaException;

    /**
     * Write a new value for the ClientLastContactTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param clientLastContactTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeClientLastContactTime(DateTime clientLastContactTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readClientLastContactTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readClientLastContactTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeClientLastContactTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeClientLastContactTimeAsync(DateTime clientLastContactTime);

    /**
     * Get the ClientLastContactTime {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ClientLastContactTime {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getClientLastContactTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getClientLastContactTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getClientLastContactTimeNodeAsync();

    /**
     * Get the local value of the CurrentSubscriptionsCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CurrentSubscriptionsCount Node.
     * @throws UaException if an error occurs creating or getting the CurrentSubscriptionsCount Node.
     */
    UInteger getCurrentSubscriptionsCount() throws UaException;

    /**
     * Set the local value of the CurrentSubscriptionsCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param currentSubscriptionsCount the local value to set for the CurrentSubscriptionsCount Node.
     * @throws UaException if an error occurs creating or getting the CurrentSubscriptionsCount Node.
     */
    void setCurrentSubscriptionsCount(UInteger currentSubscriptionsCount) throws UaException;

    /**
     * Read the value of the CurrentSubscriptionsCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readCurrentSubscriptionsCount() throws UaException;

    /**
     * Write a new value for the CurrentSubscriptionsCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param currentSubscriptionsCount the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCurrentSubscriptionsCount(UInteger currentSubscriptionsCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCurrentSubscriptionsCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readCurrentSubscriptionsCountAsync();

    /**
     * An asynchronous implementation of {@link #writeCurrentSubscriptionsCount(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCurrentSubscriptionsCountAsync(
        UInteger currentSubscriptionsCount);

    /**
     * Get the CurrentSubscriptionsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CurrentSubscriptionsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getCurrentSubscriptionsCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCurrentSubscriptionsCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getCurrentSubscriptionsCountNodeAsync();

    /**
     * Get the local value of the CurrentMonitoredItemsCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CurrentMonitoredItemsCount Node.
     * @throws UaException if an error occurs creating or getting the CurrentMonitoredItemsCount Node.
     */
    UInteger getCurrentMonitoredItemsCount() throws UaException;

    /**
     * Set the local value of the CurrentMonitoredItemsCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param currentMonitoredItemsCount the local value to set for the CurrentMonitoredItemsCount Node.
     * @throws UaException if an error occurs creating or getting the CurrentMonitoredItemsCount Node.
     */
    void setCurrentMonitoredItemsCount(UInteger currentMonitoredItemsCount) throws UaException;

    /**
     * Read the value of the CurrentMonitoredItemsCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readCurrentMonitoredItemsCount() throws UaException;

    /**
     * Write a new value for the CurrentMonitoredItemsCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param currentMonitoredItemsCount the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCurrentMonitoredItemsCount(UInteger currentMonitoredItemsCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCurrentMonitoredItemsCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readCurrentMonitoredItemsCountAsync();

    /**
     * An asynchronous implementation of {@link #writeCurrentMonitoredItemsCount(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCurrentMonitoredItemsCountAsync(
        UInteger currentMonitoredItemsCount);

    /**
     * Get the CurrentMonitoredItemsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CurrentMonitoredItemsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getCurrentMonitoredItemsCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCurrentMonitoredItemsCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getCurrentMonitoredItemsCountNodeAsync();

    /**
     * Get the local value of the CurrentPublishRequestsInQueue Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CurrentPublishRequestsInQueue Node.
     * @throws UaException if an error occurs creating or getting the CurrentPublishRequestsInQueue Node.
     */
    UInteger getCurrentPublishRequestsInQueue() throws UaException;

    /**
     * Set the local value of the CurrentPublishRequestsInQueue Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param currentPublishRequestsInQueue the local value to set for the CurrentPublishRequestsInQueue Node.
     * @throws UaException if an error occurs creating or getting the CurrentPublishRequestsInQueue Node.
     */
    void setCurrentPublishRequestsInQueue(UInteger currentPublishRequestsInQueue) throws UaException;

    /**
     * Read the value of the CurrentPublishRequestsInQueue Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readCurrentPublishRequestsInQueue() throws UaException;

    /**
     * Write a new value for the CurrentPublishRequestsInQueue Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param currentPublishRequestsInQueue the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCurrentPublishRequestsInQueue(UInteger currentPublishRequestsInQueue) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readCurrentPublishRequestsInQueue()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readCurrentPublishRequestsInQueueAsync();

    /**
     * An asynchronous implementation of {@link #writeCurrentPublishRequestsInQueue(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCurrentPublishRequestsInQueueAsync(
        UInteger currentPublishRequestsInQueue);

    /**
     * Get the CurrentPublishRequestsInQueue {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CurrentPublishRequestsInQueue {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getCurrentPublishRequestsInQueueNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCurrentPublishRequestsInQueueNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getCurrentPublishRequestsInQueueNodeAsync();

    /**
     * Get the local value of the TotalRequestCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the TotalRequestCount Node.
     * @throws UaException if an error occurs creating or getting the TotalRequestCount Node.
     */
    ServiceCounterDataType getTotalRequestCount() throws UaException;

    /**
     * Set the local value of the TotalRequestCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param totalRequestCount the local value to set for the TotalRequestCount Node.
     * @throws UaException if an error occurs creating or getting the TotalRequestCount Node.
     */
    void setTotalRequestCount(ServiceCounterDataType totalRequestCount) throws UaException;

    /**
     * Read the value of the TotalRequestCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readTotalRequestCount() throws UaException;

    /**
     * Write a new value for the TotalRequestCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param totalRequestCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeTotalRequestCount(ServiceCounterDataType totalRequestCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readTotalRequestCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readTotalRequestCountAsync();

    /**
     * An asynchronous implementation of {@link #writeTotalRequestCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeTotalRequestCountAsync(
        ServiceCounterDataType totalRequestCount);

    /**
     * Get the TotalRequestCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the TotalRequestCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getTotalRequestCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTotalRequestCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getTotalRequestCountNodeAsync();

    /**
     * Get the local value of the UnauthorizedRequestCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UnauthorizedRequestCount Node.
     * @throws UaException if an error occurs creating or getting the UnauthorizedRequestCount Node.
     */
    UInteger getUnauthorizedRequestCount() throws UaException;

    /**
     * Set the local value of the UnauthorizedRequestCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param unauthorizedRequestCount the local value to set for the UnauthorizedRequestCount Node.
     * @throws UaException if an error occurs creating or getting the UnauthorizedRequestCount Node.
     */
    void setUnauthorizedRequestCount(UInteger unauthorizedRequestCount) throws UaException;

    /**
     * Read the value of the UnauthorizedRequestCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link UInteger} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    UInteger readUnauthorizedRequestCount() throws UaException;

    /**
     * Write a new value for the UnauthorizedRequestCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param unauthorizedRequestCount the {@link UInteger} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUnauthorizedRequestCount(UInteger unauthorizedRequestCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUnauthorizedRequestCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends UInteger> readUnauthorizedRequestCountAsync();

    /**
     * An asynchronous implementation of {@link #writeUnauthorizedRequestCount(UInteger)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeUnauthorizedRequestCountAsync(
        UInteger unauthorizedRequestCount);

    /**
     * Get the UnauthorizedRequestCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UnauthorizedRequestCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getUnauthorizedRequestCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUnauthorizedRequestCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getUnauthorizedRequestCountNodeAsync();

    /**
     * Get the local value of the ReadCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ReadCount Node.
     * @throws UaException if an error occurs creating or getting the ReadCount Node.
     */
    ServiceCounterDataType getReadCount() throws UaException;

    /**
     * Set the local value of the ReadCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param readCount the local value to set for the ReadCount Node.
     * @throws UaException if an error occurs creating or getting the ReadCount Node.
     */
    void setReadCount(ServiceCounterDataType readCount) throws UaException;

    /**
     * Read the value of the ReadCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readReadCount() throws UaException;

    /**
     * Write a new value for the ReadCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param readCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeReadCount(ServiceCounterDataType readCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readReadCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readReadCountAsync();

    /**
     * An asynchronous implementation of {@link #writeReadCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeReadCountAsync(ServiceCounterDataType readCount);

    /**
     * Get the ReadCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ReadCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getReadCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getReadCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getReadCountNodeAsync();

    /**
     * Get the local value of the HistoryReadCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the HistoryReadCount Node.
     * @throws UaException if an error occurs creating or getting the HistoryReadCount Node.
     */
    ServiceCounterDataType getHistoryReadCount() throws UaException;

    /**
     * Set the local value of the HistoryReadCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param historyReadCount the local value to set for the HistoryReadCount Node.
     * @throws UaException if an error occurs creating or getting the HistoryReadCount Node.
     */
    void setHistoryReadCount(ServiceCounterDataType historyReadCount) throws UaException;

    /**
     * Read the value of the HistoryReadCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readHistoryReadCount() throws UaException;

    /**
     * Write a new value for the HistoryReadCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param historyReadCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeHistoryReadCount(ServiceCounterDataType historyReadCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readHistoryReadCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readHistoryReadCountAsync();

    /**
     * An asynchronous implementation of {@link #writeHistoryReadCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeHistoryReadCountAsync(ServiceCounterDataType historyReadCount);

    /**
     * Get the HistoryReadCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the HistoryReadCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getHistoryReadCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getHistoryReadCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getHistoryReadCountNodeAsync();

    /**
     * Get the local value of the WriteCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the WriteCount Node.
     * @throws UaException if an error occurs creating or getting the WriteCount Node.
     */
    ServiceCounterDataType getWriteCount() throws UaException;

    /**
     * Set the local value of the WriteCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param writeCount the local value to set for the WriteCount Node.
     * @throws UaException if an error occurs creating or getting the WriteCount Node.
     */
    void setWriteCount(ServiceCounterDataType writeCount) throws UaException;

    /**
     * Read the value of the WriteCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readWriteCount() throws UaException;

    /**
     * Write a new value for the WriteCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param writeCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeWriteCount(ServiceCounterDataType writeCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readWriteCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readWriteCountAsync();

    /**
     * An asynchronous implementation of {@link #writeWriteCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeWriteCountAsync(ServiceCounterDataType writeCount);

    /**
     * Get the WriteCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the WriteCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getWriteCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getWriteCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getWriteCountNodeAsync();

    /**
     * Get the local value of the HistoryUpdateCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the HistoryUpdateCount Node.
     * @throws UaException if an error occurs creating or getting the HistoryUpdateCount Node.
     */
    ServiceCounterDataType getHistoryUpdateCount() throws UaException;

    /**
     * Set the local value of the HistoryUpdateCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param historyUpdateCount the local value to set for the HistoryUpdateCount Node.
     * @throws UaException if an error occurs creating or getting the HistoryUpdateCount Node.
     */
    void setHistoryUpdateCount(ServiceCounterDataType historyUpdateCount) throws UaException;

    /**
     * Read the value of the HistoryUpdateCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readHistoryUpdateCount() throws UaException;

    /**
     * Write a new value for the HistoryUpdateCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param historyUpdateCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeHistoryUpdateCount(ServiceCounterDataType historyUpdateCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readHistoryUpdateCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readHistoryUpdateCountAsync();

    /**
     * An asynchronous implementation of {@link #writeHistoryUpdateCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeHistoryUpdateCountAsync(
        ServiceCounterDataType historyUpdateCount);

    /**
     * Get the HistoryUpdateCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the HistoryUpdateCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getHistoryUpdateCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getHistoryUpdateCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getHistoryUpdateCountNodeAsync();

    /**
     * Get the local value of the CallCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CallCount Node.
     * @throws UaException if an error occurs creating or getting the CallCount Node.
     */
    ServiceCounterDataType getCallCount() throws UaException;

    /**
     * Set the local value of the CallCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param callCount the local value to set for the CallCount Node.
     * @throws UaException if an error occurs creating or getting the CallCount Node.
     */
    void setCallCount(ServiceCounterDataType callCount) throws UaException;

    /**
     * Read the value of the CallCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readCallCount() throws UaException;

    /**
     * Write a new value for the CallCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param callCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCallCount(ServiceCounterDataType callCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCallCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readCallCountAsync();

    /**
     * An asynchronous implementation of {@link #writeCallCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCallCountAsync(ServiceCounterDataType callCount);

    /**
     * Get the CallCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CallCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getCallCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCallCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getCallCountNodeAsync();

    /**
     * Get the local value of the CreateMonitoredItemsCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CreateMonitoredItemsCount Node.
     * @throws UaException if an error occurs creating or getting the CreateMonitoredItemsCount Node.
     */
    ServiceCounterDataType getCreateMonitoredItemsCount() throws UaException;

    /**
     * Set the local value of the CreateMonitoredItemsCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param createMonitoredItemsCount the local value to set for the CreateMonitoredItemsCount Node.
     * @throws UaException if an error occurs creating or getting the CreateMonitoredItemsCount Node.
     */
    void setCreateMonitoredItemsCount(ServiceCounterDataType createMonitoredItemsCount) throws
        UaException;

    /**
     * Read the value of the CreateMonitoredItemsCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readCreateMonitoredItemsCount() throws UaException;

    /**
     * Write a new value for the CreateMonitoredItemsCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param createMonitoredItemsCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCreateMonitoredItemsCount(ServiceCounterDataType createMonitoredItemsCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readCreateMonitoredItemsCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readCreateMonitoredItemsCountAsync();

    /**
     * An asynchronous implementation of {@link #writeCreateMonitoredItemsCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCreateMonitoredItemsCountAsync(
        ServiceCounterDataType createMonitoredItemsCount);

    /**
     * Get the CreateMonitoredItemsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CreateMonitoredItemsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getCreateMonitoredItemsCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCreateMonitoredItemsCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getCreateMonitoredItemsCountNodeAsync();

    /**
     * Get the local value of the ModifyMonitoredItemsCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ModifyMonitoredItemsCount Node.
     * @throws UaException if an error occurs creating or getting the ModifyMonitoredItemsCount Node.
     */
    ServiceCounterDataType getModifyMonitoredItemsCount() throws UaException;

    /**
     * Set the local value of the ModifyMonitoredItemsCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param modifyMonitoredItemsCount the local value to set for the ModifyMonitoredItemsCount Node.
     * @throws UaException if an error occurs creating or getting the ModifyMonitoredItemsCount Node.
     */
    void setModifyMonitoredItemsCount(ServiceCounterDataType modifyMonitoredItemsCount) throws
        UaException;

    /**
     * Read the value of the ModifyMonitoredItemsCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readModifyMonitoredItemsCount() throws UaException;

    /**
     * Write a new value for the ModifyMonitoredItemsCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param modifyMonitoredItemsCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeModifyMonitoredItemsCount(ServiceCounterDataType modifyMonitoredItemsCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readModifyMonitoredItemsCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readModifyMonitoredItemsCountAsync();

    /**
     * An asynchronous implementation of {@link #writeModifyMonitoredItemsCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeModifyMonitoredItemsCountAsync(
        ServiceCounterDataType modifyMonitoredItemsCount);

    /**
     * Get the ModifyMonitoredItemsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ModifyMonitoredItemsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getModifyMonitoredItemsCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getModifyMonitoredItemsCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getModifyMonitoredItemsCountNodeAsync();

    /**
     * Get the local value of the SetMonitoringModeCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SetMonitoringModeCount Node.
     * @throws UaException if an error occurs creating or getting the SetMonitoringModeCount Node.
     */
    ServiceCounterDataType getSetMonitoringModeCount() throws UaException;

    /**
     * Set the local value of the SetMonitoringModeCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param setMonitoringModeCount the local value to set for the SetMonitoringModeCount Node.
     * @throws UaException if an error occurs creating or getting the SetMonitoringModeCount Node.
     */
    void setSetMonitoringModeCount(ServiceCounterDataType setMonitoringModeCount) throws UaException;

    /**
     * Read the value of the SetMonitoringModeCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readSetMonitoringModeCount() throws UaException;

    /**
     * Write a new value for the SetMonitoringModeCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param setMonitoringModeCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSetMonitoringModeCount(ServiceCounterDataType setMonitoringModeCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readSetMonitoringModeCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readSetMonitoringModeCountAsync();

    /**
     * An asynchronous implementation of {@link #writeSetMonitoringModeCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSetMonitoringModeCountAsync(
        ServiceCounterDataType setMonitoringModeCount);

    /**
     * Get the SetMonitoringModeCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SetMonitoringModeCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getSetMonitoringModeCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSetMonitoringModeCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getSetMonitoringModeCountNodeAsync();

    /**
     * Get the local value of the SetTriggeringCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SetTriggeringCount Node.
     * @throws UaException if an error occurs creating or getting the SetTriggeringCount Node.
     */
    ServiceCounterDataType getSetTriggeringCount() throws UaException;

    /**
     * Set the local value of the SetTriggeringCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param setTriggeringCount the local value to set for the SetTriggeringCount Node.
     * @throws UaException if an error occurs creating or getting the SetTriggeringCount Node.
     */
    void setSetTriggeringCount(ServiceCounterDataType setTriggeringCount) throws UaException;

    /**
     * Read the value of the SetTriggeringCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readSetTriggeringCount() throws UaException;

    /**
     * Write a new value for the SetTriggeringCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param setTriggeringCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSetTriggeringCount(ServiceCounterDataType setTriggeringCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSetTriggeringCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readSetTriggeringCountAsync();

    /**
     * An asynchronous implementation of {@link #writeSetTriggeringCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSetTriggeringCountAsync(
        ServiceCounterDataType setTriggeringCount);

    /**
     * Get the SetTriggeringCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SetTriggeringCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getSetTriggeringCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSetTriggeringCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getSetTriggeringCountNodeAsync();

    /**
     * Get the local value of the DeleteMonitoredItemsCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DeleteMonitoredItemsCount Node.
     * @throws UaException if an error occurs creating or getting the DeleteMonitoredItemsCount Node.
     */
    ServiceCounterDataType getDeleteMonitoredItemsCount() throws UaException;

    /**
     * Set the local value of the DeleteMonitoredItemsCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param deleteMonitoredItemsCount the local value to set for the DeleteMonitoredItemsCount Node.
     * @throws UaException if an error occurs creating or getting the DeleteMonitoredItemsCount Node.
     */
    void setDeleteMonitoredItemsCount(ServiceCounterDataType deleteMonitoredItemsCount) throws
        UaException;

    /**
     * Read the value of the DeleteMonitoredItemsCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readDeleteMonitoredItemsCount() throws UaException;

    /**
     * Write a new value for the DeleteMonitoredItemsCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param deleteMonitoredItemsCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDeleteMonitoredItemsCount(ServiceCounterDataType deleteMonitoredItemsCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readDeleteMonitoredItemsCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readDeleteMonitoredItemsCountAsync();

    /**
     * An asynchronous implementation of {@link #writeDeleteMonitoredItemsCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDeleteMonitoredItemsCountAsync(
        ServiceCounterDataType deleteMonitoredItemsCount);

    /**
     * Get the DeleteMonitoredItemsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DeleteMonitoredItemsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getDeleteMonitoredItemsCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDeleteMonitoredItemsCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getDeleteMonitoredItemsCountNodeAsync();

    /**
     * Get the local value of the CreateSubscriptionCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CreateSubscriptionCount Node.
     * @throws UaException if an error occurs creating or getting the CreateSubscriptionCount Node.
     */
    ServiceCounterDataType getCreateSubscriptionCount() throws UaException;

    /**
     * Set the local value of the CreateSubscriptionCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param createSubscriptionCount the local value to set for the CreateSubscriptionCount Node.
     * @throws UaException if an error occurs creating or getting the CreateSubscriptionCount Node.
     */
    void setCreateSubscriptionCount(ServiceCounterDataType createSubscriptionCount) throws
        UaException;

    /**
     * Read the value of the CreateSubscriptionCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readCreateSubscriptionCount() throws UaException;

    /**
     * Write a new value for the CreateSubscriptionCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param createSubscriptionCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCreateSubscriptionCount(ServiceCounterDataType createSubscriptionCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readCreateSubscriptionCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readCreateSubscriptionCountAsync();

    /**
     * An asynchronous implementation of {@link #writeCreateSubscriptionCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCreateSubscriptionCountAsync(
        ServiceCounterDataType createSubscriptionCount);

    /**
     * Get the CreateSubscriptionCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CreateSubscriptionCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getCreateSubscriptionCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCreateSubscriptionCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getCreateSubscriptionCountNodeAsync();

    /**
     * Get the local value of the ModifySubscriptionCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ModifySubscriptionCount Node.
     * @throws UaException if an error occurs creating or getting the ModifySubscriptionCount Node.
     */
    ServiceCounterDataType getModifySubscriptionCount() throws UaException;

    /**
     * Set the local value of the ModifySubscriptionCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param modifySubscriptionCount the local value to set for the ModifySubscriptionCount Node.
     * @throws UaException if an error occurs creating or getting the ModifySubscriptionCount Node.
     */
    void setModifySubscriptionCount(ServiceCounterDataType modifySubscriptionCount) throws
        UaException;

    /**
     * Read the value of the ModifySubscriptionCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readModifySubscriptionCount() throws UaException;

    /**
     * Write a new value for the ModifySubscriptionCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param modifySubscriptionCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeModifySubscriptionCount(ServiceCounterDataType modifySubscriptionCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readModifySubscriptionCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readModifySubscriptionCountAsync();

    /**
     * An asynchronous implementation of {@link #writeModifySubscriptionCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeModifySubscriptionCountAsync(
        ServiceCounterDataType modifySubscriptionCount);

    /**
     * Get the ModifySubscriptionCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ModifySubscriptionCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getModifySubscriptionCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getModifySubscriptionCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getModifySubscriptionCountNodeAsync();

    /**
     * Get the local value of the SetPublishingModeCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SetPublishingModeCount Node.
     * @throws UaException if an error occurs creating or getting the SetPublishingModeCount Node.
     */
    ServiceCounterDataType getSetPublishingModeCount() throws UaException;

    /**
     * Set the local value of the SetPublishingModeCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param setPublishingModeCount the local value to set for the SetPublishingModeCount Node.
     * @throws UaException if an error occurs creating or getting the SetPublishingModeCount Node.
     */
    void setSetPublishingModeCount(ServiceCounterDataType setPublishingModeCount) throws UaException;

    /**
     * Read the value of the SetPublishingModeCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readSetPublishingModeCount() throws UaException;

    /**
     * Write a new value for the SetPublishingModeCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param setPublishingModeCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSetPublishingModeCount(ServiceCounterDataType setPublishingModeCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readSetPublishingModeCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readSetPublishingModeCountAsync();

    /**
     * An asynchronous implementation of {@link #writeSetPublishingModeCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeSetPublishingModeCountAsync(
        ServiceCounterDataType setPublishingModeCount);

    /**
     * Get the SetPublishingModeCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SetPublishingModeCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getSetPublishingModeCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSetPublishingModeCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getSetPublishingModeCountNodeAsync();

    /**
     * Get the local value of the PublishCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the PublishCount Node.
     * @throws UaException if an error occurs creating or getting the PublishCount Node.
     */
    ServiceCounterDataType getPublishCount() throws UaException;

    /**
     * Set the local value of the PublishCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param publishCount the local value to set for the PublishCount Node.
     * @throws UaException if an error occurs creating or getting the PublishCount Node.
     */
    void setPublishCount(ServiceCounterDataType publishCount) throws UaException;

    /**
     * Read the value of the PublishCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readPublishCount() throws UaException;

    /**
     * Write a new value for the PublishCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param publishCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writePublishCount(ServiceCounterDataType publishCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readPublishCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readPublishCountAsync();

    /**
     * An asynchronous implementation of {@link #writePublishCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writePublishCountAsync(ServiceCounterDataType publishCount);

    /**
     * Get the PublishCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the PublishCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getPublishCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getPublishCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getPublishCountNodeAsync();

    /**
     * Get the local value of the RepublishCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the RepublishCount Node.
     * @throws UaException if an error occurs creating or getting the RepublishCount Node.
     */
    ServiceCounterDataType getRepublishCount() throws UaException;

    /**
     * Set the local value of the RepublishCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param republishCount the local value to set for the RepublishCount Node.
     * @throws UaException if an error occurs creating or getting the RepublishCount Node.
     */
    void setRepublishCount(ServiceCounterDataType republishCount) throws UaException;

    /**
     * Read the value of the RepublishCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readRepublishCount() throws UaException;

    /**
     * Write a new value for the RepublishCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param republishCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeRepublishCount(ServiceCounterDataType republishCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readRepublishCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readRepublishCountAsync();

    /**
     * An asynchronous implementation of {@link #writeRepublishCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeRepublishCountAsync(ServiceCounterDataType republishCount);

    /**
     * Get the RepublishCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RepublishCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getRepublishCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRepublishCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getRepublishCountNodeAsync();

    /**
     * Get the local value of the TransferSubscriptionsCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the TransferSubscriptionsCount Node.
     * @throws UaException if an error occurs creating or getting the TransferSubscriptionsCount Node.
     */
    ServiceCounterDataType getTransferSubscriptionsCount() throws UaException;

    /**
     * Set the local value of the TransferSubscriptionsCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param transferSubscriptionsCount the local value to set for the TransferSubscriptionsCount Node.
     * @throws UaException if an error occurs creating or getting the TransferSubscriptionsCount Node.
     */
    void setTransferSubscriptionsCount(ServiceCounterDataType transferSubscriptionsCount) throws
        UaException;

    /**
     * Read the value of the TransferSubscriptionsCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readTransferSubscriptionsCount() throws UaException;

    /**
     * Write a new value for the TransferSubscriptionsCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param transferSubscriptionsCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeTransferSubscriptionsCount(ServiceCounterDataType transferSubscriptionsCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readTransferSubscriptionsCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readTransferSubscriptionsCountAsync();

    /**
     * An asynchronous implementation of {@link #writeTransferSubscriptionsCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeTransferSubscriptionsCountAsync(
        ServiceCounterDataType transferSubscriptionsCount);

    /**
     * Get the TransferSubscriptionsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the TransferSubscriptionsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getTransferSubscriptionsCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTransferSubscriptionsCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getTransferSubscriptionsCountNodeAsync();

    /**
     * Get the local value of the DeleteSubscriptionsCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DeleteSubscriptionsCount Node.
     * @throws UaException if an error occurs creating or getting the DeleteSubscriptionsCount Node.
     */
    ServiceCounterDataType getDeleteSubscriptionsCount() throws UaException;

    /**
     * Set the local value of the DeleteSubscriptionsCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param deleteSubscriptionsCount the local value to set for the DeleteSubscriptionsCount Node.
     * @throws UaException if an error occurs creating or getting the DeleteSubscriptionsCount Node.
     */
    void setDeleteSubscriptionsCount(ServiceCounterDataType deleteSubscriptionsCount) throws
        UaException;

    /**
     * Read the value of the DeleteSubscriptionsCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readDeleteSubscriptionsCount() throws UaException;

    /**
     * Write a new value for the DeleteSubscriptionsCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param deleteSubscriptionsCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDeleteSubscriptionsCount(ServiceCounterDataType deleteSubscriptionsCount) throws
        UaException;

    /**
     * An asynchronous implementation of {@link #readDeleteSubscriptionsCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readDeleteSubscriptionsCountAsync();

    /**
     * An asynchronous implementation of {@link #writeDeleteSubscriptionsCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDeleteSubscriptionsCountAsync(
        ServiceCounterDataType deleteSubscriptionsCount);

    /**
     * Get the DeleteSubscriptionsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DeleteSubscriptionsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getDeleteSubscriptionsCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDeleteSubscriptionsCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getDeleteSubscriptionsCountNodeAsync();

    /**
     * Get the local value of the AddNodesCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the AddNodesCount Node.
     * @throws UaException if an error occurs creating or getting the AddNodesCount Node.
     */
    ServiceCounterDataType getAddNodesCount() throws UaException;

    /**
     * Set the local value of the AddNodesCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param addNodesCount the local value to set for the AddNodesCount Node.
     * @throws UaException if an error occurs creating or getting the AddNodesCount Node.
     */
    void setAddNodesCount(ServiceCounterDataType addNodesCount) throws UaException;

    /**
     * Read the value of the AddNodesCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readAddNodesCount() throws UaException;

    /**
     * Write a new value for the AddNodesCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param addNodesCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeAddNodesCount(ServiceCounterDataType addNodesCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readAddNodesCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readAddNodesCountAsync();

    /**
     * An asynchronous implementation of {@link #writeAddNodesCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeAddNodesCountAsync(ServiceCounterDataType addNodesCount);

    /**
     * Get the AddNodesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AddNodesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getAddNodesCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAddNodesCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getAddNodesCountNodeAsync();

    /**
     * Get the local value of the AddReferencesCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the AddReferencesCount Node.
     * @throws UaException if an error occurs creating or getting the AddReferencesCount Node.
     */
    ServiceCounterDataType getAddReferencesCount() throws UaException;

    /**
     * Set the local value of the AddReferencesCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param addReferencesCount the local value to set for the AddReferencesCount Node.
     * @throws UaException if an error occurs creating or getting the AddReferencesCount Node.
     */
    void setAddReferencesCount(ServiceCounterDataType addReferencesCount) throws UaException;

    /**
     * Read the value of the AddReferencesCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readAddReferencesCount() throws UaException;

    /**
     * Write a new value for the AddReferencesCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param addReferencesCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeAddReferencesCount(ServiceCounterDataType addReferencesCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readAddReferencesCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readAddReferencesCountAsync();

    /**
     * An asynchronous implementation of {@link #writeAddReferencesCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeAddReferencesCountAsync(
        ServiceCounterDataType addReferencesCount);

    /**
     * Get the AddReferencesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AddReferencesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getAddReferencesCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAddReferencesCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getAddReferencesCountNodeAsync();

    /**
     * Get the local value of the DeleteNodesCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DeleteNodesCount Node.
     * @throws UaException if an error occurs creating or getting the DeleteNodesCount Node.
     */
    ServiceCounterDataType getDeleteNodesCount() throws UaException;

    /**
     * Set the local value of the DeleteNodesCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param deleteNodesCount the local value to set for the DeleteNodesCount Node.
     * @throws UaException if an error occurs creating or getting the DeleteNodesCount Node.
     */
    void setDeleteNodesCount(ServiceCounterDataType deleteNodesCount) throws UaException;

    /**
     * Read the value of the DeleteNodesCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readDeleteNodesCount() throws UaException;

    /**
     * Write a new value for the DeleteNodesCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param deleteNodesCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDeleteNodesCount(ServiceCounterDataType deleteNodesCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDeleteNodesCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readDeleteNodesCountAsync();

    /**
     * An asynchronous implementation of {@link #writeDeleteNodesCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDeleteNodesCountAsync(ServiceCounterDataType deleteNodesCount);

    /**
     * Get the DeleteNodesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DeleteNodesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getDeleteNodesCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDeleteNodesCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getDeleteNodesCountNodeAsync();

    /**
     * Get the local value of the DeleteReferencesCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DeleteReferencesCount Node.
     * @throws UaException if an error occurs creating or getting the DeleteReferencesCount Node.
     */
    ServiceCounterDataType getDeleteReferencesCount() throws UaException;

    /**
     * Set the local value of the DeleteReferencesCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param deleteReferencesCount the local value to set for the DeleteReferencesCount Node.
     * @throws UaException if an error occurs creating or getting the DeleteReferencesCount Node.
     */
    void setDeleteReferencesCount(ServiceCounterDataType deleteReferencesCount) throws UaException;

    /**
     * Read the value of the DeleteReferencesCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readDeleteReferencesCount() throws UaException;

    /**
     * Write a new value for the DeleteReferencesCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param deleteReferencesCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDeleteReferencesCount(ServiceCounterDataType deleteReferencesCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDeleteReferencesCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readDeleteReferencesCountAsync();

    /**
     * An asynchronous implementation of {@link #writeDeleteReferencesCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeDeleteReferencesCountAsync(
        ServiceCounterDataType deleteReferencesCount);

    /**
     * Get the DeleteReferencesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DeleteReferencesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getDeleteReferencesCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDeleteReferencesCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getDeleteReferencesCountNodeAsync();

    /**
     * Get the local value of the BrowseCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the BrowseCount Node.
     * @throws UaException if an error occurs creating or getting the BrowseCount Node.
     */
    ServiceCounterDataType getBrowseCount() throws UaException;

    /**
     * Set the local value of the BrowseCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param browseCount the local value to set for the BrowseCount Node.
     * @throws UaException if an error occurs creating or getting the BrowseCount Node.
     */
    void setBrowseCount(ServiceCounterDataType browseCount) throws UaException;

    /**
     * Read the value of the BrowseCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readBrowseCount() throws UaException;

    /**
     * Write a new value for the BrowseCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param browseCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeBrowseCount(ServiceCounterDataType browseCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readBrowseCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readBrowseCountAsync();

    /**
     * An asynchronous implementation of {@link #writeBrowseCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeBrowseCountAsync(ServiceCounterDataType browseCount);

    /**
     * Get the BrowseCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the BrowseCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getBrowseCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getBrowseCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getBrowseCountNodeAsync();

    /**
     * Get the local value of the BrowseNextCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the BrowseNextCount Node.
     * @throws UaException if an error occurs creating or getting the BrowseNextCount Node.
     */
    ServiceCounterDataType getBrowseNextCount() throws UaException;

    /**
     * Set the local value of the BrowseNextCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param browseNextCount the local value to set for the BrowseNextCount Node.
     * @throws UaException if an error occurs creating or getting the BrowseNextCount Node.
     */
    void setBrowseNextCount(ServiceCounterDataType browseNextCount) throws UaException;

    /**
     * Read the value of the BrowseNextCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readBrowseNextCount() throws UaException;

    /**
     * Write a new value for the BrowseNextCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param browseNextCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeBrowseNextCount(ServiceCounterDataType browseNextCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readBrowseNextCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readBrowseNextCountAsync();

    /**
     * An asynchronous implementation of {@link #writeBrowseNextCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeBrowseNextCountAsync(ServiceCounterDataType browseNextCount);

    /**
     * Get the BrowseNextCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the BrowseNextCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getBrowseNextCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getBrowseNextCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getBrowseNextCountNodeAsync();

    /**
     * Get the local value of the TranslateBrowsePathsToNodeIdsCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the TranslateBrowsePathsToNodeIdsCount Node.
     * @throws UaException if an error occurs creating or getting the TranslateBrowsePathsToNodeIdsCount Node.
     */
    ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() throws UaException;

    /**
     * Set the local value of the TranslateBrowsePathsToNodeIdsCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param translateBrowsePathsToNodeIdsCount the local value to set for the TranslateBrowsePathsToNodeIdsCount Node.
     * @throws UaException if an error occurs creating or getting the TranslateBrowsePathsToNodeIdsCount Node.
     */
    void setTranslateBrowsePathsToNodeIdsCount(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount) throws UaException;

    /**
     * Read the value of the TranslateBrowsePathsToNodeIdsCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readTranslateBrowsePathsToNodeIdsCount() throws UaException;

    /**
     * Write a new value for the TranslateBrowsePathsToNodeIdsCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param translateBrowsePathsToNodeIdsCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeTranslateBrowsePathsToNodeIdsCount(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readTranslateBrowsePathsToNodeIdsCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readTranslateBrowsePathsToNodeIdsCountAsync();

    /**
     * An asynchronous implementation of {@link #writeTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeTranslateBrowsePathsToNodeIdsCountAsync(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount);

    /**
     * Get the TranslateBrowsePathsToNodeIdsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the TranslateBrowsePathsToNodeIdsCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getTranslateBrowsePathsToNodeIdsCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTranslateBrowsePathsToNodeIdsCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getTranslateBrowsePathsToNodeIdsCountNodeAsync(
    );

    /**
     * Get the local value of the QueryFirstCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the QueryFirstCount Node.
     * @throws UaException if an error occurs creating or getting the QueryFirstCount Node.
     */
    ServiceCounterDataType getQueryFirstCount() throws UaException;

    /**
     * Set the local value of the QueryFirstCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param queryFirstCount the local value to set for the QueryFirstCount Node.
     * @throws UaException if an error occurs creating or getting the QueryFirstCount Node.
     */
    void setQueryFirstCount(ServiceCounterDataType queryFirstCount) throws UaException;

    /**
     * Read the value of the QueryFirstCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readQueryFirstCount() throws UaException;

    /**
     * Write a new value for the QueryFirstCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param queryFirstCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeQueryFirstCount(ServiceCounterDataType queryFirstCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readQueryFirstCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readQueryFirstCountAsync();

    /**
     * An asynchronous implementation of {@link #writeQueryFirstCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeQueryFirstCountAsync(ServiceCounterDataType queryFirstCount);

    /**
     * Get the QueryFirstCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the QueryFirstCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getQueryFirstCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getQueryFirstCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getQueryFirstCountNodeAsync();

    /**
     * Get the local value of the QueryNextCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the QueryNextCount Node.
     * @throws UaException if an error occurs creating or getting the QueryNextCount Node.
     */
    ServiceCounterDataType getQueryNextCount() throws UaException;

    /**
     * Set the local value of the QueryNextCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param queryNextCount the local value to set for the QueryNextCount Node.
     * @throws UaException if an error occurs creating or getting the QueryNextCount Node.
     */
    void setQueryNextCount(ServiceCounterDataType queryNextCount) throws UaException;

    /**
     * Read the value of the QueryNextCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readQueryNextCount() throws UaException;

    /**
     * Write a new value for the QueryNextCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param queryNextCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeQueryNextCount(ServiceCounterDataType queryNextCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readQueryNextCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readQueryNextCountAsync();

    /**
     * An asynchronous implementation of {@link #writeQueryNextCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeQueryNextCountAsync(ServiceCounterDataType queryNextCount);

    /**
     * Get the QueryNextCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the QueryNextCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getQueryNextCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getQueryNextCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getQueryNextCountNodeAsync();

    /**
     * Get the local value of the RegisterNodesCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the RegisterNodesCount Node.
     * @throws UaException if an error occurs creating or getting the RegisterNodesCount Node.
     */
    ServiceCounterDataType getRegisterNodesCount() throws UaException;

    /**
     * Set the local value of the RegisterNodesCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param registerNodesCount the local value to set for the RegisterNodesCount Node.
     * @throws UaException if an error occurs creating or getting the RegisterNodesCount Node.
     */
    void setRegisterNodesCount(ServiceCounterDataType registerNodesCount) throws UaException;

    /**
     * Read the value of the RegisterNodesCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readRegisterNodesCount() throws UaException;

    /**
     * Write a new value for the RegisterNodesCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param registerNodesCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeRegisterNodesCount(ServiceCounterDataType registerNodesCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readRegisterNodesCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readRegisterNodesCountAsync();

    /**
     * An asynchronous implementation of {@link #writeRegisterNodesCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeRegisterNodesCountAsync(
        ServiceCounterDataType registerNodesCount);

    /**
     * Get the RegisterNodesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RegisterNodesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getRegisterNodesCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRegisterNodesCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getRegisterNodesCountNodeAsync();

    /**
     * Get the local value of the UnregisterNodesCount Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the UnregisterNodesCount Node.
     * @throws UaException if an error occurs creating or getting the UnregisterNodesCount Node.
     */
    ServiceCounterDataType getUnregisterNodesCount() throws UaException;

    /**
     * Set the local value of the UnregisterNodesCount Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param unregisterNodesCount the local value to set for the UnregisterNodesCount Node.
     * @throws UaException if an error occurs creating or getting the UnregisterNodesCount Node.
     */
    void setUnregisterNodesCount(ServiceCounterDataType unregisterNodesCount) throws UaException;

    /**
     * Read the value of the UnregisterNodesCount Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ServiceCounterDataType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ServiceCounterDataType readUnregisterNodesCount() throws UaException;

    /**
     * Write a new value for the UnregisterNodesCount Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param unregisterNodesCount the {@link ServiceCounterDataType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeUnregisterNodesCount(ServiceCounterDataType unregisterNodesCount) throws UaException;

    /**
     * An asynchronous implementation of {@link #readUnregisterNodesCount()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ServiceCounterDataType> readUnregisterNodesCountAsync();

    /**
     * An asynchronous implementation of {@link #writeUnregisterNodesCount(ServiceCounterDataType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeUnregisterNodesCountAsync(
        ServiceCounterDataType unregisterNodesCount);

    /**
     * Get the UnregisterNodesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the UnregisterNodesCount {@link BaseDataVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseDataVariableType getUnregisterNodesCountNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getUnregisterNodesCountNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends BaseDataVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends BaseDataVariableType> getUnregisterNodesCountNodeAsync();
}

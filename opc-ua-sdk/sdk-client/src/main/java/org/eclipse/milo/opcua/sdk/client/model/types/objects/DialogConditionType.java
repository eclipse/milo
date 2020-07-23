package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface DialogConditionType extends ConditionType {
    QualifiedProperty<LocalizedText> PROMPT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Prompt",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<LocalizedText[]> RESPONSE_OPTION_SET = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ResponseOptionSet",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        ValueRanks.OneDimension,
        LocalizedText[].class
    );

    QualifiedProperty<Integer> DEFAULT_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DefaultResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    QualifiedProperty<Integer> OK_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OkResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    QualifiedProperty<Integer> CANCEL_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CancelResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    QualifiedProperty<Integer> LAST_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    /**
     * Get the local value of the Prompt Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Prompt Node.
     * @throws UaException if an error occurs creating or getting the Prompt Node.
     */
    LocalizedText getPrompt() throws UaException;

    /**
     * Set the local value of the Prompt Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param prompt the local value to set for the Prompt Node.
     * @throws UaException if an error occurs creating or getting the Prompt Node.
     */
    void setPrompt(LocalizedText prompt) throws UaException;

    /**
     * Read the value of the Prompt Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readPrompt() throws UaException;

    /**
     * Write a new value for the Prompt Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param prompt the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writePrompt(LocalizedText prompt) throws UaException;

    /**
     * An asynchronous implementation of {@link #readPrompt()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readPromptAsync();

    /**
     * An asynchronous implementation of {@link #writePrompt(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writePromptAsync(LocalizedText prompt);

    /**
     * Get the Prompt {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Prompt {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getPromptNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getPromptNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getPromptNodeAsync();

    /**
     * Get the local value of the ResponseOptionSet Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ResponseOptionSet Node.
     * @throws UaException if an error occurs creating or getting the ResponseOptionSet Node.
     */
    LocalizedText[] getResponseOptionSet() throws UaException;

    /**
     * Set the local value of the ResponseOptionSet Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param responseOptionSet the local value to set for the ResponseOptionSet Node.
     * @throws UaException if an error occurs creating or getting the ResponseOptionSet Node.
     */
    void setResponseOptionSet(LocalizedText[] responseOptionSet) throws UaException;

    /**
     * Read the value of the ResponseOptionSet Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText[] readResponseOptionSet() throws UaException;

    /**
     * Write a new value for the ResponseOptionSet Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param responseOptionSet the {@link LocalizedText[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeResponseOptionSet(LocalizedText[] responseOptionSet) throws UaException;

    /**
     * An asynchronous implementation of {@link #readResponseOptionSet()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText[]> readResponseOptionSetAsync();

    /**
     * An asynchronous implementation of {@link #writeResponseOptionSet(LocalizedText[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeResponseOptionSetAsync(LocalizedText[] responseOptionSet);

    /**
     * Get the ResponseOptionSet {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ResponseOptionSet {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getResponseOptionSetNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getResponseOptionSetNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getResponseOptionSetNodeAsync();

    /**
     * Get the local value of the DefaultResponse Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DefaultResponse Node.
     * @throws UaException if an error occurs creating or getting the DefaultResponse Node.
     */
    Integer getDefaultResponse() throws UaException;

    /**
     * Set the local value of the DefaultResponse Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param defaultResponse the local value to set for the DefaultResponse Node.
     * @throws UaException if an error occurs creating or getting the DefaultResponse Node.
     */
    void setDefaultResponse(Integer defaultResponse) throws UaException;

    /**
     * Read the value of the DefaultResponse Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Integer} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Integer readDefaultResponse() throws UaException;

    /**
     * Write a new value for the DefaultResponse Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param defaultResponse the {@link Integer} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDefaultResponse(Integer defaultResponse) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDefaultResponse()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Integer> readDefaultResponseAsync();

    /**
     * An asynchronous implementation of {@link #writeDefaultResponse(Integer)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeDefaultResponseAsync(Integer defaultResponse);

    /**
     * Get the DefaultResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DefaultResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getDefaultResponseNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDefaultResponseNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getDefaultResponseNodeAsync();

    /**
     * Get the local value of the OkResponse Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the OkResponse Node.
     * @throws UaException if an error occurs creating or getting the OkResponse Node.
     */
    Integer getOkResponse() throws UaException;

    /**
     * Set the local value of the OkResponse Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param okResponse the local value to set for the OkResponse Node.
     * @throws UaException if an error occurs creating or getting the OkResponse Node.
     */
    void setOkResponse(Integer okResponse) throws UaException;

    /**
     * Read the value of the OkResponse Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Integer} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Integer readOkResponse() throws UaException;

    /**
     * Write a new value for the OkResponse Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param okResponse the {@link Integer} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeOkResponse(Integer okResponse) throws UaException;

    /**
     * An asynchronous implementation of {@link #readOkResponse()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Integer> readOkResponseAsync();

    /**
     * An asynchronous implementation of {@link #writeOkResponse(Integer)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeOkResponseAsync(Integer okResponse);

    /**
     * Get the OkResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the OkResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getOkResponseNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getOkResponseNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getOkResponseNodeAsync();

    /**
     * Get the local value of the CancelResponse Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CancelResponse Node.
     * @throws UaException if an error occurs creating or getting the CancelResponse Node.
     */
    Integer getCancelResponse() throws UaException;

    /**
     * Set the local value of the CancelResponse Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param cancelResponse the local value to set for the CancelResponse Node.
     * @throws UaException if an error occurs creating or getting the CancelResponse Node.
     */
    void setCancelResponse(Integer cancelResponse) throws UaException;

    /**
     * Read the value of the CancelResponse Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Integer} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Integer readCancelResponse() throws UaException;

    /**
     * Write a new value for the CancelResponse Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param cancelResponse the {@link Integer} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCancelResponse(Integer cancelResponse) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCancelResponse()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Integer> readCancelResponseAsync();

    /**
     * An asynchronous implementation of {@link #writeCancelResponse(Integer)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeCancelResponseAsync(Integer cancelResponse);

    /**
     * Get the CancelResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CancelResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getCancelResponseNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCancelResponseNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getCancelResponseNodeAsync();

    /**
     * Get the local value of the LastResponse Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastResponse Node.
     * @throws UaException if an error occurs creating or getting the LastResponse Node.
     */
    Integer getLastResponse() throws UaException;

    /**
     * Set the local value of the LastResponse Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastResponse the local value to set for the LastResponse Node.
     * @throws UaException if an error occurs creating or getting the LastResponse Node.
     */
    void setLastResponse(Integer lastResponse) throws UaException;

    /**
     * Read the value of the LastResponse Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Integer} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Integer readLastResponse() throws UaException;

    /**
     * Write a new value for the LastResponse Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastResponse the {@link Integer} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastResponse(Integer lastResponse) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastResponse()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Integer> readLastResponseAsync();

    /**
     * An asynchronous implementation of {@link #writeLastResponse(Integer)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeLastResponseAsync(Integer lastResponse);

    /**
     * Get the LastResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastResponse {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastResponseNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastResponseNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastResponseNodeAsync();

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
     * Get the local value of the DialogState Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the DialogState Node.
     * @throws UaException if an error occurs creating or getting the DialogState Node.
     */
    LocalizedText getDialogState() throws UaException;

    /**
     * Set the local value of the DialogState Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param dialogState the local value to set for the DialogState Node.
     * @throws UaException if an error occurs creating or getting the DialogState Node.
     */
    void setDialogState(LocalizedText dialogState) throws UaException;

    /**
     * Read the value of the DialogState Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link LocalizedText} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    LocalizedText readDialogState() throws UaException;

    /**
     * Write a new value for the DialogState Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param dialogState the {@link LocalizedText} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeDialogState(LocalizedText dialogState) throws UaException;

    /**
     * An asynchronous implementation of {@link #readDialogState()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends LocalizedText> readDialogStateAsync();

    /**
     * An asynchronous implementation of {@link #writeDialogState(LocalizedText)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeDialogStateAsync(LocalizedText dialogState);

    /**
     * Get the DialogState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the DialogState {@link TwoStateVariableType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TwoStateVariableType getDialogStateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getDialogStateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends TwoStateVariableType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TwoStateVariableType> getDialogStateNodeAsync();
}

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;

public interface ProgramDiagnosticType extends BaseDataVariableType {
    QualifiedProperty<NodeId> CREATE_SESSION_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CreateSessionId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<String> CREATE_CLIENT_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CreateClientName",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<DateTime> INVOCATION_CREATION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InvocationCreationTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> LAST_TRANSITION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastTransitionTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<String> LAST_METHOD_CALL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodCall",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<NodeId> LAST_METHOD_SESSION_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodSessionId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<Argument[]> LAST_METHOD_INPUT_ARGUMENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodInputArguments",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=296"),
        ValueRanks.OneDimension,
        Argument[].class
    );

    QualifiedProperty<Argument[]> LAST_METHOD_OUTPUT_ARGUMENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodOutputArguments",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=296"),
        ValueRanks.OneDimension,
        Argument[].class
    );

    QualifiedProperty<DateTime> LAST_METHOD_CALL_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodCallTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<StatusResult> LAST_METHOD_RETURN_STATUS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastMethodReturnStatus",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=299"),
        ValueRanks.Scalar,
        StatusResult.class
    );

    /**
     * Get the local value of the CreateSessionId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CreateSessionId Node.
     * @throws UaException if an error occurs creating or getting the CreateSessionId Node.
     */
    NodeId getCreateSessionId() throws UaException;

    /**
     * Set the local value of the CreateSessionId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param createSessionId the local value to set for the CreateSessionId Node.
     * @throws UaException if an error occurs creating or getting the CreateSessionId Node.
     */
    void setCreateSessionId(NodeId createSessionId) throws UaException;

    /**
     * Read the value of the CreateSessionId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readCreateSessionId() throws UaException;

    /**
     * Write a new value for the CreateSessionId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param createSessionId the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCreateSessionId(NodeId createSessionId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCreateSessionId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readCreateSessionIdAsync();

    /**
     * An asynchronous implementation of {@link #writeCreateSessionId(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCreateSessionIdAsync(NodeId createSessionId);

    /**
     * Get the CreateSessionId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CreateSessionId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getCreateSessionIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCreateSessionIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getCreateSessionIdNodeAsync();

    /**
     * Get the local value of the CreateClientName Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CreateClientName Node.
     * @throws UaException if an error occurs creating or getting the CreateClientName Node.
     */
    String getCreateClientName() throws UaException;

    /**
     * Set the local value of the CreateClientName Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param createClientName the local value to set for the CreateClientName Node.
     * @throws UaException if an error occurs creating or getting the CreateClientName Node.
     */
    void setCreateClientName(String createClientName) throws UaException;

    /**
     * Read the value of the CreateClientName Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readCreateClientName() throws UaException;

    /**
     * Write a new value for the CreateClientName Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param createClientName the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCreateClientName(String createClientName) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCreateClientName()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readCreateClientNameAsync();

    /**
     * An asynchronous implementation of {@link #writeCreateClientName(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCreateClientNameAsync(String createClientName);

    /**
     * Get the CreateClientName {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CreateClientName {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getCreateClientNameNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCreateClientNameNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getCreateClientNameNodeAsync();

    /**
     * Get the local value of the InvocationCreationTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the InvocationCreationTime Node.
     * @throws UaException if an error occurs creating or getting the InvocationCreationTime Node.
     */
    DateTime getInvocationCreationTime() throws UaException;

    /**
     * Set the local value of the InvocationCreationTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param invocationCreationTime the local value to set for the InvocationCreationTime Node.
     * @throws UaException if an error occurs creating or getting the InvocationCreationTime Node.
     */
    void setInvocationCreationTime(DateTime invocationCreationTime) throws UaException;

    /**
     * Read the value of the InvocationCreationTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readInvocationCreationTime() throws UaException;

    /**
     * Write a new value for the InvocationCreationTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param invocationCreationTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeInvocationCreationTime(DateTime invocationCreationTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readInvocationCreationTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readInvocationCreationTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeInvocationCreationTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeInvocationCreationTimeAsync(DateTime invocationCreationTime);

    /**
     * Get the InvocationCreationTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the InvocationCreationTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getInvocationCreationTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getInvocationCreationTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getInvocationCreationTimeNodeAsync();

    /**
     * Get the local value of the LastTransitionTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastTransitionTime Node.
     * @throws UaException if an error occurs creating or getting the LastTransitionTime Node.
     */
    DateTime getLastTransitionTime() throws UaException;

    /**
     * Set the local value of the LastTransitionTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastTransitionTime the local value to set for the LastTransitionTime Node.
     * @throws UaException if an error occurs creating or getting the LastTransitionTime Node.
     */
    void setLastTransitionTime(DateTime lastTransitionTime) throws UaException;

    /**
     * Read the value of the LastTransitionTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readLastTransitionTime() throws UaException;

    /**
     * Write a new value for the LastTransitionTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastTransitionTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastTransitionTime(DateTime lastTransitionTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastTransitionTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readLastTransitionTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeLastTransitionTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLastTransitionTimeAsync(DateTime lastTransitionTime);

    /**
     * Get the LastTransitionTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastTransitionTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastTransitionTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastTransitionTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastTransitionTimeNodeAsync();

    /**
     * Get the local value of the LastMethodCall Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastMethodCall Node.
     * @throws UaException if an error occurs creating or getting the LastMethodCall Node.
     */
    String getLastMethodCall() throws UaException;

    /**
     * Set the local value of the LastMethodCall Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastMethodCall the local value to set for the LastMethodCall Node.
     * @throws UaException if an error occurs creating or getting the LastMethodCall Node.
     */
    void setLastMethodCall(String lastMethodCall) throws UaException;

    /**
     * Read the value of the LastMethodCall Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link String} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    String readLastMethodCall() throws UaException;

    /**
     * Write a new value for the LastMethodCall Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastMethodCall the {@link String} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastMethodCall(String lastMethodCall) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastMethodCall()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends String> readLastMethodCallAsync();

    /**
     * An asynchronous implementation of {@link #writeLastMethodCall(String)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLastMethodCallAsync(String lastMethodCall);

    /**
     * Get the LastMethodCall {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastMethodCall {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastMethodCallNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastMethodCallNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastMethodCallNodeAsync();

    /**
     * Get the local value of the LastMethodSessionId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastMethodSessionId Node.
     * @throws UaException if an error occurs creating or getting the LastMethodSessionId Node.
     */
    NodeId getLastMethodSessionId() throws UaException;

    /**
     * Set the local value of the LastMethodSessionId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastMethodSessionId the local value to set for the LastMethodSessionId Node.
     * @throws UaException if an error occurs creating or getting the LastMethodSessionId Node.
     */
    void setLastMethodSessionId(NodeId lastMethodSessionId) throws UaException;

    /**
     * Read the value of the LastMethodSessionId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readLastMethodSessionId() throws UaException;

    /**
     * Write a new value for the LastMethodSessionId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastMethodSessionId the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastMethodSessionId(NodeId lastMethodSessionId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastMethodSessionId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readLastMethodSessionIdAsync();

    /**
     * An asynchronous implementation of {@link #writeLastMethodSessionId(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLastMethodSessionIdAsync(NodeId lastMethodSessionId);

    /**
     * Get the LastMethodSessionId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastMethodSessionId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastMethodSessionIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastMethodSessionIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastMethodSessionIdNodeAsync();

    /**
     * Get the local value of the LastMethodInputArguments Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastMethodInputArguments Node.
     * @throws UaException if an error occurs creating or getting the LastMethodInputArguments Node.
     */
    Argument[] getLastMethodInputArguments() throws UaException;

    /**
     * Set the local value of the LastMethodInputArguments Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastMethodInputArguments the local value to set for the LastMethodInputArguments Node.
     * @throws UaException if an error occurs creating or getting the LastMethodInputArguments Node.
     */
    void setLastMethodInputArguments(Argument[] lastMethodInputArguments) throws UaException;

    /**
     * Read the value of the LastMethodInputArguments Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Argument[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Argument[] readLastMethodInputArguments() throws UaException;

    /**
     * Write a new value for the LastMethodInputArguments Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastMethodInputArguments the {@link Argument[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastMethodInputArguments(Argument[] lastMethodInputArguments) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastMethodInputArguments()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Argument[]> readLastMethodInputArgumentsAsync();

    /**
     * An asynchronous implementation of {@link #writeLastMethodInputArguments(Argument[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLastMethodInputArgumentsAsync(
        Argument[] lastMethodInputArguments);

    /**
     * Get the LastMethodInputArguments {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastMethodInputArguments {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastMethodInputArgumentsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastMethodInputArgumentsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastMethodInputArgumentsNodeAsync();

    /**
     * Get the local value of the LastMethodOutputArguments Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastMethodOutputArguments Node.
     * @throws UaException if an error occurs creating or getting the LastMethodOutputArguments Node.
     */
    Argument[] getLastMethodOutputArguments() throws UaException;

    /**
     * Set the local value of the LastMethodOutputArguments Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastMethodOutputArguments the local value to set for the LastMethodOutputArguments Node.
     * @throws UaException if an error occurs creating or getting the LastMethodOutputArguments Node.
     */
    void setLastMethodOutputArguments(Argument[] lastMethodOutputArguments) throws UaException;

    /**
     * Read the value of the LastMethodOutputArguments Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Argument[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Argument[] readLastMethodOutputArguments() throws UaException;

    /**
     * Write a new value for the LastMethodOutputArguments Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastMethodOutputArguments the {@link Argument[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastMethodOutputArguments(Argument[] lastMethodOutputArguments) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastMethodOutputArguments()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Argument[]> readLastMethodOutputArgumentsAsync();

    /**
     * An asynchronous implementation of {@link #writeLastMethodOutputArguments(Argument[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLastMethodOutputArgumentsAsync(
        Argument[] lastMethodOutputArguments);

    /**
     * Get the LastMethodOutputArguments {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastMethodOutputArguments {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastMethodOutputArgumentsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastMethodOutputArgumentsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastMethodOutputArgumentsNodeAsync();

    /**
     * Get the local value of the LastMethodCallTime Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastMethodCallTime Node.
     * @throws UaException if an error occurs creating or getting the LastMethodCallTime Node.
     */
    DateTime getLastMethodCallTime() throws UaException;

    /**
     * Set the local value of the LastMethodCallTime Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastMethodCallTime the local value to set for the LastMethodCallTime Node.
     * @throws UaException if an error occurs creating or getting the LastMethodCallTime Node.
     */
    void setLastMethodCallTime(DateTime lastMethodCallTime) throws UaException;

    /**
     * Read the value of the LastMethodCallTime Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link DateTime} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    DateTime readLastMethodCallTime() throws UaException;

    /**
     * Write a new value for the LastMethodCallTime Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastMethodCallTime the {@link DateTime} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastMethodCallTime(DateTime lastMethodCallTime) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastMethodCallTime()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends DateTime> readLastMethodCallTimeAsync();

    /**
     * An asynchronous implementation of {@link #writeLastMethodCallTime(DateTime)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLastMethodCallTimeAsync(DateTime lastMethodCallTime);

    /**
     * Get the LastMethodCallTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastMethodCallTime {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastMethodCallTimeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastMethodCallTimeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastMethodCallTimeNodeAsync();

    /**
     * Get the local value of the LastMethodReturnStatus Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the LastMethodReturnStatus Node.
     * @throws UaException if an error occurs creating or getting the LastMethodReturnStatus Node.
     */
    StatusResult getLastMethodReturnStatus() throws UaException;

    /**
     * Set the local value of the LastMethodReturnStatus Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param lastMethodReturnStatus the local value to set for the LastMethodReturnStatus Node.
     * @throws UaException if an error occurs creating or getting the LastMethodReturnStatus Node.
     */
    void setLastMethodReturnStatus(StatusResult lastMethodReturnStatus) throws UaException;

    /**
     * Read the value of the LastMethodReturnStatus Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link StatusResult} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    StatusResult readLastMethodReturnStatus() throws UaException;

    /**
     * Write a new value for the LastMethodReturnStatus Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param lastMethodReturnStatus the {@link StatusResult} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeLastMethodReturnStatus(StatusResult lastMethodReturnStatus) throws UaException;

    /**
     * An asynchronous implementation of {@link #readLastMethodReturnStatus()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends StatusResult> readLastMethodReturnStatusAsync();

    /**
     * An asynchronous implementation of {@link #writeLastMethodReturnStatus(StatusResult)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeLastMethodReturnStatusAsync(
        StatusResult lastMethodReturnStatus);

    /**
     * Get the LastMethodReturnStatus {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LastMethodReturnStatus {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getLastMethodReturnStatusNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLastMethodReturnStatusNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getLastMethodReturnStatusNodeAsync();
}

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditUpdateMethodEventType extends AuditEventType {
    QualifiedProperty<NodeId> METHOD_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MethodId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<Object[]> INPUT_ARGUMENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InputArguments",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        ValueRanks.OneDimension,
        Object[].class
    );

    /**
     * Get the local value of the MethodId Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the MethodId Node.
     * @throws UaException if an error occurs creating or getting the MethodId Node.
     */
    NodeId getMethodId() throws UaException;

    /**
     * Set the local value of the MethodId Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param methodId the local value to set for the MethodId Node.
     * @throws UaException if an error occurs creating or getting the MethodId Node.
     */
    void setMethodId(NodeId methodId) throws UaException;

    /**
     * Read the value of the MethodId Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readMethodId() throws UaException;

    /**
     * Write a new value for the MethodId Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param methodId the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeMethodId(NodeId methodId) throws UaException;

    /**
     * An asynchronous implementation of {@link #readMethodId()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readMethodIdAsync();

    /**
     * An asynchronous implementation of {@link #writeMethodId(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeMethodIdAsync(NodeId methodId);

    /**
     * Get the MethodId {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the MethodId {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getMethodIdNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getMethodIdNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getMethodIdNodeAsync();

    /**
     * Get the local value of the InputArguments Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the InputArguments Node.
     * @throws UaException if an error occurs creating or getting the InputArguments Node.
     */
    Object[] getInputArguments() throws UaException;

    /**
     * Set the local value of the InputArguments Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param inputArguments the local value to set for the InputArguments Node.
     * @throws UaException if an error occurs creating or getting the InputArguments Node.
     */
    void setInputArguments(Object[] inputArguments) throws UaException;

    /**
     * Read the value of the InputArguments Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Object[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Object[] readInputArguments() throws UaException;

    /**
     * Write a new value for the InputArguments Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param inputArguments the {@link Object[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeInputArguments(Object[] inputArguments) throws UaException;

    /**
     * An asynchronous implementation of {@link #readInputArguments()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends Object[]> readInputArgumentsAsync();

    /**
     * An asynchronous implementation of {@link #writeInputArguments(Object[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeInputArgumentsAsync(Object[] inputArguments);

    /**
     * Get the InputArguments {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the InputArguments {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getInputArgumentsNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getInputArgumentsNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getInputArgumentsNodeAsync();
}

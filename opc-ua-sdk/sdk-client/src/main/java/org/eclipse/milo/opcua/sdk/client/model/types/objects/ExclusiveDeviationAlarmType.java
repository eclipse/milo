package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface ExclusiveDeviationAlarmType extends ExclusiveLimitAlarmType {
    QualifiedProperty<NodeId> SETPOINT_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SetpointNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    /**
     * Get the local value of the SetpointNode Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the SetpointNode Node.
     * @throws UaException if an error occurs creating or getting the SetpointNode Node.
     */
    NodeId getSetpointNode() throws UaException;

    /**
     * Set the local value of the SetpointNode Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param setpointNode the local value to set for the SetpointNode Node.
     * @throws UaException if an error occurs creating or getting the SetpointNode Node.
     */
    void setSetpointNode(NodeId setpointNode) throws UaException;

    /**
     * Read the value of the SetpointNode Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId readSetpointNode() throws UaException;

    /**
     * Write a new value for the SetpointNode Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param setpointNode the {@link NodeId} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeSetpointNode(NodeId setpointNode) throws UaException;

    /**
     * An asynchronous implementation of {@link #readSetpointNode()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId> readSetpointNodeAsync();

    /**
     * An asynchronous implementation of {@link #writeSetpointNode(NodeId)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeSetpointNodeAsync(NodeId setpointNode);

    /**
     * Get the SetpointNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the SetpointNode {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getSetpointNodeNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getSetpointNodeNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getSetpointNodeNodeAsync();
}

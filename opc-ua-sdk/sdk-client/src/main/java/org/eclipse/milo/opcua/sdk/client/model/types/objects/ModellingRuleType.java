package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NamingRuleType;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface ModellingRuleType extends BaseObjectType {
    QualifiedProperty<NamingRuleType> NAMING_RULE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamingRule",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=120"),
        ValueRanks.Scalar,
        NamingRuleType.class
    );

    /**
     * Get the local value of the NamingRule Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the NamingRule Node.
     * @throws UaException if an error occurs creating or getting the NamingRule Node.
     */
    NamingRuleType getNamingRule() throws UaException;

    /**
     * Set the local value of the NamingRule Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param namingRule the local value to set for the NamingRule Node.
     * @throws UaException if an error occurs creating or getting the NamingRule Node.
     */
    void setNamingRule(NamingRuleType namingRule) throws UaException;

    /**
     * Read the value of the NamingRule Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NamingRuleType} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NamingRuleType readNamingRule() throws UaException;

    /**
     * Write a new value for the NamingRule Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param namingRule the {@link NamingRuleType} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeNamingRule(NamingRuleType namingRule) throws UaException;

    /**
     * An asynchronous implementation of {@link #readNamingRule()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NamingRuleType> readNamingRuleAsync();

    /**
     * An asynchronous implementation of {@link #writeNamingRule(NamingRuleType)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeNamingRuleAsync(NamingRuleType namingRule);

    /**
     * Get the NamingRule {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the NamingRule {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getNamingRuleNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getNamingRuleNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getNamingRuleNodeAsync();
}

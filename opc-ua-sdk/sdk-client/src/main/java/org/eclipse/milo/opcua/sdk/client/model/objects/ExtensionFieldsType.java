package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.2.2</a>
 */
public interface ExtensionFieldsType extends BaseObjectType {
    QualifiedProperty<Object> EXTENSION_FIELD_NAME_PLACEHOLDER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "<ExtensionFieldName>",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        -1,
        Object.class
    );

    /**
     * Get the local value of the ExtensionFieldName_Placeholder Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the ExtensionFieldName_Placeholder Node.
     * @throws UaException if an error occurs creating or getting the ExtensionFieldName_Placeholder Node.
     */
    Object getExtensionFieldNamePlaceholder() throws UaException;

    /**
     * Set the local value of the ExtensionFieldName_Placeholder Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the ExtensionFieldName_Placeholder Node.
     * @throws UaException if an error occurs creating or getting the ExtensionFieldName_Placeholder Node.
     */
    void setExtensionFieldNamePlaceholder(Object value) throws UaException;

    /**
     * Read the value of the ExtensionFieldName_Placeholder Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link Object} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Object readExtensionFieldNamePlaceholder() throws UaException;

    /**
     * Write a new value for the ExtensionFieldName_Placeholder Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link Object} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeExtensionFieldNamePlaceholder(Object value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readExtensionFieldNamePlaceholder}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<?> readExtensionFieldNamePlaceholderAsync();

    /**
     * An asynchronous implementation of {@link #writeExtensionFieldNamePlaceholder}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeExtensionFieldNamePlaceholderAsync(Object value);

    /**
     * Get the ExtensionFieldName_Placeholder {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the ExtensionFieldName_Placeholder {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getExtensionFieldNamePlaceholderNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getExtensionFieldName_PlaceholderNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getExtensionFieldNamePlaceholderNodeAsync();
}

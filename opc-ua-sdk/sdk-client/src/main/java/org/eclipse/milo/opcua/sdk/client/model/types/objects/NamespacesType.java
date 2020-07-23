package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.UaException;

public interface NamespacesType extends BaseObjectType {
    /**
     * Get the AddressSpaceFile {@link AddressSpaceFileType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the AddressSpaceFile {@link AddressSpaceFileType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    AddressSpaceFileType getAddressSpaceFileNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getAddressSpaceFileNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * AddressSpaceFileType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends AddressSpaceFileType> getAddressSpaceFileNodeAsync();
}

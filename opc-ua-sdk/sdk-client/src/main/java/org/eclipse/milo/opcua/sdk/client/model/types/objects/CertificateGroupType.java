package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface CertificateGroupType extends BaseObjectType {
    QualifiedProperty<NodeId[]> CERTIFICATE_TYPES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateTypes",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        ValueRanks.OneDimension,
        NodeId[].class
    );

    /**
     * Get the local value of the CertificateTypes Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the CertificateTypes Node.
     * @throws UaException if an error occurs creating or getting the CertificateTypes Node.
     */
    NodeId[] getCertificateTypes() throws UaException;

    /**
     * Set the local value of the CertificateTypes Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param certificateTypes the local value to set for the CertificateTypes Node.
     * @throws UaException if an error occurs creating or getting the CertificateTypes Node.
     */
    void setCertificateTypes(NodeId[] certificateTypes) throws UaException;

    /**
     * Read the value of the CertificateTypes Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link NodeId[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    NodeId[] readCertificateTypes() throws UaException;

    /**
     * Write a new value for the CertificateTypes Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param certificateTypes the {@link NodeId[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCertificateTypes(NodeId[] certificateTypes) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCertificateTypes()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends NodeId[]> readCertificateTypesAsync();

    /**
     * An asynchronous implementation of {@link #writeCertificateTypes(NodeId[])}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeCertificateTypesAsync(NodeId[] certificateTypes);

    /**
     * Get the CertificateTypes {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the CertificateTypes {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getCertificateTypesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCertificateTypesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getCertificateTypesNodeAsync();

    /**
     * Get the TrustList {@link TrustListType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the TrustList {@link TrustListType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    TrustListType getTrustListNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getTrustListNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * TrustListType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends TrustListType> getTrustListNodeAsync();
}

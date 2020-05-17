package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public interface AuditCertificateEventType extends AuditSecurityEventType {
    QualifiedProperty<ByteString> CERTIFICATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Certificate",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    /**
     * Get the local value of the Certificate Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Certificate Node.
     * @throws UaException if an error occurs creating or getting the Certificate Node.
     */
    ByteString getCertificate() throws UaException;

    /**
     * Set the local value of the Certificate Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param certificate the local value to set for the Certificate Node.
     * @throws UaException if an error occurs creating or getting the Certificate Node.
     */
    void setCertificate(ByteString certificate) throws UaException;

    /**
     * Read the value of the Certificate Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link ByteString} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ByteString readCertificate() throws UaException;

    /**
     * Write a new value for the Certificate Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param certificate the {@link ByteString} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeCertificate(ByteString certificate) throws UaException;

    /**
     * An asynchronous implementation of {@link #readCertificate()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ByteString> readCertificateAsync();

    /**
     * An asynchronous implementation of {@link #writeCertificate(ByteString)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<Unit> writeCertificateAsync(ByteString certificate);

    /**
     * Get the Certificate {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Certificate {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getCertificateNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCertificateNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getCertificateNodeAsync();
}

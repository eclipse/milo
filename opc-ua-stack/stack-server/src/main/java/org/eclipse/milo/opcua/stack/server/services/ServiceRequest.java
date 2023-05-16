/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.services;

import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;

import com.google.common.base.MoreObjects;
import io.netty.util.DefaultAttributeMap;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.jetbrains.annotations.Nullable;

public class ServiceRequest extends DefaultAttributeMap {

    private final CompletableFuture<UaResponseMessage> future = new CompletableFuture<>();

    private final long receivedAtNanos = System.nanoTime();

    private final UaStackServer server;
    private final UaRequestMessage request;
    private final EndpointDescription endpoint;
    private final long secureChannelId;
    private final InetAddress clientAddress;
    private final ByteString clientCertificateBytes;

    public ServiceRequest(
        UaStackServer server,
        UaRequestMessage request,
        EndpointDescription endpoint,
        long secureChannelId,
        InetAddress clientAddress,
        @Nullable ByteString clientCertificateBytes) {

        this.server = server;
        this.request = request;
        this.endpoint = endpoint;
        this.secureChannelId = secureChannelId;
        this.clientAddress = clientAddress;
        this.clientCertificateBytes = clientCertificateBytes;

        future.whenComplete(this::updateDiagnosticCounters);
    }

    public UaStackServer getServer() {
        return server;
    }

    public EndpointDescription getEndpoint() {
        return endpoint;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    /**
     * Get the client certificate bytes, or {@code null} if not available. If the client provided
     * a certificate chain when creating the secure channel these bytes contain all certificates
     * in the chain. It is the same bytes as
     * {@link ServerSecureChannel#getRemoteCertificateChainBytes()}.
     * <p>
     * Only available on SecureConversation-based transports with security enabled.
     *
     * @return the client certificate bytes, or {@code null} if not available.
     */
    @Nullable
    public ByteString getClientCertificateBytes() {
        return clientCertificateBytes;
    }

    public long getSecureChannelId() {
        return secureChannelId;
    }

    public UaRequestMessage getRequest() {
        return request;
    }

    public CompletableFuture<UaResponseMessage> getFuture() {
        return future;
    }

    public long getReceivedAtNanos() {
        return receivedAtNanos;
    }

    public void setResponse(UaResponseMessage response) {
        future.complete(response);
    }

    public void setServiceFault(UaException exception) {
        future.completeExceptionally(exception);
    }

    public void setServiceFault(long statusCode) {
        setServiceFault(new StatusCode(statusCode));
    }

    public void setServiceFault(StatusCode statusCode) {
        future.completeExceptionally(new UaException(statusCode, "ServiceFault"));
    }

    public ResponseHeader createResponseHeader() {
        return createResponseHeader(StatusCode.GOOD);
    }

    public ResponseHeader createResponseHeader(long statusCode) {
        return createResponseHeader(new StatusCode(statusCode));
    }

    public ResponseHeader createResponseHeader(StatusCode serviceResult) {
        return new ResponseHeader(
            DateTime.now(),
            request.getRequestHeader().getRequestHandle(),
            serviceResult,
            null, null, null
        );
    }

    public ServiceFault createServiceFault(long statusCode) {
        ResponseHeader responseHeader = new ResponseHeader(
            DateTime.now(),
            request.getRequestHeader().getRequestHandle(),
            new StatusCode(statusCode),
            null, null, null
        );

        return new ServiceFault(responseHeader);
    }

    public ServiceFault createServiceFault(Throwable throwable) {
        UaException exception = (throwable instanceof UaException) ?
            (UaException) throwable : new UaException(throwable);

        ResponseHeader responseHeader = new ResponseHeader(
            DateTime.now(),
            request.getRequestHeader().getRequestHandle(),
            exception.getStatusCode(),
            null, null, null
        );

        return new ServiceFault(responseHeader);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("request", request.getClass().getSimpleName())
            .toString();
    }

    private void updateDiagnosticCounters(
        @SuppressWarnings("unused") @Nullable UaResponseMessage r,
        @Nullable Throwable ex
    ) {

        if (ex != null) {
            StatusCode statusCode = UaException.extractStatusCode(ex)
                .orElse(new StatusCode(StatusCodes.Bad_InternalError));

            if (statusCode.isSecurityError()) {
                server.getSecurityRejectedRequestCount().increment();
            }

            server.getRejectedRequestCount().increment();
        }
    }

}

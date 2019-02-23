/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.services;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import io.netty.util.DefaultAttributeMap;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.server.UaStackServer;

public class ServiceRequest extends DefaultAttributeMap {

    private final CompletableFuture<UaResponseMessage> future = new CompletableFuture<>();

    private final UaStackServer server;
    private final UaRequestMessage request;
    private final EndpointDescription endpoint;
    private final long secureChannelId;
    private final ByteString clientCertificateBytes;

    public ServiceRequest(
        UaStackServer server,
        UaRequestMessage request,
        EndpointDescription endpoint,
        long secureChannelId,
        @Nullable ByteString clientCertificateBytes) {

        this.server = server;
        this.request = request;
        this.endpoint = endpoint;
        this.secureChannelId = secureChannelId;
        this.clientCertificateBytes = clientCertificateBytes;
    }

    public UaStackServer getServer() {
        return server;
    }

    public EndpointDescription getEndpoint() {
        return endpoint;
    }

    /**
     * Get the client certificate bytes, or {@code null} if not available.
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

}

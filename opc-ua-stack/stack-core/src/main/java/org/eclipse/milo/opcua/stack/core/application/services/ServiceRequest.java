/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.application.services;

import java.util.concurrent.CompletableFuture;

import com.google.common.base.MoreObjects;
import io.netty.util.DefaultAttributeMap;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.UaStackServer;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;

public class ServiceRequest<ReqT extends UaRequestMessage, ResT extends UaResponseMessage> extends DefaultAttributeMap {

    private final CompletableFuture<ResT> future = new CompletableFuture<>();

    private final ReqT request;
    private final long requestId;
    private final UaStackServer server;
    private final ServerSecureChannel secureChannel;

    public ServiceRequest(ReqT request,
                          long requestId,
                          UaStackServer server,
                          ServerSecureChannel secureChannel) {

        this.request = request;
        this.requestId = requestId;
        this.server = server;
        this.secureChannel = secureChannel;
    }

    public CompletableFuture<ResT> getFuture() {
        return future;
    }

    public ReqT getRequest() {
        return request;
    }

    public long getRequestId() {
        return requestId;
    }

    public UaStackServer getServer() {
        return server;
    }

    public ServerSecureChannel getSecureChannel() {
        return secureChannel;
    }

    public void setResponse(ResT response) {
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
            .add("requestId", requestId)
            .add("request", request.getClass().getSimpleName())
            .toString();
    }

}

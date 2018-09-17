/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server.services;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;

public abstract class ServiceRequest2 {

    private final CompletableFuture<UaResponseMessage> future = new CompletableFuture<>();

    private final UaRequestMessage requestMessage;
    private final ServerSecureChannel secureChannel;

    public ServiceRequest2(UaRequestMessage requestMessage, ServerSecureChannel secureChannel) {
        this.requestMessage = requestMessage;
        this.secureChannel = secureChannel;
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
            requestMessage.getRequestHeader().getRequestHandle(),
            serviceResult,
            null,
            null,
            null
        );
    }

    public void setResponseMessage(UaResponseMessage response) {
        future.complete(response);
    }

    public void setServiceFault(UaException fault) {
        future.completeExceptionally(fault);
    }

    public void setServiceFault(long statusCode) {
        setServiceFault(new StatusCode(statusCode));
    }

    public void setServiceFault(StatusCode statusCode) {
        setServiceFault(new UaException(statusCode));
    }

}

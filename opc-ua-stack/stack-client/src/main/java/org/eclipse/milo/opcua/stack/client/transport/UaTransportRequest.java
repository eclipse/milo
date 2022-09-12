/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.client.transport;

import java.util.concurrent.CompletableFuture;

import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessageType;
import org.jetbrains.annotations.Nullable;

public class UaTransportRequest {

    private volatile Timeout timeout;

    private final UaRequestMessageType request;
    private final CompletableFuture<UaResponseMessageType> future;

    public UaTransportRequest(UaRequestMessageType request) {
        this(request, new CompletableFuture<>());
    }

    public UaTransportRequest(UaRequestMessageType request, CompletableFuture<UaResponseMessageType> future) {
        this.request = request;
        this.future = future;
    }

    public UaRequestMessageType getRequest() {
        return request;
    }

    public CompletableFuture<UaResponseMessageType> getFuture() {
        return future;
    }

    public synchronized void setTimeout(Timeout timeout) {
        this.timeout = timeout;
    }

    @Nullable
    public synchronized Timeout getTimeout() {
        return timeout;
    }

}

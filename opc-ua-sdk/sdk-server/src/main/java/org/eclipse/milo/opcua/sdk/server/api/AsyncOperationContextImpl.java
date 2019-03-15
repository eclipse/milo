/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AsyncOperationContextImpl<T> implements AsyncOperationContext<T> {

    private final CompletableFuture<T> future = new CompletableFuture<>();

    private final OpcUaServer server;

    public AsyncOperationContextImpl(OpcUaServer server) {
        this.server = server;
    }

    public CompletableFuture<T> getFuture() {
        return future;
    }

    @Override
    public OpcUaServer getServer() {
        return server;
    }

    @Override
    public void success(T result) {
        future.complete(result);
    }

    @Override
    public void failure(UaException failure) {
        future.completeExceptionally(failure);
    }

    @Override
    public void failure(StatusCode statusCode) {
        failure(new UaException(statusCode));
    }

}

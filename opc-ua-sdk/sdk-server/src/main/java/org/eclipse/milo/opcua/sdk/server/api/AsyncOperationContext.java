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

public class AsyncOperationContext<R> {

    private final CompletableFuture<R> future = new CompletableFuture<>();

    private final OpcUaServer server;

    public AsyncOperationContext(OpcUaServer server) {
        this.server = server;
    }

    public CompletableFuture<R> getFuture() {
        return future;
    }

    public OpcUaServer getServer() {
        return server;
    }

    public void success(R result) {
        future.complete(result);
    }

    public void failure(UaException failure) {
        future.completeExceptionally(failure);
    }

    public void failure(StatusCode statusCode) {
        failure(new UaException(statusCode));
    }

    public void failure(long statusCode) {
        failure(new StatusCode(statusCode));
    }

}

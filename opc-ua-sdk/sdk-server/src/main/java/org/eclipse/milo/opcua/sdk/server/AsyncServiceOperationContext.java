/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.jetbrains.annotations.Nullable;

public class AsyncServiceOperationContext<T, R> extends ServiceOperationContext<T> {

    private final CompletableFuture<R> future = new CompletableFuture<>();

    private final OpcUaServer server;

    public AsyncServiceOperationContext(OpcUaServer server, @Nullable Session session) {
        super(server, session);

        this.server = server;
    }

    public AsyncServiceOperationContext(
        OpcUaServer server,
        @Nullable Session session,
        DiagnosticsContext<T> diagnosticsContext,
        @Nullable String auditEntryId,
        UInteger timeoutHint,
        ExtensionObject additionalHeader
    ) {

        super(server, session, diagnosticsContext, auditEntryId, timeoutHint, additionalHeader);

        this.server = server;
    }

    /**
     * Get the {@link OpcUaServer} instance this operation is taking place in.
     *
     * @return the {@link OpcUaServer} instance this operation is taking place in.
     */
    public OpcUaServer getServer() {
        return server;
    }

    /**
     * Get the {@link CompletableFuture} backing this operation.
     * <p>
     * Do not use this future to complete the operation; use {@link #success(Object)}} or {@link #failure(UaException)}
     * overrides instead.
     *
     * @return the {@link CompletableFuture} backing this operation.
     */
    public CompletableFuture<R> getFuture() {
        return future;
    }

    /**
     * Complete this operation successfully with {@code result}.
     *
     * @param result the operation result.
     */
    public void success(R result) {
        future.complete(result);
    }

    /**
     * Fail this operation with {@code failure}.
     *
     * @param failure a {@link UaException} representing the failure.
     */
    public void failure(UaException failure) {
        future.completeExceptionally(failure);
    }

    /**
     * Fail this operation with {@code statusCode}.
     *
     * @param statusCode the {@link StatusCode} representing the failure.
     */
    public void failure(StatusCode statusCode) {
        failure(new UaException(statusCode));
    }

    /**
     * Fail this operation with {@code statusCode}.
     *
     * @param statusCode the status code representing the failure.
     */
    public void failure(long statusCode) {
        failure(new StatusCode(statusCode));
    }

}

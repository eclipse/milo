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

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;

public class OperationContext<T, U> implements AccessContext {

    private final CompletableFuture<List<U>> future;

    private final OpcUaServer server;
    private final Session session;
    private final DiagnosticsContext<T> diagnostics;

    public OperationContext(OpcUaServer server,
                            @Nullable Session session,
                            DiagnosticsContext<T> diagnostics) {

        this(server, session, new CompletableFuture<List<U>>(), diagnostics);
    }

    public OperationContext(OpcUaServer server,
                            @Nullable Session session,
                            CompletableFuture<List<U>> future,
                            DiagnosticsContext<T> diagnosticsContext) {

        this.server = server;
        this.session = session;
        this.future = future;
        this.diagnostics = diagnosticsContext;
    }

    public void complete(List<U> value) {
        future.complete(value);
    }

    public DiagnosticsContext<T> getDiagnostics() {
        return diagnostics;
    }

    public OpcUaServer getServer() {
        return server;
    }

    @Override
    public Optional<Session> getSession() {
        return Optional.ofNullable(session);
    }

}

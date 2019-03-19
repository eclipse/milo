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

import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;

public class ServiceOperationContext<T, R> extends AsyncOperationContext<List<R>> implements AccessContext {

    private final Session session;
    private final DiagnosticsContext<T> diagnosticsContext;

    public ServiceOperationContext(
        OpcUaServer server,
        @Nullable Session session
    ) {

        this(server, session, new DiagnosticsContext<>());
    }

    public ServiceOperationContext(
        OpcUaServer server,
        @Nullable Session session,
        DiagnosticsContext<T> diagnosticsContext
    ) {

        super(server);

        this.session = session;
        this.diagnosticsContext = diagnosticsContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Session> getSession() {
        return Optional.ofNullable(session);
    }

    public DiagnosticsContext<T> getDiagnosticsContext() {
        return diagnosticsContext;
    }

}

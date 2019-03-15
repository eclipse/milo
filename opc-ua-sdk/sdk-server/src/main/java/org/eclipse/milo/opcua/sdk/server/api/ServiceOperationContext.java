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

public class ServiceOperationContext<T> extends AsyncOperationContextImpl<List<T>> implements AccessContext {

    private final Session session;
    private final DiagnosticsContext<?> diagnosticsContext;

    public ServiceOperationContext(
        OpcUaServer server,
        @Nullable Session session,
        DiagnosticsContext<?> diagnosticsContext) {

        super(server);

        this.session = session;
        this.diagnosticsContext = diagnosticsContext;
    }

    @Override
    public Optional<Session> getSession() {
        return Optional.ofNullable(session);
    }

    public DiagnosticsContext<?> getDiagnosticsContext() {
        return diagnosticsContext;
    }

}

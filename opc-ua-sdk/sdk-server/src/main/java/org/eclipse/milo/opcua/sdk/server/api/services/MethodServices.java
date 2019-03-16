/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.OperationContext;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;

public interface MethodServices {

    /**
     * Invoke one or more methods belonging to this {@link MethodServices}.
     *
     * @param context  the {@link CallContext}.
     * @param requests The {@link CallMethodRequest}s for the methods to invoke.
     */
    void call(CallContext context, List<CallMethodRequest> requests);

    final class CallContext extends OperationContext<CallMethodRequest, CallMethodResult> {
        public CallContext(OpcUaServer server,
                           @Nullable Session session,
                           DiagnosticsContext<CallMethodRequest> diagnosticsContext) {

            super(server, session, diagnosticsContext);
        }

        public CallContext(OpcUaServer server,
                           @Nullable Session session,
                           CompletableFuture<List<CallMethodResult>> future,
                           DiagnosticsContext<CallMethodRequest> diagnosticsContext) {

            super(server, session, future, diagnosticsContext);
        }
    }

}

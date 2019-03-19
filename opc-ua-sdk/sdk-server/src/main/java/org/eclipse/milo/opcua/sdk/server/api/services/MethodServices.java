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
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.ServiceOperationContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;

import static java.util.Collections.nCopies;

public interface MethodServices {

    /**
     * Invoke one or more methods belonging to this {@link MethodServices}.
     *
     * @param context  the {@link CallContext}.
     * @param requests The {@link CallMethodRequest}s for the methods to invoke.
     */
    default void call(CallContext context, List<CallMethodRequest> requests) {
        CallMethodResult result = new CallMethodResult(
            new StatusCode(StatusCodes.Bad_NotImplemented),
            new StatusCode[0],
            new DiagnosticInfo[0],
            new Variant[0]
        );

        context.success(nCopies(requests.size(), result));
    }

    final class CallContext extends ServiceOperationContext<CallMethodRequest, CallMethodResult> {

        public CallContext(OpcUaServer server, @Nullable Session session) {
            super(server, session);
        }

        public CallContext(
            OpcUaServer server,
            @Nullable Session session,
            DiagnosticsContext<CallMethodRequest> diagnosticsContext
        ) {

            super(server, session, diagnosticsContext);
        }

    }

}

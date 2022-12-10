/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.servicesets.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.AddressSpace.CallContext;
import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.MethodServiceSet;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

import static org.eclipse.milo.opcua.sdk.server.servicesets.AbstractServiceSet.createResponseHeader;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class DefaultMethodServiceSet implements MethodServiceSet {

    private final OpcUaServer server;

    public DefaultMethodServiceSet(OpcUaServer server) {
        this.server = server;
    }

    @Override
    public CompletableFuture<CallResponse> onCall(ServiceRequestContext context, CallRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        List<CallMethodRequest> methodsToCall = List.of(request.getMethodsToCall());

        if (methodsToCall.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (methodsToCall.size() > server.getConfig().getLimits().getMaxNodesPerMethodCall().longValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var diagnosticsContext = new DiagnosticsContext<CallMethodRequest>();

        var callContext = new CallContext(
            server,
            session,
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        session.getSessionDiagnostics().getCallCount().record(callContext.getFuture());
        session.getSessionDiagnostics().getTotalRequestCount().record(callContext.getFuture());

        server.getAddressSpaceManager().call(callContext, methodsToCall);

        return callContext.getFuture().thenApply(values -> {
            ResponseHeader header = createResponseHeader(request);

            return new CallResponse(header, values.toArray(CallMethodResult[]::new), new DiagnosticInfo[0]);
        });
    }

}

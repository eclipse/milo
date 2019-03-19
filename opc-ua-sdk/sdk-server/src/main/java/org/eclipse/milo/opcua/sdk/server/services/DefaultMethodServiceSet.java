/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services;

import java.util.List;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.services.MethodServices.CallContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.server.services.MethodServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class DefaultMethodServiceSet implements MethodServiceSet {

    private final ServiceCounter callCounter = new ServiceCounter();

    @Override
    public void onCall(ServiceRequest service) {
        callCounter.record(service);

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        CallRequest request = (CallRequest) service.getRequest();

        List<CallMethodRequest> methodsToCall = l(request.getMethodsToCall());

        if (methodsToCall.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        DiagnosticsContext<CallMethodRequest> diagnosticsContext = new DiagnosticsContext<>();

        CallContext context = new CallContext(
            server,
            session,
            diagnosticsContext
        );

        server.getAddressSpaceManager().call(context, methodsToCall);

        context.getFuture().thenAccept(values -> {
            ResponseHeader header = service.createResponseHeader();

            CallResponse response = new CallResponse(
                header,
                a(values, CallMethodResult.class),
                new DiagnosticInfo[0]
            );

            service.setResponse(response);
        });
    }

}

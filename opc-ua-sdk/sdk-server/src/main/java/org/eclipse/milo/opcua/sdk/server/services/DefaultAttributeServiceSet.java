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
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.ReadContext;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.WriteContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.server.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class DefaultAttributeServiceSet implements AttributeServiceSet {

    private final ServiceCounter readMetric = new ServiceCounter();
    private final ServiceCounter writeMetric = new ServiceCounter();

    @Override
    public void onRead(ServiceRequest service) {
        readMetric.record(service);

        ReadRequest request = (ReadRequest) service.getRequest();

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        List<ReadValueId> nodesToRead = l(request.getNodesToRead());

        if (nodesToRead.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        if (nodesToRead.size() > server.getConfig().getLimits().getMaxNodesPerRead().longValue()) {
            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
            return;
        }

        if (request.getMaxAge() < 0d) {
            service.setServiceFault(StatusCodes.Bad_MaxAgeInvalid);
            return;
        }

        if (request.getTimestampsToReturn() == null) {
            service.setServiceFault(StatusCodes.Bad_TimestampsToReturnInvalid);
            return;
        }

        DiagnosticsContext<ReadValueId> diagnosticsContext = new DiagnosticsContext<>();

        ReadContext context = new ReadContext(server, session, diagnosticsContext);

        server.getAddressSpaceManager().read(
            context,
            request.getMaxAge(),
            request.getTimestampsToReturn(),
            nodesToRead
        );

        context.getFuture().thenAccept(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            ReadResponse response = new ReadResponse(
                header,
                values.toArray(new DataValue[0]),
                diagnosticInfos
            );

            service.setResponse(response);
        });
    }

    @Override
    public void onWrite(ServiceRequest service) {
        writeMetric.record(service);

        WriteRequest request = (WriteRequest) service.getRequest();

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        List<WriteValue> nodesToWrite = l(request.getNodesToWrite());

        if (nodesToWrite.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        if (nodesToWrite.size() > server.getConfig().getLimits().getMaxNodesPerWrite().intValue()) {
            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
            return;
        }

        DiagnosticsContext<WriteValue> diagnosticsContext = new DiagnosticsContext<>();

        WriteContext context = new WriteContext(
            server,
            session,
            new DiagnosticsContext<>()
        );

        server.getAddressSpaceManager().write(context, nodesToWrite);

        context.getFuture().thenAccept(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToWrite);

            WriteResponse response = new WriteResponse(
                header,
                values.toArray(new StatusCode[0]),
                diagnosticInfos
            );

            service.setResponse(response);
        });
    }

}

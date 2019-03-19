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
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeHistoryServices.HistoryReadContext;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeHistoryServices.HistoryUpdateContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.server.services.AttributeHistoryServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class DefaultAttributeHistoryServiceSet implements AttributeHistoryServiceSet {

    private final ServiceCounter historyReadMetric = new ServiceCounter();
    private final ServiceCounter historyUpdateMetric = new ServiceCounter();

    @Override
    public void onHistoryRead(ServiceRequest service) {
        historyReadMetric.record(service);

        HistoryReadRequest request = (HistoryReadRequest) service.getRequest();

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        List<HistoryReadValueId> nodesToRead = l(request.getNodesToRead());

        if (nodesToRead.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        if (nodesToRead.size() > server.getConfig().getLimits().getMaxNodesPerRead().longValue()) {
            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
            return;
        }

        if (request.getTimestampsToReturn() == null) {
            service.setServiceFault(StatusCodes.Bad_TimestampsToReturnInvalid);
            return;
        }

        DiagnosticsContext<HistoryReadValueId> diagnosticsContext = new DiagnosticsContext<>();

        HistoryReadContext context = new HistoryReadContext(
            server,
            session,
            diagnosticsContext
        );

        HistoryReadDetails details = (HistoryReadDetails) request.getHistoryReadDetails().decode(
            server.getConfig().getEncodingLimits(),
            OpcUaDataTypeManager.getInstance()
        );

        server.getAddressSpaceManager().historyRead(
            context,
            details,
            request.getTimestampsToReturn(),
            nodesToRead
        );

        context.getFuture().thenAccept(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            HistoryReadResponse response = new HistoryReadResponse(
                header,
                a(values, HistoryReadResult.class),
                diagnosticInfos
            );

            service.setResponse(response);
        });
    }

    @Override
    public void onHistoryUpdate(ServiceRequest service) throws UaException {
        historyUpdateMetric.record(service);

        HistoryUpdateRequest request = (HistoryUpdateRequest) service.getRequest();

        OpcUaServer server = service.attr(ServiceAttributes.SERVER_KEY).get();
        Session session = service.attr(ServiceAttributes.SESSION_KEY).get();

        List<HistoryUpdateDetails> historyUpdateDetailsList = l(request.getHistoryUpdateDetails())
            .stream().map(e -> (HistoryUpdateDetails) e.decode(
                server.getConfig().getEncodingLimits(),
                OpcUaDataTypeManager.getInstance()
            ))
            .collect(Collectors.toList());

        if (historyUpdateDetailsList.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        if (historyUpdateDetailsList.size() > server.getConfig().getLimits().getMaxNodesPerWrite().intValue()) {
            service.setServiceFault(StatusCodes.Bad_TooManyOperations);
            return;
        }

        DiagnosticsContext<HistoryUpdateDetails> diagnosticsContext = new DiagnosticsContext<>();

        HistoryUpdateContext context = new HistoryUpdateContext(
            server,
            session,
            diagnosticsContext
        );

        server.getAddressSpaceManager().historyUpdate(context, historyUpdateDetailsList);

        context.getFuture().thenAccept(values -> {
            ResponseHeader header = service.createResponseHeader();

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(historyUpdateDetailsList);

            HistoryUpdateResponse response = new HistoryUpdateResponse(
                header,
                a(values, HistoryUpdateResult.class),
                diagnosticInfos
            );

            service.setResponse(response);
        });
    }

}

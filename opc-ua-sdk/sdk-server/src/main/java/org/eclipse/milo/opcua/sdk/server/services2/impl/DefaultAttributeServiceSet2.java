/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services2.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeHistoryServices.HistoryReadContext;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeHistoryServices.HistoryUpdateContext;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.ReadContext;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.WriteContext;
import org.eclipse.milo.opcua.sdk.server.services2.AbstractServiceSet;
import org.eclipse.milo.opcua.sdk.server.services2.AttributeServiceSet2;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class DefaultAttributeServiceSet2 extends AbstractServiceSet implements AttributeServiceSet2 {

    private final OpcUaServer server;

    public DefaultAttributeServiceSet2(OpcUaServer server) {
        this.server = server;
    }

    @Override
    public CompletableFuture<ReadResponse> onRead(ServiceRequestContext context, ReadRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        List<ReadValueId> nodesToRead = List.of(request.getNodesToRead());

        if (nodesToRead.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToRead.size() > server.getConfig().getLimits().getMaxNodesPerRead().longValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        if (request.getMaxAge() < 0d) {
            return failedUaFuture(StatusCodes.Bad_MaxAgeInvalid);
        }

        if (request.getTimestampsToReturn() == null) {
            return failedUaFuture(StatusCodes.Bad_TimestampsToReturnInvalid);
        }

        var diagnosticsContext = new DiagnosticsContext<ReadValueId>();

        var readContext = new ReadContext(server, session, diagnosticsContext);

        session.getSessionDiagnostics().getReadCount().record(readContext.getFuture());
        session.getSessionDiagnostics().getTotalRequestCount().record(readContext.getFuture());

        server.getAddressSpaceManager().read(
            readContext,
            request.getMaxAge(),
            request.getTimestampsToReturn(),
            nodesToRead
        );

        return readContext.getFuture().thenApply(values -> {
            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            ResponseHeader header = createResponseHeader(request);

            return new ReadResponse(header, values.toArray(DataValue[]::new), diagnosticInfos);
        });
    }

    @Override
    public CompletableFuture<HistoryReadResponse> onHistoryRead(
        ServiceRequestContext context,
        HistoryReadRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        List<HistoryReadValueId> nodesToRead = l(request.getNodesToRead());

        if (nodesToRead.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToRead.size() > server.getConfig().getLimits().getMaxNodesPerRead().longValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        if (request.getTimestampsToReturn() == null) {
            return failedUaFuture(StatusCodes.Bad_TimestampsToReturnInvalid);
        }

        var diagnosticsContext = new DiagnosticsContext<HistoryReadValueId>();

        var historyReadContext = new HistoryReadContext(
            server,
            session,
            diagnosticsContext
        );

        session.getSessionDiagnostics().getHistoryReadCount().record(historyReadContext.getFuture());
        session.getSessionDiagnostics().getTotalRequestCount().record(historyReadContext.getFuture());

        ExtensionObject xo = request.getHistoryReadDetails();
        HistoryReadDetails details = (HistoryReadDetails) xo.decode(server.getEncodingContext());

        server.getAddressSpaceManager().historyRead(
            historyReadContext,
            details,
            request.getTimestampsToReturn(),
            nodesToRead
        );

        return historyReadContext.getFuture().thenApply(values -> {
            ResponseHeader header = createResponseHeader(request);

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            return new HistoryReadResponse(header, values.toArray(HistoryReadResult[]::new), diagnosticInfos);
        });
    }

    @Override
    public CompletableFuture<WriteResponse> onWrite(ServiceRequestContext context, WriteRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        List<WriteValue> nodesToWrite = List.of(request.getNodesToWrite());

        if (nodesToWrite.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToWrite.size() > server.getConfig().getLimits().getMaxNodesPerWrite().intValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var diagnosticsContext = new DiagnosticsContext<WriteValue>();

        var writeContext = new WriteContext(
            server,
            session,
            diagnosticsContext
        );

        session.getSessionDiagnostics().getWriteCount().record(writeContext.getFuture());
        session.getSessionDiagnostics().getTotalRequestCount().record(writeContext.getFuture());

        server.getAddressSpaceManager().write(writeContext, nodesToWrite);

        return writeContext.getFuture().thenApply(values -> {
            ResponseHeader header = createResponseHeader(request);

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToWrite);

            return new WriteResponse(header, values.toArray(StatusCode[]::new), diagnosticInfos);
        });
    }

    @Override
    public CompletableFuture<HistoryUpdateResponse> onHistoryUpdate(
        ServiceRequestContext context,
        HistoryUpdateRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        List<HistoryUpdateDetails> historyUpdateDetailsList = Stream.of(request.getHistoryUpdateDetails())
            .map(xo -> (HistoryUpdateDetails) xo.decode(server.getEncodingContext()))
            .collect(Collectors.toList());

        if (historyUpdateDetailsList.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (historyUpdateDetailsList.size() > server.getConfig().getLimits().getMaxNodesPerWrite().intValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var diagnosticsContext = new DiagnosticsContext<HistoryUpdateDetails>();

        var historyUpdateContext = new HistoryUpdateContext(
            server,
            session,
            diagnosticsContext
        );

        server.getAddressSpaceManager().historyUpdate(historyUpdateContext, historyUpdateDetailsList);

        return historyUpdateContext.getFuture().thenApply(values -> {
            ResponseHeader header = createResponseHeader(request);

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(historyUpdateDetailsList);

            return new HistoryUpdateResponse(header, values.toArray(HistoryUpdateResult[]::new), diagnosticInfos);
        });
    }

}

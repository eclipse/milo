/*
 * Copyright (c) 2024 the Eclipse Milo Authors
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.server.AddressSpace.HistoryReadContext;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.HistoryUpdateContext;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.ReadContext;
import org.eclipse.milo.opcua.sdk.server.AddressSpace.WriteContext;
import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.servicesets.AbstractServiceSet;
import org.eclipse.milo.opcua.sdk.server.servicesets.AttributeServiceSet;
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
import org.eclipse.milo.opcua.stack.core.util.Lists;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

import static java.util.Objects.requireNonNullElse;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class DefaultAttributeServiceSet extends AbstractServiceSet implements AttributeServiceSet {

    private final OpcUaServer server;

    public DefaultAttributeServiceSet(OpcUaServer server) {
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

        List<ReadValueId> nodesToRead = Lists.ofNullable(request.getNodesToRead());

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

        var readContext = new ReadContext(
            server,
            session,
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        try {
            List<DataValue> values = server.getAddressSpaceManager().read(
                readContext,
                request.getMaxAge(),
                request.getTimestampsToReturn(),
                nodesToRead
            );

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            ResponseHeader header = createResponseHeader(request);

            var response = new ReadResponse(header, values.toArray(DataValue[]::new), diagnosticInfos);

            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            session.getSessionDiagnostics().getReadCount().incrementErrorCount();

            return CompletableFuture.failedFuture(e);
        } finally {
            session.getSessionDiagnostics().getReadCount().incrementTotalCount();
        }
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

        List<HistoryReadValueId> nodesToRead = Lists.ofNullable(request.getNodesToRead());

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
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        try {
            ExtensionObject xo = request.getHistoryReadDetails();
            HistoryReadDetails details = (HistoryReadDetails) xo.decode(server.getEncodingContext());

            List<HistoryReadResult> results = server.getAddressSpaceManager().historyRead(
                historyReadContext,
                details,
                request.getTimestampsToReturn(),
                nodesToRead
            );

            ResponseHeader header = createResponseHeader(request);

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToRead);

            var response = new HistoryReadResponse(header, results.toArray(HistoryReadResult[]::new), diagnosticInfos);

            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            session.getSessionDiagnostics().getHistoryReadCount().incrementErrorCount();

            return CompletableFuture.failedFuture(e);
        } finally {
            session.getSessionDiagnostics().getHistoryReadCount().incrementTotalCount();
        }
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

        List<WriteValue> nodesToWrite = Lists.ofNullable(request.getNodesToWrite());

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
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        try {
            List<StatusCode> results = server.getAddressSpaceManager().write(writeContext, nodesToWrite);

            ResponseHeader header = createResponseHeader(request);

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(nodesToWrite);

            var response = new WriteResponse(header, results.toArray(StatusCode[]::new), diagnosticInfos);

            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            session.getSessionDiagnostics().getWriteCount().incrementErrorCount();

            return CompletableFuture.failedFuture(e);
        } finally {
            session.getSessionDiagnostics().getWriteCount().incrementTotalCount();
        }
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

        var historyUpdateDetails =
            requireNonNullElse(request.getHistoryUpdateDetails(), new ExtensionObject[0]);

        List<HistoryUpdateDetails> historyUpdateDetailsList = Stream.of(historyUpdateDetails)
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
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        try {
            List<HistoryUpdateResult> results = server.getAddressSpaceManager()
                .historyUpdate(historyUpdateContext, historyUpdateDetailsList);

            ResponseHeader header = createResponseHeader(request);

            DiagnosticInfo[] diagnosticInfos =
                diagnosticsContext.getDiagnosticInfos(historyUpdateDetailsList);

            var response = new HistoryUpdateResponse(
                header, results.toArray(HistoryUpdateResult[]::new), diagnosticInfos);

            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            session.getSessionDiagnostics().getHistoryUpdateCount().incrementErrorCount();

            return CompletableFuture.failedFuture(e);
        } finally {
            session.getSessionDiagnostics().getHistoryUpdateCount().incrementTotalCount();
        }
    }

}

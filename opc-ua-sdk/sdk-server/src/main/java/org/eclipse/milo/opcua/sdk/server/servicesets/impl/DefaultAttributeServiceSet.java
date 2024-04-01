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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import org.eclipse.milo.opcua.sdk.server.servicesets.impl.AccessController.AccessCheckResult;
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
import static org.eclipse.milo.opcua.sdk.core.util.GroupMapCollate.groupMapCollate;

public class DefaultAttributeServiceSet extends AbstractServiceSet implements AttributeServiceSet {

    private final OpcUaServer server;

    public DefaultAttributeServiceSet(OpcUaServer server) {
        this.server = server;
    }

    @Override
    public ReadResponse onRead(ServiceRequestContext context, ReadRequest request) throws UaException {
        Session session = server.getSessionManager()
            .getSession(context, request.getRequestHeader());

        try {
            return read(request, session);
        } catch (UaException e) {
            session.getSessionDiagnostics().getReadCount().incrementErrorCount();
            session.getSessionDiagnostics().getTotalRequestCount().incrementErrorCount();

            throw e;
        } finally {
            session.getSessionDiagnostics().getReadCount().incrementTotalCount();
            session.getSessionDiagnostics().getTotalRequestCount().incrementTotalCount();
        }
    }

    @Override
    public HistoryReadResponse onHistoryRead(
        ServiceRequestContext context, HistoryReadRequest request) throws UaException {

        Session session = server.getSessionManager()
            .getSession(context, request.getRequestHeader());

        try {
            return historyRead(request, session);
        } catch (UaException e) {
            session.getSessionDiagnostics().getHistoryReadCount().incrementErrorCount();
            session.getSessionDiagnostics().getTotalRequestCount().incrementErrorCount();

            throw e;
        } finally {
            session.getSessionDiagnostics().getHistoryReadCount().incrementTotalCount();
            session.getSessionDiagnostics().getTotalRequestCount().incrementTotalCount();
        }
    }

    @Override
    public WriteResponse onWrite(ServiceRequestContext context, WriteRequest request) throws UaException {
        Session session = server.getSessionManager()
            .getSession(context, request.getRequestHeader());

        try {
            return write(request, session);
        } catch (UaException e) {
            session.getSessionDiagnostics().getWriteCount().incrementErrorCount();
            session.getSessionDiagnostics().getTotalRequestCount().incrementErrorCount();

            throw e;
        } finally {
            session.getSessionDiagnostics().getWriteCount().incrementTotalCount();
            session.getSessionDiagnostics().getTotalRequestCount().incrementTotalCount();
        }
    }

    @Override
    public HistoryUpdateResponse onHistoryUpdate(
        ServiceRequestContext context, HistoryUpdateRequest request) throws UaException {

        Session session = server.getSessionManager()
            .getSession(context, request.getRequestHeader());

        try {
            return historyUpdate(request, session);
        } catch (UaException e) {
            session.getSessionDiagnostics().getHistoryUpdateCount().incrementErrorCount();
            session.getSessionDiagnostics().getTotalRequestCount().incrementErrorCount();

            throw e;
        } finally {
            session.getSessionDiagnostics().getHistoryUpdateCount().incrementTotalCount();
            session.getSessionDiagnostics().getTotalRequestCount().incrementTotalCount();
        }
    }

    private ReadResponse read(ReadRequest request, Session session) throws UaException {
        List<ReadValueId> nodesToRead = Lists.ofNullable(request.getNodesToRead());

        if (nodesToRead.isEmpty()) {
            throw new UaException(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToRead.size() > server.getConfig().getLimits().getMaxNodesPerRead().longValue()) {
            throw new UaException(StatusCodes.Bad_TooManyOperations);
        }

        if (request.getMaxAge() < 0d) {
            throw new UaException(StatusCodes.Bad_MaxAgeInvalid);
        }

        if (request.getTimestampsToReturn() == null) {
            throw new UaException(StatusCodes.Bad_TimestampsToReturnInvalid);
        }

        List<AccessCheckResult> accessCheckResults =
            new ReadAccessController(server).checkReadAccess(session, nodesToRead);

        var accessCheckResultMap = new HashMap<ReadValueId, AccessCheckResult>();
        for (int i = 0; i < nodesToRead.size(); i++) {
            accessCheckResultMap.put(nodesToRead.get(i), accessCheckResults.get(i));
        }

        var diagnosticsContext = new DiagnosticsContext<ReadValueId>();

        List<DataValue> values = groupMapCollate(
            nodesToRead,
            rvi -> accessCheckResultMap.get(rvi) == AccessCheckResult.ALLOWED,
            allowed -> group -> {
                if (allowed) {
                    var readContext = new ReadContext(
                        server,
                        session,
                        diagnosticsContext,
                        request.getRequestHeader().getAuditEntryId(),
                        request.getRequestHeader().getTimeoutHint(),
                        request.getRequestHeader().getAdditionalHeader()
                    );

                    return server.getAddressSpaceManager().read(
                        readContext,
                        request.getMaxAge(),
                        request.getTimestampsToReturn(),
                        group
                    );
                } else {
                    return Collections.nCopies(group.size(), new DataValue(StatusCodes.Bad_UserAccessDenied));
                }
            }
        );

        DiagnosticInfo[] diagnosticInfos =
            diagnosticsContext.getDiagnosticInfos(nodesToRead);

        ResponseHeader header = createResponseHeader(request);

        return new ReadResponse(header, values.toArray(DataValue[]::new), diagnosticInfos);
    }

    private HistoryReadResponse historyRead(HistoryReadRequest request, Session session) throws UaException {
        List<HistoryReadValueId> nodesToRead = Lists.ofNullable(request.getNodesToRead());

        if (nodesToRead.isEmpty()) {
            throw new UaException(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToRead.size() > server.getConfig().getLimits().getMaxNodesPerRead().longValue()) {
            throw new UaException(StatusCodes.Bad_TooManyOperations);
        }

        if (request.getTimestampsToReturn() == null) {
            throw new UaException(StatusCodes.Bad_TimestampsToReturnInvalid);
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

        return new HistoryReadResponse(header, results.toArray(HistoryReadResult[]::new), diagnosticInfos);
    }

    private WriteResponse write(WriteRequest request, Session session) throws UaException {
        List<WriteValue> nodesToWrite = Lists.ofNullable(request.getNodesToWrite());

        if (nodesToWrite.isEmpty()) {
            throw new UaException(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToWrite.size() > server.getConfig().getLimits().getMaxNodesPerWrite().intValue()) {
            throw new UaException(StatusCodes.Bad_TooManyOperations);
        }

        List<AccessCheckResult> accessCheckResults =
            new WriteAccessController(server).checkWriteAccess(session, nodesToWrite);

        var accessCheckResultMap = new HashMap<WriteValue, AccessCheckResult>();
        for (int i = 0; i < nodesToWrite.size(); i++) {
            accessCheckResultMap.put(nodesToWrite.get(i), accessCheckResults.get(i));
        }

        var diagnosticsContext = new DiagnosticsContext<WriteValue>();

        List<StatusCode> results = groupMapCollate(
            nodesToWrite,
            wv -> accessCheckResultMap.get(wv) == AccessCheckResult.ALLOWED,
            allowed -> group -> {
                if (allowed) {
                    var writeContext = new WriteContext(
                        server,
                        session,
                        diagnosticsContext,
                        request.getRequestHeader().getAuditEntryId(),
                        request.getRequestHeader().getTimeoutHint(),
                        request.getRequestHeader().getAdditionalHeader()
                    );

                    return server.getAddressSpaceManager().write(writeContext, group);
                } else {
                    return Collections.nCopies(group.size(), new StatusCode(StatusCodes.Bad_UserAccessDenied));
                }
            }
        );

        ResponseHeader header = createResponseHeader(request);

        DiagnosticInfo[] diagnosticInfos =
            diagnosticsContext.getDiagnosticInfos(nodesToWrite);

        return new WriteResponse(header, results.toArray(StatusCode[]::new), diagnosticInfos);
    }

    private HistoryUpdateResponse historyUpdate(HistoryUpdateRequest request, Session session) throws UaException {
        var historyUpdateDetails =
            requireNonNullElse(request.getHistoryUpdateDetails(), new ExtensionObject[0]);

        List<HistoryUpdateDetails> historyUpdateDetailsList = Stream.of(historyUpdateDetails)
            .map(xo -> (HistoryUpdateDetails) xo.decode(server.getEncodingContext()))
            .collect(Collectors.toList());

        if (historyUpdateDetailsList.isEmpty()) {
            throw new UaException(StatusCodes.Bad_NothingToDo);
        }

        if (historyUpdateDetailsList.size() > server.getConfig().getLimits().getMaxNodesPerWrite().intValue()) {
            throw new UaException(StatusCodes.Bad_TooManyOperations);
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

        List<HistoryUpdateResult> results = server.getAddressSpaceManager()
            .historyUpdate(historyUpdateContext, historyUpdateDetailsList);

        ResponseHeader header = createResponseHeader(request);

        DiagnosticInfo[] diagnosticInfos =
            diagnosticsContext.getDiagnosticInfos(historyUpdateDetailsList);

        return new HistoryUpdateResponse(
            header, results.toArray(HistoryUpdateResult[]::new), diagnosticInfos);
    }

}

/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.AddNodesContext;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.AddReferencesContext;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.DeleteNodesContext;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices.DeleteReferencesContext;
import org.eclipse.milo.opcua.sdk.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResult;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

import static org.eclipse.milo.opcua.sdk.server.services.AbstractServiceSet.createResponseHeader;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class DefaultNodeManagementServiceSet implements NodeManagementServiceSet {

    private final OpcUaServer server;

    public DefaultNodeManagementServiceSet(OpcUaServer server) {
        this.server = server;
    }

    @Override
    public CompletableFuture<AddNodesResponse> onAddNodes(ServiceRequestContext context, AddNodesRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<AddNodesResponse> future = addNodes(request, session);

        session.getSessionDiagnostics().getAddNodesCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<DeleteNodesResponse> onDeleteNodes(ServiceRequestContext context, DeleteNodesRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<DeleteNodesResponse> future = deleteNodes(request, session);

        session.getSessionDiagnostics().getDeleteNodesCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<AddReferencesResponse> onAddReferences(ServiceRequestContext context, AddReferencesRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<AddReferencesResponse> future = addReferences(request, session);

        session.getSessionDiagnostics().getAddReferencesCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<DeleteReferencesResponse> onDeleteReferences(ServiceRequestContext context, DeleteReferencesRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<DeleteReferencesResponse> future = deleteReferences(request, session);

        session.getSessionDiagnostics().getDeleteReferencesCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    private CompletableFuture<AddNodesResponse> addNodes(AddNodesRequest request, Session session) {
        List<AddNodesItem> nodesToAdd = List.of(request.getNodesToAdd());

        if (nodesToAdd.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToAdd.size() > server.getConfig().getLimits().getMaxNodesPerNodeManagement().longValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var diagnosticsContext = new DiagnosticsContext<AddNodesItem>();

        var addNodesContext = new AddNodesContext(
            server,
            session,
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        server.getAddressSpaceManager().addNodes(addNodesContext, nodesToAdd);

        return addNodesContext.getFuture().thenApply(results -> {
            DiagnosticsContext.AggregateDiagnosticInfos adi =
                diagnosticsContext.getDiagnosticInfos(nodesToAdd);

            ResponseHeader header = createResponseHeader(
                request,
                adi.getStringTable()
            );

            return new AddNodesResponse(
                header,
                results.toArray(AddNodesResult[]::new),
                adi.getDiagnosticInfos().toArray(DiagnosticInfo[]::new)
            );
        });
    }

    private CompletableFuture<DeleteNodesResponse> deleteNodes(DeleteNodesRequest request, Session session) {
        List<DeleteNodesItem> nodesToDelete = l(request.getNodesToDelete());

        if (nodesToDelete.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToDelete.size() > server.getConfig().getLimits().getMaxNodesPerNodeManagement().longValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var diagnosticsContext = new DiagnosticsContext<DeleteNodesItem>();

        var deleteNodesContext = new DeleteNodesContext(
            server,
            session,
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        server.getAddressSpaceManager().deleteNodes(deleteNodesContext, nodesToDelete);

        return deleteNodesContext.getFuture().thenApply(results -> {
            DiagnosticsContext.AggregateDiagnosticInfos adi =
                diagnosticsContext.getDiagnosticInfos(nodesToDelete);

            ResponseHeader header = createResponseHeader(
                request,
                adi.getStringTable()
            );

            return new DeleteNodesResponse(
                header,
                results.toArray(StatusCode[]::new),
                adi.getDiagnosticInfos().toArray(DiagnosticInfo[]::new)
            );
        });
    }

    private CompletableFuture<AddReferencesResponse> addReferences(AddReferencesRequest request, Session session) {
        List<AddReferencesItem> referencesToAdd = List.of(request.getReferencesToAdd());

        if (referencesToAdd.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (referencesToAdd.size() > server.getConfig().getLimits().getMaxNodesPerNodeManagement().longValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var diagnosticsContext = new DiagnosticsContext<AddReferencesItem>();

        var addReferencesContext = new AddReferencesContext(
            server,
            session,
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        server.getAddressSpaceManager().addReferences(addReferencesContext, referencesToAdd);

        return addReferencesContext.getFuture().thenApply(results -> {
            DiagnosticsContext.AggregateDiagnosticInfos adi =
                diagnosticsContext.getDiagnosticInfos(referencesToAdd);

            ResponseHeader header = createResponseHeader(
                request,
                adi.getStringTable()
            );

            return new AddReferencesResponse(
                header,
                results.toArray(StatusCode[]::new),
                adi.getDiagnosticInfos().toArray(DiagnosticInfo[]::new)
            );
        });
    }

    private CompletableFuture<DeleteReferencesResponse> deleteReferences(
        DeleteReferencesRequest request,
        Session session
    ) {

        List<DeleteReferencesItem> referencesToDelete = List.of(request.getReferencesToDelete());

        if (referencesToDelete.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (referencesToDelete.size() > server.getConfig().getLimits().getMaxNodesPerNodeManagement().longValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var diagnosticsContext = new DiagnosticsContext<DeleteReferencesItem>();

        var deleteReferencesContext = new DeleteReferencesContext(
            server,
            session,
            diagnosticsContext,
            request.getRequestHeader().getAuditEntryId(),
            request.getRequestHeader().getTimeoutHint(),
            request.getRequestHeader().getAdditionalHeader()
        );

        server.getAddressSpaceManager().deleteReferences(deleteReferencesContext, referencesToDelete);

        return deleteReferencesContext.getFuture().thenApply(results -> {
            DiagnosticsContext.AggregateDiagnosticInfos adi =
                diagnosticsContext.getDiagnosticInfos(referencesToDelete);

            ResponseHeader header = createResponseHeader(
                request,
                adi.getStringTable()
            );

            return new DeleteReferencesResponse(
                header,
                results.toArray(StatusCode[]::new),
                adi.getDiagnosticInfos().toArray(DiagnosticInfo[]::new)
            );
        });
    }

}

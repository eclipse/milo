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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.services.ViewServices.RegisterNodesContext;
import org.eclipse.milo.opcua.sdk.server.api.services.ViewServices.UnregisterNodesContext;
import org.eclipse.milo.opcua.sdk.server.services.helpers.BrowseHelper;
import org.eclipse.milo.opcua.sdk.server.services.helpers.BrowsePathsHelper;
import org.eclipse.milo.opcua.sdk.server.services2.ViewServiceSet2;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

import static org.eclipse.milo.opcua.sdk.server.services2.AbstractServiceSet.createResponseHeader;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class DefaultViewServiceSet2 implements ViewServiceSet2 {

    private final BrowseHelper browseHelper;

    private final OpcUaServer server;

    public DefaultViewServiceSet2(OpcUaServer server) {
        this.server = server;

        browseHelper = new BrowseHelper(server.getConfig().getExecutor());
    }

    @Override
    public CompletableFuture<BrowseResponse> onBrowse(ServiceRequestContext context, BrowseRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<BrowseResponse> future = browse(request, session);

        session.getSessionDiagnostics().getBrowseCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<BrowseNextResponse> onBrowseNext(
        ServiceRequestContext context,
        BrowseNextRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<BrowseNextResponse> future = browseHelper.browseNext(server, session, request)
            .thenApply(results -> {
                ResponseHeader header = createResponseHeader(request);

                return new BrowseNextResponse(header, results, new DiagnosticInfo[0]);
            });

        session.getSessionDiagnostics().getBrowseNextCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<TranslateBrowsePathsToNodeIdsResponse> onTranslateBrowsePaths(
        ServiceRequestContext context,
        TranslateBrowsePathsToNodeIdsRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            // TODO Session-less service invocation?
            return CompletableFuture.failedFuture(e);
        }

        var browsePathsHelper = new BrowsePathsHelper(() -> Optional.ofNullable(session), server);

        CompletableFuture<TranslateBrowsePathsToNodeIdsResponse> future =
            browsePathsHelper.translateBrowsePaths(request);

        session.getSessionDiagnostics().getTranslateBrowsePathsToNodeIdsCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<RegisterNodesResponse> onRegisterNodes(
        ServiceRequestContext context,
        RegisterNodesRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<RegisterNodesResponse> future = registerNodes(request, session);

        session.getSessionDiagnostics().getRegisterNodesCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<UnregisterNodesResponse> onUnregisterNodes(
        ServiceRequestContext context,
        UnregisterNodesRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<UnregisterNodesResponse> future = unregisterNodes(request, session);

        session.getSessionDiagnostics().getUnregisterNodesCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    private CompletableFuture<BrowseResponse> browse(BrowseRequest request, Session session) {
        List<BrowseDescription> nodesToBrowse = List.of(request.getNodesToBrowse());

        if (nodesToBrowse.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodesToBrowse.size() > server.getConfig().getLimits().getMaxNodesPerBrowse().intValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        if (request.getView().getViewId().isNotNull() &&
            !server.getRegisteredViews().contains(request.getView().getViewId())) {

            return failedUaFuture(StatusCodes.Bad_ViewIdUnknown);
        }

        Stream<CompletableFuture<BrowseResult>> futures = nodesToBrowse.stream().map(
            browseDescription ->
                browseHelper.browse(
                    () -> Optional.of(session),
                    server,
                    request.getView(),
                    request.getRequestedMaxReferencesPerNode(),
                    browseDescription
                )
        );

        return FutureUtils.sequence(futures).thenApply(results -> {
            ResponseHeader header = createResponseHeader(request);

            return new BrowseResponse(header, results.toArray(BrowseResult[]::new), new DiagnosticInfo[0]);
        });
    }

    private CompletableFuture<RegisterNodesResponse> registerNodes(RegisterNodesRequest request, Session session) {
        List<NodeId> nodeIds = List.of(request.getNodesToRegister());

        if (nodeIds.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodeIds.size() > server.getConfig().getLimits().getMaxNodesPerRegisterNodes().intValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var registerNodesContext = new RegisterNodesContext(server, session);

        server.getAddressSpaceManager().registerNodes(registerNodesContext, nodeIds);

        return registerNodesContext.getFuture().thenApply(registeredNodeIds -> {
            ResponseHeader header = createResponseHeader(request);

            return new RegisterNodesResponse(header, registeredNodeIds.toArray(new NodeId[0]));
        });
    }

    private CompletableFuture<UnregisterNodesResponse> unregisterNodes(UnregisterNodesRequest request, Session session) {
        List<NodeId> nodeIds = List.of(request.getNodesToUnregister());

        if (nodeIds.isEmpty()) {
            return failedUaFuture(StatusCodes.Bad_NothingToDo);
        }

        if (nodeIds.size() > server.getConfig().getLimits().getMaxNodesPerRegisterNodes().intValue()) {
            return failedUaFuture(StatusCodes.Bad_TooManyOperations);
        }

        var unregisterNodesContext = new UnregisterNodesContext(server, session);

        server.getAddressSpaceManager().unregisterNodes(unregisterNodesContext, nodeIds);

        return unregisterNodesContext.getFuture().thenApply(registeredNodeIds -> {
            ResponseHeader header = createResponseHeader(request);

            return new UnregisterNodesResponse(header);
        });
    }

}

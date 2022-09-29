package org.eclipse.milo.opcua.sdk.server.services2.impl;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.SessionManager;
import org.eclipse.milo.opcua.sdk.server.diagnostics.ServerDiagnosticsSummary;
import org.eclipse.milo.opcua.sdk.server.services2.SessionServiceSet2;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class DefaultSessionServiceSet2 implements SessionServiceSet2 {

    private final OpcUaServer server;

    public DefaultSessionServiceSet2(OpcUaServer server) {
        this.server = server;
    }

    @Override
    public CompletableFuture<CreateSessionResponse> onCreateSession(ServiceRequestContext context, CreateSessionRequest request) {
        ServerDiagnosticsSummary serverDiagnosticsSummary = server.getDiagnosticsSummary();

        SessionManager sessionManager = server.getSessionManager();

        try {
            CreateSessionResponse response = sessionManager.createSession(context, request);

            serverDiagnosticsSummary.getCumulatedSessionCount().increment();

            return CompletableFuture.completedFuture(response);
        } catch (UaException e) {
            serverDiagnosticsSummary.getRejectedSessionCount().increment();

            if (e.getStatusCode().isSecurityError()) {
                serverDiagnosticsSummary.getSecurityRejectedSessionCount().increment();
            }

            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<ActivateSessionResponse> onActivateSession(ServiceRequestContext context, ActivateSessionRequest request) {

        SessionManager sessionManager = server.getSessionManager();

        try {
            ActivateSessionResponse response = sessionManager.activateSession(context, request);

            return CompletableFuture.completedFuture(response);
        } catch (UaException e) {
            ServerDiagnosticsSummary serverDiagnosticsSummary = server.getDiagnosticsSummary();

            serverDiagnosticsSummary.getRejectedSessionCount().increment();

            if (e.getStatusCode().isSecurityError()) {
                serverDiagnosticsSummary.getSecurityRejectedSessionCount().increment();
            }

            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<CloseSessionResponse> onCloseSession(ServiceRequestContext context, CloseSessionRequest request) {
        return failedUaFuture(StatusCodes.Bad_NotImplemented);
    }

    @Override
    public CompletableFuture<CancelResponse> onCancel(ServiceRequestContext context, CancelRequest request) {
        return failedUaFuture(StatusCodes.Bad_NotImplemented);
    }

}

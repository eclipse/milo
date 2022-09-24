package org.eclipse.milo.opcua.sdk.server.services2.impl;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.services2.MonitoredItemServiceSet2;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

public class DefaultMonitoredItemServiceSet2 implements MonitoredItemServiceSet2 {

    private final OpcUaServer server;

    public DefaultMonitoredItemServiceSet2(OpcUaServer server) {
        this.server = server;
    }

    @Override
    public CompletableFuture<CreateMonitoredItemsResponse> onCreateMonitoredItems(
        ServiceRequestContext context,
        CreateMonitoredItemsRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<CreateMonitoredItemsResponse> future =
            session.getSubscriptionManager().createMonitoredItems(context, request);

        session.getSessionDiagnostics().getCreateMonitoredItemsCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<ModifyMonitoredItemsResponse> onModifyMonitoredItems(ServiceRequestContext context, ModifyMonitoredItemsRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<ModifyMonitoredItemsResponse> future =
            session.getSubscriptionManager().modifyMonitoredItems(context, request);

        session.getSessionDiagnostics().getModifyMonitoredItemsCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<DeleteMonitoredItemsResponse> onDeleteMonitoredItems(
        ServiceRequestContext context,
        DeleteMonitoredItemsRequest request
    ) {

        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<DeleteMonitoredItemsResponse> future =
            session.getSubscriptionManager().deleteMonitoredItems(context, request);

        session.getSessionDiagnostics().getDeleteMonitoredItemsCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<SetMonitoringModeResponse> onSetMonitoringMode(ServiceRequestContext context, SetMonitoringModeRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<SetMonitoringModeResponse> future =
            session.getSubscriptionManager().setMonitoringMode(context, request);

        session.getSessionDiagnostics().getSetMonitoringModeCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }

    @Override
    public CompletableFuture<SetTriggeringResponse> onSetTriggering(ServiceRequestContext context, SetTriggeringRequest request) {
        Session session;
        try {
            session = server.getSessionManager()
                .getSession(context, request.getRequestHeader());
        } catch (UaException e) {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<SetTriggeringResponse> future =
            session.getSubscriptionManager().setTriggering(context, request);

        session.getSessionDiagnostics().getSetTriggeringCount().record(future);
        session.getSessionDiagnostics().getTotalRequestCount().record(future);

        return future;
    }
}

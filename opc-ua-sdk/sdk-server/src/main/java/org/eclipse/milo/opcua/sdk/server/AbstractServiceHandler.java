package org.eclipse.milo.opcua.sdk.server;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import com.google.common.collect.ForwardingTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import org.eclipse.milo.opcua.sdk.server.services2.AttributeServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.MethodServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.MonitoredItemServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.Service;
import org.eclipse.milo.opcua.sdk.server.services2.SessionServiceSet2;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractServiceHandler {

    private final ServiceHandlerTable serviceHandlerTable = new ServiceHandlerTable();

    public void addServiceSet(String path, AttributeServiceSet2 serviceSet) {
        serviceHandlerTable.put(
            path,
            Service.ATTRIBUTE_READ,
            (context, request) ->
                serviceSet.onRead(context, (ReadRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.ATTRIBUTE_HISTORY_READ,
            (context, request) ->
                serviceSet.onHistoryRead(context, (HistoryReadRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.ATTRIBUTE_WRITE,
            (context, request) ->
                serviceSet.onWrite(context, (WriteRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.ATTRIBUTE_HISTORY_UPDATE,
            (context, request) ->
                serviceSet.onHistoryUpdate(context, (HistoryUpdateRequest) request).thenApply(Function.identity())
        );
    }

    public void addServiceSet(String path, MethodServiceSet2 serviceSet) {
        serviceHandlerTable.put(
            path,
            Service.METHOD_CALL,
            (context, request) ->
                serviceSet.onCall(context, (CallRequest) request).thenApply(Function.identity())
        );
    }

    public void addServiceSet(String path, MonitoredItemServiceSet2 serviceSet) {
        serviceHandlerTable.put(
            path,
            Service.MONITORED_ITEM_CREATE_MONITORED_ITEMS,
            (context, request) ->
                serviceSet.onCreateMonitoredItems(context, (CreateMonitoredItemsRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.MONITORED_ITEM_MODIFY_MONITORED_ITEMS,
            (context, request) ->
                serviceSet.onModifyMonitoredItems(context, (ModifyMonitoredItemsRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.MONITORED_ITEM_DELETE_MONITORED_ITEMS,
            (context, request) ->
                serviceSet.onDeleteMonitoredItems(context, (DeleteMonitoredItemsRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.MONITORED_ITEM_SET_MONITORING_MODE,
            (context, request) ->
                serviceSet.onSetMonitoringMode(context, (SetMonitoringModeRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.MONITORED_ITEM_SET_TRIGGERING,
            (context, request) ->
                serviceSet.onSetTriggering(context, (SetTriggeringRequest) request).thenApply(Function.identity())
        );
    }

    public void addServiceSet(String path, SessionServiceSet2 serviceSet) {
        serviceHandlerTable.put(
            path,
            Service.SESSION_CREATE_SESSION,
            (context, request) ->
                serviceSet.onCreateSession(context, (CreateSessionRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SESSION_ACTIVATE_SESSION,
            (context, request) ->
                serviceSet.onActivateSession(context, (ActivateSessionRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SESSION_CLOSE_SESSION,
            (context, request) ->
                serviceSet.onCloseSession(context, (CloseSessionRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SESSION_CANCEL,
            (context, request) ->
                serviceSet.onCancel(context, (CancelRequest) request).thenApply(Function.identity())
        );
    }

    protected void addServiceHandler(String path, Service service, ServiceHandler serviceHandler) {
        serviceHandlerTable.put(path, service, serviceHandler);
    }

    protected @Nullable ServiceHandler getServiceHandler(String path, Service service) {
        return serviceHandlerTable.get(path, service);
    }

    protected interface ServiceHandler {
        CompletableFuture<UaResponseMessageType> handle(
            ServiceRequestContext context,
            UaRequestMessageType requestMessage
        );
    }

    private static class ServiceHandlerTable extends
        ForwardingTable<String, Service, ServiceHandler> {


        private final Table<String, Service, ServiceHandler> delegate =
            Tables.synchronizedTable(HashBasedTable.create());

        @Override
        protected @NotNull Table<String, Service, ServiceHandler> delegate() {
            return delegate;
        }

    }

}

/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import org.eclipse.milo.opcua.sdk.server.services2.AttributeServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.DiscoveryServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.MethodServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.MonitoredItemServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.NodeManagementServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.Service;
import org.eclipse.milo.opcua.sdk.server.services2.SessionServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.SubscriptionServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.ViewServiceSet2;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Request;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;
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

    public void addServiceSet(String path, DiscoveryServiceSet2 serviceSet) {
        serviceHandlerTable.put(
            path,
            Service.DISCOVERY_FIND_SERVERS,
            (context, request) ->
                serviceSet.onFindServers(context, (FindServersRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.DISCOVERY_FIND_SERVERS_ON_NETWORK,
            (context, request) ->
                serviceSet.onFindServersOnNetwork(context, (FindServersOnNetworkRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.DISCOVERY_GET_ENDPOINTS,
            (context, request) ->
                serviceSet.onGetEndpoints(context, (GetEndpointsRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.DISCOVERY_REGISTER_SERVER,
            (context, request) ->
                serviceSet.onRegisterServer(context, (RegisterServerRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.DISCOVERY_REGISTER_SERVER_2,
            (context, request) ->
                serviceSet.onRegisterServer2(context, (RegisterServer2Request) request).thenApply(Function.identity())
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

    public void addServiceSet(String path, NodeManagementServiceSet2 serviceSet) {
        serviceHandlerTable.put(
            path,
            Service.NODE_MANAGEMENT_ADD_NODES,
            (context, request) ->
                serviceSet.onAddNodes(context, (AddNodesRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.NODE_MANAGEMENT_DELETE_NODES,
            (context, request) ->
                serviceSet.onDeleteNodes(context, (DeleteNodesRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.NODE_MANAGEMENT_ADD_REFERENCES,
            (context, request) ->
                serviceSet.onAddReferences(context, (AddReferencesRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.NODE_MANAGEMENT_DELETE_REFERENCES,
            (context, request) ->
                serviceSet.onDeleteReferences(context, (DeleteReferencesRequest) request).thenApply(Function.identity())
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

    public void addServiceSet(String path, SubscriptionServiceSet2 serviceSet) {
        serviceHandlerTable.put(
            path,
            Service.SUBSCRIPTION_CREATE_SUBSCRIPTION,
            (context, request) ->
                serviceSet.onCreateSubscription(context, (CreateSubscriptionRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SUBSCRIPTION_MODIFY_SUBSCRIPTION,
            (context, request) ->
                serviceSet.onModifySubscription(context, (ModifySubscriptionRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SUBSCRIPTION_DELETE_SUBSCRIPTIONS,
            (context, request) ->
                serviceSet.onDeleteSubscriptions(context, (DeleteSubscriptionsRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SUBSCRIPTION_TRANSFER_SUBSCRIPTIONS,
            (context, request) ->
                serviceSet.onTransferSubscriptions(context, (TransferSubscriptionsRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SUBSCRIPTION_SET_PUBLISHING_MODE,
            (context, request) ->
                serviceSet.onSetPublishingMode(context, (SetPublishingModeRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SUBSCRIPTION_PUBLISH,
            (context, request) ->
                serviceSet.onPublish(context, (PublishRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.SUBSCRIPTION_REPUBLISH,
            (context, request) ->
                serviceSet.onRepublish(context, (RepublishRequest) request)
                    .thenApply(Function.identity())
        );
    }

    public void addServiceSet(String path, ViewServiceSet2 serviceSet) {
        serviceHandlerTable.put(
            path,
            Service.VIEW_BROWSE,
            (context, request) ->
                serviceSet.onBrowse(context, (BrowseRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.VIEW_BROWSE_NEXT,
            (context, request) ->
                serviceSet.onBrowseNext(context, (BrowseNextRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.VIEW_BROWSE_TRANSLATE_BROWSE_PATHS,
            (context, request) ->
                serviceSet.onTranslateBrowsePaths(context, (TranslateBrowsePathsToNodeIdsRequest) request)
                    .thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.VIEW_BROWSE_REGISTER_NODES,
            (context, request) ->
                serviceSet.onRegisterNodes(context, (RegisterNodesRequest) request).thenApply(Function.identity())
        );
        serviceHandlerTable.put(
            path,
            Service.VIEW_BROWSE_UNREGISTER_NODES,
            (context, request) ->
                serviceSet.onUnregisterNodes(context, (UnregisterNodesRequest) request).thenApply(Function.identity())
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

    private static class ServiceHandlerTable {

        private final ConcurrentMap<String, ConcurrentMap<Service, ServiceHandler>> table = new ConcurrentHashMap<>();

        public @Nullable ServiceHandler get(String path, Service service) {
            ConcurrentMap<Service, ServiceHandler> handlers = table.get(path);
            if (handlers != null) {
                return handlers.get(service);
            } else {
                return null;
            }
        }

        public void put(String path, Service service, ServiceHandler handler) {
            ConcurrentMap<Service, ServiceHandler> handlers = table.computeIfAbsent(
                path,
                k -> new ConcurrentHashMap<>()
            );

            handlers.put(service, handler);
        }

    }

}

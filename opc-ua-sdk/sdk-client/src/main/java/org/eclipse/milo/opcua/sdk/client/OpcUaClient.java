/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.api.ServiceFaultListener;
import org.eclipse.milo.opcua.sdk.client.api.UaClient;
import org.eclipse.milo.opcua.sdk.client.api.UaSession;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.nodes.AddressSpace;
import org.eclipse.milo.opcua.sdk.client.api.nodes.NodeCache;
import org.eclipse.milo.opcua.sdk.client.nodes.DefaultAddressSpace;
import org.eclipse.milo.opcua.sdk.client.nodes.DefaultNodeCache;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscriptionManager;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.UaServiceFaultException;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePath;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.eclipse.milo.opcua.stack.core.util.LongSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newCopyOnWriteArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;

public class OpcUaClient implements UaClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LongSequence requestHandles = new LongSequence(0, UInteger.MAX_VALUE);

    private final List<ServiceFaultListener> faultListeners = newCopyOnWriteArrayList();
    private final ExecutionQueue faultNotificationQueue;

    private final AddressSpace addressSpace;
    private final NodeCache nodeCache = new DefaultNodeCache();
    private final OpcUaSubscriptionManager subscriptionManager;

    private final UaTcpStackClient stackClient;
    private final ClientSessionManager sessionManager;

    private final OpcUaClientConfig config;

    public OpcUaClient(OpcUaClientConfig config) {
        this.config = config;

        sessionManager = new ClientSessionManager(this);

        stackClient = new UaTcpStackClient(config);

        faultNotificationQueue = new ExecutionQueue(config.getExecutor());

        addressSpace = new DefaultAddressSpace(this);
        subscriptionManager = new OpcUaSubscriptionManager(this);
    }

    @Override
    public OpcUaClientConfig getConfig() {
        return config;
    }

    public UaTcpStackClient getStackClient() {
        return stackClient;
    }

    @Override
    public NodeCache getNodeCache() {
        return nodeCache;
    }

    @Override
    public AddressSpace getAddressSpace() {
        return addressSpace;
    }

    /**
     * Build a new {@link RequestHeader} using a null authentication token.
     *
     * @return a new {@link RequestHeader} with a null authentication token.
     */
    public RequestHeader newRequestHeader() {
        return newRequestHeader(NodeId.NULL_VALUE);
    }

    /**
     * Build a new {@link RequestHeader} using {@code authToken}.
     *
     * @param authToken the authentication token (from the session) to use.
     * @return a new {@link RequestHeader}.
     */
    public RequestHeader newRequestHeader(NodeId authToken) {
        return new RequestHeader(
            authToken,
            DateTime.now(),
            uint(requestHandles.getAndIncrement()),
            uint(0),
            null,
            config.getRequestTimeout(),
            null);
    }

    /**
     * @return the next {@link UInteger} to use as a request handle.
     */
    public UInteger nextRequestHandle() {
        return uint(requestHandles.getAndIncrement());
    }

    @Override
    public CompletableFuture<UaClient> connect() {
        return stackClient.connect().thenCompose(
            c -> getSession().thenApply(s -> OpcUaClient.this));
    }

    @Override
    public CompletableFuture<UaClient> disconnect() {
        // Subscriptions must be cleared first, effectively stopping new
        // PublishRequests from being sent, otherwise continued PublishRequests
        // will initiate reconnection and re-activation.
        subscriptionManager.clearSubscriptions();

        return sessionManager
            .closeSession()
            .thenCompose(v -> stackClient.disconnect())
            .thenApply(c -> (UaClient) OpcUaClient.this)
            .exceptionally(ex -> OpcUaClient.this);
    }

    @Override
    public OpcUaSubscriptionManager getSubscriptionManager() {
        return subscriptionManager;
    }

    @Override
    public CompletableFuture<ReadResponse> read(double maxAge,
                                                TimestampsToReturn timestampsToReturn,
                                                List<ReadValueId> readValueIds) {

        return getSession().thenCompose(session -> {
            ReadRequest request = new ReadRequest(
                newRequestHeader(session.getAuthenticationToken()),
                maxAge,
                timestampsToReturn,
                a(readValueIds, ReadValueId.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<WriteResponse> write(List<WriteValue> writeValues) {
        return getSession().thenCompose(session -> {
            WriteRequest request = new WriteRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(writeValues, WriteValue.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<HistoryReadResponse> historyRead(HistoryReadDetails historyReadDetails,
                                                              TimestampsToReturn timestampsToReturn,
                                                              boolean releaseContinuationPoints,
                                                              List<HistoryReadValueId> nodesToRead) {

        return getSession().thenCompose(session -> {
            HistoryReadRequest request = new HistoryReadRequest(
                newRequestHeader(session.getAuthenticationToken()),
                ExtensionObject.encode(historyReadDetails),
                timestampsToReturn,
                releaseContinuationPoints,
                a(nodesToRead, HistoryReadValueId.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<HistoryUpdateResponse> historyUpdate(List<HistoryUpdateDetails> historyUpdateDetails) {
        return getSession().thenCompose(session -> {
            ExtensionObject[] details = historyUpdateDetails.stream()
                .map(ExtensionObject::encode)
                .toArray(ExtensionObject[]::new);

            HistoryUpdateRequest request = new HistoryUpdateRequest(
                newRequestHeader(session.getAuthenticationToken()),
                details);

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<BrowseResponse> browse(ViewDescription viewDescription,
                                                    UInteger maxReferencesPerNode,
                                                    List<BrowseDescription> nodesToBrowse) {

        return getSession().thenCompose(session -> {
            BrowseRequest request = new BrowseRequest(
                newRequestHeader(session.getAuthenticationToken()),
                viewDescription,
                maxReferencesPerNode,
                a(nodesToBrowse, BrowseDescription.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<BrowseNextResponse> browseNext(boolean releaseContinuationPoints,
                                                            List<ByteString> continuationPoints) {

        return getSession().thenCompose(session -> {
            BrowseNextRequest request = new BrowseNextRequest(
                newRequestHeader(session.getAuthenticationToken()),
                releaseContinuationPoints,
                a(continuationPoints, ByteString.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<TranslateBrowsePathsToNodeIdsResponse> translateBrowsePaths(List<BrowsePath> browsePaths) {
        return getSession().thenCompose(session -> {
            TranslateBrowsePathsToNodeIdsRequest request = new TranslateBrowsePathsToNodeIdsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(browsePaths, BrowsePath.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<RegisterNodesResponse> registerNodes(List<NodeId> nodesToRegister) {
        return getSession().thenCompose(session -> {
            RegisterNodesRequest request = new RegisterNodesRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(nodesToRegister, NodeId.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<UnregisterNodesResponse> unregisterNodes(List<NodeId> nodesToUnregister) {
        return getSession().thenCompose(session -> {
            UnregisterNodesRequest request = new UnregisterNodesRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(nodesToUnregister, NodeId.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<CallResponse> call(List<CallMethodRequest> methodsToCall) {
        return getSession().thenCompose(session -> {
            CallRequest request = new CallRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(methodsToCall, CallMethodRequest.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<CreateSubscriptionResponse> createSubscription(double requestedPublishingInterval,
                                                                            UInteger requestedLifetimeCount,
                                                                            UInteger requestedMaxKeepAliveCount,
                                                                            UInteger maxNotificationsPerPublish,
                                                                            boolean publishingEnabled,
                                                                            UByte priority) {

        return getSession().thenCompose(session -> {
            CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                newRequestHeader(session.getAuthenticationToken()),
                requestedPublishingInterval,
                requestedLifetimeCount,
                requestedMaxKeepAliveCount,
                maxNotificationsPerPublish,
                publishingEnabled,
                priority);

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<ModifySubscriptionResponse> modifySubscription(UInteger subscriptionId,
                                                                            double requestedPublishingInterval,
                                                                            UInteger requestedLifetimeCount,
                                                                            UInteger requestedMaxKeepAliveCount,
                                                                            UInteger maxNotificationsPerPublish,
                                                                            UByte priority) {

        return getSession().thenCompose(session -> {
            ModifySubscriptionRequest request = new ModifySubscriptionRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                requestedPublishingInterval,
                requestedLifetimeCount,
                requestedMaxKeepAliveCount,
                maxNotificationsPerPublish,
                priority);

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<DeleteSubscriptionsResponse> deleteSubscriptions(List<UInteger> subscriptionIds) {
        return getSession().thenCompose(session -> {
            DeleteSubscriptionsRequest request = new DeleteSubscriptionsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(subscriptionIds, UInteger.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<TransferSubscriptionsResponse> transferSubscriptions(List<UInteger> subscriptionIds,
                                                                                  boolean sendInitialValues) {

        return getSession().thenCompose(session -> {
            TransferSubscriptionsRequest request = new TransferSubscriptionsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(subscriptionIds, UInteger.class),
                sendInitialValues);

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<SetPublishingModeResponse> setPublishingMode(boolean publishingEnabled,
                                                                          List<UInteger> subscriptionIds) {

        return getSession().thenCompose(session -> {
            SetPublishingModeRequest request = new SetPublishingModeRequest(
                newRequestHeader(session.getAuthenticationToken()),
                publishingEnabled,
                a(subscriptionIds, UInteger.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<PublishResponse> publish(List<SubscriptionAcknowledgement> subscriptionAcknowledgements) {
        return getSession().thenCompose(session -> {
            PublishRequest request = new PublishRequest(
                newRequestHeader(session.getAuthenticationToken()),
                a(subscriptionAcknowledgements, SubscriptionAcknowledgement.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<RepublishResponse> republish(UInteger subscriptionId, UInteger retransmitSequenceNumber) {
        return getSession().thenCompose(session -> {
            RepublishRequest request = new RepublishRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                retransmitSequenceNumber);

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<CreateMonitoredItemsResponse> createMonitoredItems(
        UInteger subscriptionId,
        TimestampsToReturn timestampsToReturn,
        List<MonitoredItemCreateRequest> itemsToCreate) {

        return getSession().thenCompose(session -> {
            CreateMonitoredItemsRequest request = new CreateMonitoredItemsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                timestampsToReturn,
                a(itemsToCreate, MonitoredItemCreateRequest.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<ModifyMonitoredItemsResponse> modifyMonitoredItems(
        UInteger subscriptionId,
        TimestampsToReturn timestampsToReturn,
        List<MonitoredItemModifyRequest> itemsToModify) {

        return getSession().thenCompose(session -> {
            ModifyMonitoredItemsRequest request = new ModifyMonitoredItemsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                timestampsToReturn,
                a(itemsToModify, MonitoredItemModifyRequest.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<DeleteMonitoredItemsResponse> deleteMonitoredItems(UInteger subscriptionId,
                                                                                List<UInteger> monitoredItemIds) {

        return getSession().thenCompose(session -> {
            DeleteMonitoredItemsRequest request = new DeleteMonitoredItemsRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                a(monitoredItemIds, UInteger.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<SetMonitoringModeResponse> setMonitoringMode(UInteger subscriptionId,
                                                                          MonitoringMode monitoringMode,
                                                                          List<UInteger> monitoredItemIds) {

        return getSession().thenCompose(session -> {
            SetMonitoringModeRequest request = new SetMonitoringModeRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                monitoringMode,
                a(monitoredItemIds, UInteger.class));

            return sendRequest(request);
        });
    }

    @Override
    public CompletableFuture<SetTriggeringResponse> setTriggering(UInteger subscriptionId,
                                                                  UInteger triggeringItemId,
                                                                  List<UInteger> linksToAdd,
                                                                  List<UInteger> linksToRemove) {

        return getSession().thenCompose(session -> {
            SetTriggeringRequest request = new SetTriggeringRequest(
                newRequestHeader(session.getAuthenticationToken()),
                subscriptionId,
                triggeringItemId,
                a(linksToAdd, UInteger.class),
                a(linksToRemove, UInteger.class));

            return sendRequest(request);
        });
    }

    @Override
    public final CompletableFuture<UaSession> getSession() {
        return sessionManager.getSession().thenApply(s -> (UaSession) s);
    }

    @Override
    public <T extends UaResponseMessage> CompletableFuture<T> sendRequest(UaRequestMessage request) {
        CompletableFuture<T> f = stackClient.sendRequest(request);

        if (faultListeners.size() > 0) {
            f.whenComplete(this::maybeHandleServiceFault);
        }

        return f;
    }

    @Override
    public void sendRequests(List<? extends UaRequestMessage> requests,
                             List<CompletableFuture<? extends UaResponseMessage>> futures) {

        futures.forEach(f -> f.whenComplete(this::maybeHandleServiceFault));

        stackClient.sendRequests(requests, futures);
    }

    private void maybeHandleServiceFault(UaResponseMessage response, Throwable ex) {
        if (faultListeners.isEmpty()) return;

        if (ex != null) {
            if (ex instanceof UaServiceFaultException) {
                UaServiceFaultException faultException = (UaServiceFaultException) ex;
                ServiceFault serviceFault = faultException.getServiceFault();

                logger.debug("Notifying {} ServiceFaultListeners", faultListeners.size());

                faultNotificationQueue.submit(() ->
                    faultListeners.stream().forEach(h -> h.onServiceFault(serviceFault)));
            } else if (ex.getCause() instanceof UaServiceFaultException) {
                UaServiceFaultException faultException = (UaServiceFaultException) ex.getCause();
                ServiceFault serviceFault = faultException.getServiceFault();

                logger.debug("Notifying {} ServiceFaultListeners", faultListeners.size());

                faultNotificationQueue.submit(() ->
                    faultListeners.stream().forEach(h -> h.onServiceFault(serviceFault)));
            }
        }
    }

    public void addFaultListener(ServiceFaultListener faultListener) {
        faultListeners.add(faultListener);
        logger.debug("Added ServiceFaultListener: {}", faultListener);
    }

    public void removeFaultListener(ServiceFaultListener faultListener) {
        faultListeners.remove(faultListener);
        logger.debug("Removed ServiceFaultListener: {}", faultListener);
    }

    public void addSessionActivityListener(SessionActivityListener listener) {
        sessionManager.addListener(listener);
        logger.debug("Added SessionActivityListener: {}", listener);
    }

    public void removeSessionActivityListener(SessionActivityListener listener) {
        sessionManager.removeListener(listener);
        logger.debug("Removed SessionActivityListener: {}", listener);
    }

}

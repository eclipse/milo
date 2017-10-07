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

package org.eclipse.milo.opcua.sdk.server.subscriptions;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.AttributeManager.ReadContext;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.EventItem;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.items.BaseMonitoredItem;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredDataItem;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredEventItem;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription.State;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyResult;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.NotificationMessage;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionAcknowledgement;
import org.jooq.lambda.tuple.Tuple3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.sequence;

public class SubscriptionManager {

    private static final QualifiedName DEFAULT_BINARY_ENCODING = new QualifiedName(0, "DefaultBinary");
    private static final QualifiedName DEFAULT_XML_ENCODING = new QualifiedName(0, "DefaultXML");

    private static final AtomicLong SUBSCRIPTION_IDS = new AtomicLong(0L);

    private static UInteger nextSubscriptionId() {
        return uint(SUBSCRIPTION_IDS.incrementAndGet());
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<UInteger, StatusCode[]> acknowledgeResults = Maps.newConcurrentMap();

    private final PublishQueue publishQueue = new PublishQueue();

    private final Map<UInteger, Subscription> subscriptions = Maps.newConcurrentMap();
    private final List<Subscription> transferred = Lists.newCopyOnWriteArrayList();

    private final Session session;
    private final OpcUaServer server;

    public SubscriptionManager(Session session, OpcUaServer server) {
        this.session = session;
        this.server = server;
    }

    public Session getSession() {
        return session;
    }

    public PublishQueue getPublishQueue() {
        return publishQueue;
    }

    public OpcUaServer getServer() {
        return server;
    }

    public Subscription getSubscription(UInteger subscriptionId) {
        return subscriptions.get(subscriptionId);
    }

    public void createSubscription(ServiceRequest<CreateSubscriptionRequest, CreateSubscriptionResponse> service) {
        CreateSubscriptionRequest request = service.getRequest();

        UInteger subscriptionId = nextSubscriptionId();

        Subscription subscription = new Subscription(
            this,
            subscriptionId,
            request.getRequestedPublishingInterval(),
            request.getRequestedMaxKeepAliveCount().longValue(),
            request.getRequestedLifetimeCount().longValue(),
            request.getMaxNotificationsPerPublish().longValue(),
            request.getPublishingEnabled(),
            request.getPriority().intValue()
        );

        subscriptions.put(subscriptionId, subscription);
        server.getSubscriptions().put(subscriptionId, subscription);

        subscription.setStateListener((s, ps, cs) -> {
            if (cs == State.Closed) {
                subscriptions.remove(s.getId());
                server.getSubscriptions().remove(s.getId());
            }
        });

        subscription.startPublishingTimer();

        ResponseHeader header = service.createResponseHeader();

        CreateSubscriptionResponse response = new CreateSubscriptionResponse(
            header, subscriptionId,
            subscription.getPublishingInterval(),
            uint(subscription.getLifetimeCount()),
            uint(subscription.getMaxKeepAliveCount())
        );

        service.setResponse(response);
    }

    public void modifySubscription(ServiceRequest<ModifySubscriptionRequest, ModifySubscriptionResponse> service) {
        ModifySubscriptionRequest request = service.getRequest();
        UInteger subscriptionId = request.getSubscriptionId();

        try {
            Subscription subscription = subscriptions.get(subscriptionId);

            if (subscription == null) {
                throw new UaException(StatusCodes.Bad_SubscriptionIdInvalid);
            }

            subscription.modifySubscription(request);

            ResponseHeader header = service.createResponseHeader();

            ModifySubscriptionResponse response = new ModifySubscriptionResponse(
                header,
                subscription.getPublishingInterval(),
                uint(subscription.getLifetimeCount()),
                uint(subscription.getMaxKeepAliveCount())
            );

            service.setResponse(response);
        } catch (UaException e) {
            service.setServiceFault(e);
        }
    }

    public void deleteSubscription(ServiceRequest<DeleteSubscriptionsRequest, DeleteSubscriptionsResponse> service) {
        DeleteSubscriptionsRequest request = service.getRequest();
        List<UInteger> subscriptionIds = l(request.getSubscriptionIds());

        if (subscriptionIds.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        StatusCode[] results = new StatusCode[subscriptionIds.size()];

        for (int i = 0; i < subscriptionIds.size(); i++) {
            Subscription subscription = subscriptions.remove(subscriptionIds.get(i));

            if (subscription != null) {
                List<BaseMonitoredItem<?>> deletedItems = subscription.deleteSubscription();

                /*
                * Notify namespaces of the items we just deleted.
                */

                Map<UShort, List<BaseMonitoredItem<?>>> byNamespace = deletedItems.stream()
                    .collect(Collectors.groupingBy(item -> item.getReadValueId().getNodeId().getNamespaceIndex()));

                byNamespace.entrySet().forEach(entry -> {
                    UShort namespaceIndex = entry.getKey();

                    List<BaseMonitoredItem<?>> items = entry.getValue();
                    List<DataItem> dataItems = Lists.newArrayList();
                    List<EventItem> eventItems = Lists.newArrayList();


                    for (BaseMonitoredItem<?> item : items) {
                        if (item instanceof MonitoredDataItem) {
                            dataItems.add((DataItem) item);
                        } else if (item instanceof MonitoredEventItem) {
                            eventItems.add((EventItem) item);
                        }
                    }

                    if (!dataItems.isEmpty()) {
                        server.getNamespaceManager().getNamespace(namespaceIndex).onDataItemsDeleted(dataItems);
                    }
                    if (!eventItems.isEmpty()) {
                        server.getNamespaceManager().getNamespace(namespaceIndex).onEventItemsDeleted(eventItems);
                    }
                });

                results[i] = StatusCode.GOOD;
            } else {
                results[i] = new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid);
            }
        }

        ResponseHeader header = service.createResponseHeader();
        DeleteSubscriptionsResponse response = new DeleteSubscriptionsResponse(
            header, results, new DiagnosticInfo[0]);

        service.setResponse(response);

        while (subscriptions.isEmpty() && publishQueue.isNotEmpty()) {
            ServiceRequest<PublishRequest, PublishResponse> publishService = publishQueue.poll();
            if (publishService != null) {
                publishService.setServiceFault(StatusCodes.Bad_NoSubscription);
            }
        }
    }

    public void setPublishingMode(ServiceRequest<SetPublishingModeRequest, SetPublishingModeResponse> service) {
        SetPublishingModeRequest request = service.getRequest();
        List<UInteger> subscriptionIds = l(request.getSubscriptionIds());

        StatusCode[] results = new StatusCode[subscriptionIds.size()];

        for (int i = 0; i < subscriptionIds.size(); i++) {
            Subscription subscription = subscriptions.get(subscriptionIds.get(i));
            if (subscription == null) {
                results[i] = new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid);
            } else {
                subscription.setPublishingMode(request);
                results[i] = StatusCode.GOOD;
            }
        }

        ResponseHeader header = service.createResponseHeader();
        SetPublishingModeResponse response = new SetPublishingModeResponse(
            header, results, new DiagnosticInfo[0]);

        service.setResponse(response);
    }

    public void createMonitoredItems(
        ServiceRequest<CreateMonitoredItemsRequest, CreateMonitoredItemsResponse> service) {

        CreateMonitoredItemsRequest request = service.getRequest();
        UInteger subscriptionId = request.getSubscriptionId();

        try {
            Subscription subscription = subscriptions.get(subscriptionId);
            TimestampsToReturn timestamps = service.getRequest().getTimestampsToReturn();
            List<MonitoredItemCreateRequest> itemsToCreate = l(service.getRequest().getItemsToCreate());

            if (subscription == null) {
                throw new UaException(StatusCodes.Bad_SubscriptionIdInvalid);
            }
            if (timestamps == null) {
                throw new UaException(StatusCodes.Bad_TimestampsToReturnInvalid);
            }
            if (itemsToCreate.isEmpty()) {
                throw new UaException(StatusCodes.Bad_NothingToDo);
            }

            List<BaseMonitoredItem<?>> createdItems =
                Collections.synchronizedList(newArrayListWithCapacity(itemsToCreate.size()));

            List<PendingItemCreation> pending = itemsToCreate.stream()
                .map(PendingItemCreation::new)
                .collect(toList());

            for (PendingItemCreation p : pending) {
                MonitoredItemCreateRequest r = p.getRequest();
                NodeId nodeId = r.getItemToMonitor().getNodeId();
                UInteger attributeId = r.getItemToMonitor().getAttributeId();
                QualifiedName dataEncoding = r.getItemToMonitor().getDataEncoding();

                if (!AttributeId.isValid(attributeId)) {
                    MonitoredItemCreateResult result = new MonitoredItemCreateResult(
                        new StatusCode(StatusCodes.Bad_AttributeIdInvalid),
                        uint(0), 0d, uint(0), null);

                    p.getResultFuture().complete(result);
                    continue;
                }

                if (dataEncoding.isNotNull()) {
                    if (!AttributeId.Value.isEqual(attributeId)) {
                        MonitoredItemCreateResult result = new MonitoredItemCreateResult(
                            new StatusCode(StatusCodes.Bad_DataEncodingInvalid),
                            uint(0), 0d, uint(0), null);

                        p.getResultFuture().complete(result);
                        continue;
                    }
                    if (!dataEncoding.equals(DEFAULT_BINARY_ENCODING) &&
                        !dataEncoding.equals(DEFAULT_XML_ENCODING)) {
                        MonitoredItemCreateResult result = new MonitoredItemCreateResult(
                            new StatusCode(StatusCodes.Bad_DataEncodingUnsupported),
                            uint(0), 0d, uint(0), null);

                        p.getResultFuture().complete(result);
                        continue;
                    }
                }

                Namespace namespace = server.getNamespaceManager().getNamespace(nodeId.getNamespaceIndex());

                if (attributeId.equals(AttributeId.EventNotifier.uid())) {
                    readEventAttributes(namespace, nodeId).thenAccept(as -> {
                        Optional<UByte> eventNotifier = as.v3();

                        try {
                            if (!eventNotifier.isPresent()) {
                                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
                            }

                            MonitoredEventItem item = new MonitoredEventItem(
                                uint(subscription.nextItemId()),
                                subscriptionId,
                                r.getItemToMonitor(),
                                r.getMonitoringMode(),
                                timestamps,
                                r.getRequestedParameters().getClientHandle(),
                                0.0,
                                r.getRequestedParameters().getQueueSize(),
                                r.getRequestedParameters().getDiscardOldest(),
                                r.getRequestedParameters().getFilter());

                            createdItems.add(item);

                            MonitoredItemCreateResult result = new MonitoredItemCreateResult(
                                StatusCode.GOOD,
                                item.getId(),
                                item.getSamplingInterval(),
                                uint(item.getQueueSize()),
                                item.getFilterResult());

                            p.getResultFuture().complete(result);
                        } catch (UaException e) {
                            MonitoredItemCreateResult result =
                                new MonitoredItemCreateResult(e.getStatusCode(), uint(0), 0d, uint(0), null);

                            p.getResultFuture().complete(result);
                        }
                    });
                } else {
                    readDataAttributes(session, namespace, nodeId).thenAccept(vs -> {
                        try {
                            for (DataValue value : vs) {
                                StatusCode statusCode = value.getStatusCode();

                                if (statusCode.getValue() == StatusCodes.Bad_NodeIdInvalid ||
                                    statusCode.getValue() == StatusCodes.Bad_NodeIdUnknown) {
                                    throw new UaException(statusCode);
                                }
                            }

                            UByte accessLevel = Optional.ofNullable(
                                (UByte) vs.get(0).getValue().getValue()).orElse(ubyte(1));

                            UByte userAccessLevel = Optional.ofNullable(
                                (UByte) vs.get(1).getValue().getValue()).orElse(ubyte(1));

                            Double minimumSamplingInterval = Optional.ofNullable(
                                (Double) vs.get(2).getValue().getValue()).orElse(0.0);

                            EnumSet<AccessLevel> accessLevels = AccessLevel.fromMask(accessLevel);
                            EnumSet<AccessLevel> userAccessLevels = AccessLevel.fromMask(userAccessLevel);

                            double samplingInterval = r.getRequestedParameters().getSamplingInterval();
                            double minSupportedSampleRate = server.getConfig().getLimits().getMinSupportedSampleRate();
                            double maxSupportedSampleRate = server.getConfig().getLimits().getMaxSupportedSampleRate();

                            if (samplingInterval < 0) samplingInterval = subscription.getPublishingInterval();
                            if (samplingInterval < minimumSamplingInterval) samplingInterval = minimumSamplingInterval;
                            if (samplingInterval < minSupportedSampleRate) samplingInterval = minSupportedSampleRate;
                            if (samplingInterval > maxSupportedSampleRate) samplingInterval = maxSupportedSampleRate;

                            if (!accessLevels.contains(AccessLevel.CurrentRead)) {
                                throw new UaException(StatusCodes.Bad_NotReadable);
                            }
                            if (!userAccessLevels.contains(AccessLevel.CurrentRead)) {
                                throw new UaException(StatusCodes.Bad_UserAccessDenied);
                            }

                            String indexRange = r.getItemToMonitor().getIndexRange();
                            if (indexRange != null) NumericRange.parse(indexRange);

                            MonitoredDataItem item = new MonitoredDataItem(
                                uint(subscription.nextItemId()),
                                subscriptionId,
                                r.getItemToMonitor(),
                                r.getMonitoringMode(),
                                timestamps,
                                r.getRequestedParameters().getClientHandle(),
                                samplingInterval,
                                r.getRequestedParameters().getFilter(),
                                r.getRequestedParameters().getQueueSize(),
                                r.getRequestedParameters().getDiscardOldest());

                            createdItems.add(item);

                            MonitoredItemCreateResult result = new MonitoredItemCreateResult(
                                StatusCode.GOOD,
                                item.getId(),
                                item.getSamplingInterval(),
                                uint(item.getQueueSize()),
                                item.getFilterResult());

                            p.getResultFuture().complete(result);
                        } catch (Throwable t) {
                            StatusCode statusCode = UaException.extract(t)
                                .map(UaException::getStatusCode)
                                .orElse(StatusCode.BAD);

                            MonitoredItemCreateResult result =
                                new MonitoredItemCreateResult(statusCode, uint(0), 0d, uint(0), null);

                            p.getResultFuture().complete(result);
                        }
                    });
                }
            }

            List<CompletableFuture<MonitoredItemCreateResult>> futures = pending.stream()
                .map(PendingItemCreation::getResultFuture)
                .collect(toList());

            sequence(futures).thenAccept(results -> {
                subscription.addMonitoredItems(createdItems);

                // Notify namespaces of the items we just created.
                Map<UShort, List<BaseMonitoredItem<?>>> byNamespace = createdItems.stream()
                    .collect(Collectors.groupingBy(item -> item.getReadValueId().getNodeId().getNamespaceIndex()));

                byNamespace.entrySet().forEach(entry -> {
                    UShort namespaceIndex = entry.getKey();

                    List<BaseMonitoredItem<?>> items = entry.getValue();
                    List<DataItem> dataItems = Lists.newArrayList();
                    List<EventItem> eventItems = Lists.newArrayList();

                    for (BaseMonitoredItem<?> item : items) {
                        if (item instanceof MonitoredDataItem) {
                            dataItems.add((DataItem) item);
                        } else if (item instanceof MonitoredEventItem) {
                            eventItems.add((EventItem) item);
                        }
                    }

                    if (!dataItems.isEmpty()) {
                        server.getNamespaceManager().getNamespace(namespaceIndex).onDataItemsCreated(dataItems);
                    }
                    if (!eventItems.isEmpty()) {
                        server.getNamespaceManager().getNamespace(namespaceIndex).onEventItemsCreated(eventItems);
                    }
                });

                ResponseHeader header = service.createResponseHeader();

                CreateMonitoredItemsResponse response = new CreateMonitoredItemsResponse(
                    header, a(results, MonitoredItemCreateResult.class), new DiagnosticInfo[0]);

                service.setResponse(response);
            });
        } catch (UaException e) {
            service.setServiceFault(e);
        }
    }

    public void modifyMonitoredItems(
        ServiceRequest<ModifyMonitoredItemsRequest, ModifyMonitoredItemsResponse> service) {

        ModifyMonitoredItemsRequest request = service.getRequest();
        UInteger subscriptionId = request.getSubscriptionId();

        try {
            Subscription subscription = subscriptions.get(subscriptionId);
            TimestampsToReturn timestamps = service.getRequest().getTimestampsToReturn();
            List<MonitoredItemModifyRequest> itemsToModify = l(service.getRequest().getItemsToModify());

            if (subscription == null) {
                throw new UaException(StatusCodes.Bad_SubscriptionIdInvalid);
            }
            if (timestamps == null) {
                throw new UaException(StatusCodes.Bad_TimestampsToReturnInvalid);
            }
            if (itemsToModify.isEmpty()) {
                throw new UaException(StatusCodes.Bad_NothingToDo);
            }

            List<PendingItemModification> pending = itemsToModify.stream()
                .map(PendingItemModification::new)
                .collect(toList());

            List<BaseMonitoredItem<?>> modifiedItems =
                Collections.synchronizedList(newArrayListWithCapacity(itemsToModify.size()));

            /*
             * Modify requested items and prepare results.
             */

            for (PendingItemModification p : pending) {
                MonitoredItemModifyRequest r = p.getRequest();
                UInteger itemId = r.getMonitoredItemId();
                MonitoringParameters parameters = r.getRequestedParameters();

                BaseMonitoredItem<?> item = subscription.getMonitoredItems().get(itemId);

                if (item == null) {
                    MonitoredItemModifyResult result = new MonitoredItemModifyResult(
                        new StatusCode(StatusCodes.Bad_MonitoredItemIdInvalid),
                        0d, uint(0), null);

                    p.getResultFuture().complete(result);
                } else {
                    NodeId nodeId = item.getReadValueId().getNodeId();
                    Namespace namespace = server.getNamespaceManager().getNamespace(nodeId.getNamespaceIndex());

                    readDataAttributes(session, namespace, nodeId).thenAccept(vs -> {
                        try {
                            for (DataValue value : vs) {
                                StatusCode statusCode = value.getStatusCode();

                                if (statusCode.getValue() == StatusCodes.Bad_NodeIdInvalid ||
                                    statusCode.getValue() == StatusCodes.Bad_NodeIdUnknown) {
                                    throw new UaException(statusCode);
                                }
                            }

                            UByte accessLevel = Optional.ofNullable(
                                (UByte) vs.get(0).getValue().getValue()).orElse(ubyte(1));

                            UByte userAccessLevel = Optional.ofNullable(
                                (UByte) vs.get(1).getValue().getValue()).orElse(ubyte(1));

                            Double minimumSamplingInterval = Optional.ofNullable(
                                (Double) vs.get(2).getValue().getValue()).orElse(0.0);

                            EnumSet<AccessLevel> accessLevels = AccessLevel.fromMask(accessLevel);
                            EnumSet<AccessLevel> userAccessLevels = AccessLevel.fromMask(userAccessLevel);

                            double samplingInterval = parameters.getSamplingInterval();
                            double minSupportedSampleRate = server.getConfig().getLimits().getMinSupportedSampleRate();
                            double maxSupportedSampleRate = server.getConfig().getLimits().getMaxSupportedSampleRate();

                            if (samplingInterval < 0) samplingInterval = subscription.getPublishingInterval();
                            if (samplingInterval < minimumSamplingInterval) samplingInterval = minimumSamplingInterval;
                            if (samplingInterval < minSupportedSampleRate) samplingInterval = minSupportedSampleRate;
                            if (samplingInterval > maxSupportedSampleRate) samplingInterval = maxSupportedSampleRate;

                            item.modify(
                                timestamps,
                                parameters.getClientHandle(),
                                samplingInterval,
                                parameters.getFilter(),
                                parameters.getQueueSize(),
                                parameters.getDiscardOldest());

                            modifiedItems.add(item);

                            MonitoredItemModifyResult result = new MonitoredItemModifyResult(
                                StatusCode.GOOD,
                                item.getSamplingInterval(),
                                uint(item.getQueueSize()),
                                item.getFilterResult());

                            p.getResultFuture().complete(result);
                        } catch (Throwable t) {
                            StatusCode statusCode = UaException.extract(t)
                                .map(UaException::getStatusCode)
                                .orElse(StatusCode.BAD);

                            MonitoredItemModifyResult result = new MonitoredItemModifyResult(
                                statusCode,
                                item.getSamplingInterval(),
                                uint(item.getQueueSize()),
                                item.getFilterResult());

                            p.getResultFuture().complete(result);
                        }
                    });
                }
            }

            subscription.resetLifetimeCounter();

            /*
             * Notify namespaces of the items we just modified.
             */

            List<CompletableFuture<MonitoredItemModifyResult>> futures = pending.stream()
                .map(PendingItemModification::getResultFuture)
                .collect(toList());

            sequence(futures).thenAccept(results -> {
                Map<UShort, List<BaseMonitoredItem<?>>> byNamespace = modifiedItems.stream()
                    .collect(Collectors.groupingBy(item -> item.getReadValueId().getNodeId().getNamespaceIndex()));

                byNamespace.entrySet().forEach(entry -> {
                    UShort namespaceIndex = entry.getKey();

                    List<BaseMonitoredItem<?>> items = entry.getValue();
                    List<DataItem> dataItems = Lists.newArrayList();
                    List<EventItem> eventItems = Lists.newArrayList();


                    for (BaseMonitoredItem<?> item : items) {
                        if (item instanceof MonitoredDataItem) {
                            dataItems.add((DataItem) item);
                        } else if (item instanceof MonitoredEventItem) {
                            eventItems.add((EventItem) item);
                        }
                    }

                    if (!dataItems.isEmpty()) {
                        server.getNamespaceManager().getNamespace(namespaceIndex).onDataItemsModified(dataItems);
                    }
                    if (!eventItems.isEmpty()) {
                        server.getNamespaceManager().getNamespace(namespaceIndex).onEventItemsModified(eventItems);
                    }
                });

                /*
                 * Namespaces have been notified; send response.
                 */

                ResponseHeader header = service.createResponseHeader();
                ModifyMonitoredItemsResponse response = new ModifyMonitoredItemsResponse(
                    header, a(results, MonitoredItemModifyResult.class), new DiagnosticInfo[0]);

                service.setResponse(response);
            });
        } catch (UaException e) {
            service.setServiceFault(e);
        }
    }

    private CompletableFuture<List<DataValue>> readDataAttributes(Session session, Namespace namespace, NodeId itemId) {
        Function<AttributeId, ReadValueId> f = id ->
            new ReadValueId(itemId, id.uid(), null, QualifiedName.NULL_VALUE);

        CompletableFuture<List<DataValue>> future = new CompletableFuture<>();

        ReadContext readContext = new ReadContext(
            server, session, future, new DiagnosticsContext<>());

        List<ReadValueId> attributes = newArrayList(
            f.apply(AttributeId.AccessLevel),
            f.apply(AttributeId.UserAccessLevel),
            f.apply(AttributeId.MinimumSamplingInterval));

        namespace.read(readContext, 0.0, TimestampsToReturn.Neither, attributes);

        return future;
    }

    private CompletableFuture<EventAttributes> readEventAttributes(Namespace namespace, NodeId nodeId) {
        Function<AttributeId, ReadValueId> f = id ->
            new ReadValueId(nodeId, id.uid(), null, QualifiedName.NULL_VALUE);

        CompletableFuture<List<DataValue>> future = new CompletableFuture<>();

        ReadContext readContext = new ReadContext(
            server, null, future, new DiagnosticsContext<>());

        List<ReadValueId> readValueIds = newArrayList(
            f.apply(AttributeId.AccessLevel),
            f.apply(AttributeId.UserAccessLevel),
            f.apply(AttributeId.EventNotifier));

        namespace.read(readContext, 0.0, TimestampsToReturn.Neither, readValueIds);

        return future.thenApply(values -> {
            UByte accessLevel = Optional.ofNullable((UByte) values.get(0).getValue().getValue()).orElse(ubyte(1));
            UByte userAccessLevel = Optional.ofNullable((UByte) values.get(1).getValue().getValue()).orElse(ubyte(1));
            Optional<UByte> eventNotifier = Optional.ofNullable((UByte) values.get(2).getValue().getValue());

            return new EventAttributes(
                AccessLevel.fromMask(accessLevel),
                AccessLevel.fromMask(userAccessLevel),
                eventNotifier);
        });
    }

    private static class EventAttributes extends Tuple3<EnumSet<AccessLevel>, EnumSet<AccessLevel>, Optional<UByte>> {
        public EventAttributes(EnumSet<AccessLevel> v1, EnumSet<AccessLevel> v2, Optional<UByte> v3) {
            super(v1, v2, v3);
        }
    }

    public void deleteMonitoredItems(
        ServiceRequest<DeleteMonitoredItemsRequest, DeleteMonitoredItemsResponse> service) {

        DeleteMonitoredItemsRequest request = service.getRequest();
        UInteger subscriptionId = request.getSubscriptionId();

        try {
            Subscription subscription = subscriptions.get(subscriptionId);
            List<UInteger> itemsToDelete = l(service.getRequest().getMonitoredItemIds());

            if (subscription == null) {
                throw new UaException(StatusCodes.Bad_SubscriptionIdInvalid);
            }
            if (itemsToDelete.isEmpty()) {
                throw new UaException(StatusCodes.Bad_NothingToDo);
            }

            StatusCode[] deleteResults = new StatusCode[itemsToDelete.size()];
            List<BaseMonitoredItem<?>> deletedItems = newArrayListWithCapacity(itemsToDelete.size());

            synchronized (subscription) {
                for (int i = 0; i < itemsToDelete.size(); i++) {
                    UInteger itemId = itemsToDelete.get(i);
                    BaseMonitoredItem<?> item = subscription.getMonitoredItems().get(itemId);

                    if (item == null) {
                        deleteResults[i] = new StatusCode(StatusCodes.Bad_MonitoredItemIdInvalid);
                    } else {
                        deletedItems.add(item);

                        deleteResults[i] = StatusCode.GOOD;
                    }
                }

                subscription.removeMonitoredItems(deletedItems);
            }

            /*
             * Notify namespaces of the items that have been deleted.
             */

            Map<UShort, List<BaseMonitoredItem<?>>> byNamespace = deletedItems.stream()
                .collect(Collectors.groupingBy(item -> item.getReadValueId().getNodeId().getNamespaceIndex()));

            byNamespace.entrySet().forEach(entry -> {
                UShort namespaceIndex = entry.getKey();

                List<BaseMonitoredItem<?>> items = entry.getValue();
                List<DataItem> dataItems = Lists.newArrayList();
                List<EventItem> eventItems = Lists.newArrayList();

                for (BaseMonitoredItem<?> item : items) {
                    if (item instanceof MonitoredDataItem) {
                        dataItems.add((DataItem) item);
                    } else if (item instanceof MonitoredEventItem) {
                        eventItems.add((EventItem) item);
                    }
                }

                if (!dataItems.isEmpty()) {
                    server.getNamespaceManager().getNamespace(namespaceIndex).onDataItemsDeleted(dataItems);
                }
                if (!eventItems.isEmpty()) {
                    server.getNamespaceManager().getNamespace(namespaceIndex).onEventItemsDeleted(eventItems);
                }
            });

            /*
             * Build and return results.
             */
            ResponseHeader header = service.createResponseHeader();
            DeleteMonitoredItemsResponse response = new DeleteMonitoredItemsResponse(
                header, deleteResults, new DiagnosticInfo[0]);

            service.setResponse(response);
        } catch (UaException e) {
            service.setServiceFault(e);
        }
    }

    public void setMonitoringMode(ServiceRequest<SetMonitoringModeRequest, SetMonitoringModeResponse> service) {
        SetMonitoringModeRequest request = service.getRequest();
        UInteger subscriptionId = request.getSubscriptionId();

        try {
            Subscription subscription = subscriptions.get(subscriptionId);
            List<UInteger> itemsToModify = l(request.getMonitoredItemIds());

            if (subscription == null) {
                throw new UaException(StatusCodes.Bad_SubscriptionIdInvalid);
            }
            if (itemsToModify.isEmpty()) {
                throw new UaException(StatusCodes.Bad_NothingToDo);
            }

            /*
             * Set MonitoringMode on each monitored item, if it exists.
             */

            MonitoringMode monitoringMode = request.getMonitoringMode();
            StatusCode[] results = new StatusCode[itemsToModify.size()];
            List<BaseMonitoredItem<?>> modified = newArrayListWithCapacity(itemsToModify.size());

            for (int i = 0; i < itemsToModify.size(); i++) {
                UInteger itemId = itemsToModify.get(i);
                BaseMonitoredItem<?> item = subscription.getMonitoredItems().get(itemId);

                if (item != null) {
                    item.setMonitoringMode(monitoringMode);

                    modified.add(item);

                    results[i] = StatusCode.GOOD;
                } else {
                    results[i] = new StatusCode(StatusCodes.Bad_MonitoredItemIdInvalid);
                }
            }

            /*
             * Notify namespaces of the items whose MonitoringMode has been modified.
             */

            Map<UShort, List<MonitoredItem>> byNamespace = modified.stream()
                .collect(Collectors.groupingBy(item -> item.getReadValueId().getNodeId().getNamespaceIndex()));

            byNamespace.keySet().forEach(namespaceIndex -> {
                List<MonitoredItem> items = byNamespace.get(namespaceIndex);
                server.getNamespaceManager().getNamespace(namespaceIndex).onMonitoringModeChanged(items);
            });

            /*
             * Build and return results.
             */

            ResponseHeader header = service.createResponseHeader();
            SetMonitoringModeResponse response = new SetMonitoringModeResponse(
                header, results, new DiagnosticInfo[0]);

            service.setResponse(response);
        } catch (UaException e) {
            service.setServiceFault(e);
        }
    }

    public void publish(ServiceRequest<PublishRequest, PublishResponse> service) {
        PublishRequest request = service.getRequest();

        if (!transferred.isEmpty()) {
            Subscription subscription = transferred.remove(0);
            subscription.returnStatusChangeNotification(service);
            return;
        }

        if (subscriptions.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NoSubscription);
            return;
        }

        SubscriptionAcknowledgement[] acknowledgements = request.getSubscriptionAcknowledgements();

        if (acknowledgements != null) {
            StatusCode[] results = new StatusCode[acknowledgements.length];

            for (int i = 0; i < acknowledgements.length; i++) {
                SubscriptionAcknowledgement acknowledgement = acknowledgements[i];

                UInteger sequenceNumber = acknowledgement.getSequenceNumber();
                UInteger subscriptionId = acknowledgement.getSubscriptionId();

                logger.debug("Acknowledging sequenceNumber={} on subscriptionId={}", sequenceNumber, subscriptionId);

                Subscription subscription = subscriptions.get(subscriptionId);

                if (subscription == null) {
                    results[i] = new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid);
                } else {
                    results[i] = subscription.acknowledge(sequenceNumber);
                }
            }

            acknowledgeResults.put(request.getRequestHeader().getRequestHandle(), results);
        }

        publishQueue.addRequest(service);
    }

    public void republish(ServiceRequest<RepublishRequest, RepublishResponse> service) {
        RepublishRequest request = service.getRequest();

        if (subscriptions.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_SubscriptionIdInvalid);
            return;
        }

        UInteger subscriptionId = request.getSubscriptionId();
        Subscription subscription = subscriptions.get(subscriptionId);

        if (subscription == null) {
            service.setServiceFault(StatusCodes.Bad_SubscriptionIdInvalid);
            return;
        }

        UInteger sequenceNumber = request.getRetransmitSequenceNumber();
        NotificationMessage notificationMessage = subscription.republish(sequenceNumber);

        if (notificationMessage == null) {
            service.setServiceFault(StatusCodes.Bad_MessageNotAvailable);
            return;
        }

        ResponseHeader header = service.createResponseHeader();
        RepublishResponse response = new RepublishResponse(header, notificationMessage);

        service.setResponse(response);
    }

    public void setTriggering(ServiceRequest<SetTriggeringRequest, SetTriggeringResponse> service) {
        SetTriggeringRequest request = service.getRequest();

        UInteger subscriptionId = request.getSubscriptionId();
        Subscription subscription = subscriptions.get(subscriptionId);

        if (subscription == null) {
            service.setServiceFault(StatusCodes.Bad_SubscriptionIdInvalid);
            return;
        }

        UInteger triggerId = request.getTriggeringItemId();
        List<UInteger> linksToAdd = l(request.getLinksToAdd());
        List<UInteger> linksToRemove = l(request.getLinksToRemove());

        if (linksToAdd.isEmpty() && linksToRemove.isEmpty()) {
            service.setServiceFault(StatusCodes.Bad_NothingToDo);
            return;
        }

        synchronized (subscription) {
            Map<UInteger, BaseMonitoredItem<?>> itemsById = subscription.getMonitoredItems();

            BaseMonitoredItem<?> triggerItem = itemsById.get(triggerId);
            if (triggerItem == null) {
                service.setServiceFault(StatusCodes.Bad_MonitoredItemIdInvalid);
                return;
            }

            List<StatusCode> removeResults = linksToRemove.stream()
                .map(linkedItemId -> {
                    BaseMonitoredItem<?> item = itemsById.get(linkedItemId);
                    if (item != null) {
                        if (triggerItem.getTriggeredItems().remove(linkedItemId) != null) {
                            return StatusCode.GOOD;
                        } else {
                            return new StatusCode(StatusCodes.Bad_MonitoredItemIdInvalid);
                        }
                    } else {
                        return new StatusCode(StatusCodes.Bad_MonitoredItemIdInvalid);
                    }
                })
                .collect(toList());

            List<StatusCode> addResults = linksToAdd.stream()
                .map(linkedItemId -> {
                    BaseMonitoredItem<?> linkedItem = itemsById.get(linkedItemId);
                    if (linkedItem != null) {
                        triggerItem.getTriggeredItems().put(linkedItemId, linkedItem);
                        return StatusCode.GOOD;
                    } else {
                        return new StatusCode(StatusCodes.Bad_MonitoredItemIdInvalid);
                    }
                })
                .collect(toList());

            SetTriggeringResponse response = new SetTriggeringResponse(
                service.createResponseHeader(),
                addResults.toArray(new StatusCode[addResults.size()]),
                new DiagnosticInfo[0],
                removeResults.toArray(new StatusCode[removeResults.size()]),
                new DiagnosticInfo[0]
            );

            service.setResponse(response);
        }
    }

    public void sessionClosed(boolean deleteSubscriptions) {
        Iterator<Subscription> iterator = subscriptions.values().iterator();

        while (iterator.hasNext()) {
            Subscription s = iterator.next();
            s.setStateListener(null);

            if (deleteSubscriptions) {
                server.getSubscriptions().remove(s.getId());
            }

            iterator.remove();
        }
    }

    public Subscription removeSubscription(UInteger subscriptionId) {
        Subscription subscription = subscriptions.remove(subscriptionId);
        if (subscription != null) subscription.setStateListener(null);
        return subscription;
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.put(subscription.getId(), subscription);

        subscription.setStateListener((s, ps, cs) -> {
            if (cs == State.Closed) {
                subscriptions.remove(s.getId());
                server.getSubscriptions().remove(s.getId());
            }
        });
    }

    StatusCode[] getAcknowledgeResults(UInteger requestHandle) {
        return acknowledgeResults.remove(requestHandle);
    }

    public void sendStatusChangeNotification(Subscription subscription) {
        ServiceRequest<PublishRequest, PublishResponse> service = publishQueue.poll();

        if (service != null) {
            subscription.returnStatusChangeNotification(service);
        } else {
            transferred.add(subscription);
        }
    }

}

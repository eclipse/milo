/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.subscriptions;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.NumericRange;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.EventItem;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices.ReadContext;
import org.eclipse.milo.opcua.sdk.server.items.BaseMonitoredItem;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredDataItem;
import org.eclipse.milo.opcua.sdk.server.items.MonitoredEventItem;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription.State;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
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
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

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

    public void createSubscription(ServiceRequest service) {
        CreateSubscriptionRequest request = (CreateSubscriptionRequest) service.getRequest();

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

    public void modifySubscription(ServiceRequest service) throws UaException {
        ModifySubscriptionRequest request = (ModifySubscriptionRequest) service.getRequest();

        UInteger subscriptionId = request.getSubscriptionId();
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
    }

    public void deleteSubscription(ServiceRequest service) throws UaException {
        DeleteSubscriptionsRequest request = (DeleteSubscriptionsRequest) service.getRequest();

        List<UInteger> subscriptionIds = l(request.getSubscriptionIds());

        if (subscriptionIds.isEmpty()) {
            throw new UaException(StatusCodes.Bad_NothingToDo);
        }

        StatusCode[] results = new StatusCode[subscriptionIds.size()];

        for (int i = 0; i < subscriptionIds.size(); i++) {
            UInteger subscriptionId = subscriptionIds.get(i);
            Subscription subscription = subscriptions.remove(subscriptionId);

            if (subscription != null) {
                server.getSubscriptions().remove(subscription.getId());

                List<BaseMonitoredItem<?>> deletedItems = subscription.deleteSubscription();

                /*
                 * Notify AddressSpaces of the items we just deleted.
                 */

                byMonitoredItemType(
                    deletedItems,
                    dataItems -> server.getAddressSpaceManager().onDataItemsDeleted(dataItems),
                    eventItems -> server.getAddressSpaceManager().onEventItemsDeleted(eventItems)
                );

                results[i] = StatusCode.GOOD;
            } else {
                results[i] = new StatusCode(StatusCodes.Bad_SubscriptionIdInvalid);
            }
        }

        ResponseHeader header = service.createResponseHeader();

        DeleteSubscriptionsResponse response = new DeleteSubscriptionsResponse(
            header,
            results,
            new DiagnosticInfo[0]
        );

        service.setResponse(response);

        while (subscriptions.isEmpty() && publishQueue.isNotEmpty()) {
            ServiceRequest publishService = publishQueue.poll();
            if (publishService != null) {
                publishService.setServiceFault(StatusCodes.Bad_NoSubscription);
            }
        }
    }

    public void setPublishingMode(ServiceRequest service) {
        SetPublishingModeRequest request = (SetPublishingModeRequest) service.getRequest();
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
            header,
            results,
            new DiagnosticInfo[0]
        );

        service.setResponse(response);
    }

    public void createMonitoredItems(ServiceRequest service) throws UaException {
        CreateMonitoredItemsRequest request = (CreateMonitoredItemsRequest) service.getRequest();

        UInteger subscriptionId = request.getSubscriptionId();
        Subscription subscription = subscriptions.get(subscriptionId);
        TimestampsToReturn timestamps = request.getTimestampsToReturn();
        List<MonitoredItemCreateRequest> itemsToCreate = l(request.getItemsToCreate());

        if (subscription == null) {
            throw new UaException(StatusCodes.Bad_SubscriptionIdInvalid);
        }
        if (timestamps == null) {
            throw new UaException(StatusCodes.Bad_TimestampsToReturnInvalid);
        }
        if (itemsToCreate.isEmpty()) {
            throw new UaException(StatusCodes.Bad_NothingToDo);
        }

        List<NodeId> distinctNodeIds = itemsToCreate.stream()
            .map(item -> item.getItemToMonitor().getNodeId())
            .distinct()
            .collect(toList());

        CompletableFuture<Map<NodeId, AttributeGroup>> attributesFuture = readMonitoringAttributes(distinctNodeIds);

        attributesFuture.thenAccept(attributeGroups -> {
            MonitoredItemCreateResult[] createResults = new MonitoredItemCreateResult[itemsToCreate.size()];

            List<BaseMonitoredItem<?>> monitoredItems = new ArrayList<>();

            for (int i = 0; i < itemsToCreate.size(); i++) {
                MonitoredItemCreateRequest createRequest = itemsToCreate.get(i);

                try {
                    BaseMonitoredItem<?> monitoredItem = createMonitoredItem(
                        createRequest,
                        subscription,
                        timestamps,
                        attributeGroups
                    );

                    monitoredItems.add(monitoredItem);

                    createResults[i] = new MonitoredItemCreateResult(
                        StatusCode.GOOD,
                        monitoredItem.getId(),
                        monitoredItem.getSamplingInterval(),
                        uint(monitoredItem.getQueueSize()),
                        monitoredItem.getFilterResult()
                    );
                } catch (UaException e) {
                    createResults[i] = new MonitoredItemCreateResult(
                        e.getStatusCode(),
                        UInteger.MIN,
                        0.0,
                        UInteger.MIN,
                        null
                    );
                }
            }

            subscription.addMonitoredItems(monitoredItems);

            // Notify AddressSpaces of the items we just created.

            byMonitoredItemType(
                monitoredItems,
                dataItems -> server.getAddressSpaceManager().onDataItemsCreated(dataItems),
                eventItems -> server.getAddressSpaceManager().onEventItemsCreated(eventItems)
            );

            ResponseHeader header = service.createResponseHeader();

            CreateMonitoredItemsResponse response = new CreateMonitoredItemsResponse(
                header,
                createResults,
                new DiagnosticInfo[0]
            );

            service.setResponse(response);
        });
    }

    private BaseMonitoredItem<?> createMonitoredItem(
        MonitoredItemCreateRequest request,
        Subscription subscription,
        TimestampsToReturn timestamps,
        Map<NodeId, AttributeGroup> attributeGroups
    ) throws UaException {

        NodeId nodeId = request.getItemToMonitor().getNodeId();
        UInteger attributeId = request.getItemToMonitor().getAttributeId();
        QualifiedName dataEncoding = request.getItemToMonitor().getDataEncoding();

        if (!AttributeId.isValid(attributeId)) {
            throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
        }

        if (dataEncoding.isNotNull()) {
            if (!AttributeId.Value.isEqual(attributeId)) {
                throw new UaException(StatusCodes.Bad_DataEncodingInvalid);
            }

            if (!dataEncoding.equals(DEFAULT_BINARY_ENCODING) &&
                !dataEncoding.equals(DEFAULT_XML_ENCODING)) {

                throw new UaException(StatusCodes.Bad_DataEncodingUnsupported);
            }
        }

        AttributeGroup attributeGroup = attributeGroups.get(nodeId);

        if (attributeId.equals(AttributeId.EventNotifier.uid())) {
            UByte eventNotifier = attributeGroup.getEventNotifier();

            // Verify that the SubscribeToEvents bit is set
            if (eventNotifier == null || (eventNotifier.intValue() & 1) == 0) {
                throw new UaException(StatusCodes.Bad_AttributeIdInvalid);
            }

            UInteger requestedQueueSize = request.getRequestedParameters().getQueueSize();
            AtomicReference<UInteger> revisedQueueSize = new AtomicReference<>(requestedQueueSize);

            try {
                server.getAddressSpaceManager().onCreateEventItem(
                    request.getItemToMonitor(),
                    requestedQueueSize,
                    revisedQueueSize::set
                );
            } catch (Throwable t) {
                throw new UaException(StatusCodes.Bad_InternalError, t);
            }

            return new MonitoredEventItem(
                server,
                session,
                uint(subscription.nextItemId()),
                subscription.getId(),
                request.getItemToMonitor(),
                request.getMonitoringMode(),
                timestamps,
                request.getRequestedParameters().getClientHandle(),
                0.0,
                revisedQueueSize.get(),
                request.getRequestedParameters().getDiscardOldest(),
                request.getRequestedParameters().getFilter()
            );
        } else {
            if (attributeId.equals(AttributeId.Value.uid())) {
                UByte accessLevel = attributeGroup.getAccessLevel();
                if (accessLevel == null) accessLevel = ubyte(0);

                UByte userAccessLevel = attributeGroup.getUserAccessLevel();
                if (userAccessLevel == null) userAccessLevel = ubyte(0);

                EnumSet<AccessLevel> accessLevels = AccessLevel.fromMask(accessLevel);
                EnumSet<AccessLevel> userAccessLevels = AccessLevel.fromMask(userAccessLevel);

                if (!accessLevels.contains(AccessLevel.CurrentRead)) {
                    throw new UaException(StatusCodes.Bad_NotReadable);
                }
                if (!userAccessLevels.contains(AccessLevel.CurrentRead)) {
                    throw new UaException(StatusCodes.Bad_UserAccessDenied);
                }
            }

            // Validate the requested index range by parsing it.
            String indexRange = request.getItemToMonitor().getIndexRange();
            if (indexRange != null) NumericRange.parse(indexRange);

            Double minimumSamplingInterval = 0.0;
            try {
                minimumSamplingInterval = attributeGroup.getMinimumSamplingInterval();
                if (minimumSamplingInterval == null) {
                    minimumSamplingInterval = server.getConfig().getLimits().getMinSupportedSampleRate();
                }
            } catch (UaException e) {
                if (e.getStatusCode().getValue() != StatusCodes.Bad_AttributeIdInvalid) {
                    throw e;
                }
            }

            double requestedSamplingInterval = getSamplingInterval(
                subscription,
                minimumSamplingInterval,
                request.getRequestedParameters().getSamplingInterval()
            );

            UInteger requestedQueueSize = request.getRequestedParameters().getQueueSize();

            AtomicReference<Double> revisedSamplingInterval = new AtomicReference<>(requestedSamplingInterval);
            AtomicReference<UInteger> revisedQueueSize = new AtomicReference<>(requestedQueueSize);

            try {
                server.getAddressSpaceManager().onCreateDataItem(
                    request.getItemToMonitor(),
                    requestedSamplingInterval,
                    requestedQueueSize,
                    (rsi, rqs) -> {
                        revisedSamplingInterval.set(rsi);
                        revisedQueueSize.set(rqs);
                    }
                );
            } catch (Throwable t) {
                throw new UaException(StatusCodes.Bad_InternalError, t);
            }

            return new MonitoredDataItem(
                server,
                session,
                uint(subscription.nextItemId()),
                subscription.getId(),
                request.getItemToMonitor(),
                request.getMonitoringMode(),
                timestamps,
                request.getRequestedParameters().getClientHandle(),
                revisedSamplingInterval.get(),
                request.getRequestedParameters().getFilter(),
                revisedQueueSize.get(),
                request.getRequestedParameters().getDiscardOldest()
            );
        }
    }

    public void modifyMonitoredItems(ServiceRequest service) throws UaException {
        ModifyMonitoredItemsRequest request = (ModifyMonitoredItemsRequest) service.getRequest();

        UInteger subscriptionId = request.getSubscriptionId();
        Subscription subscription = subscriptions.get(subscriptionId);
        TimestampsToReturn timestamps = request.getTimestampsToReturn();
        List<MonitoredItemModifyRequest> itemsToModify = l(request.getItemsToModify());

        if (subscription == null) {
            throw new UaException(StatusCodes.Bad_SubscriptionIdInvalid);
        }
        if (timestamps == null) {
            throw new UaException(StatusCodes.Bad_TimestampsToReturnInvalid);
        }
        if (itemsToModify.isEmpty()) {
            throw new UaException(StatusCodes.Bad_NothingToDo);
        }

        List<NodeId> distinctNodeIds = itemsToModify.stream()
            .map(item -> {
                UInteger itemId = item.getMonitoredItemId();
                BaseMonitoredItem<?> monitoredItem = subscription.getMonitoredItems().get(itemId);
                return monitoredItem != null ? monitoredItem.getReadValueId().getNodeId() : NodeId.NULL_VALUE;
            })
            .filter(NodeId::isNotNull)
            .distinct()
            .collect(toList());

        CompletableFuture<Map<NodeId, AttributeGroup>> attributesFuture = readMonitoringAttributes(distinctNodeIds);

        attributesFuture.thenAccept(attributeGroups -> {
            MonitoredItemModifyResult[] modifyResults = new MonitoredItemModifyResult[itemsToModify.size()];

            List<BaseMonitoredItem<?>> monitoredItems = new ArrayList<>();

            for (int i = 0; i < itemsToModify.size(); i++) {
                MonitoredItemModifyRequest modifyRequest = itemsToModify.get(i);

                try {
                    BaseMonitoredItem<?> monitoredItem = modifyMonitoredItem(
                        modifyRequest,
                        timestamps,
                        subscription,
                        attributeGroups
                    );

                    monitoredItems.add(monitoredItem);

                    modifyResults[i] = new MonitoredItemModifyResult(
                        StatusCode.GOOD,
                        monitoredItem.getSamplingInterval(),
                        uint(monitoredItem.getQueueSize()),
                        monitoredItem.getFilterResult()
                    );
                } catch (UaException e) {
                    modifyResults[i] = new MonitoredItemModifyResult(
                        e.getStatusCode(),
                        0.0,
                        UInteger.MIN,
                        null
                    );
                }
            }

            subscription.resetLifetimeCounter();

            /*
             * Notify AddressSpaces of the items we just modified.
             */

            byMonitoredItemType(
                monitoredItems,
                dataItems -> server.getAddressSpaceManager().onDataItemsModified(dataItems),
                eventItems -> server.getAddressSpaceManager().onEventItemsModified(eventItems)
            );

            /*
             * AddressSpaces have been notified; send response.
             */

            ResponseHeader header = service.createResponseHeader();

            ModifyMonitoredItemsResponse response = new ModifyMonitoredItemsResponse(
                header,
                modifyResults,
                new DiagnosticInfo[0]
            );

            service.setResponse(response);
        });
    }

    private BaseMonitoredItem<?> modifyMonitoredItem(
        MonitoredItemModifyRequest request,
        TimestampsToReturn timestamps,
        Subscription subscription,
        Map<NodeId, AttributeGroup> attributeGroups
    ) throws UaException {

        UInteger itemId = request.getMonitoredItemId();
        MonitoringParameters parameters = request.getRequestedParameters();

        BaseMonitoredItem<?> monitoredItem = subscription.getMonitoredItems().get(itemId);

        if (monitoredItem == null) {
            throw new UaException(StatusCodes.Bad_MonitoredItemIdInvalid);
        }

        NodeId nodeId = monitoredItem.getReadValueId().getNodeId();
        UInteger attributeId = monitoredItem.getReadValueId().getAttributeId();
        AttributeGroup attributeGroup = attributeGroups.get(nodeId);

        if (attributeId.equals(AttributeId.EventNotifier.uid())) {
            UInteger requestedQueueSize = parameters.getQueueSize();

            AtomicReference<UInteger> revisedQueueSize = new AtomicReference<>(requestedQueueSize);

            try {
                server.getAddressSpaceManager().onModifyEventItem(
                    monitoredItem.getReadValueId(),
                    requestedQueueSize,
                    revisedQueueSize::set
                );
            } catch (Throwable t) {
                throw new UaException(StatusCodes.Bad_InternalError, t);
            }

            monitoredItem.modify(
                timestamps,
                parameters.getClientHandle(),
                monitoredItem.getSamplingInterval(),
                parameters.getFilter(),
                revisedQueueSize.get(),
                parameters.getDiscardOldest()
            );
        } else {
            Double minimumSamplingInterval = 0.0;
            try {
                minimumSamplingInterval = attributeGroup.getMinimumSamplingInterval();
                if (minimumSamplingInterval == null) {
                    minimumSamplingInterval = server.getConfig().getLimits().getMinSupportedSampleRate();
                }
            } catch (UaException e) {
                if (e.getStatusCode().getValue() != StatusCodes.Bad_AttributeIdInvalid) {
                    throw e;
                }
            }

            double requestedSamplingInterval = getSamplingInterval(
                subscription,
                minimumSamplingInterval,
                request.getRequestedParameters().getSamplingInterval()
            );

            UInteger requestedQueueSize = parameters.getQueueSize();

            AtomicReference<Double> revisedSamplingInterval = new AtomicReference<>(requestedSamplingInterval);
            AtomicReference<UInteger> revisedQueueSize = new AtomicReference<>(requestedQueueSize);

            try {
                server.getAddressSpaceManager().onModifyDataItem(
                    monitoredItem.getReadValueId(),
                    requestedSamplingInterval,
                    requestedQueueSize,
                    (rsi, rqs) -> {
                        revisedSamplingInterval.set(rsi);
                        revisedQueueSize.set(rqs);
                    }
                );
            } catch (Throwable t) {
                throw new UaException(StatusCodes.Bad_InternalError, t);
            }

            monitoredItem.modify(
                timestamps,
                parameters.getClientHandle(),
                revisedSamplingInterval.get(),
                parameters.getFilter(),
                revisedQueueSize.get(),
                parameters.getDiscardOldest()
            );
        }

        return monitoredItem;
    }

    private double getSamplingInterval(
        Subscription subscription,
        Double minimumSamplingInterval,
        Double requestedSamplingInterval) {

        double samplingInterval = requestedSamplingInterval;
        double minSupportedSampleRate = server.getConfig().getLimits().getMinSupportedSampleRate();
        double maxSupportedSampleRate = server.getConfig().getLimits().getMaxSupportedSampleRate();

        if (samplingInterval < 0) {
            samplingInterval = subscription.getPublishingInterval();
        }
        if (samplingInterval < minimumSamplingInterval) {
            samplingInterval = minimumSamplingInterval;
        }
        if (samplingInterval < minSupportedSampleRate) {
            samplingInterval = minSupportedSampleRate;
        }
        if (samplingInterval > maxSupportedSampleRate) {
            samplingInterval = maxSupportedSampleRate;
        }
        return samplingInterval;
    }

    private CompletableFuture<Map<NodeId, AttributeGroup>> readMonitoringAttributes(List<NodeId> nodeIds) {
        List<ReadValueId> attributesToRead = nodeIds.stream()
            .flatMap(nodeId -> {
                Function<AttributeId, ReadValueId> f = id -> new ReadValueId(
                    nodeId,
                    id.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                );

                return Stream.of(
                    f.apply(AttributeId.AccessLevel),
                    f.apply(AttributeId.UserAccessLevel),
                    f.apply(AttributeId.EventNotifier),
                    f.apply(AttributeId.MinimumSamplingInterval)
                );
            })
            .collect(toList());

        ReadContext context = new ReadContext(server, null);

        server.getAddressSpaceManager().read(
            context,
            0.0,
            TimestampsToReturn.Neither,
            attributesToRead
        );

        return context.getFuture().thenApply(attributeValues -> {
            Map<NodeId, AttributeGroup> monitoringAttributes = new HashMap<>();

            for (int nodeIdx = 0, attrIdx = 0; nodeIdx < nodeIds.size(); nodeIdx++, attrIdx += 4) {
                monitoringAttributes.put(nodeIds.get(nodeIdx), new AttributeGroup(
                    attributeValues.get(attrIdx),
                    attributeValues.get(attrIdx + 1),
                    attributeValues.get(attrIdx + 2),
                    attributeValues.get(attrIdx + 3)
                ));
            }

            return monitoringAttributes;
        });
    }

    public void deleteMonitoredItems(ServiceRequest service) throws UaException {
        DeleteMonitoredItemsRequest request = (DeleteMonitoredItemsRequest) service.getRequest();

        UInteger subscriptionId = request.getSubscriptionId();
        Subscription subscription = subscriptions.get(subscriptionId);
        List<UInteger> itemsToDelete = l(request.getMonitoredItemIds());

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
         * Notify AddressSpaces of the items that have been deleted.
         */

        byMonitoredItemType(
            deletedItems,
            dataItems -> server.getAddressSpaceManager().onDataItemsDeleted(dataItems),
            eventItems -> server.getAddressSpaceManager().onEventItemsDeleted(eventItems)
        );

        /*
         * Build and return results.
         */
        ResponseHeader header = service.createResponseHeader();

        DeleteMonitoredItemsResponse response = new DeleteMonitoredItemsResponse(
            header,
            deleteResults,
            new DiagnosticInfo[0]
        );

        service.setResponse(response);

    }

    public void setMonitoringMode(ServiceRequest service) {
        SetMonitoringModeRequest request = (SetMonitoringModeRequest) service.getRequest();
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
            List<MonitoredItem> modified = newArrayListWithCapacity(itemsToModify.size());

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
             * Notify AddressSpace of the items whose MonitoringMode has been modified.
             */

            server.getAddressSpaceManager().onMonitoringModeChanged(modified);

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

    public void publish(ServiceRequest service) {
        PublishRequest request = (PublishRequest) service.getRequest();

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

    public void republish(ServiceRequest service) {
        RepublishRequest request = (RepublishRequest) service.getRequest();

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

    public void setTriggering(ServiceRequest service) {
        SetTriggeringRequest request = (SetTriggeringRequest) service.getRequest();

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

        StatusCode[] addResults;
        StatusCode[] removeResults;

        synchronized (subscription) {
            Map<UInteger, BaseMonitoredItem<?>> itemsById = subscription.getMonitoredItems();

            BaseMonitoredItem<?> triggerItem = itemsById.get(triggerId);
            if (triggerItem == null) {
                service.setServiceFault(StatusCodes.Bad_MonitoredItemIdInvalid);
                return;
            }

            removeResults = linksToRemove.stream()
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
                .toArray(StatusCode[]::new);

            addResults = linksToAdd.stream()
                .map(linkedItemId -> {
                    BaseMonitoredItem<?> linkedItem = itemsById.get(linkedItemId);
                    if (linkedItem != null) {
                        triggerItem.getTriggeredItems().put(linkedItemId, linkedItem);
                        return StatusCode.GOOD;
                    } else {
                        return new StatusCode(StatusCodes.Bad_MonitoredItemIdInvalid);
                    }
                })
                .toArray(StatusCode[]::new);
        }

        SetTriggeringResponse response = new SetTriggeringResponse(
            service.createResponseHeader(),
            addResults,
            new DiagnosticInfo[0],
            removeResults,
            new DiagnosticInfo[0]
        );

        service.setResponse(response);
    }

    public void sessionClosed(boolean deleteSubscriptions) {
        Iterator<Subscription> iterator = subscriptions.values().iterator();

        while (iterator.hasNext()) {
            Subscription s = iterator.next();
            s.setStateListener(null);

            if (deleteSubscriptions) {
                server.getSubscriptions().remove(s.getId());

                List<BaseMonitoredItem<?>> deletedItems = s.deleteSubscription();

                /*
                 * Notify AddressSpaces the items for this subscription are deleted.
                 */

                byMonitoredItemType(
                    deletedItems,
                    dataItems -> server.getAddressSpaceManager().onDataItemsDeleted(dataItems),
                    eventItems -> server.getAddressSpaceManager().onEventItemsDeleted(eventItems)
                );
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
        ServiceRequest service = publishQueue.poll();

        if (service != null) {
            subscription.returnStatusChangeNotification(service);
        } else {
            transferred.add(subscription);
        }
    }

    /**
     * Split {@code monitoredItems} into a list of {@link DataItem}s and a list of {@link EventItem}s and invoke the
     * corresponding {@link Consumer} for each list if non-empty.
     *
     * @param monitoredItems    the list of MonitoredItems to group.
     * @param dataItemConsumer  a {@link Consumer} that accepts a non-empty list of {@link DataItem}s.
     * @param eventItemConsumer a {@link Consumer} that accepts a non-empty list of {@link EventItem}s.
     */
    private static void byMonitoredItemType(
        List<BaseMonitoredItem<?>> monitoredItems,
        Consumer<List<DataItem>> dataItemConsumer,
        Consumer<List<EventItem>> eventItemConsumer
    ) {

        List<DataItem> dataItems = Lists.newArrayList();
        List<EventItem> eventItems = Lists.newArrayList();

        for (BaseMonitoredItem<?> item : monitoredItems) {
            if (item instanceof MonitoredDataItem) {
                dataItems.add((DataItem) item);
            } else if (item instanceof MonitoredEventItem) {
                eventItems.add((EventItem) item);
            }
        }

        try {
            if (!dataItems.isEmpty()) {
                dataItemConsumer.accept(dataItems);
            }
        } catch (Throwable t) {
            LoggerFactory.getLogger(SubscriptionManager.class)
                .error("Uncaught Throwable in dataItemConsumer", t);
        }

        try {
            if (!eventItems.isEmpty()) {
                eventItemConsumer.accept(eventItems);
            }
        } catch (Throwable t) {
            LoggerFactory.getLogger(SubscriptionManager.class)
                .error("Uncaught Throwable in eventItemConsumer", t);
        }
    }

    private static class AttributeGroup {
        final DataValue accessLevelValue;
        final DataValue userAccessLevelValue;
        final DataValue eventNotifierValue;
        final DataValue minimumSamplingIntervalValue;

        AttributeGroup(
            DataValue accessLevelValue,
            DataValue userAccessLevelValue,
            DataValue eventNotifierValue,
            DataValue minimumSamplingIntervalValue) {

            this.accessLevelValue = accessLevelValue;
            this.userAccessLevelValue = userAccessLevelValue;
            this.eventNotifierValue = eventNotifierValue;
            this.minimumSamplingIntervalValue = minimumSamplingIntervalValue;
        }

        @Nullable
        UByte getAccessLevel() throws UaException {
            Object value = getValue(accessLevelValue);

            if (value instanceof UByte) {
                return (UByte) value;
            } else {
                return null;
            }
        }

        @Nullable
        UByte getUserAccessLevel() throws UaException {
            Object value = getValue(userAccessLevelValue);

            if (value instanceof UByte) {
                return (UByte) value;
            } else {
                return null;
            }
        }

        @Nullable
        UByte getEventNotifier() throws UaException {
            Object value = getValue(eventNotifierValue);

            if (value instanceof UByte) {
                return (UByte) value;
            } else {
                return null;
            }
        }

        @Nullable
        Double getMinimumSamplingInterval() throws UaException {
            Object value = getValue(minimumSamplingIntervalValue);

            if (value instanceof Double) {
                return (Double) value;
            } else {
                return null;
            }
        }

        private Object getValue(DataValue dataValue) throws UaException {
            StatusCode statusCode = dataValue.getStatusCode();

            if (statusCode == null) {
                throw new UaException(StatusCode.BAD);
            }

            if (statusCode.isBad()) {
                throw new UaException(statusCode);
            }

            return dataValue.getValue().getValue();
        }
    }

}







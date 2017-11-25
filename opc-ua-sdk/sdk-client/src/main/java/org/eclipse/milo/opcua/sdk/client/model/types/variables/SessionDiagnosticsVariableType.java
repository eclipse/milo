/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;

public interface SessionDiagnosticsVariableType extends BaseDataVariableType {
    CompletableFuture<? extends BaseDataVariableType> getSessionIdNode();

    CompletableFuture<NodeId> getSessionId();

    CompletableFuture<StatusCode> setSessionId(NodeId value);

    CompletableFuture<? extends BaseDataVariableType> getSessionNameNode();

    CompletableFuture<String> getSessionName();

    CompletableFuture<StatusCode> setSessionName(String value);

    CompletableFuture<? extends BaseDataVariableType> getClientDescriptionNode();

    CompletableFuture<ApplicationDescription> getClientDescription();

    CompletableFuture<StatusCode> setClientDescription(ApplicationDescription value);

    CompletableFuture<? extends BaseDataVariableType> getServerUriNode();

    CompletableFuture<String> getServerUri();

    CompletableFuture<StatusCode> setServerUri(String value);

    CompletableFuture<? extends BaseDataVariableType> getEndpointUrlNode();

    CompletableFuture<String> getEndpointUrl();

    CompletableFuture<StatusCode> setEndpointUrl(String value);

    CompletableFuture<? extends BaseDataVariableType> getLocaleIdsNode();

    CompletableFuture<String[]> getLocaleIds();

    CompletableFuture<StatusCode> setLocaleIds(String[] value);

    CompletableFuture<? extends BaseDataVariableType> getActualSessionTimeoutNode();

    CompletableFuture<Double> getActualSessionTimeout();

    CompletableFuture<StatusCode> setActualSessionTimeout(Double value);

    CompletableFuture<? extends BaseDataVariableType> getMaxResponseMessageSizeNode();

    CompletableFuture<UInteger> getMaxResponseMessageSize();

    CompletableFuture<StatusCode> setMaxResponseMessageSize(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getClientConnectionTimeNode();

    CompletableFuture<DateTime> getClientConnectionTime();

    CompletableFuture<StatusCode> setClientConnectionTime(DateTime value);

    CompletableFuture<? extends BaseDataVariableType> getClientLastContactTimeNode();

    CompletableFuture<DateTime> getClientLastContactTime();

    CompletableFuture<StatusCode> setClientLastContactTime(DateTime value);

    CompletableFuture<? extends BaseDataVariableType> getCurrentSubscriptionsCountNode();

    CompletableFuture<UInteger> getCurrentSubscriptionsCount();

    CompletableFuture<StatusCode> setCurrentSubscriptionsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getCurrentMonitoredItemsCountNode();

    CompletableFuture<UInteger> getCurrentMonitoredItemsCount();

    CompletableFuture<StatusCode> setCurrentMonitoredItemsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getCurrentPublishRequestsInQueueNode();

    CompletableFuture<UInteger> getCurrentPublishRequestsInQueue();

    CompletableFuture<StatusCode> setCurrentPublishRequestsInQueue(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getTotalRequestCountNode();

    CompletableFuture<ServiceCounterDataType> getTotalRequestCount();

    CompletableFuture<StatusCode> setTotalRequestCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getUnauthorizedRequestCountNode();

    CompletableFuture<UInteger> getUnauthorizedRequestCount();

    CompletableFuture<StatusCode> setUnauthorizedRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getReadCountNode();

    CompletableFuture<ServiceCounterDataType> getReadCount();

    CompletableFuture<StatusCode> setReadCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getHistoryReadCountNode();

    CompletableFuture<ServiceCounterDataType> getHistoryReadCount();

    CompletableFuture<StatusCode> setHistoryReadCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getWriteCountNode();

    CompletableFuture<ServiceCounterDataType> getWriteCount();

    CompletableFuture<StatusCode> setWriteCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getHistoryUpdateCountNode();

    CompletableFuture<ServiceCounterDataType> getHistoryUpdateCount();

    CompletableFuture<StatusCode> setHistoryUpdateCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getCallCountNode();

    CompletableFuture<ServiceCounterDataType> getCallCount();

    CompletableFuture<StatusCode> setCallCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getCreateMonitoredItemsCountNode();

    CompletableFuture<ServiceCounterDataType> getCreateMonitoredItemsCount();

    CompletableFuture<StatusCode> setCreateMonitoredItemsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getModifyMonitoredItemsCountNode();

    CompletableFuture<ServiceCounterDataType> getModifyMonitoredItemsCount();

    CompletableFuture<StatusCode> setModifyMonitoredItemsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getSetMonitoringModeCountNode();

    CompletableFuture<ServiceCounterDataType> getSetMonitoringModeCount();

    CompletableFuture<StatusCode> setSetMonitoringModeCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getSetTriggeringCountNode();

    CompletableFuture<ServiceCounterDataType> getSetTriggeringCount();

    CompletableFuture<StatusCode> setSetTriggeringCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getDeleteMonitoredItemsCountNode();

    CompletableFuture<ServiceCounterDataType> getDeleteMonitoredItemsCount();

    CompletableFuture<StatusCode> setDeleteMonitoredItemsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getCreateSubscriptionCountNode();

    CompletableFuture<ServiceCounterDataType> getCreateSubscriptionCount();

    CompletableFuture<StatusCode> setCreateSubscriptionCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getModifySubscriptionCountNode();

    CompletableFuture<ServiceCounterDataType> getModifySubscriptionCount();

    CompletableFuture<StatusCode> setModifySubscriptionCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getSetPublishingModeCountNode();

    CompletableFuture<ServiceCounterDataType> getSetPublishingModeCount();

    CompletableFuture<StatusCode> setSetPublishingModeCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getPublishCountNode();

    CompletableFuture<ServiceCounterDataType> getPublishCount();

    CompletableFuture<StatusCode> setPublishCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getRepublishCountNode();

    CompletableFuture<ServiceCounterDataType> getRepublishCount();

    CompletableFuture<StatusCode> setRepublishCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getTransferSubscriptionsCountNode();

    CompletableFuture<ServiceCounterDataType> getTransferSubscriptionsCount();

    CompletableFuture<StatusCode> setTransferSubscriptionsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getDeleteSubscriptionsCountNode();

    CompletableFuture<ServiceCounterDataType> getDeleteSubscriptionsCount();

    CompletableFuture<StatusCode> setDeleteSubscriptionsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getAddNodesCountNode();

    CompletableFuture<ServiceCounterDataType> getAddNodesCount();

    CompletableFuture<StatusCode> setAddNodesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getAddReferencesCountNode();

    CompletableFuture<ServiceCounterDataType> getAddReferencesCount();

    CompletableFuture<StatusCode> setAddReferencesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getDeleteNodesCountNode();

    CompletableFuture<ServiceCounterDataType> getDeleteNodesCount();

    CompletableFuture<StatusCode> setDeleteNodesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getDeleteReferencesCountNode();

    CompletableFuture<ServiceCounterDataType> getDeleteReferencesCount();

    CompletableFuture<StatusCode> setDeleteReferencesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getBrowseCountNode();

    CompletableFuture<ServiceCounterDataType> getBrowseCount();

    CompletableFuture<StatusCode> setBrowseCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getBrowseNextCountNode();

    CompletableFuture<ServiceCounterDataType> getBrowseNextCount();

    CompletableFuture<StatusCode> setBrowseNextCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getTranslateBrowsePathsToNodeIdsCountNode();

    CompletableFuture<ServiceCounterDataType> getTranslateBrowsePathsToNodeIdsCount();

    CompletableFuture<StatusCode> setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getQueryFirstCountNode();

    CompletableFuture<ServiceCounterDataType> getQueryFirstCount();

    CompletableFuture<StatusCode> setQueryFirstCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getQueryNextCountNode();

    CompletableFuture<ServiceCounterDataType> getQueryNextCount();

    CompletableFuture<StatusCode> setQueryNextCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getRegisterNodesCountNode();

    CompletableFuture<ServiceCounterDataType> getRegisterNodesCount();

    CompletableFuture<StatusCode> setRegisterNodesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> getUnregisterNodesCountNode();

    CompletableFuture<ServiceCounterDataType> getUnregisterNodesCount();

    CompletableFuture<StatusCode> setUnregisterNodesCount(ServiceCounterDataType value);
}

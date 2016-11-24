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

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;


public interface SessionDiagnosticsVariableType extends BaseDataVariableType {


    CompletableFuture<? extends BaseDataVariableType> sessionId();

    CompletableFuture<NodeId> getSessionId();

    CompletableFuture<StatusCode> setSessionId(NodeId value);

    CompletableFuture<? extends BaseDataVariableType> sessionName();

    CompletableFuture<String> getSessionName();

    CompletableFuture<StatusCode> setSessionName(String value);

    CompletableFuture<? extends BaseDataVariableType> clientDescription();

    CompletableFuture<ApplicationDescription> getClientDescription();

    CompletableFuture<StatusCode> setClientDescription(ApplicationDescription value);

    CompletableFuture<? extends BaseDataVariableType> serverUri();

    CompletableFuture<String> getServerUri();

    CompletableFuture<StatusCode> setServerUri(String value);

    CompletableFuture<? extends BaseDataVariableType> endpointUrl();

    CompletableFuture<String> getEndpointUrl();

    CompletableFuture<StatusCode> setEndpointUrl(String value);

    CompletableFuture<? extends BaseDataVariableType> localeIds();

    CompletableFuture<String[]> getLocaleIds();

    CompletableFuture<StatusCode> setLocaleIds(String[] value);

    CompletableFuture<? extends BaseDataVariableType> actualSessionTimeout();

    CompletableFuture<Double> getActualSessionTimeout();

    CompletableFuture<StatusCode> setActualSessionTimeout(Double value);

    CompletableFuture<? extends BaseDataVariableType> maxResponseMessageSize();

    CompletableFuture<UInteger> getMaxResponseMessageSize();

    CompletableFuture<StatusCode> setMaxResponseMessageSize(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> clientConnectionTime();

    CompletableFuture<DateTime> getClientConnectionTime();

    CompletableFuture<StatusCode> setClientConnectionTime(DateTime value);

    CompletableFuture<? extends BaseDataVariableType> clientLastContactTime();

    CompletableFuture<DateTime> getClientLastContactTime();

    CompletableFuture<StatusCode> setClientLastContactTime(DateTime value);

    CompletableFuture<? extends BaseDataVariableType> currentSubscriptionsCount();

    CompletableFuture<UInteger> getCurrentSubscriptionsCount();

    CompletableFuture<StatusCode> setCurrentSubscriptionsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> currentMonitoredItemsCount();

    CompletableFuture<UInteger> getCurrentMonitoredItemsCount();

    CompletableFuture<StatusCode> setCurrentMonitoredItemsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> currentPublishRequestsInQueue();

    CompletableFuture<UInteger> getCurrentPublishRequestsInQueue();

    CompletableFuture<StatusCode> setCurrentPublishRequestsInQueue(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> totalRequestCount();

    CompletableFuture<ServiceCounterDataType> getTotalRequestCount();

    CompletableFuture<StatusCode> setTotalRequestCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> unauthorizedRequestCount();

    CompletableFuture<UInteger> getUnauthorizedRequestCount();

    CompletableFuture<StatusCode> setUnauthorizedRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> readCount();

    CompletableFuture<ServiceCounterDataType> getReadCount();

    CompletableFuture<StatusCode> setReadCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> historyReadCount();

    CompletableFuture<ServiceCounterDataType> getHistoryReadCount();

    CompletableFuture<StatusCode> setHistoryReadCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> writeCount();

    CompletableFuture<ServiceCounterDataType> getWriteCount();

    CompletableFuture<StatusCode> setWriteCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> historyUpdateCount();

    CompletableFuture<ServiceCounterDataType> getHistoryUpdateCount();

    CompletableFuture<StatusCode> setHistoryUpdateCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> callCount();

    CompletableFuture<ServiceCounterDataType> getCallCount();

    CompletableFuture<StatusCode> setCallCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> createMonitoredItemsCount();

    CompletableFuture<ServiceCounterDataType> getCreateMonitoredItemsCount();

    CompletableFuture<StatusCode> setCreateMonitoredItemsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> modifyMonitoredItemsCount();

    CompletableFuture<ServiceCounterDataType> getModifyMonitoredItemsCount();

    CompletableFuture<StatusCode> setModifyMonitoredItemsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> setMonitoringModeCount();

    CompletableFuture<ServiceCounterDataType> getSetMonitoringModeCount();

    CompletableFuture<StatusCode> setSetMonitoringModeCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> setTriggeringCount();

    CompletableFuture<ServiceCounterDataType> getSetTriggeringCount();

    CompletableFuture<StatusCode> setSetTriggeringCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> deleteMonitoredItemsCount();

    CompletableFuture<ServiceCounterDataType> getDeleteMonitoredItemsCount();

    CompletableFuture<StatusCode> setDeleteMonitoredItemsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> createSubscriptionCount();

    CompletableFuture<ServiceCounterDataType> getCreateSubscriptionCount();

    CompletableFuture<StatusCode> setCreateSubscriptionCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> modifySubscriptionCount();

    CompletableFuture<ServiceCounterDataType> getModifySubscriptionCount();

    CompletableFuture<StatusCode> setModifySubscriptionCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> setPublishingModeCount();

    CompletableFuture<ServiceCounterDataType> getSetPublishingModeCount();

    CompletableFuture<StatusCode> setSetPublishingModeCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> publishCount();

    CompletableFuture<ServiceCounterDataType> getPublishCount();

    CompletableFuture<StatusCode> setPublishCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> republishCount();

    CompletableFuture<ServiceCounterDataType> getRepublishCount();

    CompletableFuture<StatusCode> setRepublishCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> transferSubscriptionsCount();

    CompletableFuture<ServiceCounterDataType> getTransferSubscriptionsCount();

    CompletableFuture<StatusCode> setTransferSubscriptionsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> deleteSubscriptionsCount();

    CompletableFuture<ServiceCounterDataType> getDeleteSubscriptionsCount();

    CompletableFuture<StatusCode> setDeleteSubscriptionsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> addNodesCount();

    CompletableFuture<ServiceCounterDataType> getAddNodesCount();

    CompletableFuture<StatusCode> setAddNodesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> addReferencesCount();

    CompletableFuture<ServiceCounterDataType> getAddReferencesCount();

    CompletableFuture<StatusCode> setAddReferencesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> deleteNodesCount();

    CompletableFuture<ServiceCounterDataType> getDeleteNodesCount();

    CompletableFuture<StatusCode> setDeleteNodesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> deleteReferencesCount();

    CompletableFuture<ServiceCounterDataType> getDeleteReferencesCount();

    CompletableFuture<StatusCode> setDeleteReferencesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> browseCount();

    CompletableFuture<ServiceCounterDataType> getBrowseCount();

    CompletableFuture<StatusCode> setBrowseCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> browseNextCount();

    CompletableFuture<ServiceCounterDataType> getBrowseNextCount();

    CompletableFuture<StatusCode> setBrowseNextCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> translateBrowsePathsToNodeIdsCount();

    CompletableFuture<ServiceCounterDataType> getTranslateBrowsePathsToNodeIdsCount();

    CompletableFuture<StatusCode> setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> queryFirstCount();

    CompletableFuture<ServiceCounterDataType> getQueryFirstCount();

    CompletableFuture<StatusCode> setQueryFirstCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> queryNextCount();

    CompletableFuture<ServiceCounterDataType> getQueryNextCount();

    CompletableFuture<StatusCode> setQueryNextCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> registerNodesCount();

    CompletableFuture<ServiceCounterDataType> getRegisterNodesCount();

    CompletableFuture<StatusCode> setRegisterNodesCount(ServiceCounterDataType value);

    CompletableFuture<? extends BaseDataVariableType> unregisterNodesCount();

    CompletableFuture<ServiceCounterDataType> getUnregisterNodesCount();

    CompletableFuture<StatusCode> setUnregisterNodesCount(ServiceCounterDataType value);


}
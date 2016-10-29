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

package org.eclipse.milo.opcua.sdk.core.model.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;


public interface SessionDiagnosticsVariableType extends BaseDataVariableType {


    NodeId getSessionId();

    BaseDataVariableType getSessionIdNode();

    void setSessionId(NodeId value);

    String getSessionName();

    BaseDataVariableType getSessionNameNode();

    void setSessionName(String value);

    ApplicationDescription getClientDescription();

    BaseDataVariableType getClientDescriptionNode();

    void setClientDescription(ApplicationDescription value);

    String getServerUri();

    BaseDataVariableType getServerUriNode();

    void setServerUri(String value);

    String getEndpointUrl();

    BaseDataVariableType getEndpointUrlNode();

    void setEndpointUrl(String value);

    String[] getLocaleIds();

    BaseDataVariableType getLocaleIdsNode();

    void setLocaleIds(String[] value);

    Double getActualSessionTimeout();

    BaseDataVariableType getActualSessionTimeoutNode();

    void setActualSessionTimeout(Double value);

    UInteger getMaxResponseMessageSize();

    BaseDataVariableType getMaxResponseMessageSizeNode();

    void setMaxResponseMessageSize(UInteger value);

    DateTime getClientConnectionTime();

    BaseDataVariableType getClientConnectionTimeNode();

    void setClientConnectionTime(DateTime value);

    DateTime getClientLastContactTime();

    BaseDataVariableType getClientLastContactTimeNode();

    void setClientLastContactTime(DateTime value);

    UInteger getCurrentSubscriptionsCount();

    BaseDataVariableType getCurrentSubscriptionsCountNode();

    void setCurrentSubscriptionsCount(UInteger value);

    UInteger getCurrentMonitoredItemsCount();

    BaseDataVariableType getCurrentMonitoredItemsCountNode();

    void setCurrentMonitoredItemsCount(UInteger value);

    UInteger getCurrentPublishRequestsInQueue();

    BaseDataVariableType getCurrentPublishRequestsInQueueNode();

    void setCurrentPublishRequestsInQueue(UInteger value);

    ServiceCounterDataType getTotalRequestCount();

    BaseDataVariableType getTotalRequestCountNode();

    void setTotalRequestCount(ServiceCounterDataType value);

    UInteger getUnauthorizedRequestCount();

    BaseDataVariableType getUnauthorizedRequestCountNode();

    void setUnauthorizedRequestCount(UInteger value);

    ServiceCounterDataType getReadCount();

    BaseDataVariableType getReadCountNode();

    void setReadCount(ServiceCounterDataType value);

    ServiceCounterDataType getHistoryReadCount();

    BaseDataVariableType getHistoryReadCountNode();

    void setHistoryReadCount(ServiceCounterDataType value);

    ServiceCounterDataType getWriteCount();

    BaseDataVariableType getWriteCountNode();

    void setWriteCount(ServiceCounterDataType value);

    ServiceCounterDataType getHistoryUpdateCount();

    BaseDataVariableType getHistoryUpdateCountNode();

    void setHistoryUpdateCount(ServiceCounterDataType value);

    ServiceCounterDataType getCallCount();

    BaseDataVariableType getCallCountNode();

    void setCallCount(ServiceCounterDataType value);

    ServiceCounterDataType getCreateMonitoredItemsCount();

    BaseDataVariableType getCreateMonitoredItemsCountNode();

    void setCreateMonitoredItemsCount(ServiceCounterDataType value);

    ServiceCounterDataType getModifyMonitoredItemsCount();

    BaseDataVariableType getModifyMonitoredItemsCountNode();

    void setModifyMonitoredItemsCount(ServiceCounterDataType value);

    ServiceCounterDataType getSetMonitoringModeCount();

    BaseDataVariableType getSetMonitoringModeCountNode();

    void setSetMonitoringModeCount(ServiceCounterDataType value);

    ServiceCounterDataType getSetTriggeringCount();

    BaseDataVariableType getSetTriggeringCountNode();

    void setSetTriggeringCount(ServiceCounterDataType value);

    ServiceCounterDataType getDeleteMonitoredItemsCount();

    BaseDataVariableType getDeleteMonitoredItemsCountNode();

    void setDeleteMonitoredItemsCount(ServiceCounterDataType value);

    ServiceCounterDataType getCreateSubscriptionCount();

    BaseDataVariableType getCreateSubscriptionCountNode();

    void setCreateSubscriptionCount(ServiceCounterDataType value);

    ServiceCounterDataType getModifySubscriptionCount();

    BaseDataVariableType getModifySubscriptionCountNode();

    void setModifySubscriptionCount(ServiceCounterDataType value);

    ServiceCounterDataType getSetPublishingModeCount();

    BaseDataVariableType getSetPublishingModeCountNode();

    void setSetPublishingModeCount(ServiceCounterDataType value);

    ServiceCounterDataType getPublishCount();

    BaseDataVariableType getPublishCountNode();

    void setPublishCount(ServiceCounterDataType value);

    ServiceCounterDataType getRepublishCount();

    BaseDataVariableType getRepublishCountNode();

    void setRepublishCount(ServiceCounterDataType value);

    ServiceCounterDataType getTransferSubscriptionsCount();

    BaseDataVariableType getTransferSubscriptionsCountNode();

    void setTransferSubscriptionsCount(ServiceCounterDataType value);

    ServiceCounterDataType getDeleteSubscriptionsCount();

    BaseDataVariableType getDeleteSubscriptionsCountNode();

    void setDeleteSubscriptionsCount(ServiceCounterDataType value);

    ServiceCounterDataType getAddNodesCount();

    BaseDataVariableType getAddNodesCountNode();

    void setAddNodesCount(ServiceCounterDataType value);

    ServiceCounterDataType getAddReferencesCount();

    BaseDataVariableType getAddReferencesCountNode();

    void setAddReferencesCount(ServiceCounterDataType value);

    ServiceCounterDataType getDeleteNodesCount();

    BaseDataVariableType getDeleteNodesCountNode();

    void setDeleteNodesCount(ServiceCounterDataType value);

    ServiceCounterDataType getDeleteReferencesCount();

    BaseDataVariableType getDeleteReferencesCountNode();

    void setDeleteReferencesCount(ServiceCounterDataType value);

    ServiceCounterDataType getBrowseCount();

    BaseDataVariableType getBrowseCountNode();

    void setBrowseCount(ServiceCounterDataType value);

    ServiceCounterDataType getBrowseNextCount();

    BaseDataVariableType getBrowseNextCountNode();

    void setBrowseNextCount(ServiceCounterDataType value);

    ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount();

    BaseDataVariableType getTranslateBrowsePathsToNodeIdsCountNode();

    void setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType value);

    ServiceCounterDataType getQueryFirstCount();

    BaseDataVariableType getQueryFirstCountNode();

    void setQueryFirstCount(ServiceCounterDataType value);

    ServiceCounterDataType getQueryNextCount();

    BaseDataVariableType getQueryNextCountNode();

    void setQueryNextCount(ServiceCounterDataType value);

    ServiceCounterDataType getRegisterNodesCount();

    BaseDataVariableType getRegisterNodesCountNode();

    void setRegisterNodesCount(ServiceCounterDataType value);

    ServiceCounterDataType getUnregisterNodesCount();

    BaseDataVariableType getUnregisterNodesCountNode();

    void setUnregisterNodesCount(ServiceCounterDataType value);
}

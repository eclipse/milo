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

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public interface SubscriptionDiagnosticsType extends BaseDataVariableType {


    NodeId getSessionId();

    BaseDataVariableType getSessionIdNode();

    void setSessionId(NodeId value);

    UInteger getSubscriptionId();

    BaseDataVariableType getSubscriptionIdNode();

    void setSubscriptionId(UInteger value);

    UByte getPriority();

    BaseDataVariableType getPriorityNode();

    void setPriority(UByte value);

    Double getPublishingInterval();

    BaseDataVariableType getPublishingIntervalNode();

    void setPublishingInterval(Double value);

    UInteger getMaxKeepAliveCount();

    BaseDataVariableType getMaxKeepAliveCountNode();

    void setMaxKeepAliveCount(UInteger value);

    UInteger getMaxLifetimeCount();

    BaseDataVariableType getMaxLifetimeCountNode();

    void setMaxLifetimeCount(UInteger value);

    UInteger getMaxNotificationsPerPublish();

    BaseDataVariableType getMaxNotificationsPerPublishNode();

    void setMaxNotificationsPerPublish(UInteger value);

    Boolean getPublishingEnabled();

    BaseDataVariableType getPublishingEnabledNode();

    void setPublishingEnabled(Boolean value);

    UInteger getModifyCount();

    BaseDataVariableType getModifyCountNode();

    void setModifyCount(UInteger value);

    UInteger getEnableCount();

    BaseDataVariableType getEnableCountNode();

    void setEnableCount(UInteger value);

    UInteger getDisableCount();

    BaseDataVariableType getDisableCountNode();

    void setDisableCount(UInteger value);

    UInteger getRepublishRequestCount();

    BaseDataVariableType getRepublishRequestCountNode();

    void setRepublishRequestCount(UInteger value);

    UInteger getRepublishMessageRequestCount();

    BaseDataVariableType getRepublishMessageRequestCountNode();

    void setRepublishMessageRequestCount(UInteger value);

    UInteger getRepublishMessageCount();

    BaseDataVariableType getRepublishMessageCountNode();

    void setRepublishMessageCount(UInteger value);

    UInteger getTransferRequestCount();

    BaseDataVariableType getTransferRequestCountNode();

    void setTransferRequestCount(UInteger value);

    UInteger getTransferredToAltClientCount();

    BaseDataVariableType getTransferredToAltClientCountNode();

    void setTransferredToAltClientCount(UInteger value);

    UInteger getTransferredToSameClientCount();

    BaseDataVariableType getTransferredToSameClientCountNode();

    void setTransferredToSameClientCount(UInteger value);

    UInteger getPublishRequestCount();

    BaseDataVariableType getPublishRequestCountNode();

    void setPublishRequestCount(UInteger value);

    UInteger getDataChangeNotificationsCount();

    BaseDataVariableType getDataChangeNotificationsCountNode();

    void setDataChangeNotificationsCount(UInteger value);

    UInteger getEventNotificationsCount();

    BaseDataVariableType getEventNotificationsCountNode();

    void setEventNotificationsCount(UInteger value);

    UInteger getNotificationsCount();

    BaseDataVariableType getNotificationsCountNode();

    void setNotificationsCount(UInteger value);

    UInteger getLatePublishRequestCount();

    BaseDataVariableType getLatePublishRequestCountNode();

    void setLatePublishRequestCount(UInteger value);

    UInteger getCurrentKeepAliveCount();

    BaseDataVariableType getCurrentKeepAliveCountNode();

    void setCurrentKeepAliveCount(UInteger value);

    UInteger getCurrentLifetimeCount();

    BaseDataVariableType getCurrentLifetimeCountNode();

    void setCurrentLifetimeCount(UInteger value);

    UInteger getUnacknowledgedMessageCount();

    BaseDataVariableType getUnacknowledgedMessageCountNode();

    void setUnacknowledgedMessageCount(UInteger value);

    UInteger getDiscardedMessageCount();

    BaseDataVariableType getDiscardedMessageCountNode();

    void setDiscardedMessageCount(UInteger value);

    UInteger getMonitoredItemCount();

    BaseDataVariableType getMonitoredItemCountNode();

    void setMonitoredItemCount(UInteger value);

    UInteger getDisabledMonitoredItemCount();

    BaseDataVariableType getDisabledMonitoredItemCountNode();

    void setDisabledMonitoredItemCount(UInteger value);

    UInteger getMonitoringQueueOverflowCount();

    BaseDataVariableType getMonitoringQueueOverflowCountNode();

    void setMonitoringQueueOverflowCount(UInteger value);

    UInteger getNextSequenceNumber();

    BaseDataVariableType getNextSequenceNumberNode();

    void setNextSequenceNumber(UInteger value);

    UInteger getEventQueueOverFlowCount();

    BaseDataVariableType getEventQueueOverFlowCountNode();

    void setEventQueueOverFlowCount(UInteger value);
}

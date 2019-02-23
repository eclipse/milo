/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface SubscriptionDiagnosticsType extends BaseDataVariableType {
    CompletableFuture<? extends BaseDataVariableType> getSessionIdNode();

    CompletableFuture<NodeId> getSessionId();

    CompletableFuture<StatusCode> setSessionId(NodeId value);

    CompletableFuture<? extends BaseDataVariableType> getSubscriptionIdNode();

    CompletableFuture<UInteger> getSubscriptionId();

    CompletableFuture<StatusCode> setSubscriptionId(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getPriorityNode();

    CompletableFuture<UByte> getPriority();

    CompletableFuture<StatusCode> setPriority(UByte value);

    CompletableFuture<? extends BaseDataVariableType> getPublishingIntervalNode();

    CompletableFuture<Double> getPublishingInterval();

    CompletableFuture<StatusCode> setPublishingInterval(Double value);

    CompletableFuture<? extends BaseDataVariableType> getMaxKeepAliveCountNode();

    CompletableFuture<UInteger> getMaxKeepAliveCount();

    CompletableFuture<StatusCode> setMaxKeepAliveCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getMaxLifetimeCountNode();

    CompletableFuture<UInteger> getMaxLifetimeCount();

    CompletableFuture<StatusCode> setMaxLifetimeCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getMaxNotificationsPerPublishNode();

    CompletableFuture<UInteger> getMaxNotificationsPerPublish();

    CompletableFuture<StatusCode> setMaxNotificationsPerPublish(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getPublishingEnabledNode();

    CompletableFuture<Boolean> getPublishingEnabled();

    CompletableFuture<StatusCode> setPublishingEnabled(Boolean value);

    CompletableFuture<? extends BaseDataVariableType> getModifyCountNode();

    CompletableFuture<UInteger> getModifyCount();

    CompletableFuture<StatusCode> setModifyCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getEnableCountNode();

    CompletableFuture<UInteger> getEnableCount();

    CompletableFuture<StatusCode> setEnableCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getDisableCountNode();

    CompletableFuture<UInteger> getDisableCount();

    CompletableFuture<StatusCode> setDisableCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getRepublishRequestCountNode();

    CompletableFuture<UInteger> getRepublishRequestCount();

    CompletableFuture<StatusCode> setRepublishRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getRepublishMessageRequestCountNode();

    CompletableFuture<UInteger> getRepublishMessageRequestCount();

    CompletableFuture<StatusCode> setRepublishMessageRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getRepublishMessageCountNode();

    CompletableFuture<UInteger> getRepublishMessageCount();

    CompletableFuture<StatusCode> setRepublishMessageCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getTransferRequestCountNode();

    CompletableFuture<UInteger> getTransferRequestCount();

    CompletableFuture<StatusCode> setTransferRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getTransferredToAltClientCountNode();

    CompletableFuture<UInteger> getTransferredToAltClientCount();

    CompletableFuture<StatusCode> setTransferredToAltClientCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getTransferredToSameClientCountNode();

    CompletableFuture<UInteger> getTransferredToSameClientCount();

    CompletableFuture<StatusCode> setTransferredToSameClientCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getPublishRequestCountNode();

    CompletableFuture<UInteger> getPublishRequestCount();

    CompletableFuture<StatusCode> setPublishRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getDataChangeNotificationsCountNode();

    CompletableFuture<UInteger> getDataChangeNotificationsCount();

    CompletableFuture<StatusCode> setDataChangeNotificationsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getEventNotificationsCountNode();

    CompletableFuture<UInteger> getEventNotificationsCount();

    CompletableFuture<StatusCode> setEventNotificationsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getNotificationsCountNode();

    CompletableFuture<UInteger> getNotificationsCount();

    CompletableFuture<StatusCode> setNotificationsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getLatePublishRequestCountNode();

    CompletableFuture<UInteger> getLatePublishRequestCount();

    CompletableFuture<StatusCode> setLatePublishRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getCurrentKeepAliveCountNode();

    CompletableFuture<UInteger> getCurrentKeepAliveCount();

    CompletableFuture<StatusCode> setCurrentKeepAliveCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getCurrentLifetimeCountNode();

    CompletableFuture<UInteger> getCurrentLifetimeCount();

    CompletableFuture<StatusCode> setCurrentLifetimeCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getUnacknowledgedMessageCountNode();

    CompletableFuture<UInteger> getUnacknowledgedMessageCount();

    CompletableFuture<StatusCode> setUnacknowledgedMessageCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getDiscardedMessageCountNode();

    CompletableFuture<UInteger> getDiscardedMessageCount();

    CompletableFuture<StatusCode> setDiscardedMessageCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getMonitoredItemCountNode();

    CompletableFuture<UInteger> getMonitoredItemCount();

    CompletableFuture<StatusCode> setMonitoredItemCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getDisabledMonitoredItemCountNode();

    CompletableFuture<UInteger> getDisabledMonitoredItemCount();

    CompletableFuture<StatusCode> setDisabledMonitoredItemCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getMonitoringQueueOverflowCountNode();

    CompletableFuture<UInteger> getMonitoringQueueOverflowCount();

    CompletableFuture<StatusCode> setMonitoringQueueOverflowCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getNextSequenceNumberNode();

    CompletableFuture<UInteger> getNextSequenceNumber();

    CompletableFuture<StatusCode> setNextSequenceNumber(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getEventQueueOverFlowCountNode();

    CompletableFuture<UInteger> getEventQueueOverFlowCount();

    CompletableFuture<StatusCode> setEventQueueOverFlowCount(UInteger value);
}

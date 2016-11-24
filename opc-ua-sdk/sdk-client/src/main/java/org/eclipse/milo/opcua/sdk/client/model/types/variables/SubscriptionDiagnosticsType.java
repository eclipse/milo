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

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public interface SubscriptionDiagnosticsType extends BaseDataVariableType {


    CompletableFuture<? extends BaseDataVariableType> sessionId();

    CompletableFuture<NodeId> getSessionId();

    CompletableFuture<StatusCode> setSessionId(NodeId value);

    CompletableFuture<? extends BaseDataVariableType> subscriptionId();

    CompletableFuture<UInteger> getSubscriptionId();

    CompletableFuture<StatusCode> setSubscriptionId(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> priority();

    CompletableFuture<UByte> getPriority();

    CompletableFuture<StatusCode> setPriority(UByte value);

    CompletableFuture<? extends BaseDataVariableType> publishingInterval();

    CompletableFuture<Double> getPublishingInterval();

    CompletableFuture<StatusCode> setPublishingInterval(Double value);

    CompletableFuture<? extends BaseDataVariableType> maxKeepAliveCount();

    CompletableFuture<UInteger> getMaxKeepAliveCount();

    CompletableFuture<StatusCode> setMaxKeepAliveCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> maxLifetimeCount();

    CompletableFuture<UInteger> getMaxLifetimeCount();

    CompletableFuture<StatusCode> setMaxLifetimeCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> maxNotificationsPerPublish();

    CompletableFuture<UInteger> getMaxNotificationsPerPublish();

    CompletableFuture<StatusCode> setMaxNotificationsPerPublish(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> publishingEnabled();

    CompletableFuture<Boolean> getPublishingEnabled();

    CompletableFuture<StatusCode> setPublishingEnabled(Boolean value);

    CompletableFuture<? extends BaseDataVariableType> modifyCount();

    CompletableFuture<UInteger> getModifyCount();

    CompletableFuture<StatusCode> setModifyCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> enableCount();

    CompletableFuture<UInteger> getEnableCount();

    CompletableFuture<StatusCode> setEnableCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> disableCount();

    CompletableFuture<UInteger> getDisableCount();

    CompletableFuture<StatusCode> setDisableCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> republishRequestCount();

    CompletableFuture<UInteger> getRepublishRequestCount();

    CompletableFuture<StatusCode> setRepublishRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> republishMessageRequestCount();

    CompletableFuture<UInteger> getRepublishMessageRequestCount();

    CompletableFuture<StatusCode> setRepublishMessageRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> republishMessageCount();

    CompletableFuture<UInteger> getRepublishMessageCount();

    CompletableFuture<StatusCode> setRepublishMessageCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> transferRequestCount();

    CompletableFuture<UInteger> getTransferRequestCount();

    CompletableFuture<StatusCode> setTransferRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> transferredToAltClientCount();

    CompletableFuture<UInteger> getTransferredToAltClientCount();

    CompletableFuture<StatusCode> setTransferredToAltClientCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> transferredToSameClientCount();

    CompletableFuture<UInteger> getTransferredToSameClientCount();

    CompletableFuture<StatusCode> setTransferredToSameClientCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> publishRequestCount();

    CompletableFuture<UInteger> getPublishRequestCount();

    CompletableFuture<StatusCode> setPublishRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> dataChangeNotificationsCount();

    CompletableFuture<UInteger> getDataChangeNotificationsCount();

    CompletableFuture<StatusCode> setDataChangeNotificationsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> eventNotificationsCount();

    CompletableFuture<UInteger> getEventNotificationsCount();

    CompletableFuture<StatusCode> setEventNotificationsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> notificationsCount();

    CompletableFuture<UInteger> getNotificationsCount();

    CompletableFuture<StatusCode> setNotificationsCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> latePublishRequestCount();

    CompletableFuture<UInteger> getLatePublishRequestCount();

    CompletableFuture<StatusCode> setLatePublishRequestCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> currentKeepAliveCount();

    CompletableFuture<UInteger> getCurrentKeepAliveCount();

    CompletableFuture<StatusCode> setCurrentKeepAliveCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> currentLifetimeCount();

    CompletableFuture<UInteger> getCurrentLifetimeCount();

    CompletableFuture<StatusCode> setCurrentLifetimeCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> unacknowledgedMessageCount();

    CompletableFuture<UInteger> getUnacknowledgedMessageCount();

    CompletableFuture<StatusCode> setUnacknowledgedMessageCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> discardedMessageCount();

    CompletableFuture<UInteger> getDiscardedMessageCount();

    CompletableFuture<StatusCode> setDiscardedMessageCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> monitoredItemCount();

    CompletableFuture<UInteger> getMonitoredItemCount();

    CompletableFuture<StatusCode> setMonitoredItemCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> disabledMonitoredItemCount();

    CompletableFuture<UInteger> getDisabledMonitoredItemCount();

    CompletableFuture<StatusCode> setDisabledMonitoredItemCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> monitoringQueueOverflowCount();

    CompletableFuture<UInteger> getMonitoringQueueOverflowCount();

    CompletableFuture<StatusCode> setMonitoringQueueOverflowCount(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> nextSequenceNumber();

    CompletableFuture<UInteger> getNextSequenceNumber();

    CompletableFuture<StatusCode> setNextSequenceNumber(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> eventQueueOverFlowCount();

    CompletableFuture<UInteger> getEventQueueOverFlowCount();

    CompletableFuture<StatusCode> setEventQueueOverFlowCount(UInteger value);


}
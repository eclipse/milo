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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public class SubscriptionDiagnosticsNode extends BaseDataVariableNode implements SubscriptionDiagnosticsType {

    public SubscriptionDiagnosticsNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<BaseDataVariableNode> sessionId() {
        return getComponent(QualifiedName.parse("0:SessionId"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<NodeId> getSessionId() {
        return sessionId()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, NodeId.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionId(NodeId value) {
        return sessionId()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> subscriptionId() {
        return getComponent(QualifiedName.parse("0:SubscriptionId"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSubscriptionId() {
        return subscriptionId()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSubscriptionId(UInteger value) {
        return subscriptionId()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> priority() {
        return getComponent(QualifiedName.parse("0:Priority"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UByte> getPriority() {
        return priority()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UByte.class));
    }

    @Override
    public CompletableFuture<StatusCode> setPriority(UByte value) {
        return priority()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> publishingInterval() {
        return getComponent(QualifiedName.parse("0:PublishingInterval"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<Double> getPublishingInterval() {
        return publishingInterval()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, Double.class));
    }

    @Override
    public CompletableFuture<StatusCode> setPublishingInterval(Double value) {
        return publishingInterval()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> maxKeepAliveCount() {
        return getComponent(QualifiedName.parse("0:MaxKeepAliveCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxKeepAliveCount() {
        return maxKeepAliveCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setMaxKeepAliveCount(UInteger value) {
        return maxKeepAliveCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> maxLifetimeCount() {
        return getComponent(QualifiedName.parse("0:MaxLifetimeCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxLifetimeCount() {
        return maxLifetimeCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setMaxLifetimeCount(UInteger value) {
        return maxLifetimeCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> maxNotificationsPerPublish() {
        return getComponent(QualifiedName.parse("0:MaxNotificationsPerPublish"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxNotificationsPerPublish() {
        return maxNotificationsPerPublish()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setMaxNotificationsPerPublish(UInteger value) {
        return maxNotificationsPerPublish()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> publishingEnabled() {
        return getComponent(QualifiedName.parse("0:PublishingEnabled"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<Boolean> getPublishingEnabled() {
        return publishingEnabled()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, Boolean.class));
    }

    @Override
    public CompletableFuture<StatusCode> setPublishingEnabled(Boolean value) {
        return publishingEnabled()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> modifyCount() {
        return getComponent(QualifiedName.parse("0:ModifyCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getModifyCount() {
        return modifyCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setModifyCount(UInteger value) {
        return modifyCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> enableCount() {
        return getComponent(QualifiedName.parse("0:EnableCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getEnableCount() {
        return enableCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEnableCount(UInteger value) {
        return enableCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> disableCount() {
        return getComponent(QualifiedName.parse("0:DisableCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDisableCount() {
        return disableCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDisableCount(UInteger value) {
        return disableCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> republishRequestCount() {
        return getComponent(QualifiedName.parse("0:RepublishRequestCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRepublishRequestCount() {
        return republishRequestCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRepublishRequestCount(UInteger value) {
        return republishRequestCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> republishMessageRequestCount() {
        return getComponent(QualifiedName.parse("0:RepublishMessageRequestCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRepublishMessageRequestCount() {
        return republishMessageRequestCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRepublishMessageRequestCount(UInteger value) {
        return republishMessageRequestCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> republishMessageCount() {
        return getComponent(QualifiedName.parse("0:RepublishMessageCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRepublishMessageCount() {
        return republishMessageCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRepublishMessageCount(UInteger value) {
        return republishMessageCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> transferRequestCount() {
        return getComponent(QualifiedName.parse("0:TransferRequestCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getTransferRequestCount() {
        return transferRequestCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTransferRequestCount(UInteger value) {
        return transferRequestCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> transferredToAltClientCount() {
        return getComponent(QualifiedName.parse("0:TransferredToAltClientCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getTransferredToAltClientCount() {
        return transferredToAltClientCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTransferredToAltClientCount(UInteger value) {
        return transferredToAltClientCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> transferredToSameClientCount() {
        return getComponent(QualifiedName.parse("0:TransferredToSameClientCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getTransferredToSameClientCount() {
        return transferredToSameClientCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTransferredToSameClientCount(UInteger value) {
        return transferredToSameClientCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> publishRequestCount() {
        return getComponent(QualifiedName.parse("0:PublishRequestCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getPublishRequestCount() {
        return publishRequestCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setPublishRequestCount(UInteger value) {
        return publishRequestCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> dataChangeNotificationsCount() {
        return getComponent(QualifiedName.parse("0:DataChangeNotificationsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDataChangeNotificationsCount() {
        return dataChangeNotificationsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDataChangeNotificationsCount(UInteger value) {
        return dataChangeNotificationsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> eventNotificationsCount() {
        return getComponent(QualifiedName.parse("0:EventNotificationsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getEventNotificationsCount() {
        return eventNotificationsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEventNotificationsCount(UInteger value) {
        return eventNotificationsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> notificationsCount() {
        return getComponent(QualifiedName.parse("0:NotificationsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getNotificationsCount() {
        return notificationsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setNotificationsCount(UInteger value) {
        return notificationsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> latePublishRequestCount() {
        return getComponent(QualifiedName.parse("0:LatePublishRequestCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getLatePublishRequestCount() {
        return latePublishRequestCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setLatePublishRequestCount(UInteger value) {
        return latePublishRequestCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> currentKeepAliveCount() {
        return getComponent(QualifiedName.parse("0:CurrentKeepAliveCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentKeepAliveCount() {
        return currentKeepAliveCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentKeepAliveCount(UInteger value) {
        return currentKeepAliveCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> currentLifetimeCount() {
        return getComponent(QualifiedName.parse("0:CurrentLifetimeCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentLifetimeCount() {
        return currentLifetimeCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentLifetimeCount(UInteger value) {
        return currentLifetimeCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> unacknowledgedMessageCount() {
        return getComponent(QualifiedName.parse("0:UnacknowledgedMessageCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getUnacknowledgedMessageCount() {
        return unacknowledgedMessageCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setUnacknowledgedMessageCount(UInteger value) {
        return unacknowledgedMessageCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> discardedMessageCount() {
        return getComponent(QualifiedName.parse("0:DiscardedMessageCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDiscardedMessageCount() {
        return discardedMessageCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDiscardedMessageCount(UInteger value) {
        return discardedMessageCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> monitoredItemCount() {
        return getComponent(QualifiedName.parse("0:MonitoredItemCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMonitoredItemCount() {
        return monitoredItemCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setMonitoredItemCount(UInteger value) {
        return monitoredItemCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> disabledMonitoredItemCount() {
        return getComponent(QualifiedName.parse("0:DisabledMonitoredItemCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDisabledMonitoredItemCount() {
        return disabledMonitoredItemCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDisabledMonitoredItemCount(UInteger value) {
        return disabledMonitoredItemCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> monitoringQueueOverflowCount() {
        return getComponent(QualifiedName.parse("0:MonitoringQueueOverflowCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMonitoringQueueOverflowCount() {
        return monitoringQueueOverflowCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setMonitoringQueueOverflowCount(UInteger value) {
        return monitoringQueueOverflowCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> nextSequenceNumber() {
        return getComponent(QualifiedName.parse("0:NextSequenceNumber"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getNextSequenceNumber() {
        return nextSequenceNumber()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setNextSequenceNumber(UInteger value) {
        return nextSequenceNumber()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> eventQueueOverFlowCount() {
        return getComponent(QualifiedName.parse("0:EventQueueOverFlowCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getEventQueueOverFlowCount() {
        return eventQueueOverFlowCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEventQueueOverFlowCount(UInteger value) {
        return eventQueueOverFlowCount()
            .thenCompose(node -> node.setValue(value));
    }

}
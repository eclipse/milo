/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class SubscriptionDiagnosticsNode extends BaseDataVariableNode implements SubscriptionDiagnosticsType {
    public SubscriptionDiagnosticsNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<BaseDataVariableNode> getSessionIdNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionId").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<NodeId> getSessionId() {
        return getSessionIdNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, NodeId.class));
    }

    public CompletableFuture<StatusCode> setSessionId(NodeId value) {
        return getSessionIdNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSubscriptionIdNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SubscriptionId").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getSubscriptionId() {
        return getSubscriptionIdNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setSubscriptionId(UInteger value) {
        return getSubscriptionIdNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getPriorityNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "Priority").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UByte> getPriority() {
        return getPriorityNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UByte.class));
    }

    public CompletableFuture<StatusCode> setPriority(UByte value) {
        return getPriorityNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getPublishingIntervalNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "PublishingInterval").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<Double> getPublishingInterval() {
        return getPublishingIntervalNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, Double.class));
    }

    public CompletableFuture<StatusCode> setPublishingInterval(Double value) {
        return getPublishingIntervalNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getMaxKeepAliveCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "MaxKeepAliveCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxKeepAliveCount() {
        return getMaxKeepAliveCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setMaxKeepAliveCount(UInteger value) {
        return getMaxKeepAliveCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getMaxLifetimeCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "MaxLifetimeCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxLifetimeCount() {
        return getMaxLifetimeCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setMaxLifetimeCount(UInteger value) {
        return getMaxLifetimeCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getMaxNotificationsPerPublishNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "MaxNotificationsPerPublish").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxNotificationsPerPublish() {
        return getMaxNotificationsPerPublishNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setMaxNotificationsPerPublish(UInteger value) {
        return getMaxNotificationsPerPublishNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getPublishingEnabledNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "PublishingEnabled").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<Boolean> getPublishingEnabled() {
        return getPublishingEnabledNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, Boolean.class));
    }

    public CompletableFuture<StatusCode> setPublishingEnabled(Boolean value) {
        return getPublishingEnabledNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getModifyCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ModifyCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getModifyCount() {
        return getModifyCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setModifyCount(UInteger value) {
        return getModifyCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getEnableCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EnableCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getEnableCount() {
        return getEnableCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setEnableCount(UInteger value) {
        return getEnableCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getDisableCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DisableCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDisableCount() {
        return getDisableCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setDisableCount(UInteger value) {
        return getDisableCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getRepublishRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RepublishRequestCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRepublishRequestCount() {
        return getRepublishRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setRepublishRequestCount(UInteger value) {
        return getRepublishRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getRepublishMessageRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RepublishMessageRequestCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRepublishMessageRequestCount() {
        return getRepublishMessageRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setRepublishMessageRequestCount(UInteger value) {
        return getRepublishMessageRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getRepublishMessageCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RepublishMessageCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getRepublishMessageCount() {
        return getRepublishMessageCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setRepublishMessageCount(UInteger value) {
        return getRepublishMessageCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getTransferRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TransferRequestCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getTransferRequestCount() {
        return getTransferRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setTransferRequestCount(UInteger value) {
        return getTransferRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getTransferredToAltClientCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TransferredToAltClientCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getTransferredToAltClientCount() {
        return getTransferredToAltClientCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setTransferredToAltClientCount(UInteger value) {
        return getTransferredToAltClientCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getTransferredToSameClientCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TransferredToSameClientCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getTransferredToSameClientCount() {
        return getTransferredToSameClientCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setTransferredToSameClientCount(UInteger value) {
        return getTransferredToSameClientCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getPublishRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "PublishRequestCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getPublishRequestCount() {
        return getPublishRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setPublishRequestCount(UInteger value) {
        return getPublishRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getDataChangeNotificationsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DataChangeNotificationsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDataChangeNotificationsCount() {
        return getDataChangeNotificationsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setDataChangeNotificationsCount(UInteger value) {
        return getDataChangeNotificationsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getEventNotificationsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EventNotificationsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getEventNotificationsCount() {
        return getEventNotificationsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setEventNotificationsCount(UInteger value) {
        return getEventNotificationsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getNotificationsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "NotificationsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getNotificationsCount() {
        return getNotificationsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setNotificationsCount(UInteger value) {
        return getNotificationsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getLatePublishRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LatePublishRequestCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getLatePublishRequestCount() {
        return getLatePublishRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setLatePublishRequestCount(UInteger value) {
        return getLatePublishRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCurrentKeepAliveCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentKeepAliveCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentKeepAliveCount() {
        return getCurrentKeepAliveCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCurrentKeepAliveCount(UInteger value) {
        return getCurrentKeepAliveCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCurrentLifetimeCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentLifetimeCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentLifetimeCount() {
        return getCurrentLifetimeCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCurrentLifetimeCount(UInteger value) {
        return getCurrentLifetimeCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getUnacknowledgedMessageCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "UnacknowledgedMessageCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getUnacknowledgedMessageCount() {
        return getUnacknowledgedMessageCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setUnacknowledgedMessageCount(UInteger value) {
        return getUnacknowledgedMessageCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getDiscardedMessageCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DiscardedMessageCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDiscardedMessageCount() {
        return getDiscardedMessageCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setDiscardedMessageCount(UInteger value) {
        return getDiscardedMessageCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getMonitoredItemCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "MonitoredItemCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMonitoredItemCount() {
        return getMonitoredItemCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setMonitoredItemCount(UInteger value) {
        return getMonitoredItemCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getDisabledMonitoredItemCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DisabledMonitoredItemCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getDisabledMonitoredItemCount() {
        return getDisabledMonitoredItemCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setDisabledMonitoredItemCount(UInteger value) {
        return getDisabledMonitoredItemCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getMonitoringQueueOverflowCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "MonitoringQueueOverflowCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMonitoringQueueOverflowCount() {
        return getMonitoringQueueOverflowCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setMonitoringQueueOverflowCount(UInteger value) {
        return getMonitoringQueueOverflowCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getNextSequenceNumberNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "NextSequenceNumber").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getNextSequenceNumber() {
        return getNextSequenceNumberNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setNextSequenceNumber(UInteger value) {
        return getNextSequenceNumberNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getEventQueueOverFlowCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EventQueueOverFlowCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getEventQueueOverFlowCount() {
        return getEventQueueOverFlowCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setEventQueueOverFlowCount(UInteger value) {
        return getEventQueueOverFlowCountNode().thenCompose(node -> node.setValue(value));
    }
}

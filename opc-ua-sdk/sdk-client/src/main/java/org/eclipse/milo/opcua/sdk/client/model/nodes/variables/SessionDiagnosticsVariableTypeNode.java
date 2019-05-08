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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;

public class SessionDiagnosticsVariableTypeNode extends BaseDataVariableTypeNode implements SessionDiagnosticsVariableType {
    public SessionDiagnosticsVariableTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSessionIdNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionId").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<NodeId> getSessionId() {
        return getSessionIdNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, NodeId.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionId(NodeId value) {
        return getSessionIdNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSessionNameNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionName").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getSessionName() {
        return getSessionNameNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionName(String value) {
        return getSessionNameNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getClientDescriptionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientDescription").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ApplicationDescription> getClientDescription() {
        return getClientDescriptionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ApplicationDescription.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientDescription(ApplicationDescription value) {
        return getClientDescriptionNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getServerUriNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ServerUri").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getServerUri() {
        return getServerUriNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setServerUri(String value) {
        return getServerUriNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getEndpointUrlNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EndpointUrl").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getEndpointUrl() {
        return getEndpointUrlNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEndpointUrl(String value) {
        return getEndpointUrlNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getLocaleIdsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LocaleIds").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String[]> getLocaleIds() {
        return getLocaleIdsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setLocaleIds(String[] value) {
        return getLocaleIdsNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getActualSessionTimeoutNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ActualSessionTimeout").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<Double> getActualSessionTimeout() {
        return getActualSessionTimeoutNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, Double.class));
    }

    @Override
    public CompletableFuture<StatusCode> setActualSessionTimeout(Double value) {
        return getActualSessionTimeoutNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getMaxResponseMessageSizeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "MaxResponseMessageSize").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getMaxResponseMessageSize() {
        return getMaxResponseMessageSizeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setMaxResponseMessageSize(UInteger value) {
        return getMaxResponseMessageSizeNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getClientConnectionTimeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientConnectionTime").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<DateTime> getClientConnectionTime() {
        return getClientConnectionTimeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientConnectionTime(DateTime value) {
        return getClientConnectionTimeNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getClientLastContactTimeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientLastContactTime").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<DateTime> getClientLastContactTime() {
        return getClientLastContactTimeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientLastContactTime(DateTime value) {
        return getClientLastContactTimeNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCurrentSubscriptionsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentSubscriptionsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getCurrentSubscriptionsCount() {
        return getCurrentSubscriptionsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentSubscriptionsCount(UInteger value) {
        return getCurrentSubscriptionsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCurrentMonitoredItemsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentMonitoredItemsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getCurrentMonitoredItemsCount() {
        return getCurrentMonitoredItemsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentMonitoredItemsCount(UInteger value) {
        return getCurrentMonitoredItemsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCurrentPublishRequestsInQueueNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentPublishRequestsInQueue").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getCurrentPublishRequestsInQueue() {
        return getCurrentPublishRequestsInQueueNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentPublishRequestsInQueue(UInteger value) {
        return getCurrentPublishRequestsInQueueNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getTotalRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TotalRequestCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getTotalRequestCount() {
        return getTotalRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTotalRequestCount(ServiceCounterDataType value) {
        return getTotalRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getUnauthorizedRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "UnauthorizedRequestCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<UInteger> getUnauthorizedRequestCount() {
        return getUnauthorizedRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setUnauthorizedRequestCount(UInteger value) {
        return getUnauthorizedRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getReadCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ReadCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getReadCount() {
        return getReadCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setReadCount(ServiceCounterDataType value) {
        return getReadCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getHistoryReadCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "HistoryReadCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getHistoryReadCount() {
        return getHistoryReadCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setHistoryReadCount(ServiceCounterDataType value) {
        return getHistoryReadCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getWriteCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "WriteCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getWriteCount() {
        return getWriteCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setWriteCount(ServiceCounterDataType value) {
        return getWriteCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getHistoryUpdateCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "HistoryUpdateCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getHistoryUpdateCount() {
        return getHistoryUpdateCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setHistoryUpdateCount(ServiceCounterDataType value) {
        return getHistoryUpdateCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCallCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CallCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getCallCount() {
        return getCallCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCallCount(ServiceCounterDataType value) {
        return getCallCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCreateMonitoredItemsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CreateMonitoredItemsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getCreateMonitoredItemsCount() {
        return getCreateMonitoredItemsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCreateMonitoredItemsCount(ServiceCounterDataType value) {
        return getCreateMonitoredItemsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getModifyMonitoredItemsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ModifyMonitoredItemsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getModifyMonitoredItemsCount() {
        return getModifyMonitoredItemsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setModifyMonitoredItemsCount(ServiceCounterDataType value) {
        return getModifyMonitoredItemsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSetMonitoringModeCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SetMonitoringModeCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getSetMonitoringModeCount() {
        return getSetMonitoringModeCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSetMonitoringModeCount(ServiceCounterDataType value) {
        return getSetMonitoringModeCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSetTriggeringCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SetTriggeringCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getSetTriggeringCount() {
        return getSetTriggeringCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSetTriggeringCount(ServiceCounterDataType value) {
        return getSetTriggeringCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getDeleteMonitoredItemsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DeleteMonitoredItemsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getDeleteMonitoredItemsCount() {
        return getDeleteMonitoredItemsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDeleteMonitoredItemsCount(ServiceCounterDataType value) {
        return getDeleteMonitoredItemsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getCreateSubscriptionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CreateSubscriptionCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getCreateSubscriptionCount() {
        return getCreateSubscriptionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCreateSubscriptionCount(ServiceCounterDataType value) {
        return getCreateSubscriptionCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getModifySubscriptionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ModifySubscriptionCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getModifySubscriptionCount() {
        return getModifySubscriptionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setModifySubscriptionCount(ServiceCounterDataType value) {
        return getModifySubscriptionCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSetPublishingModeCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SetPublishingModeCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getSetPublishingModeCount() {
        return getSetPublishingModeCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSetPublishingModeCount(ServiceCounterDataType value) {
        return getSetPublishingModeCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getPublishCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "PublishCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getPublishCount() {
        return getPublishCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setPublishCount(ServiceCounterDataType value) {
        return getPublishCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getRepublishCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RepublishCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getRepublishCount() {
        return getRepublishCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRepublishCount(ServiceCounterDataType value) {
        return getRepublishCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getTransferSubscriptionsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TransferSubscriptionsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getTransferSubscriptionsCount() {
        return getTransferSubscriptionsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTransferSubscriptionsCount(ServiceCounterDataType value) {
        return getTransferSubscriptionsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getDeleteSubscriptionsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DeleteSubscriptionsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getDeleteSubscriptionsCount() {
        return getDeleteSubscriptionsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDeleteSubscriptionsCount(ServiceCounterDataType value) {
        return getDeleteSubscriptionsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getAddNodesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "AddNodesCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getAddNodesCount() {
        return getAddNodesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setAddNodesCount(ServiceCounterDataType value) {
        return getAddNodesCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getAddReferencesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "AddReferencesCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getAddReferencesCount() {
        return getAddReferencesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setAddReferencesCount(ServiceCounterDataType value) {
        return getAddReferencesCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getDeleteNodesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DeleteNodesCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getDeleteNodesCount() {
        return getDeleteNodesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDeleteNodesCount(ServiceCounterDataType value) {
        return getDeleteNodesCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getDeleteReferencesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DeleteReferencesCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getDeleteReferencesCount() {
        return getDeleteReferencesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDeleteReferencesCount(ServiceCounterDataType value) {
        return getDeleteReferencesCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getBrowseCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BrowseCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getBrowseCount() {
        return getBrowseCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBrowseCount(ServiceCounterDataType value) {
        return getBrowseCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getBrowseNextCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BrowseNextCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getBrowseNextCount() {
        return getBrowseNextCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBrowseNextCount(ServiceCounterDataType value) {
        return getBrowseNextCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getTranslateBrowsePathsToNodeIdsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TranslateBrowsePathsToNodeIdsCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getTranslateBrowsePathsToNodeIdsCount() {
        return getTranslateBrowsePathsToNodeIdsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTranslateBrowsePathsToNodeIdsCount(
        ServiceCounterDataType value) {
        return getTranslateBrowsePathsToNodeIdsCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getQueryFirstCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "QueryFirstCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getQueryFirstCount() {
        return getQueryFirstCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setQueryFirstCount(ServiceCounterDataType value) {
        return getQueryFirstCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getQueryNextCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "QueryNextCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getQueryNextCount() {
        return getQueryNextCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setQueryNextCount(ServiceCounterDataType value) {
        return getQueryNextCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getRegisterNodesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RegisterNodesCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getRegisterNodesCount() {
        return getRegisterNodesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRegisterNodesCount(ServiceCounterDataType value) {
        return getRegisterNodesCountNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getUnregisterNodesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "UnregisterNodesCount").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServiceCounterDataType> getUnregisterNodesCount() {
        return getUnregisterNodesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setUnregisterNodesCount(ServiceCounterDataType value) {
        return getUnregisterNodesCountNode().thenCompose(node -> node.setValue(value));
    }
}

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

public class SessionDiagnosticsVariableNode extends BaseDataVariableNode implements SessionDiagnosticsVariableType {
    public SessionDiagnosticsVariableNode(OpcUaClient client, NodeId nodeId) {
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

    public CompletableFuture<BaseDataVariableNode> getSessionNameNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionName").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getSessionName() {
        return getSessionNameNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    public CompletableFuture<StatusCode> setSessionName(String value) {
        return getSessionNameNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getClientDescriptionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientDescription").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ApplicationDescription> getClientDescription() {
        return getClientDescriptionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ApplicationDescription.class));
    }

    public CompletableFuture<StatusCode> setClientDescription(ApplicationDescription value) {
        return getClientDescriptionNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getServerUriNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ServerUri").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getServerUri() {
        return getServerUriNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    public CompletableFuture<StatusCode> setServerUri(String value) {
        return getServerUriNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getEndpointUrlNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EndpointUrl").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getEndpointUrl() {
        return getEndpointUrlNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    public CompletableFuture<StatusCode> setEndpointUrl(String value) {
        return getEndpointUrlNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getLocaleIdsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LocaleIds").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String[]> getLocaleIds() {
        return getLocaleIdsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String[].class));
    }

    public CompletableFuture<StatusCode> setLocaleIds(String[] value) {
        return getLocaleIdsNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getActualSessionTimeoutNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ActualSessionTimeout").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<Double> getActualSessionTimeout() {
        return getActualSessionTimeoutNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, Double.class));
    }

    public CompletableFuture<StatusCode> setActualSessionTimeout(Double value) {
        return getActualSessionTimeoutNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getMaxResponseMessageSizeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "MaxResponseMessageSize").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxResponseMessageSize() {
        return getMaxResponseMessageSizeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setMaxResponseMessageSize(UInteger value) {
        return getMaxResponseMessageSizeNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getClientConnectionTimeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientConnectionTime").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getClientConnectionTime() {
        return getClientConnectionTimeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    public CompletableFuture<StatusCode> setClientConnectionTime(DateTime value) {
        return getClientConnectionTimeNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getClientLastContactTimeNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ClientLastContactTime").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getClientLastContactTime() {
        return getClientLastContactTimeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    public CompletableFuture<StatusCode> setClientLastContactTime(DateTime value) {
        return getClientLastContactTimeNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCurrentSubscriptionsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentSubscriptionsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentSubscriptionsCount() {
        return getCurrentSubscriptionsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCurrentSubscriptionsCount(UInteger value) {
        return getCurrentSubscriptionsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCurrentMonitoredItemsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentMonitoredItemsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentMonitoredItemsCount() {
        return getCurrentMonitoredItemsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCurrentMonitoredItemsCount(UInteger value) {
        return getCurrentMonitoredItemsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCurrentPublishRequestsInQueueNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CurrentPublishRequestsInQueue").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentPublishRequestsInQueue() {
        return getCurrentPublishRequestsInQueueNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setCurrentPublishRequestsInQueue(UInteger value) {
        return getCurrentPublishRequestsInQueueNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getTotalRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TotalRequestCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getTotalRequestCount() {
        return getTotalRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setTotalRequestCount(ServiceCounterDataType value) {
        return getTotalRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getUnauthorizedRequestCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "UnauthorizedRequestCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getUnauthorizedRequestCount() {
        return getUnauthorizedRequestCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UInteger.class));
    }

    public CompletableFuture<StatusCode> setUnauthorizedRequestCount(UInteger value) {
        return getUnauthorizedRequestCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getReadCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ReadCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getReadCount() {
        return getReadCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setReadCount(ServiceCounterDataType value) {
        return getReadCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getHistoryReadCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "HistoryReadCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getHistoryReadCount() {
        return getHistoryReadCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setHistoryReadCount(ServiceCounterDataType value) {
        return getHistoryReadCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getWriteCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "WriteCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getWriteCount() {
        return getWriteCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setWriteCount(ServiceCounterDataType value) {
        return getWriteCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getHistoryUpdateCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "HistoryUpdateCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getHistoryUpdateCount() {
        return getHistoryUpdateCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setHistoryUpdateCount(ServiceCounterDataType value) {
        return getHistoryUpdateCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCallCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CallCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getCallCount() {
        return getCallCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setCallCount(ServiceCounterDataType value) {
        return getCallCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCreateMonitoredItemsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CreateMonitoredItemsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getCreateMonitoredItemsCount() {
        return getCreateMonitoredItemsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setCreateMonitoredItemsCount(ServiceCounterDataType value) {
        return getCreateMonitoredItemsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getModifyMonitoredItemsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ModifyMonitoredItemsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getModifyMonitoredItemsCount() {
        return getModifyMonitoredItemsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setModifyMonitoredItemsCount(ServiceCounterDataType value) {
        return getModifyMonitoredItemsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSetMonitoringModeCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SetMonitoringModeCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getSetMonitoringModeCount() {
        return getSetMonitoringModeCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setSetMonitoringModeCount(ServiceCounterDataType value) {
        return getSetMonitoringModeCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSetTriggeringCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SetTriggeringCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getSetTriggeringCount() {
        return getSetTriggeringCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setSetTriggeringCount(ServiceCounterDataType value) {
        return getSetTriggeringCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getDeleteMonitoredItemsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DeleteMonitoredItemsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getDeleteMonitoredItemsCount() {
        return getDeleteMonitoredItemsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setDeleteMonitoredItemsCount(ServiceCounterDataType value) {
        return getDeleteMonitoredItemsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getCreateSubscriptionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "CreateSubscriptionCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getCreateSubscriptionCount() {
        return getCreateSubscriptionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setCreateSubscriptionCount(ServiceCounterDataType value) {
        return getCreateSubscriptionCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getModifySubscriptionCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ModifySubscriptionCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getModifySubscriptionCount() {
        return getModifySubscriptionCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setModifySubscriptionCount(ServiceCounterDataType value) {
        return getModifySubscriptionCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSetPublishingModeCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SetPublishingModeCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getSetPublishingModeCount() {
        return getSetPublishingModeCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setSetPublishingModeCount(ServiceCounterDataType value) {
        return getSetPublishingModeCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getPublishCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "PublishCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getPublishCount() {
        return getPublishCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setPublishCount(ServiceCounterDataType value) {
        return getPublishCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getRepublishCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RepublishCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getRepublishCount() {
        return getRepublishCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setRepublishCount(ServiceCounterDataType value) {
        return getRepublishCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getTransferSubscriptionsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TransferSubscriptionsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getTransferSubscriptionsCount() {
        return getTransferSubscriptionsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setTransferSubscriptionsCount(ServiceCounterDataType value) {
        return getTransferSubscriptionsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getDeleteSubscriptionsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DeleteSubscriptionsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getDeleteSubscriptionsCount() {
        return getDeleteSubscriptionsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setDeleteSubscriptionsCount(ServiceCounterDataType value) {
        return getDeleteSubscriptionsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getAddNodesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "AddNodesCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getAddNodesCount() {
        return getAddNodesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setAddNodesCount(ServiceCounterDataType value) {
        return getAddNodesCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getAddReferencesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "AddReferencesCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getAddReferencesCount() {
        return getAddReferencesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setAddReferencesCount(ServiceCounterDataType value) {
        return getAddReferencesCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getDeleteNodesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DeleteNodesCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getDeleteNodesCount() {
        return getDeleteNodesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setDeleteNodesCount(ServiceCounterDataType value) {
        return getDeleteNodesCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getDeleteReferencesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DeleteReferencesCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getDeleteReferencesCount() {
        return getDeleteReferencesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setDeleteReferencesCount(ServiceCounterDataType value) {
        return getDeleteReferencesCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getBrowseCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BrowseCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getBrowseCount() {
        return getBrowseCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setBrowseCount(ServiceCounterDataType value) {
        return getBrowseCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getBrowseNextCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BrowseNextCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getBrowseNextCount() {
        return getBrowseNextCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setBrowseNextCount(ServiceCounterDataType value) {
        return getBrowseNextCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getTranslateBrowsePathsToNodeIdsCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "TranslateBrowsePathsToNodeIdsCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getTranslateBrowsePathsToNodeIdsCount() {
        return getTranslateBrowsePathsToNodeIdsCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType value) {
        return getTranslateBrowsePathsToNodeIdsCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getQueryFirstCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "QueryFirstCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getQueryFirstCount() {
        return getQueryFirstCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setQueryFirstCount(ServiceCounterDataType value) {
        return getQueryFirstCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getQueryNextCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "QueryNextCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getQueryNextCount() {
        return getQueryNextCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setQueryNextCount(ServiceCounterDataType value) {
        return getQueryNextCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getRegisterNodesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "RegisterNodesCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getRegisterNodesCount() {
        return getRegisterNodesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setRegisterNodesCount(ServiceCounterDataType value) {
        return getRegisterNodesCountNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getUnregisterNodesCountNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "UnregisterNodesCount").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getUnregisterNodesCount() {
        return getUnregisterNodesCountNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    public CompletableFuture<StatusCode> setUnregisterNodesCount(ServiceCounterDataType value) {
        return getUnregisterNodesCountNode().thenCompose(node -> node.setValue(value));
    }
}

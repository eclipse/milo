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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;


public class SessionDiagnosticsVariableNode extends BaseDataVariableNode implements SessionDiagnosticsVariableType {

    public SessionDiagnosticsVariableNode(OpcUaClient client, NodeId nodeId) {
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
    public CompletableFuture<BaseDataVariableNode> sessionName() {
        return getComponent(QualifiedName.parse("0:SessionName"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getSessionName() {
        return sessionName()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSessionName(String value) {
        return sessionName()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> clientDescription() {
        return getComponent(QualifiedName.parse("0:ClientDescription"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ApplicationDescription> getClientDescription() {
        return clientDescription()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ApplicationDescription.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientDescription(ApplicationDescription value) {
        return clientDescription()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> serverUri() {
        return getComponent(QualifiedName.parse("0:ServerUri"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getServerUri() {
        return serverUri()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setServerUri(String value) {
        return serverUri()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> endpointUrl() {
        return getComponent(QualifiedName.parse("0:EndpointUrl"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getEndpointUrl() {
        return endpointUrl()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEndpointUrl(String value) {
        return endpointUrl()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> localeIds() {
        return getComponent(QualifiedName.parse("0:LocaleIds"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String[]> getLocaleIds() {
        return localeIds()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String[].class));
    }

    @Override
    public CompletableFuture<StatusCode> setLocaleIds(String[] value) {
        return localeIds()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> actualSessionTimeout() {
        return getComponent(QualifiedName.parse("0:ActualSessionTimeout"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<Double> getActualSessionTimeout() {
        return actualSessionTimeout()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, Double.class));
    }

    @Override
    public CompletableFuture<StatusCode> setActualSessionTimeout(Double value) {
        return actualSessionTimeout()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> maxResponseMessageSize() {
        return getComponent(QualifiedName.parse("0:MaxResponseMessageSize"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getMaxResponseMessageSize() {
        return maxResponseMessageSize()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setMaxResponseMessageSize(UInteger value) {
        return maxResponseMessageSize()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> clientConnectionTime() {
        return getComponent(QualifiedName.parse("0:ClientConnectionTime"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getClientConnectionTime() {
        return clientConnectionTime()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientConnectionTime(DateTime value) {
        return clientConnectionTime()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> clientLastContactTime() {
        return getComponent(QualifiedName.parse("0:ClientLastContactTime"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getClientLastContactTime() {
        return clientLastContactTime()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setClientLastContactTime(DateTime value) {
        return clientLastContactTime()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> currentSubscriptionsCount() {
        return getComponent(QualifiedName.parse("0:CurrentSubscriptionsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentSubscriptionsCount() {
        return currentSubscriptionsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentSubscriptionsCount(UInteger value) {
        return currentSubscriptionsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> currentMonitoredItemsCount() {
        return getComponent(QualifiedName.parse("0:CurrentMonitoredItemsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentMonitoredItemsCount() {
        return currentMonitoredItemsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentMonitoredItemsCount(UInteger value) {
        return currentMonitoredItemsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> currentPublishRequestsInQueue() {
        return getComponent(QualifiedName.parse("0:CurrentPublishRequestsInQueue"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getCurrentPublishRequestsInQueue() {
        return currentPublishRequestsInQueue()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCurrentPublishRequestsInQueue(UInteger value) {
        return currentPublishRequestsInQueue()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> totalRequestCount() {
        return getComponent(QualifiedName.parse("0:TotalRequestCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getTotalRequestCount() {
        return totalRequestCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTotalRequestCount(ServiceCounterDataType value) {
        return totalRequestCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> unauthorizedRequestCount() {
        return getComponent(QualifiedName.parse("0:UnauthorizedRequestCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<UInteger> getUnauthorizedRequestCount() {
        return unauthorizedRequestCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UInteger.class));
    }

    @Override
    public CompletableFuture<StatusCode> setUnauthorizedRequestCount(UInteger value) {
        return unauthorizedRequestCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> readCount() {
        return getComponent(QualifiedName.parse("0:ReadCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getReadCount() {
        return readCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setReadCount(ServiceCounterDataType value) {
        return readCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> historyReadCount() {
        return getComponent(QualifiedName.parse("0:HistoryReadCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getHistoryReadCount() {
        return historyReadCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setHistoryReadCount(ServiceCounterDataType value) {
        return historyReadCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> writeCount() {
        return getComponent(QualifiedName.parse("0:WriteCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getWriteCount() {
        return writeCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setWriteCount(ServiceCounterDataType value) {
        return writeCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> historyUpdateCount() {
        return getComponent(QualifiedName.parse("0:HistoryUpdateCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getHistoryUpdateCount() {
        return historyUpdateCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setHistoryUpdateCount(ServiceCounterDataType value) {
        return historyUpdateCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> callCount() {
        return getComponent(QualifiedName.parse("0:CallCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getCallCount() {
        return callCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCallCount(ServiceCounterDataType value) {
        return callCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> createMonitoredItemsCount() {
        return getComponent(QualifiedName.parse("0:CreateMonitoredItemsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getCreateMonitoredItemsCount() {
        return createMonitoredItemsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCreateMonitoredItemsCount(ServiceCounterDataType value) {
        return createMonitoredItemsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> modifyMonitoredItemsCount() {
        return getComponent(QualifiedName.parse("0:ModifyMonitoredItemsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getModifyMonitoredItemsCount() {
        return modifyMonitoredItemsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setModifyMonitoredItemsCount(ServiceCounterDataType value) {
        return modifyMonitoredItemsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> setMonitoringModeCount() {
        return getComponent(QualifiedName.parse("0:SetMonitoringModeCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getSetMonitoringModeCount() {
        return setMonitoringModeCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSetMonitoringModeCount(ServiceCounterDataType value) {
        return setMonitoringModeCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> setTriggeringCount() {
        return getComponent(QualifiedName.parse("0:SetTriggeringCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getSetTriggeringCount() {
        return setTriggeringCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSetTriggeringCount(ServiceCounterDataType value) {
        return setTriggeringCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> deleteMonitoredItemsCount() {
        return getComponent(QualifiedName.parse("0:DeleteMonitoredItemsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getDeleteMonitoredItemsCount() {
        return deleteMonitoredItemsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDeleteMonitoredItemsCount(ServiceCounterDataType value) {
        return deleteMonitoredItemsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> createSubscriptionCount() {
        return getComponent(QualifiedName.parse("0:CreateSubscriptionCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getCreateSubscriptionCount() {
        return createSubscriptionCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setCreateSubscriptionCount(ServiceCounterDataType value) {
        return createSubscriptionCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> modifySubscriptionCount() {
        return getComponent(QualifiedName.parse("0:ModifySubscriptionCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getModifySubscriptionCount() {
        return modifySubscriptionCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setModifySubscriptionCount(ServiceCounterDataType value) {
        return modifySubscriptionCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> setPublishingModeCount() {
        return getComponent(QualifiedName.parse("0:SetPublishingModeCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getSetPublishingModeCount() {
        return setPublishingModeCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSetPublishingModeCount(ServiceCounterDataType value) {
        return setPublishingModeCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> publishCount() {
        return getComponent(QualifiedName.parse("0:PublishCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getPublishCount() {
        return publishCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setPublishCount(ServiceCounterDataType value) {
        return publishCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> republishCount() {
        return getComponent(QualifiedName.parse("0:RepublishCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getRepublishCount() {
        return republishCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRepublishCount(ServiceCounterDataType value) {
        return republishCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> transferSubscriptionsCount() {
        return getComponent(QualifiedName.parse("0:TransferSubscriptionsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getTransferSubscriptionsCount() {
        return transferSubscriptionsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTransferSubscriptionsCount(ServiceCounterDataType value) {
        return transferSubscriptionsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> deleteSubscriptionsCount() {
        return getComponent(QualifiedName.parse("0:DeleteSubscriptionsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getDeleteSubscriptionsCount() {
        return deleteSubscriptionsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDeleteSubscriptionsCount(ServiceCounterDataType value) {
        return deleteSubscriptionsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> addNodesCount() {
        return getComponent(QualifiedName.parse("0:AddNodesCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getAddNodesCount() {
        return addNodesCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setAddNodesCount(ServiceCounterDataType value) {
        return addNodesCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> addReferencesCount() {
        return getComponent(QualifiedName.parse("0:AddReferencesCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getAddReferencesCount() {
        return addReferencesCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setAddReferencesCount(ServiceCounterDataType value) {
        return addReferencesCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> deleteNodesCount() {
        return getComponent(QualifiedName.parse("0:DeleteNodesCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getDeleteNodesCount() {
        return deleteNodesCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDeleteNodesCount(ServiceCounterDataType value) {
        return deleteNodesCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> deleteReferencesCount() {
        return getComponent(QualifiedName.parse("0:DeleteReferencesCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getDeleteReferencesCount() {
        return deleteReferencesCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDeleteReferencesCount(ServiceCounterDataType value) {
        return deleteReferencesCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> browseCount() {
        return getComponent(QualifiedName.parse("0:BrowseCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getBrowseCount() {
        return browseCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBrowseCount(ServiceCounterDataType value) {
        return browseCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> browseNextCount() {
        return getComponent(QualifiedName.parse("0:BrowseNextCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getBrowseNextCount() {
        return browseNextCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBrowseNextCount(ServiceCounterDataType value) {
        return browseNextCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> translateBrowsePathsToNodeIdsCount() {
        return getComponent(QualifiedName.parse("0:TranslateBrowsePathsToNodeIdsCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getTranslateBrowsePathsToNodeIdsCount() {
        return translateBrowsePathsToNodeIdsCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType value) {
        return translateBrowsePathsToNodeIdsCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> queryFirstCount() {
        return getComponent(QualifiedName.parse("0:QueryFirstCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getQueryFirstCount() {
        return queryFirstCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setQueryFirstCount(ServiceCounterDataType value) {
        return queryFirstCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> queryNextCount() {
        return getComponent(QualifiedName.parse("0:QueryNextCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getQueryNextCount() {
        return queryNextCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setQueryNextCount(ServiceCounterDataType value) {
        return queryNextCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> registerNodesCount() {
        return getComponent(QualifiedName.parse("0:RegisterNodesCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getRegisterNodesCount() {
        return registerNodesCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setRegisterNodesCount(ServiceCounterDataType value) {
        return registerNodesCount()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> unregisterNodesCount() {
        return getComponent(QualifiedName.parse("0:UnregisterNodesCount"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<ServiceCounterDataType> getUnregisterNodesCount() {
        return unregisterNodesCount()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setUnregisterNodesCount(ServiceCounterDataType value) {
        return unregisterNodesCount()
            .thenCompose(node -> node.setValue(value));
    }

}
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

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:SessionDiagnosticsVariableType")
public class SessionDiagnosticsVariableNode extends BaseDataVariableNode implements SessionDiagnosticsVariableType {

    public SessionDiagnosticsVariableNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeManager, nodeId, variableTypeNode);
    }

    public SessionDiagnosticsVariableNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        DataValue value,
        NodeId dataType,
        Integer valueRank,
        UInteger[] arrayDimensions,
        UByte accessLevel,
        UByte userAccessLevel,
        Double minimumSamplingInterval,
        boolean historizing) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask,
            value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public synchronized DataValue getValue() {
        SessionDiagnosticsDataType value = new SessionDiagnosticsDataType(
            getSessionId(),
            getSessionName(),
            getClientDescription(),
            getServerUri(),
            getEndpointUrl(),
            getLocaleIds(),
            getActualSessionTimeout(),
            getMaxResponseMessageSize(),
            getClientConnectionTime(),
            getClientLastContactTime(),
            getCurrentSubscriptionsCount(),
            getCurrentMonitoredItemsCount(),
            getCurrentPublishRequestsInQueue(),
            getTotalRequestCount(),
            getUnauthorizedRequestCount(),
            getReadCount(),
            getHistoryReadCount(),
            getWriteCount(),
            getHistoryUpdateCount(),
            getCallCount(),
            getCreateMonitoredItemsCount(),
            getModifyMonitoredItemsCount(),
            getSetMonitoringModeCount(),
            getSetTriggeringCount(),
            getDeleteMonitoredItemsCount(),
            getCreateSubscriptionCount(),
            getModifySubscriptionCount(),
            getSetPublishingModeCount(),
            getPublishCount(),
            getRepublishCount(),
            getTransferSubscriptionsCount(),
            getDeleteSubscriptionsCount(),
            getAddNodesCount(),
            getAddReferencesCount(),
            getDeleteNodesCount(),
            getDeleteReferencesCount(),
            getBrowseCount(),
            getBrowseNextCount(),
            getTranslateBrowsePathsToNodeIdsCount(),
            getQueryFirstCount(),
            getQueryNextCount(),
            getRegisterNodesCount(),
            getUnregisterNodesCount()
        );

        return new DataValue(new Variant(value));
    }

    @Override
    public synchronized void setValue(DataValue value) {
        super.setValue(value);

        Object o = value.getValue().getValue();

        if (o instanceof SessionDiagnosticsDataType) {
            SessionDiagnosticsDataType v = (SessionDiagnosticsDataType) o;

            setSessionId(v.getSessionId());
            setSessionName(v.getSessionName());
            setClientDescription(v.getClientDescription());
            setServerUri(v.getServerUri());
            setEndpointUrl(v.getEndpointUrl());
            setLocaleIds(v.getLocaleIds());
            setActualSessionTimeout(v.getActualSessionTimeout());
            setMaxResponseMessageSize(v.getMaxResponseMessageSize());
            setClientConnectionTime(v.getClientConnectionTime());
            setClientLastContactTime(v.getClientLastContactTime());
            setCurrentSubscriptionsCount(v.getCurrentSubscriptionsCount());
            setCurrentMonitoredItemsCount(v.getCurrentMonitoredItemsCount());
            setCurrentPublishRequestsInQueue(v.getCurrentPublishRequestsInQueue());
            setTotalRequestCount(v.getTotalRequestCount());
            setUnauthorizedRequestCount(v.getUnauthorizedRequestCount());
            setReadCount(v.getReadCount());
            setHistoryReadCount(v.getHistoryReadCount());
            setWriteCount(v.getWriteCount());
            setHistoryUpdateCount(v.getHistoryUpdateCount());
            setCallCount(v.getCallCount());
            setCreateMonitoredItemsCount(v.getCreateMonitoredItemsCount());
            setModifyMonitoredItemsCount(v.getModifyMonitoredItemsCount());
            setSetMonitoringModeCount(v.getSetMonitoringModeCount());
            setSetTriggeringCount(v.getSetTriggeringCount());
            setDeleteMonitoredItemsCount(v.getDeleteMonitoredItemsCount());
            setCreateSubscriptionCount(v.getCreateSubscriptionCount());
            setModifySubscriptionCount(v.getModifySubscriptionCount());
            setSetPublishingModeCount(v.getSetPublishingModeCount());
            setPublishCount(v.getPublishCount());
            setRepublishCount(v.getRepublishCount());
            setTransferSubscriptionsCount(v.getTransferSubscriptionsCount());
            setDeleteSubscriptionsCount(v.getDeleteSubscriptionsCount());
            setAddNodesCount(v.getAddNodesCount());
            setAddReferencesCount(v.getAddReferencesCount());
            setDeleteNodesCount(v.getDeleteNodesCount());
            setDeleteReferencesCount(v.getDeleteReferencesCount());
            setBrowseCount(v.getBrowseCount());
            setBrowseNextCount(v.getBrowseNextCount());
            setTranslateBrowsePathsToNodeIdsCount(v.getTranslateBrowsePathsToNodeIdsCount());
            setQueryFirstCount(v.getQueryFirstCount());
            setQueryNextCount(v.getQueryNextCount());
            setRegisterNodesCount(v.getRegisterNodesCount());
            setUnregisterNodesCount(v.getUnregisterNodesCount());
        }
    }

    @Override
    public NodeId getSessionId() {
        Optional<VariableNode> component = getVariableComponent("SessionId");

        return component.map(node -> (NodeId) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSessionIdNode() {
        Optional<VariableNode> component = getVariableComponent("SessionId");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSessionId(NodeId value) {
        getVariableComponent("SessionId")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getSessionName() {
        Optional<VariableNode> component = getVariableComponent("SessionName");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSessionNameNode() {
        Optional<VariableNode> component = getVariableComponent("SessionName");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSessionName(String value) {
        getVariableComponent("SessionName")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ApplicationDescription getClientDescription() {
        Optional<VariableNode> component = getVariableComponent("ClientDescription");

        return component.map(node -> (ApplicationDescription) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getClientDescriptionNode() {
        Optional<VariableNode> component = getVariableComponent("ClientDescription");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setClientDescription(ApplicationDescription value) {
        getVariableComponent("ClientDescription")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getServerUri() {
        Optional<VariableNode> component = getVariableComponent("ServerUri");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getServerUriNode() {
        Optional<VariableNode> component = getVariableComponent("ServerUri");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setServerUri(String value) {
        getVariableComponent("ServerUri")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getEndpointUrl() {
        Optional<VariableNode> component = getVariableComponent("EndpointUrl");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getEndpointUrlNode() {
        Optional<VariableNode> component = getVariableComponent("EndpointUrl");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setEndpointUrl(String value) {
        getVariableComponent("EndpointUrl")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String[] getLocaleIds() {
        Optional<VariableNode> component = getVariableComponent("LocaleIds");

        return component.map(node -> (String[]) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getLocaleIdsNode() {
        Optional<VariableNode> component = getVariableComponent("LocaleIds");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setLocaleIds(String[] value) {
        getVariableComponent("LocaleIds")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public Double getActualSessionTimeout() {
        Optional<VariableNode> component = getVariableComponent("ActualSessionTimeout");

        return component.map(node -> (Double) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getActualSessionTimeoutNode() {
        Optional<VariableNode> component = getVariableComponent("ActualSessionTimeout");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setActualSessionTimeout(Double value) {
        getVariableComponent("ActualSessionTimeout")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getMaxResponseMessageSize() {
        Optional<VariableNode> component = getVariableComponent("MaxResponseMessageSize");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getMaxResponseMessageSizeNode() {
        Optional<VariableNode> component = getVariableComponent("MaxResponseMessageSize");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setMaxResponseMessageSize(UInteger value) {
        getVariableComponent("MaxResponseMessageSize")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public DateTime getClientConnectionTime() {
        Optional<VariableNode> component = getVariableComponent("ClientConnectionTime");

        return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getClientConnectionTimeNode() {
        Optional<VariableNode> component = getVariableComponent("ClientConnectionTime");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setClientConnectionTime(DateTime value) {
        getVariableComponent("ClientConnectionTime")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public DateTime getClientLastContactTime() {
        Optional<VariableNode> component = getVariableComponent("ClientLastContactTime");

        return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getClientLastContactTimeNode() {
        Optional<VariableNode> component = getVariableComponent("ClientLastContactTime");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setClientLastContactTime(DateTime value) {
        getVariableComponent("ClientLastContactTime")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCurrentSubscriptionsCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentSubscriptionsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCurrentSubscriptionsCountNode() {
        Optional<VariableNode> component = getVariableComponent("CurrentSubscriptionsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCurrentSubscriptionsCount(UInteger value) {
        getVariableComponent("CurrentSubscriptionsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCurrentMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentMonitoredItemsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCurrentMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("CurrentMonitoredItemsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCurrentMonitoredItemsCount(UInteger value) {
        getVariableComponent("CurrentMonitoredItemsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCurrentPublishRequestsInQueue() {
        Optional<VariableNode> component = getVariableComponent("CurrentPublishRequestsInQueue");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCurrentPublishRequestsInQueueNode() {
        Optional<VariableNode> component = getVariableComponent("CurrentPublishRequestsInQueue");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCurrentPublishRequestsInQueue(UInteger value) {
        getVariableComponent("CurrentPublishRequestsInQueue")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getTotalRequestCount() {
        Optional<VariableNode> component = getVariableComponent("TotalRequestCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getTotalRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("TotalRequestCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setTotalRequestCount(ServiceCounterDataType value) {
        getVariableComponent("TotalRequestCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getUnauthorizedRequestCount() {
        Optional<VariableNode> component = getVariableComponent("UnauthorizedRequestCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getUnauthorizedRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("UnauthorizedRequestCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setUnauthorizedRequestCount(UInteger value) {
        getVariableComponent("UnauthorizedRequestCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getReadCount() {
        Optional<VariableNode> component = getVariableComponent("ReadCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getReadCountNode() {
        Optional<VariableNode> component = getVariableComponent("ReadCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setReadCount(ServiceCounterDataType value) {
        getVariableComponent("ReadCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getHistoryReadCount() {
        Optional<VariableNode> component = getVariableComponent("HistoryReadCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getHistoryReadCountNode() {
        Optional<VariableNode> component = getVariableComponent("HistoryReadCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setHistoryReadCount(ServiceCounterDataType value) {
        getVariableComponent("HistoryReadCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getWriteCount() {
        Optional<VariableNode> component = getVariableComponent("WriteCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getWriteCountNode() {
        Optional<VariableNode> component = getVariableComponent("WriteCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setWriteCount(ServiceCounterDataType value) {
        getVariableComponent("WriteCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getHistoryUpdateCount() {
        Optional<VariableNode> component = getVariableComponent("HistoryUpdateCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getHistoryUpdateCountNode() {
        Optional<VariableNode> component = getVariableComponent("HistoryUpdateCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setHistoryUpdateCount(ServiceCounterDataType value) {
        getVariableComponent("HistoryUpdateCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getCallCount() {
        Optional<VariableNode> component = getVariableComponent("CallCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCallCountNode() {
        Optional<VariableNode> component = getVariableComponent("CallCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCallCount(ServiceCounterDataType value) {
        getVariableComponent("CallCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getCreateMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("CreateMonitoredItemsCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCreateMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("CreateMonitoredItemsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCreateMonitoredItemsCount(ServiceCounterDataType value) {
        getVariableComponent("CreateMonitoredItemsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getModifyMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("ModifyMonitoredItemsCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getModifyMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("ModifyMonitoredItemsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setModifyMonitoredItemsCount(ServiceCounterDataType value) {
        getVariableComponent("ModifyMonitoredItemsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getSetMonitoringModeCount() {
        Optional<VariableNode> component = getVariableComponent("SetMonitoringModeCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSetMonitoringModeCountNode() {
        Optional<VariableNode> component = getVariableComponent("SetMonitoringModeCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSetMonitoringModeCount(ServiceCounterDataType value) {
        getVariableComponent("SetMonitoringModeCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getSetTriggeringCount() {
        Optional<VariableNode> component = getVariableComponent("SetTriggeringCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSetTriggeringCountNode() {
        Optional<VariableNode> component = getVariableComponent("SetTriggeringCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSetTriggeringCount(ServiceCounterDataType value) {
        getVariableComponent("SetTriggeringCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getDeleteMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("DeleteMonitoredItemsCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDeleteMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("DeleteMonitoredItemsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDeleteMonitoredItemsCount(ServiceCounterDataType value) {
        getVariableComponent("DeleteMonitoredItemsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getCreateSubscriptionCount() {
        Optional<VariableNode> component = getVariableComponent("CreateSubscriptionCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCreateSubscriptionCountNode() {
        Optional<VariableNode> component = getVariableComponent("CreateSubscriptionCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCreateSubscriptionCount(ServiceCounterDataType value) {
        getVariableComponent("CreateSubscriptionCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getModifySubscriptionCount() {
        Optional<VariableNode> component = getVariableComponent("ModifySubscriptionCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getModifySubscriptionCountNode() {
        Optional<VariableNode> component = getVariableComponent("ModifySubscriptionCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setModifySubscriptionCount(ServiceCounterDataType value) {
        getVariableComponent("ModifySubscriptionCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getSetPublishingModeCount() {
        Optional<VariableNode> component = getVariableComponent("SetPublishingModeCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSetPublishingModeCountNode() {
        Optional<VariableNode> component = getVariableComponent("SetPublishingModeCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSetPublishingModeCount(ServiceCounterDataType value) {
        getVariableComponent("SetPublishingModeCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getPublishCount() {
        Optional<VariableNode> component = getVariableComponent("PublishCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getPublishCountNode() {
        Optional<VariableNode> component = getVariableComponent("PublishCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setPublishCount(ServiceCounterDataType value) {
        getVariableComponent("PublishCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getRepublishCount() {
        Optional<VariableNode> component = getVariableComponent("RepublishCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getRepublishCountNode() {
        Optional<VariableNode> component = getVariableComponent("RepublishCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setRepublishCount(ServiceCounterDataType value) {
        getVariableComponent("RepublishCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getTransferSubscriptionsCount() {
        Optional<VariableNode> component = getVariableComponent("TransferSubscriptionsCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getTransferSubscriptionsCountNode() {
        Optional<VariableNode> component = getVariableComponent("TransferSubscriptionsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setTransferSubscriptionsCount(ServiceCounterDataType value) {
        getVariableComponent("TransferSubscriptionsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getDeleteSubscriptionsCount() {
        Optional<VariableNode> component = getVariableComponent("DeleteSubscriptionsCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDeleteSubscriptionsCountNode() {
        Optional<VariableNode> component = getVariableComponent("DeleteSubscriptionsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDeleteSubscriptionsCount(ServiceCounterDataType value) {
        getVariableComponent("DeleteSubscriptionsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getAddNodesCount() {
        Optional<VariableNode> component = getVariableComponent("AddNodesCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getAddNodesCountNode() {
        Optional<VariableNode> component = getVariableComponent("AddNodesCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setAddNodesCount(ServiceCounterDataType value) {
        getVariableComponent("AddNodesCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getAddReferencesCount() {
        Optional<VariableNode> component = getVariableComponent("AddReferencesCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getAddReferencesCountNode() {
        Optional<VariableNode> component = getVariableComponent("AddReferencesCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setAddReferencesCount(ServiceCounterDataType value) {
        getVariableComponent("AddReferencesCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getDeleteNodesCount() {
        Optional<VariableNode> component = getVariableComponent("DeleteNodesCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDeleteNodesCountNode() {
        Optional<VariableNode> component = getVariableComponent("DeleteNodesCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDeleteNodesCount(ServiceCounterDataType value) {
        getVariableComponent("DeleteNodesCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getDeleteReferencesCount() {
        Optional<VariableNode> component = getVariableComponent("DeleteReferencesCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDeleteReferencesCountNode() {
        Optional<VariableNode> component = getVariableComponent("DeleteReferencesCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDeleteReferencesCount(ServiceCounterDataType value) {
        getVariableComponent("DeleteReferencesCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getBrowseCount() {
        Optional<VariableNode> component = getVariableComponent("BrowseCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getBrowseCountNode() {
        Optional<VariableNode> component = getVariableComponent("BrowseCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setBrowseCount(ServiceCounterDataType value) {
        getVariableComponent("BrowseCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getBrowseNextCount() {
        Optional<VariableNode> component = getVariableComponent("BrowseNextCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getBrowseNextCountNode() {
        Optional<VariableNode> component = getVariableComponent("BrowseNextCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setBrowseNextCount(ServiceCounterDataType value) {
        getVariableComponent("BrowseNextCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() {
        Optional<VariableNode> component = getVariableComponent("TranslateBrowsePathsToNodeIdsCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getTranslateBrowsePathsToNodeIdsCountNode() {
        Optional<VariableNode> component = getVariableComponent("TranslateBrowsePathsToNodeIdsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType value) {
        getVariableComponent("TranslateBrowsePathsToNodeIdsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getQueryFirstCount() {
        Optional<VariableNode> component = getVariableComponent("QueryFirstCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getQueryFirstCountNode() {
        Optional<VariableNode> component = getVariableComponent("QueryFirstCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setQueryFirstCount(ServiceCounterDataType value) {
        getVariableComponent("QueryFirstCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getQueryNextCount() {
        Optional<VariableNode> component = getVariableComponent("QueryNextCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getQueryNextCountNode() {
        Optional<VariableNode> component = getVariableComponent("QueryNextCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setQueryNextCount(ServiceCounterDataType value) {
        getVariableComponent("QueryNextCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getRegisterNodesCount() {
        Optional<VariableNode> component = getVariableComponent("RegisterNodesCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getRegisterNodesCountNode() {
        Optional<VariableNode> component = getVariableComponent("RegisterNodesCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setRegisterNodesCount(ServiceCounterDataType value) {
        getVariableComponent("RegisterNodesCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServiceCounterDataType getUnregisterNodesCount() {
        Optional<VariableNode> component = getVariableComponent("UnregisterNodesCount");

        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getUnregisterNodesCountNode() {
        Optional<VariableNode> component = getVariableComponent("UnregisterNodesCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setUnregisterNodesCount(ServiceCounterDataType value) {
        getVariableComponent("UnregisterNodesCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}

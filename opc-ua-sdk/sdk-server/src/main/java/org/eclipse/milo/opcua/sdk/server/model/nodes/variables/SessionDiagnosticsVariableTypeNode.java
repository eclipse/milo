/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
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

public class SessionDiagnosticsVariableTypeNode extends BaseDataVariableTypeNode implements SessionDiagnosticsVariableType {
    public SessionDiagnosticsVariableTypeNode(UaNodeContext context, NodeId nodeId,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public SessionDiagnosticsVariableTypeNode(UaNodeContext context, NodeId nodeId,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                              Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                              double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public BaseDataVariableTypeNode getSessionIdNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionId");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public NodeId getSessionId() {
        Optional<VariableNode> component = getVariableComponent("SessionId");
        return component.map(node -> (NodeId) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSessionId(NodeId value) {
        getVariableComponent("SessionId").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getSessionNameNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionName");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public String getSessionName() {
        Optional<VariableNode> component = getVariableComponent("SessionName");
        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSessionName(String value) {
        getVariableComponent("SessionName").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getClientDescriptionNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientDescription");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ApplicationDescription getClientDescription() {
        Optional<VariableNode> component = getVariableComponent("ClientDescription");
        return component.map(node -> (ApplicationDescription) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setClientDescription(ApplicationDescription value) {
        getVariableComponent("ClientDescription").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getServerUriNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ServerUri");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public String getServerUri() {
        Optional<VariableNode> component = getVariableComponent("ServerUri");
        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setServerUri(String value) {
        getVariableComponent("ServerUri").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getEndpointUrlNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EndpointUrl");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public String getEndpointUrl() {
        Optional<VariableNode> component = getVariableComponent("EndpointUrl");
        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setEndpointUrl(String value) {
        getVariableComponent("EndpointUrl").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getLocaleIdsNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LocaleIds");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public String[] getLocaleIds() {
        Optional<VariableNode> component = getVariableComponent("LocaleIds");
        return component.map(node -> (String[]) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setLocaleIds(String[] value) {
        getVariableComponent("LocaleIds").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getActualSessionTimeoutNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ActualSessionTimeout");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public Double getActualSessionTimeout() {
        Optional<VariableNode> component = getVariableComponent("ActualSessionTimeout");
        return component.map(node -> (Double) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setActualSessionTimeout(Double value) {
        getVariableComponent("ActualSessionTimeout").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getMaxResponseMessageSizeNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "MaxResponseMessageSize");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public UInteger getMaxResponseMessageSize() {
        Optional<VariableNode> component = getVariableComponent("MaxResponseMessageSize");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setMaxResponseMessageSize(UInteger value) {
        getVariableComponent("MaxResponseMessageSize").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getClientConnectionTimeNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientConnectionTime");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public DateTime getClientConnectionTime() {
        Optional<VariableNode> component = getVariableComponent("ClientConnectionTime");
        return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setClientConnectionTime(DateTime value) {
        getVariableComponent("ClientConnectionTime").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getClientLastContactTimeNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientLastContactTime");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public DateTime getClientLastContactTime() {
        Optional<VariableNode> component = getVariableComponent("ClientLastContactTime");
        return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setClientLastContactTime(DateTime value) {
        getVariableComponent("ClientLastContactTime").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentSubscriptionsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentSubscriptionsCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public UInteger getCurrentSubscriptionsCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentSubscriptionsCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setCurrentSubscriptionsCount(UInteger value) {
        getVariableComponent("CurrentSubscriptionsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentMonitoredItemsCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public UInteger getCurrentMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentMonitoredItemsCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setCurrentMonitoredItemsCount(UInteger value) {
        getVariableComponent("CurrentMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentPublishRequestsInQueueNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentPublishRequestsInQueue");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public UInteger getCurrentPublishRequestsInQueue() {
        Optional<VariableNode> component = getVariableComponent("CurrentPublishRequestsInQueue");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setCurrentPublishRequestsInQueue(UInteger value) {
        getVariableComponent("CurrentPublishRequestsInQueue").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getTotalRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TotalRequestCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getTotalRequestCount() {
        Optional<VariableNode> component = getVariableComponent("TotalRequestCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setTotalRequestCount(ServiceCounterDataType value) {
        getVariableComponent("TotalRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getUnauthorizedRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "UnauthorizedRequestCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public UInteger getUnauthorizedRequestCount() {
        Optional<VariableNode> component = getVariableComponent("UnauthorizedRequestCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setUnauthorizedRequestCount(UInteger value) {
        getVariableComponent("UnauthorizedRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getReadCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ReadCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getReadCount() {
        Optional<VariableNode> component = getVariableComponent("ReadCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setReadCount(ServiceCounterDataType value) {
        getVariableComponent("ReadCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getHistoryReadCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "HistoryReadCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getHistoryReadCount() {
        Optional<VariableNode> component = getVariableComponent("HistoryReadCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setHistoryReadCount(ServiceCounterDataType value) {
        getVariableComponent("HistoryReadCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getWriteCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "WriteCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getWriteCount() {
        Optional<VariableNode> component = getVariableComponent("WriteCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setWriteCount(ServiceCounterDataType value) {
        getVariableComponent("WriteCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getHistoryUpdateCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "HistoryUpdateCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getHistoryUpdateCount() {
        Optional<VariableNode> component = getVariableComponent("HistoryUpdateCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setHistoryUpdateCount(ServiceCounterDataType value) {
        getVariableComponent("HistoryUpdateCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getCallCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CallCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getCallCount() {
        Optional<VariableNode> component = getVariableComponent("CallCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setCallCount(ServiceCounterDataType value) {
        getVariableComponent("CallCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getCreateMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CreateMonitoredItemsCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getCreateMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("CreateMonitoredItemsCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setCreateMonitoredItemsCount(ServiceCounterDataType value) {
        getVariableComponent("CreateMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getModifyMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ModifyMonitoredItemsCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getModifyMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("ModifyMonitoredItemsCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setModifyMonitoredItemsCount(ServiceCounterDataType value) {
        getVariableComponent("ModifyMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getSetMonitoringModeCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SetMonitoringModeCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getSetMonitoringModeCount() {
        Optional<VariableNode> component = getVariableComponent("SetMonitoringModeCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSetMonitoringModeCount(ServiceCounterDataType value) {
        getVariableComponent("SetMonitoringModeCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getSetTriggeringCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SetTriggeringCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getSetTriggeringCount() {
        Optional<VariableNode> component = getVariableComponent("SetTriggeringCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSetTriggeringCount(ServiceCounterDataType value) {
        getVariableComponent("SetTriggeringCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getDeleteMonitoredItemsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DeleteMonitoredItemsCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getDeleteMonitoredItemsCount() {
        Optional<VariableNode> component = getVariableComponent("DeleteMonitoredItemsCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setDeleteMonitoredItemsCount(ServiceCounterDataType value) {
        getVariableComponent("DeleteMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getCreateSubscriptionCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CreateSubscriptionCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getCreateSubscriptionCount() {
        Optional<VariableNode> component = getVariableComponent("CreateSubscriptionCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setCreateSubscriptionCount(ServiceCounterDataType value) {
        getVariableComponent("CreateSubscriptionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getModifySubscriptionCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ModifySubscriptionCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getModifySubscriptionCount() {
        Optional<VariableNode> component = getVariableComponent("ModifySubscriptionCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setModifySubscriptionCount(ServiceCounterDataType value) {
        getVariableComponent("ModifySubscriptionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getSetPublishingModeCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SetPublishingModeCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getSetPublishingModeCount() {
        Optional<VariableNode> component = getVariableComponent("SetPublishingModeCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSetPublishingModeCount(ServiceCounterDataType value) {
        getVariableComponent("SetPublishingModeCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getPublishCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "PublishCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getPublishCount() {
        Optional<VariableNode> component = getVariableComponent("PublishCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setPublishCount(ServiceCounterDataType value) {
        getVariableComponent("PublishCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getRepublishCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RepublishCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getRepublishCount() {
        Optional<VariableNode> component = getVariableComponent("RepublishCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setRepublishCount(ServiceCounterDataType value) {
        getVariableComponent("RepublishCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getTransferSubscriptionsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TransferSubscriptionsCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getTransferSubscriptionsCount() {
        Optional<VariableNode> component = getVariableComponent("TransferSubscriptionsCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setTransferSubscriptionsCount(ServiceCounterDataType value) {
        getVariableComponent("TransferSubscriptionsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getDeleteSubscriptionsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DeleteSubscriptionsCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getDeleteSubscriptionsCount() {
        Optional<VariableNode> component = getVariableComponent("DeleteSubscriptionsCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setDeleteSubscriptionsCount(ServiceCounterDataType value) {
        getVariableComponent("DeleteSubscriptionsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getAddNodesCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "AddNodesCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getAddNodesCount() {
        Optional<VariableNode> component = getVariableComponent("AddNodesCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setAddNodesCount(ServiceCounterDataType value) {
        getVariableComponent("AddNodesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getAddReferencesCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "AddReferencesCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getAddReferencesCount() {
        Optional<VariableNode> component = getVariableComponent("AddReferencesCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setAddReferencesCount(ServiceCounterDataType value) {
        getVariableComponent("AddReferencesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getDeleteNodesCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DeleteNodesCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getDeleteNodesCount() {
        Optional<VariableNode> component = getVariableComponent("DeleteNodesCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setDeleteNodesCount(ServiceCounterDataType value) {
        getVariableComponent("DeleteNodesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getDeleteReferencesCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DeleteReferencesCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getDeleteReferencesCount() {
        Optional<VariableNode> component = getVariableComponent("DeleteReferencesCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setDeleteReferencesCount(ServiceCounterDataType value) {
        getVariableComponent("DeleteReferencesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getBrowseCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "BrowseCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getBrowseCount() {
        Optional<VariableNode> component = getVariableComponent("BrowseCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setBrowseCount(ServiceCounterDataType value) {
        getVariableComponent("BrowseCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getBrowseNextCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "BrowseNextCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getBrowseNextCount() {
        Optional<VariableNode> component = getVariableComponent("BrowseNextCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setBrowseNextCount(ServiceCounterDataType value) {
        getVariableComponent("BrowseNextCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getTranslateBrowsePathsToNodeIdsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TranslateBrowsePathsToNodeIdsCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() {
        Optional<VariableNode> component = getVariableComponent("TranslateBrowsePathsToNodeIdsCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType value) {
        getVariableComponent("TranslateBrowsePathsToNodeIdsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getQueryFirstCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "QueryFirstCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getQueryFirstCount() {
        Optional<VariableNode> component = getVariableComponent("QueryFirstCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setQueryFirstCount(ServiceCounterDataType value) {
        getVariableComponent("QueryFirstCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getQueryNextCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "QueryNextCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getQueryNextCount() {
        Optional<VariableNode> component = getVariableComponent("QueryNextCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setQueryNextCount(ServiceCounterDataType value) {
        getVariableComponent("QueryNextCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getRegisterNodesCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RegisterNodesCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getRegisterNodesCount() {
        Optional<VariableNode> component = getVariableComponent("RegisterNodesCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setRegisterNodesCount(ServiceCounterDataType value) {
        getVariableComponent("RegisterNodesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getUnregisterNodesCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "UnregisterNodesCount");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServiceCounterDataType getUnregisterNodesCount() {
        Optional<VariableNode> component = getVariableComponent("UnregisterNodesCount");
        return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setUnregisterNodesCount(ServiceCounterDataType value) {
        getVariableComponent("UnregisterNodesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}

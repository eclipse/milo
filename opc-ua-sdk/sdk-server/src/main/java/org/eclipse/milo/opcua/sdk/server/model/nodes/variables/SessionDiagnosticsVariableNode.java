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

public class SessionDiagnosticsVariableNode extends BaseDataVariableNode implements SessionDiagnosticsVariableType {
  public SessionDiagnosticsVariableNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public SessionDiagnosticsVariableNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                        Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                        double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
  }

  public BaseDataVariableNode getSessionIdNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionId");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public NodeId getSessionId() {
    Optional<VariableNode> component = getVariableComponent("SessionId");
    return component.map(node -> (NodeId) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSessionId(NodeId value) {
    getVariableComponent("SessionId").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSessionNameNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionName");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String getSessionName() {
    Optional<VariableNode> component = getVariableComponent("SessionName");
    return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSessionName(String value) {
    getVariableComponent("SessionName").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getClientDescriptionNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientDescription");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ApplicationDescription getClientDescription() {
    Optional<VariableNode> component = getVariableComponent("ClientDescription");
    return component.map(node -> (ApplicationDescription) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setClientDescription(ApplicationDescription value) {
    getVariableComponent("ClientDescription").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getServerUriNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ServerUri");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String getServerUri() {
    Optional<VariableNode> component = getVariableComponent("ServerUri");
    return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setServerUri(String value) {
    getVariableComponent("ServerUri").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getEndpointUrlNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EndpointUrl");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String getEndpointUrl() {
    Optional<VariableNode> component = getVariableComponent("EndpointUrl");
    return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setEndpointUrl(String value) {
    getVariableComponent("EndpointUrl").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getLocaleIdsNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LocaleIds");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String[] getLocaleIds() {
    Optional<VariableNode> component = getVariableComponent("LocaleIds");
    return component.map(node -> (String[]) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setLocaleIds(String[] value) {
    getVariableComponent("LocaleIds").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getActualSessionTimeoutNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ActualSessionTimeout");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public Double getActualSessionTimeout() {
    Optional<VariableNode> component = getVariableComponent("ActualSessionTimeout");
    return component.map(node -> (Double) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setActualSessionTimeout(Double value) {
    getVariableComponent("ActualSessionTimeout").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getMaxResponseMessageSizeNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "MaxResponseMessageSize");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getMaxResponseMessageSize() {
    Optional<VariableNode> component = getVariableComponent("MaxResponseMessageSize");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setMaxResponseMessageSize(UInteger value) {
    getVariableComponent("MaxResponseMessageSize").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getClientConnectionTimeNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientConnectionTime");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public DateTime getClientConnectionTime() {
    Optional<VariableNode> component = getVariableComponent("ClientConnectionTime");
    return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setClientConnectionTime(DateTime value) {
    getVariableComponent("ClientConnectionTime").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getClientLastContactTimeNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientLastContactTime");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public DateTime getClientLastContactTime() {
    Optional<VariableNode> component = getVariableComponent("ClientLastContactTime");
    return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setClientLastContactTime(DateTime value) {
    getVariableComponent("ClientLastContactTime").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCurrentSubscriptionsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentSubscriptionsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getCurrentSubscriptionsCount() {
    Optional<VariableNode> component = getVariableComponent("CurrentSubscriptionsCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCurrentSubscriptionsCount(UInteger value) {
    getVariableComponent("CurrentSubscriptionsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCurrentMonitoredItemsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentMonitoredItemsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getCurrentMonitoredItemsCount() {
    Optional<VariableNode> component = getVariableComponent("CurrentMonitoredItemsCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCurrentMonitoredItemsCount(UInteger value) {
    getVariableComponent("CurrentMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCurrentPublishRequestsInQueueNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentPublishRequestsInQueue");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getCurrentPublishRequestsInQueue() {
    Optional<VariableNode> component = getVariableComponent("CurrentPublishRequestsInQueue");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCurrentPublishRequestsInQueue(UInteger value) {
    getVariableComponent("CurrentPublishRequestsInQueue").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getTotalRequestCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TotalRequestCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getTotalRequestCount() {
    Optional<VariableNode> component = getVariableComponent("TotalRequestCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setTotalRequestCount(ServiceCounterDataType value) {
    getVariableComponent("TotalRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getUnauthorizedRequestCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "UnauthorizedRequestCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getUnauthorizedRequestCount() {
    Optional<VariableNode> component = getVariableComponent("UnauthorizedRequestCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setUnauthorizedRequestCount(UInteger value) {
    getVariableComponent("UnauthorizedRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getReadCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ReadCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getReadCount() {
    Optional<VariableNode> component = getVariableComponent("ReadCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setReadCount(ServiceCounterDataType value) {
    getVariableComponent("ReadCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getHistoryReadCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "HistoryReadCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getHistoryReadCount() {
    Optional<VariableNode> component = getVariableComponent("HistoryReadCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setHistoryReadCount(ServiceCounterDataType value) {
    getVariableComponent("HistoryReadCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getWriteCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "WriteCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getWriteCount() {
    Optional<VariableNode> component = getVariableComponent("WriteCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setWriteCount(ServiceCounterDataType value) {
    getVariableComponent("WriteCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getHistoryUpdateCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "HistoryUpdateCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getHistoryUpdateCount() {
    Optional<VariableNode> component = getVariableComponent("HistoryUpdateCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setHistoryUpdateCount(ServiceCounterDataType value) {
    getVariableComponent("HistoryUpdateCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCallCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CallCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getCallCount() {
    Optional<VariableNode> component = getVariableComponent("CallCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCallCount(ServiceCounterDataType value) {
    getVariableComponent("CallCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCreateMonitoredItemsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CreateMonitoredItemsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getCreateMonitoredItemsCount() {
    Optional<VariableNode> component = getVariableComponent("CreateMonitoredItemsCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCreateMonitoredItemsCount(ServiceCounterDataType value) {
    getVariableComponent("CreateMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getModifyMonitoredItemsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ModifyMonitoredItemsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getModifyMonitoredItemsCount() {
    Optional<VariableNode> component = getVariableComponent("ModifyMonitoredItemsCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setModifyMonitoredItemsCount(ServiceCounterDataType value) {
    getVariableComponent("ModifyMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSetMonitoringModeCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SetMonitoringModeCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getSetMonitoringModeCount() {
    Optional<VariableNode> component = getVariableComponent("SetMonitoringModeCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSetMonitoringModeCount(ServiceCounterDataType value) {
    getVariableComponent("SetMonitoringModeCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSetTriggeringCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SetTriggeringCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getSetTriggeringCount() {
    Optional<VariableNode> component = getVariableComponent("SetTriggeringCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSetTriggeringCount(ServiceCounterDataType value) {
    getVariableComponent("SetTriggeringCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getDeleteMonitoredItemsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DeleteMonitoredItemsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getDeleteMonitoredItemsCount() {
    Optional<VariableNode> component = getVariableComponent("DeleteMonitoredItemsCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setDeleteMonitoredItemsCount(ServiceCounterDataType value) {
    getVariableComponent("DeleteMonitoredItemsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCreateSubscriptionCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CreateSubscriptionCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getCreateSubscriptionCount() {
    Optional<VariableNode> component = getVariableComponent("CreateSubscriptionCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCreateSubscriptionCount(ServiceCounterDataType value) {
    getVariableComponent("CreateSubscriptionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getModifySubscriptionCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ModifySubscriptionCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getModifySubscriptionCount() {
    Optional<VariableNode> component = getVariableComponent("ModifySubscriptionCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setModifySubscriptionCount(ServiceCounterDataType value) {
    getVariableComponent("ModifySubscriptionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSetPublishingModeCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SetPublishingModeCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getSetPublishingModeCount() {
    Optional<VariableNode> component = getVariableComponent("SetPublishingModeCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSetPublishingModeCount(ServiceCounterDataType value) {
    getVariableComponent("SetPublishingModeCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getPublishCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "PublishCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getPublishCount() {
    Optional<VariableNode> component = getVariableComponent("PublishCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setPublishCount(ServiceCounterDataType value) {
    getVariableComponent("PublishCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getRepublishCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RepublishCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getRepublishCount() {
    Optional<VariableNode> component = getVariableComponent("RepublishCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setRepublishCount(ServiceCounterDataType value) {
    getVariableComponent("RepublishCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getTransferSubscriptionsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TransferSubscriptionsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getTransferSubscriptionsCount() {
    Optional<VariableNode> component = getVariableComponent("TransferSubscriptionsCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setTransferSubscriptionsCount(ServiceCounterDataType value) {
    getVariableComponent("TransferSubscriptionsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getDeleteSubscriptionsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DeleteSubscriptionsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getDeleteSubscriptionsCount() {
    Optional<VariableNode> component = getVariableComponent("DeleteSubscriptionsCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setDeleteSubscriptionsCount(ServiceCounterDataType value) {
    getVariableComponent("DeleteSubscriptionsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getAddNodesCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "AddNodesCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getAddNodesCount() {
    Optional<VariableNode> component = getVariableComponent("AddNodesCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setAddNodesCount(ServiceCounterDataType value) {
    getVariableComponent("AddNodesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getAddReferencesCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "AddReferencesCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getAddReferencesCount() {
    Optional<VariableNode> component = getVariableComponent("AddReferencesCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setAddReferencesCount(ServiceCounterDataType value) {
    getVariableComponent("AddReferencesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getDeleteNodesCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DeleteNodesCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getDeleteNodesCount() {
    Optional<VariableNode> component = getVariableComponent("DeleteNodesCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setDeleteNodesCount(ServiceCounterDataType value) {
    getVariableComponent("DeleteNodesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getDeleteReferencesCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DeleteReferencesCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getDeleteReferencesCount() {
    Optional<VariableNode> component = getVariableComponent("DeleteReferencesCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setDeleteReferencesCount(ServiceCounterDataType value) {
    getVariableComponent("DeleteReferencesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getBrowseCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "BrowseCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getBrowseCount() {
    Optional<VariableNode> component = getVariableComponent("BrowseCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setBrowseCount(ServiceCounterDataType value) {
    getVariableComponent("BrowseCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getBrowseNextCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "BrowseNextCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getBrowseNextCount() {
    Optional<VariableNode> component = getVariableComponent("BrowseNextCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setBrowseNextCount(ServiceCounterDataType value) {
    getVariableComponent("BrowseNextCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getTranslateBrowsePathsToNodeIdsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TranslateBrowsePathsToNodeIdsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() {
    Optional<VariableNode> component = getVariableComponent("TranslateBrowsePathsToNodeIdsCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType value) {
    getVariableComponent("TranslateBrowsePathsToNodeIdsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getQueryFirstCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "QueryFirstCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getQueryFirstCount() {
    Optional<VariableNode> component = getVariableComponent("QueryFirstCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setQueryFirstCount(ServiceCounterDataType value) {
    getVariableComponent("QueryFirstCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getQueryNextCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "QueryNextCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getQueryNextCount() {
    Optional<VariableNode> component = getVariableComponent("QueryNextCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setQueryNextCount(ServiceCounterDataType value) {
    getVariableComponent("QueryNextCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getRegisterNodesCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RegisterNodesCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getRegisterNodesCount() {
    Optional<VariableNode> component = getVariableComponent("RegisterNodesCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setRegisterNodesCount(ServiceCounterDataType value) {
    getVariableComponent("RegisterNodesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getUnregisterNodesCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "UnregisterNodesCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ServiceCounterDataType getUnregisterNodesCount() {
    Optional<VariableNode> component = getVariableComponent("UnregisterNodesCount");
    return component.map(node -> (ServiceCounterDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setUnregisterNodesCount(ServiceCounterDataType value) {
    getVariableComponent("UnregisterNodesCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }
}

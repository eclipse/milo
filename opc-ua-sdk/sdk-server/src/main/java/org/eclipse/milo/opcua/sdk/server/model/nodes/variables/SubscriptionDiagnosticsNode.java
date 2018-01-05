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

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SubscriptionDiagnosticsType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class SubscriptionDiagnosticsNode extends BaseDataVariableNode implements SubscriptionDiagnosticsType {
    public SubscriptionDiagnosticsNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                       UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public SubscriptionDiagnosticsNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                       UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                                       UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                       double minimumSamplingInterval, boolean historizing) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    public BaseDataVariableNode getSessionIdNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionId");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public NodeId getSessionId() {
        Optional<VariableNode> component = getVariableComponent("SessionId");
        return component.map(node -> (NodeId) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSessionId(NodeId value) {
        getVariableComponent("SessionId").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getSubscriptionIdNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SubscriptionId");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getSubscriptionId() {
        Optional<VariableNode> component = getVariableComponent("SubscriptionId");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSubscriptionId(UInteger value) {
        getVariableComponent("SubscriptionId").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getPriorityNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "Priority");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UByte getPriority() {
        Optional<VariableNode> component = getVariableComponent("Priority");
        return component.map(node -> (UByte) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setPriority(UByte value) {
        getVariableComponent("Priority").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getPublishingIntervalNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "PublishingInterval");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public Double getPublishingInterval() {
        Optional<VariableNode> component = getVariableComponent("PublishingInterval");
        return component.map(node -> (Double) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setPublishingInterval(Double value) {
        getVariableComponent("PublishingInterval").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getMaxKeepAliveCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "MaxKeepAliveCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getMaxKeepAliveCount() {
        Optional<VariableNode> component = getVariableComponent("MaxKeepAliveCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setMaxKeepAliveCount(UInteger value) {
        getVariableComponent("MaxKeepAliveCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getMaxLifetimeCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "MaxLifetimeCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getMaxLifetimeCount() {
        Optional<VariableNode> component = getVariableComponent("MaxLifetimeCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setMaxLifetimeCount(UInteger value) {
        getVariableComponent("MaxLifetimeCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getMaxNotificationsPerPublishNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "MaxNotificationsPerPublish");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getMaxNotificationsPerPublish() {
        Optional<VariableNode> component = getVariableComponent("MaxNotificationsPerPublish");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setMaxNotificationsPerPublish(UInteger value) {
        getVariableComponent("MaxNotificationsPerPublish").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getPublishingEnabledNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "PublishingEnabled");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public Boolean getPublishingEnabled() {
        Optional<VariableNode> component = getVariableComponent("PublishingEnabled");
        return component.map(node -> (Boolean) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setPublishingEnabled(Boolean value) {
        getVariableComponent("PublishingEnabled").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getModifyCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ModifyCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getModifyCount() {
        Optional<VariableNode> component = getVariableComponent("ModifyCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setModifyCount(UInteger value) {
        getVariableComponent("ModifyCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getEnableCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EnableCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getEnableCount() {
        Optional<VariableNode> component = getVariableComponent("EnableCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setEnableCount(UInteger value) {
        getVariableComponent("EnableCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getDisableCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DisableCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getDisableCount() {
        Optional<VariableNode> component = getVariableComponent("DisableCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setDisableCount(UInteger value) {
        getVariableComponent("DisableCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getRepublishRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RepublishRequestCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getRepublishRequestCount() {
        Optional<VariableNode> component = getVariableComponent("RepublishRequestCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setRepublishRequestCount(UInteger value) {
        getVariableComponent("RepublishRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getRepublishMessageRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RepublishMessageRequestCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getRepublishMessageRequestCount() {
        Optional<VariableNode> component = getVariableComponent("RepublishMessageRequestCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setRepublishMessageRequestCount(UInteger value) {
        getVariableComponent("RepublishMessageRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getRepublishMessageCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RepublishMessageCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getRepublishMessageCount() {
        Optional<VariableNode> component = getVariableComponent("RepublishMessageCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setRepublishMessageCount(UInteger value) {
        getVariableComponent("RepublishMessageCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getTransferRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TransferRequestCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getTransferRequestCount() {
        Optional<VariableNode> component = getVariableComponent("TransferRequestCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setTransferRequestCount(UInteger value) {
        getVariableComponent("TransferRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getTransferredToAltClientCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TransferredToAltClientCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getTransferredToAltClientCount() {
        Optional<VariableNode> component = getVariableComponent("TransferredToAltClientCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setTransferredToAltClientCount(UInteger value) {
        getVariableComponent("TransferredToAltClientCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getTransferredToSameClientCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TransferredToSameClientCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getTransferredToSameClientCount() {
        Optional<VariableNode> component = getVariableComponent("TransferredToSameClientCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setTransferredToSameClientCount(UInteger value) {
        getVariableComponent("TransferredToSameClientCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getPublishRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "PublishRequestCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getPublishRequestCount() {
        Optional<VariableNode> component = getVariableComponent("PublishRequestCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setPublishRequestCount(UInteger value) {
        getVariableComponent("PublishRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getDataChangeNotificationsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DataChangeNotificationsCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getDataChangeNotificationsCount() {
        Optional<VariableNode> component = getVariableComponent("DataChangeNotificationsCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setDataChangeNotificationsCount(UInteger value) {
        getVariableComponent("DataChangeNotificationsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getEventNotificationsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EventNotificationsCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getEventNotificationsCount() {
        Optional<VariableNode> component = getVariableComponent("EventNotificationsCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setEventNotificationsCount(UInteger value) {
        getVariableComponent("EventNotificationsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getNotificationsCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "NotificationsCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getNotificationsCount() {
        Optional<VariableNode> component = getVariableComponent("NotificationsCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setNotificationsCount(UInteger value) {
        getVariableComponent("NotificationsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getLatePublishRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LatePublishRequestCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getLatePublishRequestCount() {
        Optional<VariableNode> component = getVariableComponent("LatePublishRequestCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setLatePublishRequestCount(UInteger value) {
        getVariableComponent("LatePublishRequestCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getCurrentKeepAliveCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentKeepAliveCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getCurrentKeepAliveCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentKeepAliveCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setCurrentKeepAliveCount(UInteger value) {
        getVariableComponent("CurrentKeepAliveCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getCurrentLifetimeCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentLifetimeCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getCurrentLifetimeCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentLifetimeCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setCurrentLifetimeCount(UInteger value) {
        getVariableComponent("CurrentLifetimeCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getUnacknowledgedMessageCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "UnacknowledgedMessageCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getUnacknowledgedMessageCount() {
        Optional<VariableNode> component = getVariableComponent("UnacknowledgedMessageCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setUnacknowledgedMessageCount(UInteger value) {
        getVariableComponent("UnacknowledgedMessageCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getDiscardedMessageCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DiscardedMessageCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getDiscardedMessageCount() {
        Optional<VariableNode> component = getVariableComponent("DiscardedMessageCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setDiscardedMessageCount(UInteger value) {
        getVariableComponent("DiscardedMessageCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getMonitoredItemCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "MonitoredItemCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getMonitoredItemCount() {
        Optional<VariableNode> component = getVariableComponent("MonitoredItemCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setMonitoredItemCount(UInteger value) {
        getVariableComponent("MonitoredItemCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getDisabledMonitoredItemCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DisabledMonitoredItemCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getDisabledMonitoredItemCount() {
        Optional<VariableNode> component = getVariableComponent("DisabledMonitoredItemCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setDisabledMonitoredItemCount(UInteger value) {
        getVariableComponent("DisabledMonitoredItemCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getMonitoringQueueOverflowCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "MonitoringQueueOverflowCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getMonitoringQueueOverflowCount() {
        Optional<VariableNode> component = getVariableComponent("MonitoringQueueOverflowCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setMonitoringQueueOverflowCount(UInteger value) {
        getVariableComponent("MonitoringQueueOverflowCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getNextSequenceNumberNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "NextSequenceNumber");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getNextSequenceNumber() {
        Optional<VariableNode> component = getVariableComponent("NextSequenceNumber");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setNextSequenceNumber(UInteger value) {
        getVariableComponent("NextSequenceNumber").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getEventQueueOverFlowCountNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EventQueueOverFlowCount");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public UInteger getEventQueueOverFlowCount() {
        Optional<VariableNode> component = getVariableComponent("EventQueueOverFlowCount");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setEventQueueOverFlowCount(UInteger value) {
        getVariableComponent("EventQueueOverFlowCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}

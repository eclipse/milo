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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SubscriptionDiagnosticsType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:SubscriptionDiagnosticsType")
public class SubscriptionDiagnosticsNode extends BaseDataVariableNode implements SubscriptionDiagnosticsType {

    public SubscriptionDiagnosticsNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeManager, nodeId, variableTypeNode);
    }

    public SubscriptionDiagnosticsNode(
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
        SubscriptionDiagnosticsDataType value = new SubscriptionDiagnosticsDataType(
            getSessionId(),
            getSubscriptionId(),
            getPriority(),
            getPublishingInterval(),
            getMaxKeepAliveCount(),
            getMaxLifetimeCount(),
            getMaxNotificationsPerPublish(),
            getPublishingEnabled(),
            getModifyCount(),
            getEnableCount(),
            getDisableCount(),
            getRepublishRequestCount(),
            getRepublishMessageRequestCount(),
            getRepublishMessageCount(),
            getTransferRequestCount(),
            getTransferredToAltClientCount(),
            getTransferredToSameClientCount(),
            getPublishRequestCount(),
            getDataChangeNotificationsCount(),
            getEventNotificationsCount(),
            getNotificationsCount(),
            getLatePublishRequestCount(),
            getCurrentKeepAliveCount(),
            getCurrentLifetimeCount(),
            getUnacknowledgedMessageCount(),
            getDiscardedMessageCount(),
            getMonitoredItemCount(),
            getDisabledMonitoredItemCount(),
            getMonitoringQueueOverflowCount(),
            getNextSequenceNumber(),
            getEventQueueOverFlowCount()
        );

        return new DataValue(new Variant(value));
    }

    @Override
    public synchronized void setValue(DataValue value) {
        super.setValue(value);

        Object o = value.getValue().getValue();

        if (o instanceof SubscriptionDiagnosticsDataType) {
            SubscriptionDiagnosticsDataType v = (SubscriptionDiagnosticsDataType) o;

            setSessionId(v.getSessionId());
            setSubscriptionId(v.getSubscriptionId());
            setPriority(v.getPriority());
            setPublishingInterval(v.getPublishingInterval());
            setMaxKeepAliveCount(v.getMaxKeepAliveCount());
            setMaxLifetimeCount(v.getMaxLifetimeCount());
            setMaxNotificationsPerPublish(v.getMaxNotificationsPerPublish());
            setPublishingEnabled(v.getPublishingEnabled());
            setModifyCount(v.getModifyCount());
            setEnableCount(v.getEnableCount());
            setDisableCount(v.getDisableCount());
            setRepublishRequestCount(v.getRepublishRequestCount());
            setRepublishMessageRequestCount(v.getRepublishMessageRequestCount());
            setRepublishMessageCount(v.getRepublishMessageCount());
            setTransferRequestCount(v.getTransferRequestCount());
            setTransferredToAltClientCount(v.getTransferredToAltClientCount());
            setTransferredToSameClientCount(v.getTransferredToSameClientCount());
            setPublishRequestCount(v.getPublishRequestCount());
            setDataChangeNotificationsCount(v.getDataChangeNotificationsCount());
            setEventNotificationsCount(v.getEventNotificationsCount());
            setNotificationsCount(v.getNotificationsCount());
            setLatePublishRequestCount(v.getLatePublishRequestCount());
            setCurrentKeepAliveCount(v.getCurrentKeepAliveCount());
            setCurrentLifetimeCount(v.getCurrentLifetimeCount());
            setUnacknowledgedMessageCount(v.getUnacknowledgedMessageCount());
            setDiscardedMessageCount(v.getDiscardedMessageCount());
            setMonitoredItemCount(v.getMonitoredItemCount());
            setDisabledMonitoredItemCount(v.getDisabledMonitoredItemCount());
            setMonitoringQueueOverflowCount(v.getMonitoringQueueOverflowCount());
            setNextSequenceNumber(v.getNextSequenceNumber());
            setEventQueueOverFlowCount(v.getEventQueueOverFlowCount());
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
    public UInteger getSubscriptionId() {
        Optional<VariableNode> component = getVariableComponent("SubscriptionId");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSubscriptionIdNode() {
        Optional<VariableNode> component = getVariableComponent("SubscriptionId");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSubscriptionId(UInteger value) {
        getVariableComponent("SubscriptionId")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UByte getPriority() {
        Optional<VariableNode> component = getVariableComponent("Priority");

        return component.map(node -> (UByte) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getPriorityNode() {
        Optional<VariableNode> component = getVariableComponent("Priority");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setPriority(UByte value) {
        getVariableComponent("Priority")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public Double getPublishingInterval() {
        Optional<VariableNode> component = getVariableComponent("PublishingInterval");

        return component.map(node -> (Double) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getPublishingIntervalNode() {
        Optional<VariableNode> component = getVariableComponent("PublishingInterval");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setPublishingInterval(Double value) {
        getVariableComponent("PublishingInterval")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getMaxKeepAliveCount() {
        Optional<VariableNode> component = getVariableComponent("MaxKeepAliveCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getMaxKeepAliveCountNode() {
        Optional<VariableNode> component = getVariableComponent("MaxKeepAliveCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setMaxKeepAliveCount(UInteger value) {
        getVariableComponent("MaxKeepAliveCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getMaxLifetimeCount() {
        Optional<VariableNode> component = getVariableComponent("MaxLifetimeCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getMaxLifetimeCountNode() {
        Optional<VariableNode> component = getVariableComponent("MaxLifetimeCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setMaxLifetimeCount(UInteger value) {
        getVariableComponent("MaxLifetimeCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getMaxNotificationsPerPublish() {
        Optional<VariableNode> component = getVariableComponent("MaxNotificationsPerPublish");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getMaxNotificationsPerPublishNode() {
        Optional<VariableNode> component = getVariableComponent("MaxNotificationsPerPublish");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setMaxNotificationsPerPublish(UInteger value) {
        getVariableComponent("MaxNotificationsPerPublish")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public Boolean getPublishingEnabled() {
        Optional<VariableNode> component = getVariableComponent("PublishingEnabled");

        return component.map(node -> (Boolean) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getPublishingEnabledNode() {
        Optional<VariableNode> component = getVariableComponent("PublishingEnabled");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setPublishingEnabled(Boolean value) {
        getVariableComponent("PublishingEnabled")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getModifyCount() {
        Optional<VariableNode> component = getVariableComponent("ModifyCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getModifyCountNode() {
        Optional<VariableNode> component = getVariableComponent("ModifyCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setModifyCount(UInteger value) {
        getVariableComponent("ModifyCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getEnableCount() {
        Optional<VariableNode> component = getVariableComponent("EnableCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getEnableCountNode() {
        Optional<VariableNode> component = getVariableComponent("EnableCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setEnableCount(UInteger value) {
        getVariableComponent("EnableCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getDisableCount() {
        Optional<VariableNode> component = getVariableComponent("DisableCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDisableCountNode() {
        Optional<VariableNode> component = getVariableComponent("DisableCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDisableCount(UInteger value) {
        getVariableComponent("DisableCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getRepublishRequestCount() {
        Optional<VariableNode> component = getVariableComponent("RepublishRequestCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getRepublishRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("RepublishRequestCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setRepublishRequestCount(UInteger value) {
        getVariableComponent("RepublishRequestCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getRepublishMessageRequestCount() {
        Optional<VariableNode> component = getVariableComponent("RepublishMessageRequestCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getRepublishMessageRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("RepublishMessageRequestCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setRepublishMessageRequestCount(UInteger value) {
        getVariableComponent("RepublishMessageRequestCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getRepublishMessageCount() {
        Optional<VariableNode> component = getVariableComponent("RepublishMessageCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getRepublishMessageCountNode() {
        Optional<VariableNode> component = getVariableComponent("RepublishMessageCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setRepublishMessageCount(UInteger value) {
        getVariableComponent("RepublishMessageCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getTransferRequestCount() {
        Optional<VariableNode> component = getVariableComponent("TransferRequestCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getTransferRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("TransferRequestCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setTransferRequestCount(UInteger value) {
        getVariableComponent("TransferRequestCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getTransferredToAltClientCount() {
        Optional<VariableNode> component = getVariableComponent("TransferredToAltClientCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getTransferredToAltClientCountNode() {
        Optional<VariableNode> component = getVariableComponent("TransferredToAltClientCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setTransferredToAltClientCount(UInteger value) {
        getVariableComponent("TransferredToAltClientCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getTransferredToSameClientCount() {
        Optional<VariableNode> component = getVariableComponent("TransferredToSameClientCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getTransferredToSameClientCountNode() {
        Optional<VariableNode> component = getVariableComponent("TransferredToSameClientCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setTransferredToSameClientCount(UInteger value) {
        getVariableComponent("TransferredToSameClientCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getPublishRequestCount() {
        Optional<VariableNode> component = getVariableComponent("PublishRequestCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getPublishRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("PublishRequestCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setPublishRequestCount(UInteger value) {
        getVariableComponent("PublishRequestCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getDataChangeNotificationsCount() {
        Optional<VariableNode> component = getVariableComponent("DataChangeNotificationsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDataChangeNotificationsCountNode() {
        Optional<VariableNode> component = getVariableComponent("DataChangeNotificationsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDataChangeNotificationsCount(UInteger value) {
        getVariableComponent("DataChangeNotificationsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getEventNotificationsCount() {
        Optional<VariableNode> component = getVariableComponent("EventNotificationsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getEventNotificationsCountNode() {
        Optional<VariableNode> component = getVariableComponent("EventNotificationsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setEventNotificationsCount(UInteger value) {
        getVariableComponent("EventNotificationsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getNotificationsCount() {
        Optional<VariableNode> component = getVariableComponent("NotificationsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getNotificationsCountNode() {
        Optional<VariableNode> component = getVariableComponent("NotificationsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setNotificationsCount(UInteger value) {
        getVariableComponent("NotificationsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getLatePublishRequestCount() {
        Optional<VariableNode> component = getVariableComponent("LatePublishRequestCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getLatePublishRequestCountNode() {
        Optional<VariableNode> component = getVariableComponent("LatePublishRequestCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setLatePublishRequestCount(UInteger value) {
        getVariableComponent("LatePublishRequestCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCurrentKeepAliveCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentKeepAliveCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCurrentKeepAliveCountNode() {
        Optional<VariableNode> component = getVariableComponent("CurrentKeepAliveCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCurrentKeepAliveCount(UInteger value) {
        getVariableComponent("CurrentKeepAliveCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCurrentLifetimeCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentLifetimeCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCurrentLifetimeCountNode() {
        Optional<VariableNode> component = getVariableComponent("CurrentLifetimeCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCurrentLifetimeCount(UInteger value) {
        getVariableComponent("CurrentLifetimeCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getUnacknowledgedMessageCount() {
        Optional<VariableNode> component = getVariableComponent("UnacknowledgedMessageCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getUnacknowledgedMessageCountNode() {
        Optional<VariableNode> component = getVariableComponent("UnacknowledgedMessageCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setUnacknowledgedMessageCount(UInteger value) {
        getVariableComponent("UnacknowledgedMessageCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getDiscardedMessageCount() {
        Optional<VariableNode> component = getVariableComponent("DiscardedMessageCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDiscardedMessageCountNode() {
        Optional<VariableNode> component = getVariableComponent("DiscardedMessageCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDiscardedMessageCount(UInteger value) {
        getVariableComponent("DiscardedMessageCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getMonitoredItemCount() {
        Optional<VariableNode> component = getVariableComponent("MonitoredItemCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getMonitoredItemCountNode() {
        Optional<VariableNode> component = getVariableComponent("MonitoredItemCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setMonitoredItemCount(UInteger value) {
        getVariableComponent("MonitoredItemCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getDisabledMonitoredItemCount() {
        Optional<VariableNode> component = getVariableComponent("DisabledMonitoredItemCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getDisabledMonitoredItemCountNode() {
        Optional<VariableNode> component = getVariableComponent("DisabledMonitoredItemCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setDisabledMonitoredItemCount(UInteger value) {
        getVariableComponent("DisabledMonitoredItemCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getMonitoringQueueOverflowCount() {
        Optional<VariableNode> component = getVariableComponent("MonitoringQueueOverflowCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getMonitoringQueueOverflowCountNode() {
        Optional<VariableNode> component = getVariableComponent("MonitoringQueueOverflowCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setMonitoringQueueOverflowCount(UInteger value) {
        getVariableComponent("MonitoringQueueOverflowCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getNextSequenceNumber() {
        Optional<VariableNode> component = getVariableComponent("NextSequenceNumber");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getNextSequenceNumberNode() {
        Optional<VariableNode> component = getVariableComponent("NextSequenceNumber");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setNextSequenceNumber(UInteger value) {
        getVariableComponent("NextSequenceNumber")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getEventQueueOverFlowCount() {
        Optional<VariableNode> component = getVariableComponent("EventQueueOverFlowCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getEventQueueOverFlowCountNode() {
        Optional<VariableNode> component = getVariableComponent("EventQueueOverFlowCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setEventQueueOverFlowCount(UInteger value) {
        getVariableComponent("EventQueueOverFlowCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}

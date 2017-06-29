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

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class SubscriptionDiagnosticsDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SubscriptionDiagnosticsDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultXml;

    protected final NodeId sessionId;
    protected final UInteger subscriptionId;
    protected final UByte priority;
    protected final Double publishingInterval;
    protected final UInteger maxKeepAliveCount;
    protected final UInteger maxLifetimeCount;
    protected final UInteger maxNotificationsPerPublish;
    protected final Boolean publishingEnabled;
    protected final UInteger modifyCount;
    protected final UInteger enableCount;
    protected final UInteger disableCount;
    protected final UInteger republishRequestCount;
    protected final UInteger republishMessageRequestCount;
    protected final UInteger republishMessageCount;
    protected final UInteger transferRequestCount;
    protected final UInteger transferredToAltClientCount;
    protected final UInteger transferredToSameClientCount;
    protected final UInteger publishRequestCount;
    protected final UInteger dataChangeNotificationsCount;
    protected final UInteger eventNotificationsCount;
    protected final UInteger notificationsCount;
    protected final UInteger latePublishRequestCount;
    protected final UInteger currentKeepAliveCount;
    protected final UInteger currentLifetimeCount;
    protected final UInteger unacknowledgedMessageCount;
    protected final UInteger discardedMessageCount;
    protected final UInteger monitoredItemCount;
    protected final UInteger disabledMonitoredItemCount;
    protected final UInteger monitoringQueueOverflowCount;
    protected final UInteger nextSequenceNumber;
    protected final UInteger eventQueueOverFlowCount;

    public SubscriptionDiagnosticsDataType() {
        this.sessionId = null;
        this.subscriptionId = null;
        this.priority = null;
        this.publishingInterval = null;
        this.maxKeepAliveCount = null;
        this.maxLifetimeCount = null;
        this.maxNotificationsPerPublish = null;
        this.publishingEnabled = null;
        this.modifyCount = null;
        this.enableCount = null;
        this.disableCount = null;
        this.republishRequestCount = null;
        this.republishMessageRequestCount = null;
        this.republishMessageCount = null;
        this.transferRequestCount = null;
        this.transferredToAltClientCount = null;
        this.transferredToSameClientCount = null;
        this.publishRequestCount = null;
        this.dataChangeNotificationsCount = null;
        this.eventNotificationsCount = null;
        this.notificationsCount = null;
        this.latePublishRequestCount = null;
        this.currentKeepAliveCount = null;
        this.currentLifetimeCount = null;
        this.unacknowledgedMessageCount = null;
        this.discardedMessageCount = null;
        this.monitoredItemCount = null;
        this.disabledMonitoredItemCount = null;
        this.monitoringQueueOverflowCount = null;
        this.nextSequenceNumber = null;
        this.eventQueueOverFlowCount = null;
    }

    public SubscriptionDiagnosticsDataType(NodeId sessionId, UInteger subscriptionId, UByte priority, Double publishingInterval, UInteger maxKeepAliveCount, UInteger maxLifetimeCount, UInteger maxNotificationsPerPublish, Boolean publishingEnabled, UInteger modifyCount, UInteger enableCount, UInteger disableCount, UInteger republishRequestCount, UInteger republishMessageRequestCount, UInteger republishMessageCount, UInteger transferRequestCount, UInteger transferredToAltClientCount, UInteger transferredToSameClientCount, UInteger publishRequestCount, UInteger dataChangeNotificationsCount, UInteger eventNotificationsCount, UInteger notificationsCount, UInteger latePublishRequestCount, UInteger currentKeepAliveCount, UInteger currentLifetimeCount, UInteger unacknowledgedMessageCount, UInteger discardedMessageCount, UInteger monitoredItemCount, UInteger disabledMonitoredItemCount, UInteger monitoringQueueOverflowCount, UInteger nextSequenceNumber, UInteger eventQueueOverFlowCount) {
        this.sessionId = sessionId;
        this.subscriptionId = subscriptionId;
        this.priority = priority;
        this.publishingInterval = publishingInterval;
        this.maxKeepAliveCount = maxKeepAliveCount;
        this.maxLifetimeCount = maxLifetimeCount;
        this.maxNotificationsPerPublish = maxNotificationsPerPublish;
        this.publishingEnabled = publishingEnabled;
        this.modifyCount = modifyCount;
        this.enableCount = enableCount;
        this.disableCount = disableCount;
        this.republishRequestCount = republishRequestCount;
        this.republishMessageRequestCount = republishMessageRequestCount;
        this.republishMessageCount = republishMessageCount;
        this.transferRequestCount = transferRequestCount;
        this.transferredToAltClientCount = transferredToAltClientCount;
        this.transferredToSameClientCount = transferredToSameClientCount;
        this.publishRequestCount = publishRequestCount;
        this.dataChangeNotificationsCount = dataChangeNotificationsCount;
        this.eventNotificationsCount = eventNotificationsCount;
        this.notificationsCount = notificationsCount;
        this.latePublishRequestCount = latePublishRequestCount;
        this.currentKeepAliveCount = currentKeepAliveCount;
        this.currentLifetimeCount = currentLifetimeCount;
        this.unacknowledgedMessageCount = unacknowledgedMessageCount;
        this.discardedMessageCount = discardedMessageCount;
        this.monitoredItemCount = monitoredItemCount;
        this.disabledMonitoredItemCount = disabledMonitoredItemCount;
        this.monitoringQueueOverflowCount = monitoringQueueOverflowCount;
        this.nextSequenceNumber = nextSequenceNumber;
        this.eventQueueOverFlowCount = eventQueueOverFlowCount;
    }

    public NodeId getSessionId() { return sessionId; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    public UByte getPriority() { return priority; }

    public Double getPublishingInterval() { return publishingInterval; }

    public UInteger getMaxKeepAliveCount() { return maxKeepAliveCount; }

    public UInteger getMaxLifetimeCount() { return maxLifetimeCount; }

    public UInteger getMaxNotificationsPerPublish() { return maxNotificationsPerPublish; }

    public Boolean getPublishingEnabled() { return publishingEnabled; }

    public UInteger getModifyCount() { return modifyCount; }

    public UInteger getEnableCount() { return enableCount; }

    public UInteger getDisableCount() { return disableCount; }

    public UInteger getRepublishRequestCount() { return republishRequestCount; }

    public UInteger getRepublishMessageRequestCount() { return republishMessageRequestCount; }

    public UInteger getRepublishMessageCount() { return republishMessageCount; }

    public UInteger getTransferRequestCount() { return transferRequestCount; }

    public UInteger getTransferredToAltClientCount() { return transferredToAltClientCount; }

    public UInteger getTransferredToSameClientCount() { return transferredToSameClientCount; }

    public UInteger getPublishRequestCount() { return publishRequestCount; }

    public UInteger getDataChangeNotificationsCount() { return dataChangeNotificationsCount; }

    public UInteger getEventNotificationsCount() { return eventNotificationsCount; }

    public UInteger getNotificationsCount() { return notificationsCount; }

    public UInteger getLatePublishRequestCount() { return latePublishRequestCount; }

    public UInteger getCurrentKeepAliveCount() { return currentKeepAliveCount; }

    public UInteger getCurrentLifetimeCount() { return currentLifetimeCount; }

    public UInteger getUnacknowledgedMessageCount() { return unacknowledgedMessageCount; }

    public UInteger getDiscardedMessageCount() { return discardedMessageCount; }

    public UInteger getMonitoredItemCount() { return monitoredItemCount; }

    public UInteger getDisabledMonitoredItemCount() { return disabledMonitoredItemCount; }

    public UInteger getMonitoringQueueOverflowCount() { return monitoringQueueOverflowCount; }

    public UInteger getNextSequenceNumber() { return nextSequenceNumber; }

    public UInteger getEventQueueOverFlowCount() { return eventQueueOverFlowCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SessionId", sessionId)
            .add("SubscriptionId", subscriptionId)
            .add("Priority", priority)
            .add("PublishingInterval", publishingInterval)
            .add("MaxKeepAliveCount", maxKeepAliveCount)
            .add("MaxLifetimeCount", maxLifetimeCount)
            .add("MaxNotificationsPerPublish", maxNotificationsPerPublish)
            .add("PublishingEnabled", publishingEnabled)
            .add("ModifyCount", modifyCount)
            .add("EnableCount", enableCount)
            .add("DisableCount", disableCount)
            .add("RepublishRequestCount", republishRequestCount)
            .add("RepublishMessageRequestCount", republishMessageRequestCount)
            .add("RepublishMessageCount", republishMessageCount)
            .add("TransferRequestCount", transferRequestCount)
            .add("TransferredToAltClientCount", transferredToAltClientCount)
            .add("TransferredToSameClientCount", transferredToSameClientCount)
            .add("PublishRequestCount", publishRequestCount)
            .add("DataChangeNotificationsCount", dataChangeNotificationsCount)
            .add("EventNotificationsCount", eventNotificationsCount)
            .add("NotificationsCount", notificationsCount)
            .add("LatePublishRequestCount", latePublishRequestCount)
            .add("CurrentKeepAliveCount", currentKeepAliveCount)
            .add("CurrentLifetimeCount", currentLifetimeCount)
            .add("UnacknowledgedMessageCount", unacknowledgedMessageCount)
            .add("DiscardedMessageCount", discardedMessageCount)
            .add("MonitoredItemCount", monitoredItemCount)
            .add("DisabledMonitoredItemCount", disabledMonitoredItemCount)
            .add("MonitoringQueueOverflowCount", monitoringQueueOverflowCount)
            .add("NextSequenceNumber", nextSequenceNumber)
            .add("EventQueueOverFlowCount", eventQueueOverFlowCount)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SubscriptionDiagnosticsDataType> {

        @Override
        public Class<SubscriptionDiagnosticsDataType> getType() {
            return SubscriptionDiagnosticsDataType.class;
        }

        @Override
        public SubscriptionDiagnosticsDataType decode(UaDecoder decoder) throws UaSerializationException {
            NodeId sessionId = decoder.readNodeId("SessionId");
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UByte priority = decoder.readByte("Priority");
            Double publishingInterval = decoder.readDouble("PublishingInterval");
            UInteger maxKeepAliveCount = decoder.readUInt32("MaxKeepAliveCount");
            UInteger maxLifetimeCount = decoder.readUInt32("MaxLifetimeCount");
            UInteger maxNotificationsPerPublish = decoder.readUInt32("MaxNotificationsPerPublish");
            Boolean publishingEnabled = decoder.readBoolean("PublishingEnabled");
            UInteger modifyCount = decoder.readUInt32("ModifyCount");
            UInteger enableCount = decoder.readUInt32("EnableCount");
            UInteger disableCount = decoder.readUInt32("DisableCount");
            UInteger republishRequestCount = decoder.readUInt32("RepublishRequestCount");
            UInteger republishMessageRequestCount = decoder.readUInt32("RepublishMessageRequestCount");
            UInteger republishMessageCount = decoder.readUInt32("RepublishMessageCount");
            UInteger transferRequestCount = decoder.readUInt32("TransferRequestCount");
            UInteger transferredToAltClientCount = decoder.readUInt32("TransferredToAltClientCount");
            UInteger transferredToSameClientCount = decoder.readUInt32("TransferredToSameClientCount");
            UInteger publishRequestCount = decoder.readUInt32("PublishRequestCount");
            UInteger dataChangeNotificationsCount = decoder.readUInt32("DataChangeNotificationsCount");
            UInteger eventNotificationsCount = decoder.readUInt32("EventNotificationsCount");
            UInteger notificationsCount = decoder.readUInt32("NotificationsCount");
            UInteger latePublishRequestCount = decoder.readUInt32("LatePublishRequestCount");
            UInteger currentKeepAliveCount = decoder.readUInt32("CurrentKeepAliveCount");
            UInteger currentLifetimeCount = decoder.readUInt32("CurrentLifetimeCount");
            UInteger unacknowledgedMessageCount = decoder.readUInt32("UnacknowledgedMessageCount");
            UInteger discardedMessageCount = decoder.readUInt32("DiscardedMessageCount");
            UInteger monitoredItemCount = decoder.readUInt32("MonitoredItemCount");
            UInteger disabledMonitoredItemCount = decoder.readUInt32("DisabledMonitoredItemCount");
            UInteger monitoringQueueOverflowCount = decoder.readUInt32("MonitoringQueueOverflowCount");
            UInteger nextSequenceNumber = decoder.readUInt32("NextSequenceNumber");
            UInteger eventQueueOverFlowCount = decoder.readUInt32("EventQueueOverFlowCount");

            return new SubscriptionDiagnosticsDataType(sessionId, subscriptionId, priority, publishingInterval, maxKeepAliveCount, maxLifetimeCount, maxNotificationsPerPublish, publishingEnabled, modifyCount, enableCount, disableCount, republishRequestCount, republishMessageRequestCount, republishMessageCount, transferRequestCount, transferredToAltClientCount, transferredToSameClientCount, publishRequestCount, dataChangeNotificationsCount, eventNotificationsCount, notificationsCount, latePublishRequestCount, currentKeepAliveCount, currentLifetimeCount, unacknowledgedMessageCount, discardedMessageCount, monitoredItemCount, disabledMonitoredItemCount, monitoringQueueOverflowCount, nextSequenceNumber, eventQueueOverFlowCount);
        }

        @Override
        public void encode(SubscriptionDiagnosticsDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("SessionId", value.sessionId);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeByte("Priority", value.priority);
            encoder.writeDouble("PublishingInterval", value.publishingInterval);
            encoder.writeUInt32("MaxKeepAliveCount", value.maxKeepAliveCount);
            encoder.writeUInt32("MaxLifetimeCount", value.maxLifetimeCount);
            encoder.writeUInt32("MaxNotificationsPerPublish", value.maxNotificationsPerPublish);
            encoder.writeBoolean("PublishingEnabled", value.publishingEnabled);
            encoder.writeUInt32("ModifyCount", value.modifyCount);
            encoder.writeUInt32("EnableCount", value.enableCount);
            encoder.writeUInt32("DisableCount", value.disableCount);
            encoder.writeUInt32("RepublishRequestCount", value.republishRequestCount);
            encoder.writeUInt32("RepublishMessageRequestCount", value.republishMessageRequestCount);
            encoder.writeUInt32("RepublishMessageCount", value.republishMessageCount);
            encoder.writeUInt32("TransferRequestCount", value.transferRequestCount);
            encoder.writeUInt32("TransferredToAltClientCount", value.transferredToAltClientCount);
            encoder.writeUInt32("TransferredToSameClientCount", value.transferredToSameClientCount);
            encoder.writeUInt32("PublishRequestCount", value.publishRequestCount);
            encoder.writeUInt32("DataChangeNotificationsCount", value.dataChangeNotificationsCount);
            encoder.writeUInt32("EventNotificationsCount", value.eventNotificationsCount);
            encoder.writeUInt32("NotificationsCount", value.notificationsCount);
            encoder.writeUInt32("LatePublishRequestCount", value.latePublishRequestCount);
            encoder.writeUInt32("CurrentKeepAliveCount", value.currentKeepAliveCount);
            encoder.writeUInt32("CurrentLifetimeCount", value.currentLifetimeCount);
            encoder.writeUInt32("UnacknowledgedMessageCount", value.unacknowledgedMessageCount);
            encoder.writeUInt32("DiscardedMessageCount", value.discardedMessageCount);
            encoder.writeUInt32("MonitoredItemCount", value.monitoredItemCount);
            encoder.writeUInt32("DisabledMonitoredItemCount", value.disabledMonitoredItemCount);
            encoder.writeUInt32("MonitoringQueueOverflowCount", value.monitoringQueueOverflowCount);
            encoder.writeUInt32("NextSequenceNumber", value.nextSequenceNumber);
            encoder.writeUInt32("EventQueueOverFlowCount", value.eventQueueOverFlowCount);
        }
    }

}

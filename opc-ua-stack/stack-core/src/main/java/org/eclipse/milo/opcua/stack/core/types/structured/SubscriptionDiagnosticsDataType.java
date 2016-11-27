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

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("SubscriptionDiagnosticsDataType")
public class SubscriptionDiagnosticsDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SubscriptionDiagnosticsDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultXml;

    protected final NodeId _sessionId;
    protected final UInteger _subscriptionId;
    protected final UByte _priority;
    protected final Double _publishingInterval;
    protected final UInteger _maxKeepAliveCount;
    protected final UInteger _maxLifetimeCount;
    protected final UInteger _maxNotificationsPerPublish;
    protected final Boolean _publishingEnabled;
    protected final UInteger _modifyCount;
    protected final UInteger _enableCount;
    protected final UInteger _disableCount;
    protected final UInteger _republishRequestCount;
    protected final UInteger _republishMessageRequestCount;
    protected final UInteger _republishMessageCount;
    protected final UInteger _transferRequestCount;
    protected final UInteger _transferredToAltClientCount;
    protected final UInteger _transferredToSameClientCount;
    protected final UInteger _publishRequestCount;
    protected final UInteger _dataChangeNotificationsCount;
    protected final UInteger _eventNotificationsCount;
    protected final UInteger _notificationsCount;
    protected final UInteger _latePublishRequestCount;
    protected final UInteger _currentKeepAliveCount;
    protected final UInteger _currentLifetimeCount;
    protected final UInteger _unacknowledgedMessageCount;
    protected final UInteger _discardedMessageCount;
    protected final UInteger _monitoredItemCount;
    protected final UInteger _disabledMonitoredItemCount;
    protected final UInteger _monitoringQueueOverflowCount;
    protected final UInteger _nextSequenceNumber;
    protected final UInteger _eventQueueOverFlowCount;

    public SubscriptionDiagnosticsDataType() {
        this._sessionId = null;
        this._subscriptionId = null;
        this._priority = null;
        this._publishingInterval = null;
        this._maxKeepAliveCount = null;
        this._maxLifetimeCount = null;
        this._maxNotificationsPerPublish = null;
        this._publishingEnabled = null;
        this._modifyCount = null;
        this._enableCount = null;
        this._disableCount = null;
        this._republishRequestCount = null;
        this._republishMessageRequestCount = null;
        this._republishMessageCount = null;
        this._transferRequestCount = null;
        this._transferredToAltClientCount = null;
        this._transferredToSameClientCount = null;
        this._publishRequestCount = null;
        this._dataChangeNotificationsCount = null;
        this._eventNotificationsCount = null;
        this._notificationsCount = null;
        this._latePublishRequestCount = null;
        this._currentKeepAliveCount = null;
        this._currentLifetimeCount = null;
        this._unacknowledgedMessageCount = null;
        this._discardedMessageCount = null;
        this._monitoredItemCount = null;
        this._disabledMonitoredItemCount = null;
        this._monitoringQueueOverflowCount = null;
        this._nextSequenceNumber = null;
        this._eventQueueOverFlowCount = null;
    }

    public SubscriptionDiagnosticsDataType(NodeId _sessionId, UInteger _subscriptionId, UByte _priority, Double _publishingInterval, UInteger _maxKeepAliveCount, UInteger _maxLifetimeCount, UInteger _maxNotificationsPerPublish, Boolean _publishingEnabled, UInteger _modifyCount, UInteger _enableCount, UInteger _disableCount, UInteger _republishRequestCount, UInteger _republishMessageRequestCount, UInteger _republishMessageCount, UInteger _transferRequestCount, UInteger _transferredToAltClientCount, UInteger _transferredToSameClientCount, UInteger _publishRequestCount, UInteger _dataChangeNotificationsCount, UInteger _eventNotificationsCount, UInteger _notificationsCount, UInteger _latePublishRequestCount, UInteger _currentKeepAliveCount, UInteger _currentLifetimeCount, UInteger _unacknowledgedMessageCount, UInteger _discardedMessageCount, UInteger _monitoredItemCount, UInteger _disabledMonitoredItemCount, UInteger _monitoringQueueOverflowCount, UInteger _nextSequenceNumber, UInteger _eventQueueOverFlowCount) {
        this._sessionId = _sessionId;
        this._subscriptionId = _subscriptionId;
        this._priority = _priority;
        this._publishingInterval = _publishingInterval;
        this._maxKeepAliveCount = _maxKeepAliveCount;
        this._maxLifetimeCount = _maxLifetimeCount;
        this._maxNotificationsPerPublish = _maxNotificationsPerPublish;
        this._publishingEnabled = _publishingEnabled;
        this._modifyCount = _modifyCount;
        this._enableCount = _enableCount;
        this._disableCount = _disableCount;
        this._republishRequestCount = _republishRequestCount;
        this._republishMessageRequestCount = _republishMessageRequestCount;
        this._republishMessageCount = _republishMessageCount;
        this._transferRequestCount = _transferRequestCount;
        this._transferredToAltClientCount = _transferredToAltClientCount;
        this._transferredToSameClientCount = _transferredToSameClientCount;
        this._publishRequestCount = _publishRequestCount;
        this._dataChangeNotificationsCount = _dataChangeNotificationsCount;
        this._eventNotificationsCount = _eventNotificationsCount;
        this._notificationsCount = _notificationsCount;
        this._latePublishRequestCount = _latePublishRequestCount;
        this._currentKeepAliveCount = _currentKeepAliveCount;
        this._currentLifetimeCount = _currentLifetimeCount;
        this._unacknowledgedMessageCount = _unacknowledgedMessageCount;
        this._discardedMessageCount = _discardedMessageCount;
        this._monitoredItemCount = _monitoredItemCount;
        this._disabledMonitoredItemCount = _disabledMonitoredItemCount;
        this._monitoringQueueOverflowCount = _monitoringQueueOverflowCount;
        this._nextSequenceNumber = _nextSequenceNumber;
        this._eventQueueOverFlowCount = _eventQueueOverFlowCount;
    }

    public NodeId getSessionId() { return _sessionId; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public UByte getPriority() { return _priority; }

    public Double getPublishingInterval() { return _publishingInterval; }

    public UInteger getMaxKeepAliveCount() { return _maxKeepAliveCount; }

    public UInteger getMaxLifetimeCount() { return _maxLifetimeCount; }

    public UInteger getMaxNotificationsPerPublish() { return _maxNotificationsPerPublish; }

    public Boolean getPublishingEnabled() { return _publishingEnabled; }

    public UInteger getModifyCount() { return _modifyCount; }

    public UInteger getEnableCount() { return _enableCount; }

    public UInteger getDisableCount() { return _disableCount; }

    public UInteger getRepublishRequestCount() { return _republishRequestCount; }

    public UInteger getRepublishMessageRequestCount() { return _republishMessageRequestCount; }

    public UInteger getRepublishMessageCount() { return _republishMessageCount; }

    public UInteger getTransferRequestCount() { return _transferRequestCount; }

    public UInteger getTransferredToAltClientCount() { return _transferredToAltClientCount; }

    public UInteger getTransferredToSameClientCount() { return _transferredToSameClientCount; }

    public UInteger getPublishRequestCount() { return _publishRequestCount; }

    public UInteger getDataChangeNotificationsCount() { return _dataChangeNotificationsCount; }

    public UInteger getEventNotificationsCount() { return _eventNotificationsCount; }

    public UInteger getNotificationsCount() { return _notificationsCount; }

    public UInteger getLatePublishRequestCount() { return _latePublishRequestCount; }

    public UInteger getCurrentKeepAliveCount() { return _currentKeepAliveCount; }

    public UInteger getCurrentLifetimeCount() { return _currentLifetimeCount; }

    public UInteger getUnacknowledgedMessageCount() { return _unacknowledgedMessageCount; }

    public UInteger getDiscardedMessageCount() { return _discardedMessageCount; }

    public UInteger getMonitoredItemCount() { return _monitoredItemCount; }

    public UInteger getDisabledMonitoredItemCount() { return _disabledMonitoredItemCount; }

    public UInteger getMonitoringQueueOverflowCount() { return _monitoringQueueOverflowCount; }

    public UInteger getNextSequenceNumber() { return _nextSequenceNumber; }

    public UInteger getEventQueueOverFlowCount() { return _eventQueueOverFlowCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SessionId", _sessionId)
            .add("SubscriptionId", _subscriptionId)
            .add("Priority", _priority)
            .add("PublishingInterval", _publishingInterval)
            .add("MaxKeepAliveCount", _maxKeepAliveCount)
            .add("MaxLifetimeCount", _maxLifetimeCount)
            .add("MaxNotificationsPerPublish", _maxNotificationsPerPublish)
            .add("PublishingEnabled", _publishingEnabled)
            .add("ModifyCount", _modifyCount)
            .add("EnableCount", _enableCount)
            .add("DisableCount", _disableCount)
            .add("RepublishRequestCount", _republishRequestCount)
            .add("RepublishMessageRequestCount", _republishMessageRequestCount)
            .add("RepublishMessageCount", _republishMessageCount)
            .add("TransferRequestCount", _transferRequestCount)
            .add("TransferredToAltClientCount", _transferredToAltClientCount)
            .add("TransferredToSameClientCount", _transferredToSameClientCount)
            .add("PublishRequestCount", _publishRequestCount)
            .add("DataChangeNotificationsCount", _dataChangeNotificationsCount)
            .add("EventNotificationsCount", _eventNotificationsCount)
            .add("NotificationsCount", _notificationsCount)
            .add("LatePublishRequestCount", _latePublishRequestCount)
            .add("CurrentKeepAliveCount", _currentKeepAliveCount)
            .add("CurrentLifetimeCount", _currentLifetimeCount)
            .add("UnacknowledgedMessageCount", _unacknowledgedMessageCount)
            .add("DiscardedMessageCount", _discardedMessageCount)
            .add("MonitoredItemCount", _monitoredItemCount)
            .add("DisabledMonitoredItemCount", _disabledMonitoredItemCount)
            .add("MonitoringQueueOverflowCount", _monitoringQueueOverflowCount)
            .add("NextSequenceNumber", _nextSequenceNumber)
            .add("EventQueueOverFlowCount", _eventQueueOverFlowCount)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SubscriptionDiagnosticsDataType> {
        @Override
        public SubscriptionDiagnosticsDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _sessionId = reader.readNodeId();
            UInteger _subscriptionId = reader.readUInt32();
            UByte _priority = reader.readByte();
            Double _publishingInterval = reader.readDouble();
            UInteger _maxKeepAliveCount = reader.readUInt32();
            UInteger _maxLifetimeCount = reader.readUInt32();
            UInteger _maxNotificationsPerPublish = reader.readUInt32();
            Boolean _publishingEnabled = reader.readBoolean();
            UInteger _modifyCount = reader.readUInt32();
            UInteger _enableCount = reader.readUInt32();
            UInteger _disableCount = reader.readUInt32();
            UInteger _republishRequestCount = reader.readUInt32();
            UInteger _republishMessageRequestCount = reader.readUInt32();
            UInteger _republishMessageCount = reader.readUInt32();
            UInteger _transferRequestCount = reader.readUInt32();
            UInteger _transferredToAltClientCount = reader.readUInt32();
            UInteger _transferredToSameClientCount = reader.readUInt32();
            UInteger _publishRequestCount = reader.readUInt32();
            UInteger _dataChangeNotificationsCount = reader.readUInt32();
            UInteger _eventNotificationsCount = reader.readUInt32();
            UInteger _notificationsCount = reader.readUInt32();
            UInteger _latePublishRequestCount = reader.readUInt32();
            UInteger _currentKeepAliveCount = reader.readUInt32();
            UInteger _currentLifetimeCount = reader.readUInt32();
            UInteger _unacknowledgedMessageCount = reader.readUInt32();
            UInteger _discardedMessageCount = reader.readUInt32();
            UInteger _monitoredItemCount = reader.readUInt32();
            UInteger _disabledMonitoredItemCount = reader.readUInt32();
            UInteger _monitoringQueueOverflowCount = reader.readUInt32();
            UInteger _nextSequenceNumber = reader.readUInt32();
            UInteger _eventQueueOverFlowCount = reader.readUInt32();

            return new SubscriptionDiagnosticsDataType(_sessionId, _subscriptionId, _priority, _publishingInterval, _maxKeepAliveCount, _maxLifetimeCount, _maxNotificationsPerPublish, _publishingEnabled, _modifyCount, _enableCount, _disableCount, _republishRequestCount, _republishMessageRequestCount, _republishMessageCount, _transferRequestCount, _transferredToAltClientCount, _transferredToSameClientCount, _publishRequestCount, _dataChangeNotificationsCount, _eventNotificationsCount, _notificationsCount, _latePublishRequestCount, _currentKeepAliveCount, _currentLifetimeCount, _unacknowledgedMessageCount, _discardedMessageCount, _monitoredItemCount, _disabledMonitoredItemCount, _monitoringQueueOverflowCount, _nextSequenceNumber, _eventQueueOverFlowCount);
        }

        @Override
        public void encode(SerializationContext context, SubscriptionDiagnosticsDataType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._sessionId);
            writer.writeUInt32(encodable._subscriptionId);
            writer.writeByte(encodable._priority);
            writer.writeDouble(encodable._publishingInterval);
            writer.writeUInt32(encodable._maxKeepAliveCount);
            writer.writeUInt32(encodable._maxLifetimeCount);
            writer.writeUInt32(encodable._maxNotificationsPerPublish);
            writer.writeBoolean(encodable._publishingEnabled);
            writer.writeUInt32(encodable._modifyCount);
            writer.writeUInt32(encodable._enableCount);
            writer.writeUInt32(encodable._disableCount);
            writer.writeUInt32(encodable._republishRequestCount);
            writer.writeUInt32(encodable._republishMessageRequestCount);
            writer.writeUInt32(encodable._republishMessageCount);
            writer.writeUInt32(encodable._transferRequestCount);
            writer.writeUInt32(encodable._transferredToAltClientCount);
            writer.writeUInt32(encodable._transferredToSameClientCount);
            writer.writeUInt32(encodable._publishRequestCount);
            writer.writeUInt32(encodable._dataChangeNotificationsCount);
            writer.writeUInt32(encodable._eventNotificationsCount);
            writer.writeUInt32(encodable._notificationsCount);
            writer.writeUInt32(encodable._latePublishRequestCount);
            writer.writeUInt32(encodable._currentKeepAliveCount);
            writer.writeUInt32(encodable._currentLifetimeCount);
            writer.writeUInt32(encodable._unacknowledgedMessageCount);
            writer.writeUInt32(encodable._discardedMessageCount);
            writer.writeUInt32(encodable._monitoredItemCount);
            writer.writeUInt32(encodable._disabledMonitoredItemCount);
            writer.writeUInt32(encodable._monitoringQueueOverflowCount);
            writer.writeUInt32(encodable._nextSequenceNumber);
            writer.writeUInt32(encodable._eventQueueOverFlowCount);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SubscriptionDiagnosticsDataType> {
        @Override
        public SubscriptionDiagnosticsDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _sessionId = reader.readNodeId("SessionId");
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            UByte _priority = reader.readByte("Priority");
            Double _publishingInterval = reader.readDouble("PublishingInterval");
            UInteger _maxKeepAliveCount = reader.readUInt32("MaxKeepAliveCount");
            UInteger _maxLifetimeCount = reader.readUInt32("MaxLifetimeCount");
            UInteger _maxNotificationsPerPublish = reader.readUInt32("MaxNotificationsPerPublish");
            Boolean _publishingEnabled = reader.readBoolean("PublishingEnabled");
            UInteger _modifyCount = reader.readUInt32("ModifyCount");
            UInteger _enableCount = reader.readUInt32("EnableCount");
            UInteger _disableCount = reader.readUInt32("DisableCount");
            UInteger _republishRequestCount = reader.readUInt32("RepublishRequestCount");
            UInteger _republishMessageRequestCount = reader.readUInt32("RepublishMessageRequestCount");
            UInteger _republishMessageCount = reader.readUInt32("RepublishMessageCount");
            UInteger _transferRequestCount = reader.readUInt32("TransferRequestCount");
            UInteger _transferredToAltClientCount = reader.readUInt32("TransferredToAltClientCount");
            UInteger _transferredToSameClientCount = reader.readUInt32("TransferredToSameClientCount");
            UInteger _publishRequestCount = reader.readUInt32("PublishRequestCount");
            UInteger _dataChangeNotificationsCount = reader.readUInt32("DataChangeNotificationsCount");
            UInteger _eventNotificationsCount = reader.readUInt32("EventNotificationsCount");
            UInteger _notificationsCount = reader.readUInt32("NotificationsCount");
            UInteger _latePublishRequestCount = reader.readUInt32("LatePublishRequestCount");
            UInteger _currentKeepAliveCount = reader.readUInt32("CurrentKeepAliveCount");
            UInteger _currentLifetimeCount = reader.readUInt32("CurrentLifetimeCount");
            UInteger _unacknowledgedMessageCount = reader.readUInt32("UnacknowledgedMessageCount");
            UInteger _discardedMessageCount = reader.readUInt32("DiscardedMessageCount");
            UInteger _monitoredItemCount = reader.readUInt32("MonitoredItemCount");
            UInteger _disabledMonitoredItemCount = reader.readUInt32("DisabledMonitoredItemCount");
            UInteger _monitoringQueueOverflowCount = reader.readUInt32("MonitoringQueueOverflowCount");
            UInteger _nextSequenceNumber = reader.readUInt32("NextSequenceNumber");
            UInteger _eventQueueOverFlowCount = reader.readUInt32("EventQueueOverFlowCount");

            return new SubscriptionDiagnosticsDataType(_sessionId, _subscriptionId, _priority, _publishingInterval, _maxKeepAliveCount, _maxLifetimeCount, _maxNotificationsPerPublish, _publishingEnabled, _modifyCount, _enableCount, _disableCount, _republishRequestCount, _republishMessageRequestCount, _republishMessageCount, _transferRequestCount, _transferredToAltClientCount, _transferredToSameClientCount, _publishRequestCount, _dataChangeNotificationsCount, _eventNotificationsCount, _notificationsCount, _latePublishRequestCount, _currentKeepAliveCount, _currentLifetimeCount, _unacknowledgedMessageCount, _discardedMessageCount, _monitoredItemCount, _disabledMonitoredItemCount, _monitoringQueueOverflowCount, _nextSequenceNumber, _eventQueueOverFlowCount);
        }

        @Override
        public void encode(SerializationContext context, SubscriptionDiagnosticsDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("SessionId", encodable._sessionId);
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeByte("Priority", encodable._priority);
            writer.writeDouble("PublishingInterval", encodable._publishingInterval);
            writer.writeUInt32("MaxKeepAliveCount", encodable._maxKeepAliveCount);
            writer.writeUInt32("MaxLifetimeCount", encodable._maxLifetimeCount);
            writer.writeUInt32("MaxNotificationsPerPublish", encodable._maxNotificationsPerPublish);
            writer.writeBoolean("PublishingEnabled", encodable._publishingEnabled);
            writer.writeUInt32("ModifyCount", encodable._modifyCount);
            writer.writeUInt32("EnableCount", encodable._enableCount);
            writer.writeUInt32("DisableCount", encodable._disableCount);
            writer.writeUInt32("RepublishRequestCount", encodable._republishRequestCount);
            writer.writeUInt32("RepublishMessageRequestCount", encodable._republishMessageRequestCount);
            writer.writeUInt32("RepublishMessageCount", encodable._republishMessageCount);
            writer.writeUInt32("TransferRequestCount", encodable._transferRequestCount);
            writer.writeUInt32("TransferredToAltClientCount", encodable._transferredToAltClientCount);
            writer.writeUInt32("TransferredToSameClientCount", encodable._transferredToSameClientCount);
            writer.writeUInt32("PublishRequestCount", encodable._publishRequestCount);
            writer.writeUInt32("DataChangeNotificationsCount", encodable._dataChangeNotificationsCount);
            writer.writeUInt32("EventNotificationsCount", encodable._eventNotificationsCount);
            writer.writeUInt32("NotificationsCount", encodable._notificationsCount);
            writer.writeUInt32("LatePublishRequestCount", encodable._latePublishRequestCount);
            writer.writeUInt32("CurrentKeepAliveCount", encodable._currentKeepAliveCount);
            writer.writeUInt32("CurrentLifetimeCount", encodable._currentLifetimeCount);
            writer.writeUInt32("UnacknowledgedMessageCount", encodable._unacknowledgedMessageCount);
            writer.writeUInt32("DiscardedMessageCount", encodable._discardedMessageCount);
            writer.writeUInt32("MonitoredItemCount", encodable._monitoredItemCount);
            writer.writeUInt32("DisabledMonitoredItemCount", encodable._disabledMonitoredItemCount);
            writer.writeUInt32("MonitoringQueueOverflowCount", encodable._monitoringQueueOverflowCount);
            writer.writeUInt32("NextSequenceNumber", encodable._nextSequenceNumber);
            writer.writeUInt32("EventQueueOverFlowCount", encodable._eventQueueOverFlowCount);
        }
    }

}

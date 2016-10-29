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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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


    public static void encode(SubscriptionDiagnosticsDataType subscriptionDiagnosticsDataType, UaEncoder encoder) {
        encoder.encodeNodeId("SessionId", subscriptionDiagnosticsDataType._sessionId);
        encoder.encodeUInt32("SubscriptionId", subscriptionDiagnosticsDataType._subscriptionId);
        encoder.encodeByte("Priority", subscriptionDiagnosticsDataType._priority);
        encoder.encodeDouble("PublishingInterval", subscriptionDiagnosticsDataType._publishingInterval);
        encoder.encodeUInt32("MaxKeepAliveCount", subscriptionDiagnosticsDataType._maxKeepAliveCount);
        encoder.encodeUInt32("MaxLifetimeCount", subscriptionDiagnosticsDataType._maxLifetimeCount);
        encoder.encodeUInt32("MaxNotificationsPerPublish", subscriptionDiagnosticsDataType._maxNotificationsPerPublish);
        encoder.encodeBoolean("PublishingEnabled", subscriptionDiagnosticsDataType._publishingEnabled);
        encoder.encodeUInt32("ModifyCount", subscriptionDiagnosticsDataType._modifyCount);
        encoder.encodeUInt32("EnableCount", subscriptionDiagnosticsDataType._enableCount);
        encoder.encodeUInt32("DisableCount", subscriptionDiagnosticsDataType._disableCount);
        encoder.encodeUInt32("RepublishRequestCount", subscriptionDiagnosticsDataType._republishRequestCount);
        encoder.encodeUInt32("RepublishMessageRequestCount", subscriptionDiagnosticsDataType._republishMessageRequestCount);
        encoder.encodeUInt32("RepublishMessageCount", subscriptionDiagnosticsDataType._republishMessageCount);
        encoder.encodeUInt32("TransferRequestCount", subscriptionDiagnosticsDataType._transferRequestCount);
        encoder.encodeUInt32("TransferredToAltClientCount", subscriptionDiagnosticsDataType._transferredToAltClientCount);
        encoder.encodeUInt32("TransferredToSameClientCount", subscriptionDiagnosticsDataType._transferredToSameClientCount);
        encoder.encodeUInt32("PublishRequestCount", subscriptionDiagnosticsDataType._publishRequestCount);
        encoder.encodeUInt32("DataChangeNotificationsCount", subscriptionDiagnosticsDataType._dataChangeNotificationsCount);
        encoder.encodeUInt32("EventNotificationsCount", subscriptionDiagnosticsDataType._eventNotificationsCount);
        encoder.encodeUInt32("NotificationsCount", subscriptionDiagnosticsDataType._notificationsCount);
        encoder.encodeUInt32("LatePublishRequestCount", subscriptionDiagnosticsDataType._latePublishRequestCount);
        encoder.encodeUInt32("CurrentKeepAliveCount", subscriptionDiagnosticsDataType._currentKeepAliveCount);
        encoder.encodeUInt32("CurrentLifetimeCount", subscriptionDiagnosticsDataType._currentLifetimeCount);
        encoder.encodeUInt32("UnacknowledgedMessageCount", subscriptionDiagnosticsDataType._unacknowledgedMessageCount);
        encoder.encodeUInt32("DiscardedMessageCount", subscriptionDiagnosticsDataType._discardedMessageCount);
        encoder.encodeUInt32("MonitoredItemCount", subscriptionDiagnosticsDataType._monitoredItemCount);
        encoder.encodeUInt32("DisabledMonitoredItemCount", subscriptionDiagnosticsDataType._disabledMonitoredItemCount);
        encoder.encodeUInt32("MonitoringQueueOverflowCount", subscriptionDiagnosticsDataType._monitoringQueueOverflowCount);
        encoder.encodeUInt32("NextSequenceNumber", subscriptionDiagnosticsDataType._nextSequenceNumber);
        encoder.encodeUInt32("EventQueueOverFlowCount", subscriptionDiagnosticsDataType._eventQueueOverFlowCount);
    }

    public static SubscriptionDiagnosticsDataType decode(UaDecoder decoder) {
        NodeId _sessionId = decoder.decodeNodeId("SessionId");
        UInteger _subscriptionId = decoder.decodeUInt32("SubscriptionId");
        UByte _priority = decoder.decodeByte("Priority");
        Double _publishingInterval = decoder.decodeDouble("PublishingInterval");
        UInteger _maxKeepAliveCount = decoder.decodeUInt32("MaxKeepAliveCount");
        UInteger _maxLifetimeCount = decoder.decodeUInt32("MaxLifetimeCount");
        UInteger _maxNotificationsPerPublish = decoder.decodeUInt32("MaxNotificationsPerPublish");
        Boolean _publishingEnabled = decoder.decodeBoolean("PublishingEnabled");
        UInteger _modifyCount = decoder.decodeUInt32("ModifyCount");
        UInteger _enableCount = decoder.decodeUInt32("EnableCount");
        UInteger _disableCount = decoder.decodeUInt32("DisableCount");
        UInteger _republishRequestCount = decoder.decodeUInt32("RepublishRequestCount");
        UInteger _republishMessageRequestCount = decoder.decodeUInt32("RepublishMessageRequestCount");
        UInteger _republishMessageCount = decoder.decodeUInt32("RepublishMessageCount");
        UInteger _transferRequestCount = decoder.decodeUInt32("TransferRequestCount");
        UInteger _transferredToAltClientCount = decoder.decodeUInt32("TransferredToAltClientCount");
        UInteger _transferredToSameClientCount = decoder.decodeUInt32("TransferredToSameClientCount");
        UInteger _publishRequestCount = decoder.decodeUInt32("PublishRequestCount");
        UInteger _dataChangeNotificationsCount = decoder.decodeUInt32("DataChangeNotificationsCount");
        UInteger _eventNotificationsCount = decoder.decodeUInt32("EventNotificationsCount");
        UInteger _notificationsCount = decoder.decodeUInt32("NotificationsCount");
        UInteger _latePublishRequestCount = decoder.decodeUInt32("LatePublishRequestCount");
        UInteger _currentKeepAliveCount = decoder.decodeUInt32("CurrentKeepAliveCount");
        UInteger _currentLifetimeCount = decoder.decodeUInt32("CurrentLifetimeCount");
        UInteger _unacknowledgedMessageCount = decoder.decodeUInt32("UnacknowledgedMessageCount");
        UInteger _discardedMessageCount = decoder.decodeUInt32("DiscardedMessageCount");
        UInteger _monitoredItemCount = decoder.decodeUInt32("MonitoredItemCount");
        UInteger _disabledMonitoredItemCount = decoder.decodeUInt32("DisabledMonitoredItemCount");
        UInteger _monitoringQueueOverflowCount = decoder.decodeUInt32("MonitoringQueueOverflowCount");
        UInteger _nextSequenceNumber = decoder.decodeUInt32("NextSequenceNumber");
        UInteger _eventQueueOverFlowCount = decoder.decodeUInt32("EventQueueOverFlowCount");

        return new SubscriptionDiagnosticsDataType(_sessionId, _subscriptionId, _priority, _publishingInterval, _maxKeepAliveCount, _maxLifetimeCount, _maxNotificationsPerPublish, _publishingEnabled, _modifyCount, _enableCount, _disableCount, _republishRequestCount, _republishMessageRequestCount, _republishMessageCount, _transferRequestCount, _transferredToAltClientCount, _transferredToSameClientCount, _publishRequestCount, _dataChangeNotificationsCount, _eventNotificationsCount, _notificationsCount, _latePublishRequestCount, _currentKeepAliveCount, _currentLifetimeCount, _unacknowledgedMessageCount, _discardedMessageCount, _monitoredItemCount, _disabledMonitoredItemCount, _monitoringQueueOverflowCount, _nextSequenceNumber, _eventQueueOverFlowCount);
    }

    static {
        DelegateRegistry.registerEncoder(SubscriptionDiagnosticsDataType::encode, SubscriptionDiagnosticsDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SubscriptionDiagnosticsDataType::decode, SubscriptionDiagnosticsDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}

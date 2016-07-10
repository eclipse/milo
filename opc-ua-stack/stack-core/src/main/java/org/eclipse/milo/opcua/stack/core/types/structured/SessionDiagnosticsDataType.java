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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("SessionDiagnosticsDataType")
public class SessionDiagnosticsDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SessionDiagnosticsDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SessionDiagnosticsDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SessionDiagnosticsDataType_Encoding_DefaultXml;

    protected final NodeId _sessionId;
    protected final String _sessionName;
    protected final ApplicationDescription _clientDescription;
    protected final String _serverUri;
    protected final String _endpointUrl;
    protected final String[] _localeIds;
    protected final Double _actualSessionTimeout;
    protected final UInteger _maxResponseMessageSize;
    protected final DateTime _clientConnectionTime;
    protected final DateTime _clientLastContactTime;
    protected final UInteger _currentSubscriptionsCount;
    protected final UInteger _currentMonitoredItemsCount;
    protected final UInteger _currentPublishRequestsInQueue;
    protected final ServiceCounterDataType _totalRequestCount;
    protected final UInteger _unauthorizedRequestCount;
    protected final ServiceCounterDataType _readCount;
    protected final ServiceCounterDataType _historyReadCount;
    protected final ServiceCounterDataType _writeCount;
    protected final ServiceCounterDataType _historyUpdateCount;
    protected final ServiceCounterDataType _callCount;
    protected final ServiceCounterDataType _createMonitoredItemsCount;
    protected final ServiceCounterDataType _modifyMonitoredItemsCount;
    protected final ServiceCounterDataType _setMonitoringModeCount;
    protected final ServiceCounterDataType _setTriggeringCount;
    protected final ServiceCounterDataType _deleteMonitoredItemsCount;
    protected final ServiceCounterDataType _createSubscriptionCount;
    protected final ServiceCounterDataType _modifySubscriptionCount;
    protected final ServiceCounterDataType _setPublishingModeCount;
    protected final ServiceCounterDataType _publishCount;
    protected final ServiceCounterDataType _republishCount;
    protected final ServiceCounterDataType _transferSubscriptionsCount;
    protected final ServiceCounterDataType _deleteSubscriptionsCount;
    protected final ServiceCounterDataType _addNodesCount;
    protected final ServiceCounterDataType _addReferencesCount;
    protected final ServiceCounterDataType _deleteNodesCount;
    protected final ServiceCounterDataType _deleteReferencesCount;
    protected final ServiceCounterDataType _browseCount;
    protected final ServiceCounterDataType _browseNextCount;
    protected final ServiceCounterDataType _translateBrowsePathsToNodeIdsCount;
    protected final ServiceCounterDataType _queryFirstCount;
    protected final ServiceCounterDataType _queryNextCount;
    protected final ServiceCounterDataType _registerNodesCount;
    protected final ServiceCounterDataType _unregisterNodesCount;

    public SessionDiagnosticsDataType() {
        this._sessionId = null;
        this._sessionName = null;
        this._clientDescription = null;
        this._serverUri = null;
        this._endpointUrl = null;
        this._localeIds = null;
        this._actualSessionTimeout = null;
        this._maxResponseMessageSize = null;
        this._clientConnectionTime = null;
        this._clientLastContactTime = null;
        this._currentSubscriptionsCount = null;
        this._currentMonitoredItemsCount = null;
        this._currentPublishRequestsInQueue = null;
        this._totalRequestCount = null;
        this._unauthorizedRequestCount = null;
        this._readCount = null;
        this._historyReadCount = null;
        this._writeCount = null;
        this._historyUpdateCount = null;
        this._callCount = null;
        this._createMonitoredItemsCount = null;
        this._modifyMonitoredItemsCount = null;
        this._setMonitoringModeCount = null;
        this._setTriggeringCount = null;
        this._deleteMonitoredItemsCount = null;
        this._createSubscriptionCount = null;
        this._modifySubscriptionCount = null;
        this._setPublishingModeCount = null;
        this._publishCount = null;
        this._republishCount = null;
        this._transferSubscriptionsCount = null;
        this._deleteSubscriptionsCount = null;
        this._addNodesCount = null;
        this._addReferencesCount = null;
        this._deleteNodesCount = null;
        this._deleteReferencesCount = null;
        this._browseCount = null;
        this._browseNextCount = null;
        this._translateBrowsePathsToNodeIdsCount = null;
        this._queryFirstCount = null;
        this._queryNextCount = null;
        this._registerNodesCount = null;
        this._unregisterNodesCount = null;
    }

    public SessionDiagnosticsDataType(NodeId _sessionId, String _sessionName, ApplicationDescription _clientDescription, String _serverUri, String _endpointUrl, String[] _localeIds, Double _actualSessionTimeout, UInteger _maxResponseMessageSize, DateTime _clientConnectionTime, DateTime _clientLastContactTime, UInteger _currentSubscriptionsCount, UInteger _currentMonitoredItemsCount, UInteger _currentPublishRequestsInQueue, ServiceCounterDataType _totalRequestCount, UInteger _unauthorizedRequestCount, ServiceCounterDataType _readCount, ServiceCounterDataType _historyReadCount, ServiceCounterDataType _writeCount, ServiceCounterDataType _historyUpdateCount, ServiceCounterDataType _callCount, ServiceCounterDataType _createMonitoredItemsCount, ServiceCounterDataType _modifyMonitoredItemsCount, ServiceCounterDataType _setMonitoringModeCount, ServiceCounterDataType _setTriggeringCount, ServiceCounterDataType _deleteMonitoredItemsCount, ServiceCounterDataType _createSubscriptionCount, ServiceCounterDataType _modifySubscriptionCount, ServiceCounterDataType _setPublishingModeCount, ServiceCounterDataType _publishCount, ServiceCounterDataType _republishCount, ServiceCounterDataType _transferSubscriptionsCount, ServiceCounterDataType _deleteSubscriptionsCount, ServiceCounterDataType _addNodesCount, ServiceCounterDataType _addReferencesCount, ServiceCounterDataType _deleteNodesCount, ServiceCounterDataType _deleteReferencesCount, ServiceCounterDataType _browseCount, ServiceCounterDataType _browseNextCount, ServiceCounterDataType _translateBrowsePathsToNodeIdsCount, ServiceCounterDataType _queryFirstCount, ServiceCounterDataType _queryNextCount, ServiceCounterDataType _registerNodesCount, ServiceCounterDataType _unregisterNodesCount) {
        this._sessionId = _sessionId;
        this._sessionName = _sessionName;
        this._clientDescription = _clientDescription;
        this._serverUri = _serverUri;
        this._endpointUrl = _endpointUrl;
        this._localeIds = _localeIds;
        this._actualSessionTimeout = _actualSessionTimeout;
        this._maxResponseMessageSize = _maxResponseMessageSize;
        this._clientConnectionTime = _clientConnectionTime;
        this._clientLastContactTime = _clientLastContactTime;
        this._currentSubscriptionsCount = _currentSubscriptionsCount;
        this._currentMonitoredItemsCount = _currentMonitoredItemsCount;
        this._currentPublishRequestsInQueue = _currentPublishRequestsInQueue;
        this._totalRequestCount = _totalRequestCount;
        this._unauthorizedRequestCount = _unauthorizedRequestCount;
        this._readCount = _readCount;
        this._historyReadCount = _historyReadCount;
        this._writeCount = _writeCount;
        this._historyUpdateCount = _historyUpdateCount;
        this._callCount = _callCount;
        this._createMonitoredItemsCount = _createMonitoredItemsCount;
        this._modifyMonitoredItemsCount = _modifyMonitoredItemsCount;
        this._setMonitoringModeCount = _setMonitoringModeCount;
        this._setTriggeringCount = _setTriggeringCount;
        this._deleteMonitoredItemsCount = _deleteMonitoredItemsCount;
        this._createSubscriptionCount = _createSubscriptionCount;
        this._modifySubscriptionCount = _modifySubscriptionCount;
        this._setPublishingModeCount = _setPublishingModeCount;
        this._publishCount = _publishCount;
        this._republishCount = _republishCount;
        this._transferSubscriptionsCount = _transferSubscriptionsCount;
        this._deleteSubscriptionsCount = _deleteSubscriptionsCount;
        this._addNodesCount = _addNodesCount;
        this._addReferencesCount = _addReferencesCount;
        this._deleteNodesCount = _deleteNodesCount;
        this._deleteReferencesCount = _deleteReferencesCount;
        this._browseCount = _browseCount;
        this._browseNextCount = _browseNextCount;
        this._translateBrowsePathsToNodeIdsCount = _translateBrowsePathsToNodeIdsCount;
        this._queryFirstCount = _queryFirstCount;
        this._queryNextCount = _queryNextCount;
        this._registerNodesCount = _registerNodesCount;
        this._unregisterNodesCount = _unregisterNodesCount;
    }

    public NodeId getSessionId() { return _sessionId; }

    public String getSessionName() { return _sessionName; }

    public ApplicationDescription getClientDescription() { return _clientDescription; }

    public String getServerUri() { return _serverUri; }

    public String getEndpointUrl() { return _endpointUrl; }

    public String[] getLocaleIds() { return _localeIds; }

    public Double getActualSessionTimeout() { return _actualSessionTimeout; }

    public UInteger getMaxResponseMessageSize() { return _maxResponseMessageSize; }

    public DateTime getClientConnectionTime() { return _clientConnectionTime; }

    public DateTime getClientLastContactTime() { return _clientLastContactTime; }

    public UInteger getCurrentSubscriptionsCount() { return _currentSubscriptionsCount; }

    public UInteger getCurrentMonitoredItemsCount() { return _currentMonitoredItemsCount; }

    public UInteger getCurrentPublishRequestsInQueue() { return _currentPublishRequestsInQueue; }

    public ServiceCounterDataType getTotalRequestCount() { return _totalRequestCount; }

    public UInteger getUnauthorizedRequestCount() { return _unauthorizedRequestCount; }

    public ServiceCounterDataType getReadCount() { return _readCount; }

    public ServiceCounterDataType getHistoryReadCount() { return _historyReadCount; }

    public ServiceCounterDataType getWriteCount() { return _writeCount; }

    public ServiceCounterDataType getHistoryUpdateCount() { return _historyUpdateCount; }

    public ServiceCounterDataType getCallCount() { return _callCount; }

    public ServiceCounterDataType getCreateMonitoredItemsCount() { return _createMonitoredItemsCount; }

    public ServiceCounterDataType getModifyMonitoredItemsCount() { return _modifyMonitoredItemsCount; }

    public ServiceCounterDataType getSetMonitoringModeCount() { return _setMonitoringModeCount; }

    public ServiceCounterDataType getSetTriggeringCount() { return _setTriggeringCount; }

    public ServiceCounterDataType getDeleteMonitoredItemsCount() { return _deleteMonitoredItemsCount; }

    public ServiceCounterDataType getCreateSubscriptionCount() { return _createSubscriptionCount; }

    public ServiceCounterDataType getModifySubscriptionCount() { return _modifySubscriptionCount; }

    public ServiceCounterDataType getSetPublishingModeCount() { return _setPublishingModeCount; }

    public ServiceCounterDataType getPublishCount() { return _publishCount; }

    public ServiceCounterDataType getRepublishCount() { return _republishCount; }

    public ServiceCounterDataType getTransferSubscriptionsCount() { return _transferSubscriptionsCount; }

    public ServiceCounterDataType getDeleteSubscriptionsCount() { return _deleteSubscriptionsCount; }

    public ServiceCounterDataType getAddNodesCount() { return _addNodesCount; }

    public ServiceCounterDataType getAddReferencesCount() { return _addReferencesCount; }

    public ServiceCounterDataType getDeleteNodesCount() { return _deleteNodesCount; }

    public ServiceCounterDataType getDeleteReferencesCount() { return _deleteReferencesCount; }

    public ServiceCounterDataType getBrowseCount() { return _browseCount; }

    public ServiceCounterDataType getBrowseNextCount() { return _browseNextCount; }

    public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() { return _translateBrowsePathsToNodeIdsCount; }

    public ServiceCounterDataType getQueryFirstCount() { return _queryFirstCount; }

    public ServiceCounterDataType getQueryNextCount() { return _queryNextCount; }

    public ServiceCounterDataType getRegisterNodesCount() { return _registerNodesCount; }

    public ServiceCounterDataType getUnregisterNodesCount() { return _unregisterNodesCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(SessionDiagnosticsDataType sessionDiagnosticsDataType, UaEncoder encoder) {
        encoder.encodeNodeId("SessionId", sessionDiagnosticsDataType._sessionId);
        encoder.encodeString("SessionName", sessionDiagnosticsDataType._sessionName);
        encoder.encodeSerializable("ClientDescription", sessionDiagnosticsDataType._clientDescription != null ? sessionDiagnosticsDataType._clientDescription : new ApplicationDescription());
        encoder.encodeString("ServerUri", sessionDiagnosticsDataType._serverUri);
        encoder.encodeString("EndpointUrl", sessionDiagnosticsDataType._endpointUrl);
        encoder.encodeArray("LocaleIds", sessionDiagnosticsDataType._localeIds, encoder::encodeString);
        encoder.encodeDouble("ActualSessionTimeout", sessionDiagnosticsDataType._actualSessionTimeout);
        encoder.encodeUInt32("MaxResponseMessageSize", sessionDiagnosticsDataType._maxResponseMessageSize);
        encoder.encodeDateTime("ClientConnectionTime", sessionDiagnosticsDataType._clientConnectionTime);
        encoder.encodeDateTime("ClientLastContactTime", sessionDiagnosticsDataType._clientLastContactTime);
        encoder.encodeUInt32("CurrentSubscriptionsCount", sessionDiagnosticsDataType._currentSubscriptionsCount);
        encoder.encodeUInt32("CurrentMonitoredItemsCount", sessionDiagnosticsDataType._currentMonitoredItemsCount);
        encoder.encodeUInt32("CurrentPublishRequestsInQueue", sessionDiagnosticsDataType._currentPublishRequestsInQueue);
        encoder.encodeSerializable("TotalRequestCount", sessionDiagnosticsDataType._totalRequestCount != null ? sessionDiagnosticsDataType._totalRequestCount : new ServiceCounterDataType());
        encoder.encodeUInt32("UnauthorizedRequestCount", sessionDiagnosticsDataType._unauthorizedRequestCount);
        encoder.encodeSerializable("ReadCount", sessionDiagnosticsDataType._readCount != null ? sessionDiagnosticsDataType._readCount : new ServiceCounterDataType());
        encoder.encodeSerializable("HistoryReadCount", sessionDiagnosticsDataType._historyReadCount != null ? sessionDiagnosticsDataType._historyReadCount : new ServiceCounterDataType());
        encoder.encodeSerializable("WriteCount", sessionDiagnosticsDataType._writeCount != null ? sessionDiagnosticsDataType._writeCount : new ServiceCounterDataType());
        encoder.encodeSerializable("HistoryUpdateCount", sessionDiagnosticsDataType._historyUpdateCount != null ? sessionDiagnosticsDataType._historyUpdateCount : new ServiceCounterDataType());
        encoder.encodeSerializable("CallCount", sessionDiagnosticsDataType._callCount != null ? sessionDiagnosticsDataType._callCount : new ServiceCounterDataType());
        encoder.encodeSerializable("CreateMonitoredItemsCount", sessionDiagnosticsDataType._createMonitoredItemsCount != null ? sessionDiagnosticsDataType._createMonitoredItemsCount : new ServiceCounterDataType());
        encoder.encodeSerializable("ModifyMonitoredItemsCount", sessionDiagnosticsDataType._modifyMonitoredItemsCount != null ? sessionDiagnosticsDataType._modifyMonitoredItemsCount : new ServiceCounterDataType());
        encoder.encodeSerializable("SetMonitoringModeCount", sessionDiagnosticsDataType._setMonitoringModeCount != null ? sessionDiagnosticsDataType._setMonitoringModeCount : new ServiceCounterDataType());
        encoder.encodeSerializable("SetTriggeringCount", sessionDiagnosticsDataType._setTriggeringCount != null ? sessionDiagnosticsDataType._setTriggeringCount : new ServiceCounterDataType());
        encoder.encodeSerializable("DeleteMonitoredItemsCount", sessionDiagnosticsDataType._deleteMonitoredItemsCount != null ? sessionDiagnosticsDataType._deleteMonitoredItemsCount : new ServiceCounterDataType());
        encoder.encodeSerializable("CreateSubscriptionCount", sessionDiagnosticsDataType._createSubscriptionCount != null ? sessionDiagnosticsDataType._createSubscriptionCount : new ServiceCounterDataType());
        encoder.encodeSerializable("ModifySubscriptionCount", sessionDiagnosticsDataType._modifySubscriptionCount != null ? sessionDiagnosticsDataType._modifySubscriptionCount : new ServiceCounterDataType());
        encoder.encodeSerializable("SetPublishingModeCount", sessionDiagnosticsDataType._setPublishingModeCount != null ? sessionDiagnosticsDataType._setPublishingModeCount : new ServiceCounterDataType());
        encoder.encodeSerializable("PublishCount", sessionDiagnosticsDataType._publishCount != null ? sessionDiagnosticsDataType._publishCount : new ServiceCounterDataType());
        encoder.encodeSerializable("RepublishCount", sessionDiagnosticsDataType._republishCount != null ? sessionDiagnosticsDataType._republishCount : new ServiceCounterDataType());
        encoder.encodeSerializable("TransferSubscriptionsCount", sessionDiagnosticsDataType._transferSubscriptionsCount != null ? sessionDiagnosticsDataType._transferSubscriptionsCount : new ServiceCounterDataType());
        encoder.encodeSerializable("DeleteSubscriptionsCount", sessionDiagnosticsDataType._deleteSubscriptionsCount != null ? sessionDiagnosticsDataType._deleteSubscriptionsCount : new ServiceCounterDataType());
        encoder.encodeSerializable("AddNodesCount", sessionDiagnosticsDataType._addNodesCount != null ? sessionDiagnosticsDataType._addNodesCount : new ServiceCounterDataType());
        encoder.encodeSerializable("AddReferencesCount", sessionDiagnosticsDataType._addReferencesCount != null ? sessionDiagnosticsDataType._addReferencesCount : new ServiceCounterDataType());
        encoder.encodeSerializable("DeleteNodesCount", sessionDiagnosticsDataType._deleteNodesCount != null ? sessionDiagnosticsDataType._deleteNodesCount : new ServiceCounterDataType());
        encoder.encodeSerializable("DeleteReferencesCount", sessionDiagnosticsDataType._deleteReferencesCount != null ? sessionDiagnosticsDataType._deleteReferencesCount : new ServiceCounterDataType());
        encoder.encodeSerializable("BrowseCount", sessionDiagnosticsDataType._browseCount != null ? sessionDiagnosticsDataType._browseCount : new ServiceCounterDataType());
        encoder.encodeSerializable("BrowseNextCount", sessionDiagnosticsDataType._browseNextCount != null ? sessionDiagnosticsDataType._browseNextCount : new ServiceCounterDataType());
        encoder.encodeSerializable("TranslateBrowsePathsToNodeIdsCount", sessionDiagnosticsDataType._translateBrowsePathsToNodeIdsCount != null ? sessionDiagnosticsDataType._translateBrowsePathsToNodeIdsCount : new ServiceCounterDataType());
        encoder.encodeSerializable("QueryFirstCount", sessionDiagnosticsDataType._queryFirstCount != null ? sessionDiagnosticsDataType._queryFirstCount : new ServiceCounterDataType());
        encoder.encodeSerializable("QueryNextCount", sessionDiagnosticsDataType._queryNextCount != null ? sessionDiagnosticsDataType._queryNextCount : new ServiceCounterDataType());
        encoder.encodeSerializable("RegisterNodesCount", sessionDiagnosticsDataType._registerNodesCount != null ? sessionDiagnosticsDataType._registerNodesCount : new ServiceCounterDataType());
        encoder.encodeSerializable("UnregisterNodesCount", sessionDiagnosticsDataType._unregisterNodesCount != null ? sessionDiagnosticsDataType._unregisterNodesCount : new ServiceCounterDataType());
    }

    public static SessionDiagnosticsDataType decode(UaDecoder decoder) {
        NodeId _sessionId = decoder.decodeNodeId("SessionId");
        String _sessionName = decoder.decodeString("SessionName");
        ApplicationDescription _clientDescription = decoder.decodeSerializable("ClientDescription", ApplicationDescription.class);
        String _serverUri = decoder.decodeString("ServerUri");
        String _endpointUrl = decoder.decodeString("EndpointUrl");
        String[] _localeIds = decoder.decodeArray("LocaleIds", decoder::decodeString, String.class);
        Double _actualSessionTimeout = decoder.decodeDouble("ActualSessionTimeout");
        UInteger _maxResponseMessageSize = decoder.decodeUInt32("MaxResponseMessageSize");
        DateTime _clientConnectionTime = decoder.decodeDateTime("ClientConnectionTime");
        DateTime _clientLastContactTime = decoder.decodeDateTime("ClientLastContactTime");
        UInteger _currentSubscriptionsCount = decoder.decodeUInt32("CurrentSubscriptionsCount");
        UInteger _currentMonitoredItemsCount = decoder.decodeUInt32("CurrentMonitoredItemsCount");
        UInteger _currentPublishRequestsInQueue = decoder.decodeUInt32("CurrentPublishRequestsInQueue");
        ServiceCounterDataType _totalRequestCount = decoder.decodeSerializable("TotalRequestCount", ServiceCounterDataType.class);
        UInteger _unauthorizedRequestCount = decoder.decodeUInt32("UnauthorizedRequestCount");
        ServiceCounterDataType _readCount = decoder.decodeSerializable("ReadCount", ServiceCounterDataType.class);
        ServiceCounterDataType _historyReadCount = decoder.decodeSerializable("HistoryReadCount", ServiceCounterDataType.class);
        ServiceCounterDataType _writeCount = decoder.decodeSerializable("WriteCount", ServiceCounterDataType.class);
        ServiceCounterDataType _historyUpdateCount = decoder.decodeSerializable("HistoryUpdateCount", ServiceCounterDataType.class);
        ServiceCounterDataType _callCount = decoder.decodeSerializable("CallCount", ServiceCounterDataType.class);
        ServiceCounterDataType _createMonitoredItemsCount = decoder.decodeSerializable("CreateMonitoredItemsCount", ServiceCounterDataType.class);
        ServiceCounterDataType _modifyMonitoredItemsCount = decoder.decodeSerializable("ModifyMonitoredItemsCount", ServiceCounterDataType.class);
        ServiceCounterDataType _setMonitoringModeCount = decoder.decodeSerializable("SetMonitoringModeCount", ServiceCounterDataType.class);
        ServiceCounterDataType _setTriggeringCount = decoder.decodeSerializable("SetTriggeringCount", ServiceCounterDataType.class);
        ServiceCounterDataType _deleteMonitoredItemsCount = decoder.decodeSerializable("DeleteMonitoredItemsCount", ServiceCounterDataType.class);
        ServiceCounterDataType _createSubscriptionCount = decoder.decodeSerializable("CreateSubscriptionCount", ServiceCounterDataType.class);
        ServiceCounterDataType _modifySubscriptionCount = decoder.decodeSerializable("ModifySubscriptionCount", ServiceCounterDataType.class);
        ServiceCounterDataType _setPublishingModeCount = decoder.decodeSerializable("SetPublishingModeCount", ServiceCounterDataType.class);
        ServiceCounterDataType _publishCount = decoder.decodeSerializable("PublishCount", ServiceCounterDataType.class);
        ServiceCounterDataType _republishCount = decoder.decodeSerializable("RepublishCount", ServiceCounterDataType.class);
        ServiceCounterDataType _transferSubscriptionsCount = decoder.decodeSerializable("TransferSubscriptionsCount", ServiceCounterDataType.class);
        ServiceCounterDataType _deleteSubscriptionsCount = decoder.decodeSerializable("DeleteSubscriptionsCount", ServiceCounterDataType.class);
        ServiceCounterDataType _addNodesCount = decoder.decodeSerializable("AddNodesCount", ServiceCounterDataType.class);
        ServiceCounterDataType _addReferencesCount = decoder.decodeSerializable("AddReferencesCount", ServiceCounterDataType.class);
        ServiceCounterDataType _deleteNodesCount = decoder.decodeSerializable("DeleteNodesCount", ServiceCounterDataType.class);
        ServiceCounterDataType _deleteReferencesCount = decoder.decodeSerializable("DeleteReferencesCount", ServiceCounterDataType.class);
        ServiceCounterDataType _browseCount = decoder.decodeSerializable("BrowseCount", ServiceCounterDataType.class);
        ServiceCounterDataType _browseNextCount = decoder.decodeSerializable("BrowseNextCount", ServiceCounterDataType.class);
        ServiceCounterDataType _translateBrowsePathsToNodeIdsCount = decoder.decodeSerializable("TranslateBrowsePathsToNodeIdsCount", ServiceCounterDataType.class);
        ServiceCounterDataType _queryFirstCount = decoder.decodeSerializable("QueryFirstCount", ServiceCounterDataType.class);
        ServiceCounterDataType _queryNextCount = decoder.decodeSerializable("QueryNextCount", ServiceCounterDataType.class);
        ServiceCounterDataType _registerNodesCount = decoder.decodeSerializable("RegisterNodesCount", ServiceCounterDataType.class);
        ServiceCounterDataType _unregisterNodesCount = decoder.decodeSerializable("UnregisterNodesCount", ServiceCounterDataType.class);

        return new SessionDiagnosticsDataType(_sessionId, _sessionName, _clientDescription, _serverUri, _endpointUrl, _localeIds, _actualSessionTimeout, _maxResponseMessageSize, _clientConnectionTime, _clientLastContactTime, _currentSubscriptionsCount, _currentMonitoredItemsCount, _currentPublishRequestsInQueue, _totalRequestCount, _unauthorizedRequestCount, _readCount, _historyReadCount, _writeCount, _historyUpdateCount, _callCount, _createMonitoredItemsCount, _modifyMonitoredItemsCount, _setMonitoringModeCount, _setTriggeringCount, _deleteMonitoredItemsCount, _createSubscriptionCount, _modifySubscriptionCount, _setPublishingModeCount, _publishCount, _republishCount, _transferSubscriptionsCount, _deleteSubscriptionsCount, _addNodesCount, _addReferencesCount, _deleteNodesCount, _deleteReferencesCount, _browseCount, _browseNextCount, _translateBrowsePathsToNodeIdsCount, _queryFirstCount, _queryNextCount, _registerNodesCount, _unregisterNodesCount);
    }

    static {
        DelegateRegistry.registerEncoder(SessionDiagnosticsDataType::encode, SessionDiagnosticsDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SessionDiagnosticsDataType::decode, SessionDiagnosticsDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}

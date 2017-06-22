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

import javax.annotation.Nullable;

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

    @Nullable
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SessionId", _sessionId)
            .add("SessionName", _sessionName)
            .add("ClientDescription", _clientDescription)
            .add("ServerUri", _serverUri)
            .add("EndpointUrl", _endpointUrl)
            .add("LocaleIds", _localeIds)
            .add("ActualSessionTimeout", _actualSessionTimeout)
            .add("MaxResponseMessageSize", _maxResponseMessageSize)
            .add("ClientConnectionTime", _clientConnectionTime)
            .add("ClientLastContactTime", _clientLastContactTime)
            .add("CurrentSubscriptionsCount", _currentSubscriptionsCount)
            .add("CurrentMonitoredItemsCount", _currentMonitoredItemsCount)
            .add("CurrentPublishRequestsInQueue", _currentPublishRequestsInQueue)
            .add("TotalRequestCount", _totalRequestCount)
            .add("UnauthorizedRequestCount", _unauthorizedRequestCount)
            .add("ReadCount", _readCount)
            .add("HistoryReadCount", _historyReadCount)
            .add("WriteCount", _writeCount)
            .add("HistoryUpdateCount", _historyUpdateCount)
            .add("CallCount", _callCount)
            .add("CreateMonitoredItemsCount", _createMonitoredItemsCount)
            .add("ModifyMonitoredItemsCount", _modifyMonitoredItemsCount)
            .add("SetMonitoringModeCount", _setMonitoringModeCount)
            .add("SetTriggeringCount", _setTriggeringCount)
            .add("DeleteMonitoredItemsCount", _deleteMonitoredItemsCount)
            .add("CreateSubscriptionCount", _createSubscriptionCount)
            .add("ModifySubscriptionCount", _modifySubscriptionCount)
            .add("SetPublishingModeCount", _setPublishingModeCount)
            .add("PublishCount", _publishCount)
            .add("RepublishCount", _republishCount)
            .add("TransferSubscriptionsCount", _transferSubscriptionsCount)
            .add("DeleteSubscriptionsCount", _deleteSubscriptionsCount)
            .add("AddNodesCount", _addNodesCount)
            .add("AddReferencesCount", _addReferencesCount)
            .add("DeleteNodesCount", _deleteNodesCount)
            .add("DeleteReferencesCount", _deleteReferencesCount)
            .add("BrowseCount", _browseCount)
            .add("BrowseNextCount", _browseNextCount)
            .add("TranslateBrowsePathsToNodeIdsCount", _translateBrowsePathsToNodeIdsCount)
            .add("QueryFirstCount", _queryFirstCount)
            .add("QueryNextCount", _queryNextCount)
            .add("RegisterNodesCount", _registerNodesCount)
            .add("UnregisterNodesCount", _unregisterNodesCount)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SessionDiagnosticsDataType> {
        @Override
        public SessionDiagnosticsDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _sessionId = reader.readNodeId();
            String _sessionName = reader.readString();
            ApplicationDescription _clientDescription = (ApplicationDescription) context.decode(ApplicationDescription.BinaryEncodingId, reader);
            String _serverUri = reader.readString();
            String _endpointUrl = reader.readString();
            String[] _localeIds = reader.readArray(reader::readString, String.class);
            Double _actualSessionTimeout = reader.readDouble();
            UInteger _maxResponseMessageSize = reader.readUInt32();
            DateTime _clientConnectionTime = reader.readDateTime();
            DateTime _clientLastContactTime = reader.readDateTime();
            UInteger _currentSubscriptionsCount = reader.readUInt32();
            UInteger _currentMonitoredItemsCount = reader.readUInt32();
            UInteger _currentPublishRequestsInQueue = reader.readUInt32();
            ServiceCounterDataType _totalRequestCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            UInteger _unauthorizedRequestCount = reader.readUInt32();
            ServiceCounterDataType _readCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _historyReadCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _writeCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _historyUpdateCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _callCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _createMonitoredItemsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _modifyMonitoredItemsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _setMonitoringModeCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _setTriggeringCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _deleteMonitoredItemsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _createSubscriptionCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _modifySubscriptionCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _setPublishingModeCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _publishCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _republishCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _transferSubscriptionsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _deleteSubscriptionsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _addNodesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _addReferencesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _deleteNodesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _deleteReferencesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _browseCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _browseNextCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _translateBrowsePathsToNodeIdsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _queryFirstCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _queryNextCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _registerNodesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);
            ServiceCounterDataType _unregisterNodesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.BinaryEncodingId, reader);

            return new SessionDiagnosticsDataType(_sessionId, _sessionName, _clientDescription, _serverUri, _endpointUrl, _localeIds, _actualSessionTimeout, _maxResponseMessageSize, _clientConnectionTime, _clientLastContactTime, _currentSubscriptionsCount, _currentMonitoredItemsCount, _currentPublishRequestsInQueue, _totalRequestCount, _unauthorizedRequestCount, _readCount, _historyReadCount, _writeCount, _historyUpdateCount, _callCount, _createMonitoredItemsCount, _modifyMonitoredItemsCount, _setMonitoringModeCount, _setTriggeringCount, _deleteMonitoredItemsCount, _createSubscriptionCount, _modifySubscriptionCount, _setPublishingModeCount, _publishCount, _republishCount, _transferSubscriptionsCount, _deleteSubscriptionsCount, _addNodesCount, _addReferencesCount, _deleteNodesCount, _deleteReferencesCount, _browseCount, _browseNextCount, _translateBrowsePathsToNodeIdsCount, _queryFirstCount, _queryNextCount, _registerNodesCount, _unregisterNodesCount);
        }

        @Override
        public void encode(SerializationContext context, SessionDiagnosticsDataType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._sessionId);
            writer.writeString(value._sessionName);
            context.encode(ApplicationDescription.BinaryEncodingId, value._clientDescription, writer);
            writer.writeString(value._serverUri);
            writer.writeString(value._endpointUrl);
            writer.writeArray(value._localeIds, writer::writeString);
            writer.writeDouble(value._actualSessionTimeout);
            writer.writeUInt32(value._maxResponseMessageSize);
            writer.writeDateTime(value._clientConnectionTime);
            writer.writeDateTime(value._clientLastContactTime);
            writer.writeUInt32(value._currentSubscriptionsCount);
            writer.writeUInt32(value._currentMonitoredItemsCount);
            writer.writeUInt32(value._currentPublishRequestsInQueue);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._totalRequestCount, writer);
            writer.writeUInt32(value._unauthorizedRequestCount);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._readCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._historyReadCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._writeCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._historyUpdateCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._callCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._createMonitoredItemsCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._modifyMonitoredItemsCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._setMonitoringModeCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._setTriggeringCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._deleteMonitoredItemsCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._createSubscriptionCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._modifySubscriptionCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._setPublishingModeCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._publishCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._republishCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._transferSubscriptionsCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._deleteSubscriptionsCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._addNodesCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._addReferencesCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._deleteNodesCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._deleteReferencesCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._browseCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._browseNextCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._translateBrowsePathsToNodeIdsCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._queryFirstCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._queryNextCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._registerNodesCount, writer);
            context.encode(ServiceCounterDataType.BinaryEncodingId, value._unregisterNodesCount, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SessionDiagnosticsDataType> {
        @Override
        public SessionDiagnosticsDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _sessionId = reader.readNodeId("SessionId");
            String _sessionName = reader.readString("SessionName");
            ApplicationDescription _clientDescription = (ApplicationDescription) context.decode(ApplicationDescription.XmlEncodingId, reader);
            String _serverUri = reader.readString("ServerUri");
            String _endpointUrl = reader.readString("EndpointUrl");
            String[] _localeIds = reader.readArray("LocaleIds", reader::readString, String.class);
            Double _actualSessionTimeout = reader.readDouble("ActualSessionTimeout");
            UInteger _maxResponseMessageSize = reader.readUInt32("MaxResponseMessageSize");
            DateTime _clientConnectionTime = reader.readDateTime("ClientConnectionTime");
            DateTime _clientLastContactTime = reader.readDateTime("ClientLastContactTime");
            UInteger _currentSubscriptionsCount = reader.readUInt32("CurrentSubscriptionsCount");
            UInteger _currentMonitoredItemsCount = reader.readUInt32("CurrentMonitoredItemsCount");
            UInteger _currentPublishRequestsInQueue = reader.readUInt32("CurrentPublishRequestsInQueue");
            ServiceCounterDataType _totalRequestCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            UInteger _unauthorizedRequestCount = reader.readUInt32("UnauthorizedRequestCount");
            ServiceCounterDataType _readCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _historyReadCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _writeCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _historyUpdateCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _callCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _createMonitoredItemsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _modifyMonitoredItemsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _setMonitoringModeCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _setTriggeringCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _deleteMonitoredItemsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _createSubscriptionCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _modifySubscriptionCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _setPublishingModeCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _publishCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _republishCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _transferSubscriptionsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _deleteSubscriptionsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _addNodesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _addReferencesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _deleteNodesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _deleteReferencesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _browseCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _browseNextCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _translateBrowsePathsToNodeIdsCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _queryFirstCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _queryNextCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _registerNodesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);
            ServiceCounterDataType _unregisterNodesCount = (ServiceCounterDataType) context.decode(ServiceCounterDataType.XmlEncodingId, reader);

            return new SessionDiagnosticsDataType(_sessionId, _sessionName, _clientDescription, _serverUri, _endpointUrl, _localeIds, _actualSessionTimeout, _maxResponseMessageSize, _clientConnectionTime, _clientLastContactTime, _currentSubscriptionsCount, _currentMonitoredItemsCount, _currentPublishRequestsInQueue, _totalRequestCount, _unauthorizedRequestCount, _readCount, _historyReadCount, _writeCount, _historyUpdateCount, _callCount, _createMonitoredItemsCount, _modifyMonitoredItemsCount, _setMonitoringModeCount, _setTriggeringCount, _deleteMonitoredItemsCount, _createSubscriptionCount, _modifySubscriptionCount, _setPublishingModeCount, _publishCount, _republishCount, _transferSubscriptionsCount, _deleteSubscriptionsCount, _addNodesCount, _addReferencesCount, _deleteNodesCount, _deleteReferencesCount, _browseCount, _browseNextCount, _translateBrowsePathsToNodeIdsCount, _queryFirstCount, _queryNextCount, _registerNodesCount, _unregisterNodesCount);
        }

        @Override
        public void encode(SerializationContext context, SessionDiagnosticsDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("SessionId", encodable._sessionId);
            writer.writeString("SessionName", encodable._sessionName);
            context.encode(ApplicationDescription.XmlEncodingId, encodable._clientDescription, writer);
            writer.writeString("ServerUri", encodable._serverUri);
            writer.writeString("EndpointUrl", encodable._endpointUrl);
            writer.writeArray("LocaleIds", encodable._localeIds, writer::writeString);
            writer.writeDouble("ActualSessionTimeout", encodable._actualSessionTimeout);
            writer.writeUInt32("MaxResponseMessageSize", encodable._maxResponseMessageSize);
            writer.writeDateTime("ClientConnectionTime", encodable._clientConnectionTime);
            writer.writeDateTime("ClientLastContactTime", encodable._clientLastContactTime);
            writer.writeUInt32("CurrentSubscriptionsCount", encodable._currentSubscriptionsCount);
            writer.writeUInt32("CurrentMonitoredItemsCount", encodable._currentMonitoredItemsCount);
            writer.writeUInt32("CurrentPublishRequestsInQueue", encodable._currentPublishRequestsInQueue);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._totalRequestCount, writer);
            writer.writeUInt32("UnauthorizedRequestCount", encodable._unauthorizedRequestCount);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._readCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._historyReadCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._writeCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._historyUpdateCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._callCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._createMonitoredItemsCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._modifyMonitoredItemsCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._setMonitoringModeCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._setTriggeringCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._deleteMonitoredItemsCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._createSubscriptionCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._modifySubscriptionCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._setPublishingModeCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._publishCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._republishCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._transferSubscriptionsCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._deleteSubscriptionsCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._addNodesCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._addReferencesCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._deleteNodesCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._deleteReferencesCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._browseCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._browseNextCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._translateBrowsePathsToNodeIdsCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._queryFirstCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._queryNextCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._registerNodesCount, writer);
            context.encode(ServiceCounterDataType.XmlEncodingId, encodable._unregisterNodesCount, writer);
        }
    }

}

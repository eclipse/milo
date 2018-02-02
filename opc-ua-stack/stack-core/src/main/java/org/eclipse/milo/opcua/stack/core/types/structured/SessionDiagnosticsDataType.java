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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class SessionDiagnosticsDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SessionDiagnosticsDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SessionDiagnosticsDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SessionDiagnosticsDataType_Encoding_DefaultXml;

    protected final NodeId sessionId;
    protected final String sessionName;
    protected final ApplicationDescription clientDescription;
    protected final String serverUri;
    protected final String endpointUrl;
    protected final String[] localeIds;
    protected final Double actualSessionTimeout;
    protected final UInteger maxResponseMessageSize;
    protected final DateTime clientConnectionTime;
    protected final DateTime clientLastContactTime;
    protected final UInteger currentSubscriptionsCount;
    protected final UInteger currentMonitoredItemsCount;
    protected final UInteger currentPublishRequestsInQueue;
    protected final ServiceCounterDataType totalRequestCount;
    protected final UInteger unauthorizedRequestCount;
    protected final ServiceCounterDataType readCount;
    protected final ServiceCounterDataType historyReadCount;
    protected final ServiceCounterDataType writeCount;
    protected final ServiceCounterDataType historyUpdateCount;
    protected final ServiceCounterDataType callCount;
    protected final ServiceCounterDataType createMonitoredItemsCount;
    protected final ServiceCounterDataType modifyMonitoredItemsCount;
    protected final ServiceCounterDataType setMonitoringModeCount;
    protected final ServiceCounterDataType setTriggeringCount;
    protected final ServiceCounterDataType deleteMonitoredItemsCount;
    protected final ServiceCounterDataType createSubscriptionCount;
    protected final ServiceCounterDataType modifySubscriptionCount;
    protected final ServiceCounterDataType setPublishingModeCount;
    protected final ServiceCounterDataType publishCount;
    protected final ServiceCounterDataType republishCount;
    protected final ServiceCounterDataType transferSubscriptionsCount;
    protected final ServiceCounterDataType deleteSubscriptionsCount;
    protected final ServiceCounterDataType addNodesCount;
    protected final ServiceCounterDataType addReferencesCount;
    protected final ServiceCounterDataType deleteNodesCount;
    protected final ServiceCounterDataType deleteReferencesCount;
    protected final ServiceCounterDataType browseCount;
    protected final ServiceCounterDataType browseNextCount;
    protected final ServiceCounterDataType translateBrowsePathsToNodeIdsCount;
    protected final ServiceCounterDataType queryFirstCount;
    protected final ServiceCounterDataType queryNextCount;
    protected final ServiceCounterDataType registerNodesCount;
    protected final ServiceCounterDataType unregisterNodesCount;

    public SessionDiagnosticsDataType() {
        this.sessionId = null;
        this.sessionName = null;
        this.clientDescription = null;
        this.serverUri = null;
        this.endpointUrl = null;
        this.localeIds = null;
        this.actualSessionTimeout = null;
        this.maxResponseMessageSize = null;
        this.clientConnectionTime = null;
        this.clientLastContactTime = null;
        this.currentSubscriptionsCount = null;
        this.currentMonitoredItemsCount = null;
        this.currentPublishRequestsInQueue = null;
        this.totalRequestCount = null;
        this.unauthorizedRequestCount = null;
        this.readCount = null;
        this.historyReadCount = null;
        this.writeCount = null;
        this.historyUpdateCount = null;
        this.callCount = null;
        this.createMonitoredItemsCount = null;
        this.modifyMonitoredItemsCount = null;
        this.setMonitoringModeCount = null;
        this.setTriggeringCount = null;
        this.deleteMonitoredItemsCount = null;
        this.createSubscriptionCount = null;
        this.modifySubscriptionCount = null;
        this.setPublishingModeCount = null;
        this.publishCount = null;
        this.republishCount = null;
        this.transferSubscriptionsCount = null;
        this.deleteSubscriptionsCount = null;
        this.addNodesCount = null;
        this.addReferencesCount = null;
        this.deleteNodesCount = null;
        this.deleteReferencesCount = null;
        this.browseCount = null;
        this.browseNextCount = null;
        this.translateBrowsePathsToNodeIdsCount = null;
        this.queryFirstCount = null;
        this.queryNextCount = null;
        this.registerNodesCount = null;
        this.unregisterNodesCount = null;
    }

    public SessionDiagnosticsDataType(NodeId sessionId, String sessionName, ApplicationDescription clientDescription, String serverUri, String endpointUrl, String[] localeIds, Double actualSessionTimeout, UInteger maxResponseMessageSize, DateTime clientConnectionTime, DateTime clientLastContactTime, UInteger currentSubscriptionsCount, UInteger currentMonitoredItemsCount, UInteger currentPublishRequestsInQueue, ServiceCounterDataType totalRequestCount, UInteger unauthorizedRequestCount, ServiceCounterDataType readCount, ServiceCounterDataType historyReadCount, ServiceCounterDataType writeCount, ServiceCounterDataType historyUpdateCount, ServiceCounterDataType callCount, ServiceCounterDataType createMonitoredItemsCount, ServiceCounterDataType modifyMonitoredItemsCount, ServiceCounterDataType setMonitoringModeCount, ServiceCounterDataType setTriggeringCount, ServiceCounterDataType deleteMonitoredItemsCount, ServiceCounterDataType createSubscriptionCount, ServiceCounterDataType modifySubscriptionCount, ServiceCounterDataType setPublishingModeCount, ServiceCounterDataType publishCount, ServiceCounterDataType republishCount, ServiceCounterDataType transferSubscriptionsCount, ServiceCounterDataType deleteSubscriptionsCount, ServiceCounterDataType addNodesCount, ServiceCounterDataType addReferencesCount, ServiceCounterDataType deleteNodesCount, ServiceCounterDataType deleteReferencesCount, ServiceCounterDataType browseCount, ServiceCounterDataType browseNextCount, ServiceCounterDataType translateBrowsePathsToNodeIdsCount, ServiceCounterDataType queryFirstCount, ServiceCounterDataType queryNextCount, ServiceCounterDataType registerNodesCount, ServiceCounterDataType unregisterNodesCount) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.clientDescription = clientDescription;
        this.serverUri = serverUri;
        this.endpointUrl = endpointUrl;
        this.localeIds = localeIds;
        this.actualSessionTimeout = actualSessionTimeout;
        this.maxResponseMessageSize = maxResponseMessageSize;
        this.clientConnectionTime = clientConnectionTime;
        this.clientLastContactTime = clientLastContactTime;
        this.currentSubscriptionsCount = currentSubscriptionsCount;
        this.currentMonitoredItemsCount = currentMonitoredItemsCount;
        this.currentPublishRequestsInQueue = currentPublishRequestsInQueue;
        this.totalRequestCount = totalRequestCount;
        this.unauthorizedRequestCount = unauthorizedRequestCount;
        this.readCount = readCount;
        this.historyReadCount = historyReadCount;
        this.writeCount = writeCount;
        this.historyUpdateCount = historyUpdateCount;
        this.callCount = callCount;
        this.createMonitoredItemsCount = createMonitoredItemsCount;
        this.modifyMonitoredItemsCount = modifyMonitoredItemsCount;
        this.setMonitoringModeCount = setMonitoringModeCount;
        this.setTriggeringCount = setTriggeringCount;
        this.deleteMonitoredItemsCount = deleteMonitoredItemsCount;
        this.createSubscriptionCount = createSubscriptionCount;
        this.modifySubscriptionCount = modifySubscriptionCount;
        this.setPublishingModeCount = setPublishingModeCount;
        this.publishCount = publishCount;
        this.republishCount = republishCount;
        this.transferSubscriptionsCount = transferSubscriptionsCount;
        this.deleteSubscriptionsCount = deleteSubscriptionsCount;
        this.addNodesCount = addNodesCount;
        this.addReferencesCount = addReferencesCount;
        this.deleteNodesCount = deleteNodesCount;
        this.deleteReferencesCount = deleteReferencesCount;
        this.browseCount = browseCount;
        this.browseNextCount = browseNextCount;
        this.translateBrowsePathsToNodeIdsCount = translateBrowsePathsToNodeIdsCount;
        this.queryFirstCount = queryFirstCount;
        this.queryNextCount = queryNextCount;
        this.registerNodesCount = registerNodesCount;
        this.unregisterNodesCount = unregisterNodesCount;
    }

    public NodeId getSessionId() { return sessionId; }

    public String getSessionName() { return sessionName; }

    public ApplicationDescription getClientDescription() { return clientDescription; }

    public String getServerUri() { return serverUri; }

    public String getEndpointUrl() { return endpointUrl; }

    @Nullable
    public String[] getLocaleIds() { return localeIds; }

    public Double getActualSessionTimeout() { return actualSessionTimeout; }

    public UInteger getMaxResponseMessageSize() { return maxResponseMessageSize; }

    public DateTime getClientConnectionTime() { return clientConnectionTime; }

    public DateTime getClientLastContactTime() { return clientLastContactTime; }

    public UInteger getCurrentSubscriptionsCount() { return currentSubscriptionsCount; }

    public UInteger getCurrentMonitoredItemsCount() { return currentMonitoredItemsCount; }

    public UInteger getCurrentPublishRequestsInQueue() { return currentPublishRequestsInQueue; }

    public ServiceCounterDataType getTotalRequestCount() { return totalRequestCount; }

    public UInteger getUnauthorizedRequestCount() { return unauthorizedRequestCount; }

    public ServiceCounterDataType getReadCount() { return readCount; }

    public ServiceCounterDataType getHistoryReadCount() { return historyReadCount; }

    public ServiceCounterDataType getWriteCount() { return writeCount; }

    public ServiceCounterDataType getHistoryUpdateCount() { return historyUpdateCount; }

    public ServiceCounterDataType getCallCount() { return callCount; }

    public ServiceCounterDataType getCreateMonitoredItemsCount() { return createMonitoredItemsCount; }

    public ServiceCounterDataType getModifyMonitoredItemsCount() { return modifyMonitoredItemsCount; }

    public ServiceCounterDataType getSetMonitoringModeCount() { return setMonitoringModeCount; }

    public ServiceCounterDataType getSetTriggeringCount() { return setTriggeringCount; }

    public ServiceCounterDataType getDeleteMonitoredItemsCount() { return deleteMonitoredItemsCount; }

    public ServiceCounterDataType getCreateSubscriptionCount() { return createSubscriptionCount; }

    public ServiceCounterDataType getModifySubscriptionCount() { return modifySubscriptionCount; }

    public ServiceCounterDataType getSetPublishingModeCount() { return setPublishingModeCount; }

    public ServiceCounterDataType getPublishCount() { return publishCount; }

    public ServiceCounterDataType getRepublishCount() { return republishCount; }

    public ServiceCounterDataType getTransferSubscriptionsCount() { return transferSubscriptionsCount; }

    public ServiceCounterDataType getDeleteSubscriptionsCount() { return deleteSubscriptionsCount; }

    public ServiceCounterDataType getAddNodesCount() { return addNodesCount; }

    public ServiceCounterDataType getAddReferencesCount() { return addReferencesCount; }

    public ServiceCounterDataType getDeleteNodesCount() { return deleteNodesCount; }

    public ServiceCounterDataType getDeleteReferencesCount() { return deleteReferencesCount; }

    public ServiceCounterDataType getBrowseCount() { return browseCount; }

    public ServiceCounterDataType getBrowseNextCount() { return browseNextCount; }

    public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() { return translateBrowsePathsToNodeIdsCount; }

    public ServiceCounterDataType getQueryFirstCount() { return queryFirstCount; }

    public ServiceCounterDataType getQueryNextCount() { return queryNextCount; }

    public ServiceCounterDataType getRegisterNodesCount() { return registerNodesCount; }

    public ServiceCounterDataType getUnregisterNodesCount() { return unregisterNodesCount; }

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
            .add("SessionName", sessionName)
            .add("ClientDescription", clientDescription)
            .add("ServerUri", serverUri)
            .add("EndpointUrl", endpointUrl)
            .add("LocaleIds", localeIds)
            .add("ActualSessionTimeout", actualSessionTimeout)
            .add("MaxResponseMessageSize", maxResponseMessageSize)
            .add("ClientConnectionTime", clientConnectionTime)
            .add("ClientLastContactTime", clientLastContactTime)
            .add("CurrentSubscriptionsCount", currentSubscriptionsCount)
            .add("CurrentMonitoredItemsCount", currentMonitoredItemsCount)
            .add("CurrentPublishRequestsInQueue", currentPublishRequestsInQueue)
            .add("TotalRequestCount", totalRequestCount)
            .add("UnauthorizedRequestCount", unauthorizedRequestCount)
            .add("ReadCount", readCount)
            .add("HistoryReadCount", historyReadCount)
            .add("WriteCount", writeCount)
            .add("HistoryUpdateCount", historyUpdateCount)
            .add("CallCount", callCount)
            .add("CreateMonitoredItemsCount", createMonitoredItemsCount)
            .add("ModifyMonitoredItemsCount", modifyMonitoredItemsCount)
            .add("SetMonitoringModeCount", setMonitoringModeCount)
            .add("SetTriggeringCount", setTriggeringCount)
            .add("DeleteMonitoredItemsCount", deleteMonitoredItemsCount)
            .add("CreateSubscriptionCount", createSubscriptionCount)
            .add("ModifySubscriptionCount", modifySubscriptionCount)
            .add("SetPublishingModeCount", setPublishingModeCount)
            .add("PublishCount", publishCount)
            .add("RepublishCount", republishCount)
            .add("TransferSubscriptionsCount", transferSubscriptionsCount)
            .add("DeleteSubscriptionsCount", deleteSubscriptionsCount)
            .add("AddNodesCount", addNodesCount)
            .add("AddReferencesCount", addReferencesCount)
            .add("DeleteNodesCount", deleteNodesCount)
            .add("DeleteReferencesCount", deleteReferencesCount)
            .add("BrowseCount", browseCount)
            .add("BrowseNextCount", browseNextCount)
            .add("TranslateBrowsePathsToNodeIdsCount", translateBrowsePathsToNodeIdsCount)
            .add("QueryFirstCount", queryFirstCount)
            .add("QueryNextCount", queryNextCount)
            .add("RegisterNodesCount", registerNodesCount)
            .add("UnregisterNodesCount", unregisterNodesCount)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SessionDiagnosticsDataType> {

        @Override
        public Class<SessionDiagnosticsDataType> getType() {
            return SessionDiagnosticsDataType.class;
        }

        @Override
        public SessionDiagnosticsDataType decode(UaDecoder decoder) throws UaSerializationException {
            NodeId sessionId = decoder.readNodeId("SessionId");
            String sessionName = decoder.readString("SessionName");
            ApplicationDescription clientDescription = (ApplicationDescription) decoder.readBuiltinStruct("ClientDescription", ApplicationDescription.class);
            String serverUri = decoder.readString("ServerUri");
            String endpointUrl = decoder.readString("EndpointUrl");
            String[] localeIds = decoder.readArray("LocaleIds", decoder::readString, String.class);
            Double actualSessionTimeout = decoder.readDouble("ActualSessionTimeout");
            UInteger maxResponseMessageSize = decoder.readUInt32("MaxResponseMessageSize");
            DateTime clientConnectionTime = decoder.readDateTime("ClientConnectionTime");
            DateTime clientLastContactTime = decoder.readDateTime("ClientLastContactTime");
            UInteger currentSubscriptionsCount = decoder.readUInt32("CurrentSubscriptionsCount");
            UInteger currentMonitoredItemsCount = decoder.readUInt32("CurrentMonitoredItemsCount");
            UInteger currentPublishRequestsInQueue = decoder.readUInt32("CurrentPublishRequestsInQueue");
            ServiceCounterDataType totalRequestCount = (ServiceCounterDataType) decoder.readBuiltinStruct("TotalRequestCount", ServiceCounterDataType.class);
            UInteger unauthorizedRequestCount = decoder.readUInt32("UnauthorizedRequestCount");
            ServiceCounterDataType readCount = (ServiceCounterDataType) decoder.readBuiltinStruct("ReadCount", ServiceCounterDataType.class);
            ServiceCounterDataType historyReadCount = (ServiceCounterDataType) decoder.readBuiltinStruct("HistoryReadCount", ServiceCounterDataType.class);
            ServiceCounterDataType writeCount = (ServiceCounterDataType) decoder.readBuiltinStruct("WriteCount", ServiceCounterDataType.class);
            ServiceCounterDataType historyUpdateCount = (ServiceCounterDataType) decoder.readBuiltinStruct("HistoryUpdateCount", ServiceCounterDataType.class);
            ServiceCounterDataType callCount = (ServiceCounterDataType) decoder.readBuiltinStruct("CallCount", ServiceCounterDataType.class);
            ServiceCounterDataType createMonitoredItemsCount = (ServiceCounterDataType) decoder.readBuiltinStruct("CreateMonitoredItemsCount", ServiceCounterDataType.class);
            ServiceCounterDataType modifyMonitoredItemsCount = (ServiceCounterDataType) decoder.readBuiltinStruct("ModifyMonitoredItemsCount", ServiceCounterDataType.class);
            ServiceCounterDataType setMonitoringModeCount = (ServiceCounterDataType) decoder.readBuiltinStruct("SetMonitoringModeCount", ServiceCounterDataType.class);
            ServiceCounterDataType setTriggeringCount = (ServiceCounterDataType) decoder.readBuiltinStruct("SetTriggeringCount", ServiceCounterDataType.class);
            ServiceCounterDataType deleteMonitoredItemsCount = (ServiceCounterDataType) decoder.readBuiltinStruct("DeleteMonitoredItemsCount", ServiceCounterDataType.class);
            ServiceCounterDataType createSubscriptionCount = (ServiceCounterDataType) decoder.readBuiltinStruct("CreateSubscriptionCount", ServiceCounterDataType.class);
            ServiceCounterDataType modifySubscriptionCount = (ServiceCounterDataType) decoder.readBuiltinStruct("ModifySubscriptionCount", ServiceCounterDataType.class);
            ServiceCounterDataType setPublishingModeCount = (ServiceCounterDataType) decoder.readBuiltinStruct("SetPublishingModeCount", ServiceCounterDataType.class);
            ServiceCounterDataType publishCount = (ServiceCounterDataType) decoder.readBuiltinStruct("PublishCount", ServiceCounterDataType.class);
            ServiceCounterDataType republishCount = (ServiceCounterDataType) decoder.readBuiltinStruct("RepublishCount", ServiceCounterDataType.class);
            ServiceCounterDataType transferSubscriptionsCount = (ServiceCounterDataType) decoder.readBuiltinStruct("TransferSubscriptionsCount", ServiceCounterDataType.class);
            ServiceCounterDataType deleteSubscriptionsCount = (ServiceCounterDataType) decoder.readBuiltinStruct("DeleteSubscriptionsCount", ServiceCounterDataType.class);
            ServiceCounterDataType addNodesCount = (ServiceCounterDataType) decoder.readBuiltinStruct("AddNodesCount", ServiceCounterDataType.class);
            ServiceCounterDataType addReferencesCount = (ServiceCounterDataType) decoder.readBuiltinStruct("AddReferencesCount", ServiceCounterDataType.class);
            ServiceCounterDataType deleteNodesCount = (ServiceCounterDataType) decoder.readBuiltinStruct("DeleteNodesCount", ServiceCounterDataType.class);
            ServiceCounterDataType deleteReferencesCount = (ServiceCounterDataType) decoder.readBuiltinStruct("DeleteReferencesCount", ServiceCounterDataType.class);
            ServiceCounterDataType browseCount = (ServiceCounterDataType) decoder.readBuiltinStruct("BrowseCount", ServiceCounterDataType.class);
            ServiceCounterDataType browseNextCount = (ServiceCounterDataType) decoder.readBuiltinStruct("BrowseNextCount", ServiceCounterDataType.class);
            ServiceCounterDataType translateBrowsePathsToNodeIdsCount = (ServiceCounterDataType) decoder.readBuiltinStruct("TranslateBrowsePathsToNodeIdsCount", ServiceCounterDataType.class);
            ServiceCounterDataType queryFirstCount = (ServiceCounterDataType) decoder.readBuiltinStruct("QueryFirstCount", ServiceCounterDataType.class);
            ServiceCounterDataType queryNextCount = (ServiceCounterDataType) decoder.readBuiltinStruct("QueryNextCount", ServiceCounterDataType.class);
            ServiceCounterDataType registerNodesCount = (ServiceCounterDataType) decoder.readBuiltinStruct("RegisterNodesCount", ServiceCounterDataType.class);
            ServiceCounterDataType unregisterNodesCount = (ServiceCounterDataType) decoder.readBuiltinStruct("UnregisterNodesCount", ServiceCounterDataType.class);

            return new SessionDiagnosticsDataType(sessionId, sessionName, clientDescription, serverUri, endpointUrl, localeIds, actualSessionTimeout, maxResponseMessageSize, clientConnectionTime, clientLastContactTime, currentSubscriptionsCount, currentMonitoredItemsCount, currentPublishRequestsInQueue, totalRequestCount, unauthorizedRequestCount, readCount, historyReadCount, writeCount, historyUpdateCount, callCount, createMonitoredItemsCount, modifyMonitoredItemsCount, setMonitoringModeCount, setTriggeringCount, deleteMonitoredItemsCount, createSubscriptionCount, modifySubscriptionCount, setPublishingModeCount, publishCount, republishCount, transferSubscriptionsCount, deleteSubscriptionsCount, addNodesCount, addReferencesCount, deleteNodesCount, deleteReferencesCount, browseCount, browseNextCount, translateBrowsePathsToNodeIdsCount, queryFirstCount, queryNextCount, registerNodesCount, unregisterNodesCount);
        }

        @Override
        public void encode(SessionDiagnosticsDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("SessionId", value.sessionId);
            encoder.writeString("SessionName", value.sessionName);
            encoder.writeBuiltinStruct("ClientDescription", value.clientDescription, ApplicationDescription.class);
            encoder.writeString("ServerUri", value.serverUri);
            encoder.writeString("EndpointUrl", value.endpointUrl);
            encoder.writeArray("LocaleIds", value.localeIds, encoder::writeString);
            encoder.writeDouble("ActualSessionTimeout", value.actualSessionTimeout);
            encoder.writeUInt32("MaxResponseMessageSize", value.maxResponseMessageSize);
            encoder.writeDateTime("ClientConnectionTime", value.clientConnectionTime);
            encoder.writeDateTime("ClientLastContactTime", value.clientLastContactTime);
            encoder.writeUInt32("CurrentSubscriptionsCount", value.currentSubscriptionsCount);
            encoder.writeUInt32("CurrentMonitoredItemsCount", value.currentMonitoredItemsCount);
            encoder.writeUInt32("CurrentPublishRequestsInQueue", value.currentPublishRequestsInQueue);
            encoder.writeBuiltinStruct("TotalRequestCount", value.totalRequestCount, ServiceCounterDataType.class);
            encoder.writeUInt32("UnauthorizedRequestCount", value.unauthorizedRequestCount);
            encoder.writeBuiltinStruct("ReadCount", value.readCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("HistoryReadCount", value.historyReadCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("WriteCount", value.writeCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("HistoryUpdateCount", value.historyUpdateCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("CallCount", value.callCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("CreateMonitoredItemsCount", value.createMonitoredItemsCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("ModifyMonitoredItemsCount", value.modifyMonitoredItemsCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("SetMonitoringModeCount", value.setMonitoringModeCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("SetTriggeringCount", value.setTriggeringCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("DeleteMonitoredItemsCount", value.deleteMonitoredItemsCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("CreateSubscriptionCount", value.createSubscriptionCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("ModifySubscriptionCount", value.modifySubscriptionCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("SetPublishingModeCount", value.setPublishingModeCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("PublishCount", value.publishCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("RepublishCount", value.republishCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("TransferSubscriptionsCount", value.transferSubscriptionsCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("DeleteSubscriptionsCount", value.deleteSubscriptionsCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("AddNodesCount", value.addNodesCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("AddReferencesCount", value.addReferencesCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("DeleteNodesCount", value.deleteNodesCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("DeleteReferencesCount", value.deleteReferencesCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("BrowseCount", value.browseCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("BrowseNextCount", value.browseNextCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("TranslateBrowsePathsToNodeIdsCount", value.translateBrowsePathsToNodeIdsCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("QueryFirstCount", value.queryFirstCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("QueryNextCount", value.queryNextCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("RegisterNodesCount", value.registerNodesCount, ServiceCounterDataType.class);
            encoder.writeBuiltinStruct("UnregisterNodesCount", value.unregisterNodesCount, ServiceCounterDataType.class);
        }
    }

}

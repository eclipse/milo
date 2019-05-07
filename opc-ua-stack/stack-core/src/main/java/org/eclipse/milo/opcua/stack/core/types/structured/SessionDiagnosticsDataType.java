/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SessionDiagnosticsDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=865");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=866");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=867");

    private final NodeId sessionId;

    private final String sessionName;

    private final ApplicationDescription clientDescription;

    private final String serverUri;

    private final String endpointUrl;

    private final String[] localeIds;

    private final Double actualSessionTimeout;

    private final UInteger maxResponseMessageSize;

    private final DateTime clientConnectionTime;

    private final DateTime clientLastContactTime;

    private final UInteger currentSubscriptionsCount;

    private final UInteger currentMonitoredItemsCount;

    private final UInteger currentPublishRequestsInQueue;

    private final ServiceCounterDataType totalRequestCount;

    private final UInteger unauthorizedRequestCount;

    private final ServiceCounterDataType readCount;

    private final ServiceCounterDataType historyReadCount;

    private final ServiceCounterDataType writeCount;

    private final ServiceCounterDataType historyUpdateCount;

    private final ServiceCounterDataType callCount;

    private final ServiceCounterDataType createMonitoredItemsCount;

    private final ServiceCounterDataType modifyMonitoredItemsCount;

    private final ServiceCounterDataType setMonitoringModeCount;

    private final ServiceCounterDataType setTriggeringCount;

    private final ServiceCounterDataType deleteMonitoredItemsCount;

    private final ServiceCounterDataType createSubscriptionCount;

    private final ServiceCounterDataType modifySubscriptionCount;

    private final ServiceCounterDataType setPublishingModeCount;

    private final ServiceCounterDataType publishCount;

    private final ServiceCounterDataType republishCount;

    private final ServiceCounterDataType transferSubscriptionsCount;

    private final ServiceCounterDataType deleteSubscriptionsCount;

    private final ServiceCounterDataType addNodesCount;

    private final ServiceCounterDataType addReferencesCount;

    private final ServiceCounterDataType deleteNodesCount;

    private final ServiceCounterDataType deleteReferencesCount;

    private final ServiceCounterDataType browseCount;

    private final ServiceCounterDataType browseNextCount;

    private final ServiceCounterDataType translateBrowsePathsToNodeIdsCount;

    private final ServiceCounterDataType queryFirstCount;

    private final ServiceCounterDataType queryNextCount;

    private final ServiceCounterDataType registerNodesCount;

    private final ServiceCounterDataType unregisterNodesCount;

    public SessionDiagnosticsDataType(NodeId sessionId, String sessionName,
                                      ApplicationDescription clientDescription, String serverUri, String endpointUrl,
                                      String[] localeIds, Double actualSessionTimeout, UInteger maxResponseMessageSize,
                                      DateTime clientConnectionTime, DateTime clientLastContactTime,
                                      UInteger currentSubscriptionsCount, UInteger currentMonitoredItemsCount,
                                      UInteger currentPublishRequestsInQueue, ServiceCounterDataType totalRequestCount,
                                      UInteger unauthorizedRequestCount, ServiceCounterDataType readCount,
                                      ServiceCounterDataType historyReadCount, ServiceCounterDataType writeCount,
                                      ServiceCounterDataType historyUpdateCount, ServiceCounterDataType callCount,
                                      ServiceCounterDataType createMonitoredItemsCount,
                                      ServiceCounterDataType modifyMonitoredItemsCount,
                                      ServiceCounterDataType setMonitoringModeCount, ServiceCounterDataType setTriggeringCount,
                                      ServiceCounterDataType deleteMonitoredItemsCount,
                                      ServiceCounterDataType createSubscriptionCount,
                                      ServiceCounterDataType modifySubscriptionCount, ServiceCounterDataType setPublishingModeCount,
                                      ServiceCounterDataType publishCount, ServiceCounterDataType republishCount,
                                      ServiceCounterDataType transferSubscriptionsCount,
                                      ServiceCounterDataType deleteSubscriptionsCount, ServiceCounterDataType addNodesCount,
                                      ServiceCounterDataType addReferencesCount, ServiceCounterDataType deleteNodesCount,
                                      ServiceCounterDataType deleteReferencesCount, ServiceCounterDataType browseCount,
                                      ServiceCounterDataType browseNextCount,
                                      ServiceCounterDataType translateBrowsePathsToNodeIdsCount,
                                      ServiceCounterDataType queryFirstCount, ServiceCounterDataType queryNextCount,
                                      ServiceCounterDataType registerNodesCount, ServiceCounterDataType unregisterNodesCount) {
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

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public NodeId getSessionId() {
        return sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public ApplicationDescription getClientDescription() {
        return clientDescription;
    }

    public String getServerUri() {
        return serverUri;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public String[] getLocaleIds() {
        return localeIds;
    }

    public Double getActualSessionTimeout() {
        return actualSessionTimeout;
    }

    public UInteger getMaxResponseMessageSize() {
        return maxResponseMessageSize;
    }

    public DateTime getClientConnectionTime() {
        return clientConnectionTime;
    }

    public DateTime getClientLastContactTime() {
        return clientLastContactTime;
    }

    public UInteger getCurrentSubscriptionsCount() {
        return currentSubscriptionsCount;
    }

    public UInteger getCurrentMonitoredItemsCount() {
        return currentMonitoredItemsCount;
    }

    public UInteger getCurrentPublishRequestsInQueue() {
        return currentPublishRequestsInQueue;
    }

    public ServiceCounterDataType getTotalRequestCount() {
        return totalRequestCount;
    }

    public UInteger getUnauthorizedRequestCount() {
        return unauthorizedRequestCount;
    }

    public ServiceCounterDataType getReadCount() {
        return readCount;
    }

    public ServiceCounterDataType getHistoryReadCount() {
        return historyReadCount;
    }

    public ServiceCounterDataType getWriteCount() {
        return writeCount;
    }

    public ServiceCounterDataType getHistoryUpdateCount() {
        return historyUpdateCount;
    }

    public ServiceCounterDataType getCallCount() {
        return callCount;
    }

    public ServiceCounterDataType getCreateMonitoredItemsCount() {
        return createMonitoredItemsCount;
    }

    public ServiceCounterDataType getModifyMonitoredItemsCount() {
        return modifyMonitoredItemsCount;
    }

    public ServiceCounterDataType getSetMonitoringModeCount() {
        return setMonitoringModeCount;
    }

    public ServiceCounterDataType getSetTriggeringCount() {
        return setTriggeringCount;
    }

    public ServiceCounterDataType getDeleteMonitoredItemsCount() {
        return deleteMonitoredItemsCount;
    }

    public ServiceCounterDataType getCreateSubscriptionCount() {
        return createSubscriptionCount;
    }

    public ServiceCounterDataType getModifySubscriptionCount() {
        return modifySubscriptionCount;
    }

    public ServiceCounterDataType getSetPublishingModeCount() {
        return setPublishingModeCount;
    }

    public ServiceCounterDataType getPublishCount() {
        return publishCount;
    }

    public ServiceCounterDataType getRepublishCount() {
        return republishCount;
    }

    public ServiceCounterDataType getTransferSubscriptionsCount() {
        return transferSubscriptionsCount;
    }

    public ServiceCounterDataType getDeleteSubscriptionsCount() {
        return deleteSubscriptionsCount;
    }

    public ServiceCounterDataType getAddNodesCount() {
        return addNodesCount;
    }

    public ServiceCounterDataType getAddReferencesCount() {
        return addReferencesCount;
    }

    public ServiceCounterDataType getDeleteNodesCount() {
        return deleteNodesCount;
    }

    public ServiceCounterDataType getDeleteReferencesCount() {
        return deleteReferencesCount;
    }

    public ServiceCounterDataType getBrowseCount() {
        return browseCount;
    }

    public ServiceCounterDataType getBrowseNextCount() {
        return browseNextCount;
    }

    public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() {
        return translateBrowsePathsToNodeIdsCount;
    }

    public ServiceCounterDataType getQueryFirstCount() {
        return queryFirstCount;
    }

    public ServiceCounterDataType getQueryNextCount() {
        return queryNextCount;
    }

    public ServiceCounterDataType getRegisterNodesCount() {
        return registerNodesCount;
    }

    public ServiceCounterDataType getUnregisterNodesCount() {
        return unregisterNodesCount;
    }

    public static final class Codec extends GenericDataTypeCodec<SessionDiagnosticsDataType> {
        @Override
        public Class<SessionDiagnosticsDataType> getType() {
            return SessionDiagnosticsDataType.class;
        }

        @Override
        public SessionDiagnosticsDataType decode(SerializationContext context, UaDecoder decoder) {
            NodeId sessionId = decoder.readNodeId("SessionId");
            String sessionName = decoder.readString("SessionName");
            ApplicationDescription clientDescription = (ApplicationDescription) decoder.readStruct("ClientDescription", ApplicationDescription.TYPE_ID);
            String serverUri = decoder.readString("ServerUri");
            String endpointUrl = decoder.readString("EndpointUrl");
            String[] localeIds = decoder.readStringArray("LocaleIds");
            Double actualSessionTimeout = decoder.readDouble("ActualSessionTimeout");
            UInteger maxResponseMessageSize = decoder.readUInt32("MaxResponseMessageSize");
            DateTime clientConnectionTime = decoder.readDateTime("ClientConnectionTime");
            DateTime clientLastContactTime = decoder.readDateTime("ClientLastContactTime");
            UInteger currentSubscriptionsCount = decoder.readUInt32("CurrentSubscriptionsCount");
            UInteger currentMonitoredItemsCount = decoder.readUInt32("CurrentMonitoredItemsCount");
            UInteger currentPublishRequestsInQueue = decoder.readUInt32("CurrentPublishRequestsInQueue");
            ServiceCounterDataType totalRequestCount = (ServiceCounterDataType) decoder.readStruct("TotalRequestCount", ServiceCounterDataType.TYPE_ID);
            UInteger unauthorizedRequestCount = decoder.readUInt32("UnauthorizedRequestCount");
            ServiceCounterDataType readCount = (ServiceCounterDataType) decoder.readStruct("ReadCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType historyReadCount = (ServiceCounterDataType) decoder.readStruct("HistoryReadCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType writeCount = (ServiceCounterDataType) decoder.readStruct("WriteCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType historyUpdateCount = (ServiceCounterDataType) decoder.readStruct("HistoryUpdateCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType callCount = (ServiceCounterDataType) decoder.readStruct("CallCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType createMonitoredItemsCount = (ServiceCounterDataType) decoder.readStruct("CreateMonitoredItemsCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType modifyMonitoredItemsCount = (ServiceCounterDataType) decoder.readStruct("ModifyMonitoredItemsCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType setMonitoringModeCount = (ServiceCounterDataType) decoder.readStruct("SetMonitoringModeCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType setTriggeringCount = (ServiceCounterDataType) decoder.readStruct("SetTriggeringCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType deleteMonitoredItemsCount = (ServiceCounterDataType) decoder.readStruct("DeleteMonitoredItemsCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType createSubscriptionCount = (ServiceCounterDataType) decoder.readStruct("CreateSubscriptionCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType modifySubscriptionCount = (ServiceCounterDataType) decoder.readStruct("ModifySubscriptionCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType setPublishingModeCount = (ServiceCounterDataType) decoder.readStruct("SetPublishingModeCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType publishCount = (ServiceCounterDataType) decoder.readStruct("PublishCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType republishCount = (ServiceCounterDataType) decoder.readStruct("RepublishCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType transferSubscriptionsCount = (ServiceCounterDataType) decoder.readStruct("TransferSubscriptionsCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType deleteSubscriptionsCount = (ServiceCounterDataType) decoder.readStruct("DeleteSubscriptionsCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType addNodesCount = (ServiceCounterDataType) decoder.readStruct("AddNodesCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType addReferencesCount = (ServiceCounterDataType) decoder.readStruct("AddReferencesCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType deleteNodesCount = (ServiceCounterDataType) decoder.readStruct("DeleteNodesCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType deleteReferencesCount = (ServiceCounterDataType) decoder.readStruct("DeleteReferencesCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType browseCount = (ServiceCounterDataType) decoder.readStruct("BrowseCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType browseNextCount = (ServiceCounterDataType) decoder.readStruct("BrowseNextCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType translateBrowsePathsToNodeIdsCount = (ServiceCounterDataType) decoder.readStruct("TranslateBrowsePathsToNodeIdsCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType queryFirstCount = (ServiceCounterDataType) decoder.readStruct("QueryFirstCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType queryNextCount = (ServiceCounterDataType) decoder.readStruct("QueryNextCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType registerNodesCount = (ServiceCounterDataType) decoder.readStruct("RegisterNodesCount", ServiceCounterDataType.TYPE_ID);
            ServiceCounterDataType unregisterNodesCount = (ServiceCounterDataType) decoder.readStruct("UnregisterNodesCount", ServiceCounterDataType.TYPE_ID);
            return new SessionDiagnosticsDataType(sessionId, sessionName, clientDescription, serverUri, endpointUrl, localeIds, actualSessionTimeout, maxResponseMessageSize, clientConnectionTime, clientLastContactTime, currentSubscriptionsCount, currentMonitoredItemsCount, currentPublishRequestsInQueue, totalRequestCount, unauthorizedRequestCount, readCount, historyReadCount, writeCount, historyUpdateCount, callCount, createMonitoredItemsCount, modifyMonitoredItemsCount, setMonitoringModeCount, setTriggeringCount, deleteMonitoredItemsCount, createSubscriptionCount, modifySubscriptionCount, setPublishingModeCount, publishCount, republishCount, transferSubscriptionsCount, deleteSubscriptionsCount, addNodesCount, addReferencesCount, deleteNodesCount, deleteReferencesCount, browseCount, browseNextCount, translateBrowsePathsToNodeIdsCount, queryFirstCount, queryNextCount, registerNodesCount, unregisterNodesCount);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SessionDiagnosticsDataType value) {
            encoder.writeNodeId("SessionId", value.getSessionId());
            encoder.writeString("SessionName", value.getSessionName());
            encoder.writeStruct("ClientDescription", value.getClientDescription(), ApplicationDescription.TYPE_ID);
            encoder.writeString("ServerUri", value.getServerUri());
            encoder.writeString("EndpointUrl", value.getEndpointUrl());
            encoder.writeStringArray("LocaleIds", value.getLocaleIds());
            encoder.writeDouble("ActualSessionTimeout", value.getActualSessionTimeout());
            encoder.writeUInt32("MaxResponseMessageSize", value.getMaxResponseMessageSize());
            encoder.writeDateTime("ClientConnectionTime", value.getClientConnectionTime());
            encoder.writeDateTime("ClientLastContactTime", value.getClientLastContactTime());
            encoder.writeUInt32("CurrentSubscriptionsCount", value.getCurrentSubscriptionsCount());
            encoder.writeUInt32("CurrentMonitoredItemsCount", value.getCurrentMonitoredItemsCount());
            encoder.writeUInt32("CurrentPublishRequestsInQueue", value.getCurrentPublishRequestsInQueue());
            encoder.writeStruct("TotalRequestCount", value.getTotalRequestCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeUInt32("UnauthorizedRequestCount", value.getUnauthorizedRequestCount());
            encoder.writeStruct("ReadCount", value.getReadCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("HistoryReadCount", value.getHistoryReadCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("WriteCount", value.getWriteCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("HistoryUpdateCount", value.getHistoryUpdateCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("CallCount", value.getCallCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("CreateMonitoredItemsCount", value.getCreateMonitoredItemsCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("ModifyMonitoredItemsCount", value.getModifyMonitoredItemsCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("SetMonitoringModeCount", value.getSetMonitoringModeCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("SetTriggeringCount", value.getSetTriggeringCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("DeleteMonitoredItemsCount", value.getDeleteMonitoredItemsCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("CreateSubscriptionCount", value.getCreateSubscriptionCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("ModifySubscriptionCount", value.getModifySubscriptionCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("SetPublishingModeCount", value.getSetPublishingModeCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("PublishCount", value.getPublishCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("RepublishCount", value.getRepublishCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("TransferSubscriptionsCount", value.getTransferSubscriptionsCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("DeleteSubscriptionsCount", value.getDeleteSubscriptionsCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("AddNodesCount", value.getAddNodesCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("AddReferencesCount", value.getAddReferencesCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("DeleteNodesCount", value.getDeleteNodesCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("DeleteReferencesCount", value.getDeleteReferencesCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("BrowseCount", value.getBrowseCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("BrowseNextCount", value.getBrowseNextCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("TranslateBrowsePathsToNodeIdsCount", value.getTranslateBrowsePathsToNodeIdsCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("QueryFirstCount", value.getQueryFirstCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("QueryNextCount", value.getQueryNextCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("RegisterNodesCount", value.getRegisterNodesCount(), ServiceCounterDataType.TYPE_ID);
            encoder.writeStruct("UnregisterNodesCount", value.getUnregisterNodesCount(), ServiceCounterDataType.TYPE_ID);
        }
    }
}

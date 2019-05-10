/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;

public class SessionDiagnostics {

    private final AtomicReference<DateTime> clientLastContactTime = new AtomicReference<>(DateTime.MIN_VALUE);
    private final LongAdder currentSubscriptionsCount = new LongAdder();
    private final LongAdder currentMonitoredItemsCount = new LongAdder();
    private final LongAdder currentPublishRequestsInQueue = new LongAdder();
    private final ServiceCounter totalRequestCount = new ServiceCounter();
    private final LongAdder unauthorizedRequestCount = new LongAdder();
    private final ServiceCounter readCount = new ServiceCounter();
    private final ServiceCounter historyReadCount = new ServiceCounter();
    private final ServiceCounter writeCount = new ServiceCounter();
    private final ServiceCounter historyUpdateCount = new ServiceCounter();
    private final ServiceCounter callCount = new ServiceCounter();
    private final ServiceCounter createMonitoredItemsCount = new ServiceCounter();
    private final ServiceCounter modifyMonitoredItemsCount = new ServiceCounter();
    private final ServiceCounter setMonitoringModeCount = new ServiceCounter();
    private final ServiceCounter setTriggeringCount = new ServiceCounter();
    private final ServiceCounter deleteMonitoredItemsCount = new ServiceCounter();
    private final ServiceCounter createSubscriptionCount = new ServiceCounter();
    private final ServiceCounter modifySubscriptionCount = new ServiceCounter();
    private final ServiceCounter setPublishingModeCount = new ServiceCounter();
    private final ServiceCounter publishCount = new ServiceCounter();
    private final ServiceCounter republishCount = new ServiceCounter();
    private final ServiceCounter transferSubscriptionsCount = new ServiceCounter();
    private final ServiceCounter deleteSubscriptionsCount = new ServiceCounter();
    private final ServiceCounter addNodesCount = new ServiceCounter();
    private final ServiceCounter addReferencesCount = new ServiceCounter();
    private final ServiceCounter deleteNodesCount = new ServiceCounter();
    private final ServiceCounter deleteReferencesCount = new ServiceCounter();
    private final ServiceCounter browseCount = new ServiceCounter();
    private final ServiceCounter browseNextCount = new ServiceCounter();
    private final ServiceCounter translateBrowsePathsToNodeIdsCount = new ServiceCounter();
    private final ServiceCounter queryFirstCount = new ServiceCounter();
    private final ServiceCounter queryNextCount = new ServiceCounter();
    private final ServiceCounter registerNodesCount = new ServiceCounter();
    private final ServiceCounter unregisterNodesCount = new ServiceCounter();

    private final NodeId sessionId;
    private final String sessionName;
    private final ApplicationDescription clientDescription;
    private final String serverUri;
    private final String endpointUrl;
    private final String[] localeIds;
    private final Double actualSessionTimeout;
    private final UInteger maxResponseMessageSize;
    private final DateTime clientConnectionTime;

    public SessionDiagnostics(
        NodeId sessionId,
        String sessionName,
        ApplicationDescription clientDescription,
        String serverUri, String endpointUrl,
        String[] localeIds,
        Double actualSessionTimeout,
        UInteger maxResponseMessageSize,
        DateTime clientConnectionTime
    ) {

        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.clientDescription = clientDescription;
        this.serverUri = serverUri;
        this.endpointUrl = endpointUrl;
        this.localeIds = localeIds;
        this.actualSessionTimeout = actualSessionTimeout;
        this.maxResponseMessageSize = maxResponseMessageSize;
        this.clientConnectionTime = clientConnectionTime;

        this.clientLastContactTime.set(clientConnectionTime);
    }

    public AtomicReference<DateTime> getClientLastContactTime() {
        return clientLastContactTime;
    }

    public LongAdder getCurrentSubscriptionsCount() {
        return currentSubscriptionsCount;
    }

    public LongAdder getCurrentMonitoredItemsCount() {
        return currentMonitoredItemsCount;
    }

    public LongAdder getCurrentPublishRequestsInQueue() {
        return currentPublishRequestsInQueue;
    }

    public ServiceCounter getTotalRequestCount() {
        return totalRequestCount;
    }

    public LongAdder getUnauthorizedRequestCount() {
        return unauthorizedRequestCount;
    }

    public ServiceCounter getReadCount() {
        return readCount;
    }

    public ServiceCounter getHistoryReadCount() {
        return historyReadCount;
    }

    public ServiceCounter getWriteCount() {
        return writeCount;
    }

    public ServiceCounter getHistoryUpdateCount() {
        return historyUpdateCount;
    }

    public ServiceCounter getCallCount() {
        return callCount;
    }

    public ServiceCounter getCreateMonitoredItemsCount() {
        return createMonitoredItemsCount;
    }

    public ServiceCounter getModifyMonitoredItemsCount() {
        return modifyMonitoredItemsCount;
    }

    public ServiceCounter getSetMonitoringModeCount() {
        return setMonitoringModeCount;
    }

    public ServiceCounter getSetTriggeringCount() {
        return setTriggeringCount;
    }

    public ServiceCounter getDeleteMonitoredItemsCount() {
        return deleteMonitoredItemsCount;
    }

    public ServiceCounter getCreateSubscriptionCount() {
        return createSubscriptionCount;
    }

    public ServiceCounter getModifySubscriptionCount() {
        return modifySubscriptionCount;
    }

    public ServiceCounter getSetPublishingModeCount() {
        return setPublishingModeCount;
    }

    public ServiceCounter getPublishCount() {
        return publishCount;
    }

    public ServiceCounter getRepublishCount() {
        return republishCount;
    }

    public ServiceCounter getTransferSubscriptionsCount() {
        return transferSubscriptionsCount;
    }

    public ServiceCounter getDeleteSubscriptionsCount() {
        return deleteSubscriptionsCount;
    }

    public ServiceCounter getAddNodesCount() {
        return addNodesCount;
    }

    public ServiceCounter getAddReferencesCount() {
        return addReferencesCount;
    }

    public ServiceCounter getDeleteNodesCount() {
        return deleteNodesCount;
    }

    public ServiceCounter getDeleteReferencesCount() {
        return deleteReferencesCount;
    }

    public ServiceCounter getBrowseCount() {
        return browseCount;
    }

    public ServiceCounter getBrowseNextCount() {
        return browseNextCount;
    }

    public ServiceCounter getTranslateBrowsePathsToNodeIdsCount() {
        return translateBrowsePathsToNodeIdsCount;
    }

    public ServiceCounter getQueryFirstCount() {
        return queryFirstCount;
    }

    public ServiceCounter getQueryNextCount() {
        return queryNextCount;
    }

    public ServiceCounter getRegisterNodesCount() {
        return registerNodesCount;
    }

    public ServiceCounter getUnregisterNodesCount() {
        return unregisterNodesCount;
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

}

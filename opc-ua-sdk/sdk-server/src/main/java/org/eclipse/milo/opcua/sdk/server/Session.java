/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.net.InetAddress;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.server.diagnostics.SessionDiagnostics;
import org.eclipse.milo.opcua.sdk.server.diagnostics.SessionSecurityDiagnostics;
import org.eclipse.milo.opcua.sdk.server.services.DefaultAttributeHistoryServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.DefaultAttributeServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.DefaultMethodServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.DefaultMonitoredItemServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.DefaultNodeManagementServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.DefaultQueryServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.DefaultSubscriptionServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.DefaultViewServiceSet;
import org.eclipse.milo.opcua.sdk.server.subscriptions.SubscriptionManager;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.server.services.SessionServiceSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class Session implements SessionServiceSet {

    private static final int IDENTITY_HISTORY_MAX_SIZE = 10;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<LifecycleListener> listeners = Lists.newCopyOnWriteArrayList();

    private final SubscriptionManager subscriptionManager;

    private final LinkedList<Object> identityHistory = new LinkedList<>();
    private volatile Object identityObject;
    private volatile UserTokenType identityType;

    private volatile ByteString lastNonce = ByteString.NULL_VALUE;

    private volatile long lastActivityNanos = System.nanoTime();
    private volatile ScheduledFuture<?> checkTimeoutFuture;

    private final DefaultAttributeServiceSet attributeServiceSet;
    private final DefaultAttributeHistoryServiceSet attributeHistoryServiceSet;
    private final DefaultMethodServiceSet methodServiceSet;
    private final DefaultMonitoredItemServiceSet monitoredItemServiceSet;
    private final DefaultNodeManagementServiceSet nodeManagementServiceSet;
    private final DefaultQueryServiceSet queryServiceSet;
    private final DefaultSubscriptionServiceSet subscriptionServiceSet;
    private final DefaultViewServiceSet viewServiceSet;

    private volatile EndpointDescription endpoint;
    private volatile long secureChannelId;
    private volatile SecurityConfiguration securityConfiguration;
    private volatile InetAddress clientAddress;
    private volatile String[] localeIds;
    private volatile DateTime lastContactTime;

    private final DateTime connectTime = DateTime.now();
    private final SessionDiagnostics sessionDiagnostics;
    private final SessionSecurityDiagnostics sessionSecurityDiagnostics;

    private final OpcUaServer server;
    private final NodeId sessionId;
    private final String sessionName;
    private final Duration sessionTimeout;
    private final ApplicationDescription clientDescription;
    private final String serverUri;
    private final UInteger maxResponseMessageSize;

    public Session(
        OpcUaServer server,
        NodeId sessionId,
        String sessionName,
        Duration sessionTimeout,
        ApplicationDescription clientDescription,
        String serverUri,
        UInteger maxResponseMessageSize,
        EndpointDescription endpoint,
        long secureChannelId,
        SecurityConfiguration securityConfiguration
    ) {

        this.server = server;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionTimeout = sessionTimeout;
        this.clientDescription = clientDescription;
        this.serverUri = serverUri;
        this.maxResponseMessageSize = maxResponseMessageSize;
        this.secureChannelId = secureChannelId;
        this.securityConfiguration = securityConfiguration;
        this.endpoint = endpoint;

        sessionDiagnostics = new SessionDiagnostics(this);
        sessionSecurityDiagnostics = new SessionSecurityDiagnostics(this);

        subscriptionManager = new SubscriptionManager(this, server);

        attributeServiceSet = new DefaultAttributeServiceSet();
        attributeHistoryServiceSet = new DefaultAttributeHistoryServiceSet();
        methodServiceSet = new DefaultMethodServiceSet();
        monitoredItemServiceSet = new DefaultMonitoredItemServiceSet(subscriptionManager);
        nodeManagementServiceSet = new DefaultNodeManagementServiceSet();
        queryServiceSet = new DefaultQueryServiceSet();
        subscriptionServiceSet = new DefaultSubscriptionServiceSet(subscriptionManager);
        viewServiceSet = new DefaultViewServiceSet();

        checkTimeoutFuture = server.getScheduledExecutorService().schedule(
            this::checkTimeout, sessionTimeout.toNanos(), TimeUnit.NANOSECONDS);
    }

    public OpcUaServer getServer() {
        return server;
    }

    public long getSecureChannelId() {
        return secureChannelId;
    }

    public SecurityConfiguration getSecurityConfiguration() {
        return securityConfiguration;
    }

    public EndpointDescription getEndpoint() {
        return endpoint;
    }

    @Nullable
    public Object getIdentityObject() {
        return identityObject;
    }

    @Nullable
    public UserTokenType getIdentityType() {
        return identityType;
    }

    public List<Object> getIdentityHistory() {
        synchronized (identityHistory) {
            return new ArrayList<>(identityHistory);
        }
    }

    public void setSecureChannelId(long secureChannelId) {
        this.secureChannelId = secureChannelId;
    }

    public void setIdentityObject(Object identityObject, UserTokenType identityType) {
        this.identityObject = identityObject;
        this.identityType = identityType;

        synchronized (identityHistory) {
            identityHistory.addLast(identityObject);
            while (identityHistory.size() > IDENTITY_HISTORY_MAX_SIZE) {
                identityHistory.removeFirst();
            }
        }
    }

    public void setEndpoint(EndpointDescription endpoint) {
        this.endpoint = endpoint;
    }

    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    /**
     * Get the {@link InetAddress} of the client that activated this session.
     * <p>
     * The address is set or updated when time the session is activated or re-activated.
     *
     * @return the {@link InetAddress} of the client that activated this session.
     */
    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public SessionDiagnostics getSessionDiagnostics() {
        return sessionDiagnostics;
    }

    public SessionSecurityDiagnostics getSessionSecurityDiagnostics() {
        return sessionSecurityDiagnostics;
    }

    public void addLifecycleListener(LifecycleListener listener) {
        listeners.add(listener);
    }

    void updateLastActivity() {
        lastActivityNanos = System.nanoTime();
        lastContactTime = DateTime.now();
    }

    public ApplicationDescription getClientDescription() {
        return clientDescription;
    }

    public String getServerUri() {
        return serverUri;
    }

    public Double getSessionTimeout() {
        return (double) sessionTimeout.toMillis();
    }

    public UInteger getMaxResponseMessageSize() {
        return maxResponseMessageSize;
    }

    public DateTime getConnectionTime() {
        return connectTime;
    }

    public DateTime getLastContactTime() {
        return lastContactTime;
    }

    void setLastNonce(ByteString lastNonce) {
        this.lastNonce = lastNonce;
    }

    public ByteString getLastNonce() {
        return lastNonce;
    }

    private void checkTimeout() {
        long elapsed = Math.abs(System.nanoTime() - lastActivityNanos);

        if (elapsed > sessionTimeout.toNanos()) {
            logger.debug("Session id={} lifetime expired ({}ms).", sessionId, sessionTimeout.toMillis());

            close(true);

            server.getDiagnosticsSummary().getSessionTimeoutCount().increment();
        } else {
            long remaining = sessionTimeout.toNanos() - elapsed;
            logger.trace("Session id={} timeout scheduled for +{}s.",
                sessionId, Duration.ofNanos(remaining).getSeconds());

            checkTimeoutFuture = server.getScheduledExecutorService()
                .schedule(this::checkTimeout, remaining, TimeUnit.NANOSECONDS);
        }
    }

    public NodeId getSessionId() {
        return sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    @Nullable
    public String[] getLocaleIds() {
        return localeIds;
    }

    public void setLocaleIds(@Nullable String[] localeIds) {
        this.localeIds = localeIds;
    }

    public DefaultAttributeServiceSet getAttributeServiceSet() {
        return attributeServiceSet;
    }

    public DefaultAttributeHistoryServiceSet getAttributeHistoryServiceSet() {
        return attributeHistoryServiceSet;
    }

    public DefaultMethodServiceSet getMethodServiceSet() {
        return methodServiceSet;
    }

    public DefaultMonitoredItemServiceSet getMonitoredItemServiceSet() {
        return monitoredItemServiceSet;
    }

    public NodeManagementServiceSet getNodeManagementServiceSet() {
        return nodeManagementServiceSet;
    }

    public DefaultQueryServiceSet getQueryServiceSet() {
        return queryServiceSet;
    }

    public DefaultSubscriptionServiceSet getSubscriptionServiceSet() {
        return subscriptionServiceSet;
    }

    public DefaultViewServiceSet getViewServiceSet() {
        return viewServiceSet;
    }

    public SubscriptionManager getSubscriptionManager() {
        return subscriptionManager;
    }
    //region Session Services

    @Override
    public void onCreateSession(ServiceRequest serviceRequest) {
        serviceRequest.setServiceFault(StatusCodes.Bad_InternalError);
    }

    @Override
    public void onActivateSession(ServiceRequest serviceRequest) {
        serviceRequest.setServiceFault(StatusCodes.Bad_InternalError);
    }

    @Override
    public void onCloseSession(ServiceRequest serviceRequest) {
        serviceRequest.setServiceFault(StatusCodes.Bad_InternalError);
    }

    void close(boolean deleteSubscriptions) {
        if (checkTimeoutFuture != null) {
            checkTimeoutFuture.cancel(false);
        }

        subscriptionManager.sessionClosed(deleteSubscriptions);

        listeners.forEach(listener -> listener.onSessionClosed(this, deleteSubscriptions));
    }

    @Override
    public void onCancel(ServiceRequest serviceRequest) throws UaException {
        serviceRequest.setResponse(new CancelResponse(serviceRequest.createResponseHeader(), uint(0)));
    }
    //endregion

    public interface LifecycleListener {
        void onSessionClosed(Session session, boolean subscriptionsDeleted);
    }
}

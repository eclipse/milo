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
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.server.services.SessionServiceSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class Session implements SessionServiceSet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<LifecycleListener> listeners = Lists.newCopyOnWriteArrayList();

    private final SubscriptionManager subscriptionManager;

    private volatile long secureChannelId;

    private volatile Object identityObject;

    private volatile ByteString lastNonce = ByteString.NULL_VALUE;

    private volatile long lastActivity = System.nanoTime();
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
    private volatile SecurityConfiguration securityConfiguration;
    private volatile InetAddress clientAddress;
    private volatile String[] localeIds;

    private final OpcUaServer server;
    private final NodeId sessionId;
    private final String sessionName;
    private final Duration sessionTimeout;

    public Session(
        OpcUaServer server,
        NodeId sessionId,
        String sessionName,
        Duration sessionTimeout,
        long secureChannelId,
        EndpointDescription endpoint,
        SecurityConfiguration securityConfiguration) {

        this.server = server;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionTimeout = sessionTimeout;
        this.secureChannelId = secureChannelId;
        this.securityConfiguration = securityConfiguration;
        this.endpoint = endpoint;

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

    public void setSecureChannelId(long secureChannelId) {
        this.secureChannelId = secureChannelId;
    }

    public void setIdentityObject(Object identityObject) {
        this.identityObject = identityObject;
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

    void addLifecycleListener(LifecycleListener listener) {
        listeners.add(listener);
    }

    void updateLastActivity() {
        lastActivity = System.nanoTime();
    }

    void setLastNonce(ByteString lastNonce) {
        this.lastNonce = lastNonce;
    }

    public ByteString getLastNonce() {
        return lastNonce;
    }

    private void checkTimeout() {
        long elapsed = Math.abs(System.nanoTime() - lastActivity);

        if (elapsed > sessionTimeout.toNanos()) {
            logger.debug("Session id={} lifetime expired ({}ms).", sessionId, sessionTimeout.toMillis());

            subscriptionManager.sessionClosed(true);

            listeners.forEach(listener -> listener.onSessionClosed(this, true));
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
        CloseSessionRequest request = (CloseSessionRequest) serviceRequest.getRequest();

        close(request.getDeleteSubscriptions());

        serviceRequest.setResponse(new CloseSessionResponse(serviceRequest.createResponseHeader()));
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

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

package org.eclipse.milo.opcua.sdk.server;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.sdk.server.services.AttributeHistoryServices;
import org.eclipse.milo.opcua.sdk.server.services.AttributeServices;
import org.eclipse.milo.opcua.sdk.server.services.MethodServices;
import org.eclipse.milo.opcua.sdk.server.services.MonitoredItemServices;
import org.eclipse.milo.opcua.sdk.server.services.NodeManagementServices;
import org.eclipse.milo.opcua.sdk.server.services.QueryServices;
import org.eclipse.milo.opcua.sdk.server.services.SubscriptionServices;
import org.eclipse.milo.opcua.sdk.server.services.ViewServices;
import org.eclipse.milo.opcua.sdk.server.subscriptions.SubscriptionManager;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.application.services.SessionServiceSet;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class Session implements SessionServiceSet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<LifecycleListener> listeners = Lists.newCopyOnWriteArrayList();

    private final SubscriptionManager subscriptionManager;

    private volatile long secureChannelId;

    private volatile Object identityObject;
    private volatile ByteString clientCertificateBytes;

    private volatile ByteString lastNonce = ByteString.NULL_VALUE;

    private volatile long lastActivity = System.nanoTime();
    private volatile ScheduledFuture<?> checkTimeoutFuture;

    private final AttributeServices attributeServices;
    private final AttributeHistoryServices attributeHistoryServices;
    private final MethodServices methodServices;
    private final MonitoredItemServices monitoredItemServices;
    private final NodeManagementServiceSet nodeManagementServices;
    private final QueryServices queryServices;
    private final SubscriptionServices subscriptionServices;
    private final ViewServices viewServices;

    private final OpcUaServer server;
    private final NodeId sessionId;
    private final String sessionName;
    private final Duration sessionTimeout;

    public Session(OpcUaServer server,
                   NodeId sessionId,
                   String sessionName,
                   Duration sessionTimeout,
                   long secureChannelId) {

        this.server = server;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionTimeout = sessionTimeout;
        this.secureChannelId = secureChannelId;

        subscriptionManager = new SubscriptionManager(this, server);

        attributeServices = new AttributeServices();
        attributeHistoryServices = new AttributeHistoryServices();
        methodServices = new MethodServices();
        monitoredItemServices = new MonitoredItemServices(subscriptionManager);
        nodeManagementServices = new NodeManagementServices();
        queryServices = new QueryServices();
        subscriptionServices = new SubscriptionServices(subscriptionManager);
        viewServices = new ViewServices();

        checkTimeoutFuture = server.getScheduledExecutorService().schedule(
            this::checkTimeout, sessionTimeout.toNanos(), TimeUnit.NANOSECONDS);
    }

    public OpcUaServer getServer() {
        return server;
    }

    public long getSecureChannelId() {
        return secureChannelId;
    }

    @Nullable
    public Object getIdentityObject() {
        return identityObject;
    }

    @Nullable
    public ByteString getClientCertificateBytes() {
        return clientCertificateBytes;
    }

    public void setSecureChannelId(long secureChannelId) {
        this.secureChannelId = secureChannelId;
    }

    public void setIdentityObject(Object identityObject) {
        this.identityObject = identityObject;
    }

    public void setClientCertificateBytes(ByteString clientCertificateBytes) {
        this.clientCertificateBytes = clientCertificateBytes;
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

    public AttributeServices getAttributeServices() {
        return attributeServices;
    }

    public AttributeHistoryServices getAttributeHistoryServices() {
        return attributeHistoryServices;
    }

    public MethodServices getMethodServices() {
        return methodServices;
    }

    public MonitoredItemServices getMonitoredItemServices() {
        return monitoredItemServices;
    }

    public NodeManagementServiceSet getNodeManagementServices() {
        return nodeManagementServices;
    }

    public QueryServices getQueryServices() {
        return queryServices;
    }

    public SubscriptionServices getSubscriptionServices() {
        return subscriptionServices;
    }

    public ViewServices getViewServices() {
        return viewServices;
    }

    public SubscriptionManager getSubscriptionManager() {
        return subscriptionManager;
    }

    //region Session Services
    @Override
    public void onCreateSession(
        ServiceRequest<CreateSessionRequest, CreateSessionResponse> req) throws UaException {

        throw new UaException(StatusCodes.Bad_InternalError);
    }

    @Override
    public void onActivateSession(
        ServiceRequest<ActivateSessionRequest, ActivateSessionResponse> req) throws UaException {

        throw new UaException(StatusCodes.Bad_InternalError);
    }

    @Override
    public void onCloseSession(
        ServiceRequest<CloseSessionRequest, CloseSessionResponse> serviceRequest) throws UaException {

        close(serviceRequest.getRequest().getDeleteSubscriptions());

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
    public void onCancel(ServiceRequest<CancelRequest, CancelResponse> serviceRequest) throws UaException {
        serviceRequest.setResponse(new CancelResponse(serviceRequest.createResponseHeader(), uint(0)));
    }
    //endregion

    public static interface LifecycleListener {
        void onSessionClosed(Session session, boolean subscriptionsDeleted);
    }
}

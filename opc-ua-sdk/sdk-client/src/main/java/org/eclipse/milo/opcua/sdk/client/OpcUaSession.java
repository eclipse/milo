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

package org.eclipse.milo.opcua.sdk.client;

import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.sdk.client.api.UaSession;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public class OpcUaSession extends ConcurrentHashMap<String, Object> implements UaSession {

    private volatile ByteString serverNonce = ByteString.NULL_VALUE;

    private final NodeId authToken;
    private final NodeId sessionId;
    private final String sessionName;
    private final double sessionTimeout;
    private final UInteger maxRequestSize;
    private final ByteString serverCertificate;
    private final SignedSoftwareCertificate[] serverSoftwareCertificates;

    public OpcUaSession(NodeId authToken,
                        NodeId sessionId,
                        String sessionName,
                        double sessionTimeout,
                        UInteger maxRequestSize,
                        ByteString serverCertificate,
                        SignedSoftwareCertificate[] serverSoftwareCertificates) {

        this.authToken = authToken;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionTimeout = sessionTimeout;
        this.maxRequestSize = maxRequestSize;
        this.serverCertificate = serverCertificate;
        this.serverSoftwareCertificates = serverSoftwareCertificates;
    }

    @Override
    public NodeId getAuthenticationToken() {
        return authToken;
    }

    @Override
    public NodeId getSessionId() {
        return sessionId;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public Double getSessionTimeout() {
        return sessionTimeout;
    }

    @Override
    public UInteger getMaxRequestSize() {
        return maxRequestSize;
    }

    @Override
    public SignedSoftwareCertificate[] getServerSoftwareCertificates() {
        return serverSoftwareCertificates;
    }

    @Override
    public ByteString getServerCertificate() {
        return serverCertificate;
    }

    @Override
    public ByteString getServerNonce() {
        return serverNonce;
    }

    public void setServerNonce(ByteString serverNonce) {
        this.serverNonce = serverNonce;
    }

    @Nullable
    @Override
    public Object getAttribute(@Nonnull String name) {
        return get(name);
    }

    @Nullable
    @Override
    public Object setAttribute(@Nonnull String name, @Nonnull Object value) {
        return put(name, value);
    }

    @Override
    public Object removeAttribute(@Nonnull String name) {
        return remove(name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("sessionId", sessionId)
            .add("sessionName", sessionName)
            .toString();
    }

}

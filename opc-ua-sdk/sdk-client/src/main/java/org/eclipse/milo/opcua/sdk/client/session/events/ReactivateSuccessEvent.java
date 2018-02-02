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

package org.eclipse.milo.opcua.sdk.client.session.events;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaSession;

public class ReactivateSuccessEvent implements Event {

    private final OpcUaSession session;
    private final CompletableFuture<OpcUaSession> sessionFuture;

    public ReactivateSuccessEvent(OpcUaSession session, CompletableFuture<OpcUaSession> sessionFuture) {
        this.session = session;
        this.sessionFuture = sessionFuture;
    }

    public OpcUaSession getSession() {
        return session;
    }

    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

}

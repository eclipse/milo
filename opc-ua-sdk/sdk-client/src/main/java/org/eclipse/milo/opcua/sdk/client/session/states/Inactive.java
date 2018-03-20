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

package org.eclipse.milo.opcua.sdk.client.session.states;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.session.Fsm;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class Inactive extends AbstractSessionState implements SessionState {

    private final CompletableFuture<OpcUaSession> sessionFuture = failedUaFuture(StatusCodes.Bad_SessionClosed);

    @Override
    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public SessionState execute(Fsm fsm, Event event) {
        if (event instanceof CreateSessionEvent) {
            CompletableFuture<OpcUaSession> sessionFuture =
                ((CreateSessionEvent) event).getSessionFuture();

            createSessionAsync(fsm, sessionFuture);

            return new Creating();
        } else if (event instanceof CloseSessionEvent) {
            CompletableFuture<Unit> closeFuture =
                ((CloseSessionEvent) event).getCloseFuture();

            closeFuture.complete(Unit.VALUE);

            return this;
        } else {
            return this;
        }
    }

}

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
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.stack.core.StatusCodes;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class Inactive extends AbstractState implements State {

    private final CompletableFuture<OpcUaSession> sessionFuture = failedUaFuture(StatusCodes.Bad_SessionClosed);

    @Override
    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public State execute(SessionFsm fsm, Event event) {
        if (event instanceof CreateSessionEvent) {
            CompletableFuture<OpcUaSession> sessionFuture =
                ((CreateSessionEvent) event).getSessionFuture();

            createSessionAsync(fsm, sessionFuture);

            return new Creating();
        } else {
            return this;
        }
    }

}

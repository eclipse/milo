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
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.sdk.client.session.events.InitializeFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.InitializeSuccessEvent;

public class Initializing extends AbstractSessionState {

    private CompletableFuture<OpcUaSession> sessionFuture;

    @Override
    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public void onExternalTransition(Fsm fsm, SessionState from, Event event) {
        sessionFuture = from.getSessionFuture();
    }

    @Override
    public SessionState execute(Fsm fsm, Event event) {
        if (event instanceof InitializeSuccessEvent) {
            OpcUaSession session = ((InitializeSuccessEvent) event).getSession();

            sessionFuture.complete(session);

            return new Active();
        } else if (event instanceof InitializeFailureEvent) {
            InitializeFailureEvent e = (InitializeFailureEvent) event;

            Closing closing = new Closing();

            closeSessionAsync(fsm, e.getSession(), closing.getCloseFuture(), e.getSessionFuture());

            return closing;
        } else if (event instanceof CloseSessionEvent) {
            // CloseSessionEvent preempted our receipt of a success/failure event.
            // Closing state will receive one of those events and execute the appropriate action.
            return new Closing();
        } else {
            return this;
        }
    }

}

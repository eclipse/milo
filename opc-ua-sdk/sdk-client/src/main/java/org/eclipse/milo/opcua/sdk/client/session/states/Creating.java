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
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class Creating extends AbstractSessionState implements SessionState {

    private CompletableFuture<OpcUaSession> sessionFuture = new CompletableFuture<>();

    @Override
    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public void onExternalTransition(Fsm fsm, SessionState from, Event event) {
        if (from instanceof Inactive && event instanceof CreateSessionEvent) {
            sessionFuture = ((CreateSessionEvent) event).getSessionFuture();
        }
    }

    @Override
    public void onInternalTransition(Fsm fsm, Event event) {
        if (event instanceof CreateSessionEvent) {
            // Another call to SessionFsm.create() results in an internal transition; we need to ensure
            // the sessionFuture in this event is completed with the result of the one that originally
            // started the create session process.
            CreateSessionEvent e = (CreateSessionEvent) event;

            complete(e.getSessionFuture()).with(sessionFuture);
        }
    }

    @Override
    public SessionState execute(Fsm fsm, Event e) {
        if (e instanceof CreateSessionSuccessEvent) {
            CreateSessionResponse csr = ((CreateSessionSuccessEvent) e).getResponse();

            activateSessionAsync(fsm, csr, sessionFuture);

            return new Activating();
        } else if (e instanceof CreateSessionFailureEvent) {
            Throwable failure = ((CreateSessionFailureEvent) e).getFailure();

            sessionFuture.completeExceptionally(failure);

            return new Inactive();
        } else if (e instanceof CloseSessionEvent) {
            // CloseSessionEvent preempted our receipt of a success/failure event.
            // Closing state will receive one of those events and execute the appropriate action.
            return new Closing();
        } else {
            return this;
        }
    }

}

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
import org.eclipse.milo.opcua.sdk.client.session.events.ActivateSessionFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ActivateSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.sdk.client.session.events.InitializeFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.InitializeSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.TransferFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.TransferSuccessEvent;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class Closing extends AbstractSessionState implements SessionState {

    private CompletableFuture<Unit> closeFuture = new CompletableFuture<>();
    private CompletableFuture<OpcUaSession> sessionFuture;

    public CompletableFuture<Unit> getCloseFuture() {
        return closeFuture;
    }

    @Override
    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public void onExternalTransition(Fsm fsm, SessionState prev, Event event) {
        sessionFuture = prev.getSessionFuture();

        if (event instanceof CloseSessionEvent) {
            CloseSessionEvent e = (CloseSessionEvent) event;

            closeFuture = e.getCloseFuture();
        }
    }

    @Override
    public void onInternalTransition(Fsm fsm, Event event) {
        if (event instanceof CreateSessionEvent) {
            CreateSessionEvent e = (CreateSessionEvent) event;

            closeFuture.whenComplete((u, ex) ->
                e.getSessionFuture().completeExceptionally(
                    new UaException(StatusCodes.Bad_SessionClosed))
            );
        } else if (event instanceof CloseSessionEvent) {
            // Another call to SessionFsm.close() results in Closing -> Closing; we need to
            // ensure the closeFuture in this event is completed with the result of the one
            // that originally caused the transition to Closing.
            CloseSessionEvent e = (CloseSessionEvent) event;

            complete(e.getCloseFuture()).with(closeFuture);
        } else if (event instanceof CreateSessionSuccessEvent) {
            CreateSessionSuccessEvent e = (CreateSessionSuccessEvent) event;
            NodeId authToken = e.getResponse().getAuthenticationToken();
            NodeId sessionId = e.getResponse().getSessionId();

            closeSessionAsync(fsm, authToken, sessionId, closeFuture, e.getSessionFuture());
        } else if (event instanceof CreateSessionFailureEvent) {
            CreateSessionFailureEvent e = (CreateSessionFailureEvent) event;

            fsm.getClient().getConfig().getExecutor().execute(() ->
                fsm.fireEvent(new CloseSessionSuccessEvent(closeFuture, e.getSessionFuture()))
            );
        } else if (event instanceof ActivateSessionSuccessEvent) {
            ActivateSessionSuccessEvent e = (ActivateSessionSuccessEvent) event;

            closeSessionAsync(fsm, e.getSession(), closeFuture, e.getSessionFuture());
        } else if (event instanceof ActivateSessionFailureEvent) {
            ActivateSessionFailureEvent e = (ActivateSessionFailureEvent) event;

            fsm.getClient().getConfig().getExecutor().execute(() ->
                fsm.fireEvent(new CloseSessionSuccessEvent(closeFuture, e.getSessionFuture()))
            );
        } else if (event instanceof TransferSuccessEvent) {
            TransferSuccessEvent e = (TransferSuccessEvent) event;

            closeSessionAsync(fsm, e.getSession(), closeFuture, e.getSessionFuture());
        } else if (event instanceof TransferFailureEvent) {
            TransferFailureEvent e = (TransferFailureEvent) event;

            closeSessionAsync(fsm, e.getSession(), closeFuture, e.getSessionFuture());
        } else if (event instanceof ReactivateSuccessEvent) {
            ReactivateSuccessEvent e = (ReactivateSuccessEvent) event;

            closeSessionAsync(fsm, e.getSession(), closeFuture, e.getSessionFuture());
        } else if (event instanceof ReactivateFailureEvent) {
            ReactivateFailureEvent e = (ReactivateFailureEvent) event;

            fsm.getClient().getConfig().getExecutor().execute(() ->
                fsm.fireEvent(new CloseSessionSuccessEvent(closeFuture, e.getSessionFuture()))
            );
        } else if (event instanceof InitializeSuccessEvent) {
            InitializeSuccessEvent e = (InitializeSuccessEvent) event;

            closeSessionAsync(fsm, e.getSession(), closeFuture, e.getSessionFuture());
        } else if (event instanceof InitializeFailureEvent) {
            InitializeFailureEvent e = (InitializeFailureEvent) event;

            closeSessionAsync(fsm, e.getSession(), closeFuture, e.getSessionFuture());
        }
    }

    @Override
    public SessionState execute(Fsm fsm, Event event) {
        if (event instanceof CloseSessionSuccessEvent) {
            closeFuture.complete(Unit.VALUE);

            sessionFuture.completeExceptionally(
                new UaException(StatusCodes.Bad_SessionClosed));

            return new Inactive();
        } else {
            return this;
        }
    }

}

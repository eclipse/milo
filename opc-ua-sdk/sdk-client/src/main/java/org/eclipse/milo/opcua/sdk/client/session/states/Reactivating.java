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
import org.eclipse.milo.opcua.sdk.client.session.events.ActivateSessionFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ActivateSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateSuccessEvent;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class Reactivating extends AbstractState implements State {

    private static final Logger LOGGER = LoggerFactory.getLogger(Reactivating.class);

    private CompletableFuture<OpcUaSession> sessionFuture = new CompletableFuture<>();

    @Override
    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public void onExternalTransition(SessionFsm fsm, State prev, Event event) {
        if (prev instanceof Recreating && event instanceof CreateSessionSuccessEvent) {
            sessionFuture = ((CreateSessionSuccessEvent) event).getSessionFuture();
        }

    }

    @Override
    public void onInternalTransition(SessionFsm fsm, Event event) {
        if (event instanceof CreateSessionEvent) {
            // Another call to SessionFsm.create() results in an internal transition; we need to ensure
            // the sessionFuture in this event is completed with the result of the one that originally
            // started the create session process.
            CreateSessionEvent e = (CreateSessionEvent) event;

            complete(e.getSessionFuture()).with(sessionFuture);
        }
    }

    @Override
    public State execute(SessionFsm fsm, Event event) {
        if (event instanceof ReactivateSuccessEvent) {
            OpcUaSession session = ((ReactivateSuccessEvent) event).getSession();

            fsm.getClient().getConfig().getExecutor()
                .submit(() -> sessionFuture.complete(session));

            return new Active();
        } else if (event instanceof ReactivateFailureEvent) {
            ReactivateFailureEvent e = (ReactivateFailureEvent) event;

            StatusCode statusCode = UaException.extract(e.getFailure())
                .map(UaException::getStatusCode)
                .orElse(StatusCode.BAD);

            sessionFuture.completeExceptionally(new UaException(statusCode));

            if (statusCode.getValue() == StatusCodes.Bad_Timeout ||
                statusCode.getValue() == StatusCodes.Bad_ConnectionRejected) {

                Reactivating reactivating = new Reactivating();

                reactivateSessionAsync(fsm, e.getSession(), reactivating.getSessionFuture());

                return reactivating;
            } else {
                Recreating recreating = new Recreating();

                createSessionAsync(fsm, recreating.getSessionFuture());

                return recreating;
            }
        } else if (event instanceof ActivateSessionSuccessEvent) {
            OpcUaSession session = ((ActivateSessionSuccessEvent) event).getSession();

            transferSubscriptionsAsync(fsm, session, sessionFuture);

            return new Retransferring();
        } else if (event instanceof ActivateSessionFailureEvent) {
            Throwable failure = ((ActivateSessionFailureEvent) event).getFailure();

            sessionFuture.completeExceptionally(failure);

            Recreating recreating = new Recreating();

            createSessionAsync(fsm, recreating.getSessionFuture());

            return recreating;
        } else if (event instanceof CloseSessionEvent) {
            return new Closing();
        } else {
            return this;
        }
    }

}

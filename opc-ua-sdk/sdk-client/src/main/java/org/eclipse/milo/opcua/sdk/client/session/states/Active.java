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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.session.Fsm;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.sdk.client.session.events.InitializeSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ServiceFaultEvent;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class Active extends AbstractSessionState implements SessionState {

    private final Logger logger = LoggerFactory.getLogger(SessionFsm.class);

    private OpcUaSession session;
    private CompletableFuture<OpcUaSession> sessionFuture;

    private volatile boolean keepAliveActive = true;
    private final AtomicLong keepAliveFailureCount = new AtomicLong(0L);

    public OpcUaSession getSession() {
        return session;
    }

    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public void onExternalTransition(Fsm fsm, SessionState prev, Event event) {
        if (prev instanceof Initializing || prev instanceof Reinitializing) {
            if (event instanceof InitializeSuccessEvent) {
                InitializeSuccessEvent e = (InitializeSuccessEvent) event;

                session = e.getSession();
                sessionFuture = e.getSessionFuture();
            }
        } else if (prev instanceof Reactivating) {
            if (event instanceof ReactivateSuccessEvent) {
                ReactivateSuccessEvent e = (ReactivateSuccessEvent) event;

                session = e.getSession();
                sessionFuture = e.getSessionFuture();
            }
        }

        // TODO Stack should notify a new secure channel was created (due to reconnect) and we should reactivate

        scheduleKeepAlive(fsm);
    }

    @Override
    public void onInternalTransition(Fsm fsm, Event event) {
        if (event instanceof CreateSessionEvent) {
            // Another call to SessionFsm.create() results in an internal transition; we need to ensure
            // the sessionFuture in this event is completed with the result of the one that originally
            // started the create session process.
            CreateSessionEvent e = (CreateSessionEvent) event;

            complete(e.getSessionFuture()).with(sessionFuture);
        } else if (event instanceof ScheduleKeepAliveEvent) {
            scheduleKeepAlive(fsm);
        }
    }

    private void scheduleKeepAlive(Fsm fsm) {
        long delay = fsm.getClient().getConfig().getKeepAliveInterval().longValue();

        Stack.sharedScheduledExecutor().schedule(
            new KeepAlive(fsm, session), delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public SessionState execute(Fsm fsm, Event e) {
        if (e instanceof CloseSessionEvent) {
            keepAliveActive = false;

            CompletableFuture<Unit> closeFuture =
                ((CloseSessionEvent) e).getCloseFuture();

            closeSessionAsync(fsm, session, closeFuture, sessionFuture);

            return new Closing();
        } else if (e instanceof KeepAliveFailureEvent || e instanceof ServiceFaultEvent) {
            keepAliveActive = false;

            Reactivating reactivating = new Reactivating();

            fsm.getClient().getStackClient().disconnect().whenComplete(
                (c, ex) -> {
                    logger.debug("disconnect complete; reactivating...");

                    reactivateSessionAsync(fsm, session, reactivating.getSessionFuture());
                }
            );

            return reactivating;
        } else {
            return this;
        }
    }

    private class KeepAlive implements Runnable {

        private final Logger logger = LoggerFactory.getLogger(SessionFsm.class);

        private final long keepAliveFailuresAllowed;

        private final Fsm fsm;
        private final OpcUaSession session;

        KeepAlive(Fsm fsm, OpcUaSession session) {
            this.fsm = fsm;
            this.session = session;

            keepAliveFailuresAllowed = fsm.getClient().getConfig().getKeepAliveFailuresAllowed().longValue();
        }

        @Override
        public void run() {
            if (!keepAliveActive) {
                return;
            }

            ReadRequest keepAliveRequest = createKeepAliveRequest();

            CompletableFuture<ReadResponse> responseFuture =
                fsm.getClient().getStackClient()
                    .sendRequest(keepAliveRequest)
                    .thenApply(ReadResponse.class::cast);

            responseFuture.whenComplete((r, ex) -> {
                if (ex != null) {
                    if (keepAliveFailureCount.incrementAndGet() > keepAliveFailuresAllowed) {
                        logger.warn(
                            "[{}] Keep Alive failureCount={} exceeds failuresAllowed={}",
                            fsm.getId(), keepAliveFailureCount, keepAliveFailuresAllowed
                        );

                        maybeFireEvent(new KeepAliveFailureEvent());
                    } else {
                        logger.debug(
                            "[{}] Keep Alive failureCount={}",
                            fsm.getId(), keepAliveFailureCount, ex
                        );

                        StatusCode statusCode = UaException.extract(ex)
                            .map(UaException::getStatusCode)
                            .orElse(StatusCode.BAD);

                        // TODO
                        // This check wouldn't be necessary if the stack could notify use there was a reconnect or
                        // establishment of a new secure channel that allowed us to be proactive in reactivation.
                        if (statusCode.getValue() == StatusCodes.Bad_SessionClosed ||
                            statusCode.getValue() == StatusCodes.Bad_SessionIdInvalid ||
                            statusCode.getValue() == StatusCodes.Bad_SessionNotActivated) {

                            maybeFireEvent(new KeepAliveFailureEvent());
                        } else {
                            maybeFireEvent(new ScheduleKeepAliveEvent());
                        }
                    }
                } else {
                    DataValue[] results = r.getResults();

                    if (results != null && results.length > 0) {
                        Object value = results[0].getValue().getValue();
                        if (value instanceof Integer) {
                            ServerState state = ServerState.from((Integer) value);
                            logger.debug("[{}] ServerState: {}", fsm.getId(), state);
                        }
                    }

                    keepAliveFailureCount.set(0);

                    maybeFireEvent(new ScheduleKeepAliveEvent());
                }
            });
        }

        /**
         * Fire {@code event} if the Keep Alive for this instance of Active state hasn't been cancelled.
         *
         * @param event the {@link Event} to fire.
         */
        private void maybeFireEvent(Event event) {
            if (keepAliveActive) {
                fsm.fireEvent(event);
            }
        }

        private ReadRequest createKeepAliveRequest() {
            RequestHeader requestHeader = fsm.getClient().getStackClient().newRequestHeader(
                session.getAuthenticationToken(),
                fsm.getClient().getConfig().getKeepAliveTimeout()
            );

            return new ReadRequest(
                requestHeader,
                0.0,
                TimestampsToReturn.Neither,
                new ReadValueId[]{new ReadValueId(
                    Identifiers.Server_ServerStatus_State,
                    AttributeId.Value.uid(),
                    null,
                    QualifiedName.NULL_VALUE
                )}
            );
        }

    }

    private class KeepAliveFailureEvent implements Event {}

    private class ScheduleKeepAliveEvent implements Event {}

}

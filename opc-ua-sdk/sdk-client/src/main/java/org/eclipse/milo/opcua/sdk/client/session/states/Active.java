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
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.session.Fsm;
import org.eclipse.milo.opcua.sdk.client.session.events.ChannelInactiveEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.sdk.client.session.events.InitializeSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ServiceFaultEvent;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.application.UaStackClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class Active extends AbstractSessionState implements SessionState {

    private static final int KEEP_ALIVE_FAILURES_ALLOWED = 1;
    private static final int KEEP_ALIVE_INTERVAL_MS = 5000;
    private static final int KEEP_ALIVE_TIMEOUT_MS = 5000;

    private OpcUaSession session;
    private CompletableFuture<OpcUaSession> sessionFuture;

    private volatile boolean keepAliveActive = true;
    private final AtomicInteger keepAliveFailureCount = new AtomicInteger(0);

    public OpcUaSession getSession() {
        return session;
    }

    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public void onExternalTransition(Fsm fsm, SessionState prev, Event event) {
        fsm.getClient().getStackClient().getChannelFuture().thenAccept(secureChannel -> {
            Channel channel = secureChannel.getChannel();

            if (channel.pipeline().get(InactivityHandler.class) == null) {
                channel.pipeline().addLast(new InactivityHandler(fsm));
            }
        });

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

        fsm.getClient().getSubscriptionManager().startPublishing();

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
        Stack.sharedScheduledExecutor().schedule(
            new KeepAlive(fsm, session),
            KEEP_ALIVE_INTERVAL_MS,
            TimeUnit.MILLISECONDS
        );
    }

    @Override
    public SessionState execute(Fsm fsm, Event e) {
        if (e instanceof CloseSessionEvent) {
            keepAliveActive = false;

            CompletableFuture<Unit> closeFuture =
                ((CloseSessionEvent) e).getCloseFuture();

            closeSessionAsync(fsm, session, closeFuture, sessionFuture);

            return new Closing();
        } else if (e instanceof ChannelInactiveEvent) {
            keepAliveActive = false;

            Reactivating reactivating = new Reactivating();

            reactivateSessionAsync(fsm, session, reactivating.getSessionFuture());

            return reactivating;
        } else if (e instanceof KeepAliveFailureEvent || e instanceof ServiceFaultEvent) {
            keepAliveActive = false;

            CompletableFuture<CompletableFuture<UaStackClient>> reconnect =
                fsm.getClient().getStackClient()
                    .disconnect()
                    .thenApply(c -> c.connect(true));

            Reactivating reactivating = new Reactivating();

            reconnect.whenComplete((c, ex) ->
                reactivateSessionAsync(fsm, session, reactivating.getSessionFuture()));

            return reactivating;
        } else {
            return this;
        }
    }

    private class KeepAlive implements Runnable {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final Fsm fsm;
        private final OpcUaSession session;

        KeepAlive(Fsm fsm, OpcUaSession session) {
            this.fsm = fsm;
            this.session = session;
        }

        @Override
        public void run() {
            if (!keepAliveActive) {
                return;
            }

            ReadRequest keepAliveRequest = createKeepAliveRequest();

            CompletableFuture<ReadResponse> responseFuture =
                fsm.getClient().getStackClient().sendRequest(keepAliveRequest);

            responseFuture.whenComplete((r, ex) -> {
                if (ex != null) {
                    if (keepAliveFailureCount.incrementAndGet() > KEEP_ALIVE_FAILURES_ALLOWED) {
                        logger.warn(
                            "Keep Alive failureCount=" + keepAliveFailureCount +
                                " exceeds failuresAllowed=" + KEEP_ALIVE_FAILURES_ALLOWED);

                        maybeFireEvent(new KeepAliveFailureEvent());
                    } else {
                        logger.debug("Keep Alive failureCount=" + keepAliveFailureCount);

                        maybeFireEvent(new ScheduleKeepAliveEvent());
                    }
                } else {
                    DataValue[] results = r.getResults();

                    if (results != null && results.length > 0) {
                        Object value = results[0].getValue().getValue();
                        if (value instanceof Integer) {
                            ServerState state = ServerState.from((Integer) value);
                            logger.debug("ServerState: " + state);
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
                uint(KEEP_ALIVE_TIMEOUT_MS)
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

    private static class InactivityHandler extends ChannelInboundHandlerAdapter {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final Fsm fsm;

        InactivityHandler(Fsm fsm) {
            this.fsm = fsm;
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            logger.debug(
                "[local={}, remote={}] channelInactive()",
                ctx.channel().localAddress(), ctx.channel().remoteAddress());

            fsm.getClient().getConfig().getExecutor().execute(
                () -> fsm.fireEvent(new ChannelInactiveEvent())
            );

            super.channelInactive(ctx);
        }

    }

    private class KeepAliveFailureEvent implements Event {}

    private class ScheduleKeepAliveEvent implements Event {}

}

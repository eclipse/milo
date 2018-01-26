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

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
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
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class Active extends AbstractSessionState implements SessionState {

    private OpcUaSession session;
    private CompletableFuture<OpcUaSession> sessionFuture;

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
        if (e instanceof CloseSessionEvent) {
            CompletableFuture<Unit> closeFuture =
                ((CloseSessionEvent) e).getCloseFuture();

            closeSessionAsync(fsm, session, closeFuture, sessionFuture);

            return new Closing();
        } else if (e instanceof ChannelInactiveEvent) {
            Reactivating reactivating = new Reactivating();

            reactivateSessionAsync(fsm, session, reactivating.getSessionFuture());

            return reactivating;
        } else if (e instanceof ServiceFaultEvent) {
            // Try to close the underlying channel and then regardless of the result start reactivating.

            final CompletableFuture<Unit> disconnected = new CompletableFuture<>();

            fsm.getClient().getStackClient().getChannelFuture().whenComplete((c, ex) -> {
                if (c != null) {
                    Channel channel = c.getChannel();

                    channel.pipeline().remove(InactivityHandler.class);

                    channel.close().addListener(
                        (ChannelFutureListener) future ->
                            disconnected.complete(Unit.VALUE)
                    );
                } else {
                    disconnected.complete(Unit.VALUE);
                }
            });

            Reactivating reactivating = new Reactivating();

            disconnected.whenComplete((u, ex) ->
                reactivateSessionAsync(
                    fsm, session, reactivating.getSessionFuture())
            );

            return reactivating;
        } else {
            return this;
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

}

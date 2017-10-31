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
import java.util.function.Predicate;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.api.ServiceFaultListener;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.client.session.events.ChannelInactiveEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.TransferSuccessEvent;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Active extends AbstractState implements State {

    private OpcUaSession session;
    private CompletableFuture<OpcUaSession> sessionFuture;

    public OpcUaSession getSession() {
        return session;
    }

    public CompletableFuture<OpcUaSession> getSessionFuture() {
        return sessionFuture;
    }

    @Override
    public void onExternalTransition(SessionFsm fsm, State prev, Event event) {
        fsm.getClient().getStackClient().getChannelFuture().thenAccept(secureChannel -> {
            Channel channel = secureChannel.getChannel();

            if (channel.pipeline().get(InactivityHandler.class) == null) {
                channel.pipeline().addLast(new InactivityHandler(fsm));
            }
        });

        fsm.getClient().addFaultListener(new FaultListener(fsm));

        if (prev instanceof Transferring || prev instanceof Retransferring) {
            if (event instanceof TransferSuccessEvent) {
                TransferSuccessEvent e = (TransferSuccessEvent) event;

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
    }

    @Override
    public void onInternalTransition(SessionFsm fsm, Event event) {
        if (event instanceof CreateSessionEvent) {
            // Another call to SessionFsm.create() results in an internal transition; we need to ensure
            // the sessionFuture in this event is completed with the result of the one that originally
            // started the create session process.
            CreateSessionEvent e = (CreateSessionEvent) event;

            sessionFuture.whenComplete((u, ex) -> {
                if (u != null) e.getSessionFuture().complete(u);
                else e.getSessionFuture().completeExceptionally(ex);
            });
        }
    }

    @Override
    public State execute(SessionFsm fsm, Event e) {
        if (e instanceof CloseSessionEvent) {
            CompletableFuture<Unit> closeFuture =
                ((CloseSessionEvent) e).getCloseFuture();

            closeSessionAsync(fsm, session, closeFuture, sessionFuture);

            return new Closing();
        } else if (e instanceof ChannelInactiveEvent) {
            Reactivating reactivating = new Reactivating();

            reactivateSessionAsync(fsm, session, reactivating.getSessionFuture());

            return reactivating;
        } else {
            return this;
        }
    }


    private static class InactivityHandler extends ChannelInboundHandlerAdapter {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final SessionFsm fsm;

        InactivityHandler(SessionFsm fsm) {
            this.fsm = fsm;
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            logger.info(
                "[local={}, remote={}] channelInactive()",
                ctx.channel().localAddress(),
                ctx.channel().remoteAddress()
            );

            fsm.fireEvent(new ChannelInactiveEvent());

            super.channelInactive(ctx);
        }

    }

    private static class FaultListener implements ServiceFaultListener {

        private static final Predicate<StatusCode> sessionError = statusCode -> {
            long status = statusCode.getValue();

            return status == StatusCodes.Bad_SessionClosed ||
                status == StatusCodes.Bad_SessionIdInvalid ||
                status == StatusCodes.Bad_SessionNotActivated;
        };

        private static final Predicate<StatusCode> secureChannelError = statusCode -> {
            long status = statusCode.getValue();

            return status == StatusCodes.Bad_SecureChannelIdInvalid ||
                status == StatusCodes.Bad_SecurityChecksFailed ||
                status == StatusCodes.Bad_TcpSecureChannelUnknown;
        };

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final SessionFsm fsm;

        public FaultListener(SessionFsm fsm) {
            this.fsm = fsm;
        }

        @Override
        public void onServiceFault(ServiceFault serviceFault) {
            StatusCode serviceResult = serviceFault.getResponseHeader().getServiceResult();

            if (sessionError.or(secureChannelError).test(serviceResult)) {
                logger.debug("ServiceFault: {}", serviceResult);

                fsm.getClient().removeFaultListener(FaultListener.this);

                // TODO this should probably be its own event type instead
                fsm.getClient().getStackClient().getChannelFuture().thenAccept(c -> c.getChannel().close());
            }
        }

    }

}

/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.stack.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

class ClientChannelManager {

    private static final int MAX_RECONNECT_DELAY_SECONDS = 16;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ScheduledExecutorService scheduledExecutor = Stack.sharedScheduledExecutor();

    private final AtomicReference<State> state = new AtomicReference<>(new NotConnected());
    private final AtomicReference<ScheduledFuture<?>> reconnectFuture = new AtomicReference<>();

    private final UaTcpStackClient client;

    ClientChannelManager(UaTcpStackClient client) {
        this.client = client;
    }

    public CompletableFuture<ClientSecureChannel> connect() {
        State currentState = state.get();

        logger.debug("connect(), currentState={}",
            currentState.getClass().getSimpleName());

        if (currentState instanceof NotConnected) {
            Connecting nextState = new Connecting();

            if (state.compareAndSet(currentState, nextState)) {
                logger.debug("connect() while NotConnected", new Exception());

                CompletableFuture<ClientSecureChannel> connected = nextState.connected;

                connect(connected);

                return connected.whenCompleteAsync(
                    (chan, ex) -> {
                        if (chan != null) {
                            if (state.compareAndSet(nextState, new Connected(connected))) {
                                chan.getChannel().pipeline().addLast(new InactivityHandler());
                            }
                        } else {
                            state.compareAndSet(nextState, new NotConnected());
                        }
                    },
                    client.getExecutorService()
                );
            } else {
                return connect();
            }
        } else if (currentState instanceof Connecting) {
            return ((Connecting) currentState).connected;
        } else if (currentState instanceof Connected) {
            return ((Connected) currentState).connected;
        } else if (currentState instanceof Reconnecting) {
            return ((Reconnecting) currentState).reconnected;
        } else if (currentState instanceof Disconnecting) {
            CompletableFuture<ClientSecureChannel> future = new CompletableFuture<>();

            CompletableFuture<Unit> disconnectFuture = ((Disconnecting) currentState).disconnectFuture;

            disconnectFuture.whenCompleteAsync(
                (unit, ex) -> connect().whenCompleteAsync(
                    (chan, ex2) -> {
                        if (chan != null) future.complete(chan);
                        else future.completeExceptionally(ex2);
                    },
                    client.getExecutorService()
                ),
                client.getExecutorService()
            );

            return future;
        } else {
            throw new IllegalStateException(currentState.getClass().getSimpleName());
        }
    }

    public CompletableFuture<Unit> disconnect() {
        State currentState = state.get();

        logger.debug("disconnect(), currentState={}",
            currentState.getClass().getSimpleName());

        if (currentState instanceof NotConnected) {
            CompletableFuture<Unit> f = new CompletableFuture<>();
            f.complete(Unit.VALUE);
            return f;
        } else if (currentState instanceof Connected) {
            Disconnecting disconnecting = new Disconnecting();

            if (state.compareAndSet(currentState, disconnecting)) {
                ((Connected) currentState).connected.whenCompleteAsync(
                    (chan, ex) -> {
                        if (chan != null) {
                            disconnect(chan, disconnecting.disconnectFuture);
                        } else {
                            disconnecting.disconnectFuture.complete(null);
                        }

                        disconnecting.disconnectFuture.whenComplete((unit, ex2) -> {
                            if (state.compareAndSet(disconnecting, new NotConnected())) {
                                logger.debug("disconnect complete, state set to Idle");
                            }
                        });
                    },
                    client.getExecutorService()
                );

                return disconnecting.disconnectFuture;
            } else {
                return disconnect();
            }
        } else if (currentState instanceof Connecting) {
            Disconnecting disconnecting = new Disconnecting();

            if (state.compareAndSet(currentState, disconnecting)) {
                ((Connecting) currentState).connected.whenCompleteAsync(
                    (chan, ex) -> {
                        if (chan != null) {
                            disconnect(chan, disconnecting.disconnectFuture);
                        } else {
                            disconnecting.disconnectFuture.complete(Unit.VALUE);
                        }

                        disconnecting.disconnectFuture.whenComplete((unit, ex2) -> {
                            if (state.compareAndSet(disconnecting, new NotConnected())) {
                                logger.debug("disconnect complete, state set to Idle");
                            }
                        });
                    },
                    client.getExecutorService()
                );

                return disconnecting.disconnectFuture;
            } else {
                return disconnect();
            }
        } else if (currentState instanceof Reconnecting) {
            Disconnecting disconnecting = new Disconnecting();

            if (state.compareAndSet(currentState, disconnecting)) {
                Reconnecting reconnecting = (Reconnecting) currentState;

                ScheduledFuture<?> future = reconnectFuture.get();

                if (future != null && future.cancel(false)) {
                    reconnecting.reconnected.completeExceptionally(
                        new UaException(StatusCodes.Bad_ConnectionClosed));

                    disconnecting.disconnectFuture.complete(Unit.VALUE);
                } else {
                    reconnecting.reconnected.whenCompleteAsync(
                        (chan, ex) -> {
                            if (chan != null) {
                                disconnect(chan, disconnecting.disconnectFuture);
                            } else {
                                disconnecting.disconnectFuture.complete(Unit.VALUE);
                            }

                            disconnecting.disconnectFuture.whenComplete((unit, ex2) -> {
                                if (state.compareAndSet(disconnecting, new NotConnected())) {
                                    logger.debug("disconnect complete, state set to Idle");
                                }
                            });
                        },
                        client.getExecutorService()
                    );
                }

                return disconnecting.disconnectFuture;
            } else {
                return disconnect();
            }
        } else if (currentState instanceof Disconnecting) {
            return ((Disconnecting) currentState).disconnectFuture;
        } else {
            throw new IllegalStateException(currentState.getClass().getSimpleName());
        }
    }

    CompletableFuture<ClientSecureChannel> getChannel() {
        State currentState = state.get();

        logger.trace("getChannel(), currentState={}",
            currentState.getClass().getSimpleName());

        if (currentState instanceof NotConnected) {
            return failedUaFuture(StatusCodes.Bad_ServerNotConnected);
        } else if (currentState instanceof Connecting) {
            return ((Connecting) currentState).connected;
        } else if (currentState instanceof Connected) {
            return ((Connected) currentState).connected;
        } else if (currentState instanceof Reconnecting) {
            return ((Reconnecting) currentState).reconnected;
        } else if (currentState instanceof Disconnecting) {
            CompletableFuture<Unit> disconnectFuture =
                ((Disconnecting) currentState).disconnectFuture;

            // wait for disconnect to complete and then try again to see where
            // we're at; maybe someone called connect() after disconnect()?
            return disconnectFuture
                .exceptionally(ex -> Unit.VALUE)
                .thenComposeAsync(u -> getChannel(), client.getExecutorService());
        } else {
            throw new IllegalStateException(currentState.getClass().getSimpleName());
        }
    }

    private void connect(CompletableFuture<ClientSecureChannel> future) {
        try {
            CompletableFuture<ClientSecureChannel> bootstrap =
                UaTcpStackClient.bootstrap(client);

            bootstrap.whenCompleteAsync(
                (chan, ex) -> {
                    if (chan != null) {
                        logger.debug(
                            "Channel bootstrap succeeded: localAddress={}, remoteAddress={}",
                            chan.getChannel().localAddress(), chan.getChannel().remoteAddress());

                        future.complete(chan);
                    } else {
                        logger.debug("Channel bootstrap failed: {}", ex.getMessage(), ex);

                        future.completeExceptionally(ex);
                    }
                },
                client.getExecutorService()
            );
        } catch (Throwable t) {
            future.completeExceptionally(t);
        }
    }

    private void reconnect(Reconnecting reconnectState, long delaySeconds, ClientSecureChannel previousChannel) {
        logger.debug("Scheduling reconnect for +{} seconds...", delaySeconds);

        try {
            ScheduledFuture<?> future = scheduledExecutor.schedule(() -> {
                logger.debug("{} seconds elapsed; reconnecting...", delaySeconds);

                CompletableFuture<ClientSecureChannel> reconnected = reconnectState.reconnected;

                connect(reconnected);

                reconnected.whenCompleteAsync(
                    (chan, ex) -> {
                        reconnectFuture.set(null);

                        if (chan != null) {
                            logger.debug("Reconnect succeeded, channelId={}", chan.getChannelId());

                            if (state.compareAndSet(reconnectState, new Connected(reconnected))) {
                                chan.getChannel().pipeline().addLast(new InactivityHandler());
                            }
                        } else {
                            logger.debug("Reconnect failed: {}", ex.getMessage(), ex);

                            Reconnecting nextState = new Reconnecting();
                            if (state.compareAndSet(reconnectState, nextState)) {
                                reconnect(nextState, nextDelay(delaySeconds), previousChannel);
                            }
                        }
                    },
                    client.getExecutorService()
                );
            }, delaySeconds, TimeUnit.SECONDS);

            reconnectFuture.set(future);
        } catch (RejectedExecutionException e) {
            // This can happen if Stack shared resources have been released.
            logger.debug("Reconnect task execution was rejected: {}", e.getMessage(), e);
            reconnectState.reconnected.completeExceptionally(e);
            state.compareAndSet(reconnectState, new NotConnected());
        }
    }

    private void disconnect(ClientSecureChannel secureChannel, CompletableFuture<Unit> disconnected) {
        RequestHeader requestHeader = new RequestHeader(
            NodeId.NULL_VALUE, DateTime.now(), uint(0), uint(0), null, uint(0), null);

        secureChannel.getChannel().pipeline().addLast(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                logger.debug("channelInactive(), disconnect complete");
                disconnected.complete(Unit.VALUE);
                super.channelInactive(ctx);
            }
        });

        logger.debug("Sending CloseSecureChannelRequest...");
        CloseSecureChannelRequest request = new CloseSecureChannelRequest(requestHeader);
        secureChannel.getChannel().pipeline().fireUserEventTriggered(request);

        client.getConfig().getWheelTimer().newTimeout(
            timeout -> disconnected.completeExceptionally(new UaException(StatusCodes.Bad_Timeout)),
            5,
            TimeUnit.SECONDS
        );
    }

    private static long nextDelay(long delaySeconds) {
        if (delaySeconds == 0) {
            return 1;
        } else {
            return Math.min(delaySeconds << 1, MAX_RECONNECT_DELAY_SECONDS);
        }
    }

    private class InactivityHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            State currentState = state.get();

            if (currentState instanceof Connected) {
                Reconnecting nextState = new Reconnecting();

                if (state.compareAndSet(currentState, nextState)) {
                    ClientSecureChannel channel =
                        ((Connected) currentState).connected.get();

                    reconnect(nextState, 0L, channel);
                }
            }

            super.channelInactive(ctx);
        }
    }

    private interface State {
    }

    private static class NotConnected implements State {

    }

    private static class Connecting implements State {
        final CompletableFuture<ClientSecureChannel> connected = new CompletableFuture<>();
    }

    private static class Connected implements State {
        final CompletableFuture<ClientSecureChannel> connected;

        Connected(CompletableFuture<ClientSecureChannel> connected) {
            this.connected = connected;
        }
    }

    private static class Reconnecting implements State {
        final CompletableFuture<ClientSecureChannel> reconnected = new CompletableFuture<>();
    }

    private static class Disconnecting implements State {
        final CompletableFuture<Unit> disconnectFuture = new CompletableFuture<>();
    }

}

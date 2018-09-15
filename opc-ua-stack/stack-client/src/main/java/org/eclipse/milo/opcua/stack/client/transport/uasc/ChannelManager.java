/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.client.transport.uasc;

import java.net.ConnectException;
import java.net.URI;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler.ClientHandshakeStateEvent;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedUaFuture;

public class ChannelManager {

    private static final int MAX_RECONNECT_DELAY_SECONDS = 16;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ScheduledExecutorService scheduledExecutor = Stack.sharedScheduledExecutor();

    private final AtomicReference<State> state = new AtomicReference<>(new NotConnected());
    private final AtomicReference<ScheduledFuture<?>> reconnectFuture = new AtomicReference<>();

    private final UaStackClientConfig config;

    public ChannelManager(UaStackClientConfig config) {
        this.config = config;
    }

    public CompletableFuture<ClientSecureChannel> connect() {
        State currentState = state.get();

        logger.debug("connect(), currentState={}",
            currentState.getClass().getSimpleName());

        if (currentState instanceof NotConnected) {
            Connecting connectingState = new Connecting();

            if (state.compareAndSet(currentState, connectingState)) {
                logger.debug("connect() while NotConnected", new Exception());

                CompletableFuture<ClientSecureChannel> connected = connectingState.connected;

                connect(connected);

                return connected.whenCompleteAsync(
                    (chan, ex) -> {
                        if (chan != null) {
                            if (state.compareAndSet(connectingState, new Connected(connected))) {
                                chan.getChannel().pipeline().addLast(new InactivityHandler());
                            }
                        } else {
                            state.compareAndSet(connectingState, new NotConnected());
                        }
                    },
                    config.getExecutor()
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
                (unit, ex) -> {
                    connect().whenCompleteAsync(
                        (chan, ex2) -> {
                            if (chan != null) future.complete(chan);
                            else future.completeExceptionally(ex2);
                        },
                        config.getExecutor()
                    );
                },
                config.getExecutor()
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
                                logger.debug("disconnect complete, state set to NotConnected");
                            } else {
                                logger.debug("disconnect complete, currentState=" + state.get());
                            }
                        });
                    },
                    config.getExecutor()
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
                                logger.debug("disconnect complete, state set to NotConnected");
                            } else {
                                logger.debug("disconnect complete, currentState=" + state.get());
                            }
                        });
                    },
                    config.getExecutor()
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
                        },
                        config.getExecutor()
                    );
                }

                disconnecting.disconnectFuture.whenComplete((unit, ex2) -> {
                    NotConnected notConnected = new NotConnected();

                    if (state.compareAndSet(reconnecting, notConnected) ||
                        state.compareAndSet(disconnecting, notConnected)) {

                        logger.debug("disconnect complete, state set to Idle");
                    } else {
                        logger.debug("disconnect complete, currentState=" + state.get());
                    }
                });

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

    public CompletableFuture<ClientSecureChannel> getChannel() {
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
                .thenComposeAsync(u -> getChannel(), config.getExecutor());
        } else {
            throw new IllegalStateException(currentState.getClass().getSimpleName());
        }
    }

    private void connect(CompletableFuture<ClientSecureChannel> future) {
        try {
            bootstrap(config).whenCompleteAsync(
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
                config.getExecutor()
            );
        } catch (Throwable t) {
            future.completeExceptionally(t);
        }
    }

    private void reconnect(Reconnecting reconnectState, long delaySeconds) {
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
                                reconnect(nextState, nextDelay(delaySeconds));
                            }
                        }
                    },
                    config.getExecutor()
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

        config.getWheelTimer().newTimeout(
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
                    reconnect(nextState, 0L);
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

    // TODO pass initializer in instead? Seems weird ChannelManager has to know about these 2 initializers
    public static CompletableFuture<ClientSecureChannel> bootstrap(UaStackClientConfig config) {
        CompletableFuture<ClientSecureChannel> handshake = new CompletableFuture<>();

        String transportProfileUri = config.getEndpoint().getTransportProfileUri();
        TransportProfile transportProfile = TransportProfile.fromUri(transportProfileUri);

        ChannelInitializer<SocketChannel> initializer;

        if (transportProfile == TransportProfile.TCP_UASC_UABINARY) {
            initializer = new OpcTcpChannelInitializer(config, handshake);
        } else {
            initializer = new OpcWebSocketChannelInitializer(config, handshake);
        }

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(config.getEventLoop())
            .channelFactory(NioSocketChannel::new)
            .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.getConnectTimeout().intValue())
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(initializer);

        try {
            String endpointUrl = config.getEndpoint().getEndpointUrl();

            // TODO EndpointUtil should handle this instead of using URI
            URI uri = new URI(endpointUrl).parseServerAuthority();

            bootstrap.connect(uri.getHost(), uri.getPort()).addListener((ChannelFuture f) -> {
                if (!f.isSuccess()) {
                    Throwable cause = f.cause();

                    if (cause instanceof ConnectTimeoutException) {
                        handshake.completeExceptionally(
                            new UaException(StatusCodes.Bad_Timeout, f.cause()));
                    } else if (cause instanceof ConnectException) {
                        handshake.completeExceptionally(
                            new UaException(StatusCodes.Bad_ConnectionRejected, f.cause()));
                    } else {
                        handshake.completeExceptionally(cause);
                    }
                }
            });
        } catch (Throwable e) {
            UaException failure = new UaException(
                StatusCodes.Bad_TcpEndpointUrlInvalid, e);

            handshake.completeExceptionally(failure);
        }

        return handshake;
    }

    private static ClientSecureChannel createSecureChannel(UaStackClientConfig config) throws UaException {
        EndpointDescription endpoint = config.getEndpoint();

        SecurityPolicy securityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());

        if (securityPolicy == SecurityPolicy.None) {
            return new ClientSecureChannel(
                securityPolicy,
                endpoint.getSecurityMode()
            );
        } else {
            KeyPair keyPair = config.getKeyPair().orElseThrow(() ->
                new UaException(
                    StatusCodes.Bad_ConfigurationError,
                    "no KeyPair configured")
            );

            X509Certificate certificate = config.getCertificate().orElseThrow(() ->
                new UaException(
                    StatusCodes.Bad_ConfigurationError,
                    "no certificate configured")
            );

            List<X509Certificate> certificateChain = Arrays.asList(
                config.getCertificateChain().orElseThrow(() ->
                    new UaException(
                        StatusCodes.Bad_ConfigurationError,
                        "no certificate chain configured"))
            );

            X509Certificate remoteCertificate = CertificateUtil
                .decodeCertificate(endpoint.getServerCertificate().bytes());

            List<X509Certificate> remoteCertificateChain = CertificateUtil
                .decodeCertificates(endpoint.getServerCertificate().bytes());

            return new ClientSecureChannel(
                keyPair,
                certificate,
                certificateChain,
                remoteCertificate,
                remoteCertificateChain,
                securityPolicy,
                endpoint.getSecurityMode()
            );
        }
    }

    private static class OpcTcpChannelInitializer extends ChannelInitializer<SocketChannel> {

        private final UaStackClientConfig config;
        private final CompletableFuture<ClientSecureChannel> handshake;

        public OpcTcpChannelInitializer(
            UaStackClientConfig config,
            CompletableFuture<ClientSecureChannel> handshake) {

            this.config = config;
            this.handshake = handshake;
        }

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            ClientSecureChannel secureChannel;

            secureChannel = createSecureChannel(config);

            UascClientAcknowledgeHandler acknowledgeHandler = new UascClientAcknowledgeHandler(
                config,
                secureChannel,
                handshake
            );

            channel.pipeline().addLast(acknowledgeHandler);
        }

    }

    private static class OpcWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

        private final UaStackClientConfig config;
        private final CompletableFuture<ClientSecureChannel> handshake;

        public OpcWebSocketChannelInitializer(
            UaStackClientConfig config,
            CompletableFuture<ClientSecureChannel> handshake) {

            this.config = config;
            this.handshake = handshake;
        }

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            String endpointUrl = config.getEndpoint().getEndpointUrl();
            String scheme = EndpointUtil.getScheme(endpointUrl);

            if ("opc.wss".equalsIgnoreCase(scheme)) {
                SslContext sslContext = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();

                channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
            }

            int maxMessageSize = config.getChannelConfig().getMaxMessageSize();

            channel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
            channel.pipeline().addLast(new HttpClientCodec());
            channel.pipeline().addLast(new HttpObjectAggregator(maxMessageSize));

            channel.pipeline().addLast(
                new WebSocketClientProtocolHandler(
                    WebSocketClientHandshakerFactory.newHandshaker(
                        new URI(endpointUrl),
                        WebSocketVersion.V13,
                        "opcua+uajson",
                        true,
                        new DefaultHttpHeaders(),
                        config.getChannelConfig().getMaxChunkSize()
                    )
                )
            );

            channel.pipeline().addLast(new WebSocketFrameAggregator(config.getChannelConfig().getMaxMessageSize()));

            // OpcClientWebSocketFrameCodec adds UascClientAcknowledgeHandler when the WS upgrade is done.
            channel.pipeline().addLast(new OpcClientWebSocketFrameCodec(config, handshake));
        }

    }

    private static class OpcClientWebSocketFrameCodec extends MessageToMessageCodec<WebSocketFrame, ByteBuf> {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final UaStackClientConfig config;
        private final CompletableFuture<ClientSecureChannel> handshake;

        public OpcClientWebSocketFrameCodec(
            UaStackClientConfig config,
            CompletableFuture<ClientSecureChannel> handshake) {

            this.config = config;
            this.handshake = handshake;
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object event) throws Exception {
            if (event instanceof ClientHandshakeStateEvent) {
                logger.info("WebSocket handshake event: " + event);

                if (event == ClientHandshakeStateEvent.HANDSHAKE_COMPLETE) {
                    ctx.pipeline().addLast(
                        new UascClientAcknowledgeHandler(
                            config,
                            createSecureChannel(config),
                            handshake
                        )
                    );
                }
            }
        }

        @Override
        protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
            out.add(new BinaryWebSocketFrame(msg.retain()));
        }

        @Override
        protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
            if (msg instanceof BinaryWebSocketFrame) {
                out.add(msg.content().retain());
            } else if (msg instanceof TextWebSocketFrame) {
                TextWebSocketFrame textFrame = (TextWebSocketFrame) msg;

                logger.info("Received WebSocket frame:\n" + textFrame.text());
            }
        }

    }

}

/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.websocket;

import java.net.ConnectException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.digitalpetri.netty.fsm.ChannelActions;
import com.digitalpetri.netty.fsm.ChannelFsm;
import com.digitalpetri.netty.fsm.ChannelFsmConfig;
import com.digitalpetri.netty.fsm.ChannelFsmFactory;
import com.digitalpetri.netty.fsm.Event;
import com.digitalpetri.netty.fsm.State;
import com.digitalpetri.strictmachine.FsmContext;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.eclipse.milo.opcua.stack.client.transport.uasc.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.transport.AbstractTransport;
import org.eclipse.milo.opcua.stack.transport.OpcTransportConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcWebSocketTransport extends AbstractTransport {

    private static final String CHANNEL_FSM_LOGGER_NAME = "org.eclipse.milo.opcua.stack.client.ChannelFsm";

    private final ChannelFsm channelFsm;

    public OpcWebSocketTransport(OpcTransportConfig config) {
        super(config);

        // TODO use configurable executors
        ChannelFsmConfig fsmConfig = ChannelFsmConfig.newBuilder()
            .setLazy(false) // reconnect immediately
            .setMaxIdleSeconds(0) // keep alive handled by SessionFsm
            .setMaxReconnectDelaySeconds(16)
            .setPersistent(true)
            .setChannelActions(new ClientChannelActions(config))
            .setExecutor(Stack.sharedExecutor())
            .setScheduler(Stack.sharedScheduledExecutor())
            .setLoggerName(CHANNEL_FSM_LOGGER_NAME)
            .build();

        var factory = new ChannelFsmFactory(fsmConfig);

        channelFsm = factory.newChannelFsm();
    }

    @Override
    public CompletableFuture<Unit> connect() {
        return channelFsm.connect().thenApply(c -> Unit.VALUE);
    }

    @Override
    public CompletableFuture<Unit> disconnect() {
        return channelFsm.disconnect().thenApply(v -> Unit.VALUE);
    }

    @Override
    protected CompletableFuture<Channel> getChannel() {
        return channelFsm.getChannel();
    }

    private class ClientChannelActions implements ChannelActions {

        private final Logger logger = LoggerFactory.getLogger(CHANNEL_FSM_LOGGER_NAME);

        private final OpcTransportConfig config;

        private ClientChannelActions(OpcTransportConfig config) {
            this.config = config;
        }

        @Override
        public CompletableFuture<Channel> connect(FsmContext<State, Event> ctx) {
            var handshakeFuture = new CompletableFuture<ClientSecureChannel>();

            var bootstrap = new Bootstrap();

            bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5_000)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        String endpointUrl = config.getEndpoint().getEndpointUrl();
                        String scheme = EndpointUtil.getScheme(endpointUrl);

                        TransportProfile transportProfile = TransportProfile
                            .fromUri(config.getEndpoint().getTransportProfileUri());

                        String subprotocol;
                        if (transportProfile == TransportProfile.WSS_UASC_UABINARY) {
                            subprotocol = "opcua+cp";
                        } else if (transportProfile == TransportProfile.WSS_UAJSON) {
                            subprotocol = "opcua+uajson";
                        } else {
                            throw new UaException(
                                StatusCodes.Bad_InternalError,
                                "unexpected TransportProfile: " + transportProfile
                            );
                        }

                        if ("opc.wss".equalsIgnoreCase(scheme)) {
                            SslContext sslContext = SslContextBuilder.forClient()
                                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                                .build();

                            channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
                        }

                        int maxMessageSize = config.getEncodingLimits().getMaxMessageSize();

                        channel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                        channel.pipeline().addLast(new HttpClientCodec());
                        channel.pipeline().addLast(new HttpObjectAggregator(maxMessageSize));

                        channel.pipeline().addLast(
                            new WebSocketClientProtocolHandler(
                                WebSocketClientHandshakerFactory.newHandshaker(
                                    new URI(endpointUrl),
                                    WebSocketVersion.V13,
                                    subprotocol,
                                    true,
                                    new DefaultHttpHeaders(),
                                    config.getEncodingLimits().getMaxChunkSize()
                                )
                            )
                        );

                        channel.pipeline().addLast(
                            new WebSocketFrameAggregator(config.getEncodingLimits().getMaxMessageSize())
                        );

                        // OpcClientWebSocketFrameCodec adds UascClientAcknowledgeHandler when the WS upgrade is done.
                        channel.pipeline().addLast(new OpcClientWebSocketBinaryFrameCodec(config, requestId::getAndIncrement, handshakeFuture));
                    }
                });

            String endpointUrl = config.getEndpoint().getEndpointUrl();

            String host = EndpointUtil.getHost(endpointUrl);
            assert host != null;

            int port = EndpointUtil.getPort(endpointUrl);

            bootstrap.connect(host, port).addListener((ChannelFuture f) -> {
                if (!f.isSuccess()) {
                    Throwable cause = f.cause();

                    if (cause instanceof ConnectTimeoutException) {
                        handshakeFuture.completeExceptionally(
                            new UaException(StatusCodes.Bad_Timeout, f.cause())
                        );
                    } else if (cause instanceof ConnectException) {
                        handshakeFuture.completeExceptionally(
                            new UaException(StatusCodes.Bad_ConnectionRejected, f.cause())
                        );
                    } else {
                        handshakeFuture.completeExceptionally(cause);
                    }
                }
            });

            return handshakeFuture.thenApply(ClientSecureChannel::getChannel);
        }

        @Override
        public CompletableFuture<Void> disconnect(FsmContext<State, Event> ctx, Channel channel) {
            var disconnectFuture = new CompletableFuture<Void>();

            TimerTask onTimeout = t -> channel.close().addListener(
                (ChannelFutureListener) channelFuture ->
                    disconnectFuture.complete(null)
            );

            Timeout timeout = config.getWheelTimer().newTimeout(
                onTimeout,
                5,
                TimeUnit.SECONDS
            );

            channel.pipeline().addFirst(new ChannelInboundHandlerAdapter() {
                @Override
                public void channelInactive(ChannelHandlerContext channelContext) throws Exception {
                    logger.debug("[{}] channelInactive() disconnect complete", ctx.getInstanceId());
                    timeout.cancel();
                    disconnectFuture.complete(null);
                    super.channelInactive(channelContext);
                }
            });

            var requestHeader = new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.now(),
                uint(0),
                uint(0),
                null,
                uint(0),
                null
            );

            logger.debug("[{}] Sending CloseSecureChannelRequest...", ctx.getInstanceId());

            channel.pipeline().fireUserEventTriggered(new CloseSecureChannelRequest(requestHeader));

            return disconnectFuture;
        }

    }


}

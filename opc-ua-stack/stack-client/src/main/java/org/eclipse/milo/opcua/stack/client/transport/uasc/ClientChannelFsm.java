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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.digitalpetri.netty.fsm.ChannelFsm;
import com.digitalpetri.netty.fsm.ChannelFsmConfig;
import com.digitalpetri.netty.fsm.ChannelFsmFactory;
import com.digitalpetri.netty.fsm.ConnectProxy;
import com.digitalpetri.netty.fsm.DisconnectProxy;
import com.digitalpetri.netty.fsm.Event;
import com.digitalpetri.netty.fsm.KeepAliveProxy;
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
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.tcp.OpcClientTcpChannelInitializer;
import org.eclipse.milo.opcua.stack.client.transport.websocket.OpcClientWebSocketChannelInitializer;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ClientChannelFsm {

    private static final String CHANNEL_FSM_LOGGER_NAME = "org.eclipse.milo.opcua.stack.client.ChannelFsm";

    public static ChannelFsm newChannelFsm(UaStackClientConfig config) {
        ChannelFsmProxy fsmProxy = new ChannelFsmProxy(config);

        ChannelFsmConfig fsmConfig = ChannelFsmConfig.newBuilder()
            .setLazy(false)
            .setPersistent(true)
            .setMaxIdleSeconds(0)
            .setMaxReconnectDelaySeconds(16)
            .setExecutor(Stack.sharedExecutor())
            .setScheduler(Stack.sharedScheduledExecutor())
            .setConnectProxy(fsmProxy)
            .setDisconnectProxy(fsmProxy)
            .setKeepAliveProxy(fsmProxy)
            .setLoggerName(CHANNEL_FSM_LOGGER_NAME)
            .build();

        ChannelFsmFactory fsmFactory = new ChannelFsmFactory(fsmConfig);

        return fsmFactory.newChannelFsm();
    }

    private static class ChannelFsmProxy implements ConnectProxy, DisconnectProxy, KeepAliveProxy {

        private static final Logger LOGGER = LoggerFactory.getLogger(CHANNEL_FSM_LOGGER_NAME);

        private final UaStackClientConfig config;

        ChannelFsmProxy(UaStackClientConfig config) {
            this.config = config;
        }

        @Override
        public CompletableFuture<Channel> connect(FsmContext<State, Event> ctx) {
            CompletableFuture<ClientSecureChannel> handshake = new CompletableFuture<>();

            String transportProfileUri = config.getEndpoint().getTransportProfileUri();
            TransportProfile transportProfile = TransportProfile.fromUri(transportProfileUri);

            ChannelInitializer<SocketChannel> initializer;

            if (transportProfile == TransportProfile.TCP_UASC_UABINARY) {
                initializer = new OpcClientTcpChannelInitializer(config, handshake);
            } else {
                initializer = new OpcClientWebSocketChannelInitializer(config, handshake);
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

                String host = EndpointUtil.getHost(endpointUrl);
                assert host != null;

                int port = EndpointUtil.getPort(endpointUrl);

                bootstrap.connect(host, port).addListener((ChannelFuture f) -> {
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

            return handshake.thenApply(ClientSecureChannel::getChannel);
        }

        @Override
        public CompletableFuture<Void> disconnect(FsmContext<State, Event> ctx, Channel channel) {
            CompletableFuture<Void> disconnectFuture = new CompletableFuture<>();

            final TimerTask onTimeout = t -> channel.close().addListener(
                (ChannelFutureListener) channelFuture ->
                    disconnectFuture.complete(null)
            );

            final Timeout timeout = config.getWheelTimer().newTimeout(
                onTimeout,
                5,
                TimeUnit.SECONDS
            );

            channel.pipeline().addFirst(new ChannelInboundHandlerAdapter() {
                @Override
                public void channelInactive(ChannelHandlerContext channelContext) throws Exception {
                    LOGGER.debug("[{}] channelInactive(), disconnect complete", ctx.getInstanceId());
                    timeout.cancel();
                    disconnectFuture.complete(null);
                    super.channelInactive(channelContext);
                }
            });

            RequestHeader requestHeader = new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.now(),
                uint(0),
                uint(0),
                null,
                uint(0),
                null
            );

            LOGGER.debug("[{}] Sending CloseSecureChannelRequest...", ctx.getInstanceId());

            channel.pipeline().fireUserEventTriggered(
                new CloseSecureChannelRequest(requestHeader));

            return disconnectFuture;
        }

        @Override
        public CompletableFuture<Void> keepAlive(FsmContext<State, Event> ctx, Channel channel) {
            return CompletableFuture.completedFuture(null);
        }

    }
}

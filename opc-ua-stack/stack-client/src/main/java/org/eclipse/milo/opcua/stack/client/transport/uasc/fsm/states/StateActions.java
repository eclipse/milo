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

package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states;

import java.net.ConnectException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.tcp.OpcClientTcpChannelInitializer;
import org.eclipse.milo.opcua.stack.client.transport.uasc.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectFailure;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectSuccess;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.DisconnectSuccess;
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

class StateActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelFsm.class);

    private StateActions() {}

    // <editor-fold desc="Connect">
    static void connectAsync(ChannelFsm fsm) {
        fsm.getExecutor().execute(() -> connect(fsm));
    }

    private static void connect(ChannelFsm fsm) {
        bootstrap(fsm.getConfig()).whenComplete(
            (channel, ex) -> {
                if (channel != null) {
                    LOGGER.debug(
                        "Channel bootstrap succeeded: localAddress={}, remoteAddress={}",
                        channel.localAddress(), channel.remoteAddress());

                    fsm.fireEvent(new ConnectSuccess(channel));
                } else {
                    LOGGER.debug("Channel bootstrap failed: {}", ex.getMessage(), ex);

                    fsm.fireEvent(new ConnectFailure(ex));
                }
            }
        );
    }

    private static CompletableFuture<Channel> bootstrap(UaStackClientConfig config) {
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
    // </editor-fold>

    // <editor-fold desc="Disconnect">
    static void disconnectAsync(ChannelFsm fsm) {
        final CompletableFuture<Channel> channelFuture = fsm.getContext().getChannelFuture();

        if (channelFuture != null) {
            fsm.getExecutor().execute(
                () ->
                    channelFuture.whenComplete((ch, ex) -> {
                        if (ch != null) {
                            disconnect(fsm, ch);
                        } else {
                            fsm.fireEvent(new DisconnectSuccess());
                        }
                    })
            );
        } else {
            fsm.getExecutor().execute(
                () ->
                    fsm.fireEvent(new DisconnectSuccess())
            );
        }
    }

    private static void disconnect(ChannelFsm fsm, Channel channel) {
        final Timeout timeout = fsm.getConfig().getWheelTimer().newTimeout(
            t -> fsm.fireEvent(new DisconnectSuccess()),
            5,
            TimeUnit.SECONDS
        );

        channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                LOGGER.debug("channelInactive(), disconnect complete");
                timeout.cancel();
                fsm.fireEvent(new DisconnectSuccess());
                super.channelInactive(ctx);
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

        LOGGER.debug("Sending CloseSecureChannelRequest...");

        channel.pipeline().fireUserEventTriggered(
            new CloseSecureChannelRequest(requestHeader));
    }
    // </editor-fold>

    // <editor-fold desc="Reconnect">
    static void reconnectAsync(ChannelFsm fsm) {
        long delaySeconds = fsm.getContext().getReconnectDelay();
        LOGGER.debug("Scheduling reconnect for +{} seconds", delaySeconds);

        Stack.sharedScheduledExecutor().schedule(
            () -> connectAsync(fsm),
            delaySeconds,
            TimeUnit.SECONDS
        );
    }
    // </editor-fold>

}

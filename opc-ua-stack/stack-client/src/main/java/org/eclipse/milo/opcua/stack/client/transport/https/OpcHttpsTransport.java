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

package org.eclipse.milo.opcua.stack.client.transport.https;

import java.nio.channels.ClosedChannelException;
import java.util.concurrent.CompletableFuture;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.pool.AbstractChannelPoolHandler;
import io.netty.channel.pool.ChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.concurrent.FutureListener;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.UaTransportRequest;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpcHttpsTransport implements UaTransport {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpcHttpsTransport.class);

    private ChannelPool channelPool = null;

    private final UaStackClientConfig config;

    public OpcHttpsTransport(UaStackClientConfig config) {
        this.config = config;
    }

    @Override
    public synchronized CompletableFuture<UaTransport> connect() {
        if (channelPool == null) {
            channelPool = createChannelPool(config);
        }

        return CompletableFuture.completedFuture(OpcHttpsTransport.this);
    }

    @Override
    public synchronized CompletableFuture<UaTransport> disconnect() {
        if (channelPool != null) {
            channelPool.close();
            channelPool = null;
        }

        return CompletableFuture.completedFuture(OpcHttpsTransport.this);
    }

    @Override
    public synchronized CompletableFuture<UaResponseMessage> sendRequest(UaRequestMessage request) {
        LOGGER.trace("sendRequest({})", request.getClass().getSimpleName());

        return acquireChannel().thenCompose(ch ->
            sendRequest(request, ch, true)
                .whenComplete((response, ex) -> releaseChannel(ch))
        );
    }

    private synchronized CompletableFuture<UaResponseMessage> sendRequest(
        UaRequestMessage request, Channel channel, boolean firstAttempt) {

        UaTransportRequest requestFuture = new UaTransportRequest(request);

        channel.writeAndFlush(requestFuture).addListener(f -> {
            if (!f.isSuccess()) {
                Throwable cause = f.cause();

                if (cause instanceof ClosedChannelException && firstAttempt) {
                    LOGGER.debug("Channel closed; retrying...");

                    config.getExecutor().execute(() ->
                        acquireChannel().whenComplete((ch, ex) -> {
                            if (ch != null) {
                                sendRequest(request, ch, false);
                            } else {
                                requestFuture.getFuture().completeExceptionally(ex);
                            }
                        })
                    );
                } else {
                    requestFuture.getFuture().completeExceptionally(cause);

                    LOGGER.debug(
                        "Write failed, request={}, requestHandle={}",
                        request.getClass().getSimpleName(),
                        request.getRequestHeader().getRequestHandle());
                }
            } else {
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace(
                        "Write succeeded, request={}, requestHandle={}",
                        request.getClass().getSimpleName(),
                        request.getRequestHeader().getRequestHandle());
                }
            }
        });

        return requestFuture.getFuture();
    }

    private synchronized CompletableFuture<Channel> acquireChannel() {
        if (channelPool == null) {
            channelPool = createChannelPool(config);
        }

        CompletableFuture<Channel> future = new CompletableFuture<>();

        channelPool.acquire().addListener((FutureListener<Channel>) cf -> {
            if (cf.isSuccess()) {
                future.complete(cf.getNow());
            } else {
                future.completeExceptionally(cf.cause());
            }
        });

        return future;
    }

    private synchronized void releaseChannel(Channel channel) {
        if (channelPool != null) {
            channelPool.release(channel);
        }
    }

    private static ChannelPool createChannelPool(UaStackClientConfig config) {
        final String endpointUrl = config.getEndpoint().getEndpointUrl();

        String host = EndpointUtil.getHost(endpointUrl);
        if (host == null) host = "";

        int port = EndpointUtil.getPort(endpointUrl);

        Bootstrap bootstrap = new Bootstrap()
            .channelFactory(NioSocketChannel::new)
            .group(config.getEventLoop())
            .remoteAddress(host, port);

        return new SimpleChannelPool(
            bootstrap,
            new AbstractChannelPoolHandler() {
                @Override
                public void channelCreated(Channel channel) throws Exception {
                    String scheme = EndpointUtil.getScheme(endpointUrl);

                    if ("https".equalsIgnoreCase(scheme)) {
                        SslContext sslContext = SslContextBuilder.forClient()
                            .trustManager(InsecureTrustManagerFactory.INSTANCE)
                            .build();

                        channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
                    }

                    int maxMessageSize = config.getChannelConfig().getMaxMessageSize();

                    channel.pipeline().addLast(new LoggingHandler(LogLevel.TRACE));
                    channel.pipeline().addLast(new HttpClientCodec());
                    channel.pipeline().addLast(new HttpObjectAggregator(maxMessageSize));
                    channel.pipeline().addLast(new OpcClientHttpCodec(config));

                    LOGGER.debug("channelCreated(): " + channel);
                }

                @Override
                public void channelAcquired(Channel channel) {
                    LOGGER.debug("channelAcquired(): " + channel);
                }

                @Override
                public void channelReleased(Channel channel) {
                    LOGGER.debug("channelReleased(): " + channel);
                }
            }
        );
    }

}

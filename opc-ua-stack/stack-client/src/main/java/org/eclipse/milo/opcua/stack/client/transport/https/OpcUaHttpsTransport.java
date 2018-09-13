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

import java.net.URL;
import java.nio.channels.ClosedChannelException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.pool.AbstractChannelPoolHandler;
import io.netty.channel.pool.ChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.FutureListener;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.UaTransportRequest;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpcUaHttpsTransport implements UaTransport {

    public static final AttributeKey<UaTransportRequest> KEY_PENDING_REQUEST =
        AttributeKey.newInstance("pendingRequest");

    private static final Logger LOGGER =
        LoggerFactory.getLogger(OpcUaHttpsTransport.class);

    private ChannelPool channelPool = null;

    private final UaStackClientConfig config;

    public OpcUaHttpsTransport(UaStackClientConfig config) {
        this.config = config;
    }

    @Override
    public synchronized CompletableFuture<UaTransport> connect() {
        return acquireChannel().thenApply(ch -> {
            releaseChannel(ch);

            return OpcUaHttpsTransport.this;
        });
    }

    @Override
    public synchronized CompletableFuture<UaTransport> disconnect() {
        if (channelPool != null) {
            channelPool.close();
            channelPool = null;
        }

        return CompletableFuture.completedFuture(OpcUaHttpsTransport.this);
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
                    LOGGER.info("Channel closed; retrying...");

                    config.getExecutor().execute(() ->
                        acquireChannel().thenAccept(ch ->
                            sendRequest(request, ch, false)
                                .whenComplete((r, ex) -> {
                                    if (r != null) {
                                        requestFuture.getFuture().complete(r);
                                    } else {
                                        requestFuture.getFuture().completeExceptionally(ex);
                                    }
                                })
                        )
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
                        "writeAndFlush succeeded for request={}, requestHandle={}",
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
            .channel(NioSocketChannel.class)
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

                    channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                    channel.pipeline().addLast(new HttpClientCodec());
                    channel.pipeline().addLast(new HttpObjectAggregator(maxMessageSize));
                    channel.pipeline().addLast(new OpcHttpRequestEncoder(config));
                    channel.pipeline().addLast(new OpcHttpResponseDecoder(config));

                    LOGGER.debug("channelCreated(): " + channel);
                }

                @Override
                public void channelAcquired(Channel channel) throws Exception {
                    LOGGER.debug("channelAcquired(): " + channel);
                }

                @Override
                public void channelReleased(Channel channel) throws Exception {
                    LOGGER.debug("channelReleased(): " + channel);
                }
            }
        );
    }

    private static class OpcHttpRequestEncoder extends MessageToMessageEncoder<UaTransportRequest> {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final UaStackClientConfig config;

        OpcHttpRequestEncoder(UaStackClientConfig config) {
            this.config = config;
        }

        @Override
        protected void encode(
            ChannelHandlerContext ctx, UaTransportRequest transportRequest, List<Object> encoded) throws Exception {

            logger.debug("encoding: " + transportRequest.getRequest());

            ctx.channel().attr(KEY_PENDING_REQUEST).set(transportRequest);

            ByteBuf content = Unpooled.buffer();

            EndpointDescription endpoint = config.getEndpoint();
            URL endpointUrl = new URL(endpoint.getEndpointUrl());
            String transportProfileUri = endpoint.getTransportProfileUri();

            TransportProfile transportProfile =
                TransportProfile.fromUri(transportProfileUri);

            switch (transportProfile) {
                case HTTPS_UABINARY: {
                    OpcUaBinaryStreamEncoder encoder = new OpcUaBinaryStreamEncoder(content);
                    encoder.writeMessage(null, transportRequest.getRequest());
                    break;
                }

                case HTTPS_UAXML: {
                    OpcUaXmlStreamEncoder encoder = new OpcUaXmlStreamEncoder();
                    encoder.writeMessage(null, transportRequest.getRequest());
                    content.writeBytes(encoder.getDocumentXml().getBytes(StandardCharsets.UTF_8));
                    break;
                }

                default:
                    throw new UaException(StatusCodes.Bad_InternalError,
                        "no encoder for transport: " + transportProfileUri);
            }

            FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1,
                HttpMethod.POST,
                endpointUrl.getPath(),
                content
            );

            httpRequest.headers().set(HttpHeaderNames.HOST, endpointUrl.getHost());
            httpRequest.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            httpRequest.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_OCTET_STREAM);
            httpRequest.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            httpRequest.headers().set("OPCUA-SecurityPolicy", config.getEndpoint().getSecurityPolicyUri());

            encoded.add(httpRequest);
        }

    }

    private static class OpcHttpResponseDecoder extends SimpleChannelInboundHandler<HttpResponse> {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final UaStackClientConfig config;

        OpcHttpResponseDecoder(UaStackClientConfig config) {
            this.config = config;
        }

        @Override
        protected void channelRead0(
            ChannelHandlerContext ctx, HttpResponse httpResponse) {

            logger.trace("channelRead0: " + httpResponse);

            if (httpResponse instanceof FullHttpResponse) {
                FullHttpResponse fullHttpResponse = (FullHttpResponse) httpResponse;

                ByteBuf content = fullHttpResponse.content();

                OpcUaBinaryStreamDecoder decoder = new OpcUaBinaryStreamDecoder(content);

                UaResponseMessage responseMessage = (UaResponseMessage) decoder.readMessage(null);

                UaTransportRequest transportRequest = ctx.channel()
                    .attr(KEY_PENDING_REQUEST)
                    .getAndSet(null);

                logger.debug("decoded: " + responseMessage);

                transportRequest.getFuture().complete(responseMessage);
            }
        }

    }

}

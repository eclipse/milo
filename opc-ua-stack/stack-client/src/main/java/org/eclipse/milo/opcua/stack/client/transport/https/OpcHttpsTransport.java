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

import java.net.MalformedURLException;
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
import io.netty.channel.pool.AbstractChannelPoolHandler;
import io.netty.channel.pool.ChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.FutureListener;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.UaTransportRequest;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
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

    private static class OpcClientHttpCodec extends MessageToMessageCodec<HttpResponse, UaTransportRequest> {

        private static final AttributeKey<UaTransportRequest> KEY_PENDING_REQUEST =
            AttributeKey.newInstance("pendingRequest");

        private static final String UABINARY_CONTENT_TYPE =
            HttpHeaderValues.APPLICATION_OCTET_STREAM.toString();

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final EndpointDescription endpoint;
        private final URL endpointUrl;
        private final TransportProfile transportProfile;

        private final UaStackClientConfig config;

        OpcClientHttpCodec(UaStackClientConfig config) throws MalformedURLException {
            this.config = config;

            endpoint = config.getEndpoint();
            endpointUrl = new URL(endpoint.getEndpointUrl());
            transportProfile = TransportProfile.fromUri(endpoint.getTransportProfileUri());
        }

        @Override
        protected void encode(
            ChannelHandlerContext ctx,
            UaTransportRequest transportRequest,
            List<Object> out) throws Exception {

            logger.debug("encoding: " + transportRequest.getRequest());

            ctx.channel().attr(KEY_PENDING_REQUEST).set(transportRequest);

            ByteBuf content = Unpooled.buffer();

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
                        "no encoder for transport: " + transportProfile);
            }

            FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1,
                HttpMethod.POST,
                endpointUrl.getPath(),
                content
            );

            httpRequest.headers().set(HttpHeaderNames.HOST, endpointUrl.getHost());
            httpRequest.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            httpRequest.headers().set(HttpHeaderNames.CONTENT_TYPE, UABINARY_CONTENT_TYPE);
            httpRequest.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            httpRequest.headers().set("OPCUA-SecurityPolicy", config.getEndpoint().getSecurityPolicyUri());

            out.add(httpRequest);
        }

        @Override
        protected void decode(
            ChannelHandlerContext ctx,
            HttpResponse httpResponse,
            List<Object> out) throws Exception {

            logger.trace("channelRead0: " + httpResponse);

            UaTransportRequest transportRequest = ctx.channel()
                .attr(KEY_PENDING_REQUEST)
                .getAndSet(null);

            if (httpResponse instanceof FullHttpResponse) {
                String contentType = httpResponse.headers().get(HttpHeaderNames.CONTENT_TYPE);

                FullHttpResponse fullHttpResponse = (FullHttpResponse) httpResponse;
                ByteBuf content = fullHttpResponse.content();

                UaResponseMessage responseMessage;

                switch (transportProfile) {
                    case HTTPS_UABINARY: {
                        if (!UABINARY_CONTENT_TYPE.equalsIgnoreCase(contentType)) {
                            throw new UaException(StatusCodes.Bad_DecodingError,
                                "unexpected content-type: " + contentType);
                        }

                        OpcUaBinaryStreamDecoder decoder = new OpcUaBinaryStreamDecoder(content);
                        responseMessage = (UaResponseMessage) decoder.readMessage(null);
                        break;
                    }

                    default:
                        throw new UaException(StatusCodes.Bad_InternalError,
                            "no decoder for transport: " + transportProfile);
                }

                transportRequest.getFuture().complete(responseMessage);
            } else {
                HttpResponseStatus status = httpResponse.status();

                if (status.equals(HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE)) {
                    transportRequest.getFuture().completeExceptionally(
                        new UaException(StatusCodes.Bad_ResponseTooLarge));
                } else {
                    transportRequest.getFuture().completeExceptionally(
                        new UaException(StatusCodes.Bad_UnexpectedError,
                            String.format("%s: %s", status.code(), status.reasonPhrase())));
                }
            }
        }

    }

}

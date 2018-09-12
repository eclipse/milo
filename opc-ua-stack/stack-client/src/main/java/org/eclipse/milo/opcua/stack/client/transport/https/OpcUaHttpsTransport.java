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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.pool.ChannelPool;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.FutureListener;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.AbstractTransport;
import org.eclipse.milo.opcua.stack.client.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.UaTransportRequest;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

public class OpcUaHttpsTransport extends AbstractTransport implements UaTransport {

    public static final AttributeKey<UaTransportRequest> KEY_PENDING_REQUEST =
        AttributeKey.newInstance("pendingRequest");

    ChannelPool channelPool = null;

    private final UaStackClientConfig config;

    public OpcUaHttpsTransport(UaStackClientConfig config) {
        super(config);

        this.config = config;
    }

    @Override
    public synchronized CompletableFuture<UaTransport> connect() {
        if (channelPool == null) {
            // channelPool = ...
        }

        return getChannel(channelPool)
            .thenApply(ch -> channelPool.release(ch))
            .thenApply(v -> OpcUaHttpsTransport.this);
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
    public CompletableFuture<Channel> channel() {
        return getChannel(channelPool);
    }

    private static CompletableFuture<Channel> getChannel(ChannelPool pool) {
        CompletableFuture<Channel> future = new CompletableFuture<>();

        pool.acquire().addListener((FutureListener<Channel>) cf -> {
            if (cf.isSuccess()) {
                future.complete(cf.getNow());
            } else {
                future.completeExceptionally(cf.cause());
            }
        });

        return future;
    }

    private static class TransportRequestEncoder extends MessageToMessageEncoder<UaTransportRequest> {

        private final UaStackClientConfig config;

        public TransportRequestEncoder(UaStackClientConfig config) {
            this.config = config;
        }

        @Override
        protected void encode(
            ChannelHandlerContext ctx, UaTransportRequest transportRequest, List<Object> encoded) throws Exception {

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

            DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1,
                HttpMethod.POST,
                endpointUrl.getPath(),
                content
            );

            httpRequest.headers().set(HttpHeaderNames.HOST, endpointUrl.getHost());
            httpRequest.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            httpRequest.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_OCTET_STREAM);

            encoded.add(httpRequest);
        }

    }

    private static class HttpResponseDecoder extends SimpleChannelInboundHandler<HttpResponse> {

        private final UaStackClientConfig config;

        public HttpResponseDecoder(UaStackClientConfig config) {
            this.config = config;
        }

        @Override
        protected void channelRead0(
            ChannelHandlerContext ctx, HttpResponse httpResponse) {

            if (httpResponse instanceof FullHttpResponse) {
                FullHttpResponse fullHttpResponse = (FullHttpResponse) httpResponse;

                ByteBuf content = fullHttpResponse.content();

                OpcUaBinaryStreamDecoder decoder = new OpcUaBinaryStreamDecoder(content);

                UaResponseMessage responseMessage = (UaResponseMessage) decoder.readMessage(null);

                UaTransportRequest transportRequest = ctx.channel()
                    .attr(KEY_PENDING_REQUEST)
                    .getAndSet(null);

                transportRequest.getFuture().complete(responseMessage);
            }
        }

    }

}

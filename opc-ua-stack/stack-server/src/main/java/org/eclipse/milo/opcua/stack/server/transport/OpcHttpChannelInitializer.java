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

package org.eclipse.milo.opcua.stack.server.transport;

import java.util.List;
import java.util.Objects;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceResponse;

import static io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;

public class OpcHttpChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final String WS_PROTOCOL_BINARY = "opcua+uacp";
    private static final String WS_PROTOCOL_JSON = "opcua+uajson";

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // TODO how is the certificate for HTTPS configured/provided?
        // TODO configure private key and certificate
        SslContext sslContext = SslContextBuilder.forServer(null)
            .clientAuth(ClientAuth.NONE)
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .build();

        channel.pipeline().addLast(RateLimitingHandler.getInstance());
        channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
        channel.pipeline().addLast(new LoggingHandler(LogLevel.TRACE));
        channel.pipeline().addLast(new HttpServerCodec());

        channel.pipeline().addLast(new OpcTransportInitializer());
    }

    private static class OpcTransportInitializer extends SimpleChannelInboundHandler<HttpRequest> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
            // TODO server lookup might have to be by path instead of full endpoint URL?
            String uri = msg.uri();


            if (Objects.equals(msg.method(), HttpMethod.GET) &&
                "websocket".equalsIgnoreCase(msg.headers().get(HttpHeaderValues.UPGRADE))) {

                ctx.channel().pipeline().remove(this);

                ctx.channel().pipeline().addLast(new WebSocketServerCompressionHandler());

                // TODO configure webSocketPath based on path component of endpoint URL?
                ctx.channel().pipeline().addLast(new WebSocketServerProtocolHandler(
                    "/ws",
                    String.format("%s, %s", WS_PROTOCOL_BINARY, WS_PROTOCOL_JSON),
                    true
                ));

                ctx.channel().pipeline().addLast(new OpcServerWebSocketFrameHandler());

                // TODO add UascServerHelloHandler

                ctx.fireChannelRead(msg);
            } else if (Objects.equals(msg.method(), HttpMethod.POST)) {
                ctx.channel().pipeline().remove(this);

                // TODO configure maxContentLength based on MaxRequestSize?
                ctx.channel().pipeline().addLast(new HttpObjectAggregator(Integer.MAX_VALUE));

                ctx.channel().pipeline().addLast(new OpcServerHttpCodec());

                ctx.fireChannelRead(msg);
            } else {
                HttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.BAD_REQUEST
                );

                ctx.channel().writeAndFlush(response)
                    .addListener(future -> ctx.close());
            }
        }
    }

    private static class OpcServerHttpCodec extends MessageToMessageCodec<HttpRequest, ServiceResponse> {

        @Override
        protected void encode(ChannelHandlerContext ctx, ServiceResponse msg, List<Object> out) throws Exception {

        }

        @Override
        protected void decode(ChannelHandlerContext ctx, HttpRequest msg, List<Object> out) throws Exception {

        }

    }

    private static class OpcServerWebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {


        String subprotocol;
        boolean uaScInitiated = false;

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object event) throws Exception {
            if (event instanceof HandshakeComplete) {
                HandshakeComplete handshake = (HandshakeComplete) event;

                handshake.requestUri();
                handshake.requestHeaders();

                subprotocol = handshake.selectedSubprotocol();
            }

            super.userEventTriggered(ctx, event);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
            if (WS_PROTOCOL_BINARY.equalsIgnoreCase(subprotocol)) {
                // Pass the binary contents to the UA Secure Conversation handlers

                if (!uaScInitiated) {
                    // TODO add UaScHelloHandler
                }

                ctx.fireChannelRead(msg.content());
            } else if (WS_PROTOCOL_JSON.equalsIgnoreCase(subprotocol)) {
                // End of the pipeline; decode and deliver

                String text = ((TextWebSocketFrame) msg).text();
                ctx.close();
            } else {
                ctx.close();
            }
        }

    }
}

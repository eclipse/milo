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

package org.eclipse.milo.opcua.stack.server.web;

import java.net.InetSocketAddress;
import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceResponse;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.handlers.RateLimitingHandler;

import static io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;

public class WebServer {

    public static void bind(EndpointConfiguration endpoint, UaStackServer server) {

    }


    static void bootstrap(InetSocketAddress address) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(Stack.sharedEventLoop())
            .channelFactory(NioServerSocketChannel::new)
            .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    // TODO configure private key and certificate
                    SslContext sslContext = SslContextBuilder.forServer(null)
                        .clientAuth(ClientAuth.NONE)
                        .trustManager(InsecureTrustManagerFactory.INSTANCE)
                        .build();

                    channel.pipeline().addLast(RateLimitingHandler.getInstance());
                    channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
                    channel.pipeline().addLast(new LoggingHandler(LogLevel.TRACE));
                    channel.pipeline().addLast(new HttpServerCodec());

                    channel.pipeline().addLast(new TransportInterceptor());
                }
            });

        bootstrap.bind(address).addListener((ChannelFutureListener) future -> {
           if (future.isSuccess()) {
               // TODO
           } else {
               // TODO
           }
        });
    }

    private static class TransportInterceptor extends SimpleChannelInboundHandler<HttpRequest> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
            if (msg.method() == HttpMethod.GET &&
                "websocket".equalsIgnoreCase(msg.headers().get(HttpHeaderValues.UPGRADE))) {

                ctx.channel().pipeline().remove(this);

                ctx.channel().pipeline().addLast(new WebSocketServerCompressionHandler());

                // TODO configure webSocketPath based on path component of endpoint URL?
                ctx.channel().pipeline().addLast(new WebSocketServerProtocolHandler(
                    "/ws",
                    "opcua+uacp",
                    true
                ));

                ctx.channel().pipeline().addLast(new OpcServerWebSocketFrameHandler());

                // TODO add UascServerHelloHandler

                ctx.fireChannelRead(msg);
            } else if (msg.method() == HttpMethod.POST) {
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

        String subprotocol = "opcua+cp";
        boolean uaScInitiated = false;

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof HandshakeComplete) {
                HandshakeComplete handshake = (HandshakeComplete) evt;
                handshake.requestUri();
                handshake.requestHeaders();

                subprotocol = handshake.selectedSubprotocol();
            }

            super.userEventTriggered(ctx, evt);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
            if ("opcua+cp".equalsIgnoreCase(subprotocol)) {
                if (!uaScInitiated) {
                    // TODO add UaScHelloHandler
                }

                assert msg instanceof BinaryWebSocketFrame;

                ctx.fireChannelRead(msg.content());
            } else if ("opcua+uajson".equalsIgnoreCase(subprotocol)) {
                // TODO decode and deliver once OpcUaJsonStreamDecoder exists?
                // String text = ((TextWebSocketFrame) msg).text();

                ctx.close();
            } else {
                ctx.close();
            }
        }

    }

}

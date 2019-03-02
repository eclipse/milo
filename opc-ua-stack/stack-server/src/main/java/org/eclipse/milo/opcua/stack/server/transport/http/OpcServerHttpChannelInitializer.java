/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.transport.http;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Objects;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.transport.RateLimitingHandler;
import org.eclipse.milo.opcua.stack.server.transport.websocket.OpcServerWebSocketFrameHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpcServerHttpChannelInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslContext = null;

    private final UaStackServer stackServer;

    public OpcServerHttpChannelInitializer(UaStackServer stackServer) {
        this.stackServer = stackServer;

        KeyPair keyPair = stackServer.getConfig().getHttpsKeyPair().orElse(null);
        X509Certificate httpsCertificate = stackServer.getConfig().getHttpsCertificate().orElse(null);

        if (keyPair != null && httpsCertificate != null) {
            try {
                PrivateKey privateKey = keyPair.getPrivate();

                sslContext = SslContextBuilder
                    .forServer(privateKey, httpsCertificate)
                    .clientAuth(ClientAuth.NONE)
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();
            } catch (Exception e) {
                LoggerFactory.getLogger(OpcServerHttpChannelInitializer.class)
                    .error("Error configuration SslContext: {}", e.getMessage(), e);
            }
        } else {
            LoggerFactory.getLogger(OpcServerHttpChannelInitializer.class)
                .warn("HTTPS KeyPair and/or Certificate not configured; falling back to plaintext...");
        }
    }


    @Override
    protected void initChannel(SocketChannel channel) {
        stackServer.registerConnectedChannel(channel);

        channel.closeFuture().addListener(future -> stackServer.unregisterConnectedChannel(channel));

        channel.pipeline().addLast(RateLimitingHandler.getInstance());

        if (sslContext != null) {
            channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
        }

        channel.pipeline().addLast(new LoggingHandler(LogLevel.TRACE));
        channel.pipeline().addLast(new HttpServerCodec());

        // TODO configure maxContentLength based on MaxRequestSize?
        channel.pipeline().addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        channel.pipeline().addLast(new OpcHttpTransportInterceptor(stackServer));
    }

    private static class OpcHttpTransportInterceptor extends SimpleChannelInboundHandler<FullHttpRequest> {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final UaStackServer stackServer;

        public OpcHttpTransportInterceptor(UaStackServer stackServer) {
            this.stackServer = stackServer;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) throws Exception {
            String host = httpRequest.headers().get(HttpHeaderNames.HOST);
            String uri = httpRequest.uri();

            logger.debug("host={} uri={}", host, uri);

            boolean endpointMatch = stackServer.getEndpointDescriptions()
                .stream()
                .anyMatch(endpoint ->
                    Objects.equals(
                        uri,
                        EndpointUtil.getPath(endpoint.getEndpointUrl()))
                );

            if (!endpointMatch) {
                logger.debug("unrecognized endpoint URL: " + uri);

                HttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.NOT_FOUND
                );

                ctx.channel().writeAndFlush(response)
                    .addListener(future -> ctx.close());

                return;
            }

            if (Objects.equals(httpRequest.method(), HttpMethod.GET) &&
                "websocket".equalsIgnoreCase(httpRequest.headers().get(HttpHeaderValues.UPGRADE))) {

                logger.debug("intercepted WebSocket upgrade");

                ctx.channel().pipeline().remove(this);

                ctx.channel().pipeline().addLast(new WebSocketServerCompressionHandler());

                // TODO configure webSocketPath based on path component of endpoint URL?
                ctx.channel().pipeline().addLast(new WebSocketServerProtocolHandler(
                    "/ws",
                    String.format("%s, %s", Stack.WSS_PROTOCOL_BINARY, Stack.WSS_PROTOCOL_JSON),
                    true
                ));

                ctx.channel().pipeline().addLast(new OpcServerWebSocketFrameHandler(stackServer));

                httpRequest.retain();
                ctx.executor().execute(() -> ctx.fireChannelRead(httpRequest));
            } else if (Objects.equals(httpRequest.method(), HttpMethod.POST)) {
                logger.debug("intercepted HTTP POST");

                ctx.channel().pipeline().remove(this);
                ctx.channel().pipeline().addLast(new OpcServerHttpRequestHandler(stackServer));

                httpRequest.retain();
                ctx.executor().execute(() -> ctx.pipeline().fireChannelRead(httpRequest));
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

}

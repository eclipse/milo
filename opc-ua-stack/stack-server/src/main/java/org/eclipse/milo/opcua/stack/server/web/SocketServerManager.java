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
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Maps;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.eclipse.milo.opcua.stack.server.UaStackServer;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class SocketServerManager {

    public static SocketServerManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final SocketServerManager INSTANCE = new SocketServerManager();
    }

    private final Map<InetSocketAddress, SocketServer> socketServers = Maps.newConcurrentMap();

    private SocketServerManager() {}

    public CompletableFuture<Unit> bind(UaStackServer server, EndpointConfiguration endpoint) {
        CompletableFuture<Unit> future = new CompletableFuture<>();

        InetSocketAddress isa = new InetSocketAddress(
            endpoint.getBindAddress(),
            endpoint.getBindPort()
        );

        if (socketServers.containsKey(isa)) {
            SocketServer socketServer = socketServers.get(isa);
            socketServer.addServer(server, endpoint);
            return completedFuture(Unit.VALUE);
        } else {
            return SocketServer
                .bootstrap(isa, endpoint.getTransportProfile())
                .thenApply(socketServer -> {
                    socketServers.putIfAbsent(isa, socketServer);
                    socketServer.addServer(server, endpoint);
                    return Unit.VALUE;
                });
        }
    }

    public CompletableFuture<Unit> unbind(UaStackServer server, EndpointConfiguration endpoint) {
        return completedFuture(Unit.VALUE);
    }

    public static class OpcTcpChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {

        }
    }

    public static class OpcHttpChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {

        }
    }

    private static class SocketServer {

        private final Map<String, UaStackServer> stackServers = Maps.newConcurrentMap();

        public void addServer(UaStackServer stackServer, EndpointConfiguration endpoint) {
            stackServers.put(endpoint.getEndpointUrl(), stackServer);
        }

        public void removeServer(UaStackServer server, EndpointConfiguration endpoint) {
            stackServers.remove(endpoint.getEndpointUrl(), server);
        }

        public static CompletableFuture<SocketServer> bootstrap(
            InetSocketAddress address, TransportProfile transportProfile) {

            SocketServer socketServer = new SocketServer();

            ChannelInitializer<SocketChannel> initializer;

            if (transportProfile == TransportProfile.TCP_UASC_UABINARY) {
                initializer = new OpcTcpChannelInitializer();
            } else {
                initializer = new OpcHttpChannelInitializer();
            }

            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(Stack.sharedEventLoop())
                .handler(new LoggingHandler(SocketServer.class))
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(initializer);

            CompletableFuture<SocketServer> serverFuture = new CompletableFuture<>();

            bootstrap.bind(address).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    serverFuture.complete(socketServer);
                } else {
                    serverFuture.completeExceptionally(future.cause());
                }
            });

            return serverFuture;
        }

    }

}

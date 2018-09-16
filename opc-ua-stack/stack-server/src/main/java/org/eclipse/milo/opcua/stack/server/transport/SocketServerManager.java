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

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.collect.Maps;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.eclipse.milo.opcua.stack.server.UaStackServer;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class SocketServerManager {

    public static SocketServerManager get() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final SocketServerManager INSTANCE = new SocketServerManager();
    }

    private final Map<InetSocketAddress, SocketServer> socketServers = Maps.newConcurrentMap();

    private SocketServerManager() {}

    public CompletableFuture<Unit> bind(EndpointConfiguration endpoint, UaStackServer server) {
        InetSocketAddress isa = new InetSocketAddress(
            endpoint.getBindAddress(),
            endpoint.getBindPort()
        );

        if (socketServers.containsKey(isa)) {
            SocketServer socketServer = socketServers.get(isa);
            socketServer.addServer(endpoint, server);
            return completedFuture(Unit.VALUE);
        } else {
            return SocketServer
                .bootstrap(isa, endpoint.getTransportProfile())
                .thenApply(socketServer -> {
                    socketServers.putIfAbsent(isa, socketServer);
                    socketServer.addServer(endpoint, server);
                    return Unit.VALUE;
                });
        }
    }

    public CompletableFuture<Unit> unbind(EndpointConfiguration endpoint, UaStackServer stackServer) {
        InetSocketAddress isa = new InetSocketAddress(
            endpoint.getBindAddress(),
            endpoint.getBindPort()
        );

        if (socketServers.containsKey(isa)) {
            SocketServer socketServer = socketServers.get(isa);
            socketServer.removeServer(endpoint, stackServer);

            if (socketServer.isEmpty()) {
                socketServers.remove(isa);
                socketServer.shutdown();
            }
        }

        return completedFuture(Unit.VALUE);
    }

    public static class SocketServer {

        private AtomicReference<Channel> channel = new AtomicReference<>();

        private final Map<String, UaStackServer> stackServers = Maps.newConcurrentMap();

        void addServer(EndpointConfiguration endpoint, UaStackServer stackServer) {
            String path = EndpointUtil.getPath(endpoint.getEndpointUrl());
            stackServers.put(path, stackServer);
        }

        void removeServer(EndpointConfiguration endpoint, UaStackServer stackServer) {
            String path = EndpointUtil.getPath(endpoint.getEndpointUrl());
            stackServers.remove(path, stackServer);
        }

        Optional<UaStackServer> getServer(String path) {
            return Optional.ofNullable(stackServers.get(path));
        }

        boolean isEmpty() {
            return stackServers.isEmpty();
        }

        void setChannel(Channel channel) {
            this.channel.set(channel);
        }

        void shutdown() {
            Channel ch = channel.getAndSet(null);
            if (ch != null) {
                ch.close();
            }
        }

        public static CompletableFuture<SocketServer> bootstrap(
            InetSocketAddress address, TransportProfile transportProfile) {

            SocketServer socketServer = new SocketServer();

            ChannelInitializer<SocketChannel> initializer;

            if (transportProfile == TransportProfile.TCP_UASC_UABINARY) {
                initializer = new OpcServerTcpChannelInitializer(socketServer::getServer);
            } else {
                initializer = new OpcServerHttpChannelInitializer(socketServer::getServer);
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
                    socketServer.setChannel(future.channel());

                    serverFuture.complete(socketServer);
                } else {
                    serverFuture.completeExceptionally(future.cause());
                }
            });

            return serverFuture;
        }

        public interface ServerLookup {

            Optional<UaStackServer> getServer(String path);

        }

    }

}

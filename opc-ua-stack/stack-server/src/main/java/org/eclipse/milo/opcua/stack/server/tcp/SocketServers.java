/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server.tcp;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
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
import org.eclipse.milo.opcua.stack.core.util.AsyncSemaphore;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.server.handlers.UaTcpServerHelloHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketServers {

    private static final AsyncSemaphore SEMAPHORE = new AsyncSemaphore(1);

    static final ConcurrentMap<InetSocketAddress, SocketServer> SERVERS = Maps.newConcurrentMap();


    public static CompletableFuture<Unit> bindServer(UaTcpStackServer stackServer, String address, int port) {
        return SEMAPHORE.acquire().thenCompose(permit ->
            doBindServer(stackServer, address, port)
                .whenComplete((u, ex) -> permit.release())
        );
    }

    private static CompletableFuture<Unit> doBindServer(UaTcpStackServer stackServer, String address, int port) {
        InetSocketAddress isa = isa(address, port);

        if (SERVERS.containsKey(isa)) {
            SocketServer server = SERVERS.get(isa);
            server.addServer(stackServer);

            return CompletableFuture.completedFuture(Unit.VALUE);
        } else {
            return SocketServer.bootstrap(isa).thenApply(s -> {
                SERVERS.putIfAbsent(isa, s);

                return Unit.VALUE;
            });
        }
    }

    public static CompletableFuture<Unit> unbindServer(UaTcpStackServer stackServer, String address, int port) {
        return SEMAPHORE.acquire().thenCompose(permit ->
            doUnbindServer(stackServer, address, port)
                .whenComplete((u, ex) -> permit.release())
        );
    }

    private static CompletableFuture<Unit> doUnbindServer(UaTcpStackServer stackServer, String address, int port) {
        InetSocketAddress isa = isa(address, port);

        if (SERVERS.containsKey(isa)) {
            SocketServer socketServer = SERVERS.get(isa);
            socketServer.removeServer(stackServer);

            if (socketServer.isEmpty()) {
                SERVERS.remove(isa);
                return socketServer.shutdown();
            }
        }

        return CompletableFuture.completedFuture(Unit.VALUE);
    }

    public static CompletableFuture<Unit> shutdownAll() {
        return SEMAPHORE.acquire().thenCompose(permit ->
            doShutdownAll()
                .whenComplete((u, ex) -> permit.release())
        );
    }

    private static CompletableFuture<Unit> doShutdownAll() {
        List<SocketServer> servers = Lists.newArrayList(SERVERS.values());

        SERVERS.clear();

        List<CompletableFuture<Unit>> futures = servers.stream()
            .map(SocketServer::shutdown)
            .collect(Collectors.toList());

        return FutureUtils.sequence(futures).thenApply(v -> Unit.VALUE);
    }


    private static InetSocketAddress isa(String address, int port) {
        return new InetSocketAddress(address, port);
    }

    private static class SocketServer {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final Map<String, UaTcpStackServer> boundServers = Maps.newConcurrentMap();

        private final InetSocketAddress address;
        private final Channel channel;

        private SocketServer(InetSocketAddress address, Channel channel) {
            this.address = address;
            this.channel = channel;
        }

        private UaTcpStackServer getServer(String endpointUrl) {
            String path = EndpointUtil.getPath(endpointUrl);

            return boundServers.get(path);
        }

        private void addServer(UaTcpStackServer server) {
            Stream<String> endpointUrls = server.getEndpointUrls().stream();
            Stream<String> discoveryUrls = server.getDiscoveryUrls().stream();

            Stream.concat(endpointUrls, discoveryUrls).forEach(url -> {
                String serverKey = EndpointUtil.getPath(url);

                String serverName = serverKey.startsWith("/") ?
                    serverKey.substring(1) :
                    serverKey;

                if (!boundServers.containsKey(serverKey) &&
                    serverName.equals(server.getConfig().getServerName())) {

                    boundServers.put(serverKey, server);
                    logger.debug("Added server at path: \"{}\"", serverName);
                }
            });
        }

        private void removeServer(UaTcpStackServer server) {
            Stream<String> endpointUrls = server.getEndpointUrls().stream();
            Stream<String> discoveryUrls = server.getDiscoveryUrls().stream();

            Stream.concat(endpointUrls, discoveryUrls).forEach(url -> {
                String key = EndpointUtil.getPath(url);

                if (boundServers.get(key) == server) {
                    boundServers.remove(key);
                    logger.debug("Removed server at path: \"{}\"", key);
                }
            });
        }

        private boolean isEmpty() {
            return boundServers.isEmpty();
        }

        private CompletableFuture<Unit> shutdown() {
            CompletableFuture<Unit> shutdownFuture = new CompletableFuture<>();

            boundServers.clear();
            channel.close().addListener((ChannelFutureListener) future -> shutdownFuture.complete(Unit.VALUE));

            return shutdownFuture;
        }

        static CompletableFuture<SocketServer> bootstrap(InetSocketAddress address) {

            final CompletableFuture<SocketServer> serverFuture = new CompletableFuture<>();

            final ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(Stack.sharedEventLoop())
                .handler(new LoggingHandler(SocketServer.class))
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        Function<String, Optional<UaTcpStackServer>> serverLookup =
                            endpointUrl -> getServerByEndpointUrl(address, endpointUrl);

                        channel.pipeline().addLast(new UaTcpServerHelloHandler(serverLookup));
                    }
                });

            bootstrap.bind(address).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    SocketServer socketServer = new SocketServer(address, future.channel());
                    serverFuture.complete(socketServer);
                } else {
                    serverFuture.completeExceptionally(future.cause());
                }
            });

            return serverFuture;
        }

        static Optional<UaTcpStackServer> getServerByEndpointUrl(InetSocketAddress address, String endpointUrl) {
            SocketServer socketServer = SocketServers.SERVERS.get(address);

            if (socketServer != null) {
                return Optional.ofNullable(socketServer.getServer(endpointUrl));
            }

            return Optional.empty();
        }

    }

}

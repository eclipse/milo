/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.transport;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
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
import org.eclipse.milo.opcua.stack.core.util.AsyncSemaphore;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.transport.http.OpcServerHttpChannelInitializer;
import org.eclipse.milo.opcua.stack.server.transport.tcp.OpcServerTcpChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerChannelManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AsyncSemaphore semaphore = new AsyncSemaphore(1);

    private final Multiset<InetSocketAddress> addresses = ConcurrentHashMultiset.create();
    private final Map<InetSocketAddress, Channel> channels = Maps.newConcurrentMap();

    private final UaStackServer stackServer;

    public ServerChannelManager(UaStackServer stackServer) {
        this.stackServer = stackServer;
    }

    public CompletableFuture<Unit> bind(EndpointConfiguration endpoint) {
        CompletableFuture<Unit> future = new CompletableFuture<>();

        semaphore.acquire().thenApply(permit -> {
            future.whenComplete((u, ex) -> permit.release());

            InetSocketAddress bindAddress = new InetSocketAddress(
                endpoint.getBindAddress(),
                endpoint.getBindPort()
            );

            if (channels.containsKey(bindAddress)) {
                return future.complete(Unit.VALUE);
            } else {
                logger.debug("binding to {}", bindAddress);

                CompletableFuture<Channel> bootstrap = bootstrap(
                    stackServer,
                    bindAddress,
                    endpoint.getTransportProfile()
                );

                return bootstrap.whenComplete((channel, ex) -> {
                    if (channel != null) {
                        addresses.add(bindAddress);
                        channels.put(bindAddress, channel);
                        future.complete(Unit.VALUE);
                    } else {
                        future.completeExceptionally(ex);
                    }
                });
            }
        });

        return future;
    }

    public CompletableFuture<Unit> unbind(EndpointConfiguration endpoint) {
        CompletableFuture<Unit> future = new CompletableFuture<>();

        semaphore.acquire().thenAccept(permit -> {
            future.whenComplete((u, ex) -> permit.release());

            InetSocketAddress bindAddress = new InetSocketAddress(
                endpoint.getBindAddress(),
                endpoint.getBindPort()
            );

            if (addresses.remove(bindAddress, 1) == 1) {
                logger.debug("unbinding from {}", bindAddress);

                Channel channel = channels.remove(bindAddress);

                if (channel != null) {
                    channel.close();
                }
            }

            future.complete(Unit.VALUE);
        });

        return future;
    }

    private static CompletableFuture<Channel> bootstrap(
        UaStackServer stackServer,
        InetSocketAddress bindAddress,
        TransportProfile transportProfile) {

        ChannelInitializer<SocketChannel> initializer;

        if (transportProfile == TransportProfile.TCP_UASC_UABINARY) {
            initializer = new OpcServerTcpChannelInitializer(stackServer);
        } else {
            initializer = new OpcServerHttpChannelInitializer(stackServer);
        }

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(Stack.sharedEventLoop())
            .handler(new LoggingHandler(ServerChannelManager.class))
            .channel(NioServerSocketChannel.class)
            .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childHandler(initializer);

        CompletableFuture<Channel> channelFuture = new CompletableFuture<>();

        bootstrap.bind(bindAddress).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                Channel channel = future.channel();

                channelFuture.complete(channel);
            } else {
                channelFuture.completeExceptionally(future.cause());
            }
        });

        return channelFuture;
    }

}

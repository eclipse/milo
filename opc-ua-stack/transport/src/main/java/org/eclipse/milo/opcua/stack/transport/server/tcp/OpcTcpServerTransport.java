/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.server.tcp;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.util.Lazy;
import org.eclipse.milo.opcua.stack.transport.server.OpcServerTransport;
import org.eclipse.milo.opcua.stack.transport.server.ServerApplicationContext;
import org.eclipse.milo.opcua.stack.transport.server.uasc.UascServerHelloHandler;

public class OpcTcpServerTransport implements OpcServerTransport {

    private final Set<InetSocketAddress> boundAddresses = new HashSet<>();
    private final Set<Channel> channelReferences = new HashSet<>();
    private final Lazy<ServerBootstrap> serverBootstrap = new Lazy<>();

    private final OpcTcpServerTransportConfig config;

    public OpcTcpServerTransport(OpcTcpServerTransportConfig config) {
        this.config = config;
    }

    @Override
    public synchronized void bind(ServerApplicationContext applicationContext, InetSocketAddress bindAddress) throws Exception {
        ServerBootstrap bootstrap = serverBootstrap.getOrCompute(() ->
            new ServerBootstrap()
                .channel(NioServerSocketChannel.class)
                .group(config.getEventLoop())
                .handler(new LoggingHandler(OpcTcpServerTransport.class))
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) {
                        channel.pipeline().addLast(RateLimitingHandler.getInstance());
                        channel.pipeline().addLast(
                            new UascServerHelloHandler(config, applicationContext, TransportProfile.TCP_UASC_UABINARY)
                        );
                    }
                })
        );

        assert bootstrap != null;

        if (!boundAddresses.contains(bindAddress)) {
            ChannelFuture bindFuture = bootstrap.bind(bindAddress).sync();

            boundAddresses.add(bindAddress);
            channelReferences.add(bindFuture.channel());
        }
    }

    @Override
    public synchronized void unbind() {
        boundAddresses.clear();

        channelReferences.forEach(channel -> {
            try {
                channel.close().sync();
            } catch (InterruptedException ignored) {
            }
        });
        channelReferences.clear();

        serverBootstrap.reset();
    }

}
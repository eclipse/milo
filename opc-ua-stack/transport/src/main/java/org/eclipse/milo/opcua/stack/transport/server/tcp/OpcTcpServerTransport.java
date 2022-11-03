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

import java.util.concurrent.atomic.AtomicReference;

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
import org.eclipse.milo.opcua.stack.transport.server.OpcServerTransport;
import org.eclipse.milo.opcua.stack.transport.server.ServerApplicationContext;
import org.eclipse.milo.opcua.stack.transport.server.uasc.UascServerHelloHandler;

public class OpcTcpServerTransport implements OpcServerTransport {

    private final AtomicReference<Channel> channelReference = new AtomicReference<>();

    private final OpcTcpServerTransportConfig config;

    public OpcTcpServerTransport(OpcTcpServerTransportConfig config) {
        this.config = config;
    }

    @Override
    public void bind(ServerApplicationContext applicationContext, String bindAddress, int bindPort) throws Exception {
        var bootstrap = new ServerBootstrap();

        bootstrap.channel(NioServerSocketChannel.class)
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
            });

        ChannelFuture bindFuture = bootstrap.bind(bindAddress, bindPort).sync();
        channelReference.set(bindFuture.channel());
    }

    @Override
    public void unbind() throws Exception {
        Channel channel = channelReference.getAndSet(null);
        if (channel != null) {
            channel.close().sync();
        }
    }

}

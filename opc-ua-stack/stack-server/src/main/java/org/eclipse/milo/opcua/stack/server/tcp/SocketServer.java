/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.server.tcp;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.google.common.collect.Maps;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.server.handlers.UaTcpServerHelloHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<String, UaTcpStackServer> servers = Maps.newConcurrentMap();

    private volatile boolean strictEndpointUrlsEnabled = true;

    private volatile Channel channel;

    private final ServerBootstrap bootstrap = new ServerBootstrap();

    private final InetSocketAddress address;

    private SocketServer(InetSocketAddress address) {
        this.address = address;

        bootstrap.group(Stack.sharedEventLoop())
            .handler(new LoggingHandler(SocketServer.class))
            .channel(NioServerSocketChannel.class)
            .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new UaTcpServerHelloHandler(SocketServer.this));
                }
            });
    }

    public synchronized void bind() throws ExecutionException, InterruptedException {
        if (channel != null) return; // Already bound

        CompletableFuture<Unit> bindFuture = new CompletableFuture<>();

        bootstrap.bind(address).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    channel = future.channel();
                    bindFuture.complete(Unit.VALUE);
                } else {
                    bindFuture.completeExceptionally(future.cause());
                }
            }
        });

        bindFuture.get();
    }

    public void addServer(UaTcpStackServer server) {
        server.getEndpointUrls().forEach(url -> {
            String key = pathOrUrl(url);

            if (!servers.containsKey(key)) {
                servers.put(key, server);
                logger.debug("Added server at path: \"{}\"", key);
            }
        });

        server.getDiscoveryUrls().forEach(url -> {
            String key = pathOrUrl(url);

            if (!servers.containsKey(key)) {
                servers.put(key, server);
                logger.debug("Added server at path: \"{}\"", key);
            }
        });
    }

    public void removeServer(UaTcpStackServer server) {
        server.getEndpointUrls().forEach(url -> {
            String key = pathOrUrl(url);

            if (servers.remove(key) != null) {
                logger.debug("Removed server at path: \"{}\"", key);
            }
        });
        server.getDiscoveryUrls().forEach(url -> {
            String key = pathOrUrl(url);

            if (servers.remove(key) != null) {
                logger.debug("Removed server at path: \"{}\"", key);
            }
        });

        if (servers.isEmpty()) {
            removeSocketServer(this);
            shutdown();
        }
    }

    /**
     * Get the server identified {@code endpointUrl}.
     *
     * @return the {@link UaTcpStackServer} identified by {@code endpointUrl}.
     */
    public UaTcpStackServer getServer(String endpointUrl) {
        String path = pathOrUrl(endpointUrl);

        UaTcpStackServer server = servers.get(path);

        if (server == null && servers.size() == 1 && !strictEndpointUrlsEnabled) {
            Iterator<UaTcpStackServer> iterator = servers.values().iterator();

            if (iterator.hasNext()) {
                server = iterator.next();
            }
        }

        return server;
    }

    private String pathOrUrl(String endpointUrl) {
        try {
            URI uri = new URI(endpointUrl).parseServerAuthority();
            return uri.getPath();
        } catch (Throwable e) {
            logger.warn("Endpoint URL '{}' is not a valid URI: {}", e.getMessage(), e);
            return endpointUrl;
        }
    }

    public SocketAddress getLocalAddress() {
        final Channel ch = channel;
        return ch != null ? ch.localAddress() : null;
    }

    public void shutdown() {
        if (channel != null) {
            channel.close();
            channel = null;
        }
    }

    /**
     * @return {@code true} if strict endpoint URLs are enabled.
     */
    public boolean isStrictEndpointUrlsEnabled() {
        return strictEndpointUrlsEnabled;
    }

    /**
     * If {@code true}, during a {@link #getServer(String)} call the path of the endpoint URL must exactly match a
     * registered server name. If {@code false}, and only one server is registered, that server will be returned even
     * if the path does not match.
     *
     * @param strictEndpointUrlsEnabled {@code true} if strict endpoint URLs should be enabled.
     */
    public void setStrictEndpointUrlsEnabled(boolean strictEndpointUrlsEnabled) {
        this.strictEndpointUrlsEnabled = strictEndpointUrlsEnabled;
    }

    public static synchronized SocketServer boundTo(String address) throws Exception {
        return boundTo(address, Stack.DEFAULT_PORT);
    }

    public static synchronized SocketServer boundTo(String address, int port) throws Exception {
        return boundTo(InetAddress.getByName(address), port);
    }

    public static synchronized SocketServer boundTo(InetAddress address) throws Exception {
        return boundTo(address, Stack.DEFAULT_PORT);
    }

    public static synchronized SocketServer boundTo(InetAddress address, int port) throws Exception {
        return boundTo(new InetSocketAddress(address, port));
    }

    public static synchronized SocketServer boundTo(InetSocketAddress address) throws Exception {
        if (SOCKET_SERVERS.containsKey(address)) {
            return SOCKET_SERVERS.get(address);
        } else {
            SocketServer server = new SocketServer(address);
            server.bind();

            SOCKET_SERVERS.put(address, server);

            return server;
        }
    }

    private static void removeSocketServer(SocketServer server) {
        Iterator<SocketServer> iterator = SOCKET_SERVERS.values().iterator();

        while (iterator.hasNext()) {
            SocketServer next = iterator.next();
            if (next == server) {
                iterator.remove();
                break;
            }
        }
    }

    public static synchronized void shutdownAll() {
        SOCKET_SERVERS.values().forEach(SocketServer::shutdown);
        SOCKET_SERVERS.clear();
    }

    static final Map<InetSocketAddress, SocketServer> SOCKET_SERVERS = Maps.newConcurrentMap();

}

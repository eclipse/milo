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

package org.eclipse.milo.opcua.stack.client.transport.tcp;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.uasc.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.UascClientAcknowledgeHandler;

public class OpcClientTcpChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final UaStackClientConfig config;
    private final CompletableFuture<ClientSecureChannel> handshake;

    public OpcClientTcpChannelInitializer(
        UaStackClientConfig config,
        CompletableFuture<ClientSecureChannel> handshake) {

        this.config = config;
        this.handshake = handshake;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new UascClientAcknowledgeHandler(config, handshake));
    }

}

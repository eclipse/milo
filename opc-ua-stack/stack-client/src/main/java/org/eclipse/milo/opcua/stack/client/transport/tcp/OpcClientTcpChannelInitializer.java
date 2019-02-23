/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

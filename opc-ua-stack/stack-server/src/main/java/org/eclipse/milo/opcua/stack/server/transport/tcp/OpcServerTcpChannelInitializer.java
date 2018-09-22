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

package org.eclipse.milo.opcua.stack.server.transport.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.transport.RateLimitingHandler;
import org.eclipse.milo.opcua.stack.server.transport.uasc.UascServerHelloHandler;

public class OpcServerTcpChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final UaStackServer stackServer;

    public OpcServerTcpChannelInitializer(UaStackServer stackServer) {
        this.stackServer = stackServer;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(RateLimitingHandler.getInstance());
        channel.pipeline().addLast(new UascServerHelloHandler(stackServer));
    }

}

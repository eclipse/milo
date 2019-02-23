/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.transport.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.transport.RateLimitingHandler;
import org.eclipse.milo.opcua.stack.server.transport.uasc.UascServerHelloHandler;

public class OpcServerTcpChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final UaStackServer stackServer;

    public OpcServerTcpChannelInitializer(UaStackServer stackServer) {
        this.stackServer = stackServer;
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(RateLimitingHandler.getInstance());
        channel.pipeline().addLast(new UascServerHelloHandler(stackServer, TransportProfile.TCP_UASC_UABINARY));
    }

}

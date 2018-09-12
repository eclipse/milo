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

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.AbstractTransport;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.uacp.ChannelManager;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;

public class OpcUaTcpTransport extends AbstractTransport implements UaTransport {

    private final ChannelManager channelManager;

    private final UaStackClientConfig config;

    public OpcUaTcpTransport(UaStackClientConfig config) {
        super(config);

        this.config = config;

        channelManager = new ChannelManager(config);
    }

    @Override
    public CompletableFuture<UaTransport> connect() {
        return channelManager.connect()
            .thenApply(v -> OpcUaTcpTransport.this);
    }

    @Override
    public CompletableFuture<UaTransport> disconnect() {
        return channelManager.disconnect()
            .thenApply(v -> OpcUaTcpTransport.this);
    }

    @Override
    public CompletableFuture<Channel> channel() {
        return channelManager.getChannel()
            .thenApply(ClientSecureChannel::getChannel);
    }


}

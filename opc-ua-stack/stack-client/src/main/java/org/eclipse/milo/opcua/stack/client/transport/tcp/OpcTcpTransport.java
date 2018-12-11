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

import com.digitalpetri.netty.fsm.ChannelFsm;
import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.AbstractTransport;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.uasc.ClientChannelFsm;

public class OpcTcpTransport extends AbstractTransport implements UaTransport {

    private final ChannelFsm channelFsm;

    private final UaStackClientConfig config;

    public OpcTcpTransport(UaStackClientConfig config) {
        super(config);

        this.config = config;

        channelFsm = ClientChannelFsm.newChannelFsm(config);
    }

    public UaStackClientConfig getConfig() {
        return config;
    }

    @Override
    public CompletableFuture<UaTransport> connect() {
        return channelFsm.connect()
            .thenApply(v -> OpcTcpTransport.this);
    }

    @Override
    public CompletableFuture<UaTransport> disconnect() {
        return channelFsm.disconnect()
            .thenApply(v -> OpcTcpTransport.this);
    }

    @Override
    public CompletableFuture<Channel> channel() {
        return channelFsm.getChannel();
    }

}

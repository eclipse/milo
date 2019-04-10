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

import com.digitalpetri.netty.fsm.ChannelFsm;
import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.AbstractTransport;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.uasc.ClientChannelFsm;

public class OpcTcpTransport extends AbstractTransport implements UaTransport {

    private final ChannelFsm channelFsm;

    private final UaStackClient client;

    public OpcTcpTransport(UaStackClient client) {
        super(client.getConfig());

        this.client = client;
        
        channelFsm = ClientChannelFsm.newChannelFsm(client);
    }

    public UaStackClientConfig getConfig() {
        return client.getConfig();
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

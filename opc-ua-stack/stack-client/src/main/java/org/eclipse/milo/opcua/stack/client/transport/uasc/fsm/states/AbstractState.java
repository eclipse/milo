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

package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Connect;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.GetChannel;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

abstract class AbstractState extends ChannelFsm.State {

    void handleConnectOrGetChannel(ChannelFsm fsm, ChannelFsm.Event event) {
        CompletableFuture<Channel> channelFuture =
            fsm.getContext().getChannelFuture();

        if (event instanceof Connect) {
            CompletableFuture<Channel> future =
                ((Connect) event).getChannelFuture();

            complete(future).with(channelFuture);
        } else if (event instanceof GetChannel) {
            CompletableFuture<Channel> future =
                ((GetChannel) event).getChannelFuture();

            complete(future).with(channelFuture);
        }
    }

}

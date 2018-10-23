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
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectFailure;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectSuccess;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Disconnect;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.DisconnectSuccess;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.GetChannel;
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class Disconnecting extends ChannelFsm.State {

    @Override
    public ChannelFsm.State execute(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof Connect) {
            StateActions.connectAsync(fsm);

            return new Connecting();
        } else if (event instanceof DisconnectSuccess) {
            return new NotConnected();
        } else {
            return this;
        }
    }

    @Override
    public void onInternalTransition(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof GetChannel) {
            CompletableFuture<Channel> future =
                ((GetChannel) event).getChannelFuture();

            complete(future).with(fsm.getContext().getChannelFuture());
        } else if (event instanceof Disconnect) {
            CompletableFuture<Unit> future =
                ((Disconnect) event).getDisconnectFuture();

            complete(future).with(fsm.getContext().getDisconnectFuture());
        } else if (event instanceof ConnectFailure) {
            ConnectFailure connectFailure = (ConnectFailure) event;

            CompletableFuture<Channel> future =
                fsm.getContext().getChannelFuture();

            if (future != null) {
                future.completeExceptionally(connectFailure.getFailure());
            }
        } else if (event instanceof ConnectSuccess) {
            ConnectSuccess connectSuccess = (ConnectSuccess) event;

            CompletableFuture<Channel> future =
                fsm.getContext().getChannelFuture();

            if (future != null) {
                future.complete(connectSuccess.getChannel());
            }
        }
    }

    @Override
    public void onExternalTransition(ChannelFsm fsm, ChannelFsm.State prevState, ChannelFsm.Event event) {
        if (event instanceof Disconnect) {
            CompletableFuture<Unit> disconnectFuture =
                ((Disconnect) event).getDisconnectFuture();

            fsm.getContext().setDisconnectFuture(disconnectFuture);
        }
    }

}

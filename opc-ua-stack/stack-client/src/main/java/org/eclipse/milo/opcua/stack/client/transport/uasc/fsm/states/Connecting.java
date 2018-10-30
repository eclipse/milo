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
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class Connecting extends AbstractState {

    @Override
    public ChannelFsm.State execute(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof ConnectSuccess) {
            return new Connected();
        } else if (event instanceof ConnectFailure) {
            if (fsm.isPersistent()) {
                StateActions.connectAsync(fsm);

                return new Reconnecting();
            } else {
                return new NotConnected();
            }
        } else if (event instanceof Disconnect) {
            // on external transition from Connecting -> Disconnecting, Disconnecting
            // should wait for the current connect and then issue a disconnect.

            return new Disconnecting();
        } else {
            return this;
        }
    }

    @Override
    public void onInternalTransition(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof DisconnectSuccess) {
            CompletableFuture<Unit> disconnectFuture = fsm.getContext().getDisconnectFuture();

            if (disconnectFuture != null) {
                disconnectFuture.complete(Unit.VALUE);
            }
        }

        handleConnectOrGetChannel(fsm, event);
    }

    @Override
    public void onExternalTransition(ChannelFsm fsm, ChannelFsm.State prevState, ChannelFsm.Event event) {
        CompletableFuture<Channel> prevChannelFuture = fsm.getContext().getChannelFuture();

        if (prevChannelFuture != null) {
            prevChannelFuture.whenComplete(
                (c, x) ->
                    fsm.getContext().setChannelFuture(new CompletableFuture<>())
            );
        } else {
            fsm.getContext().setChannelFuture(new CompletableFuture<>());
        }

        if (event instanceof Connect && prevState instanceof Disconnecting) {
            CompletableFuture<Unit> disconnectFuture = fsm.getContext().getDisconnectFuture();

            if (disconnectFuture != null) {
                disconnectFuture.whenComplete((u, x) -> StateActions.connectAsync(fsm));
            } else {
                StateActions.connectAsync(fsm);
            }

            CompletableFuture<Channel> future =
                ((Connect) event).getChannelFuture();

            complete(future).with(fsm.getContext().getChannelFuture());
        } else {
            handleConnectOrGetChannel(fsm, event);
        }
    }

}

package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectFailure;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectSuccess;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Disconnect;

public class Reconnecting extends AbstractState {

    private static final int MAX_RECONNECT_DELAY = 16; // seconds

    @Override
    public ChannelFsm.State execute(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof ConnectSuccess) {
            return new Connected();
        } else if (event instanceof ConnectFailure) {
            StateActions.reconnectAsync(fsm);

            return new Reconnecting();
        } else if (event instanceof Disconnect) {
            StateActions.disconnectAsync(fsm);

            return new Disconnecting();
        } else {
            return this;
        }
    }

    @Override
    public void onInternalTransition(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof ConnectFailure) {
            Throwable failure = ((ConnectFailure) event).getFailure();

            CompletableFuture<Channel> channelFuture = fsm.getContext().getChannelFuture();

            if (channelFuture != null) {
                channelFuture.completeExceptionally(failure);
            }

            fsm.getContext().setChannelFuture(new CompletableFuture<>());

            long delay = Math.max(1, fsm.getContext().getReconnectDelay());
            long nextDelay = Math.min(MAX_RECONNECT_DELAY, delay << 1L);

            fsm.getContext().setReconnectDelay(nextDelay);
        } else {
            handleConnectOrGetChannel(fsm, event);
        }
    }

    @Override
    public void onExternalTransition(ChannelFsm fsm, ChannelFsm.State prevState, ChannelFsm.Event event) {
        if (event instanceof ConnectFailure) {
            Throwable failure = ((ConnectFailure) event).getFailure();

            CompletableFuture<Channel> channelFuture = fsm.getContext().getChannelFuture();

            if (channelFuture != null) {
                channelFuture.completeExceptionally(failure);
            }

            fsm.getContext().setChannelFuture(new CompletableFuture<>());
            fsm.getContext().setReconnectDelay(1L);
        } else {
            handleConnectOrGetChannel(fsm, event);
        }
    }

}

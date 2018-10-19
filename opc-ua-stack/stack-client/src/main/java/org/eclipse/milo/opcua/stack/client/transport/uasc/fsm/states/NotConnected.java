package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Connect;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectFailure;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Disconnect;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.DisconnectSuccess;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.GetChannel;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class NotConnected extends ChannelFsm.State {

    @Override
    public ChannelFsm.State execute(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof Connect) {
            StateActions.connectAsync(fsm);

            return new Connecting();
        } else {
            return this;
        }
    }

    @Override
    public void onInternalTransition(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof Disconnect) {
            CompletableFuture<Unit> future =
                ((Disconnect) event).getDisconnectFuture();

            future.complete(Unit.VALUE);
        } else if (event instanceof GetChannel) {
            CompletableFuture<Channel> future =
                ((GetChannel) event).getChannelFuture();

            future.completeExceptionally(new Exception("not connected"));
        }
    }

    @Override
    public void onExternalTransition(ChannelFsm fsm, ChannelFsm.State prevState, ChannelFsm.Event event) {
        if (event instanceof ConnectFailure) {
            Throwable failure = ((ConnectFailure) event).getFailure();

            CompletableFuture<Channel> future =
                fsm.getContext().getChannelFuture();

            if (future != null) {
                future.completeExceptionally(failure);
            }
        } else if (event instanceof DisconnectSuccess) {
            CompletableFuture<Unit> future =
                fsm.getContext().getDisconnectFuture();

            fsm.getContext().setDisconnectFuture(null);

            if (future != null) {
                future.complete(Unit.VALUE);
            }
        }
    }

}


package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectFailure;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectSuccess;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Disconnect;

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
            StateActions.disconnectAsync(fsm);

            return new Disconnecting();
        } else {
            return this;
        }
    }

    @Override
    public void onInternalTransition(ChannelFsm fsm, ChannelFsm.Event event) {
        handleConnectOrGetChannel(fsm, event);
    }

    @Override
    public void onExternalTransition(ChannelFsm fsm, ChannelFsm.State prevState, ChannelFsm.Event event) {
        fsm.getContext().setChannelFuture(new CompletableFuture<>());

        handleConnectOrGetChannel(fsm, event);
    }

}

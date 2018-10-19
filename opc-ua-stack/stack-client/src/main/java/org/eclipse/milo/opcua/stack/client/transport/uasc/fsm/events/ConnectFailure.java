package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events;

import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;

public class ConnectFailure implements ChannelFsm.Event {

    private final Throwable failure;

    public ConnectFailure(Throwable failure) {
        this.failure = failure;
    }

    public Throwable getFailure() {
        return failure;
    }

}

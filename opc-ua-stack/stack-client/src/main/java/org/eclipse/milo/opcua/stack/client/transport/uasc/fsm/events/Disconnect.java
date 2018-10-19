package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class Disconnect implements ChannelFsm.Event {

    private final CompletableFuture<Unit> future = new CompletableFuture<>();

    public CompletableFuture<Unit> getDisconnectFuture() {
        return future;
    }

}

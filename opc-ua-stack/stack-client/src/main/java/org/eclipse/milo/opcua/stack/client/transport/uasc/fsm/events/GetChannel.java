package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;

public class GetChannel implements ChannelFsm.Event {

    private final CompletableFuture<Channel> channelFuture = new CompletableFuture<>();

    public CompletableFuture<Channel> getChannelFuture() {
        return channelFuture;
    }

}

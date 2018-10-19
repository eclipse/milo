package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Connect;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.GetChannel;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public abstract class AbstractState extends ChannelFsm.State {

    protected void handleConnectOrGetChannel(ChannelFsm fsm, ChannelFsm.Event event) {
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

package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ChannelInactive;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.ConnectSuccess;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Disconnect;

public class Connected extends AbstractState {

    @Override
    public ChannelFsm.State execute(ChannelFsm fsm, ChannelFsm.Event event) {
        if (event instanceof ChannelInactive) {
            StateActions.connectAsync(fsm);

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
        handleConnectOrGetChannel(fsm, event);
    }

    @Override
    public void onExternalTransition(ChannelFsm fsm, ChannelFsm.State prevState, ChannelFsm.Event event) {
        if (event instanceof ConnectSuccess) {
            Channel channel = ((ConnectSuccess) event).getChannel();

            channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                @Override
                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                    LOGGER.debug("channelInactive() local={}, remote={}",
                        ctx.channel().localAddress(), ctx.channel().remoteAddress());

                    fsm.fireEvent(new ChannelInactive());

                    super.channelInactive(ctx);
                }
            });

            CompletableFuture<Channel> future =
                fsm.getContext().getChannelFuture();

            if (future != null) {
                future.complete(channel);
            }
        }
    }

}

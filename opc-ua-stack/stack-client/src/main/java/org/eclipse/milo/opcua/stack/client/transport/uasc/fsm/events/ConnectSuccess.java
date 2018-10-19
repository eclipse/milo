package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.ChannelFsm;

public class ConnectSuccess implements ChannelFsm.Event {

    private final Channel channel;

    public ConnectSuccess(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

}

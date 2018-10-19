package org.eclipse.milo.opcua.stack.client.transport.uasc.fsm;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;

import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Connect;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.Disconnect;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.events.GetChannel;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states.Connected;
import org.eclipse.milo.opcua.stack.client.transport.uasc.fsm.states.NotConnected;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.complete;

public class ChannelFsm {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private final Context context = new Context();

    private final AtomicReference<State> state = new AtomicReference<>(new NotConnected());

    private final UaStackClientConfig config;

    public ChannelFsm(UaStackClientConfig config) {
        this.config = config;
    }

    public UaStackClientConfig getConfig() {
        return config;
    }

    public Executor getExecutor() {
        return config.getExecutor();
    }

    public CompletableFuture<Channel> connect() {
        Connect connect = new Connect();

        fireEvent(connect);

        return complete(new CompletableFuture<Channel>())
            .async(config.getExecutor())
            .with(connect.getChannelFuture());
    }

    public CompletableFuture<Unit> disconnect() {
        Disconnect disconnect = new Disconnect();

        fireEvent(disconnect);

        return complete(new CompletableFuture<Unit>())
            .async(config.getExecutor())
            .with(disconnect.getDisconnectFuture());
    }

    public CompletableFuture<Channel> getChannel() {
        State current;

        try {
            readWriteLock.readLock().lock();
            current = state.get();

            if (current instanceof Connected) {
                // "Fast" path... already connected.
                return context.getChannelFuture();
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

        // "Slow" path... not connected yet.
        GetChannel getChannel = new GetChannel();

        fireEvent(getChannel);

        return complete(new CompletableFuture<Channel>())
            .async(config.getExecutor())
            .with(getChannel.getChannelFuture());
    }

    public void fireEvent(Event event) {
        if (readWriteLock.writeLock().isHeldByCurrentThread()) {
            config.getExecutor().execute(() -> fireEvent(event));
        } else {
            readWriteLock.writeLock().lock();

            try {
                State prevState = state.get();

                State nextState = state.updateAndGet(
                    state ->
                        state.execute(this, event)
                );

                logger.debug(
                    "S({}) x E({}) = S'({})",
                    prevState.getClass().getSimpleName(),
                    event.getClass().getSimpleName(),
                    nextState.getClass().getSimpleName()
                );

                if (prevState.getClass() == nextState.getClass()) {
                    nextState.onInternalTransition(this, event);
                } else {
                    nextState.onExternalTransition(this, prevState, event);
                }
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public boolean isPersistent() {
        return true; // TODO config
    }

    public static class Context {

        private CompletableFuture<Channel> channelFuture;
        private CompletableFuture<Unit> disconnectFuture;
        private long reconnectDelay;

        @Nullable
        public CompletableFuture<Channel> getChannelFuture() {
            return channelFuture;
        }

        public void setChannelFuture(@Nullable CompletableFuture<Channel> channelFuture) {
            this.channelFuture = channelFuture;
        }

        @Nullable
        public CompletableFuture<Unit> getDisconnectFuture() {
            return disconnectFuture;
        }

        public void setDisconnectFuture(@Nullable CompletableFuture<Unit> disconnectFuture) {
            this.disconnectFuture = disconnectFuture;
        }

        public long getReconnectDelay() {
            return reconnectDelay;
        }

        public void setReconnectDelay(long reconnectDelay) {
            this.reconnectDelay = reconnectDelay;
        }

    }

    public interface Event {}

    public abstract static class State {

        protected static final Logger LOGGER = LoggerFactory.getLogger(ChannelFsm.class);

        public abstract State execute(ChannelFsm fsm, Event event);

        public abstract void onInternalTransition(ChannelFsm fsm, Event event);

        public abstract void onExternalTransition(ChannelFsm fsm, State prevState, Event event);

    }
}

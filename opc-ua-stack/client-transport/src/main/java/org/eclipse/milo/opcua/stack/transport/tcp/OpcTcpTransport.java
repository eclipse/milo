/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.tcp;

import java.net.ConnectException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.digitalpetri.netty.fsm.ChannelActions;
import com.digitalpetri.netty.fsm.ChannelFsm;
import com.digitalpetri.netty.fsm.ChannelFsmConfig;
import com.digitalpetri.netty.fsm.ChannelFsmFactory;
import com.digitalpetri.netty.fsm.Event;
import com.digitalpetri.netty.fsm.State;
import com.digitalpetri.strictmachine.FsmContext;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.eclipse.milo.opcua.stack.client.transport.uasc.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSecureChannelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.transport.OpcTransport;
import org.eclipse.milo.opcua.stack.transport.uasc.UascClientAcknowledgeHandler;
import org.eclipse.milo.opcua.stack.transport.uasc.UascClientConfig;
import org.eclipse.milo.opcua.stack.transport.uasc.UascClientResponseHandler;
import org.eclipse.milo.opcua.stack.transport.uasc.UascMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcTcpTransport implements OpcTransport {

    private static final String CHANNEL_FSM_LOGGER_NAME = "org.eclipse.milo.opcua.stack.client.ChannelFsm";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<Long, CompletableFuture<UaResponseMessageType>> pendingRequests = new ConcurrentHashMap<>();
    private final Map<Long, Timeout> pendingTimeouts = new ConcurrentHashMap<>();

    private final AtomicLong requestId = new AtomicLong(1L);

    private final ChannelFsm channelFsm;

    private final UascClientConfig config;

    public OpcTcpTransport(UascClientConfig config) {
        this.config = config;

        // TODO use configurable executors
        ChannelFsmConfig fsmConfig = ChannelFsmConfig.newBuilder()
            .setLazy(false) // reconnect immediately
            .setMaxIdleSeconds(0) // keep alive handled by SessionFsm
            .setMaxReconnectDelaySeconds(16)
            .setPersistent(true)
            .setChannelActions(new ClientChannelActions(config))
            .setExecutor(Stack.sharedExecutor())
            .setScheduler(Stack.sharedScheduledExecutor())
            .setLoggerName(CHANNEL_FSM_LOGGER_NAME)
            .build();

        var factory = new ChannelFsmFactory(fsmConfig);

        channelFsm = factory.newChannelFsm();
    }

    @Override
    public CompletableFuture<Unit> connect() {
        return channelFsm.connect().thenApply(c -> Unit.VALUE);
    }

    @Override
    public CompletableFuture<Unit> disconnect() {
        return channelFsm.disconnect().thenApply(c -> Unit.VALUE);
    }

    @Override
    public CompletableFuture<UaResponseMessageType> sendRequestMessage(UaRequestMessageType requestMessage) {
        return getChannel().thenCompose(ch -> sendRequestMessage(requestMessage, ch));
    }

    private CompletableFuture<Channel> getChannel() {
        return channelFsm.getChannel();
    }

    private CompletableFuture<UaResponseMessageType> sendRequestMessage(
        UaRequestMessageType requestMessage,
        Channel channel
    ) {

        var request = new UascMessage.Request(requestId.getAndIncrement(), requestMessage);
        var responseFuture = new CompletableFuture<UaResponseMessageType>();

        pendingRequests.put(request.getRequestId(), responseFuture);
        scheduleRequestTimeout(request);

        channel.writeAndFlush(request).addListener(f -> {
            if (!f.isSuccess()) {
                pendingRequests.remove(request.getRequestId());
                cancelRequestTimeout(request.getRequestId());

                responseFuture.completeExceptionally(f.cause());

                logger.debug(
                    "Write failed, request={}, requestHandle={}",
                    requestMessage.getClass().getSimpleName(), request.getRequestId()
                );
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace(
                        "Write succeeded, request={}, requestId={}",
                        requestMessage.getClass().getSimpleName(), request.getRequestId()
                    );
                }
            }
        });

        return responseFuture;
    }

    private void scheduleRequestTimeout(UascMessage.Request request) {
        RequestHeader requestHeader = request.getRequestMessage().getRequestHeader();

        long timeoutHint = requestHeader.getTimeoutHint() != null ?
            requestHeader.getTimeoutHint().longValue() : 0L;

        if (timeoutHint > 0) {
            Timeout timeout = config.getWheelTimer().newTimeout(
                t -> {
                    if (!t.isCancelled()) {
                        CompletableFuture<UaResponseMessageType> future =
                            pendingRequests.remove(request.getRequestId());

                        if (future != null) {
                            UaException exception = new UaException(
                                StatusCodes.Bad_Timeout,
                                String.format(
                                    "requestId=%s timed out after %sms",
                                    request.getRequestId(), timeoutHint)
                            );

                            future.completeExceptionally(exception);
                        }
                    }
                },
                timeoutHint,
                TimeUnit.MILLISECONDS
            );

            pendingTimeouts.put(request.getRequestId(), timeout);
        }
    }

    private void cancelRequestTimeout(long requestId) {
        Timeout timeout = pendingTimeouts.remove(requestId);
        if (timeout != null) timeout.cancel();
    }


    private class ClientResponseHandler extends UascClientResponseHandler {

        @Override
        protected void handleResponse(long requestId, UaResponseMessageType responseMessage) {
            CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

            if (responseFuture != null) {
                cancelRequestTimeout(requestId);

                // TODO use configurable executor
                Stack.sharedExecutor().submit(() -> responseFuture.complete(responseMessage));
            } else {
                logger.warn("Received response for unknown request, requestId={}", requestId);
            }
        }

        @Override
        protected void handleSendFailure(long requestId, UaException exception) {
            CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

            if (responseFuture != null) {
                cancelRequestTimeout(requestId);

                // TODO use configurable executor
                Stack.sharedExecutor().submit(() -> responseFuture.completeExceptionally(exception));
            } else {
                logger.warn("Send failed for unknown request, requestId={}", requestId);
            }
        }

        @Override
        protected void handleReceiveFailure(long requestId, UaException exception) {
            CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

            if (responseFuture != null) {
                cancelRequestTimeout(requestId);

                // TODO use configurable executor
                Stack.sharedExecutor().submit(() -> responseFuture.completeExceptionally(exception));
            } else {
                logger.warn("Receive failed for unknown request, requestId={}", requestId);
            }
        }

    }


    private class ClientChannelActions implements ChannelActions {

        private final Logger logger = LoggerFactory.getLogger(CHANNEL_FSM_LOGGER_NAME);

        private final UascClientConfig config;

        private ClientChannelActions(UascClientConfig config) {
            this.config = config;
        }

        @Override
        public CompletableFuture<Channel> connect(FsmContext<State, Event> ctx) {
            var handshakeFuture = new CompletableFuture<ClientSecureChannel>();

            var bootstrap = new Bootstrap();

            bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5_000)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        var responseHandler = new ClientResponseHandler();

                        var acknowledgeHandler = new UascClientAcknowledgeHandler(
                            config,
                            requestId::getAndIncrement,
                            handshakeFuture
                        );

                        ch.pipeline().addLast(responseHandler);
                        ch.pipeline().addLast(acknowledgeHandler);
                    }
                });

            String endpointUrl = config.getEndpoint().getEndpointUrl();

            String host = EndpointUtil.getHost(endpointUrl);
            assert host != null;
            int port = EndpointUtil.getPort(endpointUrl);

            bootstrap.connect(host, port).addListener((ChannelFuture f) -> {
                if (!f.isSuccess()) {
                    Throwable cause = f.cause();

                    if (cause instanceof ConnectTimeoutException) {
                        handshakeFuture.completeExceptionally(
                            new UaException(StatusCodes.Bad_Timeout, f.cause())
                        );
                    } else if (cause instanceof ConnectException) {
                        handshakeFuture.completeExceptionally(
                            new UaException(StatusCodes.Bad_ConnectionRejected, f.cause())
                        );
                    } else {
                        handshakeFuture.completeExceptionally(cause);
                    }
                }
            });

            return handshakeFuture.thenApply(ClientSecureChannel::getChannel);
        }

        @Override
        public CompletableFuture<Void> disconnect(FsmContext<State, Event> ctx, Channel channel) {
            var disconnectFuture = new CompletableFuture<Void>();

            TimerTask onTimeout = t -> channel.close().addListener(
                (ChannelFutureListener) channelFuture ->
                    disconnectFuture.complete(null)
            );

            Timeout timeout = config.getWheelTimer().newTimeout(
                onTimeout,
                5,
                TimeUnit.SECONDS
            );

            channel.pipeline().addFirst(new ChannelInboundHandlerAdapter() {
                @Override
                public void channelInactive(ChannelHandlerContext channelContext) throws Exception {
                    logger.debug("[{}] channelInactive() disconnect complete", ctx.getInstanceId());
                    timeout.cancel();
                    disconnectFuture.complete(null);
                    super.channelInactive(channelContext);
                }
            });

            var requestHeader = new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.now(),
                uint(0),
                uint(0),
                null,
                uint(0),
                null
            );

            logger.debug("[{}] Sending CloseSecureChannelRequest...", ctx.getInstanceId());

            channel.pipeline().fireUserEventTriggered(new CloseSecureChannelRequest(requestHeader));

            return disconnectFuture;
        }

    }


}

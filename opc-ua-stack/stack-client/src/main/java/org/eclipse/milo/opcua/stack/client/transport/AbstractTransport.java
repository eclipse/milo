/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.client.transport;

import java.nio.channels.ClosedChannelException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTransport implements UaTransport {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final HashedWheelTimer wheelTimer;

    private final UaStackClientConfig config;

    public AbstractTransport(UaStackClientConfig config) {
        this.config = config;

        wheelTimer = config.getWheelTimer();
    }

    /**
     * Get the {@link UaStackClientConfig} of the {@link UaStackClient} this transport instance belongs to.
     *
     * @return the {@link UaStackClientConfig} of the {@link UaStackClient} this transport instance belongs to.
     */
    public UaStackClientConfig getConfig() {
        return config;
    }

    /**
     * Get a {@link Channel} suitable for sending a request on.
     * <p>
     * The Channel must have a handler capable of matching inbound responses to pending outbound
     * {@link UaTransportRequest}s.
     *
     * @return a {@link Channel} suitable for sending a request on.
     */
    public abstract CompletableFuture<Channel> channel();

    @Override
    public CompletableFuture<UaResponseMessage> sendRequest(UaRequestMessage request) {
        return channel().thenCompose(channel -> sendRequest(request, channel, true));
    }

    protected CompletableFuture<UaResponseMessage> sendRequest(
        UaRequestMessage request,
        Channel channel,
        boolean firstAttempt) {

        UaTransportRequest transportRequest = new UaTransportRequest(request);

        scheduleRequestTimeout(transportRequest);

        transportRequest.getFuture().whenComplete(
            (response, ex) ->
                cancelRequestTimeout(transportRequest)
        );

        channel.writeAndFlush(transportRequest).addListener(f -> {
            if (!f.isSuccess()) {
                Throwable cause = f.cause();

                if (cause instanceof ClosedChannelException && firstAttempt) {
                    logger.debug("Write failed, channel closed; retrying...");

                    Stack.sharedScheduledExecutor().schedule(
                        () -> config.getExecutor().execute(() -> {
                            CompletableFuture<UaResponseMessage> sendAgain =
                                channel().thenCompose(ch -> sendRequest(request, ch, false));

                            sendAgain.whenComplete((r, ex) -> {
                                if (r != null) {
                                    transportRequest.getFuture().complete(r);
                                } else {
                                    transportRequest.getFuture().completeExceptionally(ex);
                                }
                            });
                        }),
                        1,
                        TimeUnit.SECONDS
                    );
                } else {
                    transportRequest.getFuture().completeExceptionally(cause);

                    logger.debug(
                        "Write failed, request={}, requestHandle={}",
                        request.getClass().getSimpleName(),
                        request.getRequestHeader().getRequestHandle());
                }
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace(
                        "Write succeeded for request={}, requestHandle={}",
                        request.getClass().getSimpleName(),
                        request.getRequestHeader().getRequestHandle());
                }
            }
        });

        return transportRequest.getFuture();
    }

    private void scheduleRequestTimeout(UaTransportRequest transportRequest) {
        RequestHeader requestHeader = transportRequest.getRequest().getRequestHeader();

        long timeoutHint = requestHeader.getTimeoutHint() != null ?
            requestHeader.getTimeoutHint().longValue() : 0L;

        if (timeoutHint > 0) {
            Timeout timeout = wheelTimer.newTimeout(
                t -> {
                    UaException exception = new UaException(
                        StatusCodes.Bad_Timeout,
                        String.format(
                            "requestId=%s timed out after %sms",
                            requestHeader.getRequestHandle(), timeoutHint)
                    );

                    transportRequest.getFuture().completeExceptionally(exception);
                },
                timeoutHint,
                TimeUnit.MILLISECONDS
            );

            transportRequest.setTimeout(timeout);
        }
    }

    private void cancelRequestTimeout(UaTransportRequest transportRequest) {
        Timeout timeout = transportRequest.getTimeout();
        if (timeout != null) timeout.cancel();
    }

}

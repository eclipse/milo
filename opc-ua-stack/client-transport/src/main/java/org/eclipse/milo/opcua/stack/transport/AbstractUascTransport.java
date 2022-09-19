/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.transport.uasc.UascClientConfig;
import org.eclipse.milo.opcua.stack.transport.uasc.UascClientResponseHandler;
import org.eclipse.milo.opcua.stack.transport.uasc.UascMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractUascTransport extends UascClientResponseHandler implements OpcTransport {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<Long, CompletableFuture<UaResponseMessageType>> pendingRequests = new ConcurrentHashMap<>();
    private final Map<Long, Timeout> pendingTimeouts = new ConcurrentHashMap<>();

    private final UascClientConfig config;

    public AbstractUascTransport(UascClientConfig config) {
        this.config = config;
    }


    protected abstract CompletableFuture<Channel> getChannel();

    protected abstract long nextRequestId();

    @Override
    public CompletableFuture<UaResponseMessageType> sendRequestMessage(UaRequestMessageType requestMessage) {
        return getChannel().thenCompose(ch -> sendRequestMessage(requestMessage, ch));
    }

    private CompletableFuture<UaResponseMessageType> sendRequestMessage(
        UaRequestMessageType requestMessage,
        Channel channel
    ) {

        var request = new UascMessage.Request(nextRequestId(), requestMessage);
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

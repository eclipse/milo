/*
 * Copyright (c) 2018 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.client;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Maps;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.https.OpcHttpsTransport;
import org.eclipse.milo.opcua.stack.client.transport.tcp.OpcTcpTransport;
import org.eclipse.milo.opcua.stack.client.transport.websocket.OpcWebSocketTransport;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaServiceFaultException;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.eclipse.milo.opcua.stack.core.util.LongSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class UaStackClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LongSequence requestHandles = new LongSequence(0, UInteger.MAX_VALUE);

    private final Map<UInteger, CompletableFuture<UaResponseMessage>> pending = Maps.newConcurrentMap();
    private final Map<UInteger, Timeout> timeouts = Maps.newConcurrentMap();

    private final ExecutionQueue deliveryQueue;
    private final HashedWheelTimer wheelTimer;

    private final UaStackClientConfig config;
    private final UaTransport transport;

    public UaStackClient(UaStackClientConfig config, UaTransport transport) {
        this.config = config;
        this.transport = transport;


        deliveryQueue = new ExecutionQueue(config.getExecutor());
        wheelTimer = config.getWheelTimer();
    }

    public UaStackClientConfig getConfig() {
        return config;
    }

    public CompletableFuture<UaStackClient> connect() {
        return transport.connect()
            .thenApply(t -> UaStackClient.this);
    }

    public CompletableFuture<UaStackClient> disconnect() {
        return transport.disconnect()
            .whenComplete((u, ex) -> {
                UaException disconnect = new UaException(
                    StatusCodes.Bad_Disconnect, "client disconnect");

                pending.forEach((h, cf) -> cf.completeExceptionally(disconnect));
                pending.clear();

                timeouts.forEach((h, t) -> t.cancel());
                timeouts.clear();
            })
            .thenApply(t -> UaStackClient.this);
    }

    public RequestHeader newRequestHeader() {
        return newRequestHeader(NodeId.NULL_VALUE);
    }

    public RequestHeader newRequestHeader(NodeId authToken) {
        return newRequestHeader(authToken, config.getRequestTimeout());
    }

    public RequestHeader newRequestHeader(NodeId authToken, UInteger requestTimeout) {
        return new RequestHeader(
            authToken,
            DateTime.now(),
            uint(requestHandles.getAndIncrement()),
            uint(0),
            null,
            requestTimeout,
            null
        );
    }

    public CompletableFuture<UaResponseMessage> sendRequest(UaRequestMessage request) {
        RequestHeader requestHeader = request.getRequestHeader();
        UInteger requestHandle = requestHeader.getRequestHandle();

        CompletableFuture<UaResponseMessage> future = new CompletableFuture<>();

        pending.put(requestHandle, future);
        scheduleRequestTimeout(requestHeader);

        transport.sendRequest(request).whenComplete((response, ex) -> {
            if (response != null) {
                receiveResponse(request, response);
            } else {
                pending.remove(requestHandle);
                future.completeExceptionally(ex);

                Timeout timeout = timeouts.remove(requestHandle);
                if (timeout != null) timeout.cancel();
            }
        });

        return future;
    }

    private void receiveResponse(UaRequestMessage request, UaResponseMessage response) {
        ResponseHeader header = response.getResponseHeader();
        UInteger requestHandle = header.getRequestHandle();

        CompletableFuture<UaResponseMessage> future = pending.remove(requestHandle);

        Timeout timeout = timeouts.remove(requestHandle);
        if (timeout != null) timeout.cancel();

        if (future != null) {
            deliveryQueue.submit(() -> {
                if (header.getServiceResult().isGood()) {
                    future.complete(response);
                } else {
                    ServiceFault serviceFault;

                    if (response instanceof ServiceFault) {
                        serviceFault = (ServiceFault) response;
                    } else {
                        serviceFault = new ServiceFault(header);
                    }

                    if (logger.isDebugEnabled()) {
                        logger.debug(
                            "Received ServiceFault request={} requestHandle={}, result={}",
                            request.getClass().getSimpleName(),
                            requestHandle,
                            header.getServiceResult());
                    }

                    future.completeExceptionally(new UaServiceFaultException(serviceFault));
                }
            });
        } else {
            logger.warn(
                "Received unmatched {} with requestHandle={}, timestamp={}",
                response.getClass().getSimpleName(),
                requestHandle,
                response.getResponseHeader().getTimestamp());
        }
    }

    private void scheduleRequestTimeout(RequestHeader requestHeader) {
        UInteger requestHandle = requestHeader.getRequestHandle();

        long timeoutHint = requestHeader.getTimeoutHint() != null ?
            requestHeader.getTimeoutHint().longValue() : 0L;

        if (timeoutHint > 0) {
            Timeout timeout = wheelTimer.newTimeout(t -> {
                if (timeouts.remove(requestHandle) != null && !t.isCancelled()) {
                    CompletableFuture<UaResponseMessage> f = pending.remove(requestHandle);
                    if (f != null) {
                        String message = "request timed out after " + timeoutHint + "ms";
                        f.completeExceptionally(new UaException(StatusCodes.Bad_Timeout, message));
                    }
                }
            }, timeoutHint, TimeUnit.MILLISECONDS);

            timeouts.put(requestHandle, timeout);
        }
    }

    public static UaStackClient create(UaStackClientConfig config) throws UaException {
        String transportProfileUri =
            config.getEndpoint().getTransportProfileUri();

        TransportProfile transportProfile =
            TransportProfile.fromUri(transportProfileUri);

        UaTransport transport;

        switch (transportProfile) {
            case TCP_UASC_UABINARY:
                transport = new OpcTcpTransport(config);
                break;

            case HTTPS_UABINARY:
                transport = new OpcHttpsTransport(config);
                break;

            case WSS_UASC_UABINARY:
                transport = new OpcWebSocketTransport(config);
                break;

            case HTTPS_UAXML:
            case HTTPS_UAJSON:
            case WSS_UAJSON:
            default:
                throw new UaException(
                    StatusCodes.Bad_InternalError,
                    "unsupported transport: " + transportProfileUri);
        }

        return new UaStackClient(config, transport);
    }

}

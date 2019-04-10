/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.client;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.client.transport.UaTransport;
import org.eclipse.milo.opcua.stack.client.transport.http.OpcHttpTransport;
import org.eclipse.milo.opcua.stack.client.transport.tcp.OpcTcpTransport;
import org.eclipse.milo.opcua.stack.client.transport.websocket.OpcWebSocketTransport;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaServiceFaultException;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.DefaultDataTypeManager;
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

    private final DataTypeManager dataTypeManager = new DefaultDataTypeManager();
    private final NamespaceTable namespaceTable = new NamespaceTable();

    private final UaTransport transport;
    private final ExecutionQueue deliveryQueue;
    private final SerializationContext serializationContext;

    private final UaStackClientConfig config;

    public UaStackClient(
        UaStackClientConfig config,
        Function<UaStackClient, UaTransport> transportFactory
    ) {

        this.config = config;

        deliveryQueue = new ExecutionQueue(config.getExecutor());

        serializationContext = new SerializationContext() {
            @Override
            public EncodingLimits getEncodingLimits() {
                return config.getEncodingLimits();
            }

            @Override
            public NamespaceTable getNamespaceTable() {
                return namespaceTable;
            }

            @Override
            public DataTypeManager getDataTypeManager() {
                return dataTypeManager;
            }
        };

        transport = transportFactory.apply(this);
    }

    /**
     * @return the {@link UaStackClientConfig} this client was created with.
     */
    public UaStackClientConfig getConfig() {
        return config;
    }

    /**
     * Connect this {@link UaStackClient} to the server in the configured endpoint.
     * <p>
     * Depending on the underlying transport this may or may not actually make a connection attempt.
     *
     * @return this {@link UaStackClient}.
     */
    public CompletableFuture<UaStackClient> connect() {
        return transport.connect()
            .thenApply(t -> UaStackClient.this);
    }

    /**
     * Disconnect this {@link UaStackClient} from the server in the configured endpoint.
     * <p>
     * Depending on the underlying transport this may or may not actually make a disconnect attempt.
     *
     * @return this {@link UaStackClient}.
     */
    public CompletableFuture<UaStackClient> disconnect() {
        return transport.disconnect()
            .whenComplete((u, ex) -> {
                UaException disconnect = new UaException(
                    StatusCodes.Bad_Disconnect, "client disconnect");

                pending.forEach((h, cf) -> cf.completeExceptionally(disconnect));
                pending.clear();
            })
            .thenApply(t -> UaStackClient.this);
    }

    /**
     * Get the client {@link DataTypeManager}.
     *
     * @return the client {@link DataTypeManager}.
     */
    public DataTypeManager getDataTypeManager() {
        return dataTypeManager;
    }

    /**
     * Get the client {@link NamespaceTable}.
     *
     * @return the client {@link NamespaceTable}.
     */
    public NamespaceTable getNamespaceTable() {
        return namespaceTable;
    }

    /**
     * Get the client {@link SerializationContext}.
     *
     * @return the client {@link SerializationContext}.
     */
    public SerializationContext getSerializationContext() {
        return serializationContext;
    }

    /**
     * Create a new {@link RequestHeader} with a null authentication token.
     * <p>
     * A unique request handle will be automatically assigned to the header.
     *
     * @return a new {@link RequestHeader} with a null authentication token.
     */
    public RequestHeader newRequestHeader() {
        return newRequestHeader(NodeId.NULL_VALUE);
    }

    /**
     * Create a new {@link RequestHeader} with {@code authToken}.
     * <p>
     * A unique request handle will be automatically assigned to the header.
     *
     * @param authToken the authentication token to create the header with.
     * @return a new {@link RequestHeader} created with {@code authToken}.
     */
    public RequestHeader newRequestHeader(NodeId authToken) {
        return newRequestHeader(authToken, config.getRequestTimeout());
    }

    /**
     * Create a new {@link RequestHeader} with {@code authToken} and {@code requestTimeout}.
     * <p>
     * A unique request handle will be automatically assigned to the header.
     *
     * @param authToken      the authentication token to create the header with.
     * @param requestTimeout the timeout hint to create the header with.f
     * @return a new {@link RequestHeader} created with {@code authToken} and {@code requestTimeout}.
     */
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

    /**
     * Send a {@link UaRequestMessage} to the connected server.
     * <p>
     * The {@link RequestHeader} of {@code request} must have a unique request handle. Use the
     * {@code newRequestHeader} helper functions to create headers for {@link UaRequestMessage}s.
     *
     * @param request the {@link UaRequestMessage} to send.
     * @return a {@link CompletableFuture} containing the eventual {@link UaResponseMessage} from the server.
     * @see #newRequestHeader()
     * @see #newRequestHeader(NodeId)
     * @see #newRequestHeader(NodeId, UInteger)
     */
    public CompletableFuture<UaResponseMessage> sendRequest(UaRequestMessage request) {
        RequestHeader requestHeader = request.getRequestHeader();
        UInteger requestHandle = requestHeader.getRequestHandle();

        final CompletableFuture<UaResponseMessage> future = new CompletableFuture<>();
        pending.put(requestHandle, future);

        transport.sendRequest(request).whenComplete((response, ex) -> {
            pending.remove(requestHandle);

            if (response != null) {
                deliverResponse(request, response, future);
            } else {
                future.completeExceptionally(ex);
            }
        });

        return future;
    }

    /**
     * Complete {@code future} with {@code response} on the {@code deliveryQueue}.
     * <p>
     * This is done for two reasons:
     * 1. the transport future is completed on its serialization queue thread, which we want to get off of ASAP.
     * 2. the futures need to be completed serially, in the order received from the server.
     *
     * @param request  the original {@link UaRequestMessage}.
     * @param response the {@link UaResponseMessage}.
     * @param future   the {@link CompletableFuture} awaiting completion.
     */
    private void deliverResponse(
        UaRequestMessage request,
        UaResponseMessage response,
        CompletableFuture<UaResponseMessage> future) {

        ResponseHeader header = response.getResponseHeader();
        UInteger requestHandle = header.getRequestHandle();

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
    }

    /**
     * Create a {@link UaStackClient} with {@code config}.
     * <p>
     * The {@link UaTransport} instance to create will be inferred from the transport profile URI in the configured
     * endpoint.
     * <p>
     * Supported transports:
     * <ul>
     * <li>TCP + UA Binary</li>
     * <li>HTTP(s) + UA Binary</li>
     * </ul>
     * <p>
     * Experimentally supported:
     * <ul>
     * <li>WebSocket + UA Binary</li>
     * </ul>
     * <p>
     * Not supported:
     * <ul>
     * <li>HTTP(s) + UA JSON</li>
     * <li>HTTP(s) + UA XML</li>
     * <li>WebSocket + UA JSON</li>
     * </ul>
     *
     * @param config the {@link UaStackClientConfig}.
     * @return a {@link UaStackClient} created with {@code config}.
     * @throws UaException if the transport is unsupported.
     */
    public static UaStackClient create(UaStackClientConfig config) throws UaException {
        String transportProfileUri =
            config.getEndpoint().getTransportProfileUri();

        TransportProfile transportProfile =
            TransportProfile.fromUri(transportProfileUri);

        Function<UaStackClient, UaTransport> transportFactory;

        switch (transportProfile) {
            case TCP_UASC_UABINARY:
                transportFactory = OpcTcpTransport::new;
                break;

            case HTTPS_UABINARY:
                transportFactory = OpcHttpTransport::new;
                break;

            case WSS_UASC_UABINARY:
                transportFactory = OpcWebSocketTransport::new;
                break;

            case HTTPS_UAXML:
            case HTTPS_UAJSON:
            case WSS_UAJSON:
            default:
                throw new UaException(
                    StatusCodes.Bad_InternalError,
                    "unsupported transport: " + transportProfileUri);
        }

        return new UaStackClient(config, transportFactory);
    }

}

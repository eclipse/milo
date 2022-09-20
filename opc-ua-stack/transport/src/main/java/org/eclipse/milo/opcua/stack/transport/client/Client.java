/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.transport.client.tcp.OpcTcpTransport;
import org.eclipse.milo.opcua.stack.transport.client.tcp.OpcTcpTransportConfig;
import org.eclipse.milo.opcua.stack.transport.client.tcp.OpcTcpTransportConfigBuilder;

public abstract class Client {

    public static Client create(OpcTransportConfig config) {
        return null;
    }

    public static Client create(String endpointUrl) {
        return null;
    }

    public static Client create(
        String endpointUrl,
        Function<List<EndpointDescription>, Optional<EndpointDescription>> selectEndpoint,
        Consumer<OpcTcpTransportConfigBuilder> configureTransport,
        Consumer<ClientConfigBuilder> configureClient
    ) {

        OpcTcpTransportConfigBuilder tcb = OpcTcpTransportConfig.newBuilder();
        configureTransport.accept(tcb);
        OpcTcpTransportConfig transportConfig = tcb.build();

        ClientConfigBuilder ccb = ClientConfig.newBuilder();
        configureClient.accept(ccb);
        ClientConfig clientConfig = ccb.build();

        var transport = new OpcTcpTransport(transportConfig);
        return new Client(transport, clientConfig) {};
    }

    private final OpcTransport transport;

    public Client(OpcTransport transport, ClientConfig clientConfig) {
        this.transport = transport;
    }

    public void connect() throws UaException {
        try {
            transport.connect().get();
        } catch (InterruptedException | ExecutionException e) {
            throw UaException.extract(e)
                .orElseGet(() -> new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public void disconnect() throws UaException {
        try {
            transport.disconnect().get();
        } catch (InterruptedException | ExecutionException e) {
            throw UaException.extract(e)
                .orElseGet(() -> new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public UaResponseMessageType sendRequest(UaRequestMessageType request) throws UaException {
        try {
            return transport.sendRequestMessage(request).get();
        } catch (InterruptedException | ExecutionException e) {
            throw UaException.extract(e)
                .orElseGet(() -> new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<UaResponseMessageType> sendRequestAsync(UaRequestMessageType request) {
        return transport.sendRequestMessage(request);
    }

}

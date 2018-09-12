/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.stack.examples.client;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ClientExample {

    private final AtomicLong requestHandle = new AtomicLong(1L);

    private final UaStackClient client;

    public ClientExample(X509Certificate certificate, KeyPair keyPair) throws Exception {
        // Query endpoints and select highest security level.
        List<EndpointDescription> endpoints = DiscoveryClient
            .getEndpoints("opc.tcp://localhost:12685/example").get();

        EndpointDescription endpoint = endpoints.stream()
            .sorted((e1, e2) -> e2.getSecurityLevel().intValue() - e1.getSecurityLevel().intValue())
            .findFirst()
            .orElseThrow(() -> new Exception("no endpoints returned"));

        UaStackClientConfig config = UaStackClientConfig.builder()
            .setCertificate(certificate)
            .setKeyPair(keyPair)
            .setEndpoint(endpoint)
            .build();

        client = UaStackClient.create(config);
    }

    public CompletableFuture<ReadResponse> testStack(NodeId nodeId) {
        RequestHeader header = new RequestHeader(
            NodeId.NULL_VALUE,
            DateTime.now(),
            uint(requestHandle.getAndIncrement()),
            uint(0),
            null,
            uint(60000),
            null
        );

        ReadRequest request = new ReadRequest(
            header,
            0.0,
            TimestampsToReturn.Neither,
            new ReadValueId[]{
                new ReadValueId(
                    nodeId,
                    AttributeId.Value.uid(),
                    null,
                    null)
            }
        );

        return client.sendRequest(request)
            .thenApply(ReadResponse.class::cast);
    }

    public CompletableFuture<UaStackClient> disconnect() {
        return client.disconnect();
    }

}

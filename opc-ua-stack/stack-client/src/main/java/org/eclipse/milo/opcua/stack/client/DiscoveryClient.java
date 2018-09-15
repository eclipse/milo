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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedFuture;

public class DiscoveryClient {

    private final UaStackClient stackClient;

    public DiscoveryClient(UaStackClient stackClient) {
        this.stackClient = stackClient;
    }

    public CompletableFuture<DiscoveryClient> connect() {
        return stackClient.connect().thenApply(c -> DiscoveryClient.this);
    }

    public CompletableFuture<DiscoveryClient> disconnect() {
        return stackClient.disconnect().thenApply(c -> DiscoveryClient.this);
    }

    public CompletableFuture<GetEndpointsResponse> getEndpoints(
        String endpointUrl,
        String[] localeIds,
        String[] profileUris) {

        RequestHeader header = stackClient.newRequestHeader(
            NodeId.NULL_VALUE,
            stackClient.getConfig().getRequestTimeout()
        );

        GetEndpointsRequest request = new GetEndpointsRequest(
            header,
            endpointUrl,
            localeIds,
            profileUris
        );

        return stackClient.sendRequest(request)
            .thenApply(GetEndpointsResponse.class::cast);
    }

    public static CompletableFuture<List<EndpointDescription>> getEndpoints(String endpointUrl) {
        String scheme = EndpointUtil.getScheme(endpointUrl);

        String profileUri;

        if ("opc.tcp".equalsIgnoreCase(scheme)) {
            profileUri = Stack.TCP_UASC_UABINARY_TRANSPORT_URI;
        } else if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme) ||
            "opc.http".equalsIgnoreCase(scheme) || "opc.https".equalsIgnoreCase(scheme)) {

            profileUri = Stack.HTTPS_UABINARY_TRANSPORT_URI;
        } else {
            return failedFuture(
                new UaException(
                    StatusCodes.Bad_InternalError,
                    "unsupported protocol: " + scheme));
        }

        return getEndpoints(endpointUrl, profileUri);
    }

    private static CompletableFuture<List<EndpointDescription>> getEndpoints(String endpointUrl, String profileUri) {
        EndpointDescription endpoint = new EndpointDescription(
            endpointUrl,
            null,
            null,
            MessageSecurityMode.None,
            SecurityPolicy.None.getSecurityPolicyUri(),
            null,
            profileUri,
            ubyte(0)
        );

        UaStackClientConfig config = UaStackClientConfig.builder()
            .setEndpoint(endpoint)
            .build();

        try {
            UaStackClient stackClient = UaStackClient.create(config);

            DiscoveryClient discoveryClient = new DiscoveryClient(stackClient);

            return discoveryClient
                .connect()
                .thenCompose(c -> c.getEndpoints(endpointUrl, new String[0], new String[0]))
                .whenComplete((e, ex) -> discoveryClient.disconnect())
                .thenApply(response -> l(response.getEndpoints()));
        } catch (UaException e) {
            return failedFuture(e);
        }
    }

}

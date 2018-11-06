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
import java.util.function.Function;

import com.google.common.base.Strings;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
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

    public CompletableFuture<FindServersResponse> findServers(
        String endpointUrl,
        String[] localeIds,
        String[] serverUris) {

        RequestHeader header = stackClient.newRequestHeader(
            NodeId.NULL_VALUE,
            stackClient.getConfig().getRequestTimeout()
        );

        FindServersRequest request = new FindServersRequest(
            header,
            endpointUrl,
            localeIds,
            serverUris
        );

        return stackClient.sendRequest(request)
            .thenApply(FindServersResponse.class::cast);
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

    public static CompletableFuture<List<ApplicationDescription>> findServers(String endpointUrl) {
        return findServers(endpointUrl, new String[0]);
    }

    private static CompletableFuture<List<ApplicationDescription>> findServers(
        String endpointUrl,
        String[] serverUris) {

        return withDiscoveryClient(endpointUrl, dc -> {
            CompletableFuture<FindServersResponse> future =
                dc.findServers(endpointUrl, new String[0], serverUris);

            return future.thenApply(r -> l(r.getServers()));
        });
    }

    public static CompletableFuture<List<EndpointDescription>> getEndpoints(String endpointUrl) {
        try {
            String profileUri = getProfileUri(endpointUrl);

            return getEndpoints(endpointUrl, profileUri);
        } catch (UaException e) {
            return failedFuture(e);
        }
    }

    private static CompletableFuture<List<EndpointDescription>> getEndpoints(String endpointUrl, String profileUri) {
        return withDiscoveryClient(endpointUrl, dc -> {
            CompletableFuture<GetEndpointsResponse> future =
                dc.getEndpoints(endpointUrl, new String[0], new String[]{profileUri});

            return future.thenApply(r -> l(r.getEndpoints()));
        });
    }

    private static <T> CompletableFuture<T> withDiscoveryClient(
        String endpointUrl,
        Function<DiscoveryClient, CompletableFuture<T>> f) {

        try {
            String profileUri = getProfileUri(endpointUrl);

            EndpointDescription endpoint = new EndpointDescription(
                endpointUrl,
                null,
                null,
                MessageSecurityMode.None,
                SecurityPolicy.None.getUri(),
                null,
                profileUri,
                ubyte(0)
            );

            UaStackClientConfig config = UaStackClientConfig.builder()
                .setEndpoint(endpoint)
                .setConnectPersistent(false)
                .build();

            UaStackClient stackClient = UaStackClient.create(config);

            DiscoveryClient discoveryClient = new DiscoveryClient(stackClient);

            return discoveryClient
                .connect()
                .thenCompose(f::apply)
                .whenComplete((e, ex) -> discoveryClient.disconnect());
        } catch (UaException e) {
            return failedFuture(e);
        }
    }

    private static String getProfileUri(String endpointUrl) throws UaException {
        String scheme = EndpointUtil.getScheme(endpointUrl);

        switch (Strings.nullToEmpty(scheme).toLowerCase()) {
            case "opc.tcp":
                return Stack.TCP_UASC_UABINARY_TRANSPORT_URI;

            case "http":
            case "https":
            case "opc.http":
            case "opc.https":
                return Stack.HTTPS_UABINARY_TRANSPORT_URI;

            case "opc.ws":
            case "opc.wss":
                return Stack.WSS_UASC_UABINARY_TRANSPORT_URI;

            default:
                throw new UaException(
                    StatusCodes.Bad_InternalError,
                    "unsupported protocol: " + scheme);
        }
    }

}

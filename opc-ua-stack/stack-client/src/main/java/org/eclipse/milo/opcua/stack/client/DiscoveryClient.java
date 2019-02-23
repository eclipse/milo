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

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    /**
     * Query the FindServers service at the {@code endpointUrl}.
     * <p>
     * The endpoint URL(s) for each server {@link ApplicationDescription} in the response can then be used in a
     * {@link #getEndpoints(String)} call to discover the endpoints for that server.
     *
     * @param endpointUrl the endpoint URL to find servers at.
     * @param localeIds   list of locales to use. The server should return the applicationName in the
     *                    ApplicationDescription using one of locales specified. If the server supports more than one of
     *                    the requested locales then the server shall use the locale that appears first in this list. If
     *                    the server does not support any of the requested locales it chooses an appropriate default
     *                    locale. The server chooses an appropriate default locale if this list is empty.
     * @param serverUris  list of servers to return. All known servers are returned if the list is empty.
     * @return the {@link FindServersResponse}s returned by the FindServers service.
     */
    public CompletableFuture<FindServersResponse> findServers(
        String endpointUrl,
        String[] localeIds,
        String[] serverUris
    ) {

        RequestHeader requestHeader = stackClient.newRequestHeader(
            NodeId.NULL_VALUE,
            stackClient.getConfig().getRequestTimeout()
        );

        FindServersRequest request = new FindServersRequest(
            requestHeader,
            endpointUrl,
            localeIds,
            serverUris
        );

        return stackClient.sendRequest(request)
            .thenApply(FindServersResponse.class::cast);
    }

    /**
     * Query the GetEndpoints service at {@code endpointUrl}.
     *
     * @param endpointUrl the endpoint URL to get endpoints from.
     * @param localeIds   list of locales to use. Specifies the locale to use when returning human readable strings.
     * @param profileUris list of Transport Profile that the returned Endpoints shall support. All Endpoints are
     *                    returned if the list is empty.
     * @return the {@link GetEndpointsResponse} returned by the GetEndpoints service.
     */
    public CompletableFuture<GetEndpointsResponse> getEndpoints(
        String endpointUrl,
        String[] localeIds,
        String[] profileUris
    ) {

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

    /**
     * Query the FindServers service at the {@code endpointUrl}.
     * <p>
     * The discovery URL(s) for each server {@link ApplicationDescription} in the response can then be used in a
     * {@link #getEndpoints(String)} call to discover the endpoints for that server.
     *
     * @param endpointUrl the endpoint URL to find servers at.
     * @return a List of {@link ApplicationDescription}s returned by the FindServers service.
     */
    public static CompletableFuture<List<ApplicationDescription>> findServers(String endpointUrl) {
        EndpointDescription endpoint = new EndpointDescription(
            endpointUrl,
            null,
            null,
            MessageSecurityMode.None,
            SecurityPolicy.None.getUri(),
            null,
            Stack.TCP_UASC_UABINARY_TRANSPORT_URI,
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
                .thenCompose(c -> c.findServers(endpointUrl, new String[0], new String[0]))
                .whenComplete((e, ex) -> discoveryClient.disconnect())
                .thenApply(response -> l(response.getServers()));
        } catch (UaException e) {
            return failedFuture(e);
        }
    }

    /**
     * Query the GetEndpoints service at {@code endpointUrl}.
     *
     * @param endpointUrl the endpoint URL to get endpoints from.
     * @return a List of {@link EndpointDescription}s returned by the GetEndpoints service.
     */
    public static CompletableFuture<List<EndpointDescription>> getEndpoints(String endpointUrl) {
        String scheme = EndpointUtil.getScheme(endpointUrl);

        String profileUri;

        switch (Strings.nullToEmpty(scheme).toLowerCase()) {
            case "opc.tcp":
                profileUri = Stack.TCP_UASC_UABINARY_TRANSPORT_URI;
                break;

            case "http":
            case "https":
            case "opc.http":
            case "opc.https":
                profileUri = Stack.HTTPS_UABINARY_TRANSPORT_URI;
                break;

            case "opc.ws":
            case "opc.wss":
                profileUri = Stack.WSS_UASC_UABINARY_TRANSPORT_URI;
                break;

            default:
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
            SecurityPolicy.None.getUri(),
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
                .thenCompose(c -> c.getEndpoints(endpointUrl, new String[0], new String[]{profileUri}))
                .whenComplete((e, ex) -> discoveryClient.disconnect())
                .thenApply(response -> l(response.getEndpoints()));
        } catch (UaException e) {
            return failedFuture(e);
        }
    }

}

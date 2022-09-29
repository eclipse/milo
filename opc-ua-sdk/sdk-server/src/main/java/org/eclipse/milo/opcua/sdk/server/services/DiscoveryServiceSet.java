/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Request;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Response;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerResponse;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

public interface DiscoveryServiceSet {


    CompletableFuture<FindServersResponse> onFindServers(ServiceRequestContext context, FindServersRequest request);

    CompletableFuture<FindServersOnNetworkResponse> onFindServersOnNetwork(
        ServiceRequestContext context,
        FindServersOnNetworkRequest request
    );

    CompletableFuture<GetEndpointsResponse> onGetEndpoints(
        ServiceRequestContext context,
        GetEndpointsRequest request
    );

    CompletableFuture<RegisterServerResponse> onRegisterServer(
        ServiceRequestContext context,
        RegisterServerRequest request
    );

    CompletableFuture<RegisterServer2Response> onRegisterServer2(
        ServiceRequestContext context,
        RegisterServer2Request request
    );

}

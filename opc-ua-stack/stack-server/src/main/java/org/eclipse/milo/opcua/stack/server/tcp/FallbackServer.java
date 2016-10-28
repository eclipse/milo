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

package org.eclipse.milo.opcua.stack.server.tcp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequestHandler;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

/**
 * Provides a "fallback" server for when a UA TCP Hello contains an unknown endpoint URL.
 */
public class FallbackServer {

    private final Set<UaTcpStackServer> registered = Sets.newConcurrentHashSet();
    private final Map<String, UaTcpStackServer> servers = Maps.newConcurrentMap();

    private final UaTcpStackServer server;

    public FallbackServer() {
        UaTcpStackServerConfig config = UaTcpStackServerConfig.builder()
            .setApplicationName(LocalizedText.english("Stack Discovery Server"))
            .setApplicationUri("urn:eclipse:milo:opcua:stack:discovery")
            .setProductUri("https://projects.eclipse.org/projects/iot.milo")
            .build();

        server = new UaTcpStackServer(config);
        server.addRequestHandler(FindServersRequest.class, new FindServersHandler());
        server.addRequestHandler(GetEndpointsRequest.class, new GetEndpointsHandler());
    }

    public void registerServer(UaTcpStackServer server) {
        if (registered.add(server)) {
            server.getDiscoveryUrls().forEach(url -> servers.put(url, server));
        }
    }

    public void unregisterServer(UaTcpStackServer server) {
        if (registered.remove(server)) {
            server.getDiscoveryUrls().forEach(servers::remove);
        }
    }

    public UaTcpStackServer getServer() {
        return server;
    }

    private class GetEndpointsHandler implements ServiceRequestHandler<GetEndpointsRequest, GetEndpointsResponse> {

        @Override
        public void handle(ServiceRequest<GetEndpointsRequest, GetEndpointsResponse> service) throws UaException {
            GetEndpointsRequest request = service.getRequest();

            String endpointUrl = request.getEndpointUrl();
            if (endpointUrl == null) endpointUrl = "";

            UaTcpStackServer server = servers.get(endpointUrl);

            EndpointDescription[] endpoints = (server != null) ?
                server.getEndpointDescriptions() :
                new EndpointDescription[0];

            List<String> profileUris = request.getProfileUris() != null ?
                Lists.newArrayList(request.getProfileUris()) :
                new ArrayList<>();

            EndpointDescription[] filtered = Arrays.stream(endpoints)
                .filter(ed -> filterProfileUris(ed, profileUris))
                .filter(this::filterEndpointUrls)
                .toArray(EndpointDescription[]::new);

            service.setResponse(new GetEndpointsResponse(
                service.createResponseHeader(),
                filtered
            ));
        }

        private boolean filterProfileUris(EndpointDescription endpoint, List<String> profileUris) {
            return profileUris.size() == 0 || profileUris.contains(endpoint.getTransportProfileUri());
        }

        private boolean filterEndpointUrls(EndpointDescription endpoint) {
            return true;
        }

    }

    private class FindServersHandler implements ServiceRequestHandler<FindServersRequest, FindServersResponse> {

        @Override
        public void handle(ServiceRequest<FindServersRequest, FindServersResponse> service) throws UaException {
            FindServersRequest request = service.getRequest();

            List<ApplicationDescription> servers = new ArrayList<>();
            List<String> serverUris = l(request.getServerUris());

            for (UaTcpStackServer server : registered) {
                ApplicationDescription description = server.getApplicationDescription();

                if (serverUris.isEmpty()) {
                    servers.add(description);
                } else {
                    if (serverUris.contains(description.getApplicationUri())) {
                        servers.add(description);
                    }
                }
            }

            ResponseHeader header = service.createResponseHeader();
            FindServersResponse response = new FindServersResponse(
                header, servers.toArray(new ApplicationDescription[servers.size()]));

            service.setResponse(response);
        }

    }

}

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

package org.eclipse.milo.opcua.stack.server;

import java.util.concurrent.CompletableFuture;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.Lazy;
import org.eclipse.milo.opcua.stack.server.tcp.TcpServer;
import org.eclipse.milo.opcua.stack.server.web.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UaStackServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Lazy<ImmutableList<EndpointDescription>> endpointDescriptions = new Lazy<>();

    private final UaStackServerConfig config;

    public UaStackServer(UaStackServerConfig config) {
        this.config = config;
    }

    public UaStackServerConfig getConfig() {
        return config;
    }

    public CompletableFuture<UaStackServer> startup() {
        config.getEndpointConfigurations().forEach(endpoint -> {
            TransportProfile transportProfile = endpoint.getTransportProfile();

            // TODO how is the certificate for HTTPS configured/provided?

            switch (transportProfile) {
                case TCP_UASC_UABINARY:
                    TcpServer.bind(endpoint, this);
                    break;

                case HTTPS_UAXML:
                    WebServer.bind(endpoint, this);
                    break;

                default:
                    logger.warn("Ignoring endpoint for unsupported transport: {}", endpoint);
            }
        });

        return CompletableFuture.completedFuture(this); // TODO
    }

    public CompletableFuture<UaStackServer> shutdown() {
        return CompletableFuture.completedFuture(this); // TODO
    }

    public ImmutableList<EndpointDescription> getEndpoints() {
        return endpointDescriptions.getOrCompute(
            () ->
                ImmutableList.<EndpointDescription>builder()
                    .addAll(
                        config.getEndpointConfigurations()
                            .stream()
                            .map(this::transformEndpoint)
                            .iterator()
                    )
                    .build()
        );
    }

    private EndpointDescription transformEndpoint(EndpointConfiguration configuration) {
        return new EndpointDescription(); // TODO
    }

}

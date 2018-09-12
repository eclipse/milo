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

import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

public class Playground {

    public static void main(String[] args) throws Exception {
        String endpointUrl = args[0];

        List<EndpointDescription> endpoints =
            DiscoveryClient.getEndpoints(endpointUrl).get();

        for (EndpointDescription endpoint : endpoints) {
            SecurityPolicy securityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());
            MessageSecurityMode securityMode = endpoint.getSecurityMode();

            System.out.println(endpoint.getEndpointUrl());
            System.out.println("\tsecurity: " + String.format("%s/%s", securityPolicy, securityMode));
            System.out.println("\ttransport: " + endpoint.getTransportProfileUri());
        }

        EndpointDescription endpoint = endpoints.get(0);

        UaStackClientConfig config = UaStackClientConfig.builder()
            .setEndpoint(endpoint)
            .build();

        UaStackClient client = UaStackClient.create(config);

    }

}

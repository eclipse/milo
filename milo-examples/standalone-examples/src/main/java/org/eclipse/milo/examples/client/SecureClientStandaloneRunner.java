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

package org.eclipse.milo.examples.client;

import java.security.Security;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class SecureClientStandaloneRunner {

    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String APPLICATION_NAME = "fraunhofer opc-ua client";

    private static final String APPLICATION_URI = "urn:eclipse:milo:examples:secureclient";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CompletableFuture<OpcUaClient> future = new CompletableFuture<>();

    private final SecureClientExample clientExample;

    SecureClientStandaloneRunner(SecureClientExample clientExample) {
        this.clientExample = clientExample;
    }

    private OpcUaClient createClient() throws Exception {
        String discoveryUrl = clientExample.getDiscoveryEndpointUrl();
        logger.info("URL of discovery endpoint = {}", discoveryUrl);

        List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints(discoveryUrl).get();

        logger.info("Available endpoints:");
        for (EndpointDescription endpointDescription : endpoints) {
            logger.info(endpointDescription.getEndpointUrl() + " " + endpointDescription.getSecurityPolicyUri());
        }

        EndpointDescription endpoint = chooseEndpoint(
            endpoints,
            clientExample.getSecurityPolicy(),
            clientExample.getMessageSecurityMode()
        );

        logger.info("Using endpoint: {} [{}, {}]", endpoint.getEndpointUrl(), endpoint.getSecurityPolicyUri(),
            endpoint.getSecurityMode());

        OpcUaClientConfig config = OpcUaClientConfig.builder()
            .setApplicationName(LocalizedText.english(APPLICATION_NAME))
            .setApplicationUri(APPLICATION_URI)
            .setEndpoint(endpoint)
            .setIdentityProvider(clientExample.getIdentityProvider())
            .setRequestTimeout(uint(5000))
            .setCertificate(clientExample.getClientCertificate())
            .setKeyPair(clientExample.getKeyPair())
            .build();

        return OpcUaClient.create(config);
    }

    private EndpointDescription chooseEndpoint(
        List<EndpointDescription> endpoints,
        SecurityPolicy minSecurityPolicy,
        MessageSecurityMode minMessageSecurityMode) {

        EndpointDescription bestFound = null;
        SecurityPolicy bestFoundSecurityPolicy = null;
        for (EndpointDescription endpoint : endpoints) {
            SecurityPolicy endpointSecurityPolicy;
            try {
                endpointSecurityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());
            } catch (UaException e) {
                continue;
            }
            if (minSecurityPolicy.compareTo(endpointSecurityPolicy) <= 0) {
                if (minMessageSecurityMode.compareTo(endpoint.getSecurityMode()) <= 0) {
                    // Found endpoint which fulfills minimum requirements
                    if (bestFound == null) {
                        bestFound = endpoint;
                        bestFoundSecurityPolicy = endpointSecurityPolicy;
                    } else {
                        if (bestFoundSecurityPolicy.compareTo(endpointSecurityPolicy) < 0) {
                            // Found endpoint that has higher security than previously found one
                            bestFound = endpoint;
                            bestFoundSecurityPolicy = endpointSecurityPolicy;
                        }
                    }
                }
            }
        }
        if (bestFound == null) {
            throw new RuntimeException("no desired endpoints returned");
        } else {
            return bestFound;
        }
    }

    public void run() {
        future.whenComplete((client, ex) -> {
            if (client != null) {
                try {
                    client.disconnect().get();
                    Stack.releaseSharedResources();
                    System.exit(0);
                } catch (InterruptedException | ExecutionException e) {
                    logger.error("Error disconnecting:", e.getMessage(), e);
                }
            } else {
                logger.error("Error running example: {}", ex.getMessage(), ex);
                Stack.releaseSharedResources();
            }

            try {
                Thread.sleep(1000);
                System.exit(0);
            } catch (InterruptedException e) {
                logger.error("Interrupted exception", e);
            }
        });

        try {
            OpcUaClient client = createClient();

            try {
                clientExample.run(client, future);
                future.get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                logger.error("Error running client example: {}", e.getMessage(), e);
                future.complete(client);
            }
        } catch (Exception e) {
            future.completeExceptionally(e);
        }

        try {
            Thread.sleep(999999999);
        } catch (InterruptedException e) {
            logger.error("Error running client example: {}", e.getMessage(), e);
        }
    }

}

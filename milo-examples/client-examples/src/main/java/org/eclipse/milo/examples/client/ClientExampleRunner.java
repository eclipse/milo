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

import java.io.File;
import java.security.Security;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.examples.server.ExampleServer;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.CryptoRestrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ClientExampleRunner {

    static {
        CryptoRestrictions.remove();

        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CompletableFuture<OpcUaClient> future = new CompletableFuture<>();

    private ExampleServer exampleServer;

    private final ClientExample clientExample;
    private final boolean serverRequired;

    public ClientExampleRunner(ClientExample clientExample) throws Exception {
        this(clientExample, true);
    }

    public ClientExampleRunner(ClientExample clientExample, boolean serverRequired) throws Exception {
        this.clientExample = clientExample;
        this.serverRequired = serverRequired;

        if (serverRequired) {
            exampleServer = new ExampleServer();
            exampleServer.startup().get();
        }
    }

    private OpcUaClient createClient() throws Exception {
        File securityTempDir = new File(System.getProperty("java.io.tmpdir"), "security");
        if (!securityTempDir.exists() && !securityTempDir.mkdirs()) {
            throw new Exception("unable to create security dir: " + securityTempDir);
        }
        LoggerFactory.getLogger(getClass())
            .info("security temp dir: {}", securityTempDir.getAbsolutePath());

        KeyStoreLoader loader = new KeyStoreLoader().load(securityTempDir);

        SecurityPolicy securityPolicy = clientExample.getSecurityPolicy();

        EndpointDescription[] endpoints;

        try {
            endpoints = UaTcpStackClient
                .getEndpoints(clientExample.getEndpointUrl())
                .get();
        } catch (Throwable ex) {
            // try the explicit discovery endpoint as well
            String discoveryUrl = clientExample.getEndpointUrl() + "/discovery";
            logger.info("Trying explicit discovery URL: {}", discoveryUrl);
            endpoints = UaTcpStackClient
                .getEndpoints(discoveryUrl)
                .get();
        }

        EndpointDescription endpoint = Arrays.stream(endpoints)
            .filter(e -> e.getSecurityPolicyUri().equals(securityPolicy.getSecurityPolicyUri()))
            .findFirst().orElseThrow(() -> new Exception("no desired endpoints returned"));

        logger.info("Using endpoint: {} [{}]", endpoint.getEndpointUrl(), securityPolicy);

        OpcUaClientConfig config = OpcUaClientConfig.builder()
            .setApplicationName(LocalizedText.english("eclipse milo opc-ua client"))
            .setApplicationUri("urn:eclipse:milo:examples:client")
            .setCertificate(loader.getClientCertificate())
            .setKeyPair(loader.getClientKeyPair())
            .setEndpoint(endpoint)
            .setIdentityProvider(clientExample.getIdentityProvider())
            .setRequestTimeout(uint(5000))
            .build();

        return new OpcUaClient(config);
    }

    public void run() {
        try {
            OpcUaClient client = createClient();

            future.whenComplete((c, ex) -> {
                if (ex != null) {
                    logger.error("Error running example: {}", ex.getMessage(), ex);
                }

                try {
                    client.disconnect().get();
                    if (serverRequired && exampleServer != null) {
                        exampleServer.shutdown().get();
                    }
                    Stack.releaseSharedResources();
                } catch (InterruptedException | ExecutionException e) {
                    logger.error("Error disconnecting:", e.getMessage(), e);
                }

                try {
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            try {
                clientExample.run(client, future);
                future.get(15, TimeUnit.SECONDS);
            } catch (Throwable t) {
                logger.error("Error running client example: {}", t.getMessage(), t);
                future.completeExceptionally(t);
            }
        } catch (Throwable t) {
            logger.error("Error getting client: {}", t.getMessage(), t);

            future.completeExceptionally(t);

            try {
                Thread.sleep(1000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(999999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

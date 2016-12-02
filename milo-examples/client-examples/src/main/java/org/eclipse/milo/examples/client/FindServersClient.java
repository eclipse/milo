/*
 * Copyright (c) 2016 Kevin Herron, Stefan Profanter
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

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.milo.examples.client.util.KeyStoreLoader;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfig;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerOnNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class FindServersClient {

    private final AtomicLong requestHandle = new AtomicLong(1L);


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) throws Exception {
        FindServersClient example = new FindServersClient();


        example.OutputFindServersOnNetwork("opc.tcp://localhost:4840/discovery")
                .thenCompose(aVoid -> example.OutputFindServers("opc.tcp://localhost:4840/discovery"))
                .get();

    }

    private UaTcpStackClient getClient(String endpointUrl) {
        KeyStoreLoader loader;
        try {
            loader = new KeyStoreLoader().load();
        } catch (Exception e) {
            logger.error("Could not load keys {}", e);
            return null;
        }

        UaTcpStackClientConfig config = UaTcpStackClientConfig.builder()
                .setApplicationName(LocalizedText.english("Stack Example Client"))
                .setApplicationUri(String.format("urn:example-client:%s", UUID.randomUUID()))
                .setCertificate(loader.getClientCertificate())
                .setKeyPair(loader.getClientKeyPair())
                .setEndpointUrl(endpointUrl)
                .build();

        return new UaTcpStackClient(config);
    }

    private CompletableFuture<Void> OutputFindServersOnNetwork(String discoveryEndpointUrl) {
        CompletableFuture<Void> finished = new CompletableFuture<>();

        FindServersOnNetwork(discoveryEndpointUrl).exceptionally(ex -> {
            logger.error("error invoking FindServersOnNetwork", ex);
            return new ServerOnNetwork[0];
        }).thenAccept(v -> {
            logger.info("FindServersOnNetwork returned " + v.length + " Servers");
            for (ServerOnNetwork sn : v) {
                logger.info("Server: " + sn.getServerName());
                logger.info("\tRecordID: " + sn.getRecordId());
                logger.info("\tDiscovery URL: " + sn.getDiscoveryUrl());
                StringBuilder caps = new StringBuilder();
                caps.append("\tCapabilities: ");
                if (sn.getServerCapabilities() != null) {
                    for (String cap : sn.getServerCapabilities()) {
                        caps.append(cap + ",");
                    }
                }
                logger.info(caps.toString());
            }
            finished.complete(null);
        });

        return finished;
    }

    private CompletableFuture<ServerOnNetwork[]> FindServersOnNetwork(String discoveryEndpointUrl) {

        UaTcpStackClient client = getClient(discoveryEndpointUrl);

        RequestHeader header = new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.now(),
                uint(requestHandle.getAndIncrement()),
                uint(0), null, uint(60), null);

        FindServersOnNetworkRequest request = new FindServersOnNetworkRequest(header, null, null, null );

        return client.<FindServersOnNetworkResponse>sendRequest(request).thenCompose(result -> {
            StatusCode statusCode = result.getResponseHeader().getServiceResult();

            if (statusCode.isGood()) {
                return CompletableFuture.completedFuture(result.getServers());
            } else {
                CompletableFuture<ServerOnNetwork[]> f = new CompletableFuture<>();
                f.completeExceptionally(new UaException(statusCode));
                return f;
            }
        });
    }

    private CompletableFuture<Void> OutputFindServers(String discoveryEndpointUrl) {
        CompletableFuture<Void> finished = new CompletableFuture<>();

        FindServers(discoveryEndpointUrl).exceptionally(ex -> {
            logger.error("error invoking FindServers", ex);
            return new ApplicationDescription[0];
        }).thenAccept(v -> {
            logger.info("FindServers returned " + v.length + " Servers");

            for (ApplicationDescription ad : v) {
                logger.info("Server: " + ad.getApplicationUri());
                logger.info("\tName: " + ad.getApplicationName());
                logger.info("\tProduct URI: " + ad.getProductUri());
                logger.info("\tType: " + ad.getApplicationType().toString());
                if (ad.getDiscoveryUrls() != null) {
                    logger.info("\tDiscovery URLs:");
                    for (String url : ad.getDiscoveryUrls()) {
                        logger.info("\t\t" + url);
                    }
                } else {
                    logger.info("\tDiscovery URLs: null");
                }
                try {
                    OutputGetEndpoints(ad).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                logger.info("---");
            }
            finished.complete(null);
        });

        return finished;
    }

    private CompletableFuture<ApplicationDescription[]> FindServers(String discoveryEndpointUrl) {

        UaTcpStackClient client = getClient(discoveryEndpointUrl);

        RequestHeader header = new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.now(),
                uint(requestHandle.getAndIncrement()),
                uint(0), null, uint(60), null);

        FindServersRequest request = new FindServersRequest(header, null, null, null);

        return client.<FindServersResponse>sendRequest(request).thenCompose(result -> {
            StatusCode statusCode = result.getResponseHeader().getServiceResult();

            if (statusCode.isGood()) {
                return CompletableFuture.completedFuture(result.getServers());
            } else {
                CompletableFuture<ApplicationDescription[]> f = new CompletableFuture<>();
                f.completeExceptionally(new UaException(statusCode));
                return f;
            }
        });
    }

    private CompletableFuture<Void> OutputGetEndpoints(ApplicationDescription applicationDescription) {
        CompletableFuture<Void> finished = new CompletableFuture<>();

        if (applicationDescription.getDiscoveryUrls() == null || applicationDescription.getDiscoveryUrls().length == 0) {
            logger.warn("Can not get endpoints. Empty discovery urls for Server: " + applicationDescription.getApplicationUri());
            CompletableFuture<EndpointDescription[]> future = new CompletableFuture<>();
            future.complete(new EndpointDescription[0]);
            return finished;
        }

        EndpointDescription[] endpoints = new EndpointDescription[0];
        try {
            endpoints = UaTcpStackClient.getEndpoints(applicationDescription.getDiscoveryUrls()[0]).get();
        } catch (InterruptedException | ExecutionException e) {
            finished.completeExceptionally(e);
        }

        logger.info("GetEndpoints for " + applicationDescription.getApplicationUri() + " returned " + endpoints.length + " Endpoints");
        for (EndpointDescription ed : endpoints) {
            logger.info("\tEndpoint URL: " + ed.getEndpointUrl());
            logger.info("\tTransport profile URI: " + ed.getTransportProfileUri());
            logger.info("---");
        }
        finished.complete(null);

        return finished;
    }

}

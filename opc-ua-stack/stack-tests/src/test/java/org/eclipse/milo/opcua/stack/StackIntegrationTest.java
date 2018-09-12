/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.stack;

import java.security.Security;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfigBuilder;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfigBuilder;
import org.eclipse.milo.opcua.stack.server.tcp.SocketServers;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class StackIntegrationTest extends SecurityFixture {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    protected UaStackClient client;
    protected UaTcpStackServer server;

    @BeforeSuite
    public void setUpClientServer() throws Exception {
        UaTcpStackServerConfig serverConfig = configureServer(
            UaTcpStackServerConfig.builder()
                .setServerName("test")
                .setCertificateManager(serverCertificateManager)
                .setCertificateValidator(serverCertificateValidator)
        ).build();

        server = new UaTcpStackServer(serverConfig);

        server
            .addEndpoint("opc.tcp://localhost:12685/test", null)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic256, MessageSecurityMode.Sign)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt);

        server.startup().get();

        EndpointDescription endpoint = selectEndpoint(
            DiscoveryClient.getEndpoints(
                "opc.tcp://localhost:12685/test").get()
        );

        UaStackClientConfig clientConfig = configureClient(
            UaStackClientConfig.builder()
                .setEndpoint(endpoint)
                .setKeyPair(clientKeyPair)
                .setCertificate(clientCertificate)
        ).build();

        client = UaStackClient.create(clientConfig);
        client.connect().get();
    }

    @AfterSuite
    public void tearDownClientServer() throws Exception {
        client.disconnect().get();
        server.shutdown().get();
        SocketServers.shutdownAll().get();
    }

    protected EndpointDescription selectEndpoint(List<EndpointDescription> endpoints) {
        return endpoints.get(0);
    }

    protected UaStackClientConfigBuilder configureClient(UaStackClientConfigBuilder builder) {
        return builder;
    }

    protected UaTcpStackServerConfigBuilder configureServer(UaTcpStackServerConfigBuilder builder) {
        return builder;
    }

}

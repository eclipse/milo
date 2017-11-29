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

import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfig;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfigBuilder;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.CryptoRestrictions;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfigBuilder;
import org.eclipse.milo.opcua.stack.server.tcp.SocketServers;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class StackIntegrationTest extends SecurityFixture {

    static {
        CryptoRestrictions.remove();
    }

    protected UaTcpStackClient client;
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
            UaTcpStackClient.getEndpoints(
                "opc.tcp://localhost:12685/test").get()
        );

        UaTcpStackClientConfig clientConfig = configureClient(
            UaTcpStackClientConfig.builder()
                .setEndpoint(endpoint)
                .setKeyPair(clientKeyPair)
                .setCertificate(clientCertificate)
        ).build();

        client = new UaTcpStackClient(clientConfig);
        client.connect().get();
    }

    @AfterSuite
    public void tearDownClientServer() throws Exception {
        client.disconnect().get();
        server.shutdown().get();
        SocketServers.shutdownAll().get();
    }

    protected EndpointDescription selectEndpoint(EndpointDescription[] endpoints) {
        return endpoints[0];
    }

    protected UaTcpStackClientConfigBuilder configureClient(UaTcpStackClientConfigBuilder builder) {
        return builder;
    }

    protected UaTcpStackServerConfigBuilder configureServer(UaTcpStackServerConfigBuilder builder) {
        return builder;
    }

}

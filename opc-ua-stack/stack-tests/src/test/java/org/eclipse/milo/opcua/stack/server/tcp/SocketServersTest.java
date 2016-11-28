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

import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.stack.SecurityFixture;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SocketServersTest extends SecurityFixture {

    @BeforeSuite
    public void setUp() throws Exception {
        super.setUp();
    }

    // this test can'nt be run while other tests are running
    @Test(enabled = false)
    public void testShutdownRemovesInstance() throws ExecutionException, InterruptedException {
        UaTcpStackServerConfig config = UaTcpStackServerConfig.builder()
            .setServerName("test")
            .setCertificateManager(serverCertificateManager)
            .setCertificateValidator(serverCertificateValidator)
            .build();

        UaTcpStackServer server = new UaTcpStackServer(config);

        server.addEndpoint("opc.tcp://localhost:12685/test", null);

        server.startup().get();
        assertFalse(SocketServers.SERVERS.isEmpty());

        server.shutdown().get();
        assertTrue(SocketServers.SERVERS.isEmpty());
    }

}

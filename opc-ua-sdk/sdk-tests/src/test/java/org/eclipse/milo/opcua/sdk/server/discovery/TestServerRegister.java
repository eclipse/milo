/*
 * Copyright (c) 2017 Stefan Profanter
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


package org.eclipse.milo.opcua.sdk.server.discovery;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import com.sun.corba.se.spi.activation.Server;
import io.netty.util.concurrent.Promise;
import org.eclipse.milo.opcua.sdk.common.KeyStoreLoader;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfig;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerOnNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class TestServerRegister {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ServerRunner serverRunner;
    private final AtomicLong requestHandle = new AtomicLong(1L);

    // set register timeout to 1 second so we are able to test it.
    private final static int REGISTER_TIMEOUT  = 1;
    // cleanup is only triggered every 10 seconds, thus wait a bit longer to check
    private final static int CHECK_WAIT = REGISTER_TIMEOUT + 11;

    public TestServerRegister() {
        serverRunner = new ServerRunner(REGISTER_TIMEOUT);
    }

    private void serverRegister(boolean expectedResult, boolean waitSuccess) throws ExecutionException, InterruptedException {
        CompletableFuture<StatusCode> registered = new CompletableFuture<>();
        boolean result = serverRunner.serverRegister.registerWithDiscoveryServer("opc.tcp://localhost:4840/discovery", null, registered);
        assertTrue(result == expectedResult);
        if (!expectedResult || !waitSuccess)
            return;
        StatusCode status = registered.get();
        assertEquals(StatusCode.GOOD, status);

    }

    private void serverUnregister(boolean waitSuccess) throws ExecutionException, InterruptedException {
        CompletableFuture<StatusCode> future = serverRunner.serverRegister.unregisterFromDiscoveryServer();
        if (!waitSuccess)
            return;
        StatusCode result = future.get();
        assertEquals(StatusCode.GOOD, result);
    }

    private void serverRegisterSemaphore() throws ExecutionException, InterruptedException, IOException {
        CompletableFuture<StatusCode> registered = new CompletableFuture<>();
        File f = new File(System.getProperty("java.io.tmpdir"), "milo-unit-test-semaphore");
        if (!f.exists()) {
            logger.info("Creating semaphore at: " + f.getAbsolutePath());
            assertTrue(f.createNewFile());
        }
        boolean result = serverRunner.serverRegister.registerWithDiscoveryServer("opc.tcp://localhost:4840/discovery", "/tmp/milo-unit-test-semaphore", registered);
        assertTrue(result);
        StatusCode status = registered.get();
        assertEquals(StatusCode.GOOD, status);
    }

    private void serverUnregisterSemaphore() {
        // delete the semaphore, this should remove the registration automatically on next check
        File f = new File(System.getProperty("java.io.tmpdir"), "milo-unit-test-semaphore");
        logger.info("Deleting semaphore at: " + f.getAbsolutePath());
        assertTrue(f.delete());
    }

    private void clientFindRegistered() throws ExecutionException, InterruptedException {
        String expectedUris[] = {"urn:eclipse:milo:examples:server_lds", "urn:eclipse:milo:examples:server_register"};
        findAndCheck(expectedUris, null, null, null, null);
    }

    private void clientFindFilter() throws ExecutionException, InterruptedException {
        String expectedUris[] = {"urn:eclipse:milo:examples:server_register"};
        findAndCheck(expectedUris, null, null, "urn:eclipse:milo:examples:server_register", null);
    }

    private void clientFilterLocale() throws ExecutionException, InterruptedException {
        String expectedUris[] = {"urn:eclipse:milo:examples:server_lds", "urn:eclipse:milo:examples:server_register"};
        String expectedNames[] = {"Eclipse Milo OPC-UA Example Discovery Server",
            "Anmeldungsserver"};
        String expectedLocales[] = {"en", "de"};

        // even if we request en_US, the server will return de_DE because it only has that name.
        findAndCheck(expectedUris, expectedLocales, expectedNames, null, "en");
    }

    private void clientFindDiscovery() throws ExecutionException, InterruptedException {
        String expectedUris[] = {"urn:eclipse:milo:examples:server_lds"};
        findAndCheck(expectedUris, null, null, null, null);
    }

    private void clientFindOnNetworkRegistered() throws ExecutionException, InterruptedException {
        String expectedNames[] = {"Eclipse Milo OPC-UA Example Discovery Server", "Anmeldungsserver"};


        findOnNetworkAndCheck(expectedNames, null);

        // only LDS expected
        String caps1[] = {"LDS"};
        String subset1[] = {expectedNames[0]};
        findOnNetworkAndCheck(subset1, caps1);

        // only registered server expected
        String caps2[] = {"NA"};
        String subset2[] = {expectedNames[1]};
        findOnNetworkAndCheck(subset2, caps2);

        // no server expected
        String caps3[] = {"NA", "LDS"};
        findOnNetworkAndCheck(null, caps3);
    }

    private void clientGetEndpoints() {
        String expectedEndpoints[] = {"opc.tcp://localhost:4840/discovery"};
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

    private ApplicationDescription[] findServers(UaTcpStackClient client, String filterUri, String filterLocale)
        throws ExecutionException, InterruptedException {
        RequestHeader header = new RequestHeader(
            NodeId.NULL_VALUE,
            DateTime.now(),
            uint(requestHandle.getAndIncrement()),
            uint(0), null, uint(60), null);

        String localeIds[] = null;
        if (filterLocale != null) {
            localeIds = new String[1];
            localeIds[0] = filterLocale;
        }
        String serverUris[] = null;
        if (filterUri != null) {
            serverUris = new String[1];
            serverUris[0] = filterUri;
        }


        FindServersRequest request = new FindServersRequest(header, null, localeIds, serverUris);

        CompletableFuture<ApplicationDescription[]> appDescriptionFuture = client.<FindServersResponse>sendRequest(request).thenCompose(result -> {
            StatusCode statusCode = result.getResponseHeader().getServiceResult();

            if (statusCode.isGood()) {
                return CompletableFuture.completedFuture(result.getServers());
            } else {
                CompletableFuture<ApplicationDescription[]> f = new CompletableFuture<>();
                f.completeExceptionally(new UaException(statusCode));
                return f;
            }
        });

        return appDescriptionFuture.get();
    }

    private ServerOnNetwork[] findServersOnNetwork(UaTcpStackClient client, String[] filterCapabilities)
        throws ExecutionException, InterruptedException {
        RequestHeader header = new RequestHeader(
            NodeId.NULL_VALUE,
            DateTime.now(),
            uint(requestHandle.getAndIncrement()),
            uint(0), null, uint(60), null);


        FindServersOnNetworkRequest request = new FindServersOnNetworkRequest(header, UInteger.valueOf(0), UInteger.valueOf(0), filterCapabilities);

        CompletableFuture<ServerOnNetwork[]> serversOnNetwork = client.<FindServersOnNetworkResponse>sendRequest(request).thenCompose(result -> {
            StatusCode statusCode = result.getResponseHeader().getServiceResult();

            if (statusCode.isGood()) {
                return CompletableFuture.completedFuture(result.getServers());
            } else {
                CompletableFuture<ServerOnNetwork[]> f = new CompletableFuture<>();
                f.completeExceptionally(new UaException(statusCode));
                return f;
            }
        });

        return serversOnNetwork.get();
    }

    private void findAndCheck(String expectedUris[], String expectedLocales[], String expectedNames[], String filterUri, String filterLocale)
        throws ExecutionException, InterruptedException {
        UaTcpStackClient client = getClient("opc.tcp://localhost:4840/discovery");

        assertNotNull(client);

        ApplicationDescription[] descriptions = findServers(client, filterUri, filterLocale);

        assertEquals(descriptions.length, expectedUris.length);

        for (int i=0; i<expectedUris.length; i++) {
            assertEquals(descriptions[i].getApplicationUri(), expectedUris[i]);

            if (expectedNames != null) {
                assertEquals(descriptions[i].getApplicationName().getText(), expectedNames[i]);
            }

            if (expectedLocales != null) {
                assertEquals(descriptions[i].getApplicationName().getLocale(), expectedLocales[i]);
            }
        }

        client.disconnect().get();
    }

    private void findOnNetworkAndCheck(String expectedServerNames[], String filterCapabilities[])
        throws ExecutionException, InterruptedException {
        UaTcpStackClient client = getClient("opc.tcp://localhost:4840/discovery");

        assertNotNull(client);

        ServerOnNetwork[] servers = findServersOnNetwork(client, filterCapabilities);

        for (int i=0; i<servers.length; i++) {
            boolean found = false;
            for (int j=0; j<expectedServerNames.length && !found; j++) {
                found = expectedServerNames[j].equals(servers[i].getServerName());
            }
            assertTrue(found);
        }

        client.disconnect().get();
    }

    @Test
    public void testRegisterUnregister() throws Exception {
        logger.info("testRegisterUnregister()");

        serverRunner.startLds();
        serverRunner.startRegisterServer();

        serverRegister(true, true);
        // register two times to check if it is handled correctly
        serverRegister(false, true);
        serverUnregister(true);

        serverRunner.stopRegisterServer();
        serverRunner.stopLds();
    }

    @Test
    public void testRegisterRetry() throws Exception {
        logger.info("testRegisterRetry()");

        serverRunner.startRegisterServer();
        // test when LDS not yet up
        serverRegister(true, false);

        // wait until server started and first try was unsuccessful
        Thread.sleep(1500);

        serverRunner.startLds();

        // first retry is after 2 seconds, then 4, so it should be enough to wait 3 seconds
        Thread.sleep(3000);

        // check if server is there
        clientFindRegistered();
        serverUnregister(true);
        serverRunner.stopRegisterServer();

        clientFindDiscovery();

        serverRunner.stopLds();

    }

    @Test
    public void testRegisterAndFindServers() throws Exception {
        logger.info("testRegisterAndFindServers()");


        serverRunner.startLds();
        serverRunner.startRegisterServer();

        // only discovery server
        clientFindDiscovery();

        serverRegister(true, true);

        // check if server is there
        clientFindRegistered();

        // wait until mdns discovery
        Thread.sleep(3000);

        clientFindOnNetworkRegistered();

        clientFindFilter();
        clientFilterLocale();

        serverUnregister(true);

        serverRunner.stopRegisterServer();
        serverRunner.stopLds();

    }


    @Test
    public void testRegisterTimeout() throws Exception {
        serverRunner.startLds();
        serverRunner.startRegisterServer();

        // only discovery server
        clientFindDiscovery();

        serverRegister(true, true);

        // check if server is there
        clientFindRegistered();

        // wait until registration timed out
        Thread.sleep((CHECK_WAIT + 2)*1000);

        clientFindDiscovery();

        serverRunner.stopRegisterServer();
        serverRunner.stopLds();
    }

    @Test
    public void testRegisterSemaphore() throws Exception {
        serverRunner.startLds();
        serverRunner.startRegisterServer();

        serverRegisterSemaphore();

        // check if server is there
        clientFindRegistered();

        serverUnregisterSemaphore();

        // wait until semaphore deletion detected
        Thread.sleep((CHECK_WAIT + 2)*1000);

        clientFindDiscovery();

        serverRunner.stopRegisterServer();
        serverRunner.stopLds();
    }
}

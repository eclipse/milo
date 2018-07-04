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

package org.eclipse.milo.opcua.stack;

import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.beust.jcommander.internal.Lists;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfig;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.InsecureCertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.util.CryptoRestrictions;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.eclipse.milo.opcua.stack.server.tcp.SocketServers;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertThrows;

public class ClientServerTest extends SecurityFixture {

    private static final UInteger DEFAULT_TIMEOUT_HINT = uint(60000);

    @DataProvider
    public Object[][] getVariants() {
        return new Object[][]{
            {new Variant(true)},
            {new Variant((byte) 1)},
            {new Variant(ubyte(1))},
            {new Variant((short) 1)},
            {new Variant(ushort(1))},
            {new Variant(1)},
            {new Variant(uint(1))},
            {new Variant(1L)},
            {new Variant(ulong(1L))},
            {new Variant(3.14f)},
            {new Variant(6.12d)},
            {new Variant("hello, world")},
            {new Variant(DateTime.now())},
            {new Variant(UUID.randomUUID())},
            {new Variant(ByteString.of(new byte[]{1, 2, 3, 4}))},
            {new Variant(new XmlElement("<tag>hello</tag>"))},
            {new Variant(new NodeId(0, 42))},
            {new Variant(new ExpandedNodeId(1, 42, "uri", 1))},
            {new Variant(StatusCode.GOOD)},
            {new Variant(new QualifiedName(0, "QualifiedName"))},
            {new Variant(LocalizedText.english("LocalizedText"))},
            {new Variant(ExtensionObject.encode(new ReadValueId(NodeId.NULL_VALUE, uint(1), null, new QualifiedName(0, "DataEncoding"))))},
        };
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    private EndpointDescription[] endpoints;

    private UaTcpStackServer server;

    @BeforeSuite
    public void setUpClientServer() throws Exception {
        super.setUp();

        CryptoRestrictions.remove();

        UaTcpStackServerConfig config = UaTcpStackServerConfig.builder()
            .setServerName("test")
            .setCertificateManager(serverCertificateManager)
            .setCertificateValidator(serverCertificateValidator)
            .build();

        server = new UaTcpStackServer(config);

        server.addEndpoint("opc.tcp://localhost:12685/test", null)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic256, MessageSecurityMode.Sign)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt)
            .addEndpoint("opc.tcp://localhost:12685/test", null, serverCertificate, SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt);

        setReadRequestHandler(new Variant(42));

        server.startup().get();

        endpoints = UaTcpStackClient.getEndpoints("opc.tcp://localhost:12685/test").get();
    }

    private void setReadRequestHandler(Variant variant) {
        server.addRequestHandler(ReadRequest.class, service -> {
            ReadRequest request = service.getRequest();

            ResponseHeader header = new ResponseHeader(
                DateTime.now(),
                request.getRequestHeader().getRequestHandle(),
                StatusCode.GOOD,
                null,
                null,
                null
            );

            List<ReadValueId> nodesToRead = l(request.getNodesToRead());
            List<DataValue> results = Collections.nCopies(nodesToRead.size(), new DataValue(variant));

            ReadResponse response = new ReadResponse(header, a(results, DataValue.class), null);

            service.setResponse(response);
        });
    }

    @AfterSuite
    public void tearDownClientServer() throws Exception {
        server.shutdown().get();
        SocketServers.shutdownAll().get();
    }

    @Test(dataProvider = "getVariants")
    public void testClientServerRoundTrip_TestStack_NoSecurity(Variant input) throws Exception {
        EndpointDescription endpoint = endpoints[0];

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        connectAndTest(input, client);
    }

    @Test(dataProvider = "getVariants")
    public void testClientServerRoundTrip_TestStack_Basic128Rsa15_Sign(Variant input) throws Exception {
        EndpointDescription endpoint = endpoints[1];

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        connectAndTest(input, client);
    }

    @Test(dataProvider = "getVariants")
    public void testClientServerRoundTrip_TestStack_Basic256_Sign(Variant input) throws Exception {
        EndpointDescription endpoint = endpoints[2];

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        connectAndTest(input, client);
    }

    @Test(dataProvider = "getVariants")
    public void testClientServerRoundTrip_TestStack_Basic256Sha256_Sign(Variant input) throws Exception {
        EndpointDescription endpoint = endpoints[3];

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        connectAndTest(input, client);
    }

    @Test(dataProvider = "getVariants")
    public void testClientServerRoundTrip_TestStack_Basic128Rsa15_SignAndEncrypt(Variant input) throws Exception {
        EndpointDescription endpoint = endpoints[4];

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        connectAndTest(input, client);
    }

    @Test(dataProvider = "getVariants")
    public void testClientServerRoundTrip_TestStack_Basic256_SignAndEncrypt(Variant input) throws Exception {
        EndpointDescription endpoint = endpoints[5];

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        connectAndTest(input, client);
    }

    @Test(dataProvider = "getVariants")
    public void testClientServerRoundTrip_TestStack_Basic256Sha256_SignAndEncrypt(Variant input) throws Exception {
        EndpointDescription endpoint = endpoints[6];

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        connectAndTest(input, client);
    }

    @Test
    public void testClientStateMachine() throws Exception {
        EndpointDescription endpoint = endpoints[0];

        Variant input = new Variant(42);
        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        // Test some where we don't wait for disconnect to finish...
        for (int i = 0; i < 1000; i++) {
            client.connect().get();

            RequestHeader header = new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.now(),
                uint(i),
                uint(0),
                null,
                DEFAULT_TIMEOUT_HINT,
                null
            );

            ReadRequest request = new ReadRequest(
                header,
                0.0,
                TimestampsToReturn.Neither,
                new ReadValueId[]{
                    new ReadValueId(
                        NodeId.NULL_VALUE,
                        AttributeId.Value.uid(),
                        null,
                        null)
                }
            );

            logger.debug("sending request: {}", request);
            UaResponseMessage response = client.sendRequest(request).get();
            logger.debug("got response: {}", response);

            client.disconnect();
        }

        // and test some where we DO wait...
        for (int i = 0; i < 1000; i++) {
            client.connect().get();

            RequestHeader header = new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.now(),
                uint(i),
                uint(0),
                null,
                DEFAULT_TIMEOUT_HINT,
                null
            );

            ReadRequest request = new ReadRequest(
                header,
                0.0,
                TimestampsToReturn.Neither,
                new ReadValueId[]{
                    new ReadValueId(
                        NodeId.NULL_VALUE,
                        AttributeId.Value.uid(),
                        null,
                        null)
                }
            );

            logger.debug("sending request: {}", request);
            UaResponseMessage response = client.sendRequest(request).get();
            logger.debug("got response: {}", response);

            client.disconnect().get();
        }
    }

    @Test
    public void testClientDisconnect() throws Exception {
        EndpointDescription endpoint = endpoints[0];
        Variant input = new Variant(42);

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        client.connect().get();

        RequestHeader header = new RequestHeader(
            NodeId.NULL_VALUE,
            DateTime.now(),
            uint(0),
            uint(0),
            null,
            DEFAULT_TIMEOUT_HINT,
            null
        );

        ReadRequest request = new ReadRequest(
            header,
            0.0,
            TimestampsToReturn.Neither,
            new ReadValueId[]{
                new ReadValueId(
                    NodeId.NULL_VALUE,
                    AttributeId.Value.uid(),
                    null,
                    null)
            }
        );

        logger.info("sending request: {}", request);
        UaResponseMessage response0 = client.sendRequest(request).get();
        logger.info("got response: {}", response0);

        // client doesn't wait for server to close the channel, it
        // closes after flushing the message, so we need to delay
        // here for the purpose of testing. we close the secure
        // channel and sleep to give the server time to act, then
        // assert that the server no longer knows about it.
        long secureChannelId = client.getChannelFuture().get().getChannelId();
        client.disconnect().get();
        Thread.sleep(100);
        logger.info("asserting channel closed...");
        assertNull(server.getSecureChannel(secureChannelId));
    }

    @Test
    public void testClientReconnect() throws Exception {
        EndpointDescription endpoint = endpoints[0];
        Variant input = new Variant(42);

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        client.connect().get();

        RequestHeader header = new RequestHeader(
            NodeId.NULL_VALUE,
            DateTime.now(),
            uint(0),
            uint(0),
            null,
            DEFAULT_TIMEOUT_HINT,
            null
        );

        ReadRequest request = new ReadRequest(
            header,
            0.0,
            TimestampsToReturn.Neither,
            new ReadValueId[]{
                new ReadValueId(
                    NodeId.NULL_VALUE,
                    AttributeId.Value.uid(),
                    null,
                    null)
            }
        );

        logger.info("sending request: {}", request);
        UaResponseMessage response0 = client.sendRequest(request).get();
        logger.info("got response: {}", response0);

        logger.info("initiating a reconnect by closing channel in server...");
        long secureChannelId = client.getChannelFuture().get().getChannelId();
        server.getSecureChannel(secureChannelId).attr(UaTcpStackServer.BoundChannelKey).get().close().await();

        logger.info("sending request: {}", request);
        UaResponseMessage response1 = client.sendRequest(request).get();
        logger.info("got response: {}", response1);

        client.disconnect().get();
    }

    @Test
    public void testClientReconnect_InvalidSecureChannel() throws Exception {
        EndpointDescription endpoint = endpoints[0];
        Variant input = new Variant(42);

        logger.info("SecurityPolicy={}, MessageSecurityMode={}, input={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode(), input);

        UaTcpStackClient client = createClient(endpoint);

        client.connect().get();

        RequestHeader header = new RequestHeader(
            NodeId.NULL_VALUE,
            DateTime.now(),
            uint(0),
            uint(0),
            null,
            DEFAULT_TIMEOUT_HINT,
            null
        );

        ReadRequest request = new ReadRequest(
            header,
            0.0,
            TimestampsToReturn.Neither,
            new ReadValueId[]{
                new ReadValueId(
                    NodeId.NULL_VALUE,
                    AttributeId.Value.uid(),
                    null,
                    null)
            }
        );

        logger.info("sending request: {}", request);
        UaResponseMessage response0 = client.sendRequest(request).get();
        logger.info("got response: {}", response0);

        // Get our original valid secure channel, then sabotage it, then cause a disconnect.
        // The end effect is that we reconnect with an invalid secure channel id.
        ClientSecureChannel secureChannel = client.getChannelFuture().get();
        long secureChannelId = secureChannel.getChannelId();
        secureChannel.setChannelId(Long.MAX_VALUE);
        server.getSecureChannel(secureChannelId).attr(UaTcpStackServer.BoundChannelKey).get().close().await();
        Thread.sleep(500);

        logger.info("sending request: {}", request);
        UaResponseMessage response1 = client.sendRequest(request).get();
        logger.info("got response: {}", response1);
    }

    @Test
    public void testClientTimeout() throws Exception {
        EndpointDescription endpoint = endpoints[0];

        logger.info("SecurityPolicy={}, MessageSecurityMode={}",
            SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri()), endpoint.getSecurityMode());

        UaTcpStackClientConfig config = UaTcpStackClientConfig.builder()
            .setEndpoint(endpoint)
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .build();

        UaTcpStackClient client = new UaTcpStackClient(config);

        server.addRequestHandler(ReadRequest.class, service -> {
            // intentionally do nothing so the request can timeout
            logger.info("received {}; ignoring...", service.getRequest());
        });

        RequestHeader header = new RequestHeader(
            NodeId.NULL_VALUE,
            DateTime.now(),
            uint(0),
            uint(0),
            null,
            DEFAULT_TIMEOUT_HINT,
            null
        );

        ReadRequest request = new ReadRequest(
            header,
            0.0,
            TimestampsToReturn.Neither,
            new ReadValueId[]{
                new ReadValueId(
                    NodeId.NULL_VALUE,
                    AttributeId.Value.uid(),
                    null,
                    null)
            }
        );

        assertThrows(ExecutionException.class, () -> {
            UaResponseMessage response = client.sendRequest(request).get();

            logger.info("response={}", response);
        });
    }

    private UaTcpStackClient createClient(EndpointDescription endpoint) throws UaException {
        UaTcpStackClientConfig config = UaTcpStackClientConfig.builder()
            .setEndpoint(endpoint)
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setCertificateValidator(new InsecureCertificateValidator() {
                @Override
                public void validate(X509Certificate certificate) throws UaException {}

                @Override
                public void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException {}
            })
            .build();

        return new UaTcpStackClient(config);
    }

    private void connectAndTest(Variant input, UaTcpStackClient client) throws InterruptedException, java.util.concurrent.ExecutionException {
        setReadRequestHandler(input);

        client.connect().get();

        List<ReadRequest> requests = Lists.newArrayList();
        List<CompletableFuture<? extends UaResponseMessage>> futures = Lists.newArrayList();

        for (int i = 0; i < 1000; i++) {
            RequestHeader header = new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.now(),
                uint(i),
                uint(0),
                null,
                DEFAULT_TIMEOUT_HINT,
                null
            );

            ReadRequest request = new ReadRequest(
                header,
                0.0,
                TimestampsToReturn.Neither,
                new ReadValueId[]{
                    new ReadValueId(
                        NodeId.NULL_VALUE,
                        AttributeId.Value.uid(),
                        null,
                        null)
                }
            );

            requests.add(request);

            CompletableFuture<ReadResponse> future = new CompletableFuture<>();

            future.thenAccept((response) ->
                assertEquals(l(response.getResults()).get(0).getValue(), input));

            futures.add(future);
        }

        client.sendRequests(requests, futures);

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();

        client.disconnect().get();
    }

}

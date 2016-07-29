/*
 * Copyright (c) 2016 Red Hat
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */
package org.eclipse.milo.examples.helloworld;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateValidator;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.Random;

import static com.google.common.io.Files.createTempDir;
import static java.util.Arrays.stream;
import static java.util.Collections.singletonList;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS;

public class HelloWorld {

    public static void main(String[] args) throws Exception {
        // Start server

        int port = 12686;
        String serverName = "test-server";
        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
                .setBindPort(port)
                .setCertificateManager(new DefaultCertificateManager())
                .setCertificateValidator(new DefaultCertificateValidator(createTempDir()))
                .setServerName(serverName)
                .setUserTokenPolicies(singletonList(USER_TOKEN_POLICY_ANONYMOUS))
                .build();

        OpcUaServer server = new OpcUaServer(serverConfig);

        server.getNamespaceManager().registerAndAdd(
                "urn:eclipse:milo:opcua:test-namespace",
                idx -> new HelloNamespace());

        server.startup();


        // Start client

        EndpointDescription[] endpoints = UaTcpStackClient.getEndpoints("opc.tcp://localhost:" + port + "/" + serverName).get();
        EndpointDescription endpoint = stream(endpoints).findFirst().orElseThrow(() -> new Exception("no desired endpoints returned"));
        OpcUaClientConfig clientConfig = OpcUaClientConfig.builder().setEndpoint(endpoint).build();
        OpcUaClient client = new OpcUaClient(clientConfig);


        // Read value

        NodeId nodeId = new NodeId(2, "/foo/bar/baz");
        UaVariableNode node = client.getAddressSpace().getVariableNode(nodeId);
        Object value = node.readValueAttribute().get();


        // Write value

        DataValue newValue = new DataValue(new Variant(new Random().nextInt()));
        node.writeValue(newValue).get();
        Object valueAfter = node.readValueAttribute().get();


        // Print results

        System.out.println("Node value before update:\t" + value);
        System.out.println("Node value after update:\t" + valueAfter);
    }

}

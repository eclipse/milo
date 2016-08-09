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

import java.util.Random;

import com.google.common.collect.Lists;
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

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.io.Files.createTempDir;
import static java.util.Arrays.stream;
import static java.util.Collections.singletonList;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS;

public class HelloWorld {

    public static void main(String[] args) throws Exception {
        // Start server
        final String serverName = "hello-world";

        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
            .setApplicationUri("urn:eclipse:milo:hello-world:server")
            .setBindAddresses(newArrayList("localhost"))
            .setBindPort(12686)
            .setCertificateManager(new DefaultCertificateManager())
            .setCertificateValidator(new DefaultCertificateValidator(createTempDir()))
            .setServerName(serverName)
            .setUserTokenPolicies(singletonList(USER_TOKEN_POLICY_ANONYMOUS))
            .build();

        OpcUaServer server = new OpcUaServer(serverConfig);

        server.getNamespaceManager().registerAndAdd(
            HelloNamespace.NAMESPACE_URI,
            idx -> new HelloNamespace(server, idx));

        server.startup();


        // Start client
        EndpointDescription[] endpoints = UaTcpStackClient
            .getEndpoints("opc.tcp://localhost:12686/" + serverName).get();

        EndpointDescription endpoint = stream(endpoints)
            .findFirst()
            .orElseThrow(() -> new Exception("no desired endpoints returned"));

        OpcUaClientConfig clientConfig = OpcUaClientConfig.builder()
            .setEndpoint(endpoint)
            .build();

        OpcUaClient client = new OpcUaClient(clientConfig);


        // Read and write a node
        NodeId nodeId = new NodeId(2, "HelloWorld/Int32");
        UaVariableNode node = client.getAddressSpace().getVariableNode(nodeId);

        // Read value
        Object value = node.readValueAttribute().get();
        System.out.println("Value before write: " + value);

        // Write new value
        DataValue newValue = new DataValue(new Variant(new Random().nextInt()));
        node.writeValue(newValue).get();
        System.out.println("Wrote value: " + newValue.getValue());

        // Read value again
        Object valueAfter = node.readValueAttribute().get();
        System.out.println("Value after write: " + valueAfter);
    }

}

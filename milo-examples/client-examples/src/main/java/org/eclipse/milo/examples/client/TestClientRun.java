/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.client;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClientRun {

    public static void main(String[] args) throws Exception {
        TestClientRun example = new TestClientRun();
        String uri = "opc.tcp://milo.digitalpetri.com:62541/milo";
        List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints(uri).get();
        OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();
        cfg.setEndpoint(endpoints.get(0)); // please do better, and not only pick the first entry
        OpcUaClient client = OpcUaClient.create(cfg.build());
        example.run(client, example.future);
    }

    private final CompletableFuture<OpcUaClient> future = new CompletableFuture<>();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        // start browsing at root folder
        browseNode("", client, Identifiers.RootFolder);
        future.complete(client);
    }

    private void browseNode(String indent, OpcUaClient client, NodeId browseRoot) {
        try {
            List<Node> nodes = client.getAddressSpace().browse(browseRoot).get();

            for (Node node : nodes) {
                logger.info("{} Node={}", indent, node.getBrowseName().get().getName());
                NodeId id =  node.getNodeId().get();
                QualifiedName bname = node.getBrowseName().get();
                LocalizedText dname = node.getDisplayName().get();
                browseNode(indent + "  ", client, node.getNodeId().get());
                System.out.println("---------------------------");
                System.out.println(id);
                System.out.println(bname);
                System.out.println(dname);
                System.out.println();

                // recursively browse to children
                browseNode(indent + "  ", client, node.getNodeId().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Browsing nodeId={} failed: {}", browseRoot, e.getMessage(), e);
        }
    }

}

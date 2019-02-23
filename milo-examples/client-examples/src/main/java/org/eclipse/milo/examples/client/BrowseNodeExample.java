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
import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowseNodeExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        BrowseNodeExample example = new BrowseNodeExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
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

                // recursively browse to children
                browseNode(indent + "  ", client, node.getNodeId().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Browsing nodeId={} failed: {}", browseRoot, e.getMessage(), e);
        }
    }

}

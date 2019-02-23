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

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadNodeExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        ReadNodeExample example = new ReadNodeExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        // Get a typed reference to the Server object: ServerNode
        ServerNode serverNode = client.getAddressSpace().getObjectNode(
            Identifiers.Server,
            ServerNode.class
        ).get();

        // Read properties of the Server object...
        String[] serverArray = serverNode.getServerArray().get();
        String[] namespaceArray = serverNode.getNamespaceArray().get();

        logger.info("ServerArray={}", Arrays.toString(serverArray));
        logger.info("NamespaceArray={}", Arrays.toString(namespaceArray));

        // Read the value of attribute the ServerStatus variable component
        ServerStatusDataType serverStatus = serverNode.getServerStatus().get();

        logger.info("ServerStatus={}", serverStatus);

        // Get a typed reference to the ServerStatus variable
        // component and read value attributes individually
        ServerStatusNode serverStatusNode = serverNode.getServerStatusNode().get();
        BuildInfo buildInfo = serverStatusNode.getBuildInfo().get();
        DateTime startTime = serverStatusNode.getStartTime().get();
        DateTime currentTime = serverStatusNode.getCurrentTime().get();
        ServerState state = serverStatusNode.getState().get();

        logger.info("ServerStatus.BuildInfo={}", buildInfo);
        logger.info("ServerStatus.StartTime={}", startTime);
        logger.info("ServerStatus.CurrentTime={}", currentTime);
        logger.info("ServerStatus.State={}", state);

        future.complete(client);
    }

}